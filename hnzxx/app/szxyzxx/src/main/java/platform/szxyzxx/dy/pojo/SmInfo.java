package platform.szxyzxx.dy.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;
import platform.szxyzxx.excelhelper.constants.CellValueContants;

public class SmInfo implements Comparable<SmInfo>{
    @ExcelColumnImport(index = 0,ignoreValue = CellValueContants.BLANK)
    private String teamName;

    @ExcelColumnImport(index = 1)
    private Integer cybh=0;

    @ExcelColumnImport(index = 2)
    private Integer dkj=0;

    @ExcelColumnImport(index = 3)
    private Integer xzbj=0;

    @ExcelColumnImport(index = 4)
    private Integer xzbw=0;

    @ExcelColumnImport(index = 5)
    private Integer zdfd=0;

    @ExcelColumnImport(index = 6)
    private Integer zhfk=0;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getCybh() {
        return cybh;
    }

    public void setCybh(Integer cybh) {
        this.cybh = cybh;
    }

    public Integer getDkj() {
        return dkj;
    }

    public void setDkj(Integer dkj) {
        this.dkj = dkj;
    }

    public Integer getXzbj() {
        return xzbj;
    }

    public void setXzbj(Integer xzbj) {
        this.xzbj = xzbj;
    }

    public Integer getXzbw() {
        return xzbw;
    }

    public void setXzbw(Integer xzbw) {
        this.xzbw = xzbw;
    }

    public Integer getZdfd() {
        return zdfd;
    }

    public void setZdfd(Integer zdfd) {
        this.zdfd = zdfd;
    }

    public Integer getZhfk() {
        return zhfk;
    }

    public void setZhfk(Integer zhfk) {
        this.zhfk = zhfk;
    }

    public Integer getSum(){
        return this.cybh+this.dkj+this.xzbj+this.xzbw+this.zdfd+this.zhfk;
    }

    @Override
    public int compareTo(SmInfo o) {
        //降序
        return o.getSum().compareTo(this.getSum());
    }
}
