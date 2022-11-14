package platform.education.lads.vo.discussToolVo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import platform.education.lads.model.LadsDiscussReplyTool;

/**
 *
 * @author 罗志明
 */
public class LadsDiscussReplyVo {

    private LadsDiscussReplyTool reply;
    private String userName;
    private String image;
    private List<LadsDiscussAttachmentVo> attachmentList;
    private List<LadsDiscussReplyVo> childrenReply;
    private boolean canDelete;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LadsDiscussReplyTool getReply() {
        return reply;
    }

    public void setReply(LadsDiscussReplyTool reply) {
        this.reply = reply;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<LadsDiscussAttachmentVo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<LadsDiscussAttachmentVo> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<LadsDiscussReplyVo> getChildrenReply() {
        return childrenReply;
    }

    public void setChildrenReply(List<LadsDiscussReplyVo> childrenReply) {
        this.childrenReply = childrenReply;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public void setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
    }
    
    
}
