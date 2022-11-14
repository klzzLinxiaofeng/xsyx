/**   
* @Title: PaperProperty.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月27日 上午9:44:58 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;

/** 
* @ClassName: PaperProperty 
* @Description: 试卷属性实体类 
* @author pantq
* @date 2017年2月27日 上午9:44:58 
*  
*/
public class PaperProperty implements Serializable{
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private String stageCode; //学段CODE
	private String subjectCode; //学科CODE
	private String gradeCode;//年级CODE
	private String versionCode; //版本CODE
	private String volumnCode;//版本CODE
	/**
	 * @return the stageCode
	 */
	public String getStageCode() {
		return stageCode;
	}
	/**
	 * @param stageCode the stageCode to set
	 */
	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	/**
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}
	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	/**
	 * @return the gradeCode
	 */
	public String getGradeCode() {
		return gradeCode;
	}
	/**
	 * @param gradeCode the gradeCode to set
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	/**
	 * @return the versionCode
	 */
	public String getVersionCode() {
		return versionCode;
	}
	/**
	 * @param versionCode the versionCode to set
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	/**
	 * @return the volumnCode
	 */
	public String getVolumnCode() {
		return volumnCode;
	}
	/**
	 * @param volumnCode the volumnCode to set
	 */
	public void setVolumnCode(String volumnCode) {
		this.volumnCode = volumnCode;
	}
	
	
	

}
