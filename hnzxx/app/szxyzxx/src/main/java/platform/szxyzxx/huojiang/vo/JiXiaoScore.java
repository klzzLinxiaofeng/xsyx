package platform.szxyzxx.huojiang.vo;

public class JiXiaoScore {
    /*
    * 主键id
    */
    private Integer id;
    /*
     * 身份名称
     */
    private String  name;
    /*
     * 级别
     */
    private Integer jibie;
    /*
     * 普通分数
     */
    private Integer putongscore;
    /*
     * 绩效分数
     */
    private Integer jixiaoScore;
    /*
     * 是否删除
     */
    private Integer isDelete;

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

    public Integer getJibie() {
        return jibie;
    }

    public void setJibie(Integer jibie) {
        this.jibie = jibie;
    }

    public Integer getPutongscore() {
        return putongscore;
    }

    public void setPutongscore(Integer putongscore) {
        this.putongscore = putongscore;
    }

    public Integer getJixiaoScore() {
        return jixiaoScore;
    }

    public void setJixiaoScore(Integer jixiaoScore) {
        this.jixiaoScore = jixiaoScore;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
