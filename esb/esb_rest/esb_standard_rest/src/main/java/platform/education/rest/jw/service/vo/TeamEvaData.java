package platform.education.rest.jw.service.vo;

import java.io.Serializable;
import java.util.List;

import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;

public class TeamEvaData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//扣分项数据列表
	private List<TeamEvaScoreData> minusScoreList;
	//加分项数据列表
	private List<TeamEvaScoreData> addScoreList;
	//扣分总分
	private float minusScore;
	//加分总分
	private float addScore;

	public List<TeamEvaScoreData> getMinusScoreList() {
		return minusScoreList;
	}

	public void setMinusScoreList(List<TeamEvaScoreData> minusScoreList) {
		this.minusScoreList = minusScoreList;
	}

	public List<TeamEvaScoreData> getAddScoreList() {
		return addScoreList;
	}

	public void setAddScoreList(List<TeamEvaScoreData> addScoreList) {
		this.addScoreList = addScoreList;
	}

	public float getMinusScore() {
		return minusScore;
	}

	public void setMinusScore(float minusScore) {
		this.minusScore = minusScore;
	}

	public float getAddScore() {
		return addScore;
	}

	public void setAddScore(float addScore) {
		this.addScore = addScore;
	}
	
	
}
