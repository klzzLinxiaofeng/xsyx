/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.web.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.lads.contants.editorToolCons.StudyStatus;
import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsEditorUserStatusTool;
import platform.education.lads.service.EditorToolService;
import platform.education.lads.service.LadsEditorUserStatusToolService;
import platform.education.lads.service.impl.EditorToolServiceImpl;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author Administrator
 */
@Controller("editorToolController")
@Scope("prototype")
@RequestMapping(value = "/lads/tool/editor")
public class EditorToolController{

    @Resource
    private EditorToolService editorToolService;
    @Resource
    private LadsEditorUserStatusToolService ladsEditorUserStatusToolService;

    @RequestMapping(value = "/saveUserStatus")
    public String saveUserStatus(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String toolId = request.getParameter("toolId");
        String editorScore = request.getParameter("editorScore");
        if (userId != null && !"".equals(userId)) {
            this.editorToolService.saveUserStatus(toolId, Integer.parseInt(userId), editorScore);
        }
        return null;
    }

    @RequestMapping(value = "/seeContent")
    public String seeContent(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String toolId = request.getParameter("toolId");
        LadsEditorTool et = this.editorToolService.getEditorByToolId(toolId);
        PrintWriter pw = this.setAjaxResponse(request, response);
        String content = et.getContent();
        if(content!=null&&!"".equals(content.trim())){
            pw.print(et.getContent());
        }else{
            pw.print("没有内容");
        }
        return null;
    }

    @RequestMapping(value = "/editUserScore")
    public String editUserScore(HttpServletRequest request, HttpServletResponse response) {
        String statusId = request.getParameter("statusId");
        String score = request.getParameter("score");
        LadsEditorUserStatusTool status;
        if (statusId != null && !"".equals(statusId)) {
            status = this.ladsEditorUserStatusToolService.findLadsEditorUserStatusToolByUuid(statusId);
            //已学的用户修改分数
            status.setScore(score);
            this.ladsEditorUserStatusToolService.modify(status);
        } else {
            //未学的用户修改分数
            String toolId = request.getParameter("toolId");
            String userId = request.getParameter("userId");
            status = new LadsEditorUserStatusTool();
            status.setCreateDate(new Date());
            status.setModifyDate(new Date());
            status.setEditorTool(this.editorToolService.getEditorByToolId(toolId).getUuid());
            status.setStudyTime(0);
            status.setStatus(StudyStatus.NOT_STUDY);
            status.setScore(score == null || "".equals(score) ? "0" : score);
            status.setUserId(Integer.parseInt(userId));
            status.setUuid(UUIDUtil.getUUID());
            this.ladsEditorUserStatusToolService.add(status);
        }
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
