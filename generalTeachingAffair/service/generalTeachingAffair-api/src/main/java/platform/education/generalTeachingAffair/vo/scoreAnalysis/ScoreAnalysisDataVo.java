package platform.education.generalTeachingAffair.vo.scoreAnalysis;

import java.io.Serializable;
/**
 * @功能描述: 成绩分析模板实体对象
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年1月31日下午7:44:00
 */
public class ScoreAnalysisDataVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	//排名
	private int rank; 
	//学生用户ID
	private Integer userId;
	//学生名
	private String name;
	//学生成绩
	private float score;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}

}
