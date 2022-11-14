package platform.education.generalTeachingAffair.service;

import java.util.List;

import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.vo.SubjectGradeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public interface SubjectGradeService {
	
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

	SubjectGrade findSubjectGardeById(Integer id);
	   
	SubjectGrade add(SubjectGrade subjectGrade);
		   
	SubjectGrade modify(SubjectGrade subjectGrade);
	
	/**
	 * 功能描述：根据学校ID,年级Code,科目Code可以查到年级科目中唯一记录
	 * @param gradeCode
	 * @param subjectCode
	 * @param schoolId
	 * @return
	 */
	SubjectGrade findSubjectGradeByGradeCodeAndSubjectCode(String gradeCode,String subjectCode, Integer schoolId);
		   
	void remove(SubjectGrade gardeSubject);
	
	/**
	 * 功能描述：根据学校ID,年级Code可以查询到本学校该年级下的科目
	 * @param schoolId
	 * @param gradeCode
	 * @return
	 */
	List<SubjectGrade> findSubjectGradeByGradeCode(Integer schoolId, String gradeCode);
	
	List<SubjectGrade> findSubjectGradeByCondition(SubjectGradeCondition subjectGradeCondition, Page page, Order order);

	void addByBatch(String gradeCode,List<Subject> sList, String stageCode,Integer schoolId);
	
	/**
	 * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
	 * @param subjectGrade
	 * @return
	 */
	String abandon(SubjectGrade subjectGrade);
}
