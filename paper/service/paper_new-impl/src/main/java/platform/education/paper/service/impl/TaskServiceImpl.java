package platform.education.paper.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.paper.dao.TaskDao;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.Task;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.model.TaskUser;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.TaskTeamService;
import platform.education.paper.service.TaskUserService;
import platform.education.paper.vo.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private TaskDao taskDao;

    private TaskTeamService taskTeamService;

    private TaskUserService taskUserService;

    private PaPaperService paPaperService;


    public PaPaperService getPaPaperService() {
        return paPaperService;
    }

    public void setPaPaperService(PaPaperService paPaperService) {
        this.paPaperService = paPaperService;
    }

    public TaskTeamService getTaskTeamService() {
        return taskTeamService;
    }

    public void setTaskTeamService(TaskTeamService taskTeamService) {
        this.taskTeamService = taskTeamService;
    }

    public TaskUserService getTaskUserService() {
        return taskUserService;
    }

    public void setTaskUserService(TaskUserService taskUserService) {
        this.taskUserService = taskUserService;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task findTaskById(Integer id) {
        try {
            return taskDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public Task add(Task task) {
        if (task == null) {
            return null;
        }
        Date createDate = task.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        task.setCreateDate(createDate);
        task.setModifyDate(createDate);
        return taskDao.create(task);
    }

    @Override
    public Task modify(Task task) {
        if (task == null) {
            return null;
        }
        Date modify = task.getModifyDate();
        task.setModifyDate(modify != null ? modify : new Date());
        return taskDao.update(task);
    }

    @Override
    public void remove(Task task) {
        try {
            taskDao.delete(task);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", task.getId(), e);
            }
        }
    }

    @Override
    public List<Task> findTaskByCondition(TaskCondition taskCondition, Page page, Order order) {
        return taskDao.findTaskByCondition(taskCondition, page, order);
    }

    @Override
    public List<Task> findTaskByCondition(TaskCondition taskCondition) {
        return taskDao.findTaskByCondition(taskCondition, null, null);
    }

    @Override
    public List<Task> findTaskByCondition(TaskCondition taskCondition, Page page) {
        return taskDao.findTaskByCondition(taskCondition, page, null);
    }

    @Override
    public List<Task> findTaskByCondition(TaskCondition taskCondition, Order order) {
        return taskDao.findTaskByCondition(taskCondition, null, order);
    }

    @Override
    public Long count() {
        return this.taskDao.count(null);
    }

    @Override
    public Long count(TaskCondition taskCondition) {
        return this.taskDao.count(taskCondition);
    }


    @Override
    public void addPaperRelate(List<TaskTeam> teamIds, List<TaskUser> tus, Task task) {
        task = taskDao.create(task);
        if (teamIds != null && teamIds.size() > 0 && tus != null && tus.size() > 0) {
            TaskTeam[] tts = new TaskTeam[teamIds.size()];
            for (int i = 0; i < teamIds.size(); i++) {
                TaskTeam tt = new TaskTeam();
                tt = teamIds.get(i);
                tt.setTaskId(task.getId());
                tts[i] = tt;
            }
            TaskUser[] tu = new TaskUser[tus.size()];
            for (int i = 0; i < tus.size(); i++) {
                TaskUser tuser = new TaskUser();
                tuser = tus.get(i);
                tuser.setTaskId(task.getId());
                tu[i] = tuser;
            }
            PaPaper pa = paPaperService.findPaPaperById(task.getPaperId());
            pa.setUsedCount(pa.getUsedCount() + 1);
            pa.setModifyDate(pa.getModifyDate());
            paPaperService.modify(pa);
            taskTeamService.createBatch(tts);
            taskUserService.createBatch(tu);
        }

    }

    @Override
    public List<Integer> findPaperStatus(Integer paperId) {

        return taskDao.findPaperStatus(paperId);
    }


    @Override
    public void deleteTaskRelate(Integer taskId, Integer teamId) {
        TaskTeamCondition tt = new TaskTeamCondition();
        tt.setTaskId(taskId);
        tt.setTeamId(teamId);
        tt.setIsDeleted(false);
        List<TaskTeam> ttlist = new ArrayList<TaskTeam>();
        ttlist = taskTeamService.findTaskTeamByCondition(tt);
        if (ttlist != null && ttlist.size() > 0) {
            taskTeamService.modifyByTaskIdAndTeamIds(taskId, teamId);
            taskUserService.modifyByTaskIdAndTeamIds(taskId, teamId);
        }
        tt = new TaskTeamCondition();
        tt.setTaskId(taskId);
        tt.setIsDeleted(false);
        ttlist = taskTeamService.findTaskTeamByCondition(tt);
        if (ttlist == null || ttlist.size() == 0) {

            Task task = taskDao.findById(taskId);
            task.setIsDeleted(true);
            taskDao.update(task);
        }
    }

    @Override
    public List<TaskVo> findTaskVoByPaperVo(PaPaperVo vo, Integer userId,
                                            Page page, Order order) {

        return taskDao.findTaskVoByPaperVo(vo, userId, page, order);
    }

    @Override
    public List<TaskVo> findTaskVoByTeamId(PaPaperVo vo, Integer teamId,
                                           Page page, Order order) {
        return taskDao.findTaskVoByTeamId(vo, teamId, page, order);
    }

    @Override
    public List<TaskVo> findTaskVoOfReceiveByPaperVo(PaPaperVo vo,
                                                     Integer userId, Page page, Order order) {
        return taskDao.findTaskVoOfReceiveByPaperVo(vo, userId, page, order);
    }

    @Override
    public void modifyFinished(Integer taskId, Integer userId) {

        TaskUserCondition tuc = new TaskUserCondition();
        tuc.setTaskId(taskId);
        tuc.setUserId(userId);
        List<TaskUser> tulist = new ArrayList<TaskUser>();
        tulist = taskUserService.findTaskUserByCondition(tuc);
        if (tulist != null && tulist.size() > 0) {
            TaskUser tu = tulist.get(0);
            if (tu.getFinishedFlag().intValue() == 2) {
                tu.setFinishedDate(new Date());
                tu.setFinishedFlag(1);
                taskUserService.modify(tu);
                taskDao.updateFinishedCount(taskId);
            }
        }
    }

    @Override
    public TaskVo findTaskVoByExamId(Integer examId) {

        return taskDao.findTaskVoByExamId(examId);
    }

    @Override
    public boolean getHasTaskStateByPaperId(Integer paperId) {
        Page page = new Page();
        TaskCondition taskCondition = new TaskCondition();
        taskCondition.setPaperId(paperId);
        List<Task> taskList = this.findTaskByCondition(taskCondition, page);
        if (taskList.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public void modifyBatchTaskPjExamId(List<TaskTeam> list) {
        if (list != null && list.size() > 0) {
            for (TaskTeam tt : list) {
                taskDao.updateBatchTaskPjExamId(tt.getId(), tt.getPjExamId());
            }
        }

    }

    @Override
    public Task findTaskByUuid(String uuid) {
        return taskDao.findTaskByUuid(uuid);
    }

    @Override
    public List<TaskVo> findTaskVo(Integer teamId, Integer subjectCode, String title, Integer schoolId, Integer gradeId, Page page, Order order) {
        return taskDao.findTaskVo(teamId, subjectCode, title, schoolId, gradeId, page, order);
    }

}
