package platform.szxyzxx.web.personnel.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.BeanUtils;
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

import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.personnel.model.PerformanceEvaluationPersonnel;
import platform.education.personnel.model.PerformanceEvaluationScore;
import platform.education.personnel.model.PerformanceEvaluationTask;
import platform.education.personnel.model.PerformanceEvaluationTaskItem;
import platform.education.personnel.service.PerformanceEvaluationScoreService;
import platform.education.personnel.vo.PerformanceEvaluationPersonnelCondition;
import platform.education.personnel.vo.PerformanceEvaluationPersonnelVo;
import platform.education.personnel.vo.PerformanceEvaluationScoreCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskItemCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskItemVo;
import platform.education.personnel.vo.PerformanceEvaluationTaskVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/personnel/performanceEvaluationScore")
public class PerformanceEvaluationScoreController extends BaseController {

	private final static String viewBasePath = "/personnel/performanceEvaluationScore";

	@Autowired
	@Qualifier("performanceEvaluationScoreService")
	private PerformanceEvaluationScoreService performanceEvaluationScoreService;

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PerformanceEvaluationTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		// conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		if (user.getTeacherId() != null) {
			PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
			personnelCondition.setPersonnelId(user.getTeacherId());
			personnelCondition.setType("0");
			personnelCondition.setSchoolId(user.getSchoolId());
			personnelCondition.setIsDelete(false);
			List<PerformanceEvaluationPersonnel> personnels = this.performanceEvaluationPersonnelService
					.findPerformanceEvaluationPersonnelByCondition(personnelCondition);
			List<PerformanceEvaluationTaskVo> voList = new ArrayList<PerformanceEvaluationTaskVo>();
			if (personnels.size() > 0) {
				condition.setSchoolId(user.getSchoolId());
				condition.setIsIssue(true);
				condition.setPersonnelId(user.getTeacherId());
				condition.setType("0");
				condition.setIsDelete(false);
				List<PerformanceEvaluationTask> tasks = this.performanceEvaluationTaskService
						.findPerformanceEvaluationTaskByConditionMore(
								condition, page, order);

				for (PerformanceEvaluationTask task : tasks) {

					// 查询是否已对所有被考核人评分
					PerformanceEvaluationScoreCondition scoreCondition = new PerformanceEvaluationScoreCondition();
					scoreCondition.setTaskId(task.getId());
					scoreCondition.setEvaluate(user.getTeacherId());
					List<PerformanceEvaluationScore> scoreEvaluateList = this.performanceEvaluationScoreService
							.findPerformanceEvaluationScoreByCondition(scoreCondition);

					PerformanceEvaluationPersonnelCondition perCondition = new PerformanceEvaluationPersonnelCondition();
					perCondition.setTaskId(task.getId());
					perCondition.setType("1");
					List<PerformanceEvaluationPersonnel> personnelEvaluateList = this.performanceEvaluationPersonnelService
							.findPerformanceEvaluationPersonnelByCondition(perCondition);

					PerformanceEvaluationTaskItemCondition taskItemCondition = new PerformanceEvaluationTaskItemCondition();
					taskItemCondition.setTaskId(task.getId());
					List<PerformanceEvaluationTaskItem> taskItemList = this.performanceEvaluationTaskItemService
							.findPerformanceEvaluationTaskItemByCondition(taskItemCondition);

					Integer type;
					if (scoreEvaluateList.size() == (personnelEvaluateList
							.size() * taskItemList.size())) {
						type = 0;
					} else {
						type = 1;
					}

					PerformanceEvaluationTaskVo performanceEvaluationTaskvo = new PerformanceEvaluationTaskVo();
					BeanUtils.copyProperties(task, performanceEvaluationTaskvo);
					PerformanceEvaluationPersonnelCondition performanceEvaluationPersonnelCondition = new PerformanceEvaluationPersonnelCondition();
					performanceEvaluationPersonnelCondition.setTaskId(task
							.getId());
					List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
							.findPerformanceEvaluationPersonnelByCondition(performanceEvaluationPersonnelCondition);
					List<PerformanceEvaluationPersonnel> evaluateList = new ArrayList<PerformanceEvaluationPersonnel>();
					List<PerformanceEvaluationPersonnel> evaluatedList = new ArrayList<PerformanceEvaluationPersonnel>();

					for (PerformanceEvaluationPersonnel performanceEvaluationPersonnel : personnelList) {
						if (("0").equals(performanceEvaluationPersonnel
								.getType())) {
							evaluateList.add(performanceEvaluationPersonnel);
						} else if (("1").equals(performanceEvaluationPersonnel
								.getType())) {
							evaluatedList.add(performanceEvaluationPersonnel);
						}
					}
					performanceEvaluationTaskvo.setEvaluateList(evaluateList);
					performanceEvaluationTaskvo.setEvaluatedList(evaluatedList);
					performanceEvaluationTaskvo.setType(type);
					voList.add(performanceEvaluationTaskvo);
				}
			}
			model.addAttribute("voList", voList);
		}
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<PerformanceEvaluationScore> jsonList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") PerformanceEvaluationScoreCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.performanceEvaluationScoreService
				.findPerformanceEvaluationScoreByCondition(condition, page,
						order);
	}

	@RequestMapping(value = "checkName", method = RequestMethod.GET)
	public ModelAndView checkName(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "type", required = true) String type,
			Model model) {

		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setTaskId(id);
		personnelCondition.setType(type);
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);
		List<PerformanceEvaluationPersonnelVo> voList = new ArrayList<PerformanceEvaluationPersonnelVo>();
		for (PerformanceEvaluationPersonnel personnel : personnelList) {
			PerformanceEvaluationPersonnelVo personnelVo = new PerformanceEvaluationPersonnelVo();
			BeanUtils.copyProperties(personnel, personnelVo);
			String department = "";
			DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
			condition.setTeacherId(personnel.getPersonnelId());
			condition.setSchoolId(user.getSchoolId());
			condition.setIsDeleted(false);
			List<DepartmentTeacher> departmentTeacherList = this.departmentTeacherService
					.findDepartmentTeacherByCondition(condition, null, null);
			if (departmentTeacherList.size() > 0) {
				for (DepartmentTeacher departmentTeacher : departmentTeacherList) {
					if ("".equals(department)) {
						department = departmentTeacher.getDepartmentName();
					} else {
						department = department + ";"
								+ departmentTeacher.getDepartmentName();
					}
				}
			} else {
				department = "无";
			}
			personnelVo.setDepartment(department);
			voList.add(personnelVo);
		}

		model.addAttribute("voList", voList);
		return new ModelAndView(structurePath("/check"), model.asMap());
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			PerformanceEvaluationScore performanceEvaluationScore,
			@CurrentUser UserInfo user) {
		performanceEvaluationScore = this.performanceEvaluationScoreService
				.add(performanceEvaluationScore);
		return performanceEvaluationScore != null ? new ResponseInfomation(
				performanceEvaluationScore.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PerformanceEvaluationScore performanceEvaluationScore = this.performanceEvaluationScoreService
				.findPerformanceEvaluationScoreById(id);
		model.addAttribute("performanceEvaluationScore",
				performanceEvaluationScore);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/evaluation", method = RequestMethod.GET)
	public ModelAndView evaluation(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {

		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setSchoolId(user.getSchoolId());
		personnelCondition.setTaskId(id);
		personnelCondition.setType("1");
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);

		// 考核任务下的考核项目
		PerformanceEvaluationTaskItemCondition taskItemCondition = new PerformanceEvaluationTaskItemCondition();
		taskItemCondition.setSchoolId(user.getSchoolId());
		taskItemCondition.setTaskId(id);
		taskItemCondition.setIsDelete(false);
		List<PerformanceEvaluationTaskItemVo> taskItemList = this.performanceEvaluationTaskItemService
				.findPerformanceEvaluationTaskItemByConditionMore(taskItemCondition);

		model.addAttribute("personnelList", personnelList);
		model.addAttribute("taskItemList", taskItemList);
		return new ModelAndView(structurePath("/evaluation"), model.asMap());
	}

	@RequestMapping(value = "/checkScore", method = RequestMethod.POST)
	@ResponseBody
	public List<PerformanceEvaluationScore> checkScore(
			@CurrentUser UserInfo user,
			@RequestParam(value = "taskId", required = true) Integer taskId,
			@RequestParam(value = "evaluatedId", required = true) Integer evaluatedId) {
		PerformanceEvaluationScoreCondition condition = new PerformanceEvaluationScoreCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setTaskId(taskId);
		condition.setEvaluate(user.getTeacherId());
		condition.setEvaluated(evaluatedId);
		condition.setIsDelete(false);
		List<PerformanceEvaluationScore> scoreList = this.performanceEvaluationScoreService
				.findPerformanceEvaluationScoreByCondition(condition);
		return scoreList;
	}

	@RequestMapping(value = "/saveScore", method = RequestMethod.POST)
	@ResponseBody
	public void saveScore(@CurrentUser UserInfo user,
			@RequestParam(value = "taskItems") String taskItems) {
		JSONArray itemJson = JSONArray.fromObject(taskItems);
		for (int i = 0; i < itemJson.size(); i++) {
			JSONObject jo = itemJson.getJSONObject(i);
			PerformanceEvaluationScore score = new PerformanceEvaluationScore();
			Integer scoreId = jo.getInt("scoreId");
			String scoreTemp = jo.getString("score");
			if (0 == scoreId) {
				if (!"".equals(scoreTemp)) {
					score.setSchoolId(user.getSchoolId());
					score.setEvaluate(user.getTeacherId());
					score.setEvaluated(jo.getInt("evaluated"));
					score.setTaskId(jo.getInt("taskId"));
					score.setTaskItemId(jo.getInt("taskItemId"));
					score.setScore(scoreTemp);
					score.setRemark(jo.getString("remark"));
					score = this.performanceEvaluationScoreService.add(score);
				}
			} else {
				if ("".equals(scoreTemp)) {
					score.setScore("0");
				}
				score.setId(scoreId);
				score.setScore(jo.getString("score"));
				score.setRemark(jo.getString("remark"));
				score = this.performanceEvaluationScoreService.modify(score);
			}

		}
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setSchoolId(user.getSchoolId());
		personnelCondition.setTaskId(id);
		personnelCondition.setType("1");
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);

		// 考核任务下的考核项目
		PerformanceEvaluationTaskItemCondition taskItemCondition = new PerformanceEvaluationTaskItemCondition();
		taskItemCondition.setSchoolId(user.getSchoolId());
		taskItemCondition.setTaskId(id);
		taskItemCondition.setIsDelete(false);
		List<PerformanceEvaluationTaskItemVo> taskItemList = this.performanceEvaluationTaskItemService
				.findPerformanceEvaluationTaskItemByConditionMore(taskItemCondition);

		model.addAttribute("personnelList", personnelList);
		model.addAttribute("taskItemList", taskItemList);
		return new ModelAndView(structurePath("/viewEvaluation"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			PerformanceEvaluationScore performanceEvaluationScore) {
		if (performanceEvaluationScore != null) {
			performanceEvaluationScore.setId(id);
		}
		try {
			this.performanceEvaluationScoreService
					.remove(performanceEvaluationScore);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			PerformanceEvaluationScore performanceEvaluationScore) {
		performanceEvaluationScore.setId(id);
		performanceEvaluationScore = this.performanceEvaluationScoreService
				.modify(performanceEvaluationScore);
		return performanceEvaluationScore != null ? new ResponseInfomation(
				performanceEvaluationScore.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user,
			PerformanceEvaluationScoreCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
