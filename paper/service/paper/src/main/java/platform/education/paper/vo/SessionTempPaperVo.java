/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.paper.vo;

/**
 *
 * @author Administrator
 */
public class SessionTempPaperVo {

    public String paperId= "";
    public String paperType= "";
    public String title= "";
    public String difficulity= "";
    public String subjectCode= "";
    public String publishCode= "";
    public String gradeCode= "";
    public String volumeCode= "";
    public String unitCode= "";
    public String sectionCode= "";
    public String questionsJson = "[]";
    //js字符串数组
    public String questionIds = "[]";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(String questionIds) {
        this.questionIds = questionIds;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getDifficulity() {
        return difficulity;
    }

    public void setDifficulity(String difficulity) {
        this.difficulity = difficulity;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getPublishCode() {
        return publishCode;
    }

    public void setPublishCode(String publishCode) {
        this.publishCode = publishCode;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode;
    }

    public String getVolumeCode() {
        return volumeCode;
    }

    public void setVolumeCode(String volumeCode) {
        this.volumeCode = volumeCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getPaperType() {
        return paperType;
    }

    public void setPaperType(String paperType) {
        this.paperType = paperType;
    }

    public String getQuestionsJson() {
        return questionsJson;
    }

    public void setQuestionsJson(String questionsJson) {
        this.questionsJson = questionsJson;
    }
}
