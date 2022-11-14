package platform.education.lads.service.impl;

///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.gzxtjy.lads.service.impl;
//
//import com.gzxtjy.common.dao.hibernate.HibernateBaseDao;
//import com.gzxtjy.common.log.LoggerUtil;
//import com.gzxtjy.common.util.FileMd5Util;
//import com.gzxtjy.common.util.FtpUtils;
//import com.gzxtjy.common.util.StrUtils;
//import com.gzxtjy.datacenter.entities.centeruser.CenterUser;
//import com.gzxtjy.lads.constants.FinishedStatus;
//import com.gzxtjy.lads.constants.ToolName;
//import com.gzxtjy.lads.entities.LadsPowerpointTool;
//import com.gzxtjy.lads.entities.LadsPowerpointUserStatusTool;
//import com.gzxtjy.lads.entities.LadsToolLibrary;
//import com.gzxtjy.lads.service.LadsService;
//import com.gzxtjy.lads.service.PowerPointToolService;
//import com.gzxtjy.lads.vo.LadsUserVo;
//import com.gzxtjy.lads.vo.powerpointToolVo.LadsPowerPointUserStatusVo;
//import com.gzxtjy.portal.session.constants.SessionConstants;
//import com.gzxtjy.resources.constants.ResourceConverStatus;
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Administrator
// */
//@Service("powerPointToolServiceImpl")
//@Transactional(rollbackFor = {Exception.class})
//public class PowerPointToolServiceImpl implements PowerPointToolService {
//
//    public static final String STUDYED = "已学";
//    public static final String NOT_STUDY = "未学";
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
//    public int getFinishedStatus(String toolId, String userId) {
//       LadsPowerpointUserStatusTool status = getPowerPointUserStautsByToolIdAndUserId(toolId, userId);
//        if (status != null) {
//            if (STUDYED.equals(status.getStatus())) {
//                return FinishedStatus.FINISHED;
//            } else {
//                return FinishedStatus.NOT_FINISHED;
//            }
//        } else {
//            return FinishedStatus.NOT_FINISHED;
//        }
//    }
//
//    @Override
//    public int countFinishedStatus(List<String> toolIds, String userId) {
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
//                "select count(et.status) from LadsPowerpointUserStatusTool et where et.ladsPowerpointTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "' and et.status = " + FinishedStatus.FINISHED);
//        int count = ((Long) list.get(0)).intValue();
//        return count;
//    }
//
//    @Override
//    public String getUserScore(String toolId, String userId) {
//        LadsPowerpointUserStatusTool status = getPowerPointUserStautsByToolIdAndUserId(toolId, userId);
//        if (status != null) {
//            return status.getScore();
//        } else {
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
//        List<String> list = this.hibernateBaseDao.find("select et.score from LadsPowerpointUserStatusTool et where et.ladsPowerpointTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "'");
//        double score = 0;
//        for (String obj : list) {
//            if (obj != null && !("".equals(obj)) && StrUtils.isNumeric(obj)) {
//                double realScore = Double.parseDouble(obj);
//                score = score + realScore;
//            }
//        }
//        return score;
//    }
//
//    @Override
//    public LadsPowerpointTool getPowerpointByToolId(String toolId) {
//        List list = this.hibernateBaseDao.find("from LadsPowerpointTool et where et.toolId = '" + toolId + "' order by et.createTime desc");
//        if (list.size() > 0) {
//            return (LadsPowerpointTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public LadsPowerpointTool save(String toolId, String title, String fileId) {
//        LadsPowerpointTool ppt = getPowerpointByToolId(toolId);
//        if (ppt == null) {
//            ppt = new LadsPowerpointTool();
//            ppt.setCreateTime(new Date());
//            ppt.setLadsToolLibrary(this.hibernateBaseDao.findUniqueBy(LadsToolLibrary.class, "toolName", ToolName.POWER_POINT_TOOL));
//        }
//        ppt.setTitle(title);
//        ppt.setToolId(toolId);
//        ppt.setResFile(this.hibernateBaseDao.findUniqueBy(com.gzxtjy.resources.entities.File.class, "id", fileId));
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(ppt);
//        return ppt;
//    }
//
//    @Override
//    public LadsPowerpointUserStatusTool getPowerPointUserStautsByToolIdAndUserId(String toolId, String userId) {
//        List list = this.hibernateBaseDao.find("from LadsPowerpointUserStatusTool et where et.ladsPowerpointTool.toolId = '" + toolId + "' and et.userId = '" + userId + "'");
//        if (list.size() > 0) {
//            return (LadsPowerpointUserStatusTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public LadsPowerpointUserStatusTool saveUserStatus(String toolId, String userId, String powerPointScore) {
//        LadsPowerpointUserStatusTool status = this.getPowerPointUserStautsByToolIdAndUserId(toolId, userId);
//        if (status == null) {
//            status = new LadsPowerpointUserStatusTool();
//            status.setCreateTime(new Date());
//            status.setLadsPowerpointTool(getPowerpointByToolId(toolId));
//            status.setStatus(STUDYED);
//            status.setStudyTime(1);
//            status.setScore(powerPointScore == null || "".equals(powerPointScore) ? "0" : powerPointScore);
//            status.setUserId(userId);
//        } else {
//            status.setScore(powerPointScore == null || "".equals(powerPointScore) ? "0" : powerPointScore);
//            status.setStatus(STUDYED);
//            status.setStudyTime(status.getStudyTime() + 1);
//        }
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(status);
//        return status;
//    }
//
//    @Override
//    public Object[] getUserStatusList(String sysType, String ldId, String toolId) {
//        Object[] obj = new Object[3];
//        int finish = 0;
//        int notFinish = 0;
//        List<LadsUserVo> userList = this.ladsServiceImpl.getStudyUserList(sysType, ldId);
//        List<LadsPowerPointUserStatusVo> voList = new ArrayList();
//        for (LadsUserVo user : userList) {
//            LadsPowerPointUserStatusVo vo = new LadsPowerPointUserStatusVo();
//            vo.setRealName(user.getRealName());
//            vo.setUserId(user.getUserId());
//            LadsPowerpointUserStatusTool status = this.getPowerPointUserStautsByToolIdAndUserId(toolId, user.getUserId());
//            if (status != null) {
//                vo.setStatus(status);
//                if (status.getStatus().equals(STUDYED)) {
//                    finish++;
//                }
//            }
//            voList.add(vo);
//        }
//        notFinish = voList.size() - finish;
//        obj[0] = voList;
//        obj[1] = finish;
//        obj[2] = notFinish;
//        return obj;
//    }
//
//    @Override
//    public String uploadPpt(File file, String uploadFileName, String toolId) throws IOException {
//        String md5Code = FileMd5Util.getFileMd5String(file);
//        com.gzxtjy.resources.entities.File entityFile = this.hibernateBaseDao.findUniqueBy(com.gzxtjy.resources.entities.File.class, "md5Code", md5Code);
//        Date uploadDateTime = new Date();
//        if (entityFile != null) {
//            LoggerUtil.info(this, "系统已存在相同的文件,不用重新上传,直接使用实体文件");
//            return entityFile.getId();
//        } else {
//            FtpUtils ftp = new FtpUtils();
//            String suffix = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1, uploadFileName.length());
//            String fileName = FtpUtils.getRandomNameByTime();
//            String path = "/resources/lads/ppt/" + toolId + "/";
//            if (ftp.uploadFile(path, fileName + "." + suffix, file)) {
//                entityFile = new com.gzxtjy.resources.entities.File();
//                entityFile.setHttpUrl(ftp.getFtpCode() + "|" + ftp.getUploadPath());
//                //lads系统默认使用参考库用户作为上传用户
//                entityFile.setCenterUser(this.hibernateBaseDao.findUniqueBy(CenterUser.class, "userId", SessionConstants.SYS_ADMIN_ID));
//                entityFile.setSuffix(suffix);
//                entityFile.setConverStatus(ResourceConverStatus.NOT_CONVERED);
//                entityFile.setUploadTime(uploadDateTime);
//                entityFile.setFileSize(ftp.getUploadFileSize());
//                entityFile.setMd5Code(md5Code);
//                entityFile.setFileName(fileName);
//                entityFile.setAbsolutePath(ftp.getAbsolutePath());
//                this.hibernateBaseDao.save(entityFile);
//                return entityFile.getId();
//            } else {
//                return null;
//            }
//        }
//    }
//}
