package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.VirtualTeamService;
import platform.education.generalTeachingAffair.service.VirtualTeamStudentService;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.VirtualTeamCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.*;


@Controller
@RequestMapping("/teach/virtualTeam")
public class VirtualTeamController extends BaseController {
	
	private final static String viewBasePath = "/teach/virtualTeam";
	
	@Autowired
	@Qualifier("virtualTeamService")
	private VirtualTeamService virtualTeamService;

	@Autowired
	@Qualifier("virtualTeamStudentService")
	private VirtualTeamStudentService virtualTeamStudentService;
	
	@RequestMapping(value = "/index")
	public ModelAndView index(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") VirtualTeamCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		List<Grade> gradeList = gradeService.findGradeBySchoolYear(user.getSchoolId(), user.getSchoolYear());
		model.addAttribute("gradeList", gradeList);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/index");
		}
		return new ModelAndView(viewPath, model.asMap());
	}


	/**
	 * 根据年级id获取行政班和虚拟班
	 * @param user
	 * @param gradeId	年级Id
	 * @param isAll		是否包含行政班，默认true
	 * @param excludeType	需排除的班级类型 0=无（默认值） 1=行政班 2=虚拟班
	 * @param excludeId		需排除的班级ID（行政班或虚拟班）
	 * @return
	 */
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public Object jsonList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId") Integer gradeId,
			@RequestParam(value = "isAll", defaultValue = "true") Boolean isAll,
			@RequestParam(value = "excludeType", defaultValue = "0") Integer excludeType,
			@RequestParam(value = "excludeId", required = false) Integer excludeId
	) {

		List<VirtualTeam> virtualTeamList = virtualTeamService.findByGradeId(gradeId);
		if (virtualTeamList != null && !virtualTeamList.isEmpty()) {
			if (excludeType == 2 && excludeId != null) {
				for (VirtualTeam virtualTeam : virtualTeamList) {
					if (virtualTeam.getId().equals(excludeId)) {
						virtualTeamList.remove(virtualTeam);
						break;
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("virtualTeam", virtualTeamList);
		if (isAll) {
			List<Team> teamList = teamService.findTeamOfGrade(gradeId);
			//排除选中班
			if (teamList != null && !teamList.isEmpty()) {
				if (excludeType == 1 && excludeId != null) {
					for (Team team : teamList) {
						if (team.getId().equals(excludeId)) {
							teamList.remove(team);
							break;
						}
					}
				}
				map.put("team", teamList);
			}
		}

		return map;
	}

	/**
	 * 根据班级id和类型 获取学生数据
	 * @param teamId 	班级id
	 * @param type		班级类型 1=行政班 2=虚拟班
	 * @return	学生列表，男女学生列表，总人数，男生数，女生数
	 */
	@RequestMapping(value = "/student/list", method = RequestMethod.GET)
	@ResponseBody
	public Object virtualTeamStudentList(
			@RequestParam(value = "teamId") Integer teamId,
			@RequestParam(value = "type", defaultValue = "1") Integer type) {
		List<Student> studentList = null;
		if (type == 1) {
			Team team = teamService.findTeamById(teamId);
			if (team == null) {
				return null;
			}
			StudentCondition condition = new StudentCondition();
			condition.setSchoolId(team.getSchoolId());
			condition.setTeamId(team.getId());
			condition.setIsDelete(false);
			studentList = studentService.findStudentByCondition(condition, null, null);
		} else if (type == 2) {
			studentList = virtualTeamStudentService.findStudentByVirtualTeamId(teamId);
		}
		List<Student> manList = new ArrayList<>();
		List<Student> womanList = new ArrayList<>();
		int totalNumber = 0;
		int manNumber = 0;
		int womanNumber = 0;
		if (studentList != null && !studentList.isEmpty()) {
			for (Student student : studentList) {
				String sex = student.getSex();
				if ("1".equals(sex)) {
					manList.add(student);
				} else if ("2".equals(sex)) {
					womanList.add(student);
				}
			}
			totalNumber = studentList.size();
			manNumber = manList.size();
			womanNumber = womanList.size();
		}
		Map<String, Object> map = new HashMap<>();
		map.put("studentList", studentList);
		map.put("manList", manList);
		map.put("womanList", womanList);
		map.put("totalNumber", totalNumber);
		map.put("manNumber", manNumber);
		map.put("womanNumber", womanNumber);
		return map;
	}

	/**
	 * 获取全校师生，姓名模糊查询
	 */
	@RequestMapping(value = "/student/list/all", method = RequestMethod.GET)
	@ResponseBody
	public Object studentListJson(
			@CurrentUser UserInfo user,
			@ModelAttribute("condition") StudentCondition condition) {
		condition.setSchoolId(user.getSchoolId());
		condition.setIsDelete(false);
		return studentService.findStudentByCondition(condition, null, null);
	}

	/**
	 * 检测学生是否已存在虚拟班
	 * @return	若存在，返回提示学生姓名
	 */
	@RequestMapping(value = "/check/student", method = RequestMethod.POST)
	@ResponseBody
	public Object checkStudentIsExist(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId") Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "studentIds") String studentIds,
			@RequestParam(value = "studentNames") String studentNames) {
		String[] sids = studentIds.split(",");
		String[] names = studentNames.split(",");
		Integer[] ids = new Integer[sids.length];
        for (int i = 0; i < sids.length; i++) {
            ids[i] = Integer.parseInt(sids[i]);
        }
		List<VirtualTeamStudent> studentList = virtualTeamStudentService.findByStudentIds(gradeId, null, ids);
		String message = "";
		if (studentList != null && !studentList.isEmpty()) {
			for (int i = 0; i < ids.length; i++) {
				for (VirtualTeamStudent student : studentList) {
					if (student.getStudentId().equals(ids[i]) && !student.getVirtualTeamId().equals(teamId)) {
//						message += student.getName();
						message += names[i];
					}
				}
			}
			if (message != "") {
				return new ResponseInfomation(message, ResponseInfomation.OPERATION_FAIL);
			}
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * 虚拟班级学生换进换出
	 * @param teamId		需保存的虚拟班级-右侧
	 * @param studentIds	需保存的虚拟班级学生
	 * @param outTeamId		换出班级-左侧
	 * @param outStudentIds	换出的学生
	 * @return
	 */
	@RequestMapping(value = "/studentInfo/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateStudentInfo(
			@RequestParam(value = "gradeId", required = true) Integer gradeId,
			@RequestParam(value = "teamId", required = true) Integer teamId,
			@RequestParam(value = "studentIds", required = false) String studentIds,
			@RequestParam(value = "outTeamId", required = false) Integer outTeamId,
			@RequestParam(value = "outStudentIds", required = false) String outStudentIds
	) {
		try {
			//从编辑班级中换出的学生，需当场保存才生效，切换班级无效
			saveOutStudents(gradeId, outTeamId, outStudentIds);
			//编辑班级，学生为空，清空班级学生；学生不为空，获取所在班级数据，跟原班级数据比较：
			//①已存在其他班级的（同年级的）删除，并加入新班级；②已在本班级的不动；③原班级其他学生则删除
			saveVirtualStudent(gradeId, teamId, studentIds);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	private void saveVirtualStudent(Integer gradeId, Integer teamId, String studentIds) {
		if (studentIds != null && !"".equals(studentIds)) {
			String[] sids = studentIds.split(",");
            Integer[] ids = new Integer[sids.length];
            for (int i = 0; i < sids.length; i++) {
                ids[i] = Integer.parseInt(sids[i]);
            }
			//已存在班级的学生
			List<VirtualTeamStudent> existStudents = virtualTeamStudentService.findByStudentIds(gradeId, null, ids);
			//原有班级的学生
			List<VirtualTeamStudent> oldStudents = virtualTeamStudentService.findByStudentIds(gradeId, teamId, null);

			List<VirtualTeamStudent> removeAndAddStudents = new ArrayList<>();  //学生列表中需删除并新增的学生①
			List<Integer> removeAndAddIds = new ArrayList<>();		//学生列表中需删除并新增的学生ID
			List<Integer> removeIds = new ArrayList<>();			//原班级需删除的学生
			List<Integer> addStudentIds = new ArrayList<>();		//新班级需新增的学生
			for (int i = 0; i < ids.length; i++) {
				Integer studentId = Integer.valueOf(ids[i]);
				boolean isAdd = true;
				for (VirtualTeamStudent existStudent : existStudents) {
					if (existStudent.getStudentId().equals(studentId)) {
						//studentId相同，teamId不同，需先删后增；teamId相同，不处理
						if (!existStudent.getVirtualTeamId().equals(teamId)) {
							removeAndAddIds.add(existStudent.getId());//删除原班级数据并添加进新班级
							removeAndAddStudents.add(existStudent);
                            addStudentIds.add(existStudent.getStudentId());
						}
						isAdd = false;
						break;
					}
				}
				if (isAdd) {
					addStudentIds.add(studentId);
				}
			}
//			addStudentIds.addAll(removeAndAddIds);		//合并新增

			for (VirtualTeamStudent oldStudent : oldStudents) {
				boolean isExist = false;
				for (Integer id : ids) {
					if (oldStudent.getStudentId().equals(id)) {
						isExist = true;
						break;
					}
				}
				if (!isExist) {
					removeIds.add(oldStudent.getId());
				}
			}

			if(removeAndAddIds.size()>0){
                virtualTeamStudentService.batchRemove(removeAndAddIds.toArray(new Integer[removeAndAddIds.size()]));
            }
            if(removeIds.size()>0){
                virtualTeamStudentService.batchRemove(removeIds.toArray(new Integer[removeIds.size()]));
            }

			if (!addStudentIds.isEmpty()) {
				List<VirtualTeamStudent> addList = new ArrayList<>();
				VirtualTeamStudent addStudent = null;
				Date now = new Date();
				for (Integer studentId : addStudentIds) {
					Student student = studentService.findStudentById(studentId);
					if (student != null) {
						addStudent = new VirtualTeamStudent();
						addStudent.setGradeId(gradeId);
						addStudent.setVirtualTeamId(teamId);
						addStudent.setStudentId(student.getId());
						addStudent.setUserId(student.getUserId());
						addStudent.setName(student.getName());
						addStudent.setInState(true);
						addStudent.setIsDeleted(false);
						addStudent.setCreateDate(now);
						addStudent.setModifyDate(now);
						addList.add(addStudent);
					}
				}
				virtualTeamStudentService.batchCreate(addList.toArray(new VirtualTeamStudent[addList.size()]));
			}

		} else {
			virtualTeamStudentService.batchRemoveByStudentIds(gradeId, teamId, null);
		}
	}

	//从被编辑的虚拟班级换出的学生 outTeamId，outStudentIds不为空
	private void saveOutStudents(Integer gradeId, Integer outTeamId, String outStudentIds) {
		if (outStudentIds != null && !"".equals(outStudentIds) && outTeamId != null) {
			String[] sids = outStudentIds.split(",");
            Integer[] outIds = new Integer[sids.length];
            for (int i = 0; i < sids.length; i++) {
                outIds[i] = Integer.parseInt(sids[i]);
            }
			List<VirtualTeamStudent> removeStudents = virtualTeamStudentService.findByStudentIds(gradeId, null, outIds);
			if (removeStudents != null && !removeStudents.isEmpty()) {
				int size = removeStudents.size();
				Date now = new Date();
				List<Integer> ids = new ArrayList<>(size);
				List<VirtualTeamStudent> list = new ArrayList<>(size);
				for (VirtualTeamStudent vts : removeStudents) {
					ids.add(vts.getId());

					vts.setId(null);
					vts.setVirtualTeamId(outTeamId);
					vts.setCreateDate(now);
					vts.setModifyDate(now);
					list.add(vts);
				}
				Integer[] removeIds = ids.toArray(new Integer[size]);
				VirtualTeamStudent[] addStudents = list.toArray(new VirtualTeamStudent[size]);
				virtualTeamStudentService.batchRemove(removeIds);
				virtualTeamStudentService.batchCreate(addStudents);
			}
		}
	}

	@RequestMapping(value = "/creator", method = RequestMethod.GET)
	public ModelAndView creator() {
		return new ModelAndView(structurePath("/input"));
	}

	/**
	 * 新建虚拟走班
	 * @param virtualTeam
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/creator", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation creator(VirtualTeam virtualTeam, @CurrentUser UserInfo user) {
		Integer gradeId = virtualTeam.getGradeId();
		String name = virtualTeam.getName().trim();
		boolean isRepeat = virtualTeamService.checkRepeatName(gradeId, name, null);
		if (isRepeat) {
			return new ResponseInfomation("NAME_REPEAT", ResponseInfomation.OPERATION_FAIL);
		}
		Grade grade = gradeService.findGradeById(gradeId);
		if (grade == null) {
			return new ResponseInfomation("NO_GRADE", ResponseInfomation.OPERATION_FAIL);
		}
		virtualTeam.setSchoolId(grade.getSchoolId());
		virtualTeam.setSchoolYear(grade.getSchoolYear());
		virtualTeam = this.virtualTeamService.add(virtualTeam);
		return virtualTeam != null ? new ResponseInfomation(virtualTeam.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}

	/**
	 * 删除虚拟走班
	 * @param id
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/delVirtualTeam", method = RequestMethod.POST)
	@ResponseBody
	public String delVirtualTeam(@RequestParam(value = "id", required = true) Integer id,
								 @CurrentUser UserInfo user) {
		try {
			VirtualTeam virtualTeam = new VirtualTeam();
			virtualTeam.setId(id);
			virtualTeam.setIsDeleted(true);
			this.virtualTeamService.modify(virtualTeam);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	/**
	 * 获取全校年级
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/getSchoolGrade")
	@ResponseBody
	public Object getSchoolGrade(@CurrentUser UserInfo user) {
		List<Grade> gradeList = gradeService.findGradeBySchoolYear(user.getSchoolId(), user.getSchoolYear());
		Map<Object, Integer> gradeMap = new LinkedHashMap<Object, Integer>();

		for (Grade grade : gradeList) {
			List<Team> list = teamService.findTeamOfGradeAndSchool(grade.getId(), user.getSchoolId());
			if(list.size()==0){
				continue;
			}
			gradeMap.put(grade.getName(), grade.getId());
		}
		return gradeMap;
	}

	@RequestMapping(value = "/editor", method = RequestMethod.GET)
	public ModelAndView editor(
			@RequestParam(value = "id", required = true) Integer id, Model model) {
		VirtualTeam virtualTeam = this.virtualTeamService.findVirtualTeamById(id);
		model.addAttribute("virtualTeam", virtualTeam);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}
	
	@RequestMapping(value = "/viewer", method = RequestMethod.GET)
	public ModelAndView viewer(
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		VirtualTeam virtualTeam = this.virtualTeamService.findVirtualTeamById(id);
		model.addAttribute("isCK", "disable");
		model.addAttribute("virtualTeam", virtualTeam);
		return new ModelAndView(structurePath("/input"), model.asMap());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable(value = "id") Integer id, VirtualTeam virtualTeam) {
		if (virtualTeam != null) {
			virtualTeam.setId(id);
		}
		try {
			this.virtualTeamService.remove(virtualTeam);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation edit(@PathVariable(value = "id") Integer id, VirtualTeam virtualTeam) {
		boolean isRepeat = virtualTeamService.checkRepeatName(virtualTeam.getGradeId(), virtualTeam.getName(), id);
		if (isRepeat) {
			return new ResponseInfomation("NAME_REPEAT", ResponseInfomation.OPERATION_FAIL);
		}
		virtualTeam.setId(id);
		virtualTeam = this.virtualTeamService.modify(virtualTeam);
		return virtualTeam != null ? new ResponseInfomation(virtualTeam.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
}
