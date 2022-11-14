package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.vo.SchoolTermCurrentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolTermCurrentService {
	
	/**
	 * 当前接口操作 成功时返回的状态值
	 */
	public final static String OPERATE_SUCCESS = "success";
	
	/**
	 * 当前接口操作 失败时返回的状态值
	 */
	public final static String OPERATE_FAIL = "fail";
	
	/**
	 * 返回学校当前学期记录
	 * @param schoolId
	 * @return
	 */
	List<SchoolTermCurrent> findCurrentSchoolYear(Integer schoolId);
	/***
	 * 根据学校ID和学年ID 获取当前学年
	 * @param schoolId
	 * @param scoolYearId
	 * @return
	 */
	SchoolTermCurrent findSchoolTermCurrentBySchoolIdAndSchoolYearId(Integer schoolId,Integer scoolYearId);
	
    SchoolTermCurrent findSchoolTermCurrentById(Integer id);
	   
	SchoolTermCurrent add(SchoolTermCurrent schoolTermCurrent);
	   
	SchoolTermCurrent modify(SchoolTermCurrent schoolTermCurrent);
	   
	void remove(SchoolTermCurrent schoolTermCurrent);
	   
	List<SchoolTermCurrent> findSchoolTermCurrentByCondition(SchoolTermCurrentCondition schoolTermCurrentCondition, Page page, Order order);

	/**
	 * 功能描述：根据schoolTerm传入的ID增加或者修改学校的当前学期
	 * @param id
	 * @return
	 */
	String setCurrentSchoolTerm(Integer schoolTermId);
	
	/**
	 * 功能描述： 通过学校ID查找该学校当前学期
	 * @param schoolId
	 * @return
	 */
	SchoolTermCurrent findSchoolTermCurrentBySchoolId(Integer schoolId);
}
