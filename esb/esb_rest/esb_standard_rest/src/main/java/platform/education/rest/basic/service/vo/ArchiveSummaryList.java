package platform.education.rest.basic.service.vo;

import java.util.List;

public class ArchiveSummaryList {
	//已完成
	private List<ArchiveSummary> finished;
	
	//未完成
	private List<ArchiveSummary> unfinished;

	public List<ArchiveSummary> getFinished() {
		return finished;
	}

	public void setFinished(List<ArchiveSummary> finished) {
		this.finished = finished;
	}

	public List<ArchiveSummary> getUnfinished() {
		return unfinished;
	}

	public void setUnfinished(List<ArchiveSummary> unfinished) {
		this.unfinished = unfinished;
	}
	
}
