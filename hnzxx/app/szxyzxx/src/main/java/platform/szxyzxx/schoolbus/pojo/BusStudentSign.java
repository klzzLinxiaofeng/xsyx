package platform.szxyzxx.schoolbus.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * bus_student_sign
 * @author 
 */
public class BusStudentSign implements Serializable {
    private Integer id;

    /**
     * 学生校园卡卡号
     */
    private String stuCard;

    /**
     * 校车车牌
     */
    private String schoolBusCard;

    /**
     * 打卡日期时间
     */
    private String signTime;

    /**
     * 打卡日期
     */
    private String signDate;

    /**
     * 方向（0：上学，1：放学）
     */
    private Integer direction;

    /**
     * 打卡站点名称
     */
    private String signAddress;

    /**
     * 请求的ip地址
     */
    private String requestIp;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuCard() {
        return stuCard;
    }

    public void setStuCard(String stuCard) {
        this.stuCard = stuCard;
    }

    public String getSchoolBusCard() {
        return schoolBusCard;
    }

    public void setSchoolBusCard(String schoolBusCard) {
        this.schoolBusCard = schoolBusCard;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}