/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.web.controller;

import platform.education.lads.vo.LadsLearningdesignVo;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import platform.education.lads.contants.ToolValidFlag;
import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.model.LadsActivityType;
import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.model.LadsToolLibrary;
import platform.education.lads.service.DiscussToolService;
import platform.education.lads.service.EditorToolService;
import platform.education.lads.service.LadsActivityService;
import platform.education.lads.service.LadsActivityTransitionService;
import platform.education.lads.service.LadsActivityTypeService;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsToolLibraryService;
import platform.education.lads.service.MediaToolService;
import platform.education.lads.vo.LadsActivityCondition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import platform.education.lads.vo.LadsActivityTypeCondition;
import platform.education.lads.vo.LadsToolLibraryCondition;
import platform.education.resource.utils.UUIDUtil;

/**
 *
 * @author Administrator
 */
@Controller("authoringController")
@Scope("prototype")
@RequestMapping(value = "/lads/authoring")
public class AuthoringController {

    @Resource
    private LadsService ladsService;
    @Resource
    public EditorToolService editorToolService;
    @Resource
    public MediaToolService mediaToolService;
    @Resource
    public DiscussToolService discussToolService;
//    @Resource
//    public QuizToolService quizToolServiceImpl;
//    @Resource
//    public FaceTeachingToolService faceTeachingToolServiceImpl;
//    @Resource
//    public SurveyToolService surveyToolServiceImpl;
//    @Resource
//    public PowerPointToolService powerPointToolServiceImpl;
    //新加入的依赖注入
    @Resource
    public LadsToolLibraryService ladsToolLibraryService;
    @Resource
    public LadsLearningdesignService ladsLearningdesignService;
    @Resource
    public LadsActivityService ladsActivityService;
    @Resource
    public LadsActivityTypeService ladsActivityTypeService;
    @Resource
    public LadsActivityTransitionService ladsActivityTransitionService;
    public static final String baseDir = "lads";
    public static final String authorDir = baseDir + "/authoring";

    //创作功能
    @RequestMapping(value = "/author")
    public String author(HttpServletRequest request, HttpServletResponse response) {
        LadsToolLibraryCondition tlc = new LadsToolLibraryCondition();
        tlc.setValidFlag(ToolValidFlag.VALID);
        List<LadsToolLibrary> toolLibraryList = this.ladsToolLibraryService.findLadsToolLibraryByCondition(tlc);

        String ldId = request.getParameter("ldId");
        String lessonId = request.getParameter("lessonId");
        String sysType = request.getParameter("sysType");
        //外部课程保存方法的url
        String emUrl = request.getParameter("emUrl");
        //模版id
        String templateId = request.getParameter("templateId");
        if (ldId != null && !"".equals(ldId)) {
            //直接调用课件id模式
            LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
            request.setAttribute("ldId", ldId);
        }

        request.setAttribute("toolLibraryList", toolLibraryList);
        request.setAttribute("userId", lessonId);
        request.setAttribute("sysType", sysType);
        request.setAttribute("emUrl", emUrl);
        request.setAttribute("templateId", templateId);
        return authorDir + "/authoringpaper";
    }

    //编辑功能
    @RequestMapping(value = "/getAuthoringJson")
    public String getAuthoringJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        PrintWriter pw = this.setAjaxResponse(request, response);
        String ldId = request.getParameter("ldId");
        JSONObject json = this.createAuthorJson(ldId);
        pw.print(json);
        return null;
    }

    //使用模版功能
    @RequestMapping(value = "/getTemplateJson")
    public String getTemplateJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        PrintWriter pw = this.setAjaxResponse(request, response);
        String templateId = request.getParameter("templateId");
        JSONObject jsonObject = this.createAuthorJson(templateId);
        jsonObject.put("ldId", "");
        JSONArray jsonArray = jsonObject.getJSONArray("activitys");
        this.ladsService.copyJson(jsonArray);
        pw.print(jsonObject);
        return null;
    }

    @RequestMapping(value = "/save")
    public String save(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String json = request.getParameter("json");
        LadsLearningdesign ld = saveImpl(json);
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(ld.getUuid());
        return null;
    }

    public JSONObject createAuthorJson(String ldId) {
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        JSONObject json = new JSONObject();
        json.put("title", ld.getTitle());
        json.put("userId", ld.getUserId());
        JSONArray actsArray = new JSONArray();
        LadsActivity firstAct = this.ladsActivityService.findLadsActivityByUuid(ld.getFirstActivity());
        //添加第一个活动
        actsArray.add(this.assembleJsonFromAct(firstAct));
        //添加其它活动
        actsArray = this.putActsToArray(actsArray, firstAct);
        json.put("activitys", actsArray);
        return json;
    }

    public JSONObject assembleJsonFromAct(LadsActivity act) {
        JSONObject jAct = new JSONObject();
        String activityName = this.ladsActivityService.getActivityType(act).getActivityName();
        jAct.put("id", act.getToolId());
        jAct.put("activityType", activityName);
        if (activityName.equals(ActivityType.TOOL)) {
            jAct.put("toolName", act.getToolLibrary());
            jAct.put("toolId", act.getToolId());
            if (act.getToolLibrary().equals(ToolName.EDITOR_TOOL)) {
                jAct.put("editorTitle", act.getTitle());
                jAct.put("editorScore", act.getScore());
            } else if (act.getToolLibrary().equals(ToolName.MEDIA_TOOL)) {
                jAct.put("mediaTitle", act.getTitle());
                jAct.put("mediaScore", act.getScore());
            } else if (act.getToolLibrary().equals(ToolName.FACE_TEACHING_TOOL)) {
//                LadsFaceteachingTool teaching = this.faceTeachingToolServiceImpl.getFaceteachingByToolId(act.getToolId());
//                jAct.put("faceTeachingTitle", teaching.getTitle());
//                jAct.put("faceTeachingScore", act.getScore());
//                jAct.put("faceTeachingStartTime", teaching.getStartTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(teaching.getStartTime()).toString() : "");
//                jAct.put("faceTeachingStopTime", teaching.getStopTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(teaching.getStopTime()).toString() : "");
//                jAct.put("faceTeachingAddress", teaching.getAddress());
//                jAct.put("faceTeachingDescription", teaching.getDescription());
            } else if (act.getToolLibrary().equals(ToolName.DISCUSS_TOOL)) {
                LadsDiscussTool discuss = this.discussToolService.getLadsDiscussToolByToolId(act.getToolId());
                jAct.put("discussTitle", discuss.getTitle());
                jAct.put("discussScore", act.getScore());
                jAct.put("discussContent", discuss.getContent());
                jAct.put("discussAllowUpload", discuss.getAllowUpload());
                jAct.put("discussStartTime", discuss.getStartTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(discuss.getStartTime()).toString() : "");
                jAct.put("discussStopTime", discuss.getStopTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(discuss.getStopTime()).toString() : "");
            } else if (act.getToolLibrary().equals(ToolName.QUIZ_TOOL)) {
//                LadsQuizTool qt = this.quizToolServiceImpl.getQuizByToolId(act.getToolId());
//                jAct.put("quizTitle", qt.getTitle());
//                jAct.put("quizScore", act.getScore());
//                jAct.put("quizXmlPath", DocPathUtil.converHttpUrl(qt.getHttpUrl()));
            } else if (act.getToolLibrary().equals(ToolName.SURVEY_TOOL)) {
//                LadsSurveyTool st = this.surveyToolServiceImpl.getSurveyByToolId(act.getToolId());
//                jAct.put("surveyTitle", st.getTitle());
//                jAct.put("surveyDescription", st.getDescription());
//                jAct.put("surveyScore", act.getScore());
            } else if (act.getToolLibrary().equals(ToolName.POWER_POINT_TOOL)) {
//                LadsPowerpointTool st = this.powerPointToolServiceImpl.getPowerpointByToolId(act.getToolId());
//                jAct.put("powerPointTitle", st.getTitle());
//                jAct.put("powerPointScore", act.getScore());
//                jAct.put("powerPointFileId", st.getResFile() != null ? st.getResFile().getId() : "");
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
                subActsArray.add(this.assembleJsonFromAct(firstSubAct));
                //添加其它活动
                subActsArray = this.putActsToArray(subActsArray, firstSubAct);
            }
            jAct.put("subActivitys", subActsArray);
        }
        return jAct;
    }

    public JSONArray putActsToArray(JSONArray actsArray, LadsActivity fromAct) {
        LadsActivityTransitionCondition atc = new LadsActivityTransitionCondition();
        atc.setFromActivity(fromAct.getUuid());
        List<LadsActivityTransition> atList = this.ladsActivityTransitionService.findLadsActivityTransitionByCondition(atc);
        LadsActivity toAct;
        if (atList.size() > 0) {
            toAct = this.ladsActivityTransitionService.getToActivity(atList.get(0));
            //添加活动
            actsArray.add(this.assembleJsonFromAct(toAct));
            //递归
            actsArray = putActsToArray(actsArray, toAct);
        }
        return actsArray;
    }

    public LadsActivityType getStandardActivityType(String typeName) {
        LadsActivityTypeCondition atc = new LadsActivityTypeCondition();
        atc.setActivityName(typeName);
        List<LadsActivityType> atl = this.ladsActivityTypeService.findLadsActivityTypeByCondition(atc);
        return atl.get(0);
    }

    public LadsActivity saveTool(JSONObject obj, LadsActivity act, String toolId) {
        //inLoad 标记是确定该工具在编辑过程有没有被加载过，如果为false，即未被加载过，不需要保存
        String inLoad = (String) obj.get("inLoad");
        String toolName = (String) obj.get("toolName");
        act.setActivityType(getStandardActivityType(ActivityType.TOOL).getUuid());
        act.setToolId(toolId);
        act.setToolLibrary(toolName);
        if (toolName.equals(ToolName.EDITOR_TOOL)) {
            String editorTitle = (String) obj.get("editorTitle");
            String editorScore = (String) obj.get("editorScore");
            act.setTitle(editorTitle);
            act.setScore(editorScore);
            if (inLoad == null || !"false".equals(inLoad)) {
                String editorContent = (String) obj.get("editorContent");
                JSONArray editorUploadList = (JSONArray) obj.get("editorUploadList");
                this.editorToolService.save(toolId, editorTitle, editorContent, editorUploadList != null ? editorUploadList.toString() : "[]");
            }
        } else if (toolName.equals(ToolName.MEDIA_TOOL)) {
            String mediaTitle = (String) obj.get("mediaTitle");
            String mediaScore = (String) obj.get("mediaScore");
            act.setTitle(mediaTitle);
            act.setScore(mediaScore);
            if (inLoad == null || !"false".equals(inLoad)) {
                String mediaEntityId = (String) obj.get("mediaEntityId");
                JSONArray mediaUploadList = (JSONArray) obj.get("mediaUploadList");
                this.mediaToolService.save(toolId, mediaTitle, mediaEntityId, mediaUploadList != null ? mediaUploadList.toString() : "[]");
            }
        } else if (toolName.equals(ToolName.FACE_TEACHING_TOOL)) {
            String faceTeachingTitle = (String) obj.get("faceTeachingTitle");
            act.setTitle(faceTeachingTitle);
            String faceTeachingScore = (String) obj.get("faceTeachingScore");
            act.setScore(faceTeachingScore);
            if (inLoad == null || !"false".equals(inLoad)) {
                String faceTeachingStartTime = (String) obj.get("faceTeachingStartTime");
                String faceTeachingStopTime = (String) obj.get("faceTeachingStopTime");
                String faceTeachingDescription = (String) obj.get("faceTeachingDescription");
                String faceTeachingAddress = (String) obj.get("faceTeachingAddress");
//                this.faceTeachingToolServiceImpl.save(toolId, faceTeachingTitle, faceTeachingStartTime, faceTeachingStopTime, faceTeachingDescription, faceTeachingAddress);
            }
        } else if (toolName.equals(ToolName.QUIZ_TOOL)) {
            String quizTitle = (String) obj.get("quizTitle");
            act.setTitle(quizTitle);
            String quizScore = (String) obj.get("quizScore");
            act.setScore(quizScore);
            if (inLoad == null || !"false".equals(inLoad)) {
                String quizXmlContent = (String) obj.get("quizXmlContent");
                String quizUploadPath = (String) obj.get("quizUploadPath");
//                this.quizToolServiceImpl.save(toolId, quizTitle, quizXmlContent, quizUploadPath);
            }
        } else if (toolName.equals(ToolName.DISCUSS_TOOL)) {
            String discussTitle = (String) obj.get("discussTitle");
            act.setTitle(discussTitle);
            String discussScore = (String) obj.get("discussScore");
            act.setScore(discussScore);
            if (inLoad == null || !"false".equals(inLoad)) {
                String discussStartTime = (String) obj.get("discussStartTime");
                String discussStopTime = (String) obj.get("discussStopTime");
                String discussContent = (String) obj.get("discussContent");
                String discussAllowUpload = (String) obj.get("discussAllowUpload");
                this.discussToolService.save(toolId, discussTitle, discussContent, discussStartTime, discussStopTime, discussAllowUpload);
            }
        } else if (toolName.equals(ToolName.SURVEY_TOOL)) {
            String surveyTitle = (String) obj.get("surveyTitle");
            act.setTitle(surveyTitle);
            String surveyScore = (String) obj.get("surveyScore");
            act.setScore(surveyScore);
            String surveyDescription = (String) obj.get("surveyDescription");
            if (inLoad == null || !"false".equals(inLoad)) {
                JSONArray surveyArray = (JSONArray) obj.get("surveyQuestions");
//                this.surveyToolServiceImpl.save(toolId, surveyTitle, surveyDescription, surveyArray);
            }
        } else if (toolName.equals(ToolName.POWER_POINT_TOOL)) {
            String powerPointTitle = (String) obj.get("powerPointTitle");
            act.setTitle(powerPointTitle);
            String powerPointScore = (String) obj.get("powerPointScore");
            act.setScore(powerPointScore);
            if (inLoad == null || !"false".equals(inLoad)) {
                String powerPointFileId = (String) obj.get("powerPointFileId");
//                this.powerPointToolServiceImpl.save(toolId, powerPointTitle, powerPointFileId);
            }
        }
        return act;
    }

    @RequestMapping(value = "/groupPaper")
    public String groupPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        return authorDir + "/grouppaper";
    }

    @RequestMapping(value = "/editorToolPaper")
    public String editorToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String editorScore = request.getParameter("editorScore");
        String editorContent = request.getParameter("editorContent");
        String editorUploadList = request.getParameter("editorUploadList");
        String content = null;
        String uploadList = null;
        //先使用参数传过来的值
        if (editorContent != null && !"".equals(editorContent)) {
            content = editorContent;
        }
        if (editorUploadList != null && !"".equals(editorUploadList)) {
            uploadList = editorUploadList;
        }
        //没有传参才查询数据库获取数据
        if (editorContent == null || editorUploadList == null) {
            LadsEditorTool editor = this.editorToolService.getEditorByToolId(id);
            if (editor != null) {
                content = editor.getContent();
                uploadList = editor.getUploadList();
            }
        }
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("editorScore", editorScore);
        request.setAttribute("editorContent", content);
        request.setAttribute("editorUploadList", uploadList);
        return authorDir + "/editortoolpaper";
    }

    @RequestMapping(value = "/mediaToolPaper")
    public String mediaToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String mediaScore = request.getParameter("mediaScore");
        String mediaEntityId = request.getParameter("mediaEntityId");
        String mediaUploadList = request.getParameter("mediaUploadList");
        String entityId = null;
        String uploadList = null;
        //先使用参数传过来的值
        if (mediaEntityId != null && !"".equals(mediaEntityId)) {
            entityId = mediaEntityId;
        }
        if (mediaUploadList != null && !"".equals(mediaUploadList)) {
            uploadList = mediaUploadList;
        }
        //没有传参才查询数据库获取数据
        if (mediaEntityId == null || mediaUploadList == null) {
            LadsMediaTool media = this.mediaToolService.getMediaByToolId(id);
            if (media != null) {
                entityId = media.getEntityId();
                uploadList = media.getUploadList();
            }
        }
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("mediaScore", mediaScore);
        request.setAttribute("mediaEntityId", entityId);
        request.setAttribute("mediaUploadList", uploadList);
        return authorDir + "/mediatoolpaper";
    }

    @RequestMapping(value = "/faceTeachingToolPaper")
    public String faceTeachingToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String faceTeachingScore = request.getParameter("faceTeachingScore");
        String faceTeachingAddress = request.getParameter("faceTeachingAddress");
        String faceTeachingDescription = request.getParameter("faceTeachingDescription");
        String faceTeachingStartTime = request.getParameter("faceTeachingStartTime");
        String faceTeachingStopTime = request.getParameter("faceTeachingStopTime");
        request.setAttribute("id", id);
        request.setAttribute("faceTeachingScore", faceTeachingScore);
        request.setAttribute("faceTeachingAddress", faceTeachingAddress);
        request.setAttribute("faceTeachingDescription", faceTeachingDescription);
        request.setAttribute("faceTeachingStartTime", faceTeachingStartTime);
        request.setAttribute("faceTeachingStopTime", faceTeachingStopTime);
        request.setAttribute("title", title);
        return "author_faceTeachingToolPaper";
    }

    @RequestMapping(value = "/quizToolPaper")
    public String quizToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String quizScore = request.getParameter("quizScore");
        request.setAttribute("quizScore", quizScore);
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        return "author_quizToolPaper";
    }

    @RequestMapping(value = "/discussToolPaper")
    public String discussToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String discussContent = request.getParameter("discussContent");
        String discussScore = request.getParameter("discussScore");
        String discussStartTime = request.getParameter("discussStartTime");
        String discussStopTime = request.getParameter("discussStopTime");
        String discussAllowUpload = request.getParameter("discussAllowUpload");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("discussContent", discussContent);
        request.setAttribute("discussScore", discussScore);
        request.setAttribute("discussStartTime", discussStartTime);
        request.setAttribute("discussStopTime", discussStopTime);
        request.setAttribute("discussAllowUpload", discussAllowUpload);
        return authorDir + "/discusstoolpaper";
    }

    @RequestMapping(value = "/surveyToolPaper")
    public String surveyToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String surveyScore = request.getParameter("surveyScore");
        String surveyDescription = request.getParameter("surveyDescription");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("surveyScore", surveyScore);
        request.setAttribute("surveyDescription", surveyDescription);
        return "author_surveyToolPaper";
    }

    @RequestMapping(value = "/powerPointToolPaper")
    public String powerPointToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String powerPointScore = request.getParameter("powerPointScore");
        String powerPointFileId = request.getParameter("powerPointFileId");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("powerPointScore", powerPointScore);
        request.setAttribute("powerPointFileId", powerPointFileId);
        return "author_powerPointToolPaper";
    }

    public LadsLearningdesign saveImpl(String json) {
        JSONObject jsonObject = JSONObject.fromObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("activitys");
        String title = jsonObject.getString("title");
        String userId = jsonObject.getString("userId");
        String ldId = jsonObject.getString("ldId");
        LadsLearningdesign ld;
        boolean updateFlag = false;
        if (ldId != null && !"".equals(ldId)) {
            ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
            ld.setModifyDate(new Date());
            updateFlag = true;
        } else {
            ld = new LadsLearningdesign();
            ld.setCreateDate(new Date());
            ld.setModifyDate(new Date());
            ld.setUuid(UUIDUtil.getUUID());
        }
        ld.setTitle(title);
        ld.setUserId(userId);
        if (updateFlag) {
            ld = this.ladsLearningdesignService.modify(ld);
            //先删除原有活动和顺序
            LadsActivityTransitionCondition atc = new LadsActivityTransitionCondition();
            atc.setLearningdesign(ld.getUuid());
            if (this.ladsActivityTransitionService.findLadsActivityTransitionByCondition(atc).size() > 0) {
                this.ladsActivityTransitionService.remove(atc);
            }
            //this.hibernateBaseServiceImpl.delete("delete from LadsActivityTransition la where la.ladsLearningdesign.id = '" + ld.getId() + "'");

            this.ladsActivityService.removeChildrenActivity(ld.getUuid());
            //this.hibernateBaseServiceImpl.delete("delete from LadsActivity la where la.ladsActivity is not null and la.ladsLearningdesign.id = '" + ld.getId() + "'");

            LadsActivityCondition ac = new LadsActivityCondition();
            ac.setLearningdesign(ld.getUuid());
            this.ladsActivityService.remove(ac);
            //this.hibernateBaseServiceImpl.delete("delete from LadsActivity la where la.ladsLearningdesign.id = '" + ld.getId() + "'");
        } else {
            ld = this.ladsLearningdesignService.add(ld);
        }
        LadsActivity firstAct = null;
        LadsActivity[] acts = new LadsActivity[jsonArray.size()];
        List<LadsActivity[]> subActs = new ArrayList<LadsActivity[]>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String activityType = obj.getString("activityType");
            String toolId = obj.getString("id");
            LadsActivity act = new LadsActivity();
            act.setCreateDate(new Date());
            act.setUuid(UUIDUtil.getUUID());
            act.setToolId(toolId);
            act.setLearningdesign(ld.getUuid());
            if (activityType.equals(ActivityType.TOOL)) {
                //保存工具
                act = saveTool(obj, act, toolId);
                act = (LadsActivity) this.ladsActivityService.add(act);
            } else if (activityType.equals(ActivityType.GROUP)) {
                String groupTitle = obj.getString("groupTitle");
                act.setActivityType(getStandardActivityType(ActivityType.GROUP).getUuid());
                act.setTitle(groupTitle);
                act = (LadsActivity) this.ladsActivityService.add(act);
                JSONArray subArray = obj.getJSONArray("subActivitys");
                LadsActivity[] subArrAct = new LadsActivity[subArray.size()];
                for (int x = 0; x < subArray.size(); x++) {
                    JSONObject subObj = subArray.getJSONObject(x);
                    String subToolId = subObj.getString("id");
                    LadsActivity subAct = new LadsActivity();
                    subAct.setCreateDate(new Date());
                    subAct.setUuid(UUIDUtil.getUUID());
                    subAct.setToolId(subToolId);
                    subAct.setLearningdesign(ld.getUuid());
                    //保存工具
                    subAct = saveTool(subObj, subAct, subToolId);
                    //设置父活动
                    subAct.setParentActivity(act.getUuid());
                    subAct = (LadsActivity) this.ladsActivityService.add(subAct);
                    subArrAct[x] = subAct;
                }
                subActs.add(subArrAct);
            }
            if (i == 0) {
                //记录第一个活动
                firstAct = act;
                ld.setFirstActivity(firstAct.getUuid());
                ld = (LadsLearningdesign) this.ladsLearningdesignService.modify(ld);
            }
            acts[i] = act;
        }
        for (int v = 0; v < acts.length; v++) {
            if (v + 1 != acts.length) {
                LadsActivityTransition at = new LadsActivityTransition();
                at.setCreateDate(new Date());
                at.setModifyDate(new Date());
                at.setUuid(UUIDUtil.getUUID());
                at.setFromActivity(acts[v].getUuid());
                at.setToActivity(acts[v + 1].getUuid());
                at.setLearningdesign(ld.getUuid());
                this.ladsActivityTransitionService.add(at);
            }
        }
        for (LadsActivity[] groupAct : subActs) {
            for (int h = 0; h < groupAct.length; h++) {
                if (h + 1 != groupAct.length) {
                    LadsActivityTransition at = new LadsActivityTransition();
                    at.setCreateDate(new Date());
                    at.setModifyDate(new Date());
                    at.setUuid(UUIDUtil.getUUID());
                    at.setFromActivity(groupAct[h].getUuid());
                    at.setToActivity(groupAct[h + 1].getUuid());
                    at.setLearningdesign(ld.getUuid());
                    this.ladsActivityTransitionService.add(at);
                }
            }
        }
        return ld;
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
