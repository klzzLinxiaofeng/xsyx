package platform.szxyzxx.web.resource.vo;

/**
 *
 * @author 罗志明
 */
public class MyResourceVo {

    private Object resEnt;
    private String thumbnailUrl;
    private Integer iconType;
    private String entityId;
    private String learnDesignType;

   
	public String getLearnDesignType() {
		return learnDesignType;
	}

	public void setLearnDesignType(String learnDesignType) {
		this.learnDesignType = learnDesignType;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Object getResEnt() {
        return resEnt;
    }
 
    public void setResEnt(Object resEnt) {
        this.resEnt = resEnt;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getIconType() {
        return iconType;
    }

    public void setIconType(Integer iconType) {
        this.iconType = iconType;
    }
    
}
