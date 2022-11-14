package platform.education.lads.web.controller;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package platform.education.lads.action;
//
//import com.gzxtjy.common.service.HibernateBaseService;
//import com.gzxtjy.common.web.action.BaseAction;
//import com.gzxtjy.lads.constants.EmbedSystem;
//import com.gzxtjy.lads.entities.LadsPowerpointUserStatusTool;
//import com.gzxtjy.lads.service.PowerPointToolService;
//import com.gzxtjy.lads.service.impl.PowerPointToolServiceImpl;
//import com.gzxtjy.lads.util.LadsCommonUtils;
//import com.gzxtjy.portal.session.SessionManager;
//import com.gzxtjy.resources.constants.ResourceTypeName;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//
///**
// *
// * @author Administrator
// */
//@Controller("ladsPowerPointAction")
//@Scope("prototype")
//public class PowerPointAction extends BaseAction {
//
//    @Resource
//    private HibernateBaseService hibernateBaseServiceImpl;
//    @Resource
//    public PowerPointToolService powerPointToolServiceImpl;
//    public File ppt;
//    private String pptFileName;
//    private String pptContentType;
//
//    public File getPpt() {
//        return ppt;
//    }
//
//    public void setPpt(File ppt) {
//        this.ppt = ppt;
//    }
//
//    public String getPptFileName() {
//        return pptFileName;
//    }
//
//    public void setPptFileName(String pptFileName) {
//        this.pptFileName = pptFileName;
//    }
//
//    public String getPptContentType() {
//        return pptContentType;
//    }
//
//    public void setPptContentType(String pptContentType) {
//        this.pptContentType = pptContentType;
//    }
//
//    public String uploadPpt() throws IOException, UnsupportedEncodingException {
//        HttpServletRequest request = super.getRequest();
//        HttpServletResponse response = super.getResponse();
//        String toolId = request.getParameter("toolId");
//        String fileId = this.powerPointToolServiceImpl.uploadPpt(ppt, pptFileName, toolId);
//        PrintWriter pw = this.setAjaxResponse(request, response);
//        pw.print(fileId);
//        pw.close();
//        return null;
//    }
//    
//    public String loadPptContent(){
//        HttpServletRequest request = super.getRequest();
//        String toolId = request.getParameter("toolId");
//        String pptFileTitle  = request.getParameter("pptFileTitle");
//        String pptFileId = request.getParameter("pptFileId");
//        request.setAttribute("pptFileTitle",pptFileTitle);
//        request.setAttribute("pptFileId", pptFileId);
//        request.setAttribute("id", toolId);
//        return "author_powerPoint_content";
//    }
//
//    public String uploadPptIndex() {
//        HttpServletRequest request = super.getRequest();
//        String toolId = request.getParameter("toolId");
//        request.setAttribute("id", toolId);
//        return "author_powerPoint_upload_index";
//    }
//
//    public String loadPersonalPptList() {
//        HttpServletRequest request = super.getRequest();
//        String sysType = request.getParameter("sysType");
//        String toolId = request.getParameter("toolId");
//        if (sysType.equals(EmbedSystem.XTJY)) {
//            DetachedCriteria criteria = DetachedCriteria.forClass(com.gzxtjy.resources.entities.Resource.class);
//            criteria.createAlias("centerUser", "centerUser");
//            criteria.add(Restrictions.eq("centerUser.userId", SessionManager.getUser().getId()));
//            // 教材目录筛选
//            //criteria = CatalogUtil.setDirectoryInCriteria(request, criteria);
//            criteria.createAlias("resFile", "resFile");
//            criteria.add(Restrictions.or(Restrictions.eq("resFile.suffix", "ppt").ignoreCase(), Restrictions.eq("resFile.suffix", "pptx").ignoreCase()));
////            if (keyWord != null && !"".equals(keyWord)) {
////                criteria.add(Restrictions.like("title", "%" + keyWord + "%"));
////            }
//            criteria.addOrder(Order.desc("createTime"));
//            List<com.gzxtjy.resources.entities.Resource> pptList = this.hibernateBaseServiceImpl.findByCriteria(pagination, criteria);
//            request.setAttribute("urlPara", LadsCommonUtils.createUrlPara(request));
//            request.setAttribute("pptList", pptList);
//        }
//        request.setAttribute("id", toolId);
//
//        return "author_powerPoint_personal_list";
//    }
//    
//    public String resourcesPptSearch(){
//        HttpServletRequest request = super.getRequest();
//        String sysType = request.getParameter("sysType");
//        String toolId = request.getParameter("toolId");
//        if (sysType.equals(EmbedSystem.XTJY)) {
//            
//        }else{
//            
//        }
//        request.setAttribute("id", toolId);
//        return "author_powerPoint_resources_list";
//    }
//
//    public String saveUserStatus() {
//        HttpServletRequest request = super.getRequest();
//        String userId = request.getParameter("userId");
//        String toolId = request.getParameter("toolId");
//        String powerPointScore = request.getParameter("powerPointScore");
//        if (userId != null && !"".equals(userId)) {
//            this.powerPointToolServiceImpl.saveUserStatus(toolId, userId, powerPointScore);
//        }
//        return null;
//    }
//
//    public String editUserScore() {
//        HttpServletRequest request = super.getRequest();
//        String statusId = request.getParameter("statusId");
//        String score = request.getParameter("score");
//        LadsPowerpointUserStatusTool status;
//        if (statusId != null && !"".equals(statusId)) {
//            status = this.hibernateBaseServiceImpl.findById(LadsPowerpointUserStatusTool.class, statusId);
//            //已学的用户修改分数
//            status.setScore(score);
//        } else {
//            //未学的用户修改分数
//            String toolId = request.getParameter("toolId");
//            String userId = request.getParameter("userId");
//            status = new LadsPowerpointUserStatusTool();
//            status.setCreateTime(new Date());
//            status.setLadsPowerpointTool(this.powerPointToolServiceImpl.getPowerpointByToolId(toolId));
//            status.setStudyTime(0);
//            status.setStatus(PowerPointToolServiceImpl.NOT_STUDY);
//            status.setUserId(userId);
//            status.setScore(score);
//        }
//        this.hibernateBaseServiceImpl.saveOrUpdate(status);
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
