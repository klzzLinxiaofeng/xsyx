package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksTeam;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamCondition;

import java.util.List;
import java.util.Map;

public interface ExamWorksTeamService {
    ExamWorksTeam findExamWorksTeamById(Integer id);
	   
	ExamWorksTeam add(ExamWorksTeam examWorksTeam);
	   
	ExamWorksTeam modify(ExamWorksTeam examWorksTeam);
	   
	void remove(ExamWorksTeam examWorksTeam);
	   
	List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Page page, Order order);
	
	List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition);

	List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Page page);
	
	List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Order order);
	
	Long count();
	
	Long count(ExamWorksTeamCondition examWorksTeamCondition);

	List<ExamWorksTeam> findOfExamWorks(Integer examWorksId, Integer gradeId);

	ExamWorksTeam findUnique(Integer examWorksId, Integer teamId);

	List<Map<String, Object>> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId);

	Map<String, Object> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId, Integer teamId);

	/**
	 *	某年级考务下 已发布成绩的班级数
	 */
	Long countPublishTeam(Integer examWorksId, Integer gradeId);

	/**
	 *  某年级考务下 已发布成绩（参与统计）的学生总数
	 *  有录入分数的，统计所有科目，取最大值
	 */
	Long countPublishStudent(Integer examWorksId, Integer gradeId);

}
