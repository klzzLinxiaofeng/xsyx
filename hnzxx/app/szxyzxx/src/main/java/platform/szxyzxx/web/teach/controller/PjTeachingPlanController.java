package platform.szxyzxx.web.teach.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.PjTeachingPlan;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.PjTeachingPlanService;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanCondition;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanVo;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/generalTeachingAffair/pjteachingplan")
public class PjTeachingPlanController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/teachingPlan";
	
	@Autowired
	@Qualifier("pjTeachingPlanService")
	private PjTeachingPlanService pjTeachingPlanService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PjTeachingPlanCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		
		condition.setIsDeleted(false);
		condition.setSchoolId(user.getSchoolId());
		
		if(type==null || "".equals(type) || !type.equals("gl")){
			Teacher teacher = teacherService.findOfUser(user.getSchoolId(), user.getId());
			if(teacher != null){
				condition.setTeacherId(teacher.getId());
			}
		}
		
		List<PjTeachingPlanVo> items = null;
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			items = this.pjTeachingPlanService.findMoreByCondition(condition, page, order);
		} else {
			viewPath = structurePath("/index");
			
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolId(user.getSchoolId());
			gradeCondition.setDelete(false);
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
			model.addAttribute("gradeList", gradeList);
		}
		
		if(type!=null && type.equals("gl")){
			List<Teacher> teacherList = this.teacherService.findTeacherListBySchoolId(user.getSchoolId());
			model.addAttribute("teacherList", teacherList);
		}
		
		model.addAttribute("type", type);
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<PjTeachingPlan> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") PjTeachingPlanCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.pjTeachingPlanService.findPjTeachingPlanByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model,@CurrentUser UserInfo user) {
		SchoolTermCurrent termCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCurrent", termCurrent);
		return new ModelAndView(structurePath("/input"),model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(PjTeachingPlan pjTeachingPlan, @CurrentUser UserInfo user) {
		Teacher teacher = teacherService.findOfUser(user.getSchoolId(), user.getId());
		if(teacher != null){
			pjTeachingPlan.setCreateDate(new Date());
			pjTeachingPlan.setIsDeleted(false);
			pjTeachingPlan.setModifyDate(new Date());
			pjTeachingPlan.setTeacherId(teacher.getId());
			pjTeachingPlan.setSchoolId(user.getSchoolId());
			pjTeachingPlan = this.pjTeachingPlanService.add(pjTeachingPlan);
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);//该用户不是教师，上传教案失败
		}
		return pjTeachingPlan != null ? new ResponseInfomation(pjTeachingPlan.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		
		PjTeachingPlan teacherPlan = this.pjTeachingPlanService.findPjTeachingPlanById(id);
		SchoolYear schoolYear = null;
		SchoolTerm schoolTerm = null;
		
		if(teacherPlan.getSchoolYear() != null && !"".equals(teacherPlan.getSchoolYear())){
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setYear(teacherPlan.getSchoolYear());
			schoolYearCondition.setSchoolId(teacherPlan.getSchoolId());
			schoolYearCondition.setIsDelete(false);
			List<SchoolYear> list = this.schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, null);
			if(list.size() > 0){
				schoolYear = list.get(0);
			}
		}
		
		if(teacherPlan.getTermCode() != null && !"".equals(teacherPlan.getTermCode())){
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			if(schoolYear != null){
				schoolTermCondition.setSchoolYearId(schoolYear.getId());
				schoolTermCondition.setCode(teacherPlan.getTermCode());
				schoolTermCondition.setSchoolId(teacherPlan.getSchoolId());
				schoolTermCondition.setIsDelete(false);
				List<SchoolTerm> list = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
				if(list.size() > 0){
					schoolTerm = list.get(0);
				}
			}
		}
		
		if(teacherPlan.getFileId() != null && !"".equals(teacherPlan.getFileId())){
			FileResult entity = this.fileService.findFileByUUID(teacherPlan.getFileId());
			if(entity != null){
				model.addAttribute("entity", entity);
			}
		}
		
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolTerm", schoolTerm);
		model.addAttribute("pjTeachingPlan", teacherPlan);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		PjTeachingPlan teacherPlan = this.pjTeachingPlanService.findPjTeachingPlanById(id);
		SchoolYear schoolYear = null;
		SchoolTerm schoolTerm = null;
		
		if(teacherPlan.getSchoolYear() != null && !"".equals(teacherPlan.getSchoolYear())){
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setYear(teacherPlan.getSchoolYear());
			schoolYearCondition.setSchoolId(teacherPlan.getSchoolId());
			schoolYearCondition.setIsDelete(false);
			List<SchoolYear> list = this.schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, null);
			if(list.size() > 0){
				schoolYear = list.get(0);
			}
		}
		
		if(teacherPlan.getTermCode() != null && !"".equals(teacherPlan.getTermCode())){
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			if(schoolYear != null){
				schoolTermCondition.setSchoolYearId(schoolYear.getId());
				schoolTermCondition.setCode(teacherPlan.getTermCode());
				schoolTermCondition.setSchoolId(teacherPlan.getSchoolId());
				schoolTermCondition.setIsDelete(false);
				List<SchoolTerm> list = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
				if(list.size() > 0){
					schoolTerm = list.get(0);
				}
			}
		}
		
		if(teacherPlan.getFileId() != null && !"".equals(teacherPlan.getFileId())){
			FileResult entity = this.fileService.findFileByUUID(teacherPlan.getFileId());
			if(entity != null){
				model.addAttribute("entity", entity);
			}
		}
		
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolTerm", schoolTerm);
		model.addAttribute("isCK", "disable");
		model.addAttribute("pjTeachingPlan", teacherPlan);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, PjTeachingPlan pjTeachingPlan) {
		if (pjTeachingPlan != null) {
			pjTeachingPlan.setId(id);
			pjTeachingPlan.setIsDeleted(true);
		}
		try {
			this.pjTeachingPlanService.modify(pjTeachingPlan);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PjTeachingPlan pjTeachingPlan) {
		pjTeachingPlan.setId(id);
		pjTeachingPlan = this.pjTeachingPlanService.modify(pjTeachingPlan);
		return pjTeachingPlan != null ? new ResponseInfomation(pjTeachingPlan.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	//根据学年查询学期
		@RequestMapping(value = "/getTermBySchoolYear")
		@ResponseBody
		public List<SchoolTerm> getTermBySchoolYear(@CurrentUser UserInfo user,@RequestParam(value = "schoolYear", required = false) String schoolYear){
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			schoolTermCondition.setIsDelete(false);
			schoolTermCondition.setSchoolId(user.getSchoolId());
			schoolTermCondition.setSchoolYear(schoolYear);
			List<SchoolTerm> schoolTermList = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
			return schoolTermList;
		}
		
		//根据学年查询年级
		@RequestMapping(value = "/getGradeBySchoolYear")
		@ResponseBody
		public List<Grade> getGradeBySchoolYear(@CurrentUser UserInfo user,@RequestParam(value = "schoolYear", required = false) String schoolYear){
			List<Grade> gradeList = this.gradeService.findGradeBySchoolYear(user.getSchoolId(),schoolYear);
			return gradeList;
		}
		
		//根据年级查询科目
		@RequestMapping(value = "/getSubjectByGradeCode")
		@ResponseBody
		public List<SubjectGrade> getSubjectByGradeCode(@CurrentUser UserInfo user,@RequestParam(value = "gradeCode", required = false) String gradeCode){
			List<SubjectGrade> SubjectGradeList = this.subjectGradeService.findSubjectGradeByGradeCode(user.getSchoolId(), gradeCode);
			return SubjectGradeList;
		}
		
		//去到教案统计页面
		@RequestMapping(value = "/lessonCountPage")
		public ModelAndView toLessonCountPage(Model model,@CurrentUser UserInfo user){
//			List<Teacher> teacherList = this.teacherService.findActiveTeacherOfSchool(user.getSchoolId());
			List<Teacher> teacherList = this.teacherService.findTeacherListBySchoolId(user.getSchoolId());
			model.addAttribute("teacherList", teacherList);
			return new ModelAndView(structurePath("/lessonCount"));
		}
		
		//根据教师统计获取数据
		@RequestMapping(value = "/countByTeacher")
		@ResponseBody
		public List<PjTeachingPlanVo> countByTeacher(@CurrentUser UserInfo user,
				@RequestParam(value = "schoolYear", required = false) String schoolYear,
				@RequestParam(value = "termCode", required = false) String termCode,
				@RequestParam(value = "teacherId", required = false) String teacherId){
			List<PjTeachingPlanVo> LessonPlanVoList = this.pjTeachingPlanService.findCountNumberByTeacher(user.getSchoolId(),schoolYear,termCode,teacherId);
			return LessonPlanVoList;
		}
		
		//根据学科统计
		@RequestMapping(value = "/getTeacherSubjectData")
		@ResponseBody
		public Map<String,Integer> getTeacherSubjectData(@CurrentUser UserInfo user,
				@RequestParam(value = "schoolYear", required = false) String schoolYear,
				@RequestParam(value = "termCode", required = false) String termCode){
			Map<String,Integer> map = new HashMap<String,Integer>();
			List<PjTeachingPlanVo> list = this.pjTeachingPlanService.findCountNumberBySubject(user.getSchoolId(),schoolYear,termCode);
			for(PjTeachingPlanVo lesson : list){
				map.put(lesson.getSubjectName(), lesson.getCountNumber());
			}
			return map;
		}
		
		//根据学科统计
		@RequestMapping(value = "/findCountNumberByGrade")
		@ResponseBody
		public Map<String,PjTeachingPlanVo> findCountNumberByGrade(@CurrentUser UserInfo user,
				@RequestParam(value = "schoolYear", required = false) String schoolYear,
				@RequestParam(value = "termCode", required = false) String termCode){
			Map<String,PjTeachingPlanVo> map = new HashMap<String,PjTeachingPlanVo>();
			List<PjTeachingPlanVo> list = this.pjTeachingPlanService.findCountNumberByGrade(user.getSchoolId(),schoolYear,termCode);
			for(PjTeachingPlanVo lesson : list){
				map.put(lesson.getGradeCode(), lesson);
			}
			
			Map<String, PjTeachingPlanVo> resultMap = sortMapByKey(map);
			if(resultMap == null){
				resultMap = new HashMap<String, PjTeachingPlanVo>();
			}
			return resultMap;
		}
		
		/** 
	     * 使用 Map按key进行排序 
	     * @param map 
	     * @return 
	     */  
	    public static Map<String, PjTeachingPlanVo> sortMapByKey(Map<String, PjTeachingPlanVo> map) {  
	        if (map == null || map.isEmpty()) {  
	            return null;  
	        }  
	        Map<String, PjTeachingPlanVo> sortMap = new TreeMap<String, PjTeachingPlanVo>(new MapKeyComparator());  
	        sortMap.putAll(map);
	        return sortMap;
	    }  
	  
		private String structurePath(String subPath) {
			return viewBasePath + subPath;
		}
		
		private void conditionFilter(UserInfo user, PjTeachingPlanCondition condition) {
		}
}

	//比较器类  
	class MapKeyComparator implements Comparator<String>{  
	    public int compare(String str1, String str2) {  
	        return str1.compareTo(str2);  
	    }  
	} 
