package platform.education.generalTeachingAffair.vo;
/**
 * @function 用于成绩统计页面的数据模型
 * @author hmzhang
 * @date 2016年1月26日
 */
public class CommonScoreManagement {
	/**
	 * 平均分
	 */
	private float avg_score;
	
	/**
	 * 最高分
	 */
	private float max_score;
	
	/**
	 * 最低分
	 */
	private float min_score;
	
	/**
	 * 标准差
	 */
	private float ad_score;
	
	/**
	 * 考试总人数
	 */
	private Long totalStudent;
	
	/**
	 * 极差
	 */
	private float mov_value;
	
	/**
	 * 补考人数
	 */
	private Long nextExamStudent;
	
	/**
	 * 离差
	 */
	private float mad_value;

	public float getAvg_score() {
		return avg_score;
	}

	public void setAvg_score(float avg_score) {
		this.avg_score = avg_score;
	}

	public float getMax_score() {
		return max_score;
	}

	public void setMax_score(float max_score) {
		this.max_score = max_score;
	}

	public float getMin_score() {
		return min_score;
	}

	public void setMin_score(float min_score) {
		this.min_score = min_score;
	}

	public float getAd_score() {
		return ad_score;
	}

	public void setAd_score(float ad_score) {
		this.ad_score = ad_score;
	}

	public Long getTotalStudent() {
		return totalStudent;
	}

	public void setTotalStudent(Long totalStudent) {
		this.totalStudent = totalStudent;
	}

	public float getMov_value() {
		return mov_value;
	}

	public void setMov_value(float mov_value) {
		this.mov_value = mov_value;
	}

	public Long getNextExamStudent() {
		return nextExamStudent;
	}

	public void setNextExamStudent(Long nextExamStudent) {
		this.nextExamStudent = nextExamStudent;
	}

	public float getMad_value() {
		return mad_value;
	}

	public void setMad_value(float mad_value) {
		this.mad_value = mad_value;
	}
	
}
