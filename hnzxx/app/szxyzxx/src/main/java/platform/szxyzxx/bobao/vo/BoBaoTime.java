package platform.szxyzxx.bobao.vo;

public class BoBaoTime {
    /*
    * 播报时间id
    */
    private  Integer id;
    /*
     * 播报设备号
     */
    private String haoma;
    /*
     * 播报开始时间  数据库保存时间戳
     */
    private String startTime;
    /*
     * 播报结束时间
     */
    private String endTime;
    /*
     * 播报时间是否删除 0 ，1
     */
    private Integer isDelete;

    public BoBaoTime(){}

    public BoBaoTime(Integer id, String haoma, String startTime, String endTime, Integer isDelete) {
        this.id = id;
        this.haoma = haoma;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "BoBaoTime{" +
                "id=" + id +
                ", haoma='" + haoma + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHaoma() {
        return haoma;
    }

    public void setHaoma(String haoma) {
        this.haoma = haoma;
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
