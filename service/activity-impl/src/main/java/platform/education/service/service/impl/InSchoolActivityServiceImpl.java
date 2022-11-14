package platform.education.service.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.service.dao.InSchoolActivityApprovalDao;
import platform.education.service.dao.InSchoolActivityDao;
import platform.education.service.dao.InSchoolActivityUserDao;
import platform.education.service.model.InSchoolActivity;
import platform.education.service.model.InSchoolActivityApproval;
import platform.education.service.model.InSchoolActivityUser;
import platform.education.service.service.InSchoolActivityService;
import platform.education.service.vo.InSchoolActivityApprovalCondition;
import platform.education.service.vo.InSchoolActivityCondition;
import platform.education.service.vo.InSchoolActivityParticipantVo;
import platform.education.service.vo.InSchoolActivityVo;
import platform.education.user.contants.GroupContants;
import platform.education.user.contants.SysDefRole;
import platform.education.user.dao.GroupDao;
import platform.education.user.dao.UserRoleDao;
import platform.education.user.model.Group;
import platform.education.user.model.UserRole;

import java.util.Date;
import java.util.List;

public class InSchoolActivityServiceImpl implements InSchoolActivityService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private InSchoolActivityDao inSchoolActivityDao;
    private InSchoolActivityUserDao inSchoolActivityUserDao;
    private InSchoolActivityApprovalDao inSchoolActivityApprovalDao;
    private UserRoleDao userRoleDao;
    private GroupDao groupDao;

    public void setInSchoolActivityDao(InSchoolActivityDao inSchoolActivityDao) {
        this.inSchoolActivityDao = inSchoolActivityDao;
    }

    public void setInSchoolActivityUserDao(InSchoolActivityUserDao inSchoolActivityUserDao) {
        this.inSchoolActivityUserDao = inSchoolActivityUserDao;
    }

    public void setInSchoolActivityApprovalDao(InSchoolActivityApprovalDao inSchoolActivityApprovalDao) {
        this.inSchoolActivityApprovalDao = inSchoolActivityApprovalDao;
    }

    public void setUserRoleDao(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
    public InSchoolActivity findInSchoolActivityById(Integer id) {
        try {
            return inSchoolActivityDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public InSchoolActivity add(InSchoolActivity inSchoolActivity) {
        if (inSchoolActivity == null) {
            return null;
        }
        Date createDate = inSchoolActivity.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        inSchoolActivity.setCreateDate(createDate);
        inSchoolActivity.setModifyDate(createDate);
        return inSchoolActivityDao.create(inSchoolActivity);
    }

    @Override
    public InSchoolActivity modify(InSchoolActivity inSchoolActivity) {
        if (inSchoolActivity == null) {
            return null;
        }
        Date modify = inSchoolActivity.getModifyDate();
        inSchoolActivity.setModifyDate(modify != null ? modify : new Date());
        return inSchoolActivityDao.update(inSchoolActivity);
    }

    @Override
    public void remove(InSchoolActivity inSchoolActivity) {
        try {
            inSchoolActivityDao.delete(inSchoolActivity);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", inSchoolActivity.getId(), e);
            }
        }
    }

    @Override
    public List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page, Order order) {
        return inSchoolActivityDao.findInSchoolActivityByCondition(inSchoolActivityCondition, page, order);
    }

    @Override
    public List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition) {
        return inSchoolActivityDao.findInSchoolActivityByCondition(inSchoolActivityCondition, null, null);
    }

    @Override
    public List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page) {
        return inSchoolActivityDao.findInSchoolActivityByCondition(inSchoolActivityCondition, page, null);
    }

    @Override
    public List<InSchoolActivity> findInSchoolActivityByCondition(InSchoolActivityCondition inSchoolActivityCondition, Order order) {
        return inSchoolActivityDao.findInSchoolActivityByCondition(inSchoolActivityCondition, null, order);
    }

    @Override
    public Long count() {
        return this.inSchoolActivityDao.count(null);
    }

    @Override
    public Long count(InSchoolActivityCondition inSchoolActivityCondition) {
        return this.inSchoolActivityDao.count(inSchoolActivityCondition);
    }

    @Override
    public List<InSchoolActivityVo> findInSchoolActivityVoByCondition(InSchoolActivityCondition inSchoolActivityCondition, Page page, Order order) {
        return this.inSchoolActivityDao.findInSchoolActivityVoByCondition(inSchoolActivityCondition, page, order);
    }

    @Override
    public InSchoolActivityParticipantVo findInSchoolActivityVoById(Integer id) {
        if (id == null) {
            return new InSchoolActivityParticipantVo();
        }
        return this.inSchoolActivityDao.findInSchoolActivityVoById(id);
    }

    @Override
    public InSchoolActivity addActivity(Integer userId, Integer schoolId, Integer roomId, String name, Long startTime, Long endTime, List<Integer> participantIds, String description) {
        if (userId == null || schoolId == null || roomId == null || name == null || name.isEmpty() || startTime == null || endTime == null || startTime > endTime || participantIds == null || participantIds.isEmpty()) {
            throw new RuntimeException("必选参数错误");
        }
        Date now = new Date();
        InSchoolActivity inSchoolActivity = new InSchoolActivity();
        inSchoolActivity.setUserId(userId);
        inSchoolActivity.setRoomId(roomId);
        inSchoolActivity.setName(name);
//        try {
//            inSchoolActivity.setStartTime(new Date(startTime));
//            inSchoolActivity.setEndTime(new Date(endTime));
//        } catch (Exception e) {
//            throw new RuntimeException("时间转换异常");
//        }
        inSchoolActivity.setDescription(description);
        inSchoolActivity.setCreateDate(now);
        inSchoolActivity.setModifyDate(now);
        inSchoolActivity = this.add(inSchoolActivity);

        if (inSchoolActivity == null) {
            this.log.error("InSchoolActivity is null");
            throw new RuntimeException("申请活动失败");
        }

        // 添加参与人
        InSchoolActivityUser inSchoolActivityUser;
        for (Integer participantId : participantIds) {
            inSchoolActivityUser = new InSchoolActivityUser();
            inSchoolActivityUser.setActivityId(inSchoolActivity.getId());
            inSchoolActivityUser.setUserId(participantId);
            inSchoolActivityUser.setModifyDate(now);
            this.inSchoolActivityUserDao.create(inSchoolActivityUser);
        }

        // 组类型   0=System,1=School,2=Unit
        Group group = this.groupDao.findUnique(schoolId, GroupContants.TYPE_SCHOOL);
        // 查找学校管理员
        List<UserRole> userRoleList = this.userRoleDao.findByUserIdAndGroupId(null, group.getId(), SysDefRole.SCHOOL_MANAGER);

        if (userRoleList == null || userRoleList.isEmpty()) {
            this.log.error("List<UserRole> is null");
            throw new RuntimeException("找不到学校管理员");
        }

        for (UserRole ur : userRoleList) {
            if (ur.getUser() == null) {
                continue;
            }
            // 添加审批人
            InSchoolActivityApproval inSchoolActivityApproval = new InSchoolActivityApproval();
            inSchoolActivityApproval.setUserId(ur.getUser().getId());
            inSchoolActivityApproval.setActivityId(inSchoolActivity.getId());
            inSchoolActivityApproval.setStatus(InSchoolActivityApproval.STAT100);
            inSchoolActivityApproval.setCreateDate(now);
            inSchoolActivityApproval.setModifyDate(now);
            inSchoolActivityApproval = this.inSchoolActivityApprovalDao.create(inSchoolActivityApproval);
            if (inSchoolActivityApproval == null) {
                this.log.error("InSchoolActivityApproval is null");
                throw new RuntimeException("添加审批人失败");
            }
        }
        return inSchoolActivity;
    }

    @Override
    public InSchoolActivity updateActivity(Integer id, Integer roomId, String name, Long startTime, Long endTime, List<Integer> participantIds, String description) {
        if (id == null) {
            throw new RuntimeException("必选参数错误");
        }
        // 查找活动
        InSchoolActivity inSchoolActivity = this.inSchoolActivityDao.findById(id);
        if (inSchoolActivity == null) {
            throw new RuntimeException("找不到该活动");
        }
        // 查询审批状态
        InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition = new InSchoolActivityApprovalCondition();
        inSchoolActivityApprovalCondition.setActivityId(id);
        List<InSchoolActivityApproval> inSchoolActivityApprovalList = this.inSchoolActivityApprovalDao.findInSchoolActivityApprovalByCondition(inSchoolActivityApprovalCondition, null, null);
        if (inSchoolActivityApprovalList == null || inSchoolActivityApprovalList.isEmpty()) {
            throw new RuntimeException("找不到该活动审批信息");
        }
        if (inSchoolActivityApprovalList.get(0).getStatus() > InSchoolActivityApproval.STAT200) {
            throw new RuntimeException("已审批通过的活动禁止修改");
        }
        // 更新活动信息
        if (roomId != null) {
            inSchoolActivity.setRoomId(roomId);
        }
        if (name != null && !name.isEmpty()) {
            inSchoolActivity.setName(name);
        }
//        if (startTime != null && endTime != null) {
//            if (startTime > endTime) {
//                throw new RuntimeException("开始时间大于结束时间");
//            }
//            try {
//                inSchoolActivity.setStartTime(new Date(startTime));
//                inSchoolActivity.setEndTime(new Date(endTime));
//            } catch (Exception e) {
//                throw new RuntimeException("时间转换异常");
//            }
//        }
//        if (startTime != null && endTime == null) {
//            if (startTime > inSchoolActivity.getEndTime().getTime()) {
//                throw new RuntimeException("开始时间大于结束时间");
//            }
//            try {
//                inSchoolActivity.setStartTime(new Date(startTime));
//            } catch (Exception e) {
//                throw new RuntimeException("时间转换异常");
//            }
//        }
//        if (startTime == null && endTime != null) {
//            if (inSchoolActivity.getStartTime().getTime() > endTime) {
//                throw new RuntimeException("开始时间大于结束时间");
//            }
//            try {
//                inSchoolActivity.setEndTime(new Date(endTime));
//            } catch (Exception e) {
//                throw new RuntimeException("时间转换异常");
//            }
//        }
//        inSchoolActivity.setDescription(description);
//        inSchoolActivity = this.inSchoolActivityDao.update(inSchoolActivity);
//        if (participantIds == null || participantIds.isEmpty()) {
//            return inSchoolActivity;
//        }
//        // 更新参与人
//        InSchoolActivityUserCondition inSchoolActivityUserCondition = new InSchoolActivityUserCondition();
//        inSchoolActivityUserCondition.setActivityId(id);
//        List<InSchoolActivityUser> inSchoolActivityUserList = this.inSchoolActivityUserDao.findInSchoolActivityUserByCondition(inSchoolActivityUserCondition, null, null);
//        // 先删除
//        for (InSchoolActivityUser inSchoolActivityUser : inSchoolActivityUserList) {
//            this.inSchoolActivityUserDao.delete(inSchoolActivityUser);
//        }
//        // 再添加
//        InSchoolActivityUser inSchoolActivityUser;
//        Date now = new Date();
//        for (Integer participantId : participantIds) {
//            inSchoolActivityUser = new InSchoolActivityUser();
//            inSchoolActivityUser.setActivityId(id);
//            inSchoolActivityUser.setUserId(participantId);
//            inSchoolActivityUser.setModifyDate(now);
//            this.inSchoolActivityUserDao.create(inSchoolActivityUser);
//        }
//        return inSchoolActivity;
        return null;
    }

    @Override
    public List<InSchoolActivity> findInSchoolActivityAndApprovalVoByCondition(InSchoolActivity act, Page page, Order order) {
        return this.inSchoolActivityDao.findInSchoolActivityAndApprovalVoByCondition(act, page, order);
    }

    /*@Override
    public Integer findByOenetId(Integer oenetId) {
        return inSchoolActivityDao.findByOenetId(oenetId);
    }*/
}
