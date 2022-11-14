package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaPaperCatalog
 * @author AutoCreate
 *
 */
public class PaPaperCatalog implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 试卷ID
	 */
	private Integer paperId;
	/**
	 * 科目 subject.code
	 */
	private String subjectCode;
	/**
	 * 教材目录 textbook_catalog.code
	 */
	private String catalogCode;
	//学段
	private String stageCode;
	
	public PaPaperCatalog() {
		
	}
	
	public PaPaperCatalog(Integer id) {
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
	 * 获取试卷ID
	 * @return java.lang.Integer
	 */
	public Integer getPaperId() {
		return this.paperId;
	}
	
	/**
	 * 设置试卷ID
	 * @param paperId
	 * @type java.lang.Integer
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * 获取科目 subject.code
	 * @return java.lang.String
	 */
	public String getSubjectCode() {
		return this.subjectCode;
	}
	
	/**
	 * 设置科目 subject.code
	 * @param subjectCode
	 * @type java.lang.String
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	
	/**
	 * 获取教材目录 textbook_catalog.code
	 * @return java.lang.String
	 */
	public String getCatalogCode() {
		return this.catalogCode;
	}
	
	/**
	 * 设置教材目录 textbook_catalog.code
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