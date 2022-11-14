package platform.szxyzxx.web.bbx.client.vo;

import java.util.List;

public class GradeClientVo <T>  {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 年级id
	 */
	 private Integer gradeId;
	 
	 /**
	  * 年级名称
	  */
	private String gradeName;
	
	/**
	 *学段 
	 */
	private String stageCode;
	
	/**
	 * 年级在学校中的顺序，如初二是2
	 */
	private Integer gradeNumber;
	
	
	/**
	 * 对象
	 */
	private List<T> tlist;
	
	public GradeClientVo() {
			
		}
	
	
	
	
	
	public GradeClientVo(Integer gradeId, String gradeName, String stageCode,
			Integer gradeNumber, List<T> tlist) {
		super();
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.stageCode = stageCode;
		this.gradeNumber = gradeNumber;
		this.tlist = tlist;
	}



	public GradeClientVo(Integer gradeId,String gradeName, List<T> tlist) {
		this.gradeId = gradeId;
		this.gradeName = gradeName;
		this.tlist = tlist;
	}


	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public List<T> getTlist() {
		return tlist;
	}

	public void setTlist(List<T> tlist) {
		this.tlist = tlist;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}

	public Integer getGradeNumber() {
		return gradeNumber;
	}

	public void setGradeNumber(Integer gradeNumber) {
		this.gradeNumber = gradeNumber;
	}

	
	
}
