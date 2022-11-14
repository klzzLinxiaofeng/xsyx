package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.PerformancePojo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/13 15:56
 * @Description: 学生表现
 */
public interface StudentAssessDao extends GenericDao<PerformancePojo, Integer> {

	void createStuShow(String stuId, Integer teamId, Integer id);

	void deletePerformance(Integer id);

	void deletePerformanceStu(Integer id);

	List<PerformancePojo> findSutBusByGroupCondition(PerformancePojo performancePojo, Page page);

}
