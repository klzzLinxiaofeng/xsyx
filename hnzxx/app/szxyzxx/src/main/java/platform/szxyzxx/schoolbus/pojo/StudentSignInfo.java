package platform.szxyzxx.schoolbus.pojo;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 学生以及打卡信息
 */
public class StudentSignInfo{
    private Integer stuId;
    private Integer stuUserId;
    private String name;
    private boolean used;
    private String headPicture;
    private Date leaveStartDate;
    private Date leaveEndDate;
    private List<BusStudentSign> signList;
    private BusInfo busInfo;
    private String lastCarNo;
    private Integer mId;

    private String date;
    private String stuStatus;
    private String upTime;
    private String upPlace;
    private String downTime;
    private String downPlace;
    private Boolean parentPicked;
    private String teamName;
    private String lastByCarNo;
    private GatePickInfo gatePickInfo;

    private String inStatus;
    private String outStatus;

    private String goToType;

    private String outType;


    private String parentInTime;
    private String parentOutTime;
    private String parentInOutCarNo;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BusStudentSign> getSignList() {
        return signList;
    }

    public void setSignList(List<BusStudentSign> signList) {
        this.signList = signList;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public BusInfo getBusInfo() {
        return busInfo;
    }

    public void setBusInfo(BusInfo busInfo) {
        this.busInfo = busInfo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStuStatus() {
        return stuStatus;
    }

    public void setStuStatus(String stuStatus) {
        this.stuStatus = stuStatus;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getUpPlace() {
        return upPlace;
    }

    public void setUpPlace(String upPlace) {
        this.upPlace = upPlace;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getDownPlace() {
        return downPlace;
    }

    public void setDownPlace(String downPlace) {
        this.downPlace = downPlace;
    }

    public Boolean getParentPicked() {
        return parentPicked;
    }

    public void setParentPicked(Boolean parentPicked) {
        this.parentPicked = parentPicked;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLastByCarNo() {
        return lastByCarNo;
    }

    public void setLastByCarNo(String lastByCarNo) {
        this.lastByCarNo = lastByCarNo;
    }

    public Integer getStuUserId() {
        return stuUserId;
    }

    public void setStuUserId(Integer stuUserId) {
        this.stuUserId = stuUserId;
    }

    public GatePickInfo getGatePickInfo() {
        return gatePickInfo;
    }

    public void setGatePickInfo(GatePickInfo gatePickInfo) {
        this.gatePickInfo = gatePickInfo;
    }

    public String getLastCarNo() {
        return lastCarNo;
    }

    public void setLastCarNo(String lastCarNo) {
        this.lastCarNo = lastCarNo;
    }

    public String getGoToType() {
        return goToType;
    }

    public void setGoToType(String goToType) {
        this.goToType = goToType;
    }

    public String getInStatus() {
        return inStatus;
    }

    public void setInStatus(String inStatus) {
        this.inStatus = inStatus;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType;
    }

    public Integer getmId() {
        return mId;
    }

    public void setmId(Integer mId) {
        this.mId = mId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSignInfo that = (StudentSignInfo) o;
        return Objects.equals(stuId, that.stuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stuId);
    }


    public String getParentInTime() {
        return parentInTime;
    }

    public void setParentInTime(String parentInTime) {
        this.parentInTime = parentInTime;
    }

    public String getParentOutTime() {
        return parentOutTime;
    }

    public void setParentOutTime(String parentOutTime) {
        this.parentOutTime = parentOutTime;
    }

    public String getParentInOutCarNo() {
        return parentInOutCarNo;
    }

    public void setParentInOutCarNo(String parentInOutCarNo) {
        this.parentInOutCarNo = parentInOutCarNo;
    }
}
