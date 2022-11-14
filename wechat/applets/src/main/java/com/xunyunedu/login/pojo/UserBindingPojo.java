package com.xunyunedu.login.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户绑定
 *
 * @author: yhc
 * @Date: 2020/12/2 17:44
 * @Description:
 */
public class UserBindingPojo implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 关联用户账号表的id
     */
    private Integer userId;

    /**
     * 绑定的用户账号名
     */
    private String bindingName;

    /**
     * 绑定账号的来源类型  0=官方代码, 1=手机号码 , 2=邮件地址
     */
    private String bindingType;

    /**
     * 是否激活
     */
    private Boolean enabled;

    /**
     * 删除标记
     */
    private Boolean isDeleted;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date modifyDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBindingName() {
        return bindingName;
    }

    public void setBindingName(String bindingName) {
        this.bindingName = bindingName;
    }

    public String getBindingType() {
        return bindingType;
    }

    public void setBindingType(String bindingType) {
        this.bindingType = bindingType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
}

