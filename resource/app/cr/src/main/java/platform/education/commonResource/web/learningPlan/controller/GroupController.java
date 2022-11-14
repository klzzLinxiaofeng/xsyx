package platform.education.commonResource.web.learningPlan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.dao.Order;

import org.springframework.beans.BeanUtils;

import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.PjGroup;
import platform.education.generalTeachingAffair.model.PjGroupStudent;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.PjGroupService;
import platform.education.generalTeachingAffair.service.PjGroupStudentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.PjGroupCondition;
import platform.education.generalTeachingAffair.vo.PjGroupStudentCondition;
import platform.education.generalTeachingAffair.vo.PjGroupStudentVo;
import platform.education.generalTeachingAffair.vo.PjGroupStudentsVo;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;

/**
 * 分组控制器
 * @author zhenxinghui
 * 2018-11-6
 */
@Controller
@RequestMapping("/learningPlan")
public class GroupController {
	
	@Autowired
    @Qualifier("pjGroupService")
	private PjGroupService pjGroupService;
	
	@Autowired
	@Qualifier("pjGroupStudentService")
	private PjGroupStudentService pjGroupStudentService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	private final static String viewBasePath = "learningplan/group";
	
	@RequestMapping(value = "/group")
	public Object group() {
		ModelAndView model = new ModelAndView();
		model.setViewName(structurePath("/lp_index"));
		return model;
	}
	
	/**
	 * 获取分组信息
	 * @param user
	 * @param gradeId
	 * @param teamId
	 * @param groupType
	 * @return
	 */
	@RequestMapping(value = "/getGroup", method = RequestMethod.POST)
	public ModelAndView getGroup(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="gradeId", required=false) Integer gradeId,
			@RequestParam(value="teamId", required=false) Integer teamId,
			@RequestParam(value="groupType", required=false, defaultValue="1") Integer groupType) {
		//获取改用户的组别信息
		PjGroupCondition pjGroupCondition = new PjGroupCondition();
		pjGroupCondition.setSchoolId(user.getSchoolId());
		pjGroupCondition.setGradeId(gradeId);
		pjGroupCondition.setTeamId(teamId);
		pjGroupCondition.setTeacherId(user.getTeacherId());
		pjGroupCondition.setGroupType(groupType);
		pjGroupCondition.setIsDeleted(false);
		List<PjGroup> groupList = pjGroupService.findPjGroupByCondition(pjGroupCondition);
		model.addAttribute("groupList", groupList);
		
		return new ModelAndView(structurePath("/group_list"), model.asMap());
	}
	
	/**
	 * 获取分组信息Json
	 * @param user
	 * @param gradeId
	 * @param teamId
	 * @param groupType
	 * @return
	 */
	@RequestMapping(value = "/getGroupJson", method = RequestMethod.POST)
	@ResponseBody
	public Object getGroupJson(@CurrentUser UserInfo user,
			@RequestParam(value="gradeId", required=false) Integer gradeId,
			@RequestParam(value="teamId", required=false) Integer teamId,
			@RequestParam(value="groupType", required=false, defaultValue="1") Integer groupType) {
		List<String> data = new ArrayList<String>();
		//获取改用户的组别信息
		PjGroupCondition pjGroupCondition = new PjGroupCondition();
		pjGroupCondition.setSchoolId(user.getSchoolId());
		pjGroupCondition.setGradeId(gradeId);
		pjGroupCondition.setTeamId(teamId);
		pjGroupCondition.setTeacherId(user.getTeacherId());
		pjGroupCondition.setGroupType(groupType);
		pjGroupCondition.setIsDeleted(false);
		List<PjGroup> groupList = pjGroupService.findPjGroupByCondition(pjGroupCondition);
		
		for(PjGroup g : groupList) {
			
			PjGroupStudentCondition pjGroupStudentCondition = new PjGroupStudentCondition();
			pjGroupStudentCondition.setGroupId(g.getId());
			List<PjGroupStudent> groupStudentList = pjGroupStudentService.findPjGroupStudentByCondition(pjGroupStudentCondition);
			
			for(PjGroupStudent gs : groupStudentList) {
				if(gs.getIsLeader()) {
					data.add(studentService.findStudentById(gs.getStudentId()).getName()+"-"+"分组 "+gs.getGroupNumber());
				}
			}
		}
		return data;
	}
	
	/**
	 * 根据组ID获取分组
	 * @param user
	 * @param model
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/getStudentGroup", method = RequestMethod.POST)
	public ModelAndView getStudentGroup(@CurrentUser UserInfo user, Model model,
			@RequestParam(value="groupId", required=false) Integer groupId) {
		if(groupId != null) {
			
			PjGroupCondition pjGroupCondition = new PjGroupCondition();
			pjGroupCondition.setId(groupId);
			pjGroupCondition.setIsDeleted(false);
			List<PjGroup> groupList = pjGroupService.findPjGroupByCondition(pjGroupCondition);
			
			PjGroupStudentCondition pjGroupStudentCondition = new PjGroupStudentCondition();
			pjGroupStudentCondition.setGroupId(groupList.get(0).getId());
			pjGroupStudentCondition.setIsDeleted(false);
			List<PjGroupStudentVo> groupStudentList = pjGroupStudentService.findPjGroupStudentVoByCondition(pjGroupStudentCondition, null, Order.desc("is_leader"));
			
			List<PjGroupStudentVo> newGroupStudentList = new ArrayList<PjGroupStudentVo>();
			//获取第一个分组方案的信息
			for(int i=1;i<=groupList.get(0).getGroupCount();i++) {//分组数量按分组方案定义的数量为准
				//存在的所有分组
				PjGroupStudentVo vo = new PjGroupStudentVo();
				List<Integer> num = new ArrayList<Integer>();
				
				List<PjGroupStudentsVo> students = new ArrayList<PjGroupStudentsVo>();
				
				if(groupStudentList.size()>0) {//如果集合为0，该分组方案没有分组，就加一个空的分组
					for(PjGroupStudentVo pj : groupStudentList) {//循环已存在的小组
						if(pj.getGroupNumber().equals(i)) {
							//把学生对应的学生放到该组中
							PjGroupStudentsVo vos = new PjGroupStudentsVo();
							vos.setStudentId(pj.getStudentId());
							vos.setStudentName(pj.getStudentName());//studentService.findStudentById(pj.getStudentId()).getName());
							students.add(vos);
							if(!num.contains(i)) {//判断小组是否存在
								num.add(pj.getGroupNumber());//如果存在了，就把这个组存在一个集合中
								BeanUtils.copyProperties(pj, vo);
							}else {
								vo.setGroupId(groupList.get(0).getId());
								vo.setGroupNumber(i);
							}
						}else {
							vo.setGroupId(groupList.get(0).getId());
							vo.setGroupNumber(i);
						}
					}
				}else {
					vo.setGroupId(groupList.get(0).getId());
					vo.setGroupNumber(i);
				}
				
				vo.setStudent(students);
				newGroupStudentList.add(vo);
			}
			
			model.addAttribute("newGroupStudentList", newGroupStudentList);
		}
		
		return new ModelAndView(structurePath("/group_student_list"), model.asMap());
	}
	
	@RequestMapping(value = "/saveGroup", method = RequestMethod.POST)
	@ResponseBody
	public String saveGroup(@CurrentUser UserInfo user, 
			@RequestParam(value="groupId", required=false) Integer groupId, 
			@RequestParam(value="groupName", required=false) String groupName, 
			@RequestParam(value="gradeId", required=false) Integer gradeId, 
			@RequestParam(value="teamId", required=false) Integer teamId) {
		if(groupId==null) {
			PjGroup pjGroup = new PjGroup();
			pjGroup.setId(groupId);
			pjGroup.setSchoolId(user.getSchoolId());
			pjGroup.setGradeId(gradeId);
			pjGroup.setTeamId(teamId);
			pjGroup.setTeacherId(user.getTeacherId());
			pjGroup.setName(groupName);
			pjGroup.setGroupType(1);
			pjGroup.setGroupCount(10);
			pjGroup.setIsDeleted(false);
			pjGroupService.add(pjGroup);
		}else {
			PjGroup pjGroup = pjGroupService.findPjGroupById(groupId);
			pjGroup.setName(groupName);
			pjGroupService.modify(pjGroup);
		}
		return "success";
	}
	
	@RequestMapping(value = "/saveStudentGroup", method = RequestMethod.POST)
	@ResponseBody
	public String saveStudentGroup(@CurrentUser UserInfo user,
			@RequestParam(value="gradeId", required=false) Integer gradeId,
			@RequestParam(value="teamId", required=false) Integer teamId,
			@RequestParam(value="groupId", required=false) Integer groupId,
			@RequestParam(value="groupName", required=false) String groupName,
			@RequestParam(value="groupList", required=false) String groupList) {
		//更新一下分组方案名
		PjGroup pjGroup = new PjGroup();
		if(groupId != null) {
			PjGroup g = new PjGroup();
			g.setId(groupId);
			g.setName(groupName);
			g.setIsDeleted(false);
			pjGroup = pjGroupService.modify(g);
		}else {
			PjGroupCondition pjGroupCondition = new PjGroupCondition();
			pjGroupCondition.setGradeId(gradeId);
			pjGroupCondition.setTeamId(teamId);
			pjGroupCondition.setName(groupName);
			pjGroupCondition.setIsDeleted(false);
			List<PjGroup> grouplist = pjGroupService.findPjGroupByCondition(pjGroupCondition);
			if(grouplist.size()==0) {
				PjGroup g = new PjGroup();
				g.setSchoolId(user.getSchoolId());
				g.setGradeId(gradeId);
				g.setTeamId(teamId);
				g.setTeacherId(user.getTeacherId());
				g.setName("默认分组");
				g.setGroupType(1);
				g.setGroupCount(10);
				g.setIsDeleted(false);
				pjGroup = pjGroupService.add(g);
			}
		}
		
		//保存分组
		String data = groupList.substring(1, groupList.length()-1);
		String studentIdList[] = data.split(",");
		
		if(studentIdList.length>0) {
			
			for(int i=0;i<studentIdList.length;i++) {
				if(!studentIdList[i].equals("\"\"")) {
					String student[] = studentIdList[i].substring(1, studentIdList[i].length()-2).split("-");
					if(student.length>0) {
						for(int j=0;j<student.length;j++) {
							PjGroupStudentCondition pjGroupStudentCondition = new PjGroupStudentCondition();
							pjGroupStudentCondition.setGroupId(pjGroup.getId());
//							pjGroupStudentCondition.setGroupNumber(i+1);//需要删除该学生的其他小组信息，所以取消此筛选条件  吴鸿乔-2019/01/16
							pjGroupStudentCondition.setStudentId(Integer.parseInt(student[j]));
							pjGroupStudentCondition.setUserId(studentService.findStudentById(Integer.parseInt(student[j])).getUserId());
							pjGroupStudentCondition.setIsDeleted(false);
							List<PjGroupStudent> list = pjGroupStudentService.findPjGroupStudentByCondition(pjGroupStudentCondition);
								PjGroupStudent pjGroupStudent = new PjGroupStudent();
								if(j==0) {
									pjGroupStudent.setIsLeader(true);
								}else {
									pjGroupStudent.setIsLeader(false);
								}
								pjGroupStudent.setGroupId(pjGroup.getId());
								pjGroupStudent.setGroupNumber(i+1);
								pjGroupStudent.setStudentId(Integer.parseInt(student[j]));
								pjGroupStudent.setUserId(studentService.findStudentById(Integer.parseInt(student[j])).getUserId());
								pjGroupStudent.setIsDeleted(false);
							if(list.size()>=0) {
								for(PjGroupStudent s : list){
									if((i+1)==s.getGroupId()){
										//学生已在该小组
										pjGroupStudent.setId(s.getId());
										pjGroupStudentService.modify(pjGroupStudent);
									}else {
										//删除该学生在其他小组的记录
										PjGroupStudent deleter = new PjGroupStudent();
										deleter.setId(s.getId());
										deleter.setIsDeleted(true);
										pjGroupStudentService.modify(deleter);
									}
								}
								if(pjGroupStudent.getId() == null){//未修改，添加
									pjGroupStudentService.add(pjGroupStudent);
								}
							}
						}
					}
				}
			}
		}
		
		return "success";
	}
	
	/**
	 * 删除分组方案，isDelete=1
	 * @param user
	 * @param groupId
	 * @return
	 */
	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	@ResponseBody
	public String deleteGroup(@CurrentUser UserInfo user, 
			@RequestParam(value="groupId", required=false) Integer groupId) {
		PjGroup pjGroup = pjGroupService.findPjGroupById(groupId);
		pjGroup.setIsDeleted(true);
		pjGroupService.modify(pjGroup);
		
		PjGroupStudentCondition pjGroupStudentCondition = new PjGroupStudentCondition();
		pjGroupStudentCondition.setGroupId(groupId);
		List<PjGroupStudent> groupStudentList = pjGroupStudentService.findPjGroupStudentByCondition(pjGroupStudentCondition);
		for(PjGroupStudent g : groupStudentList) {
			g.setIsDeleted(true);
			pjGroupStudentService.modify(g);
		}
		return "success";
	}
	
	/**
	 * 点击加号获取学生
	 * @param user
	 * @param gradeId
	 * @param teamId
	 * @return
	 */
	@RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> getStudentList(@CurrentUser UserInfo user,
			@RequestParam(value="gradeId", required=false) Integer gradeId,
			@RequestParam(value="teamId", required=false) Integer teamId){
		Map<String, Integer> data = new HashMap<String, Integer>();
		
		TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
		teamStudentCondition.setGradeId(gradeId);
		teamStudentCondition.setTeamId(teamId);
		teamStudentCondition.setIsDelete(false);
		teamStudentCondition.setInState(true);
		List<TeamStudent> teamStudentList = teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
		for(TeamStudent t : teamStudentList) {
			data.put(t.getName(), t.getStudentId());
		}
		return data;
	}
	
	@RequestMapping(value = "/test")
	public String test() {
		String path = structurePath("/test");
		return path;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
