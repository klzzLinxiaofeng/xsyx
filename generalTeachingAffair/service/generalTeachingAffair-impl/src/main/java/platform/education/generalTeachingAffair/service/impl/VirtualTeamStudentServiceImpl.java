package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.VirtualTeamStudent;
import platform.education.generalTeachingAffair.vo.VirtualTeamStudentCondition;
import platform.education.generalTeachingAffair.service.VirtualTeamStudentService;
import platform.education.generalTeachingAffair.dao.VirtualTeamStudentDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class VirtualTeamStudentServiceImpl implements VirtualTeamStudentService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private VirtualTeamStudentDao virtualTeamStudentDao;

    public void setVirtualTeamStudentDao(VirtualTeamStudentDao virtualTeamStudentDao) {
        this.virtualTeamStudentDao = virtualTeamStudentDao;
    }

    private StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public VirtualTeamStudent findVirtualTeamStudentById(Integer id) {
        try {
            return virtualTeamStudentDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public VirtualTeamStudent add(VirtualTeamStudent virtualTeamStudent) {
        if (virtualTeamStudent == null) {
            return null;
        }
        Date createDate = virtualTeamStudent.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        virtualTeamStudent.setCreateDate(createDate);
        virtualTeamStudent.setModifyDate(createDate);
        return virtualTeamStudentDao.create(virtualTeamStudent);
    }

    @Override
    public VirtualTeamStudent modify(VirtualTeamStudent virtualTeamStudent) {
        if (virtualTeamStudent == null) {
            return null;
        }
        Date modify = virtualTeamStudent.getModifyDate();
        virtualTeamStudent.setModifyDate(modify != null ? modify : new Date());
        return virtualTeamStudentDao.update(virtualTeamStudent);
    }

    @Override
    public void remove(VirtualTeamStudent virtualTeamStudent) {
        try {
            virtualTeamStudentDao.delete(virtualTeamStudent);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", virtualTeamStudent.getId(), e);
            }
        }
    }

    @Override
    public List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Page page, Order order) {
        return virtualTeamStudentDao.findVirtualTeamStudentByCondition(virtualTeamStudentCondition, page, order);
    }

    @Override
    public List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition) {
        return virtualTeamStudentDao.findVirtualTeamStudentByCondition(virtualTeamStudentCondition, null, null);
    }

    @Override
    public List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Page page) {
        return virtualTeamStudentDao.findVirtualTeamStudentByCondition(virtualTeamStudentCondition, page, null);
    }

    @Override
    public List<VirtualTeamStudent> findVirtualTeamStudentByCondition(VirtualTeamStudentCondition virtualTeamStudentCondition, Order order) {
        return virtualTeamStudentDao.findVirtualTeamStudentByCondition(virtualTeamStudentCondition, null, order);
    }

    @Override


    public Long count() {
        return this.virtualTeamStudentDao.count(null);
    }

    @Override
    public Long count(VirtualTeamStudentCondition virtualTeamStudentCondition) {
        return this.virtualTeamStudentDao.count(virtualTeamStudentCondition);
    }

    @Override
    public List<Student> findStudentByVirtualTeamId(Integer virtualTeamId) {
        return this.virtualTeamStudentDao.findStudentByVirtualTeamId(virtualTeamId);
    }

    @Override
    public List<VirtualTeamStudent> findByStudentIds(Integer gradeId, Integer virtualTeamId, Integer[] studentIds) {
        return this.virtualTeamStudentDao.findByStudentIds(gradeId, virtualTeamId, studentIds);
    }

    @Override
    public VirtualTeamStudent findUnique(Integer virtualTeamId, Integer studentId) {
        return this.virtualTeamStudentDao.findUnique(virtualTeamId, studentId);
    }

    @Override
    public void batchCreate(VirtualTeamStudent[] list) {
        this.virtualTeamStudentDao.batchCreate(list);
    }

    @Override
    public void batchRemove(Integer[] ids) {
        this.virtualTeamStudentDao.batchRemove(ids);
    }

    @Override
    public void batchRemoveByStudentIds(Integer gradeId, Integer virtualTeamId, Integer[] studentIds) {
        this.virtualTeamStudentDao.batchRemoveByStudentIds(gradeId, virtualTeamId, studentIds);
    }
}
