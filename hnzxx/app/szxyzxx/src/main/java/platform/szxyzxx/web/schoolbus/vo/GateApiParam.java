package platform.szxyzxx.web.schoolbus.vo;


public class GateApiParam {

    private String userId;

    private Integer schoolDirection;

    private Integer doorDirection;

    private String date;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getSchoolDirection() {
        return schoolDirection;
    }

    public void setSchoolDirection(Integer schoolDirection) {
        this.schoolDirection = schoolDirection;
    }

    public Integer getDoorDirection() {
        return doorDirection;
    }

    public void setDoorDirection(Integer doorDirection) {
        this.doorDirection = doorDirection;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
