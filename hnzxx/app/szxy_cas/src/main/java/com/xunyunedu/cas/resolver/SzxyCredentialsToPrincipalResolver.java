package com.xunyunedu.cas.resolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jasig.cas.authentication.principal.Credentials;
import org.jasig.cas.authentication.principal.CredentialsToPrincipalResolver;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.ParentService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.user.model.AppletVendorUserBinding;
import platform.education.user.model.Role;
import platform.education.user.model.User;
import platform.education.user.model.UserRole;
import platform.education.user.service.AppletVendorUserBindingService;
import platform.education.user.service.RoleService;
import platform.education.user.service.UserBindingService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.education.user.vo.AppletVendorUserBindingCondition;

public class SzxyCredentialsToPrincipalResolver implements CredentialsToPrincipalResolver {

	private UserService userService;

	private UserBindingService userBindingService;
	
	private AppletVendorUserBindingService appletVendorUserBindingService;

	
	private SchoolService schoolService;
	
	private UserRoleService userRoleService;
	
	private RoleService roleService;
	
	private TeacherService teacherService; 
	
	private ParentService parentService;
	
	private StudentService studentService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setUserBindingService(UserBindingService userBindingService) {
		this.userBindingService = userBindingService;
	}
	
	public void setAppletVendorUserBindingService(AppletVendorUserBindingService appletVendorUserBindingService) {
		this.appletVendorUserBindingService = appletVendorUserBindingService;
	}
	

	@Override
	public Principal resolvePrincipal(Credentials credentials) {
		UsernamePasswordCredentials up = (UsernamePasswordCredentials) credentials;
		if (up != null) {
			Map<String, Object> attrs = new HashMap<String, Object>();
			String username = up.getUsername();
			String schoolId = up.getSchoolId();
			Integer userId = this.userBindingService.findUserId(username);
			User user = this.userService.findUserById(userId);
			String password = up.getPassword();
			attrs.put("username", username);
			attrs.put("pwd", password);
			attrs.put("schoolId", schoolId);
			attrs.put("sourceUsername", user != null ? user.getUserName() : username);
			attrs.put("uid", user != null ? user.getId() : username);
			
			//加入定南业务（返回教师名称，学校名称，学校ID 只有教师角色）
			addExtendPamas(attrs,userId);
			addVendorMessage(attrs);
			Principal p = new SimplePrincipal(username, attrs);
			System.out.println(attrs);
			return p;
		}
		return null;
	}
	
	
	private void addExtendPamas(Map<String, Object> attrs,Integer userId) {
		if(attrs != null && attrs.size() > 0) {
			String name = "";
			List<UserRole> userRoleList = this.userRoleService.findByUserId(userId);
			if(userRoleList != null && userRoleList.size() > 0) {
				for(UserRole userRole :userRoleList) {
					Role role = this.roleService.findRoleById(userRole.getRole().getId());
					if(role != null ) {
						String userType = role.getUserType();
						if("1".contains(userType)) { //教师
							List<Teacher> teacherList = this.teacherService.findTeacherByUserId(userId); 
							if(teacherList != null && teacherList.size() > 0) {
								Teacher teacher = teacherList.get(0);
								name = teacher.getName();
								attrs.put("name", name); //名称
								attrs.put("ownerId", teacher.getSchoolId());
								attrs.put("sex", teacher.getSex());
								attrs.put("userType", "1");
								School school = this.schoolService.findSchoolById(teacher.getSchoolId());
								if(school != null) {
									attrs.put("schoolName", school.getName());
								}
							}
						}else if("3".contains(userType)) { //家长
							Parent parent = parentService.findUniqueByUserId(userId);
							attrs.put("name", parent.getName()); //名称
							attrs.put("userType", "3");
							
						}else if("4".contains(userType)) {
							 Student student = studentService.findStudentByUserId(userId);
							 attrs.put("name", student.getName()); //学生名称
							 attrs.put("ownerId", student.getSchoolId());
							 attrs.put("userType", "4");
							 attrs.put("sex", student.getSex());
							 School school = this.schoolService.findSchoolById(student.getSchoolId());
							 if(school != null) {
								 attrs.put("schoolName", school.getName());
							 }
						}
					}
					if(name != null || !"".equals(name)) {
						 break;
					}
				}
			}
			
		}
	}

	private void addVendorMessage(Map<String, Object> attrs) {
		Map<String, String> vendor = new HashMap<String, String>();
		AppletVendorUserBindingCondition condition = new AppletVendorUserBindingCondition();
		condition.setUserId(new Integer(attrs.get("uid").toString()));
		List<AppletVendorUserBinding> list = appletVendorUserBindingService.findAppletVendorUserBindingByCondition(condition);
		for (AppletVendorUserBinding bind : list) {
			vendor.put(bind.getVendorId().toString(), bind.getVendorUserName());
		}
		String vendorJson = JSON.toJSONString(vendor);
		attrs.put("vendor", vendorJson);
	}
	
	
	@Override
	public boolean supports(Credentials credentials) {
		return credentials != null && UsernamePasswordCredentials.class.isAssignableFrom(credentials.getClass());
	}


	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	public UserRoleService getUserRoleService() {
		return userRoleService;
	}

	public void setUserRoleService(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setParentService(ParentService parentService) {
		this.parentService = parentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public ParentService getParentService() {
		return parentService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}
