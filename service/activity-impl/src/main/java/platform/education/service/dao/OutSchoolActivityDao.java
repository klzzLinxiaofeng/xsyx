package platform.education.service.dao;

import platform.education.service.model.OutSchoolActivity;
import platform.education.service.vo.OutSchoolActivityAndApprovalVo;
import platform.education.service.vo.OutSchoolActivityCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.service.vo.OutSchoolActivityPictureVo;
import platform.education.service.vo.OutSchoolActivityVo;

import java.util.Date;
import java.util.List;

public interface OutSchoolActivityDao extends GenericDao<OutSchoolActivity, Integer> {

    List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page, Order order);

    OutSchoolActivity findById(Integer id);

    Long count(OutSchoolActivityCondition outSchoolActivityCondition);

    /**
     * 注：审批状态，按审批与否并存在多个审批者使用 GROUP BY 去重
     *
     * @param outSchoolActivityCondition 查询条件
     * @param page                       分页
     * @param order                      排序
     */
    List<OutSchoolActivityVo> findOutSchoolActivityVoByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page, Order order);

    /**
     * 注：审批状态，存在多个审批者使用 GROUP BY 去重
     *
     * @param id 活动ID
     */
    OutSchoolActivityPictureVo findOutSchoolActivityVoById(Integer id);

    List<OutSchoolActivity> findOutSchoolActivityAndApprovalVoByCondition(OutSchoolActivity act, Page page, Order order);

    OutSchoolActivityVo findOutSchoolActivityByIdAndApprovalId(Integer activityId, Integer approvalId);
}
