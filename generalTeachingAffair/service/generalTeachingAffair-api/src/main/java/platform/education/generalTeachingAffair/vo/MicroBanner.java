package platform.education.generalTeachingAffair.vo;

import framework.generic.dao.Model;

/**
 * MicroBanner
 * @author AutoCreate
 *
 */
public class MicroBanner implements Model<Integer> {
	
	/**
	 * id
	 */
	private Integer id;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 链接地址
	 */
	private String url;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 缩略图entity_id
	 */
	private String entityId;
	/**
	 * 是否推送(0:推送，1:不推送)，默认不推送
	 */
	private String pushState;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;

	private String content;

	private Integer notId;

	public Integer getNotId() {
		return notId;
	}

	public void setNotId(Integer notId) {
		this.notId = notId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MicroBanner() {
		
	}
	
	public MicroBanner(Integer id) {
		this.id = id;
	}
	
	@Override
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
	 * 获取标题
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置标题
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取描述
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置描述
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取缩略图entity_id
	 * @return java.lang.String
	 */
	public String getEntityId() {
		return this.entityId;
	}
	
	/**
	 * 设置缩略图entity_id
	 * @param entityId
	 * @type java.lang.String
	 */
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	
	/**
	 * 获取是否推送(0:推送，1:不推送)，默认不推送
	 * @return java.lang.String
	 */
	public String getPushState() {
		return this.pushState;
	}
	
	/**
	 * 设置是否推送(0:推送，1:不推送)，默认不推送
	 * @param pushState
	 * @type java.lang.String
	 */
	public void setPushState(String pushState) {
		this.pushState = pushState;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}