package platform.education.paper.vo;

import platform.education.paper.model.PaQuestion;

/**
 * PaQuestion
 * 
 * @author AutoCreate
 *
 */
public class PaQuestionCondition extends PaQuestion {
	private static final long serialVersionUID = 1L;
	
	//难度查询
	private Float difficulityUpperBound;
	private Float difficulityLowerBound;
	public Float getDifficulityUpperBound() {
		return difficulityUpperBound;
	}
	public void setDifficulityUpperBound(Float difficulityUpperBound) {
		this.difficulityUpperBound = difficulityUpperBound;
	}
	public Float getDifficulityLowerBound() {
		return difficulityLowerBound;
	}
	public void setDifficulityLowerBound(Float difficulityLowerBound) {
		this.difficulityLowerBound = difficulityLowerBound;
	}


}