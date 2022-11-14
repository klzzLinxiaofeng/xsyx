package platform.education.generalTeachingAffair.vo;

import java.util.List;

public class ShiTangDate {
    private String tran_code;
    private String sign_name;
    private List<EmployeeList> employeeList;

    public String getTran_code() {
        return tran_code;
    }

    public void setTran_code(String tran_code) {
        this.tran_code = tran_code;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
    }

    public List<EmployeeList> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<EmployeeList> employeeList) {
        this.employeeList = employeeList;
    }

    public ShiTangDate(String tran_code, String sign_name, List<EmployeeList> employeeList) {
        this.tran_code = tran_code;
        this.sign_name = sign_name;
        this.employeeList = employeeList;
    }
    public ShiTangDate(){}

}
