package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;

import java.util.Date;

/**
 * 资源路径描述
 * @author edison
 */
public class ResPath implements Model<Integer> {

    /**
     * 主键
     */
    Integer id;

    /**
     * 路径名称
     */
    String name;

    /**
     * 路径
     */
    String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 路径使用的用户类型
     * {@link platform.education.user.model.Usertype}
     */
    String userTypeId;

    Date createDate;


    @Override
    public Integer getKey() {
        return this.id;
    }


}
