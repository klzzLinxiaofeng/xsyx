package platform.education.oa.model;

import java.util.UUID;

import platform.education.oa.utils.UUIDUtils;
import framework.generic.dao.Model;
/**
 * Leave
 * @author AutoCreate
 *
 */
public class Leave implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 创建人名字
	 */
	private String createname;
	/**
	 * 创建人id
	 */
	private Integer createuserId;
	/**
	 * 请假天数
	 */
	private Float day;
	/**
	 * 部门ID
	 */
	private String departmentId;
	/**
	 * 开始时间
	 */
	private String starttime;
	/**
	 *  结束时间
	 */
	private String endtime;
	/**
	 * kewuanpai
	 */
	private String kewuanpai;
	/**
	 * 请假说明
	 */
	private String leaveinfo;
	/**
	 * 0为正在审批中，1为同意，2为不同意
	 */
	private Integer leavestate=0;
	/**
	 * 请假类型
	 */
	private String leavetype;
	/**
	 * 创建人头像
	 */
	private String userimage;
	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 审批人名字列表
	 */
	private String selApprName;
	/**
	 * 抄送人名字列表
	 */
	private String selCcName;
	
	private String uuid=UUIDUtils.getUUID();
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
 
	
	public Leave() {
		
	}
	
	public Leave(Integer id) {
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
	 * 获取请假天数
	 * @return java.lang.Float
	 */
	public Float getDay() {
		return this.day;
	}
	
	/**
	 * 设置请假天数
	 * @param day
	 * @type java.lang.Float
	 */
	public void setDay(Float day) {
		this.day = day;
	}
	
	/**
	 * 获取部门ID
	 * @return java.lang.String
	 */
	public String getDepartmentId() {
		return this.departmentId;
	}
	
	/**
	 * 设置部门ID
	 * @param departmentId
	 * @type java.lang.String
	 */
	public void setDepartmentId(String departmentId) {
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
	 * 获取 结束时间
	 * @return java.lang.String
	 */
	public String getEndtime() {
		return this.endtime;
	}
	
	/**
	 * 设置 结束时间
	 * @param endtime
	 * @type java.lang.String
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
	/**
	 * 获取kewuanpai
	 * @return java.lang.String
	 */
	public String getKewuanpai() {
		return this.kewuanpai;
	}
	
	/**
	 * 设置kewuanpai
	 * @param kewuanpai
	 * @type java.lang.String
	 */
	public void setKewuanpai(String kewuanpai) {
		this.kewuanpai = kewuanpai;
	}
	
	/**
	 * 获取请假说明
	 * @return java.lang.String
	 */
	public String getLeaveinfo() {
		return this.leaveinfo;
	}
	
	/**
	 * 设置请假说明
	 * @param leaveinfo
	 * @type java.lang.String
	 */
	public void setLeaveinfo(String leaveinfo) {
		this.leaveinfo = leaveinfo;
	}
	
	/**
	 * 获取0为正在审批中，1为同意，2为不同意
	 * @return java.lang.Integer
	 */
	public Integer getLeavestate() {
		return this.leavestate;
	}
	
	/**
	 * 设置0为正在审批中，1为同意，2为不同意
	 * @param leavestate
	 * @type java.lang.Integer
	 */
	public void setLeavestate(Integer leavestate) {
		this.leavestate = leavestate;
	}
	
	/**
	 * 获取请假类型
	 * @return java.lang.String
	 */
	public String getLeavetype() {
		return this.leavetype;
	}
	
	/**
	 * 设置请假类型
	 * @param leavetype
	 * @type java.lang.String
	 */
	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
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
	 * 获取审批人名字列表
	 * @return java.lang.String
	 */
	public String getSelApprName() {
		return this.selApprName;
	}
	
	/**
	 * 设置审批人名字列表
	 * @param selApprName
	 * @type java.lang.String
	 */
	public void setSelApprName(String selApprName) {
		this.selApprName = selApprName;
	}
	
	/**
	 * 获取抄送人名字列表
	 * @return java.lang.String
	 */
	public String getSelCcName() {
		return this.selCcName;
	}
	
	/**
	 * 设置抄送人名字列表
	 * @param selCcName
	 * @type java.lang.String
	 */
	public void setSelCcName(String selCcName) {
		this.selCcName = selCcName;
	}
	
	
	
	
	
	public String getUuid() {
		return uuid;
	}

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
	 
	/**
	 * 逻辑关联审批结果
	 */
	
	  private LeaveAppr apprs;
	  
	    public LeaveAppr getApprs() {
	        return apprs;
	    }
	    public void setApprs(LeaveAppr apprs) {
	        this.apprs = apprs;
	    }
	
}