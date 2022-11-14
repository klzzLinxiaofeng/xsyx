package platform.education.paper.vo;
import platform.education.paper.model.PaQuestionCatalog;
/**
 * PaQuestionCatalog
 * @author AutoCreate
 *
 */
public class PaQuestionCatalogCondition extends PaQuestionCatalog {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private String code;
	private Integer textbookId;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getTextbookId() {
		return textbookId;
	}
	public void setTextbookId(Integer textbookId) {
		this.textbookId = textbookId;
	}
	
	
}