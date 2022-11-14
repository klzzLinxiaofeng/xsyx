package com.xunyunedu.logger.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/10/31 15:08
 * @Version 1.0
 */
public class Loggers {
    /*
    * id
    */
    private Integer id;
    /*
     * 操作者id
     */
    private Integer caozuoId;
    /*
     * 操作者姓名
     */
    private String name;
    /*
     * 用户名
     */
    private String username;
    /*
     * 手机号
     */
    private String mobile;
    /*
     * 操作日期
     */
    private Date createTime;
    /*
     * 修改日期
     */
    private Date modiyTme;
    /*
     * 模块名称
     */
    private String mokuaiName;
    /*
    * 操作类型
    */
    private Integer type;
    private Integer isDelete;
    /*
    *描述
    */
   private String message;
   private String schoolYear;

   private String schoolTrem;
    public Loggers(){}
    public Loggers(Integer id, Integer caozuoId, String name, String username, String mobile, Date createTime, Date modiyTme, String mokuaiName, Integer type, Integer isDelete, String message, String schoolYear, String schoolTrem) {
        this.id = id;
        this.caozuoId = caozuoId;
        this.name = name;
        this.username = username;
        this.mobile = mobile;
        this.createTime = createTime;
        this.modiyTme = modiyTme;
        this.mokuaiName = mokuaiName;
        this.type = type;
        this.isDelete = isDelete;
        this.message = message;
        this.schoolYear = schoolYear;
        this.schoolTrem = schoolTrem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaozuoId() {
        return caozuoId;
    }

    public void setCaozuoId(Integer caozuoId) {
        this.caozuoId = caozuoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModiyTme() {
        return modiyTme;
    }

    public void setModiyTme(Date modiyTme) {
        this.modiyTme = modiyTme;
    }

    public String getMokuaiName() {
        return mokuaiName;
    }

    public void setMokuaiName(String mokuaiName) {
        this.mokuaiName = mokuaiName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getSchoolTrem() {
        return schoolTrem;
    }

    public void setSchoolTrem(String schoolTrem) {
        this.schoolTrem = schoolTrem;
    }
}
