package platform.education.generalTeachingAffair.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * @功能描述: 成绩分析相关接口DAO
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年2月10日下午6:12:24
 */
public interface ScoreAnalysisDao  {

	/**
	 * 根据学校ID和用户ID查找该学校该用户的相关学年学期信息
	 * @param schoolId 学校ID
	 * @param userId 用户ID
	 * @return
	 */
	List<Map<String,Object>> findSchoolTermByUserId(Integer schoolId,Integer userId);
	
	
	/**
	 * 根据班级ID、学年、学期查找相应的考务信息(已发布成绩C端才能显示)
	 * @param teamId 班级ID
	 * @param schoolYear 学年
	 * @param termCode 学期
	 * @param isJointExam 是否统考（1：是，0：否）
	 * @param schoolId 学校ID
	 * @return
	 */
	List<Map<String,Object>> findAllExamWorksByTeam(Integer teamId,String schoolYear, String termCode, Integer isJointExam,Integer schoolId);
	
	/**
	 * 根据考务ID、学校ID、用户ID查找某个学生的成绩（班级测试FREE界面）
	 * @param examWorksId 考务ID
	 * @param schoolId 学校ID
	 * @param userId 用户ID
	 * @return
	 */
	
	Map<String,Object>findTeamExamWorksByUserId(Integer examWorksId,Integer schoolId,Integer userId);

	
	/**
	 * 查找班级、年级参与考试学生数
	 * @param examId pj_exam表ID
	 * @param jointExamCode 代表同一次考试code
	 * @return
	 */
	Integer findExamStudentCount(Integer examId,String jointExamCode);
	
	/**
	 * 查找个人分数趋势已经个人班内排名
	 * @param userId 用户ID
	 * @param teamId 班级ID
	 * @return
	 */
	List<Map<String,Object>> findFractionalTrendByUserId(Integer userId,Integer teamId);
	
	

	/**
	 * 查找某个班级的班级排名列表 
	 * @param examId
	 * @return
	 */
	List<Map<Integer,Object>> findTeamRanksByExamId(Integer examId);
	
	

	/**
	 * 根据用户ID查找某个的考务信息
	 * @param examWorksId 考务ID
	 * @param userId 用户ID
	 * @param subjectCode 科目
	 * @return
	 */
	List<Map<String, Object>> findExamWorksByUserId(Integer examWorksId, Integer userId,String subjectCode, Integer isPublished);
	
	
	/**
	 * 根据考务ID查询某个人的所有需要统计科目信息
	 * @param examWorksId 考务ID
	 * @param userId 用户ID
	 * @return
	 */
	List<Map<String,Object>> findUserExamTeamSubjectScore(Integer examWorksId,Integer userId);
	
	
	/**
	 * 根据jointExamCode和科目名称 查找年级的总得分
	 * @param jointExamCode
	 * @param subjectCode
	 * @return
	 */
	Float findGradeTotalScore(String jointExamCode,String subjectCode);
	
	/**
	 * 根据是否统考，学科，班级，用户ID 查找最近10次考试信息
	 * @param isJointExam 1：统考，0：普通考试
	 * @param subjectCode 科目CODE
	 * @param teamId 班级ID
	 * @param userId 用户ID
	 * @return
	 */
	List<Map<String,Object>> findUserExamWorksBySubjectCodeAndUserId(Integer isJointExam ,String subjectCode,Integer teamId,Integer userId, Integer isPublished);
	
	
	/**
	 * 根据考务ID、班级ID查找某个班级所有学生需要参与统计的全科总分
	 * @param examWorksId
	 * @param teamId
	 * @return
	 */
	List<Map<String,Object>> findAllSubjectTotalScoreByExamWorksId(Integer examWorksId,Integer teamId);
	
}
