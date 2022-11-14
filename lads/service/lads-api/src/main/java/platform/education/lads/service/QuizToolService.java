///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service;
//
//import com.gzxtjy.lads.entities.LadsQuizQuestionTool;
//import com.gzxtjy.lads.entities.LadsQuizResultTool;
//import com.gzxtjy.lads.entities.LadsQuizTool;
//import com.gzxtjy.lads.vo.quizToolVo.LadsQuizResultVo;
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//import org.dom4j.DocumentException;
//
///**
// *
// * @author Administrator
// */
//public interface QuizToolService extends ToolService {
//
//    public LadsQuizTool save(String toolId, String title, String xmlContent, String uploadPath);
//
//    public LadsQuizTool getQuizByToolId(String toolId);
//
//    public String[] uploadQuizXml(String xmlContent, String uploadPath);
//
//    public List<LadsQuizQuestionTool> getQuestionVoList(LadsQuizTool pq) throws DocumentException;
//
//    public LadsQuizResultTool getLastQuizResult(String userId, String quizId);
//
//    public LadsQuizResultTool saveQuizResult(LadsQuizResultTool eqr);
//
//    public StringBuilder questionReportCreater(LadsQuizQuestionTool qq, List<LadsQuizResultVo> rList) throws DocumentException, UnsupportedEncodingException;
//
//    public StringBuilder xmlReportCreater(LadsQuizResultTool eqr) throws DocumentException;
//
//    public LadsQuizResultTool saveScore(LadsQuizResultTool eqr, String[] score) throws DocumentException;
//    
//}
