package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

public class SchoolBusMangerVo implements Model<Integer> {
    /**
     * id
     */
    private Integer id;

    /**
     * 学生id
     */
    private Integer studentId;

    /**
     * 所属校车系统 萌童校卫：“0”；国盛：“1”
     */
//    private Integer systemId;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改日期
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;


    /**
     * 学生信息是否发送到昊吉顺校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
     */
    private Integer isSendSchoolBusHjs;
    /**
     * 学生信息是否发送到国盛校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
     */
    private Integer isSendSchoolBusGs;

    public Integer getIsSendSchoolBusHjs() {
        return isSendSchoolBusHjs;
    }

    public void setIsSendSchoolBusHjs(Integer isSendSchoolBusHjs) {
        this.isSendSchoolBusHjs = isSendSchoolBusHjs;
    }

    public Integer getIsSendSchoolBusGs() {
        return isSendSchoolBusGs;
    }

    public void setIsSendSchoolBusGs(Integer isSendSchoolBusGs) {
        this.isSendSchoolBusGs = isSendSchoolBusGs;
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

//    public Integer getSystemId() {
//        return systemId;
//    }
//
//    public void setSystemId(Integer systemId) {
//        this.systemId = systemId;
//    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Override
    public Integer getKey() {
        return this.id;
    }
}

