package platform.education.generalTeachingAffair.vo;

/**
 * 家庭成员信息
 */
public class ParentMess {
    private Integer parentUserId;

    /**
     * 家庭成员或监护人姓名
     */
    private String name;
    /**
     * 学生与家长关系（家庭关系）XY-JY-XSJTGX
     */
    private String parentRelation;
    /**
     * 关系说明
     */
    private String prealtionRemarks;
    /**
     * 民族
     */
    private String race;
    /**
     * 工作单位
     */
    private String workingPlace;
    /**
     * 现住地址
     */
    private String address;
    /**
     * 户口所在地
     */
    private String residenceAddress;
    /**
     *
     */
    private String residenceAddressCode;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 家长关系类别，0==普通  ，1==主监护人  XY-JY-JZLB
     */
    private String rank;
    /**
     * 身份证类别
     */
    private String idCardType;
    /**
     * 身份证
     */
    private String idCardNumber;
    /**
     * 职务
     */
    private String position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentRelation() {
        return parentRelation;
    }

    public void setParentRelation(String parentRelation) {
        this.parentRelation = parentRelation;
    }

    public String getPrealtionRemarks() {
        return prealtionRemarks;
    }

    public void setPrealtionRemarks(String prealtionRemarks) {
        this.prealtionRemarks = prealtionRemarks;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    public String getResidenceAddressCode() {
        return residenceAddressCode;
    }

    public void setResidenceAddressCode(String residenceAddressCode) {
        this.residenceAddressCode = residenceAddressCode;
    }

    @Override
    public String toString() {
        return "{parentUserId:'" + parentUserId + "', name:'" + name
                + "', parentRelation:'" + parentRelation
                + "', prealtionRemarks:'" + prealtionRemarks + "', race:'" + race
                + "', workingPlace:'" + workingPlace + "', address:'" + address
                + "', residenceAddress:'" + residenceAddress + "', residenceAddressCode:'"
                + residenceAddressCode + "', mobile:'"
                + mobile + "', rank:'" + rank + "', idCardType:'" + idCardType
                + "', idCardNumber:'" + idCardNumber + "', position:'" + position
                + "'}";
    }

}
