package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.ExamQuestion;

/**
 * ExamQuestion
 * @author AutoCreate
 *
 */
public class ExamQuestionVo extends ExamQuestion {
	private static final long serialVersionUID = 1L;
	
		private String teamName;
		
		private Float rightRate;
		
		private Float finishRate;
		
		private Integer teamNumber;
		

		public Integer getTeamNumber() {
			return teamNumber;
		}

		public void setTeamNumber(Integer teamNumber) {
			this.teamNumber = teamNumber;
		}

		public String getTeamName() {
			return teamName;
		}

		public void setTeamName(String teamName) {
			this.teamName = teamName;
		}

		public Float getRightRate() {
			return rightRate;
		}

		public void setRightRate(Float rightRate) {
			this.rightRate = rightRate;
		}

		public Float getFinishRate() {
			return finishRate;
		}

		public void setFinishRate(Float finishRate) {
			this.finishRate = finishRate;
		}

		@Override
		public String toString() {
			return "{teamName:'" + teamName + "', rightRate:'"+ rightRate + "', finishRate:'" + finishRate + "'}";
		}
		
		
}