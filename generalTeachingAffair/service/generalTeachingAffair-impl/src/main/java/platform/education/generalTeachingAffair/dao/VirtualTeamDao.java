package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.VirtualTeam;
import platform.education.generalTeachingAffair.vo.VirtualTeamCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface VirtualTeamDao extends GenericDao<VirtualTeam, Integer> {

	List<VirtualTeam> findVirtualTeamByCondition(VirtualTeamCondition virtualTeamCondition, Page page, Order order);
	
	VirtualTeam findById(Integer id);
	
	Long count(VirtualTeamCondition virtualTeamCondition);

	List<VirtualTeam> findByGradeId(Integer gradeId);

	List<VirtualTeam> findByGradeIdAndName(Integer schoolId, String year, Integer gradeId, String name);
}
