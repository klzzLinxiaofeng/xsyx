/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service.impl;

import framework.generic.dao.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import platform.education.lads.contants.FinishedStatus;
import platform.education.lads.contants.ToolName;
import platform.education.lads.contants.mediaToolCons.StudyStatus;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.model.LadsMediaUserStatusTool;
import platform.education.lads.service.LadsMediaToolService;
import platform.education.lads.service.LadsMediaUserStatusToolService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsToolLibraryService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.service.MediaToolService;
import platform.education.lads.util.StrUtils;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;
import platform.education.lads.vo.mediaToolVo.LadsMediaToolCondition;
import platform.education.lads.vo.LadsUserVo;
import platform.education.lads.vo.mediaToolVo.LadsMediaUserStatusVo;
import platform.education.resource.utils.UUIDUtil;

/**
 * media工具业务器
 *
 * @author Administrator
 */
public class MediaToolServiceImpl implements MediaToolService {

    private LadsService ladsService;
    private LadsUserService ladsUserService;
    private LadsMediaToolService ladsMediaToolService;
    private LadsMediaUserStatusToolService ladsMediaUserStatusToolService;
    private LadsToolLibraryService ladsToolLibraryService;

    public void setLadsMediaToolService(LadsMediaToolService ladsMediaToolService) {
        this.ladsMediaToolService = ladsMediaToolService;
    }

    public void setLadsService(LadsService ladsService) {
        this.ladsService = ladsService;
    }

    public void setLadsToolLibraryService(LadsToolLibraryService ladsToolLibraryService) {
        this.ladsToolLibraryService = ladsToolLibraryService;
    }

    public void setLadsMediaUserStatusToolService(LadsMediaUserStatusToolService ladsMediaUserStatusToolService) {
        this.ladsMediaUserStatusToolService = ladsMediaUserStatusToolService;
    }

    public void setLadsUserService(LadsUserService ladsUserService) {
        this.ladsUserService = ladsUserService;
    }

    @Override
    public String toHtml() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toXml() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LadsMediaTool save(String toolId, String title, String entityId, String uploadList) {
        LadsMediaTool editor = getMediaByToolId(toolId);
        boolean updateFlag = false;
        if (editor == null) {
            editor = new LadsMediaTool();
            editor.setCreateDate(new Date());
            editor.setUuid(UUIDUtil.getUUID());
            editor.setToolLibrary(ToolName.MEDIA_TOOL);
        } else {
            updateFlag = true;
        }
        editor.setTitle(title);
        editor.setToolId(toolId);
        editor.setEntityId(entityId);
        editor.setUploadList(uploadList);
        if (updateFlag) {
            editor = this.ladsMediaToolService.modify(editor);
        } else {
            editor = this.ladsMediaToolService.add(editor);
        }
        return editor;
    }

    @Override
    public LadsMediaTool getMediaByToolId(String toolId) {
        LadsMediaToolCondition etc = new LadsMediaToolCondition();
        etc.setToolId(toolId);
        List<LadsMediaTool> list = this.ladsMediaToolService.findLadsMediaToolByCondition(etc, Order.desc("create_date"));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LadsMediaUserStatusTool getMediaUserStatusByToolIdAndUserId(String toolId, Integer userId) {
        GetToolCondition egsc = new GetToolCondition();
        egsc.setToolId(toolId);
        egsc.setUserId(userId);
        List list = this.ladsMediaUserStatusToolService.findUserStatusByToolIdAndUserId(egsc);
        if (list.size() > 0) {
            return (LadsMediaUserStatusTool) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LadsMediaUserStatusTool saveUserStatus(String toolId, Integer userId, Double lastPlayTime, String finishedFlag, String mediaScore) {
        LadsMediaUserStatusTool status = getMediaUserStatusByToolIdAndUserId(toolId, userId);
        if (status == null) {
            status = new LadsMediaUserStatusTool();
            status.setCreateDate(new Date());
            status.setModifyDate(new Date());
            status.setMediaTool(getMediaByToolId(toolId).getUuid());
            status.setStatus(StudyStatus.STUDYED);
            status.setStudyTime(1);
            status.setScore(mediaScore == null || "".equals(mediaScore) ? "0" : mediaScore);
            status.setUserId(userId);
            status.setUuid(UUIDUtil.getUUID());
            this.ladsMediaUserStatusToolService.add(status);
        } else {
            status.setScore(mediaScore == null || "".equals(mediaScore) ? "0" : mediaScore);
            if (finishedFlag != null && "complete".equals(finishedFlag)) {
                status.setStatus(StudyStatus.FINISHED);
            } else //如果之前已经看完就不需要改成已看过
            {
                if (!status.getStatus().equals(StudyStatus.FINISHED)) {
                    status.setStatus(StudyStatus.STUDYED);
                }
            }
            //最后播放时间为空时证明在记录浏览次数
            if (lastPlayTime != null) {
                //记录最后播放时间
                status.setLastPlayTime(lastPlayTime);
            } else {
                //记录浏览次数
                status.setStudyTime(status.getStudyTime() + 1);
            }
            status.setModifyDate(new Date());
            this.ladsMediaUserStatusToolService.modify(status);
        }
        return status;
    }

    @Override
    public Object[] getUserStatusList(String ldId, String toolId) {
        Object[] obj = new Object[3];
        int finish = 0;
        int notFinish = 0;
        List<LadsUserVo> userList = this.ladsUserService.getStudyUserList(ldId);
        List<LadsMediaUserStatusVo> voList = new ArrayList();
        for (LadsUserVo user : userList) {
            LadsMediaUserStatusVo vo = new LadsMediaUserStatusVo();
            vo.setRealName(user.getRealName());
            vo.setUserId(user.getUserId());
            LadsMediaUserStatusTool status = this.getMediaUserStatusByToolIdAndUserId(toolId, user.getUserId());
            if (status != null) {
                vo.setStatus(status);
                if (status.getStatus().equals(StudyStatus.FINISHED)) {
                    finish++;
                }
                if (status.getLastPlayTime() != null && !"".equals(status.getLastPlayTime())) {
                    Long lpt = status.getLastPlayTime().longValue();
                    String sd = timeMillisToString(lpt);
                    vo.setFormatLastPlayTime(sd);
                }
            }
            voList.add(vo);
        }
        notFinish = voList.size() - finish;
        obj[0] = voList;
        obj[1] = finish;
        obj[2] = notFinish;
        return obj;
    }

    @Override
    public int getFinishedStatus(String toolId, Integer userId) {
        LadsMediaUserStatusTool status = getMediaUserStatusByToolIdAndUserId(toolId, userId);
        if (status != null) {
            if (StudyStatus.FINISHED.equals(status.getStatus())) {
                return FinishedStatus.FINISHED;
            } else {
                return FinishedStatus.NOT_FINISHED;
            }
        } else {
            return FinishedStatus.NOT_FINISHED;
        }
    }

    @Override
    public int countFinishedStatus(List<String> toolIds, Integer userId) {
        if (toolIds.size() > 0) {
            GetToolStatusCondition gtsc = new GetToolStatusCondition();
            gtsc.setToolIds(toolIds);
            gtsc.setUserId(userId);
            int count = this.ladsMediaUserStatusToolService.countFinishedStatus(gtsc).intValue();
            return count;
        } else {
            return 0;
        }
    }

    @Override
    public String getUserScore(String toolId, Integer userId) {
        LadsMediaUserStatusTool status = getMediaUserStatusByToolIdAndUserId(toolId, userId);
        if (status != null) {
            return status.getScore();
        } else {
            return null;
        }
    }

    @Override
    public double getUserScore(List<String> toolIds, Integer userId) {
        CountFinishedStatusCondition cfsc = new CountFinishedStatusCondition();
        cfsc.setToolIds(toolIds);
        cfsc.setUserId(userId);
        List<String> list = this.ladsMediaUserStatusToolService.findScoreByToolIdAndUserId(cfsc);
        double score = 0;
        for (String obj : list) {
            if (obj != null && !("".equals(obj)) && StrUtils.isNumeric(obj)) {
                double realScore = Double.parseDouble(obj);
                score = score + realScore;
            }
        }
        return score;
    }

    public String timeMillisToString(long timeMillis) {
        int minute;
        int second;
        String m;
        String s;
        minute = (int) (timeMillis / 60);
        second = (int) (timeMillis % 60);
        if (minute < 10) {
            m = "0" + minute;
        } else {
            m = String.valueOf(minute);
        }
        if (second < 10) {
            s = "0" + second;
        } else {
            s = String.valueOf(second);
        }
        return m + ":" + s;
    }

}
