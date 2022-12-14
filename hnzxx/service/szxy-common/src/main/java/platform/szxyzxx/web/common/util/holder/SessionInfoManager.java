package platform.szxyzxx.web.common.util.holder;

import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.im.model.ImAccount;
import platform.education.im.service.IMService;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.model.UserRegion;
import platform.education.user.service.*;
import platform.szxyzxx.web.common.contants.InfoCode;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.annotation.Resource;
import java.util.List;

public class SessionInfoManager {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("groupUserService")
	private GroupUserService groupUserService;

	@Autowired
	@Qualifier("schoolService")
	private SchoolService schoolService;

	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;

	@Resource
	private SchoolTermService schoolTermService;

	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;

	@Autowired
	@Qualifier("userBindingService")
	private UserBindingService userBindingService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;

	@Autowired
	@Qualifier("userRegionService")
	private UserRegionService userRegionService;

	@Autowired
	@Qualifier("imService")
	private IMService imService;

	public void setUserInfoToSession(String username, Session session) {
		session.setAttribute(InfoCode.CURRENT_USER, this.getUserInfo(username));
	}
	
	public void setUserInfoToSession(String username, String password, Session session) {
		UserInfo userInfo = this.getUserInfo(username);
		userInfo.setInputPassword(password);
		session.setAttribute(InfoCode.CURRENT_USER, userInfo);
	}

	public UserInfo getUserInfo(String username) {
		UserInfo userInfo = new UserInfo();

		Integer schoolId = null;

		Integer userId = this.userBindingService.findUserId(username);
		User user = this.userService.findUserById(userId);

		List<Group> groups = this.groupUserService.findGroups(userId, GroupContants.TYPE_SCHOOL);
		
		// ???????????????????????????Group ??? ??????ID
		if (groups.size() > 0) {
			Group group = groups.get(0);
			if (group != null) {
				userInfo.setGroupId(group.getId());
				userInfo.setGroupType(group.getType());
				schoolId = group.getOwnerId();
				userInfo.setSchoolId(schoolId);
				SchoolInfo schoolInfo = this.schoolService.findSchoolInfoById(schoolId);
				if (schoolInfo != null) {
					// ???????????????????????????????????????
					userInfo.setSchoolName(schoolInfo.getName());
					userInfo.setSchoolLogo(schoolInfo.getEntityId_image());
					userInfo.setSchooUUID(schoolInfo.getUuid());
					// ????????????????????????????????????????????????
					String stageCode = schoolInfo.getStageScope();
					if (stageCode != null) {
						stageCode = stageCode.replace(",-1", "").replace("-1,", "");
						userInfo.setStageCodes(stageCode.split(","));
						userInfo.setStageCode(stageCode);
					}
					// ??????????????????????????? ????????????code????????????code
					SchoolTermCurrent currentTerm = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
					if (currentTerm != null) {
						userInfo.setSchoolYear(currentTerm.getSchoolYear());
						userInfo.setSchoolTermCode(currentTerm.getSchoolTermCode());

						SchoolTerm schoolTermByCode = schoolTermService.findSchoolTermByCode(schoolInfo.getId(), currentTerm.getSchoolTermCode());
						if (schoolTermByCode != null) {
							userInfo.setSchoolTermId(schoolTermByCode.getId());
						}
					}
				}
			}
		}
		if (schoolId != null) {
			// ????????????????????????????????????
			List<String> userTypes = this.userRoleService.findRoleTypesByUserId(userId, SysContants.SYSTEM_APP_ID);
			if(userTypes != null && userTypes.size() > 0) {
				//????????????????????????
				String userTypeStr = userTypes.toString();
				userTypeStr = userTypeStr.substring(1, userTypeStr.length() - 1);
				userInfo.setUserTypes(userTypeStr);
				Integer teacherId = userInfo.getTeacherId();
				Integer studentId = userInfo.getStudentId();
				for (String userType : userTypes) {
					//????????????????????????????????????????????????????????????????????????????????????
					if (SysContants.USER_TYPE_TEACHER.equals(userType)) {
						if (teacherId == null) {
							Teacher teacher = this.teacherService.findOfUser(schoolId, userId);
							if(teacher != null) {
								userInfo.setRealName(teacher.getName());
								userInfo.setTeacherId(teacher.getId());
								userInfo.setTelephone(teacher.getMobile());
							}
							continue;
						}
					//???????????????????????????????????????????????????????????????????????????????????????
					} else if (SysContants.USER_TYPE_STUDENT.equals(userType)) {
						if (studentId == null) {
							Student student = this.studentService.findOfUser(schoolId, userId);
							if(student != null) {
								userInfo.setRealName(student.getName());
								userInfo.setStudentId(student.getId());
								userInfo.setTelephone(student.getMobile());
							}
						}
					}
				}
			}

			List<String> roles = this.userRoleService.findRoleCodesByUserIdAndSchool(userId, schoolId, SysContants.SYSTEM_APP_ID);
			for (String role : roles) {
				if ("SCHOOL_LEADER".equals(role)) {
					userInfo.setCurrentRoleCode("SCHOOL_LEADER");
					break;
				} else if ("SCHOOL_MASTER".equals(role)) {
					userInfo.setCurrentRoleCode("SCHOOL_MASTER");
					break;
				} else if ("CLASS_MASTER".equals(role)) {
					userInfo.setCurrentRoleCode("CLASS_MASTER");
				} else if ("SUBJECT_TEACHER".equals(role)) {
					userInfo.setCurrentRoleCode("SUBJECT_TEACHER");
				}
			}
		}
		
		String realName = userInfo.getRealName();
		//??????????????????????????????????????????
		Profile profile = this.profileService.findByUserId(userId);
		if (profile != null) {
//			realName = realName != null && !"".equals(realName) ? realName : profile.getName() != null ? profile.getName() : "";
			realName = profile.getName() != null ? profile.getName() : realName != null && !"".equals(realName) ? realName : "";
			userInfo.setRealName(realName);
			userInfo.setNickName(profile.getNickname());
			userInfo.setIcon(profile.getIcon());
			userInfo.setEmail(profile.getEmail());
		}
		
		userInfo.setRealName(realName != null && !"".equals(realName) ? realName : username);
		
		// ????????????????????????????????????????????????
		List<UserRegion> urs = userRegionService.findByUserId(user.getId());
		if (urs != null && urs.size() > 0) {
			UserRegion ur = urs.get(0);
			userInfo.setRegionCode(ur.getRegionCode());
			userInfo.setLevel(ur.getLevel());
		}
		
		BeanUtils.copyProperties(user, userInfo);
		//??????????????????????????????????????????
		userInfo.setOriginalUsername(user.getUserName());
		userInfo.setUserName(username);
		ImAccount imAccount = imService.createIMAccountForUser(userInfo.getId(), "", "xunyun#educloud#web");
		if(imAccount != null) {
			if (!"fail".equals(imAccount.getImAccountStatus())) {
				userInfo.setImAccount(imAccount.getAccountName());
			}
		
		}



		return userInfo;
	}
}
