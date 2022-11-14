package com.xunyunedu.development.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/11 9:34
 * @Version 1.0
 * 品德发展指标
 */
public class DevelopmentIndicators {
    /*
    * id
    */
    private Integer id;
    /*
     * 指标名称
     */
    private String name;
    /*
    *评价人员角色
    * */
    private String code;

    /*
    * 指标最大分值
    */
    private Integer score;
    /*
    * 创建时间
    */
    private Date createTime;
    /*
    * 修改时间
    */
    private Date modiyTime;
    /*
    * 是否删除
    */
    private Integer isDelete;
    /*
    * 学年
    */
    private String xn;
    /*
     * 学期
     */
    private String xq;

    public String getXn() {
        return xn;
    }

    public void setXn(String xn) {
        this.xn = xn;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTime() {
        return modiyTime;
    }

    public void setModiyTime(Date modiyTime) {
        this.modiyTime = modiyTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
