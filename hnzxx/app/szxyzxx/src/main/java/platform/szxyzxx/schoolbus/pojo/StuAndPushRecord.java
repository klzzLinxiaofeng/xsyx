package platform.szxyzxx.schoolbus.pojo;

import java.util.List;

public class StuAndPushRecord {

    private String stuId;

    private Integer userId;

    private String openId;

    private String name;

    List<BusStuPushRecord> pushRecordList;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BusStuPushRecord> getPushRecordList() {
        return pushRecordList;
    }

    public void setPushRecordList(List<BusStuPushRecord> pushRecordList) {
        this.pushRecordList = pushRecordList;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
