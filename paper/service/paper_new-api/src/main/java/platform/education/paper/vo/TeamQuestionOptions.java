package platform.education.paper.vo;

public class TeamQuestionOptions {
	//选项
		private String questionOption;
		
		//选项所对应的人数
		private Long questionOptionCount;
		
		//选项内容
		private String questionOptionContent;

		public String getQuestionOption() {
			return questionOption;
		}

		public void setQuestionOption(String questionOption) {
			this.questionOption = questionOption;
		}

		public Long getQuestionOptionCount() {
			return questionOptionCount;
		}

		public void setQuestionOptionCount(Long questionOptionCount) {
			this.questionOptionCount = questionOptionCount;
		}

		public String getQuestionOptionContent() {
			return questionOptionContent;
		}

		public void setQuestionOptionContent(String questionOptionContent) {
			this.questionOptionContent = questionOptionContent;
		}
}
