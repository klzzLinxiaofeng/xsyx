///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service;
//
//import com.gzxtjy.lads.entities.LadsSurveyAnswerTool;
//import com.gzxtjy.lads.entities.LadsSurveyQuestionTool;
//import com.gzxtjy.lads.entities.LadsSurveyTool;
//import com.gzxtjy.lads.vo.LadsUserVo;
//import java.util.List;
//import net.sf.json.JSONArray;
//
///**
// *
// * @author Administrator
// */
//public interface SurveyToolService extends ToolService{
//    
//    
//    public LadsSurveyTool getSurveyByToolId(String toolId);
//    
//    public LadsSurveyTool save(String toolId, String title,String surveyDescription,JSONArray surveyArray);
//    
//    public List<LadsSurveyQuestionTool> getSurveyQuestionList(String toolId);
//    
//    public LadsSurveyAnswerTool saveAnswer(String questionId,String userId,String userAnswer);
//    
//    public LadsSurveyQuestionTool getSurveyQuestionByToolIdAnsPos(String toolId, int pos);
//    
//    public LadsSurveyAnswerTool getSurveyAnswerByUserIdAndQuestion(String userId, String questionId);
//    
//    public int[] getAnswerPercentByQuestion(String questionId,List<LadsUserVo> voList,String standardAnswer);
//    
//    public JSONArray getWordAnswerByQuestion(String questionId, List<LadsUserVo> voList);
//}
