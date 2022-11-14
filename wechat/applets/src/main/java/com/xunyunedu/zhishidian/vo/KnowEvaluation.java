package com.xunyunedu.zhishidian.vo;


import java.util.Date;
import java.util.List;

/*
* @Pama 知识点评价类
* autor zy
*/
public class KnowEvaluation {
    /*
    * id
    */
    private Integer id;
    /*
     * 知识点id
     */
    private Integer knowMenuId;
    /*
     * 知识点Name
     */
    private String knowMenuName;
    /*
     * 学生id
     */
    private Integer studentId;
    /*
     * 学生name
     */
    private String  studentName;
    /*
     * 评分
     */
    private Integer pingfen;
    /*
     * 评语
     */
    private String pingyu;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modiyTime;
    /*
     *
     */
    private Integer isDelete;

    private List<KnowEvaluation> knowEvaluationList;

    public List<KnowEvaluation> getKnowEvaluationList() {
        return knowEvaluationList;
    }

    public void setKnowEvaluationList(List<KnowEvaluation> knowEvaluationList) {
        this.knowEvaluationList = knowEvaluationList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKnowMenuId() {
        return knowMenuId;
    }

    public void setKnowMenuId(Integer knowMenuId) {
        this.knowMenuId = knowMenuId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getKnowMenuName() {
        return knowMenuName;
    }

    public void setKnowMenuName(String knowMenuName) {
        this.knowMenuName = knowMenuName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getPingfen() {
        return pingfen;
    }

    public void setPingfen(Integer pingfen) {
        this.pingfen = pingfen;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
