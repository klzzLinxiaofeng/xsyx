package platform.education.sysbanner.vo;

import platform.education.sysbanner.model.SysBanner;

public class SysBannerVo extends SysBanner {
    private static final long serialVersionUID = 1L;
    private String thumUrl;

    public SysBannerVo() {
    }

    public String getThumUrl() {
        return this.thumUrl;
    }

    public void setThumUrl(String thumUrl) {
        this.thumUrl = thumUrl;
    }
}
