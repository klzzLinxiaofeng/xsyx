package platform.education.rest.jw.service.vo;

import java.util.List;
import java.util.Map;

import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.rest.jw.service.vo.LpTaskUserActivityVo;

/**
 * LpTaskUserActivity
 * 
 * @author AutoCreate
 *
 */
public class LpTaskUserActivityInfo extends LpTaskUserActivity {
	private static final long serialVersionUID = 1L;

	/**
	 * 引用的讨论内容
	 */
	private List<LpTaskUserActivityInfo> quoteList;
	
	private String iocnPath;
	
	private String role;
	
	private String name;
	
	private List<String> filePath;

	public List<LpTaskUserActivityInfo> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<LpTaskUserActivityInfo> quoteList) {
		this.quoteList = quoteList;
	}

	public String getIocnPath() {
		return iocnPath;
	}

	public void setIocnPath(String iocnPath) {
		this.iocnPath = iocnPath;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
}