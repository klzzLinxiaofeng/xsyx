package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/**
 * 已发卡教师用户信息
 *
 * @author: yhc
 * @Date: 2020/10/28 15:06
 * @Description:
 */
public class CanteenTeacher implements Serializable {
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


    private Integer sex;

    private String empCode;

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


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "CanteenTeacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", icNumber='" + icNumber + '\'' +
                ", sex=" + sex +
                '}';
    }
}

