package platform.education.service.service;

import platform.education.service.model.OutSchoolActivityApproval;
import platform.education.service.vo.ApprovalVo;
import platform.education.service.vo.OutSchoolActivityApprovalCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.List;

public interface OutSchoolActivityApprovalService {

    OutSchoolActivityApproval findOutSchoolActivityApprovalById(Integer id);

    OutSchoolActivityApproval add(OutSchoolActivityApproval outSchoolActivityApproval);

    OutSchoolActivityApproval modify(OutSchoolActivityApproval outSchoolActivityApproval);

    void remove(OutSchoolActivityApproval outSchoolActivityApproval);

    List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Page page, Order order);

    List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition);

    List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Page page);

    List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Order order);

    Long count();

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

    /**
     * @param activityId 活动ID
     * @param approvalId 审批人
     * @param status     审批状态
     * @param feedback   反馈
     */
    boolean updateOutSchoolActivityApproval(Integer activityId, Integer approvalId, Integer status, String feedback);
}
