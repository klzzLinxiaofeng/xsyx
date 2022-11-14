package platform.education.commonResource.web.common.util.holder;

import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.user.service.AppService;
import platform.education.user.service.GroupService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;

public class UserModuleServiceHolder {

	private static UserModuleServiceHolder instance;

	private AppService appService;

	private ProfileService profileService;

	private GroupService groupService;

	private UserService userService;
	
	private TeacherService teacherService;

	public UserModuleServiceHolder() {
		instance = this;
	}

	public static UserModuleServiceHolder getInstance() {
		return instance;
	}

	public AppService getAppService() {
		return appService;
	}

	public void setAppService(AppService appService) {
		this.appService = appService;
	}

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

}
