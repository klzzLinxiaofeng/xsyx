package platform.szxyzxx.web.teach.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;

public class ParentStudentExportInfo {
    @ExcelColumnExport("学生姓名")
    private String stuName;
    @ExcelColumnExport("班级")
    private String teamName;
    @ExcelColumnExport("一卡通卡号")
    private String empCard;
    @ExcelColumnExport("家长姓名")
    private String parentName;
    @ExcelColumnExport("与学生关系")
    private String relation;
    @ExcelColumnExport(value = "是否主监护人",replace = {"1=是","0=否"})
    private String sfzJh;
    @ExcelColumnExport("家长用户名")
    private String paretUserName;
    @ExcelColumnExport("家长手机号")
    private String mobile;
    @ExcelColumnExport("家长车牌")
    private String carNo;
    @ExcelColumnExport("家长邮箱")
    private String email;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getSfzJh() {
        return sfzJh;
    }

    public void setSfzJh(String sfzJh) {
        this.sfzJh = sfzJh;
    }

    public String getParetUserName() {
        return paretUserName;
    }

    public void setParetUserName(String paretUserName) {
        this.paretUserName = paretUserName;
    }


    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
