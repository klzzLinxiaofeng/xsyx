package platform.szxyzxx.web.moral.vo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.io.Serializable;

/**
 * @author: yhc
 * @Date: 2021/4/19 16:30
 * @Description:
 */
public class ClassTeacherVo implements Serializable {
    private static final long serialVersionUID = 1L;
    @ExcelColumnImport(index = 0,endValue = "合计")
    private String first;
    @ExcelColumnImport("自评")
    private String zp;
    @ExcelColumnImport("校评")
    private String xp;
    @ExcelColumnImport("小组评")
    private String xzp;

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

    public String getXp() {
        return xp;
    }

    public void setXp(String xp) {
        this.xp = xp;
    }

    public String getXzp() {
        return xzp;
    }

    public void setXzp(String xzp) {
        this.xzp = xzp;
    }

    @Override
    public String toString() {
        return "ClassTeacherVo{" +
                "zp='" + zp + '\'' +
                ", xp='" + xp + '\'' +
                ", xzp='" + xzp + '\'' +
                '}';
    }
}
