package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.List;

public class BasketGroupJson {
	private String groupName;
	private Integer pos;
	private List<Integer> questionId = new ArrayList<Integer>();
	private Long questionSize;

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

	public List<Integer> getQuestionId() {
		return questionId;
	}

	public void setQuestionId(List<Integer> questionId) {
		this.questionId = questionId;
	}

	public Long getQuestionSize() {
		return questionSize;
	}

	public void setQuestionSize(Long questionSize) {
		this.questionSize = questionSize;
	}


}
