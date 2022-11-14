package platform.education.service.service;

import platform.education.service.model.InSchoolActivityApproval;
import platform.education.service.vo.InSchoolActivityApprovalCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface InSchoolActivityApprovalService {
    InSchoolActivityApproval findInSchoolActivityApprovalById(Integer id);

    InSchoolActivityApproval add(InSchoolActivityApproval inSchoolActivityApproval);

    InSchoolActivityApproval modify(InSchoolActivityApproval inSchoolActivityApproval);

    void remove(InSchoolActivityApproval inSchoolActivityApproval);

    List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Page page, Order order);

    List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition);

    List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Page page);

    List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Order order);

    Long count();

    Long count(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition);

    boolean batchInSchoolActivityApproval(Integer activityId, Integer approvalId);
}
