package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;
import java.util.Map;

/**
 * 班级表扬客户端vo
 * @author huchuhan
 *
 */
public class PraiseClientVo {
	
	private Integer id;
	// 学校id
	private Integer schoolId;
	// 班级id
	private Integer teamId;
	// 表扬内容
	private String content;
	// 勋章id
	private Integer medalId;
	// 勋章名字
	private String medalRank;
	// 表扬人员信息:Id,姓名和头像[{'id':xxx,'name':xxx,'iconUrl':xxx}]
	private List<Map<String, Object>> students;
	// 发布表扬的老师名字
	private String posterName;
	private String postTime;
	
	private String teamName;
	//修改时间
	private String modifyTime;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMedalRank() {
		return medalRank;
	}
	public void setMedalRank(String medalRank) {
		this.medalRank = medalRank;
	}
	public List<Map<String, Object>> getStudents() {
		return students;
	}
	public void setStudents(List<Map<String, Object>> students) {
		this.students = students;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	public Integer getMedalId() {
		return medalId;
	}
	public void setMedalId(Integer medalId) {
		this.medalId = medalId;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}
	
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
}
