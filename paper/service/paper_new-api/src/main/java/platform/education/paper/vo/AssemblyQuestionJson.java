package platform.education.paper.vo;

import java.io.Serializable;
import java.util.Arrays;

public class AssemblyQuestionJson implements Serializable {
	private static final long serialVersionUID = 1L;

	private String questionUuid;
	
	private Integer questionId;
	
	private String strPos;
	
	private Integer pos;
	
	private Integer childSize;
	
	private AssemblyQuestionJson[] children;
	
	private String subjectCode;
	
	public Integer getChildSize() {
		return childSize;
	}

	public void setChildSize(Integer childSize) {
		this.childSize = childSize;
	}

	public AssemblyQuestionJson[] getChildren() {
		return children;
	}

	public void setChildren(AssemblyQuestionJson[] children) {
		this.children = children;
	}


	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public String getStrPos() {
		return strPos;
	}

	public void setStrPos(String strPos) {
		this.strPos = strPos;
	}

	public String getQuestionUuid() {
		return questionUuid;
	}

	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Override
	public String toString() {
		return "AssemblyQuestionJson [questionUuid=" + questionUuid + ", questionId=" + questionId + ", strPos="
				+ strPos + ", pos=" + pos + ", childSize=" + childSize + ", children=" + Arrays.toString(children)
				+ "]";
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
}
