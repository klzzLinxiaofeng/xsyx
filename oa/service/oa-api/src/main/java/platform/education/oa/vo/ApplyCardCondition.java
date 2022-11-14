package platform.education.oa.vo;
import platform.education.oa.model.ApplyCard;
/**
 * ApplyCard
 * @author AutoCreate
 *
 */
public class ApplyCardCondition extends ApplyCard {
	private static final long serialVersionUID = 1L;
	private String cardName;
	private String ssword;
	private Integer teacherId;
	private String auditStatusAlready;
	
	public String getAuditStatusAlready() {
		return auditStatusAlready;
	}
	public void setAuditStatusAlready(String auditStatusAlready) {
		this.auditStatusAlready = auditStatusAlready;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getSsword() {
		return ssword;
	}
	public void setSsword(String ssword) {
		this.ssword = ssword;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
}