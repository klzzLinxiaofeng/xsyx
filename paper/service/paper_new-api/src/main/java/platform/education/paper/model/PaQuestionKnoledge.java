package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaQuestionKnoledge
 * @author AutoCreate
 *
 */
public class PaQuestionKnoledge implements Model<Integer> {
	
	
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
	 * 知识点
	 */
	private String knowledgeCode;
	
	public PaQuestionKnoledge() {
		
	}
	
	public PaQuestionKnoledge(Integer id) {
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
	 * 获取知识点
	 * @return java.lang.String
	 */
	public String getKnowledgeCode() {
		return this.knowledgeCode;
	}
	
	/**
	 * 设置知识点
	 * @param knowledgeCode
	 * @type java.lang.String
	 */
	public void setKnowledgeCode(String knowledgeCode) {
		this.knowledgeCode = knowledgeCode;
	}
	
}