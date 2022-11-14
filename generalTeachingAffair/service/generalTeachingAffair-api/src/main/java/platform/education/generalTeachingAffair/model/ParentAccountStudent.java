package platform.education.generalTeachingAffair.model;

import java.util.Date;
import java.util.List;

public class ParentAccountStudent {

    private Integer pid;

    private Integer userId;

    private String name;

    private String mobile;

    private String email;

    private String userName;


    private Date createTime;

    private String status;

    private String carNo;


    List<ParentStudentInfo> studentList;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ParentStudentInfo> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<ParentStudentInfo> studentList) {
        this.studentList = studentList;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getStuInfo(){

        if(studentList==null || studentList.size()==0){
            return null;
        }
        StringBuilder infoStr=new StringBuilder();
        for (ParentStudentInfo s : studentList) {
            if(infoStr.length()!=0){
                infoStr.append("、");
            }
            infoStr.append(s.getStuName());
            infoStr.append("|").append(s.getTeamName()).append("|");
            infoStr.append(s.getRank().equals("1")? "主监护人":"");
        }

        return infoStr.toString();
    }

}
