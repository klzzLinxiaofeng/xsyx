/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.web.controller;

//import com.gzxtjy.lads.service.DiscussToolService;
//import com.gzxtjy.lads.service.EditorToolService;
//import com.gzxtjy.lads.service.FaceTeachingToolService;
//import com.gzxtjy.lads.service.PowerPointToolService;
//import com.gzxtjy.lads.service.QuizToolService;
//import com.gzxtjy.lads.service.SurveyToolService;
//import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusVo;
//import platform.education.lads.vo.editorToolVo.LadsEditorUserStatusVo;
//import platform.education.lads.vo.faceteachingToolVo.LadsFaceTeachingUserStatusVo;
//import platform.education.lads.vo.powerpointToolVo.LadsPowerPointUserStatusVo;
import platform.education.lads.vo.LadsUserVo;
import platform.education.lads.vo.LearnerStatisticsVo;
import platform.education.lads.vo.ScoreStatisticsVo;
import platform.education.lads.vo.UserScoreVo;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.DocumentException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import platform.education.lads.contants.ActivityType;
import platform.education.lads.contants.ToolName;
import platform.education.lads.contants.discussToolCons.AllowUpload;
import platform.education.lads.contants.mediaToolCons.StudyStatus;
import platform.education.lads.model.LadsActivity;
import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.model.LadsActivityType;
import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.model.LadsEditorTool;
import platform.education.lads.model.LadsLearningdesign;
import platform.education.lads.model.LadsMediaTool;
import platform.education.lads.service.DiscussToolService;
import platform.education.lads.service.EditorToolService;
import platform.education.lads.service.LadsActivityService;
import platform.education.lads.service.LadsActivityTransitionService;
import platform.education.lads.service.LadsActivityTypeService;
import platform.education.lads.service.LadsLearningdesignService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsToolLibraryService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.service.MediaToolService;
import platform.education.lads.util.LadsCommonUtils;
import platform.education.lads.vo.LadsActivityCondition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import platform.education.lads.vo.LadsActivityTypeCondition;
import platform.education.lads.vo.LadsActivityVo;
import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusVo;
import platform.education.lads.vo.editortoolVo.LadsEditorUserStatusVo;
import platform.education.lads.vo.mediaToolVo.LadsMediaUserStatusVo;

/**
 *
 * @author Administrator
 */
@Controller("monitoringController")
@Scope("prototype")
@RequestMapping(value = "/lads/monitoring")
public class MonitoringController {

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
//    public SurveyToolService surveyToolServiceImpl;
//    @Resource
//    public PowerPointToolService powerPointToolServiceImpl;
    @Resource
    private LadsService ladsService;
    @Resource
    private LadsUserService ladsUserService;

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
    public static final String monitoringDir = baseDir + "/monitoring";
    public static final String discussToolDir = "/discuss";
    public static final String mediaToolDir = "/media";

    @RequestMapping(value = "/monitor")
    public String monitor(HttpServletRequest request, HttpServletResponse response) {
        String ldId = request.getParameter("ldId");
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        request.setAttribute("ld", ld);
        return monitoringDir + "/monitoringpaper";
    }

    @RequestMapping(value = "/getMonitorJson")
    public String getMonitorJson(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
        String ldId = request.getParameter("ldId");
        String studyUserId = request.getParameter("studyUserId");
        Integer userId = null;
        if (studyUserId != null && !"".equals(studyUserId)) {
            userId = Integer.parseInt(studyUserId);
        }
        JSONObject json = createMonitorJson(ldId,userId );
        PrintWriter pw = this.setAjaxResponse(request, response);
        pw.print(json);
        return null;
    }

    public JSONObject createMonitorJson(String ldId, Integer studyUserId) {
        LadsLearningdesign ld = this.ladsLearningdesignService.findLadsLearningdesignByUuid(ldId);
        JSONObject json = new JSONObject();
        json.put("title", ld.getTitle());
        json.put("userId", ld.getUserId());
        JSONArray actsArray = new JSONArray();
        LadsActivity firstAct = this.ladsActivityService.findLadsActivityByUuid(ld.getFirstActivity());
        //添加第一个活动
        actsArray.add(this.assembleJsonFromAct(firstAct, studyUserId));
        //添加其它活动
        actsArray = this.putActsToArray(actsArray, firstAct, studyUserId);
        json.put("activitys", actsArray);
        return json;
    }

    /*
     * 获取某用户某份课件的总成绩
     * 参数 ldId userId
     * 返回的是一个 键为总分数 值为<toolId 分数>的map
     */
    public Map<Double, Map<String, Double>> getUserScore(String ldId, String userId) {
        LadsActivityCondition ac = new LadsActivityCondition();
        ac.setLearningdesign(ldId);
        ac.setActivityType(this.getStandardActivityType(ActivityType.TOOL).getUuid());
        List<LadsActivity> actList = this.ladsActivityService.findLadsActivityByCondition(ac);
        // List<LadsActivity> actList = this.hibernateBaseServiceImpl.findList("from LadsActivity act where act.ladsLearningdesign.id='" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "'");
        double total = 0;
        Map<Double, Map<String, Double>> map = new HashMap();
        Map<String, Double> scoreMap = new HashMap();
        for (LadsActivity act : actList) {
            String score = null;
            if (act.getToolLibrary().equals(ToolName.EDITOR_TOOL)) {
//                score = this.editorToolServiceImpl.getUserScore(act.getToolId(), userId);
            } else if (act.getToolLibrary().equals(ToolName.FACE_TEACHING_TOOL)) {
//                score = this.faceTeachingToolServiceImpl.getUserScore(act.getToolId(), userId);
            } else if (act.getToolLibrary().equals(ToolName.DISCUSS_TOOL)) {
//                score = this.discussToolServiceImpl.getUserScore(act.getToolId(), userId);
            } else if (act.getToolLibrary().equals(ToolName.QUIZ_TOOL)) {
//                score = this.quizToolServiceImpl.getUserScore(act.getToolId(), userId);
            } else if (act.getToolLibrary().equals(ToolName.SURVEY_TOOL)) {
//                score = this.surveyToolServiceImpl.getUserScore(act.getToolId(), userId);
            } else if (act.getToolLibrary().equals(ToolName.POWER_POINT_TOOL)) {
//                score = this.powerPointToolServiceImpl.getUserScore(act.getToolId(), userId);
            }
            if (score != null && !("".equals(score)) && LadsCommonUtils.isNumeric(score)) {
                double realScore = Double.parseDouble(score);
                total = total + realScore;
                scoreMap.put(act.getToolId(), realScore);
            }
        }
        map.put(total, scoreMap);
        return map;
    }


    /*
     * 获取学习用户人数统计饼图的vo 
     */
    @RequestMapping(value = "/dataStatistics")
    public String dataStatistics(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        int t1 = (int) System.currentTimeMillis();
        String ldId = request.getParameter("ldId");
        LadsActivityVo avo = new LadsActivityVo();
        avo.setLearningdesign(ldId);
        avo.setToolLibrary(ToolName.EDITOR_TOOL);
        List<String> eactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> eactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.EDITOR_TOOL + "'");
        avo.setToolLibrary(ToolName.DISCUSS_TOOL);      
        List<String> dactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> dactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.DISCUSS_TOOL + "'");
        avo.setToolLibrary(ToolName.FACE_TEACHING_TOOL);  
        List<String> factList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> factList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.FACE_TEACHING_TOOL + "'");
        avo.setToolLibrary(ToolName.QUIZ_TOOL);  
        List<String> qactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> qactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.QUIZ_TOOL + "'");
        avo.setToolLibrary(ToolName.SURVEY_TOOL);  
        List<String> sactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> sactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.SURVEY_TOOL + "'");
        avo.setToolLibrary(ToolName.POWER_POINT_TOOL);  
        List<String> pactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> pactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.POWER_POINT_TOOL + "'");
        avo.setToolLibrary(ToolName.MEDIA_TOOL);  
        List<String> mactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
        LearnerStatisticsVo lvo = new LearnerStatisticsVo();
        ScoreStatisticsVo svo = new ScoreStatisticsVo();
        List<LadsUserVo> userList = this.ladsUserService.getStudyUserList(ldId);
        int graterThanOrEqual90 = 0;
        int between80To90 = 0;
        int between60To80 = 0;
        int lessThanOrEqual60 = 0;
        int finished = 0;
        int total = userList.size();
        for (LadsUserVo user : userList) {
            boolean statusFlag = true;
            double ldScore = 0;
            System.out.println("realName=" + user.getRealName());
            if (this.editorToolService.countFinishedStatus(eactList, user.getUserId()) > 0) {
                finished++;
                statusFlag = false;
            } else if (statusFlag && (this.discussToolService.countFinishedStatus(dactList, user.getUserId()) > 0)) {
                finished++;
                statusFlag = false;
//            } else if (statusFlag && (this.quizToolServiceImpl.countFinishedStatus(qactList, user.getUserId()) > 0)) {
//                finished++;
//                statusFlag = false;
//            } else if (statusFlag && (this.faceTeachingToolServiceImpl.countFinishedStatus(factList, user.getUserId()) > 0)) {
//                finished++;
//                statusFlag = false;
//            } else if (statusFlag && (this.surveyToolServiceImpl.countFinishedStatus(sactList, user.getUserId()) > 0)) {
//                finished++;
//                statusFlag = false;
//            } else if (statusFlag && (this.powerPointToolServiceImpl.countFinishedStatus(pactList, user.getUserId()) > 0)) {
//                finished++;
//                statusFlag = false;
            } else if(statusFlag && (this.mediaToolService.countFinishedStatus(mactList, user.getUserId()) > 0)){
                finished++;
                statusFlag = false;
            }
            if (eactList.size() > 0) {
                ldScore = ldScore + this.editorToolService.getUserScore(eactList, user.getUserId());
            }
            if (dactList.size() > 0) {
                ldScore = ldScore + this.discussToolService.getUserScore(dactList, user.getUserId());
            }
            if (qactList.size() > 0) {
//                ldScore = ldScore + this.quizToolServiceImpl.getUserScore(qactList, user.getUserId());
            }
            if (factList.size() > 0) {
//                ldScore = ldScore + this.faceTeachingToolServiceImpl.getUserScore(factList, user.getUserId());
            }
            if (sactList.size() > 0) {
//                ldScore = ldScore + this.surveyToolServiceImpl.getUserScore(sactList, user.getUserId());
            }
            if (pactList.size() > 0) {
//                ldScore = ldScore + this.powerPointToolServiceImpl.getUserScore(pactList, user.getUserId());
            }
            if (mactList.size() > 0) {
                ldScore = ldScore + this.mediaToolService.getUserScore(mactList, user.getUserId());
            }
            System.out.println("ldScore==" + ldScore);
            if (ldScore >= 90) {
                graterThanOrEqual90++;
            } else if (ldScore < 90 && ldScore >= 80) {
                between80To90++;
            } else if (ldScore < 80 && ldScore >= 60) {
                between60To80++;
            } else if (ldScore < 60) {
                lessThanOrEqual60++;
            }
        }
        lvo.setFinished(finished);
        lvo.setTotal(total);
        svo.setTotal(total);
        svo.setGraterThanOrEqual90(graterThanOrEqual90);
        svo.setBetween80To90(between80To90);
        svo.setBetween60To80(between60To80);
        svo.setLessThanOrEqual60(lessThanOrEqual60);
        request.setAttribute("learnerVo", lvo);
        request.setAttribute("scoreVo", svo);
        //运行测试内容
        int t2 = (int) System.currentTimeMillis();
        System.out.println("耗时：" + ((t2 - t1) / 1000));
        return monitoringDir +"/dataStatistics";
    }

    //获取所有学员列表
    @RequestMapping(value = "/learnerRecord")
    public String learnerRecord(HttpServletRequest request, HttpServletResponse response) {
        int t1 = (int) System.currentTimeMillis();
        String ldId = request.getParameter("ldId");
        List<LadsUserVo> userList = this.ladsUserService.getStudyUserList(ldId);
        LadsActivityVo avo = new LadsActivityVo();
        avo.setLearningdesign(ldId);
        avo.setToolLibrary(ToolName.EDITOR_TOOL);
        List<String> eactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> eactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.EDITOR_TOOL + "'");
        avo.setToolLibrary(ToolName.DISCUSS_TOOL);      
        List<String> dactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> dactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.DISCUSS_TOOL + "'");
        avo.setToolLibrary(ToolName.FACE_TEACHING_TOOL);  
        List<String> factList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> factList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.FACE_TEACHING_TOOL + "'");
        avo.setToolLibrary(ToolName.QUIZ_TOOL);  
        List<String> qactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> qactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.QUIZ_TOOL + "'");
        avo.setToolLibrary(ToolName.SURVEY_TOOL);  
        List<String> sactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> sactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.SURVEY_TOOL + "'");
        avo.setToolLibrary(ToolName.POWER_POINT_TOOL);  
        List<String> pactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
//        List<String> pactList = this.hibernateBaseServiceImpl.findList(
//                "select act.toolId from LadsActivity act where act.ladsLearningdesign.id = '" + ldId + "' and act.ladsActivityType.activityName = '" + ActivityType.TOOL + "' and act.toolLibrary ='" + ToolName.POWER_POINT_TOOL + "'");
        avo.setToolLibrary(ToolName.MEDIA_TOOL);  
        List<String> mactList = this.ladsActivityService.findToolIdByToolNameAndLdid(avo);
        //总活动数
        int allActivity = eactList.size()+dactList.size()+factList.size()+qactList.size()+sactList.size()+pactList.size()+mactList.size();
        List<UserScoreVo> scoreList = new ArrayList();
        for (LadsUserVo vo : userList) {
            double total = 0;
            UserScoreVo scoreVo = new UserScoreVo();
            double editorScore = 0;
            double faceteachingScore = 0;
            double discussScore = 0;
            double quizScore = 0;
            double surveyScore = 0;
            double powerpointScore = 0;
            double mediaScore = 0;
            int finishedActivity = 0;
            System.out.println("realName=" + vo.getRealName());
            if (eactList.size() > 0 ) {
                double realScore = this.editorToolService.getUserScore(eactList, vo.getUserId());
                editorScore = editorScore + realScore;
                total = total + realScore;
                finishedActivity = finishedActivity + this.editorToolService.countFinishedStatus(eactList, vo.getUserId());
            }
            if (dactList.size() > 0) {
                double realScore = this.discussToolService.getUserScore(dactList, vo.getUserId());
                discussScore = discussScore + realScore;
                total = total + realScore;
                finishedActivity = finishedActivity + this.discussToolService.countFinishedStatus(dactList, vo.getUserId());
            }
            if (qactList.size() > 0) {
//                double realScore = this.quizToolServiceImpl.getUserScore(qactList, vo.getUserId());
//                quizScore = quizScore + realScore;
//                total = total + realScore;
            }
            if (factList.size() > 0) {
//                double realScore = this.faceTeachingToolServiceImpl.getUserScore(factList, vo.getUserId());
//                faceteachingScore = faceteachingScore + realScore;
//                total = total + realScore;
            }
            if (sactList.size() > 0) {
//                double realScore = this.surveyToolServiceImpl.getUserScore(sactList, vo.getUserId());
//                surveyScore = surveyScore + realScore;
//                total = total + realScore;
            }
            if (pactList.size() > 0) {
//                double realScore = this.powerPointToolServiceImpl.getUserScore(pactList, vo.getUserId());
//                powerpointScore = powerpointScore + realScore;
//                total = total + realScore;
            }
            if (mactList.size() > 0) {
                double realScore = this.mediaToolService.getUserScore(mactList, vo.getUserId());
                mediaScore = mediaScore + realScore;
                total = total + realScore;
                finishedActivity = finishedActivity + this.mediaToolService.countFinishedStatus(mactList, vo.getUserId());
            }
            System.out.println("total=" + total);
            vo.setScore(total);
            scoreVo.setUserVo(vo);
            scoreVo.setEditorScore(editorScore);
            scoreVo.setFaceteachingScore(faceteachingScore);
            scoreVo.setDiscussScore(discussScore);
            scoreVo.setSurveyScore(surveyScore);
            scoreVo.setQuizScore(quizScore);
            scoreVo.setPowerpointScore(powerpointScore);
            scoreVo.setMediaScore(mediaScore);
            scoreVo.setFinishedPercent(Math.round(((float)finishedActivity/allActivity)*100));
            scoreList.add(scoreVo);
        }
        request.setAttribute("scoreList", scoreList);
        //运行测试内容
        int t2 = (int) System.currentTimeMillis();
        System.out.println("耗时：" + ((t2 - t1) / 1000));
        return monitoringDir + "/learnerRecord";
    }

    //获取单个学员学习记录
    @RequestMapping(value = "/singleRecord")
    public String singleRecord(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        request.setAttribute("userName", userName);
        request.setAttribute("userId", userId);
        return monitoringDir + "/singleRecord";
    }

    public JSONObject assembleJsonFromAct(LadsActivity act, Integer studyUserId) {
        JSONObject jAct = new JSONObject();
        jAct.put("id", act.getToolId());
        String activityName = this.ladsActivityService.getActivityType(act).getActivityName();
        jAct.put("activityType", activityName);
        if (activityName.equals(ActivityType.TOOL)) {
            jAct.put("toolName", act.getToolLibrary());
            if (act.getToolLibrary().equals(ToolName.EDITOR_TOOL)) {
                LadsEditorTool editor = this.editorToolService.getEditorByToolId(act.getToolId());
                jAct.put("editorTitle", editor.getTitle());
                jAct.put("editorScore", act.getScore());
                jAct.put("editorContent", editor.getContent());
                //学习用户信息
                if (studyUserId != null) {
                    jAct.put("finished", this.editorToolService.getFinishedStatus(act.getToolId(), studyUserId));
                    jAct.put("userScore", this.editorToolService.getUserScore(act.getToolId(), studyUserId));
                }
            } else if (act.getToolLibrary().equals(ToolName.FACE_TEACHING_TOOL)) {
//                LadsFaceteachingTool teaching = this.faceTeachingToolServiceImpl.getFaceteachingByToolId(act.getToolId());
//                jAct.put("faceTeachingTitle", teaching.getTitle());
//                jAct.put("faceTeachingScore", act.getScore());
//                jAct.put("faceTeachingStartTime", teaching.getStartTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(teaching.getStartTime()).toString() : "");
//                jAct.put("faceTeachingStopTime", teaching.getStopTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(teaching.getStopTime()).toString() : "");
//                jAct.put("faceTeachingAddress", teaching.getAddress());
//                jAct.put("faceTeachingDescription", teaching.getDescription());
//                jAct.put("finished", this.faceTeachingToolServiceImpl.getFinishedStatus(act.getToolId(), studyUserId));
//                jAct.put("userScore", this.faceTeachingToolServiceImpl.getUserScore(act.getToolId(), studyUserId));
            } else if (act.getToolLibrary().equals(ToolName.DISCUSS_TOOL)) {
                LadsDiscussTool discuss = this.discussToolService.getLadsDiscussToolByToolId(act.getToolId());
                jAct.put("discussTitle", discuss.getTitle());
                jAct.put("discussAllowUpload", discuss.getAllowUpload());
                jAct.put("discussScore", act.getScore());
                jAct.put("discussStartTime", discuss.getStartTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(discuss.getStartTime()).toString() : "");
                jAct.put("discussStopTime", discuss.getStopTime() != null ? new SimpleDateFormat("MM.dd HH:mm").format(discuss.getStopTime()).toString() : "");
                //学习用户信息
                if (studyUserId != null) {
                    jAct.put("finished", this.discussToolService.getFinishedStatus(act.getToolId(), studyUserId));
                    jAct.put("userScore", this.discussToolService.getUserScore(act.getToolId(), studyUserId));
                }
            } else if (act.getToolLibrary().equals(ToolName.QUIZ_TOOL)) {
//                LadsQuizTool qt = this.quizToolServiceImpl.getQuizByToolId(act.getToolId());
//                jAct.put("quizTitle", qt.getTitle());
//                jAct.put("quizScore", act.getScore());
//                jAct.put("quizXmlPath", DocPathUtil.converHttpUrl(qt.getHttpUrl()));
//                jAct.put("finished", this.quizToolServiceImpl.getFinishedStatus(act.getToolId(), studyUserId));
//                jAct.put("userScore", this.quizToolServiceImpl.getUserScore(act.getToolId(), studyUserId));
            } else if (act.getToolLibrary().equals(ToolName.SURVEY_TOOL)) {
//                LadsSurveyTool st = this.surveyToolServiceImpl.getSurveyByToolId(act.getToolId());
//                jAct.put("surveyTitle", st.getTitle());
//                jAct.put("surveyScore", act.getScore());
//                jAct.put("surveyDescription", st.getDescription());
//                jAct.put("finished", this.surveyToolServiceImpl.getFinishedStatus(act.getToolId(), studyUserId));
//                jAct.put("userScore", this.surveyToolServiceImpl.getUserScore(act.getToolId(), studyUserId));
            } else if (act.getToolLibrary().equals(ToolName.POWER_POINT_TOOL)) {
//                LadsPowerpointTool st = this.powerPointToolServiceImpl.getPowerpointByToolId(act.getToolId());
//                jAct.put("powerPointTitle", st.getTitle());
//                jAct.put("powerPointScore", act.getScore());
//                jAct.put("powerPointFileId", st.getResFile() != null ? st.getResFile().getId() : "");
//                jAct.put("finished", this.powerPointToolServiceImpl.getFinishedStatus(act.getToolId(), studyUserId));
//                jAct.put("userScore", this.powerPointToolServiceImpl.getUserScore(act.getToolId(), studyUserId));
            } else if (act.getToolLibrary().equals(ToolName.MEDIA_TOOL)) {
                LadsMediaTool media = this.mediaToolService.getMediaByToolId(act.getToolId());
                jAct.put("mediaTitle", media.getTitle());
                jAct.put("mediaScore", act.getScore());
                //学习用户信息
                if (studyUserId != null) {
                    jAct.put("finished", this.mediaToolService.getFinishedStatus(act.getToolId(), studyUserId));
                    jAct.put("userScore", this.mediaToolService.getUserScore(act.getToolId(), studyUserId));
                }
            }
        } else if (activityName.equals(ActivityType.GROUP)) {
            jAct.put("groupTitle", act.getTitle());
            LadsActivityCondition ac = new LadsActivityCondition();
            ac.setParentActivity(act.getUuid());
            List<LadsActivity> subActs = this.ladsActivityService.findLadsActivityByCondition(ac);
//            List<LadsActivity> subActs = this.hibernateBaseServiceImpl.findList("from LadsActivity la where la.ladsActivity.id = '" + act.getId() + "'");
            JSONArray subActsArray = new JSONArray();
            if (subActs.size() > 0) {
                //添加组内第一个活动
                LadsActivity firstSubAct = subActs.get(0);
                //递归
                subActsArray.add(this.assembleJsonFromAct(firstSubAct, studyUserId));
                //添加其它活动
                subActsArray = this.putActsToArray(subActsArray, firstSubAct, studyUserId);
            }
            jAct.put("subActivitys", subActsArray);
        }
        return jAct;
    }

    public LadsActivityType getStandardActivityType(String typeName) {
        LadsActivityTypeCondition atc = new LadsActivityTypeCondition();
        atc.setActivityName(typeName);
        List<LadsActivityType> atl = this.ladsActivityTypeService.findLadsActivityTypeByCondition(atc);
        return atl.get(0);
    }

    public JSONArray putActsToArray(JSONArray actsArray, LadsActivity fromAct, Integer studyUserId) {
        LadsActivityTransitionCondition atc = new LadsActivityTransitionCondition();
        atc.setFromActivity(fromAct.getUuid());
        List<LadsActivityTransition> atList = this.ladsActivityTransitionService.findLadsActivityTransitionByCondition(atc);
        LadsActivity toAct;
        if (atList.size() > 0) {
            toAct = this.ladsActivityTransitionService.getToActivity(atList.get(0));
            //添加活动
            actsArray.add(this.assembleJsonFromAct(toAct, studyUserId));
            //递归
            actsArray = putActsToArray(actsArray, toAct, studyUserId);
        }
        return actsArray;
    }

    @RequestMapping(value = "/editorToolPaper")
    public String editorToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String ldId = request.getParameter("ldId");
        String editorScore = request.getParameter("editorScore");
        Object obj[] = this.editorToolService.getUserStatusList(ldId, id);
        List<LadsEditorUserStatusVo> voList = (List<LadsEditorUserStatusVo>) obj[0];
        Integer finish = (Integer) obj[1];
        Integer notFinish = (Integer) obj[2];
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("finish", finish);
        request.setAttribute("notFinish", notFinish);
        request.setAttribute("editorScore", editorScore);
        request.setAttribute("editorFinishedCons", platform.education.lads.contants.editorToolCons.StudyStatus.STUDYED);
        request.setAttribute("voList", voList);
        return monitoringDir + "/editortoolpaper";
    }

    @RequestMapping(value = "/faceTeachingToolPaper")
    public String faceTeachingToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String sysType = request.getParameter("sysType");
        String faceTeachingAddress = request.getParameter("faceTeachingAddress");
        String faceTeachingDescription = request.getParameter("faceTeachingDescription");
        String faceTeachingStartTime = request.getParameter("faceTeachingStartTime");
        String faceTeachingStopTime = request.getParameter("faceTeachingStopTime");
        String faceTeachingScore = request.getParameter("faceTeachingScore");
        String ldId = request.getParameter("ldId");
//        Object[] obj = this.faceTeachingToolServiceImpl.getUserStatusList(sysType, ldId, id, faceTeachingScore);
//        List<LadsFaceTeachingUserStatusVo> voList = (List<LadsFaceTeachingUserStatusVo>) obj[0];
//        Integer fullAttendance = (Integer) obj[1];
//        Integer absenteetsm = (Integer) obj[2];
//        Integer sickLeave = (Integer) obj[3];
//        Integer publicHoliday = (Integer) obj[4];
//        request.setAttribute("id", id);
//        request.setAttribute("voList", voList);
//        request.setAttribute("faceTeachingFullAttendance", fullAttendance);
//        request.setAttribute("faceTeachingAbsenteetsm", absenteetsm);
//        request.setAttribute("faceTeachingSickLeave", sickLeave);
//        request.setAttribute("faceTeachingPublicHoliday", publicHoliday);
//        request.setAttribute("faceTeachingScore", faceTeachingScore);
//        request.setAttribute("faceTeachingAddress", faceTeachingAddress);
//        request.setAttribute("faceTeachingDescription", faceTeachingDescription);
//        request.setAttribute("faceTeachingStartTime", faceTeachingStartTime);
//        request.setAttribute("faceTeachingStopTime", faceTeachingStopTime);
//        request.setAttribute("title", title);
        return "monitor_faceTeachingToolPaper";
    }

    @RequestMapping(value = "/quizToolPaper")
    public String quizToolPaper(HttpServletRequest request, HttpServletResponse response) throws DocumentException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        return "monitor_quizToolPaper";
    }

    @RequestMapping(value = "/mediaToolPaper")
    public String mediaToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String ldId = request.getParameter("ldId");
        String mediaScore = request.getParameter("mediaScore");
        Object obj[] = this.mediaToolService.getUserStatusList(ldId, id);
        List<LadsMediaUserStatusVo> voList = (List<LadsMediaUserStatusVo>) obj[0];
        Integer finish = (Integer) obj[1];
        Integer notFinish = (Integer) obj[2];
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("finish", finish);
        request.setAttribute("notFinish", notFinish);
        request.setAttribute("mediaScore", mediaScore);
        request.setAttribute("mediaFinishedCons", platform.education.lads.contants.mediaToolCons.StudyStatus.FINISHED);
        request.setAttribute("voList", voList);
        return monitoringDir + mediaToolDir+ "/mediatoolpaper";
    }

    @RequestMapping(value = "/surveyToolPaper")
    public String surveyToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String desc = request.getParameter("surveyDescription");
        String score = request.getParameter("surveyScore");
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("surveyScore", score);
        request.setAttribute("surveyDescription", desc);
        return "monitor_surveyToolPaper";
    }

    @RequestMapping(value = "/discussToolPaper")
    public String discussToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String ldId = request.getParameter("ldId");
        String title = request.getParameter("title");
        String discussStartTime = request.getParameter("discussStartTime");
        String discussStopTime = request.getParameter("discussStopTime");
        String discussScore = request.getParameter("discussScore");
        String discussAllowUpload = request.getParameter("discussAllowUpload");
        Object[] obj = this.discussToolService.getUserStatusList(ldId, id);
        List<LadsDiscussUserStatusVo> voList = (List<LadsDiscussUserStatusVo>) obj[0];
        Integer allComments = (Integer) obj[1];
        Integer commented = (Integer) obj[2];
        Integer notComment = (Integer) obj[3];
        request.setAttribute("voList", voList);
        request.setAttribute("allComments", allComments);
        request.setAttribute("commented", commented);
        request.setAttribute("notComment", notComment);
        request.setAttribute("id", id);
        request.setAttribute("title", title);
        request.setAttribute("discussAllowUpload", discussAllowUpload);
        request.setAttribute("discussScore", discussScore);
        request.setAttribute("discussStartTime", discussStartTime);
        request.setAttribute("discussStopTime", discussStopTime);
        return monitoringDir + discussToolDir + "/discusstoolpaper";
    }

    @RequestMapping(value = "/powerPointToolPaper")
    public String powerPointToolPaper(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String sysType = request.getParameter("sysType");
        String ldId = request.getParameter("ldId");
        String powerPointScore = request.getParameter("powerPointScore");
        String powerPointFileId = request.getParameter("powerPointFileId");
//        Object obj[] = this.powerPointToolServiceImpl.getUserStatusList(sysType, ldId, id);
//        List<LadsPowerPointUserStatusVo> voList = (List<LadsPowerPointUserStatusVo>) obj[0];
//        Integer finish = (Integer) obj[1];
//        Integer notFinish = (Integer) obj[2];
//        request.setAttribute("id", id);
//        request.setAttribute("title", title);
//        request.setAttribute("finish", finish);
//        request.setAttribute("notFinish", notFinish);
//        request.setAttribute("voList", voList);
//        request.setAttribute("powerPointScore", powerPointScore);
//        request.setAttribute("powerPointFileId", powerPointFileId);
        return "monitor_powerPointToolPaper";
    }
    
    @RequestMapping(value = "/finishedImage")
    public String finishedImage(HttpServletRequest request, HttpServletResponse response) {
        return monitoringDir + "/finishedImage";
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
