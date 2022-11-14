package platform.education.paper.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.Task;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.model.TaskUser;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.TaskCondition;
import platform.education.paper.vo.TaskVo;

import java.util.List;

public interface TaskService {
    Task findTaskById(Integer id);

    Task add(Task task);

    Task modify(Task task);

    void remove(Task task);

    List<Task> findTaskByCondition(TaskCondition taskCondition, Page page, Order order);

    List<Task> findTaskByCondition(TaskCondition taskCondition);

    List<Task> findTaskByCondition(TaskCondition taskCondition, Page page);

    List<Task> findTaskByCondition(TaskCondition taskCondition, Order order);

    Long count();

    Long count(TaskCondition taskCondition);

    /**
     * 插入主表以及任务关联表
     */
    void addPaperRelate(List<TaskTeam> teamIds, List<TaskUser> tus, Task task);

    /**
     * 查询这份试卷发布过的班级Id
     *
     * @param paperId
     * @return
     */
    List<Integer> findPaperStatus(Integer paperId);

    /**
     * 根据学段,科目和发布者ID查询我发布的试卷
     *
     * @param vo
     * @param userId
     * @return
     */
    List<TaskVo> findTaskVoByPaperVo(PaPaperVo vo, Integer userId, Page page, Order order);

    /**
     * 根据学段,科目和TeamID,ID查询班级发布的试卷
     *
     * @param vo
     * @param teamId
     * @return
     */
    List<TaskVo> findTaskVoByTeamId(PaPaperVo vo, Integer teamId, Page page, Order order);

    /**
     * 删除某一个班级的数据，如果删除完所有的班级，会自动删除主表的
     *
     * @param taskId
     * @param teamId
     */
    void deleteTaskRelate(Integer taskId, Integer teamId);

    /**
     * 根据学段,科目和发布者ID查询我发布的试卷
     *
     * @param vo
     * @param userId
     * @return
     */
    List<TaskVo> findTaskVoOfReceiveByPaperVo(PaPaperVo vo, Integer userId, Page page, Order order);

    /**
     * 根据统计ID找到那次任务
     *
     * @param examId
     * @return
     */
    TaskVo findTaskVoByExamId(Integer examId);

    void modifyFinished(Integer taskId, Integer userId);

    /**
     * 查询paper是否有发布过
     *
     * @param paperId
     * @return
     */
    boolean getHasTaskStateByPaperId(Integer paperId);

    void modifyBatchTaskPjExamId(List<TaskTeam> list);

    Task findTaskByUuid(String uuid);

    List<TaskVo> findTaskVo(Integer teamId, Integer subjectCode, String title, Integer schoolId, Integer gradeId, Page page, Order order);
}
