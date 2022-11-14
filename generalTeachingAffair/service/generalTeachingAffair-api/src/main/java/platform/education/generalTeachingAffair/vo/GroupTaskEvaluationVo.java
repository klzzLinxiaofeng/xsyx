package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.GroupTaskEvaluation;
/**
 * GroupTaskEvaluation
 * @author AutoCreate
 *
 */
public class GroupTaskEvaluationVo extends GroupTaskEvaluation {
	private static final long serialVersionUID = 1L;

	private String title;
	private String groupName;
	private Float grades;
	private Boolean isSubmit;

	public Boolean getIsSubmit() {
		return this.isSubmit;
	}

	public void setIsSubmit(Boolean isSubmit) {
		this.isSubmit = isSubmit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Float getGrades() {
		return grades;
	}

	public void setGrades(Float grades) {
		this.grades = grades;
	}
}