package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ComplainFile
 * @author AutoCreate
 *
 */
public class ComplainFile implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 投诉记录id
	 */
	private Integer complainId;
	/**
	 * 图片uuid
	 */
	private String fileId;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	
	public ComplainFile() {
		
	}
	
	public ComplainFile(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取投诉记录id
	 * @return java.lang.Integer
	 */
	public Integer getComplainId() {
		return this.complainId;
	}
	
	/**
	 * 设置投诉记录id
	 * @param complainId
	 * @type java.lang.Integer
	 */
	public void setComplainId(Integer complainId) {
		this.complainId = complainId;
	}
	
	/**
	 * 获取图片uuid
	 * @return java.lang.String
	 */
	public String getFileId() {
		return this.fileId;
	}
	
	/**
	 * 设置图片uuid
	 * @param fileId
	 * @type java.lang.String
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	
}