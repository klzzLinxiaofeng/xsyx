package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.ExamStatMajorStudent;
import platform.education.generalTeachingAffair.vo.ExamStatMajorStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ExamStatMajorStudentService {
    ExamStatMajorStudent findExamStatMajorStudentById(Integer id);
	   
	ExamStatMajorStudent add(ExamStatMajorStudent examStatMajorStudent);
	   
	ExamStatMajorStudent modify(ExamStatMajorStudent examStatMajorStudent);
	   
	void remove(ExamStatMajorStudent examStatMajorStudent);
	   
	List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Page page, Order order);
	
	List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition);
	
	List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Page page);
	
	List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Order order);
	
	Long count();
	
	Long count(ExamStatMajorStudentCondition examStatMajorStudentCondition);
	
	
	ExamStatMajorStudent findExamStatMajorStudentByExamWorksIdAndStudentId(Integer examWorksId,Integer studentId);
	

	void createBatch(ExamStatMajorStudent[] examStatMajorStudents);
	
	
	void batchUpdateExamStatMajorStudentTotalScore(Object[] list);
	void batchUpdateExamStatMajorStudentTeamRank(Object[] list);
	void batchUpdateExamStatMajorStudentGradeRank(Object[] list);
	
	
	/**
	 * 查找某个班，某个年级的总分参考人总数
	 * @param examWorksId 考务ID
	 * @param jointExamCode 参考年级
	 * @param teamId 班级ID
	 * @return
	 */
	List<ExamStatMajorStudent> findExamStatMajorCount(Integer examWorksId,String jointExamCode,Integer teamId,Integer flag);
	
	
}
