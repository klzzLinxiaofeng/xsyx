package platform.szxyzxx.web.teach.controller;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.model.TextbookPublisher;
import platform.education.generalcode.model.TextbookVersion;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.model.Writer;
import platform.education.generalcode.vo.ResTextbookCondition;
import platform.education.generalcode.vo.TextbookCatalogCondition;
import platform.education.generalcode.vo.TextbookCatalogVo;
import platform.education.generalcode.vo.TextbookCondition;
import platform.education.generalcode.vo.TextbookPublisherCondition;
import platform.education.generalcode.vo.TextbookVersionCondition;
import platform.education.generalcode.vo.TextbookVo;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;


@Controller
@RequestMapping("/teach/textBookMaster/textBook")
public class TextbookController  extends BaseController{ 
	
	private final static String viewBasePath = "/teach/textBookMaster/textBook";

	
	/*
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty() : "modify_time");
		//??????????????????
		page.setPageSize(20);
		List<TextbookVo>  textbookVoList = new ArrayList<TextbookVo>(20);
		List<Textbook> textbookList =  null;
		if(condition.getKeyword() == null||"".equals(condition.getKeyword())){
			String gradeVolmn = condition.getGradeCodeVolumn();
			if(gradeVolmn != null&&!"".equals(gradeVolmn)){
				String[] array = gradeVolmn.split("-");
				if(array != null&&array.length == 2){
					condition.setGradeCode(array[0]);
					condition.setVolumn(array[1]);
				}
			}
			textbookList = this.jcTextbookService.findTextbookByCondition(condition, page, order);
		}else{
			textbookList = this.jcTextbookService.findFuzzyTextbookByKeyword(condition.getKeyword(), page, order);
		}
		
		
		try {
			TextbookVersionCondition textbookVersionCondition = new TextbookVersionCondition();
			TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
			ResTextbookCondition resTextBookcondition = new ResTextbookCondition();
			for (Textbook textbook : textbookList) {//?????????????????????
				TextbookVo bookvo = jcTextbookCatalogService.textBookHasCatalog(textbook);
				
				if(textbook.getPublisherId() != null&& textbook.getPublisherId()>0){
					TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(textbook.getPublisherId());
					bookvo.setPublishName(publish.getName());
				}else{
					 textbookVersionCondition.setId(Integer.parseInt(textbook.getVersion()));
					 List<TextbookVersion> versionList = this.jcTextbookVersionService.findTextbookVersionByCondition(textbookVersionCondition);
					 if(versionList != null &&versionList.size() == 1){
						 bookvo.setPublisherId(versionList.get(0).getPublisherId());
						 TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(bookvo.getPublisherId());
						 bookvo.setPublishName(publish.getName());
					 }
				}
				
				textbookVolumnCondition.setCode(bookvo.getVolumn());
				List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
				if(volumnList != null&&volumnList.size()>0){
					bookvo.setVolumn(volumnList.get(0).getName());
				}
				
				bookvo.setIsSchool(0);
				if("resList".equals(sub) || "resFirst".equals(sub)) {
					resTextBookcondition.setUuid(textbook.getUuid());
					resTextBookcondition.setResourceLibId(user.getSchoolId());
					resTextBookcondition.setIsDelete(false);
					List<ResTextbook> resTextBookList = resTextbookService.findResTextbookByCondition(resTextBookcondition);
					if(resTextBookList !=null && resTextBookList.size()==1) {
						bookvo.setIsSchool(1);
					}
				}
				//?????????????????????
				this.setTextBookWriter(bookvo);
				
				textbookVoList.add(bookvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		if ("list".equals(sub) || "resList".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		
		if("resList".equals(sub) || "resFirst".equals(sub)) {
			mav.addObject("list", "resList");
		} else {
			mav.addObject("list", "list");
		}
		
		String initSelect = "stageCode";
		mav.addObject(initSelect+"Map", this.findTextBookNoHidden(null, null, null, null, initSelect));
		mav.addObject("textbookVoList", textbookVoList);
		mav.setViewName(viewPath);
		
		return mav;
	}
	*/
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TextbookCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty() : "modify_time");
		//??????????????????
		page.setPageSize(20);
		List<Textbook> textbookList =  null;
		if(condition.getKeyword() == null||"".equals(condition.getKeyword())){
			String gradeVolmn = condition.getGradeCodeVolumn();
			if(gradeVolmn != null&&!"".equals(gradeVolmn)){
				String[] array = gradeVolmn.split("-");
				if(array != null&&array.length == 2){
					condition.setGradeCode(array[0]);
					condition.setVolumn(array[1]);
				}
			}
			textbookList = this.jcTextbookService.findTextbookByCondition(condition, page, order);
		}else{
			textbookList = this.jcTextbookService.findFuzzyTextbookByKeyword(condition.getKeyword(), page, order);
		}
		
		List<TextbookVo> textbookVoList = null;
		try {
			/**??????????????????, ????????????, ??????, ????????????????????????*/
			textbookVoList = this.dealTextbookList(textbookList, user, sub);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if ("list".equals(sub) || "resList".equals(sub)) {
			/**???????????????????????????*/
			viewPath = structurePath("/list");
		} else {
			/**?????????????????????????????????*/
			viewPath = structurePath("/index");
		}
		
		/**????????????*/
		if("resList".equals(sub) || "resFirst".equals(sub)) {
			/**??????????????????????????????*/
			mav.addObject("list", "resList");
		} else {
			/**????????????????????????????????????*/
			mav.addObject("list", "list");
		}
		
		String initSelect = "stageCode";
		/**????????????????????????????????????*/
		mav.addObject(initSelect+"Map", this.findTextBookNoHidden(null, null, null, null, initSelect));
		mav.addObject("textbookVoList", textbookVoList);
		mav.setViewName(viewPath);
		
		return mav;
	}
	
	

	private void setTextBookWriter(TextbookVo vo) {
		List<Writer> writerList = this.writerService.findWriterByTextBookId(vo.getId());
		if(writerList != null && writerList.size()>0){
			List<Writer> writerMain = new ArrayList<Writer>(4);
			List<Writer> writerTranslator = new ArrayList<Writer>(4);
			
			for (Writer writer : writerList) {
				if(TextbookVo.WRITERMAIN.equals(writer.getType())){
					writerMain.add(writer);
				}
				if(TextbookVo.WRITERTRANSLATOR.equals(writer.getType())){
					writerTranslator.add(writer);
				}
			}
			
			vo.setWriterMain(writerMain);
			vo.setWriterTranslator(writerTranslator);
		}
	}
	/**
	 * ??????????????????????????????????????????????????????
	 * @param vo
	 * @return
	 */
	private String hasLink(TextbookVo vo){
		TextbookCatalogCondition jcTextbookCatalogCondition = new TextbookCatalogCondition();
		jcTextbookCatalogCondition.setTestBookId(vo.getId());
		jcTextbookCatalogCondition.setIsDelete(false);
		List<TextbookCatalog> textbookCatalogList = this.jcTextbookCatalogService.findTextbookCatalogByCondition(jcTextbookCatalogCondition);
		if(textbookCatalogList != null&&textbookCatalogList.size()>1){
			return TextbookVo.IS_TRUE;
		}else{
			return TextbookVo.IS_FALSE;
		}
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Textbook> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TextbookCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
			
		page = usePage ? page : null;
		return this.jcTextbookService.findTextbookByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/add"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TextbookVo jcTextbookVo, @CurrentUser UserInfo user) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		Textbook jcTextbookEnd = null;
		try {
			
			jcTextbookEnd = this.jcTextbookService.addTextbookVo(jcTextbookVo);
		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("????????????????????????",e));
			e.printStackTrace();
		}
		if(jcTextbookEnd == null){
			responseInfomation 	= new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		return responseInfomation;
	}
	/**
	 * ????????????
	 * @param e
	 * @return
	 */
	private String getMessage(String error,Exception e) {
		String message = e.getMessage().length() >=10 ?e.getMessage().substring(0, 10):e.getMessage();
		return error+":"+message;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		Textbook jcTextbook = this.jcTextbookService.findTextbookById(id);
		TextbookVo bookvo = new TextbookVo();
		try {
			bookvo = jcTextbookCatalogService.textBookHasCatalog(jcTextbook);//?????????????????????
			bookvo.setHasLink(this.hasLink(bookvo));//?????????????????????
			this.setTextBookWriter(bookvo);//????????????
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		mav.setViewName(structurePath("/modify"));
		mav.addObject("jcTextbook", bookvo);
		
		return mav;
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "sub", required = false) String sub) {
		ModelAndView mav = new ModelAndView();
		Textbook jcTextbook = this.jcTextbookService.findTextbookById(id);
		TextbookVo bookvo = new TextbookVo();
		try {
			bookvo = jcTextbookCatalogService.textBookHasCatalog(jcTextbook);//?????????????????????
			bookvo.setHasLink(this.hasLink(bookvo));//?????????????????????
			TextbookVolumnCondition textbookVolumnCondition =  null;
			if(bookvo.getVolumn() != null&&!"".equals(bookvo.getVolumn().trim())){
				   textbookVolumnCondition = new TextbookVolumnCondition();
					textbookVolumnCondition.setCode(bookvo.getVolumn());
					List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
					if(volumnList != null&&volumnList.size()>0){
						bookvo.setVolumn(volumnList.get(0).getName());
					} 
			 }
			
			if(jcTextbook.getPublisherId() != null&& jcTextbook.getPublisherId()>0){
				TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(jcTextbook.getPublisherId());
				bookvo.setPublishName(publish.getName());
			}else{

				TextbookVersionCondition textbookVersionCondition = new TextbookVersionCondition();
				textbookVersionCondition.setId(Integer.parseInt(jcTextbook.getVersion()));
				 List<TextbookVersion> versionList = this.jcTextbookVersionService.findTextbookVersionByCondition(textbookVersionCondition);
				 
				 if(versionList != null &&versionList.size() == 1){
					 bookvo.setPublisherId(versionList.get(0).getPublisherId());
					 TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(bookvo.getPublisherId());
					 bookvo.setPublishName(publish.getName());
				 }
			}
			this.setTextBookWriter(bookvo);//????????????
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		mav.addObject("isCK", "disable");
		mav.addObject("jcTextbook", bookvo);
		mav.addObject("list", sub);
		mav.setViewName(structurePath("/viewer"));
		
		return mav;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Textbook jcTextbook) {
		if (jcTextbook != null) {
			jcTextbook.setId(id);
		}
		try {
			
			Textbook textbook = this.jcTextbookService.findTextbookById(id);
			TextbookVo bookvo = new TextbookVo();
			//BeanUtils.copyProperties(bookvo, textbook);
			bookvo.setId(textbook.getId());
			String hasLink = this.hasLink(bookvo);
			if(TextbookVo.IS_TRUE.equals(hasLink)){
				return "????????????????????????????????????????????????";
			}else{
				this.jcTextbookService.remove(jcTextbook);
			}
			
		} catch (Exception e) {
			return getMessage("????????????????????????", e);
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TextbookVo jcTextbookVo) {
		Textbook textBook = null;
		jcTextbookVo.setId(id);
		try {
			textBook = this.jcTextbookService.modifyTextbookVo(jcTextbookVo);
		} catch (Exception e) {
			return new ResponseInfomation("????????????"+e.getMessage());
					
		}
		return textBook != null ? new ResponseInfomation(textBook.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	
	@RequestMapping(value = "/textBook")
	@ResponseBody
	public Map<String, String> findTextBook(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type
			) {
		String[] stageCodes = null;
		if(user != null&&user.getStageCodes().length>0){
			stageCodes = user.getStageCodes();
		}
		Map<String, String> map = new HashMap<String, String>();
		map=this.jcTextbookMasterService.findTextBook(stageCodes,stageCode, subjectCode, gradeCodeVolumn, version, type);
		return map;
	}
	
	
	@RequestMapping(value = "/findTextBookNoHidden")
	@ResponseBody
	public Map<String, String> findTextBookNoHidden(
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type) {
		
		return this.jcTextbookMasterService.findTextBookNoHidden(stageCode,
				subjectCode, gradeCodeVolumn, version, type);
	}
	
	@RequestMapping(value = "/textBookCatalog")
	@ResponseBody
	public TextbookCatalogVo findTextBookCatalog(
			@RequestParam(value = "id", required = false) Integer id
			) {
		TextbookCatalogVo vo = new TextbookCatalogVo();
		if(id == null ||id == 0){
			
		}else{
			vo =this.jcTextbookMasterService.findTextbookCatalogList(id);
		}
		
		return vo;
	}
	
	@RequestMapping(value = "/textBookVersion")
	@ResponseBody
	public Map<String, String> findTextBookVersion(
			@CurrentUser UserInfo user
			) {
		
		Map<String, String> map = new HashMap<String, String>();
		TextbookVersionCondition textbookVersionCondition = new TextbookVersionCondition();
		List<TextbookVersion> versionList = this.jcTextbookVersionService.findTextbookVersionByCondition(textbookVersionCondition);
		for (TextbookVersion textbookVersion : versionList) {
			map.put(textbookVersion.getName(), textbookVersion.getId().toString());
		}
		return map;
	}
	
	@RequestMapping(value = "/textBookPublish")
	@ResponseBody
	public Map<String, String> findTextBookPublish(@CurrentUser UserInfo user) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("?????????", "");
		TextbookPublisherCondition jcTextbookPublisherCondition = new TextbookPublisherCondition();
		List<TextbookPublisher> publishList = this.jcTextbookPublisherService.findTextbookPublisherByCondition(jcTextbookPublisherCondition);
		for (TextbookPublisher textbookPublisher : publishList) {
			map.put(textbookPublisher.getName(), textbookPublisher.getId().toString());
		}
		
		return map;
	}
	
	/**
	 * @param textbookList ???????????? 
	 * @param user ??????????????????
	 * @param sub ????????????????????????????????????????????????
	 * ??????????????????, ????????????, ??????, ????????????????????????
	 */
	private List<TextbookVo> dealTextbookList(List<Textbook> textbookList, UserInfo user, String sub) throws IllegalAccessException, InvocationTargetException {
		List<TextbookVo>  textbookVoList = new ArrayList<TextbookVo>(20);
		
		/**?????????????????????*/
		Map<Integer, Integer> textBookCatalogMap = jcTextbookCatalogService.findTextBookCatalogBatch(textbookList);
		/**??????????????????*/
		Map<String, String> textbookVersionMap = jcTextbookVersionService.findTextbookVersionBatch(textbookList);
		/**??????????????????*/
		Map<String, String> textbookVolumnMap = jcTextbookVolumnService.findTextbookVolumnBatch(textbookList);
		/**????????????????????????*/
		Map<Integer, String> textbookPublisherMap = jcTextbookPublisherService.findTextbookPublisherBatch(textbookList);
		
		ResTextbookCondition resTextBookcondition = new ResTextbookCondition();
		for (Textbook textbook : textbookList) {
			TextbookVo bookvo = new TextbookVo();
			boolean isAdd = false;
			/**?????????????????????*/
			if(textbook.getPublishDate() != null){
				
			}else{
				isAdd = true;
				textbook.setPublishDate(new Date());
			}
			
			BeanUtils.copyProperties(bookvo, textbook);
			if(isAdd){
				bookvo.setPublishDate(null);
			}
			
			/**??????????????????????????????*/
			if(textBookCatalogMap.get(textbook.getId()) != null){
				bookvo.setHasCatalog(TextbookVo.IS_TRUE);
				bookvo.setHasLink(TextbookVo.IS_TRUE);
			}else{
				bookvo.setHasCatalog(TextbookVo.IS_FALSE);
				bookvo.setHasLink(TextbookVo.IS_FALSE);
			}
			
			/**????????????????????????*/
			if(textbookPublisherMap.get(textbook.getPublisherId())!=null){
				bookvo.setPublishName(textbookPublisherMap.get(textbook.getPublisherId()));
			}else{
				bookvo.setPublishName(textbookVersionMap.get(textbook.getVersion()));
			}
			
			/**?????????????????????*/
			if(textbookVolumnMap.get(bookvo.getVolumn()) != null){
				bookvo.setVolumn(textbookVolumnMap.get(bookvo.getVolumn()));
			}
			
			/**???????????????????????????????????????*/
			if("resList".equals(sub) || "resFirst".equals(sub)) {
				resTextBookcondition.setUuid(textbook.getUuid());
				resTextBookcondition.setResourceLibId(user.getSchoolId());
				resTextBookcondition.setIsDelete(false);
				List<ResTextbook> resTextBookList = resTextbookService.findResTextbookByCondition(resTextBookcondition);
				if(resTextBookList !=null && resTextBookList.size()==1) {
					bookvo.setIsSchool(1);
				}
			} else {
				bookvo.setIsSchool(0);
			}
			
			//?????????????????????
			this.setTextBookWriter(bookvo);
			textbookVoList.add(bookvo);
		}
		return textbookVoList;
	}
	
}
