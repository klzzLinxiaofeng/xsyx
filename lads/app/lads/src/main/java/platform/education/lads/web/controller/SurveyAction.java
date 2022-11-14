package platform.education.lads.web.controller;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package platform.education.lads.action;
//
//import com.gzxtjy.common.service.HibernateBaseService;
//import com.gzxtjy.common.web.action.BaseAction;
//import com.gzxtjy.lads.constants.surveyToolCons.QuestionType;
//import com.gzxtjy.lads.entities.LadsSurveyAnswerTool;
//import com.gzxtjy.lads.entities.LadsSurveyQuestionTool;
//import com.gzxtjy.lads.service.LadsService;
//import com.gzxtjy.lads.service.SurveyToolService;
//import platform.education.lads.vo.LadsUserVo;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author 罗志明
// */
//@Controller("ladsSurveyAction")
//@Scope("prototype")
//public class SurveyAction extends BaseAction {
//
//    @Resource
//    private HibernateBaseService hibernateBaseServiceImpl;
//    @Resource
//    private SurveyToolService surveyToolServiceImpl;
//    @Resource
//    private LadsService ladsServiceImpl;
//
//    public String loadSurveyJson() throws UnsupportedEncodingException, IOException {
//        HttpServletRequest request = super.getRequest();
//        HttpServletResponse response = super.getResponse();
//        String toolId = request.getParameter("toolId");
//        String userId = request.getParameter("userId");
//        JSONObject jAct = new JSONObject();
//        JSONArray questionsArray = new JSONArray();
//        List<LadsSurveyQuestionTool> questionList = this.surveyToolServiceImpl.getSurveyQuestionList(toolId);
//        for (LadsSurveyQuestionTool qt : questionList) {
//            JSONObject question = new JSONObject();
//            question.put("type", qt.getQuestionType());
//            question.put("content", qt.getContent());
//            question.put("answer", qt.getAnswer());
//            question.put("questionId", qt.getId());
//            question.put("pos", qt.getPos());
//            if (userId != null && !"".equals(userId)) {
//                LadsSurveyAnswerTool userAnswer = this.surveyToolServiceImpl.getSurveyAnswerByUserIdAndQuestion(userId, qt.getId());
//                question.put("userAnswer", userAnswer != null ? userAnswer.getUserAnswer() : "");
//            }
//            questionsArray.add(question);
//        }
//        jAct.put("surveyQuestions", questionsArray);
//        PrintWriter pw = this.setAjaxResponse(request, response);
//        pw.print(jAct);
//        return null;
//    }
//
//    public String loadSurveyQuestionStatisticsJson() throws UnsupportedEncodingException, IOException {
//        HttpServletRequest request = super.getRequest();
//        HttpServletResponse response = super.getResponse();
//        String toolId = request.getParameter("toolId");
//        String sysType = request.getParameter("sysType");
//        String ldId = request.getParameter("ldId");
//        JSONObject jAct = new JSONObject();
//        JSONArray questionsArray = new JSONArray();
//        List<LadsSurveyQuestionTool> questionList = this.surveyToolServiceImpl.getSurveyQuestionList(toolId);
//        List<LadsUserVo> voList = this.ladsServiceImpl.getStudyUserList(sysType, ldId);
//        for (LadsSurveyQuestionTool qt : questionList) {
//            JSONObject question = new JSONObject();
//            question.put("type", qt.getQuestionType());
//            question.put("content", qt.getContent());
//            question.put("answer", qt.getAnswer());
//            question.put("questionId", qt.getId());
//            if (qt.getQuestionType().equals(QuestionType.RADIO) || qt.getQuestionType().equals(QuestionType.CHECKBOX)) {
//                int[] answers = this.surveyToolServiceImpl.getAnswerPercentByQuestion(qt.getId(), voList, qt.getAnswer());
//                JSONArray percentArray = new JSONArray();
//                for(int a=0;a<answers.length;a++){
//                    percentArray.add(answers[a]);
//                }
//                question.put("answerPercents",percentArray);
//            }else if(qt.getQuestionType().equals(QuestionType.WORD)){
//                question.put("wordAnswers", this.surveyToolServiceImpl.getWordAnswerByQuestion(qt.getId(), voList));
//            }
//            question.put("pos", qt.getPos());
//            questionsArray.add(question);
//        }
//        jAct.put("surveyQuestions", questionsArray);
//        PrintWriter pw = this.setAjaxResponse(request, response);
//        pw.print(jAct);
//        return null;
//    }
//
//    public String saveUserAnswer() {
//        HttpServletRequest request = super.getRequest();
//        String toolId = request.getParameter("toolId");
//        String userId = request.getParameter("userId");
//        String userAnswers = request.getParameter("userAnswers");
//        JSONArray answersArray = JSONArray.fromObject(userAnswers);
//        for (int i = 0; i < answersArray.size(); i++) {
//            JSONObject answer = answersArray.getJSONObject(i);
//            String userAnswer = (String) answer.get("answer");
//            String questionId = (String) answer.get("questionId");
//            int pos = (Integer) answer.get("pos");
//            this.surveyToolServiceImpl.saveAnswer(questionId, userId, userAnswer);
//        }
//        return null;
//    }
//
//    private PrintWriter setAjaxResponse(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("utf-8");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setHeader("Pragma", "no-cache");
//        response.setDateHeader("Expires", -1);
//        return response.getWriter();
//    }
//}
