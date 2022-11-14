package platform.szxyzxx.schoolbus.pojo;

import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/4/9 14:32
 *  @Description: 海康门禁请求事件
 */
public class HikDoorRequestData {


    /**
     * startTime : 2018-08-10T12:00:00+08:00
     * endTime : 2018-08-11T12:00:00+08:00
     * eventType : 1
     * personName : 1
     * personIds : ["1"]
     * doorName : 1
     * doorIndexCodes : ["1f276203e5234bdca08f7d99e1097bba"]
     * doorRegionIndexCode : 3f9512ec067248dfa0679cf4a1634800
     * sort : personName
     * order : acs
     * pageNo : 1
     * pageSize : 100
     */

    private String startTime;
    private String endTime;
    private Integer eventType;
    private String personName;
    private String doorName;
    private String doorRegionIndexCode;
    private String sort;
    private String order;
    private int pageNo;
    private int pageSize;
    private List<String> personIds;
    private List<String> doorIndexCodes;


    public HikDoorRequestData(String startTime, String endTime, int pageNo, int pageSize, List<String> personIds) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.personIds = personIds;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getDoorRegionIndexCode() {
        return doorRegionIndexCode;
    }

    public void setDoorRegionIndexCode(String doorRegionIndexCode) {
        this.doorRegionIndexCode = doorRegionIndexCode;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<String> getPersonIds() {
        return personIds;
    }

    public void setPersonIds(List<String> personIds) {
        this.personIds = personIds;
    }

    public List<String> getDoorIndexCodes() {
        return doorIndexCodes;
    }

    public void setDoorIndexCodes(List<String> doorIndexCodes) {
        this.doorIndexCodes = doorIndexCodes;
    }
}
