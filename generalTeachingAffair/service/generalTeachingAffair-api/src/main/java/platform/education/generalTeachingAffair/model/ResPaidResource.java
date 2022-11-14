package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 付费资源
 * @author edison
 */
public class ResPaidResource implements Model<Integer> {

    Integer id;

    /**
     * 标题
     */
    String title;

    /**
     * 描述
     */
    String description;

    /**
     * 文件关联的id
     */
    String entityId;

    /**
     * 价格
     */
    BigDecimal price;

    /**
     * 做关联的id
     */
    String uuid;

    /**
     * 上传者的id
     */
    Integer userId;

    /**
     * 创建时间
     */
    Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Override
    public Integer getKey() {
        return this.id;
    }
}
