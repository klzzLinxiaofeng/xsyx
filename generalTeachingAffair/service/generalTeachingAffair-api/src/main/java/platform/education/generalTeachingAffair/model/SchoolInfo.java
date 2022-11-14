package platform.education.generalTeachingAffair.model;

import java.io.Serializable;

/**
 * 学校主要信息，放在SESSION里面 School
 * 
 * @author zhoujin
 *
 */
public class SchoolInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2545183030509004248L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * uuid
	 */
	private String uuid;
	/**
	 * 学校名称
	 */
	private String name;

	/**
	 * 学校教学范围(如小学+初中)
	 */
	private String stageScope;

	/**
	 * 学校图片
	 */
	private String entityId_image;

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

	public String getStageScope() {
		return stageScope;
	}

	public void setStageScope(String stageScope) {
		this.stageScope = stageScope;
	}

	public String getEntityId_image() {
		return entityId_image;
	}

	public void setEntityId_image(String entityId_image) {
		this.entityId_image = entityId_image;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "SchoolInfo [id=" + id + ", name=" + name + ", stageScope="
				+ stageScope + "]";
	}

}
