package platform.szxyzxx.web.oa.termial.vo;

import java.util.ArrayList;
import java.util.List;

import platform.education.oa.model.Notice;
import platform.education.oa.model.NoticeImg;
import platform.education.oa.utils.UtilDate;
 
 
public class PhoneNoticeVo {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 公告标题
	 */
	private String title;
	/**
	 * 公告描述
	 */
	private String content;
	/**
	 * 公告类型
	 */
	private String type;
	 
	/**
	 * 发布者用户名
	 */
	private String posterName;
	/**
	 * 创建时间
	 */
	private String createDate;
	/**
	 * 修改时间
	 */
	private String modifyDate;
	
	private List<PhoneNoticeImgsVo> images=new ArrayList<PhoneNoticeImgsVo>();
	public PhoneNoticeVo(Notice n,List<PhoneNoticeImgsVo> img){
		this.id=n.getId()+"";
		this.title=n.getTitle();
		this.content=n.getContent();
		this.type=n.getType();
		this.posterName=n.getPosterName();
		this.createDate=UtilDate.getDateFormatter(n.getCreateDate());
		this.modifyDate=UtilDate.getDateFormatter(n.getModifyDate());
		
		if(img!=null){
			images=img;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPosterName() {
		return posterName;
	}

	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<PhoneNoticeImgsVo> getImages() {
		return images;
	}

	public void setImages(List<PhoneNoticeImgsVo> images) {
		this.images = images;
	}
	 
	
	
	
}