package com.xunyunedu.mergin.vo;


import framework.generic.dao.Model;
/**
 * TeamStudent
 * @author AutoCreate
 *
 */
public class TeamStudent implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * 所在年级ID
     */
    private Integer gradeId;

    /**
     * 所在年级名称
     */
    private String gradeName;

    /**
     * 所在班级ID
     */
    private Integer teamId;
    /**
     * 学生ID
     */
    private Integer studentId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 在班中的学号（顺序编号）
     */
    private Integer number;
    /**
     * 加入时间
     */
    private java.util.Date joinDate;
    /**
     * 结束时间
     */
    private java.util.Date finishDate;
    /**
     * 本记录创建时间
     */
    private java.util.Date recordDate;
    /**
     * 职务
     */
    private String position;
    /**
     * createDate
     */
    private java.util.Date createDate;
    /**
     * modifyDate
     */
    private java.util.Date modifyDate;

    /**
     * 删除标识
     */
    private Boolean isDelete;

    /**
     * 用户ID（2015-12-15）
     */
    private Integer userId;

    /**
     * 迁入/迁出状态  true=迁入，false=迁出（2015-12-22）
     */
    private Boolean inState;

//	public boolean isDelete() {
//		return isDelete;
//	}
//
//	public void setDelete(boolean isDelete) {
//		this.isDelete = isDelete;
//	}

    public TeamStudent() {

    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public TeamStudent(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    /**
     * 获取id
     * @return java.lang.Integer
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     * @param id
     * @type java.lang.Integer
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所在年级ID
     * @return java.lang.Integer
     */
    public Integer getGradeId() {
        return this.gradeId;
    }

    /**
     * 设置所在年级ID
     * @param gradeId
     * @type java.lang.Integer
     */
    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * 获取所在班级ID
     * @return java.lang.Integer
     */
    public Integer getTeamId() {
        return this.teamId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 设置所在班级ID
     * @param teamId
     * @type java.lang.Integer
     */
    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    /**
     * 获取学生ID
     * @return java.lang.Integer
     */
    public Integer getStudentId() {
        return this.studentId;
    }

    /**
     * 设置学生ID
     * @param studentId
     * @type java.lang.Integer
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取姓名
     * @return java.lang.String
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置姓名
     * @param name
     * @type java.lang.String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取在班中的学号（顺序编号）
     * @return java.lang.Integer
     */
    public Integer getNumber() {
        return this.number;
    }

    /**
     * 设置在班中的学号（顺序编号）
     * @param number
     * @type java.lang.Integer
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * 获取加入时间
     * @return java.util.Date
     */
    public java.util.Date getJoinDate() {
        return this.joinDate;
    }

    /**
     * 设置加入时间
     * @param joinDate
     * @type java.util.Date
     */
    public void setJoinDate(java.util.Date joinDate) {
        this.joinDate = joinDate;
    }

    /**
     * 获取结束时间
     * @return java.util.Date
     */
    public java.util.Date getFinishDate() {
        return this.finishDate;
    }

    /**
     * 设置结束时间
     * @param finishDate
     * @type java.util.Date
     */
    public void setFinishDate(java.util.Date finishDate) {
        this.finishDate = finishDate;
    }



    public java.util.Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(java.util.Date recordDate) {
        this.recordDate = recordDate;
    }


    /**
     * 获取职务
     * @return java.lang.String
     */
    public String getPosition() {
        return this.position;
    }

    /**
     * 设置职务
     * @param position
     * @type java.lang.String
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * 获取createDate
     * @return java.util.Date
     */
    public java.util.Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     * @param createDate
     * @type java.util.Date
     */
    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取modifyDate
     * @return java.util.Date
     */
    public java.util.Date getModifyDate() {
        return this.modifyDate;
    }

    /**
     * 设置modifyDate
     * @param modifyDate
     * @type java.util.Date
     */
    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getInState() {
        return inState;
    }

    public void setInState(Boolean inState) {
        this.inState = inState;
    }

}
