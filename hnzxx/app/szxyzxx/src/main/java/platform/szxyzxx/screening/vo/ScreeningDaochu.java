package platform.szxyzxx.screening.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class ScreeningDaochu {
    /*
     * 排查人员姓名
     */
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("排查人")
    private String paichaUserName;
    /*
     * 排查时间
     */
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("排查时间")
    private String paichaTime;
    /*
     * 区域
     */
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("排查区域")
    private String screeningArea;
    /*
     * 水电
     */
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("水电")
    private String waterElectricity;
    /*
     * 安全隐患
     */
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("安全隐患")
    private String trouble;
    /*
     * 建筑质量
     */
    @ExcelColumnImport(index =5)
    @ExcelColumnExport("建筑质量")
    private String construction;
    /*
     * 设施设备
     */
    @ExcelColumnImport(index =6)
    @ExcelColumnExport("设施设备")
    private String facilities;
    /*
     * 备注
     */
    @ExcelColumnImport(index =7)
    @ExcelColumnExport("备注")
    private String  beizhu;

    public String getPaichaUserName() {
        return paichaUserName;
    }

    public void setPaichaUserName(String paichaUserName) {
        this.paichaUserName = paichaUserName;
    }

    public String getPaichaTime() {
        return paichaTime;
    }

    public void setPaichaTime(String paichaTime) {
        this.paichaTime = paichaTime;
    }

    public String getScreeningArea() {
        return screeningArea;
    }

    public void setScreeningArea(String screeningArea) {
        this.screeningArea = screeningArea;
    }

    public String getWaterElectricity() {
        return waterElectricity;
    }

    public void setWaterElectricity(String waterElectricity) {
        this.waterElectricity = waterElectricity;
    }

    public String getTrouble() {
        return trouble;
    }

    public void setTrouble(String trouble) {
        this.trouble = trouble;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }
}
