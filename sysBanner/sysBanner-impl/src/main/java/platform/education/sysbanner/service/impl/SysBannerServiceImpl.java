package platform.education.sysbanner.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.sysbanner.model.SysBanner;
import platform.education.sysbanner.service.SysBannerService;
import platform.education.sysbanner.vo.SysBannerCondition;
import platform.education.sysbanner.dao.SysBannerDao;

import java.util.Date;
import java.util.List;

public class SysBannerServiceImpl implements SysBannerService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private SysBannerDao SysBannerDao;
    public void setSysBannerDao(SysBannerDao SysBannerDao) {
        this.SysBannerDao = SysBannerDao;
    }

    @Override
    public SysBanner findSysBannerById(Integer id) {
        try {
            return this.SysBannerDao.findById(id);
        } catch (Exception var3) {
            var3.printStackTrace();
            this.log.info("数据库无存在ID为 {} ", id);
            return null;
        }
    }

    @Override
    public SysBanner add(SysBanner SysBanner) {
        if (SysBanner == null) {
            return null;
        } else {
            Date createDate = SysBanner.getCreateDate();
            if (createDate == null) {
                createDate = new Date();
            }

            SysBanner.setCreateDate(createDate);
            SysBanner.setModifyDate(createDate);
            return this.SysBannerDao.create(SysBanner);
        }
    }

    @Override
    public SysBanner modify(SysBanner SysBanner) {
        if (SysBanner == null) {
            return null;
        } else {
            Date modify = SysBanner.getModifyDate();
            SysBanner.setModifyDate(modify != null ? modify : new Date());
            return  this.SysBannerDao.update(SysBanner);
        }
    }

    @Override
    public void remove(SysBanner SysBanner) {
        try {
            this.SysBannerDao.delete(SysBanner);
        } catch (Exception var3) {
            if (this.log.isDebugEnabled()) {
                this.log.debug("删除数据库无存在ID为 {} ,异常为：{}", SysBanner.getId(), var3);
            }
        }

    }

    @Override
    public List<SysBanner> findSysBannerCondition(SysBannerCondition SysBannerCondition, Page page, Order order) {
        return this.SysBannerDao.findSysBannerByCondition(SysBannerCondition,page,order);
    }

    @Override
    public List<SysBanner> findSysBannerCondition(SysBannerCondition SysBannerCondition) {
        return this.SysBannerDao.findSysBannerByCondition(SysBannerCondition,null,null);
    }

    @Override
    public List<SysBanner> findSysBannerCondition(SysBannerCondition SysBannerCondition, Page page) {
        return this.SysBannerDao.findSysBannerByCondition(SysBannerCondition,page,null);
    }

    @Override
    public List<SysBanner> findSysBannerCondition(SysBannerCondition SysBannerCondition, Order order) {
        return this.SysBannerDao.findSysBannerByCondition(SysBannerCondition,null,order);
    }

    @Override
    public Long count() {
        return this.SysBannerDao.count(null);
    }

    @Override
    public Long count(SysBannerCondition SysBannerCondition) {
        return this.SysBannerDao.count(SysBannerCondition);
    }
}
