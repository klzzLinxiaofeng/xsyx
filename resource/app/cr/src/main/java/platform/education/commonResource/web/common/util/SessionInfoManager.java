package platform.education.commonResource.web.common.util;

import org.apache.shiro.session.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.user.contants.GroupContants;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.User;
import platform.education.user.model.UserRegion;
import platform.education.user.service.*;

import java.util.List;

/**
 * <p>Title:SessionInfoManager.java</p>
 * <p>Description:教育基础管理平台</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2015年7月11日
 */
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

	public void setUserInfoToSession(String username, Session session) {
		session.setAttribute(SysContants.CURRENT_USER, this.getUserInfo(username));
	}

	public UserInfo getUserInfo(String username) {
		UserInfo userInfo = new UserInfo();

		Integer schoolId = null;

		Integer userId = this.userBindingService.findUserId(username);

		User user = this.userService.findUserById(userId);

		List<Group> groups = this.groupUserService.findGroups(userId, GroupContants.TYPE_SCHOOL);
		
		//设置当前用户所登录使用的账号
		userInfo.setUsername(username);
		
		// 获取当前用户所属的Group 和 学校ID
		if (groups.size() > 0) {
			Group group = groups.get(0);
			if (group != null) {
				userInfo.setGroupId(group.getId());
				schoolId = group.getOwnerId();
				userInfo.setSchoolId(schoolId);
				SchoolInfo schoolInfo = this.schoolService.findSchoolInfoById(schoolId);
				if (schoolInfo != null) {
					// 获取当前用户所属的学校姓名
					userInfo.setSchoolName(schoolInfo.getName());
					userInfo.setSchoolLogo(schoolInfo.getEntityId_image());
					userInfo.setSchooUUID(schoolInfo.getUuid());
					// 获取当前用户所属的学校的学段信息
					String stageCode = schoolInfo.getStageScope();

					if (stageCode == null) {
						stageCode = "2,3,4";
					}

					if (stageCode != null) {
						stageCode = stageCode.replace(",-1", "").replace("-1,", "");
						userInfo.setStageCodes(stageCode.split(","));
						userInfo.setStageCode(stageCode);
					}
					// 获取学校的当前学期 包括学年code以及学期code
					SchoolTermCurrent currentTerm = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
					if (currentTerm != null) {
						userInfo.setSchoolYear(currentTerm.getSchoolYear());
						userInfo.setSchoolTermCode(currentTerm.getSchoolTermCode());
					}
				}
			}
		}
		if (schoolId != null) {
			// 获取当前用户所拥有的角色
			List<String> userTypes = this.userRoleService.findRoleTypesByUserId(userId, SysContants.SYSTEM_APP_ID);
			if(userTypes != null && userTypes.size() > 0) {
				//设置当前用户类型
				String userTypeStr = userTypes.toString();
				userTypeStr = userTypeStr.substring(1, userTypeStr.length() - 1);
				userInfo.setUserTypes(userTypeStr);
				Integer teacherId = userInfo.getTeacherId();
				Integer studentId = userInfo.getStudentId();
				for (String userType : userTypes) {
					//如果当前用户所属类型为教师，即从教师表获取当前用户的姓名
					if (SysContants.USER_TYPE_TEACHER.equals(userType)) {
						if (teacherId == null) {
							Teacher teacher = this.teacherService.findOfUser(schoolId, userId);
							if(teacher != null) {
								userInfo.setRealName(teacher.getName());
								userInfo.setTeacherId(teacher.getId());
							}
							continue;
						}
					//如果当前用户所属类型为学生，即从学生表获取当期那用户的姓名
					} else if (SysContants.USER_TYPE_STUDENT.equals(userType)) {
						if (studentId == null) {
							Student student = this.studentService.findOfUser(schoolId, userId);
							if(student != null) {
								userInfo.setRealName(student.getName());
								userInfo.setStudentId(student.getId());
							}
						}
					}
				}
			}
			
		}
		
		String realName = userInfo.getRealName();
		//获取当前用户的基本信息，假如
		Profile profile = this.profileService.findByUserId(userId);
		if (profile != null && !"".equals(profile.getName())) {
			realName = realName != null && !"".equals(realName) ? realName : profile.getName();
			userInfo.setRealName(realName);
			userInfo.setNickName(profile.getNickname());
			userInfo.setTelephone(profile.getMobile());
			userInfo.setIcon(profile.getIcon());
		}
		
		userInfo.setRealName(realName != null && !"".equals(realName) ? realName : username);
		
		// 设置当前用户所管理的区域以及级别
		List<UserRegion> urs = userRegionService.findByUserId(user.getId());
		if (urs != null && urs.size() > 0) {
			UserRegion ur = urs.get(0);
			userInfo.setRegionCode(ur.getRegionCode());
			userInfo.setLevel(ur.getLevel());
		}
		
		userInfo.setUserId(userId);
		
		BeanUtils.copyProperties(user, userInfo);
		return userInfo;
	}
}
