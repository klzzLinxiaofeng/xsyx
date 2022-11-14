package platform.education.generalTeachingAffair.service;

import java.util.Date;
import java.util.List;

import platform.education.generalTeachingAffair.model.ApsMedal;
import platform.education.generalTeachingAffair.model.ApsRuleItem;
import platform.education.generalTeachingAffair.model.ApsStuSummary;
import platform.education.generalTeachingAffair.model.ApsTask;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.vo.ClassEvaScoreData;
import platform.education.generalTeachingAffair.vo.ClassOptimizingSummaryData;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.IncreaseEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseSummaryData;
import platform.education.generalTeachingAffair.vo.NormalEvaScoreData;
import platform.education.generalTeachingAffair.vo.NormalSummaryData;
import platform.education.generalTeachingAffair.vo.StarEvaluateData;
import platform.education.generalTeachingAffair.vo.StudentItemData;
import platform.education.generalTeachingAffair.vo.StudentScoreData;

public interface StudentApsService {
	//==================================================== basisc start ====================================================
	
	/**
	 * 添加学生评价任务
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_school_term.code
	 */
	Boolean addStudentEvaluationTask(Integer schoolId, String termCode);
	
	/**
	 * 获取某个学校下某个学期的所有学生评价项目
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_school_term.code
	 * @return
	 */
	List<ApsTaskItem> findTaskItems(String termCode);

	/**
	 * 根据评价项目类型获取某个学校下某个学期的学生评价项目
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_school_term.code
	 * @param checkType 01：常规、02：加分、03：减分
	 * @return
	 */
	List<ApsTaskItem> findTaskItemsByCheckType(String termCode, String checkType);

	/**
	 * 获取某个学校某个学期下的减分评价项目，如课堂优化项目
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_school_term.code
	 * @return
	 */
	List<ApsTaskItem> findDeductMarksItems( String termCode);

	/**
	 * 获取某个学校某个学期下的加分评价项目，如激励评价项目
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_school_term.code
	 * @return
	 */
	List<ApsTaskItem> findAwardedMarksItems(String termCode);

	/**
	 * 获取某个学校某个学期下的常规评价项目，如发展评价项目
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_school_term.code
	 * @return
	 */
	List<ApsTaskItem> findNormalItems(String termCode);
	
	/**
	 * 获取某个学校某个学期下 某一类型的 激励评价项目
	 * @param termCode
	 * @param category
	 * @return
	 */
	List<ApsTaskItem> findAwardedItemsOfCategory(String termCode, String category);

	/**
	 * 获取某个学生评价任务的评价项目
	 * @param taskId 学生评价任务ID pj_aps_task.id
	 * @return
	 */
	List<ApsTaskItem> findTaskItems(Integer taskId);

	/**
	 * 获取某个学生评价任务的某种评价项目类型的评价项目
	 * @param taskId 	学生评价任务ID pj_aps_task.id
	 * @param checkType 	评价项目类型 01：常规、02：加分、03：减分
	 * @return
	 */
	List<ApsTaskItem> findTaskItemsByCheckType(Integer taskId, String checkType);

	/**
	 * 获取某个学生评价任务的减分项目，如课堂优化项目
	 * @param taskId 	学生评价任务ID pj_aps_task.id
	 * @return
	 */
	List<ApsTaskItem> findDeductMarksItems(Integer taskId);

	/**
	 * 获取某个学生评价任务的加分项目，如激励评价项目
	 * @param taskId 	学生评价任务ID pj_aps_task.id
	 * @return
	 */
	List<ApsTaskItem> findAwardedMarksItems(Integer taskId);

	/**
	 * 获取某个学生评价任务的常规项目，如发展卡评价项目
	 * @param taskId 	学生评价任务ID pj_aps_task.id
	 * @return
	 */
	List<ApsTaskItem> findNormalItems(Integer taskId);

	/**
	 * 获取某个学校的学生评价任务对应的评价项目模板数据
	 * @param schoolId 	学校ID pj_school.id
	 * @return
	 */
	List<ApsRuleItem> findRuleItems(Integer schoolId);

	/**
	 * 根据评价项目类型获取某个学校的学生评价任务对应的评价项目模板数据
	 * @param schoolId 	学校ID pj_school.id
	 * @param checkType 评价项目类型 01：常规、02：加分、03：减分
	 * @return
	 */
	List<ApsRuleItem> findRuleItems(Integer schoolId, String checkType);
	
	//==================================================== basisc end ====================================================
	
	//==================================================== 课堂优化 start ====================================================

	/**
	 * 进行课堂评价，针对具体某个学生在某节课上的不良行为进行评价。
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 * @param studentId 	学生ID pj_student.id
	 * @param teamId 		班级ID pj_team.id
	 * @param teacherId 	进行评价的教师ID pj_teacher.id
	 * @param checkDate 	考核日期
	 * @param checkRange 	考核的节次
	 */
	Boolean setClassScores(Integer taskItemId, Integer studentId, Integer teamId, Integer teacherId, Date checkDate, String checkRange);

	/**
	 * 获取课堂优化项目 单人单项单节数
	 * @param taskItemId
	 * @param studentId
	 * @param checkDate
	 * @param checkRange
	 * @return
	 */
	ApsTaskScore getStudentTaskScore(Integer taskItemId, Integer studentId, Date checkDate, String checkRange);
	
	/**
	 * 进行批量课堂评价，针对具体某个班级的所有学生在某节课上的不良行为进行评价。
	 * @param teacherId 	进行评价的教师ID pj_teacher.id
	 * @param teamId 		学生所属的班级ID pj_student.id
	 * @param checkDate 	考核日期
	 * @param checkRange 	核的节次
	 * @param studentItemDatas  被考核的学生以及对应的不良行为项目标记
	 */
	Boolean batchSetClassScores(Integer teacherId, Integer teamId, Date checkDate, String checkRange, List<StudentItemData> studentItemDatas);

	/**
	 * 根据评价项目ID 获取某个班的 课堂优化已经评价的学生列表 用于回显录入列表（某班某节课某个项目的所有学生）
	 * @param teamId 	班级ID pj_team.id
	 * @param itemId 	评价项目ID pj_aps_task_item.id
	 * @param checkDate 	考核日期
	 * @param checkRange 	考核节次
	 * @return
	 */
	List<StudentItemData> findStudentsByItemIdForClassOptimizing(Integer teamId, Integer itemId, Date checkDate, String checkRange);

	/**
	 * 获取某个班已经被差评的学生列表，用于回显录入列表（某班某次所有项目所有的学生）
	 * @param teamId 		班级ID pj_team.id
	 * @param checkDate 	考核日期
	 * @param checkRange 	考核节次
	 * @return
	 */
	List<StudentItemData> findStudentsForClassOptimizing(String termCode, Integer teamId, Date checkDate, String checkRange);
	
	/**
	 * 批量删除某班 某天某节课的激励评价
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @param checkRange
	 * @return
	 */
	Boolean deleteItemScoresOfOptimizing(String termCode, Integer teamId, Date checkDate, String checkRange);
	

	/**
	 * 获取某个学期每周某个班所被差评的学生以及对应的差评项目
	 * @param termCode 		学期CODE pj_school_term.code
	 * @param teamId 		班级ID pj_team.id
	 * @param startDate  	每一周的开始时间
	 * @param finishDate 	每一周的结束时间
	 * @return
	 */
	List<ClassEvaScoreData> findClassScoresForTeam(String schoolYear, String termCode, Integer teamId, Date startDate, Date finishDate, String name);

	/**
	 * 获取某一天某个班级被差评的学生以及对应的差评项目
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	List<ClassEvaScoreData> findClassScoresForTeam(String schoolYear, String termCode, Integer teamId, Date checkDate);
	
	/**
	 * 获取 某时间段内 学校各年级 的课堂优化统计情况
	 * @param schoolId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<ClassOptimizingSummaryData> findClassOptimizingCountForSchool(Integer schoolId, String termCode, Date startDate, Date finishDate);

	/**
	 * 获取 某时间段内 某年级各个班级 的课堂优化统计情况
	 * @param gradeId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<ClassOptimizingSummaryData> findClassOptimizingCountForGrade(Integer gradeId, String termCode, Date startDate, Date finishDate);

	/**
	 * 获取 某时间段内 某班级各个项目 的课堂优化统计情况
	 * @param teamId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<ClassOptimizingSummaryData> findClassOptimizingCountForTeam(Integer teamId, Integer studentId, String termCode, Date startDate, Date finishDate);
	
	//==================================================== 课堂优化 start ====================================================
	
	//==================================================== 激励评价 start ====================================================
	
	/**
	 * 进行激励评价，针对具体某个学生某一天的某项优秀行为进行评价
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 * @param studentId 	学生ID pj_student.id
	 * @param teamId 		班级ID pj_team.id
	 * @param teacherId 	教师ID pj_teacher.id
	 * @param checkDate 	考核日期
	 */
	Boolean setIncreaseScores(Integer taskItemId, Integer studentId, Integer teamId, Integer teacherId, Date checkDate);

	/**
	 * 批量进行激励评价，针对具体某班的所有学生某一天的所有优秀行为进行评价
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 * @param studentId 	学生ID pj_student.id
	 * @param teamId 		班级ID pj_team.id
	 * @param teacherId 	教师ID pj_teacher.id
	 * @param checkDate 	考核日期
	 * @param studentItemDatas 	 被考核的学生以及优秀行为项目标记
	 */
	Boolean batchSetIncreaseScores(Integer teamId, Integer teacherId, Date checkDate, List<StudentItemData> studentItemDatas);

	/**
	 * 获取某个班级 某天 某个优秀行为（激励评价）的记录
	 * @param teamId
	 * @param itemId
	 * @param checkDate
	 * @return
	 */
	List<StudentItemData> findStudentsByItemIdForIncrease(Integer teamId, Integer itemId, Date checkDate);
	
	/**
	 * 获取某个班级 某天 所有优秀行为（激励评价）的记录
	 * @param termCode		
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	List<StudentItemData> findStudentsByItemIdForIncrease(String termCode, Integer teamId, Date checkDate);
	
	/**
	 * 批量删除某天某班的激励评价
	 * @param termCode		
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	Boolean deleteItemScoresOfIncrease(String termCode, Integer teamId, Date checkDate);
	
	/**
	 * 统计班级每个学生的 激励评价总数
	 * @param termCode
	 * @param teamId
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<IncreaseEvaScoreData> findIncreaseScoresForTeam(String schoolYear, String termCode, Integer teamId, Date startDate, Date finishDate);

	/**
	 * 获取 某时间段内 学校各年级 的激励评价统计情况
	 * @param schoolId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<IncreaseSummaryData> findIncreaseCountForSchool(Integer schoolId, String termCode, Date startDate, Date finishDate);
	
	/**
	 * 获取 某时间段内 某年级各班级 的激励评价统计情况
	 * @param gradeId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<IncreaseSummaryData> findIncreaseCountForGrade(Integer gradeId, String termCode, Date startDate, Date finishDate);
	
	/**
	 * 获取 某时间段内 某班级各项目组 的激励评价统计情况
	 * @param teamId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<IncreaseSummaryData> findIncreaseCountForTeam(Integer teamId, Integer studentId, String termCode, Date startDate, Date finishDate);
	
	//==================================================== 激励评价 end ====================================================
	
	//==================================================== 发展评价 start ====================================================
	/**
	 * 设置发展评价卡，针对具体某个学生某一个月的优秀行为进行评分
	 * @param studentId 	学生ID pj_student.id
	 * @param teamId 		班级ID pj_team.id
	 * @param score 		分数
	 * @param teacherId 	评价教师ID pj_teacher.id
	 * @param checkDate 	考核日期
	 */
	Boolean setNormalScores(String termCode, Integer studentId, Integer teamId, Float score, Integer teacherId, Date checkDate);

	/**
	 * 
	 * 批量设置发展评价卡，针对具体某个班级的一批学生某一个月的优秀行为进行评分
	 * @param teamId 				学生所属的班级ID pj_team.id
	 * @param teacherId 			评价教师ID pj_teacher.id
	 * @param studentScoreDatas 	被考核的学生以及对应的分数
	 * @param checkData 			考核日期
	 */
	Boolean batchSetNormalScores(String termCode, Integer teamId, Integer teacherId, List<StudentScoreData> studentScoreDatas, Date checkDate);
	
	/**
	 * 获取某个班级每个学生的发展卡总数
	 * @param termCode		学期Code pj_school_term.code
	 * @param teamId		班级ID pj_team.id
	 * @param startDate		考核的开始时间
	 * @param finishDate	考核的结束时间
	 * @return
	 */
	List<NormalEvaScoreData> findNormalScoresForTeam(String schoolYear, String termCode, Integer teamId, Date startDate, Date finishDate);
	
	/**
	 * 获取 某时间段内 学校各年级 的发展评价统计情况
	 * @param schoolId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<NormalSummaryData> findNormalCountForSchool(Integer schoolId, String termCode, Date startDate, Date finishDate);
	
	/**
	 * 获取 某时间段内 某年级各班级 的发展评价统计情况
	 * @param gradeId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<NormalSummaryData> findNormalCountForGrade(Integer gradeId, String termCode, Date startDate, Date finishDate);
	
	/**
	 * 获取 某时间段内 某班级 的发展评价统计情况
	 * @param teamId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<NormalSummaryData> findNormalCountForTeam(Integer teamId, Integer studentId, String termCode, Date startDate, Date finishDate);
	
	//==================================================== 发展评价 end ====================================================
	
	//==================================================== 星级个人 start ====================================================
	
	/**
	 * 设置 班级之星的 获取人数
	 * @param gradeId		年级ID pj_grade.id
	 * @param reachCount	入围次数（可获奖人数）
	 */
	Boolean setTeamStarReachCount(Integer gradeId, Integer reachCount);
	
	/**
	 * 批量设置 班级之星的 获取人数
	 * @param evaluationMedalDatas 年级和对应项目分数的数据
	 */
	Boolean bacthSetTeamStarReachCount(List<EvaluationMedalData> evaluationMedalDatas);
	
	/**
	 * 设置 年级之星的 获取人数
	 * @param gradeId		年级ID pj_grade.id
	 * @param reachCount	入围次数（可获奖人数）
	 */
	Boolean setGradeStarReachCount(Integer gradeId, Integer reachCount);
	
	/**
	 * 批量 年级之星的 获取人数
	 * @param evaluationMedalDatas 年级和对应项目分数的数据
	 */
	Boolean batchSetGradeStarReachCount(List<EvaluationMedalData> evaluationMedalDatas);
	
	//-------------------//
	
	/**
	 * 设置 全校之星的 获取人数
	 * @param schoolId 	学校ID pj_school.id
	 * @param reachCount	入围次数（可获奖人数）
	 */
	Boolean setSchoolStarReachCount(Integer schoolId, Integer reachCount);
	
	
	/**
	 * 设置星级个人的 获奖人数
	 * @param gradeId		年级ID pj_grade.id
	 * @param reachCount	入围次数（可获奖人数）
	 */
	Boolean setStarReachCount(Integer gradeId, String name, Integer reachCount);
	
	/**
	 * 批量设置 班级之星和年级之星的 获奖人数
	 * @param datas
	 */
	void batchsetStarReachCount(List<EvaluationMedalData> datas);
	
	/**
	 * 获取星级个人的 获奖人数
	 * @param gradeId	年级ID pj_grade.id
	 * @param name		奖项名称
	 * @return
	 */
	ApsMedal getStarReachCount(Integer gradeId, String name);
	
	/**
	 *  获取 全校之星的 获奖人数
	 * @param schoolId
	 * @return
	 */
	ApsMedal getSchooltStarReachCount(Integer schoolId);

	/**
	 * 批量获取 年纪之星和班级之星的 获奖人数
	 * @param schoolId
	 * @param termCode
	 * @return
	 */
	List<EvaluationMedalData> batchgetStarReachCount(Integer schoolId, String termCode);
	
	//----------------------//
	
	/**
	 * 获取月度 的班级之星
	 * @param termCode		学期Code pj_school_term.code	
	 * @param teamId		班级ID pj_team.id
	 * @param startDate		每月的开始时间
	 * @param endDate		每月的结束时间
	 */
	List<StarEvaluateData> getMonthlyTeamStar(String schoolYear, String termCode, Integer teamId, Date startDate, Date endDate);
	
	/**
	 * 获取月度 的年级之星	
	 * @param termCode		学期Code pj_school_term.code	
	 * @param gradeId		年级ID pj_grade.id
	 * @param startDate		每月的开始时间
	 * @param endDate		每月的结束时间
	 */
	List<StarEvaluateData> getMonthlyGradeStar(String schoolYear, String termCode, Integer gradeId, Date startDate, Date endDate);
	
	/**
	 * 获取月度 的全校之星
	 * @param termCode		学期Code pj_school_term.code	
	 * @param schoolId		学校ID pj_school.id
	 * @param startDate
	 * @param endDate
	 */
	List<StarEvaluateData> getMonthlySchoolStar(String schoolYear, String termCode, Integer schoolId, Date startDate, Date endDate);

	/**
	 * 获取星级个人（未评定）
	 * @param termCode
	 * @param objectId
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ApsStuSummary> getPersonalStar(String schoolYear, String termCode, Integer objectId,String type, Date startDate, Date endDate);

	/**
	 * 获取星级个人（评定后）
	 * @param termCode
	 * @param objectId
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param periodCode
	 * @return
	 */
	List<ApsStuSummary> getEvaluateStar(String termCode, Integer objectId,String type, 
			Date startDate, Date endDate, String periodCode);
	
	/**
	 * 评定星级个人
	 * @param termCode
	 * @param objectId
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @param periodCode
	 * @return
	 */
	List<ApsStuSummary> evaluatePersonalStar(String schoolYear, String termCode, Integer objectId,String type, 
			Date startDate, Date endDate, String periodCode);

	
	/**
	 * 删除学生的某种评价卡
	 * @param termCode
	 * @param studentId
	 * @param checkType
	 * @param checkDate
	 * @param checkRange
	 * @return
	 */
	Boolean deleteStudentItemDatas(String termCode, Integer studentId, String checkType, Date checkDate,String checkRange);
	
	List<ApsTaskScore> findApsTaskScoreByCondition(ApsTaskScore apsTaskScore);
	
	ApsTask getApsTask(String termCode);
	
}
