package platform.education.rest.resource.service.vo;

import java.util.List;

public class ExtPushDetails {
	private Integer finishedCount;
	
	private Integer unFinishedCount;
	
	private Integer partFinishedCount;
	
	private List<ExtMicroPublishedRecord> recordList;

	public Integer getFinishedCount() {
		return finishedCount;
	}

	public void setFinishedCount(Integer finishedCount) {
		this.finishedCount = finishedCount;
	}

	public Integer getUnFinishedCount() {
		return unFinishedCount;
	}

	public void setUnFinishedCount(Integer unFinishedCount) {
		this.unFinishedCount = unFinishedCount;
	}

	public Integer getPartFinishedCount() {
		return partFinishedCount;
	}

	public void setPartFinishedCount(Integer partFinishedCount) {
		this.partFinishedCount = partFinishedCount;
	}

	public List<ExtMicroPublishedRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ExtMicroPublishedRecord> recordList) {
		this.recordList = recordList;
	}
	
}
