package platform.szxyzxx.web.bbx.client.vo;

public class ClientWelcomeVo {

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 学校id（pj_school.id）
	 */
	private Integer schoolId;
	/**  
	 *  班级id（pj_team.id）
	 */
	private Integer teamId;
	/**
	 * 播放背景类型   默认为2 type=1：模板 type=2：颜色码
	 */
	private String backgroundType;
	/**
	 * 播放背景十六进制颜色码   默认为#ffffff  例如：#FF000
	 */
	private String backgroundColor;
	/**
	 * 当前使用背景文件的uuid（bw_template.file_uuid）
	 */
	private String fileUuid;
	
	
	/**
	 * 欢迎词当前使用模板路径 
	 */
	private String fileUrl;
	
	
	/**
	 * 欢迎词
	 */
	private String content;
	/**
	 * 播放速度(0-10)默认为0
	 */
	private Integer playSpeed;
	/**
	 * 字体大小  (5-100)默认为10
	 */
	private Integer fontSize;
	/**
	 * 字体颜色 默认为#000000
	 */
	private String fontColor;
	/**
	 * 字体背景颜色 默认为null
	 */
	private String fontBackcolor;
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
	public String getBackgroundType() {
		return backgroundType;
	}
	public void setBackgroundType(String backgroundType) {
		this.backgroundType = backgroundType;
	}
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getFileUuid() {
		return fileUuid;
	}
	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPlaySpeed() {
		return playSpeed;
	}
	public void setPlaySpeed(Integer playSpeed) {
		this.playSpeed = playSpeed;
	}
	public Integer getFontSize() {
		return fontSize;
	}
	public void setFontSize(Integer fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public String getFontBackcolor() {
		return fontBackcolor;
	}
	public void setFontBackcolor(String fontBackcolor) {
		this.fontBackcolor = fontBackcolor;
	}
	
	
	
	
	
}
