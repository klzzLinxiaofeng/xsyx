package platform.szxyzxx.web.schoolaffair.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;

import java.util.Date;

public class CarExportInfo {
    @ExcelColumnExport("姓名")
    private String name;
    @ExcelColumnExport("班级/部门")
    private String teamName;
    @ExcelColumnExport("补卡理由")
    private String reason;
    @ExcelColumnExport("原卡号")
    private String oldCar;
    @ExcelColumnExport("补卡卡号")
    private String newCar;
    @ExcelColumnExport(value = "提交时间",dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOldCar() {
        return oldCar;
    }

    public void setOldCar(String oldCar) {
        this.oldCar = oldCar;
    }

    public String getNewCar() {
        return newCar;
    }

    public void setNewCar(String newCar) {
        this.newCar = newCar;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
