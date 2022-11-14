package platform.education.service.vo;

import platform.education.service.model.Substitute;

/**
 * Substitute
 *
 * @author AutoCreate
 */
public class SubstituteCondition extends Substitute {
    private static final long serialVersionUID = 1L;
    private Boolean handled;

    public Boolean getHandled() {
        return handled;
    }

    public void setHandled(Boolean handled) {
        this.handled = handled;
    }

    private String kaishiTime;
    private String kaishiTime2;
    private String daiKeRen;

    public String getKaishiTime() {
        return kaishiTime;
    }

    public void setKaishiTime(String kaishiTime) {
        this.kaishiTime = kaishiTime;
    }

    public String getKaishiTime2() {
        return kaishiTime2;
    }

    public void setKaishiTime2(String kaishiTime2) {
        this.kaishiTime2 = kaishiTime2;
    }

    public String getDaiKeRen() {
        return daiKeRen;
    }

    public void setDaiKeRen(String daiKeRen) {
        this.daiKeRen = daiKeRen;
    }
}