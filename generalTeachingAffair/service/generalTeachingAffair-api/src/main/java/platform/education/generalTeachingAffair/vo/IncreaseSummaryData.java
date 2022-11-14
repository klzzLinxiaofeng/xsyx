package platform.education.generalTeachingAffair.vo;
/**
 * 激励评价的汇总数据
 */
public class IncreaseSummaryData {
	
	private Integer objectId;
	
	private String objectName;
	
	private Integer count;
	
	private Float ratio;

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Float getRatio() {
		return ratio;
	}

	public void setRatio(Float ratio) {
		this.ratio = ratio;
	}
	
	
}
