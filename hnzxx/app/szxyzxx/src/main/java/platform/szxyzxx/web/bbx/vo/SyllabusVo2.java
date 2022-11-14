package platform.szxyzxx.web.bbx.vo;

import java.io.Serializable;

import platform.education.generalTeachingAffair.vo.SyllabusVo;

public class SyllabusVo2 extends SyllabusVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9217680553850030643L;
	/**
	 * 课程类型： 0不上课，1行政班，2走班
	 */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
