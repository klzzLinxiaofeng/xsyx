package platform.szxyzxx.wuyu.pindemoban.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/14 13:05
 * @Version 1.0
 */
public class PinDeMoBan {
    private Integer id;
    /*
    * 评语内容
    */
    private String text;
    /*
     * 类型id
     */
    private Integer zhibiaoId;
    /*
     * 评语名称
     */
    private String zhibiaoName;

    /*
     * 评语类型id
     */
    private Integer pingYuId;

    /*
     * 评语类型名称
     */
    private String pingYuTypeName;

    private Date createTime;

    /*
    * 评价等级 1，2，3 大于，等于，小于
    **/
    private Integer dengji;

    private Date modiyTime;

    private Integer isDelete;

    private String schoolYear;

    private String schoolTrem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getZhibiaoId() {
        return zhibiaoId;
    }

    public void setZhibiaoId(Integer zhibiaoId) {
        this.zhibiaoId = zhibiaoId;
    }

    public String getZhibiaoName() {
        return zhibiaoName;
    }

    public void setZhibiaoName(String zhibiaoName) {
        this.zhibiaoName = zhibiaoName;
    }

    public Integer getPingYuId() {
        return pingYuId;
    }

    public void setPingYuId(Integer pingYuId) {
        this.pingYuId = pingYuId;
    }

    public String getPingYuTypeName() {
        return pingYuTypeName;
    }

    public void setPingYuTypeName(String pingYuTypeName) {
        this.pingYuTypeName = pingYuTypeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDengji() {
        return dengji;
    }

    public void setDengji(Integer dengji) {
        this.dengji = dengji;
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
