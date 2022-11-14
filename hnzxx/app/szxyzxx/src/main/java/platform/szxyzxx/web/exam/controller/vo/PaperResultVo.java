/**   
* @Title: ExamVo.java
* @Package platform.szxyzxx.web.exam.controller.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月27日 下午3:47:40 
* @version V1.0   
*/
package platform.szxyzxx.web.exam.controller.vo;

import java.io.Serializable;

import platform.service.storage.vo.FileResult;

/** 
* @ClassName: ExamVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年2月27日 下午3:47:40 
*  
*/
public class PaperResultVo implements Serializable{
	
		/** 
		* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
		*/ 
		private static final long serialVersionUID = 1L;
		
		/**
		 * 试卷标题
		 */
		private String title;
		
		private Integer paperId;
		
		/**
		 * 用户名字
		 */
		private Integer userId;
		
		private String userName;
		
		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

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
		 * 文件实体类
		 */
		private FileResult fileResult;
		
		/**
		 * paperUuid
		 */
		private String paperUuid;

		private String subjectCode; 
		private String versionCode;
		private String gradeCode ;
		private String volumnCode ;
		private String stageCode;
		
		private String subjectName; 
		private String versionName;
		private String gradeName ;
		private String volumnName ;
		private String stageName;
		
		private Integer schoolId;
		
		private Integer appId;
		
		private String fileUuid;
		
		private String schoolName;
		
		private String textbookCatalogCode;
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
		 * @return the fileResult
		 */
		public FileResult getFileResult() {
			return fileResult;
		}

		/**
		 * @param fileResult the fileResult to set
		 */
		public void setFileResult(FileResult fileResult) {
			this.fileResult = fileResult;
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
		 * @return the title
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * @param title the title to set
		 */
		public void setTitle(String title) {
			this.title = title;
		}

		/**
		 * @return the userId
		 */
		public Integer getUserId() {
			return userId;
		}

		/**
		 * @param userId the userId to set
		 */
		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		/**
		 * @return the subjectName
		 */
		public String getSubjectName() {
			return subjectName;
		}

		/**
		 * @param subjectName the subjectName to set
		 */
		public void setSubjectName(String subjectName) {
			this.subjectName = subjectName;
		}

		/**
		 * @return the versionName
		 */
		public String getVersionName() {
			return versionName;
		}

		/**
		 * @param versionName the versionName to set
		 */
		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}

		/**
		 * @return the gradeName
		 */
		public String getGradeName() {
			return gradeName;
		}

		/**
		 * @param gradeName the gradeName to set
		 */
		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
		}

		/**
		 * @return the volumnName
		 */
		public String getVolumnName() {
			return volumnName;
		}

		/**
		 * @param volumnName the volumnName to set
		 */
		public void setVolumnName(String volumnName) {
			this.volumnName = volumnName;
		}

		/**
		 * @return the stageName
		 */
		public String getStageName() {
			return stageName;
		}

		/**
		 * @param stageName the stageName to set
		 */
		public void setStageName(String stageName) {
			this.stageName = stageName;
		}

		/**
		 * @return the schoolId
		 */
		public Integer getSchoolId() {
			return schoolId;
		}

		/**
		 * @param schoolId the schoolId to set
		 */
		public void setSchoolId(Integer schoolId) {
			this.schoolId = schoolId;
		}

		/**
		 * @return the appId
		 */
		public Integer getAppId() {
			return appId;
		}

		/**
		 * @param appId the appId to set
		 */
		public void setAppId(Integer appId) {
			this.appId = appId;
		}

		/**
		 * @return the schoolName
		 */
		public String getSchoolName() {
			return schoolName;
		}
		public String getFileUuid() {
			return fileUuid;
		}

		/**
		 * @param schoolName the schoolName to set
		 */
		public void setSchoolName(String schoolName) {
			this.schoolName = schoolName;
		}

		public void setFileUuid(String fileUuid) {
			this.fileUuid = fileUuid;
		}

		public String getTextbookCatalogCode() {
			return textbookCatalogCode;
		}

		public void setTextbookCatalogCode(String textbookCatalogCode) {
			this.textbookCatalogCode = textbookCatalogCode;
		}


	}	
		
