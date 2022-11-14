package platform.szxyzxx.web.oa.utils;

import java.util.ArrayList;
import java.util.List;
import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.DepartmentService;
import platform.education.generalTeachingAffair.service.DepartmentTeacherService;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 去掉重复的值  
 * 2015-10-22
 */

public class CommonUtil {
	
	/**
	 * @function 去掉List<Integer> 中的重复值
	 * @param userList
	 * @return List<Integer>
	 */
	public static List<Integer> distinctInteger(List<Integer> userList){
		List<Integer> list = new ArrayList<Integer>();
		for(Integer user : userList){
			boolean flag = true;
			for(Integer uList : list){
				if(user.equals(uList)){
					flag = false;
					break;
				}
			}
			if(flag){
				list.add(user);
			}
		}
		return list;
	}
	
	/**
	 * @function 去掉List<String> 中的重复值
	 * @param userList
	 * @return List<String>
	 */
	public static List<String> distinctString(String[] stringArray){
		List<String> list = new ArrayList<String>();
		for(String user : stringArray){
			boolean flag = true;
			for(String uList : list){
				if(user.equals(uList)){
					flag = false;
					break;
				}
			}
			if(flag){
				list.add(user);
			}
		}
		return list;
	}
	
	/**
	 * @function 根据userId 去掉List<Teacher> 中的重复值
	 * @param List<Teacher>
	 * @return List<Teacher>
	 */
	public static List<Teacher> distinctTeacherOfUserId(List<Teacher> tList){
		List<Teacher> list = new ArrayList<Teacher>();
		for(int i=0;i<tList.size();i++){
			boolean flag = true;
			for(Teacher t2 : list){
				if(tList.get(i).getUserId().equals(t2.getUserId())){
					flag = false;
				}
			}
			if(flag){
				list.add(tList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * @function 根据teacherId 去掉List<Teacher> 中的重复值
	 * @param List<Teacher>
	 * @return List<Teacher>
	 */
	public static List<Teacher> distinctTeacherOfTeacherId(List<Teacher> tList){
		List<Teacher> list = new ArrayList<Teacher>();
		for(int i=0;i<tList.size();i++){
			boolean flag = true;
			for(Teacher t2 : list){
				if(tList.get(i).getId().equals(t2.getId())){
					flag = false;
				}
			}
			if(flag){
				list.add(tList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * 根据teacherId 去掉 List<DepartmentTeacher>中的重复值
	 * @param List<DepartmentTeacher> departmentTeacherList
	 * @return List<DepartmentTeacher> departmentTeacher
	 */
	public static List<DepartmentTeacher> distinctDepartmentTeacherOfTeacherId(List<DepartmentTeacher> tList){
		List<DepartmentTeacher> list = new ArrayList<DepartmentTeacher>();
		for(int i=0;i<tList.size();i++){
			boolean flag = true;
			for(DepartmentTeacher t2 : list){
				if(tList.get(i).getTeacherId().equals(t2.getTeacherId())){
					flag = false;
				}
			}
			if(flag){
				list.add(tList.get(i));
			}
		}
		return list;
	}
	
	/**
	 * @function 根据学校查找所有部门列表
	 * * @param user
	 * @return List<Department>
	 */
	public static List<Department> findDepartment(DepartmentService departmentService,@CurrentUser UserInfo user){
		DepartmentCondition departmentCondition = new DepartmentCondition();
		departmentCondition.setSchoolId(user.getSchoolId());
		departmentCondition.setIsDelete(false);
		List<Department> dList = departmentService.findDepartmentByCondition(
				departmentCondition, null, null);
		return dList;
	}
	
	
	
	/**
	 * @function 根据departmentId查找对应部门的教师部门信息
	 * @param departmentId
	 * * @param user
	 * @return List<DepartmentTeacher>
	 */
	public static List<DepartmentTeacher> findTeacherOfDepartmentId(DepartmentTeacherService departmentTeacherService,Integer departmentId,@CurrentUser UserInfo user){
		DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setDepartmentId(departmentId);
		condition.setIsDeleted(false);
		List<DepartmentTeacher> departmentList = departmentTeacherService
				.findDepartmentTeacherByCondition(condition,null,null);
		return departmentList;
	}
	
	
	/**
	 * @function 根据teacherId查找对应部门的教师部门信息
	 * * @param user
	 * @return List<DepartmentTeacher>
	 */
	public static List<DepartmentTeacher> findTeacherOfTeacherId(DepartmentTeacherService departmentTeacherService,@CurrentUser UserInfo user){
		DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setTeacherId(user.getTeacherId() == null ? -1 : user.getTeacherId());
		condition.setIsDeleted(false);
		List<DepartmentTeacher> departmentList = departmentTeacherService
				.findDepartmentTeacherByCondition(condition,null,null);
		return departmentList;
	}
	
	
	/**
	 * @function 根据teacherId关联department表查找对应部门的教师部门信息
	 * * @param user
	 * @return List<DepartmentTeacher>
	 */
	public static List<DepartmentTeacher> findTeacherOfTeacherIdMore(DepartmentTeacherService departmentTeacherService,@CurrentUser UserInfo user){
		DepartmentTeacherCondition condition = new DepartmentTeacherCondition();
		condition.setSchoolId(user.getSchoolId());
		condition.setTeacherId(user.getTeacherId() == null ? -1 : user.getTeacherId());
		condition.setIsDeleted(false);
		List<DepartmentTeacher> departmentList = departmentTeacherService
				.findDepartmentTeacherByConditionMore(condition);
		return departmentList;
	}
	
	/**
	 * @function 根据departmentId关联teacher表查找对应部门的教师部门信息
	 * * @param user
	 * @return List<DepartmentTeacher>
	 */
	public static List<DepartmentTeacher> findTeacherOfdepartmentIdMore(DepartmentTeacherService departmentTeacherService,@CurrentUser UserInfo user,Integer departmentId){
		DepartmentTeacherCondition dtCondition = new DepartmentTeacherCondition();
		dtCondition.setSchoolId(user.getSchoolId());
		dtCondition.setDepartmentId(departmentId);
		dtCondition.setIsDeleted(false);
		List<DepartmentTeacher> dtList = departmentTeacherService
				.findDepartmentTeacherByCondition1(dtCondition, null,
						null);
		return dtList;
	}
	
}
