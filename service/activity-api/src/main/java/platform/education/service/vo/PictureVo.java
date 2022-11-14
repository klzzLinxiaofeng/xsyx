package platform.education.service.vo;

import java.io.Serializable;

public class PictureVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private String name;
    private String url;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
