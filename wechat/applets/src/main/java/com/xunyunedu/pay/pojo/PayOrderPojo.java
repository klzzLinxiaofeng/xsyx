package com.xunyunedu.pay.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表
 *
 * @author: yhc
 * @Date: 2020/10/22 11:24
 * @Description:
 */
public class PayOrderPojo implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 订单ID
     */
    private Long id;

    /**
     * 所在学校pj_shool.id
     */
    private Integer schoolId;

    /**
     * 订购用户ID 如果为家长端,需要获取家长下的某个孩子id,如果为教师端获取id
     */
    private Integer userId;

    /**
     * 用户类型 1: 学生(家校通端) 2:教师(教师端)
     */
    private Integer userType;

    /**
     * 用户登录之后的openid
     */
    private String openid;

    /**
     * 用户账号
     */
    private String empCode;
    /**
     * 用户名称
     */
    private String empName;
    /**
     * 食堂卡号
     */
    private String empCard;

    /**
     * 订单流水号
     */
    private String orderNumber;

    /**
     * 微信支付订单号
     */
    private String payNo;

    /**
     * 支付金额id
     */
    private Integer payAmountId;

    /**
     * 支付金额，单位：元
     */
    private Double payAmount;

    /**
     * 支付方式 0 手动代付 1 微信支付 2 支付宝
     */
    private Integer payType;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 付款完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 是否支付 2: 支付失败 , 1：已经支付，0：没有支付(支付失败,取消支付,正在支付)
     */
    private Integer isPayed;

    /**
     * 下单状态: 1 成功 0 失败
     */
    private Integer placeOrderState;

    /**
     * 订单是否发送到远程接口，1：已发送(发送成功)，0：没有发送(包含发送失败)
     */
    private Integer isSendOrder;
    /*
    * 0：账户充值 ，1，选课支付
    */
    public Integer type;

    /*
     * 0：待支付 ，1，已支付
     */
    public Integer payStatus;

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPlaceOrderState() {
        return placeOrderState;
    }

    public void setPlaceOrderState(Integer placeOrderState) {
        this.placeOrderState = placeOrderState;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public Integer getPayAmountId() {
        return payAmountId;
    }

    public void setPayAmountId(Integer payAmountId) {
        this.payAmountId = payAmountId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public Integer getIsSendOrder() {
        return isSendOrder;
    }

    public void setIsSendOrder(Integer isSendOrder) {
        this.isSendOrder = isSendOrder;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", schoolId=").append(schoolId);
        sb.append(", userId=").append(userId);
        sb.append(", empCode=").append(empCode);
        sb.append(", empName=").append(empName);
        sb.append(", empCard=").append(empCard);
        sb.append(", openid=").append(openid);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", bizPayNo=").append(payNo);
        sb.append(", payAmountId=").append(payAmountId);
        sb.append(", payType=").append(payType);
        sb.append(", createTime=").append(createTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", placeOrderState=").append(placeOrderState);
        sb.append(", isSendOrder=").append(isSendOrder);
        sb.append("]");
        return sb.toString();
    }
}