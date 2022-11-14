package platform.szxyzxx.wokeBiao.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class DaoChuPojo {
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("时间")
    private String shijian;
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("星期一")
    private String zhouyi;
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("星期二")
    private String zhouer;
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("星期三")
    private String zhousan;
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("星期四")
    private String zhousi;
    @ExcelColumnImport(index =5)
    @ExcelColumnExport("星期五")
    private String zhouwu;
    @ExcelColumnImport(index =6)
    @ExcelColumnExport("星期六")
    private String zhouliu;
    @ExcelColumnImport(index =7)
    @ExcelColumnExport("星期日")
    private String zhouqi;

    @Override
    public String toString() {
        return "DaoChuPojo{" +
                "shijian='" + shijian + '\'' +
                ", zhouyi='" + zhouyi + '\'' +
                ", zhouer='" + zhouer + '\'' +
                ", zhousan='" + zhousan + '\'' +
                ", zhousi='" + zhousi + '\'' +
                ", zhouwu='" + zhouwu + '\'' +
                ", zhouliu='" + zhouliu + '\'' +
                ", zhouqi='" + zhouqi + '\'' +
                '}';
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }

    public String getZhouyi() {
        return zhouyi;
    }

    public void setZhouyi(String zhouyi) {
        this.zhouyi = zhouyi;
    }

    public String getZhouer() {
        return zhouer;
    }

    public void setZhouer(String zhouer) {
        this.zhouer = zhouer;
    }

    public String getZhousan() {
        return zhousan;
    }

    public void setZhousan(String zhousan) {
        this.zhousan = zhousan;
    }

    public String getZhousi() {
        return zhousi;
    }

    public void setZhousi(String zhousi) {
        this.zhousi = zhousi;
    }

    public String getZhouwu() {
        return zhouwu;
    }

    public void setZhouwu(String zhouwu) {
        this.zhouwu = zhouwu;
    }

    public String getZhouliu() {
        return zhouliu;
    }

    public void setZhouliu(String zhouliu) {
        this.zhouliu = zhouliu;
    }

    public String getZhouqi() {
        return zhouqi;
    }

    public void setZhouqi(String zhouqi) {
        this.zhouqi = zhouqi;
    }
}
