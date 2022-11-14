package platform.szxyzxx.ishangkelilu.pojo;

import java.util.Date;

public class ZuoWeiHangLie {
    private Integer id;
    private Integer teamId;
    private Integer isDelete;
    private Date createTime;
    private Date modelTime;
    private Integer zuNumber;
    private Integer hangNumber;
    private Integer lieNumber;



    public ZuoWeiHangLie(){}

    public ZuoWeiHangLie(Integer id, Integer teamId, Integer isDelete, Date createTime, Date modelTime, Integer zuNumber, Integer hangNumber, Integer lieNumber) {
        this.id = id;
        this.teamId = teamId;
        this.isDelete = isDelete;
        this.createTime = createTime;
        this.modelTime = modelTime;
        this.zuNumber = zuNumber;
        this.hangNumber = hangNumber;
        this.lieNumber = lieNumber;
    }

    public Integer getZuNumber() {
        return zuNumber;
    }

    public void setZuNumber(Integer zuNumber) {
        this.zuNumber = zuNumber;
    }

    public Integer getHangNumber() {
        return hangNumber;
    }

    public void setHangNumber(Integer hangNumber) {
        this.hangNumber = hangNumber;
    }

    public Integer getLieNumber() {
        return lieNumber;
    }

    public void setLieNumber(Integer lieNumber) {
        this.lieNumber = lieNumber;
    }

    @Override
    public String toString() {
        return "ZuoWeiHangLie{" +
                "id=" + id +
                ", teamId=" + teamId +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", modelTime=" + modelTime +
                ", zuNumber=" + zuNumber +
                ", hangNumber=" + hangNumber +
                ", lieNumber=" + lieNumber +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModelTime() {
        return modelTime;
    }

    public void setModelTime(Date modelTime) {
        this.modelTime = modelTime;
    }
}
