package platform.szxyzxx.web.teach.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.ParentCondition;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

/**
 * 班级学生管理
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/teamStudent")
public class TeamStudentController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/teamStudent";
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TeamStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		List<TeamStudentVo> teamStudentVos = null;
		
		//于2016-1-21 将查询放入list中 避免首次进入的时候查询了两次  另外XML中findTeamStudentByConditionStudent把查询列stu.user_id去掉了，因为在s.*中已包含user_id，还学校条件和学年条件放在了前面，之前放在最后面  查询效率偏低
		//这些修改 对这里的功能没有任何影响，如果对其他功能有影响  请按照上面的恢复
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			teamStudentVos = this.teamStudentService.findTeamStudentByConditionStudent(condition, page, order);
			for(TeamStudentVo teamStudent : teamStudentVos) {
				ParentStudentCondition psCondition = new ParentStudentCondition();
				psCondition.setStudentUserId(teamStudent.getUserId());
				psCondition.setRank("1"); //0--普通；1--主监护人
				psCondition.setIsDelete(false);
				List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(psCondition);
				List<Parent> parentList = new ArrayList<Parent>();
				for(ParentStudent parentStudent : psList) {
					ParentCondition parentCondition = new ParentCondition();
					parentCondition.setUserId(parentStudent.getParentUserId());
					parentCondition.setIsDelete(false);
					List<Parent> pList = this.parentService.findParentByCondition(parentCondition);
					if(pList.size() > 0) {
						for(Parent parent : pList) {
							parentList.add(parent);
						}
					}
				}
				teamStudent.setParentList(parentList);
			}
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("teamStudentVos", teamStudentVos);
		return new ModelAndView(viewPath, model.asMap());
	}



	@RequestMapping(value = "/indexStu")
	public ModelAndView indexStu(
			@CurrentUser UserInfo user,
			@RequestParam(value = "systemId") Integer systemId,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TeamStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		List<TeamStudentVo> teamStudentVos = null;
		//这些修改 对这里的功能没有任何影响，如果对其他功能有影响  请按照上面的恢复
		if ("list".equals(sub)) {
			viewPath = "/schoolBusSystems/schoolBusMangement/teamStudent/list";
			teamStudentVos = this.teamStudentService.findTeamStudentByConditionStudentSchoolBus(condition, page, order);
			for(TeamStudentVo teamStudent : teamStudentVos) {
				ParentStudentCondition psCondition = new ParentStudentCondition();
				psCondition.setStudentUserId(teamStudent.getUserId());
				psCondition.setRank("1");
				psCondition.setIsDelete(false);
				List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(psCondition);
				List<Parent> parentList = new ArrayList<Parent>();
				for(ParentStudent parentStudent : psList) {
					ParentCondition parentCondition = new ParentCondition();
					parentCondition.setUserId(parentStudent.getParentUserId());
					parentCondition.setIsDelete(false);
					List<Parent> pList = this.parentService.findParentByCondition(parentCondition);
					if(pList.size() > 0) {
						for(Parent parent : pList) {
							parentList.add(parent);
						}
					}
				}
				teamStudent.setParentList(parentList);
			}
		} else {
			viewPath = "/schoolBusSystems/schoolBusMangement/teamStudent/index";
		}
		model.addAttribute("teamStudentVos", teamStudentVos);
		model.addAttribute("systemId", systemId);
		return new ModelAndView(viewPath, model.asMap());
	}


	@RequestMapping(value = "/chroseIndexStu")
	public ModelAndView chroseIndexStu(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") TeamStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
//		conditionFilter(user, condition);
		order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
		condition.setSchoolId(user.getSchoolId());
		List<TeamStudentVo> teamStudentVos = null;
		//于2016-1-21 将查询放入list中 避免首次进入的时候查询了两次  另外XML中findTeamStudentByConditionStudent把查询列stu.user_id去掉了，因为在s.*中已包含user_id，还学校条件和学年条件放在了前面，之前放在最后面  查询效率偏低
		//这些修改 对这里的功能没有任何影响，如果对其他功能有影响  请按照上面的恢复
		if ("list".equals(sub)) {
			viewPath = "/schoolBusSystems/schoolBusMangement/teamStudent/list";
			teamStudentVos = this.teamStudentService.findTeamStudentByConditionStudent(condition, page, order);
			for(TeamStudentVo teamStudent : teamStudentVos) {
				ParentStudentCondition psCondition = new ParentStudentCondition();
				psCondition.setStudentUserId(teamStudent.getUserId());
				psCondition.setRank("1"); //0--普通；1--主监护人
				psCondition.setIsDelete(false);
				List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(psCondition);
				List<Parent> parentList = new ArrayList<Parent>();
				for(ParentStudent parentStudent : psList) {
					ParentCondition parentCondition = new ParentCondition();
					parentCondition.setUserId(parentStudent.getParentUserId());
					parentCondition.setIsDelete(false);
					List<Parent> pList = this.parentService.findParentByCondition(parentCondition);
					if(pList.size() > 0) {
						for(Parent parent : pList) {
							parentList.add(parent);
						}
					}
				}
				teamStudent.setParentList(parentList);
			}
		} else {
			viewPath = "/schoolBusSystems/schoolBusMangement/teamStudent/index";
		}
		model.addAttribute("teamStudentVos", teamStudentVos);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<TeamStudent> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") TeamStudentCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.teamStudentService.findTeamStudentByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(TeamStudent teamStudent, @CurrentUser UserInfo user) {
		
		teamStudent = this.teamStudentService.add(teamStudent);
		return teamStudent != null ? new ResponseInfomation(teamStudent.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		TeamStudent teamStudent = this.teamStudentService.findTeamStudentById(id);
		model.addAttribute("teamStudent", teamStudent);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		TeamStudent teamStudent = this.teamStudentService.findTeamStudentById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("teamStudent", teamStudent);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, TeamStudent teamStudent) {
		if (teamStudent != null) {
			teamStudent.setId(id);
		}
		try {
			this.teamStudentService.remove(teamStudent);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, TeamStudent teamStudent) {
		teamStudent.setId(id);
		teamStudent = this.teamStudentService.modify(teamStudent);
		return teamStudent != null ? new ResponseInfomation(teamStudent.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	@RequestMapping(value = "/downLoadTeamStudent")
	public void downLoadTeamStudent(
			HttpServletRequest request,
			HttpServletResponse response,
			@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "name", required = false) String name,
			@ModelAttribute("condition") TeamStudentCondition condition) {
		ParseConfig config = SzxyExcelTookit.getConfig("downLoadTeamStudent");
		List<Object> list = new ArrayList<Object>();
		
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		condition.setSchoolYear(schoolYear);
		condition.setGradeId(gradeId);
		condition.setTeamId(teamId);
//		try {
//			name = new String((request.getParameter("name")).getBytes("iso-8859-1"), "utf-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		condition.setName(name);
		List<TeamStudentVo> VoList = this.teamStudentService.findTeamStudentByConditionStudent(condition, null, null);
		for(TeamStudentVo teamStudent : VoList) {
			List<Parent> parentList = new ArrayList<Parent>();
			ParentStudentCondition psCondition = new ParentStudentCondition();
			psCondition.setStudentUserId(teamStudent.getUserId());
			psCondition.setRank("1"); //0--普通；1--主监护人
			psCondition.setIsDelete(false);
			List<ParentStudent> psList = this.parentStudentService.findParentStudentByCondition(psCondition);
			for(ParentStudent parentStudent : psList) {
				ParentCondition parentCondition = new ParentCondition();
				parentCondition.setUserId(parentStudent.getParentUserId());
				parentCondition.setIsDelete(false);
				List<Parent> tempList = this.parentService.findParentByCondition(parentCondition);
				if(tempList.size() > 0) {
					for(Parent parent : tempList) {
						parentList.add(parent);
					}
				}
			}
			if(parentList.size() > 0) {
				teamStudent.setParentName(parentList.get(0).getName());
				teamStudent.setParentMobile(parentList.get(0).getMobile());
			}
		}
		
		String[] titles = {"用户名", "学籍号", "姓名", "性别", "手机号码", "职务", "家长姓名", "家长手机号码"};
		config.setTitles(titles);
		config.setSheetTitle("班级学生信息表");
		String fileName = "班级学生信息表.xls";
		
		try {
			for(TeamStudentVo vo : VoList) {
				list.add(vo);
			}
			SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	private void conditionFilter(UserInfo user, TeamStudentCondition condition) {
		
	}
}
