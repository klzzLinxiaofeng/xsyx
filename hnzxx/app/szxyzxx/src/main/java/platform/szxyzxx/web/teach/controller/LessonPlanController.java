package platform.szxyzxx.web.teach.controller;
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
import platform.education.generalTeachingAffair.model.LessonPlan;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.LessonPlanService;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.LessonPlanCondition;
import platform.education.generalTeachingAffair.vo.LessonPlanVo;
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
@RequestMapping("/generalTeachingAffair/lessonplan")
public class LessonPlanController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/lessonPlan";
	
	@Autowired
	@Qualifier("lessonPlanService")
	private LessonPlanService lessonPlanService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") LessonPlanCondition condition,
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
		
		List<LessonPlanVo> items = null;
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			items = this.lessonPlanService.findMoreByCondition(condition, page, order);
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
	public List<LessonPlan> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") LessonPlanCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.lessonPlanService.findLessonPlanByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(Model model,@CurrentUser UserInfo user) {
		SchoolTermCurrent termCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		model.addAttribute("termCurrent", termCurrent);
		return new ModelAndView(structurePath("/input"),model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(LessonPlan lessonPlan, @CurrentUser UserInfo user) {
		Teacher teacher = teacherService.findOfUser(user.getSchoolId(), user.getId());
		if(teacher != null){
			lessonPlan.setCreateDate(new Date());
			lessonPlan.setIsDeleted(false);
			lessonPlan.setModifyDate(new Date());
			lessonPlan.setTeacherId(teacher.getId());
			lessonPlan.setSchoolId(user.getSchoolId());
			lessonPlan = this.lessonPlanService.add(lessonPlan);
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);//该用户不是教师，上传教案失败
		}
		return lessonPlan != null ? new ResponseInfomation(lessonPlan.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		LessonPlan lessonPlan = this.lessonPlanService.findLessonPlanById(id);
		SchoolYear schoolYear = null;
		SchoolTerm schoolTerm = null;
		
		if(lessonPlan.getSchoolYear() != null && !"".equals(lessonPlan.getSchoolYear())){
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setYear(lessonPlan.getSchoolYear());
			schoolYearCondition.setSchoolId(lessonPlan.getSchoolId());
			schoolYearCondition.setIsDelete(false);
			List<SchoolYear> list = this.schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, null);
			if(list.size() > 0){
				schoolYear = list.get(0);
			}
		}
		
		if(lessonPlan.getTermCode() != null && !"".equals(lessonPlan.getTermCode())){
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			if(schoolYear != null){
				schoolTermCondition.setSchoolYearId(schoolYear.getId());
				schoolTermCondition.setCode(lessonPlan.getTermCode());
				schoolTermCondition.setSchoolId(lessonPlan.getSchoolId());
				schoolTermCondition.setIsDelete(false);
				List<SchoolTerm> list = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
				if(list.size() > 0){
					schoolTerm = list.get(0);
				}
			}
		}
		
		if(lessonPlan.getFileId() != null && !"".equals(lessonPlan.getFileId())){
			FileResult entity = this.fileService.findFileByUUID(lessonPlan.getFileId());
			if(entity != null){
				model.addAttribute("entity", entity);
			}
		}
		
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolTerm", schoolTerm);
		model.addAttribute("lessonPlan", lessonPlan);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		LessonPlan lessonPlan = this.lessonPlanService.findLessonPlanById(id);
		SchoolYear schoolYear = null;
		SchoolTerm schoolTerm = null;
		
		if(lessonPlan.getSchoolYear() != null && !"".equals(lessonPlan.getSchoolYear())){
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setYear(lessonPlan.getSchoolYear());
			schoolYearCondition.setSchoolId(lessonPlan.getSchoolId());
			schoolYearCondition.setIsDelete(false);
			List<SchoolYear> list = this.schoolYearService.findSchoolYearByCondition(schoolYearCondition, null, null);
			if(list.size() > 0){
				schoolYear = list.get(0);
			}
		}
		
		if(lessonPlan.getTermCode() != null && !"".equals(lessonPlan.getTermCode())){
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			if(schoolYear != null){
				schoolTermCondition.setSchoolYearId(schoolYear.getId());
				schoolTermCondition.setCode(lessonPlan.getTermCode());
				schoolTermCondition.setSchoolId(lessonPlan.getSchoolId());
				schoolTermCondition.setIsDelete(false);
				List<SchoolTerm> list = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
				if(list.size() > 0){
					schoolTerm = list.get(0);
				}
			}
		}
		
		if(lessonPlan.getFileId() != null && !"".equals(lessonPlan.getFileId())){
			FileResult entity = this.fileService.findFileByUUID(lessonPlan.getFileId());
			if(entity != null){
				model.addAttribute("entity", entity);
			}
		}
		
		model.addAttribute("schoolYear", schoolYear);
		model.addAttribute("schoolTerm", schoolTerm);
		model.addAttribute("isCK", "disable");
		model.addAttribute("lessonPlan", lessonPlan);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, LessonPlan lessonPlan) {
		if (lessonPlan != null) {
			lessonPlan.setId(id);
			lessonPlan.setIsDeleted(true);
		}
		try {
			this.lessonPlanService.modify(lessonPlan);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, LessonPlan lessonPlan) {
		lessonPlan.setId(id);
		lessonPlan = this.lessonPlanService.modify(lessonPlan);
		return lessonPlan != null ? new ResponseInfomation(lessonPlan.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
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
//		List<Teacher> teacherList = this.teacherService.findActiveTeacherOfSchool(user.getSchoolId());
		List<Teacher> teacherList = this.teacherService.findTeacherListBySchoolId(user.getSchoolId());
		model.addAttribute("teacherList", teacherList);
		return new ModelAndView(structurePath("/lessonCount"));
	}
	
	//根据教师统计获取数据
	@RequestMapping(value = "/countByTeacher")
	@ResponseBody
	public List<LessonPlanVo> countByTeacher(@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "teacherId", required = false) String teacherId){
		List<LessonPlanVo> LessonPlanVoList = this.lessonPlanService.findCountNumberByTeacher(user.getSchoolId(),schoolYear,termCode,teacherId);
		return LessonPlanVoList;
	}
	
	//根据学科统计
	@RequestMapping(value = "/getTeacherSubjectData")
	@ResponseBody
	public Map<String,Integer> getTeacherSubjectData(@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termCode", required = false) String termCode){
		Map<String,Integer> map = new HashMap<String,Integer>();
		List<LessonPlanVo> list = this.lessonPlanService.findCountNumberBySubject(user.getSchoolId(),schoolYear,termCode);
		for(LessonPlanVo lesson : list){
			map.put(lesson.getSubjectName(), lesson.getCountNumber());
		}
		return map;
	}
	
	//根据学科统计
	@RequestMapping(value = "/findCountNumberByGrade")
	@ResponseBody
	public Map<String,LessonPlanVo> findCountNumberByGrade(@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termCode", required = false) String termCode){
		Map<String,LessonPlanVo> map = new HashMap<String,LessonPlanVo>();
		List<LessonPlanVo> list = this.lessonPlanService.findCountNumberByGrade(user.getSchoolId(),schoolYear,termCode);
		for(LessonPlanVo lesson : list){
			map.put(lesson.getGradeCode(), lesson);
		}
		
		Map<String, LessonPlanVo> resultMap = sortMapByKey(map);
		if(resultMap == null){
			resultMap = new HashMap<String, LessonPlanVo>();
		}
		
		return resultMap;
	}
	
	private void conditionFilter(UserInfo user, LessonPlanCondition condition) {
	}
	
	/** 
     * 使用 Map按key进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, LessonPlanVo> sortMapByKey(Map<String, LessonPlanVo> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }  
        Map<String, LessonPlanVo> sortMap = new TreeMap<String, LessonPlanVo>(new MapKeyComparator());  
        sortMap.putAll(map);
        return sortMap;
    }  
  
}
