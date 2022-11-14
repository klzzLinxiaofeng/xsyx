package platform.education.service.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.service.model.InSchoolRoom;
import platform.education.service.vo.InSchoolRoomCondition;
import platform.education.service.service.InSchoolRoomService;
import platform.education.service.dao.InSchoolRoomDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.service.vo.RoomVo;

public class InSchoolRoomServiceImpl implements InSchoolRoomService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private InSchoolRoomDao inSchoolRoomDao;

    public void setInSchoolRoomDao(InSchoolRoomDao inSchoolRoomDao) {
        this.inSchoolRoomDao = inSchoolRoomDao;
    }

    @Override
    public InSchoolRoom findInSchoolRoomById(Integer id) {
        try {
            return inSchoolRoomDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public InSchoolRoom add(InSchoolRoom inSchoolRoom) {
        if (inSchoolRoom == null) {
            return null;
        }
        Date createDate = inSchoolRoom.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        inSchoolRoom.setCreateDate(createDate);
        inSchoolRoom.setModifyDate(createDate);
        return inSchoolRoomDao.create(inSchoolRoom);
    }

    @Override
    public InSchoolRoom modify(InSchoolRoom inSchoolRoom) {
        if (inSchoolRoom == null) {
            return null;
        }
        Date modify = inSchoolRoom.getModifyDate();
        inSchoolRoom.setModifyDate(modify != null ? modify : new Date());
        return inSchoolRoomDao.update(inSchoolRoom);
    }

    @Override
    public void remove(InSchoolRoom inSchoolRoom) {
        try {
            inSchoolRoomDao.delete(inSchoolRoom);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", inSchoolRoom.getId(), e);
            }
        }
    }

    @Override
    public List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Page page, Order order) {
        return inSchoolRoomDao.findInSchoolRoomByCondition(inSchoolRoomCondition, page, order);
    }

    @Override
    public List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition) {
        return inSchoolRoomDao.findInSchoolRoomByCondition(inSchoolRoomCondition, null, null);
    }

    @Override
    public List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Page page) {
        return inSchoolRoomDao.findInSchoolRoomByCondition(inSchoolRoomCondition, page, null);
    }

    @Override
    public List<InSchoolRoom> findInSchoolRoomByCondition(InSchoolRoomCondition inSchoolRoomCondition, Order order) {
        return inSchoolRoomDao.findInSchoolRoomByCondition(inSchoolRoomCondition, null, order);
    }

    @Override
    public Long count() {
        return this.inSchoolRoomDao.count(null);
    }

    @Override
    public Long count(InSchoolRoomCondition inSchoolRoomCondition) {
        return this.inSchoolRoomDao.count(inSchoolRoomCondition);
    }

    @Override
    public InSchoolRoom findInSchoolRoomByName(String name) {
        return this.inSchoolRoomDao.findInSchoolRoomByName(name);
    }

    @Override
    public List<RoomVo> findInSchoolRoomByUserIdAndStatus(Integer userId, Integer status) {
        if (userId == null || status == null) {
            return new ArrayList<>();
        }
        return this.inSchoolRoomDao.findInSchoolRoomByUserIdAndStatus(userId, status);
    }

}
