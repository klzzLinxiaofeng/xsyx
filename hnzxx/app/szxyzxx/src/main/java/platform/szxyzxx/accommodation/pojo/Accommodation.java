package platform.szxyzxx.accommodation.pojo;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Accommodation {
    /*
    * 主键id
    */
    private Integer id;
    /*
     * 上报人id
     */
    private Integer teacherUserId;
    /*
     * 上报人Name
     */
    @ExcelColumnImport(index =0)
    @ExcelColumnExport("上报人")
    private String teacherName;
    //上报时间
    @ExcelColumnImport(index =2)
    @ExcelColumnExport("上报时间")
    private String shangBaoTime;
    private Date shangBaoTime2;
    /*
     * 房室号
     */
    @ExcelColumnImport(index =1)
    @ExcelColumnExport("房室号")
    private String fangshiHao;
    /*
     * 附件id
     */
    private String fujianUUID;
    /*
     *详情
     */
    @ExcelColumnImport(index =3)
    @ExcelColumnExport("详情")
    private String tontent;
    /*
    * 住房面积
    */
    @ExcelColumnImport(index =4)
    @ExcelColumnExport("住房面积（平方米）")
    private String area;
    /*
     * 热水表
     */
    @ExcelColumnImport(index =5)
    @ExcelColumnExport("热水度数")
    private Integer hotWater;
    /*
    * 热水表度数图片
    */
    private String hotPictureUUid;
    /*
     * 冷水表
     */
    @ExcelColumnImport(index =6)
    @ExcelColumnExport("冷水度数")
    private Integer  coldWater;
    /*
     * 冷水表度数图片
     */
    private String coldPictureUUid;
    /*
     * 电表
     */
    @ExcelColumnImport(index =7)
    @ExcelColumnExport("用电度数")
    private Integer electricity;
    /*
     * 电表度数图片
     */
    private String electricityUUid;
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
    * 附件map
    */
    private List<Map<String,Object>> fujianMap;
    /*
    * 热水map
    */
    private List<Map<String,Object>> reshuiMap;
    /*
    *冷水map
    */
    private List<Map<String,Object>> lenshuiMap;
    /*
     *电表map
     */
    private List<Map<String,Object>> dianbiaoMap;

    public List<Map<String, Object>> getFujianMap() {
        return fujianMap;
    }

    public void setFujianMap(List<Map<String, Object>> fujianMap) {
        this.fujianMap = fujianMap;
    }

    public List<Map<String, Object>> getReshuiMap() {
        return reshuiMap;
    }

    public void setReshuiMap(List<Map<String, Object>> reshuiMap) {
        this.reshuiMap = reshuiMap;
    }

    public List<Map<String, Object>> getLenshuiMap() {
        return lenshuiMap;
    }

    public void setLenshuiMap(List<Map<String, Object>> lenshuiMap) {
        this.lenshuiMap = lenshuiMap;
    }

    public List<Map<String, Object>> getDianbiaoMap() {
        return dianbiaoMap;
    }

    public void setDianbiaoMap(List<Map<String, Object>> dianbiaoMap) {
        this.dianbiaoMap = dianbiaoMap;
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

    public Integer getTeacherUserId() {
        return teacherUserId;
    }

    public void setTeacherUserId(Integer teacherUserId) {
        this.teacherUserId = teacherUserId;
    }

    public String getShangBaoTime() {
        return shangBaoTime;
    }

    public void setShangBaoTime(String shangBaoTime) {
        this.shangBaoTime = shangBaoTime;
    }

    public Date getShangBaoTime2() {
        return shangBaoTime2;
    }

    public void setShangBaoTime2(Date shangBaoTime2) {
        this.shangBaoTime2 = shangBaoTime2;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getHotWater() {
        return hotWater;
    }

    public void setHotWater(Integer hotWater) {
        this.hotWater = hotWater;
    }

    public String getHotPictureUUid() {
        return hotPictureUUid;
    }

    public void setHotPictureUUid(String hotPictureUUid) {
        this.hotPictureUUid = hotPictureUUid;
    }

    public Integer getColdWater() {
        return coldWater;
    }

    public void setColdWater(Integer coldWater) {
        this.coldWater = coldWater;
    }

    public String getColdPictureUUid() {
        return coldPictureUUid;
    }

    public void setColdPictureUUid(String coldPictureUUid) {
        this.coldPictureUUid = coldPictureUUid;
    }

    public Integer getElectricity() {
        return electricity;
    }

    public void setElectricity(Integer electricity) {
        this.electricity = electricity;
    }

    public String getElectricityUUid() {
        return electricityUUid;
    }

    public void setElectricityUUid(String electricityUUid) {
        this.electricityUUid = electricityUUid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFangshiHao() {
        return fangshiHao;
    }

    public void setFangshiHao(String fangshiHao) {
        this.fangshiHao = fangshiHao;
    }

    public String getFujianUUID() {
        return fujianUUID;
    }

    public void setFujianUUID(String fujianUUID) {
        this.fujianUUID = fujianUUID;
    }


    public String getTontent() {
        return tontent;
    }

    public void setTontent(String tontent) {
        this.tontent = tontent;
    }
}
