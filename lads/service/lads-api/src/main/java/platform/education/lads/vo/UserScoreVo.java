/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.vo;

/**
 * 用户各模块的成绩和学习进度vo
 * @author Administrator
 */
public class UserScoreVo {

    private LadsUserVo userVo;
    private double editorScore = 0;
    private double faceteachingScore = 0;
    private double discussScore = 0;
    private double quizScore = 0;
    private double surveyScore = 0;
    private double powerpointScore = 0 ;
    private double mediaScore = 0;
    private int finishedPercent = 0;

    public double getEditorScore() {
        return editorScore;
    }

    public void setEditorScore(double editorScore) {
        this.editorScore = editorScore;
    }

    public double getFaceteachingScore() {
        return faceteachingScore;
    }

    public void setFaceteachingScore(double faceteachingScore) {
        this.faceteachingScore = faceteachingScore;
    }

    public double getDiscussScore() {
        return discussScore;
    }

    public void setDiscussScore(double discussScore) {
        this.discussScore = discussScore;
    }

    public double getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(double quizScore) {
        this.quizScore = quizScore;
    }

    public double getSurveyScore() {
        return surveyScore;
    }

    public void setSurveyScore(double surveyScore) {
        this.surveyScore = surveyScore;
    }


    public LadsUserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(LadsUserVo userVo) {
        this.userVo = userVo;
    }

    public double getPowerpointScore() {
        return powerpointScore;
    }

    public void setPowerpointScore(double powerpointScore) {
        this.powerpointScore = powerpointScore;
    }

    public double getMediaScore() {
        return mediaScore;
    }

    public void setMediaScore(double mediaScore) {
        this.mediaScore = mediaScore;
    }

    public int getFinishedPercent() {
        return finishedPercent;
    }

    public void setFinishedPercent(int finishedPercent) {
        this.finishedPercent = finishedPercent;
    }

    
    
    
}
