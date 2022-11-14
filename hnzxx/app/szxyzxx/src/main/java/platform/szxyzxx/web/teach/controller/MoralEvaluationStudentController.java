package platform.szxyzxx.web.teach.controller;
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

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemVo;
import platform.education.generalTeachingAffair.vo.MoralEvaluationResultCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentVo;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/moralEvaluationStudent")
public class MoralEvaluationStudentController extends BaseController { 
	
	private final static String viewBasePath = "/teach/moralEvaluationStudent";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MoralEvaluationStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		List<MoralEvaluationStudent> moralEvaluationStudent = this.moralEvaluationStudentService.findMoralEvaluationStudentByCondition(condition, page, order);
		List<MoralEvaluationStudentVo> moralEvaluationStudentVos = toVos(moralEvaluationStudent, user);
//		List<MoralEvaluationStudentVo> moralEvaluationStudentVos = this.moralEvaluationStudentService.findMoralEvaluationStudentByConditionMore(condition, page, order);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("moralEvaluationStudentVos", moralEvaluationStudentVos);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(
			@CurrentUser UserInfo user,
			@RequestParam(value = "studentId", required = true) Integer studentId,
			Model model) {
		String viewPath = null;
		MoralEvaluationStudentCondition condition = new MoralEvaluationStudentCondition();
		conditionFilter(user, condition);
		condition.setStudentId(studentId);
		List<MoralEvaluationStudent> moralEvaluationStudentList = this.moralEvaluationStudentService.findMoralEvaluationStudentByCondition(condition);
		List<MoralEvaluationStudentVo> moralEvaluationStudentVoList = toVos(moralEvaluationStudentList, user);
//		List<MoralEvaluationStudentVo> studentVoList = this.moralEvaluationStudentService.findMoralEvaluationStudentByConditionMore(condition, null, null);
//		if(studentVoList.size() > 0) {
//			for(MoralEvaluationStudentVo vo : studentVoList) {
//				SchoolYearCondition yearCondition = new SchoolYearCondition();
//				yearCondition.setSchoolId(user.getSchoolId());
//				yearCondition.setYear(vo.getSchoolYear());
//				SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(yearCondition);
//				if(schoolYear != null) {
//					vo.setSchoolYearName(schoolYear.getName());;
//				}
//			}
//		}
		model.addAttribute("studentVoList", moralEvaluationStudentVoList);
		viewPath = structurePath("/viewList");
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<MoralEvaluationStudent> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") MoralEvaluationStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.moralEvaluationStudentService.findMoralEvaluationStudentByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user, Model model) {
		MoralEvaluationItemCondition condition = new MoralEvaluationItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<MoralEvaluationItem> items = this.moralEvaluationItemService.findMoralEvaluationItemByCondition(condition);
		model.addAttribute("items", items);
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(
			@CurrentUser UserInfo user, 
			MoralEvaluationStudent moralEvaluationStudent,
			@RequestParam(value = "classifications") String classifications) {
		Integer schoolId = user.getSchoolId();
		
		List<MoralEvaluationResult> moralEvaluationResults = new ArrayList<MoralEvaluationResult>();  //针对某个学生的评价结果
		
		JSONArray josnArray = JSONArray.fromObject(classifications);
		for (int i = 0; i < josnArray.size(); i++) {
			MoralEvaluationResult result = new MoralEvaluationResult();
			JSONObject jo = josnArray.getJSONObject(i);
			result.setItemId(jo.getInt("itemId"));
			result.setResult(jo.getString("result"));
			result.setRemark(jo.getString("remark"));
			moralEvaluationResults.add(result);
		}
		
		String msg = null;
		msg = this.moralEvaluationStudentService.addMoralEvaluationResult(schoolId, moralEvaluationStudent, moralEvaluationResults);
		return msg.equals("success") ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@CurrentUser UserInfo user, 
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		
		MoralEvaluationStudent moralEvaluationStudent = this.moralEvaluationStudentService.findMoralEvaluationStudentById(id);
		MoralEvaluationStudentVo moralEvaluationStudentVo = toVo(moralEvaluationStudent, user);
		
		List<Integer> idResultList = new ArrayList<Integer>();
		
		//查询出评价项目
		MoralEvaluationItemCondition condition = new MoralEvaluationItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<MoralEvaluationItem> itemList = this.moralEvaluationItemService.findMoralEvaluationItemByCondition(condition);
		List<Integer> idList = new ArrayList<Integer>();
		for(MoralEvaluationItem item : itemList) {
			idList.add(item.getId());
		}
		
		//查询出已经存在的评价结果
		MoralEvaluationResultCondition resultCondition = new MoralEvaluationResultCondition();
		resultCondition.setEvaluationId(id);
		List<MoralEvaluationResult> resultList = this.moralEvaluationResultService.findMoralEvaluationResultByCondition(resultCondition);
		List<Integer> itemIdList = new ArrayList<Integer>();
		if(resultList.size() > 0) {
			for(MoralEvaluationResult result : resultList) {
				itemIdList.add(result.getItemId());
			}
		}
		
		idResultList.clear();
		idResultList.addAll(itemIdList);
		idResultList.retainAll(idList); //并集
		itemIdList.removeAll(idResultList); //已经被删除的记录移除
		if(itemIdList != null) {
			for(Integer itemId : itemIdList) {
				MoralEvaluationResultCondition resCondition = new MoralEvaluationResultCondition();
				resCondition.setItemId(itemId);
				resCondition.setEvaluationId(id);
				this.moralEvaluationResultService.remove(resCondition);
			}
		}
		
		List<MoralEvaluationItemVo> itemVoList = new ArrayList<MoralEvaluationItemVo>();
		MoralEvaluationItemCondition itemCondition = new MoralEvaluationItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		itemCondition.setEvaluationId(id);
		itemVoList = this.moralEvaluationItemService.findMoralEvaluationItemByConditionMore(itemCondition);
		
		idList.removeAll(idResultList); //新添加的项目添加
		if(idList != null) {
			for(Integer temp : idList) {
				MoralEvaluationItem item = this.moralEvaluationItemService.findMoralEvaluationItemById(temp);
				MoralEvaluationItemVo itemVo = new MoralEvaluationItemVo();
				BeanUtils.copyProperties(item, itemVo);
				itemVoList.add(itemVo);
			}
		}

		model.addAttribute("itemVoList", itemVoList);
		model.addAttribute("moralEvaluationStudent", moralEvaluationStudentVo);
		return new ModelAndView(structurePath("/modify"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		
		MoralEvaluationStudent moralEvaluationStudent = this.moralEvaluationStudentService.findMoralEvaluationStudentById(id);
		MoralEvaluationStudentVo moralEvaluationStudentVo = toVo(moralEvaluationStudent, user);
		
		List<Integer> idResultList = new ArrayList<Integer>();
		
		//查询出评价项目
		MoralEvaluationItemCondition condition = new MoralEvaluationItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		List<MoralEvaluationItem> itemList = this.moralEvaluationItemService.findMoralEvaluationItemByCondition(condition);
		List<Integer> idList = new ArrayList<Integer>();
		for(MoralEvaluationItem item : itemList) {
			idList.add(item.getId());
		}
		
		//查询出已经存在的评价结果
		MoralEvaluationResultCondition resultCondition = new MoralEvaluationResultCondition();
		resultCondition.setEvaluationId(id);
		List<MoralEvaluationResult> resultList = this.moralEvaluationResultService.findMoralEvaluationResultByCondition(resultCondition);
		List<Integer> itemIdList = new ArrayList<Integer>();
		if(resultList.size() > 0) {
			for(MoralEvaluationResult result : resultList) {
				itemIdList.add(result.getItemId());
			}
		}
		
		idResultList.clear();
		idResultList.addAll(itemIdList);
		idResultList.retainAll(idList); //并集
		itemIdList.removeAll(idResultList); //已经被删除的记录移除
		if(itemIdList != null) {
			for(Integer itemId : itemIdList) {
				MoralEvaluationResultCondition resCondition = new MoralEvaluationResultCondition();
				resCondition.setItemId(itemId);
				resCondition.setEvaluationId(id);
				this.moralEvaluationResultService.remove(resCondition);
			}
		}
		
		List<MoralEvaluationItemVo> itemVoList = new ArrayList<MoralEvaluationItemVo>();
		MoralEvaluationItemCondition itemCondition = new MoralEvaluationItemCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		itemCondition.setEvaluationId(id);
		itemVoList = this.moralEvaluationItemService.findMoralEvaluationItemByConditionMore(itemCondition);
		
		idList.removeAll(idResultList); //新添加的项目添加
		if(idList != null) {
			for(Integer temp : idList) {
				MoralEvaluationItem item = this.moralEvaluationItemService.findMoralEvaluationItemById(temp);
				MoralEvaluationItemVo itemVo = new MoralEvaluationItemVo();
				BeanUtils.copyProperties(item, itemVo);
				itemVoList.add(itemVo);
			}
		}

		model.addAttribute("isCK", "disable");
		model.addAttribute("itemVoList", itemVoList);
		model.addAttribute("moralEvaluationStudent", moralEvaluationStudentVo);
		return new ModelAndView(structurePath("/modify"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, MoralEvaluationStudent moralEvaluationStudent) {
		if (moralEvaluationStudent != null) {
			moralEvaluationStudent.setId(id);
		}
		return this.moralEvaluationStudentService.abandon(moralEvaluationStudent);
	}

	//保存修改
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(
			@PathVariable(value = "id") Integer id,
			MoralEvaluationStudent moralEvaluationStudent,
			@RequestParam(value = "classifications") String classifications) {
		
		List<MoralEvaluationResult> moralEvaluationResults = new ArrayList<MoralEvaluationResult>();
		
		JSONArray josnArray = JSONArray.fromObject(classifications);
		for (int i = 0; i < josnArray.size(); i++) {
			MoralEvaluationResult result = new MoralEvaluationResult();
			JSONObject jo = josnArray.getJSONObject(i);
			result.setItemId(jo.getInt("itemId"));
			result.setResult(jo.getString("result"));
			result.setRemark(jo.getString("remark"));
			result.setEvaluationId(id);
			moralEvaluationResults.add(result);
		}
		
		String msg = null;
		msg = this.moralEvaluationStudentService.modifyMoralEvaluationResult(id, moralEvaluationStudent, moralEvaluationResults);
		return msg.equals("success") ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private List<MoralEvaluationStudentVo> toVos(List<MoralEvaluationStudent> moralEvaluationStudentList, UserInfo user) {
		List<MoralEvaluationStudentVo> voList = new ArrayList<MoralEvaluationStudentVo>();
		if(moralEvaluationStudentList.size() > 0) {
			for(MoralEvaluationStudent moralEvaluationStudent : moralEvaluationStudentList) {
				voList.add(toVo(moralEvaluationStudent, user));
			}
		}
		return voList;
	}
	
	private MoralEvaluationStudentVo toVo(MoralEvaluationStudent moralEvaluationStudent, UserInfo user) {
		Student student = new Student();
		Grade grade = new Grade();
		Team team = new Team();
		
		MoralEvaluationStudentVo vo = new MoralEvaluationStudentVo();
		BeanUtils.copyProperties(moralEvaluationStudent, vo);
		
		//获取学生姓名
		student = this.studentService.findStudentById(moralEvaluationStudent.getStudentId());
		if(student != null) {
			vo.setStudentName(student.getName());
		}
		//获取班级名称
		team = this.teamService.findTeamById(moralEvaluationStudent.getTeamId());
		if(team != null) {
			vo.setSchoolYear(team.getSchoolYear()); //学年的开始年份
			vo.setTeamName(team.getName());  //班级名称
			SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
			schoolYearCondition.setSchoolId(user.getSchoolId());
			schoolYearCondition.setYear(team.getSchoolYear());
			//获取学年名称
			SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
			if(schoolYear != null) {
				vo.setSchoolYearName(schoolYear.getName());
			}
			//获取年级名称
			grade = this.gradeService.findGradeById(team.getGradeId());
			if(grade != null) {
				vo.setGradeId(grade.getId());
				vo.setGradeName(grade.getName());
			}
		}
		return vo;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, MoralEvaluationStudentCondition condition) {
		Integer schoolId = condition.getSchoolId();
		Boolean isDeleted = condition.getIsDelete();
		condition.setIsDelete(isDeleted != null ? isDeleted : false);
		condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
	}
	
}
