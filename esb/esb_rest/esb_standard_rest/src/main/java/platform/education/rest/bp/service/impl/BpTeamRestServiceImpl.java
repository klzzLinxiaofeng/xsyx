package platform.education.rest.bp.service.impl;

import framework.generic.dao.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.clazz.model.RoomType;
import platform.education.clazz.service.RoomService;
import platform.education.clazz.service.RoomTypeService;
import platform.education.clazz.vo.RoomCondition;
import platform.education.clazz.vo.RoomTypeCondition;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.rest.basic.service.vo.CommonSubject;
import platform.education.rest.basic.service.vo.ExtTeamStudentVo;
import platform.education.rest.bp.service.BpTeamRestService;
import platform.education.rest.bp.service.vo.*;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;
import platform.service.storage.service.FileService;

import java.util.*;

public class BpTeamRestServiceImpl implements BpTeamRestService {

	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;

	@Autowired
	@Qualifier("fileService")
	protected FileService fileService;

	@Autowired
	@Qualifier("profileService")
	protected ProfileService profileService;

	@Autowired
	@Qualifier("userService")
	protected UserService userService;

	@Autowired
	@Qualifier("personService")
	protected PersonService personService;

	@Autowired
	@Qualifier("studentService")
	protected StudentService studentService;

	@Autowired
	@Qualifier("teamStudentService")
	protected TeamStudentService teamStudentService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("subjectGradeService")
	protected SubjectGradeService subjectGradeService;

	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;

	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;

	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;

	@Autowired
	@Qualifier("roomTypeService")
	private RoomTypeService roomTypeService;

	@Autowired
	@Qualifier("roomService")
	private RoomService roomService;

	private static String SCHOOL_MASTER = "0";

	private static String CLASS_MASTER = "1";

	private static String SUB_TEACHER = "2";


	@Override
	public Object getMyTeam(String schoolId, String userId, String type) {
		ComTeamComparator ctc = new ComTeamComparator();
		try {
			List<CommonTeacherTeam> teamList = null;
			List<CommonTeacherTeamVo> voList = null;

			if(CLASS_MASTER.equals(type)){//班主任
				teamList = getClassMaster(userId, schoolId, CLASS_MASTER );
				if(teamList!=null && teamList.size()>0){
					Collections.sort(teamList, ctc);
					CommonTeacherTeamVo vo = null;
					voList = new ArrayList<CommonTeacherTeamVo>();
					for(CommonTeacherTeam ctt : teamList){
						vo = new CommonTeacherTeamVo();
						BeanUtils.copyProperties(ctt,vo);
						voList.add(vo);
					}
				}

			} else if(SUB_TEACHER.equals(type)){//科任教师
				teamList = getSubTeacher(userId, schoolId, SUB_TEACHER );
				if(teamList!=null && teamList.size()>0){
					Collections.sort(teamList, ctc);
					CommonTeacherTeamVo vo = null;
					voList = new ArrayList<CommonTeacherTeamVo>();
					for(CommonTeacherTeam ctt : teamList){
						vo = new CommonTeacherTeamVo();
						BeanUtils.copyProperties(ctt,vo);
						voList.add(vo);
					}
				}
			} else if(SCHOOL_MASTER.equals(type) || "".equals(type)){//学校管理者，拿到全校年级班级
				teamList = getAllTeams(schoolId, type);
				if(teamList!=null && teamList.size()>0){
					Collections.sort(teamList, ctc);
					CommonTeacherTeamVo vo = null;
					voList = new ArrayList<CommonTeacherTeamVo>();
					for(CommonTeacherTeam ctt : teamList){
						vo = new CommonTeacherTeamVo();
						BeanUtils.copyProperties(ctt,vo);
						voList.add(vo);
					}
				}
			} else {
				teamList = new ArrayList<CommonTeacherTeam>();
				teamList = getSubTeacher(userId, schoolId, null );
				if(teamList!=null && teamList.size()>0){
					Collections.sort(teamList, ctc);
					CommonTeacherTeamVo vo = null;
					voList = new ArrayList<CommonTeacherTeamVo>();
					for(CommonTeacherTeam ctt : teamList){
						vo = new CommonTeacherTeamVo();
						BeanUtils.copyProperties(ctt,vo);
						voList.add(vo);
					}
				}
			}
			return new ResponseVo<List<CommonTeacherTeamVo>>("0",voList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("学校id异常...");
			info.setMsg("参数出错");
			info.setParam("schoolId");
			return new ResponseError("060612",info);
		}
	}

	/**
	 * 根据用户获取在哪些班级担任班主任
	 *
	 * @param userId
	 * @param schoolId
	 * @param type
	 * @return
	 */
	private List<CommonTeacherTeam> getClassMaster(String userId, String schoolId, String type){
		List<CommonTeacherTeam> teamList = null;
		CommonTeacherTeam ctt = null;
		SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(Integer.parseInt(schoolId));
		Teacher teacher = this.teacherService.findOfUser(Integer.parseInt(schoolId), Integer.parseInt(userId));
		if(teacher!=null){
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setSchoolId(Integer.parseInt(schoolId));
			teamTeacherCondition.setTeacherId(teacher.getId());
			teamTeacherCondition.setType(Integer.parseInt(type));
			if(stc!=null){
				teamTeacherCondition.setSchoolYear(stc.getSchoolYear());
			}
			List<TeamTeacher> tTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition,null,null);
			teamList = new ArrayList<CommonTeacherTeam>();
			Team curTeam = null;
			Grade grade = null;
			for(TeamTeacher teamTeacher : tTeacherList){
				ctt = new CommonTeacherTeam();
				ctt.setId(teamTeacher.getTeamId());
				curTeam = this.teamService.findTeamById(teamTeacher.getTeamId());
				if(curTeam != null){
					ctt.setName(curTeam.getName());
					ctt.setTeamNumber(curTeam.getTeamNumber());
				}
				grade = this.gradeService.findGradeById(teamTeacher.getGradeId());
				if(grade != null){
					ctt.setGradeId(grade.getId());
					ctt.setGradeName(grade.getName());
					ctt.setUniGradeCode(grade.getUniGradeCode());
				}
				ctt.setType(teamTeacher.getType()+"");
				teamList.add(ctt);
			}
		}
		return teamList;
	}

	/**
	 * 根据用户获取在哪些班级担任科任教师
	 *
	 * @param userId
	 * @param schoolId
	 * @param type
	 * @return
	 */
	private List<CommonTeacherTeam> getSubTeacher(String userId, String schoolId, String type){
		List<CommonTeacherTeam> teamList = null;
		CommonTeacherTeam ctt = null;
		SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(Integer.parseInt(schoolId));
		Teacher teacher = this.teacherService.findOfUser(Integer.parseInt(schoolId), Integer.parseInt(userId));
		if(teacher!=null){
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setSchoolId(Integer.parseInt(schoolId));
			teamTeacherCondition.setTeacherId(teacher.getId());
			if(type!=null){
				teamTeacherCondition.setType(Integer.parseInt(type));
			}
			if(stc!=null){
				teamTeacherCondition.setSchoolYear(stc.getSchoolYear());
			}
			List<TeamTeacher> tTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition,null,null);
			teamList = new ArrayList<CommonTeacherTeam>();
			Team curTeam = null;
			Integer[] ids = new Integer[tTeacherList.size()];
			for(int i=0;i<tTeacherList.size();i++){
				ids[i] = tTeacherList.get(i).getTeamId();
			}
			Set<Integer> set = new HashSet<Integer>();
			for(Integer i : ids){
				set.add(i);
			}
			for(Integer curTeamId : set){
				ctt = new CommonTeacherTeam();
				ctt.setType(type);
				ctt.setId(curTeamId);
				curTeam = this.teamService.findTeamById(curTeamId);
				if(curTeam!=null){
					ctt.setName(curTeam.getName());
					ctt.setTeamNumber(curTeam.getTeamNumber());
				}
				Grade grade = this.gradeService.findGradeById(curTeam.getGradeId());
				if(grade != null){
					ctt.setGradeId(grade.getId());
					ctt.setGradeName(grade.getName());
					ctt.setUniGradeCode(grade.getUniGradeCode());
				}
				teamList.add(ctt);
			}
		}
		return teamList;
	}

	/**
	 * 获取当前学年下的所有班级
	 * @param schoolId
	 * @param type
	 * @return
	 */
	private List<CommonTeacherTeam> getAllTeams(String schoolId, String type){
		List<CommonTeacherTeam> teamList = null;
		CommonTeacherTeam ctt = null;
		SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(Integer.parseInt(schoolId));
		if(stc!=null){
			List<Team> tempList = this.teamService.findCurrentTeamOfSchoolAndYear(Integer.parseInt(schoolId), stc.getSchoolYear());
			teamList = new ArrayList<CommonTeacherTeam>();
			Team curTeam = null;
			for(Team team : tempList){
				ctt = new CommonTeacherTeam();
				ctt.setType(type);
				ctt.setId(team.getId());
				curTeam = this.teamService.findTeamById(team.getId());
				if(curTeam!=null){
					ctt.setName(curTeam.getName());
					ctt.setTeamNumber(curTeam.getTeamNumber());
				}
				Grade grade = this.gradeService.findGradeById(curTeam.getGradeId());
				if(grade != null){
					ctt.setGradeId(grade.getId());
					ctt.setGradeName(grade.getName());
					ctt.setUniGradeCode(grade.getUniGradeCode());
				}
				teamList.add(ctt);
			}
		}
		return teamList;
	}



	@Override
	public Object getSubject(String teamId, String teamIds){
		try {

			if (teamIds != null && !"".equals(teamIds)) {
				String[] ids = teamIds.split(",");
				List<Object> list = new ArrayList<>();
				List<CommonSubject> subject = null;
				for (String id : ids) {
					subject = getSubjectOfTeam(Integer.parseInt(id));
					Map<String, Object> map = new HashMap<>();
					map.put("teamId", Integer.parseInt(id));
					map.put("subjectList", subject);
					list.add(map);
				}
				return new ResponseVo<Object>("0",list);
			} else {
				List<CommonSubject> subject = getSubjectOfTeam(Integer.parseInt(teamId));
				return new ResponseVo<List<CommonSubject>>("0",subject);
			}
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("班级id转换异常...");
			info.setMsg("参数出错");
			info.setParam("teamId");
			return new ResponseError("060612",info);
		}
	}

	private List<CommonSubject> getSubjectOfTeam(Integer teamId){
		Team team = this.teamService.findTeamById(teamId);
		Grade grade = this.gradeService.findGradeById(team.getGradeId());
		List<SubjectGrade> subjectList = null;
		List<CommonSubject> subject = new ArrayList<CommonSubject>();
		CommonSubject vo = null;
		if (grade != null) {
			subjectList = this.subjectGradeService.findSubjectGradeByGradeCode(grade.getSchoolId(),grade.getUniGradeCode() );
			for (SubjectGrade sbg : subjectList) {
				vo = new CommonSubject();
				vo.setCode(sbg.getSubjectCode());
				vo.setName(sbg.getSubjectName());
				subject.add(vo);
			}
		}
		return subject;
	}

	@Override
	public Object getStudentList(String teamId) {
		List<TeamStudentVo> teamStudentVoList = new ArrayList<TeamStudentVo>();
		List<ExtTeamStudentVo> teamStudents = new ArrayList<ExtTeamStudentVo>();
		try{

			if(teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			Student student = null;
			teamStudentVoList = this.teamStudentService.getTeamStudentsByTeamId(Integer.parseInt(teamId));
			//默认头像
			String imgUrl = ImgUtil.getImgUrl(null);
			for (TeamStudentVo vo : teamStudentVoList) {
				Integer personId = this.studentService.findStudentByUserId(vo.getUserId()).getPersonId();
				imgUrl = ImgUtil.getStudentIconUrl(personId, personService);
				Student s = this.studentService.findStudentById(vo.getStudentId());
				ExtTeamStudentVo teamStudentVo = new ExtTeamStudentVo();
				if(s.getPosition()!=null){
					teamStudentVo.setPosition(s.getPosition());
				}
				teamStudentVo.setUserIcon(imgUrl);
				teamStudentVo.setAlias(student == null ? null : student.getAlias());
				teamStudentVo.setId(vo.getUserId());
				teamStudentVo.setStudentId(vo.getStudentId());
				teamStudentVo.setName(vo.getName());
				teamStudentVo.setSex(vo.getSex());
				teamStudentVo.setNumber(vo.getNumber());
				teamStudentVo.setStudentNumber(vo.getStudentNumber());
				teamStudentVo.setUserName(vo.getUserName());
				teamStudents.add(teamStudentVo);
			}

			return new ResponseVo<List<ExtTeamStudentVo>>("0", teamStudents);

		}catch(Exception e){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过班级ID获取班级学生名单异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}

	@Override
	public Object setMonitor(Integer teamId, Integer userId) {
		try {
			this.studentService.setMonitor(userId, teamId);
			return new ResponseVo<Boolean>("0", true);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("userId...");
			return new ResponseError("060112",info);
		}
	}

	@Override
	public Object getTeacherList(String teamId) {
		try {
			Team team = this.teamService.findTeamById(Integer.parseInt(teamId));
			Order order = new Order();
			order.setProperty("type");
			order.setAscending(true);
			List<Teacher> teacherList = this.teacherService.findTeacherListByTeamId(Integer.parseInt(teamId), order);
			for(Teacher teacher : teacherList){
				if(teacher.getType().equals("1")&&teacher.getSubjectName()==null){
					teacher.setSubjectName("班主任");
				}
			}
			Integer[] ids = new Integer[teacherList.size()];
			for(int i=0;i<teacherList.size();i++){
				ids[i] = teacherList.get(i).getId();
			}
			for(int i=0;i<ids.length;i++){
				for(int j=i+1;j<ids.length;j++){
					if(ids[i].intValue()==ids[j].intValue()){
						if(teacherList.get(i)!=null&&teacherList.get(i)!=null){
							String subject = teacherList.get(j).getSubjectName();
							teacherList.get(i).setSubjectName(teacherList.get(i).getSubjectName()+"&"+subject);
							teacherList.remove(j);
							teacherList.add(j, null);
						}
					}
				}
			}
			List<Teacher> teacherListCopy = new ArrayList<Teacher>();
			for(int i=0;i<teacherList.size();i++){
				if(teacherList.get(i)!=null){
					teacherListCopy.add(teacherList.get(i));
				}
			}
			List<SimplifyTeacherVo> voList = new ArrayList<SimplifyTeacherVo>();
			SimplifyTeacherVo vo = null;
			String imgSrc = "";
			for(Teacher tea : teacherListCopy){
				vo = new SimplifyTeacherVo();
				if(tea.getSex()==null){
					vo.setSex("0");
				}else{
					vo.setSex(tea.getSex());
				}
				vo.setId(tea.getUserId());
				vo.setName(tea.getName());
				vo.setSubjectName(tea.getSubjectName());
				vo.setTeamName(team.getName());
				vo.setType(tea.getType());
				imgSrc = ImgUtil.getImgSrc(tea.getUserId(),profileService);
				vo.setUserIcon(imgSrc);
				voList.add(vo);
			}
			return new ResponseVo<List<SimplifyTeacherVo>>("0",voList);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("班级id转换异常...");
			info.setMsg("参数出错");
			info.setParam("teamId");
			return new ResponseError("060612",info);
		}
	}

	@Override
	public Object iconChange(String userId,String iconUuid) {
		Profile profile = null;
		try {
			if(iconUuid == null || "".equals(iconUuid)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("iconUuid...");
				return new ResponseError("060111",info);
			}
			Integer userIdInt = Integer.parseInt(userId);
			profile = this.profileService.findByUserId(userIdInt);
			if(profile!=null){
				profile.setIcon(iconUuid);
				profile = this.profileService.modify(profile);
			}else{
				profile = new Profile();
				User user = this.userService.findUserById(userIdInt);
				if(user!=null){
					profile.setUserId(userIdInt);
					profile.setUserName(user.getUserName());
					profile.setIcon(iconUuid);
					profile.setCreateDate(new Date());
					profile = this.profileService.add(profile);
				}
			}
			Person person = this.personService.findPersonById(this.userService.findUserById(userIdInt).getPersonId());
			if (person != null) {
				person.setPhotoUuid(iconUuid);
				this.personService.modify(person);
			} else {
				person = new Person();
				person.setPhotoUuid(iconUuid);
				Student student = this.studentService.findStudentByUserId(userIdInt);
				if (student != null) {
					person.setName(student.getName());
					Date date = new Date();
					person.setCreateDate(date);
					person.setModifyDate(date);
					person.setIsDelete(false);
					person = this.personService.add(person);
					if (person != null) {
						student.setPersonId(person.getId());
						this.studentService.modify(student);
						User user = this.userService.findUserById(userIdInt);
						user.setPersonId(person.getId());
						this.userService.modify(user);
					}
				}
			}
			String userIcon = "";
			if(profile != null){
				userIcon = this.fileService.relativePath2HttpUrlByUUID(profile.getIcon());
			}
			ExtIcon icon = new ExtIcon(userIcon);
			return new ResponseVo<ExtIcon>("0",icon);
		} catch (Exception e) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数转化异常...");
			info.setMsg("参数类型出错");
			info.setParam("userId...");
			return new ResponseError("060112",info);
		}
	}

	@Override
	public Object getStudentTeamList(Integer userId) {
		try {
			Team team = new Team();
			TeamStudentCondition condition = new TeamStudentCondition();
			condition.setUserId(userId);
			condition.setInState(true);
			condition.setIsDelete(false);
			TeamStudent teamStudent = this.teamStudentService.findTeamStudentByCondition(condition, null, null).get(0);
			team = this.teamService.findTeamById(teamStudent.getTeamId());
			return new ResponseVo<Team>("0",team);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}

	@Override
	public Object getRoomList(Integer schoolId) {
		try {
			List<BpRoomVo> bpRoomVoList = new ArrayList<BpRoomVo>();
			//SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			List<RoomType> roomTypeList = this.roomTypeService.findRoomTypeByCondition(new RoomTypeCondition());
			if(roomTypeList != null && roomTypeList.size() > 0){
				Order order = new Order();
				order.setProperty("reorder,name");
				order.setAscending(true);
				for(RoomType roomType: roomTypeList){
					BpRoomVo bpRoomVo = new BpRoomVo();
					bpRoomVo.setRoomTypeName(roomType.getName());
					bpRoomVo.setRoomTypeCode(roomType.getCode());
					RoomCondition roomCondition = new RoomCondition();
					roomCondition.setSchoolId(schoolId);
					roomCondition.setTypeCode(roomType.getCode());
					roomCondition.setIsUsed(true);
					bpRoomVo.setRoomList(this.roomService.findRoomByCondition(roomCondition, null, order));
					bpRoomVoList.add(bpRoomVo);
				}
			}
			return new ResponseVo<List<BpRoomVo>>("0",bpRoomVoList);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("接口异常");
			info.setMsg("接口异常");
			return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL,info);
		}
	}
}
