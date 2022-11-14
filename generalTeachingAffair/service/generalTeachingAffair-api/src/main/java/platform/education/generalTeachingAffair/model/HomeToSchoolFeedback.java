package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Eternityhua
 * @create 2020-12-03 10:20
 */
public class HomeToSchoolFeedback  implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 家长姓名
     */
    private String parentName;

    /**
     * 家长手机号
     */
    private String phone;

    /**
     * 反馈内容
     */
    private String content;
    /*
    * 回复的教师id
    */
    private Integer teacherId;
    /*
     * 回复的教师id
     */
    private String  teacherName;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    /**
     * 返回完整的图片说明url
     */
    private String picUrl;
    private String picUrl2;
    private String picUrl3;

    /**
     * 图片说明uuid
     */
    private String uuid;




    /**
     * 实际图片的地址，不存在于表中
     */
    private String coverUrl;

    public String getPicUrl2() {
        return picUrl2;
    }

    public void setPicUrl2(String picUrl2) {
        this.picUrl2 = picUrl2;
    }

    public String getPicUrl3() {
        return picUrl3;
    }

    public void setPicUrl3(String picUrl3) {
        this.picUrl3 = picUrl3;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 请求类型 0 回复   1  详情
     */
    private Integer type;

    /*
    * 回复标记
    *
    * 0 未回复   1 回复
    *
    * */
    private Integer isReply;



    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }





    private Integer schoolId;
    /**
     * 创建日期
     */
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date createDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 作废标记
     */
    private Integer isDelete;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 回复内容 表中不存在 在外表 回复表中
     */
    private String remark;


    @Override
    public Integer getKey()  {
        return this.id;
    }


    @Override
    public String toString() {
        return "HomeToSchoolFeedback{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", name='" + name + '\'' +
                ", parentName='" + parentName + '\'' +
                ", phone='" + phone + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", picUrl2='" + picUrl2 + '\'' +
                ", picUrl3='" + picUrl3 + '\'' +
                ", uuid='" + uuid + '\'' +
                ", coverUrl='" + coverUrl + '\'' +
                ", type=" + type +
                ", isReply=" + isReply +
                ", schoolId=" + schoolId +
                ", createDate=" + createDate +
                ", isDelete=" + isDelete +
                ", remark='" + remark + '\'' +
                '}';
    }
}
