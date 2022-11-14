package platform.education.service.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.model.InSchoolActivity;
import platform.education.service.vo.InSchoolActivityCondition;
import platform.education.service.vo.InSchoolActivityParticipantVo;
import platform.education.service.vo.InSchoolActivityVo;

import java.util.List;

public interface InSchoolActivityDao extends GenericDao<InSchoolActivity, Integer> {

    List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page, Order order);

    InSchoolActivity findById(Integer id);

    Long count(InSchoolActivityCondition inSchoolActivityCondition);

    /**
     * 注：审批状态，按审批与否并存在多个审批者使用 GROUP BY 去重
     *
     * @param inSchoolActivityCondition 查询条件
     * @param page                      分页
     * @param order                     排序
     */
    List<InSchoolActivityVo> findInSchoolActivityVoByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page, Order order);

    /**
     * 注：审批状态，存在多个审批者使用 GROUP BY 去重
     *
     * @param id 活动ID
     */
    InSchoolActivityParticipantVo findInSchoolActivityVoById(Integer id);

    List<InSchoolActivity> findInSchoolActivityAndApprovalVoByCondition(InSchoolActivity act, Page page, Order order);


    InSchoolActivityVo findInSchoolActivityByIdAndApprovalId(Integer id, Integer approvalId);
   /*  Integer findByOenetId(Integer oenetId);*/
}
