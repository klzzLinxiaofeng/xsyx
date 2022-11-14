package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/5.
 * 知识点得分率统计相关VO
 */
public class ScoringAverageVo implements Serializable {
    //知识点ID
    private Integer knowledgeId;
    //知识点名称
    private String knowledgeName;
    //得分百分比
    private Float percent;
    //examID
    private Integer examId;
    //类型
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }
}
