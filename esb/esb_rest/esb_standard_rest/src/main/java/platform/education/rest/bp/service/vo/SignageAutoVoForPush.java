package platform.education.rest.bp.service.vo;

public class SignageAutoVoForPush {

	/**
	 * 0星期日、1星期一、2星期二、3星期三、4星期四、5星期五、6星期六
	 */
	private Integer weekDay;
	/**
	 * 定时开机时间
	 */
	private String openTime;
	/**
	 * 定时关机时间
	 */
	private String closeTime;
	public Integer getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(Integer weekDay) {
		this.weekDay = weekDay;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	
	
	
	
}
