package platform.education.generalTeachingAffair.vo;
/**
 * 课堂优化的汇总数据
 */
public class ClassOptimizingSummaryData {
	/**
	 * 汇总项目id（项目、班级、年级）
	 */
	private Integer objectId;
	/**
	 * 汇总项目名
	 */
	private String objectName;
	/**
	 * 统计次数
	 */
	private Integer count;
	/**
	 * 百分比
	 */
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
