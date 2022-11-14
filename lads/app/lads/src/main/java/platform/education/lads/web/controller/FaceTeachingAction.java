package platform.education.lads.web.controller;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package platform.education.lads.action;
//
//import com.gzxtjy.common.service.HibernateBaseService;
//import com.gzxtjy.common.web.action.BaseAction;
//import com.gzxtjy.lads.entities.LadsFaceteachingUserStatusTool;
//import com.gzxtjy.lads.service.FaceTeachingToolService;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author Administrator
// */
//@Controller("ladsFaceTeachingAction")
//@Scope("prototype")
//public class FaceTeachingAction extends BaseAction{
//
//    @Resource
//    private HibernateBaseService hibernateBaseServiceImpl;
//    @Resource
//    private FaceTeachingToolService faceTeachingToolServiceImpl;
//    
//    public String editUserScore(){
//        HttpServletRequest request = super.getRequest();
//        String statusId = request.getParameter("statusId");
//        String score = request.getParameter("score");
//        LadsFaceteachingUserStatusTool status = this.hibernateBaseServiceImpl.findById(LadsFaceteachingUserStatusTool.class, statusId);
//        status.setScore(score);
//        this.hibernateBaseServiceImpl.update(status);
//        return null;
//    }
//
//    public String editUserStatus(){
//        HttpServletRequest request = super.getRequest();
//        String statusId = request.getParameter("statusId");
//        String attend = request.getParameter("status");
//        LadsFaceteachingUserStatusTool status = this.hibernateBaseServiceImpl.findById(LadsFaceteachingUserStatusTool.class, statusId);
//        status.setStatus(attend);
//        this.hibernateBaseServiceImpl.update(status);
//        return null;
//    }
//}
