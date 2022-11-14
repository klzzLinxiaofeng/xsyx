package platform.szxyzxx.web.common.tags;

import java.util.List;

import platform.education.generalTeachingAffair.holder.GeneralTeachingAffairServiceHolder;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.user.holder.UserModuleServiceHolder;
import platform.education.user.model.App;
import platform.education.user.model.Group;
import platform.education.user.model.User;
import platform.education.user.service.AppService;
import platform.education.user.service.GroupService;
import platform.education.user.service.UserService;

public class DataOfUserFunctions {
	
	private final static AppService appService = UserModuleServiceHolder.getInstance().getAppService();
	
	private final static UserService userService = UserModuleServiceHolder.getInstance().getUserService();
	
	private final static GroupService groupService = UserModuleServiceHolder.getInstance().getGroupService();
	
	private final static TeacherService teacherService = GeneralTeachingAffairServiceHolder.getInstance().getTeacherService();
	
	private final static String NOT_DATA = "无";
	
	public static String getFieldVal(String type, String code) {
		String result = null;
		if("app".equals(type)) {
			try {
				if(!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					App app = appService.findAppById(id);
					if (app != null) {
						result = app.getName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result = NOT_DATA;
				}
			} catch (Exception e) {
				result = "请传入年级的主键ID";
			}
		} else if ("user".equals(type)) {
			try {
				if(!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					User user = userService.findUserById(id);
					if (user != null) {
						result = user.getUserName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result = NOT_DATA;
				}
			} catch (Exception e) {
				result = "请传入用户的主键ID";
			}
		} else if ("group".equals(type)) {
			try {
				if(!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					Group group = groupService.findGroupById(id);
					if (group != null) {
						result = group.getName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result = NOT_DATA;
				}
			} catch (Exception e) {
				result = "请传入组的主键ID";
			}
		}else if("teacher".equals(type)) {
			try {
				if(!"".equals(code)) {
					Integer id = Integer.parseInt(code);
					
					TeacherCondition condition = new TeacherCondition();
					condition.setUserId(id);
					List<Teacher> teachers = teacherService.findTeacherByCondition(condition, null, null);
					if(teachers!=null && teachers.size()>0) {
						return result = teachers.get(0).getName();
					}
					
					User user = userService.findUserById(id);
					if (user != null) {
						result = user.getUserName();
					} else {
						result = NOT_DATA;
					}
				} else {
					result ="参考库";
				}
			} catch (Exception e) {
				result = "请传入用户的主键ID";
			}
		}
		return result;
	}
	
}
