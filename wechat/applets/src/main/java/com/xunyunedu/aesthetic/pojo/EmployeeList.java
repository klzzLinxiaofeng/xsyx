package com.xunyunedu.aesthetic.pojo;

import lombok.Data;

@Data
public class EmployeeList {
    /*
     *智慧校园id
     */
    private String emp_pycode;
    /*
     *工号
     */
    private String emp_code;
    /*
     *姓名
     */
    private String emp_name;
    /*
     *卡号
     */
    private String emp_card;
    /*
     *性别
     */
    private String emp_sex;
    /*
     *出生日期
     */
    private String emp_birthday;
    /*
     *身份证号码
     */
    private String emp_idcard;
    /*
     *入学日期
     */
    private String emp_workdate;
    /*
     *部门编码
     */
    private String dept_code;
    /*
     *部门名称
     */
    private String dept_name;
    /*
     *职务
     */
    private String job_name;
    /*
     *人员级别
     */
    private String title_name;
    /*
     *就餐类型
     */
    private String emp_mealtype;
    /*
     *电话号码
     */
    private String emp_tel;
    /*
     *家庭地址
     */
    private String emp_address;

}
