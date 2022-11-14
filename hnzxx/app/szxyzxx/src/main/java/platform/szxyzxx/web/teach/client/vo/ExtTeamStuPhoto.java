package platform.szxyzxx.web.teach.client.vo;

public class ExtTeamStuPhoto {
	/**
	 * 用户的userId
	 */
	private Integer id;
	
	/**
	 * 学生ID
	 */
	private Integer studentId;
	
	/**
	 * 学生姓名
	 */
	private String name;
	
	/**
	 * 用户照片
	 */
	private String photoUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
}
