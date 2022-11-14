package platform.education.service.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.model.InSchoolActivity;
import platform.education.service.vo.InSchoolActivityCondition;
import platform.education.service.vo.InSchoolActivityParticipantVo;
import platform.education.service.vo.InSchoolActivityVo;

import java.util.List;

public interface InSchoolActivityService {
    InSchoolActivity findInSchoolActivityById(Integer id);

    InSchoolActivity add(InSchoolActivity inSchoolActivity);

    InSchoolActivity modify(InSchoolActivity inSchoolActivity);

    void remove(InSchoolActivity inSchoolActivity);

    List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page, Order order);

    List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition);

    List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page);

    List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Order order);

    Long count();

    Long count(InSchoolActivityCondition inSchoolActivityCondition);

    List<InSchoolActivityVo> findInSchoolActivityVoByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page, Order order);

    InSchoolActivityParticipantVo findInSchoolActivityVoById(Integer id);

    /**
     * @param userId         申请人ID（必选）
     * @param schoolId       学校ID（必选）
     * @param roomId         活动室ID（必选）
     * @param name           活动名称（必选）
     * @param startTime      开始时间（必选）
     * @param endTime        结束时间（必选）
     * @param participantIds 参与者ID（必选，多个重复传）
     * @param description    活动描述（可选）
     */
    InSchoolActivity addActivity(Integer userId, Integer schoolId, Integer roomId, String name, Long startTime, Long endTime, List<Integer> participantIds, String description);

    /**
     * @param id             活动ID（必选）
     * @param roomId         活动室ID（必选）
     * @param name           活动名称（必选）
     * @param startTime      开始时间（必选）
     * @param endTime        结束时间（必选）
     * @param participantIds 参与者ID（必选，多个重复传）
     * @param description    活动描述（可选）
     */
    InSchoolActivity updateActivity(Integer id, Integer roomId, String name, Long startTime, Long endTime, List<Integer> participantIds, String description);

    List<InSchoolActivity> findInSchoolActivityAndApprovalVoByCondition(InSchoolActivity act, Page page, Order order);
    /*Integer findByOenetId(Integer oenetId);*/
}
