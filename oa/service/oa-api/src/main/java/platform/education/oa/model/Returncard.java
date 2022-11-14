package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * Returncard
 * @author AutoCreate
 *
 */
public class Returncard implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 借出车ID
	 */
	private Integer usecardId;
	/**
	 * 还车日期
	 */
	private java.util.Date returnDate;
	/**
	 * 还车说明
	 */
	private String returnExplain;
	/**
	 * 还车状态
	 */
	private String returnStatus;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标记
	 */
	private Integer schoolId;
	private Boolean isDelete;
	private String cardNumber;
	private Integer proposer;
	
	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getProposer() {
		return proposer;
	}

	public void setProposer(Integer proposer) {
		this.proposer = proposer;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Returncard() {
		
	}
	
	public Returncard(Integer id) {
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
	 * 获取借出车ID
	 * @return java.lang.Integer
	 */
	public Integer getUsecardId() {
		return this.usecardId;
	}
	
	/**
	 * 设置借出车ID
	 * @param usecardId
	 * @type java.lang.Integer
	 */
	public void setUsecardId(Integer usecardId) {
		this.usecardId = usecardId;
	}
	
	/**
	 * 获取还车日期
	 * @return java.util.Date
	 */
	public java.util.Date getReturnDate() {
		return this.returnDate;
	}
	
	/**
	 * 设置还车日期
	 * @param returnDate
	 * @type java.util.Date
	 */
	public void setReturnDate(java.util.Date returnDate) {
		this.returnDate = returnDate;
	}
	
	/**
	 * 获取还车说明
	 * @return java.lang.String
	 */
	public String getReturnExplain() {
		return this.returnExplain;
	}
	
	/**
	 * 设置还车说明
	 * @param returnExplain
	 * @type java.lang.String
	 */
	public void setReturnExplain(String returnExplain) {
		this.returnExplain = returnExplain;
	}
	
	/**
	 * 获取还车状态
	 * @return java.lang.String
	 */
	public String getReturnStatus() {
		return this.returnStatus;
	}
	
	/**
	 * 设置还车状态
	 * @param returnStatus
	 * @type java.lang.String
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
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
	
	/**
	 * 获取是否删除
	 * @return java.lang.String
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置是否删除
	 * @param i
	 * @type java.lang.String
	 */
	public void setIsDelete(Boolean i) {
		this.isDelete = i;
	}
	
}