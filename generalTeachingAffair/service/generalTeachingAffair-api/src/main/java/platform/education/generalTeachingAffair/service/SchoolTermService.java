package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolTermVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.List;

public interface SchoolTermService {
	
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
	 * 返回学校所有学年下的所有学期记录
	 * 
	 * @param schoolId
	 * @return
	 */
	List<SchoolTerm> getSchoolTermOfSchool(Integer schoolId);
	/**
	 * 返回学校某学年的所有学期记录
	 * @param schoolId
	 * @param schoolYearId 学年的开始年份
	 * @return
	 */
	List<SchoolTerm> getSchoolTermOfYear(Integer schoolId,Integer schoolYearId);

    SchoolTerm findSchoolTermById(Integer id);
	   
	SchoolTerm add(SchoolTerm schoolTerm);
	   
	SchoolTerm modify(SchoolTerm schoolTerm);
	   
	void remove(SchoolTerm schoolTerm);
	   
	List<SchoolTerm> findSchoolTermByCondition(SchoolTermCondition schoolTermCondition, Page page, Order order);

	/**
	 * 功能描述：用于关联显示学年名称
	 * @param schoolTermCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<SchoolTermVo> findSchoolTermByConditionMore(SchoolTermCondition schoolTermCondition, Page page, Order order);
	
	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 * @param schoolTerm
	 * @return
	 */
	String abandon(SchoolTerm schoolTerm);
/**
	 * 修改所属学年下的学期
	 * @param schoolYear
	 */
	void modifyBySchoolYear(SchoolYear schoolYear);

	SchoolTerm findSchoolTermByCode(Integer schoolId, String code);
	/**
	 * 判断某一天在这个学校属于哪个学期的
	 * @param schoolId
	 * @param date
	 * @return
	 */
	SchoolTerm findSchoolYearByToday(Integer schoolId,Date date);
}
