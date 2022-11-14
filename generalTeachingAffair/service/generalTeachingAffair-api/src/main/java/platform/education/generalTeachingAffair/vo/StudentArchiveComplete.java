package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.Person;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class StudentArchiveComplete implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 基本信息
     */
    private Basic basic;

    /**
     * 学籍信息
     */
    private Archive archive;

    /**
     * 辅助信息
     */
    private Assist assist;

    /**
     * 扩展信息
     */
    private Extra extra;

    /**
     * 家长信息
     */
    private Parent parent;

    /**
     * 联系信息
     */
    private Relation relation;

    /**
     * 交通方式
     */
    private Traffic traffic;

    /**
     * 备注信息
     */
    private Remarks remarks;

    /**
     * 学生简历
     */
    private Resumes resumes;

    /**
     * 个人档案
     */
    private Person person;


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Resumes getResumes() {
        return resumes;
    }

    public void setResumes(Resumes resumes) {
        this.resumes = resumes;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public Archive getArchive() {
        return archive;
    }

    public void setArchive(Archive archive) {
        this.archive = archive;
    }

    public Assist getAssist() {
        return assist;
    }

    public void setAssist(Assist assist) {
        this.assist = assist;
    }

    public Extra getExtra() {
        return extra;
    }

    public void setExtra(Extra extra) {
        this.extra = extra;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public Relation getRelation() {
        return relation;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public Traffic getTraffic() {
        return traffic;
    }

    public void setTraffic(Traffic traffic) {
        this.traffic = traffic;
    }

    public Remarks getRemarks() {
        return remarks;
    }

    public void setRemarks(Remarks remarks) {
        this.remarks = remarks;
    }


    /**
     * 基本信息类
     */
    public class Basic {
        /**
         * 姓名
         */
        private String name;

        /**
         * 性别
         */
        private String sex;

        /**
         * 出生日期
         */
        private Date birthday;

        /**
         * 出生地（行政区划）
         */
        private String birthPlace;

        /**
         *
         */
        private String birthPlaceCode;

        /**
         * 籍贯
         */
        private String nativePlace;

        /**
         * 籍贯id
         */
        private String nativePlaceCode;

        /**
         * 民族
         */
        private String race;

        /**
         * 国籍
         */
        private String nationality;

        /**
         * 身份证件类别
         */
        private String idCardType;

        /**
         * 身份证
         */
        private String idCardNumber;

        /**
         * 港澳台侨外码
         */
        private String abroadCode;

        /**
         * 政治面貌
         */
        private String politicalStatus;

        /**
         * 健康状况
         */
        private String healthStatus;

        /**
         * 血型
         */
        private String bloodType;

        private String photoUuid;

        private String photoUrl;

        public String getBirthPlaceCode() {
            return birthPlaceCode;
        }

        public void setBirthPlaceCode(String birthPlaceCode) {
            this.birthPlaceCode = birthPlaceCode;
        }

        public String getNativePlaceCode() {
            return nativePlaceCode;
        }

        public void setNativePlaceCode(String nativePlaceCode) {
            this.nativePlaceCode = nativePlaceCode;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getNativePlace() {
            return nativePlace;
        }

        public void setNativePlace(String nativePlace) {
            this.nativePlace = nativePlace;
        }

        public String getRace() {
            return race;
        }

        public void setRace(String race) {
            this.race = race;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public String getIdCardType() {
            return idCardType;
        }

        public void setIdCardType(String idCardType) {
            this.idCardType = idCardType;
        }

        public String getIdCardNumber() {
            return idCardNumber;
        }

        public void setIdCardNumber(String idCardNumber) {
            this.idCardNumber = idCardNumber;
        }

        public String getAbroadCode() {
            return abroadCode;
        }

        public void setAbroadCode(String abroadCode) {
            this.abroadCode = abroadCode;
        }

        public String getPoliticalStatus() {
            return politicalStatus;
        }

        public void setPoliticalStatus(String politicalStatus) {
            this.politicalStatus = politicalStatus;
        }

        public String getHealthStatus() {
            return healthStatus;
        }

        public void setHealthStatus(String healthStatus) {
            this.healthStatus = healthStatus;
        }

        public String getBloodType() {
            return bloodType;
        }

        public void setBloodType(String bloodType) {
            this.bloodType = bloodType;
        }

        public String getPhotoUuid() {
            return photoUuid;
        }

        public void setPhotoUuid(String photoUuid) {
            this.photoUuid = photoUuid;
        }
    }

    /**
     * 辅助信息类
     */
    public class Assist {
        /**
         * 户口所在地
         */
        private String residenceAddress;

        private String residenceAddressCode;

        /**
         * 户口性质
         */
        private String residenceType;

        /**
         * 特长
         */
        private String specialSkill;

        /**
         * 姓名拼音
         */
        private String pinyinName;

        /**
         * 曾用名
         */
        private String usedName;

        /**
         * 身份证有效日期
         */
        private Date idCardDate;

        public String getResidenceAddress() {
            return residenceAddress;
        }

        public void setResidenceAddress(String residenceAddress) {
            this.residenceAddress = residenceAddress;
        }

        public String getResidenceType() {
            return residenceType;
        }

        public void setResidenceType(String residenceType) {
            this.residenceType = residenceType;
        }

        public String getSpecialSkill() {
            return specialSkill;
        }

        public void setSpecialSkill(String specialSkill) {
            this.specialSkill = specialSkill;
        }

        public String getPinyinName() {
            return pinyinName;
        }

        public void setPinyinName(String pinyinName) {
            this.pinyinName = pinyinName;
        }

        public String getUsedName() {
            return usedName;
        }

        public void setUsedName(String usedName) {
            this.usedName = usedName;
        }

        public Date getIdCardDate() {
            return idCardDate;
        }

        public void setIdCardDate(Date idCardDate) {
            this.idCardDate = idCardDate;
        }

        public String getResidenceAddressCode() {
            return residenceAddressCode;
        }

        public void setResidenceAddressCode(String residenceAddressCode) {
            this.residenceAddressCode = residenceAddressCode;
        }

    }

    /**
     * 学籍信息类
     */
    public class Archive {
        /**
         * 学生类别
         */
        private String studentType;
        /**
         * 入学方式
         */
        private String enrollType;

        /**
         * 就读方式
         */
        private String attendSchoolType;
        /**
         * 学生来源
         */
        private String studentSource;

        /**
         * 全国统一学籍号
         */
        private String studentNumber;
        /**
         * 在班中的学号（顺序编号）
         */
        private String number;
        /**
         * 所在年级ID
         */
        private Integer gradeId;
        /**
         * 原来所在年级ID
         */
        private Integer ordelgradeId;
        /**
         * 所在班级ID
         */
        private Integer teamId;

        /**
         * 入学时间
         */
        private Date enrollDate;
        /**
         * 离校时间
         */
        private Date leaveDate;

        /**
         *  年级名次
         */
        private String gradeName;

        /**
         * 班级名称
         */
        private String teamName;
        /**
         * 学生在读状态:01=在读。02=休学。03=退学。04=停学。07=毕业。08=结业。09=肄业。10=转学.11=死亡 14=开除。99=其它
         */
        private String studyState;

        public Integer getOrdelgradeId() {
            return ordelgradeId;
        }

        public void setOrdelgradeId(Integer ordelgradeId) {
            this.ordelgradeId = ordelgradeId;
        }

        public String getStudyState() {
            return studyState;
        }

        public void setStudyState(String studyState) {
            this.studyState = studyState;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public String getStudentType() {
            return studentType;
        }

        public void setStudentType(String studentType) {
            this.studentType = studentType;
        }

        public String getEnrollType() {
            return enrollType;
        }

        public void setEnrollType(String enrollType) {
            this.enrollType = enrollType;
        }

        public String getAttendSchoolType() {
            return attendSchoolType;
        }

        public void setAttendSchoolType(String attendSchoolType) {
            this.attendSchoolType = attendSchoolType;
        }

        public String getStudentSource() {
            return studentSource;
        }

        public void setStudentSource(String studentSource) {
            this.studentSource = studentSource;
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public void setStudentNumber(String studentNumber) {
            this.studentNumber = studentNumber;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public Integer getGradeId() {
            return gradeId;
        }

        public void setGradeId(Integer gradeId) {
            this.gradeId = gradeId;
        }

        public Integer getTeamId() {
            return teamId;
        }

        public void setTeamId(Integer teamId) {
            this.teamId = teamId;
        }

        public Date getEnrollDate() {
            return enrollDate;
        }

        public void setEnrollDate(Date enrollDate) {
            this.enrollDate = enrollDate;
        }

        public Date getLeaveDate() {
            return leaveDate;
        }

        public void setLeaveDate(Date leaveDate) {
            this.leaveDate = leaveDate;
        }

    }

    /**
     * 联系信息类
     */
    public class Relation {
        /**
         * 地址
         */
        private String address;

        /**
         * 家庭地址
         */
        private String homeAddress;

        /**
         * 居住地址
         */
        private String resideAddress;

        /**
         * 手机
         */
        private String mobile;

        /**
         * 电话
         */
        private String telephone;

        /**
         * 邮政编码
         */
        private String zipCode;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 个人主页
         */
        private String homepage;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getResideAddress() {
            return resideAddress;
        }

        public void setResideAddress(String resideAddress) {
            this.resideAddress = resideAddress;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHomepage() {
            return homepage;
        }

        public void setHomepage(String homepage) {
            this.homepage = homepage;
        }

    }

    /**
     * 扩展信息类
     */
    public class Extra {
        /**
         * 是否独生子女
         */
        private Boolean isOnlyChild;
        /**
         * 是否受过学前教育
         */
        private Boolean isPreeducated;
        /**
         * 是否农村留守儿童
         */
        private String isUnattendedChild;
        /**
         * 进城务工随迁子女
         */
        private Boolean isCityLabourChild;
        /**
         * 是否是孤儿
         */
        private Boolean isOrphan;
        /**
         * 是否烈士或优抚子女
         */
        private Boolean isMartyrChild;
        /**
         * 是否随班就读
         */
        private String followStudy;
        /**
         * 残疾类型
         */
        private String disabilityType;
        /**
         * 是否由政府购买学位
         */
        private Boolean isBuyedDegree;
        /**
         * 是否需要申请资助
         */
        private Boolean isSponsored;
        /**
         * 是否享受一保
         */
        private Boolean isFirstRecommended;

        /**
         * 所住房屋权属
         */
        private String houseAuthority;

        private Boolean needSpecialCare;
        /**
         *性格
         */
        private String xingge;
        /**
         * 爱好
         */
        private String aihao;
        /**
         * 最喜欢的书
         */
        private String likeBook;
        /**
         * 最敬佩的人
         */
        private String jingpei;
        /**
         * 座右铭
         */
        private String zuoyouming;
        /**
         * 成长感言
         */
        private String ganyan;
        /**
         * 成长感言
         */
        private String ganyan1;
        /**
         * 图片uuid
         */
        private String pictureUuid;
        /**
         * 图片url;
         */
        private String pictureUrl;


        public String getGanyan1() {
            return ganyan1;
        }

        public void setGanyan1(String ganyan1) {
            this.ganyan1 = ganyan1;
        }

        public String getPictureUuid() {
            return pictureUuid;
        }

        public void setPictureUuid(String pictureUuid) {
            this.pictureUuid = pictureUuid;
        }

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        public String getXingge() {
            return xingge;
        }

        public void setXingge(String xingge) {
            this.xingge = xingge;
        }

        public String getAihao() {
            return aihao;
        }

        public void setAihao(String aihao) {
            this.aihao = aihao;
        }

        public String getLikeBook() {
            return likeBook;
        }

        public void setLikeBook(String likeBook) {
            this.likeBook = likeBook;
        }

        public String getJingpei() {
            return jingpei;
        }

        public void setJingpei(String jingpei) {
            this.jingpei = jingpei;
        }

        public String getZuoyouming() {
            return zuoyouming;
        }

        public void setZuoyouming(String zuoyouming) {
            this.zuoyouming = zuoyouming;
        }

        public String getGanyan() {
            return ganyan;
        }

        public void setGanyan(String ganyan) {
            this.ganyan = ganyan;
        }

        public Boolean getIsOnlyChild() {
            return isOnlyChild;
        }

        public void setIsOnlyChild(Boolean isOnlyChild) {
            this.isOnlyChild = isOnlyChild;
        }

        public Boolean getIsPreeducated() {
            return isPreeducated;
        }

        public void setIsPreeducated(Boolean isPreeducated) {
            this.isPreeducated = isPreeducated;
        }

        public String getIsUnattendedChild() {
            return isUnattendedChild;
        }

        public void setIsUnattendedChild(String isUnattendedChild) {
            this.isUnattendedChild = isUnattendedChild;
        }

        public Boolean getIsCityLabourChild() {
            return isCityLabourChild;
        }

        public void setIsCityLabourChild(Boolean isCityLabourChild) {
            this.isCityLabourChild = isCityLabourChild;
        }

        public Boolean getIsOrphan() {
            return isOrphan;
        }

        public void setIsOrphan(Boolean isOrphan) {
            this.isOrphan = isOrphan;
        }

        public Boolean getIsMartyrChild() {
            return isMartyrChild;
        }

        public void setIsMartyrChild(Boolean isMartyrChild) {
            this.isMartyrChild = isMartyrChild;
        }

        public String getFollowStudy() {
            return followStudy;
        }

        public void setFollowStudy(String followStudy) {
            this.followStudy = followStudy;
        }

        public String getDisabilityType() {
            return disabilityType;
        }

        public void setDisabilityType(String disabilityType) {
            this.disabilityType = disabilityType;
        }

        public Boolean getIsBuyedDegree() {
            return isBuyedDegree;
        }

        public void setIsBuyedDegree(Boolean isBuyedDegree) {
            this.isBuyedDegree = isBuyedDegree;
        }

        public Boolean getIsSponsored() {
            return isSponsored;
        }

        public void setIsSponsored(Boolean isSponsored) {
            this.isSponsored = isSponsored;
        }

        public Boolean getIsFirstRecommended() {
            return isFirstRecommended;
        }

        public void setIsFirstRecommended(Boolean isFirstRecommended) {
            this.isFirstRecommended = isFirstRecommended;
        }

        public String getHouseAuthority() {
            return houseAuthority;
        }

        public void setHouseAuthority(String houseAuthority) {
            this.houseAuthority = houseAuthority;
        }

        public Boolean getNeedSpecialCare() {
            return needSpecialCare;
        }

        public void setNeedSpecialCare(Boolean needSpecialCare) {
            this.needSpecialCare = needSpecialCare;
        }

    }

    /**
     * 交通方式信息类
     */
    public class Traffic {
        /**
         * 上下学距离(公里)
         */
        private String schoolDistance;
        /**
         * 上下学交通方式
         */
        private String trafficType;
        /**
         * 是否乘坐交通方式
         */
        private Boolean bySchoolBus;

        public String getSchoolDistance() {
            return schoolDistance;
        }

        public void setSchoolDistance(String schoolDistance) {
            this.schoolDistance = schoolDistance;
        }

        public String getTrafficType() {
            return trafficType;
        }

        public void setTrafficType(String trafficType) {
            this.trafficType = trafficType;
        }

        public Boolean getBySchoolBus() {
            return bySchoolBus;
        }

        public void setBySchoolBus(Boolean bySchoolBus) {
            this.bySchoolBus = bySchoolBus;
        }

    }

    /**
     * 家庭成员信息类<多个家长信息>
     */
    public class Parent {
        /**
         * 多个家庭成员信息
         */
        private List<ParentMess> parentMess;

        public List<ParentMess> getParentMess() {
            return parentMess;
        }

        public void setParentMess(List<ParentMess> parentMess) {
            this.parentMess = parentMess;
        }
    }

    /**
     * 备注信息
     */
    public class Remarks {
        /**
         * 备注信息
         */
        private String remark;

        private String errorCode;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

    }


    /**
     * 备注信息
     */
    public class Resumes {
        private String resume;

        private String errorCode;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

    }
}