package platform.education.generalTeachingAffair.service;
import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.StudentActivity;
import platform.education.generalTeachingAffair.vo.StudentActivityCondition;

public interface StudentActivityService {
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
    StudentActivity findStudentActivityById(Integer id);
	   
	StudentActivity add(StudentActivity studentActivity);
	   
	StudentActivity modify(StudentActivity studentActivity);
	   
	void remove(StudentActivity studentActivity);
	   
	List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Page page, Order order);
	
	List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition);
	
	List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Page page);
	
	List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Order order);
	
	Long count();
	
	Long count(StudentActivityCondition studentActivityCondition);
	
	String moveTo(StudentActivity studentActivity);
}
