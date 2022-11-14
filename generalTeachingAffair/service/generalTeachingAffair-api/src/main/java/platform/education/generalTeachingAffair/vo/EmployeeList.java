package platform.education.generalTeachingAffair.vo;

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

    public String getEmp_pycode() {
        return emp_pycode;
    }

    public void setEmp_pycode(String emp_pycode) {
        this.emp_pycode = emp_pycode;
    }

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

    public String getEmp_sex() {
        return emp_sex;
    }

    public void setEmp_sex(String emp_sex) {
        this.emp_sex = emp_sex;
    }

    public String getEmp_birthday() {
        return emp_birthday;
    }

    public void setEmp_birthday(String emp_birthday) {
        this.emp_birthday = emp_birthday;
    }

    public String getEmp_idcard() {
        return emp_idcard;
    }

    public void setEmp_idcard(String emp_idcard) {
        this.emp_idcard = emp_idcard;
    }

    public String getEmp_workdate() {
        return emp_workdate;
    }

    public void setEmp_workdate(String emp_workdate) {
        this.emp_workdate = emp_workdate;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
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

    public String getEmp_mealtype() {
        return emp_mealtype;
    }

    public void setEmp_mealtype(String emp_mealtype) {
        this.emp_mealtype = emp_mealtype;
    }

    public String getEmp_tel() {
        return emp_tel;
    }

    public void setEmp_tel(String emp_tel) {
        this.emp_tel = emp_tel;
    }

    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }

    public EmployeeList(){}
    public EmployeeList(String emp_pycode, String emp_code, String emp_name, String emp_card, String emp_sex, String emp_birthday, String emp_idcard, String emp_workdate, String dept_code, String dept_name, String job_name, String title_name, String emp_mealtype, String emp_tel, String emp_address) {
        this.emp_pycode = emp_pycode;
        this.emp_code = emp_code;
        this.emp_name = emp_name;
        this.emp_card = emp_card;
        this.emp_sex = emp_sex;
        this.emp_birthday = emp_birthday;
        this.emp_idcard = emp_idcard;
        this.emp_workdate = emp_workdate;
        this.dept_code = dept_code;
        this.dept_name = dept_name;
        this.job_name = job_name;
        this.title_name = title_name;
        this.emp_mealtype = emp_mealtype;
        this.emp_tel = emp_tel;
        this.emp_address = emp_address;
    }
}
