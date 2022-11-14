package platform.education.paper.model;

import java.io.Serializable;
import java.util.List;

/**
 * 上传答案数据模型
 * @author pantq
 *
 */
public class UploadAnswerModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//图片文件UUID
	private  String fileUuid;

	private List<AnswerQuestionModel> questions;

	public List<AnswerQuestionModel> getQuestions() {
		return questions;
	}


	public void setQuestions(List<AnswerQuestionModel> questions) {
		this.questions = questions;
	}


	public String getFileUuid() {
		return fileUuid;
	}


	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
	}
}
