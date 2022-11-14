package platform.education.service.service;

import org.springframework.web.multipart.MultipartFile;
import platform.education.service.model.OutSchoolActivity;
import platform.education.service.vo.OutSchoolActivityAndApprovalVo;
import platform.education.service.vo.OutSchoolActivityCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.vo.OutSchoolActivityPictureVo;
import platform.education.service.vo.OutSchoolActivityVo;

import java.util.Date;
import java.util.List;

public interface OutSchoolActivityService {

    OutSchoolActivity findOutSchoolActivityById(Integer id);

    OutSchoolActivity add(OutSchoolActivity outSchoolActivity);

    OutSchoolActivity modify(OutSchoolActivity outSchoolActivity);

    void remove(OutSchoolActivity outSchoolActivity);

    List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page, Order order);

    List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition);

    List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page);

    List<OutSchoolActivity> findOutSchoolActivityByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Order order);

    Long count();

    Long count(OutSchoolActivityCondition outSchoolActivityCondition);

    List<OutSchoolActivityVo> findOutSchoolActivityVoByCondition(OutSchoolActivityCondition outSchoolActivityCondition, Page page, Order order);

    OutSchoolActivityPictureVo findOutSchoolActivityVoById(Integer id);

    /**
     * @param userId      申请人ID（必选）
     * @param schoolId    学校ID（必选）
     * @param name        活动名称（必选）
     * @param location    活动地点（必选）
     * @param startTime   开始时间（必选）
     * @param endTime     结束时间（必选）
     * @param file        活动附件（可选）
     * @param description 活动描述（可选）
     * @param appId       AppId
     */
    OutSchoolActivity addActivity(Integer userId, Integer schoolId, String name, String location, Long startTime, Long endTime, MultipartFile file, String description, String appId);

    /**
     * @param id          活动ID（必选）
     * @param name        活动名称（必选）
     * @param location    活动地点（必选）
     * @param startTime   开始时间（必选）
     * @param endTime     结束时间（必选）
     * @param file        活动附件（可选）
     * @param description 活动描述（可选）
     */
    OutSchoolActivity updateActivity(Integer id, String name, String location, Long startTime, Long endTime, MultipartFile file, String description, String appId);

    List<OutSchoolActivity> findOutSchoolActivityAndApprovalVoByCondition(OutSchoolActivity act, Page page, Order order);
}
