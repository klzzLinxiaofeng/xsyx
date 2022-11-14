package platform.szxyzxx.web.teach.controller;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
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

import platform.education.clazz.model.BwSyllabus;
import platform.education.clazz.model.BwSyllabusLesson;
import platform.education.clazz.model.RoomTeam;
import platform.education.clazz.model.SchoolCourse;
import platform.education.clazz.service.BwSyllabusLessonService;
import platform.education.clazz.service.BwSyllabusService;
import platform.education.clazz.service.RoomTeamService;
import platform.education.clazz.service.SchoolCourseService;
import platform.education.clazz.vo.BwSyllabusCondition;
import platform.education.clazz.vo.BwSyllabusLessonCondition;
import platform.education.clazz.vo.SchoolCourseCondition;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.ResponseDataCarrier;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.utils.BeanUtilsSub;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.SubjectTeamTeacherVo;
import platform.education.generalTeachingAffair.vo.SyllabusCondition;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.service.ImAccountService;
import platform.education.im.service.ImProviderService;
import platform.szxyzxx.web.bbx.contants.BpCommonContants;
import platform.szxyzxx.web.bbx.contants.DataAction;
import platform.szxyzxx.web.bbx.contants.RoleGroup;
import platform.szxyzxx.web.bbx.util.IMPushUtil;
import platform.szxyzxx.web.bbx.util.SzxyExcelTookitSub;
import platform.szxyzxx.web.bbx.vo.SyllabusVo2;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
/**
 * <p>Title:SyllabusController.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：课表控制器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年5月18日
 */
@Controller
@RequestMapping("/teach/syllabus")
public class SyllabusController extends BaseController {

	@Resource(name = "bp_syllabus_taskExecutor")
	private TaskExecutor taskExecutor;

	@Autowired
	@Qualifier("pushSubscriptionDao")
	private PushSubscriptionDao pushSubscriptionDao;

	@Autowired
	@Qualifier("imProviderService")
	private ImProviderService imProviderService;

	@Autowired
	@Qualifier("imAccountService")
	private ImAccountService imAccountService;

	@Autowired
	@Qualifier("schoolCourseService")
	private SchoolCourseService schoolCourseService;
	@Autowired
	@Qualifier("bwSyllabusService")
	private BwSyllabusService bwSyllabusService;
	@Autowired
	@Qualifier("bwSyllabusLessonService")
	private BwSyllabusLessonService bwSyllabusLessonService;
	// 教师
	@Autowired
	@Qualifier("teacherService")
	protected TeacherService teacherService;
	@Autowired
	@Qualifier("roomTeamService")
	private RoomTeamService roomTeamService;

	@Autowired
	@Qualifier("schoolYearService")
	private SchoolYearService schoolYearService;

	private final static String viewBasePath = "/teach/syllabus";

	private Logger log  = LoggerFactory.getLogger(this.getClass());

	/************************************班主任修改课表********************************************************/
	@RequestMapping("/classTeacherRkap")
	public String classTeacherSyllabusManager(@CurrentUser UserInfo userInfo, Model model) {
		model.addAttribute("schoolYear", userInfo.getSchoolYear());
		model.addAttribute("termCode", userInfo.getSchoolTermCode());
		return structurePath("/classTeacher_rkap_index");
	}

	@RequestMapping(value = "/getGrade", method = RequestMethod.POST)
	@ResponseBody
	public Integer getGrade(@RequestParam(value = "teamId") Integer teamId) {
		Team team = this.teamService.findTeamById(teamId);
		if(team != null){
			return team.getGradeId();
		}
		return null;
	}
	/************************************班主任修改课表********************************************************/

	@RequestMapping("/rkap")
	public String syllabusManager() {
		return structurePath("/rkap_index");
	}

	@RequestMapping("/setting/editor")
	public String syllabusSettingEditor(
			@CurrentUser UserInfo userInfo,
			@RequestParam(value = "gradeId") Integer gradeId,
			@RequestParam(value = "teamId") Integer teamId,
			@RequestParam(value = "termCode") String termCode,
			Model model) {
		Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, termCode);
		if(syllabus != null) {
			model.addAttribute("item", syllabus);
			model.addAttribute("isSetted", "all_true");
		} else {
			if(userInfo != null) {
				Integer schoolId = userInfo.getSchoolId();
				Grade grade = this.gradeService.findGradeById(gradeId);
				if(grade != null) {
					SchoolSystem schoolSystem = this.schoolSystemService.findUnique(schoolId, grade.getStageCode(), grade.getUniGradeCode());
					if(schoolSystem != null) {
						Integer days = schoolSystem.getDays();
						String daysPlan = schoolSystem.getDaysPlan();
						if(days != null && daysPlan != null) {
							model.addAttribute("isSetted", "setting_true");
						}
						model.addAttribute("item", schoolSystem);
					}
				}
			}
		}
		return structurePath("/setting_input");
	}

	@RequestMapping(value = "/rkap/list", method = RequestMethod.GET)
	public ModelAndView list(
			@CurrentUser UserInfo user,
			@RequestParam(value="syllabusId") Integer syllabusId,
			@RequestParam(value="type", defaultValue="0") String type,
			Model model) {
		Syllabus syllabus = this.syllabusService.findSyllabusById(syllabusId);
		getSyllabusInfo(model, syllabus);
		return new ModelAndView(structurePath("/rkap_list" + type), model.asMap());
	}

	private void getSyllabusInfo(Model model, Syllabus syllabus) {
		if (syllabus != null) {
			String daysPlan = syllabus.getDaysPlan();
			if(daysPlan != null) {
				model.addAttribute("xqs", daysPlan.split(","));
			}
			Integer morningCount = syllabus.getLessonOfMorning();
			Integer afternoonCount = syllabus.getLessonOfAfternoon();
			Integer eveningCount = syllabus.getLessonOfEvening();
			model.addAttribute("item", syllabus);
			model.addAttribute("morningLessons", number2Array(morningCount, null, null));
			model.addAttribute("afternoonLessons", number2Array(morningCount  + afternoonCount, morningCount + 1, null));
			model.addAttribute("eveningLessons", number2Array(morningCount + afternoonCount + eveningCount, morningCount + afternoonCount + 1, null));
		}
	}

	@RequestMapping(value = "/searcher/team/list", method = RequestMethod.GET)
	public ModelAndView searcherList(
			@CurrentUser UserInfo user,
			@RequestParam(value="teamId") Integer teamId,
			@RequestParam(value="termCode") String termCode,
			@RequestParam(value="type", defaultValue="0") String type,
			Model model) {
		Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, termCode);
		getSyllabusInfo(model, syllabus);
		return new ModelAndView(structurePath("/rkap_list" + type), model.asMap());
	}

	@RequestMapping(value = "/searcher/teacher/list", method = RequestMethod.GET)
	public ModelAndView teacherSyllabusList(
			@CurrentUser UserInfo user,
			@RequestParam(value="teachId") Integer teachId,
			@RequestParam(value="termCode") String termCode,
			@RequestParam(value="type", defaultValue="0") String type,
			Model model) {
		return new ModelAndView(structurePath("/teacher_syllabus_list" + type), model.asMap());
	}


	@RequestMapping(value = "/teacher/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SyllabusVo> jsonList(
			@CurrentUser UserInfo user,
			@RequestParam(value="teachId") Integer teachId,
			@RequestParam(value="termCode") String termCode) {
			Integer schoolId = null;
			if(user != null) {
				schoolId = user.getSchoolId();
			}
			List<SyllabusLesson> lessons = this.syllabusService.getTeacherSyllabus(teachId, termCode);
			return syllabusLesson2SyllabusVo2(lessons, schoolId);
	}


	@RequestMapping(value = "/rkap/creator", method = RequestMethod.GET)
	public ModelAndView getCreator(@ModelAttribute("syllabusVo") SyllabusVo syllabusVo, Model model) {
		Integer teamId = syllabusVo.getTeamId();
		Integer gradeId = syllabusVo.getGradeId();
//		List<TeamTeacher> items = this.teamTeacherService.findClassTeacherByGradeIdAndTeamId(gradeId, teamId);
		List<SubjectTeamTeacherVo> items = this.subjectTeacherService.findSubjectsWithTeacher(gradeId, teamId);
		model.addAttribute("items", items);
		return new ModelAndView(structurePath("/rkap_input"), model.asMap());
	}


	@RequestMapping(value = "/rkap/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation excutelessonCreator(SyllabusLesson lesson, @CurrentUser UserInfo user) {
		//2016-8-17 添加网页端课表的时候，课程名称没有填充数据库
		Subject subject = subjectService.findUnique(user.getSchoolId(), lesson.getSubjectCode());
		if(subject != null) {
			lesson.setSubjectName(subject.getName());
		}
		Syllabus syllabus = syllabusService.findSyllabusById(lesson.getSyllabusId());
		SchoolYear schoolYear = schoolYearService.findByYearAndSchoolId(syllabus.getSchoolId(), syllabus.getSchoolYear());
		if(schoolYear != null) {
			lesson.setStartDate(schoolYear.getBeginDate());
			lesson.setEndDate(schoolYear.getFinishDate());
		}
		lesson.setDefaultFlag(0);
		lesson.setAdjustFlag(0);

		lesson = this.syllabusService.addSyllabusLesson(lesson);
		return lesson != null ? new ResponseInfomation(lesson.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/rkap/editor", method = RequestMethod.GET)
	public ModelAndView editor(@ModelAttribute("syllabusVo") SyllabusVo syllabusVo, Model model) {
		Integer teamId = syllabusVo.getTeamId();
		Integer gradeId = syllabusVo.getGradeId();
		Integer lessonId = syllabusVo.getLessonId();
		SyllabusLesson lesson = this.syllabusService.findSyllabusLessonById(lessonId);
//		List<TeamTeacher> items = this.teamTeacherService.findClassTeacherByGradeIdAndTeamId(gradeId, teamId);
		List<SubjectTeamTeacherVo> items = this.subjectTeacherService.findSubjectsWithTeacher(gradeId, teamId);
		model.addAttribute("items", items);
		model.addAttribute("lesson", lesson);
		return new ModelAndView(structurePath("/rkap_input"), model.asMap());
	}

	@RequestMapping(value = "/rkap/rules/validator", method = RequestMethod.GET)
	@ResponseBody
	public Object isAllowCreate(@CurrentUser UserInfo user, SyllabusVo vo) {
		if(user != null) {
			Integer schoolId = user.getSchoolId();
			vo.setSchoolId(schoolId);
			if(vo.getTeacherId() != null){
				ResponseDataCarrier responseDataCarrier = this.syllabusService.verifyRules(vo);
				Object responseObj = responseDataCarrier.getResponseData();
				if(responseObj != null && responseObj instanceof SyllabusVo) {
					SyllabusVo tmpKb = (SyllabusVo) responseObj;
					Integer teamId = tmpKb.getTeamId();
					Team team = this.teamService.findTeamById(teamId);
					if(team != null) {
						tmpKb.setTeamName(team.getName());
					}
					responseDataCarrier.setResponseData(tmpKb);
				}
				return responseDataCarrier;
			}else{
				ResponseDataCarrier responseDataCarrier = new ResponseDataCarrier();
				responseDataCarrier.setInfo("success");
				return responseDataCarrier;
			}
		}
		return ResponseInfomation.OPERATION_SUC;
	}


	@RequestMapping(value = "/rkap/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, SyllabusLesson lesson) {
		if(lesson != null) {
			lesson.setId(id);
		}
		try {
			this.syllabusService.removeSyllabusLesson(lesson);
		} catch (Exception e) {
			if(log.isInfoEnabled()) {
				log.info("移除课节是失败，异常信息为:", e);
				return ResponseInfomation.OPERATION_FAIL;
			}
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/rkap/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, SyllabusLesson lesson,@CurrentUser UserInfo user) {
		lesson.setId(id);
		//2016-8-17 添加网页端课表的时候，课程名称没有填充数据库
		Subject subject = subjectService.findUnique(user.getSchoolId(), lesson.getSubjectCode());
		if(subject != null){
			lesson.setSubjectName(subject.getName());
		}
		lesson = this.syllabusService.modifySyllabusLesson(lesson);
		if(lesson != null) {
			return new ResponseInfomation(lesson.getId(), ResponseInfomation.OPERATION_SUC);
		}
		return new ResponseInfomation();
	}

	@RequestMapping(value = "/rkap/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<SyllabusVo2> jsonList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "syllabusId") Integer syllabusId,
			@RequestParam(value = "schoolId") Integer schoolId,
			@RequestParam(value = "teamId") Integer teamId
			) {
			if(user != null) {
				schoolId = user.getSchoolId();
			}
			List<SchoolCourse> schoolCourseList = getSchoolCourseList(schoolId);
			List<SyllabusLesson> lessons = null;
			List<SyllabusVo2> result = new ArrayList<>();
			if ( schoolCourseList != null && schoolCourseList.size() > 0 ) { // 获取走班班级的课表信息
				List<BwSyllabusLesson> bwSyllabusLessonList = getBwSyllabusLessonList(user, schoolId, teamId);
				if ( bwSyllabusLessonList != null && bwSyllabusLessonList.size() > 0 ) {
					result = getSyllabusLessonList(bwSyllabusLessonList);
				}
			} else { // 获取行政班级的课表信息
				lessons = this.syllabusService.getSyllabusLessonBySyllabusId(syllabusId);
				getSyllabusVo2(schoolId, lessons, result, 1);
			}
			return result;
	}

	@RequestMapping(value = "/rkap/list/json2", method = RequestMethod.GET)
	@ResponseBody
	public List<SyllabusVo> jsonList2(
			@CurrentUser UserInfo user,
			@RequestParam(value = "syllabusId") Integer syllabusId) {
		List<SyllabusLesson> lessons = null;
		if(syllabusId != null){
			lessons = this.syllabusService.getSyllabusLessonBySyllabusId(syllabusId);
		}
		List<SyllabusVo> syllabusVoList = syllabusLesson2SyllabusVo(lessons, user.getSchoolId());
		return syllabusVoList;
	}

	private void getSyllabusVo2(Integer schoolId, List<SyllabusLesson> lessons, List<SyllabusVo2> result, Integer courseType) {
		List<SyllabusVo> syllabusVoList = syllabusLesson2SyllabusVo(lessons, schoolId);
		for (SyllabusVo syllabusVo : syllabusVoList) {
			SyllabusVo2 syllabusVo2 = new SyllabusVo2();
			BeanUtilsSub.copyProperties(syllabusVo2, syllabusVo);
			if ( courseType == 1 ) {
				syllabusVo2.setType(1);
			}
			result.add(syllabusVo2);
		}
	}

	private List<SyllabusVo2> getSyllabusLessonList(List<BwSyllabusLesson> bwSyllabusLessonList) {
		List<SyllabusVo2> lessons;
		lessons = new ArrayList<>();
		for (BwSyllabusLesson bwSyllabusLesson : bwSyllabusLessonList) {
			SyllabusVo2 e = new SyllabusVo2();
			e.setId(bwSyllabusLesson.getId());
			e.setLesson(bwSyllabusLesson.getLesson());
			e.setDayOfWeek(bwSyllabusLesson.getDayOfWeek());
			if ( bwSyllabusLesson.getSubjectCode() != null && bwSyllabusLesson.getSubjectName() != null ) {
				e.setSubjectCode(bwSyllabusLesson.getSubjectCode());
				e.setSubjectName(bwSyllabusLesson.getSubjectName());
			}
			if ( bwSyllabusLesson.getTeacherId() != null ) {
				e.setTeacherId(bwSyllabusLesson.getTeacherId());
				Teacher teacher = this.teacherService.getTeacherById(bwSyllabusLesson.getTeacherId());
				if(teacher != null) {
					e.setTeacherName(teacher.getName());
				}
			}
			if ( bwSyllabusLesson.getType() == 2 ) {
				e.setType(2);
			} else if ( bwSyllabusLesson.getType() == 1 ) {
				e.setType(1);
			}
			lessons.add(e);
		}
		return lessons;
	}

	private List<BwSyllabusLesson> getBwSyllabusLessonList(UserInfo user, Integer schoolId, Integer teamId) {
		List<BwSyllabusLesson> bwSyllabusLessonList = null;
		RoomTeam roomTeam = this.roomTeamService.findRoomTeamByTeamId(teamId, user.getSchoolYear());
		if ( roomTeam == null) {
			return null;
		}
		BwSyllabusCondition bwSyllabusCondition = new BwSyllabusCondition();
		//bwSyllabusCondition.setTeamId(teamId);
		bwSyllabusCondition.setRoomId(roomTeam.getRoomId());
		bwSyllabusCondition.setSchoolId(schoolId);
		List<BwSyllabus> bwSyllabusList = bwSyllabusService.findBwSyllabusByCondition(bwSyllabusCondition);
		if ( bwSyllabusList != null && bwSyllabusList.size() > 0 ) {
			BwSyllabus bwSyllabus = bwSyllabusList.get(0);
			BwSyllabusLessonCondition bwSyllabusLessonCondition = new BwSyllabusLessonCondition();
			bwSyllabusLessonCondition.setSyllabusId(bwSyllabus.getId());
			bwSyllabusLessonList = bwSyllabusLessonService.findBwSyllabusLessonByCondition(bwSyllabusLessonCondition);
		}
		return bwSyllabusLessonList;
	}

	private List<SchoolCourse> getSchoolCourseList(Integer schoolId) {
		SchoolCourseCondition schoolCourseCondition = new SchoolCourseCondition();
		schoolCourseCondition.setSchoolId(schoolId);
		schoolCourseCondition.setIsDeleted(false);
		schoolCourseCondition.setIsOpen(true);
		List<SchoolCourse> schoolCourseList = schoolCourseService.findSchoolCourseByCondition(schoolCourseCondition);
		return schoolCourseList;
	}

	private List<SyllabusVo> syllabusLesson2SyllabusVo(List<SyllabusLesson> lessons, Integer schoolId) {
		List<SyllabusVo> syllabusVos = new ArrayList<SyllabusVo>();
		if(lessons != null && lessons.size() > 0){
			for(SyllabusLesson lesson : lessons) {
				SyllabusVo syllabusVo = new SyllabusVo();
				String subjectCode = lesson.getSubjectCode();
				Integer teacherId = lesson.getTeacherId();
				Teacher teacher = this.teacherService.getTeacherById(teacherId);
				Subject subject = this.subjectService.findUnique(schoolId, subjectCode);
				syllabusVo.setLessonId(lesson.getId());
				syllabusVo.setLesson(lesson.getLesson());
				syllabusVo.setId(lesson.getSyllabusId());
				syllabusVo.setDayOfWeek(lesson.getDayOfWeek());
				if(teacher != null) {
					syllabusVo.setTeacherId(teacher.getId());
					syllabusVo.setTeacherName(teacher.getName());
				}
				if(subject != null) {
					syllabusVo.setSubjectCode(subject.getCode());
					syllabusVo.setSubjectName(subject.getName());
				}else{
					syllabusVo.setSubjectName(lesson.getSubjectName());
				}
				syllabusVos.add(syllabusVo);
			}
		}
		return syllabusVos;

	}

	private List<SyllabusVo> syllabusLesson2SyllabusVo2(List<SyllabusLesson> lessons, Integer schoolId) {
		List<SyllabusVo> syllabusVos = new ArrayList<SyllabusVo>();
		for(SyllabusLesson lesson : lessons) {
			SyllabusVo syllabusVo = new SyllabusVo();
			String subjectCode = lesson.getSubjectCode();
			Integer teacherId = lesson.getTeacherId();
			Integer syllabusId = lesson.getSyllabusId();
			Teacher teacher = null;
			if(teacherId != null){
				teacher = this.teacherService.findTeacherById(teacherId);
			}
			Subject subject = this.subjectService.findUnique(schoolId, subjectCode);
			Syllabus syllabus = this.syllabusService.findSyllabusById(syllabusId);
			syllabusVo.setLessonId(lesson.getId());
			syllabusVo.setLesson(lesson.getLesson());
			syllabusVo.setId(lesson.getSyllabusId());
			syllabusVo.setDayOfWeek(lesson.getDayOfWeek());
			if(teacher != null) {
				syllabusVo.setTeacherId(teacher.getId());
				syllabusVo.setTeacherName(teacher.getName());
			}
			if(subject != null) {
				syllabusVo.setSubjectCode(subject.getCode());
				syllabusVo.setSubjectName(subject.getName());
			}
			if(syllabus != null) {
				Integer teamId = syllabus.getTeamId();
				Team team = this.teamService.findTeamById(teamId);
				if(team != null) {
					syllabusVo.setTeamId(teamId);
					syllabusVo.setTeamName(team.getName());
				}
				syllabusVo.setLessonTimes(syllabus.getLessonTimes());
			}
			syllabusVos.add(syllabusVo);
		}
		return syllabusVos;

	}

	@RequestMapping(value="/searcher/team", method = RequestMethod.GET)
	public String teamSearcher() {
		return structurePath("/team_syllabus");
	}

	@RequestMapping(value="/searcher/teacher", method = RequestMethod.GET)
	public String teachSearcher(
				@RequestParam(value="type", required = false) String type,
				@CurrentUser UserInfo user, Model model) {
		if(!"all".equals(type)) {
			Integer userId = user.getId();
			Integer schoolId = user.getSchoolId();
			Teacher teacher = this.teacherService.findOfUser(schoolId, userId);
			Integer teachId = null;
			if(teacher != null) {
				teachId = teacher.getId();
			}
			model.addAttribute("teachId", teachId != null ? teachId : "");
		}
		return structurePath("/teacher_syllabus");
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<Syllabus> jsonList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") SyllabusCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.syllabusService.findSyllabusByCondition(condition, page, order);
	}


	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(Syllabus syllabus, @CurrentUser UserInfo user) {
		Integer schoolId = syllabus.getSchoolId();
		if(schoolId == null) {
			if(user != null) {
				syllabus.setSchoolId(user.getSchoolId());
			}
		}
		syllabus = this.syllabusService.add(syllabus);
		return syllabus != null ? new ResponseInfomation(syllabus.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}


	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) throws ParseException {
		Syllabus syllabus = this.syllabusService.findSyllabusById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("syllabus", syllabus);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		Syllabus syllabus = this.syllabusService.findSyllabusById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("syllabus", syllabus);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, Syllabus syllabus) {
		if (syllabus != null) {
			syllabus.setId(id);
		}
		try {
			this.syllabusService.remove(syllabus);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, Syllabus syllabus) {
		syllabus.setId(id);
		syllabus = this.syllabusService.modify(syllabus);
		return syllabus != null ? new ResponseInfomation(syllabus.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/pushSySyllabus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation pushSySyllabus(@RequestParam(value="teamId") Integer teamId,
			@RequestParam(value="termCode") String termCode) {
		Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, termCode);
		if(syllabus != null){
			//推送
			//要推送的班级
			List<Integer>teamIds = new ArrayList<Integer>();
			teamIds.add(syllabus.getTeamId());
			String roleGroups = RoleGroup.T$BPACCOUNT+",";
//			IMPushUtil.push(roleGroups, teamIds, DataAction.D$Update, syllabus.getId(), BpCommonContants.PUSH_SYLLABUS, "",
//				teamUserService, teamAccountService, teamTeacherService, teacherService, bpBwSignageService, schoolTermCurrentService,
//				roomTeamService, teamService, taskExecutor);

//			IMPushUtil.signagePush(teamIds, 1, DataAction.D$Update, syllabus.getId(), BpCommonContants.PUSH_SYLLABUS, "", bpBwSignageService, taskExecutor);
			IMPushUtil.PushByXJXP(teamIds, 1,DataAction.D$Update,syllabus.getId(),BpCommonContants.PUSH_SYLLABUS,"",
					bpBwSignageService,imAccountService,imProviderService ,pushSubscriptionDao,taskExecutor);
			//推送结束 ====
		}
		return syllabus != null ? new ResponseInfomation(syllabus.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/check")
	@ResponseBody
	public ResponseInfomation check(@CurrentUser UserInfo userInfo, HttpServletResponse response, HttpServletRequest request,
			String termCode,Integer teamId) throws Exception {
		Syllabus syllabus = null;
		syllabus = this.syllabusService.getTeamSyllabus(teamId, termCode);
		if(syllabus == null){
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}else{
			return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		}
	}

	@RequestMapping(value = "/downLoadData")
	@ResponseBody
	public void downLoadPage(@CurrentUser UserInfo userInfo, HttpServletResponse response, HttpServletRequest request,
			String termCode,Integer teamId) throws Exception {
		Syllabus syllabus = null;
		syllabus = this.syllabusService.getTeamSyllabus(teamId, termCode);
		Integer morningLesson = SysContants.LESSON_COUNT_MORNING;
		Integer afrerLesson = SysContants.LESSON_COUNT_AFTERNOON;
		Integer eveningLesson = SysContants.LESSON_COUNT_EVENING;
		morningLesson = syllabus.getLessonOfMorning();
		afrerLesson = syllabus.getLessonOfAfternoon();
		eveningLesson = syllabus.getLessonOfEvening();
		// 装配所有的数据项
		List<Object> maps = new ArrayList<Object>();
		// 装配单个教师的数据
		for (int i = 1; i <= morningLesson; i++ ) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 装配单个教师的数据
			map = new HashMap<String, Object>();
			map.put("lesson", "上午"+i);
			maps.add(map);
		}
		for (int i = 1; i <= afrerLesson; i++ ) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 装配单个教师的数据
			map = new HashMap<String, Object>();
			map.put("lesson", "下午"+i);
			maps.add(map);
		}
		for (int i = 1; i <= eveningLesson; i++ ) {
			Map<String, Object> map = new HashMap<String, Object>();
			// 装配单个教师的数据
			map = new HashMap<String, Object>();
			map.put("lesson", "晚上"+i);
			maps.add(map);
		}
		// 列名
		String[] columnNames = new String[8];
		columnNames[0] = "节次\\星期";
		columnNames[1] = "星期一";
		columnNames[2] = "星期二";
		columnNames[3] = "星期三";
		columnNames[4] = "星期四";
		columnNames[5] = "星期五";
		columnNames[6] = "星期六";
		columnNames[7] = "星期天";
		// 数据库对应的列名称
		String[] filesNames = new String[1];
		filesNames[0] = "lesson";
		ParseConfig config = SzxyExcelTookit.getConfig();
		config.setTitles(columnNames);
		config.setFieldNames(filesNames);
		config.setSheetTitle("班级课程表");

		Workbook workbook = new HSSFWorkbook();
		workbook = SzxyExcelTookitSub.generateWorkbook(maps, config, workbook);
		Sheet sheet0 = workbook.getSheetAt(0);
		sheet0.setColumnWidth(0, 4200);
		sheet0.getRow(0).setHeight((short)500);
		//int regionRow = morningLesson + afrerLesson + eveningLesson + 1;
		//CellRangeAddress region = new CellRangeAddress(regionRow, regionRow+3, 0, 7);
		//sheet0.addMergedRegion(region);

		HSSFFont font = (HSSFFont) workbook.createFont();
        font.setFontName("宋体");
        font.setColor((short)16);
        font.setFontHeightInPoints((short) 8);// 字体大小
        HSSFCellStyle style = (HSSFCellStyle) workbook.createCellStyle();
        style.setFont(font);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);//
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);//
        style.setLocked(true);
		SzxyExcelTookitSub.exportExcelToWEB(workbook, request, response, "班级课程表模版.xls");
		// SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,"课表信息数据.xls");
	}

	@RequestMapping("/upLoaddata")
	@ResponseBody
	public Map<String,Object> upLoadScoreInfoByModel(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@CurrentUser UserInfo user, HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "gradeId", required = true)Integer gradeId,
			@RequestParam(value = "teamId", required = true)Integer teamId,
			@RequestParam(value = "termCode", required = true)String termCode
			) throws Exception {
		if ( fileUpload == null ) {
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		InputStream is = fileUpload.getInputStream();
		HSSFWorkbook workbook = new HSSFWorkbook(is);
		HSSFSheet sh = workbook.getSheetAt(0);
		if ( sh == null ) {
			return null;
		}
		Integer schoolId = user.getSchoolId();
		Syllabus syllabus = this.syllabusService.getTeamSyllabus(teamId, termCode);
		StringBuilder sBuilder = new StringBuilder();
		if ( syllabus == null ) {
			sBuilder.append("请先设置作息时间!");
			map.put("ERROR", sBuilder.toString());
			return map;
		}
		if ( syllabus != null && syllabus.getId() != null ) {
			importSyllabusLesson(gradeId, teamId, sh, schoolId, sBuilder,syllabus);

		}
		String error = sBuilder.toString();

		if ( error != null && error.length() > 0 ) {
			map.put("ERROR", error);
		} else {
			map.put("SUCCESS", "success");
		}
		return map;
	}

	private void importSyllabusLesson(Integer gradeId, Integer teamId, HSSFSheet sh, Integer schoolId,
			StringBuilder sBuilder,Syllabus syllabus) throws NumberFormatException {
		this.syllabusService.removeSyllabusLessonBySyllabusId(syllabus.getId());
		Integer days = syllabus.getDays();
		Integer lessonPlan = syllabus.getLessonOfAfternoon()+syllabus.getLessonOfMorning()+syllabus.getLessonOfEvening();
		for(int i=1;i<=days;i++){
			for(int j=1;j<=lessonPlan;j++){
				SyllabusLesson syllabusLesson = new SyllabusLesson();
				Integer dayOfWeek = i;
				Integer lesson = j;
				syllabusLesson.setSyllabusId(syllabus.getId());
				syllabusLesson.setDayOfWeek(String.valueOf(dayOfWeek));
				syllabusLesson.setLesson(lesson);
				HSSFRow hr = sh.getRow(lesson);
				HSSFCell cell = null;
				if(dayOfWeek != null && 7 == dayOfWeek){
					cell = hr.getCell(7);
				}else{
					cell = hr.getCell(dayOfWeek);
				}
				if ( cell != null ) {
					String cellContent = parseExcel(cell);
					if ( cellContent.contains("说明:") ) {
						continue;
					}
					String[] contentArray = cellContent.split("/");
					String subjectName = getSubjectName(contentArray);
					String teacherName = getTeacherName(contentArray);
					if ( subjectName == null ) {
						continue;
					}
					syllabusLesson.setSubjectName(subjectName);
						Subject subject = getSubject(schoolId, subjectName);
						if ( subject == null ) {
							sBuilder.append(" 星期"+ dayOfWeek + "第"+lesson + "节:"+cellContent+"有误,行政学科名称不存在;");
							continue;
						}
						TeamTeacher teamTeacher = getTeamTeacher(gradeId,teamId,teacherName,subject);
						if ( teamTeacher == null && teacherName != null ) {
							sBuilder.append(" 星期"+ dayOfWeek + "第"+lesson + "节:"+cellContent+"有误,行政学科教师不存在;");
							syllabusLesson.setTeacherId(null);
						} else if ( teamTeacher != null ) {
							syllabusLesson.setTeacherId(teamTeacher.getTeacherId());
						}
						syllabusLesson.setSubjectCode(subject.getCode());
					this.syllabusService.addSyllabusLesson(syllabusLesson);
				}
			}
		}
	}

	private String getTeacherName( String[] contentArray ) {
		String teacherName = null;
		if ( contentArray.length >= 2 ) {
			teacherName = contentArray[1];
		}
		return teacherName;
	}

	private String parseExcel(Cell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// 日期
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				double value = cell.getNumericCellValue();
				Date date = org.apache.poi.ss.usermodel.DateUtil
						.getJavaDate(value);
				result = sdf.format(date);
			} else {
				double value = cell.getNumericCellValue();
				result = value+"";
			}
			break;
		case HSSFCell.CELL_TYPE_STRING:// String类型
			result = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			result = "";
		default:
			result = "";
			break;
		}
		return result;
	}

	private Subject getSubject(Integer schoolId, String subjectName) {
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setName(subjectName);
		subjectCondition.setSchoolId(schoolId);
		subjectCondition.setIsDelete(false);
		List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
		Subject subject = null;
		if ( subjectList != null && subjectList.size() > 0 ) {
			subject = subjectList.get(0);
		}
		return subject;
	}

	private TeamTeacher getTeamTeacher(Integer gradeId, Integer teamId, String teacherName, Subject subject) {
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setName(teacherName);
		teamTeacherCondition.setGradeId(gradeId);
		teamTeacherCondition.setTeamId(teamId);
		teamTeacherCondition.setSubjectCode(subject.getCode());
		teamTeacherCondition.setSubjectName(subject.getName());
		List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		TeamTeacher teamTeacher = null;
		if (teamTeacherList != null && teamTeacherList.size() > 0 ) {
			teamTeacher = teamTeacherList.get(0);
		}
		return teamTeacher;
	}

	private String getSubjectName( String[] contentArray ) {
		String subjectName = null;
		if ( contentArray.length >= 1 ) {
			subjectName = contentArray[0];
		}
		return subjectName;
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, SyllabusCondition condition) {

	}

	private static Integer[] number2Array(Integer number, Integer begin, Integer end) {
		List<Integer> resultList = new ArrayList<Integer>();
		Integer[] result = {};
		end = (end != null && end > number) || end == null ? number : end;
		begin = begin == null ? 1 : begin;
		for(int i = begin; i <= end; i++) {
			resultList.add(i);
		}
		return resultList.toArray(result);

	}
}
