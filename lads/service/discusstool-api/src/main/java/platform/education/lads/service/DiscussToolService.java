/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import platform.education.lads.model.LadsDiscussReplyTool;
import platform.education.lads.model.LadsDiscussTool;
import platform.education.lads.model.LadsDiscussUserStatusTool;
import platform.education.lads.vo.discussToolVo.LadsDiscussReplyVo;

/**
 *
 * @author Administrator
 */
public interface DiscussToolService extends ToolService {

    public LadsDiscussTool save(String toolId, String title,String content,String startTime, String stopTime,String discussAllowUpload);

    public LadsDiscussTool getLadsDiscussToolByToolId(String toolId);

    public LadsDiscussReplyTool saveDiscussReply(String toolId, Integer userId,String content, String attachmentJson, String parentReply);

    public List<LadsDiscussReplyVo> getReplyListByToolId(String toolId,Integer userId,Integer sessionUserId);

    public Object[] getUserStatusList(String ldId, String toolId);
    
    public List<LadsDiscussReplyTool> getReplyByToolId(String toolId);
    
    public LadsDiscussUserStatusTool getUserStatusByToolIdAndUserId(String discussId,Integer userId);
    
    public List<LadsDiscussReplyTool> getReplyByToolIdAndUserId(String toolId, Integer userId);
    
    public String uploadAttachment(File file, String uploadFileName, String toolId) throws IOException;
    
    public void deleteAttachment(String replyId, String fileId);
    
    public void deleteDiscussReply(String replyId);
}
