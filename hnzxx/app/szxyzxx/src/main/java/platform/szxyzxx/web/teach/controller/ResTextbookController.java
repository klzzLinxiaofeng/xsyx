package platform.szxyzxx.web.teach.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.model.Textbook;
import platform.education.generalcode.model.TextbookCatalog;
import platform.education.generalcode.model.TextbookPublisher;
import platform.education.generalcode.model.TextbookVersion;
import platform.education.generalcode.model.TextbookVolumn;
import platform.education.generalcode.model.Writer;
import platform.education.generalcode.vo.ResTextbookCatalogCondition;
import platform.education.generalcode.vo.ResTextbookCondition;
import platform.education.generalcode.vo.ResTextbookVo;
import platform.education.generalcode.vo.TextbookVersionCondition;
import platform.education.generalcode.vo.TextbookVo;
import platform.education.generalcode.vo.TextbookVolumnCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

@Controller
@RequestMapping("/teach/textBookMaster/resTextBook")
public class ResTextbookController  extends BaseController{ 
	private final static String viewBasePath = "/teach/textBookMaster/resTextbook";
	
	/**
	 * ??????????????????
	 * @param ResTextbookVo ?????????????????????
	 * @param user	??????????????????
	 * @param addType ????????????	?????????????????????????????????????????????????????????  0??????null??????????????????  1???????????????????????????
	 * @return ResponseInfomation ????????????
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(ResTextbookVo resTextbookVo, @CurrentUser UserInfo user,
			@RequestParam(value = "addType", required = false) Integer addType) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		ResTextbook resTextbookEnd = null;
		try {
			/**?????????????????????????????????*/
			if(addType!=null && addType==1) {
				/**????????????????????????*/
				Textbook textbook = jcTextbookService.findTextbookById(resTextbookVo.getId());
				/**??????uuid???????????????????????????*/
				ResTextbookCondition resTextBookcondition = new ResTextbookCondition();
				resTextBookcondition.setUuid(textbook.getUuid());
				resTextBookcondition.setIsDelete(true);
				List<ResTextbook> resTextBookList = resTextbookService.findResTextbookByCondition(resTextBookcondition);
				/**???????????????????????????????????????????????? ?????????????????????????????????is_delete??????*/
				if(resTextBookList!=null && resTextBookList.size()>0) {
					ResTextbook resTextBook = resTextBookList.get(0);
					resTextBook.setIsDelete(false);
					resTextbookService.modify(resTextBook);
					return responseInfomation;
				}
				/**??????????????????????????????????????????*/
				resTextbookService.copyTextbookProperties(textbook, resTextbookVo);
			}
			/**???????????????libraryId*/
			resTextbookVo.setResourceLibId(user.getSchoolId());
			/**????????????*/
			resTextbookEnd = this.resTextbookService.addTextbookVo(resTextbookVo,addType);
		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("????????????????????????",e));
			e.printStackTrace();
		}
		if(resTextbookEnd == null){
			responseInfomation 	= new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		
		return responseInfomation;
	}
	
	/**
	 * ??????????????????
	 * @param ResTextbookVo ?????????????????????
	 * @param user	??????????????????
	 * @param addType ????????????	?????????????????????????????????????????????????????????  0??????null??????????????????  1???????????????????????????
	 * @return ResponseInfomation ????????????
	 */
	@RequestMapping(value = "bantch/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation bantchCreator(@RequestParam(value = "data", required = false) String data,
			@CurrentUser UserInfo user,
			@RequestParam(value = "textbookId", required = false) Integer textbookId) {
		ResponseInfomation responseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		try {
			/**????????????*/
			this.resTextbookService.addTextbookBantch(data,user.getSchoolId());
		} catch (Exception e) {
			responseInfomation = new ResponseInfomation(getMessage("????????????????????????",e));
			e.printStackTrace();
		}
		return responseInfomation;
	}
	

	/**
	 * ??????????????????
	 * @param id ???????????????id
	 * @param type ????????????     1 ??????????????????????????????????????????  0 ??????????????????????????????????????????
	 * @param resTextbook entity
	 * @return ResponseInfomation
	 */
	@RequestMapping(value = "/{id}/{type}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
						 @PathVariable(value = "type") Integer type,
						 ResTextbook resTextbook) {
		/**???????????????????????????id????????????????????????*/
		if (type!=null && type == 1) {
			/** ??????????????????????????????????????????, ???????????????????????????*/
			Textbook textbook = jcTextbookService.findTextbookById(id);
			/**??????uuid???????????????????????????*/
			ResTextbookCondition resTextBookcondition = new ResTextbookCondition();
			resTextBookcondition.setUuid(textbook.getUuid());
			List<ResTextbook> resTextBookList = resTextbookService.findResTextbookByCondition(resTextBookcondition);
			resTextbook.setId(resTextBookList.get(0).getId());
		} else {
			resTextbook.setId(id);
		}
		try {
			this.resTextbookService.remove(resTextbook);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") ResTextbookCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) {
		String viewPath = null;
		ModelAndView mav = new ModelAndView();
		order.setProperty(order.getProperty() != null ? order.getProperty() : "modify_time");
		//??????????????????
		page.setPageSize(20);
		List<ResTextbookVo>  resTextbookVoList = new ArrayList<ResTextbookVo>(20);
		List<ResTextbook> resTextbookList =  null;
		if(condition.getKeyword() == null||"".equals(condition.getKeyword())){
			String gradeVolmn = condition.getGradeCodeVolumn();
			if(gradeVolmn != null&&!"".equals(gradeVolmn)){
				String[] array = gradeVolmn.split("-");
				if(array != null&&array.length == 2){
					condition.setGradeCode(array[0]);
					condition.setVolumn(array[1]);
				}
			}
			condition.setIsDelete(false);
			condition.setResourceLibId(user.getSchoolId());
			resTextbookList = this.resTextbookService.findResTextbookByCondition(condition, page, order);
		}else{
			condition.setResourceLibId(user.getSchoolId());
			resTextbookList = this.resTextbookService.findFuzzyResTextbookByKeyword(condition, page, order);
		}
		
		try {
			for (ResTextbook resTextbook : resTextbookList) {//?????????????????????
				ResTextbookVo resBookvo = resTextBookHasCatalog(resTextbook);
				if(resTextbook.getPublisherId() != null&& resTextbook.getPublisherId()>0){
					TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(resTextbook.getPublisherId());
					resBookvo.setPublishName(publish.getName());
				}else{
					
				}
				
				if(resTextbook.getPublisherId() != null&& resTextbook.getPublisherId()>0){
					TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(resTextbook.getPublisherId());
					resBookvo.setPublishName(publish.getName());
				}else{
					TextbookVersionCondition textbookVersionCondition = new TextbookVersionCondition();
					textbookVersionCondition.setId(Integer.parseInt(resTextbook.getVersion()));
					 List<TextbookVersion> versionList = this.jcTextbookVersionService.findTextbookVersionByCondition(textbookVersionCondition);
					 if(versionList != null &&versionList.size() == 1){
						 resBookvo.setPublisherId(versionList.get(0).getPublisherId());
						 TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(resBookvo.getPublisherId());
						 resBookvo.setPublishName(publish.getName());
					 }
				}
				TextbookVolumnCondition textbookVolumnCondition = new TextbookVolumnCondition();
				textbookVolumnCondition.setCode(resBookvo.getVolumn());
				List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
				if(volumnList != null&&volumnList.size()>0){
					resBookvo.setVolumn(volumnList.get(0).getName());
				}
				resTextbookVoList.add(resBookvo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		for (ResTextbookVo vo : resTextbookVoList) {//???????????????????????????????????????
			vo.setHasLink(this.hasLink(vo));
		}
		
		for (ResTextbookVo vo : resTextbookVoList) {//?????????????????????
			this.setResTextBookWriter(vo);
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		
		String initSelect = "stageCode";
		mav.addObject(initSelect+"Map", resTextbookService.findTextBookNoHidden(null, null, null, null, initSelect,user.getSchoolId()));
		mav.addObject("textbookVoList", resTextbookVoList);
		
		mav.setViewName(viewPath);
		
		return mav;
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(structurePath("/add"));
		String stageCodes[] = user.getStageCodes();
		String stage = "";
		for (String stageCode : stageCodes) {
			stage+=stageCode;
		}
		mav.addObject("stageCode", stage);
		return mav;
	}
	
	private ResTextbookVo resTextBookHasCatalog(ResTextbook resTextbook)
			throws IllegalAccessException, InvocationTargetException {
		ResTextbookVo bookvo = new ResTextbookVo();
		boolean isAdd = false;
		if(resTextbook.getPublishDate() != null){
			
		}else{
			isAdd = true;
			resTextbook.setPublishDate(new Date());
		}
		
		
		BeanUtils.copyProperties(bookvo, resTextbook);
		if(isAdd){
			bookvo.setPublishDate(null);
		}
		ResTextbookCatalogCondition catalogCondition = new ResTextbookCatalogCondition();
		catalogCondition.setTestBookId(resTextbook.getId());
		List<ResTextbookCatalog> cataLogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(catalogCondition);
		if(cataLogList != null &&cataLogList.size()>1){
			bookvo.setHasCatalog(TextbookVo.IS_TRUE);
		}else{
			bookvo.setHasCatalog(TextbookVo.IS_FALSE);
		}
		return bookvo;
	}
	
	@RequestMapping(value = "/findTextBookNoHidden")
	@ResponseBody
	public Map<String, String> findTextBookNoHidden(
			@CurrentUser UserInfo user,
			@RequestParam(value = "stageCode", required = false) String stageCode,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "gradeCodeVolumn", required = false) String gradeCodeVolumn,
			@RequestParam(value = "publisherId", required = false) String version,
			@RequestParam(value = "type", required = false) String type) {
		
		return this.resTextbookService.findTextBookNoHidden(stageCode,
				subjectCode, gradeCodeVolumn, version, type,user.getSchoolId());
	}
	
	/**
	 * ??????????????????????????????????????????????????????
	 * @param vo
	 * @return
	 */
	private String hasLink(ResTextbookVo vo){
		ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
		resTextbookCatalogCondition.setTestBookId(vo.getId());
		resTextbookCatalogCondition.setIsDelete(false);
		List<ResTextbookCatalog> resTextbookCatalogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
		if(resTextbookCatalogList != null&&resTextbookCatalogList.size()>1){
			return TextbookVo.IS_TRUE;
		}else{
			return TextbookVo.IS_FALSE;
		}
	}
	
	private void setResTextBookWriter(ResTextbookVo vo) {
		List<Writer> writerList = this.writerService.findWriterByTextBookId(vo.getId());
		if(writerList != null && writerList.size()>0){
			List<Writer> writerMain = new ArrayList<Writer>();
			List<Writer> writerTranslator = new ArrayList<Writer>();
			
			for (Writer writer : writerList) {
				if(ResTextbookVo.WRITERMAIN.equals(writer.getType())){
					writerMain.add(writer);
				}
				if(ResTextbookVo.WRITERTRANSLATOR.equals(writer.getType())){
					writerTranslator.add(writer);
				}
			}
			
			vo.setWriterMain(writerMain);
			vo.setWriterTranslator(writerTranslator);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(
			 @PathVariable(value = "id") Integer id,
			 @CurrentUser UserInfo user,
			ResTextbookVo resTextbookVo) {
		ResTextbook resTextBook = null;
		resTextbookVo.setId(id);
		resTextbookVo.setResourceLibId(user.getSchoolId());
		try {
			resTextBook = this.resTextbookService.modifyResTextbookVo(resTextbookVo);
		} catch (Exception e) {
			return new ResponseInfomation("????????????"+e.getMessage());
					
		}
		return resTextBook != null ? new ResponseInfomation(resTextBook.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		ResTextbook resTextbook = this.resTextbookService.findResTextbookById(id);
		ResTextbookVo bookvo = new ResTextbookVo();
		try {
			bookvo = textBookHasCatalog(resTextbook);//?????????????????????
			bookvo.setHasLink(this.hasLink(bookvo));//?????????????????????
			this.setResTextBookWriter(bookvo);//????????????
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		mav.setViewName(structurePath("/modify"));
		mav.addObject("resTextbook", bookvo);
		
		return mav;
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mav = new ModelAndView();
		ResTextbook resTextbook = this.resTextbookService.findResTextbookById(id);
		ResTextbookVo resBookvo = new ResTextbookVo();
		try {
			
			resBookvo = textBookHasCatalog(resTextbook);//?????????????????????
			resBookvo.setHasLink(this.hasLink(resBookvo));//?????????????????????
			TextbookVolumnCondition textbookVolumnCondition =  null;
			if(resBookvo.getVolumn() != null&&!"".equals(resBookvo.getVolumn().trim())){
				   textbookVolumnCondition = new TextbookVolumnCondition();
					textbookVolumnCondition.setCode(resBookvo.getVolumn());
					List<TextbookVolumn> volumnList = this.jcTextbookVolumnService.findTextbookVolumnByCondition(textbookVolumnCondition);
					if(volumnList != null&&volumnList.size()>0){
						resBookvo.setVolumn(volumnList.get(0).getName());
					} 
			 }
			
			if(resTextbook.getPublisherId() != null&& resTextbook.getPublisherId()>0){
				TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(resTextbook.getPublisherId());
				resBookvo.setPublishName(publish.getName());
			}else{

				TextbookVersionCondition textbookVersionCondition = new TextbookVersionCondition();
				textbookVersionCondition.setId(Integer.parseInt(resTextbook.getVersion()));
				 List<TextbookVersion> versionList = this.jcTextbookVersionService.findTextbookVersionByCondition(textbookVersionCondition);
				 
				 if(versionList != null &&versionList.size() == 1){
					 resBookvo.setPublisherId(versionList.get(0).getPublisherId());
					 TextbookPublisher  publish = this.jcTextbookPublisherService.findTextbookPublisherById(resBookvo.getPublisherId());
					 resBookvo.setPublishName(publish.getName());
				 }
			}
			this.setResTextBookWriter(resBookvo);//????????????
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		mav.addObject("isCK", "disable");
		mav.addObject("resTextbook", resBookvo);
		mav.setViewName(structurePath("/viewer"));
		
		return mav;
	}
	
	private ResTextbookVo textBookHasCatalog(ResTextbook textbook)
			throws IllegalAccessException, InvocationTargetException {
		ResTextbookVo bookvo = new ResTextbookVo();
		boolean isAdd = false;
		if(textbook.getPublishDate() != null){
			
		}else{
			isAdd = true;
			textbook.setPublishDate(new Date());
		}
		
		BeanUtils.copyProperties(bookvo, textbook);
		if(isAdd){
			bookvo.setPublishDate(null);
		}
		ResTextbookCatalogCondition catalogCondition = new ResTextbookCatalogCondition();
		catalogCondition.setTestBookId(textbook.getId());
		List<ResTextbookCatalog> resCataLogList = this.resTextbookCatalogService.findResTextbookCatalogByCondition(catalogCondition);
		if(resCataLogList != null &&resCataLogList.size()>1){
			bookvo.setHasCatalog(TextbookVo.IS_TRUE);
		}else{
			bookvo.setHasCatalog(TextbookVo.IS_FALSE);
		}
		return bookvo;
	}
	
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
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
}
