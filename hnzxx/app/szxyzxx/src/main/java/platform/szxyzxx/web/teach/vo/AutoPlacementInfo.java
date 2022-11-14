package platform.szxyzxx.web.teach.vo;

/**
 * 自动分班相关属性
 * @author admin
 *
 */
public class AutoPlacementInfo {

	/**
	 *没有分班的学生人数
	 */
	private String noClassStuNum;
	
	/**
	 * 男生数量
	 */
	private String manNum;
	
	/**
	 * 女生数量
	 */
	private String womNum;
	/**
	 * 男生所占百分比
	 */
	private String manPercentNot;
	/**
	 * 女生所占百分比
	 */
	private String wonPercentNot;
	/**
	 * 学段
	 */
	private String stageCode;
	/**
	 * 年级ID
	 */
	private String gradeId;
	/**
	 * 每个人班人数
	 */
	private String perTeamNum;
	/**
	 * 剩余人数
	 */
	private String lastNum;
	public String getNoClassStuNum() {
		return noClassStuNum;
	}
	public void setNoClassStuNum(String noClassStuNum) {
		this.noClassStuNum = noClassStuNum;
	}
	public String getManNum() {
		return manNum;
	}
	public void setManNum(String manNum) {
		this.manNum = manNum;
	}
	public String getWomNum() {
		return womNum;
	}
	public void setWomNum(String womNum) {
		this.womNum = womNum;
	}
	public String getManPercentNot() {
		return manPercentNot;
	}
	public void setManPercentNot(String manPercentNot) {
		this.manPercentNot = manPercentNot;
	}
	public String getWonPercentNot() {
		return wonPercentNot;
	}
	public void setWonPercentNot(String wonPercentNot) {
		this.wonPercentNot = wonPercentNot;
	}
	public String getStageCode() {
		return stageCode;
	}
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public String getPerTeamNum() {
		return perTeamNum;
	}
	public void setPerTeamNum(String perTeamNum) {
		this.perTeamNum = perTeamNum;
	}
	public String getLastNum() {
		return lastNum;
	}
	public void setLastNum(String lastNum) {
		this.lastNum = lastNum;
	}
	
}
