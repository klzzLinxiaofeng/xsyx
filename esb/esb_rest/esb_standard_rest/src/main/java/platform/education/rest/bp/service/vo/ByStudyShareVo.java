package platform.education.rest.bp.service.vo;

import java.io.Serializable;
import java.util.List;

public class ByStudyShareVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 班级:team.id
	 */
	private Integer teamId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 创建时间
	 */
	private Long createDate;
	
	private List<String> imageUrlList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
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

	public Long getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public List<String> getImageUrlList() {
		return imageUrlList;
	}

	public void setImageUrlList(List<String> imageUrlList) {
		this.imageUrlList = imageUrlList;
	}
}
