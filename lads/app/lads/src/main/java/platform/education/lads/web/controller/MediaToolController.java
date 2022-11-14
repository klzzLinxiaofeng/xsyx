package platform.education.lads.web.controller;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.lads.contants.mediaToolCons.StudyStatus;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.model.LadsMediaUserStatusTool;
import platform.education.lads.service.LadsMediaUserStatusToolService;
import platform.education.lads.service.MediaToolService;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author 罗志明
 */
@Controller("mediaToolController")
@Scope("prototype")
@RequestMapping(value = "/lads/tool/media")
public class MediaToolController {

    @Resource
    private MediaToolService mediaToolService;
    @Resource
    private LadsMediaUserStatusToolService ladsMediaUserStatusToolService;

    public static final String baseDir = "lads";
    public static final String monitoringDir = baseDir + "/monitoring";
    ;
    public static final String mediaToolDir = "/media";

    @RequestMapping(value = "/saveUserStatus")
    public String saveUserStatus(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String toolId = request.getParameter("toolId");
        String lastPlayTime = request.getParameter("lastPlayTime");
        String mediaScore = request.getParameter("mediaScore");
        String finishedFlag = request.getParameter("finishedFlag");
        Double ld = null;
        if (lastPlayTime != null && !"".equals(lastPlayTime) && !"0".equals(lastPlayTime)) {
            ld = Double.parseDouble(lastPlayTime);
        }
        if (userId != null && !"".equals(userId)) {
            this.mediaToolService.saveUserStatus(toolId, Integer.parseInt(userId), ld, finishedFlag, mediaScore);
        }
        return null;
    }
    
    @RequestMapping(value = "/editUserScore")
    public String editUserScore(HttpServletRequest request, HttpServletResponse response) {
        String statusId = request.getParameter("statusId");
        String score = request.getParameter("score");
        LadsMediaUserStatusTool status;
        if (statusId != null && !"".equals(statusId)) {
            status = this.ladsMediaUserStatusToolService.findLadsMediaUserStatusToolByUuid(statusId);
            //已学的用户修改分数
            status.setScore(score);
            this.ladsMediaUserStatusToolService.modify(status);
        } else {
            //未学的用户修改分数
            String toolId = request.getParameter("toolId");
            String userId = request.getParameter("userId");
            status = new LadsMediaUserStatusTool();
            status.setCreateDate(new Date());
            status.setModifyDate(new Date());
            status.setMediaTool(this.mediaToolService.getMediaByToolId(toolId).getUuid());
            status.setStudyTime(0);
            status.setStatus(StudyStatus.NOT_STUDY);
            status.setScore(score == null || "".equals(score) ? "0" : score);
            status.setUserId(Integer.parseInt(userId));
            status.setUuid(UUIDUtil.getUUID());
            this.ladsMediaUserStatusToolService.add(status);
        }
        return null;
    }

    @RequestMapping(value = "/previewMonitorMedia")
    public String previewMonitorMedia(HttpServletRequest request, HttpServletResponse response) {
        String toolId = request.getParameter("toolId");
        LadsMediaTool media = this.mediaToolService.getMediaByToolId(toolId);
        request.setAttribute("media", media);
        request.setAttribute("id", toolId);
        return monitoringDir + mediaToolDir + "/mediapreview";
    }
}
