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
import platform.education.lads.contants.editorToolCons.StudyStatus;
import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsEditorUserStatusTool;
import platform.education.lads.service.EditorToolService;
import platform.education.lads.service.LadsEditorToolService;
import platform.education.lads.service.LadsEditorUserStatusToolService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsToolLibraryService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.util.StrUtils;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.GetToolStatusCondition;
import platform.education.lads.vo.editortoolVo.LadsEditorToolCondition;
import platform.education.lads.vo.editortoolVo.LadsEditorUserStatusVo;
import platform.education.lads.vo.LadsUserVo;
import platform.education.resource.utils.UUIDUtil;

/**
 * editor工具业务器
 *
 * @author Administrator
 */
public class EditorToolServiceImpl implements EditorToolService {

    private LadsService ladsService;
    private LadsUserService ladsUserService;
    private LadsEditorToolService ladsEditorToolService;
    private LadsToolLibraryService ladsToolLibraryService;
    private LadsEditorUserStatusToolService ladsEditorUserStatusToolService;

    public void setLadsEditorToolService(LadsEditorToolService ladsEditorToolService) {
        this.ladsEditorToolService = ladsEditorToolService;
    }

    public void setLadsService(LadsService ladsService) {
        this.ladsService = ladsService;
    }

    public void setLadsToolLibraryService(LadsToolLibraryService ladsToolLibraryService) {
        this.ladsToolLibraryService = ladsToolLibraryService;
    }

    public void setLadsEditorUserStatusToolService(LadsEditorUserStatusToolService ladsEditorUserStatusToolService) {
        this.ladsEditorUserStatusToolService = ladsEditorUserStatusToolService;
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
    public LadsEditorTool save(String toolId, String title, String content, String uploadList) {
        LadsEditorTool editor = getEditorByToolId(toolId);
        boolean updateFlag = false;
        if (editor == null) {
            editor = new LadsEditorTool();
            editor.setCreateDate(new Date());
            editor.setUuid(UUIDUtil.getUUID());
            editor.setToolLibrary(ToolName.EDITOR_TOOL);
        } else {
            updateFlag = true;
        }
        editor.setTitle(title);
        editor.setToolId(toolId);
        editor.setContent(content);
        editor.setUploadList(uploadList);
        if (updateFlag) {
            editor = this.ladsEditorToolService.modify(editor);
        } else {
            editor = this.ladsEditorToolService.add(editor);
        }
        return editor;
    }

    @Override
    public LadsEditorTool getEditorByToolId(String toolId) {
        LadsEditorToolCondition etc = new LadsEditorToolCondition();
        etc.setToolId(toolId);
        List<LadsEditorTool> list = this.ladsEditorToolService.findLadsEditorToolByCondition(etc, Order.desc("create_date"));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LadsEditorUserStatusTool getEditorUserStatusByToolIdAndUserId(String toolId, Integer userId) {
        GetToolCondition egsc = new GetToolCondition();
        egsc.setToolId(toolId);
        egsc.setUserId(userId);
        List list = this.ladsEditorUserStatusToolService.findUserStatusByToolIdAndUserId(egsc);
        if (list.size() > 0) {
            return (LadsEditorUserStatusTool) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LadsEditorUserStatusTool saveUserStatus(String toolId, Integer userId, String editorScore) {
        LadsEditorUserStatusTool status = getEditorUserStatusByToolIdAndUserId(toolId, userId);
        if (status == null) {
            status = new LadsEditorUserStatusTool();
            status.setCreateDate(new Date());
            status.setModifyDate(new Date());
            status.setEditorTool(getEditorByToolId(toolId).getUuid());
            status.setStatus(StudyStatus.STUDYED);
            status.setStudyTime(1);
            status.setScore(editorScore == null || "".equals(editorScore) ? "0" : editorScore);
            status.setUserId(userId);
            status.setUuid(UUIDUtil.getUUID());
            this.ladsEditorUserStatusToolService.add(status);
        } else {
            status.setScore(editorScore == null || "".equals(editorScore) ? "0" : editorScore);
            status.setStatus(StudyStatus.STUDYED);
            status.setModifyDate(new Date());
            status.setStudyTime(status.getStudyTime() + 1);
            this.ladsEditorUserStatusToolService.modify(status);
        }
        return status;
    }

    @Override
    public Object[] getUserStatusList(String ldId, String toolId) {
        Object[] obj = new Object[3];
        int finish = 0;
        int notFinish = 0;
        List<LadsUserVo> userList = this.ladsUserService.getStudyUserList(ldId);
        List<LadsEditorUserStatusVo> voList = new ArrayList();
        for (LadsUserVo user : userList) {
            LadsEditorUserStatusVo vo = new LadsEditorUserStatusVo();
            vo.setRealName(user.getRealName());
            vo.setUserId(user.getUserId());
            LadsEditorUserStatusTool status = this.getEditorUserStatusByToolIdAndUserId(toolId, user.getUserId());
            if (status != null) {
                vo.setStatus(status);
                if (status.getStatus().equals(StudyStatus.STUDYED)) {
                    finish++;
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
        LadsEditorUserStatusTool status = getEditorUserStatusByToolIdAndUserId(toolId, userId);
        if (status != null) {
            if (StudyStatus.STUDYED.equals(status.getStatus())) {
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
            int count = this.ladsEditorUserStatusToolService.countFinishedStatus(gtsc).intValue();
            return count;
        } else {
            return 0;
        }
    }

    @Override
    public String getUserScore(String toolId, Integer userId) {
        LadsEditorUserStatusTool status = getEditorUserStatusByToolIdAndUserId(toolId, userId);
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
        List<String> list = this.ladsEditorUserStatusToolService.findScoreByToolIdAndUserId(cfsc);
        double score = 0;
        for (String obj : list) {
            if (obj != null && !("".equals(obj)) && StrUtils.isNumeric(obj)) {
                double realScore = Double.parseDouble(obj);
                score = score + realScore;
            }
        }
        return score;
    }
}
