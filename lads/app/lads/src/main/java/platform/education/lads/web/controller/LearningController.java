/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.web.controller;

import framework.generic.dao.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.lads.contants.ActivityType;
import platform.education.lads.contants.ToolName;
import platform.education.lads.contants.discussToolCons.AllowUpload;
import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.model.LadsPreviewJson;
import platform.education.lads.service.DiscussToolService;
import platform.education.lads.service.EditorToolService;
import platform.education.lads.service.LadsActivityService;
import platform.education.lads.service.LadsActivityTransitionService;
import platform.education.lads.service.LadsActivityTypeService;
import platform.education.lads.service.LadsLastStudyRecordService;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.service.LadsPreviewJsonService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.service.MediaToolService;
import platform.education.lads.vo.LadsActivityCondition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import platform.education.lads.vo.LadsLastStudyRecordCondition;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author Administrator
 */
@Controller("learningController")
@Scope("prototype")
@RequestMapping(value = "/lads/learning")
public class LearningController {

    @Resource
    private EditorToolService editorToolService;
    @Resource
    private MediaToolService mediaToolService;
    @Resource
    private DiscussToolService discussToolService;
//    @Resource
//    private QuizToolService quizToolServiceImpl;
//    @Resource
//    private FaceTeachingToolService faceTeachingToolServiceImpl;
//    @Resource
//    private SurveyToolService surveyToolServiceImpl;
//    @Resource
//    public PowerPointToolService powerPointToolServiceImpl;
    @Resource
    private LadsService ladsService;
    @Resource
    private LadsUserService ladsUserService;
    //新加入的依赖注入
    @Resource
    public LadsLearningdesignService ladsLearningdesignService;
    @Resource
    public LadsLastStudyRecordService ladsLastStudyRecordService;
    @Resource
    public LadsPreviewJsonService ladsPreviewJsonService;
    @Resource
    public LadsActivityService ladsActivityService;
    @Resource
    public LadsActivityTypeService ladsActivityTypeService;
    @Resource
    public LadsActivityTransitionService ladsActivityTransitionService;
    public static final String baseDir = "lads";
    public static final String learningDir = baseDir + "/learning";
    public static final String discussToolDir = "/discuss";

    //学习功能
    @RequestMapping(value = "/learn")
    public String learn(HttpServletRequest request, HttpServletResponse response) {
        String ldId = request.getParameter("ldId");
        String toolId = request.getParameter("toolId");
        String template = request.getParameter("template");
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        request.setAttribute("ld", ld);
        request.setAttribute("toolId", toolId);
        if (template != null && !"".equals(template)) {
            return learningDir + "/learningpaper_" + template;
        } else {
            return learningDir + "/learningpaper";
        }
    }

    //预览功能
    @RequestMapping(value = "/preview")
    public String preview(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String jsonId = request.getParameter("jsonId");
        String template = request.getParameter("template");
        request.setAttribute("jsonId", jsonId);
        request.setAttribute("preview", "true");
        if (template != null && !"".equals(template)) {
            return learningDir + "/learningpaper_" + template;
        } else {
            return learningDir + "/learningpaper";
        }
    }

    //查看功能
    @RequestMapping(value = "/info")
    public String info(HttpServletRequest request, HttpServletResponse response) {
        String ldId = request.getParameter("ldId");
        String toolId = request.getParameter("toolId");
        String template = request.getParameter("template");
        request.setAttribute("ldId", ldId);
        request.setAttribute("info", "true");
        request.setAttribute("toolId", toolId);
        if (template != null && !"".equals(template)) {
            return learningDir + "/learningpaper_" + template;
        } else {
            return learningDir + "/learningpaper";
        }
    }

    //记录现用户最后学习到哪个工具
    @RequestMapping(value = "/saveLadsRecord")
    public String saveLadsRecord(HttpServletRequest request, HttpServletResponse response) {
        String toolId = request.getParameter("toolId");
        String ldId = request.getParameter("ldId");
        String userId = request.getParameter("userId");
        if ((userId != null && !"".equals(userId)) && (toolId != null && !"".equals(toolId))) {
            LadsLastStudyRecord lsr;
            LadsLastStudyRecordCondition lsrc = new LadsLastStudyRecordCondition();
            lsrc.setLdid(ldId);
            lsrc.setUserId(Integer.parseInt(userId));
            List<LadsLastStudyRecord> lsrList = this.ladsLastStudyRecordService.findLadsLastStudyRecordByCondition(lsrc, Order.desc("create_date"));
            if (lsrList.size() <= 0) {
                lsr = new LadsLastStudyRecord();
                lsr.setLdid(ldId);
                lsr.setLastToolId(toolId);
                lsr.setUuid(UUIDUtil.getUUID());
                lsr.setCreateDate(new Date());
                lsr.setModifyDate(new Date());
                lsr.setUserId(Integer.parseInt(userId));
                this.ladsLastStudyRecordService.add(lsr);
            } else {
                lsr = lsrList.get(0);
                lsr.setLastToolId(toolId);
                lsr.setModifyDate(new Date());
                this.ladsLastStudyRecordService.modify(lsr);
            }
        }
        return null;
    }

    @RequestMapping(value = "/savePreviewJson")
    public String savePreviewJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getParameter("json");
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("activitys");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String activityType = obj.getString("activityType");
            if (activityType.equals(ActivityType.TOOL)) {
                obj = createPreviewToolJson(obj);
            } else if (activityType.equals(ActivityType.GROUP)) {
                JSONArray subArray = obj.getJSONArray("subActivitys");
                for (int x = 0; x < subArray.size(); x++) {
                    JSONObject subObj = subArray.getJSONObject(x);
                    subObj = createPreviewToolJson(subObj);
                }
            }
        }
        LadsPreviewJson pj = new LadsPreviewJson();
        pj.setCreateDate(new Date());
        pj.setUuid(UUIDUtil.getUUID());
        pj.setContent(jsonObject.toString());
        pj = (LadsPreviewJson) this.ladsPreviewJsonService.add(pj);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(pj.getUuid());
        return null;
    }

    @RequestMapping(value = "/getPreviewJson")
    public String getPreviewJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        PrintWriter pw = this.setAjaxResponse(request, response);
        String jsonId = request.getParameter("jsonId");
        LadsPreviewJson pj = this.ladsPreviewJsonService.findLadsPreviewJsonByUuid(jsonId);
        pw.print(pj.getContent());
        return null;
    }

    public JSONObject createPreviewToolJson(JSONObject obj) {
        //某些工具在预览模式需要特殊处理
        String toolName = obj.getString("toolName");
        if (toolName.equals(ToolName.QUIZ_TOOL)) {
//            String quizXmlContent = (String) obj.get("quizXmlContent");
//            String quizUploadPath = (String) obj.get("quizUploadPath");
//            if (quizXmlContent != null && !"".equals(quizXmlContent)) {
//                String[] path = this.quizToolServiceImpl.uploadQuizXml(quizXmlContent, quizUploadPath);
//                obj.remove("quizXmlContent");
//                obj.put("quizXmlPath", DocPathUtil.converHttpUrl(path[0]));
//            }
//            obj.put("quizResultUrl", "xxxx");
        } else if (toolName.equals(ToolName.SURVEY_TOOL)) {
            //新建toolId保证预览与保存是不同的数据
//            String newToolId = this.ladsServiceImpl.toolIdCreater();
//            obj.put("id", newToolId);
//            String description = (String) obj.get("surveyDescription");
//            String title = (String) obj.get("surveyTitle");
//            JSONArray surveyArray = (JSONArray) obj.get("surveyQuestions");
//            this.surveyToolServiceImpl.save(newToolId, title, description, surveyArray);
        }
        return obj;
    }

    @RequestMapping(value = "/getLearningJson")
    public String getLearningJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String ldId = request.getParameter("ldId");
        //标记是否包括用户学习信息
        String status = request.getParameter("status");
        Integer embedUserId = this.ladsUserService.getEmbedUserId(request);
        JSONObject json = createLearnJson(ldId, embedUserId,status);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(json);
        return null;
    }

    public JSONObject createLearnJson(String ldId, Integer embedUserId,String status) {
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        JSONObject json = new JSONObject();
        json.put("title", ld.getTitle());
        json.put("userId", ld.getUserId());
        JSONArray actsArray = new JSONArray();
        LadsActivity firstAct = this.ladsActivityService.findLadsActivityByUuid(ld.getFirstActivity());
        //添加第一个活动
        actsArray.add(this.assembleJsonFromAct(firstAct, embedUserId,status));
        //添加其它活动
        actsArray = this.putActsToArray(actsArray, firstAct, embedUserId,status);
        json.put("activitys", actsArray);
        return json;
    }

    public JSONObject assembleJsonFromAct(LadsActivity act, Integer embedUserId,String status) {
        JSONObject jAct = new JSONObject();
        jAct.put("id", act.getToolId());
        String activityName = this.ladsActivityService.getActivityType(act).getActivityName();
        jAct.put("activityType", activityName);
        jAct.put("taught", act.getTaught());
        jAct.put("startTime", act.getStartTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(act.getStartTime()).toString() : "");
        jAct.put("stopTime", act.getStopTime() != null ? new SimpleDateFormat("yyyy-MM-dd").format(act.getStopTime()).toString() : "");
        if (activityName.equals(ActivityType.TOOL)) {
            jAct.put("toolName", act.getToolLibrary());
            if (act.getToolLibrary().equals(ToolName.EDITOR_TOOL)) {
                jAct.put("editorTitle", act.getTitle());
                jAct.put("editorScore", act.getScore());
                //用户学习信息
                if (status != null) {
                    jAct.put("finished", this.editorToolService.getFinishedStatus(act.getToolId(), embedUserId));
                    jAct.put("userScore", this.editorToolService.getUserScore(act.getToolId(), embedUserId));
                }
            } else if (act.getToolLibrary().equals(ToolName.MEDIA_TOOL)) {
                jAct.put("mediaTitle", act.getTitle());
                jAct.put("mediaScore", act.getScore());
                //用户学习信息
                if (status != null) {
                    jAct.put("finished", this.mediaToolService.getFinishedStatus(act.getToolId(), embedUserId));
                    jAct.put("userScore", this.mediaToolService.getUserScore(act.getToolId(), embedUserId));
                }
            } else if (act.getToolLibrary().equals(ToolName.FACE_TEACHING_TOOL)) {
//                LadsFaceteachingTool teaching = this.faceTeachingToolServiceImpl.getFaceteachingByToolId(act.getToolId());
//                jAct.put("faceTeachingTitle", teaching.getTitle());
//                jAct.put("faceTeachingScore", act.getScore());
//                jAct.put("faceTeachingStartTime", teaching.getStartTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(teaching.getStartTime()).toString() : "");
//                jAct.put("faceTeachingStopTime", teaching.getStopTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(teaching.getStopTime()).toString() : "");
//                jAct.put("faceTeachingAddress", teaching.getAddress());
//                jAct.put("faceTeachingDescription", teaching.getDescription());
//                jAct.put("finished", this.faceTeachingToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.faceTeachingToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            } else if (act.getToolLibrary().equals(ToolName.DISCUSS_TOOL)) {
                LadsDiscussTool discuss = this.discussToolService.getLadsDiscussToolByToolId(act.getToolId());
                jAct.put("discussTitle", discuss.getTitle());
                jAct.put("discussContent", discuss.getContent());
                jAct.put("discussScore", act.getScore());
                jAct.put("discussAllowUpload", discuss.getAllowUpload());
                jAct.put("discussStartTime", discuss.getStartTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(discuss.getStartTime()).toString() : "");
                jAct.put("discussStopTime", discuss.getStopTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(discuss.getStopTime()).toString() : "");
                //用户学习信息
                if (status != null) {
                    jAct.put("finished", this.discussToolService.getFinishedStatus(act.getToolId(), embedUserId));
                    jAct.put("userScore", this.discussToolService.getUserScore(act.getToolId(), embedUserId));
                }
            } else if (act.getToolLibrary().equals(ToolName.QUIZ_TOOL)) {
//                LadsQuizTool qt = this.quizToolServiceImpl.getQuizByToolId(act.getToolId());
//                jAct.put("quizTitle", qt.getTitle());
//                jAct.put("quizScore", act.getScore());
//                jAct.put("quizResultUrl", "/common/lads/ladsQuizAction_saveQuizResult.action");
//                jAct.put("quizXmlPath", DocPathUtil.converHttpUrl(qt.getHttpUrl()));
//                jAct.put("finished", this.quizToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.quizToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            } else if (act.getToolLibrary().equals(ToolName.SURVEY_TOOL)) {
//                LadsSurveyTool st = this.surveyToolServiceImpl.getSurveyByToolId(act.getToolId());
//                jAct.put("surveyTitle", st.getTitle());
//                jAct.put("surveyScore", act.getScore());
//                jAct.put("surveyDescription", st.getDescription());
//                jAct.put("finished", this.surveyToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.surveyToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            } else if (act.getToolLibrary().equals(ToolName.POWER_POINT_TOOL)) {
//                LadsPowerpointTool st = this.powerPointToolServiceImpl.getPowerpointByToolId(act.getToolId());
//                jAct.put("powerPointTitle", st.getTitle());
//                jAct.put("powerPointScore", act.getScore());
//                jAct.put("powerPointFileId", st.getResFile() != null ? st.getResFile().getId() : "");
//                jAct.put("finished", this.powerPointToolServiceImpl.getFinishedStatus(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
//                jAct.put("userScore", this.powerPointToolServiceImpl.getUserScore(act.getToolId(), this.ladsServiceImpl.getEmbedUserId(sysType)));
            }
        } else if (activityName.equals(ActivityType.GROUP)) {
            jAct.put("groupTitle", act.getTitle());
            LadsActivityCondition ac = new LadsActivityCondition();
            ac.setParentActivity(act.getUuid());
            List<LadsActivity> subActs = this.ladsActivityService.findLadsActivityByCondition(ac);
            //List<LadsActivity> subActs = this.hibernateBaseServiceImpl.findList("from LadsActivity la where la.ladsActivity.id = '" + act.getId() + "'");
            JSONArray subActsArray = new JSONArray();
            if (subActs.size() > 0) {
                //添加组内第一个活动
                LadsActivity firstSubAct = subActs.get(0);
                //递归
                subActsArray.add(this.assembleJsonFromAct(firstSubAct, embedUserId,status));
                //添加其它活动
                subActsArray = this.putActsToArray(subActsArray, firstSubAct, embedUserId,status);
            }
            jAct.put("subActivitys", subActsArray);
        }
        return jAct;
    }

    public JSONArray putActsToArray(JSONArray actsArray, LadsActivity fromAct, Integer embedUserId,String status) {
        LadsActivityTransitionCondition atc = new LadsActivityTransitionCondition();
        atc.setFromActivity(fromAct.getUuid());
        List<LadsActivityTransition> atList = this.ladsActivityTransitionService.findLadsActivityTransitionByCondition(atc);
        LadsActivity toAct;
        if (atList.size() > 0) {
            toAct = this.ladsActivityTransitionService.getToActivity(atList.get(0));
            //添加活动
            actsArray.add(this.assembleJsonFromAct(toAct, embedUserId,status));
            //递归
            actsArray = putActsToArray(actsArray, toAct, embedUserId,status);
        }
        return actsArray;
    }

    @RequestMapping(value = "/export")
    public String export(HttpServletRequest request, HttpServletResponse response) {
        String ldId = request.getParameter("ldId");
        return null;
    }

    @RequestMapping(value = "/editorToolPaper")
    public String editorToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String editorScore = request.getParameter("editorScore");
        String editorContent = request.getParameter("editorContent");
        String editorUploadList = request.getParameter("editorUploadList");
        String template = request.getParameter("template");
        String content;
        String uploadList;
        LadsEditorTool editor = this.editorToolService.getEditorByToolId(id);
        if (editor != null && (editorContent == null || "".equals(editorContent))) {
            content = editor.getContent();
        } else {
            content = editorContent;
        }
        if (editor != null && (editorUploadList == null || "".equals(editorUploadList))) {
            uploadList = editor.getUploadList();
        } else {
            uploadList = editorUploadList;
        }
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("editorScore", editorScore);
        request.setAttribute("editorContent", content);
        request.setAttribute("editorUploadList", uploadList);
        if (template != null && !"".equals(template)) {
            return learningDir + "/editortoolpaper_" + template;
        } else {
            return learningDir + "/editortoolpaper";
        }
    }

    @RequestMapping(value = "/mediaToolPaper")
    public String mediaToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String mediaScore = request.getParameter("mediaScore");
        String mediaEntityId = request.getParameter("mediaEntityId");
        String mediaUploadList = request.getParameter("mediaUploadList");
        String template = request.getParameter("template");
        String entityId;
        String uploadList;
        LadsMediaTool media = this.mediaToolService.getMediaByToolId(id);
        if (media != null && (mediaEntityId == null || "".equals(mediaEntityId))) {
            entityId = media.getEntityId();
        } else {
            entityId = mediaEntityId;
        }
        if (media != null && (mediaUploadList == null || "".equals(mediaUploadList))) {
            uploadList = media.getUploadList();
        } else {
            uploadList = mediaUploadList;
        }
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("mediaScore", mediaScore);
        request.setAttribute("mediaEntityId", entityId);
        request.setAttribute("mediaUploadList", uploadList);
        if (template != null && !"".equals(template)) {
            return learningDir + "/mediatoolpaper_" + template;
        } else {
            return learningDir + "/mediatoolpaper";
        }
    }

    @RequestMapping(value = "/faceTeachingToolPaper")
    public String faceTeachingToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String faceTeachingAddress = request.getParameter("faceTeachingAddress");
        String faceTeachingDescription = request.getParameter("faceTeachingDescription");
        String faceTeachingStartTime = request.getParameter("faceTeachingStartTime");
        String faceTeachingStopTime = request.getParameter("faceTeachingStopTime");
        request.setAttribute("id", id);
        request.setAttribute("faceTeachingAddress", faceTeachingAddress);
        request.setAttribute("faceTeachingDescription", faceTeachingDescription);
        request.setAttribute("faceTeachingStartTime", faceTeachingStartTime);
        request.setAttribute("faceTeachingStopTime", faceTeachingStopTime);
        request.setAttribute("title", title);
        return "learn_faceTeachingToolPaper";
    }

    @RequestMapping(value = "/quizToolPaper")
    public String quizToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String xmlPath = request.getParameter("quizXmlPath");
        String resultUrl = request.getParameter("quizResultUrl");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("quizXmlPath", xmlPath);
        request.setAttribute("quizResultUrl", resultUrl);
        return "learn_quizToolPaper";
    }

    @RequestMapping(value = "/discussToolPaper")
    public String discussToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String discussContent = request.getParameter("discussContent");
        String discussAllowUpload = request.getParameter("discussAllowUpload");
        String discussStartTime = request.getParameter("discussStartTime");
        String discussStopTime = request.getParameter("discussStopTime");
        String template = request.getParameter("template");
//        List<LadsDiscussReplyVo> replyList = this.discussToolServiceImpl.getReplyListByToolId(sysType, id, null);
//        request.setAttribute("replyList", replyList);
        request.setAttribute("id", id);
        if (discussContent != null) {
            discussContent = discussContent.replaceAll("\r\n", "<br/>");
            discussContent = discussContent.replaceAll("\n", "<br/>");
        }
        request.setAttribute("title", title);
        request.setAttribute("discussContent", discussContent);
        request.setAttribute("discussAllowUpload", discussAllowUpload);
        request.setAttribute("discussStartTime", discussStartTime);
        request.setAttribute("discussStopTime", discussStopTime);
        request.setAttribute("discussAllowCons", AllowUpload.ALLOW);
        if (template != null && !"".equals(template)) {
            return learningDir + discussToolDir + "/discusstoolpaper_" + template;
        } else {
            return learningDir + discussToolDir + "/discusstoolpaper";
        }
    }

    @RequestMapping(value = "/surveyToolPaper")
    public String surveyToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("surveyDescription");
        String sysType = request.getParameter("sysType");
        request.setAttribute("sysType", sysType);
        request.setAttribute("id", id);
        if (description != null) {
            description = description.replaceAll("\r\n", "<br/>");
            description = description.replaceAll("\n", "<br/>");
        }
        request.setAttribute("surveyDescription", description);
        request.setAttribute("title", title);
        return "learn_surveyToolPaper";
    }

    @RequestMapping(value = "/powerPointToolPaper")
    public String powerPointToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String powerPointFileId = request.getParameter("powerPointFileId");
        String powerPointScore = request.getParameter("powerPointScore");
        String sysType = request.getParameter("sysType");
        request.setAttribute("sysType", sysType);
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("powerPointScore", powerPointScore);
        request.setAttribute("powerPointFileId", powerPointFileId);
        return "learn_powerPointToolPaper";
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