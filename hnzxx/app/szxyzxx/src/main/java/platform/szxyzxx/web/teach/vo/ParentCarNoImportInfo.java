package platform.szxyzxx.web.teach.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class ParentCarNoImportInfo {

    @ExcelColumnImport(index = 9)
    private String stuName;
    @ExcelColumnImport(index = 0)
    private String carNo;
    @ExcelColumnImport(index = 11)
    private String org;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "ParentCarNoImportInfo{" +
                "stuName='" + stuName + '\'' +
                ", carNo='" + carNo + '\'' +
                ", org='" + org + '\'' +
                '}';
    }
}
