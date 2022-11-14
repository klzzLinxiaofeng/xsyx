package platform.education.oa.vo;
import platform.education.oa.model.ApplyCard;
/**
 * ApplyCard
 * @author AutoCreate
 *
 */
public class ApplyCardVo extends ApplyCard {
	private static final long serialVersionUID = 1L;
	private String cardName;
	private String plateNumber;
	private String tolDay;
	private String tatolAudit;
	private String waitAudit;
	private String alertyAudit;
	private String depetmrnt;
	public String getDepetmrnt() {
		return depetmrnt;
	}
	public void setDepetmrnt(String depetmrnt) {
		this.depetmrnt = depetmrnt;
	}
	public String getTatolAudit() {
		return tatolAudit;
	}
	public void setTatolAudit(String tatolAudit) {
		this.tatolAudit = tatolAudit;
	}
	public String getWaitAudit() {
		return waitAudit;
	}
	public void setWaitAudit(String waitAudit) {
		this.waitAudit = waitAudit;
	}
	public String getAlertyAudit() {
		return alertyAudit;
	}
	public void setAlertyAudit(String alertyAudit) {
		this.alertyAudit = alertyAudit;
	}
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public String getTolDay() {
		return tolDay;
	}
	public void setTolDay(String tolDay) {
		this.tolDay = tolDay;
	}
}