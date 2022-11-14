package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.model.TextbookVersion;
import platform.education.generalcode.vo.ResTextbookCatalogVo;
import platform.education.generalcode.vo.ResTextbookCondition;
import platform.education.generalcode.vo.TextbookCatalogVo;
import platform.education.generalcode.vo.TextbookCondition;
import platform.education.generalcode.vo.TextbookVersionCondition;
import platform.education.resource.model.CatalogResource;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.vo.CatalogResourceCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/teach/textBookMaster/master")
public class TextbookMasterController extends BaseController {

	private final static String viewBasePath = "/teach/textBookMaster/master";
	private final static String VersionPath = "/teach/textBookMaster/version";
	    @Autowired
	    @Qualifier("catalogResourceService")
	    private CatalogResourceService catalogResourceService;
	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<Textbook> items = this.jcTextbookMasterService
				.findTextbookByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		String initSelect = "stageCode";
		mav.addObject(initSelect + "Map",
				this.findTextBook(user,null, null, null, null, initSelect));
		
		mav.addObject("items", items);
		mav.setViewName(viewPath);
		return mav;
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Textbook> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") TextbookCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {

		page = usePage ? page : null;
		return this.jcTextbookMasterService.findTextbookByCondition(condition,
				page, order);
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Textbook jcTextbook,
			@CurrentUser UserInfo user) {
		ResponseInfomation responseInfomation = new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC);
		Textbook jcTextbookEnd = null;
		try {
			jcTextbookEnd = this.jcTextbookMasterService.add(jcTextbook);

		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("添加教材信息错误",
					e));
			e.printStackTrace();
		}
		if (jcTextbookEnd == null) {
			responseInfomation = new ResponseInfomation(
					ResponseInfomation.OPERATION_FAIL);
		}

		return responseInfomation;
	}

	/**
	 * 错误信息
	 * 
	 * @param e
	 * @return
	 */
	private String getMessage(String error, Exception e) {
		String message = e.getMessage().length() >= 10 ? e.getMessage()
				.substring(0, 10) : e.getMessage();
		return error + ":" + message;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		Textbook jcTextbook = this.jcTextbookMasterService.findTextbookById(id);

		mav.setViewName(structurePath("/input"));
		mav.addObject("jcTextbook", jcTextbook);

		return mav;
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		Textbook jcTextbook = this.jcTextbookMasterService.findTextbookById(id);
		mav.addObject("isCK", "disable");
		mav.addObject("jcTextbook", jcTextbook);
		mav.setViewName(structurePath("/input"));

		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			Textbook jcTextbook) {
		if (jcTextbook != null) {
			jcTextbook.setId(id);
		}
		try {
			this.jcTextbookMasterService.remove(jcTextbook);
		} catch (Exception e) {
			return getMessage("删除教材信息错误", e);
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			Textbook jcTextbook) {
		jcTextbook.setId(id);
		jcTextbook = this.jcTextbookMasterService.modify(jcTextbook);
		return jcTextbook != null ? new ResponseInfomation(jcTextbook.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	@RequestMapping(value = "/contenteditor", method = RequestMethod.GET)
	public ModelAndView contenteditor(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();

		TextbookCatalog jcTextbookCatalog = this.jcTextbookMasterService
				.findTextbookCatalogById(id);
		mav.addObject("jcTextbookCatalog", jcTextbookCatalog);
		mav.setViewName(structurePath("/cataloginput"));

		return mav;
	}

	@RequestMapping(value = "/contentviewer", method = RequestMethod.GET)
	public ModelAndView contentviewer(
			@RequestParam(value = "id", required = true) Integer id) {

		ModelAndView mav = new ModelAndView();

		TextbookCatalog jcTextbookCatalog = this.jcTextbookMasterService
				.findTextbookCatalogById(id);
		mav.addObject("isCK", "disable");
		mav.addObject("jcTextbookCatalog", jcTextbookCatalog);
		mav.setViewName(structurePath("/cataloginput"));

		return mav;
	}

	@RequestMapping(value = "/cataloglist", method = RequestMethod.GET)
	public ModelAndView cataloglist() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName(structurePath("/cataloglist"));

		return mav;
	}

	@RequestMapping(value = "/textBook")
	@ResponseBody
	public Map<String, String> findTextBook(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "type", required = false) String type) {
		String[] stageCodes = null;
		if(user != null&&user.getStageCode()!=null&user.getStageCodes().length>0){
			stageCodes = user.getStageCodes();
		}
		
		Map<String, String> map =this.jcTextbookMasterService.findTextBook(stageCodes,stageCode,subjectCode, gradeCodeVolumn, version, type);
		
		return map;
	}
	
	@RequestMapping(value = "/resTextBook")
	@ResponseBody
	public Map<String, String> findResTextBook(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "type", required = false) String type) {
		String[] stageCodes = null;
		if(user != null&&user.getStageCode()!=null&user.getStageCodes().length>0){
			stageCodes = user.getStageCodes();
		}
		Map<String, String> map =this.resTextbookService.findTextBook(stageCodes,stageCode,subjectCode, gradeCodeVolumn, version, type,user.getSchoolId());
		
		return map;
	}
	
	
	@RequestMapping(value = "/textBookCatalog")
	@ResponseBody
	public TextbookCatalogVo findTextBookCatalog(
			@RequestParam(value = "id", required = false) Integer id) {
		TextbookCatalogVo vo = new TextbookCatalogVo();
		if (id == null || id == 0) {

		} else {
			vo = this.jcTextbookMasterService.findTextbookCatalogList(id);
		}

		return vo;
	}
	
	@RequestMapping(value = "/resTextBookCatalog")
	@ResponseBody
	public ResTextbookCatalogVo findResTextBookCatalog(
			@RequestParam(value = "id", required = false) Integer id) {
		ResTextbookCatalogVo vo = new ResTextbookCatalogVo();
		if (id == null || id == 0) {

		} else {
			vo = this.resTextbookCatalogService.findTextbookCatalogList(id);
		}

		return vo;
	}

	@RequestMapping(value = "/textBookCatalogSelect")
	@ResponseBody
	public List<TextbookCatalog> findTextBookCatalogSelect(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "type", required = false) String type) {
		List<TextbookCatalog> resultList = new ArrayList<TextbookCatalog>();

		TextbookCondition condition = new TextbookCondition();
		condition.setStageCode(stageCode);
		condition.setSubjectCode(subjectCode);
		condition.setGradeCode(gradeCode);
		condition.setVolumn(volumn);
		// condition.setPublisherId(version);
		condition.setVersion(version);
		List<Textbook> list = this.jcTextbookService
				.findTextbookByCondition(condition);
		if (list.size() != 1) {

		} else {
			Integer textBookId = list.get(0).getId();
			resultList = this.jcTextbookMasterService
					.findTextBookCatalogSelect(textBookId, parentId, type);
		}
		return resultList;
	}
	
	@RequestMapping(value = "/resTextBookCatalogSelect")
	@ResponseBody
	public List<ResTextbookCatalog> findResTextBookCatalogSelect(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "parentId", required = false) Integer parentId,
			@RequestParam(value = "type", required = false) String type) {
		List<ResTextbookCatalog> resultList = new ArrayList<ResTextbookCatalog>();
		/**根据校本教材上一级目录获取下一级目录*/
		ResTextbookCondition condition = new ResTextbookCondition();
		condition.setStageCode(stageCode);
		condition.setSubjectCode(subjectCode);
		condition.setGradeCode(gradeCode);
		condition.setVolumn(volumn);
		// condition.setPublisherId(version);
		condition.setVersion(version);
		condition.setResourceLibId(user.getSchoolId());
		List<ResTextbook> list = this.resTextbookService
				.findResTextbookByCondition(condition);
		if (list.size() == 0) {

		} else {
			Integer textBookId = list.get(0).getId();
			resultList = this.resTextbookCatalogService
					.findResTextBookCatalogSelect(textBookId, parentId, type);
		}
		
		return resultList;
	}


	@RequestMapping(value = "/textBookCatalogList")
	@ResponseBody
	public List<TextbookCatalogVo> findTextBookCatalogList(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "catalogId", required = false) Integer catalogId) {
		TextbookCatalogVo root = new TextbookCatalogVo();
		List<TextbookCatalogVo> resultList = new ArrayList<TextbookCatalogVo>();
		if (type != null && "select".equals(type)) {

			TextbookCondition condition = new TextbookCondition();
			condition.setStageCode(stageCode);
			condition.setSubjectCode(subjectCode);
			
			if(gradeCode != null&&"0".equals(gradeCode)){
				gradeCode = "";
			}
			condition.setGradeCode(gradeCode);
			condition.setVolumn(volumn);
			// condition.setPublisherId(version);
			condition.setVersion(version);
			List<Textbook> list = this.jcTextbookService
					.findTextbookByCondition(condition);
			if (list.size() != 1) {

			} else {
				catalogId = list.get(0).getId();
			}
		}
		if (catalogId != null && catalogId > 0) {
			root = this.jcTextbookMasterService
					.findTextbookCatalogList(catalogId);

		}
		if (root.getId() != null && root.getId() > 0) {
			resultList = this.jcTextbookMasterService
					.nodeOutListExcludeQuote(root);
		}

		return resultList;
	}
	
	@RequestMapping(value = "/resTextBookCatalogList")
	@ResponseBody
	public List<ResTextbookCatalogVo> findResTextBookCatalogList(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCode", required = false) String gradeCode,
			@RequestParam(value = "volumn", required = false) String volumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type,
			@CurrentUser UserInfo user,
			@RequestParam(value = "catalogId", required = false) Integer catalogId) {
		ResTextbookCatalogVo root = new ResTextbookCatalogVo();
		List<ResTextbookCatalogVo> resultList = new ArrayList<ResTextbookCatalogVo>();
		if (type != null && "select".equals(type)) {

			ResTextbookCondition condition = new ResTextbookCondition();
			condition.setStageCode(stageCode);
			condition.setSubjectCode(subjectCode);
			
			if(gradeCode != null&&"0".equals(gradeCode)){
				gradeCode = "";
			}
			condition.setGradeCode(gradeCode);
			condition.setVolumn(volumn);
			condition.setResourceLibId(user.getSchoolId());
			// condition.setPublisherId(version);
			condition.setVersion(version);
			List<ResTextbook> list = this.resTextbookService
					.findResTextbookByCondition(condition);
			if (list.size() == 0) {

			} else {
				catalogId = list.get(0).getId();
			}
		}
		if (catalogId != null && catalogId > 0) {
			root = this.resTextbookCatalogService
					.findTextbookCatalogList(catalogId);

		}
		if (root.getId() != null && root.getId() > 0) {
			resultList = this.resTextbookCatalogService
					.nodeOutListExcludeQuote(root);
		}

		return resultList;
	}

	public static void main(String[] args) {
		int parentId = 2;
		for (int i = 0; i < 3 && parentId != 2; i++) {
			System.out.println(i);
		}
	}
	@RequestMapping(value = "/versionIndex")
	public ModelAndView versonIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookVersionCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<TextbookVersion> items = this.jcTextbookVersionService
				.findTextbookVersionByCondition(condition,page,order);
		if ("list".equals(sub)) {
			viewPath = VersionPath+"/list";
		} else {
			viewPath = VersionPath+"/index";
		}
		mav.addObject("items", items);
		mav.setViewName(viewPath);
		return mav;
	}
	@RequestMapping(value = "/input")
	@ResponseBody
	public ModelAndView creatorVersion() {
		return new ModelAndView(VersionPath+"/input");
	}

	@RequestMapping(value = "/creatorVersion", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creatorVersion(TextbookVersion jcTextbookVersion,
			@CurrentUser UserInfo user) {
		ResponseInfomation responseInfomation = new ResponseInfomation(
				ResponseInfomation.OPERATION_SUC);
		List<TextbookVersion> lists=new ArrayList<TextbookVersion>();
		TextbookVersionCondition condition=new TextbookVersionCondition();
		if(jcTextbookVersion.getName()!=null){
			condition.setName(jcTextbookVersion.getName());
			lists=this.jcTextbookVersionService.findTextbookVersionByCondition(condition);
			if(lists==null||lists.size()==0){
				
			}else{
				responseInfomation = new ResponseInfomation(
						ResponseInfomation.DATA_REPEAT);
				return responseInfomation;
			}
		}
		TextbookVersion jcTextbookVersionEnd = null;
		try {
			jcTextbookVersionEnd  = this.jcTextbookVersionService.add(jcTextbookVersion);

		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("添加版本信息错误",
					e));
			e.printStackTrace();
		}
		if (jcTextbookVersionEnd  == null) {
			responseInfomation = new ResponseInfomation(
					ResponseInfomation.OPERATION_FAIL);
		}

		return responseInfomation;
	}
	@RequestMapping(value = "/deleteVersion")
	@ResponseBody
	public String deleteVersion(@RequestParam(value = "id") Integer id) {
		try {
			
			TextbookVersion version=this.jcTextbookVersionService.findTextbookVersionById(id);
			CatalogResourceCondition condition=new CatalogResourceCondition();
			condition.setVersionCode(String.valueOf(version.getId()));
		       List<CatalogResource>lists=new ArrayList<CatalogResource>();
	            lists=this.catalogResourceService.findCatalogResourceByCondition(condition);
	            if(lists!=null&&lists.size()>0){
	            	return ResponseInfomation.NO_DELETE;
	            }else{
	            	this.jcTextbookVersionService.remove(version);
	            }
		} catch (Exception e) {
			return getMessage("删除教材信息错误", e);
		}
		return ResponseInfomation.OPERATION_SUC;
	}
}
