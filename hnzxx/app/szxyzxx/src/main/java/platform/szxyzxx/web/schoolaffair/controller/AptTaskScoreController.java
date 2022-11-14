package platform.szxyzxx.web.schoolaffair.controller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeacherSort;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeacherSortService;
import platform.education.generalTeachingAffair.vo.TeacherSortCondition;
import platform.education.school.affair.model.AptTask;
import platform.education.school.affair.model.AptTaskItem;
import platform.education.school.affair.model.AptTaskScore;
import platform.education.school.affair.model.AptTaskUser;
import platform.education.school.affair.service.AptRuleItemService;
import platform.education.school.affair.service.AptRuleService;
import platform.education.school.affair.service.AptTaskItemService;
import platform.education.school.affair.service.AptTaskJudgeService;
import platform.education.school.affair.service.AptTaskScoreService;
import platform.education.school.affair.service.AptTaskService;
import platform.education.school.affair.service.AptTaskUserService;
import platform.education.school.affair.vo.AptTaskCondition;
import platform.education.school.affair.vo.AptTaskItemCondition;
import platform.education.school.affair.vo.AptTaskScoreCondition;
import platform.education.school.affair.vo.AptTaskUserCondition;
import platform.education.school.affair.vo.AptTaskUserVo;
import platform.education.school.affair.vo.AptTaskVo;
import platform.service.storage.model.EntityFile;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolaffair.contants.AptContans;
import platform.szxyzxx.web.schoolaffair.vo.TaskScoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;



/**
 * 考核评分
 * @author 陈业强
 *
 */
@Controller
@RequestMapping("/schoolAffair/aptTaskScore")
public class AptTaskScoreController extends BaseController{ 
	
	private final static String viewBasePath = "/schoolaffair/aptTaskScore";
	
	@Resource
	private AptTaskScoreService aptTaskScoreService;
	
	@Resource
	private AptTaskService aptTaskService;
	
	@Resource
	private AptTaskItemService aptTaskItemService;
	
	@Resource
	private AptTaskUserService aptTaskUserService;
	
	@Resource
	private AptTaskJudgeService aptTaskJudgeService;
	
	@Resource
	private TeacherService teacherService;
	
	@Resource
	private TeacherSortService teacherSortService;

	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private AptRuleService aptRuleService;
	
	@Resource
	private AptRuleItemService aptRuleItemService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		if(user.getTeacherId() != null){
			List<AptTaskVo> items = this.aptTaskService.findAptTaskByJudgeIdAndCondition(condition, user.getTeacherId(),null, page, order);
			List<AptTaskVo> taskVos = new ArrayList<AptTaskVo>();
			if(items.size() > 0){
				for(AptTaskVo vo : items){
					String scopeType = vo.getScopeType();
					Integer taskId = vo.getId();
					condition.setId(taskId);
					List<AptTaskVo> dailys = this.aptTaskService.findAptTaskByJudgeIdAndCondition(condition, user.getTeacherId(), true, null, null);
					List<AptTaskVo> bonus = this.aptTaskService.findAptTaskByJudgeIdAndCondition(condition, user.getTeacherId(), false, null, null);
					if(dailys.size() > 0){
						vo.setDailyExit(true);
					}
					if(bonus.size() > 0 ){
						vo.setBonusExit(true);
					}
					if(AptContans.SCOPE_OTHER.equals(scopeType)){
						List<Integer> teacherIds = vo.getTeacherIds();
	//					if(teacherIds.contains(user.getTeacherId())){
							List<String> teacherNames = new ArrayList<String>();
							if(teacherIds.size() > 0){
								for(Integer teacherId : teacherIds){
									Teacher teacher = teacherService.findTeacherById(teacherId);
									String name = teacher.getName();
									teacherNames.add(name);
								}
							}
							vo.setUserNames(teacherNames);
							taskVos.add(vo);
	//					}
					}else if(AptContans.SCOPE_DEPARTMENT.equals(scopeType)){
						//设置部门名称
						Integer departmentId = vo.getDepartmentId();
	//					List<DepartmentTeacher> dts = departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(user.getTeacherId(), user.getSchoolId());
	//					List<Integer> departmentIds = new ArrayList<Integer>();
	//					if(dts.size() > 0){
	//						for(DepartmentTeacher dt : dts){
	//							Integer deptId = dt.getDepartmentId();
	//							if(deptId != null){
	//								departmentIds.add(deptId);
	//							}
	//						}
	//					}
	//					if(departmentIds.contains(departmentId)){
							if(departmentId != null){
								Department dept = departmentService.findDepartmentById(departmentId);
								vo.setDepartmentName(dept.getName());
							}
							taskVos.add(vo);
	//					}
					}else{
						taskVos.add(vo);
					}
				}
			}
			model.addAttribute("items", taskVos);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}
	/**
	 * 获取考核项
	 * @param condition
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/nameJson", method = RequestMethod.POST)
	@ResponseBody
	public List<AptTaskItem> nameJson(@ModelAttribute("condition") AptTaskItemCondition condition,@CurrentUser UserInfo user){
		condition.setJudgeId(user.getTeacherId());
		condition.setCheckType(AptContans.DAILY);
		return aptTaskItemService.findAptTaskItemByCondition(condition);
	}
	
	/**
	 * 批量创建考核成绩
	 * @param taskItemId
	 * @param checkTime
	 * @param taskId
	 * @param score
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/batchCreator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation batchCreator(
			@RequestParam(value = "taskItemId", required = true)Integer taskItemId,
			@RequestParam(value = "checkTime", required = true)Date checkTime,
			@RequestParam(value = "taskId", required = true)Integer taskId,
			@RequestParam(value = "score", required = true)Float score,
			@CurrentUser UserInfo user) {
		AptTask task = aptTaskService.findAptTaskById(taskId);
		if(task != null && score != 0){
			String scopeType = task.getScopeType();
			if(AptContans.SCOPE_SCHOOL.equals(scopeType)){
				Integer schoolId = user.getSchoolId();
				if(schoolId != null){
					List<Teacher> teachers = teacherService.findTeacherListBySchoolId(user.getSchoolId());
					List<AptTaskScore> taskScores = new ArrayList<AptTaskScore>();
					if(teachers.size() > 0){
						for(Teacher teacher : teachers){
							AptTaskScore taskScore = taskScoreFiter(taskId, taskItemId, checkTime, teacher.getId());
							if(taskScore != null){
								if(taskScore.getId() == null ){
									taskScore.setScore(score);
									if(user.getTeacherId() != null){
										taskScore.setJudgeId(user.getTeacherId());
									}
									taskScores.add(taskScore);
								}else{
									taskScore.setScore(score);
									aptTaskScoreService.modify(taskScore);
								}
							}
						}
					}
					if(taskScores.size() > 0){
						aptTaskScoreService.batchAdd(taskScores);
					}
				}
			}else if(AptContans.SCOPE_DEPARTMENT.equals(scopeType)){
				List<DepartmentTeacher> teachers = departmentTeacherService.findByDepartmentId(task.getDepartmentId());
				List<AptTaskScore> taskScores = new ArrayList<AptTaskScore>();
				if(teachers.size() > 0){
					for(DepartmentTeacher teacher : teachers){
						AptTaskScore taskScore = taskScoreFiter(taskId, taskItemId, checkTime, teacher.getTeacherId());
						if(taskScore != null){
							if(taskScore.getId() == null ){
								taskScore.setScore(score);
								if(user.getTeacherId() != null){
									taskScore.setJudgeId(user.getTeacherId());
								}
								taskScores.add(taskScore);
							}else{
								taskScore.setScore(score);
								aptTaskScoreService.modify(taskScore);
							}
						}
					}
				}
				if(taskScores.size() > 0){
					aptTaskScoreService.batchAdd(taskScores);
				}
			}else{
				AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
				aptTaskUserCondition.setAptTaskId(taskId);
				List<AptTaskUser> taskUsers = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
				List<AptTaskScore> taskScores = new ArrayList<AptTaskScore>();
				if(taskUsers.size() > 0){
					for(AptTaskUser taskUser : taskUsers){
						AptTaskScore taskScore = taskScoreFiter(taskId, taskItemId, checkTime, taskUser.getTeacherId());
						if(taskScore != null){
							if(taskScore.getId() == null ){
								taskScore.setScore(score);
								if(user.getTeacherId() != null){
									taskScore.setJudgeId(user.getTeacherId());
								}
								taskScores.add(taskScore);
							}else{
								taskScore.setScore(score);
								aptTaskScoreService.modify(taskScore);
							}
						}
					}
				}
				if(taskScores.size() > 0){
					aptTaskScoreService.batchAdd(taskScores);
				}
			}
		}else{
			return new ResponseInfomation("传入参数有误", ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation("打分成功", ResponseInfomation.OPERATION_SUC);
	}
	
	/**
	 * 考核结果创建
	 * @param aptTaskScore
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(@ModelAttribute("aptTaskScore")AptTaskScore aptTaskScore, @CurrentUser UserInfo user) {
		Integer taskItemId = aptTaskScore.getAptTaskItemId();
		if(taskItemId != null){
			AptTaskItem taskItem = aptTaskItemService.findAptTaskItemById(taskItemId);
			aptTaskScore.setAptTaskItemId(taskItem.getId());
			aptTaskScore.setAptTaskId(taskItem.getAptTaskId());
			if(aptTaskScore.getCheckType() == null || "".equals(aptTaskScore.getCheckType())){
				aptTaskScore.setCheckType(taskItem.getCheckType());
			}
		}
		if(user.getTeacherId() != null){
			aptTaskScore.setJudgeId(user.getTeacherId());
			aptTaskScore = this.aptTaskScoreService.add(aptTaskScore);
		}else{
			return new ResponseInfomation("无权限操作", ResponseInfomation.OPERATION_FAIL);
		}
		return aptTaskScore != null ? new ResponseInfomation(aptTaskScore.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	/**
	 * 日常审核
	 * @param id
	 * @param edit
	 * @param sub
	 * @param taskItemId
	 * @param checkTime
	 * @param page
	 * @param order
	 * @param user
	 * @param model
	 * @return
	 * @author 陈业强
	 */
	@RequestMapping(value = "/checkEdit")
	public ModelAndView checkEdit(
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "edit", required = true) boolean edit,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "taskItemId", required = false) Integer taskItemId,
			@RequestParam(value = "checkTime", required = false) Date checkTime,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "autoSearch",defaultValue = "true")boolean autoSearch,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user,
			Model model) {
		String viewPath = null;
		List<AptTaskScore> taskScores = null;
		List<List<TaskScoreVo>> scoresMap = new ArrayList<List<TaskScoreVo>>(); 
		//获取名称
		AptTaskItemCondition aptTaskItemCondition = new AptTaskItemCondition();
		aptTaskItemCondition.setAptTaskId(id);
		List<AptTaskItem> taskItems = aptTaskItemService.findAptTaskItemByCondition(aptTaskItemCondition);
		//获取分类
		List<String> categorys = aptTaskService.findCategoryByTaskIdAndTeacherId(user.getTeacherId(),id);
		//列表数据
		AptTask aptTask = this.aptTaskService.findAptTaskById(id);
//		AptTaskScoreCondition aptTaskScoreCondition = new AptTaskScoreCondition();
		if(taskItemId != null && !"".equals(taskItemId) && checkTime != null){
			//查询考核登记信息
//			aptTaskScoreCondition.setAptTaskId(id);
//			aptTaskScoreCondition.setAptTaskItemId(taskItemId);
//			aptTaskScoreCondition.setCheckTime(checkTime);
//			aptTaskScoreCondition.setCheckType(AptContans.DAILY);
//			taskScores = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
//			if(taskScores.size() == 0 ){
				//获取被考核人信息
				String scopeType = aptTask.getScopeType();
				if(AptContans.SCOPE_SCHOOL.equals(scopeType)){
//					List<Teacher> teachers = teacherService.findActiveTeacherOfSchool(user.getSchoolId());
					List<Teacher> teachers = teacherService.findTeacherListBySchoolId(user.getSchoolId());
					List<AptTaskScore> aptTaskScores = new ArrayList<AptTaskScore>();
					if(teachers.size() > 0){
						for(Teacher teacher : teachers){
//							AptTaskScore aptTaskScore = new AptTaskScore();
//							aptTaskScore.setAptTaskId(id);
//							aptTaskScore.setAptTaskItemId(taskItemId);
//							aptTaskScore.setCheckTime(checkTime == null ? new Date() : checkTime);
//							aptTaskScore.setCheckType(AptContans.DAILY);
//							aptTaskScore.setScore(0f);
//							aptTaskScore.setJudgeId(-1);
//							aptTaskScore.setTeacherId(teacher.getId());
							aptTaskScores.add(taskScoreFiter(id, taskItemId, checkTime, teacher.getId()));
							
						}
						taskScores = aptTaskScores;
//						aptTaskScoreService.batchAdd(aptTaskScores);
					}
					
				}else if(AptContans.SCOPE_DEPARTMENT.equals(scopeType)){
					List<DepartmentTeacher> teachers = departmentTeacherService.findByDepartmentId(aptTask.getDepartmentId());
					List<AptTaskScore> aptTaskScores = new ArrayList<AptTaskScore>();
					if(teachers.size() > 0){
						for(DepartmentTeacher teacher : teachers){
//							AptTaskScore aptTaskScore = new AptTaskScore();
//							aptTaskScore.setAptTaskId(id);
//							aptTaskScore.setAptTaskItemId(taskItemId);
//							aptTaskScore.setCheckTime(checkTime == null ? new Date() : checkTime);
//							aptTaskScore.setCheckType(AptContans.DAILY);
//							aptTaskScore.setScore(0f);
//							aptTaskScore.setJudgeId(-1);
//							aptTaskScore.setTeacherId(teacher.getTeacherId());
//							aptTaskScores.add(aptTaskScore);
//							aptTaskScoreService.add(aptTaskScore);
							aptTaskScores.add(taskScoreFiter(id, taskItemId, checkTime, teacher.getTeacherId()));
						}
//						aptTaskScoreService.batchAdd(aptTaskScores);
						taskScores = aptTaskScores;
					}
				}else{
					AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
					aptTaskUserCondition.setAptTaskId(id);
					List<AptTaskUser> taskUsers = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
					List<AptTaskScore> aptTaskScores = new ArrayList<AptTaskScore>();
					if(taskUsers.size() > 0){
						for(AptTaskUser taskUser : taskUsers){
//							AptTaskScore aptTaskScore = new AptTaskScore();
//							aptTaskScore.setAptTaskId(id);
//							aptTaskScore.setAptTaskItemId(taskItemId);
//							aptTaskScore.setCheckTime(checkTime == null ? new Date() : checkTime);
//							aptTaskScore.setCheckType(AptContans.DAILY);
//							aptTaskScore.setScore(0.0f);
//							aptTaskScore.setJudgeId(-1);
//							aptTaskScore.setTeacherId(taskUser.getTeacherId());
//							aptTaskScores.add(aptTaskScore);
//							aptTaskScoreService.add(aptTaskScore);
							aptTaskScores.add(taskScoreFiter(id, taskItemId, checkTime, taskUser.getTeacherId()));
						}
//						aptTaskScoreService.batchAdd(aptTaskScores);
						taskScores = aptTaskScores;
					}
//				}
//				taskScores = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
			}
			ScoreCompartor sc = new ScoreCompartor();
			Collections.sort(taskScores, sc);
			if(taskScores.size() > 18){
				int mapSize = 1 + taskScores.size()/18;
				for (int j = 0; j < mapSize; j++) {
					int forMax = ((j + 1) < mapSize ?  (j + 1) * 18  : taskScores.size());
//					Map<String,Object> scoreMap = new HashMap<String,Object>();
					List<TaskScoreVo> pageScores = new ArrayList<TaskScoreVo>();
					for (int i = j * 18; i < forMax; i++) {
						AptTaskScore taskScore = taskScores.get(i);
						TaskScoreVo vo = new TaskScoreVo();
						BeanUtils.copyProperties(taskScore, vo);
						Teacher teacher = teacherService.findTeacherById(vo.getTeacherId());
						if(teacher != null){
							vo.setTeacherName(teacher.getName());
						}
						pageScores.add(vo);
					}
					scoresMap.add(pageScores); 
				}
			}else{
				List<TaskScoreVo> pageScores = new ArrayList<TaskScoreVo>();
				if(taskScores.size() > 0){
					for(AptTaskScore taskScore : taskScores){
						TaskScoreVo vo = new TaskScoreVo();
						BeanUtils.copyProperties(taskScore, vo);
						Teacher teacher = teacherService.findTeacherById(taskScore.getTeacherId());
						if(teacher != null){
							vo.setTeacherName(teacher.getName());
						}
						pageScores.add(vo);
					}
				}
				scoresMap.add(pageScores);
			}
			
		}
		//初始化搜索栏
		AptTaskItem taskItem = aptTaskItemService.findAptTaskItemById(taskItemId);
		
		viewPath = structurePath("/check_score");
//		if(!edit){
		model.addAttribute("edit", edit);
//		}
		model.addAttribute("aptTask", aptTask);
		model.addAttribute("taskItems", taskItems);
		model.addAttribute("taskItem", taskItem);
		model.addAttribute("checkTime", checkTime);
		model.addAttribute("category", category);
		model.addAttribute("autoSearch", autoSearch);
		model.addAttribute("categorys", categorys);
		model.addAttribute("taskScores", scoresMap);
		if ( scoresMap != null && scoresMap.size() > 0 ) {
			viewPath = structurePath("/check_list");
		}
		model.addAttribute("judgeId", user.getTeacherId());
		return new ModelAndView(viewPath, model.asMap());
	}
	
	private AptTaskScore taskScoreFiter(Integer taskId,Integer taskItemId,Date checkTime,Integer teacherId){
		AptTaskScoreCondition aptTaskScoreCondition = new AptTaskScoreCondition();
		//查询考核登记信息
		aptTaskScoreCondition.setAptTaskId(taskId);
		aptTaskScoreCondition.setAptTaskItemId(taskItemId);
		aptTaskScoreCondition.setCheckTime(checkTime == null ? new Date() : checkTime);
		aptTaskScoreCondition.setCheckType(AptContans.DAILY);
		aptTaskScoreCondition.setTeacherId(teacherId);
		List<AptTaskScore> taskScores = aptTaskScoreService.findAptTaskScoreByCondition(aptTaskScoreCondition);
		AptTaskScore aptTaskScore = new AptTaskScore();
		Float score = null;
		Integer judgeId = null;
		if(taskScores.size() > 0){
				score = taskScores.get(0).getScore();
				judgeId = taskScores.get(0).getJudgeId() == -1 ? null : taskScores.get(0).getJudgeId();
				aptTaskScore.setId(taskScores.get(0).getId());
		}
		aptTaskScore.setAptTaskId(taskId);
		aptTaskScore.setAptTaskItemId(taskItemId);
		aptTaskScore.setCheckTime(checkTime == null ? new Date() : checkTime);
		aptTaskScore.setCheckType(AptContans.DAILY);
		aptTaskScore.setScore(score == null ? 0.0f : score);
		aptTaskScore.setJudgeId(judgeId == null ? -1 : judgeId);
		aptTaskScore.setTeacherId(teacherId);
		return aptTaskScore;
	}
	/**
	 * 加减分考核跳转
	 * @param id
	 * @param edit
	 * @param sub
	 * @param taskItemId
	 * @param checkTime
	 * @param page
	 * @param order
	 * @param user
	 * @param model
	 * @return
	 * @author 陈业强
	 */
	@RequestMapping(value = "/bonusEdit")
	public ModelAndView bonusEdit(
			@RequestParam(value = "taskId", required = true) Integer taskId,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") AptTaskItemCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user,
			Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setAptTaskId(taskId);
		condition.setJudgeId(user.getTeacherId());
		List<AptTaskItem> taskItems = aptTaskItemService.findBonusByCondition(condition, page, order);
//		AptTask aptTask = this.aptTaskService.findAptTaskById(taskId);
		if ("list".equals(sub)) {
			viewPath = structurePath("/bonus_list");
		} else {
			viewPath = structurePath("/bonus_index");
		}
		model.addAttribute("taskId", taskId);
		model.addAttribute("taskItems", taskItems);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * 加减分考核列表页
	 * @param id
	 * @param edit
	 * @param sub
	 * @param taskItemId
	 * @param checkTime
	 * @param page
	 * @param order
	 * @param user
	 * @param model
	 * @return
	 * @author 陈业强
	 */
	@RequestMapping(value = "/bonusCheck")
	public ModelAndView bonusCheck(
			@RequestParam(value = "edit", required = true) boolean edit,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "taskItemId", required = true) Integer taskItemId,
			@RequestParam(value = "checkTime", required = false) Date checkTime,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user,
			Model model) {
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		AptTaskItem taskItem = aptTaskItemService.findAptTaskItemById(taskItemId);
		List<AptTaskScore> taskScores = null;
		List<TaskScoreVo> taskScoreVos = new ArrayList<TaskScoreVo>();
		String viewPath = null;
		if(taskItem != null){
			AptTaskScoreCondition condition = new AptTaskScoreCondition();
			condition.setAptTaskId(taskItem.getAptTaskId());
			condition.setAptTaskItemId(taskItemId);
			condition.setIsDelete(false);
//			aptTaskScoreService.findAptTaskScoreByCondition(condition);
			taskScores = aptTaskScoreService.findAptTaskScoreByCondition(condition,page,order);
			if(taskScores != null && taskScores.size() > 0){
				for(AptTaskScore taskScore : taskScores){
					TaskScoreVo vo = new TaskScoreVo();
					BeanUtils.copyProperties(taskScore, vo);
					EntityFile entity = this.entityFileService.findFileByUUID(taskScore.getAttachmentFileUuid());
					vo.setEntityFile(entity);
					Teacher teacher = teacherService.findTeacherById(taskScore.getTeacherId());
					if(teacher != null){
						vo.setTeacherName(teacher.getName());
					}
					taskScoreVos.add(vo);
				}
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/bonus_check_list");
		} else {
			viewPath = structurePath("/bonus_check_index");
		}
		model.addAttribute("taskScores", taskScoreVos);
		model.addAttribute("taskItemId", taskItemId);
		model.addAttribute("edit", edit);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * 加减分考核列表页
	 * @param id
	 * @param edit
	 * @param sub
	 * @param taskItemId
	 * @param checkTime
	 * @param page
	 * @param order
	 * @param user
	 * @param model
	 * @return
	 * @author 陈业强
	 */
	@RequestMapping(value = "/bonusCheck2")
	public ModelAndView bonusCheck2(
			@RequestParam(value = "edit", required = true) boolean edit,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "taskId", required = true) Integer taskId,
			@RequestParam(value = "checkTime", required = false) Date checkTime,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@CurrentUser UserInfo user,
			Model model) {
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<AptTaskScore> taskScores = null;
		List<TaskScoreVo> taskScoreVos = new ArrayList<TaskScoreVo>();
		String viewPath = null;
		boolean hasBonusPermission = false;
		boolean hasDeductPermission = false;
		AptTaskItemCondition itemCondition = new AptTaskItemCondition();
		itemCondition.setAptTaskId(taskId);
		itemCondition.setJudgeId(user.getTeacherId());
		itemCondition.setCheckType(AptContans.BONUS);
		List<AptTaskItem> bonusItems = aptTaskItemService.findBonusByCondition(itemCondition);
		if(bonusItems.size() > 0){
			hasBonusPermission = true;
		}
		itemCondition.setCheckType(AptContans.DEDUCT);
		List<AptTaskItem> deductItems = aptTaskItemService.findBonusByCondition(itemCondition);
		if(deductItems.size() > 0){
			hasDeductPermission = true;
		}
		AptTaskScoreCondition condition = new AptTaskScoreCondition();
		condition.setAptTaskId(taskId);
		condition.setIsDelete(false);
		condition.setSearchDaily(false);
		taskScores = aptTaskScoreService.findAptTaskScoreByCondition(condition,order);
		if(taskScores != null && taskScores.size() > 0){
			for(AptTaskScore taskScore : taskScores){
				TaskScoreVo vo = new TaskScoreVo();
				BeanUtils.copyProperties(taskScore, vo);
				EntityFile entity = this.entityFileService.findFileByUUID(taskScore.getAttachmentFileUuid());
				vo.setEntityFile(entity);
				Teacher teacher = teacherService.findTeacherById(taskScore.getTeacherId());
				if(teacher != null){
					vo.setTeacherName(teacher.getName());
				}
				taskScoreVos.add(vo);
			}
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/bonus_check_list");
		} else {
			viewPath = structurePath("/bonus_check_index");
		}
		model.addAttribute("taskScores", taskScoreVos);
		model.addAttribute("edit", edit);
		model.addAttribute("taskId", taskId);
		model.addAttribute("hasBonusPermission", hasBonusPermission);
		model.addAttribute("hasDeductPermission", hasDeductPermission);
		return new ModelAndView(viewPath, model.asMap());
	}
	/**
	 * 加载考核加减分项页面
	 * @param taskItemId
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/createCheckScoreByType", method = RequestMethod.GET)
	public ModelAndView createCheckScoreByType(@RequestParam(value = "taskId")Integer taskId,@RequestParam(value = "checkType")String checkType,Model model,@CurrentUser UserInfo user){
		if(taskId != null){
			AptTask task = aptTaskService.findAptTaskById(taskId);
			String scopeType = task.getScopeType();
			if(AptContans.SCOPE_SCHOOL.equals(scopeType)){
				List<Teacher> teachers = teacherService.findTeacherListBySchoolId(user.getSchoolId());
				model.addAttribute("teachers", teachers);
				
			}else if(AptContans.SCOPE_DEPARTMENT.equals(scopeType)){
				List<DepartmentTeacher> teachers = departmentTeacherService.findByDepartmentId(task.getDepartmentId());
				model.addAttribute("teachers", teachers);
			}else{
				AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
				aptTaskUserCondition.setAptTaskId(task.getId());
				List<AptTaskUser> taskUsers = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
				List<AptTaskUserVo> teachers = new ArrayList<AptTaskUserVo>();
				if(taskUsers.size() > 0){
					for(AptTaskUser taskUser : taskUsers){
						AptTaskUserVo vo = new AptTaskUserVo();
						BeanUtils.copyProperties(taskUser, vo);
						Integer teacherId = taskUser.getTeacherId();
						Teacher teacher = teacherService.findTeacherById(teacherId);
						if(teacher != null){
							vo.setTeacherName(teacher.getName());
						}
						teachers.add(vo);
					}
				}
				model.addAttribute("teachers", teachers);
			}
			model.addAttribute("task", task);
			AptTaskItemCondition condition = new AptTaskItemCondition();
			condition.setAptTaskId(taskId);;
			condition.setCheckType(checkType);
			List<AptTaskItem> taskItems = aptTaskItemService.findBonusByCondition(condition);
			if(taskItems.size() > 0){
				model.addAttribute("taskItemId", taskItems.get(0).getId());
			}
		}
		model.addAttribute("checkType", checkType);
		return new ModelAndView(structurePath("/bonus_check_input"), model.asMap());
	}
	/**
	 * 加载考核加减分项页面
	 * @param taskItemId
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/createCheckScore", method = RequestMethod.GET)
	public ModelAndView createCheckScore(@RequestParam(value = "taskItemId")Integer taskItemId,Model model,@CurrentUser UserInfo user){
		AptTaskItem taskItem = aptTaskItemService.findAptTaskItemById(taskItemId);
		if(taskItem != null){
			AptTask task = aptTaskService.findAptTaskById(taskItem.getAptTaskId());
			String scopeType = task.getScopeType();
			if(AptContans.SCOPE_SCHOOL.equals(scopeType)){
				List<Teacher> teachers = teacherService.findTeacherListBySchoolId(user.getSchoolId());
				model.addAttribute("teachers", teachers);
				
			}else if(AptContans.SCOPE_DEPARTMENT.equals(scopeType)){
				List<DepartmentTeacher> teachers = departmentTeacherService.findByDepartmentId(task.getDepartmentId());
				model.addAttribute("teachers", teachers);
			}else{
				AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
				aptTaskUserCondition.setAptTaskId(task.getId());
				List<AptTaskUser> taskUsers = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
				List<AptTaskUserVo> teachers = new ArrayList<AptTaskUserVo>();
				if(taskUsers.size() > 0){
					for(AptTaskUser taskUser : taskUsers){
						AptTaskUserVo vo = new AptTaskUserVo();
						BeanUtils.copyProperties(taskUser, vo);
						Integer teacherId = taskUser.getTeacherId();
						Teacher teacher = teacherService.findTeacherById(teacherId);
						if(teacher != null){
							vo.setTeacherName(teacher.getName());
						}
						teachers.add(vo);
					}
				}
				model.addAttribute("teachers", teachers);
			}
			model.addAttribute("task", task);
		}
		model.addAttribute("taskItemId", taskItemId);
		return new ModelAndView(structurePath("/bonus_check_input"), model.asMap());
	}
	/**
	 * 编辑考核加减分项页面
	 * @param id
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/editCheckScore", method = RequestMethod.GET)
	public ModelAndView editCheckScore(@RequestParam(value = "id")Integer id,Model model,@CurrentUser UserInfo user){
		AptTaskScore taskScore = aptTaskScoreService.findAptTaskScoreById(id);
		if(taskScore != null){
			AptTask task = aptTaskService.findAptTaskById(taskScore.getAptTaskId());
			String scopeType = task.getScopeType();
			if(AptContans.SCOPE_SCHOOL.equals(scopeType)){
				List<Teacher> teachers = teacherService.findTeacherListBySchoolId(user.getSchoolId());
				model.addAttribute("teachers", teachers);
				
			}else if(AptContans.SCOPE_DEPARTMENT.equals(scopeType)){
				List<DepartmentTeacher> teachers = departmentTeacherService.findByDepartmentId(task.getDepartmentId());
				model.addAttribute("teachers", teachers);
			}else{
				AptTaskUserCondition aptTaskUserCondition = new AptTaskUserCondition();
				aptTaskUserCondition.setAptTaskId(task.getId());
				List<AptTaskUser> taskUsers = aptTaskUserService.findAptTaskUserByCondition(aptTaskUserCondition);
				List<AptTaskUserVo> teachers = new ArrayList<AptTaskUserVo>();
				if(taskUsers.size() > 0){
					for(AptTaskUser taskUser : taskUsers){
						AptTaskUserVo vo = new AptTaskUserVo();
						BeanUtils.copyProperties(taskUser, vo);
						Integer teacherId = taskUser.getTeacherId();
						Teacher teacher = teacherService.findTeacherById(teacherId);
						if(teacher != null){
							vo.setTeacherName(teacher.getName());
						}
						teachers.add(vo);
					}
				}
				model.addAttribute("teachers", teachers);
			}
			Teacher teacher = teacherService.findTeacherById(taskScore.getTeacherId());
			EntityFile entity = this.entityFileService.findFileByUUID(taskScore.getAttachmentFileUuid());
			model.addAttribute("entity", entity);
			if(teacher != null){
				model.addAttribute("teacherName",teacher.getName());
			}
			model.addAttribute("task", task);
		}
		model.addAttribute("taskScore", taskScore);
		return new ModelAndView(structurePath("/bonus_check_input"), model.asMap());
	}
	
//	@RequestMapping(value = "/editor", method = RequestMethod.GET)
//	public ModelAndView editor(
//			@RequestParam(value = "id", required = true) Integer id, Model model) {
//		AptTaskScore aptTaskScore = this.aptTaskScoreService.findAptTaskScoreById(id);
//		model.addAttribute("aptTaskScore", aptTaskScore);
//		return new ModelAndView(structurePath("/input"), model.asMap());
//	}
	
//	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
//	public ModelAndView viewer(
//			@RequestParam(value = "id", required = true) Integer id,
//			Model model) {
//		AptTaskScore aptTaskScore = this.aptTaskScoreService.findAptTaskScoreById(id);
//		model.addAttribute("isCK", "disable");
//		model.addAttribute("aptTaskScore", aptTaskScore);
//		return new ModelAndView(structurePath("/input"), model.asMap());
//	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,@ModelAttribute("aptTaskScore")AptTaskScore aptTaskScore) {
		if (aptTaskScore != null) {
			aptTaskScore.setId(id);
		}
		try {
			aptTaskScore.setIsDelete(true);
			this.aptTaskScoreService.modify(aptTaskScore);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, AptTaskScore aptTaskScore) {
		aptTaskScore.setId(id);
		aptTaskScore = this.aptTaskScoreService.modify(aptTaskScore);
		return aptTaskScore != null ? new ResponseInfomation(aptTaskScore.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, AptTaskCondition condition) {
		if(condition != null){
			condition.setSchoolId(user.getSchoolId());
		}
	}
	/**
	 * 自定义排序
	 * @author 陈业强
	 *
	 */
	class ScoreCompartor implements Comparator<AptTaskScore> {

		@Override
		public int compare(AptTaskScore score1, AptTaskScore score2) {
			Integer teacherId1 = score1.getTeacherId();
			Integer teacherId2 = score2.getTeacherId();
			Teacher teacher1 =teacherService.findTeacherById(teacherId1);
			Teacher teacher2 =teacherService.findTeacherById(teacherId2);
			TeacherSortCondition condition = new TeacherSortCondition();
			condition.setTeacherId(teacher1.getId());
			condition.setSchoolId(teacher1.getSchoolId());
			List<TeacherSort> ts1 = teacherSortService.findTeacherSortByCondition(condition);
			condition.setTeacherId(teacher2.getId());
			condition.setSchoolId(teacher2.getSchoolId());
			List<TeacherSort> ts2 =teacherSortService.findTeacherSortByCondition(condition);
			Integer sort1 = 0;
			Integer sort2 = 0;
			if(ts1.size() > 0){
				sort1 = ts1.get(0).getSort();
			}
			if(ts2.size() > 0){
				sort2 = ts2.get(0).getSort();
			}
			if ( sort1 == null || sort2 == null ) {
				return 0;
			}
			return sort1 - sort2;
		}
		
	}
}
