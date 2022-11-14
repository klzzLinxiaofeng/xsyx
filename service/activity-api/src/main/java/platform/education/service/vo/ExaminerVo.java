package platform.education.service.vo;

import java.io.Serializable;
import java.util.Date;

public class ExaminerVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer sender;
    private Integer receiver;
    private Integer status;
    private Date startTime;
    private Date endTime;
    private String description;
    private Date createDate;
    private String senderName;
    private String receiverName;
    private String feedback;
    private Integer daikeId ;
    private String daikeName;

    public Integer getDaikeId() {
        return daikeId;
    }

    public void setDaikeId(Integer daikeId) {
        this.daikeId = daikeId;
    }

    public String getDaikeName() {
        return daikeName;
    }

    public void setDaikeName(String daikeName) {
        this.daikeName = daikeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSender() {
        return sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
