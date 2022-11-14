package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.SubjectVo;
import platform.education.generalcode.model.Stage;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
/**
 * 科目
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/subject")
public class SubjectController extends BaseController{

	private static final Logger log = LoggerFactory.getLogger(SubjectController.class);
	/**
	 * 课程列表
	 * @return
	 */
	@RequestMapping("/subjectList")
	public ModelAndView getSubjectList(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			@ModelAttribute("subjectCondition") SubjectCondition subjectCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			order.setAscending(true);
			order.setProperty("list_order");
			subjectCondition.setSchoolId(user.getSchoolId());
			List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, order);
			//getSubjectList(subjectList);
			if("list".equals(sub)){
				viewPath="/teach/subject/list";
			}else{
				viewPath="/teach/subject/subjectList";
			}
			mav.addObject("subjectList", toVos(subjectList));
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("..课程列表查询异常..");
			e.printStackTrace();
		}
		return mav;
	}
	
//	public List<Subject> getSubjectList(List<Subject> subjectList){
//		List<Subject> subjectListTemp = new ArrayList<Subject>();
//		for(int i=0;i<subjectList.size();i++){
//			Subject subject = subjectList.get(i);
//			System.out.println(subject.getStageCode());
//		}
//		return subjectListTemp;
//	}
	
	public SubjectVo toVo(Subject subject) {
		SubjectVo vo = new SubjectVo();
		BeanUtils.copyProperties(subject, vo);
		String stageCode = subject.getStageCode();
		String[] stageCodeArr = stageCode.split(",");
		Stage stage = null;    //jc_stage
		String stageName = "";
		for(String stageCodeTemp : stageCodeArr) {
			stage = this.jcStageService.findStageByCode(stageCodeTemp);
			if(stage != null) {
				stageName = stageName + stage.getName() + ";";
			}
		}
		stageName = stageName.substring(0, stageName.length() - 1);
		vo.setStageNames(stageName);
		return vo;
	}
	
	public List<SubjectVo> toVos(List<Subject> subjects) {
		List<SubjectVo> vos = new ArrayList<SubjectVo>();
		for(Subject subject : subjects) {
			vos.add(toVo(subject));
		}
		return vos;
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Subject> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SubjectCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		
		if(condition.getSchoolId()==null){
			condition.setSchoolId(user.getSchoolId());
		}else{
			condition.setSchoolId(condition.getSchoolId());
		}
		condition.setIsDelete(false);
		
		page = usePage ? page : null;
	
		List<Subject> list = subjectService.findSubjectByCondition(condition, page, order);
		
		
		return list;
	}
	
	/**
	 * 修改科目
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifySubject")
	public ModelAndView modifySubject(@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = null;
		try{
		    mav = new ModelAndView();
		    Subject subject = subjectService.findSubjectById(Integer.parseInt(id));
			mav.addObject("subject", subject);
			mav.setViewName("/teach/subject/modifySubject");
		}catch(Exception e){
			log.info("修改科目异常.....");
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 更新科目
	 * @param grade
	 * @return
	 */
	@RequestMapping("/updateSubject")
	@ResponseBody
	public String updateSubject(Subject subject){
		try{
			subject.setModifyDate(new Date());
			subjectService.modify(subject);
		}catch(Exception e){
			log.info("更新科目异常.....");
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	
	/**
	 * 删除科目
	 * @param id
	 */
	@RequestMapping("/deleteSubject")
	@ResponseBody
	public ResponseInfomation deleteSubject(@RequestParam(value="id",required=true) String id){
		try{
			Subject subject = new Subject();
			subject.setId(Integer.parseInt(id));
			boolean isDelete = true;// 默认为0，否则为true
			subject.setIsDelete(isDelete);
			subjectService.modify(subject);
		}catch(Exception e){
			log.info("..删除科目异常..");
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	
	/**
	 * 新增科目页面
	 * @return
	 */
	@RequestMapping("/addSubjectPage")
	public ModelAndView addSubjectPage(){
		ModelAndView mav = new ModelAndView();
		List<Stage> stageList = jcStageService.findAll();
		mav.addObject("stageList", stageList);
		mav.setViewName("/teach/subject/addSubjectPage");
		return mav;
	}
	
	//科目排序
	@RequestMapping(value = "/orderSubject", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation saveDepartTeacherSort(
			@RequestParam("subjectIds") String subjectIds,
			@CurrentUser UserInfo user) {
		try{
			String[] teachersId = subjectIds.split(",");
			this.subjectService.updateSubjectSort(teachersId,user.getSchoolId());
		}catch(Exception e){
			return new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	
	/**
	 * 保存科目
	 * @param grade
	 */
	@RequestMapping("/addSubject")
	@ResponseBody
	public ResponseInfomation addSubject(Subject subject,
			@CurrentUser UserInfo userInfo){
		String subjectName = "";
		platform.education.generalcode.model.Subject s =null;
//		platform.education.generalcode.model.Subject jcSubject =  null;
		try{
			String subClass = subject.getSubjectClass();
			if(subClass=="1" || "1".equals(subClass)){//公共课程
//				jcSubject = this.jcSubjectService.findByCode(Integer.parseInt(subject.getPublicSubject()));
//				subjectName = jcSubject.getName();
				s = this.jcSubjectService.findByCode(Integer.parseInt(subject.getPublicSubject()));
				subjectName = s.getName();
			}else if(subClass=="2" || "2".equals(subClass)){//地方课程
				subjectName = subject.getName();
				s = saveJcSubject(subject);
			}else if(subClass=="3" || "3".equals(subClass)){//校内课程
				subjectName = subject.getName();
				s = saveJcSubject(subject);
			}
			subject.setName(subjectName);
			subject.setSchoolId(userInfo.getSchoolId());
			subject.setCreateDate(new Date());
			subject.setModifyDate(new Date());
			subject.setCode(String.valueOf(s.getCode()));
			subject.setIsDelete(false);
			subject.setSubjectClass(s.getSubjectClass());
			subject.setSubjectType(s.getSubjectType());
			subject = subjectService.add(subject);
			
			/* 创建课程成功后，设置年级科目  */
			if(subject != null) {
				setSubjectGrade(subject);
			}
			
		}catch(Exception e){
			log.info("----保存科目异常----");
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	
	/**
	 * 创建课程成功后，设置年级科目 
	 * @param subject
	 */
	public void setSubjectGrade(Subject subject) {
		List<SchoolSystem> schoolSystemList = this.schoolSystemService.findDefaultGradesOfSchool(subject.getSchoolId());
		for(SchoolSystem schoolSystem : schoolSystemList) {
			SubjectGrade subjectGrade = new SubjectGrade();
			subjectGrade.setSchoolId(subject.getSchoolId());
			subjectGrade.setStageCode(schoolSystem.getStageCode());  //学段Code
			subjectGrade.setGradeCode(schoolSystem.getGradeCode());  //年级Code
			subjectGrade.setSubjectCode(subject.getCode());          //科目Code
			subjectGrade.setSubjectName(subject.getName());          //科目名称
			subjectGrade.setDelete(false);
			subjectGrade.setCreateDate(new Date()); 
			subjectGrade.setModifyDate(new Date());
			subjectGrade = this.subjectGradeService.add(subjectGrade);
		}
	}
	
	private void conditionFilter(UserInfo user, SubjectCondition condition) {
		Integer schoolId = condition.getSchoolId();
		if(user != null && schoolId == null) {
			condition.setSchoolId(user.getSchoolId());
		}
	}
	
	public platform.education.generalcode.model.Subject saveJcSubject(Subject subject){
		platform.education.generalcode.model.Subject s = null;
		try{
			platform.education.generalcode.model.Subject jcsubject = new platform.education.generalcode.model.Subject();
			jcsubject.setCreateDate(new Date());
			jcsubject.setName(subject.getName());
			jcsubject.setStageCode(subject.getStageCode());
			jcsubject.setSubjectCharacter(subject.getSubjectCharacter());
			jcsubject.setSubjectClass(subject.getSubjectClass());
			jcsubject.setSubjectType(subject.getSubjectType());
			jcsubject.setModifyDate(new Date());
			s = this.jcSubjectService.addFromTeach(jcsubject);
		}catch(Exception e){
			e.printStackTrace();
		}
		return s;
	}
	
	/***
	 * 根据学段，异步加载基础科目
	 * @param stageCode
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getAjaxPubjectSubjectList", method = RequestMethod.POST)  
	@ResponseBody
	public List<platform.education.generalcode.model.Subject> getAjaxPubjectSubjectList(
			@RequestParam(value = "stageCode", required = true) String stageCode,
			@CurrentUser UserInfo user){
		List<platform.education.generalcode.model.Subject> jcSubjectList = this.jcSubjectService.findByStageCode(stageCode);
		return jcSubjectList;
	}
	
	@RequestMapping(value = "/getPublicSubject") 
	@ResponseBody
	public List<platform.education.generalcode.model.Subject> getPublicSubject(@CurrentUser UserInfo user) {
		//获得本学校的学段
		String stageCode = user.getStageCode();   
		String[] stageCodeArr = stageCode.split(",");
		List<platform.education.generalcode.model.Subject> jcSubjectList = new ArrayList<platform.education.generalcode.model.Subject>();
		//获得jc_suject表中的公共课程
		platform.education.generalcode.vo.SubjectCondition subjectCondition = new platform.education.generalcode.vo.SubjectCondition();
		subjectCondition.setSubjectClass("1");
		List<platform.education.generalcode.model.Subject> jcSubList = jcSubjectService.findSubjectByCondition(subjectCondition, null, null);
		for(int i = 0; i < jcSubList.size(); i++){
			platform.education.generalcode.model.Subject jcSubject = jcSubList.get(i);
			//获得公共课程所属的学段
			String stageTemp = jcSubject.getStageCode();
//			platform.education.generalcode.model.Subject jcSubjectTemp = null;
			//公共课程所属学段与本学校的学段比较，匹配的则在jcSubjectList中添加该科目
			for(int k = 0; k < stageCodeArr.length; k++){ 
//				jcSubjectTemp = new platform.education.generalcode.model.Subject();
				String stageStr = stageCodeArr[k];
				if(stageTemp.contains(stageStr)){
					jcSubjectList.add(jcSubject);
					break;
				}
			}
		}
		return jcSubjectList;
	}
	
	/***
	 * 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "checkerSubject", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerSubject(@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "publicSubject") String publicSubject,
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = false) Integer id){
		boolean isExist = false;
		if ("publicSubject".equals(dxlx)) {
			Subject subject = this.subjectService.findUnique(user.getSchoolId(), publicSubject);
			
			if(subject==null){
				isExist = true;
			}else{
				Integer currentId = subject.getId();
				if (currentId != null && currentId == id) {
					isExist = true;
				} else {
					isExist = false;
				}
			}
		}
		return isExist;
	}
	
//	@RequestMapping(value = "checkerPublicSubject", method = RequestMethod.GET)
//	@ResponseBody
//	public boolean checkerPublicSubject(@RequestParam(value = "dxlx", required = false) String dxlx,
//			@RequestParam(value = "publicSubject") String publicSubject,
//			@CurrentUser UserInfo user) {
//		boolean isExist = false;
//		if ("publicSubject".equals(dxlx)) {
//			Subject subject = this.subjectService.findUnique(user.getSchoolId(), publicSubject);
//			Integer currentId = subject.getId();
//			if(subject==null){
//				isExist = true;
//			}else{
//				if (currentId != null && currentId == id) {
//					isExist = true;
//				} else {
//					isExist = false;
//				}
//			}
//		}
//		return isExist;
//	}
	
	
	/**
	 * @throws UnsupportedEncodingException *
	 * 
	 */
	@RequestMapping(value = "checkerSubjectName", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerSubjectName(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "stageCode") String stageCode,
			@RequestParam(value = "subjectClass") String subjectClass,
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = false) Integer id) throws UnsupportedEncodingException{
		String nameTemp =  URLDecoder.decode(name.trim(),"utf-8");
		boolean isExist = false;
		if ("name".equals(dxlx)) {
//			List<Subject> subjectList = this.subjectService.findSubjectByName(nameTemp,user.getSchoolId(),stageCode,null);
			List<Subject> subjectList = this.subjectService.findSubjectByName(nameTemp,user.getSchoolId(),null,null);
			if(subjectList.isEmpty()){
				isExist = true;
			}else{
				if(id!=null && !"".equals(id)){
					Subject subject = subjectList.get(0);
					Integer currentId = subject.getId();
					System.out.println("currentId:"+currentId+"id:"+id);
					if (currentId != null && currentId.equals(id)) {
						isExist = true;
					} else {
						isExist = false;
					}
				}else{
					isExist = false;
				}
			}
		}
		return isExist;
	}
	
	
	@RequestMapping(value = "checkerSubjectClass", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerSubjectClass(@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "subjectClass") String subjectClass,
			@CurrentUser UserInfo user){
		
		if ("subjectClass".equals(dxlx)) {
			if("1".equals(subjectClass) || subjectClass=="1"){
				return false;
			}
			return true;
		}
		return true;
	}
}
