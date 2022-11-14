package platform.szxyzxx.dy.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 德育-优秀班主任
 * @TableName dy_excellent_tm
 */
public class DyExcellentTm implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 申请人
     */
    private Integer userId;

    /**
     * 申请人姓名
     */
    private String userName;

    /**
     * 申请人管理的班级
     */
    private String userTeam;

    /**
     * 上传的工作材料名字
     */
    private String workDataName;

    /**
     * 上传的工作材料路径
     */
    private String workDataPath;

    /**
     * 工作材料得分
     */
    private Integer workDataScore;

    /**
     * 班级量化得分
     */
    private Integer teamScore;

    /**
     * 学生评教得分
     */
    private Integer studentEvaluateScore;

    /**
     * 总分
     */
    private Integer sumScore;

    /**
     * 初审状态（0：待审批，1：通过，2：未通过）
     */
    private Byte firstState;

    /**
     * 终审状态（0：待审批，1：通过，2：未通过）
     */
    private Byte finalState;

    /**
     * 学期
     */
    private String xq;

    /**
     * 学年
     */
    private String year;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 初审审批时间
     */
    private Date firstHandleTime;

    /**
     * 初审人
     */
    private Integer firstHandleUserId;

    /**
     * 初审人姓名
     */
    private String firstHandleUserName;

    /**
     * 终审审批时间
     */
    private Date finalHandleTime;

    /**
     * 终审人
     */
    private Integer finalHandleUserId;

    /**
     * 终审人姓名
     */
    private String finalHandleUserName;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 申请人
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 申请人
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 申请人姓名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 申请人姓名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 申请人管理的班级
     */
    public String getUserTeam() {
        return userTeam;
    }

    /**
     * 申请人管理的班级
     */
    public void setUserTeam(String userTeam) {
        this.userTeam = userTeam;
    }

    /**
     * 上传的工作材料名字
     */
    public String getWorkDataName() {
        return workDataName;
    }

    /**
     * 上传的工作材料名字
     */
    public void setWorkDataName(String workDataName) {
        this.workDataName = workDataName;
    }

    /**
     * 上传的工作材料路径
     */
    public String getWorkDataPath() {
        return workDataPath;
    }

    /**
     * 上传的工作材料路径
     */
    public void setWorkDataPath(String workDataPath) {
        this.workDataPath = workDataPath;
    }

    /**
     * 工作材料得分
     */
    public Integer getWorkDataScore() {
        return workDataScore;
    }

    /**
     * 工作材料得分
     */
    public void setWorkDataScore(Integer workDataScore) {
        this.workDataScore = workDataScore;
    }

    /**
     * 班级量化得分
     */
    public Integer getTeamScore() {
        return teamScore;
    }

    /**
     * 班级量化得分
     */
    public void setTeamScore(Integer teamScore) {
        this.teamScore = teamScore;
    }

    /**
     * 学生评教得分
     */
    public Integer getStudentEvaluateScore() {
        return studentEvaluateScore;
    }

    /**
     * 学生评教得分
     */
    public void setStudentEvaluateScore(Integer studentEvaluateScore) {
        this.studentEvaluateScore = studentEvaluateScore;
    }

    /**
     * 总分
     */
    public Integer getSumScore() {
        return sumScore;
    }

    /**
     * 总分
     */
    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    /**
     * 初审状态（0：待审批，1：通过，2：未通过）
     */
    public Byte getFirstState() {
        return firstState;
    }

    /**
     * 初审状态（0：待审批，1：通过，2：未通过）
     */
    public void setFirstState(Byte firstState) {
        this.firstState = firstState;
    }

    /**
     * 终审状态（0：待审批，1：通过，2：未通过）
     */
    public Byte getFinalState() {
        return finalState;
    }

    /**
     * 终审状态（0：待审批，1：通过，2：未通过）
     */
    public void setFinalState(Byte finalState) {
        this.finalState = finalState;
    }

    /**
     * 学期
     */
    public String getXq() {
        return xq;
    }

    /**
     * 学期
     */
    public void setXq(String xq) {
        this.xq = xq;
    }

    /**
     * 学年
     */
    public String getYear() {
        return year;
    }

    /**
     * 学年
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 初审审批时间
     */
    public Date getFirstHandleTime() {
        return firstHandleTime;
    }

    /**
     * 初审审批时间
     */
    public void setFirstHandleTime(Date firstHandleTime) {
        this.firstHandleTime = firstHandleTime;
    }

    /**
     * 初审人
     */
    public Integer getFirstHandleUserId() {
        return firstHandleUserId;
    }

    /**
     * 初审人
     */
    public void setFirstHandleUserId(Integer firstHandleUserId) {
        this.firstHandleUserId = firstHandleUserId;
    }

    /**
     * 初审人姓名
     */
    public String getFirstHandleUserName() {
        return firstHandleUserName;
    }

    /**
     * 初审人姓名
     */
    public void setFirstHandleUserName(String firstHandleUserName) {
        this.firstHandleUserName = firstHandleUserName;
    }

    /**
     * 终审审批时间
     */
    public Date getFinalHandleTime() {
        return finalHandleTime;
    }

    /**
     * 终审审批时间
     */
    public void setFinalHandleTime(Date finalHandleTime) {
        this.finalHandleTime = finalHandleTime;
    }

    /**
     * 终审人
     */
    public Integer getFinalHandleUserId() {
        return finalHandleUserId;
    }

    /**
     * 终审人
     */
    public void setFinalHandleUserId(Integer finalHandleUserId) {
        this.finalHandleUserId = finalHandleUserId;
    }

    /**
     * 终审人姓名
     */
    public String getFinalHandleUserName() {
        return finalHandleUserName;
    }

    /**
     * 终审人姓名
     */
    public void setFinalHandleUserName(String finalHandleUserName) {
        this.finalHandleUserName = finalHandleUserName;
    }
}