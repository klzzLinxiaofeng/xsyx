package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/16.
 * 获取典型错题丢分率
 */
public class ExamErrorVo implements Serializable{
    //题号
    private Integer pos;

    //题型
    private String type;

    //知识点ID
    private Integer knowledgeId;

    //知识点名称
    private String knowledgeName;

    //丢分率
    private Float errorPercent;

    private String questionUuid;

    public String getQuestionUuid() {
        return questionUuid;
    }

    public void setQuestionUuid(String questionUuid) {
        this.questionUuid = questionUuid;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Float getErrorPercent() {
        return errorPercent;
    }

    public void setErrorPercent(Float errorPercent) {
        this.errorPercent = errorPercent;
    }
}
