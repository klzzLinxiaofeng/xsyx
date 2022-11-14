package platform.education.generalTeachingAffair.model;
import framework.generic.dao.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * xw_hq_canteen_recipes
 * @author 
 */

public class XwHqCanteenRecipes implements Model<Integer> {
    private static final long serialVersionUID = 1L;
    public String getcuisinId;
    private Integer id;

    public String getGetcuisinId() {
        return getcuisinId;
    }

    public void setGetcuisinId(String getcuisinId) {
        this.getcuisinId = getcuisinId;
    }

    /**
     * 所属学校
     */
    private Integer schoolId;



    /**
     * 食谱详情
     */
    private String description;

    /**
     * 0未删除  1已经删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;
    /**
     * jsp日期
     */
    private String date;
    /**
     * 菜系list
     */
    private String cxList;

    /**
     * 菜系表ids
     */
    private String ids;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCxList() {
        return cxList;
    }

    public void setCxList(String cxList) {
        this.cxList = cxList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 菜系名称，只在实体类中，实际表中不存在
     */
    private String cuisineName;

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }
    /**
     * 菜系id集合，只在实体类中，实际表中不存在
     */
    private String cuisineIds;

    public String getCuisineIds() {
        return cuisineIds;
    }

    public void setCuisineIds(String cuisineIds) {
        this.cuisineIds = cuisineIds;
    }

    @Override
    public String toString() {
        return "XwHqCanteenRecipes{" +
                "getcuisinId='" + getcuisinId + '\'' +
                ", id=" + id +
                ", schoolId=" + schoolId +
                ", description='" + description + '\'' +
                ", isDelete=" + isDelete +
                ", createDate=" + createDate +
                ", modifyDate=" + modifyDate +
                ", date='" + date + '\'' +
                ", cxList='" + cxList + '\'' +
                ", ids='" + ids + '\'' +
                ", cuisineName='" + cuisineName + '\'' +
                ", cuisineIds='" + cuisineIds + '\'' +
                '}';
    }

    @Override
    public Integer getKey() {
        return this.id;
    }

}