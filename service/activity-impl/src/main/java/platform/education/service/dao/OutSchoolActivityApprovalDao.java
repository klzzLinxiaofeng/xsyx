package platform.education.service.dao;

import platform.education.service.model.OutSchoolActivityApproval;
import platform.education.service.vo.ApprovalVo;
import platform.education.service.vo.OutSchoolActivityApprovalCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.Date;
import java.util.List;

public interface OutSchoolActivityApprovalDao extends GenericDao<OutSchoolActivityApproval, Integer> {

    List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Page page, Order order);

    OutSchoolActivityApproval findById(Integer id);

    Long count(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition);

    /**
     * 查找已结束校外活动
     *
     * @param time 时间
     */
    List<Integer> findOutSchoolActivityApprovalIdByEnded(Date time);

    /**
     * 查找审批状态 COUNT 值
     *
     * @param userId 审批人
     */
    List<ApprovalVo> findOutSchoolActivityApprovalGetStatusByUserId(Integer userId);
}
