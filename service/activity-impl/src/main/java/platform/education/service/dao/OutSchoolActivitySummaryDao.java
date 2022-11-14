package platform.education.service.dao;

import platform.education.service.model.OutSchoolActivitySummary;
import platform.education.service.vo.OutSchoolActivitySummaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.service.vo.OutSchoolActivitySummaryVo;

import java.util.List;

public interface OutSchoolActivitySummaryDao extends GenericDao<OutSchoolActivitySummary, Integer> {

    List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Page page, Order order);

    OutSchoolActivitySummary findById(Integer id);

    Long count(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition);

    OutSchoolActivitySummaryVo findOutSchoolActivitySummaryVoByActivityId(Integer activityId);
}
