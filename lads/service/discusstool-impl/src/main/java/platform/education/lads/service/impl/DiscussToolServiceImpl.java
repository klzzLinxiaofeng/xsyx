/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service.impl;

import framework.generic.dao.Order;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.lads.contants.FinishedStatus;
import platform.education.lads.contants.ToolName;
import platform.education.lads.contants.discussToolCons.AllowUpload;
import platform.education.lads.model.LadsDiscussReplyTool;
import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.model.LadsDiscussUserStatusTool;
import platform.education.lads.service.DiscussToolService;
import platform.education.lads.service.LadsDiscussReplyToolService;
import platform.education.lads.service.LadsDiscussToolService;
import platform.education.lads.service.LadsDiscussUserStatusToolService;
import platform.education.lads.service.LadsService;
import platform.education.lads.service.LadsToolLibraryService;
import platform.education.lads.service.LadsUserService;
import platform.education.lads.util.StrUtils;
import platform.education.lads.vo.GetToolCondition;
import platform.education.lads.vo.discussToolVo.LadsDiscussAttachmentResult;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyToolCondition;
import platform.education.lads.vo.discussToolVo.LadsDiscussToolCondition;
import platform.education.lads.vo.LadsUserVo;
import platform.education.lads.vo.CountFinishedStatusCondition;
import platform.education.lads.vo.discussToolVo.LadsDiscussAttachmentVo;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyVo;
import platform.education.lads.vo.discussToolVo.LadsDiscussUserStatusVo;
import platform.education.resource.utils.UUIDUtil;

/**
 * discuss工具业务器
 *
 * @author Administrator
 */
public class DiscussToolServiceImpl implements DiscussToolService {

    private LadsService ladsService;
    private LadsUserService ladsUserService;
    private LadsToolLibraryService ladsToolLibraryService;
    private LadsDiscussToolService ladsDiscussToolService;
    private LadsDiscussReplyToolService ladsDiscussReplyToolService;
    private LadsDiscussUserStatusToolService ladsDiscussUserStatusToolService;

    public void setLadsService(LadsService ladsService) {
        this.ladsService = ladsService;
    }

    public void setLadsToolLibraryService(LadsToolLibraryService ladsToolLibraryService) {
        this.ladsToolLibraryService = ladsToolLibraryService;
    }

    public void setLadsDiscussToolService(LadsDiscussToolService ladsDiscussToolService) {
        this.ladsDiscussToolService = ladsDiscussToolService;
    }

    public void setLadsUserService(LadsUserService ladsUserService) {
        this.ladsUserService = ladsUserService;
    }

    public void setLadsDiscussReplyToolService(LadsDiscussReplyToolService ladsDiscussReplyToolService) {
        this.ladsDiscussReplyToolService = ladsDiscussReplyToolService;
    }

    public void setLadsDiscussUserStatusToolService(LadsDiscussUserStatusToolService ladsDiscussUserStatusToolService) {
        this.ladsDiscussUserStatusToolService = ladsDiscussUserStatusToolService;
    }

    @Override
    public String toHtml() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toXml() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public LadsDiscussTool save(String toolId, String title, String content, String startTime, String stopTime, String discussAllowUpload) {
        LadsDiscussTool discuss = getLadsDiscussToolByToolId(toolId);
        boolean updateFlag = false;
        if (discuss == null) {
            discuss = new LadsDiscussTool();
            discuss.setCreateDate(new Date());
            discuss.setModifyDate(new Date());
            discuss.setUuid(UUIDUtil.getUUID());
            discuss.setToolLibrary(ToolName.DISCUSS_TOOL);
        } else {
            updateFlag = true;
        }
        discuss.setTitle(title);
        discuss.setContent(content);
        discuss.setToolId(toolId);
        if (discussAllowUpload != null && !"".equals(discussAllowUpload) && AllowUpload.ALLOW.equals(discussAllowUpload)) {
            discuss.setAllowUpload(AllowUpload.ALLOW);
        } else {
            discuss.setAllowUpload(AllowUpload.NOT_ALLOW);
        }
        try {
            discuss.setStartTime((startTime == null || "".equals(startTime)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime));
            discuss.setStopTime((stopTime == null || "".equals(stopTime)) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stopTime));
        } catch (ParseException ex) {
            Logger.getLogger(DiscussToolServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (updateFlag) {
            discuss = this.ladsDiscussToolService.modify(discuss);
        } else {
            discuss = this.ladsDiscussToolService.add(discuss);
        }
        return discuss;
    }

    @Override
    public LadsDiscussTool getLadsDiscussToolByToolId(String toolId) {
        LadsDiscussToolCondition dtc = new LadsDiscussToolCondition();
        dtc.setToolId(toolId);
        List<LadsDiscussTool> list = this.ladsDiscussToolService.findLadsDiscussToolByCondition(dtc, Order.desc("create_date"));
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Object[] getUserStatusList(String ldId, String toolId) {
        List<LadsUserVo> userList = this.ladsUserService.getStudyUserList(ldId);
        List<LadsDiscussUserStatusVo> voList = new ArrayList();
        LadsDiscussTool discuss = getLadsDiscussToolByToolId(toolId);
        Object[] obj = new Object[4];
        int commented = 0;
        int notComment = 0;
        int allComments = 0;
        for (LadsUserVo user : userList) {
            LadsDiscussUserStatusVo vo = new LadsDiscussUserStatusVo();
            LadsDiscussReplyToolCondition drtc = new LadsDiscussReplyToolCondition();
            drtc.setUserId(user.getUserId());
            drtc.setDiscuss(discuss.getUuid());
            int comments = this.ladsDiscussReplyToolService.count(drtc).intValue();
            LadsDiscussUserStatusTool status = getUserStatusByToolIdAndUserId(toolId, user.getUserId());
            if (status == null) {
                status = new LadsDiscussUserStatusTool();
                status.setCreateDate(new Date());
                status.setModifyDate(new Date());
                status.setScore("0");
                status.setUserId(user.getUserId());
                status.setDiscuss(discuss.getUuid());
                status.setUuid(UUIDUtil.getUUID());
                this.ladsDiscussUserStatusToolService.add(status);
            }
            vo.setRealName(user.getRealName());
            vo.setStatus(status);
            vo.setComments(comments);
            LadsDiscussReplyToolCondition adrtc = new LadsDiscussReplyToolCondition();
            adrtc.setDiscuss(discuss.getUuid());
            adrtc.setUserId(user.getUserId());
            List<LadsDiscussAttachmentResult> attachmentJsonList = this.ladsDiscussReplyToolService.findAttachMentByDiscussIdAndUserId(adrtc, null, null);
            List<LadsDiscussAttachmentVo> avoList = new ArrayList();
            for (LadsDiscussAttachmentResult ar : attachmentJsonList) {
                String jsonString = ar.getAttachment();
                String replyId = ar.getUuid();
                JSONObject json = JSONObject.fromObject(jsonString);
                JSONArray jarry = (JSONArray) json.get("attachment");
                for (int v = 0; v < jarry.size(); v++) {
                    JSONObject attJ = (JSONObject) jarry.get(v);
                    if (attJ.get("fileName") != null && attJ.get("fileId") != null) {
                        LadsDiscussAttachmentVo avo = new LadsDiscussAttachmentVo();
                        avo.setFileName((String) attJ.get("fileName"));
                        avo.setFileId((String) attJ.get("fileId"));
                        avo.setReplyId(replyId);
                        avoList.add(avo);
                    }
                }
            }
            vo.setAttachmentList(avoList);
            voList.add(vo);
            if (comments > 0) {
                commented++;
            } else {
                notComment++;
            }
            allComments = allComments + comments;
        }
        obj[0] = voList;
        obj[1] = allComments;
        obj[2] = commented;
        obj[3] = notComment;
        return obj;
    }

    @Override
    public List<LadsDiscussReplyVo> getReplyListByToolId(String toolId, Integer userId, Integer sessionUserId) {
        List<LadsDiscussReplyTool> list;
        if (userId != null) {
            list = this.getReplyByToolIdAndUserId(toolId, userId);
        } else {
            list = this.getReplyByToolId(toolId);
        }
        List<LadsDiscussReplyVo> voList = new ArrayList();
        for (LadsDiscussReplyTool dr : list) {
            LadsDiscussReplyVo rv = assembleDiscussReplyVo(dr, sessionUserId);
            voList.add(rv);
        }
        return voList;
    }

    public LadsDiscussReplyVo assembleDiscussReplyVo(LadsDiscussReplyTool dr, Integer sessionUserId) {
        LadsDiscussReplyVo rv = new LadsDiscussReplyVo();
        rv.setReply(dr);
        if (dr.getAttachment() != null && !"".equals(dr.getAttachment())) {
            JSONObject obj = JSONObject.fromObject(dr.getAttachment());
            List<LadsDiscussAttachmentVo> attList = new ArrayList();
            JSONArray array = (JSONArray) obj.get("attachment");
            for (int i = 0; i < array.size(); i++) {
                JSONObject attach = (JSONObject) array.get(i);
                LadsDiscussAttachmentVo attvo = new LadsDiscussAttachmentVo();
                attvo.setFileId((String) attach.get("fileId"));
                attvo.setFileName((String) attach.get("fileName"));
                attList.add(attvo);
            }
            rv.setAttachmentList(attList);
        }
        LadsUserVo cu = this.ladsUserService.getUser(dr.getUserId());
        if (cu != null) {
            rv.setUserName(cu.getRealName());
            if (cu.getPhoto() != null && !"".equals(cu.getPhoto())) {
                rv.setImage(cu.getPhoto());
            }
            if (sessionUserId != null && sessionUserId == cu.getUserId()) {
                rv.setCanDelete(true);
            }
        } else {
            rv.setUserName("游客");
        }
        LadsDiscussReplyToolCondition drtc = new LadsDiscussReplyToolCondition();
        drtc.setParentReply(dr.getUuid());
        List<LadsDiscussReplyTool> children = this.ladsDiscussReplyToolService.findLadsDiscussReplyToolByCondition(drtc, null, Order.asc("create_date"));
        List<LadsDiscussReplyVo> voList = new ArrayList();
        for (LadsDiscussReplyTool child : children) {
            LadsDiscussReplyVo childVo = assembleDiscussReplyVo(child, sessionUserId);
            voList.add(childVo);
        }
        rv.setChildrenReply(voList);
        return rv;
    }

    @Override
    public LadsDiscussReplyTool saveDiscussReply(String toolId, Integer userId, String content, String attachmentJson, String parentReply) {
        LadsDiscussReplyTool reply = new LadsDiscussReplyTool();
        LadsDiscussTool discuss = this.getLadsDiscussToolByToolId(toolId);
        reply.setDiscuss(discuss.getUuid());
        reply.setContent(content);
        if (userId != null) {
            reply.setUserId(userId);
        }
        if (attachmentJson != null && !"".equals(attachmentJson)) {
            reply.setAttachment(attachmentJson);
        }
        if (parentReply != null && !"".equals(parentReply)) {
            reply.setParentReply(parentReply);
        }
        reply.setUuid(UUIDUtil.getUUID());
        reply.setCreateDate(new Date());
        reply.setModifyDate(new Date());
        this.ladsDiscussReplyToolService.add(reply);
        //这边暂时的操作是只要有回复讨论主题，就建立个人状态数据并给满分
        LadsDiscussUserStatusTool status = getUserStatusByToolIdAndUserId(toolId, userId);
        if (status == null) {
            status = new LadsDiscussUserStatusTool();
            status.setCreateDate(new Date());
            status.setModifyDate(new Date());
            status.setUuid(UUIDUtil.getUUID());
            status.setUserId(userId);
            status.setDiscuss(discuss.getUuid());
            status.setScore(this.ladsService.getActivityByToolId(toolId).getScore());
            this.ladsDiscussUserStatusToolService.add(status);
        } else {
            status.setScore(this.ladsService.getActivityByToolId(toolId).getScore());
            this.ladsDiscussUserStatusToolService.modify(status);
        }
        return reply;
    }

    @Override
    public LadsDiscussUserStatusTool getUserStatusByToolIdAndUserId(String toolId, Integer userId) {
        GetToolCondition gtc = new GetToolCondition();
        gtc.setToolId(toolId);
        gtc.setUserId(userId);
        LadsDiscussUserStatusTool dust = this.ladsDiscussUserStatusToolService.findUserStatusByToolIdAndUserId(gtc);
        if (dust != null) {
            return dust;
        } else {
            return null;
        }
    }

    @Override
    public List<LadsDiscussReplyTool> getReplyByToolId(String toolId) {
        List<LadsDiscussReplyTool> replyList = this.ladsDiscussReplyToolService.findReplyListByToolId(toolId, null, Order.asc("create_date"));
        return replyList;
    }

    @Override
    public List<LadsDiscussReplyTool> getReplyByToolIdAndUserId(String toolId, Integer userId) {
        GetToolCondition gtc = new GetToolCondition();
        gtc.setToolId(toolId);
        gtc.setUserId(userId);
        List<LadsDiscussReplyTool> replyList = this.ladsDiscussReplyToolService.findReplyListByToolIdAndUserId(gtc, null, Order.asc("create_date"));
        return replyList;
    }

    @Override
    public int getFinishedStatus(String toolId, Integer userId) {
        List<LadsDiscussReplyTool> reply = getReplyByToolIdAndUserId(toolId, userId);
        if (reply.size() > 0) {
            return FinishedStatus.FINISHED;
        } else {
            return FinishedStatus.NOT_FINISHED;
        }
    }

    @Override
    public int countFinishedStatus(List<String> toolIds, Integer userId) {
        if (toolIds.size() > 0) {
            CountFinishedStatusCondition cfsc = new CountFinishedStatusCondition();
            cfsc.setToolIds(toolIds);
            cfsc.setUserId(userId);
            int count = this.ladsDiscussReplyToolService.countFinishedStatus(cfsc).intValue();
            return count;
        } else {
            return 0;
        }
    }

    @Override
    public String getUserScore(String toolId, Integer userId) {
        LadsDiscussUserStatusTool status = getUserStatusByToolIdAndUserId(toolId, userId);
        if (status != null) {
            return status.getScore();
        } else {
            return null;
        }
    }

    @Override
    public double getUserScore(List<String> toolIds, Integer userId) {
        CountFinishedStatusCondition cfsc = new CountFinishedStatusCondition();
        cfsc.setToolIds(toolIds);
        cfsc.setUserId(userId);
        List<String> list = this.ladsDiscussUserStatusToolService.findScoreByToolIdAndUserId(cfsc);
        double score = 0;
        for (String obj : list) {
            if (obj != null && !("".equals(obj)) && StrUtils.isNumeric(obj)) {
                double realScore = Double.parseDouble(obj);
                score = score + realScore;
            }
        }
        return score;
    }

    @Override
    public void deleteAttachment(String replyId, String fileId) {
        LadsDiscussReplyTool reply = this.ladsDiscussReplyToolService.findLadsDiscussReplyToolByUuid(replyId);
        JSONObject obj = JSONObject.fromObject(reply.getAttachment());
        JSONArray array = (JSONArray) obj.get("attachment");
        for (int i = 0; i < array.size(); i++) {
            JSONObject attach = (JSONObject) array.get(i);
            String afileId = (String) attach.get("fileId");
            if (afileId.equals(fileId)) {
                array.remove(attach);
                break;
            }
        }
        reply.setAttachment(obj.toString());
        this.ladsDiscussReplyToolService.modify(reply);
    }

    @Override
    public void deleteDiscussReply(String replyId) {
        LadsDiscussReplyTool reply = this.ladsDiscussReplyToolService.findLadsDiscussReplyToolByUuid(replyId);
        this.ladsDiscussReplyToolService.remove(reply);
    }

    @Override
    public String uploadAttachment(File file, String uploadFileName, String toolId) throws IOException {
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
        return null;
//            }
//        }
    }
}
