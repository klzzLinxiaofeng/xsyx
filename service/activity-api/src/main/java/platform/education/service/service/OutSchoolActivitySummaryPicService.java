package platform.education.service.service;

import platform.education.service.model.OutSchoolActivitySummaryPic;
import platform.education.service.vo.OutSchoolActivitySummaryPicCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface OutSchoolActivitySummaryPicService {

    OutSchoolActivitySummaryPic findOutSchoolActivitySummaryPicById(Integer id);

    OutSchoolActivitySummaryPic add(OutSchoolActivitySummaryPic outSchoolActivitySummaryPic);

    OutSchoolActivitySummaryPic modify(OutSchoolActivitySummaryPic outSchoolActivitySummaryPic);

    void remove(OutSchoolActivitySummaryPic outSchoolActivitySummaryPic);

    List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Page page, Order order);

    List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition);

    List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Page page);

    List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Order order);

    Long count();

    Long count(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition);
}
