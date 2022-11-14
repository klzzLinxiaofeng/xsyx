package platform.education.service.vo;

import platform.education.service.model.OutSchoolActivity;

import java.util.List;

/**
 * OutSchoolActivity
 *
 * @author AutoCreate
 */
public class OutSchoolActivityPictureVo extends OutSchoolActivity {
    private static final long serialVersionUID = 1L;
    private Integer status;
    private String feedback;
    private String accessoryName;
    private String accessoryUrl;
    private String summary;
    private List<PictureVo> pictureVoList;

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

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<PictureVo> getPictureVoList() {
        return pictureVoList;
    }

    public void setPictureVoList(List<PictureVo> pictureVoList) {
        this.pictureVoList = pictureVoList;
    }
}