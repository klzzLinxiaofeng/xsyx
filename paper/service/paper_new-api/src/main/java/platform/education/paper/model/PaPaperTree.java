package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaPaperTree
 * @author AutoCreate
 *
 */
public class PaPaperTree implements Model<Integer> {
	
	
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
	 * 父节点ID
	 */
	private Integer parentId;
	/**
	 * nodeOrder
	 */
	private Integer nodeOrder;
	/**
	 * 节点元素类型 0=根（标题） 1=题组 2=题目 3=子题目
	 */
	private Integer nodeType;
	/**
	 * 试卷标题、题组标题
	 */
	private String title;
	/**
	 * 各个元素的说明，通常是整个试卷或某个大题组的说明
	 */
	private String memo;
	/**
	 * 题目整卷编号。只对题目元素有效
	 */
	private Integer pos;
	/**
	 * 题组或题目组内编号 如果本元素是题组，是题组在卷中编号；如果是是题目，是题目在组内编号，如果题目无分组则是在卷中编号
	 */
	private String num;
	/**
	 * 题型 如果本元素是题目，指本题的题型； 如果是题组，说明组内的题目的题型，如果组内题目题型混合，则置空； 其他元素置空
	 */
	private String questionType;
	/**
	 * 试题id question.id
	 */
	private Integer questionId;
	/**
	 * 如果是题目，指题目满分分数 如果是题组，指全组题目总分
	 */
	private Float score;
	/**
	 * 记录删除标识
	 */
	private Integer isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public PaPaperTree() {
		
	}
	
	public PaPaperTree(Integer id) {
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
	 * 获取父节点ID
	 * @return java.lang.Integer
	 */
	public Integer getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置父节点ID
	 * @param parentId
	 * @type java.lang.Integer
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 获取nodeOrder
	 * @return java.lang.Integer
	 */
	public Integer getNodeOrder() {
		return this.nodeOrder;
	}
	
	/**
	 * 设置nodeOrder
	 * @param nodeOrder
	 * @type java.lang.Integer
	 */
	public void setNodeOrder(Integer nodeOrder) {
		this.nodeOrder = nodeOrder;
	}
	
	/**
	 * 获取节点元素类型 0=根（标题） 1=题组 2=题目 3=子题目
	 * @return java.lang.Integer
	 */
	public Integer getNodeType() {
		return this.nodeType;
	}
	
	/**
	 * 设置节点元素类型 0=根（标题） 1=题组 2=题目 3=子题目
	 * @param nodeType
	 * @type java.lang.Integer
	 */
	public void setNodeType(Integer nodeType) {
		this.nodeType = nodeType;
	}
	
	/**
	 * 获取试卷标题、题组标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置试卷标题、题组标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取各个元素的说明，通常是整个试卷或某个大题组的说明
	 * @return java.lang.String
	 */
	public String getMemo() {
		return this.memo;
	}
	
	/**
	 * 设置各个元素的说明，通常是整个试卷或某个大题组的说明
	 * @param memo
	 * @type java.lang.String
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	/**
	 * 获取题目整卷编号。只对题目元素有效
	 * @return java.lang.Integer
	 */
	public Integer getPos() {
		return this.pos;
	}
	
	/**
	 * 设置题目整卷编号。只对题目元素有效
	 * @param pos
	 * @type java.lang.Integer
	 */
	public void setPos(Integer pos) {
		this.pos = pos;
	}
	
	/**
	 * 获取题组或题目组内编号 如果本元素是题组，是题组在卷中编号；如果是是题目，是题目在组内编号，如果题目无分组则是在卷中编号
	 * @return java.lang.String
	 */
	public String getNum() {
		return this.num;
	}
	
	/**
	 * 设置题组或题目组内编号 如果本元素是题组，是题组在卷中编号；如果是是题目，是题目在组内编号，如果题目无分组则是在卷中编号
	 * @param num
	 * @type java.lang.String
	 */
	public void setNum(String num) {
		this.num = num;
	}
	
	/**
	 * 获取题型 如果本元素是题目，指本题的题型； 如果是题组，说明组内的题目的题型，如果组内题目题型混合，则置空； 其他元素置空
	 * @return java.lang.String
	 */
	public String getQuestionType() {
		return this.questionType;
	}
	
	/**
	 * 设置题型 如果本元素是题目，指本题的题型； 如果是题组，说明组内的题目的题型，如果组内题目题型混合，则置空； 其他元素置空
	 * @param questionType
	 * @type java.lang.String
	 */
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	
	/**
	 * 获取试题id question.id
	 * @return java.lang.Integer
	 */
	public Integer getQuestionId() {
		return this.questionId;
	}
	
	/**
	 * 设置试题id question.id
	 * @param questionId
	 * @type java.lang.Integer
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	
	/**
	 * 获取如果是题目，指题目满分分数 如果是题组，指全组题目总分
	 * @return java.lang.Float
	 */
	public Float getScore() {
		return this.score;
	}
	
	/**
	 * 设置如果是题目，指题目满分分数 如果是题组，指全组题目总分
	 * @param score
	 * @type java.lang.Float
	 */
	public void setScore(Float score) {
		this.score = score;
	}
	
	/**
	 * 获取记录删除标识
	 * @return Integer
	 */
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录删除标识
	 * @param isDeleted
	 * @type Integer
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
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
	
}