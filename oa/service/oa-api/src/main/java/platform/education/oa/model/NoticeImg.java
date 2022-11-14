package platform.education.oa.model;

import framework.generic.dao.Model;
/**
 * NoticeImg
 * @author AutoCreate
 *
 */
public class NoticeImg implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 图片ID
	 */
	private String imgUuid;
	/**
	 * 图片路径
	 */
	private String imgUrl;
	/**
	 * 关联的公告ID
	 */
	private Integer noticeId;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public NoticeImg() {
		
	}
	
	public NoticeImg(Integer id) {
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
	 * 获取图片ID
	 * @return java.lang.String
	 */
	public String getImgUuid() {
		return this.imgUuid;
	}
	
	/**
	 * 设置图片ID
	 * @param imgUuid
	 * @type java.lang.String
	 */
	public void setImgUuid(String imgUuid) {
		this.imgUuid = imgUuid;
	}
	
	/**
	 * 获取图片路径
	 * @return java.lang.String
	 */
	public String getImgUrl() {
		return this.imgUrl;
	}
	
	/**
	 * 设置图片路径
	 * @param imgUrl
	 * @type java.lang.String
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	/**
	 * 获取关联的公告ID
	 * @return java.lang.Integer
	 */
	public Integer getNoticeId() {
		return this.noticeId;
	}
	
	/**
	 * 设置关联的公告ID
	 * @param noticeId
	 * @type java.lang.Integer
	 */
	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
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