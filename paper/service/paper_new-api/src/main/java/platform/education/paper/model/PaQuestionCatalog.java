package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaQuestionCatalog
 * @author AutoCreate
 *
 */
public class PaQuestionCatalog implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * questionId
	 */
	private Integer questionId;
	/**
	 * 科目
	 */
	private String subjectCode;
	/**
	 * 教材目录
	 */
	private String catalogCode;
	
	private String stageCode;
	
	public PaQuestionCatalog() {
		
	}
	
	public PaQuestionCatalog(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取questionId
	 * @return java.lang.Integer
	 */
	public Integer getQuestionId() {
		return this.questionId;
	}
	
	/**
	 * 设置questionId
	 * @param questionId
	 * @type java.lang.Integer
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	/**
	 * 获取科目
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取教材目录
	 * @return java.lang.String
	 */
	public String getCatalogCode() {
		return this.catalogCode;
	}
	
	/**
	 * 设置教材目录
	 * @param catalogCode
	 * @type java.lang.String
	 */
	public void setCatalogCode(String catalogCode) {
		this.catalogCode = catalogCode;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	
}