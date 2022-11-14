package com.xunyunedu.schoolbus.pojo;

/**
 * 打卡信息
 */
public class PickInfo {
    /**
     * 刷卡类型（0:校车刷卡，1:门禁刷卡）
     */
    private Integer type;

    /**
     * 刷卡状态
     */
    private String status;

    /**
     * 刷卡位置
     */
    private String place;
    /**
     * 刷卡时间
     */
    private String time;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
