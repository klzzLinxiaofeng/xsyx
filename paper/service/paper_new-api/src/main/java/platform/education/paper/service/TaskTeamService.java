package platform.education.paper.service;

import platform.education.paper.model.TaskTeam;
import platform.education.paper.vo.TaskTeamCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.vo.TaskTeamVo;

import java.util.List;

public interface TaskTeamService {
    TaskTeam findTaskTeamById(Integer id);

    TaskTeam add(TaskTeam taskTeam);

    TaskTeam modify(TaskTeam taskTeam);

    void remove(TaskTeam taskTeam);

    List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Page page, Order order);

    List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition);

    List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Page page);

    List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Order order);

    Long count();

    Long count(TaskTeamCondition taskTeamCondition);

    void createBatch(TaskTeam[] eilist);

    void modifyByTaskIdAndTeamIds(Integer taskId, Integer teamId);

    TaskTeam findTaskTeamByTaskIdAndTeamId(Integer taskId, Integer teamId);

    TaskTeam findTaskTeamUnique(Integer taskId, Integer teamId);

    List<TaskTeamVo> findTaskTeamByTaskId(Integer taskId);
}
