package platform.education.rest.resource.service.vo;

import java.io.Serializable;

import net.sf.json.JSONArray;

public class ExtMicroLessonRelate implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 5545706201218414059L;
	
	private Integer id;
	private String createDate;
	private String modifyDate;
	private Integer relateId;
	private String publishMicroLessonId;
	private String relateName;
	private String title;
	private String startDate;
	private String finishedDate;
	private Integer publisherId;
	private String publisherName;
	private JSONArray realMicroList;
	private String relateType;
	
	private Integer finishedCount;
	private Integer unFinishedCount;
	/**
     * 微课结束时间判断是否完成标记
     */
    private Boolean finishFlag;
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
	public Integer getRelateId() {
		return relateId;
	}
	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}
	public String getPublishMicroLessonId() {
		return publishMicroLessonId;
	}
	public void setPublishMicroLessonId(String publishMicroLessonId) {
		this.publishMicroLessonId = publishMicroLessonId;
	}
	public String getRelateName() {
		return relateName;
	}
	public void setRelateName(String relateName) {
		this.relateName = relateName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishedDate() {
		return finishedDate;
	}
	public void setFinishedDate(String finishedDate) {
		this.finishedDate = finishedDate;
	}
	public Integer getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public JSONArray getRealMicroList() {
		return realMicroList;
	}
	public void setRealMicroList(JSONArray realMicroList) {
		this.realMicroList = realMicroList;
	}
	public String getRelateType() {
		return relateType;
	}
	public void setRelateType(String relateType) {
		this.relateType = relateType;
	}
	public Integer getFinishedCount() {
		return finishedCount;
	}
	public void setFinishedCount(Integer finishedCount) {
		this.finishedCount = finishedCount;
	}
	public Integer getUnFinishedCount() {
		return unFinishedCount;
	}
	public void setUnFinishedCount(Integer unFinishedCount) {
		this.unFinishedCount = unFinishedCount;
	}
	public Boolean getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(Boolean finishFlag) {
		this.finishFlag = finishFlag;
	}
	
}
