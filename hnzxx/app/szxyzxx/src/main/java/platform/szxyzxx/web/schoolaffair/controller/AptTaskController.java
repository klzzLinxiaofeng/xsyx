package platform.szxyzxx.web.schoolaffair.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.school.affair.model.AptRule;
import platform.education.school.affair.model.AptTask;
import platform.education.school.affair.model.AptTaskItem;
import platform.education.school.affair.model.AptTaskJudge;
import platform.education.school.affair.model.AptTaskScore;
import platform.education.school.affair.model.AptTaskUser;
import platform.education.school.affair.service.AptRuleItemService;
import platform.education.school.affair.service.AptRuleService;
import platform.education.school.affair.service.AptTaskItemService;
import platform.education.school.affair.service.AptTaskJudgeService;
import platform.education.school.affair.service.AptTaskScoreService;
import platform.education.school.affair.service.AptTaskService;
import platform.education.school.affair.service.AptTaskUserService;
import platform.education.school.affair.vo.AptRuleCondition;
import platform.education.school.affair.vo.AptRuleItemCondition;
import platform.education.school.affair.vo.AptRuleItemVo;
import platform.education.school.affair.vo.AptTaskCondition;
import platform.education.school.affair.vo.AptTaskItemCondition;
import platform.education.school.affair.vo.AptTaskItemVo;
import platform.education.school.affair.vo.AptTaskJudgeCondition;
import platform.education.school.affair.vo.AptTaskScoreCondition;
import platform.education.school.affair.vo.AptTaskScoreVo;
import platform.education.school.affair.vo.AptTaskUserCondition;
import platform.education.school.affair.vo.AptTaskVo;
import platform.education.school.affair.vo.TaskItemVo;
import platform.education.school.affair.vo.TaskVo;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolaffair.contants.AptContans;
import platform.szxyzxx.web.schoolaffair.vo.AptTaskImportVo;
import platform.szxyzxx.web.schoolaffair.vo.AptTaskItemImportVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

/**
 * ????????????
 * 
 * @author ?????????
 *
 */
@Controller
@RequestMapping("/schoolAffair/aptTask")
public class AptTaskController extends BaseController {

	private final static String viewBasePath = "/schoolaffair/aptTask";

	@Resource
	private AptTaskService aptTaskService;

	@Resource
	private AptTaskItemService aptTaskItemService;

	@Resource
	private AptRuleService aptRuleService;

	@Resource
	private AptRuleItemService aptRuleItemService;

	@Resource
	private AptTaskScoreService aptTaskScoreService;

	@Resource
	private AptTaskJudgeService aptTaskJudgeService;

	@Resource
	private AptTaskUserService aptTaskUserService;

	@Resource
	private TeacherService teacherService;

	@Resource
	private DepartmentService departmentService;
	
	private static final int  ONE_SECOND = 1000;
    private static final int  ONE_MINUTE = 60*ONE_SECOND;
    private static final int  ONE_HOUR   = 60*ONE_MINUTE;
    private static final long ONE_DAY    = 24*ONE_HOUR;
	
	/**
	 * ????????????????????????
	 * 
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<AptTaskVo> items = this.aptTaskService.findAptTaskByCondition(
				condition, page, order);
		if (items.size() > 0) {
			for (AptTaskVo vo : items) {
				// ????????????????????????
				List<Integer> teacherIds = vo.getTeacherIds();
				List<String> teacherNames = new ArrayList<String>();
				if (teacherIds.size() > 0) {
					for (Integer teacherId : teacherIds) {
						Teacher teacher = teacherService
								.findTeacherById(teacherId);
						String name = teacher.getName();
						teacherNames.add(name);
					}
				}
				vo.setUserNames(teacherNames);
				// ??????????????????
				Integer departmentId = vo.getDepartmentId();
				if (departmentId != null) {
					Department dept = departmentService.findDepartmentById(departmentId);
					vo.setDepartmentName(dept != null ? dept.getName() : "");
				}
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ??????????????????
	 * 
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/searchIndex")
	public ModelAndView searchIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<AptTaskVo> items = this.aptTaskService.findAptTaskByCondition(
				condition, null, order);
		List<AptTaskVo> taskVos = new ArrayList<AptTaskVo>();
		if (items.size() > 0) {
			for (AptTaskVo vo : items) {
				String scopeType = vo.getScopeType();
				if (AptContans.SCOPE_OTHER.equals(scopeType)) {
					List<Integer> teacherIds = vo.getTeacherIds();
					if (teacherIds.contains(user.getTeacherId())) {
						List<String> teacherNames = new ArrayList<String>();
						if (teacherIds.size() > 0) {
							for (Integer teacherId : teacherIds) {
								Teacher teacher = teacherService
										.findTeacherById(teacherId);
								String name = teacher.getName();
								teacherNames.add(name);
							}
						}
						vo.setUserNames(teacherNames);
						taskVos.add(vo);
					}
				} else if (AptContans.SCOPE_DEPARTMENT.equals(scopeType)) {
					// ??????????????????
					Integer departmentId = vo.getDepartmentId();
					List<DepartmentTeacher> dts = departmentTeacherService
							.findDepartmentTeacherByTeacherIdAndSchoolId(
									user.getTeacherId(), user.getSchoolId());
					List<Integer> departmentIds = new ArrayList<Integer>();
					if (dts.size() > 0) {
						for (DepartmentTeacher dt : dts) {
							Integer deptId = dt.getDepartmentId();
							if (deptId != null) {
								departmentIds.add(deptId);
							}
						}
					}
					if (departmentIds.contains(departmentId)) {
						if (departmentId != null) {
							Department dept = departmentService
									.findDepartmentById(departmentId);
							vo.setDepartmentName(dept.getName());
						}
						taskVos.add(vo);
					}
				} else {
					taskVos.add(vo);
				}
			}
		}
		/** ??????????????? */
		List<AptTaskVo> voList = new ArrayList<AptTaskVo>();
		int currentPage = page.getCurrentPage();
		int pageSize = page.getPageSize();
		int start = (currentPage - 1) * pageSize;
		int end = currentPage * pageSize - 1;
		int i = 0;
		for (AptTaskVo vo : taskVos) {
			if (i >= start && i <= end) {
				voList.add(vo);
			}
			i++;
		}
		int totalRows = taskVos.size();
		page.setTotalRows(totalRows);
		page.setCurrentPage(currentPage);
		int totalPages = (totalRows % pageSize) == 0 ? (totalRows / pageSize)
				: (totalRows / pageSize) + 1;
		page.setTotalPages(totalPages);

		if ("list".equals(sub)) {
			viewPath = structurePath("/search_list");
		} else {
			viewPath = structurePath("/search_index");
		}
		model.addAttribute("items", voList);
		model.addAttribute("page", page);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ?????????????????????
	 * 
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/judgeSearchIndex")
	public ModelAndView judgeSearchIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		if (user.getTeacherId() != null) {
			List<AptTaskVo> items = this.aptTaskService
					.findAptTaskByJudgeIdAndCondition(condition,
							user.getTeacherId(), true, page, order);
			if (items.size() > 0) {
				for (AptTaskVo vo : items) {
					List<Integer> teacherIds = vo.getTeacherIds();
					List<String> teacherNames = new ArrayList<String>();
					if (teacherIds.size() > 0) {
						for (Integer teacherId : teacherIds) {
							Teacher teacher = teacherService
									.findTeacherById(teacherId);
							String name = teacher.getName();
							teacherNames.add(name);
						}
					}
					vo.setUserNames(teacherNames);
					// ??????????????????
					Integer departmentId = vo.getDepartmentId();
					if (departmentId != null) {
						Department dept = departmentService
								.findDepartmentById(departmentId);
						vo.setDepartmentName(dept.getName());
					}
				}
			}
			model.addAttribute("items", items);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/judge_search_list");
		} else {
			viewPath = structurePath("/judge_search_index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ?????????????????????
	 * 
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/allSearchIndex")
	public ModelAndView allSearchIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<AptTaskVo> items = this.aptTaskService.findAptTaskByCondition(
				condition, page, order);
		if (items.size() > 0) {
			for (AptTaskVo vo : items) {
				List<Integer> teacherIds = vo.getTeacherIds();
				List<String> teacherNames = new ArrayList<String>();
				if (teacherIds.size() > 0) {
					for (Integer teacherId : teacherIds) {
						Teacher teacher = teacherService
								.findTeacherById(teacherId);
						String name = teacher.getName();
						teacherNames.add(name);
					}
				}
				vo.setUserNames(teacherNames);
				// ??????????????????
				Integer departmentId = vo.getDepartmentId();
				if (departmentId != null) {
					Department dept = departmentService
							.findDepartmentById(departmentId);
					vo.setDepartmentName(dept.getName());
				}
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/all_search_list");
		} else {
			viewPath = structurePath("/all_search_index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ????????????
	 * 
	 * @param user
	 * @param dm
	 * @param sub
	 * @param condition
	 * @param page
	 * @param order
	 * @param model
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/sumIndex")
	public ModelAndView sumIndex(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		List<AptTaskVo> items = this.aptTaskService.findAptTaskByCondition(
				condition, page, order);
		if (items.size() > 0) {
			for (AptTaskVo vo : items) {
				List<Integer> teacherIds = vo.getTeacherIds();
				List<String> teacherNames = new ArrayList<String>();
				if (teacherIds.size() > 0) {
					for (Integer teacherId : teacherIds) {
						Teacher teacher = teacherService
								.findTeacherById(teacherId);
						String name = teacher.getName();
						teacherNames.add(name);
					}
				}
				vo.setUserNames(teacherNames);
				// ??????????????????
				Integer departmentId = vo.getDepartmentId();
				if (departmentId != null) {
					Department dept = departmentService
							.findDepartmentById(departmentId);
					vo.setDepartmentName(dept.getName());
				}
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/sum_list");
		} else {
			viewPath = structurePath("/sum_index");
		}
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ??????????????????-??????
	 * 
	 * @param page
	 * @param order
	 * @param sub
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/searchDetail")
	public ModelAndView searchDetail(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@ModelAttribute("condition") AptTaskScoreCondition condition,
			@RequestParam(value = "week", required = false) Integer week,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "finishDate", required = false) Date finishDate,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		page.setPageSize(20);
		float totalNum = 0l;
		int totalRow = 0;
		// ????????????????????????
		AptTask task = aptTaskService.findAptTaskById(id);
		// ????????????????????????????????????
		AptRule rule = aptRuleService.findAptRuleById(task.getRuleId());
		// ????????????
		List<Map<String, Object>> weeks = getWeeks(task.getStartDate(),
				task.getFinishDate());
		// ??????taskItem?????????
		List<TaskItemVo> voList = aptTaskItemService.findAptTaskItemAndCategoryByTaskIdAndTeacherId(id,user.getTeacherId() == null ? 0 : user.getTeacherId());
		//??????????????????????????????
		AptTaskItemCondition bonusCondition = new AptTaskItemCondition();
		bonusCondition.setAptTaskId(id);
		List<AptTaskItem> bonusList = aptTaskItemService.findBonusByCondition(bonusCondition);
		float bonusScore = 0l;
		float deductScore = 0l;
		if(bonusList.size() > 0){
			for(AptTaskItem taskItem : bonusList){
				String checkType = taskItem.getCheckType();
				AptTaskScoreCondition aptTaskScoreCondition = new AptTaskScoreCondition();
				aptTaskScoreCondition.setAptTaskItemId(taskItem.getId());
				aptTaskScoreCondition.setTeacherId(user.getTeacherId() == null ? 0 : user.getTeacherId());
				List<AptTaskScore> scores = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
				if(scores.size() > 0){
					for(AptTaskScore score : scores){
						if(AptContans.BONUS.equals(checkType)){
							bonusScore += score.getScore() == null ? 0 : score.getScore();
						}
						if(AptContans.DEDUCT.equals(checkType)){
							deductScore += score.getScore() == null ? 0 : score.getScore();
						}
					}
				}
			}
		}

		if (startDate != null) {
			condition.setStartDate(startDate);
		}
		if (finishDate != null) {
			condition.setFinishDate(finishDate);
		}
		if (condition.getAptTaskItemId() != null) {
			condition.setAptTaskId(id);
			condition.setId(null);
		}
		condition.setTeacherId(user.getTeacherId() == null ? 0 : user
				.getTeacherId());
		List<AptTaskScore> scores = aptTaskScoreService
				.findAptTaskScoreOfSearchAndCount(condition, page, order);
		List<AptTaskScoreVo> scoreVoList = new ArrayList<AptTaskScoreVo>();
		if (scores != null) {
			for (AptTaskScore taskScore : scores) {
				AptTaskScoreVo vo = new AptTaskScoreVo();
				BeanUtils.copyProperties(taskScore, vo);
				Integer judgeId = taskScore.getJudgeId();
				Integer teacherId = taskScore.getTeacherId();
				totalNum += taskScore.getScore();
				if (judgeId != null) {
					Teacher teacher = teacherService.findTeacherById(judgeId);
					if (teacher != null) {
						vo.setJudgeName(teacher.getName());
					}
				}
				if (teacherId != null) {
					Teacher teacher = teacherService.findTeacherById(teacherId);
					if (teacher != null) {
						vo.setUserName(teacher.getName());
					}
				}
				scoreVoList.add(vo);
			}
		}
		totalRow = scoreVoList.size();
		if ("list".equals(sub)) {
			viewPath = structurePath("/search_detail_list");
		} else {
			viewPath = structurePath("/search_detail");
		}
		model.addAttribute("task", task);
		model.addAttribute("voList", voList);
		model.addAttribute("scoreVoList", scoreVoList);
		model.addAttribute("weeks", weeks);
		model.addAttribute("totalNum", totalNum);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rule", rule);
		model.addAttribute("bonusList", bonusList);
		model.addAttribute("bonusScore", bonusScore);
		model.addAttribute("deductScore", deductScore);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ?????????????????????-??????
	 * 
	 * @param page
	 * @param order
	 * @param sub
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/judgeSearchDetail")
	public ModelAndView judgeSearchDetail(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@ModelAttribute("condition") AptTaskScoreCondition condition,
			@RequestParam(value = "week", required = false) Integer week,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "finishDate", required = false) Date finishDate,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		page.setPageSize(20);
		// ????????????????????????
		AptTask task = aptTaskService.findAptTaskById(id);
		// ????????????????????????????????????
		AptRule rule = aptRuleService.findAptRuleById(task.getRuleId());
		// ????????????
		List<Map<String, Object>> weeks = getWeeks(task.getStartDate(),
				task.getFinishDate());
		float totalNum = 0l;
		int totalRow = 0;
		// ??????taskItem?????????
		List<TaskItemVo> voList = aptTaskItemService.findAptTaskItemAndCategoryByTaskIdAndJudgeId(id,user.getTeacherId());
		//??????????????????????????????
		AptTaskItemCondition bonusCondition = new AptTaskItemCondition();
		bonusCondition.setAptTaskId(id);
		bonusCondition.setJudgeId(user.getTeacherId());
		List<AptTaskItem> bonusList = aptTaskItemService.findBonusByCondition(bonusCondition);
		float bonusScore = 0l;
		float deductScore = 0l;
		if(bonusList.size() > 0){
			for(AptTaskItem taskItem : bonusList){
				String checkType = taskItem.getCheckType();
				AptTaskScoreCondition aptTaskScoreCondition = new AptTaskScoreCondition();
				aptTaskScoreCondition.setAptTaskItemId(taskItem.getId());
				List<AptTaskScore> scores = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
				if(scores.size() > 0){
					for(AptTaskScore score : scores){
						if(AptContans.BONUS.equals(checkType)){
							bonusScore += score.getScore() == null ? 0 : score.getScore();
						}
						if(AptContans.DEDUCT.equals(checkType)){
							deductScore += score.getScore() == null ? 0 : score.getScore();
						}
					}
				}
			}
		}

		// ??????????????????????????????
		String scopeType = task.getScopeType();
		if (AptContans.SCOPE_SCHOOL.equals(scopeType)) {
			List<Teacher> teachers = teacherService
					.findTeacherListBySchoolId(user.getSchoolId());
			model.addAttribute("teachers", teachers);

		} else if (AptContans.SCOPE_DEPARTMENT.equals(scopeType)) {
			List<DepartmentTeacher> teachers = departmentTeacherService
					.findByDepartmentId(task.getDepartmentId());
			model.addAttribute("teachers", teachers);
		} else {
			AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
			aptTaskUserCondition.setAptTaskId(id);
			List<AptTaskUser> taskUsers = aptTaskUserService
					.findAptTaskUserByCondition(aptTaskUserCondition);
			List<Teacher> teachers = new ArrayList<Teacher>();
			if (taskUsers.size() > 0) {
				for (AptTaskUser taskUser : taskUsers) {
					Integer teacherId = taskUser.getTeacherId();
					Teacher teacher = teacherService.findTeacherById(teacherId);
					teachers.add(teacher);
				}
			}
			model.addAttribute("teachers", teachers);
		}

		if (startDate != null) {
			condition.setStartDate(startDate);
		}
		if (finishDate != null) {
			condition.setFinishDate(finishDate);
		}
		if (condition.getAptTaskItemId() != null) {
			condition.setAptTaskId(id);
			condition.setId(null);
		}
		List<AptTaskScore> scores = aptTaskScoreService
				.findAptTaskScoreOfSearchAndCount(condition, page, order);
		List<AptTaskScoreVo> scoreVoList = new ArrayList<AptTaskScoreVo>();
		if (scores != null) {
			for (AptTaskScore taskScore : scores) {
				AptTaskScoreVo vo = new AptTaskScoreVo();
				BeanUtils.copyProperties(taskScore, vo);
				Integer judgeId = taskScore.getJudgeId();
				Integer teacherId = taskScore.getTeacherId();
				totalNum += taskScore.getScore();
				if (judgeId != null) {
					Teacher teacher = teacherService.findTeacherById(judgeId);
					if (teacher != null) {
						vo.setJudgeName(teacher.getName());
					}
				}
				if (teacherId != null) {
					Teacher teacher = teacherService.findTeacherById(teacherId);
					if (teacher != null) {
						vo.setUserName(teacher.getName());
					}
				}
				scoreVoList.add(vo);
			}
		}
		totalRow = scoreVoList.size();
		if ("list".equals(sub)) {
			viewPath = structurePath("/judge_search_detail_list");
		} else {
			viewPath = structurePath("/judge_search_detail");
		}
		model.addAttribute("task", task);
		model.addAttribute("voList", voList);
		model.addAttribute("scoreVoList", scoreVoList);
		model.addAttribute("weeks", weeks);
		model.addAttribute("totalNum", totalNum);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rule", rule);
		model.addAttribute("bonusList", bonusList);
		model.addAttribute("bonusScore", bonusScore);
		model.addAttribute("deductScore", deductScore);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ?????????????????????-??????
	 * 
	 * @param page
	 * @param order
	 * @param sub
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/allSearchDetail")
	public ModelAndView allSearchDetail(
			@RequestParam(value = "id", required = true) Integer id,
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@ModelAttribute("condition") AptTaskScoreCondition condition,
			@RequestParam(value = "week", required = false) Integer week,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "finishDate", required = false) Date finishDate,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		page.setPageSize(20);
		float totalNum = 0l;
		int totalRow = 0;
		// ????????????????????????
		AptTask task = aptTaskService.findAptTaskById(id);
		// ????????????????????????????????????
		AptRule rule = aptRuleService.findAptRuleById(task.getRuleId());
		// ????????????
		List<Map<String, Object>> weeks = getWeeks(task.getStartDate(),task.getFinishDate());
		// ??????taskItem?????????
		List<TaskItemVo> voList = aptTaskItemService.findAptTaskItemAndCategoryByTaskIdAndTeacherId(id,condition.getTeacherId());
		//??????????????????????????????
		AptTaskItemCondition bonusCondition = new AptTaskItemCondition();
		bonusCondition.setAptTaskId(id);
		List<AptTaskItem> bonusList = aptTaskItemService.findBonusByCondition(bonusCondition);
		float bonusScore = 0l;
		float deductScore = 0l;
		if(bonusList.size() > 0){
			for(AptTaskItem taskItem : bonusList){
				String checkType = taskItem.getCheckType();
				AptTaskScoreCondition aptTaskScoreCondition = new AptTaskScoreCondition();
				aptTaskScoreCondition.setAptTaskItemId(taskItem.getId());
				List<AptTaskScore> scores = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
				if(scores.size() > 0){
					for(AptTaskScore score : scores){
						if(AptContans.BONUS.equals(checkType)){
							bonusScore += score.getScore() == null ? 0 : score.getScore();
						}
						if(AptContans.DEDUCT.equals(checkType)){
							deductScore += score.getScore() == null ? 0 : score.getScore();
						}
					}
				}
			}
		}

		// ??????????????????????????????
		String scopeType = task.getScopeType();
		if (AptContans.SCOPE_SCHOOL.equals(scopeType)) {
			List<Teacher> teachers = teacherService.findTeacherListBySchoolId(user.getSchoolId());
			model.addAttribute("teachers", teachers);

		} else if (AptContans.SCOPE_DEPARTMENT.equals(scopeType)) {
			List<DepartmentTeacher> teachers = departmentTeacherService
					.findByDepartmentId(task.getDepartmentId());
			model.addAttribute("teachers", teachers);
		} else {
			AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
			aptTaskUserCondition.setAptTaskId(id);
			List<AptTaskUser> taskUsers = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
			List<Teacher> teachers = new ArrayList<Teacher>();
			if (taskUsers.size() > 0) {
				for (AptTaskUser taskUser : taskUsers) {
					Integer teacherId = taskUser.getTeacherId();
					Teacher teacher = teacherService.findTeacherById(teacherId);
					teachers.add(teacher);
				}
			}
			model.addAttribute("teachers", teachers);
		}

		if (startDate != null) {
			condition.setStartDate(startDate);
		}
		if (finishDate != null) {
			condition.setFinishDate(finishDate);
		}
		if (condition.getAptTaskItemId() != null) {
			condition.setAptTaskId(id);
			condition.setId(null);
		}
		List<AptTaskScore> scores = aptTaskScoreService
				.findAptTaskScoreOfSearchAndCount(condition, page, order);
		List<AptTaskScoreVo> scoreVoList = new ArrayList<AptTaskScoreVo>();
		if (scores != null) {
			for (AptTaskScore taskScore : scores) {
				AptTaskScoreVo vo = new AptTaskScoreVo();
				BeanUtils.copyProperties(taskScore, vo);
				String fileUUID = taskScore.getAttachmentFileUuid();
				if(fileUUID != null){
					FileResult file = fileService.findFileByUUID(fileUUID);
					if(file != null && file.getEntityFile() != null){
						String fileName = file.getEntityFile().getFileName();
						vo.setFileName(fileName);
					}
				}
				Integer judgeId = taskScore.getJudgeId();
				Integer teacherId = taskScore.getTeacherId();
				totalNum += taskScore.getScore();
				if (judgeId != null) {
					Teacher teacher = teacherService.findTeacherById(judgeId);
					if (teacher != null) {
						vo.setJudgeName(teacher.getName());
					}
				}
				if (teacherId != null) {
					Teacher teacher = teacherService.findTeacherById(teacherId);
					if (teacher != null) {
						vo.setUserName(teacher.getName());
					}
				}
				scoreVoList.add(vo);
			}
		}
		totalRow = scoreVoList.size();
		if ("list".equals(sub)) {
			viewPath = structurePath("/all_search_detail_list");
		} else {
			viewPath = structurePath("/all_search_detail");
		}
		model.addAttribute("task", task);
		model.addAttribute("voList", voList);
		model.addAttribute("scoreVoList", scoreVoList);
		model.addAttribute("weeks", weeks);
		model.addAttribute("totalNum", totalNum);
		model.addAttribute("totalRow", totalRow);
		model.addAttribute("rule", rule);
		model.addAttribute("bonusList", bonusList);
		model.addAttribute("bonusScore", bonusScore);
		model.addAttribute("deductScore", deductScore);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ????????????
	 * 
	 * @param startDate
	 * @param finishDate
	 * @return
	 * @author ?????????
	 */
	private List<Map<String, Object>> getWeeks(Date startDate, Date finishDate) {
		Integer weeks = (int) ((finishDate.getTime() - startDate.getTime()) / (ONE_DAY * 7));
		Integer weekRemainder = (int) ((finishDate.getTime() - startDate.getTime()) % (ONE_DAY * 7));
		weeks = weekRemainder == 0 ? weeks : weeks + 1;
		List<Map<String, Object>> weekMap = new ArrayList<Map<String, Object>>();
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		if (weeks != null) {
			for (int i = 1; i <= weeks; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("week", i);
				if (i == 1) {
					if (startDate != null) {
						calendar.setTime(startDate);
						map.put("beginDate", startDate);
						int dow = calendar.get(Calendar.DAY_OF_WEEK) - 1;
						if (dow == 7)
							dow = 0;
						// ??????1??????
						calendar.add(Calendar.DAY_OF_YEAR, -dow);
						// ???????????????
						calendar.add(Calendar.DAY_OF_YEAR, 7);
						map.put("endDate", calendar.getTime());
					}
				} else {
					// ??????1??????
					calendar.add(Calendar.DAY_OF_YEAR, 1);
					map.put("beginDate", calendar.getTime());
					// ???????????????
					calendar.add(Calendar.DAY_OF_YEAR, 6);
					map.put("endDate", calendar.getTime());
				}
				weekMap.add(map);
			}
		}
		return weekMap;
	}
	/**
	 * ?????????????????????
	 * @param taskId
	 * @param type
	 * @param model
	 * @param user
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/loadExport",method = RequestMethod.GET)
	public ModelAndView loadExport(
			@RequestParam(value = "taskId",required = true)Integer taskId,
			@RequestParam(value = "type",required = true)String type,
			Model model,@CurrentUser UserInfo user){
		if("own".equals(type)){
			model.addAttribute("currentTeacherId", user.getTeacherId());
		}
		model.addAttribute("type", type);
		return new ModelAndView(structurePath("/export"),model.asMap());
	}
	/**
	 * ??????????????????
	 * @param user ????????????
	 * @param taskId ????????????id
	 * @param startDate ????????????
	 * @param finishDate ????????????
	 * @param teacherId ??????id
	 * @param taskItems ???????????????????????????
	 * @param condition 
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportSearchData")
	@ResponseBody
	public void exportSearchData(
			@CurrentUser UserInfo user,
			@RequestParam(value = "taskId" , required = true)Integer taskId,
			@RequestParam(value = "startDate", required = false) Date startDate,
			@RequestParam(value = "finishDate", required = false) Date finishDate,
			@RequestParam(value = "teacherId", required = false) Integer teacherId,
			@RequestParam(value = "taskItems", required = true) String taskItems,
			@ModelAttribute("condition") AptTaskScoreCondition condition,
			HttpServletResponse response,
			HttpServletRequest request) throws Exception{
		//taskItems???????????????
		String[] aptTaskItems = null; 
		if(!StringUtils.isEmpty(taskItems)){
			aptTaskItems = taskItems.split(",");
		}
		// ??????????????????ID??????????????????
		AptTask aptTask = aptTaskService.findAptTaskById(taskId);
		ParseConfig config = SzxyExcelTookit.getConfig();
		// ????????????????????????
		List<Object> maps = new ArrayList<Object>();
		// ???????????????????????????
		Map<String, Object> map = null;
		if (aptTask != null) {
			// ??????????????????
			if(teacherId != null){
				Teacher teacher = teacherService.findTeacherById(teacherId);
				map = setMap(aptTaskItems,startDate, finishDate, taskId,teacherId, condition);
				map.put("teacherId", teacherId);
				map.put("teacherName", teacher.getName());
				maps.add(map);
			}else{
				if (aptTask.getScopeType().equals(AptContans.SCOPE_DEPARTMENT)) {
					List<DepartmentTeacher> list = departmentTeacherService.findByDepartmentId(aptTask.getDepartmentId());
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							// ???????????????????????????
							map = setMap(aptTaskItems,startDate, finishDate, taskId,list.get(i).getTeacherId(), condition);
							map.put("teacherId", list.get(i).getTeacherId());
							map.put("teacherName", list.get(i).getTeacherName());
							maps.add(map);
						}
					}
				} else if (aptTask.getScopeType().equals(AptContans.SCOPE_OTHER)) {
					AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
					aptTaskUserCondition.setAptTaskId(aptTask.getId());
					List<AptTaskUser> list = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							Teacher teacher = teacherService.findTeacherById(list.get(i).getTeacherId());
							// ???????????????????????????
							map = setMap(aptTaskItems,startDate, finishDate, taskId, teacher.getId(),condition);
							map.put("teacherId", teacher.getId());
							map.put("teacherName", teacher.getName());
							maps.add(map);
						}
					}
				} else {
					List<Teacher> list = teacherService.findTeacherListBySchoolId(user.getSchoolId());
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							// ???????????????????????????
							map = setMap(aptTaskItems,startDate, finishDate, taskId, list.get(i).getId(),condition);
							map.put("teacherId", list.get(i).getId());
							map.put("teacherName", list.get(i).getName());
							maps.add(map);
						}
					}
				}
			}
		}
		// ??????????????????
		if (taskId != null) {
				// ??????
				String[] columnNames = new String[aptTaskItems.length + 3];
				// ???????????????????????????
				String[] filesNames = new String[aptTaskItems.length + 3];
				columnNames[0] = "??????ID";
				filesNames[0] = "teacherId";
				columnNames[1] = "????????????";
				filesNames[1] = "teacherName";
				for (int i = 0; i < aptTaskItems.length; i++) {
					String taskItem = aptTaskItems[i];
					AptTaskItem ti = aptTaskItemService.findAptTaskItemById(Integer.valueOf(taskItem));
					if(ti != null){
						String checkType = ti.getCheckType();
						String name = ti.getName();
						if(!checkType.equals(AptContans.DAILY)){
							if(checkType.equals(AptContans.BONUS)){
								name = "??????";
							}else if(checkType.equals(AptContans.DEDUCT)){
								name = "??????";
							}
						}
						columnNames[i + 2] = name;
						filesNames[i + 2] = ti.getId() + "";
					}
				}
				columnNames[aptTaskItems.length + 2] = "??????";
				filesNames[aptTaskItems.length + 2] = "sum";
				config.setTitles(columnNames);
				config.setFieldNames(filesNames);
				config.setSheetTitle("??????????????????");
				SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,aptTask.getName() + "??????????????????.xls");
//			}
		}
	}
	/**
	 * ???????????????Map??????
	 * @param taskItems
	 * @param startDate
	 * @param finishDate
	 * @param taskId
	 * @param teacherId
	 * @param condition
	 * @return
	 */
	private Map<String,Object> setMap(String[] taskItems,Date startDate,Date finishDate,Integer taskId,Integer teacherId,AptTaskScoreCondition condition){
		Map<String,Object> map = new HashMap<String, Object>();
		Float sum = 0f;
		if (startDate != null) {
			condition.setStartDate(startDate);
		}
		if (finishDate != null) {
			condition.setFinishDate(finishDate);
		}
		if (condition.getAptTaskItemId() != null) {
			condition.setAptTaskId(taskId);
			condition.setId(null);
		}
		if(teacherId != null){
			condition.setTeacherId(teacherId);
		}
		if(taskId != null){
			condition.setAptTaskId(taskId);
			AptTaskItemCondition aptTaskItemCondition = new AptTaskItemCondition();
			aptTaskItemCondition.setAptTaskId(taskId);
			if(taskItems.length > 0 && taskItems != null){
				for(String taskItem : taskItems){
					condition.setAptTaskItemId(Integer.parseInt(taskItem));
					Float score = aptTaskScoreService.countScore(condition);
					sum += score == null ? 0 : score;
					map.put(taskItem + "", score == null ? 0 : score);
				}
			}
		}
		map.put("sum", sum);
		return map;
	}
	/**
	 * ????????????-????????????
	 * 
	 * @param page
	 * @param order
	 * @param sub
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/sumDetail")
	public ModelAndView sumDetail(
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "aptTaskId", required = false) Integer aptTaskId,
			@RequestParam(value = "teacherId", required = false) Integer teacherId,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		List<Integer> teacherIds = new ArrayList<Integer>();
		// ??????????????????
		AptTask task = aptTaskService.findAptTaskById(aptTaskId);
		// ????????????
		AptTaskCondition condition = new AptTaskCondition();
		condition.setId(aptTaskId);
		condition.setSchoolId(user.getSchoolId());
		Long judgeCount = aptTaskService.getJudgeCount(condition);// ????????????
		// ????????????
		Long userCount = null;
		String scopeType = task.getScopeType();
		if (AptContans.SCOPE_SCHOOL.equals(scopeType)) {
			List<Teacher> teachers = teacherService
					.findTeacherListBySchoolId(user.getSchoolId());
			if (teachers.size() > 0) {
				for (Teacher teacher : teachers) {
					teacherIds.add(teacher.getId());
				}
			}
			userCount = (long) teachers.size();

		} else if (AptContans.SCOPE_DEPARTMENT.equals(scopeType)) {
			List<DepartmentTeacher> teachers = departmentTeacherService
					.findByDepartmentId(task.getDepartmentId());
			userCount = (long) teachers.size();
			if (teachers.size() > 0) {
				for (DepartmentTeacher dt : teachers) {
					teacherIds.add(dt.getTeacherId());
				}
			}

		} else {
			AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
			aptTaskUserCondition.setAptTaskId(aptTaskId);
			// userCount = aptTaskUserService.count(aptTaskUserCondition);
			List<AptTaskUser> taskUsers = aptTaskUserService
					.findAptTaskUserByCondition(aptTaskUserCondition);
			userCount = (long) taskUsers.size();
			if (taskUsers.size() > 0) {
				for (AptTaskUser tu : taskUsers) {
					teacherIds.add(tu.getTeacherId());
				}
			}
		}
		// ????????????
		
		AptTaskScoreCondition scoreCondition = new AptTaskScoreCondition();
		scoreCondition.setCheckType(AptContans.BONUS);
		scoreCondition.setAptTaskId(aptTaskId);
		Long bonusCount = aptTaskScoreService.countForPoints(scoreCondition);
		
		scoreCondition.setCheckType(AptContans.DEDUCT);
		Long deductCount = aptTaskScoreService.countForPoints(scoreCondition);

		// ???????????????
		List<TaskVo> list = aptTaskService.findCountByTeacher(aptTaskId,
				teacherIds);
		CountCompartor comp = new CountCompartor();
		Collections.sort(list, comp);
		if (list.size() > 0) {
			for (TaskVo vo : list) {
				Integer id = vo.getTeacherId();
				Teacher teacher = teacherService.findTeacherById(id);
				List<DepartmentTeacher> dts = departmentTeacherService
						.findDepartmentTeacherByTeacherIdAndSchoolId(id,
								user.getSchoolId());
				if (teacher != null) {
					vo.setTeacherName(teacher.getName());
				}
				String departmentName = null;
				if (dts.size() > 0) {
					for (DepartmentTeacher dt : dts) {
						departmentName = departmentName == null ? dt
								.getDepartmentName() : departmentName + ","
								+ dt.getDepartmentName();
					}
					vo.setDepartmentName(departmentName);
				}
			}
		}
		model.addAttribute("task", task);
		model.addAttribute("judgeCount", judgeCount);
		model.addAttribute("userCount", userCount);
		model.addAttribute("bonusCount", bonusCount);
		model.addAttribute("deductCount", deductCount);
		model.addAttribute("list", list);

		return new ModelAndView(structurePath("/sum_detail"));
	}

	/**
	 * ?????? -- ????????????
	 * 
	 * @param user
	 * @param page
	 * @param order
	 * @param aptTaskId
	 * @param teacherId
	 * @param scopeType
	 * @param model
	 * @param sub
	 * @return
	 */
	@RequestMapping(value = "/sumDetailDept")
	@ResponseBody
	public List<TaskVo> sumDetailDept(
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "aptTaskId", required = false) Integer aptTaskId,
			@RequestParam(value = "teacherId", required = false) Integer teacherId,
			@RequestParam(value = "scopeType", required = false) Integer scopeType,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		AptTask task = aptTaskService.findAptTaskById(aptTaskId);
		List<TaskVo> taskVos = new ArrayList<TaskVo>();
		// ???????????????taskVos
		if (task.getScopeType().equals(AptContans.SCOPE_DEPARTMENT)) {
			// ???????????? --??????
			TaskVo taskVo = getTaskVoByDeptId(task.getDepartmentId(), aptTaskId);
			taskVos.add(taskVo);
			// model.addAttribute("taskVo", taskVo);
		} else if (task.getScopeType().equals(AptContans.SCOPE_SCHOOL)) {
			// ???????????? --??????
			List<Department> departments = departmentService
					.findDepartmentBySchoolId(user.getSchoolId(), null, null);
			if (departments.size() > 0) {
				for (Department dept : departments) {
					TaskVo taskVo = getTaskVoByDeptId(dept.getId(), aptTaskId);
					taskVos.add(taskVo);
				}
			}
		} else {
			// ???????????? --????????????
			AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
			aptTaskUserCondition.setAptTaskId(aptTaskId);
			List<AptTaskUser> taskUsers = aptTaskUserService
					.findAptTaskUserByCondition(aptTaskUserCondition);
			if (taskUsers.size() > 0) {
				for (AptTaskUser taskUser : taskUsers) {
					List<DepartmentTeacher> dts = departmentTeacherService
							.findDepartmentTeacherByTeacherIdAndSchoolId(
									taskUser.getTeacherId(), user.getSchoolId());
					if (dts.size() > 0) {
						for (DepartmentTeacher dt : dts) {
							TaskVo taskVo = getTaskVoByDeptId(
									dt.getDepartmentId(), aptTaskId);
							taskVos.add(taskVo);
						}
					}
				}
			}
		}
		return taskVos;
	}

	private TaskVo getTaskVoByDeptId(Integer deptId, Integer taskId) {
		List<Integer> teacherIds = new ArrayList<Integer>();
		Department dept = departmentService.findDepartmentById(deptId);
		TaskVo taskVo = new TaskVo();
		if (dept != null) {
			List<DepartmentTeacher> teachers = departmentTeacherService
					.findByDepartmentId(deptId);
			if (teachers.size() > 0) {
				for (DepartmentTeacher dt : teachers) {
					teacherIds.add(dt.getTeacherId());
				}
			}
			List<TaskVo> list = aptTaskService.findCountByTeacher(taskId,
					teacherIds);
			Float dailyCount = 0f;
			Float deductCount = 0f;
			Float bonusCount = 0f;
			if (list.size() > 0) {
				for (TaskVo vo : list) {
					dailyCount += vo.getDailyCount() == null ? 0l : vo
							.getDailyCount();
					deductCount += vo.getDeductCount() == null ? 0l : vo
							.getDeductCount();
					bonusCount += vo.getBonusCount() == null ? 0l : vo
							.getBonusCount();
				}
			}
			taskVo.setBonusCount(bonusCount);
			taskVo.setDailyCount(dailyCount);
			taskVo.setDeductCount(deductCount);
			taskVo.setDepartmentName(dept.getName());
			taskVo.setDepartmentNum(teachers.size());
		}
		return taskVo;
	}

	/**
	 * ?????? -- ????????????
	 * 
	 * @param user
	 * @param page
	 * @param order
	 * @param aptTaskId
	 * @param teacherId
	 * @param scopeType
	 * @param model
	 * @param sub
	 * @return
	 */
	@RequestMapping(value = "/sumDetailPerson")
	@ResponseBody
	public List<TaskItemVo> sumDetailPerson(
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "aptTaskId", required = false) Integer aptTaskId,
			@RequestParam(value = "teacherId", required = false) Integer teacherId,
			@RequestParam(value = "scopeType", required = false) Integer scopeType,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		List<TaskItemVo> voList = aptTaskItemService
				.findAptTaskItemAndCategoryByTaskIdAndTeacherId(aptTaskId,
						teacherId);
		if (voList.size() > 0) {
			for (TaskItemVo taskItemVo : voList) {
				List<AptTaskItemVo> taskItems = taskItemVo.getTaskItems();
				if (taskItems.size() > 0) {
					for (AptTaskItemVo taskItem : taskItems) {
						AptTaskScoreCondition condition = new AptTaskScoreCondition();
						condition.setAptTaskItemId(taskItem.getId());
						condition.setTeacherId(teacherId);
						Float count = aptTaskScoreService.countScore(condition);
						taskItem.setItemTotalNum(count);
					}
				}
			}
		}
		return voList;
	}

	/**
	 * ???????????? -- ??????????????????
	 * 
	 * @param user
	 * @param page
	 * @param order
	 * @param aptTaskId
	 * @param teacherId
	 * @param scopeType
	 * @param model
	 * @param sub
	 * @return
	 */
	@RequestMapping(value = "/sumDetailPersonBonus")
	@ResponseBody
	public List<AptTaskItemVo> sumDetailPersonBonus(
			@CurrentUser UserInfo user,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "aptTaskId", required = false) Integer aptTaskId,
			@RequestParam(value = "teacherId", required = false) Integer teacherId,
			@RequestParam(value = "scopeType", required = false) Integer scopeType,
			Model model,
			@RequestParam(value = "sub", required = false) String sub) {
		AptTaskItemCondition aptTaskItemCondition = new AptTaskItemCondition();
		aptTaskItemCondition.setAptTaskId(aptTaskId);
		List<AptTaskItem> list = aptTaskItemService
				.findBonusByCondition(aptTaskItemCondition);
		List<AptTaskItemVo> resultVos = new ArrayList<AptTaskItemVo>();
		if (list.size() > 0) {
			for (AptTaskItem taskItem : list) {
				AptTaskItemVo vo = new AptTaskItemVo();
				BeanUtils.copyProperties(taskItem, vo);
				AptTaskScoreCondition condition = new AptTaskScoreCondition();
				condition.setAptTaskId(aptTaskId);
				condition.setTeacherId(teacherId);
				condition.setAptTaskItemId(taskItem.getId());
				Float count = aptTaskScoreService.countScore(condition);
				vo.setItemTotalNum(count);
				resultVos.add(vo);
			}
		}
		return resultVos;
	}

	/**
	 * ??????????????????
	 * 
	 * @param page
	 * @param order
	 * @param sub
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/loadRuleItem")
	public ModelAndView editRuleItem(@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@ModelAttribute("condition") AptTaskItemCondition condition,
			@RequestParam(value = "sub", required = false) String sub,
			Model model) {
		String viewPath = null;
		AptTask task = aptTaskService.findAptTaskById(condition.getAptTaskId());
		List<AptTaskItem> taskItems = aptTaskItemService
				.findAptTaskItemByCondition(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/editRuleItem_list");
		} else {
			viewPath = structurePath("/editRuleItem_index");
		}
		model.addAttribute("taskItems", taskItems);
		model.addAttribute("task", task);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ??????????????????
	 * 
	 * @param id
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/editRuleItem/{id}", method = RequestMethod.GET)
	public ModelAndView editRuleItem(@PathVariable(value = "id") Integer id,
			Model model) {
		String viewPath = null;
		AptTaskItem taskItem = aptTaskItemService.findAptTaskItemById(id);
		AptTaskJudgeCondition condition = new AptTaskJudgeCondition();
		condition.setAptTaskItemId(taskItem.getId());
		List<AptTaskJudge> taskJudges = aptTaskJudgeService
				.findAptTaskJudgeByCondition(condition);
		String teacherIds = null;
		if (taskJudges.size() > 0) {
			for (AptTaskJudge taskJudge : taskJudges) {
				teacherIds = teacherIds == null ? taskJudge.getTeacherId() + ""
						: teacherIds + "," + taskJudge.getTeacherId();
			}
		}
		if (taskItem.getCheckType().equals("01")) {
			viewPath = structurePath("/editRuleItem_input");
		} else {
			viewPath = structurePath("/editRuleItem_bonus_input");
		}
		model.addAttribute("taskItem", taskItem);
		model.addAttribute("taskId", taskItem.getAptTaskId());
		model.addAttribute("teacherIds", teacherIds);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ?????????????????????
	 * 
	 * @param id
	 * @param aptTaskItem
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deleteRuleItem/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteRuleItem(@PathVariable(value = "id") Integer id,
			@ModelAttribute("aptTaskItem") AptTaskItem aptTaskItem, Model model) {
		if (aptTaskItem != null) {
			aptTaskItem.setId(id);
		}
		try {
			this.aptTaskItemService.remove(aptTaskItem);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * ?????????????????????
	 * 
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/taskItemCreator", method = RequestMethod.GET)
	public ModelAndView taskItemCreator(
			@CurrentUser UserInfo user,
			@RequestParam(value = "isTypeDaily", required = false) Boolean isTypeDaily,
			Model model, @RequestParam(value = "id") Integer id) {
		String viewPath = null;
		if (isTypeDaily) {
			viewPath = structurePath("/editRuleItem_input");
		} else {
			viewPath = structurePath("/editRuleItem_bonus_input");
		}
		model.addAttribute("taskId", id);
		return new ModelAndView(viewPath, model.asMap());
	}

	/**
	 * ???????????? -- ??????????????????
	 * 
	 * @param aptTaskItem
	 * @param judgeNames
	 * @param teacherIds
	 * @param user
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/taskItemCreator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation taskItemCreator(
			@ModelAttribute("aptTaskItem") AptTaskItem aptTaskItem,
			@RequestParam(value = "judgeNames", required = false) String judgeNames,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@CurrentUser UserInfo user) {
		if (judgeNames != null && !"".equals(judgeNames)) {
			aptTaskItem.setJudgeName(judgeNames);
		}
		String checkType = aptTaskItem.getCheckType();
		if (checkType == null) {
			aptTaskItem.setCheckType(AptContans.DAILY);
		}
		aptTaskItem = aptTaskItemService.add(aptTaskItem);
		if (teacherIds != null && !"".equals(teacherIds)) {
			if (teacherIds.contains(",")) {
				String[] teachers = teacherIds.split(",");
				for (int i = 0; i < teachers.length; i++) {
					Integer teacherId = Integer.parseInt(teachers[i]);
					Teacher teacher = teacherService.findTeacherById(teacherId);
					AptTaskJudge aptTaskJudge = new AptTaskJudge();
					aptTaskJudge.setAptTaskItemId(aptTaskItem.getId());
					aptTaskJudge.setTeacherId(teacherId);
					if (teacher != null) {
						aptTaskJudge.setUserId(teacher.getUserId());
					}
					aptTaskJudgeService.add(aptTaskJudge);
				}
			} else {
				Integer teacherId = Integer.parseInt(teacherIds);
				Teacher teacher = teacherService.findTeacherById(teacherId);
				AptTaskJudge aptTaskJudge = new AptTaskJudge();
				aptTaskJudge.setAptTaskItemId(aptTaskItem.getId());
				aptTaskJudge.setTeacherId(teacherId);
				if (teacher != null) {
					aptTaskJudge.setUserId(teacher.getUserId());
				}
				aptTaskJudgeService.add(aptTaskJudge);
			}

		}
		return aptTaskItem != null ? new ResponseInfomation(
				aptTaskItem.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}

	/**
	 * ???????????? -- ??????????????????
	 * 
	 * @param aptTaskItem
	 * @param judgeNames
	 * @param teacherIds
	 * @param user
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/taskItemEdit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation taskItemEdit(
			@ModelAttribute("aptTaskItem") AptTaskItem aptTaskItem,
			@RequestParam(value = "judgeNames", required = false) String judgeNames,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@CurrentUser UserInfo user) {
		if (judgeNames != null && !"".equals(judgeNames)) {
			aptTaskItem.setJudgeName(judgeNames);
		}
		aptTaskItem = aptTaskItemService.modify(aptTaskItem);
		if (aptTaskItem != null) {
			// ???????????????????????????????????? ?????????????????????
			AptTaskJudgeCondition condition = new AptTaskJudgeCondition();
			condition.setAptTaskItemId(aptTaskItem.getId());
			List<AptTaskJudge> taskJudges = aptTaskJudgeService
					.findAptTaskJudgeByCondition(condition);
			if (taskJudges.size() > 0) {
				for (AptTaskJudge taskJudge : taskJudges) {
					aptTaskJudgeService.remove(taskJudge);
				}
			}
			// ???????????????
			if (teacherIds != null && !"".equals(teacherIds)) {
				if (teacherIds.contains(",")) {
					String[] teachers = teacherIds.split(",");
					for (int i = 0; i < teachers.length; i++) {
						Integer teacherId = Integer.parseInt(teachers[i]);
						Teacher teacher = teacherService
								.findTeacherById(teacherId);
						AptTaskJudge aptTaskJudge = new AptTaskJudge();
						aptTaskJudge.setAptTaskItemId(aptTaskItem.getId());
						aptTaskJudge.setTeacherId(teacherId);
						if (teacher != null) {
							aptTaskJudge.setUserId(teacher.getUserId());
						}
						aptTaskJudgeService.add(aptTaskJudge);
					}
				} else {
					Integer teacherId = Integer.parseInt(teacherIds);
					Teacher teacher = teacherService.findTeacherById(teacherId);
					AptTaskJudge aptTaskJudge = new AptTaskJudge();
					aptTaskJudge.setAptTaskItemId(aptTaskItem.getId());
					aptTaskJudge.setTeacherId(teacherId);
					if (teacher != null) {
						aptTaskJudge.setUserId(teacher.getUserId());
					}
					aptTaskJudgeService.add(aptTaskJudge);
				}

			}
		}
		return aptTaskItem != null ? new ResponseInfomation(
				aptTaskItem.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}

	/**
	 * ?????????????????????
	 * 
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		// ????????????????????????
		AptRuleCondition condition = new AptRuleCondition();
		condition.setSchoolId(user.getSchoolId());
		List<AptRule> rules = aptRuleService.findAptRuleByCondition(condition);
		model.addAttribute("rules", rules);
		model.addAttribute("schoolId", user.getSchoolId());
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	/**
	 * ??????????????????
	 * 
	 * @param aptTask
	 * @param user
	 * @return
	 * @author ?????????
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			@ModelAttribute("aptTask") AptTask aptTask,
			@RequestParam(value = "teacherIds", required = false) String teacherIds,
			@CurrentUser UserInfo user) {
		if (user.getSchoolId() != null) {
			aptTask.setSchoolId(user.getSchoolId());
		}
		aptTask.setCreateUserId(user.getId());
		aptTask = this.aptTaskService.add(aptTask);
		
		
		
		
		
		if (teacherIds != null && !"".equals(teacherIds)) {
			if (teacherIds.contains(",")) {
				String[] teachers = teacherIds.split(",");
				for (int i = 0; i < teachers.length; i++) {
					Integer teacherId = Integer.parseInt(teachers[i]);
					Teacher teacher = teacherService.findTeacherById(teacherId);
					AptTaskUser aptTaskUser = new AptTaskUser();
					aptTaskUser.setAptTaskId(aptTask.getId());
					aptTaskUser.setTeacherId(teacherId);
					if (teacher != null) {
						aptTaskUser.setUserId(teacher.getUserId() == null ? -1
								: teacher.getUserId());
					}
					aptTaskUserService.add(aptTaskUser);
				}
			} else {
				Integer teacherId = Integer.parseInt(teacherIds);
				Teacher teacher = teacherService.findTeacherById(teacherId);
				AptTaskUser aptTaskUser = new AptTaskUser();
				aptTaskUser.setAptTaskId(aptTask.getId());
				aptTaskUser.setTeacherId(teacherId);
				if (teacher != null) {
					aptTaskUser.setUserId(teacher.getUserId() == null ? -1
							: teacher.getUserId());
				}
				aptTaskUserService.add(aptTaskUser);
			}
		}
		if (aptTask.getRuleId() != null) {
			AptRuleItemCondition condition = new AptRuleItemCondition();
			condition.setRuleId(aptTask.getRuleId());
			List<AptRuleItemVo> ruleItems = aptRuleItemService
					.findAptRuleItemByCondition(condition);
			if (ruleItems.size() > 0) {
				for (AptRuleItemVo vo : ruleItems) {
					AptTaskItem item = new AptTaskItem();
					item.setAptRuleItemId(vo.getId());
					item.setAptTaskId(aptTask.getId());
					item.setCategory(vo.getCategory());
					item.setCheckType(vo.getCheckType());
					item.setDescription(vo.getDescription());
					item.setLabel(vo.getLabel());
					item.setListOrder(vo.getListOrder());
					item.setName(vo.getName());
					item.setParentId(vo.getParentId());
					item.setScale(vo.getScale());
					item.setScore(vo.getScore());
					aptTaskItemService.add(item);
				}
			}
		}
		return aptTask != null ? new ResponseInfomation(aptTask.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}
	
	@RequestMapping(value = "chcekTypeChecker")
	@ResponseBody
	public boolean chcekTypeChecker(
			@RequestParam(value = "checkType")String checkType,
			@RequestParam(value = "taskId")Integer taskId,
			@RequestParam(value = "itemId",required = false)Integer itemId){
		boolean isExit = true;
		if(itemId != null){
			AptTaskItem item = aptTaskItemService.findAptTaskItemById(itemId);
			if(checkType.equals(item.getCheckType())){
				isExit = true;
			}else{
				isExit = false;
			}
		}else{
			AptTaskItemCondition condition = new AptTaskItemCondition();
			condition.setAptTaskId(taskId);
			condition.setCheckType(checkType);
			List<AptTaskItem> list = aptTaskItemService.findBonusByCondition(condition);
			if(list.size() > 0){
				isExit = false;
			}
		}
		return isExit;
	}

	@RequestMapping(value = "/checkEdit", method = RequestMethod.GET)
	public ModelAndView checkEdit(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AptTask aptTask = this.aptTaskService.findAptTaskById(id);
		model.addAttribute("aptTask", aptTask);
		return new ModelAndView(structurePath("/checkEdit"), model.asMap());
	}

	@RequestMapping(value = "/checkView", method = RequestMethod.GET)
	public ModelAndView checkView(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AptTask aptTask = this.aptTaskService.findAptTaskById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("aptTask", aptTask);
		return new ModelAndView(structurePath("/checkEdit"), model.asMap());
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id,
			Model model, @CurrentUser UserInfo user) {
		AptTask aptTask = this.aptTaskService.findAptTaskById(id);
		// ??????????????????
		AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
		aptTaskUserCondition.setAptTaskId(aptTask.getId());
		List<AptTaskUser> atUsers = aptTaskUserService
				.findAptTaskUserByCondition(aptTaskUserCondition);
		String teacherNames = null;
		String teacherIds = null;
		if (atUsers.size() > 0) {
			for (AptTaskUser atUser : atUsers) {
				Teacher teacher = teacherService.findTeacherById(atUser
						.getTeacherId());
				String teacherName = teacher.getName();
				Integer teacherId = teacher.getId();
				teacherNames = teacherNames == null ? teacherName
						: teacherNames + "," + teacherName;
				teacherIds = teacherIds == null ? teacherId + "" : teacherIds
						+ "," + teacherId;
			}
		}
		// ????????????????????????
		AptRuleCondition condition = new AptRuleCondition();
		condition.setSchoolId(user.getSchoolId());
		List<AptRule> rules = aptRuleService.findAptRuleByCondition(condition);
		model.addAttribute("rules", rules);
		model.addAttribute("aptTask", aptTask);
		model.addAttribute("atUsers", atUsers);
		model.addAttribute("teacherNames", teacherNames);
		model.addAttribute("teacherIds", teacherIds);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		AptTask aptTask = this.aptTaskService.findAptTaskById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("aptTask", aptTask);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, AptTask aptTask) {
		if (aptTask != null) {
			aptTask.setId(id);
		}
		try {
			this.aptTaskService.remove(aptTask);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(
			@PathVariable(value = "id") Integer id,
			AptTask aptTask,
			@RequestParam(value = "teacherIds", required = false) String teacherIds) {
		aptTask.setId(id);
		aptTask = this.aptTaskService.modify(aptTask);
		if (aptTask != null) {
			// ??????????????????????????????????????? ????????????????????????
			AptTaskUserCondition condition = new AptTaskUserCondition();
			condition.setAptTaskId(id);
			List<AptTaskUser> taskUsers = aptTaskUserService
					.findAptTaskUserByCondition(condition);
			if (taskUsers.size() > 0) {
				for (AptTaskUser taskUser : taskUsers)
					aptTaskUserService.remove(taskUser);
			}
			// ??????????????????
			if (teacherIds != null && !"".equals(teacherIds)) {
				if (teacherIds.contains(",")) {
					String[] teachers = teacherIds.split(",");
					for (int i = 0; i < teachers.length; i++) {
						Integer teacherId = Integer.parseInt(teachers[i]);
						Teacher teacher = teacherService
								.findTeacherById(teacherId);
						AptTaskUser taskUser = new AptTaskUser();
						taskUser.setAptTaskId(aptTask.getId());
						taskUser.setTeacherId(teacherId);
						if (teacher != null) {
							taskUser.setUserId(teacher.getUserId());
						}
						aptTaskUserService.add(taskUser);
					}
				} else {
					Integer teacherId = Integer.parseInt(teacherIds);
					Teacher teacher = teacherService.findTeacherById(teacherId);
					AptTaskUser taskUser = new AptTaskUser();
					taskUser.setAptTaskId(aptTask.getId());
					taskUser.setTeacherId(teacherId);
					if (teacher != null) {
						taskUser.setUserId(teacher.getUserId() == null ? -1
								: teacher.getUserId());
					}
					aptTaskUserService.add(taskUser);
				}
			}
		}
		return aptTask != null ? new ResponseInfomation(aptTask.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, AptTaskCondition condition) {
		if (condition != null) {
			condition.setSchoolId(user.getSchoolId());
		}
	}

	class CountCompartor implements Comparator<TaskVo> {
		public int compare(TaskVo vo1, TaskVo vo2) {
			Float score1 = (vo1.getBonusCount() == null ? 0 : vo1
					.getBonusCount())
					+ (vo1.getDailyCount() == null ? 0 : vo1.getDailyCount())
					+ (vo1.getDeductCount() == null ? 0 : vo1.getDeductCount());
			Float score2 = (vo2.getBonusCount() == null ? 0 : vo2
					.getBonusCount())
					+ (vo2.getDailyCount() == null ? 0 : vo2.getDailyCount())
					+ (vo2.getDeductCount() == null ? 0 : vo2.getDeductCount());
			if (score1 >= score2) {
				return -1;
			} else {
				return 1;
			}
		}
	}

	// ????????????????????????
	@RequestMapping("/downLoadPage")
	public ModelAndView upLoadScoreInfoPage(Model model,
			@CurrentUser UserInfo user) {
		// ???????????????????????????????????? ?????????
		AptTaskCondition aptTaskCondition = new AptTaskCondition();
		aptTaskCondition.setIsDelete(false);
		aptTaskCondition.setSchoolId(user.getSchoolId());
		List<AptTaskVo> list = aptTaskService
				.findAptTaskByCondition(aptTaskCondition);
		List<AptTaskVo> notFinishTask = new ArrayList<AptTaskVo>();
		if (list != null && list.size() > 0) {
			for (AptTaskVo taskVo : list) {
				if (taskVo.getFinishDate().after(new Date())) {
					notFinishTask.add(taskVo);
				}
			}
		}
		model.addAttribute("taskList", notFinishTask);
		return new ModelAndView(structurePath("/downLoadPage"), model.asMap());
	}

	@RequestMapping(value = "/downLoadData")
	@ResponseBody
	public void downLoadPage(@CurrentUser UserInfo user,
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "taskId", required = false)Integer taskId) throws Exception {
		// ??????????????????ID??????????????????
		AptTask aptTask = aptTaskService.findAptTaskById(taskId);

		ParseConfig config = SzxyExcelTookit.getConfig();

		// ????????????????????????
		List<Object> maps = new ArrayList<Object>();

		// ???????????????????????????
		Map<String, Object> map = new HashMap<String, Object>();

		if (aptTask != null) {
			// ??????????????????
			if (aptTask.getScopeType().equals(AptContans.SCOPE_DEPARTMENT)) {
				List<DepartmentTeacher> list = departmentTeacherService.findByDepartmentId(aptTask.getDepartmentId());
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						// ???????????????????????????
						map = new HashMap<String, Object>();
						map.put("teacherId", list.get(i).getTeacherId());
						map.put("teacherName", list.get(i).getTeacherName());
						map.put("name", "");
						map.put("getTime", "");
						map.put("judgeTeacherName", "");
						maps.add(map);
					}
				}
			} else if (aptTask.getScopeType().equals(AptContans.SCOPE_OTHER)) {
				AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
				aptTaskUserCondition.setAptTaskId(aptTask.getId());
				List<AptTaskUser> list = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						Teacher teacher = teacherService.findTeacherById(list.get(i).getTeacherId());
						// ???????????????????????????
						map = new HashMap<String, Object>();
						map.put("teacherId", teacher.getId());
						map.put("teacherName", teacher.getName());
						map.put("name", "");
						map.put("getTime", "");
						map.put("judgeTeacherName", "");
						maps.add(map);
					}
				}
			} else {
				List<Teacher> list = teacherService
						.findTeacherListBySchoolId(user.getSchoolId());
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						// ???????????????????????????
						map = new HashMap<String, Object>();
						map.put("teacherId", list.get(i).getId());
						map.put("teacherName", list.get(i).getName());
						map.put("name", "");
						map.put("getTime", "");
						map.put("judgeTeacherName", "");
						maps.add(map);
					}
				}
			}

			// ??????????????????
			AptRule aptRule = aptRuleService.findAptRuleById(aptTask
					.getRuleId());
			if (aptRule != null) {
				AptRuleItemCondition aptRuleItemCondition = new AptRuleItemCondition();
				aptRuleItemCondition.setRuleId(aptRule.getId());
				List<AptRuleItemVo> aptRuleItemList = aptRuleItemService.findAptRuleItemByCondition(aptRuleItemCondition);
				if (aptRuleItemList != null && aptRuleItemList.size() > 0) {
					// ??????
					String[] columnNames = new String[aptRuleItemList.size() + 4];
					// ???????????????????????????
					String[] filesNames = new String[aptRuleItemList.size() + 4];
					columnNames[0] = "??????ID";
					filesNames[0] = "teacherId";
					columnNames[1] = "????????????";
					filesNames[1] = "teacherName";
					for (int i = 0; i < aptRuleItemList.size(); i++) {
						columnNames[i + 2] = aptRuleItemList.get(i).getName()+"(????????????)";
						filesNames[i + 2] = "name";
					}
					columnNames[aptRuleItemList.size() + 2] = "????????????(????????? 2016/02/18)";
					columnNames[aptRuleItemList.size() + 3] = "?????????(?????????)";
					filesNames[aptRuleItemList.size() + 2] = "getTime";
					filesNames[aptRuleItemList.size() + 3] = "judgeTeacherName";
					config.setTitles(columnNames);
					config.setFieldNames(filesNames);
					config.setSheetTitle("??????????????????");
					SzxyExcelTookit.exportExcelToWEB(maps, config, request, response,aptTask.getName() + "??????????????????.xls");
				}
			}

		}
	}

	@RequestMapping(value = "/upLoadPage")
	@ResponseBody
	public ModelAndView upLoadPage(@RequestParam(value = "taskId", required = false)Integer taskId, Model model) {
		model.addAttribute("taskId", taskId);
		return new ModelAndView(structurePath("/upLoadPage"), model.asMap());
	}

	
	// ????????????
	@RequestMapping("/upLoaddata")
	@ResponseBody
	public Map<String, Object> upLoadScoreInfoByModel(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@CurrentUser UserInfo user, HttpServletResponse response,
			HttpServletRequest request,@RequestParam(value = "taskId", required = false)Integer aptTaskId) throws Exception {
		InputStream is = null;
		List<AptTaskImportVo> listForError = new ArrayList<AptTaskImportVo>();
		List<AptTaskImportVo> listForRight = new ArrayList<AptTaskImportVo>();
		Map<String,Object> map = new HashMap<String,Object>();
		is = fileUpload.getInputStream();
		List<AptTaskImportVo> excelList = this.excelDataToObject(is,aptTaskId);
		
		AptTaskScore ats = new AptTaskScore();
		List<Teacher> teacherList = null;
		Teacher judgeTeacher = null;
		Teacher teacher = null;
		
		TeacherCondition teacherCondition = new TeacherCondition();
		teacherCondition.setSchoolId(user.getSchoolId());
		teacherCondition.setIsDelete(false);
		teacherList = teacherService.findTeacherByCondition(teacherCondition,null,null);
		
		Map<String, Teacher> teacherMap = new HashMap<String, Teacher>();
		Map<String, Teacher> teacherMap1 = new HashMap<String, Teacher>();
		for (Teacher teacher2 : teacherList) {
			teacherMap.put(teacher2.getName(), teacher2);
			teacherMap1.put(""+teacher2.getId(), teacher2);
		}
		
		
		if(excelList != null && excelList.size() > 0){
			for(AptTaskImportVo vo : excelList){
				try{
					//???????????????????????????  ????????????????????? listForError ????????????????????????
					if(vo.getErrorMessage() != null && !"".equals(vo.getErrorMessage())){
						listForError.add(vo);
						continue;
					}
					
					if(teacherMap.get(vo.getJudgeTeacher()) != null){
						judgeTeacher = teacherMap.get(vo.getJudgeTeacher());
					}
					
					teacher =  teacherMap1.get(""+(vo.getTeacherId() == null ? -1 : vo.getTeacherId()));
					
					if(teacherMap.get(vo.getJudgeTeacher()) != null){
					}else{
						//????????????????????????????????????
						if(vo.getJudgeTeacher() == null || "".equals(vo.getJudgeTeacher()) || judgeTeacher == null || "".equals(judgeTeacher)){
							vo.setErrorMessage(vo.getErrorMessage()==null?"":vo.getErrorMessage()+"????????????????????????????????????");
						}
						listForError.add(vo);
						continue;
					}
					
					if(vo.getTaskItem() != null && vo.getTaskItem().size() > 0){
						for(AptTaskItemImportVo itemVo : vo.getTaskItem()){
							if(teacher != null){
								try{
									if(itemVo.getScoreId() != null){
										ats = new AptTaskScore();
										ats.setId(itemVo.getScoreId());
										ats.setAptTaskId(aptTaskId);
										ats.setAptTaskItemId(itemVo.getId());
										ats.setCheckTime(vo.getGetTime());
										ats.setCheckType("01");
										ats.setIsDelete(false);
										ats.setJudgeId(judgeTeacher == null ? -1 : judgeTeacher.getId());
										ats.setModifyDate(new Date());
										ats.setPassed(false);
										ats.setScore((itemVo.getScore() == null || itemVo.getScore() == "") ? null : Float.valueOf(itemVo.getScore()));
										ats.setTeacherId(teacher.getId());
										aptTaskScoreService.modify(ats);
									}else{
										ats = new AptTaskScore();
										ats.setAptTaskId(aptTaskId);
										ats.setAptTaskItemId(itemVo.getId());
										ats.setCheckTime(vo.getGetTime());
										ats.setCheckType("01");
										ats.setCreateDate(new Date());
										ats.setIsDelete(false);
										ats.setJudgeId(judgeTeacher == null ? -1 : judgeTeacher.getId());
										ats.setModifyDate(new Date());
										ats.setPassed(false);
										ats.setScore((itemVo.getScore() == null || itemVo.getScore() == "") ? null : Float.valueOf(itemVo.getScore()));
										ats.setTeacherId(teacher.getId());
										aptTaskScoreService.add(ats);
									}
								}catch(Exception e){
									vo.setErrorMessage("????????????"+vo.getTeacherName()+"?????????????????????????????????????????????????????????????????????????????????");
								}
							}
						}
					}
					
					//??????????????????????????????????????????????????????????????????????????? ??????????????????
					listForRight.add(vo);
					}catch(Exception e){
						//???????????????????????????  ????????????????????????????????????
						listForError.add(vo);
					}
				}
		}
		
		map.put("ERROR", listForError);
		map.put("SUCCESS", listForRight);
		return map;
	}

	private List<AptTaskImportVo> excelDataToObject(InputStream is,Integer aptTaskId)
			throws Exception {
		HSSFWorkbook workbook = null;
		
		//???????????????????????????????????????
		List<AptTaskImportVo> listForTeacher = new ArrayList<AptTaskImportVo>();
		
		//??????????????????????????????????????????
		List<AptTaskItemImportVo> list = new ArrayList<AptTaskItemImportVo>();
		
		//?????????????????????????????????
		AptTaskImportVo vo = new AptTaskImportVo();
		
		//???????????????????????????
		AptTaskItemImportVo atvo = new AptTaskItemImportVo();
		
		workbook = new HSSFWorkbook(is);
		HSSFSheet sh = workbook.getSheetAt(0);
		
		if(sh != null){
			int rowNum = sh.getLastRowNum();
			
			//???????????????ID
			AptTaskItemCondition aptTaskItemCondition = new AptTaskItemCondition();
			aptTaskItemCondition.setAptTaskId(aptTaskId);
			List<AptTaskItem> aptTaskItemList = aptTaskItemService.findAptTaskItemByCondition(aptTaskItemCondition);
			
			Map<String, AptTaskItem> aptTaskItemMap = new HashMap<String, AptTaskItem>();
			for (AptTaskItem aptTask : aptTaskItemList) {
				aptTaskItemMap.put(aptTask.getName()+"(????????????)", aptTask);
			}
			
			//??????????????????
			AptTaskScoreCondition aptTaskScoreCondition = new AptTaskScoreCondition();
			aptTaskScoreCondition.setAptTaskId(aptTaskId);
			List<AptTaskScore> listScore = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
			
			Map<String, AptTaskScore> listScoreMap = new HashMap<String, AptTaskScore>();
			for (AptTaskScore score : listScore) {
				listScoreMap.put(""+score.getTeacherId()+score.getAptTaskItemId()+score.getCheckTime(), score);
			}
			
			//?????????
			for(int i = 1; i <= rowNum; i++){
				HSSFRow hr = sh.getRow(i);
				HSSFRow hr0 = sh.getRow(0);
				//????????????Id
				String hcOfTeacherId = parseExcel(hr.getCell(0));
				//??????????????????
				String hcOfTeacherName = parseExcel(hr.getCell(1));
				
				vo = new AptTaskImportVo();
				//??????????????????
				String dateStr = parseExcel(hr.getCell(hr.getLastCellNum()-2));
				Date getTime = null;
				if(dateStr == null || "".equals(dateStr)){
					vo.setErrorMessage("????????????????????????");
				}else{
					try{
						SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
						getTime = sdf.parse(dateStr);
						vo.setGetTime(getTime);
					}catch(Exception e){
						vo.setErrorMessage("??????????????????????????????");
					}
				}
				
				//???????????????
				String hcOfjudge = parseExcel(hr.getCell(hr.getLastCellNum()-1));;
				if(hcOfjudge == null || "".equals(hcOfjudge)){
					vo.setErrorMessage(vo.getErrorMessage()==null?"":vo.getErrorMessage()+"?????????????????????");
				}
				
				list = new ArrayList<AptTaskItemImportVo>();
				
				//??????????????????
				for(int j = 2; j < hr.getLastCellNum()-2; j++){
					//????????????????????????
					String hcOfItemsScore = parseExcel(hr.getCell(j));
					String hcOfItemsName = parseExcel(hr0.getCell(j));
					
					//????????????????????????????????????????????????
					if(hcOfItemsScore == null || "".equals(hcOfItemsScore)){
						continue;
					}
					
					atvo = new AptTaskItemImportVo();
					try{
						if(hcOfItemsScore != null && !"".equals(hcOfItemsScore)){
							Double.valueOf(hcOfItemsScore+"");
						}else{
							hcOfItemsScore = "0";
						}
					}catch(Exception e){
						atvo.setErrorMessage("???????????? ???"+hcOfItemsName+"?????????????????????????????????");
					}
					
					//????????????ID
					Integer id = null;
					if(aptTaskItemMap != null){
						AptTaskItem apt = aptTaskItemMap.get(hcOfItemsName);
						if(apt != null){
							id = apt.getId();
						}
					}
					
					//?????????ID
					Integer ScoreId = null;
					if(listScoreMap != null){
						AptTaskScore sc = listScoreMap.get(hcOfTeacherId+id+getTime);
						if(sc != null){
							ScoreId = sc.getId();
						}
					}
					
					atvo.setId(id);
					atvo.setName(hcOfItemsName+"");
					atvo.setScore(hcOfItemsScore+"");
					atvo.setScoreId(ScoreId);
					list.add(atvo);
				}
				
				//?????????????????????????????????????????????????????? ??????????????????????????????
				if(list != null && list.size() > 0){
					String error = "";
					for(AptTaskItemImportVo ati : list){
						if(ati.getErrorMessage() != null && !"".equals(ati.getErrorMessage())){
							if(error == ""){
								error += ati.getErrorMessage();
							}else{
								error += "," + ati.getErrorMessage();
							}
						}
					}
					vo.setErrorMessage(vo.getErrorMessage() == null ? "" + error : vo.getErrorMessage() + error);
				}
				
				vo.setTeacherId(Integer.valueOf(hcOfTeacherId+""));
				vo.setTeacherName(hcOfTeacherName+"");
				vo.setJudgeTeacher(hcOfjudge+"");
				vo.setGetTime(getTime);
				vo.setTaskItem(list);
				listForTeacher.add(vo);
			}
		}
		return listForTeacher;
	}
	
	private String parseExcel(Cell cell) {
		String result = new String();
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:// ????????????
			if (HSSFDateUtil.isCellDateFormatted(cell)) {// ?????????????????????????????????
				SimpleDateFormat sdf = null;
				if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
						.getBuiltinFormat("h:mm")) {
					sdf = new SimpleDateFormat("HH:mm");
				} else {// ??????
					sdf = new SimpleDateFormat("yyyy-MM-dd");
				}
				Date date = cell.getDateCellValue();
				result = sdf.format(date);
			} else if (cell.getCellStyle().getDataFormat() == 58) {
				// ??????????????????????????????m???d???(??????????????????????????????id?????????id?????????58)
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
		case HSSFCell.CELL_TYPE_STRING:// String??????
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

}
