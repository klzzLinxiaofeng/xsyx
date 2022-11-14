package platform.education.generalTeachingAffair.service;
import java.util.Date;
import java.util.List;

import platform.education.generalTeachingAffair.model.Department;
import platform.education.generalTeachingAffair.vo.DepartmentCondition;
import platform.education.generalTeachingAffair.vo.DepartmentVo;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
/**
 * 
 * @author zhoujin
 * 部门接口
 *
 */
public interface DepartmentService {
	
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
	
	/**
	 * 存在子部门机构
	 */
	public final static String EXIST_CHILDRES = "exist_childres";
	
	
	/**
	 * 通过学校ID,查询出该学校下所有的部门列表
	 * @param schoolId
	 * @return
	 */
	List<Department> findDepartmentBySchoolId(Integer schoolId,Page page, Order order);

	List<Department> findBySchoolId(Integer schoolId);

	/**
	 * 通过部门父ID，查出所有子部门
	 * @param parentId
	 * @return
	 */
	List<Department> findDepartmentByParentId(Integer parentId);
	
	/**
	 * 通过部门主键ID,获取部门的相关信息
	 * @param id
	 * @return
	 */
    Department findDepartmentById(Integer id);
    
    /**
	 * 通过部门主键ID,获取部门的相关信息
	 * @param id
	 * @return
	 */
    DepartmentVo findDepartmentById1(Integer id);
    
    
    /**
     * 添加部门
     * @param department
     * @return
     */
	Department add(Department department);
	
	/**
	 * 修改部门
	 * @param department
	 * @return
	 */
	Department modify(Department department);
	/**
	 * 删除部门   
	 * @param department
	 */
	String remove(Department department);
	
	/**
	 * 根据Id使部门人数加1
	 * @param id
	 * @return
	 */
	public int updateIncreaseDepatrmentNum(Integer id);
	
	
	/**
	 * 根据Id使部门人数减1
	 * @param id
	 * @return
	 */
	public int updateReduceDepatrmentNum(Integer id);
	
	/**
	 * @param departmentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<Department> findDepartmentByCondition(DepartmentCondition departmentCondition, Page page, Order order);
	
	/**
	 * @Method findByParentId
	 * @Function 功能描述：根据父ID获取子部门 可分页 可排序
	 * @param parentId 分部门ID
	 * @param page 分页组件
	 * @param order 排序组件
	 * @return
	 * @Date 2015年3月26日
	 */
	List<Department> findByParentId(Integer parentId, Page page, Order order);
	
	/**
	 * @Method findByParentId
	 * @Function 功能描述：根据父ID获取子部门 可分页
	 * @param parentId
	 * @param page
	 * @return
	 * @Date 2015年3月26日
	 */
	List<Department> findByParentId(Integer parentId, Page page);
	
	/**
	 * @Method findByParentId
	 * @Function 功能描述：根据父ID获取子部门 可排序
	 * @param parentId
	 * @param order
	 * @return
	 * @Date 2015年3月26日
	 */
	List<Department> findByParentId(Integer parentId, Order order);
	
	/**
	 * @Method findChildrensByCascade
	 * @Function 功能描述：
	 * @param parentId
	 * @return
	 * @Date 2015年3月26日
	 */
	List<Department> findChildrensByCascade(Integer parentId);
	
	/**
	 * @Method deleteByRecursive
	 * @Function 功能描述：
	 * @param department
	 * @return
	 * @Date 2015年3月26日
	 */
	public String deleteByRecursive(Department department);
	
	/**
	 * @Method abandon
	 * @Function 功能描述：禁用部门 逻辑删除部门
	 * @return
	 * @Date 2015年4月9日
	 */
	public String abandon(Department department);
	
	/**
	 * @Method recover
	 * @Function 功能描述：恢复部门 逻辑恢复部门
	 * @return
	 * @Date 2015年4月9日
	 */
	public String recover(Department department);
	
	/**
	 * @function 根据学校去查找部门的人数
	 * @param schoolId
	 * @return list
	 */
	List<Department> finDepartmentPeopleCountBySchoolId(Integer schoolId);
	
	/**
	 * 修改排序号 
	 * @param ids
	 */
	void modifyOrder(String ids);
	
	//根据部门名称和学校ID获取部门  2016-1-27
	Department findDepartmentByNameAndSchoolId(String departmentName, Integer schoolId);
	/**
	 * 根据学校id获取每个部门的男女教师人数
	 * @param schoolId
	 * @return
	 */
	List<TeacherVo> findDepartmentPeopleCountBySchoolIdAndSex(Integer schoolId);

	List<Department> findIncrementByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer departmentId, Boolean isGetNew);
}
