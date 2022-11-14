package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/6.
 */
public class KnolwdgeDiffVo implements Serializable{
    //考试ID
    private Integer examId;
    //知识点ID
    private Integer knowledgeId;
    //知识点名称
    private Integer knowledgeName;
    //容易率
    private Float easyScore;
    //中等率
    private Float mediumScore;
    //困难率
    private Float difficultyScore;
    //满分总分
    private Float fullScore;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Integer getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(Integer knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Float getEasyScore() {
        return easyScore;
    }

    public void setEasyScore(Float easyScore) {
        this.easyScore = easyScore;
    }

    public Float getMediumScore() {
        return mediumScore;
    }

    public void setMediumScore(Float mediumScore) {
        this.mediumScore = mediumScore;
    }

    public Float getDifficultyScore() {
        return difficultyScore;
    }

    public void setDifficultyScore(Float difficultyScore) {
        this.difficultyScore = difficultyScore;
    }

    public Float getFullScore() {
        return fullScore;
    }

    public void setFullScore(Float fullScore) {
        this.fullScore = fullScore;
    }
}
