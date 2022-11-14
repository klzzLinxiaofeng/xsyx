package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.Result;
/**
 * Result
 * @author AutoCreate
 *
 */
public class ResultVo extends Result {
	private static final long serialVersionUID = 1L;
	/**
	 * 教师工号
	 */
	private String jobNumber;
	/**
	 * 查询文字
	 */
	private String searchWord;
	public String getJobNumber() {
		return jobNumber;
	}
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}