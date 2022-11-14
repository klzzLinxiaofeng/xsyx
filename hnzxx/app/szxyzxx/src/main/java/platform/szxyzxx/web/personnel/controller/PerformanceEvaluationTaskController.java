package platform.szxyzxx.web.personnel.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.personnel.model.PerformanceEvaluationItem;
import platform.education.personnel.model.PerformanceEvaluationPersonnel;
import platform.education.personnel.model.PerformanceEvaluationScore;
import platform.education.personnel.model.PerformanceEvaluationTask;
import platform.education.personnel.model.PerformanceEvaluationTaskItem;
import platform.education.personnel.service.PerformanceEvaluationTaskService;
import platform.education.personnel.vo.PerformanceEvaluationItemCondition;
import platform.education.personnel.vo.PerformanceEvaluationPersonnelCondition;
import platform.education.personnel.vo.PerformanceEvaluationPersonnelVo;
import platform.education.personnel.vo.PerformanceEvaluationScoreCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskItemCondition;
import platform.education.personnel.vo.PerformanceEvaluationTaskVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/personnel/performanceEvaluationTask")
public class PerformanceEvaluationTaskController extends BaseController {

	private final static String viewBasePath = "/personnel/performanceEvaluationTask";

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
		List<PerformanceEvaluationTask> tasks = this.performanceEvaluationTaskService.findPerformanceEvaluationTaskByCondition(condition, page, order);

		List<PerformanceEvaluationTaskVo> voList = new ArrayList<PerformanceEvaluationTaskVo>();
		for (PerformanceEvaluationTask task : tasks) {
			PerformanceEvaluationTaskVo performanceEvaluationTaskvo = new PerformanceEvaluationTaskVo();
			BeanUtils.copyProperties(task, performanceEvaluationTaskvo);
			
			/**
			 * 查询是否已经完成全部考核
			 */
			
			//查询考核分数表
			PerformanceEvaluationScoreCondition scoreCondition = new PerformanceEvaluationScoreCondition();
			scoreCondition.setTaskId(task.getId());
			scoreCondition.setIsDelete(false);
			List<PerformanceEvaluationScore> scoreList = this.performanceEvaluationScoreService.findPerformanceEvaluationScoreByCondition(scoreCondition);
			
			//查询考核人与被考核人
			PerformanceEvaluationPersonnelCondition performanceEvaluationPersonnelCondition = new PerformanceEvaluationPersonnelCondition();
			performanceEvaluationPersonnelCondition.setTaskId(task.getId());
			performanceEvaluationPersonnelCondition.setIsDelete(false);
			List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService
					.findPerformanceEvaluationPersonnelByCondition(performanceEvaluationPersonnelCondition);
			List<PerformanceEvaluationPersonnel> evaluates = new ArrayList<PerformanceEvaluationPersonnel>();
			List<PerformanceEvaluationPersonnel> evaluateds = new ArrayList<PerformanceEvaluationPersonnel>();
			if(personnelList.size() > 0){
				for(PerformanceEvaluationPersonnel personnel : personnelList) {
					if("0".equals(personnel.getType())) {
						evaluates.add(personnel);
					}else if("1".equals(personnel.getType())) {
						evaluateds.add(personnel);
					}
				}
			}
			//查询考核项目
			PerformanceEvaluationTaskItemCondition taskItemContion = new PerformanceEvaluationTaskItemCondition();
			taskItemContion.setTaskId(task.getId());
			taskItemContion.setIsDelete(false);
			List<PerformanceEvaluationTaskItem> taskItemList = this.performanceEvaluationTaskItemService.findPerformanceEvaluationTaskItemByCondition(taskItemContion);
			
			Integer type;
			if(scoreList.size() == (evaluates.size()*evaluateds.size()*taskItemList.size())) {
				type = 0;
			}else {
				type = 1;
			}
			
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

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<PerformanceEvaluationTask> jsonList(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") PerformanceEvaluationTaskCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.performanceEvaluationTaskService.findPerformanceEvaluationTaskByCondition(condition, page, order);
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
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		// 查询考核项目
		PerformanceEvaluationItemCondition itemCondition = new PerformanceEvaluationItemCondition();
		itemCondition.setSchoolId(user.getSchoolId());
		List<PerformanceEvaluationItem> items = this.performanceEvaluationItemService.findPerformanceEvaluationItemByCondition(itemCondition, null, null);
		model.addAttribute("items", items);
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(PerformanceEvaluationTaskVo taskVo,
			@CurrentUser UserInfo user,
			@RequestParam(value = "saveItems") String saveItems,
			@RequestParam(value = "addItems") String addItems) {
		// 保存任务，返回任务ID
		taskVo.setSchoolId(user.getSchoolId());
		PerformanceEvaluationTask task = new PerformanceEvaluationTask();
		BeanUtils.copyProperties(taskVo, task);
		task = this.performanceEvaluationTaskService.add(task);
		Integer taskId = null;
		if (task != null) {
			taskId = task.getId();
		}

		String evaluateId = taskVo.getEvaluateId();
		String evaluatedId = taskVo.getEvaluatedId();
		String[] evaluateArr = evaluateId.split(";");
		String[] evaluatedArr = evaluatedId.split(";");
		for (String eId : evaluateArr) {
			if (!eId.equals("")) {
				Integer personnelId = Integer.valueOf(eId);
				PerformanceEvaluationPersonnel personnel = new PerformanceEvaluationPersonnel();
				personnel.setSchoolId(user.getSchoolId());
				personnel.setTaskId(taskId);
				personnel.setPersonnelId(personnelId);
				personnel.setType("0");
				personnel = this.performanceEvaluationPersonnelService
						.add(personnel);
			}
		}
		for (String eId : evaluatedArr) {
			if (!eId.equals("")) {
				Integer personnelId = Integer.valueOf(eId);
				PerformanceEvaluationPersonnel personnel = new PerformanceEvaluationPersonnel();
				personnel.setSchoolId(user.getSchoolId());
				personnel.setTaskId(taskId);
				personnel.setPersonnelId(personnelId);
				personnel.setType("1");
				personnel = this.performanceEvaluationPersonnelService
						.add(personnel);
			}
		}

		// 已存在的考核项目
		JSONArray saveJson = JSONArray.fromObject(saveItems);
		for (int i = 0; i < saveJson.size(); i++) {
			JSONObject jo = saveJson.getJSONObject(i);
			PerformanceEvaluationTaskItem taskItem = new PerformanceEvaluationTaskItem();
			taskItem.setSchoolId(user.getSchoolId());
			taskItem.setTaskId(taskId);
			taskItem.setItemId(jo.getInt("itemId"));
			taskItem = this.performanceEvaluationTaskItemService.add(taskItem);
		}

		// 新添加的考核项目先保存到考核项目表中返回itemId后再保存到考核任务与考核项目表中
		ArrayList<Integer> itemIds = new ArrayList<Integer>();
		JSONArray addJson = JSONArray.fromObject(addItems);
		for (int i = 0; i < addJson.size(); i++) {
			PerformanceEvaluationItem item = new PerformanceEvaluationItem();
			JSONObject jo = addJson.getJSONObject(i);
			item.setSchoolId(user.getSchoolId());
			item.setName(jo.getString("name"));
			item.setStartScore(jo.getString("startScore"));
			item.setEndScore(jo.getString("endScore"));
			item.setDescription(jo.getString("description"));
			item = this.performanceEvaluationItemService.add(item);
			if (item != null) {
				itemIds.add(item.getId());
			}
		}

		for (Integer itemId : itemIds) {
			PerformanceEvaluationTaskItem taskItem = new PerformanceEvaluationTaskItem();
			taskItem.setSchoolId(user.getSchoolId());
			taskItem.setTaskId(taskId);
			taskItem.setItemId(itemId);
			taskItem = this.performanceEvaluationTaskItemService.add(taskItem);
		}
		
		

		return task != null ? new ResponseInfomation(task.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		PerformanceEvaluationTask task = this.performanceEvaluationTaskService.findPerformanceEvaluationTaskById(id);
		// 获得所属考核任务的考核项目
		PerformanceEvaluationTaskItemCondition condition = new PerformanceEvaluationTaskItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setTaskId(id);
		condition.setIsDelete(false);
		List<PerformanceEvaluationTaskItem> taskItems = this.performanceEvaluationTaskItemService.findPerformanceEvaluationTaskItemByCondition(condition);
		List<PerformanceEvaluationItem> items = new ArrayList<PerformanceEvaluationItem>();
		for (PerformanceEvaluationTaskItem taskItem : taskItems) {
			PerformanceEvaluationItem item = this.performanceEvaluationItemService.findPerformanceEvaluationItemById(taskItem.getItemId());
			items.add(item);
		}

		// 获得考核人与被考核人
		String evaluateName = "";
		String evaluateId = "";
		String evaluatedName = "";
		String evaluatedId = "";
		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setSchoolId(user.getSchoolId());
		personnelCondition.setTaskId(id);
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnels = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);
		for (PerformanceEvaluationPersonnel personnel : personnels) {
			Teacher teacher = this.teacherService.findTeacherById(personnel.getPersonnelId());
			if(teacher != null) {
			if ("0".equals(personnel.getType())) {
				if ("".equals(evaluateName)) {
					evaluateName = teacher.getName();
				} else {
					evaluateName = evaluateName + ";" + teacher.getName();
				}
				if ("".equals(evaluateId)) {
					evaluateId = personnel.getPersonnelId().toString();
				} else {
					evaluateId = evaluateId + ";" + personnel.getPersonnelId();
				}
			} else if ("1".equals(personnel.getType())) {
				if ("".equals(evaluatedName)) {
					evaluatedName = teacher.getName();
				} else {
					evaluatedName = evaluatedName + ";" + teacher.getName();
				}
				if ("".equals(evaluatedId)) {
					evaluatedId = personnel.getPersonnelId().toString();
				} else {
					evaluatedId = evaluatedId + ";"
							+ personnel.getPersonnelId();
				}
			}
			}
		}
		model.addAttribute("evaluateId", evaluateId);
		model.addAttribute("evaluateName", evaluateName);
		model.addAttribute("evaluatedId", evaluatedId);
		model.addAttribute("evaluatedName", evaluatedName);
		model.addAttribute("task", task);
		model.addAttribute("items", items);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id, Model model) {

		PerformanceEvaluationTask task = this.performanceEvaluationTaskService.findPerformanceEvaluationTaskById(id);
		// 获得所属考核任务的考核项目
		PerformanceEvaluationTaskItemCondition condition = new PerformanceEvaluationTaskItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setTaskId(id);
		condition.setIsDelete(false);
		List<PerformanceEvaluationTaskItem> taskItems = this.performanceEvaluationTaskItemService.findPerformanceEvaluationTaskItemByCondition(condition);
		List<PerformanceEvaluationItem> items = new ArrayList<PerformanceEvaluationItem>();
		for (PerformanceEvaluationTaskItem taskItem : taskItems) {
			PerformanceEvaluationItem item = this.performanceEvaluationItemService.findPerformanceEvaluationItemById(taskItem.getItemId());
			items.add(item);
		}

		// 获得考核人与被考核人
		String evaluateName = "";
		String evaluateId = "";
		String evaluatedName = "";
		String evaluatedId = "";
		PerformanceEvaluationPersonnelCondition personnelCondition = new PerformanceEvaluationPersonnelCondition();
		personnelCondition.setSchoolId(user.getSchoolId());
		personnelCondition.setTaskId(id);
		personnelCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> personnels = this.performanceEvaluationPersonnelService
				.findPerformanceEvaluationPersonnelByCondition(personnelCondition);
		for (PerformanceEvaluationPersonnel personnel : personnels) {
			Teacher teacher = this.teacherService.findTeacherById(personnel.getPersonnelId());
			if(teacher != null) {
			if ("0".equals(personnel.getType())) {
				if ("".equals(evaluateName)) {
					evaluateName = teacher.getName();
				} else {
					evaluateName = evaluateName + ";" + teacher.getName();
				}
				if ("".equals(evaluateId)) {
					evaluateId = personnel.getPersonnelId().toString();
				} else {
					evaluateId = evaluateId + ";" + personnel.getPersonnelId();
				}
			} else if ("1".equals(personnel.getType())) {
				if ("".equals(evaluatedName)) {
					evaluatedName = teacher.getName();
				} else {
					evaluatedName = evaluatedName + ";" + teacher.getName();
				}
				if ("".equals(evaluatedId)) {
					evaluatedId = personnel.getPersonnelId().toString();
				} else {
					evaluatedId = evaluatedId + ";"
							+ personnel.getPersonnelId();
				}
			}
			}
		}
		model.addAttribute("evaluateId", evaluateId);
		model.addAttribute("evaluateName", evaluateName);
		model.addAttribute("evaluatedId", evaluatedId);
		model.addAttribute("evaluatedName", evaluatedName);
		model.addAttribute("task", task);
		model.addAttribute("items", items);
		model.addAttribute("isCK", "disable");
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/issue/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String issue(@PathVariable(value = "id") Integer id,
			@CurrentUser UserInfo user,
			PerformanceEvaluationTask performanceEvaluationTask) {
		if(performanceEvaluationTask != null) {
			performanceEvaluationTask.setId(id);
		}
		return this.performanceEvaluationTaskService.issue(performanceEvaluationTask);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			@CurrentUser UserInfo user,
			PerformanceEvaluationTask performanceEvaluationTask) {
		if (performanceEvaluationTask != null) {
			performanceEvaluationTask.setId(id);
		}
		String result = this.performanceEvaluationTaskService.abandon(performanceEvaluationTask);
		
		
		if(PerformanceEvaluationTaskService.OPERATE_SUCCESS.equals(result)) {
			//删除考核任务废弃后，把考核人与被考核人表中的记录废弃
			PerformanceEvaluationPersonnelCondition performanceEvaluationPersonnelCondition = new PerformanceEvaluationPersonnelCondition();
			performanceEvaluationPersonnelCondition.setSchoolId(user.getSchoolId());
			performanceEvaluationPersonnelCondition.setTaskId(id);
			List<PerformanceEvaluationPersonnel> personnelList = this.performanceEvaluationPersonnelService.findPerformanceEvaluationPersonnelByCondition(performanceEvaluationPersonnelCondition);
			for(PerformanceEvaluationPersonnel personnel : personnelList) {
				this.performanceEvaluationPersonnelService.abandon(personnel);
			}
			
			//删除考核任务废弃后，把考核任务与考核项目表中的记录废弃
			PerformanceEvaluationTaskItemCondition performanceEvaluationTaskItemCondition = new PerformanceEvaluationTaskItemCondition();
			performanceEvaluationTaskItemCondition.setSchoolId(user.getSchoolId());
			performanceEvaluationTaskItemCondition.setTaskId(id);
			List<PerformanceEvaluationTaskItem> taskItemList = this.performanceEvaluationTaskItemService.findPerformanceEvaluationTaskItemByCondition(performanceEvaluationTaskItemCondition);
			for(PerformanceEvaluationTaskItem taskItem : taskItemList) {
				this.performanceEvaluationTaskItemService.abandon(taskItem);
			}
			
			//删除考核任务废弃后，把考核任务与考核项目表中的记录废弃
			PerformanceEvaluationScoreCondition performanceEvaluationScoreCondition = new PerformanceEvaluationScoreCondition();
			performanceEvaluationScoreCondition.setSchoolId(user.getSchoolId());
			performanceEvaluationScoreCondition.setTaskId(id);
			List<PerformanceEvaluationScore> scoreList = this.performanceEvaluationScoreService.findPerformanceEvaluationScoreByCondition(performanceEvaluationScoreCondition);
			for(PerformanceEvaluationScore socre : scoreList) {
				this.performanceEvaluationScoreService.abandon(socre);
			}
		}
		
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			@CurrentUser UserInfo user,
			PerformanceEvaluationTaskVo taskVo,
			@RequestParam(value = "saveItems") String saveItems,
			@RequestParam(value = "addItems") String addItems) {
		PerformanceEvaluationTask task = new PerformanceEvaluationTask();
		BeanUtils.copyProperties(taskVo, task);
		task.setId(id);
		task = this.performanceEvaluationTaskService.modify(task);

		Integer taskId = null;
		if (task != null) {
			taskId = task.getId();
		}

		String evaluateId = taskVo.getEvaluateId();
		String evaluatedId = taskVo.getEvaluatedId();
		String[] evaluateArr = evaluateId.split(";");
		String[] evaluatedArr = evaluatedId.split(";");
		List<Integer> evaluateIds = new ArrayList<Integer> ();
		List<Integer> evaluatedIds = new ArrayList<Integer> ();
		List<Integer> evaluateResult = new ArrayList<Integer> ();
		List<Integer> evaluatedResult = new ArrayList<Integer> ();
		
		//考核人做修改操作时，先判断是否已经存在，若不存在，则添加记录
		PerformanceEvaluationPersonnelCondition evaluateCondition = new PerformanceEvaluationPersonnelCondition();
		evaluateCondition.setSchoolId(user.getSchoolId());
		evaluateCondition.setTaskId(taskId);
		evaluateCondition.setType("0");
		evaluateCondition.setIsDelete(false);
		List<PerformanceEvaluationPersonnel> evaluateList = this.performanceEvaluationPersonnelService.findPerformanceEvaluationPersonnelByCondition(evaluateCondition);
		List<Integer> evaluateIdList = new ArrayList<Integer>();
		for(PerformanceEvaluationPersonnel personnel : evaluateList){
			Integer pId = personnel.getPersonnelId();
			evaluateIdList.add(pId);
		}
		for (String eId : evaluateArr) {
			if (!eId.equals("")) {
				Integer personnelId = Integer.valueOf(eId);
				evaluateIds.add(personnelId);
			}
		}
		
		evaluateResult.clear();
		evaluateResult.addAll(evaluateIdList);
		evaluateResult.retainAll(evaluateIds); 
		//求出交集，不在交集内的删除
		evaluateIdList.removeAll(evaluateResult);
		
		if(evaluateIdList != null) {
			for(Integer personnelId : evaluateIdList) {
				PerformanceEvaluationPersonnelCondition condition = new PerformanceEvaluationPersonnelCondition();
				condition.setSchoolId(user.getSchoolId());
				condition.setPersonnelId(personnelId);
				condition.setType("0");
				this.performanceEvaluationPersonnelService.remove(condition);
			}
		}
		
		//求出交集，不在交集内的添加
		evaluateIds.removeAll(evaluateResult);
		if(evaluateIds != null) {
			for(Integer personnelId : evaluateIds) {
				PerformanceEvaluationPersonnel personnel = new PerformanceEvaluationPersonnel();
				personnel.setSchoolId(user.getSchoolId());
				personnel.setTaskId(taskId);
				personnel.setPersonnelId(personnelId);
				personnel.setType("0");
				personnel = this.performanceEvaluationPersonnelService
						.add(personnel);
			}
		}
		
		//被考核人做修改操作时，先判断是否已经存在，若不存在，则添加记录
		PerformanceEvaluationPersonnelCondition evaluatedCondition = new PerformanceEvaluationPersonnelCondition();
		evaluatedCondition.setSchoolId(user.getSchoolId());
		evaluatedCondition.setTaskId(taskId);
		evaluatedCondition.setType("1");
		List<PerformanceEvaluationPersonnel> evaluatedList = this.performanceEvaluationPersonnelService.findPerformanceEvaluationPersonnelByCondition(evaluatedCondition);
		List<Integer> evaluatedIdList = new ArrayList<Integer>();
		for(PerformanceEvaluationPersonnel personnel : evaluatedList){
			Integer pId = personnel.getPersonnelId();
			evaluatedIdList.add(pId);
		}
		
		for (String eId : evaluatedArr) {
			if (!eId.equals("")) {
				Integer personnelId = Integer.valueOf(eId);
				evaluatedIds.add(personnelId);
			}
		}
		
		evaluatedResult.clear();
		evaluatedResult.addAll(evaluatedIdList); 
		evaluatedResult.retainAll(evaluatedIds);  
		//求出交集，不在交集内的删除
		evaluatedIdList.removeAll(evaluatedResult);
		
		if(evaluatedIdList != null) {
			for(Integer personnelId : evaluatedIdList) {
				PerformanceEvaluationPersonnelCondition condition = new PerformanceEvaluationPersonnelCondition();
				condition.setSchoolId(user.getSchoolId());
				condition.setPersonnelId(personnelId);
				condition.setType("1");
				this.performanceEvaluationPersonnelService.remove(condition);
			}
		}
		
		//求出交集，不在交集内的添加
		evaluatedIds.removeAll(evaluatedResult);
		if(evaluatedIds != null) {
			for(Integer personnelId : evaluatedIds) {
				PerformanceEvaluationPersonnel personnel = new PerformanceEvaluationPersonnel();
				personnel.setSchoolId(user.getSchoolId());
				personnel.setTaskId(taskId);
				personnel.setPersonnelId(personnelId);
				personnel.setType("1");
				personnel = this.performanceEvaluationPersonnelService.add(personnel);
			}
		}

		// 已存在的考核项目
		PerformanceEvaluationTaskItemCondition taskItemCondition = new PerformanceEvaluationTaskItemCondition();
		taskItemCondition.setSchoolId(user.getSchoolId());
		taskItemCondition.setTaskId(taskId);
		taskItemCondition.setIsDelete(false);
		List<PerformanceEvaluationTaskItem> taskItems = this.performanceEvaluationTaskItemService.findPerformanceEvaluationTaskItemByCondition(taskItemCondition);
		List<Integer> taskItemList = new ArrayList<Integer> ();
		for(PerformanceEvaluationTaskItem taskItem : taskItems) {
			Integer itemId = taskItem.getItemId();
			taskItemList.add(itemId);
		}
		List<Integer> itemList = new ArrayList<Integer> ();
		JSONArray saveJson = JSONArray.fromObject(saveItems);
		for (int i = 0; i < saveJson.size(); i++) {
			JSONObject jo = saveJson.getJSONObject(i);
			itemList.add(jo.getInt("itemId"));
		}
		List<Integer> results = new ArrayList<Integer>();
		results.clear();
		results.addAll(taskItemList);
		results.removeAll(itemList);
		
		for(Integer result : results) {
			PerformanceEvaluationTaskItemCondition condition = new PerformanceEvaluationTaskItemCondition();
			condition.setSchoolId(user.getSchoolId());
			condition.setItemId(result);
			this.performanceEvaluationTaskItemService.remove(condition);
		}
		
		// 新添加的考核项目先保存到考核项目表中返回itemId后再保存到考核任务与考核项目表中
		ArrayList<Integer> itemIds = new ArrayList<Integer>();
		JSONArray addJson = JSONArray.fromObject(addItems);
		for (int i = 0; i < addJson.size(); i++) {
			PerformanceEvaluationItem item = new PerformanceEvaluationItem();
			JSONObject jo = addJson.getJSONObject(i);
			item.setSchoolId(user.getSchoolId());
			item.setName(jo.getString("name"));
			item.setStartScore(jo.getString("startScore"));
			item.setEndScore(jo.getString("endScore"));
			item.setDescription(jo.getString("description"));
			item = this.performanceEvaluationItemService.add(item);
			if (item != null) {
				itemIds.add(item.getId());
			}
		}

		for (Integer itemId : itemIds) {
			PerformanceEvaluationTaskItem taskItem = new PerformanceEvaluationTaskItem();
			taskItem.setSchoolId(user.getSchoolId());
			taskItem.setTaskId(taskId);
			taskItem.setItemId(itemId);
			taskItem = this.performanceEvaluationTaskItemService.add(taskItem);
		}

		return task != null ? new ResponseInfomation(task.getId(),
				ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	private void conditionFilter(UserInfo user, PerformanceEvaluationTaskCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
}
