package platform.education.generalTeachingAffair.service;

import java.util.Date;
import java.util.List;

import org.springframework.core.task.TaskExecutor;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolInfo;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.user.model.Group;
import platform.education.user.model.User;
import platform.education.user.vo.GroupCondition;

/**
 * 
 * @author zhoujin 2015-2-13 学校接口
 * 
 */
public interface SchoolService {

	/**
	 * 通过App下的某个区域下的所有学校记录
	 * 
	 * @param appId
	 * @param regionCode
	 */
	List<School> getSchoolOfRegion(Integer appId, String regionCode, Page page, Order order);

	/**
	 * 通过主键ID 获取学校实例
	 * 
	 * @param id
	 * @return
	 */
	School findSchoolById(Integer id);

	/**
	 * 添加学校
	 * 
	 * @param school
	 * @return
	 */
	School add(School school);
	
	public School add(School school,Integer sys_appId,TaskExecutor taskExcutor) throws Exception;

	/**
	 * 修改学校
	 * 
	 * @param school
	 * @return
	 */
	School modify(School school);

	/**
	 * 删除学校
	 * 
	 * @param school
	 */
	void remove(School school);

	/**
	 * 根据动态条件查询
	 * 
	 * @param schoolCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<School> findSchoolByCondition(SchoolCondition schoolCondition, Page page, Order order);

	/**
	 * 通过学校名称去查询
	 * 
	 * @return
	 */
	public List<School> findSchoolByName(String name);

	/**
	 * 查询学校代码
	 * 
	 * @param code
	 * @return
	 */
	public List<School> findSchoolByCode(String code);

	/**
	 * 学校ID 返回学校主要信息
	 */
	public SchoolInfo findSchoolInfoById(Integer id);

	/**
	 * @Method findSchoolInfoByCondition
	 * @Function 功能描述：
	 * @param condition
	 * @param page
	 * @param order
	 * @return
	 * @Date 2015年4月27日
	 */
	public List<SchoolInfo> findSchoolInfoByCondition(SchoolCondition condition, Page page, Order order);

	/**
	 * @Method findSchoolInfoByCondition
	 * @Function 功能描述：保存学校信息---（最小化保存）
	 * @param school
	 * @param type
	 *            0=Sysadmin,1=School,2=Unit
	 * @param sys_appId
	 * @return
	 * @Date 2015年8月3日
	 */
	School addSchool(School school, String type, Integer sys_appId);

	/**
	 * @Method findUserBySchoolAndGroupAndRoleId
	 * @Function 功能描述：根据学校、组、角色ID查询出所有用户
	 * @param schoolCondition
	 * @param group
	 * @param roleId
	 * @param page
	 * @param order
	 * @return
	 * @Date 2015年8月5日
	 */
	List<User> findUserBySchoolAndGroupAndRoleId(SchoolCondition schoolCondition, Group group, Integer roleId, Page page, Order order);

	/**
	 * @Method findGroupBySchoolCondition
	 * @Function 功能描述：根据组、学校查询出所有组
	 * @param groupCondition
	 * @param school
	 * @param page
	 * @param order
	 * @return
	 */
	List<Group> findGroupBySchoolCondition(GroupCondition groupCondition, SchoolCondition schoolCondition, Page page, Order order);


	/**
	 * 获取某个时间段后，有所变动的学校数据
	 * @param isGetNew	是否获取新增数据(id!=null时有效)
	 * @param isOrder	是否排序(按modify_date和id升序排列)
	 * @return
	 */
	List<School> findIncrementData(Boolean isDelete, Date modifyDate, Integer schoolId, Boolean isGetNew);

	School findSchoolByUserId(Integer userId);


}
