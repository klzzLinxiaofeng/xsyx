package platform.education.service.service;

import org.springframework.web.multipart.MultipartFile;
import platform.education.service.model.OutSchoolActivitySummary;
import platform.education.service.vo.OutSchoolActivitySummaryCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.vo.OutSchoolActivitySummaryVo;

import java.util.List;

public interface OutSchoolActivitySummaryService {

    OutSchoolActivitySummary findOutSchoolActivitySummaryById(Integer id);

    OutSchoolActivitySummary add(OutSchoolActivitySummary outSchoolActivitySummary);

    OutSchoolActivitySummary modify(OutSchoolActivitySummary outSchoolActivitySummary);

    void remove(OutSchoolActivitySummary outSchoolActivitySummary);

    List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Page page, Order order);

    List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition);

    List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Page page);

    List<OutSchoolActivitySummary> findOutSchoolActivitySummaryByCondition(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition, Order order);

    Long count();

    Long count(OutSchoolActivitySummaryCondition outSchoolActivitySummaryCondition);

    OutSchoolActivitySummary addActivitySummary(Integer activityId, String summary, List<MultipartFile> files, String appId);

    OutSchoolActivitySummaryVo findOutSchoolActivitySummaryVoByActivityId(Integer activityId);
}
