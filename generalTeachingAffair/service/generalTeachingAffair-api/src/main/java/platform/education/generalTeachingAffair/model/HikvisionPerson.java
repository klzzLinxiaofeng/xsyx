package platform.education.generalTeachingAffair.model;

import java.util.Arrays;

public class HikvisionPerson {
    private String personId;
    private String personName;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "HikvisionPerson{" +
                "personId='" + personId + '\'' +
                ", personName='" + personName + '\'' +
                ", genderNum=" + genderNum +
                ", genderStr='" + genderStr + '\'' +
                ", orgIndexCode='" + orgIndexCode + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", certificateType='" + certificateType + '\'' +
                ", certificateNo='" + certificateNo + '\'' +
                ", jobNo='" + jobNo + '\'' +
                ", faces=" + Arrays.toString(faces) +
                ", faceData='" + faceData + '\'' +
                '}';
    }

    /**
     * 性别
     * 1：男
     * 2：女
     * 0：未知
     * 如果是批量是num 单条str
     */
    private Number genderNum;
    private String genderStr;
    /**
     *  	所属组织标识，必须是已存在组织
     */
    private String orgIndexCode;
    /**
     *  	出生日期，举例：1992-09-12
     */
    private String birthday;

    public Number getGenderNum() {
        return genderNum;
    }

    public void setGenderNum(Number genderNum) {
        this.genderNum = genderNum;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    /**
     * 手机号，1-20位数字
     */
    private String phoneNo;
    /**
     * 邮箱，举例：hic@163.com
     */
    private String email;
    /**
     * 证件类型
     * 111:身份证
     * 414:护照
     * 113:户口簿
     * 335:驾驶证
     * 131:工作证
     * 133:学生证
     * 990:其他
     */
    private String certificateType;
    /**
     * 证件号码，1-20位数字字母，
     */
    private String certificateNo;
    /**
     * 工号
     */
    private String jobNo;
    /**
     * 人脸信息
     */
    private Object[] faces;
    /**
     * 人脸图片base64编码后的字符
     */
    private String faceData;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }


    public String getOrgIndexCode() {
        return orgIndexCode;
    }

    public void setOrgIndexCode(String orgIndexCode) {
        this.orgIndexCode = orgIndexCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public Object[] getFaces() {
        return faces;
    }

    public void setFaces(Object[] faces) {
        this.faces = faces;
    }

    public String getFaceData() {
        return faceData;
    }

    public void setFaceData(String faceData) {
        this.faceData = faceData;
    }
}
