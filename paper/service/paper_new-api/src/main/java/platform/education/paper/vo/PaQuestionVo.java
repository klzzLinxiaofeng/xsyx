package platform.education.paper.vo;

import java.util.ArrayList;
import java.util.List;

import platform.education.paper.model.PaQuestion;

/**
 * PaQuestion
 * 
 * @author AutoCreate
 *
 */
public class PaQuestionVo extends PaQuestion {
	private static final long serialVersionUID = 1L;
	private List<String> knowledge;
	private String difficulityString;
	private Integer rightRate;
	private String questionTypeString;
	private Float score;
	private String stageCode;
	private String subjectCode;
	private Integer treeParentId;
	private Integer orderType;
	private Integer nodeOrder;
	private List<TeamQuestionOptions> questionAnswerList;
	private Integer pos;
	private Integer paperId;
	private Boolean isFav;
	private String[] answers;
	private String[] correctAnswers;
	private List<PaQuestionVo> childrenQuestionVo = new ArrayList<PaQuestionVo>();
	private String memo;
	private Integer num;
	private Integer statusForBasket;
	public Integer getStatusForBasket() {
		return statusForBasket;
	}

	public void setStatusForBasket(Integer statusForBasket) {
		this.statusForBasket = statusForBasket;
	}

	@SuppressWarnings("unused")
	private Integer answersCount;

	private String ecContent;

	public String getEcContent() {
		return ecContent;
	}

	public void setEcContent(String ecContent) {
		this.ecContent = ecContent;
	}

	public List<PaQuestionVo> getChildrenQuestionVo() {
		return childrenQuestionVo;
	}

	public void setChildrenQuestionVo(List<PaQuestionVo> childrenQuestionVo) {
		this.childrenQuestionVo = childrenQuestionVo;
	}

	public Boolean getIsFav() {
		return isFav;
	}

	public void setIsFav(Boolean isFav) {
		this.isFav = isFav;
	}

	public List<TeamQuestionOptions> getQuestionAnswerList() {
		return questionAnswerList;
	}

	public void setQuestionAnswerList(List<TeamQuestionOptions> questionAnswerList) {
		this.questionAnswerList = questionAnswerList;
	}

	public Integer getNodeOrder() {
		return nodeOrder;
	}

	public void setNodeOrder(Integer nodeOrder) {
		this.nodeOrder = nodeOrder;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getTreeParentId() {
		return treeParentId;
	}

	public void setTreeParentId(Integer treeParentId) {
		this.treeParentId = treeParentId;
	}

	public String getStageCode() {
		return stageCode;
	}

	public void setStageCode(String stageCode) {
		this.stageCode = stageCode;
	}
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getQuestionTypeString() {
		return questionTypeString;
	}

	public void setQuestionTypeString(String questionTypeString) {
		this.questionTypeString = questionTypeString;
	}

	public List<String> getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(List<String> knowledge) {
		this.knowledge = knowledge;
	}

	public String getDifficulityString() {
		return difficulityString;
	}

	public void setDifficulityString(String difficulityString) {
		this.difficulityString = difficulityString;
	}

	public Integer getRightRate() {
		return rightRate;
	}

	public void setRightRate(Integer rightRate) {
		this.rightRate = rightRate;
	}

	public Integer getPos() {
		return pos;
	}

	public void setPos(Integer pos) {
		this.pos = pos;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public String[] getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(String[] correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public Integer getAnswersCount() {
		if (answers != null) {
			return correctAnswers.length;
		}
		return 0;
	}

	public void setAnswersCount(Integer answersCount) {
		this.answersCount = answersCount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}