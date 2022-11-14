package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaUserPaper
 * @author AutoCreate
 *
 */
public class PaUserPaper implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 发布练习
	 */
	private String paper;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private String userId;
	/**
	 * 做关联的uuid
	 */
	private String uuid;
	
	public PaUserPaper() {
		
	}
	
	public PaUserPaper(Integer id) {
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
	 * 获取发布练习
	 * @return java.lang.String
	 */
	public String getPaper() {
		return this.paper;
	}
	
	/**
	 * 设置发布练习
	 * @param paper
	 * @type java.lang.String
	 */
	public void setPaper(String paper) {
		this.paper = paper;
	}
	
	/**
	 * 获取这个userid指的是使用者的id或者有使用权的id
	 * @return java.lang.String
	 */
	public String getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置这个userid指的是使用者的id或者有使用权的id
	 * @param userId
	 * @type java.lang.String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取做关联的uuid
	 * @return java.lang.String
	 */
	public String getUuid() {
		return this.uuid;
	}
	
	/**
	 * 设置做关联的uuid
	 * @param uuid
	 * @type java.lang.String
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}