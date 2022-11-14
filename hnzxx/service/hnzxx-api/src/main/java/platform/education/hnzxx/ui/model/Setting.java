package platform.education.hnzxx.ui.model;

import framework.generic.dao.Model;
/**
 * Setting
 * @author AutoCreate
 *
 */
public class Setting implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 所在学校
	 */
	private Integer schoolId;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 壁纸的绝对路径
	 */
	private String wallpaperPath;
	/**
	 * 创建日期
	 */
	private java.util.Date createDate;
	/**
	 * 修改日期
	 */
	private java.util.Date modifyDate;
	
	/**
	 * 壁纸对应的颜色值
	 */
	private String wallpaperColor;
	
	/**
	 * 新版首页使用
	 */
	private String newWallpaperPath;
	
	
	public Setting() {
		
	}
	
	public Setting(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键ID
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键ID
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取所在学校
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置所在学校
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取用户ID
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置用户ID
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取壁纸的绝对路径
	 * @return java.lang.String
	 */
	public String getWallpaperPath() {
		return this.wallpaperPath;
	}
	
	/**
	 * 设置壁纸的绝对路径
	 * @param wallpaperPath
	 * @type java.lang.String
	 */
	public void setWallpaperPath(String wallpaperPath) {
		this.wallpaperPath = wallpaperPath;
	}
	
	/**
	 * 获取创建日期
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建日期
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取修改日期
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改日期
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getWallpaperColor() {
		return wallpaperColor;
	}

	public void setWallpaperColor(String wallpaperColor) {
		this.wallpaperColor = wallpaperColor;
	}

	public String getNewWallpaperPath() {
		return newWallpaperPath;
	}

	public void setNewWallpaperPath(String newWallpaperPath) {
		this.newWallpaperPath = newWallpaperPath;
	}
	
}