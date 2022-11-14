package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaperQuestion
 * @author AutoCreate
 *
 */
public class PaperQuestion implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 题目顺序
	 */
	private Integer pos;
	/**
	 * 分数
	 */
	private Double score;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 原题目的id
	 */
	private String questionUuid;
	/**
	 * 原试卷uuid
	 */
	private String paperUuid;
	
	/**
	 * 原试卷id
	 */
	private Integer paperId;
	
	public PaperQuestion() {
		
	}
	
	public PaperQuestion(Integer id) {
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
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取题目顺序
	 * @return java.lang.Integer
	 */
	public Integer getPos() {
		return this.pos;
	}
	
	/**
	 * 设置题目顺序
	 * @param pos
	 * @type java.lang.Integer
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	/**
	 * 获取分数
	 * @return java.lang.Double
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置分数
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取原题目的id
	 * @return java.lang.String
	 */
	public String getQuestionUuid() {
		return this.questionUuid;
	}
	
	/**
	 * 设置原题目的id
	 * @param questionUuid
	 * @type java.lang.String
	 */
	public void setQuestionUuid(String questionUuid) {
		this.questionUuid = questionUuid;
	}
	
	/**
	 * 获取原试卷id
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置原试卷id
	 * @param paperUuid
	 * @type java.lang.String
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
}