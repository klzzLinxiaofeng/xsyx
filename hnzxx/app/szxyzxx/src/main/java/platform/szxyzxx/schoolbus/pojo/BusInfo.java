package platform.szxyzxx.schoolbus.pojo;

import java.util.List;

/**
 * 校车信息
 */
public class BusInfo {

    /**
     * 校车车牌号码
     */
    private String schoolBusCard;
    /**
     * 校车编号
     */
    private String schoolBusNumber;
    /**
     * 路线编号
     */
    private String routeNum;
    /**
     * 路线名称
     */
    private String routeName;
    /**
     * 司机姓名
     */
    private String driverName;
    /**
     * 司机电话
     */
    private String driverPhone;
    /**
     * 照管员姓名
     */
    private String caretakerName;
    /**
     * 照管员电话
     */
    private String caretakerPhone;

    private String status;

    private String companyName;

    private List<StudentSignInfo> stuList;

    private String inTime;

    private String outTime;



    public String getSchoolBusCard() {
        return schoolBusCard;
    }

    public void setSchoolBusCard(String schoolBusCard) {
        this.schoolBusCard = schoolBusCard;
    }

    public String getSchoolBusNumber() {
        return schoolBusNumber;
    }

    public void setSchoolBusNumber(String schoolBusNumber) {
        this.schoolBusNumber = schoolBusNumber;
    }

    public String getRouteNum() {
        return routeNum;
    }

    public void setRouteNum(String routeNum) {
        this.routeNum = routeNum;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getCaretakerName() {
        return caretakerName;
    }

    public void setCaretakerName(String caretakerName) {
        this.caretakerName = caretakerName;
    }

    public String getCaretakerPhone() {
        return caretakerPhone;
    }

    public void setCaretakerPhone(String caretakerPhone) {
        this.caretakerPhone = caretakerPhone;
    }

    public List<StudentSignInfo> getStuList() {
        return stuList;
    }

    public void setStuList(List<StudentSignInfo> stuList) {
        this.stuList = stuList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
