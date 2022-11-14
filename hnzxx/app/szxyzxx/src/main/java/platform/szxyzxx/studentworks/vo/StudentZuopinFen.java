package platform.szxyzxx.studentworks.vo;

import java.util.Date;

/**
 * @Author zhangyong
 * @Date 2022/11/10 14:04
 * @Version 1.0
 */
public class StudentZuopinFen {
    private Integer id;
    private Integer zuoPinScore;
    private Date createTime;
    private Date modiyTime;
    private Integer isDelete;

    public StudentZuopinFen(){}
    public StudentZuopinFen(Integer id, Integer zuoPinScore, Date createTime, Date modiyTime, Integer isDelete) {
        this.id = id;
        this.zuoPinScore = zuoPinScore;
        this.createTime = createTime;
        this.modiyTime = modiyTime;
        this.isDelete = isDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getZuoPinScore() {
        return zuoPinScore;
    }

    public void setZuoPinScore(Integer zuoPinScore) {
        this.zuoPinScore = zuoPinScore;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
}
