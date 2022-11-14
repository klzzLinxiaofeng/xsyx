package platform.education.service.vo;

import platform.education.service.model.InSchoolActivity;

/**
 * InSchoolActivity
 *
 * @author AutoCreate
 */
public class InSchoolActivityVo extends InSchoolActivity {
    private static final long serialVersionUID = 1L;
    private String roomName;
    private Integer status;
    private String feedback;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}