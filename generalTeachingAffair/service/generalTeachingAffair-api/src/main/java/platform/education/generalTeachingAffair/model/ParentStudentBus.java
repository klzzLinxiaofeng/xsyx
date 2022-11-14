package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * ParentStudent
 *
 * @author AutoCreate
 */
public class ParentStudentBus implements Model<Integer> {


    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户姓名(冗余) yh_user.username
     */
    private String userName;

    /**
     * 家长姓名
     */
    private String name;
    /**
     * 家长手机号
     */
    private String mobile;

    /**
     * 学生名称
     */
    private String stuName;
    /**
     * 班级名称
     */
    private String teamName;

    /**
     * 学生卡号
     */
    private String empCard;

    private Integer parentUserId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getParentUserId() {
        return parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    @Override
    public Integer getKey() {
        return this.id;
    }
}