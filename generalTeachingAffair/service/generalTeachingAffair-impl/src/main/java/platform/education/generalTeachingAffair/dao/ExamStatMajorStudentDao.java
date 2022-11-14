package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ExamStatMajorStudent;
import platform.education.generalTeachingAffair.vo.ExamStatMajorStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ExamStatMajorStudentDao extends GenericDao<ExamStatMajorStudent, Integer> {

	List<ExamStatMajorStudent> findExamStatMajorStudentByCondition(ExamStatMajorStudentCondition examStatMajorStudentCondition, Page page, Order order);
	
	ExamStatMajorStudent findById(Integer id);
	
	Long count(ExamStatMajorStudentCondition examStatMajorStudentCondition);
	ExamStatMajorStudent findExamStatMajorStudentByExamWorksIdAndStudentId(Integer examWorksId,Integer studentId);
	

	void createBatch(ExamStatMajorStudent[] examStatMajorStudents);
	
	//批量更新
	void batchUpdateExamStatMajorStudentTotalScore(Object[] list);
	void batchUpdateExamStatMajorStudentTeamRank(Object[] list);
	void batchUpdateExamStatMajorStudentGradeRank(Object[] list);

	/**
	 * 查找某个班，某个年级的总分参考人总数
	 * @param examWorksId 考务ID
	 * @param jointExamCode 参考年级
	 * @param teamId 班级ID
	 * @param flag 是否查询总分为空的人
	 * @return
	 */
	List<ExamStatMajorStudent> findExamStatMajorCount(Integer examWorksId,String jointExamCode,Integer teamId,Integer flag);
}
