package platform.education.generalTeachingAffair.service;


import java.util.Date;
import java.util.List;

import platform.education.generalTeachingAffair.vo.RedBannerVo;
import platform.education.generalTeachingAffair.model.ApsRuleItem;
import platform.education.generalTeachingAffair.vo.DutyTeacherStatData;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.JudgeTeacher;
import platform.education.generalTeachingAffair.vo.RedBannerScore;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;

import java.util.Date;
import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ApsMedalWinner;
import platform.education.generalTeachingAffair.model.ApsTask;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskJudge;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.model.ApsTeamSummary;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;


public interface TeamApsService {
	
	/**
	 * 新建班级评价任务
	 * @param schoolId	学校ID pj_school.id
	 * @param termCode	学期代码 pj_school_term.code
	 */
	void AddTeamTask (Integer schoolId, String termCode);
	
	/**
	 * 设置某个班级针对某个评价项目所得的分数
	 * @param taskItemId  	评价项目ID pj_aps_task_item.id
	 * @param teamId		班级ID pj_team.id
	 * @param teacherId		考核教师ID pj_teacher.id
	 * @param score 		所得分数
	 * @param checkDate 	考核日期
	 */
	void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, Integer teacherId, Float score, Date checkDate);
	
	/**
	 * 获取某个班级针对某个评价项目的分数
	 * @param taskItemId	
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	ApsTaskScore getTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Date checkDate);
	
	/**
	 * 修改某个班级针对某个评价项目的分数
	 * @param taskItemId	
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	void modifyTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Float score, Date checkDate);
	
	/**
	 * 设置某个班级针对某个评价项目所得的分数
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 * @param teamId 		班级ID pj_team.id
	 * @param teacherId 	考核教师ID pj_teacher.id
	 * @param score 		所得分数
	 * @param checkDate 	考核日期
	 * @param remark 		备注信息
	 */
	void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, Integer teacherId, Float score, Date checkDate,String remark);

	/**
	 * 设置某个班级针对某个评价项目所得的分数
	 * @param taskItemId  	评价项目ID pj_aps_task_item.id
	 * @param teamId		班级ID pj_team.id
	 * @param teacherId		考核教师ID pj_teacher.id
	 * @param score 		所得分数
	 * @param checkDate 	考核日期
	 * @param fileUUIDs 	附件UUID集合
	 */
	void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, Integer teacherId, Float score, Date checkDate,List<String> fileUUIDs);

	/**
	 * 设置某个班级针对某个评价项目所得的分数
	 * @param taskItemId  	评价项目ID pj_aps_task_item.id
	 * @param teamId		班级ID pj_team.id
	 * @param teacherId		考核教师ID pj_teacher.id
	 * @param score 		所得分数
	 * @param checkDate 	考核日期
	 * @param remark 		备注信息
	 * @param fileUUIDs		附件UUID集合
	 */
	void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, Integer teacherId, Float score, Date checkDate,String remark, List<String> fileUUIDs);
	
	/**
	 * 批量设置整个年级的一次评分
	 * @param teacherid		考核教师ID pj_teacher.id
	 * @param checkDate		考核日期
	 * @param teamScoreDatas	整个年级所有班级所有项目的得分数据
	 */
	void batchSetTeamEvaluationTaskScore(Integer teacherId, Integer parentObjectId, Date checkDate, List<TeamScoreData> teamScoreDatas);
	
	/**
	 * 查询整个年级的一次评分结果（扣分项）
	 * @param schoolId		
	 * @param termCode
	 * @param checkDate		
	 * @return
	 */
	List<TeamScoreData> findTeamMinusScoresOfGrade(Integer schoolId, String termCode, Integer gradeId, Date checkDate);
	
	/**
	 * 查询整个年级的一次评分结果（加分项）
	 * @param schoolId		
	 * @param termCode
	 * @param checkDate		
	 * @return
	 */
	List<TeamScoreData> findTeamAddScoresOfGrade(Integer schoolId, String termCode, Integer gradeId, Date checkDate);
	
	/**
	 * 获取班级评价的所有项目名单
	 * @param schoolId
	 * @param termCode
	 * @return
	 */
	List<ApsTaskItem> findTeamTaskItems(String termCode);
	
	/**
	 * 添加班级评价任务 自定义评价项目
	 * @param taskId   	班级评价任务ID pj_aps_task.id
	 * @param name  	评价项目名称
	 * @param checkType 	 评价项目类型， 01：常规、02：加分、03：减分
	 * @param score		评价项目对应的分值
	 */
	void addTeamEvaluationTask (Integer taskID, String name, String checkType, Float score );
	
	/**
	 * 启用 评价项目
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 */
	void changeEnableOfItem(Integer taskItemId);
	
	/**
	 * 删除 评价项目(逻辑删除)
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 */
	void deleteTeamEvaItem(Integer taskItemId);
	
	/**
	 * 修改对应的评价项目名称
	 * @param itemId 	评价项目ID pj_aps_task_item.id
	 * @param name 		评价项目名称
	 */
	void updateTeamEvaluationItemOfName(String name,Integer id);
	
	/**
	 * 设置年级每周可获取流动红旗的标准分
	 * @param gradeId 	年级ID pj_grade.id
	 * @param score 	分数
	 */
	void setRedBannerWeeklyStandardScore(Integer gradeId, Float score,int count, String criterion);
	
	/**
	 * 修改某个评价项目的分数
	 * @param taskItemId 	评价项目ID pj_aps_task_item.id
	 * @param teamId 		班级ID pj_team.id
	 * @param teacherId 	考核教师ID pj_teacher.id
	 * @param score 		所得分数
	 * @param checkDate 	考核日期
	 * @param remark 		备注信息
	 */
	void updateTeamEvaluationItemOfScore(Integer itemId, Float score);

	/**
	 * 批量设置年级每周可获取流动红旗的标准分
	 * @param evaluationMedalDatas
	 */
	void batchSetRedBannerWeeklyStandardScore(List<EvaluationMedalData> evaluationMedalDatas);
	
	/**
	 * 
	 * 评定当前班级是否可获取到流动红旗
	 * @param termCode 	学期code pj_school_term.code
	 * @param teamId 	班级ID pj_team.id
	 * @param startDate 	考核的开始时间
	 * @param finishDate 考核的结束时间
	 */
	boolean evaluateWeeklyWinnerOfRedBanner(String termCode, Integer teamId, Date startDate, Date finishDate);
	
	/**
	 * 
	 * 批量评定当前班级是否可获取到流动红旗
	 * @param termCode 	学期code pj_school_term.code
	 * @param teamIds 	班级ID pj_team.id
	 * @param startDate 考核的开始时间
	 * @param finishDate 考核的结束时间
	 */
	void batchEvaluateWeeklyWinnerOfRedBanner(String termCode, List<Integer> teamId, Date startDate, Date finishDate);

	/**
	 * 获取某个学校在某个学期下每周流动红旗的获奖者
	 * @param schoolId 	学校ID pj_school.id
	 * @param termCode 	学期Code pj_term.code
	 * @param startDate 	考核开始时间 一般为一周的开始时间
	 * @param finishDate 	考核结束时间 一般为一周的结束时间
	 * @return
	 */
	List<ApsMedalWinner> findWeeklyWinnerOfReadBanner(Integer schoolId, String termCode, Date startDate, Date finishDate);

	/**
	 *  获取班级评价的某种项目名单(已启用的)
	 * @param schoolId
	 * @param termCode
	 * @param checkType
	 * @return
	 */
	List<ApsTaskItem> findTeamTaskItems(String termCode, String checkType);
	
	/**
	 *  获取班级评价的某种项目名单（无论是否启用）
	 * @param schoolId
	 * @param termCode
	 * @param checkType
	 * @return
	 */
	List<ApsTaskItem> findAllTaskItemsOfType(String termCode, String checkType);
	
	/**
	 * 已启用项目 + 分数对应的项目
	 * @param termCode
	 * @param checkType
	 * @param checkDate
	 * @return
	 */
	List<ApsTaskItem> findUnionItem(String termCode, String checkType, Date checkDate);
	
	/**
	 * 获取班级评价的所有项目名单
	 * @param schoolId
	 * @param termCode
	 * @return
	 */
	List<ApsTaskItem> findTeamTaskItemsByTaskId(Integer taskId);
	
	/**
	 *  获取班级评价的某种项目名单
	 * @param schoolId
	 * @param termCode
	 * @param checkType
	 * @return
	 */
	List<ApsTaskItem> findTeamTaskItemsByTaskId(Integer taskId, String checkType);
	

	/**
	 * 查询班级某个时间段所有项目的总分
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	List<TeamSummaryData> summaryTeamEvaluationTaskForTeam(String termCode, Integer teamId, Date beginDate, Date endDate);
	/**
	 * 查询整个年级所有班级某个时间段所有项目的总分
	 * @param gradeId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<TeamSummaryData> summaryTeamEvaluationTaskForGrade(Integer gradeId, String termCode, Date startDate, Date finishDate);
	
	/**
	 * 查询所有年级某个时间段所有项目的总分
	 * @param schoolId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	List<TeamSummaryData> summaryTeamEvaluationTaskForSchool(Integer schoolId, String termCode, Date startDate, Date finishDate);
	
	/**
	 * 获取某个学校的评价
	 * @param schoolId 	学校ID pj_school.id
	 * @return
	 */
	List<ApsRuleItem> findRuleItems(Integer schoolId);
	
	/**
	 * 根据学校ID以及评价项目类型获取某个学校的评价项目列表
	 * @param schoolId 	学校ID pj_school.id
	 * @param checkType 评价项目类型 01：常规、02：加分、03：减分
	 * @return
	 */
	List<ApsRuleItem> findRuleItems(Integer schoolId, String checkType);
	
	/**
	 * 评定年级红旗
	 * @param termCode
	 * @param gradeID
	 * @param startDate
	 * @param finishDate
	 * @param priodCode
	 * @return
	 */
	List<TeamSummaryData> evaluateWeeklyGradeRedBanner(String termCode, Integer gradeID, Date startDate, Date finishDate,String priodCode);
	
	/**
	 * 查找红旗公示
	 * @param termCode
	 * @param gradeID
	 * @param priodCode
	 * @return
	 */
	List<RedBannerVo> findWeeklyGradeRedBanner(String termCode,Integer gradeID,String priodCode,Page page,Order order);
	
	/**
	 * 查找红旗公示(未评定的)
	 */
	List<RedBannerVo> findWeeklyGradeRedBanner(String termCode, Integer gradeId, Date beginDate, Date endDate);
	
	/**
	 * 查看班级当天的所有减分项目
	 * @param termCode
	 * @param teamId
	 * @param CheckDate
	 * @return
	 */
	List<TeamScoreData> getTeamMinusScore(String termCode, Integer teamId, Date checkDate);
	
	/**
	 * 查看班级当天的所有加分项目
	 * @param termCode
	 * @param teamId
	 * @param CheckDate
	 * @return
	 */
	List<TeamScoreData> getTeamAddScore(String termCode, Integer teamId, Date checkDate);
	
	/**
	 * 查询当前学校流动红旗的达标分数
	 * @param schoolId
	 * @return 
	 */
	List<RedBannerScore> findRedBannerScores(Integer schoolId,String schoolYear);

	/**
	 * 获取 某个评价项目的 完整信息（含备注、图片）
	 * @param taskItemId
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	TeamScoreData getTeamEvaScore(Integer taskItemId, Integer teamId,Date checkDate);
    /**
     * 修改紅旗評定的方式
     * @param bannerScores
     */
    void setRedBannerWeeklyStandardWay(List<RedBannerScore> bannerScores);
    
    /**
     * 查看加分具体情况
     * @param termCode
     * @param teamId
     * @param checkDate
     * @return
     */
    List<TeamEvaScoreData> getScoreOfAdd(String termCode, Integer teamId, Date checkDate);
    
    /**
     * 查看减分具体情况
     * @param termCode
     * @param teamId
     * @param checkDate
     * @return
     */
    List<TeamEvaScoreData> getScoreOfMinus(String termCode, Integer teamId, Date checkDate);
    
    
    ApsTask getTask(String termCode);
    //----------------------------值日管理----------------------------
    /**
     * 录入值日教师
     * @param termCode
     * @param gradeId
     * @param teacherId
     * @param userId
     * @param currentDate
     */
    void setJudgeTeacher(String termCode, Integer gradeId, Integer teacherId, Integer userId, Date onDutyDate, String week);
    
    /**
     * 批量录入值日教师
     * @param termCode
     * @param gradeId
     * @param currentDate
     * @param teacherList
     */
    void batchSetJudgeTeacher(String termCode, Integer gradeId, Date beginDate, Date endDate, List<JudgeTeacher> teacherList, String week);
    
    /**
     * 获取年级的值日教师
     * @param termCode
     * @param gradeId
     * @param currentDate
     * @return
     */
    List<ApsTaskJudge> findTaskJudge(String termCode, Integer gradeId, Date onDutyDate);
    
    /**
     * 获取值日教师的年级
     */
    List<ApsTaskJudge> findJudgeTeacher(String termCode, Integer teacherId, Date onDutyDate);
    
    /**
     * 值日教师 完成值日任务
     * @param termCode
     * @param gradeId
     * @param teacherId
     * @param onDutyDate
     */
    void finishedJudge(String termCode,Integer gradeId, Integer teacherId, Date onDutyDate);
    
    /**
     * 值日教师统计数据
     * @param schoolId
     * @param schoolYear
     * @param termCode
     * @param gradeId
     * @param beginDate
     * @param endDate
     * @return
     */
	List<DutyTeacherStatData> dutyTeacherStatistics(Integer schoolId, String schoolYear, String termCode, 
			Integer gradeId, Date beginDate, Date endDate, Page page, Order order);
	
	/**
	 * 获取指定年级的值日教师
	 * @param termCode
	 * @param teacherId
	 * @param onDutyDate
	 * @return
	 */
	List<ApsTaskJudge> findUniqueJudge(String termCode,Integer gradeId, Integer teacherId, Date beginDate, Date endDate);
	
	/**
	 * 班级的所有项目的分数
	 */
	float[][] findAllItemScoreForTeam(String termCode, String checkType, Date checkDate, List<Team> teamList);
}


