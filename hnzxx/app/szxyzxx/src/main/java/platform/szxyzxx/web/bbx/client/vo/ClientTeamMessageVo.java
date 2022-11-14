package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

public class ClientTeamMessageVo {

	private static final long serialVersionUID = 1L;
	
	/**
	 * id主键
	 */
	private Integer id ;
	
	/**
	 * 通知元所在学校 school.id
	 */
	private Integer schoolId;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 发送者UserId  user.id
	 */
	private Integer postId;
	
	/**
	 * 发送时间
	 */
	private String postTime;
	
	/**
	 * 计划发送时间
	 */
	private String planTime;
	
	/**
	 *  接收者类别  1=全体师生 2=全体教师 3=全体学生 4=指定年级 5=指定班级 7=多个班级 8=选择多人
	 */
	private Integer receiverType;
	
	
	/**
	 * 接收者id (班级id)
	 */
	private  Integer receiverId; 
	/**
	 * 接收者姓名
	 */
	private String receiverName;
	

	/**
	 * 通知内容 
	 */
	private String content;
	
	/**
	 * 班级通知记录创建时间
	 */
	private String createDate;
	
	
	List<FilesVo>files;
	

	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	
	public String getPostTime() {
		return postTime;
	}
	
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	
	public String getPlanTime() {
		return planTime;
	}
	
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	

	public Integer getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}
	
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
	
	public Integer getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(Integer receiverType) {
		this.receiverType = receiverType;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	public List<FilesVo> getFiles() {
		return files;
	}


	public void setFiles(List<FilesVo> files) {
		this.files = files;
	}
	
	
	
	
}
