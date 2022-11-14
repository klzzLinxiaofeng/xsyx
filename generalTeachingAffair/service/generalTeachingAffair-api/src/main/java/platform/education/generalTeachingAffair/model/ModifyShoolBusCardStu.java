package platform.education.generalTeachingAffair.model;

/**
 * @author: yhc
 * @Date: 2021/4/7 14:55
 * @Description: 需要修改卡号的学生信息
 */
public class ModifyShoolBusCardStu {
    private Integer id;

    private String empCard;

    /**
     * 学生信息是否发送到昊吉顺校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
     */
    private Integer isSendSchoolBusHjs;
    /**
     * 学生信息是否发送到国盛校车，1：已发送(发送成功)，0：没有发送(包含发送失败，失败也需要重试)，2：修改卡号的学生，需要将新卡号发送到校车
     */
    private Integer isSendSchoolBusGs;

    public Integer getIsSendSchoolBusHjs() {
        return isSendSchoolBusHjs;
    }

    public void setIsSendSchoolBusHjs(Integer isSendSchoolBusHjs) {
        this.isSendSchoolBusHjs = isSendSchoolBusHjs;
    }

    public Integer getIsSendSchoolBusGs() {
        return isSendSchoolBusGs;
    }

    public void setIsSendSchoolBusGs(Integer isSendSchoolBusGs) {
        this.isSendSchoolBusGs = isSendSchoolBusGs;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpCard() {
        return empCard;
    }

    public void setEmpCard(String empCard) {
        this.empCard = empCard;
    }

}
