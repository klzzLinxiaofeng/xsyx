package platform.szxyzxx.schoolbus.pojo;

import java.util.Objects;

/**
 * 门禁打卡信息
 */
public class GatePickInfo {

    private String personId;

    /**
     * 打卡门禁点
     */
    private String doorName;
    /**
     * 打卡时间
     */
    private String eventTime;

    /**
     * 进出方向（1：进，0：出）
     */
    private Integer inAndOutType;

    /**
     * 设备标识 门禁点唯一标识
     */
    private String doorIndexCode;

    public String getDoorIndexCode() {
        return doorIndexCode;
    }

    public void setDoorIndexCode(String doorIndexCode) {
        this.doorIndexCode = doorIndexCode;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public Integer getInAndOutType() {
        return inAndOutType;
    }

    public void setInAndOutType(Integer inAndOutType) {
        this.inAndOutType = inAndOutType;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GatePickInfo that = (GatePickInfo) o;
        return Objects.equals(personId, that.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId);
    }

    @Override
    public String toString() {
        return "GatePickInfo{" +
                "personId='" + personId + '\'' +
                ", doorName='" + doorName + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", inAndOutType=" + inAndOutType +
                ", doorIndexCode='" + doorIndexCode + '\'' +
                '}';
    }
}
