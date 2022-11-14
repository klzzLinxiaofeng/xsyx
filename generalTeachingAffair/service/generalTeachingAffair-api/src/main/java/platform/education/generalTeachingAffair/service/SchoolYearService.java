package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolYearService {
	
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
	 * 功能描述：返回学校的所有学年记录
	 * @param schoolId
	 * @return
	 * 
	 */
	List<SchoolYear> findSchoolYearOfSchool(Integer schoolId);
	
	/**
	 * 功能描述：通过当前学年返回学年记录
	 * @param year
	 * @return
	 */
	SchoolYear findSchoolYearByYear(SchoolYearCondition schoolYearCondition);
	
    SchoolYear findSchoolYearById(Integer id);
	   
	SchoolYear add(SchoolYear schoolYear);
	   
	SchoolYear modify(SchoolYear schoolYear);
	   
	void remove(SchoolYear schoolYear);
	   
	List<SchoolYear> findSchoolYearByCondition(SchoolYearCondition schoolYearCondition, Page page, Order order);

	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 * @param schoolYear
	 * @return
	 */
	String abandon(SchoolYear schoolYear);
	
	SchoolYear findByYearAndSchoolId(Integer schoolId, String year);
}
