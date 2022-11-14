package com.xunyunedu.pay.pojo;

import java.io.Serializable;

/**
 * 食堂订单对象
 *
 * @author: yhc
 * @Date: 2020/10/26 16:22
 * @Description:
 */
public class CanteenPojo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String emp_code;
    private String emp_name;

    private String emp_card;
    private double change_money;
    private Integer change_flag;
    private String clock_id;
    private String createPerson;
    private String change_why;
    private String change_type;

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_card() {
        return emp_card;
    }

    public void setEmp_card(String emp_card) {
        this.emp_card = emp_card;
    }

    public double getChange_money() {
        return change_money;
    }

    public void setChange_money(double change_money) {
        this.change_money = change_money;
    }

    public Integer getChange_flag() {
        return change_flag;
    }

    public void setChange_flag(Integer change_flag) {
        this.change_flag = change_flag;
    }

    public String getClock_id() {
        return clock_id;
    }

    public void setClock_id(String clock_id) {
        this.clock_id = clock_id;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public String getChange_why() {
        return change_why;
    }

    public void setChange_why(String change_why) {
        this.change_why = change_why;
    }

    public String getChange_type() {
        return change_type;
    }

    public void setChange_type(String change_type) {
        this.change_type = change_type;
    }

    @Override
    public String toString() {
        return "Pojos{" +
                "emp_code='" + emp_code + '\'' +
                ", emp_name='" + emp_name + '\'' +
                ", emp_card='" + emp_card + '\'' +
                ", change_money='" + change_money + '\'' +
                ", change_flag='" + change_flag + '\'' +
                ", clock_id='" + clock_id + '\'' +
                ", createPerson='" + createPerson + '\'' +
                ", change_why='" + change_why + '\'' +
                ", change_type='" + change_type + '\'' +
                '}';
    }
}
