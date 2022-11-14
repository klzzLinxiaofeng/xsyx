package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.List;

public class GroupParameter {
	private String groupName;
	private Integer pos;
	private List<QuestionParameter> questions = new ArrayList<QuestionParameter>();

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public List<QuestionParameter> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionParameter> questions) {
		this.questions = questions;
	}

}
