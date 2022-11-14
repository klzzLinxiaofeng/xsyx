package platform.education.oa.vo;

import platform.education.oa.model.PaperUserRead;

/**
 * OaPaperUserRead
 * 
 * @author AutoCreate
 *
 */
public class PaperUserReadVo extends PaperUserRead {
	private static final long serialVersionUID = 1L;

	private String realName;

	private String telphone;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	

}