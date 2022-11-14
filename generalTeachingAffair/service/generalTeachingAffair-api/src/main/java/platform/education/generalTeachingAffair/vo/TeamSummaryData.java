package platform.education.generalTeachingAffair.vo;

public class TeamSummaryData implements Comparable{
	//排名
	private Integer rank;
	
	private Integer objectId;

	private String objectName;
	//总分		
	private Float totalScore;
	//加分								
	private Float addScore;
	//减分
	private Float deductScore;
	
	//加分项百分比
	private Float addRatio;
	//减分项百分比
	private Float deductRatio;
	//排序（teamNumber/gradeNumber)
	private Integer sequence;
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
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
	public Float getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}
	public Float getAddScore() {
		return addScore;
	}
	public void setAddScore(Float addScore) {
		this.addScore = addScore;
	}
	public Float getDeductScore() {
		return deductScore;
	}
	public void setDeductScore(Float deductScore) {
		this.deductScore = deductScore;
	}
	public Float getAddRatio() {
		return addRatio;
	}
	public void setAddRatio(Float addRatio) {
		this.addRatio = addRatio;
	}
	public Float getDeductRatio() {
		return deductRatio;
	}
	public void setDeductRatio(Float deductRatio) {
		this.deductRatio = deductRatio;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	@Override
	public int compareTo(Object o) {
		TeamSummaryData data = (TeamSummaryData) o;
		int n = totalScore.compareTo(data.getTotalScore());
		return (n != 0 ? -n : sequence.compareTo(data.getSequence()));
	}
	
}
