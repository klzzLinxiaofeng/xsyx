package platform.education.generalTeachingAffair.vo;

import java.util.List;

/**
 * 存放成绩查询结果集
 * @author Administrator
 *
 */
public class ExamScoreSelectedVo {
	
	private List<ExamScoreVo> examScoreVoList;//存放各次考试及其成绩
	
	public List<ExamScoreVo> getExamScoreVoList() {
		return examScoreVoList;
	}

	public void setExamScoreVoList(List<ExamScoreVo> examScoreVoList) {
		this.examScoreVoList = examScoreVoList;
	}
	
}
