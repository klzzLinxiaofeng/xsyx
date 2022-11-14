package platform.education.rest.oa.service.vo;

public class FileMsg {
	//通知上传的附件uuid
	private String fileUuid;
	//通知上传的附件路径
	private String fileUrl;
	//通知上传的附件名称
	private String fileName;

	public String getFileUuid()
	{
		return fileUuid;
	}

	public void setFileUuid(String fileUuid)
	{
		this.fileUuid = fileUuid;
	}

	public String getFileUrl()
	{
		return fileUrl;
	}

	public void setFileUrl(String fileUrl)
	{
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
