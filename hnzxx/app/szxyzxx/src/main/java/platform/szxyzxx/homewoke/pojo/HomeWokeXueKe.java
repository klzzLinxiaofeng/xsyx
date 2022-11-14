package platform.szxyzxx.homewoke.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class HomeWokeXueKe {
    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("学生姓名")
    private String name;
    @ExcelColumnImport(index = 2)
    @ExcelColumnExport("状态")
    private String zhuantai;
    @ExcelColumnImport(index = 3)
    @ExcelColumnExport("等级")
    private String dengji;
    @ExcelColumnImport(index = 4)
    @ExcelColumnExport("评语")
    private String pingyu;
    @ExcelColumnImport(index = 5)
    @ExcelColumnExport("时间")
    private String shijian;
    @ExcelColumnImport(index = 6)
    @ExcelColumnExport("布置教师")
    private String teacherName;
    @ExcelColumnImport(index = 1)
    @ExcelColumnExport("作业内容")
    private String controt;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getControt() {
        return controt;
    }

    public void setControt(String controt) {
        this.controt = controt;
    }

    public String getName() {
        return name;
    }

    private Integer zhuantaiid;

    public Integer getZhuantaiid() {
        return zhuantaiid;
    }

    public void setZhuantaiid(Integer zhuantaiid) {
        this.zhuantaiid = zhuantaiid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZhuantai() {
        return zhuantai;
    }

    public void setZhuantai(String zhuantai) {
        this.zhuantai = zhuantai;
    }

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public String getShijian() {
        return shijian;
    }

    public void setShijian(String shijian) {
        this.shijian = shijian;
    }
}
