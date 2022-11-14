package platform.education.paper.dao;

import platform.education.paper.model.TaskTeam;
import platform.education.paper.vo.TaskTeamCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.paper.vo.TaskTeamVo;

import java.util.List;

public interface TaskTeamDao extends GenericDao<TaskTeam, java.lang.Integer> {

    List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Page page, Order order);

    TaskTeam findById(Integer id);

    Long count(TaskTeamCondition taskTeamCondition);

    void createBatch(TaskTeam[] eilist);

    void updateByTaskIdAndTeamId(Integer taskId, Integer teamId);

    List<TaskTeamVo> findTaskTeamByTaskId(Integer taskId);
}
