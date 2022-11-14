package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.PjAptsTaskJudge;
import platform.education.generalTeachingAffair.model.PjAptsTaskScore;

import java.util.List;

/**
 * PjAptsTaskJudge
 * @author AutoCreate
 *
 */
public class PjAptsTaskJudgeVo extends PjAptsTaskJudge {
	private static final long serialVersionUID = 1L;

	private List<PjAptsTaskScore> scores;

	private  String name;

	private  Integer num;

	private String descriptionWithNewline;

	public String getDescriptionWithNewline() {
		return descriptionWithNewline;
	}

	public void setDescriptionWithNewline(String descriptionWithNewline) {
		this.descriptionWithNewline = descriptionWithNewline;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PjAptsTaskScore> getScores() {
		return scores;
	}

	public void setScores(List<PjAptsTaskScore> scores) {
		this.scores = scores;
	}
}