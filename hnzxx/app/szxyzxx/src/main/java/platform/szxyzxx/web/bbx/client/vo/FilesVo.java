package platform.szxyzxx.web.bbx.client.vo;

public class FilesVo {

	private String fileUuid;
	
	private String fileName;
	
	private String fileUrl;

	public FilesVo(){
		
	}
	
	public FilesVo(String fileUuid, String fileName, String fileUrl) {
		this.fileUuid = fileUuid;
		this.fileName = fileName;
		this.fileUrl = fileUrl;
	}

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
