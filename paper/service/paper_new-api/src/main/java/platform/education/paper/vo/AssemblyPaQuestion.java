package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.List;

import platform.education.paper.vo.PaQuestionVo;

public class AssemblyPaQuestion {
	private String groupName;
	private String strPos;
	private Integer pos;
	private Float score;
	List<PaQuestionVo> questionList = new ArrayList<PaQuestionVo>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getStrPos() {
		return strPos;
	}

	public void setStrPos(String strPos) {
		this.strPos = strPos;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public List<PaQuestionVo> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<PaQuestionVo> questionList) {
		this.questionList = questionList;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
}
