package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

import platform.education.clazz.model.BBXNoticeFile;

/**
 * 切换公用通知组件后的手机、pc端通知实体
 * @author huangyanchun
 * @date 2016-07-28
 *
 */
public class BBXNoticeClient {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;


	/**
	 * 通知的ＵＵＩＤ
	 */
	private String uuid;
	/**
	 * yh_app_edition.key
	 */
	private String appKey;
	/**
	 * 通知标题
	 */
	private String title;
	/**
	 * 发送者ID
	 */
	private Integer posterId;
	/**
	 * 发送者
	 */
	private String posterName;
	/**
	 * 定时发送的时间
	 */
	private String postTime;
	/**
	 * 接收类型
	 */
	private String receiverType;
	/**
	 * 接收者姓名
	 */
	private String receiverName;
	/**
	 * 通知内容
	 */
	private String content;
	
	/**
	 * 阅读人数
	 */
	private Integer readCount;
	
	/**
	 * 发送的总人数
	 */
	private Integer userCount;
	

	/**
	 * 创建时间
	 */
	private String createDate;
	
	
	/**
	 *通知附件 
	 */
	private List<BBXNoticeFile>files;
	
	
	/**
	 * 阅读标志
	 */
	private Integer hasRead;
	
	


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getUuid() {
		return uuid;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	public String getAppKey() {
		return appKey;
	}


	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Integer getPosterId() {
		return posterId;
	}


	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}


	public String getPosterName() {
		return posterName;
	}


	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}


	public String getPostTime() {
		return postTime;
	}


	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}


	public String getReceiverType() {
		return receiverType;
	}


	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}


	public String getReceiverName() {
		return receiverName;
	}


	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getReadCount() {
		return readCount;
	}


	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}


	public Integer getUserCount() {
		return userCount;
	}


	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	
	public List<BBXNoticeFile> getFiles() {
		return files;
	}
	
	
	public void setFiles(List<BBXNoticeFile> files) {
		this.files = files;
	}


	public Integer getHasRead() {
		return hasRead;
	}


	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}
	
	
	
	
	
	
	
}
