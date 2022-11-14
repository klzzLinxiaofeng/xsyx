package platform.szxyzxx.web.oa.termial.vo;
 
import platform.education.oa.model.Schedule;
import platform.education.oa.utils.UtilDate;
 
 
public class PhoneScheduleVo {
	 
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 公告标题
	 */
	private String planStartTime;
	
	/**
	 * 公告类型
	 */
	private String planFinishTime;
	/**
	 * 公告描述
	 */
	private String content;
	
	 
	/**
	 * 发布者用户名
	 */
	private String posterName;
	/**
	 * 创建时间
	 */
	private String createDate;
	 
	
	 
	public PhoneScheduleVo(Schedule n){
		this.id=n.getId()+"";
		this.planStartTime=n.getPlanStartTime();
		this.planFinishTime=n.getPlanFinishTime();
		this.content=n.getContent();
		this.posterName=n.getPosterName();
		this.createDate=UtilDate.getDateFormatter(n.getCreateDate()); 
		 
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPlanStartTime() {
		return planStartTime;
	}



	public void setPlanStartTime(String planStartTime) {
		this.planStartTime = planStartTime;
	}



	public String getPlanFinishTime() {
		return planFinishTime;
	}



	public void setPlanFinishTime(String planFinishTime) {
		this.planFinishTime = planFinishTime;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getPosterName() {
		return posterName;
	}



	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}



	public String getCreateDate() {
		return createDate;
	}



	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	 
	
}