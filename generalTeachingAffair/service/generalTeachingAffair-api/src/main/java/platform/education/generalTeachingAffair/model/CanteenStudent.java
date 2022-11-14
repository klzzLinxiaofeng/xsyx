package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/**
 * 已发卡学生用户信息
 *
 * @author: yhc
 * @Date: 2020/10/28 15:06
 * @Description:
 */
public class CanteenStudent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 食堂卡号
     */
    private String icNumber;

    /**
     * 年级班级
     */
    private String gradeName;

    /**
     * 班级
     */
    private Integer classNum;

    /**
     * 食堂工号
     */
    private String empCode;


    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @Override
    public String toString() {
        return "CanteenStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icNumber='" + icNumber + '\'' +
                ", gradeName='" + gradeName + '\'' +
                ", empCode='" + empCode + '\'' +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}

