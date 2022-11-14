package platform.resource.main.vo;

public interface HandleResourceInterface {
	
	public boolean checkBox(String checkBox);
	public boolean resourcePath(String resourcePath);
	public String startUploadResource();
	public String startUploadKnowledge();
	String outBeforeDoneString();
	String outNowDoneString();
	String outNowUndoString();

}
