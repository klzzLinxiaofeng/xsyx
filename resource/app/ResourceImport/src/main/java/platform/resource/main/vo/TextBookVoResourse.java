package platform.resource.main.vo;

import java.util.List;

public class TextBookVoResourse {
	
	private List<TextBookCatalogVoResourse> voCatalogVoResourseList;
	private String message;
	public static String ERROR="教材添加错误";
	public static String SUCCESS="教材添加成功";
	public static String BEFORESUCCESS="教材已添加过";
	public static String CATALOGERROR="教材添加错误";
	private boolean isDownload;//数据库是否存在这条教材目录
	private String name;//书名
	private String stageCode;//学段
	private String subjectCode;//科目
	private String gradeCode;//年级
	private String volumn;//册次
	private String version;//版本
	private Integer id;
	private boolean doneBefore;
	private boolean isWrong;
	private String path;
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isWrong() {
		return isWrong;
	}
	public void setWrong(boolean isWrong) {
		this.isWrong = isWrong;
	}
	public boolean isDoneBefore() {
		return doneBefore;
	}
	public void setDoneBefore(boolean doneBefore) {
		this.doneBefore = doneBefore;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStageCode() {
		return stageCode;
	}
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	public String getVolumn() {
		return volumn;
	}
	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<TextBookCatalogVoResourse> getVoCatalogVoResourseList() {
		return voCatalogVoResourseList;
	}
	public void setVoCatalogVoResourseList(
			List<TextBookCatalogVoResourse> voCatalogVoResourseList) {
		this.voCatalogVoResourseList = voCatalogVoResourseList;
	}
	public boolean isDownload() {
		return isDownload;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	
}
