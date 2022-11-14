package platform.education.oa.vo;
import platform.education.oa.model.ApplyRepair;

/**
 * ApplyRepair
 * @author AutoCreate
 *
 */
public class ApplyRepairVo extends ApplyRepair {
	private static final long serialVersionUID = 1L;
	private Integer accepterId;
	private String accepterName;
	private String appraise;
	private Integer isc;


	public Integer getIsc() {
		return isc;
	}

	public void setIsc(Integer isc) {
		this.isc = isc;
	}

	public Integer getAccepterId() {
		return accepterId;
	}
	public void setAccepterId(Integer accepterId) {
		this.accepterId = accepterId;
	}
	public String getAccepterName() {
		return accepterName;
	}
	public void setAccepterName(String accepterName) {
		this.accepterName = accepterName;
	}
	public String getAppraise() {
		return appraise;
	}
	public void setAppraise(String appraise) {
		this.appraise = appraise;
	}

}