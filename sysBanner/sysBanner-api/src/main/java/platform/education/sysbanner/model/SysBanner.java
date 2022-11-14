//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package platform.education.sysbanner.model;

import framework.generic.dao.Model;

import java.util.Date;

public class SysBanner implements Model<Integer> {
    private static final long serialVersionUID = 1L;
    /**
     * id
     *
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;
    /**
     * 缩略图url
     */
    private String url;
    /**
     * 详细
     */
    private String description;
    /**
     *缩略图entity_id
     */
    private String entityId;
    /**
     *是否推送(0:推送，1:不推送)，默认不推送
     */
    private String pushState;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyDate;

    public SysBanner() {
    }

    public SysBanner(Integer id) {
        this.id = id;
    }

    public Integer getKey() {
        return this.id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityId() {
        return this.entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getPushState() {
        return this.pushState;
    }

    public void setPushState(String pushState) {
        this.pushState = pushState;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return this.modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
