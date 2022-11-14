package platform.education.paper.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/3.
 */
public class KnowledgeCountVo implements Serializable{
    //知识点ID
    private Integer knowledgeId;

    //知识点ID
    private String knowledgeName;

    //该知识点在当前试卷的分数
    private Float scoreCount;

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public Float getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(Float scoreCount) {
        this.scoreCount = scoreCount;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }
}
