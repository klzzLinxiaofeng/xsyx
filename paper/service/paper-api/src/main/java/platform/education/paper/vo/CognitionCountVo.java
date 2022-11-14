package platform.education.paper.vo;

import java.io.Serializable;

/**
 * Created by panfei on 2017/5/15.
 */
public class CognitionCountVo implements Serializable{
    //认知度类型
    private String cognition;

    //认知度类型得分百分比
    private Float percent;

    public String getCognition() {
        return cognition;
    }

    public void setCognition(String cognition) {
        this.cognition = cognition;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }
}
