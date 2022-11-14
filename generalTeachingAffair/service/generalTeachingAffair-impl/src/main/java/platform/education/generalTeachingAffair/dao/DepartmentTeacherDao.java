package platform.education.generalTeachingAffair.dao;

import java.util.Date;
import java.util.List;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.TeacherVo;

public interface DepartmentTeacherDao extends GenericDao<DepartmentTeacher, java.lang.Integer>{
	
	public List<DepartmentTeacher> findDepartmentTeacherByCondition(DepartmentTeacherCondition departmentTeacherCondition, Page page, Order order);
	
	public DepartmentTeacher findById(Integer id);
	
	public DepartmentTeacher findUnique(Integer teachId, Integer deptId);
	
	public List<DepartmentTeacher>findDepartmentTeacherByConditionMore(DepartmentTeacherCondition departmentTeacherCondition);
	

	public List<DepartmentTeacher>findDepartmentTeacherByCondition1(DepartmentTeacherCondition departmentTeacherCondition,Page page, Order order);

	public List<DepartmentTeacher> findByDeptId(Integer deptId);
	
	public List<DepartmentTeacher> findByTeacherId(Integer deptId);
	
	//根据部门id得到对应 的教师列表集合
	public List<DepartmentTeacher>findByDepartmentId(Integer departmentId);

	//根据教师ID和学校ID获取某教所在的部门 2016-1-27
	public List<DepartmentTeacher> findDepartmentTeacherByTeacherIdAndSchoolId(
				Integer teacherId, Integer schoolId);
	
	/**
	 * @function 照片管理模块
	 * @param departmentId
	 * @return
	 * @date 2016年03月03日
	 */
	public List<DepartmentTeacherVo> findDepartmentTeacherVoByDepartmentId(Integer departmentId);
	
	/**
	 * @function 没有图片的老师
	 * @param departmentId
	 * @return
	 * @date 2016年03月04日
	 */
	public Long countNoPhotoDepartmentId(Integer departmentId);
	
	/**
	 * @function 按部门统计人数
	 * @param departmentId
	 * @return
	 * @date 2016年03月04日
	 */
	public Long countByDepartmentId (Integer departmentId);
	
	/**
	 * @function 照片统计模块 查询出一个部门没有照片的人的姓名
	 * @param departmentId
	 * @return
	 * @date 2016年03月04日
	 */
	public String findNoPhotoName(Integer departmentId);
	
	/**
	 * @function 照片统计模块 查询出一个部门有照片的人的姓名
	 * @param departmentId
	 * @return
	 * @date 2016年03月04日
	 */
	public String findHasPhotoName(Integer departmentId);

	List<DepartmentTeacher> findIncrementByTeacherId(Integer schoolId, Integer teacherId, Date modifyDate, Boolean isDeleted);

	public List<TeacherVo> findTeacherInfoByDepartmentId(Integer departmentId);

	List<DepartmentTeacherVo> findDepartmentTeacherBySchool(Integer schoolId);
}
