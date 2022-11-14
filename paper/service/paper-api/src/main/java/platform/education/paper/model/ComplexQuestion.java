/**   
* @Title: ComplexQuestion.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年3月21日 下午3:12:14 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;
import java.util.List;

/** 
* @ClassName: ComplexQuestion 
* @Description: 复合题实体类
* @author pantq
* @date 2017年3月21日 下午3:12:14 
*  
*/
public class ComplexQuestion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//复合题大标题
	private String extraContent ;
	
	//复合题小题
	private List<WrongPaper> complexQuestion;

	/**
	 * @return the extraContent
	 */
	public String getExtraContent() {
		return extraContent;
	}

	/**
	 * @param extraContent the extraContent to set
	 */
	public void setExtraContent(String extraContent) {
		this.extraContent = extraContent;
	}

	/**
	 * @return the complexQuestion
	 */
	public List<WrongPaper> getComplexQuestion() {
		return complexQuestion;
	}

	/**
	 * @param complexQuestion the complexQuestion to set
	 */
	public void setComplexQuestion(List<WrongPaper> complexQuestion) {
		this.complexQuestion = complexQuestion;
	}
}
