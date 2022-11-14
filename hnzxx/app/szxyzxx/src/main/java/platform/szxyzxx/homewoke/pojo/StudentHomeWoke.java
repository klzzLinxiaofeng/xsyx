package platform.szxyzxx.homewoke.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.util.Date;

public class StudentHomeWoke {
    private Integer id;
    private Integer jobId;
    private Date createTime;
    private Date modieTime;
    private Integer isDelete;
    private Integer HomeWokeId;
    private  Integer studentId;
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("学生姓名")
    private  String studentName;
    /*
    * 作业状态 0待提交，1已提交 ，2缺交，3 补交
    */
    private Integer zhuantai;
    /*
     * 作业状态 0待提交，1已提交 ，2缺交，3 补交
     */
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("状态")
    private String zhuangzhongwen;

    /*
    * 评价分值
    */
    private Float fenzhi;
    /*
     * 评价等级   a+ 100.a90--99.5 ,b+85-89.5 b80-89.5  c70-79.5 d60-69.5 E<60
     */
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("评价等级")
    private String dengji;
    /*
    * 评语
    */
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("评语")
    private String pingyu;

    private String errorInfo;//错误信息

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getZhuangzhongwen() {
        return zhuangzhongwen;
    }

    public void setZhuangzhongwen(String zhuangzhongwen) {
        this.zhuangzhongwen = zhuangzhongwen;
    }

    public String getPingyu() {
        return pingyu;
    }

    public void setPingyu(String pingyu) {
        this.pingyu = pingyu;
    }

    public Float getFenzhi() {
        return fenzhi;
    }

    public void setFenzhi(Float fenzhi) {
        this.fenzhi = fenzhi;
    }

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModieTime() {
        return modieTime;
    }

    public void setModieTime(Date modieTime) {
        this.modieTime = modieTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getHomeWokeId() {
        return HomeWokeId;
    }

    public void setHomeWokeId(Integer homeWokeId) {
        HomeWokeId = homeWokeId;
    }

    public Integer getZhuantai() {
        return zhuantai;
    }

    public void setZhuantai(Integer zhuantai) {
        this.zhuantai = zhuantai;
    }
}
