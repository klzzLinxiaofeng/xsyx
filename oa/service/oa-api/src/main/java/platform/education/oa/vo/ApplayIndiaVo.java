package platform.education.oa.vo;
import platform.education.oa.model.ApplayIndia;
/**
 * ApplayIndia
 * @author AutoCreate
 *
 */
public class ApplayIndiaVo extends ApplayIndia {
	private static final long serialVersionUID = 1L;
	
	//部门名称
	private String departmentName;
	
	//上传文件的名称
	private String realFileName;
	
	 //图片ID
	 
	private String entityId;
	
	
	//文印申请总数
	private String totalCount;
	
	//待处理总数
	private String daichuliCount;
	
	//处理中总数
	private String chulizhongCount;
	
	//已处理总数
	private String yichuliCount;
	
	//未处理总数
	private String weichuliCount;
	
	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getDaichuliCount() {
		return daichuliCount;
	}

	public void setDaichuliCount(String daichuliCount) {
		this.daichuliCount = daichuliCount;
	}

	public String getChulizhongCount() {
		return chulizhongCount;
	}

	public void setChulizhongCount(String chulizhongCount) {
		this.chulizhongCount = chulizhongCount;
	}

	public String getYichuliCount() {
		return yichuliCount;
	}

	public void setYichuliCount(String yichuliCount) {
		this.yichuliCount = yichuliCount;
	}

	public String getWeichuliCount() {
		return weichuliCount;
	}

	public void setWeichuliCount(String weichuliCount) {
		this.weichuliCount = weichuliCount;
	}


	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}

	
	
	
}