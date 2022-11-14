package platform.education.rest.user.service.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 中间层登录实体类
 * @author pantq
 * 
 *
 */
public class MesosphereUserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String name;
	private String iconUrl;
	private Integer schoolId;
	private String schoolName;
	private List<MesosphereTeamInfo> teamList;
	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 仅评师业务用到,老师当前日期所拥有的角色 0.既不是班主任也不是科任教师，1 班主任  2科任教师 3 既是班主任也是科任教师
	 */
	private Integer teacherType;
	public Integer getTeacherType() {
		return teacherType;
	}
	public void setTeacherType(Integer teacherType) {
		this.teacherType = teacherType;
	}
	//角色列表
	private List<String>roleStrList;
	
	public List<String> getRoleStrList() {
		return roleStrList;
	}
	public void setRoleStrList(List<String> roleStrList) {
		this.roleStrList = roleStrList;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public List<MesosphereTeamInfo> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<MesosphereTeamInfo> teamList) {
		this.teamList = teamList;
	}
	
}
