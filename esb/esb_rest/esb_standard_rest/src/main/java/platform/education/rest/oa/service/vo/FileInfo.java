package platform.education.rest.oa.service.vo;

import java.util.List;

public class FileInfo {
	private Integer noticeId;
	
	private List<FileMsg> files;

	public Integer getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Integer noticeId) {
		this.noticeId = noticeId;
	}

	public List<FileMsg> getFiles() {
		return files;
	}

	public void setFiles(List<FileMsg> files) {
		this.files = files;
	}
}
