package platform.szxyzxx.web.teach.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.KnowledgeCatalog;
import platform.education.generalcode.model.KnowledgeVersion;
import platform.education.generalcode.service.KnowledgeCatalogService;
import platform.education.generalcode.service.KnowledgeVersionService;
import platform.education.generalcode.vo.KnowledgeCatalogCondition;
import platform.education.generalcode.vo.KnowledgeCatalogVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.TreeVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;




@Controller
@RequestMapping("/teach/catalog")
public class KnowledgeCatalogController { 
	
	private final static String viewBasePath = "/teach/knowledgeCatalog";
	
	@Resource
	private KnowledgeCatalogService knowledgeCatalogService;
	
	@Resource
	private KnowledgeVersionService knowledgeVersionService;
	
	@RequestMapping(value = "/tree/json")
	@ResponseBody
	public List<TreeVo> getTreeJsonData(
			@CurrentUser UserInfo user,
			@RequestParam(value = "usePage", required = false, defaultValue = "0") String usePage,
			@RequestParam(value = "useOrder", required = false, defaultValue = "0") String useOrder,
			@ModelAttribute("condition") KnowledgeCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
			List<KnowledgeCatalog> kcs = knowledgeCatalogService.findKnowledgeCatalogByCondition(condition, "1".equals(usePage) ? page : null, "1".equals(useOrder) ? order : null);
			return kcsToTreeVo(kcs);
	}
	
	/**
	 * 
	 * @Method creator
	 * @Function 功能描述：跳转者创建页面
	 * @param parentId
	 *            父资源ID
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(
			@RequestParam(value = "caller", required = false) String caller,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "code", required = false) String code,
			Model model) {
		KnowledgeCatalog knowledgeCatalog = this.knowledgeCatalogService.findKnowledgeCatalogById(parentId);
		if(code != null && !"".equals(code)){
			model.addAttribute("code", code);
		}
		model.addAttribute("parent", knowledgeCatalog);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	/**
	 * 导出Excel
	 * @param knowledgeVersionCode
	 * @param response
	 * @param request
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/exportExcel")
	@ResponseBody
	public void downLoadExcel(
			@RequestParam(value = "knowledgeVersionCode", required = false) String knowledgeVersionCode
			,HttpServletResponse response,
			HttpServletRequest request){
		
			String filename = "知识点信息"+".xls";
			KnowledgeCatalogCondition condition = new KnowledgeCatalogCondition(); 
			List<KnowledgeCatalog> resultList = new ArrayList<KnowledgeCatalog>();
			if(knowledgeVersionCode != null && !"".equals(knowledgeVersionCode.trim())){
				if(knowledgeVersionCode.indexOf(",") > 0){
					String[] codes = knowledgeVersionCode.split(",");
					for(String code : codes){
						condition.setKnowledgeVersionCode(code);
						List<KnowledgeCatalog> catalogList = this.knowledgeCatalogService.findKnowledgeCatalogByCondition(condition);
						if(catalogList.size() > 0){
							for(KnowledgeCatalog catalog : catalogList){
								resultList.add(catalog);
							}
						}
					}
				}else{
					condition.setKnowledgeVersionCode(knowledgeVersionCode);
					List<KnowledgeCatalog> catalogList = this.knowledgeCatalogService.findKnowledgeCatalogByCondition(condition);
					if(catalogList.size() > 0){
						for(KnowledgeCatalog catalog : catalogList){
							resultList.add(catalog);
						}
					}
				}
			}
			List<Object> list = new ArrayList<Object>();
			if(resultList.size() > 0){
				for (KnowledgeCatalog vo : resultList) {
					KnowledgeCatalogVo catalogVo = new KnowledgeCatalogVo();
					BeanUtils.copyProperties(vo, catalogVo);
					if(vo.getParentId() != null && vo.getParentId() > 0){
						KnowledgeCatalog catalog = knowledgeCatalogService.findKnowledgeCatalogById(vo.getParentId());
						catalogVo.setParentName(catalog.getName());
					}else{
						catalogVo.setParentName("无");
					}
					list.add(catalogVo);
				}
			}
			ParseConfig config = SzxyExcelTookit.getConfig("knowledgeCatalog");
			config.setSheetTitle("知识点数据");
			try {
				SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping("/toUpload")
	public ModelAndView toUpload(@RequestParam("knowledgeVersionCode") String knowledgeVersionCode,Model model){
		model.addAttribute("knowledgeVersionCode", knowledgeVersionCode);
		return new ModelAndView(structurePath("/upLoadInfoPage"), model.asMap());
	}
	
	@RequestMapping("/upLoadInfo")
	@ResponseBody
	public ResponseInfomation upLoadInfo(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@RequestParam(value = "knowledgeVersionCode", required = false) String knowledgeVersionCode,
			@CurrentUser UserInfo userInfo,HttpServletResponse response,HttpServletRequest request){
		
		//错误信息列表
//		List<KnowledgeCatalogVo> errorVoList = new ArrayList<KnowledgeCatalogVo>();
		
		String fileName = fileUpload.getOriginalFilename();//获取文件名
		InputStream is= null;
		try {
			is = fileUpload.getInputStream();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			ParseConfig config = SzxyExcelTookit.getConfig("knowledgeCatalog");
			
			List<Object> catalogVoList = SzxyExcelTookit.excelDataToModels(config, is, suffix);
			List<KnowledgeCatalogVo> list = new ArrayList<KnowledgeCatalogVo>();
			for (Object object : catalogVoList) {
				KnowledgeCatalogVo knowledgeCatalogVo = (KnowledgeCatalogVo)object;
				KnowledgeCatalog knowledgeCatalog = new KnowledgeCatalog();
				//校验，添加内容
				if(knowledgeCatalogVo.getName() != null && 
						knowledgeCatalogVo.getParentName() != null && 
								!"".equals(knowledgeCatalogVo.getParentName()) &&
								knowledgeCatalogVo.getLevel() != null &&
										knowledgeCatalogVo.getLevel() >= 0 &&
											knowledgeCatalogVo.getSort() != null &&
												knowledgeCatalogVo.getSort() >= 0){
					//添加内容
					knowledgeCatalog.setKnowledgeVersionCode(knowledgeVersionCode);
					knowledgeCatalog.setName(knowledgeCatalogVo.getName());
					knowledgeCatalog.setLevel(knowledgeCatalogVo.getLevel());
					knowledgeCatalog.setSort(knowledgeCatalogVo.getSort());
					if("无".equals(knowledgeCatalogVo.getParentName())){
						knowledgeCatalog.setParentId(0);
						this.knowledgeCatalogService.add(knowledgeCatalog);
					}else{
						list.add(knowledgeCatalogVo);
					}
				}else{
					return new ResponseInfomation("知识点名称，"+"父知识点名称，"+"知识点层次，"+"内容必填，知识点层次要大于等于0，且为数字", ResponseInfomation.OPERATION_SUC);
				}
			}
			ACompartor ac = new ACompartor();
			Collections.sort(list,ac);
			for(KnowledgeCatalogVo vo : list){
				Integer level = vo.getLevel();
				KnowledgeCatalog knowledgeCatalog = new KnowledgeCatalog();
				knowledgeCatalog.setKnowledgeVersionCode(knowledgeVersionCode);
				knowledgeCatalog.setName(vo.getName());
				knowledgeCatalog.setLevel(level);
				knowledgeCatalog.setSort(vo.getSort());
				String parentName = vo.getParentName();
				KnowledgeCatalogCondition condition = new KnowledgeCatalogCondition();
				condition.setName(parentName);
				List<KnowledgeCatalog> nameList = knowledgeCatalogService.findKnowledgeCatalogByCondition(condition);
				if(nameList.size() > 0){
					Integer parentId = 0;
					for(KnowledgeCatalog kc: nameList){
						Integer cId = kc.getId();
						if(cId > parentId){
							parentId = cId;
						}
					}
					knowledgeCatalog.setParentId(parentId);
				}else{
					return new ResponseInfomation("父知识点错误，导入失败", ResponseInfomation.OPERATION_FAIL);
				}
				this.knowledgeCatalogService.add(knowledgeCatalog);
			}
		} catch (IOException e) {
			return new ResponseInfomation("导入失败", ResponseInfomation.OPERATION_FAIL);
		}

		return new ResponseInfomation("导入成功", ResponseInfomation.OPERATION_SUC);
	}
	
	//自定义排序
	static class ACompartor implements Comparator<KnowledgeCatalogVo>{

		public int compare(KnowledgeCatalogVo vo1, KnowledgeCatalogVo vo2) {
			Integer level1 = vo1.getLevel();
			Integer level2 = vo2.getLevel();
			if(level1 >= level2){
				return -1;
			}else{
				return 1;
			}
		}

	}
	/**
	 * 检查名称是否可用
	 * @param name
	 * @param opera
	 * @param id
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "checker", method = RequestMethod.GET)
	@ResponseBody
	public boolean checker(
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "opera", required = true) String opera,
			@RequestParam(value = "id", required = false) Integer id) {
		boolean isExist = true;
		try {
			name = URLDecoder.decode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		KnowledgeCatalogCondition condition = new KnowledgeCatalogCondition();
		condition.setName(name);
		List<KnowledgeCatalog> list = knowledgeCatalogService.findKnowledgeCatalogByCondition(condition);
		if("save".equals(opera)){
			if(list.size() > 0){
				isExist = false;
			}
		}else{
			KnowledgeCatalog catalog = knowledgeCatalogService.findKnowledgeCatalogById(id);
			if(catalog.getName().equals(name)){
				isExist = true;
			}else{
				if(list.size() > 0){
					isExist = false;
				}
			}
		}
		return isExist;
	}
	
	/**
	 * 
	 * @Method creator
	 * @Function 功能描述：post请求 即创建菜单
	 * @param permission
	 * @return 	
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(KnowledgeCatalog knowledgeCatalog, @CurrentUser UserInfo user) {
		if(knowledgeCatalog.getParentId() == 0){
			knowledgeCatalog.setLevel(0);
		}else{
			KnowledgeCatalog catalog = knowledgeCatalogService.findKnowledgeCatalogById(knowledgeCatalog.getParentId());
			knowledgeCatalog.setLevel(catalog.getLevel() + 1);
		}
		knowledgeCatalog = this.knowledgeCatalogService.add(knowledgeCatalog);
		return knowledgeCatalog != null ? new ResponseInfomation(knowledgeCatalog.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeCatalog> jsonList(@CurrentUser UserInfo user, 
			@ModelAttribute("condition") KnowledgeCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return knowledgeCatalogService.findKnowledgeCatalogByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/list/parentJson", method = RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeCatalog> parentJsonList(@CurrentUser UserInfo user, 
			@ModelAttribute("condition") KnowledgeCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		order.setProperty("level");
		order.setAscending(true);
		condition.setParentId(0);
		List<KnowledgeCatalog> list = knowledgeCatalogService.findKnowledgeCatalogByCondition(condition, page, order);
		if(list.size() > 0){
			for(KnowledgeCatalog catalog : list){
				KnowledgeVersion version = knowledgeVersionService.findKnowledgeVersionByCode(catalog.getKnowledgeVersionCode());
				if(version != null){
					catalog.setKnowledgeVersionCode(version.getName());
				}
			}
		}
		return list;
	}
	@RequestMapping(value = "/list/sonJson", method = RequestMethod.GET)
	@ResponseBody
	public List<KnowledgeCatalog> sonJsonList(@CurrentUser UserInfo user, 
			@ModelAttribute("condition") KnowledgeCatalogCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		order.setProperty("level");
		order.setAscending(true);
		List<KnowledgeCatalog> list = knowledgeCatalogService.findByParentId(condition.getParentId(), order);
		if(list.size() > 0){
			for(KnowledgeCatalog catalog : list){
				KnowledgeVersion version = knowledgeVersionService.findKnowledgeVersionByCode(catalog.getKnowledgeVersionCode());
				if(version != null){
					catalog.setKnowledgeVersionCode(version.getName());
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * @Method editor
	 * @Function 功能描述：跳转者修改页面
	 * @param id 菜单ID
	 * @param model
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		KnowledgeCatalog catalog = this.knowledgeCatalogService.findKnowledgeCatalogById(id);
		if(catalog != null ){
			if(catalog.getParentId() != null && catalog.getParentId() != 0){
				model.addAttribute("parent", knowledgeCatalogService.findKnowledgeCatalogById(catalog.getParentId()));
			}
		}
		model.addAttribute("catalog", catalog);
		return new ModelAndView(structurePath("/edit"), model.asMap());
	}

	/**
	 * 
	 * @Method delete
	 * @Function 功能描述：删除某菜单
	 * @param id
	 * @param permission
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, KnowledgeCatalog knowledgeCatalog) {
		knowledgeCatalog = this.knowledgeCatalogService.findKnowledgeCatalogById(id);
		return this.knowledgeCatalogService.deleteByRecursive(knowledgeCatalog);
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	public KnowledgeCatalog getItem(@PathVariable(value = "id") Integer id) {
		KnowledgeCatalog knowledgeCatalog = this.knowledgeCatalogService.findKnowledgeCatalogById(id);
		return knowledgeCatalog;
	}

	/**
	 * 
	 * @Method edit
	 * @Function 功能描述：更新某菜单
	 * @param id
	 * @param permission
	 * @return
	 * @Date 2014年7月28日
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			KnowledgeCatalog knowledgeCatalog) {
		knowledgeCatalog.setId(id);
		knowledgeCatalog = this.knowledgeCatalogService.modify(knowledgeCatalog);
		return knowledgeCatalog != null ? new ResponseInfomation(knowledgeCatalog.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	/**
	 * 功能描述：查找父知识点
	 * @param id
	 * @param knowledgeCatalog
	 * @return
	 * @Author 陈业强
	 */
	@RequestMapping(value = "/parent/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String parentCatalog(@PathVariable(value = "id") Integer id) {
		KnowledgeCatalog knowledgeCatalog = knowledgeCatalogService.findKnowledgeCatalogById(id);
		String catalogIds = "";
		if(knowledgeCatalog != null){
			catalogIds = id + "";
//			if(knowledgeCatalog.getParentId() != 0){
//				knowledgeCatalog = knowledgeCatalogService.findKnowledgeCatalogById(knowledgeCatalog.getParentId());
//			}
			while(knowledgeCatalog.getParentId() != 0){
				knowledgeCatalog = knowledgeCatalogService.findKnowledgeCatalogById(knowledgeCatalog.getParentId());
				catalogIds = knowledgeCatalog.getId() + "," + catalogIds;
			}
		}
		return catalogIds;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	private List<TreeVo> kcsToTreeVo(List<KnowledgeCatalog> kcs) {
		List<TreeVo> treeVos = new ArrayList<TreeVo>();
		Page page = new Page();
		page.setPageSize(1);
		for(KnowledgeCatalog kc : kcs) {
			TreeVo vo = new TreeVo();
			vo.setId(String.valueOf(kc.getId()));
			vo.setpId(String.valueOf(kc.getParentId()));
			vo.setName(kc.getName());
			List<KnowledgeCatalog> knowledgeCatalogs = this.knowledgeCatalogService.findByParentId(Integer.parseInt(vo.getId()), page);
			vo.setIsParent(knowledgeCatalogs != null && knowledgeCatalogs.size() > 0 ? true : false);
			treeVos.add(vo);
		}
		return treeVos;
	}
}
