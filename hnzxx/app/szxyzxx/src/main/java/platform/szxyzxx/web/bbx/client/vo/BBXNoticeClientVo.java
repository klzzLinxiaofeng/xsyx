package platform.szxyzxx.web.bbx.client.vo;

/**
 * 切换公用通知组件后的手机、pc端通知Vo实体
 * @author huangyanchun
 * @date 2016-08-05
 *
 */
public class BBXNoticeClientVo extends BBXNoticeClient{
	
	/**
	 * 发送者校区名称
	 */
	private String posterSchoolName;
	
	/**
	 * 发送者头像
	 */
	private String posterIcon;

	public String getPosterSchoolName() {
		return posterSchoolName;
	}

	public void setPosterSchoolName(String posterSchoolName) {
		this.posterSchoolName = posterSchoolName;
	}

	public String getPosterIcon() {
		return posterIcon;
	}

	public void setPosterIcon(String posterIcon) {
		this.posterIcon = posterIcon;
	}
	
	
	
	

}
