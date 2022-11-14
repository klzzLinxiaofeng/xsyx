package platform.education.rest.oa.service.vo;

import java.util.List;

/**
 * 通知详细信息
 * 
 * @author Administrator
 *
 */
public class NoticeMess {
	// 通知记录id
	private Integer id;
	// 通知uuid
	private String uuid;
	// 发送消息的app
	private String appKey;
	// 标题
	private String title;
	// 发送者用户UserId
	private Integer posterId;
	// 发送者姓名
	private String posterName;
	// 通知定时发送时间
	private String postTime;
	// 接收者类型：pj.school = 学校通知
	private String receiverType;
	// 学校名称
	private String receiverName;
	// 通知内容
	private String content;
	// 接收本通知的所有用户人数
	private Integer userCount;
	// 已阅读人数
	private Integer readCount;
	// 通知记录创建日期
	private String createDate;
	//阅读状态
	private Integer hasRead;
	// 附件文件
	private List<FileMsg> files;

	//2017-8-24 新增头像
	// 发送者头像
	private String posterIcon;

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

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getHasRead() {
		return hasRead;
	}

	public void setHasRead(Integer hasRead) {
		this.hasRead = hasRead;
	}

	public List<FileMsg> getFiles() {
		return files;
	}

	public void setFiles(List<FileMsg> files) {
		this.files = files;
	}

	public String getPosterIcon() {
		return posterIcon;
	}

	public void setPosterIcon(String posterIcon) {
		this.posterIcon = posterIcon;
	}
}
