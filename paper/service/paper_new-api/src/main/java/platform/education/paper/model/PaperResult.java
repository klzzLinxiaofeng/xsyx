/**   
* @Title: PaperResult.java
* @Package platform.education.paper.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月23日 下午7:53:51 
* @version V1.0   
*/
package platform.education.paper.model;

import java.io.Serializable;

/** 
* @ClassName: PaperResult 
* @Description: Paper模块返回值实体类
* @author pantq
* @date 2017年2月23日 下午7:53:51 
*  
*/
public class PaperResult implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = 1L;
	private Integer paperId;
	private String paperUuid;
	
	/**
	 * 状态
	 * 1. 文件不存在
	 * 2. 文件已存在并且没有被使用
	 * 3. 文件已存在并且被使用
	 */
	private Integer status;
	
	/**
	 * 状态
	 * 1. 文件不存在
	 * 2. 文件已存在并且没有被使用
	 * 3. 文件已存在并且被使用
	 */
	private String msg;
	

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the paperId
	 */
	public Integer getPaperId() {
		return paperId;
	}

	/**
	 * @param paperId the paperId to set
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	/**
	 * @return the paperUuid
	 */
	public String getPaperUuid() {
		return paperUuid;
	}

	/**
	 * @param paperUuid the paperUuid to set
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
	}

}	
	
	
