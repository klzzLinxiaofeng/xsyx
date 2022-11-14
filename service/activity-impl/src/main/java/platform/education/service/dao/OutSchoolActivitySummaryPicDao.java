package platform.education.service.dao;

import platform.education.service.model.OutSchoolActivitySummaryPic;
import platform.education.service.vo.OutSchoolActivitySummaryPicCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface OutSchoolActivitySummaryPicDao extends GenericDao<OutSchoolActivitySummaryPic, Integer> {

    List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Page page, Order order);

    OutSchoolActivitySummaryPic findById(Integer id);

    Long count(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition);

    List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByActivityId(Integer activityId);
}
