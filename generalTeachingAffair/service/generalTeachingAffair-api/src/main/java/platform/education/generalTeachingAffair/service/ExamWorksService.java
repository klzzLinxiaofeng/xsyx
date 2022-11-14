package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.vo.ExamWorksCondition;
import platform.education.generalTeachingAffair.vo.ExamWorksVo;

import java.util.Date;
import java.util.List;

public interface ExamWorksService {
    ExamWorks findExamWorksById(Integer id);
	   
	ExamWorks add(ExamWorks examWorks);
	   
	ExamWorks modify(ExamWorks examWorks);
	   
	void remove(ExamWorks examWorks);
	   
	List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Page page, Order order);
	
	List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition);
	
	List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Page page);
	
	List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Order order);
	
	Long count();
	
	Long count(ExamWorksCondition examWorksCondition);

	/**
	 * 查询学校统考记录（按学期或全部学期）
	 */
	List<ExamWorks> findMajorExamWorks(Integer schoolId, String schoolYear, String termCode);

	/**
	 * 查询某种统考类型下的考试记录（按学期）
	 */
	List<ExamWorks> findMajorExamWorksByType(Integer schoolId, String schoolYear, String termCode, String type);

	/**
	 * 获取教师所有的班级测试记录
	 */
	List<ExamWorksVo> findClassExamWorksByTeacherId(Integer schoolId, String schoolYear, String termCode, Integer teacherId, Page page, Order order);

	/**
	 * 初始化统考考务
	 */
	void initJointExam(Integer schoolId, String schoolYear, String termCode, String examType, Integer examRound, String name,
                       Date beginDate, Date endDate, Boolean isShowRanking, String gradeIds, Integer teacherId
    );

	/**
	 * 添加单个年级的统考
	 */
	void initJointExamOfGrade(Integer schoolId, String schoolYear, String termCode, String examType,
							  String name, Integer teacherId, Integer examWorksId, Integer gradeId);

	/**
	 * 初始化班级测试
	 */
	int initClassExam(Integer schoolId, String schoolYear, String termCode, Integer gradeId, Integer teamId, String subjectCode,
                       String examType, Integer examRound, String name, Date beginDate, Date endDate, Boolean isShowRanking,
                       Float fullScore, Float highScore, Float lowScore, Float passScore, Integer teacherId
    );

	/**
	 * 删除单个年级的统考
	 */
	void deleteExamOfGrade(Integer examWorksId, Integer gradeId);

	/**
	 * 修改班级测试
	 */
	void modifyClassExam(Integer examWorksId, String name, Date beginDate, Date endDate, Float fullScore, Float highScore, Float lowScore, Float passScore);

	/**
	 * 删除班级测试
	 */
	void deleteClassExam(Integer examWorksId);

	/**
	 * 获取学生的考务记录
	 * @param schoolId
	 * @param schoolYear
	 * @param termCode
	 * @param userId
	 * @param page
	 * @param order
	 * @return
	 */
	List<ExamWorksVo> findExamWorksOfStudent(Integer schoolId, String schoolYear, String termCode, Integer userId, Page page, Order order);


	List<ExamWorks> findExamWorksByType(Integer schoolId, String schoolYear, String termCode, Boolean isJoint,Integer teamId, Page page, Order order);
}
