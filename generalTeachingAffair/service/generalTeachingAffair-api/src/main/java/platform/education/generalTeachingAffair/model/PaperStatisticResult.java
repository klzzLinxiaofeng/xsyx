/**   
* @Title: PaperStatisticResult.java
* @Package platform.education.generalTeachingAffair.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月23日 下午9:14:27 
* @version V1.0   
*/
package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/** 
* @ClassName: PaperStatisticResult 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年3月23日 下午9:14:27 
*  
*/
public class PaperStatisticResult implements Serializable{

	 
	private static final long serialVersionUID = 1L;
	
	//名次
	private Integer examId;
		
	//名次
	private Integer rank;
	
	//班级ID
	private Integer teamId;
	
	//班级名称
	private String teamName;
	
	//平均分
	private Float averageScore;
	
	//应答人数
	private Integer studentCount ;

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the averageScore
	 */
	public Float getAverageScore() {
		
		if(averageScore == null){
			rank = 0;
		}
		return averageScore;
	}

	/**
	 * @param averageScore the averageScore to set
	 */
	public void setAverageScore(Float averageScore) {
		this.averageScore = averageScore;
	}

	/**
	 * @return the studentCount
	 */
	public Integer getStudentCount() {
		
		if(studentCount == null){
			studentCount =  0;
		}
		return studentCount;
	}

	/**
	 * @param studentCount the studentCount to set
	 */
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}

	/**
	 * @return the teamId
	 */
	public Integer getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getExamId() {
		return examId;
	}

	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	@Override
	public String toString() {
		return "{examId:'" + examId + "', rank:'" + rank
				+ "', teamId:'" + teamId + "', teamName:'" + teamName
				+ "', averageScore:'" + averageScore + "', studentCount:'"
				+ studentCount + "'}";
	}
	
}
