package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.List;

public class AssemblyGroup {
	private String name;
	private Integer pos;
	private Float score;
	private String memo;
	private List<AssemblyQuestion> questions = new ArrayList<AssemblyQuestion>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<AssemblyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<AssemblyQuestion> questions) {
		this.questions = questions;
	}

}
