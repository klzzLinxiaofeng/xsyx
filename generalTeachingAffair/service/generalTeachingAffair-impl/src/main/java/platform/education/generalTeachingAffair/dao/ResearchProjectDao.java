package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ResearchProject;
import platform.education.generalTeachingAffair.vo.ResearchProjectCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ResearchProjectDao extends GenericDao<ResearchProject, java.lang.Integer> {

	List<ResearchProject> findResearchProjectByCondition(ResearchProjectCondition researchProjectCondition, Page page, Order order);
	
	ResearchProject findById(Integer id);
	
	Long count(ResearchProjectCondition researchProjectCondition);
	
}
