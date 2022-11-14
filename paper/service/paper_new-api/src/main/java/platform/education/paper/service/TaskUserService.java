package platform.education.paper.service;

import platform.education.paper.model.TaskUser;
import platform.education.paper.vo.TaskUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.vo.TaskUserVo;

import java.util.List;

public interface TaskUserService {
    TaskUser findTaskUserById(Integer id);

    TaskUser add(TaskUser taskUser);

    TaskUser modify(TaskUser taskUser);

    void remove(TaskUser taskUser);

    List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Page page, Order order);

    List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition);

    List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Page page);

    List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Order order);

    Long count();

    Long count(TaskUserCondition taskUserCondition);

    void createBatch(TaskUser[] eilist);

    void modifyByTaskIdAndTeamIds(Integer taskId, Integer teamId);
}
