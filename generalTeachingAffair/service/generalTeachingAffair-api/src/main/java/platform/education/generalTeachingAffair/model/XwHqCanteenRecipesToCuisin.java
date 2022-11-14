package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
import org.apache.ibatis.annotations.Param;

/**
 * 菜谱与菜系表中间表
 */

public class XwHqCanteenRecipesToCuisin implements Model<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * 表id
     */
    private Integer id;
    /**
     * 菜系表id
     */
    private Integer cuisinId;
    /**
     * 菜谱表id
     */
    private Integer recipesId;

    public String getIds() {
        return Ids;
    }

    public void setIds(String ids) {
        Ids = ids;
    }

    /**
     * 表中不存在 Ids
     */
    private String Ids;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCuisinId() {
        return cuisinId;
    }

    public void setCuisinId(Integer cuisinId) {
        this.cuisinId = cuisinId;
    }

    public Integer getRecipesId() {
        return recipesId;
    }

    public void setRecipesId(Integer recipesId) {
        this.recipesId = recipesId;
    }


    @Override
    public String toString() {
        return "XwHqCanteenRecipesToCuisin{" +
                "id=" + id +
                ", cuisinId=" + cuisinId +
                ", recipesId=" + recipesId +
                ", Ids='" + Ids + '\'' +
                '}';
    }

    @Override
    public Integer getKey() {
        return this.id;
    }


}
