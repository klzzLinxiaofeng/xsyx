///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service;
//
//import com.gzxtjy.lads.entities.LadsPowerpointTool;
//import com.gzxtjy.lads.entities.LadsPowerpointUserStatusTool;
//import java.io.File;
//import java.io.IOException;
//
///**
// *
// * @author Administrator
// */
//public interface PowerPointToolService extends ToolService{
//    
//    public LadsPowerpointTool getPowerpointByToolId(String toolId);
//    
//    public LadsPowerpointTool save(String toolId ,String title,String fileId);
//    
//    public String uploadPpt(File file, String uploadFileName, String toolId) throws IOException;
//    
//    public LadsPowerpointUserStatusTool getPowerPointUserStautsByToolIdAndUserId(String toolId, String userId);
//        
//    public LadsPowerpointUserStatusTool saveUserStatus(String toolId, String userId, String powerPointScore);
//    
//    public Object[] getUserStatusList(String sysType, String ldId, String toolId);
//}
