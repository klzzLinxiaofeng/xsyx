package platform.education.rest.bp.service.vo;

public class ResponseAdd {
	private Integer addedId;
	
	private String createTime;
	
	public ResponseAdd(){
		
	}

	public ResponseAdd(Integer addedId, String createTime) {
		this.addedId = addedId;
		this.createTime = createTime;
	}



	public Integer getAddedId() {
		return addedId;
	}

	public void setAddedId(Integer addedId) {
		this.addedId = addedId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
