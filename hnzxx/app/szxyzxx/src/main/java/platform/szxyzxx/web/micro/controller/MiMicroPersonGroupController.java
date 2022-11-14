package platform.szxyzxx.web.micro.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.education.micro.model.MiMicroPersonGroup;
import platform.education.micro.model.MiMicroPersonGroupUserId;
import platform.education.micro.service.MiMicroPersonGroupService;
import platform.education.micro.service.MiMicroPersonGroupUserIdService;
import platform.education.micro.vo.MiMicroPersonGroupCondition;
import platform.education.micro.vo.MiMicroPersonGroupVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@Controller
@RequestMapping("/micropersongroup")
public class MiMicroPersonGroupController extends BaseController {

	private final static String viewBasePath = "/micro/persongroup";

	@Autowired
	@Qualifier("miMicroPersonGroupService")
	private MiMicroPersonGroupService miMicroPersonGroupService;
	
	@Resource
	private MiMicroPersonGroupUserIdService MiMicroPersonGroupUserIdService;

	@RequestMapping(value = "/index")
	public ModelAndView index(@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") MiMicroPersonGroupCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		order.setProperty(order.getProperty() != null ? order.getProperty(): "create_date");
		condition.setSchoolId(user.getSchoolId());
		condition.setUserId(user.getId());
		List<MiMicroPersonGroupVo> items = miMicroPersonGroupService.findByCount(condition, page, order);
		if(items.size()>0){
			for(MiMicroPersonGroupVo item:items){
				String groupPersonName = "";
				List<MiMicroPersonGroupUserId>mupList = this.MiMicroPersonGroupUserIdService.findByMiMicroPersonGroupId(item.getId());
				if(mupList.size()>0){
					for(MiMicroPersonGroupUserId mup :mupList){
						groupPersonName += this.studentService.findStudentByUserId(mup.getUserId()).getName()+" ， ";
					}
				}
				
				item.setGroupPersonName(groupPersonName==""?"":groupPersonName.substring(0, groupPersonName.length()-2));
			}
		}
		
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		model.addAttribute("items", items);
		model.addAttribute("classGradeMap",getStudent(user));
		return new ModelAndView(viewPath, model.asMap());
	}

	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<MiMicroPersonGroup> jsonList(@CurrentUser UserInfo user,
			@ModelAttribute("condition") MiMicroPersonGroupCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		page = usePage ? page : null;
		return this.miMicroPersonGroupService
				.findMiMicroPersonGroupByCondition(condition, page, order);
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator(@CurrentUser UserInfo user,Model model) {
		model.addAttribute("teamStudents",getStudent(user));
		return new ModelAndView(structurePath("/input"));
	}

	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(MiMicroPersonGroup miMicroPersonGroup,@RequestParam(value="ids[]")Integer[] ids,
			@CurrentUser UserInfo user) {
		Integer id = 0;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i]!=null) {
				id=ids[i];
			}
		}
		Student student = studentService.findOfUser(user.getSchoolId(), id);
		Team team = teamService.findTeamById(student.getTeamId());
			miMicroPersonGroup.setGradeId(team.getGradeId());
			miMicroPersonGroup.setUserId(user.getId());
			miMicroPersonGroup.setSchoolId(user.getSchoolId());
			miMicroPersonGroup.setSchoolTermCode(user.getSchoolTermCode());
			miMicroPersonGroup.setSchoolYear(user.getSchoolYear());
			MiMicroPersonGroup add = this.miMicroPersonGroupService.addPersonGroup(miMicroPersonGroup, ids);
		return add != null ? new ResponseInfomation(
				add.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		MiMicroPersonGroup miMicroPersonGroup = miMicroPersonGroupService.findMiMicroPersonGroupById(id);
		model.addAttribute("miMicroPersonGroup", miMicroPersonGroup);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		MiMicroPersonGroup miMicroPersonGroup = this.miMicroPersonGroupService
				.findMiMicroPersonGroupById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("miMicroPersonGroup", miMicroPersonGroup);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id,
			MiMicroPersonGroup miMicroPersonGroup) {
		if (miMicroPersonGroup != null) {
			miMicroPersonGroup.setId(id);
		}
		try {
			this.miMicroPersonGroupService.remove(miMicroPersonGroup);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
			MiMicroPersonGroup miMicroPersonGroup) {
		miMicroPersonGroup.setId(id);
		miMicroPersonGroup = this.miMicroPersonGroupService
				.modify(miMicroPersonGroup);
		return miMicroPersonGroup != null ? new ResponseInfomation(
				miMicroPersonGroup.getId(), ResponseInfomation.OPERATION_SUC)
				: new ResponseInfomation();
	}

	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}

	/*private Map getClassGradeMap(UserInfo user, boolean includeSubject,
			boolean includeSameClass) {
		Map classGradeMap = new LinkedHashMap();
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(),
				user.getId());
		if (teacher != null && schoolTermCurrent != null) {
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeacherId(teacher.getId());
			// 1 班主任 2 任课教师
			teamTeacherCondition.setType(2);
			teamTeacherCondition.setSchoolYear(schoolTermCurrent
					.getSchoolYear());
			List<TeamTeacher> teamTeacherList = teamTeacherService
					.findTeamTeacherByCondition(teamTeacherCondition, null,
							Order.asc("grade_id"));
			for (TeamTeacher tt : teamTeacherList) {
				Grade grade = this.gradeService.findGradeById(tt.getGradeId());
				if (grade != null) {
					List<Map> classList = new ArrayList();
					String viewName = grade.getName() + "&&"
							+ grade.getUniGradeCode();
					if (classGradeMap.containsKey(viewName)) {
						classList = (List<Map>) classGradeMap.get(viewName);
					}
					Team team = this.teamService.findTeamById(tt.getTeamId());
					if (team != null) {
						Map map = new HashMap();
						String classSubjectName = team.getName();
						if (includeSubject) {
							classSubjectName = classSubjectName + "   ["
									+ tt.getSubjectName() + "]" + "&&"
									+ tt.getSubjectCode();
						}
						map.put(team.getId(), classSubjectName);
						if (includeSameClass) {
							classList.add(map);
						} else if (!classList.contains(map)) {
							classList.add(map);
						}
					}
					classGradeMap.put(viewName, classList);
				}
			}
		}
		return classGradeMap;
	}*/

	private List<Map<String, List<Student>>> getStudent(UserInfo user) {
		List<Map<String, List<Student>>> list = new ArrayList<Map<String, List<Student>>>();
		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(),user.getId());
		if (teacher != null && schoolTermCurrent != null) {
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setTeacherId(teacher.getId());
			//teamTeacherCondition.setType(2);
			teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
			List<TeamTeacherVo> teamTeacherList = teamTeacherService.findTeamTeacherVoByGroupBy(teamTeacherCondition);
			for (TeamTeacherVo teamTeacher : teamTeacherList) {
				Team team = this.teamService.findTeamById(teamTeacher.getTeamId());
				String teamName = teamTeacher.getTeamName() + "&&" + teamTeacher.getGradeId() + "&&" + teamTeacher.getTeamId();
				if (team != null) {
					List<Student> students = this.studentService.findStudentOfTeam(teamTeacher.getTeamId());
						Map<String, List<Student>> map = new HashMap<String, List<Student>>();
						map.put(teamName, students);
						list.add(map);
				}
			}
		}
		return list;
	}
}
