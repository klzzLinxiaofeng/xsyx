package platform.education.service.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.service.model.OutSchoolActivitySummaryPic;
import platform.education.service.vo.OutSchoolActivitySummaryPicCondition;
import platform.education.service.service.OutSchoolActivitySummaryPicService;
import platform.education.service.dao.OutSchoolActivitySummaryPicDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class OutSchoolActivitySummaryPicServiceImpl implements OutSchoolActivitySummaryPicService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private OutSchoolActivitySummaryPicDao outSchoolActivitySummaryPicDao;

    public void setOutSchoolActivitySummaryPicDao(OutSchoolActivitySummaryPicDao outSchoolActivitySummaryPicDao) {
        this.outSchoolActivitySummaryPicDao = outSchoolActivitySummaryPicDao;
    }

    @Override
    public OutSchoolActivitySummaryPic findOutSchoolActivitySummaryPicById(Integer id) {
        try {
            return outSchoolActivitySummaryPicDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public OutSchoolActivitySummaryPic add(OutSchoolActivitySummaryPic outSchoolActivitySummaryPic) {
        if (outSchoolActivitySummaryPic == null) {
            return null;
        }
        Date createDate = outSchoolActivitySummaryPic.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        outSchoolActivitySummaryPic.setCreateDate(createDate);
        outSchoolActivitySummaryPic.setModifyDate(createDate);
        return outSchoolActivitySummaryPicDao.create(outSchoolActivitySummaryPic);
    }

    @Override
    public OutSchoolActivitySummaryPic modify(OutSchoolActivitySummaryPic outSchoolActivitySummaryPic) {
        if (outSchoolActivitySummaryPic == null) {
            return null;
        }
        Date modify = outSchoolActivitySummaryPic.getModifyDate();
        outSchoolActivitySummaryPic.setModifyDate(modify != null ? modify : new Date());
        return outSchoolActivitySummaryPicDao.update(outSchoolActivitySummaryPic);
    }

    @Override
    public void remove(OutSchoolActivitySummaryPic outSchoolActivitySummaryPic) {
        try {
            outSchoolActivitySummaryPicDao.delete(outSchoolActivitySummaryPic);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", outSchoolActivitySummaryPic.getId(), e);
            }
        }
    }

    @Override
    public List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Page page, Order order) {
        return outSchoolActivitySummaryPicDao.findOutSchoolActivitySummaryPicByCondition(outSchoolActivitySummaryPicCondition, page, order);
    }

    @Override
    public List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition) {
        return outSchoolActivitySummaryPicDao.findOutSchoolActivitySummaryPicByCondition(outSchoolActivitySummaryPicCondition, null, null);
    }

    @Override
    public List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Page page) {
        return outSchoolActivitySummaryPicDao.findOutSchoolActivitySummaryPicByCondition(outSchoolActivitySummaryPicCondition, page, null);
    }

    @Override
    public List<OutSchoolActivitySummaryPic> findOutSchoolActivitySummaryPicByCondition(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition, Order order) {
        return outSchoolActivitySummaryPicDao.findOutSchoolActivitySummaryPicByCondition(outSchoolActivitySummaryPicCondition, null, order);
    }

    @Override
    public Long count() {
        return this.outSchoolActivitySummaryPicDao.count(null);
    }

    @Override
    public Long count(OutSchoolActivitySummaryPicCondition outSchoolActivitySummaryPicCondition) {
        return this.outSchoolActivitySummaryPicDao.count(outSchoolActivitySummaryPicCondition);
    }

}
