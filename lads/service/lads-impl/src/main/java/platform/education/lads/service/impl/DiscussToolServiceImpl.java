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
//import com.gzxtjy.lads.constants.discussToolCons.AllowUpload;
//import com.gzxtjy.lads.entities.LadsDiscussReplyTool;
//import com.gzxtjy.lads.entities.LadsDiscussTool;
//import com.gzxtjy.lads.entities.LadsDiscussUserStatusTool;
//import com.gzxtjy.lads.entities.LadsToolLibrary;
//import com.gzxtjy.lads.service.DiscussToolService;
//import com.gzxtjy.lads.service.LadsService;
//import com.gzxtjy.lads.vo.LadsUserVo;
//import com.gzxtjy.lads.vo.discussToolVo.LadsDiscussAttachmentVo;
//import com.gzxtjy.lads.vo.discussToolVo.LadsDiscussReplyVo;
//import com.gzxtjy.lads.vo.discussToolVo.LadsDiscussUserStatusVo;
//import com.gzxtjy.portal.session.constants.SessionConstants;
//import com.gzxtjy.resources.constants.ResourceConverStatus;
//import com.gzxtjy.resources.util.DocPathUtil;
//import java.io.File;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.Resource;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author Administrator
// */
//@Service("discussToolServiceImpl")
//@Transactional(rollbackFor = {Exception.class})
//public class DiscussToolServiceImpl implements DiscussToolService {
//
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
//    public LadsDiscussTool save(String toolId, String title, String content, String startTime, String stopTime, String discussAllowUpload) {
//        LadsDiscussTool discuss = getLadsDiscussToolByToolId(toolId);
//        if (discuss == null) {
//            discuss = new LadsDiscussTool();
//            discuss.setCreateTime(new Date());
//            discuss.setLadsToolLibrary(this.hibernateBaseDao.findUniqueBy(LadsToolLibrary.class, "toolName", ToolName.DISCUSS_TOOL));
//        }
//        discuss.setTitle(title);
//        discuss.setContent(content);
//        discuss.setToolId(toolId);
//        if (discussAllowUpload != null && !"".equals(discussAllowUpload) && AllowUpload.ALLOW.equals(discussAllowUpload)) {
//            discuss.setAllowUpload(AllowUpload.ALLOW);
//        } else {
//            discuss.setAllowUpload(AllowUpload.NOT_ALLOW);
//        }
//        try {
//            discuss.setStartTime((startTime == null || "".equals(startTime)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime));
//            discuss.setStopTime((stopTime == null || "".equals(stopTime)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stopTime));
//        } catch (ParseException ex) {
//            Logger.getLogger(DiscussToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(discuss);
//        return discuss;
//    }
//
//    @Override
//    public LadsDiscussTool getLadsDiscussToolByToolId(String toolId) {
//        List list = this.hibernateBaseDao.find("from LadsDiscussTool et where et.toolId = '" + toolId + "' order by et.createTime desc");
//        if (list.size() > 0) {
//            return (LadsDiscussTool) list.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Object[] getUserStatusList(String sysType, String ldId, String toolId) {
//        List<LadsUserVo> userList = this.ladsServiceImpl.getStudyUserList(sysType, ldId);
//        List<LadsDiscussUserStatusVo> voList = new ArrayList();
//        LadsDiscussTool discuss = getLadsDiscussToolByToolId(toolId);
//        Object[] obj = new Object[4];
//        int commented = 0;
//        int notComment = 0;
//        int allComments = 0;
//        for (LadsUserVo user : userList) {
//            LadsDiscussUserStatusVo vo = new LadsDiscussUserStatusVo();
//            int comments = Integer.parseInt(this.hibernateBaseDao.find("select count(*) from LadsDiscussReplyTool dr where dr.ladsDiscussTool.id='" + discuss.getId() + "' and dr.userId = '" + user.getUserId() + "'").get(0).toString());
//            LadsDiscussUserStatusTool status = getUserStatusByToolIdAndUserId(toolId, user.getUserId());
//            if (status == null) {
//                status = new LadsDiscussUserStatusTool();
//                status.setCreateTime(new Date());
//                status.setScore("0");
//                status.setUserId(user.getUserId());
//                status.setLadsDiscussTool(discuss);
//                this.hibernateBaseDao.save(status);
//            }
//            vo.setRealName(user.getRealName());
//            vo.setStatus(status);
//            vo.setComments(comments);
//            List<Object[]> attachmentJsonList = this.hibernateBaseDao.find("select dr.id,dr.attachment from LadsDiscussReplyTool dr where dr.ladsDiscussTool.id='" + discuss.getId() + "' and dr.userId = '" + user.getUserId() + "' and dr.attachment <> null order by dr.createTime desc");
//            List<LadsDiscussAttachmentVo> avoList = new ArrayList();
//            for (Object[] objArray : attachmentJsonList) {
//                String jsonString = (String) objArray[1];
//                String replyId = (String) objArray[0];
//                JSONObject json = JSONObject.fromObject(jsonString);
//                JSONArray jarry = (JSONArray) json.get("attachment");
//                for (int v = 0; v < jarry.size(); v++) {
//                    JSONObject attJ = (JSONObject) jarry.get(v);
//                    if (attJ.get("fileName") != null && attJ.get("fileId") != null) {
//                        LadsDiscussAttachmentVo avo = new LadsDiscussAttachmentVo();
//                        avo.setFileName((String) attJ.get("fileName"));
//                        avo.setFileId((String) attJ.get("fileId"));
//                        avo.setReplyId(replyId);
//                        avoList.add(avo);
//                    }
//                }
//            }
//            vo.setAttachmentList(avoList);
//            voList.add(vo);
//            if (comments > 0) {
//                commented++;
//            } else {
//                notComment++;
//            }
//            allComments = allComments + comments;
//        }
//        obj[0] = voList;
//        obj[1] = allComments;
//        obj[2] = commented;
//        obj[3] = notComment;
//        return obj;
//    }
//
//    @Override
//    public List<LadsDiscussReplyVo> getReplyListByToolId(String sysType, String toolId, String userId) {
//        List<LadsDiscussReplyTool> list;
//        if (userId != null && !"".equals(userId)) {
//            list = this.getReplyByToolIdAndUserId(toolId, userId);
//        } else {
//            list = this.getReplyByToolId(toolId);
//        }
//        List<LadsDiscussReplyVo> voList = new ArrayList();
//        for (LadsDiscussReplyTool dr : list) {
//            LadsDiscussReplyVo rv = assembleDiscussReplyVo(dr, sysType);
//            voList.add(rv);
//        }
//        return voList;
//    }
//
//    public LadsDiscussReplyVo assembleDiscussReplyVo(LadsDiscussReplyTool dr, String sysType) {
//        LadsDiscussReplyVo rv = new LadsDiscussReplyVo();
//        rv.setReply(dr);
//        if (dr.getAttachment() != null && !"".equals(dr.getAttachment())) {
//            JSONObject obj = JSONObject.fromObject(dr.getAttachment());
//            List<LadsDiscussAttachmentVo> attList = new ArrayList();
//            JSONArray array = (JSONArray) obj.get("attachment");
//            for (int i = 0; i < array.size(); i++) {
//                JSONObject attach = (JSONObject) array.get(i);
//                LadsDiscussAttachmentVo attvo = new LadsDiscussAttachmentVo();
//                attvo.setFileId((String) attach.get("fileId"));
//                attvo.setFileName((String) attach.get("fileName"));
//                attList.add(attvo);
//            }
//            rv.setAttachmentList(attList);
//        }
//        LadsUserVo cu = this.ladsServiceImpl.getUser(sysType, dr.getUserId());
//        if (cu != null) {
//            rv.setUserName(cu.getRealName());
//            if (cu.getPhoto() != null && !"".equals(cu.getPhoto())) {
//                rv.setImage(DocPathUtil.converHttpUrl(cu.getPhoto()));
//            } else {
//                rv.setImage("/image/login/noPhoto.jpg");
//            }
//            String sessionUserId = ladsServiceImpl.getEmbedUserId(sysType);
//            if (sessionUserId != null && !"".equals(sessionUserId) && sessionUserId.equals(cu.getUserId())) {
//                rv.setCanDelete(true);
//            }
//        } else {
//            rv.setUserName("游客");
//            rv.setImage("/image/login/noPhoto.jpg");
//        }
//        List<LadsDiscussReplyTool> children = this.hibernateBaseDao.find("from LadsDiscussReplyTool r where r.ladsDiscussReplyTool.id='" + dr.getId() + "' order by r.createTime asc");
//        List<LadsDiscussReplyVo> voList = new ArrayList();
//        for (LadsDiscussReplyTool child : children) {
//            LadsDiscussReplyVo childVo = assembleDiscussReplyVo(child, sysType);
//            voList.add(childVo);
//        }
//        rv.setChildrenReply(voList);
//        return rv;
//    }
//
//    @Override
//    public LadsDiscussReplyTool saveDiscussReply(String toolId, String userId,String sysType ,String content, String attachmentJson, String parentReply) {
//        LadsDiscussReplyTool reply = new LadsDiscussReplyTool();
//        LadsDiscussTool discuss = this.getLadsDiscussToolByToolId(toolId);
//        reply.setLadsDiscussTool(discuss);
//        reply.setContent(content);
//        //如果userId为空,获取当前系统登录用户的id
//        if(userId==null||"".equals(userId)||"null".equals(userId)){
//            userId = this.ladsServiceImpl.getEmbedUserId(sysType);
//        }
//        reply.setUserId(userId);
//        if (attachmentJson != null && !"".equals(attachmentJson)) {
//            reply.setAttachment(attachmentJson);
//        }
//        if (parentReply != null && !"".equals(parentReply)) {
//            reply.setLadsDiscussReplyTool(this.hibernateBaseDao.findUniqueBy(LadsDiscussReplyTool.class, "id", parentReply));
//        }
//        reply.setCreateTime(new Date());
//        this.hibernateBaseDao.save(reply);
//        //这边暂时的操作是只要有回复讨论主题，就建立个人状态数据并给满分
//        LadsDiscussUserStatusTool status = getUserStatusByToolIdAndUserId(toolId, userId);
//        if (status == null) {
//            status = new LadsDiscussUserStatusTool();
//            status.setCreateTime(new Date());
//            status.setUserId(userId);
//            status.setLadsDiscussTool(discuss);
//        }
//        status.setScore(this.ladsServiceImpl.getActivityByToolId(toolId).getScore());
//        this.hibernateBaseDao.getHibernateTemplate().saveOrUpdate(status);
//        return reply;
//    }
//
//    @Override
//    public LadsDiscussUserStatusTool getUserStatusByToolIdAndUserId(String toolId, String userId) {
//        List<LadsDiscussUserStatusTool> statusList = this.hibernateBaseDao.find("from LadsDiscussUserStatusTool ust where ust.ladsDiscussTool.toolId='" + toolId + "' and ust.userId = '" + userId + "'");
//        if (statusList.size() > 0) {
//            return statusList.get(0);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<LadsDiscussReplyTool> getReplyByToolId(String toolId) {
//        List<LadsDiscussReplyTool> replyList = this.hibernateBaseDao.find("from LadsDiscussReplyTool dr where dr.ladsDiscussReplyTool=null and dr.ladsDiscussTool.toolId = '" + toolId + "' order by dr.createTime asc");
//        return replyList;
//    }
//
//    @Override
//    public List<LadsDiscussReplyTool> getReplyByToolIdAndUserId(String toolId, String userId) {
//        List<LadsDiscussReplyTool> replyList = this.hibernateBaseDao.find("from LadsDiscussReplyTool dr where dr.ladsDiscussTool.toolId = '" + toolId + "' and dr.userId = '" + userId + "' order by dr.createTime asc");
//        return replyList;
//    }
//
//    @Override
//    public int getFinishedStatus(String toolId, String userId) {
//        List<LadsDiscussReplyTool> reply = getReplyByToolIdAndUserId(toolId, userId);
//        if (reply.size() > 0) {
//            return FinishedStatus.FINISHED;
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
//                "select count(*) from LadsDiscussReplyTool et where et.ladsDiscussTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "'");
//        int count = ((Long) list.get(0)).intValue();
//        return count;
//    }
//
//    @Override
//    public String getUserScore(String toolId, String userId) {
//        LadsDiscussUserStatusTool status = getUserStatusByToolIdAndUserId(toolId, userId);
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
//        List<String> list = this.hibernateBaseDao.find("select et.score from LadsDiscussUserStatusTool et where et.ladsDiscussTool.toolId in (" + sb.toString() + ") and et.userId = '" + userId + "'");
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
//    public void deleteAttachment(String replyId, String fileId) {
//        LadsDiscussReplyTool reply = this.hibernateBaseDao.findUniqueBy(LadsDiscussReplyTool.class, "id", replyId);
//        JSONObject obj = JSONObject.fromObject(reply.getAttachment());
//        JSONArray array = (JSONArray) obj.get("attachment");
//        for (int i = 0; i < array.size(); i++) {
//            JSONObject attach = (JSONObject) array.get(i);
//            String afileId = (String) attach.get("fileId");
//            if (afileId.equals(fileId)) {
//                array.remove(attach);
//                break;
//            }
//        }
//        reply.setAttachment(obj.toString());
//        this.hibernateBaseDao.update(reply);
//    }
//
//    @Override
//    public void deleteDiscussReply(String replyId) {
//        this.hibernateBaseDao.executeUpdate("delete from LadsDiscussReplyTool dr where dr.id='" + replyId + "'");
//    }
//
//    @Override
//    public String uploadAttachment(File file, String uploadFileName, String toolId) throws IOException {
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
//            String path = "/resources/lads/discuss/attachment/" + toolId + "/";
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
