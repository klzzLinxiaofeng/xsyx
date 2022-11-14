package platform.education.service.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.service.dao.InSchoolActivityDao;
import platform.education.service.model.InSchoolActivity;
import platform.education.service.model.InSchoolActivityApproval;
import platform.education.service.vo.InSchoolActivityApprovalCondition;
import platform.education.service.service.InSchoolActivityApprovalService;
import platform.education.service.dao.InSchoolActivityApprovalDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.vo.InSchoolActivityVo;

public class InSchoolActivityApprovalServiceImpl implements InSchoolActivityApprovalService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private InSchoolActivityApprovalDao inSchoolActivityApprovalDao;
    private InSchoolActivityDao inSchoolActivityDao;

    public void setInSchoolActivityApprovalDao(InSchoolActivityApprovalDao inSchoolActivityApprovalDao) {
        this.inSchoolActivityApprovalDao = inSchoolActivityApprovalDao;
    }

    public void setInSchoolActivityDao(InSchoolActivityDao inSchoolActivityDao) {
        this.inSchoolActivityDao = inSchoolActivityDao;
    }

    @Override
    public InSchoolActivityApproval findInSchoolActivityApprovalById(Integer id) {
        try {
            return inSchoolActivityApprovalDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public InSchoolActivityApproval add(InSchoolActivityApproval inSchoolActivityApproval) {
        if (inSchoolActivityApproval == null) {
            return null;
        }
        Date createDate = inSchoolActivityApproval.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        inSchoolActivityApproval.setCreateDate(createDate);
        inSchoolActivityApproval.setModifyDate(createDate);
        return inSchoolActivityApprovalDao.create(inSchoolActivityApproval);
    }

    @Override
    public InSchoolActivityApproval modify(InSchoolActivityApproval inSchoolActivityApproval) {
        if (inSchoolActivityApproval == null) {
            return null;
        }
        Date modify = inSchoolActivityApproval.getModifyDate();
        inSchoolActivityApproval.setModifyDate(modify != null ? modify : new Date());
        return inSchoolActivityApprovalDao.update(inSchoolActivityApproval);
    }

    @Override
    public void remove(InSchoolActivityApproval inSchoolActivityApproval) {
        try {
            inSchoolActivityApprovalDao.delete(inSchoolActivityApproval);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", inSchoolActivityApproval.getId(), e);
            }
        }
    }

    @Override
    public List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Page page, Order order) {
        return inSchoolActivityApprovalDao.findInSchoolActivityApprovalByCondition(inSchoolActivityApprovalCondition, page, order);
    }

    @Override
    public List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition) {
        return inSchoolActivityApprovalDao.findInSchoolActivityApprovalByCondition(inSchoolActivityApprovalCondition, null, null);
    }

    @Override
    public List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Page page) {
        return inSchoolActivityApprovalDao.findInSchoolActivityApprovalByCondition(inSchoolActivityApprovalCondition, page, null);
    }

    @Override
    public List<InSchoolActivityApproval> findInSchoolActivityApprovalByCondition(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition, Order order) {
        return inSchoolActivityApprovalDao.findInSchoolActivityApprovalByCondition(inSchoolActivityApprovalCondition, null, order);
    }

    @Override
    public Long count() {
        return this.inSchoolActivityApprovalDao.count(null);
    }

    @Override
    public Long count(InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition) {
        return this.inSchoolActivityApprovalDao.count(inSchoolActivityApprovalCondition);
    }

    @Override
    public boolean batchInSchoolActivityApproval(Integer activityId, Integer approvalId) {
        if (activityId == null || approvalId == null) {
            return false;
        }
        // 查找对应审批人记录，并且是未审批状态
        InSchoolActivityVo inSchoolActivityVo = this.inSchoolActivityDao.findInSchoolActivityByIdAndApprovalId(activityId, approvalId);
        if (inSchoolActivityVo == null || inSchoolActivityVo.getStatus() == null || inSchoolActivityVo.getStatus() >= InSchoolActivityApproval.STAT200) {
            return false;
        }
        // 驳回
//        List<InSchoolActivityApproval> rejectInSchoolActivityApprovalList = this.inSchoolActivityApprovalDao.findInSchoolActivityApprovalByActivity(inSchoolActivityVo.getStartTime(), inSchoolActivityVo.getEndTime(), inSchoolActivityVo.getRoomId(), activityId);
//        for (InSchoolActivityApproval inSchoolActivityApproval : rejectInSchoolActivityApprovalList) {
//            inSchoolActivityApproval.setStatus(InSchoolActivityApproval.STAT200);
//            inSchoolActivityApproval.setFeedback(InSchoolActivityApproval.FEEDBACK);
//            this.inSchoolActivityApprovalDao.update(inSchoolActivityApproval);
//        }
        // 同意
        InSchoolActivityApprovalCondition inSchoolActivityApprovalCondition = new InSchoolActivityApprovalCondition();
        inSchoolActivityApprovalCondition.setActivityId(activityId);
        List<InSchoolActivityApproval> inSchoolActivityApprovalList = this.inSchoolActivityApprovalDao.findInSchoolActivityApprovalByCondition(inSchoolActivityApprovalCondition, null, null);
        for (InSchoolActivityApproval inSchoolActivityApproval : inSchoolActivityApprovalList) {
            inSchoolActivityApproval.setStatus(InSchoolActivityApproval.STAT201);
            this.inSchoolActivityApprovalDao.update(inSchoolActivityApproval);
        }
        return true;
    }
}
