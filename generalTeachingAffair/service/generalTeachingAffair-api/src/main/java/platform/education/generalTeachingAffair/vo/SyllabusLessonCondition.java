package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.SyllabusLesson;

import java.util.Date;

/**
 * SyllabusLesson
 * @author AutoCreate
 *
 */
public class SyllabusLessonCondition extends SyllabusLesson {

	private static final long serialVersionUID = 1L;

	private Date effectiveDate;

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
}