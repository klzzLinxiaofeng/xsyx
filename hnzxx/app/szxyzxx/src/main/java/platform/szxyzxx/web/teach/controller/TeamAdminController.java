package platform.szxyzxx.web.teach.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
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

import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.TeamAdminService;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;
import platform.education.generalTeachingAffair.vo.TeamAdminCondition;
import platform.education.generalTeachingAffair.vo.TeamAdminVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;




@Controller
@RequestMapping("/teach/teamAdmin")
public class TeamAdminController  extends BaseController{
	
	private final static String viewBasePath = "/teach/teamAdmin";

	private final static int fullCount = 99;

	@Autowired
	@Qualifier("teamAdminService")
	private TeamAdminService teamAdminService;

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			Model model) {
		String viewPath = null;
		Integer schoolId = user.getSchoolId();
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
			List<Team> teamList = teamService.findTeamOfGradeAndSchool(gradeId, schoolId);
			List<TeamAdminVo> teamAdminVoList = teamAdminService.findByGradeIdAndTeamId(gradeId, null);

			List<Object> list = new ArrayList<Object>();
			Map<String, Object> map = null;
			List<TeamAdminVo> teamAdminList  = null;
			for (Team team : teamList) {
				Integer teamId = team.getId();
				teamAdminList = new ArrayList<TeamAdminVo>();
				for (TeamAdminVo vo : teamAdminVoList) {
					if (teamId.equals(vo.getTeamId())) {
						teamAdminList.add(vo);
					}
				}

				map = new HashMap<String, Object>();
				map.put("teamId", teamId);
				map.put("teamName", team.getName());
				map.put("teamNumber", team.getTeamNumber());
				map.put("teacherList", teamAdminList);
				list.add(map);
			}
			model.addAttribute("list", list);

		} else {
			viewPath = structurePath("/index");
			String schoolYear = user.getSchoolYear();
			if (schoolYear != null && !"".equals(schoolYear)) {
				List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
				model.addAttribute("gradeList", gradeList);
			}
		}
		return new ModelAndView(viewPath, model.asMap());
	}

	//新增页
	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user,Model model) {
		List<Object> list = teamService.findAllTeamOfSchool(user.getSchoolId(), user.getSchoolYear());
		List<Integer> unableTeams = teamAdminService.findFullConfigureTeams(user.getSchoolId(), user.getSchoolYear(), fullCount);
		list = getUnionData(list, null, unableTeams);
		model.addAttribute("list", list);
		model.addAttribute("unableTeams",  JSONArray.fromObject(unableTeams).toString());
		return new ModelAndView(structurePath("/input"));
	}

	//保存 新增数据
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(@CurrentUser UserInfo user, Integer teacherId, String addIds) {
		try {
			Teacher teacher = teacherService.findTeacherById(teacherId);
			addTeamAdmin(addIds, teacher);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	//教师选择页
	@RequestMapping(value = "/teacher/select")
	public ModelAndView teacherSelectPage(@CurrentUser UserInfo user,Model model) {
		List<Integer> unableTeams = teamAdminService.findFullConfigureTeams(user.getSchoolId(), user.getSchoolYear(), fullCount);
		List<DepartmentTeacherVo> teacherList = departmentTeacherService.findDepartmentTeacherBySchool(user.getSchoolId());
		model.addAttribute("unableTeams",  JSONArray.fromObject(unableTeams).toString());
		model.addAttribute("teacherList", JSONArray.fromObject(teacherList).toString());
		return new ModelAndView(structurePath("/teacherSelect"));
	}

	//获取教师所管的班级
	@RequestMapping(value = "/teacher/team")
	@ResponseBody
	public Object getTeamsOfTeamAdmin(@CurrentUser UserInfo user, @RequestParam(value = "id", required = true) Integer teacherId){
		List<TeamAdminVo> teamAdminList = teamAdminService.findByTeacherId(user.getSchoolId(), user.getSchoolYear(), teacherId);
		List<Integer> teams = getTeacherTeams(teamAdminList);
		return new ResponseInfomation(teams, ResponseInfomation.OPERATION_SUC);
	}


	//一进入，点击“+”时查询
	@RequestMapping(value = "/dept/tree/json")
	@ResponseBody
	public Object getDepartmentList(@CurrentUser UserInfo user, @RequestParam(value = "parentId", required = false)Integer parentId) {
		Integer schoolId = user.getSchoolId();
		if (parentId == null) {
			parentId = 0;
		}
		DepartmentCondition condition = new DepartmentCondition();
		condition.setSchoolId(schoolId);
		condition.setParentId(parentId);
		condition.setIsDelete(false);
		List<Department> departmentList = departmentService.findDepartmentByCondition(condition, null, null);
		if ((departmentList == null || departmentList.size() == 0) && parentId != 0) {
			List<DepartmentTeacher> teacherList = departmentTeacherService.findByDepartmentId(parentId);
			return new ResponseInfomation(teacherList, "teacher");
		}
		return new ResponseInfomation(departmentList, "department");
	}

	/**
	 * 个人管理班级弹窗
	 * @param teacherId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer teacherId, Model model) {
		Integer schoolId = user.getSchoolId();
		String schoolYear = user.getSchoolYear();
		Teacher teacher = teacherService.findTeacherById(teacherId);
		List<Object> list = teamService.findAllTeamOfSchool(schoolId, schoolYear);
		List<TeamAdminVo> teamAdminList = teamAdminService.findByTeacherId(schoolId, schoolYear, teacherId);
		List<Integer> unableTeams = teamAdminService.findFullConfigureTeams(schoolId, schoolYear, fullCount);

		list = getUnionData(list, teamAdminList, unableTeams);
		List<Integer> teams = getTeacherTeams(teamAdminList);
		model.addAttribute("teacher", teacher);
		model.addAttribute("list", list);
		model.addAttribute("teams",  JSONArray.fromObject(teams).toString());
		model.addAttribute("unableTeams", unableTeams);
		return new ModelAndView(structurePath("/modifyPage"), model.asMap());
	}

	//将班级数据与 级长数据和超出限制的班级数据进行整合
	private List<Object> getUnionData(List<Object> gradeTeams, List<TeamAdminVo> teamAdminList, List<Integer> unableTeams){

		if ((teamAdminList != null && teamAdminList.size() > 0) || (unableTeams != null && unableTeams.size() > 0)) {
			List<Object> list = new ArrayList<>();

			for (Object gradeTeam : gradeTeams) {
				Map<String, Object> gradeMap = (Map<String, Object>) gradeTeam;
				List<Map<String, Object>> teamList = (List<Map<String, Object>>)gradeMap.get("teamList");

				List<Map<String, Object>> newTeams = new ArrayList<>();
				for (Map<String, Object> teamMap : teamList) {
					Integer teamId = (Integer) teamMap.get("teamId");
					if (teamAdminList != null && teamAdminList.size() > 0) {
						for (TeamAdminVo vo : teamAdminList) {
							if (vo.getTeamId().equals(teamId)) {
								teamMap.put("isChosen", true);
								break;
							}
						}
					}
					if (unableTeams != null && unableTeams.size() > 0) {
						for (Integer unableTeam : unableTeams) {
							if (unableTeam.intValue() == teamId.intValue()) {
								teamMap.put("isOver", true);
								break;
							}
						}
					}
					newTeams.add(teamMap);
				}
				gradeMap.put("teamList", newTeams);
				list.add(gradeMap);
			}
			return list;

		} else {
			return gradeTeams;
		}
	}

	private List<Integer> getTeacherTeams(List<TeamAdminVo> teamAdminList) {
		List<Integer> list = new ArrayList<>(teamAdminList.size());
		for (TeamAdminVo vo : teamAdminList) {
			list.add(vo.getTeamId());
		}
		return list;
	}

	/**
	 * 修改
	 * @param teacherId
	 * @param addIds
	 * @param modifyIds
	 * @return
	 */
	@RequestMapping(value = "/modify")
	@ResponseBody
	public ResponseInfomation edit(
			@RequestParam(value = "id", required = false) Integer teacherId,
			@RequestParam(value = "addIds", required = false) String addIds,
			@RequestParam(value = "modifyIds", required = false) String modifyIds) {
		try {
			Teacher teacher = teacherService.findTeacherById(teacherId);

			//修改数据结构 teamId,teamId,teamId
			if (modifyIds != null && !"".equals(modifyIds)) {
                String[] teams = modifyIds.split(",");
                Integer[] teamIds = new Integer[teams.length];
                for (int i = 0; i < teams.length; i++) {
                    teamIds[i] = Integer.valueOf(teams[i]);
                }
                teamAdminService.removeBatch(teacherId, teamIds);
            }

			//添加数据结构 gradeId:teamId,teamId;gradeId:teamId,teamId,teamId
			addTeamAdmin(addIds, teacher);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	private void addTeamAdmin(String addIds, Teacher teacher) {
		//添加数据结构 gradeId:teamId,teamId;gradeId:teamId,teamId,teamId
		TeamAdmin teamAdmin = null;
		if (addIds != null && !"".equals(addIds)) {
			String[] gradeTeams = addIds.split(";");
			for (String gradeTeam : gradeTeams) {
				String[] split = gradeTeam.split(":");

				Integer gradeId = Integer.valueOf(split[0]);
				String[] teams = split[1].split(",");
				Integer[] teamIds = new Integer[teams.length];
				for (int i = 0; i < teams.length; i++) {
					teamIds[i] = Integer.valueOf(teams[i]);
				}

				teamAdmin = new TeamAdmin();
				teamAdmin.setSchoolId(teacher.getSchoolId());
				teamAdmin.setGradeId(gradeId);
				teamAdmin.setTeacherId(teacher.getId());
				teamAdmin.setUserId(teacher.getUserId());
				teamAdmin.setName(teacher.getName());
				teamAdmin.setType(3);
				teamAdminService.addTeamAdminBatch(teamAdmin, teamIds);
			}
		}
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
