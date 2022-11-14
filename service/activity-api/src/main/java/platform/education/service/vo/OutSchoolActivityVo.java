package platform.education.service.vo;

import platform.education.service.model.OutSchoolActivity;

/**
 * OutSchoolActivity
 *
 * @author AutoCreate
 */
public class OutSchoolActivityVo extends OutSchoolActivity {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String feedback;

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