package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class JobControlVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 业务名称
	 */
	private String name;
	/**
	 * 目标记录ID
	 */
	private Integer objectId;
	/**
	 * 开关
	 */
	private Boolean interrupteur;
	/**
	 * 状态
	 */
	private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public Boolean getInterrupteur() {
		return interrupteur;
	}
	public void setInterrupteur(Boolean interrupteur) {
		this.interrupteur = interrupteur;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
