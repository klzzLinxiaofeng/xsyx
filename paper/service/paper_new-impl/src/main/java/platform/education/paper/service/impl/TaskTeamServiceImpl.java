package platform.education.paper.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.paper.dao.TaskTeamDao;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.service.TaskTeamService;
import platform.education.paper.util.StatisticsUtil;
import platform.education.paper.vo.TaskTeamCondition;
import platform.education.paper.vo.TaskTeamVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TaskTeamServiceImpl implements TaskTeamService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private TaskTeamDao taskTeamDao;

    public void setTaskTeamDao(TaskTeamDao taskTeamDao) {
        this.taskTeamDao = taskTeamDao;
    }

    @Override
    public TaskTeam findTaskTeamById(Integer id) {
        try {
            return taskTeamDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public TaskTeam add(TaskTeam taskTeam) {
        if (taskTeam == null) {
            return null;
        }
        Date createDate = taskTeam.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        taskTeam.setCreateDate(createDate);
        taskTeam.setModifyDate(createDate);
        return taskTeamDao.create(taskTeam);
    }

    @Override
    public TaskTeam modify(TaskTeam taskTeam) {
        if (taskTeam == null) {
            return null;
        }
        Date modify = taskTeam.getModifyDate();
        taskTeam.setModifyDate(modify != null ? modify : new Date());
        return taskTeamDao.update(taskTeam);
    }

    @Override
    public void remove(TaskTeam taskTeam) {
        try {
            taskTeamDao.delete(taskTeam);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", taskTeam.getId(), e);
            }
        }
    }

    @Override
    public List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Page page, Order order) {
        return taskTeamDao.findTaskTeamByCondition(taskTeamCondition, page, order);
    }

    @Override
    public List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition) {
        return taskTeamDao.findTaskTeamByCondition(taskTeamCondition, null, null);
    }

    @Override
    public List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Page page) {
        return taskTeamDao.findTaskTeamByCondition(taskTeamCondition, page, null);
    }

    @Override
    public List<TaskTeam> findTaskTeamByCondition(TaskTeamCondition taskTeamCondition, Order order) {
        return taskTeamDao.findTaskTeamByCondition(taskTeamCondition, null, order);
    }

    @Override
    public Long count() {
        return this.taskTeamDao.count(null);
    }

    @Override
    public Long count(TaskTeamCondition taskTeamCondition) {
        return this.taskTeamDao.count(taskTeamCondition);
    }

    @Override
    public void createBatch(TaskTeam[] eilist) {
        taskTeamDao.createBatch(eilist);
    }

    @Override
    public void modifyByTaskIdAndTeamIds(Integer taskId, Integer teamId) {
        taskTeamDao.updateByTaskIdAndTeamId(taskId, teamId);
    }

    @Override
    public TaskTeam findTaskTeamByTaskIdAndTeamId(Integer taskId, Integer teamId) {
        TaskTeamCondition examRelateCondition = new TaskTeamCondition();
        examRelateCondition.setTaskId(taskId);
        examRelateCondition.setTeamId(teamId);
        examRelateCondition.setIsDeleted(false);
        List<TaskTeam> examRelateList = this.findTaskTeamByCondition(examRelateCondition);
        if (examRelateList.size() > 0) {
            return examRelateList.get(0);
        }
        return null;
    }

    @Override
    public TaskTeam findTaskTeamUnique(Integer taskId, Integer teamId) {
        /** 试卷发布的班级情况 */
        TaskTeamCondition examRelateCondition = new TaskTeamCondition();
        examRelateCondition.setTaskId(taskId);
        examRelateCondition.setTeamId(teamId);
        examRelateCondition.setIsDeleted(false);
        List<TaskTeam> examRelateList = this.findTaskTeamByCondition(examRelateCondition);

        if (examRelateList.size() > 0) {
            return examRelateList.get(0);
        }
        return null;
    }

    @Override
    public List<TaskTeamVo> findTaskTeamByTaskId(Integer taskId) {
        if (taskId == null) {
            return new ArrayList<>();
        }
        List<TaskTeamVo> taskTeamVoList = this.taskTeamDao.findTaskTeamByTaskId(taskId);
        for (TaskTeamVo taskTeamVo : taskTeamVoList) {
            String[] toc = taskTeamVo.getTotalOfCompleted().split("/");
            if (toc[1].equals("0")) {
                taskTeamVo.setFinishRate("-");
            } else if (toc[0].equals("0")) {
                taskTeamVo.setFinishRate("0%");
            } else {
                BigDecimal x = new BigDecimal(toc[0]);
                BigDecimal y = new BigDecimal(toc[1]);

                float percent = (x.floatValue() / y.floatValue()) * 100;
                BigDecimal finishRate = new BigDecimal(percent);
                taskTeamVo.setFinishRate(StatisticsUtil.subZeroAndDot(finishRate.setScale(2, BigDecimal.ROUND_HALF_UP).toString()) + "%");
            }
        }
        Collections.sort(taskTeamVoList);
        return taskTeamVoList;
    }


}
