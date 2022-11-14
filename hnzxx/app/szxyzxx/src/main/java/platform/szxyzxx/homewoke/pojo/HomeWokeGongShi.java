package platform.szxyzxx.homewoke.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class HomeWokeGongShi {
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("教师名称")
    private String name;

    private String time;

    @ExcelColumnImport(index =1)
    @ExcelColumnExport("次数")
    private String cishu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCishu() {
        return cishu;
    }

    public void setCishu(String cishu) {
        this.cishu = cishu;
    }
}
