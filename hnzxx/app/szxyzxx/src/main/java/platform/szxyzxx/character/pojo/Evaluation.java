package platform.szxyzxx.character.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/*
 * 评价指标表
 */
public class Evaluation {
    /*
    * 主键 id
    */
    private Integer id;
    /*
     * 评价指标名称
     */
    private String name;
    /*
     * 初始分值
     */
    private Integer initScore;
    /*
     * 是否删除
     */
    private Integer isDelete;
    /*
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getInitScore() {
        return initScore;
    }

    public void setInitScore(Integer initScore) {
        this.initScore = initScore;
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

    public Evaluation(Integer id, String name, Integer initScore, Integer isDelete, Date createTime) {
        this.id = id;
        this.name = name;
        this.initScore = initScore;
        this.isDelete = isDelete;
        this.createTime = createTime;
    }
    public Evaluation(){}

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initScore=" + initScore +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                '}';
    }
}
