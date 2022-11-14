package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

public class TeamScoreDataVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//班级id
	private Integer teamId;
	//项目id
	private Integer itemId;
	//项目名称
	private String itemName;
	//得分
	private Float score;
	
	private String code;
	
	private String remark;
	
	//private List<String> fileUUIDs;
	private List<ScoreFile> fileUUIDs;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<ScoreFile> getFileUUIDs() {
		return fileUUIDs;
	}

	public void setFileUUIDs(List<ScoreFile> fileUUIDs) {
		this.fileUUIDs = fileUUIDs;
	}

}
