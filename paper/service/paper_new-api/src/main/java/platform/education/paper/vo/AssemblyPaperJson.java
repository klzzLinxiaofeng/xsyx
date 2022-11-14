package platform.education.paper.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class AssemblyPaperJson implements Serializable {
	private static final long serialVersionUID = 1L;
	/** 大题模式 global(全局题号)、 group(分组题号) */
	private String model = "global";
	
	private AssemblySubject[] subject;

	private AssemblyGroupJson[] groups;
	
	private Float totalScore;
	
	private Integer questionSize;
	
	private String title;
	
	private String stageCode;
	
	private String stageName;
	
	private String type = "1";
	
	private Date modifyDate;
	
	private Date createDate;
	
	private Integer hasGroup = 1;
	
	private Date oldDate;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public AssemblySubject[] getSubject() {
		return subject;
	}

	public void setSubject(AssemblySubject[] subject) {
		this.subject = subject;
	}

	public Float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getQuestionSize() {
		return questionSize;
	}

	public void setQuestionSize(Integer questionSize) {
		this.questionSize = questionSize;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getHasGroup() {
		return hasGroup;
	}

	public void setHasGroup(Integer hasGroup) {
		this.hasGroup = hasGroup;
	}

	public AssemblyGroupJson[] getGroups() {
		return groups;
	}

	public void setGroups(AssemblyGroupJson[] groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "AssemblyPaperJson [model=" + model + ", subject=" + Arrays.toString(subject) + ", groups="
				+ Arrays.toString(groups) + ", totalScore=" + totalScore + ", questionSize=" + questionSize + ", title="
				+ title + ", stageCode=" + stageCode + ", stageName=" + stageName + ", type=" + type + ", modifyDate="
				+ modifyDate + ", createDate=" + createDate + ", hasGroup=" + hasGroup + "]";
	}

	public Date getOldDate() {
		return oldDate;
	}

	public void setOldDate(Date oldDate) {
		this.oldDate = oldDate;
	}
}
