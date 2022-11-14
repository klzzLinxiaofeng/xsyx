package platform.education.paper.vo;

import java.io.Serializable;
import java.util.Arrays;

public class AssemblyGroupJson implements Serializable{
	private static final long serialVersionUID = 1L;

	private AssemblyQuestionJson[] question;
	
	private String groupName;

	private String strPos;
	
	private Integer pos;

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

	public AssemblyQuestionJson[] getQuestion() {
		return question;
	}

	public void setQuestion(AssemblyQuestionJson[] question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "AssemblyGroupJson [question=" + Arrays.toString(question) + ", groupName=" + groupName + ", strPos="
				+ strPos + ", pos=" + pos + "]";
	}
}
