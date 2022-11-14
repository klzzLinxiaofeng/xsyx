package platform.education.lads.web.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyVo;
import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusVo;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import platform.education.lads.contants.discussToolCons.AllowUpload;
import platform.education.lads.model.LadsDiscussReplyTool;
import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.model.LadsDiscussUserStatusTool;
import platform.education.lads.service.DiscussToolService;
import platform.education.lads.service.LadsDiscussUserStatusToolService;

/**
 *
 * @author Administrator
 */
@Controller("discussToolController")
@Scope("prototype")
@RequestMapping(value = "/lads/tool/discuss")
public class DiscussToolController {

    @Resource
    private DiscussToolService discussToolService;
    @Resource
    private LadsDiscussUserStatusToolService ladsDiscussUserStatusToolService;
    public File attachment;
    private String attachmentFileName;
    private String attachmentContentType;

    public static final String baseDir = "lads";
    public static final String learningDir = baseDir + "/learning";
    public static final String monitoringDir = baseDir + "/monitoring";
    public static final String discussToolDir = "/discuss";

    public File getAttachment() {
        return attachment;
    }

    public void setAttachment(File attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentContentType() {
        return attachmentContentType;
    }

    public void setAttachmentContentType(String attachmentContentType) {
        this.attachmentContentType = attachmentContentType;
    }

    @RequestMapping(value = "/reply")
    public String reply(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String reply = request.getParameter("reply");
        String userId = request.getParameter("userId");
        String toolId = request.getParameter("toolId");
        String parentReply = request.getParameter("parentReply");
        String attachmentJson = request.getParameter("attachmentJson");
        Integer userIdint = null;
        if (userId != null && !"".equals(userId)) {
            userIdint = Integer.parseInt(userId);
        }
        LadsDiscussReplyTool dr = this.discussToolService.saveDiscussReply(toolId, userIdint, reply, attachmentJson, parentReply);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(dr.getId());
        pw.close();
        return null;
    }

    @RequestMapping(value = "/deleteReply")
    public String deleteReply(HttpServletRequest request, HttpServletResponse response) {
        String replyId = request.getParameter("replyId");
        this.discussToolService.deleteDiscussReply(replyId);
        return null;
    }

    @RequestMapping(value = "/getMonitoringReplyList")
    public String getMonitoringReplyList(HttpServletRequest request, HttpServletResponse response) {
        String toolId = request.getParameter("toolId");
        String userId = request.getParameter("userId");
        String sessionUserId = request.getParameter("sessionUserId");
        Integer userIdint = null;
        Integer sessionUserIdint = null;
        if (userId != null && !"".equals(userId)) {
            userIdint = Integer.parseInt(userId);
        }
        if (sessionUserId != null && !"".equals(sessionUserId)) {
            sessionUserIdint = Integer.parseInt(sessionUserId);
        }
        List<LadsDiscussReplyVo> replyList = this.discussToolService.getReplyListByToolId(toolId, userIdint, sessionUserIdint);
        LadsDiscussTool discuss = this.discussToolService.getLadsDiscussToolByToolId(toolId);
        String title = discuss.getTitle();
        String content = discuss.getContent();
        if (content != null) {
            content = content.replaceAll("\r\n", "<br/>");
            content = content.replaceAll("\n", "<br/>");
        }
        discuss.setTitle(title);
        discuss.setContent(content);
        request.setAttribute("userId", userId);
        request.setAttribute("id", toolId);
        request.setAttribute("discuss", discuss);
        request.setAttribute("replyList", replyList);
        request.setAttribute("discussAllowCons", AllowUpload.ALLOW);
        return monitoringDir + discussToolDir + "/discussReplyList";
    }

    @RequestMapping(value = "/getStudentVoList")
    public String getStudentVoList(HttpServletRequest request, HttpServletResponse response) {
        String toolId = request.getParameter("toolId");
        String ldId = request.getParameter("ldId");
        String type = request.getParameter("type");
        Object[] obj = this.discussToolService.getUserStatusList(ldId, toolId);
        List<LadsDiscussUserStatusVo> voList = (List<LadsDiscussUserStatusVo>) obj[0];
        request.setAttribute("voList", voList);
        request.setAttribute("id", toolId);
        if (type != null && !"".equals(type) && "file".equals(type)) {
            return monitoringDir + discussToolDir + "/discussFileList";
        } else {
            return monitoringDir + discussToolDir + "/discussStudentList";
        }
    }

    @RequestMapping(value = "/getLearningReplyList")
    public String getLearningReplyList(HttpServletRequest request, HttpServletResponse response) {
        String toolId = request.getParameter("toolId");
        String sessionUserId = request.getParameter("sessionUserId");
        Integer sessionUserIdint = null;
        if (sessionUserId != null && !"".equals(sessionUserId)) {
            sessionUserIdint = Integer.parseInt(sessionUserId);
        }
        List<LadsDiscussReplyVo> replyList = this.discussToolService.getReplyListByToolId(toolId, null, sessionUserIdint);
        request.setAttribute("replyList", replyList);
        request.setAttribute("id", toolId);
        return learningDir + discussToolDir + "/discussReplyList";
    }

    @RequestMapping(value = "/uploadAttachment")
    public String uploadAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String toolId = request.getParameter("toolId");
        String fileId = this.discussToolService.uploadAttachment(attachment, attachmentFileName, toolId);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(fileId);
        pw.close();
        return null;
    }

    @RequestMapping(value = "/deleteAttachment")
    public String deleteAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String toolId = request.getParameter("toolId");
        String replyId = request.getParameter("replyId");
        String fileId = request.getParameter("fileId");
        this.discussToolService.deleteAttachment(replyId, fileId);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(fileId);
        pw.close();
        return null;
    }

    @RequestMapping(value = "/editUserScore")
    public String editUserScore(HttpServletRequest request, HttpServletResponse response) {
        String statusId = request.getParameter("statusId");
        String score = request.getParameter("score");
        LadsDiscussUserStatusTool status = ladsDiscussUserStatusToolService.findLadsDiscussUserStatusToolByUuid(statusId);
        status.setScore(score);
        ladsDiscussUserStatusToolService.modify(status);
        return null;
    }

    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
        return response.getWriter();
    }
}
