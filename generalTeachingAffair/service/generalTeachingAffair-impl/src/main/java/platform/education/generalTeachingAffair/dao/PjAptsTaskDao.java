package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjAptsTask;
import platform.education.generalTeachingAffair.vo.AptsTeamStuentCount;
import platform.education.generalTeachingAffair.vo.PjAptsTaskCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherScoreVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjAptsTaskDao extends GenericDao<PjAptsTask, java.lang.Integer> {

	List<PjAptsTask> findPjAptsTaskByCondition(PjAptsTaskCondition pjAptsTaskCondition, Page page, Order order);
	
	PjAptsTask findById(Integer id);
	
	Long count(PjAptsTaskCondition pjAptsTaskCondition);
	
	List<TeamTeacherScoreVo> findStatisticsByCondition(PjAptsTaskUserCondition condition);
	
	List<AptsTeamStuentCount> findTeamStuentCount(Integer schoolId,String schoolYear);


}
