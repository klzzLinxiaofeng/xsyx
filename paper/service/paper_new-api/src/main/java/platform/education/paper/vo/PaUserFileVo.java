package platform.education.paper.vo;
import platform.education.paper.model.PaUserFile;
/**
 * PaUserFile
 * @author AutoCreate
 *
 */
public class PaUserFileVo extends PaUserFile {
	private static final long serialVersionUID = 1L;
	
	String realUserQuestionUuid;

	public String getRealUserQuestionUuid() {
		return realUserQuestionUuid;
	}

	public void setRealUserQuestionUuid(String realUserQuestionUuid) {
		this.realUserQuestionUuid = realUserQuestionUuid;
	}
}