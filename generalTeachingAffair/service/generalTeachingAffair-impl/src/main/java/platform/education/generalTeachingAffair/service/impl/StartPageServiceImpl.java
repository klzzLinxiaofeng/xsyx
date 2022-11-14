package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.StartPageDao;
import platform.education.generalTeachingAffair.model.StartPagePojo;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.education.generalTeachingAffair.service.StartPageService;

import java.util.Date;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/12 16:24
 * @Description: 启动页管理
 */
public class StartPageServiceImpl implements StartPageService {
    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 启动页
     */
    private StartPageDao startPageDao;

    public void setStartPageDao(StartPageDao startPageDao) {
        this.startPageDao = startPageDao;
    }

    /**
     * 获取所有
     *
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @Override
    public List<StartPagePojo> findQuestionnaireVoByCondition(StartPagePojo condition, Page page, Order order) {
        List<StartPagePojo> publicClassByCondition = null;
        try {
            publicClassByCondition = startPageDao.findStartPageVoByCondition(condition, page, order);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return publicClassByCondition;
    }

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
     */
    @Override
    public String abandon(StartPagePojo startPagePojo) {
        if (startPagePojo != null) {
            startPagePojo.setIsDelete(1);
            Date modify = startPagePojo.getModifyDate();
            startPagePojo.setModifyDate(modify != null ? modify : new Date());
            try {
                startPagePojo = this.startPageDao.update(startPagePojo);
                return PublicClassService.OPERATE_SUCCESS;
            } catch (Exception e) {
                log.error("废弃 -> {} 失败，异常信息为 {}", startPagePojo.getId(), e);
                return PublicClassService.OPERATE_ERROR;
            }
        }
        return PublicClassService.OPERATE_FAIL;
    }


    /**
     * 新增
     *
     * @param startPagePojo
     * @return
     */
    @Override
    public StartPagePojo add(StartPagePojo startPagePojo) {
        if (startPagePojo == null) {
            return null;
        }

        Integer status = startPagePojo.getStatus();
        if (status != null && status == 0) {
            List<StartPagePojo> startPagePojoList = startPageDao.findStartPageVoByCondition(null, null, null);
            for (StartPagePojo pagePojo : startPagePojoList) {
                StartPagePojo pojo = new StartPagePojo();
                // 修改其他启动状态为不启动
                pojo.setStatus(1);
                pojo.setModifyDate(new Date());
                pojo.setId(pagePojo.getId());
                startPageDao.update(pojo);
            }
        }
        Date createDate = startPagePojo.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        startPagePojo.setCreateDate(createDate);
        startPagePojo.setModifyDate(createDate);
        return startPageDao.create(startPagePojo);
    }

    @Override
    public StartPagePojo findStartPagePojoVoById(Integer id) {
        try {
            return startPageDao.findById(id);
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }


    @Override
    public StartPagePojo modify(StartPagePojo startPagePojo) {
        if (startPagePojo == null) {
            return null;
        }
        Date modify = startPagePojo.getModifyDate();
        Integer status = startPagePojo.getStatus();
        if (status != null && status == 0) {
            List<StartPagePojo> startPagePojoList = startPageDao.findStartPageVoByCondition(null, null, null);
            for (StartPagePojo pagePojo : startPagePojoList) {
                StartPagePojo pojo = new StartPagePojo();
                // 修改其他启动状态为不启动
                pojo.setStatus(1);
                pojo.setModifyDate(modify != null ? modify : new Date());
                pojo.setId(pagePojo.getId());
                startPageDao.update(pojo);
            }
        }
        startPagePojo.setModifyDate(modify != null ? modify : new Date());
        return startPageDao.update(startPagePojo);
    }

}
