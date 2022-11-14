package platform.szxyzxx.ishangkelilu.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class KeTangGongShi {
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("教师姓名")
    private  String name;
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("次数")
    private  Integer cishu;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCishu() {
        return cishu;
    }

    public void setCishu(Integer cishu) {
        this.cishu = cishu;
    }
}
