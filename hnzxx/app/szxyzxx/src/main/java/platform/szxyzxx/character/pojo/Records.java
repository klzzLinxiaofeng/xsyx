package platform.szxyzxx.character.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Records {
    private Integer id;
    /*
     * 学生id
     */
    private Integer studentId;
    /*
     *  学生name
     */
    private String stuName;

    private Integer evaluationId;
    private String evName;
    private Integer teacherId;
    private String teaName;
    private  String pictureId;
    private  String  pictureUrl;
    private Integer score;
    private Integer isDelete;
    private String message;
    private String voice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    private List<Evaluation> evaluationList;

    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public Records(){}

    public Records(Integer id, Integer studentId, String stuName, Integer evaluationId, String evName, Integer teacherId, String teaName, String pictureId, String pictureUrl, Integer score, Integer isDelete, String message, String voice, Date createTime, List<Evaluation> evaluationList) {
        this.id = id;
        this.studentId = studentId;
        this.stuName = stuName;
        this.evaluationId = evaluationId;
        this.evName = evName;
        this.teacherId = teacherId;
        this.teaName = teaName;
        this.pictureId = pictureId;
        this.pictureUrl = pictureUrl;
        this.score = score;
        this.isDelete = isDelete;
        this.message = message;
        this.voice = voice;
        this.createTime = createTime;
        this.evaluationList = evaluationList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
