package platform.education.service.vo;

import platform.education.service.model.InSchoolActivity;

import java.util.List;

/**
 * InSchoolActivityAndApprovalVo
 *
 * @author AutoCreate
 */
public class InSchoolActivityAndApprovalVo extends InSchoolActivity {
    private static final long serialVersionUID = 1L;
    private String roomName;
    private Integer status;
    private String approvalName;
    private String approvalAlias;
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

    public String getApprovalName() {
        return approvalName;
    }

    public void setApprovalName(String approvalName) {
        this.approvalName = approvalName;
    }

    public String getApprovalAlias() {
        return approvalAlias;
    }

    public void setApprovalAlias(String approvalAlias) {
        this.approvalAlias = approvalAlias;
    }

    public List<PersonVo> getParticipantList() {
        return participantList;
    }

    public void setParticipantList(List<PersonVo> participantList) {
        this.participantList = participantList;
    }
}