package platform.education.rest.bp.service.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级动态客户端vo
 * @author huchuhan
 *
 */
public class CircleMessageClientVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String content;
	private Date postTime;
	private String modifyTime;
	private Integer posterId;
	private String posterName;
	private List<Map<String, Object>> files;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getPosterId() {
		return posterId;
	}
	public void setPosterId(Integer posterId) {
		this.posterId = posterId;
	}
	public String getPosterName() {
		return posterName;
	}
	public void setPosterName(String posterName) {
		this.posterName = posterName;
	}
	public List<Map<String, Object>> getFiles() {
		return files;
	}
	public void setFiles(List<Map<String, Object>> files) {
		this.files = files;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	
	public String getModifyTime() {
		return modifyTime;
	}
	
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
