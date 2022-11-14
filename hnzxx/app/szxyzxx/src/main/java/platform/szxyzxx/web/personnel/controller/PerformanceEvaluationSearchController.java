package platform.szxyzxx.web.personnel.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.personnel.model.PerformanceEvaluationPersonnel;
import platform.education.personnel.model.PerformanceEvaluationScore;
import platform.education.personnel.model.PerformanceEvaluationTask;
import platform.education.personnel.model.PerformanceEvaluationTaskItem;
import platform.education.personnel.vo.PerformanceEvaluationPersonnelCondition;
import platform.education.personnel.vo.PerformanceEvaluationPersonnelVo;
import platform.education.personnel.vo.PerformanceEvaluationScoreCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskItemCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskItemSort;
import platform.education.personnel.vo.PerformanceEvaluationTaskItemVo;
import platform.education.personnel.vo.PerformanceEvaluationTaskVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import com.ibm.icu.math.BigDecimal;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

@Controller
@RequestMapping("/personnel/performanceEvaluationSearch")
public class PerformanceEvaluationSearchController extends BaseController {

	private final static String viewBasePath = "/personnel/performanceEvaluationSearch";

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") PerformanceEvaluationTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty()
				: "create_date");
		// 查询出已经发布的考核任务
		condition.setIsIssue(true);
		List<PerformanceEvaluationTask> tasks = this.performanceEvaluationTaskService
				.findPerformanceEvaluationTaskByCondition(condition, page,
						order);

		List<PerformanceEvaluationTaskVo> voList = new ArrayList<PerformanceEvaluationTaskVo>();
		for (PerformanceEvaluationTask task : tasks) {
			PerformanceEvaluationTaskVo performanceEvaluationTaskvo = new PerformanceEvaluationTaskVo();
			BeanUtils.copyProperties(task, performanceEvaluationTaskvo);

			/**
			 * 查询是否已经完成全部考核
			 */

			// 查询考核分数表
			PerformanceEvaluationScoreCondition scoreCondition = new PerformanceEvaluationScoreCondition();
			scoreCondition.setTaskId(task.getId());
			scoreCondition.setIsDelete(false);
			List<PerformanceEvaluationScore> scoreList = this.performanceEvaluationScoreService
					.findPerformanceEvaluationScoreByCondition(scoreCondition);

			// 查询考核人与被考核人
			PerformanceEvaluationPersonnelCondition performanceEvaluationPersonnelCondition = new PerformanceEvaluationPersonnelCondition();
			performanceEvaluationPersonnelCondition.setTaskId(task.getId());
			performanceEvaluationPersonnelCondition.setIsDelete(false);
			List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
					.findPerformanceEvaluationPersonnelByCondition(performanceEvaluationPersonnelCondition);
			List<PerformanceEvaluationPersonnel> evaluates = new ArrayList<PerformanceEvaluationPersonnel>();
			List<PerformanceEvaluationPersonnel> evaluateds = new ArrayList<PerformanceEvaluationPersonnel>();
			if (personnelList.size() > 0) {
				for (PerformanceEvaluationPersonnel personnel : personnelList) {
					if ("0".equals(personnel.getType())) {
						evaluates.add(personnel);
					} else if ("1".equals(personnel.getType())) {
						evaluateds.add(personnel);
					}
				}
			}

			// 查询考核项目
			PerformanceEvaluationTaskItemCondition taskItemContion = new PerformanceEvaluationTaskItemCondition();
			taskItemContion.setTaskId(task.getId());
			taskItemContion.setIsDelete(false);
			List<PerformanceEvaluationTaskItem> taskItemList = this.performanceEvaluationTaskItemService
					.findPerformanceEvaluationTaskItemByCondition(taskItemContion);

			Integer type;
			if (scoreList.size() == (evaluates.size() * evaluateds.size() * taskItemList
					.size())) {
				type = 0;
			} else {
				type = 1;
			}

			// 关联查询出属于考核任务下的考核人（type="0"）与被考核人(type="1")
			List<PerformanceEvaluationPersonnel> evaluateList = new ArrayList<PerformanceEvaluationPersonnel>();
			List<PerformanceEvaluationPersonnel> evaluatedList = new ArrayList<PerformanceEvaluationPersonnel>();

			for (PerformanceEvaluationPersonnel performanceEvaluationPersonnel : personnelList) {
				if (("0").equals(performanceEvaluationPersonnel.getType())) {
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

		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}

		model.addAttribute("voList", voList);
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "checkName", method = RequestMethod.GET)
	public ModelAndView checkName(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "type", required = true) String type,
			Model model) {

		// 关联查询出考核人/被考核人所属的部门名称
		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setTaskId(id);
		personnelCondition.setType(type);
		personnelCondition.setIsDelete(false);
		;
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

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		// 查询出考核任务下的考核项目
		PerformanceEvaluationTaskItemCondition taskItemCondition = new PerformanceEvaluationTaskItemCondition();
		taskItemCondition.setTaskId(id);
		taskItemCondition.setSchoolId(user.getSchoolId());
		taskItemCondition.setIsDelete(false);
		List<PerformanceEvaluationTaskItemVo> taskItemList = this.performanceEvaluationTaskItemService
				.findPerformanceEvaluationTaskItemByConditionMore(taskItemCondition);
		// 查询出考核任务下的所有被考核人
		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setTaskId(id);
		personnelCondition.setType("1");
		personnelCondition.setSchoolId(user.getSchoolId());
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);
		// 查询出考核任务下的被考核人所属的部门
		List<PerformanceEvaluationPersonnelVo> personnelVoList = new ArrayList<PerformanceEvaluationPersonnelVo>();
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
			personnelVoList.add(personnelVo);
		}
		// 查询出考核任务下的考核人对被考核人针对考核项目的考核评分
		List<PerformanceEvaluationPersonnelVo> listResult = new ArrayList<PerformanceEvaluationPersonnelVo>();
		for (PerformanceEvaluationPersonnelVo vo : personnelVoList) {
			PerformanceEvaluationPersonnelVo resultPerson = new PerformanceEvaluationPersonnelVo();
			Map<String, List<PerformanceEvaluationTaskItemSort>> map = new HashMap<String, List<PerformanceEvaluationTaskItemSort>>();
			// 查询出考核任务下当前被考核人针对考核项目的所有考核评分
			PerformanceEvaluationScoreCondition performanceEvaluationScoreCondition = new PerformanceEvaluationScoreCondition();
			performanceEvaluationScoreCondition.setTaskId(id);
			performanceEvaluationScoreCondition.setEvaluated(vo.getPersonnelId());
			performanceEvaluationScoreCondition.setSchoolId(user.getSchoolId());
			performanceEvaluationScoreCondition.setIsDelete(false);
			List<PerformanceEvaluationScore> scoreList = this.performanceEvaluationScoreService
					.findPerformanceEvaluationScoreByCondition(performanceEvaluationScoreCondition);
			for (PerformanceEvaluationScore performanceEvaluationScore : scoreList) {
				String key = performanceEvaluationScore.getEvaluated() + ""
						+ performanceEvaluationScore.getTaskItemId() + "";
				List<PerformanceEvaluationTaskItemSort> list = map.get(key);
				if (list == null) {
					list = new ArrayList<PerformanceEvaluationTaskItemSort>();
				}
				PerformanceEvaluationTaskItemSort sortTemp = new PerformanceEvaluationTaskItemSort();
				sortTemp.setId(performanceEvaluationScore.getTaskItemId());
				sortTemp.setScore(performanceEvaluationScore.getScore());
				list.add(sortTemp);
				map.put(key, list);

			}

			Set<String> set = map.keySet();
			Map<String, PerformanceEvaluationTaskItemSort> mapTotal = new HashMap<String, PerformanceEvaluationTaskItemSort>();
			for (String key : set) {
				List<PerformanceEvaluationTaskItemSort> list = map.get(key);
				BigDecimal total = new BigDecimal("0");
				int itemId = 0;
				for (PerformanceEvaluationTaskItemSort performanceEvaluationTaskItemSort : list) {
					itemId = performanceEvaluationTaskItemSort.getId();
					total = total.add(new BigDecimal(
							performanceEvaluationTaskItemSort.getScore()));
				}
				PerformanceEvaluationTaskItemSort totalScore = new PerformanceEvaluationTaskItemSort();
				totalScore.setScore(total.toString());
				totalScore.setId(itemId);
				mapTotal.put(key, totalScore);
			}

			resultPerson.setDepartment(vo.getDepartment());
			resultPerson.setPersonnelId(vo.getPersonnelId());
			List<PerformanceEvaluationTaskItemSort> taskItemListSort = new ArrayList<PerformanceEvaluationTaskItemSort>();
			Set<String> setTemp = mapTotal.keySet();
			for (String key : setTemp) {
				PerformanceEvaluationTaskItemSort totalScore = mapTotal
						.get(key);
				if (totalScore != null) {
					PerformanceEvaluationTaskItemSort sort2 = new PerformanceEvaluationTaskItemSort();
					sort2.setId(totalScore.getId());
					sort2.setScore(totalScore.getScore());
					taskItemListSort.add(sort2);
				}
			}
			BigDecimal totalScore = new BigDecimal("0");
			for (PerformanceEvaluationTaskItemSort sort : taskItemListSort) {
				totalScore = totalScore.add(new BigDecimal(sort.getScore()));
			}
			PerformanceEvaluationTaskItemSort totalScore2 = new PerformanceEvaluationTaskItemSort();
			totalScore2.setId(Integer.MAX_VALUE);
			totalScore2.setScore(totalScore.toString());
			taskItemListSort.add(totalScore2);

			Collections.sort(taskItemListSort);
			resultPerson.setTaskItemListSort(taskItemListSort);
			listResult.add(resultPerson);
		}

		List<PerformanceEvaluationTaskItemSort> taskItemListSort = new ArrayList<PerformanceEvaluationTaskItemSort>();
		for (PerformanceEvaluationTaskItemVo temp : taskItemList) {
			PerformanceEvaluationTaskItemSort sort = new PerformanceEvaluationTaskItemSort();
			sort.setId(temp.getId());
			sort.setItemName(temp.getItemName());
			taskItemListSort.add(sort);
		}
		PerformanceEvaluationTaskItemSort sortBig = new PerformanceEvaluationTaskItemSort();
		sortBig.setId(Integer.MAX_VALUE);
		sortBig.setItemName("总分数");
		taskItemListSort.add(sortBig);
		Collections.sort(taskItemListSort);
		
		
		listResult = this.addBackSpace(listResult,taskItemListSort);
		
		
		model.addAttribute("taskItemList", taskItemListSort);
		model.addAttribute("personnelList", listResult);
		model.addAttribute("taskId", id);
		return new ModelAndView(structurePath("/search"), model.asMap());
	}
	
	
	private List<PerformanceEvaluationPersonnelVo> addBackSpace(List<PerformanceEvaluationPersonnelVo> listResult,
			List<PerformanceEvaluationTaskItemSort> taskItemListSort){
		
		for (PerformanceEvaluationPersonnelVo performanceEvaluationPersonnelVo : listResult) {
			List<PerformanceEvaluationTaskItemSort> scoreList =  performanceEvaluationPersonnelVo.getTaskItemListSort();
			Map<Integer, PerformanceEvaluationTaskItemSort> map = new HashMap<Integer, PerformanceEvaluationTaskItemSort>();
			for (PerformanceEvaluationTaskItemSort taskTitle : taskItemListSort) {
				taskTitle.setScore(null);
				map.put(taskTitle.getId(), taskTitle);
			}
			
			for (PerformanceEvaluationTaskItemSort score : scoreList) {
				map.put(score.getId(), score);
			}
			
			List<PerformanceEvaluationTaskItemSort> midScoreList =  new ArrayList<PerformanceEvaluationTaskItemSort>();
			Set<Integer> set = map.keySet();
			for (Integer id : set) {
				midScoreList.add(map.get(id));
			}
			Collections.sort(midScoreList);
			performanceEvaluationPersonnelVo.setTaskItemListSort(midScoreList);
		}
		
		return listResult;
		
	}
	

	@RequestMapping(value = "/downLoadTask", method = RequestMethod.GET)
	@ResponseBody
	public void downLoadTask(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user,
			@RequestParam(value = "taskId", required = true) Integer id,
			Model model) {

		ParseConfig config = SzxyExcelTookit.getConfig("downLoadTask");
		
		// 查询出考核任务下的考核项目
		PerformanceEvaluationTaskItemCondition taskItemCondition = new PerformanceEvaluationTaskItemCondition();
		taskItemCondition.setTaskId(id);
		taskItemCondition.setSchoolId(user.getSchoolId());
		taskItemCondition.setIsDelete(false);
		List<PerformanceEvaluationTaskItemVo> taskItemList = this.performanceEvaluationTaskItemService
				.findPerformanceEvaluationTaskItemByConditionMore(taskItemCondition);
		// 查询出考核任务下的所有
		// 查询出考核任务下的所有被考核人与考核人
		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setTaskId(id);
		personnelCondition.setSchoolId(user.getSchoolId());
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);
		// 考核人相关部门信息集合
		List<PerformanceEvaluationPersonnelVo> evaluateVoList = new ArrayList<PerformanceEvaluationPersonnelVo>();
		// 被考核人相关部门信息集合
		List<PerformanceEvaluationPersonnelVo> evaluatedVoList = new ArrayList<PerformanceEvaluationPersonnelVo>();
		for (PerformanceEvaluationPersonnel personnel : personnelList) {
			PerformanceEvaluationPersonnelVo personnelVo = new PerformanceEvaluationPersonnelVo();
			BeanUtils.copyProperties(personnel, personnelVo);
			String evaluatedName = "";
			Teacher teacher = this.teacherService.findTeacherById(personnel.getPersonnelId());
			if(teacher != null) {
				evaluatedName = teacher.getName();
			}
			personnelVo.setEvaluatedName(evaluatedName);
			
			String department = "";
			DepartmentTeacherCondition deptcondition = new DepartmentTeacherCondition();
			deptcondition.setTeacherId(personnel.getPersonnelId());
			deptcondition.setSchoolId(user.getSchoolId());
			deptcondition.setIsDeleted(false);
			List<DepartmentTeacher> departmentTeacherList = this.departmentTeacherService
					.findDepartmentTeacherByCondition(deptcondition, null, null);
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

			if ("0".equals(personnelVo.getType())) {
				evaluateVoList.add(personnelVo);
			} else if ("1".equals(personnelVo.getType())) {
				evaluatedVoList.add(personnelVo);
			}
		}

		// 查询出考核任务下的考核人对被考核人针对考核项目的考核评分
		List<PerformanceEvaluationPersonnelVo> listResult = new ArrayList<PerformanceEvaluationPersonnelVo>();
		for (PerformanceEvaluationPersonnelVo vo : evaluatedVoList) {

			// 获得考核任务名称
			String taskName = "";
			PerformanceEvaluationTask perTask = this.performanceEvaluationTaskService
					.findPerformanceEvaluationTaskById(vo.getTaskId());
			if (perTask != null) {
				taskName = perTask.getName();
			}

			String evaluateName = "";
			for (PerformanceEvaluationPersonnelVo evaluateVo : evaluateVoList) {
				Teacher teacher = this.teacherService
						.findTeacherById(evaluateVo.getPersonnelId());
				if (teacher != null) {
					if ("".equals(evaluateName)) {
						evaluateName = teacher.getName();
					} else {
						evaluateName = evaluateName + ";" + teacher.getName();
					}
				}
			}

			PerformanceEvaluationPersonnelVo resultPerson = new PerformanceEvaluationPersonnelVo();
			Map<String, List<PerformanceEvaluationTaskItemSort>> map = new HashMap<String, List<PerformanceEvaluationTaskItemSort>>();
			// 查询出考核任务下当前被考核人针对考核项目的所有考核评分
			PerformanceEvaluationScoreCondition performanceEvaluationScoreCondition = new PerformanceEvaluationScoreCondition();
			performanceEvaluationScoreCondition.setTaskId(id);
			performanceEvaluationScoreCondition.setEvaluated(vo
					.getPersonnelId());
			performanceEvaluationScoreCondition.setSchoolId(user.getSchoolId());
			performanceEvaluationScoreCondition.setIsDelete(false);
			List<PerformanceEvaluationScore> scoreList = this.performanceEvaluationScoreService
					.findPerformanceEvaluationScoreByCondition(performanceEvaluationScoreCondition);
			for (PerformanceEvaluationScore performanceEvaluationScore : scoreList) {
				String key = performanceEvaluationScore.getEvaluated() + ""
						+ performanceEvaluationScore.getTaskItemId() + "";
				List<PerformanceEvaluationTaskItemSort> list = map.get(key);
				if (list == null) {
					list = new ArrayList<PerformanceEvaluationTaskItemSort>();
				}
				PerformanceEvaluationTaskItemSort sortTemp = new PerformanceEvaluationTaskItemSort();
				sortTemp.setId(performanceEvaluationScore.getTaskItemId());
				sortTemp.setScore(performanceEvaluationScore.getScore());
				list.add(sortTemp);
				map.put(key, list);

			}

			Set<String> set = map.keySet();
			Map<String, PerformanceEvaluationTaskItemSort> mapTotal = new HashMap<String, PerformanceEvaluationTaskItemSort>();
			for (String key : set) {
				List<PerformanceEvaluationTaskItemSort> list = map.get(key);
				BigDecimal total = new BigDecimal("0");
				int itemId = 0;
				for (PerformanceEvaluationTaskItemSort performanceEvaluationTaskItemSort : list) {
					itemId = performanceEvaluationTaskItemSort.getId();
					total = total.add(new BigDecimal(
							performanceEvaluationTaskItemSort.getScore()));
				}
				PerformanceEvaluationTaskItemSort totalScore = new PerformanceEvaluationTaskItemSort();
				totalScore.setScore(total.toString());
				totalScore.setId(itemId);
				mapTotal.put(key, totalScore);
			}

			resultPerson.setDepartment(vo.getDepartment());
			resultPerson.setTaskName(taskName);
			resultPerson.setEvaluateName(evaluateName);
			resultPerson.setPersonnelId(vo.getPersonnelId());
			resultPerson.setEvaluatedName(vo.getEvaluatedName());
			List<PerformanceEvaluationTaskItemSort> taskItemListSort = new ArrayList<PerformanceEvaluationTaskItemSort>();
			Set<String> setTemp = mapTotal.keySet();
			for (String key : setTemp) {
				PerformanceEvaluationTaskItemSort totalScore = mapTotal
						.get(key);
				if (totalScore != null) {
					PerformanceEvaluationTaskItemSort sort2 = new PerformanceEvaluationTaskItemSort();
					sort2.setId(totalScore.getId());
					sort2.setScore(totalScore.getScore());
					taskItemListSort.add(sort2);
				}
			}
			BigDecimal totalScore = new BigDecimal("0");
			for (PerformanceEvaluationTaskItemSort sort : taskItemListSort) {
				totalScore = totalScore.add(new BigDecimal(sort.getScore()));
			}
			PerformanceEvaluationTaskItemSort totalScore2 = new PerformanceEvaluationTaskItemSort();
			totalScore2.setId(Integer.MAX_VALUE);
			totalScore2.setScore(totalScore.toString());
			taskItemListSort.add(totalScore2);

			Collections.sort(taskItemListSort);
			resultPerson.setTaskItemListSort(taskItemListSort);
			listResult.add(resultPerson);
		}

		List<PerformanceEvaluationTaskItemSort> taskItemListSort = new ArrayList<PerformanceEvaluationTaskItemSort>();
		for (PerformanceEvaluationTaskItemVo temp : taskItemList) {
			PerformanceEvaluationTaskItemSort sort = new PerformanceEvaluationTaskItemSort();
			sort.setId(temp.getId());
			sort.setItemName(temp.getItemName());
			taskItemListSort.add(sort);
		}
		PerformanceEvaluationTaskItemSort sortBig = new PerformanceEvaluationTaskItemSort();
		sortBig.setId(Integer.MAX_VALUE);
		sortBig.setItemName("总分数");
		taskItemListSort.add(sortBig);
		Collections.sort(taskItemListSort);
//		model.addAttribute("taskItemList", taskItemListSort);
//		model.addAttribute("personnelList", listResult);

		listResult = this.addBackSpace(listResult,taskItemListSort);
		
		 List<String> titleList = new ArrayList<String>();
		 titleList.add("考核任务");
		 titleList.add("考核人");
		 titleList.add("被考核人");
		 titleList.add("部门");
		 
		 List<String> tList = new ArrayList<String>();
		 tList.add("taskName");
		 tList.add("evaluateName");
		 tList.add("evaluatedName");
		 tList.add("department");
		 
		 for(PerformanceEvaluationTaskItemSort taskItemSort : taskItemListSort) {
			 titleList.add(taskItemSort.getItemName());
			 tList.add((taskItemSort.getId()).toString());
		 }
		 //list转换成数组
		 String[] titles = new String[titleList.size()];
		 for(int i = 0, j = titleList.size(); i < j; i++) {
			 titles[i] = titleList.get(i);
		 }
		 String[] fieldNames = new String[tList.size()];
		 for(int i = 0, j = tList.size(); i < j; i++) {
			 fieldNames[i] = tList.get(i);
		 }
		 
		 List<Object> list = new ArrayList<Object>();
		 for(PerformanceEvaluationPersonnelVo vo : listResult) {
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("taskName", vo.getTaskName());
			 map.put("evaluateName", vo.getEvaluateName());
			 map.put("evaluatedName", vo.getEvaluatedName());
			 map.put("department", vo.getDepartment());
			 if(vo.getTaskItemListSort().size() > 0) {
				 for(PerformanceEvaluationTaskItemSort sort : vo.getTaskItemListSort()) {
					 String key = (sort.getId()).toString();
					 map.put(key, sort.getScore());
				 }
			 }
			 list.add(map);
		 }
		 
		 config.setTitles(titles);
		 config.setFieldNames(fieldNames);
		 config.setSheetTitle("考核任务信息表");
		 String fileName = "考核任务信息表.xls";
		 
		 try {
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user,
			PerformanceEvaluationTaskCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
