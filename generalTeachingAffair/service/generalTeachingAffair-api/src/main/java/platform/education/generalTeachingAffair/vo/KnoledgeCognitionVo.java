package platform.education.generalTeachingAffair.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/6.
 */
public class KnoledgeCognitionVo implements Serializable {
    //考试ID
    private Integer examId;
    //识记
    private Float memorize;
    //理解
    private Float understand;
    //应用
    private Float apply;
    //探究
    private Float exploration;
    //综合
    private Float synthesis;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Float getMemorize() {
        return memorize;
    }

    public void setMemorize(Float memorize) {
        this.memorize = memorize;
    }

    public Float getUnderstand() {
        return understand;
    }

    public void setUnderstand(Float understand) {
        this.understand = understand;
    }

    public Float getApply() {
        return apply;
    }

    public void setApply(Float apply) {
        this.apply = apply;
    }

    public Float getExploration() {
        return exploration;
    }

    public void setExploration(Float exploration) {
        this.exploration = exploration;
    }

    public Float getSynthesis() {
        return synthesis;
    }

    public void setSynthesis(Float synthesis) {
        this.synthesis = synthesis;
    }
}
