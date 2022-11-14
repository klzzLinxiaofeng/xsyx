package platform.education.oa.model;

import java.util.Date;

import platform.education.oa.utils.UUIDUtils;
import platform.education.oa.utils.UtilDate;
import framework.generic.dao.Model;
/**
 * Meeting
 * @author AutoCreate
 *
 */
public class Meeting implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 创建人id
	 */
	private Integer createuserId;
	/**
	 * 创建人名字
	 */
	private String createname;
	/**
	 * 创建人头像
	 */
	private String userimage;
	/**
	 * 部门ID
	 */
	private Integer departmentId;
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 * 结束时间
	 */
	private String endtime;
	/**
	 * 会议名称
	 */
	private String meetingName;
	/**
	 * 会议类别,1:重要，0:一般
	 */
	private Integer meetingType;
	/**
	 * 会议地点
	 */
	private String address;
	/**
	 * 主持人
	 */
	private String zhuchi;
	/**
	 * 会议说明
	 */
	private String meetingContent;
	/**
	 * 1：全部人参加，0：选择的人参加
	 */
	private Integer quanbu=0;
	
	private Integer summaryId;
	
	/**
	 * UUID
	 */
	private String uuid=UUIDUtils.getUUID();
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	/**
	 * 可见范围 ，0：只有参加的人可见，1：本部门的人可见，2：全部的人可见
	 */
	private Integer fanwei;
	
	private Integer meetingNum=0;
	
	private String uploadFile;
	
	public String getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(String uploadFile) {
		this.uploadFile = uploadFile;
	}

	public Meeting() {
		
	}
	
	public Meeting(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取学校ID
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校ID
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取创建人id
	 * @return java.lang.Integer
	 */
	public Integer getCreateuserId() {
		return this.createuserId;
	}
	
	/**
	 * 设置创建人id
	 * @param createuserId
	 * @type java.lang.Integer
	 */
	public void setCreateuserId(Integer createuserId) {
		this.createuserId = createuserId;
	}
	
	/**
	 * 获取创建人名字
	 * @return java.lang.String
	 */
	public String getCreatename() {
		return this.createname;
	}
	
	/**
	 * 设置创建人名字
	 * @param createname
	 * @type java.lang.String
	 */
	public void setCreatename(String createname) {
		this.createname = createname;
	}
	
	/**
	 * 获取创建人头像
	 * @return java.lang.String
	 */
	public String getUserimage() {
		return this.userimage;
	}
	
	/**
	 * 设置创建人头像
	 * @param userimage
	 * @type java.lang.String
	 */
	public void setUserimage(String userimage) {
		this.userimage = userimage;
	}
	
	/**
	 * 获取部门ID
	 * @return java.lang.Integer
	 */
	public Integer getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置部门ID
	 * @param departmentId
	 * @type java.lang.Integer
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	/**
	 * 获取开始时间
	 * @return java.lang.String
	 */
	public String getStarttime() {
		return this.starttime;
	}
	
	/**
	 * 设置开始时间
	 * @param starttime
	 * @type java.lang.String
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	
	/**
	 * 获取结束时间
	 * @return java.lang.String
	 */
	public String getEndtime() {
		return this.endtime;
	}
	
	/**
	 * 设置结束时间
	 * @param endtime
	 * @type java.lang.String
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	/**
	 * 获取会议名称
	 * @return java.lang.String
	 */
	public String getMeetingName() {
		return this.meetingName;
	}
	
	/**
	 * 设置会议名称
	 * @param meetingName
	 * @type java.lang.String
	 */
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}
	
	/**
	 * 获取会议类别,1:重要，0:一般
	 * @return java.lang.Integer
	 */
	public Integer getMeetingType() {
		return this.meetingType;
	}
	
	/**
	 * 设置会议类别,1:重要，0:一般
	 * @param meetingType
	 * @type java.lang.Integer
	 */
	public void setMeetingType(Integer meetingType) {
		this.meetingType = meetingType;
	}
	
	/**
	 * 获取会议地点
	 * @return java.lang.String
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * 设置会议地点
	 * @param address
	 * @type java.lang.String
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取主持人
	 * @return java.lang.String
	 */
	public String getZhuchi() {
		return this.zhuchi;
	}
	
	/**
	 * 设置主持人
	 * @param zhuchi
	 * @type java.lang.String
	 */
	public void setZhuchi(String zhuchi) {
		this.zhuchi = zhuchi;
	}
	
	/**
	 * 获取会议说明
	 * @return java.lang.String
	 */
	public String getMeetingContent() {
		return this.meetingContent;
	}
	
	/**
	 * 设置会议说明
	 * @param meetingContent
	 * @type java.lang.String
	 */
	public void setMeetingContent(String meetingContent) {
		this.meetingContent = meetingContent;
	}
	
	/**
	 * 获取1：全部人参加，0：选择的人参加
	 * @return java.lang.Integer
	 */
	public Integer getQuanbu() {
		return this.quanbu;
	}
	
	/**
	 * 设置1：全部人参加，0：选择的人参加
	 * @param quanbu
	 * @type java.lang.Integer
	 */
	public void setQuanbu(Integer quanbu) {
		this.quanbu = quanbu;
	}
	
	
	
	
	public Integer getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Integer summaryId) {
		this.summaryId = summaryId;
	}

	/**
	 * 获取UUID
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置UUID
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
	public Integer getFanwei() {
		return fanwei;
	}

	public void setFanwei(Integer fanwei) {
		this.fanwei = fanwei;
	}
	
	
	

	public Integer getMeetingNum() {
		return meetingNum;
	}

	public void setMeetingNum(Integer meetingNum) {
		this.meetingNum = meetingNum;
	}

	public String getType(){
		String type="一般";
		if(meetingType==1){
			type="重要";
		}
		return type;
	}
	
	public String getState(){
		String state="未开始";
		int time=UtilDate.getLeftDayTime(UtilDate.getDateFormatters(starttime, "yyyy-MM-dd HH:mm:ss"), new Date());
 
		if(time>=0){
			int etime=UtilDate.getLeftDayTime(UtilDate.getDateFormatters(endtime, "yyyy-MM-dd HH:mm:ss"), new Date());
			if(etime<0){
				state="进行中";
			}else{
				state="已结束";
			}
		}
		return state;
	}
}