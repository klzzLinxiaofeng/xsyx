package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsTaskScoreFile
 * @author AutoCreate
 *
 */
public class ApsTaskScoreFile implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的评价任务 aps_task_score.id
	 */
	private Integer taskScoreId;
	/**
	 * 文件uuid
	 */
	private String fileId;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	
	public ApsTaskScoreFile() {
		
	}
	
	public ApsTaskScoreFile(Integer id) {
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
	 * 获取对应的评价任务 aps_task_score.id
	 * @return java.lang.Integer
	 */
	public Integer getTaskScoreId() {
		return this.taskScoreId;
	}
	
	/**
	 * 设置对应的评价任务 aps_task_score.id
	 * @param taskScoreId
	 * @type java.lang.Integer
	 */
	public void setTaskScoreId(Integer taskScoreId) {
		this.taskScoreId = taskScoreId;
	}
	
	/**
	 * 获取文件uuid
	 * @return java.lang.String
	 */
	public String getFileId() {
		return this.fileId;
	}
	
	/**
	 * 设置文件uuid
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