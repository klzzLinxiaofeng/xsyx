package platform.szxyzxx.web.teach.vo;

import java.util.List;

public class TextBookCatalogVoResourse {
	
	List<ResourseVo> resourseVoList;
	private boolean isDownload;//数据库是否有这条目录记录
	private Integer textBookId;
	private Integer parentId;
	private Integer id;
	private Integer level;
	private String catalogtype;
	private Integer page;
	private String name;
	private boolean doneBefore;
	private boolean isWrong;
	private String message;
	public static String ERROR="教材目录添加错误";
	public static String SUCCESS="教材目录添加成功";
	public static String BEFORESUCCESS="教材目录已添加过";
	private String path;
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isWrong() {
		return isWrong;
	}
	public void setWrong(boolean isWrong) {
		this.isWrong = isWrong;
	}
	public boolean isDoneBefore() {
		return doneBefore;
	}
	public void setDoneBefore(boolean doneBefore) {
		this.doneBefore = doneBefore;
	}
	
	public Integer getTextBookId() {
		return textBookId;
	}

	public void setTextBookId(Integer textBookId) {
		this.textBookId = textBookId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCatalogtype() {
		return catalogtype;
	}

	public void setCatalogtype(String catalogtype) {
		this.catalogtype = catalogtype;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDownload() {
		return isDownload;
	}

	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	public List<ResourseVo> getResourseVoList() {
		return resourseVoList;
	}

	public void setResourseVoList(List<ResourseVo> resourseVoList) {
		this.resourseVoList = resourseVoList;
	}
	
	
}
