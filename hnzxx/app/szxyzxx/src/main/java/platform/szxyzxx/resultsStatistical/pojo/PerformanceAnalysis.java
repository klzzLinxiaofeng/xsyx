package platform.szxyzxx.resultsStatistical.pojo;


import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.io.Serializable;

/**
 *
 * @TableName Performance_analysis
 */
public class PerformanceAnalysis implements Serializable {
    private Integer id;
    /**
     * 学年
     */

    private String xn;
    /**
     * 学期
     */
    private String xq;
    /**
     * 年级
     */
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("年级")
    private String grade;
    /**
     * 科目
     */
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("科目")
    private String subjects;
    /**
     * 考试类型
     */
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("考试类型")
    private String testName;
    /**
     * 平均分
     */
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("平均分")
    private String average;
    /**
     * 合格率
     */
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("合格率")
    private String percentPass;

    /**
     * 优秀率
     */
    @ExcelColumnImport(index =5)
    @ExcelColumnExport("优秀率")
    private String proficiency;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getPercentPass() {
        return percentPass;
    }

    public void setPercentPass(String percentPass) {
        this.percentPass = percentPass;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

    public PerformanceAnalysis(){}
    public PerformanceAnalysis(Integer id, String xn, String xq, String grade, String subjects, String testName, String average, String percentPass, String proficiency) {
        this.id = id;
        this.xn = xn;
        this.xq = xq;
        this.grade = grade;
        this.subjects = subjects;
        this.testName = testName;
        this.average = average;
        this.percentPass = percentPass;
        this.proficiency = proficiency;
    }
}
