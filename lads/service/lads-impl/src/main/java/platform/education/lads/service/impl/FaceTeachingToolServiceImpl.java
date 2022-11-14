package platform.education.lads.service.impl;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service.impl;
//
//import com.gzxtjy.common.dao.hibernate.HibernateBaseDao;
//import com.gzxtjy.common.util.StrUtils;
//import com.gzxtjy.lads.constants.FinishedStatus;
//import com.gzxtjy.lads.constants.ToolName;
//import com.gzxtjy.lads.entities.LadsFaceteachingTool;
//import com.gzxtjy.lads.entities.LadsFaceteachingUserStatusTool;
//import com.gzxtjy.lads.entities.LadsToolLibrary;
//import com.gzxtjy.lads.service.FaceTeachingToolService;
//import com.gzxtjy.lads.service.LadsService;
//import com.gzxtjy.lads.vo.LadsUserVo;
//import com.gzxtjy.lads.vo.faceteachingToolVo.LadsFaceTeachingUserStatusVo;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Administrator
// */
//@Service("faceTeachingToolServiceImpl")
//@Transactional(rollbackFor = {Exception.class})
//public class FaceTeachingToolServiceImpl implements FaceTeachingToolService {
//
//    public static final String FULL_ATTENDANCE = "全勤";
//    public static final String PUBLIC_HOLIDAY = "公假";
//    public static final String SICK_LEAVE = "病事假";
//    public static final String ABSENTEEISM = "旷课";
//    @Resource
//    private HibernateBaseDao hibernateBaseDao;
//    @Resource
//    private LadsService ladsServiceImpl;
//
//    @Override
//    public String toHtml() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public String toXml() {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public LadsFaceteachingTool save(String toolId, String title, String startTime, String stopTime, String description, String address) {
//        LadsFaceteachingTool ft = getFaceteachingByToolId(toolId);
//        if (ft == null) {
//            ft = new LadsFaceteachingTool();
//            ft.setCreateTime(new Date());
//            ft.setLadsToolLibrary(this.hibernateBaseDao.findUniqueBy(LadsToolLibrary.class, "toolName", ToolName.FACE_TEACHING_TOOL));
//        }
//        ft.setAddress(address);
//        ft.setTitle(title);
//        ft.setDescription(description);
//        ft.setToolId(toolId);
//        try {
//            ft.setStartTime((startTime == null || "".equals(startTime)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime));
//            ft.setStopTime((stopTime == null || "".equals(stopTime)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stopTime));
//        } catch (ParseException ex) {
//            Logger.getLogger(FaceTeachingToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(ft);
//        return ft;
//    }
//
//    @Override
//    public LadsFaceteachingTool getFaceteachingByToolId(String toolId) {
//        List list = this.hibernateBaseDao.find("from LadsFaceteachingTool et where et.toolId = '" + toolId + "' order by et.createTime desc");
//        if (list.size() > 0) {
//            return (LadsFaceteachingTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Object[] getUserStatusList(String sysType, String ldId, String toolId, String score) {
//        List<LadsUserVo> userList = this.ladsServiceImpl.getStudyUserList(sysType, ldId);
//        List<LadsFaceTeachingUserStatusVo> voList = new ArrayList();
//        Object[] obj = new Object[5];
//        int fullAttendance = 0;
//        int publicHoliday = 0;
//        int sickLeave = 0;
//        int absenteetsm = 0;
//        for (LadsUserVo user : userList) {
//            LadsFaceTeachingUserStatusVo vo = new LadsFaceTeachingUserStatusVo();
//            vo.setUserId(user.getUserId());
//            vo.setRealName(user.getRealName());
//            LadsFaceteachingUserStatusTool status = getUserStatusByToolIdAnsUserId(toolId,user.getUserId());
//            if (status==null) {
//                status = new LadsFaceteachingUserStatusTool();
//                //默认旷课
//                status.setStatus(ABSENTEEISM);
//                status.setScore("0");
//                status.setUserId(user.getUserId());
//                status.setCreateTime(new Date());
//                status.setLadsFaceteachingTool(getFaceteachingByToolId(toolId));
//                this.hibernateBaseDao.save(status);
//            }
//            vo.setStatus(status);
//            voList.add(vo);
//            if (status.getStatus().equals(FULL_ATTENDANCE)) {
//                fullAttendance++;
//            } else if (status.getStatus().equals(ABSENTEEISM)) {
//                absenteetsm++;
//            } else if (status.getStatus().equals(SICK_LEAVE)) {
//                sickLeave++;
//            } else if (status.getStatus().equals(PUBLIC_HOLIDAY)) {
//                publicHoliday++;
//            }
//        }
//        obj[0] = voList;
//        obj[1] = fullAttendance;
//        obj[2] = absenteetsm;
//        obj[3] = sickLeave;
//        obj[4] = publicHoliday;
//        return obj;
//    }
//
//    @Override
//    public LadsFaceteachingUserStatusTool getUserStatusByToolIdAnsUserId(String toolId, String userId) {
//        List<LadsFaceteachingUserStatusTool> statusList = this.hibernateBaseDao.find("from LadsFaceteachingUserStatusTool us where us.ladsFaceteachingTool.toolId='" + toolId + "' and us.userId = '" + userId + "' order by us.createTime desc");
//        if (statusList.size() > 0) {
//            return statusList.get(0);
//        } else {
//            return null;
//        }
//    }
//    
//    @Override
//    public int getFinishedStatus(String toolId, String userId){ 
//        LadsFaceteachingUserStatusTool status = getUserStatusByToolIdAnsUserId(toolId,userId);
//        if(status!=null){
//            if(FULL_ATTENDANCE.equals(status.getStatus())){
//                return FinishedStatus.FINISHED;
//            }else{
//                return FinishedStatus.NOT_FINISHED;
//            }
//        }else{
//            return FinishedStatus.NOT_FINISHED;
//        }
//    }
//    
//    @Override
//    public int countFinishedStatus(List<String> toolIds, String userId){
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        if (toolIds.size() > 0) {
//            for (String toolId : toolIds) {
//                sb.append("'" + toolId + "'");
//                i++;
//                if (i < toolIds.size()) {
//                    sb.append(",");
//                }
//            }
//        } else {
//            sb.append("''");
//        }
//        List list = this.hibernateBaseDao.find(
//                "select count(et.status) from LadsFaceteachingUserStatusTool et where et.ladsFaceteachingTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "' and et.status = '"+FULL_ATTENDANCE+"'");
//        int count = ((Long)list.get(0)).intValue();
//        return count;
//    }
//
//    @Override
//    public String getUserScore(String toolId, String userId) {
//        LadsFaceteachingUserStatusTool status = getUserStatusByToolIdAnsUserId(toolId,userId);
//        if(status!=null){
//            return status.getScore();
//        }else{
//            return null;
//        }
//    }
//    
//    @Override
//    public double getUserScore(List<String> toolIds, String userId) {
//        StringBuilder sb = new StringBuilder();
//        int i = 0;
//        if (toolIds.size() > 0) {
//            for (String toolId : toolIds) {
//                sb.append("'" + toolId + "'");
//                i++;
//                if (i < toolIds.size()) {
//                    sb.append(",");
//                }
//            }
//        } else {
//            sb.append("''");
//        }
//        List<String> list = this.hibernateBaseDao.find("select et.score from LadsFaceteachingUserStatusTool et where et.ladsFaceteachingTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "'");
//        double score = 0;
//        for (String obj : list) {
//            if (obj != null && !("".equals(obj))&& StrUtils.isNumeric(obj)) {
//                double realScore = Double.parseDouble(obj);
//                score = score + realScore;
//            }
//        }
//        return score;
//    }
//}
