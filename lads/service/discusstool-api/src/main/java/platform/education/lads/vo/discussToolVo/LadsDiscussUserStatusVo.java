package platform.education.lads.vo.discussToolVo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import platform.education.lads.model.LadsDiscussUserStatusTool;

/**
 *
 * @author Administrator
 */
public class LadsDiscussUserStatusVo {

    private LadsDiscussUserStatusTool status;
    private int comments;
    private String realName;
    private List<LadsDiscussAttachmentVo> attachmentList;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public LadsDiscussUserStatusTool getStatus() {
        return status;
    }

    public void setStatus(LadsDiscussUserStatusTool status) {
        this.status = status;
    }

    public List<LadsDiscussAttachmentVo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<LadsDiscussAttachmentVo> attachmentList) {
        this.attachmentList = attachmentList;
    }
}
