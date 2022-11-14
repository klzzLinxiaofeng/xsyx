package platform.szxyzxx.web.teach.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import framework.generic.facility.poi.excel.config.ParseConfig;
import net.sf.json.JSONArray;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.vo.ResTextbookCatalogVo;
import platform.education.generalcode.vo.TextbookCatalogVo;
import platform.education.generalcode.vo.ZTreeVO;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;


@Controller
@RequestMapping("/teach/textBookMaster/resTextBookCatalog")
public class ResTextbookCatalogController  extends BaseController{ 
	private Logger log = LoggerFactory.getLogger(getClass());
	private final static String viewBasePath = "/teach/textBookMaster/resTextBookCatalog";
	
	@RequestMapping(value = "/resCatalogList")
	@ResponseBody
	public ModelAndView findResTextBookCatalogList(
			@RequestParam(value = "resTextBookId", required = false) Integer resTextBookId){
		ModelAndView mav = new ModelAndView();
		/**获取校本教材目录结构*/
		ResTextbookCatalogVo root = new ResTextbookCatalogVo();
		List<ResTextbookCatalogVo> resultList = new ArrayList<ResTextbookCatalogVo>();
		if(resTextBookId != null && resTextBookId >0){
			root = this.resTextbookCatalogService.findResTextbookCatalogList(resTextBookId);
		}
		if(root.getId() != null && root.getId() >0){
			resultList = this.resTextbookCatalogService.nodeOutListExcludeQuote(root);
		}
		List<ZTreeVO> zTreeList = resTextbookCatalogService.getTree(resultList);
		mav.addObject("zTreeList", JSONArray.fromObject(zTreeList));
		if(resultList!=null && resultList.size()>0) {
			mav.addObject("parentCatalog",resultList.get(0));
		}
		/**参数回传*/
		mav.addObject("textBookId",resTextBookId);
		mav.setViewName(structurePath("/index"));
		return mav;
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation modify(ResTextbookCatalog resTextbookCatalog, @CurrentUser UserInfo user) {
		resTextbookCatalog = this.resTextbookCatalogService.modify(resTextbookCatalog);
		return resTextbookCatalog != null ? new ResponseInfomation(resTextbookCatalog.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, ResTextbookCatalog resTextbookCatalog) {
		if (resTextbookCatalog != null) {
			resTextbookCatalog.setId(id);
		}
		try {
			this.resTextbookCatalogService.remove(resTextbookCatalog);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResTextbookCatalog creator(ResTextbookCatalog resTextbookCatalog, @CurrentUser UserInfo user) {
		resTextbookCatalog = this.resTextbookCatalogService.add(resTextbookCatalog);
		return resTextbookCatalog;
	}
	
	/**
	 * 对教材目录的上级、下级、前移、后移操作进行更新 
	 * @param catalogJson	目录信息的json格式数据
	 * @return ResponseInfomation
	 */
	@RequestMapping(value = "bantch/editor", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation batchEditor(@RequestParam(value = "data", required = false)String data) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		try {
			this.resTextbookCatalogService.batchEditor(data);
		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("上移/下移模板节点出错",e));
			e.printStackTrace();
		}
		return responseInfomation;
	}
	

	@RequestMapping(value = "/downLoadModel")
	@ResponseBody
	public void downLoadModel(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		 // path是指欲下载的文件的路径。 
		 String path= session.getServletContext().getRealPath("/")+"/template/catalog_info.xls";
		 try {
			response.addHeader("Content-Disposition", "attachment;filename="
			  + new String("下载模板.xls".getBytes("GBK"),"ISO8859_1"));
			 response.setContentType("text/html");
			 PrintWriter out = response.getWriter();
			 FileInputStream in = null;
			 File f = new File(path);
			 in = new FileInputStream(f);
			 byte b[] = new byte[1024];
			 while (in.read(b, 0, 1024) != -1) {
			 out.write(new String(b, "ISO8859_1"));
			 }
			 in.close();
			 
			 out.flush();
			 out.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/downLoadExcel")
	@ResponseBody
	public void downLoadExcel(
		@RequestParam(value = "textBookId", required = false) Integer textBookId
		,HttpServletResponse response,
		HttpServletRequest request){
	
		String filename = "目录信息"+".xls";
		ResTextbookCatalogVo root = new ResTextbookCatalogVo();
		List<ResTextbookCatalogVo> resultList = new ArrayList<ResTextbookCatalogVo>();
		if(textBookId != null && textBookId >0){
			root = this.resTextbookCatalogService.findTextbookCatalogList(textBookId);
			
		}
		if(root.getId() != null && root.getId() >0){
			resultList = this.resTextbookCatalogService.nodeOutListExcludeQuote(root);
		}
	
		List<Object> list = new ArrayList<Object>();
		for (ResTextbookCatalogVo vo : resultList) {
			if(vo.getParentId()==0||vo.getLevel()==0){
				
			}else{
				//vo.setCode(a+"");
				vo.setBackCode(vo.getCode());
				list.add(vo);
			}
			
		}
		ParseConfig config = SzxyExcelTookit.getConfig("catalog");
		try {
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@RequestMapping("/upLoadInfo")
	@ResponseBody
	public  Map<String, Object>  upLoadInfo(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@RequestParam(value = "textBookId", required = false) Integer textBookId,
			@CurrentUser UserInfo userInfo,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		//错误信息列表
		List<ResTextbookCatalogVo> errorVoList = new ArrayList<ResTextbookCatalogVo>();
		
		String fileName = fileUpload.getOriginalFilename();//获取文件名
		InputStream is= null;
		try {
			is = fileUpload.getInputStream();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			ParseConfig config = SzxyExcelTookit.getConfig("catalog");
			
			List<Object> catalogVoList = SzxyExcelTookit.excelDataToModels(config, is, suffix);
			List<ResTextbookCatalogVo> list = new ArrayList<ResTextbookCatalogVo>();
			for (Object object : catalogVoList) {
				TextbookCatalogVo textbookCatalogVo = (TextbookCatalogVo)object;
				textbookCatalogVo.setCode(textbookCatalogVo.getBackCode());
				//校验，添加内容
				if(textbookCatalogVo.getName() != null && 
						/*textbookCatalogVo.getPage() != null&&
						textbookCatalogVo.getPage()>0&&*/
						textbookCatalogVo.getLevel() !=null&&
						textbookCatalogVo.getLevel()>0){
					//添加内容
					textbookCatalogVo.setTestBookId(textBookId);
				}else{
					textbookCatalogVo.setErrorInfo("目录名称，"+"目录层次，"+"内容必填，且页码和目录层次要大于0，且为数字");
					errorVoList.add(resTextbookCatalogService.copyProperties(textbookCatalogVo));
				}
				list.add(resTextbookCatalogService.copyProperties(textbookCatalogVo));
			}
			if(errorVoList.size() == 0){
				try {
					errorVoList = this.resTextbookCatalogService.addList(list);
				} catch (Exception e) {
					new ResponseInfomation("导入目录错误："+e.getMessage().substring(0, e.getMessage().length()>15?15:e.getMessage().length()));
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			map.put("status", "error");
			return map;
		}
			
		
		//错误信息excel返回给用户
		if(errorVoList.size()>0){
			
			map.put("status", "error");
			return map;
		}
				
		map.put("status", "success");
		return map;
	}
	
	@RequestMapping("/upLoadInfoPage")
	@ResponseBody
	public ModelAndView upLoadStudentAwardInfoPage(@RequestParam(value = "textBookId", required = false) Integer textBookId
			){
		ModelAndView mav = new ModelAndView();
		mav.addObject("textBookId", textBookId);
		mav.setViewName(this.structurePath("/upLoadInfoPage"));
		return mav;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	/**
	 * 错误信息
	 * @param e
	 * @return
	 */
	private String getMessage(String error,Exception e) {
		String message = e.getMessage().length() >=10 ?e.getMessage().substring(0, 10):e.getMessage();
		return error+":"+message;
	}
}
