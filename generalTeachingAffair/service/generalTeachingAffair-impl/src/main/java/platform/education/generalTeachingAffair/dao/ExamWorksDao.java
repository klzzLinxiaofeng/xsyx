package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.vo.ExamWorksCondition;
import platform.education.generalTeachingAffair.vo.ExamWorksVo;

import java.util.List;

public interface ExamWorksDao extends GenericDao<ExamWorks, Integer> {

	List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Page page, Order order);
	
	ExamWorks findById(Integer id);
	
	Long count(ExamWorksCondition examWorksCondition);

	List<ExamWorks> findMajorExamWorks(Integer schoolId, String schoolYear, String termCode, String examType, Integer examRound);

	List<ExamWorks> findMajorExamWorksByType(Integer schoolId, String schoolYear, String termCode, String[] examTypes);

	List<ExamWorksVo> findClassExamWorksByTeacherId(Integer schoolId, String schoolYear, String termCode, Integer teacherId, Page page, Order order);

	/**
	 * 根据学校ID和考试开始时间查找上一次考务信息
	 * @param examDateBegin
	 * @param schoolId
	 * @return
	 */
	ExamWorks findFrontExamWorksByExamDate(String examDateBegin,Integer schoolId);

	List<ExamWorksVo> findExamWorksOfStudent(Integer schoolId, String schoolYear, String termCode, Integer userId, Page page, Order order);

	List<ExamWorks> findExamWorksByType(Integer schoolId, String schoolYear, String termCode, Boolean isJoint,Integer teamId, Page page, Order order);
}
