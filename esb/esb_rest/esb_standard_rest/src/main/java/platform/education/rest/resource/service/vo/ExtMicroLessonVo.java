package platform.education.rest.resource.service.vo;

import java.util.List;

import platform.education.micro.vo.MicroLessonBgpictureVo;

public class ExtMicroLessonVo {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String createDate;
	private String modifyDate;
	private String title;
	private String description;
	private String uuid;
	private String type;
	private Integer userId;
	private Integer appId;
	private String entityId;
	private Double playLength;
	private String subjectName;
	private String subjectCode;
	private String md5;
	private String httpUrl;
	private String thumbUrl;
	private String name;
	private String entityPath;
	/**
	 * 播放次数
	 */
	private Integer playNumber;
	/**
	 * 微课星资源时长
	 */
	private Long lessonLength;
	private String serverName;
	
	public String getEntityPath() {
		return entityPath;
	}
	public void setEntityPath(String entityPath) {
		this.entityPath = entityPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Double getPlayLength() {
		return playLength;
	}
	public void setPlayLength(Double playLength) {
		this.playLength = playLength;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public String getHttpUrl() {
		return httpUrl;
	}
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	public Long getLessonLength() {
		return lessonLength;
	}
	public void setLessonLength(Long lessonLength) {
		this.lessonLength = lessonLength;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public Integer getPlayNumber() {
		return playNumber;
	}
	public void setPlayNumber(Integer playNumber) {
		this.playNumber = playNumber;
	}
}
