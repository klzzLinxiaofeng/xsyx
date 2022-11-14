package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ParentStudentTmp
 * @author AutoCreate
 *
 */
public class ParentStudentTmp implements Model<Integer> {


    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    private Integer id;
    /**
     * gradeName
     */
    private String gradeName;
    /**
     * teamNumber
     */
    private String teamNumber;
    /**
     * studentName
     */
    private String studentName;
    /**
     * number
     */
    private String number;
    /**
     * parentName
     */
    private String parentName;
    /**
     * mobile
     */
    private String mobile;
    /**
     * relation
     */
    private String relation;
    /**
     * rank
     */
    private String rank;
    /**
     * status
     */
    private Integer status;
    /**
     * errorFiled
     */
    private String errorFiled;
    /**
     * errorInfo
     */
    private String errorInfo;
    /**
     * code
     */
    private String code;
    /**
     * studentId
     */
    private Integer studentId;

    public ParentStudentTmp() {

    }

    public ParentStudentTmp(Integer id) {
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
     * 获取gradeName
     * @return java.lang.String
     */
    public String getGradeName() {
        return this.gradeName;
    }

    /**
     * 设置gradeName
     * @param gradeName
     * @type java.lang.String
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    /**
     * 获取teamNumber
     * @return java.lang.String
     */
    public String getTeamNumber() {
        return this.teamNumber;
    }

    /**
     * 设置teamNumber
     * @param teamNumber
     * @type java.lang.String
     */
    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    /**
     * 获取studentName
     * @return java.lang.String
     */
    public String getStudentName() {
        return this.studentName;
    }

    /**
     * 设置studentName
     * @param studentName
     * @type java.lang.String
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * 获取number
     * @return java.lang.String
     */
    public String getNumber() {
        return this.number;
    }

    /**
     * 设置number
     * @param number
     * @type java.lang.String
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * 获取parentName
     * @return java.lang.String
     */
    public String getParentName() {
        return this.parentName;
    }

    /**
     * 设置parentName
     * @param parentName
     * @type java.lang.String
     */
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 获取mobile
     * @return java.lang.String
     */
    public String getMobile() {
        return this.mobile;
    }

    /**
     * 设置mobile
     * @param mobile
     * @type java.lang.String
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取relation
     * @return java.lang.String
     */
    public String getRelation() {
        return this.relation;
    }

    /**
     * 设置relation
     * @param relation
     * @type java.lang.String
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }

    /**
     * 获取rank
     * @return java.lang.String
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * 设置rank
     * @param rank
     * @type java.lang.String
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * 获取status
     * @return java.lang.Integer
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置status
     * @param status
     * @type java.lang.Integer
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取errorFiled
     * @return java.lang.String
     */
    public String getErrorFiled() {
        return this.errorFiled;
    }

    /**
     * 设置errorFiled
     * @param errorFiled
     * @type java.lang.String
     */
    public void setErrorFiled(String errorFiled) {
        this.errorFiled = errorFiled;
    }

    /**
     * 获取errorInfo
     * @return java.lang.String
     */
    public String getErrorInfo() {
        return this.errorInfo;
    }

    /**
     * 设置errorInfo
     * @param errorInfo
     * @type java.lang.String
     */
    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    /**
     * 获取code
     * @return java.lang.String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * 设置code
     * @param code
     * @type java.lang.String
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取studentId
     * @return java.lang.Integer
     */
    public Integer getStudentId() {
        return this.studentId;
    }

    /**
     * 设置studentId
     * @param studentId
     * @type java.lang.Integer
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

}