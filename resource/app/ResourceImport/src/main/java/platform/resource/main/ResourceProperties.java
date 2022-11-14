package platform.resource.main;

public class ResourceProperties {

    private String resourcePath="";	
	private String undoPath = "";
	private String donePath = "";
	private String beforePath = "";
	private String lineMark = "";
	
	private String[] defaultMicros;
	private String[] defaultLearning_designs;
	private String[] defaultLearning_plan;
	private String[] defaultHomeworks;
	private String[] defaultExams;
	private String[] defaultTeaching_plans;
	private String[] defaultMaterials;
	private String[] defaultOthers;
	
	
	
	public String[] getDefaultOthers() {
		return defaultOthers;
	}
	public void setDefaultOthers(String[] defaultOthers) {
		this.defaultOthers = defaultOthers;
	}
	public String[] getDefaultMicros() {
		return defaultMicros;
	}
	public void setDefaultMicros(String[] defaultMicros) {
		this.defaultMicros = defaultMicros;
	}
	public String[] getDefaultLearning_designs() {
		return defaultLearning_designs;
	}
	public void setDefaultLearning_designs(String[] defaultLearning_designs) {
		this.defaultLearning_designs = defaultLearning_designs;
	}
	public String[] getDefaultHomeworks() {
		return defaultHomeworks;
	}
	public void setDefaultHomeworks(String[] defaultHomeworks) {
		this.defaultHomeworks = defaultHomeworks;
	}
	public String[] getDefaultExams() {
		return defaultExams;
	}
	public void setDefaultExams(String[] defaultExams) {
		this.defaultExams = defaultExams;
	}
	public String[] getDefaultTeaching_plans() {
		return defaultTeaching_plans;
	}
	public void setDefaultTeaching_plans(String[] defaultTeaching_plans) {
		this.defaultTeaching_plans = defaultTeaching_plans;
	}
	public String[] getDefaultMaterials() {
		return defaultMaterials;
	}
	public void setDefaultMaterials(String[] defaultMaterials) {
		this.defaultMaterials = defaultMaterials;
	}
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
	public String[] getDefaultLearning_plan() {
		return defaultLearning_plan;
	}
	public void setDefaultLearning_plan(String[] defaultLearning_plan) {
		this.defaultLearning_plan = defaultLearning_plan;
	}	
		
}
