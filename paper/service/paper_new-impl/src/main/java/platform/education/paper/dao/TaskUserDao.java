package platform.education.paper.dao;

import platform.education.paper.model.TaskUser;
import platform.education.paper.vo.TaskUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.paper.vo.TaskUserVo;

import java.util.List;

public interface TaskUserDao extends GenericDao<TaskUser, java.lang.Integer> {

    List<TaskUser> findTaskUserByCondition(TaskUserCondition taskUserCondition, Page page, Order order);

    TaskUser findById(Integer id);

    Long count(TaskUserCondition taskUserCondition);

    void createBatch(TaskUser[] eilist);

    void updateByTaskIdAndTeamId(Integer taskId, Integer teamId);
}
