package platform.education.service.vo;

import platform.education.service.model.InSchoolActivity;

import java.util.List;

/**
 * InSchoolActivity
 *
 * @author AutoCreate
 */
public class InSchoolActivityParticipantVo extends InSchoolActivity {
    private static final long serialVersionUID = 1L;
    private String roomName;
    private Integer status;
    private String feedback;
    private List<PersonVo> participantList;

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

    public List<PersonVo> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<PersonVo> participantList) {
        this.participantList = participantList;
    }
}