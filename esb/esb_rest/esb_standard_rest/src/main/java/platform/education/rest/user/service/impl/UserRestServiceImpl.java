package platform.education.rest.user.service.impl;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.ParentCondition;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.user.service.UserRestService;
import platform.education.rest.user.service.contants.SchoolRoles;
import platform.education.rest.user.service.vo.ExtRoleVo;
import platform.education.rest.user.service.vo.UserDetailInfo;
import platform.education.rest.user.service.vo.UserDetailInfo.Area;
import platform.education.rest.user.service.vo.UserInfo;
import platform.education.rest.user.service.vo.UserInfomation;
import platform.education.rest.user.service.vo.UserMsg;
import platform.education.rest.util.ImgUtil;
import platform.education.rest.util.IpAddr;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.AppEdition;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserBinding;
import platform.education.user.model.UserLoginLog;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.GroupService;
import platform.education.user.service.GroupUserService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserLoginLogService;
import platform.education.user.service.UserRegionService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;

/**
 * 
 * @author hmzhang 2016/07/25
 *
 */
public class UserRestServiceImpl implements UserRestService {

//	private static final Logger log = LoggerFactory.getLogger(UserRestServiceImpl.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;
	
	@Autowired
	@Qualifier("groupUserService")
	private GroupUserService groupUserService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;

	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;

	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;

	@Autowired
	@Qualifier("userRegionService")
	private UserRegionService userRegionService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("parentService")
	private ParentService parentService;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("userLoginLogService")
	private UserLoginLogService userLoginLogService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;
    
    @Autowired
    @Qualifier("schoolUserService")
    private SchoolUserService schoolUserService;
    
    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;
    
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
    
    @Autowired
    @Qualifier("parentStudentService")
    private ParentStudentService parentStudentService;
    
    
   /* public Object login(String username,String password,String appKey,String userType,String timestamp,String signature) {
    	
    	String[] params = {username,password,appKey,userType,timestamp,signature};
    	//1. ????????????????????????
    	if(ClientCallbackApi.isLegitimacyApi(params, signature)) { //??????????????????????????????
    		
    	}
    	return null;
    	
    }*/
    
    
	@Override
	public Object login(String username, String password, String appKey, HttpServletRequest request) {
		UserBinding ub = this.userBindingService.findByBindingName(username);
		if(ub != null){
			Integer userId = ub.getUserId();
			User user = this.userService.findUserById(userId);
			if(user != null){
				//????????????
				if(user.getPassword().equals(password)){
					if(user.getValidDate() != null && user.getValidDate().before(new Date())){
						//???????????????
						ResponseInfo info = new ResponseInfo();
						info.setDetail("????????????????????????");
						info.setMsg("????????????????????????");
						info.setParam("username");
						return new ResponseError(CommonCode.U$USERACCOUNT_HAS_EXPIRED, info);
					} else {
						if("0".equals(user.getState())){
							AppEdition app = this.appEditionService.findByAppKey(appKey);
							if(app == null){
								ResponseInfo info = new ResponseInfo();
								info.setDetail("appkey?????????,?????????");
								info.setMsg("????????????appKey");
								info.setParam("appKey");
								return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
							} else {
								UserInfo userInfo = new UserInfo();
								userInfo.setId(user.getId());
								Integer schoolId = null;
								List<Group> groups = this.groupUserService.findGroups(userId, GroupContants.TYPE_SCHOOL);
								if (groups.size() > 0) {
									Group group = groups.get(0);
									if (group != null) {
										schoolId = group.getOwnerId();
									}
								}
								if (schoolId != null) {
									// ????????????????????????????????????
									List<String> userTypes = this.userRoleService.findRoleTypesByUserId(userId, app.getAppId());
									if(userTypes != null && userTypes.size() > 0) {
//										for (String userType : userTypes) {
//											//????????????????????????????????????????????????????????????????????????????????????
//											if (SysContants.USER_TYPE_TEACHER.equals(userType)) {
//												Teacher teacher = this.teacherService.findOfUser(schoolId, userId);
//												if(teacher != null) {
//													userInfo.setName(teacher.getName());
//												}
//											//???????????????????????????????????????????????????????????????????????????????????????
//											} else if (SysContants.USER_TYPE_STUDENT.equals(userType)) {
//												Student student = this.studentService.findOfUser(schoolId, userId);
//												if(student != null) {
//													userInfo.setName(student.getName());
//												}
//											} else if (SysContants.USER_TYPE_PARENT.equals(userType)) {
//												Parent parent = this.parentService.findUniqueByUserId(userId);
//												if(parent != null) {
//													userInfo.setName(parent.getName());
//												}
//											}
//										}
										//??????
										if(userTypes.contains(SysContants.USER_TYPE_TEACHER)){
											userInfo.setIsTeacher(true);
										}
									}
								}
								
								if (user.getPersonId() != null) {
									Person person = this.personService.findPersonById(user.getPersonId());
									if (person != null) {
										userInfo.setName(person.getName());
									}
								}
								
								String name = userInfo.getName();
								Profile profile = this.profileService.findByUserId(userId);
								if (profile != null) {
									name = name != null && !"".equals(name) ? name : profile.getName() != null ? profile.getName() : "";
									userInfo.setName(name);
								}
								String imgUrl = ImgUtil.getImgSrc(userId, profileService);
								userInfo.setIconUrl(imgUrl);
								
								/**
								 * ??????????????????
								 */
								UserLoginLog userLoginLog = new UserLoginLog();
								userLoginLog.setAppKey(appKey);
								userLoginLog.setUserId(userId);
								userLoginLog.setCurIp(IpAddr.getIpAddr(request) != null ? IpAddr.getIpAddr(request):"");
								userLoginLog.setCurLoginTime(new Date());
								this.userLoginLogService.addLoginLog(userLoginLog);
								return new ResponseVo<UserInfo>("0",userInfo);
							}
						} else if("1".equals(user.getState())){
							//???????????????
							ResponseInfo info = new ResponseInfo();
							info.setDetail("?????????????????????");
							info.setMsg("??????????????????");
							info.setParam("username");
							return new ResponseError(CommonCode.U$USERACCOUNT_NOT_IN_USE, info);
						} else if("2".equals(user.getState())){
							//???????????????
							ResponseInfo info = new ResponseInfo();
							info.setDetail("?????????????????????");
							info.setMsg("??????????????????");
							info.setParam("username");
							return new ResponseError(CommonCode.U$USERACCOUNT_LOCKED, info);
						}
					}
				} else {
					//???????????????
					ResponseInfo info = new ResponseInfo();
					info.setDetail("????????????");
					info.setMsg("????????????");
					info.setParam("password");
					return new ResponseError(CommonCode.U$PASSWORD_ERROR, info);
				}
			}
		} else {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????");
			info.setMsg("??????????????????");
			info.setParam("username");
			return new ResponseError(CommonCode.U$USERNAME_NOT_EXIST, info);
		}
		return null;
	}
	
	public Object getUserInfo(@QueryParam("userId") Integer userId){
		UserDetailInfo udi = null;
		try{
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("??????????????????");
				info.setMsg("userId???????????????");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
			}
			
			User user = userService.findUserById(userId);
			
			if(user != null){
				if(user.getId() != null){
					Person person = personService.findPersonById(user.getPersonId());
					udi = new UserDetailInfo();
					udi.setUserName(user.getUserName());
					udi.setPassword(user.getPassword());
					udi.setUserId(userId);
					Area area = udi.new Area();
					if(person != null){
						udi.setSex(person.getSex() == null ? "9" : person.getSex());
						udi.setSignature(null);
						udi.setQrCodeFile(null);
						udi.setEmail(person.getEmail());
						udi.setMobile(person.getMobile());
						udi.setName(person.getName());
						
						area.setCity(person.getCity());
						area.setDistrict(person.getDistrict());
						area.setStreet(person.getStreet());
						area.setProvince(person.getProvince());
					}
					if (udi.getName() == null || "".equals(udi.getName())) {
						List<Teacher> teacherList = teacherService.findTeacherByUserId(userId);
						if (teacherList != null && teacherList.size() > 0) {
							Teacher teacher = teacherList.get(0);
							udi.setName(teacher.getName());
							udi.setSex(teacher.getSex() == null ? "9" : teacher.getSex());
							udi.setMobile(teacher.getMobile());
						}
					}
					if (udi.getName() == null || "".equals(udi.getName())) {
						Parent parent = parentService.findUniqueByUserId(userId);
						if (parent != null) {
							udi.setName(parent.getName());
							udi.setSex("9");
							udi.setMobile(parent.getMobile());
							udi.setEmail(parent.getEmail());
						}
					}
					if (udi.getName() == null || "".equals(udi.getName())) {
						Student student = studentService.findStudentByUserId(userId);
						if (student != null) {
							udi.setName(student.getName());
							udi.setSex(student.getSex() == null ? "9" : student.getSex());
						}
					}
					if(udi.getName() == null || "".equals(udi.getName())){
						Profile profile = this.profileService.findByUserId(userId);
						if (profile != null) {
							udi.setName(profile.getName());
							udi.setSex(profile.getSex() == null ? "9" : profile.getSex());
							area.setCity(profile.getCity());
							area.setProvince(profile.getProvince());
						}
					}
					udi.setArea(area);
					if (udi.getSex() == null || "".equals(udi.getSex())) {
						udi.setSex("9");
					}
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("????????????");
					info.setMsg("?????????????????????????????????");
					info.setParam("userId");
					return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
				}
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????");
				info.setMsg("???????????????????????????");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseVo<UserDetailInfo>("0", udi);
	}

	@Override
	public Object changePassword(Integer userId, String password, String newPassword, String appKey) {
		//???app?????????????????????app
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		//?????????app?????????????????????
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		//??????id????????????
		if(userId == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????");
			info.setMsg("userId???????????????");
			info.setParam("userId");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
		}
		//?????????????????????
		if(newPassword == null || "".equals(newPassword)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????");
			info.setMsg("??????????????????");
			info.setParam("newPassword");
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,info);
		}
		User user = this.userService.findUserById(userId);
		if(user != null){
			if(password.equals(user.getPassword())){
				user.setPassword(newPassword);
				this.userService.modify(user);
				return new ResponseVo<>("0", null);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("??????????????????");
				info.setMsg("????????????");
				info.setParam("password");
				return new ResponseError(CommonCode.U$PASSWORD_ERROR, info);
			}
		}else{//???????????????
			ResponseInfo info = new ResponseInfo();
			info.setDetail("???????????????");
			info.setMsg("???????????????");
			info.setParam("userId");
			return new ResponseError(CommonCode.U$USER_NOT_EXIST, info);
		}
	}

    @Override
    public Object getRoleOfUser(@FormParam("userId") Integer userId, @FormParam("appKey") String appKey) {
        if(userId == null){
        	ResponseInfo info = new ResponseInfo();
			info.setDetail("Id????????????");
			info.setMsg("userId????????????");
			info.setParam("userId");
            return new ResponseError("060111",info);//??????????????????
        }
        ExtRoleVo roleVo = null;
        List<ExtRoleVo> roleVoList = new ArrayList<ExtRoleVo>();
        boolean flag = true;
        try{
            List<Integer> roleIds =  this.userRoleService.findRoleIdsByUserId(userId, 1);
            for (Integer roleId : roleIds) {
                Role role = this.roleService.findRoleById(roleId);
                if (role != null) {
                    //????????????
                    if(SchoolRoles.R$CLASS_MASTER.equals(role.getCode())
                            || SchoolRoles.R$PARENT.equals(role.getCode())
                            || SchoolRoles.R$SCHOOL_LEADER.equals(role.getCode())
                            || SchoolRoles.R$SCHOOL_MASTER.equals(role.getCode())
                            || SchoolRoles.R$STUDENT.equals(role.getCode())
                            || SchoolRoles.R$SUBJECT_TEACHER.equals(role.getCode())
                            || SchoolRoles.R$TEACHER.equals(role.getCode())){
                        Integer groupId = role.getGroupId();
                        Group group = this.groupService.findGroupById(groupId);
                        if (group != null) {
                        	//???????????????
                        	if(SchoolRoles.R$PARENT.equals(role.getCode())){
                        		//???????????????
                        		if(flag){
                        			roleVo = new ExtRoleVo();
                                    roleVo.setId(role.getId());
                                    roleVo.setName(role.getName());
                                    roleVo.setCode(role.getCode());
									roleVo.setSchoolName("");
                                    //??????????????????
                                    roleVoList.add(roleVo);
                                    flag = false;
                            	}
                        	}else{
                        		roleVo = new ExtRoleVo();
                                roleVo.setId(role.getId());
                                roleVo.setName(role.getName());
                                roleVo.setCode(role.getCode());
                                roleVo.setSchoolId(group.getOwnerId());
                                roleVo.setSchoolName(group.getName());
                                roleVoList.add(roleVo);
                        	}
                        }
                    }
                }
            }
            if(roleVoList.size() <= 0){
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//???????????????
            }
        }catch(Exception e){
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
        }
        return new ResponseVo<List<ExtRoleVo>>("0", roleVoList);
    }

	@Override
	public Object getUserInfomation(String username) {
		if ( username == null ) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("username????????????");
			info.setMsg("username????????????");
			info.setParam("username");
			return new ResponseError("060111",info);//??????????????????
		}
		UserBinding bindingUser = userBindingService.findByBindingName(username);
		if ( bindingUser == null ) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("???????????????");
			info.setMsg("???????????????");
			info.setParam("username");
			return new ResponseError(CommonCode.U$USER_NOT_EXIST, info);
		}
		Integer userId = bindingUser.getUserId();
		UserInfomation result = null;
		try {
			List<SchoolUser> list = schoolUserService.findSchoolUserByCondition(getSchoolUserCondition(userId), null, null);
			if ( list != null && list.size() > 0 ) {
				int userType = getUserType(list);
				result = new UserInfomation();
				result.setIconUrl(ImgUtil.getImgSrc(userId, profileService));
				switch (userType) {
				case 0b01:
					List<Teacher> teacherList = teacherService.findTeacherByCondition(getTeacherCondition(userId), null, null);
					if ( teacherList != null && teacherList.size() > 0 ) 
						getUserInfomationByTeacher(result, teacherList.get(0));
					break;
				case 0b11:
					List<Parent> parentList = parentService.findParentByCondition(getParentCondition(userId), null, null);
					if ( parentList != null && parentList.size() > 0 ) {
						getUserInfomationByParent(result, parentList.get(0));
						Integer personId = parentService.findUniqueByUserId(userId).getPersonId();
						if ( personId != null ) {
							Person person = personService.findPersonById(personId);
							if ( person != null ) {
								result.setSex(person.getSex());
							}
						}
						ParentStudentCondition arg = new ParentStudentCondition();
						arg.setIsDelete(false);
						arg.setParentUserId(userId);
						List<ParentStudent> parentStudentList = parentStudentService.findParentStudentByCondition(arg);
						if ( parentStudentList != null && parentStudentList.size() > 0 ) {
							Student student = studentService.findStudentByUserId( parentStudentList.get(0).getStudentUserId() );
							if ( student != null ) {
								result.setStudentUsername(student.getUserName());
							}
						}
					}
					break;
				case 0b100:
					Student student = studentService.findStudentByUserId(userId);
					if ( student != null ) {
						getUserInfomationByStudent(result, student);
						
						Team team = teamService.findCurrentTeamOfStudent(student.getUserId(), student.getSchoolId());
						if ( team != null ) {
							
							Grade grade = gradeService.findGradeById(team.getGradeId());
							result.setTeamName(team.getName());
							result.setTeamId(team.getId());
							if ( grade != null ) {
								result.setGradeCode(grade.getUniGradeCode());
								result.setGradeName(grade.getName());
							}
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
		return new ResponseVo<UserInfomation>("0",result);
	}
	
	/**
	 * ?????????????????????????????????
	 * @param userId
	 * @return
	 */
	private SchoolUserCondition getSchoolUserCondition(Integer userId) {
		SchoolUserCondition param = new SchoolUserCondition();
		param.setUserId(userId);
		return param;
	}
	
	/**
	 * ????????????????????????
	 * @param userId
	 * @return
	 */
	private TeacherCondition getTeacherCondition(Integer userId) {
		TeacherCondition param = new TeacherCondition();
		param.setUserId(userId);
		return param;
	}
	
	/**
	 * ????????????????????????
	 * @param userId
	 * @return
	 */
	private ParentCondition getParentCondition(Integer userId) {
		ParentCondition param = new ParentCondition();
		param.setUserId(userId);
		return param;
	}
	
	/**
	 * ????????????????????????
	 * @param result
	 * @param teacher
	 * @return
	 */
	public static UserInfomation getUserInfomationByTeacher(UserInfomation result, Teacher teacher) {
		result.setMobile(teacher.getMobile());
		result.setName(teacher.getName());
		result.setSex(teacher.getSex());
		result.setUserId(teacher.getUserId());
		result.setUsername(teacher.getUserName());
		result.setRole("TEACHER");
		return result;
	}
	
	/**
	 * ????????????????????????
	 * @param result
	 * @param parent
	 * @return
	 */
	public static UserInfomation getUserInfomationByParent(UserInfomation result, Parent parent) {
		result.setMobile(parent.getMobile());
		result.setName(parent.getName());
		//result.setSex(parent.getSex());
		result.setUserId(parent.getUserId());
		result.setUsername(parent.getUserName());
		result.setRole("PARENT");
		return result;
	}
	
	/**
	 * ????????????????????????
	 * @param result
	 * @param student
	 * @return
	 */
	public static UserInfomation getUserInfomationByStudent(UserInfomation result, Student student) {
		result.setMobile(student.getMobile());
		result.setName(student.getName());
		result.setSex(student.getSex());
		result.setUsername(student.getUserName());
		result.setUserId(student.getUserId());
		result.setRole("STUDENT");
		result.setTeamId(student.getTeamId());
		return result;
	}
	
	/**
	 * ??????????????????(1:??????,2:??????,3:??????)
	 * @param list
	 * @return
	 */
	private int getUserType(List<SchoolUser> list) {
		int userType = 0;
		a:for (SchoolUser schoolUser : list) {
			switch (schoolUser.getUserType()) {
			case "1": // ????????????
				userType = 0b01;
				break a;
			case "3": // ??????
				userType = 0b11;
			case "4": // ??????
				if ( userType != 0b11 ) {
					userType = 0b100;
				}
			}
		}
		return userType;
	}

	@Override
	public Object getMySchool(Integer userId) {
		if ( userId == null ) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId????????????");
			info.setMsg("userId????????????");
			info.setParam("userId");
			return new ResponseError("060111",info);//??????????????????
		}
		
		User user = userService.findUserById(userId);
		if(user == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId?????????");
			info.setMsg("userId?????????");
			info.setParam("userId");
			return new ResponseError("060111",info);//??????????????????
		}
		
		TeacherCondition tc = new TeacherCondition();
		tc.setUserId(userId);
		tc.setIsDelete(false);
		List<Teacher> teacherList = teacherService.findTeacherByCondition(tc, null, null);
		
		School school = null;
		UserMsg userMsg = null;
		List<UserMsg> userMsgList = new ArrayList<UserMsg>();
		
		if(teacherList != null && teacherList.size() > 0){
			for(Teacher teacher : teacherList){
				if(teacher != null){
					school = schoolService.findSchoolById(teacher.getSchoolId());
					userMsg = new UserMsg();
					userMsg.setRole("TEACHER");
					userMsg.setSchoolId(teacher.getSchoolId());
					if(school != null){
						userMsg.setSchoolName(school.getName());
					}
					userMsgList.add(userMsg);
				}
			}
		}
		return new ResponseVo<List<UserMsg>>("0",userMsgList);
	}

	@Override
	public Object getUserRoleByEncryption(Integer userId) {
		if(userId == null){
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					new ResponseInfo("userId????????????", "userId????????????", "userId"));
		}

		Map<String, Object> map = new HashMap<>();
		List<Object> list = new ArrayList<>();
		try {
			List<Integer> roleIds =  this.userRoleService.findRoleIdsByUserId(userId, 1);
			int max = 0;
			for (Integer roleId : roleIds) {
                Role role = this.roleService.findRoleById(roleId);
                if (role != null) {
                    //????????????
                    if(SchoolRoles.R$CLASS_MASTER.equals(role.getCode())
							|| SchoolRoles.R$SCHOOL_MANAGER.equals(role.getCode())
                            || SchoolRoles.R$PARENT.equals(role.getCode())
                            || SchoolRoles.R$SCHOOL_LEADER.equals(role.getCode())
                            || SchoolRoles.R$SCHOOL_MASTER.equals(role.getCode())
                            || SchoolRoles.R$STUDENT.equals(role.getCode())
                            || SchoolRoles.R$SUBJECT_TEACHER.equals(role.getCode())
                            || SchoolRoles.R$TEACHER.equals(role.getCode())){
                        Integer groupId = role.getGroupId();
                        Group group = this.groupService.findGroupById(groupId);
                        if (group != null) {
                            max = checkRoleLevel(role.getCode(), max);
                            Map<String, Object> roleMap = new HashMap<>();
                            roleMap.put("name", role.getName());
                            roleMap.put("code", role.getCode());
                            roleMap.put("schoolId", group.getOwnerId());
                            roleMap.put("schoolName", group.getName());
                            list.add(roleMap);
                        }
                    }
                }
            }
			map.put("roleList", list);
			map.put("max", max);
		} catch (Exception e) {
			return new ResponseError("000001", new ResponseInfo("????????????","???????????????????????????", ""));
		}
		return  new ResponseVo<Object>("0", map);
	}

	private int checkRoleLevel(String code, int level){
		int max = 0;
		if (SchoolRoles.R$SCHOOL_LEADER.equals(code) || SchoolRoles.R$SCHOOL_MASTER.equals(code) || SchoolRoles.R$SCHOOL_MANAGER.equals(code)) {
			max = 3;
		}
		if (SchoolRoles.R$CLASS_MASTER.equals(code)) {
			max = 2;
		}
		if (SchoolRoles.R$SUBJECT_TEACHER.equals(code)) {
			max = 1;
		}
		if (max < level) {
			max = level;
		}
		return max;
	}

}





