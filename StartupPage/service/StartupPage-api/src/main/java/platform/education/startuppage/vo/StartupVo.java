package platform.education.startuppage.vo;

import platform.education.startuppage.model.Startup;

public class StartupVo extends Startup {
    private static final long serialVersionUID = 1L;
    private String thumUrl;

    public StartupVo() {
    }

    public String getThumUrl() {
        return this.thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }
}
