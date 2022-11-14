package platform.education.rest.basic.service.vo;

/**
 * 
 * @author hmzhang 2016/07/24
 *
 */
public class PersonNumber {
	private Integer boy = 0;
	
	private Integer girl = 0;
	
	private Integer unknown = 0;
	
	private Integer unsecified = 0;
	
	private Integer total;

	public Integer getBoy() {
		return boy;
	}

	public void setBoy(Integer boy) {
		this.boy = boy;
	}

	public Integer getGirl() {
		return girl;
	}

	public void setGirl(Integer girl) {
		this.girl = girl;
	}

	public Integer getUnknown() {
		return unknown;
	}

	public void setUnknown(Integer unknown) {
		this.unknown = unknown;
	}

	public Integer getUnsecified() {
		return unsecified;
	}

	public void setUnsecified(Integer unsecified) {
		this.unsecified = unsecified;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public void addBoy(){
		this.boy++;
	}
	
	public void addGirl(){
		this.girl++;
	}
	
	public void addUnknow(){
		this.unknown++;
	}
	
	public void addUnsecified(){
		this.unsecified++;
	}
}
