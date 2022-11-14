package com.xunyunedu.mergin.vo;

import platform.education.generalTeachingAffair.model.Person;

public class PersonCondition extends Person {
    private static final long serialVersionUID = 1L;

    /**
     * 是否使用模糊查询，默认为否，添加于2015-11-19
     */
    private Boolean nameLike = false;
    /**
     * 是否使用模糊查询，默认为否，添加于2015-11-19
     */
    private Boolean idCardNumberLike;

    public Boolean getNameLike() {
        return nameLike;
    }
    public void setNameLike(Boolean nameLike) {
        this.nameLike = nameLike;
    }
    public Boolean getIdCardNumberLike() {
        return idCardNumberLike;
    }
    public void setIdCardNumberLike(Boolean idCardNumberLike) {
        this.idCardNumberLike = idCardNumberLike;
    }




}
