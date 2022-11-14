package platform.education.paper.vo;

import platform.education.paper.model.Task;

import java.util.Date;

/**
 * Task
 *
 * @author AutoCreate
 */
public class TaskVo extends Task {
    private static final long serialVersionUID = 1L;

    private Integer examId;

    private Integer teamId;

    private String subjectCode;

    private String subjectName;

    private Integer state;

    private String xepFileId;

    private String difficultyString;

    private Float score;

    private Integer publishFlag;

    private Date finishedDate;

    private Date createDate;

    private String publisherName;

    private Integer id;

    private Integer userCount;

    private Integer finishedCount;

    private String title;

    private String percent;

    public String getDifficultyString() {
        return difficultyString;
    }

    public void setDifficultyString(String difficultyString) {
        this.difficultyString = difficultyString;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }


    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }


    public Integer getPublishFlag() {
        return publishFlag;
    }

    public void setPublishFlag(Integer publishFlag) {
        this.publishFlag = publishFlag;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getXepFileId() {
        return xepFileId;
    }

    public void setXepFileId(String xepFileId) {
        this.xepFileId = xepFileId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }


    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String getPublisherName() {
        return publisherName;
    }

    @Override
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getUserCount() {
        return userCount;
    }

    @Override
    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    @Override
    public Integer getFinishedCount() {
        return finishedCount;
    }

    @Override
    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}