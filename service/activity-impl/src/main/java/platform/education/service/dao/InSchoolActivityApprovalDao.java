package platform.education.service.dao;

import platform.education.service.model.InSchoolActivityApproval;
import platform.education.service.vo.InSchoolActivityApprovalCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.Date;
import java.util.List;

public interface InSchoolActivityApprovalDao extends GenericDao<InSchoolActivityApproval, Integer> {

    List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Page page, Order order);

    InSchoolActivityApproval findById(Integer id);

    Long count(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition);

    List<InSchoolActivityApproval> findInSchoolActivityApprovalByActivity(Date startTime, Date endTime, Integer roomId, Integer activityId);
}
