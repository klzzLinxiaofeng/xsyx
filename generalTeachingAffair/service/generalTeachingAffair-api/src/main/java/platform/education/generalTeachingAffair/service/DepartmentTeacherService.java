package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.DepartmentTeacher;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherCondition;
import platform.education.generalTeachingAffair.vo.DepartmentTeacherVo;
import platform.education.generalTeachingAffair.vo.TeacherVo;

import java.util.Date;
import java.util.List;

/**
 * 部门教师
 * 
 * @author admin
 *
 */
public interface DepartmentTeacherService {

	/**
	 * 当前接口crud操作 成功时返回的状态值
	 */
	public final static String OPERATE_SUCCESS = "success";

	/**
	 * 当前接口crud操作 失败时返回的状态值
	 */
	public final static String OPERATE_FAIL = "fail";

	/**
	 * 系统异常造成的操作失败 系统返回的状态值
	 */
	public final static String OPERATE_ERROR = "error";

	DepartmentTeacher findDepartmentTeacherById(Integer id);

	DepartmentTeacher add(DepartmentTeacher departmentTeacher);

	public String addBatch(Integer deptId, String[] teachIds, Integer schoolId);

	DepartmentTeacher modify(DepartmentTeacher departmentTeacher);

	void remove(DepartmentTeacher departmentTeacher);

	List<DepartmentTeacher> findDepartmentTeacherByCondition(DepartmentTeacherCondition departmentTeacherCondition, Page page, Order order);
	
	List<DepartmentTeacher> findDepartmentTeacherByCondition1(DepartmentTeacherCondition departmentTeacherCondition, Page page, Order order);
	
	List<DepartmentTeacher>findDepartmentTeacherByConditionMore(DepartmentTeacherCondition departmentTeacherCondition);

	String remove(Integer teachId, Integer deptId);
	
	String abandon(Integer teachId, Integer deptId);
	
	String recover(Integer teachId, Integer deptId);
	
	//根据部门id得到对应 的教师列表集合
    List<DepartmentTeacher>findByDepartmentId(Integer departmentId);

    //根据部门ID 和 所有教师Id的先后顺序 进行排序
	void updateSort(String[] teachIds, Integer deptId,Integer schoolId);
	//根据教师ID和学校ID获取该教师所在的所有部门  2016-1-27
	List<DepartmentTeacher> findDepartmentTeacherByTeacherIdAndSchoolId(Integer teacherId,Integer schoolId);
	
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
	
	/**
	 * @function 批量删除一个部门的教师(逻辑删除)
	 * @param departmentId
	 * @date 2016-11-11
	 */
	public void batchDelete(Integer departmentId);

	List<DepartmentTeacher> findIncrementByTeacherId(Integer schoolId, Integer teacherId, Date modifyDate, Boolean isDeleted);
	/**
	 * 通过departmentId找所有部门成员的信息
	 * @param departmentId
	 * @return
	 */
	List<TeacherVo> findTeacherInfoByDepartmentId(Integer departmentId);

	void removeByTeacherId(Integer id);

	/**
	 * 获取学校所有的教师及其部门信息
	 * @param schoolId
	 * @return
	 */
	List<DepartmentTeacherVo> findDepartmentTeacherBySchool(Integer schoolId);
}
