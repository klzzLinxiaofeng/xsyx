package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.PjExam;

import java.io.Serializable;

/**
 * Created by clouds on 17/3/21.
 */
public class ExamResult implements Serializable {

    private PjExam pjExam;

    private String status;

    public PjExam getPjExam() {
        return pjExam;
    }

    public void setPjExam(PjExam pjExam) {
        this.pjExam = pjExam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
