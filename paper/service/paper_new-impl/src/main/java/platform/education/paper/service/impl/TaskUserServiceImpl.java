package platform.education.paper.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.paper.model.TaskUser;
import platform.education.paper.vo.TaskUserCondition;
import platform.education.paper.service.TaskUserService;
import platform.education.paper.dao.TaskUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.vo.TaskUserVo;

public class TaskUserServiceImpl implements TaskUserService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private TaskUserDao taskUserDao;

    public void setTaskUserDao(TaskUserDao taskUserDao) {
        this.taskUserDao = taskUserDao;
    }

    @Override
    public TaskUser findTaskUserById(Integer id) {
        try {
            return taskUserDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public TaskUser add(TaskUser taskUser) {
        if (taskUser == null) {
            return null;
        }
        Date createDate = taskUser.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        taskUser.setCreateDate(createDate);
        taskUser.setModifyDate(createDate);
        return taskUserDao.create(taskUser);
    }

    @Override
    public TaskUser modify(TaskUser taskUser) {
        if (taskUser == null) {
            return null;
        }
        Date modify = taskUser.getModifyDate();
        taskUser.setModifyDate(modify != null ? modify : new Date());
        return taskUserDao.update(taskUser);
    }

    @Override
    public void remove(TaskUser taskUser) {
        try {
            taskUserDao.delete(taskUser);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", taskUser.getId(), e);
            }
        }
    }

    @Override
    public List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Page page, Order order) {
        return taskUserDao.findTaskUserByCondition(taskUserCondition, page, order);
    }

    @Override
    public List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition) {
        return taskUserDao.findTaskUserByCondition(taskUserCondition, null, null);
    }

    @Override
    public List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Page page) {
        return taskUserDao.findTaskUserByCondition(taskUserCondition, page, null);
    }

    @Override
    public List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Order order) {
        return taskUserDao.findTaskUserByCondition(taskUserCondition, null, order);
    }

    @Override
    public Long count() {
        return this.taskUserDao.count(null);
    }

    @Override
    public Long count(TaskUserCondition taskUserCondition) {
        return this.taskUserDao.count(taskUserCondition);
    }

    @Override
    public void createBatch(TaskUser[] eilist) {
        // TODO Auto-generated method stub
        this.taskUserDao.createBatch(eilist);
    }

    @Override
    public void modifyByTaskIdAndTeamIds(Integer taskId, Integer teamId) {
        // TODO Auto-generated method stub
        taskUserDao.updateByTaskIdAndTeamId(taskId, teamId);
    }
}
