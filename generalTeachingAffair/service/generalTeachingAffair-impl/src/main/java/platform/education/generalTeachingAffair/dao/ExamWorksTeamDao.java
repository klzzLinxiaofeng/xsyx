package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamWorksTeam;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamCondition;

import java.util.List;
import java.util.Map;

public interface ExamWorksTeamDao extends GenericDao<ExamWorksTeam, Integer> {

	List<ExamWorksTeam> findExamWorksTeamByCondition(ExamWorksTeamCondition examWorksTeamCondition, Page page, Order order);
	
	ExamWorksTeam findById(Integer id);
	
	Long count(ExamWorksTeamCondition examWorksTeamCondition);

	List<ExamWorksTeam> findOfExamWorks(Integer examWorksId, Integer gradeId);

	ExamWorksTeam findUnique(Integer examWorksId, Integer teamId);

	List<Map<String, Object>> findOfExamWorksWithScore(Integer examWorksId, Integer gradeId, Integer teamId);

	Long countPublishTeam(Integer examWorksId, Integer gradeId);

	Long countPublishStudent(Integer examWorksId, Integer gradeId);
}
