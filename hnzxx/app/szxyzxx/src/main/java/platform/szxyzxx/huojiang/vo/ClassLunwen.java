package platform.szxyzxx.huojiang.vo;

public class ClassLunwen extends HuoJiang implements Cloneable {
    /*
    * 得分
    */
    private Integer score;
    /*
     * 绩效得分
     */
    private Integer jiXiaoDeFen;

    /*
    * 工号
    */
    private String  empCode;

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getJiXiaoDeFen() {
        return jiXiaoDeFen;
    }

    public void setJiXiaoDeFen(Integer jiXiaoDeFen) {
        this.jiXiaoDeFen = jiXiaoDeFen;
    }

    @Override
    public ClassLunwen clone(){
        ClassLunwen employee = null;
        try {
            employee = (ClassLunwen)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return employee;
    }


}
