package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Eternityhua
 * @create 2020-12-06 15:30
 */
public class HomeToSchoolFeedbackContent implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 反馈内容
     */
    private String content;
//    private String remark;


    /**
     * 家校反馈表id
     */
    private Integer feedbackId;


    /**
     * 学校id
     */
    private Integer schoolId;

    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date createDate;

    @Override
    public String toString() {
        return "HomeToSchoolFeedbackContent{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", feedbackId=" + feedbackId +
                ", schoolId=" + schoolId +
                ", createDate=" + createDate +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public Integer getKey()  {
        return this.id;
    }

}
