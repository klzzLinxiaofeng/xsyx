package com.xunyunedu.visit.pojo;

import java.io.Serializable;

/**
 * visit_visit_apply_user
 * @author 
 */
public class VisitVisitApplyUser implements Serializable {
    private Integer id;

    /**
     * 访问申请表id
     */
    private Integer applyId;

    /**
     * 访问者姓名
     */
    private String visitorName;

    /**
     * 访问者身份证号
     */
    private String visitorId;

    /**
     * 访问者电话号码
     */
    private String visitorPhone;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }
}