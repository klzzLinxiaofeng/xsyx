package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamQuestionScoreVo;
import platform.education.generalTeachingAffair.model.ExamQuestionVo;
import platform.education.generalTeachingAffair.model.ExamTeamSubject;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectVo;

import java.util.List;

public interface ExamTeamSubjectDao extends GenericDao<ExamTeamSubject, java.lang.Integer> {

	List<ExamTeamSubject> findExamTeamSubjectByCondition(ExamTeamSubjectCondition examTeamSubjectCondition, Page page, Order order);
	List<ExamTeamSubjectVo> findGradeByCondition(Integer gradeId, Integer schoolId, String schoolYear, String schoolTrem, Integer type);
	List<Double> findAvgScoreTeam(Integer schoolId,String schoolYear,String schoolTrem,Integer gradeId,String code,String type);
	List<Double> findAvgScoreGrade(Integer schoolId,String schoolYear,String schoolTrem,Integer gradeId,String code);


	ExamTeamSubject findById(Integer id);
	ExamTeamSubject findByExamId(Integer id);
	
	Long count(ExamTeamSubjectCondition examTeamSubjectCondition);

	Integer createquest(ExamQuestionVo examQuestionVo);
	Integer createquestScore(ExamQuestionScoreVo examQuestionScoreVo);
}
