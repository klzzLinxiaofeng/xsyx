package platform.education.service.vo;

import platform.education.service.model.Substitute;

/**
 * Substitute
 *
 * @author AutoCreate
 */
public class SubstituteVo extends Substitute {
    private static final long serialVersionUID = 1L;

    private String accessoryName;
    private String accessoryUrl;

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public String getAccessoryUrl() {
        return accessoryUrl;
    }

    public void setAccessoryUrl(String accessoryUrl) {
        this.accessoryUrl = accessoryUrl;
    }
}