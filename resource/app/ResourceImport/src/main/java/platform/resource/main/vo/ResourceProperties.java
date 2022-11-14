package platform.resource.main.vo;

public class ResourceProperties {

    private String resourcePath="";	
	private String undoPath = "";
	private String donePath = "";
	private String beforePath = "";
	private String lineMark = "";
	
	
	public String getLineMark() {
		return lineMark;
	}
	public void setLineMark(String lineMark) {
		this.lineMark = lineMark;
	}
	public String getResourcePath() {
		return resourcePath;
	}
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	public String getUndoPath() {
		return undoPath;
	}
	public void setUndoPath(String undoPath) {
		this.undoPath = undoPath;
	}
	public String getDonePath() {
		return donePath;
	}
	public void setDonePath(String donePath) {
		this.donePath = donePath;
	}
	public String getBeforePath() {
		return beforePath;
	}
	public void setBeforePath(String beforePath) {
		this.beforePath = beforePath;
	}	
		
}
