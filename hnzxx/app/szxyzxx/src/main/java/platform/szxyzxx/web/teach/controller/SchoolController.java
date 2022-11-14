package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolAttributePlatform;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.service.SchoolAttributePlatformService;
import platform.education.generalTeachingAffair.vo.SchoolAttributePlatformCondition;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalTeachingAffair.vo.SchoolSystemCondition;
import platform.education.generalcode.model.Grade;
import platform.education.generalcode.vo.GradeCondition;
import platform.education.generalcode.vo.StageVo;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.resource.model.ResourceLibrary;
import platform.education.resource.service.ResourceLibraryService;
import platform.education.resource.utils.UUIDUtil;
import platform.education.user.model.Group;
import platform.education.user.vo.GroupCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.RandomUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

/**
 * 学校
 * @author zhoujin
 *
 */


@Controller
@RequestMapping("/teach/school")
public class SchoolController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(SchoolController.class);

	@Resource(name = "addSchool_taskExecutor")
    private TaskExecutor taskExecutor;
	@Autowired
	@Qualifier("resourceLibraryService")
	private ResourceLibraryService resourceLibraryService;
	@Autowired
	@Qualifier("schoolAttributePlatformService")
	private SchoolAttributePlatformService schoolAttributePlatformService;
	
	/***
	 * 学校列表
	 * @return
	 */
	@RequestMapping("/schoolList")
	public ModelAndView getSchoolList(
			@CurrentUser UserInfo user, 
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("schoolCondition") SchoolCondition schoolCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = new ModelAndView();
		String viewPath = "";
		try{
			
			List<School> schoolList = new ArrayList<School>();
			if("list".equals(sub)){
				schoolCondition.setId(user.getSchoolId());
				schoolList = schoolService.findSchoolByCondition(schoolCondition, page, order);
				viewPath="/teach/school/list";
			}else{
				viewPath="/teach/school/schoolList";
			}
			mav.addObject("schoolList", schoolList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("学校更表查询异常.");
			//e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 修改学校
	 * @param id
	 * @return
	 */
	@RequestMapping("/schoolModify")
	public ModelAndView modifySchool(@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = new ModelAndView();
		try{
			School school =	schoolService.findSchoolById(Integer.parseInt(id));
			//List<Stage> stageList = this.jcStageService.findAll();
			String stageScope = school.getStageScope();
			String[] stage_scope = stageScope.split(",");
			List<StageVo> stageVoList = new ArrayList<StageVo>();
			StageVo  stageVo = getIsSelected(stage_scope);
			mav.addObject("stageVo", stageVo);
			mav.addObject("stageVoList", stageVoList);
			mav.addObject("school", school);
			mav.setViewName("/teach/school/schoolModify");
		}catch(Exception e){
			log.info("学校修改异常",id);
			e.printStackTrace();
		}
		return mav;
	}
	
	public StageVo getIsSelected(String[] stage_scope){
		StageVo stageVo = new StageVo();
		String stageScope0 = stage_scope[0];
		
		if(stageScope0=="2" || "2".equals(stageScope0)){
			stageVo.setStageScope_one(stageScope0);
		}else{
			stageVo.setStageScope_one("-1");
		}
		
		String stageScope1 = stage_scope[1];
		if(stageScope1=="3" || "3".equals(stageScope1)){
			stageVo.setStageScope_two(stageScope1);
		}else{
			stageVo.setStageScope_two("-1");
		}
		
		String stageScope2 = stage_scope[2];
		if(stageScope2=="4" || "4".equals(stageScope2)){
			stageVo.setStageScope_three(stageScope2);
		}else{
			stageVo.setStageScope_three("-1");
		}
		return stageVo;
	}
	
	/**
	 * 学校详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/schoolDetail")
	public ModelAndView schoolDetail(@RequestParam(value = "id", required = true) String id){
		ModelAndView mav = new ModelAndView();
		try{
			School school =	schoolService.findSchoolById(Integer.parseInt(id));
			mav.addObject("school", school);
			mav.setViewName("/teach/school/schoolDetail");
		}catch(Exception e){
			log.info("学校详情异常",id);
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 更新学校
	 * @param school
	 */
	@RequestMapping("/updateSchool")
	@ResponseBody
	
	public ResponseInfomation updateSchool(School school, @RequestParam(value = "isNameChange", required = false) boolean isNameChange){
		School sl = null;
		try{
			school.setModifyDate(new Date());
			sl = schoolService.modify(school);

			if (isNameChange) {
				Group group = groupService.findUnique(sl.getId(), "1");
				if (group != null) {
					group = new Group(group.getId());
					group.setName(sl.getName());
					groupService.modify(group);
				}
			}
		}catch(Exception e){
			log.info("更新学校异常");
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return sl != null ? new ResponseInfomation(sl.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping("/schoolAddPage")
	public ModelAndView schoolAddPage(){
		ModelAndView mav = new ModelAndView();
		//List<Stage> stageList = this.jcStageService.findAll();
		//mav.addObject("stageList", stageList);
		
		//学校归类List
		SchoolAttributePlatformCondition schoolAttributePlatformCondition = new SchoolAttributePlatformCondition();
		schoolAttributePlatformCondition.setIsDeleted(false);
		List<SchoolAttributePlatform> schoolAttributePlatformList = schoolAttributePlatformService.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition);
		
		mav.addObject("schoolAttributePlatformList", schoolAttributePlatformList);
		mav.setViewName("/teach/school/schoolAddPage");
		return mav;
	}
	
	/**
	 *  保存学校
	 * @param school
	 * @return
	 */
	@RequestMapping("/addSchool")
	@ResponseBody
	public String addSchool(School school){
		try{
			school.setCreateDate(new Date());
			school.setModifyDate(new Date());
			school.setUuid(UUIDUtil.getUUID());//生成UUID 用于OA中
			school.setDelete(false);
			school.setCode2(school.getDistrict()+RandomUtil.get4RandomUtil());
			school = this.schoolService.add(school,SysContants.SYSTEM_APP_ID,taskExecutor);
		
			//增加加入校本库的逻辑
			ResourceLibrary resourceLibrary = new ResourceLibrary();
			resourceLibrary.setUuid(UUIDUtil.getUUID()); 
			resourceLibrary.setAppId(1);
			resourceLibrary.setOwerId(school.getId());
			resourceLibrary.setName(school.getName());
			resourceLibrary.setCreateDate(new Date());
			resourceLibraryService.add(resourceLibrary);
			
		}catch(Exception e){
			log.info("..保存学校异常...");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 同步基础课程后，分别设置年级科目（2015-10-23）
	 * @param subject
	 */
	public List<SubjectGrade> setSubjectGrade(Subject subject) {
		String[] stageCodes = subject.getStageCode().split(",");
		List<SchoolSystem> schoolSystemList = new ArrayList<SchoolSystem>();
		List<SubjectGrade> subjectGradeList = new ArrayList<SubjectGrade>();
		SchoolSystemCondition schoolSystemCondition;
		for(String stageCode : stageCodes) {
			schoolSystemCondition = new SchoolSystemCondition();
			schoolSystemCondition.setSchoolId(subject.getSchoolId());
			schoolSystemCondition.setStageCode(stageCode);
			schoolSystemList = this.schoolSystemService.findSchoolSystemByCondition(schoolSystemCondition);
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
				if(subjectGrade != null) {
					subjectGradeList.add(subjectGrade);
				}
			}
		}
		return subjectGradeList;
	}
	
	/**
	 * 查检学校称是否重复
	 * @return
	 */
	@RequestMapping(value = "checkerSchool", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerSchool(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name){
		boolean isExist = false;
		if ("name".equals(dxlx)) {
			List<School> schoolList = schoolService.findSchoolByName(name);
			if(schoolList.isEmpty()){
				isExist = true;
			}else{
				isExist = false;
			}
		}
		return isExist;
	}
	
	/**
	 * 查检学校代码是否重复
	 * @return
	 */
	@RequestMapping(value = "checkerSchoolCode", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerSchoolCode(
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "code") String code){
		boolean isExist = false;
		if ("code".equals(dxlx)) {
			List<School> schoolList = schoolService.findSchoolByCode(code);
			if(schoolList.isEmpty()){
				isExist = true;
			}else{
				isExist = false;
			}
			
		}
		return isExist;
	}
	
	
	@RequestMapping("/deleteSchool")
	@ResponseBody
	public String deleteSchool(@RequestParam(value = "id", required = true) String id){
		try{
			School school = new School();
			school.setId(Integer.parseInt(id));
			school.setDelete(true);
			schoolService.modify(school);
		}catch(Exception e){
			log.info("删除学校异常");
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		
		return ResponseInfomation.OPERATION_SUC;
	}
	
	@RequestMapping(value = "/info/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SchoolInfo> schoolInfoJsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") SchoolCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return schoolService.findSchoolInfoByCondition(condition, page, order);
	}
	
	private void conditionFilter(UserInfo user, SchoolCondition condition) {
		
	}
	
}
