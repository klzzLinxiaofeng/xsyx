package platform.szxyzxx.web.hikservionDoor.model;

import java.util.Date;

/**
 * 食堂返回的数据
 *
 * @author: cmb
 * @create: 2020-11-11 11:17
 **/
public class EmpData {
    /**
     * id
     */
    private String emp_id;
    /**
     * 工号
     */
    private String emp_code;
    /**
     * 卡号
     */
    private String emp_card;
    /**
     * 姓名
     */
    private String emp_name;
    /**
     * 部门
     */
    private String dept_name;
    /**
     * 职务
     */
    private String job_name;
    /**
     * 职级
     */
    private String title_name;
/**
 * 入职日期
 */
  private Date emp_workdate;
  /**
   * 补贴账户
   */
  private Integer emp_comcardmoney;
  /**
   * 补贴账户
   */
  private Integer emp_percardmoney;
  /**
   * 电话
   */
  private String emp_tel;
  /**
   * 地址
   */
  private String emp_linkaddress;

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public String getEmp_card() {
        return emp_card;
    }

    public void setEmp_card(String emp_card) {
        this.emp_card = emp_card;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public Date getEmp_workdate() {
        return emp_workdate;
    }

    public void setEmp_workdate(Date emp_workdate) {
        this.emp_workdate = emp_workdate;
    }

    public Integer getEmp_comcardmoney() {
        return emp_comcardmoney;
    }

    public void setEmp_comcardmoney(Integer emp_comcardmoney) {
        this.emp_comcardmoney = emp_comcardmoney;
    }

    public Integer getEmp_percardmoney() {
        return emp_percardmoney;
    }

    public void setEmp_percardmoney(Integer emp_percardmoney) {
        this.emp_percardmoney = emp_percardmoney;
    }

    public String getEmp_tel() {
        return emp_tel;
    }

    public void setEmp_tel(String emp_tel) {
        this.emp_tel = emp_tel;
    }

    public String getEmp_linkaddress() {
        return emp_linkaddress;
    }

    public void setEmp_linkaddress(String emp_linkaddress) {
        this.emp_linkaddress = emp_linkaddress;
    }

    @Override
    public String toString() {
        return "EmpData{" +
                "emp_id='" + emp_id + '\'' +
                ", emp_code='" + emp_code + '\'' +
                ", emp_card='" + emp_card + '\'' +
                ", emp_name='" + emp_name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                ", job_name='" + job_name + '\'' +
                ", title_name='" + title_name + '\'' +
                ", emp_workdate=" + emp_workdate +
                ", emp_comcardmoney=" + emp_comcardmoney +
                ", emp_percardmoney=" + emp_percardmoney +
                ", emp_tel='" + emp_tel + '\'' +
                ", emp_linkaddress='" + emp_linkaddress + '\'' +
                '}';
    }
}
