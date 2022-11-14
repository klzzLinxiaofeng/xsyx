package platform.szxyzxx.kexuanke.vo;

public class GradeKeXuanKe {
    private Integer gradeId;
    private String gradeName;
    private Integer zhuanTai;
    public GradeKeXuanKe(){}
    public GradeKeXuanKe(Integer gradeId, String gradeName, Integer zhuanTai) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.zhuanTai = zhuanTai;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Integer getZhuanTai() {
        return zhuanTai;
    }

    public void setZhuanTai(Integer zhuanTai) {
        this.zhuanTai = zhuanTai;
    }
}
