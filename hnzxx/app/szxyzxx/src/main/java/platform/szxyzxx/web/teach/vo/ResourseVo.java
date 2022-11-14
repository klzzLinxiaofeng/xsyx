package platform.szxyzxx.web.teach.vo;


public class ResourseVo {
	
	private String name;//名称
	private Integer level;//层次
	private String path;//路径
	private boolean isLeaf;
	private boolean isDownload;//数据库是否有这条资源记录
	private String message;
	public static String ERROR="资源添加错误";
	public static String SUCCESS="资源添加成功";
	public static String BEFORESUCCESS="资源已添加过";
	
	/*//微课= 1;
    public static final Integer MICRO 
    //课件= 2;
    public static final Integer LEARNING_DESIGN 
    //作业= 3;
    public static final Integer HOMEWORK 
    //试卷= 4;
    public static final Integer EXAM 
    //教案= 5;
    public static final Integer TEACHING_PLAN 
    //素材= 6;
    public static final Integer MATERIAL */
	private Integer resType;//资源类型
	private String resName;//资源名称
	
	

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}

	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
