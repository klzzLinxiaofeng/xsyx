package platform.szxyzxx.web.teach.vo;

public class ErroMessageVo {

	
	private Integer rowId;
	
	private String name;
	//英文名
	private String englistName;
	//用户名
	private String username;
	//性别
	private String sex;
	//学籍号
	private String studentNum;
	
	private String errorMessage;
	
	public Integer getRowId() {
		return rowId;
	}
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnglistName() {
		return englistName;
	}
	public void setEnglistName(String englistName) {
		this.englistName = englistName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getStudentNum() {
		return studentNum;
	}
	public void setStudentNum(String studentNum) {
		this.studentNum = studentNum;
	}
	
}
