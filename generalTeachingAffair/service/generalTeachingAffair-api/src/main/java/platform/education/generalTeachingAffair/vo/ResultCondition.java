package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Result;
/**
 * Result
 * @author AutoCreate
 *
 */
public class ResultCondition extends Result {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 查询文字
	 */
	private String searchWord;
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}