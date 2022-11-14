package platform.education.service.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.service.dao.OutSchoolActivityApprovalDao;
import platform.education.service.dao.OutSchoolActivityDao;
import platform.education.service.model.OutSchoolActivityApproval;
import platform.education.service.service.OutSchoolActivityApprovalService;
import platform.education.service.vo.ApprovalVo;
import platform.education.service.vo.OutSchoolActivityApprovalCondition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OutSchoolActivityApprovalServiceImpl implements OutSchoolActivityApprovalService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OutSchoolActivityApprovalDao outSchoolActivityApprovalDao;
    private OutSchoolActivityDao outSchoolActivityDao;

    @Autowired
    private BasicSQLService basicSQLService;

    public void setOutSchoolActivityApprovalDao(OutSchoolActivityApprovalDao outSchoolActivityApprovalDao) {
        this.outSchoolActivityApprovalDao = outSchoolActivityApprovalDao;
    }

    public void setOutSchoolActivityDao(OutSchoolActivityDao outSchoolActivityDao) {
        this.outSchoolActivityDao = outSchoolActivityDao;
    }

    @Override
    public OutSchoolActivityApproval findOutSchoolActivityApprovalById(Integer id) {
        try {
            return outSchoolActivityApprovalDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public OutSchoolActivityApproval add(OutSchoolActivityApproval outSchoolActivityApproval) {
        if (outSchoolActivityApproval == null) {
            return null;
        }
        Date createDate = outSchoolActivityApproval.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        outSchoolActivityApproval.setCreateDate(createDate);
        outSchoolActivityApproval.setModifyDate(createDate);
        return outSchoolActivityApprovalDao.create(outSchoolActivityApproval);
    }

    @Override
    public OutSchoolActivityApproval modify(OutSchoolActivityApproval outSchoolActivityApproval) {
        if (outSchoolActivityApproval == null) {
            return null;
        }
        Date modify = outSchoolActivityApproval.getModifyDate();
        outSchoolActivityApproval.setModifyDate(modify != null ? modify : new Date());
        return outSchoolActivityApprovalDao.update(outSchoolActivityApproval);
    }

    @Override
    public void remove(OutSchoolActivityApproval outSchoolActivityApproval) {
        try {
            outSchoolActivityApprovalDao.delete(outSchoolActivityApproval);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", outSchoolActivityApproval.getId(), e);
            }
        }
    }

    @Override
    public List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Page page, Order order) {
        return outSchoolActivityApprovalDao.findOutSchoolActivityApprovalByCondition(outSchoolActivityApprovalCondition, page, order);
    }

    @Override
    public List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition) {
        return outSchoolActivityApprovalDao.findOutSchoolActivityApprovalByCondition(outSchoolActivityApprovalCondition, null, null);
    }

    @Override
    public List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Page page) {
        return outSchoolActivityApprovalDao.findOutSchoolActivityApprovalByCondition(outSchoolActivityApprovalCondition, page, null);
    }

    @Override
    public List<OutSchoolActivityApproval> findOutSchoolActivityApprovalByCondition(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition, Order order) {
        return outSchoolActivityApprovalDao.findOutSchoolActivityApprovalByCondition(outSchoolActivityApprovalCondition, null, order);
    }

    @Override
    public Long count() {
        return this.outSchoolActivityApprovalDao.count(null);
    }

    @Override
    public Long count(OutSchoolActivityApprovalCondition outSchoolActivityApprovalCondition) {
        return this.outSchoolActivityApprovalDao.count(outSchoolActivityApprovalCondition);
    }

    @Override
    public List<Integer> findOutSchoolActivityApprovalIdByEnded(Date time) {
        return this.outSchoolActivityApprovalDao.findOutSchoolActivityApprovalIdByEnded(time);
    }

    @Override
    public List<ApprovalVo> findOutSchoolActivityApprovalGetStatusByUserId(Integer userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        return this.outSchoolActivityApprovalDao.findOutSchoolActivityApprovalGetStatusByUserId(userId);
    }

    @Override
    public boolean updateOutSchoolActivityApproval(Integer activityId, Integer approvalId, Integer status, String feedback) {
       return basicSQLService.update("update at_out_school_activity set state="+status+",refuse_cause="+feedback+",handle_user_id="+approvalId+" where id="+activityId)>0;
    }
}
