package platform.education.generalTeachingAffair.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.dao.ApsMedalDao;
import platform.education.generalTeachingAffair.dao.ApsMedalWinnerDao;
import platform.education.generalTeachingAffair.dao.ApsRuleDao;
import platform.education.generalTeachingAffair.dao.ApsRuleItemDao;
import platform.education.generalTeachingAffair.dao.ApsRuleMedalDao;
import platform.education.generalTeachingAffair.dao.ApsTaskDao;
import platform.education.generalTeachingAffair.dao.ApsTaskItemDao;
import platform.education.generalTeachingAffair.dao.ApsTaskJudgeDao;
import platform.education.generalTeachingAffair.dao.ApsTaskScoreDao;
import platform.education.generalTeachingAffair.dao.ApsTaskScoreFileDao;
import platform.education.generalTeachingAffair.dao.ApsTeamSummaryDao;
import platform.education.generalTeachingAffair.dao.SchoolTermDao;
import platform.education.generalTeachingAffair.model.ApsMedal;
import platform.education.generalTeachingAffair.model.ApsMedalWinner;
import platform.education.generalTeachingAffair.model.ApsRule;
import platform.education.generalTeachingAffair.model.ApsRuleItem;
import platform.education.generalTeachingAffair.model.ApsRuleMedal;
import platform.education.generalTeachingAffair.model.ApsTask;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskJudge;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.model.ApsTeamSummary;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.ApsTaskScoreFile;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.DutyTeacherStatData;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.JudgeTeacher;
import platform.education.generalTeachingAffair.vo.RedBannerScore;
import platform.education.generalTeachingAffair.vo.RedBannerVo;
import platform.education.generalTeachingAffair.vo.SchoolCondition;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.generalTeachingAffair.vo.TeamUserCondition;

public class TeamApsServiceImpl implements TeamApsService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ApsTaskDao apsTaskDao;

	private ApsTaskItemDao apsTaskItemDao;

	private ApsTaskScoreDao apsTaskScoreDao;

	private ApsTaskScoreFileDao apsTaskScoreFileDao;

	private ApsMedalDao apsMedalDao;

	private ApsMedalWinnerDao apsMedalWinnerDao;

	private ApsRuleDao apsRuleDao;

	private ApsRuleItemDao apsRuleItemDao;

	private ApsRuleMedalDao apsRuleMedalDao;

	private ApsTeamSummaryDao apsTeamSummaryDao;
	
	private SchoolTermDao schoolTermDao;

	private GradeService gradeService;

	private SchoolTermService schoolTermService;
	
	private TeamService teamService;
	private TeamUserService teamUserService;
	
	private ApsTaskJudgeDao apsTaskJudgeDao;
	
	public void setApsTaskJudgeDao(ApsTaskJudgeDao apsTaskJudgeDao) {
		this.apsTaskJudgeDao = apsTaskJudgeDao;
	}

	public TeacherService getTeacherService() {
		return teacherService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	private TeacherService teacherService;
	
	public void setApsTaskDao(ApsTaskDao apsTaskDao) {
		this.apsTaskDao = apsTaskDao;
	}

	public void setApsTaskItemDao(ApsTaskItemDao apsTaskItemDao) {
		this.apsTaskItemDao = apsTaskItemDao;
	}

	public void setApsTaskScoreDao(ApsTaskScoreDao apsTaskScoreDao) {
		this.apsTaskScoreDao = apsTaskScoreDao;
	}

	public void setApsTaskScoreFileDao(ApsTaskScoreFileDao apsTaskScoreFileDao) {
		this.apsTaskScoreFileDao = apsTaskScoreFileDao;
	}

	public void setApsMedalDao(ApsMedalDao apsMedalDao) {
		this.apsMedalDao = apsMedalDao;
	}

	public void setApsMedalWinnerDao(ApsMedalWinnerDao apsMedalWinnerDao) {
		this.apsMedalWinnerDao = apsMedalWinnerDao;
	}

	public void setApsRuleDao(ApsRuleDao apsRuleDao) {
		this.apsRuleDao = apsRuleDao;
	}

	public void setApsRuleItemDao(ApsRuleItemDao apsRuleItemDao) {
		this.apsRuleItemDao = apsRuleItemDao;
	}

	public void setApsRuleMedalDao(ApsRuleMedalDao apsRuleMedalDao) {
		this.apsRuleMedalDao = apsRuleMedalDao;
	}

	public void setSchoolTermDao(SchoolTermDao schoolTermDao) {
		this.schoolTermDao = schoolTermDao;
	}

	public void setApsTeamSummaryDao(ApsTeamSummaryDao apsTeamSummaryDao) {
		this.apsTeamSummaryDao = apsTeamSummaryDao;
	}

	public SchoolTermService getSchoolTermService() {
		return schoolTermService;
	}

	public void setSchoolTermService(SchoolTermService schoolTermService) {
		this.schoolTermService = schoolTermService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	/**
	 * 新建班级评价任务
	 */
	@Override
	public void AddTeamTask(Integer schoolId, String termCode) {
		
		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("新建班级评价任务失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("新建班级评价任务失败");
		}
		
		try{
			//通过学校id和学期号找到学期
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			if(schoolTerm == null){
				log.info("找不到对应的学期");
				throw new RuntimeException("新建班级评价任务失败");
			}
			//判断评价任务模板是否有该学校的模板
			ApsRule apsRule = apsRuleDao.findBySchoolId(schoolId,ApsTaskContants.OBJECT_TEAM);
			//没有数据，新建模板（复制内置模板），再根据新模板创建评价任务
			if(apsRule == null){
				//找到内置任务模板
				apsRule = apsRuleDao.findBySchoolId(ApsTaskContants.XUNYUN_SCHOOL_ID, ApsTaskContants.OBJECT_TEAM);
				//新增学校模板
				ApsRule rule = new ApsRule();
				rule.setSchoolId(schoolId);
				rule.setName(apsRule.getName());
				rule.setVersion(apsRule.getVersion());
				rule.setObjectType(ApsTaskContants.OBJECT_TEAM);
				rule.setDescription(apsRule.getDescription());
				rule.setIsDeleted(false);
				rule.setCreateDate(new Date());
				apsRuleDao.create(rule);
				//新建评价任务（复制模板）
				ApsTask task = new ApsTask();
				task.setRuleId(rule.getId());
				task.setSchoolId(schoolId);
				task.setObjectType(ApsTaskContants.OBJECT_TEAM);
				task.setName(rule.getName());
				task.setSchoolYear(schoolTerm.getSchoolYear());
				task.setTermCode(termCode);
				task.setStartDate(schoolTerm.getBeginDate());
				task.setFinishDate(schoolTerm.getFinishDate());
				task.setDescription(rule.getDescription());
				task.setIsDeleted(false);
				task.setCreateDate(new Date());
				apsTaskDao.create(task);
				
				//找到内置项目模板
				List<ApsRuleItem> ApsRuleItemList = apsRuleItemDao.findByRuleId(apsRule.getId());
				//新增评价项目模板
				for(ApsRuleItem ApsRuleItem : ApsRuleItemList){
					ApsRuleItem ruleItem = new ApsRuleItem();
					ruleItem.setRuleId(rule.getId());
					ruleItem.setCheckType(ApsRuleItem.getCheckType());
					ruleItem.setName(ApsRuleItem.getName());
					ruleItem.setCategory(ApsRuleItem.getCategory());
					ruleItem.setDescription(ApsRuleItem.getDescription());
					ruleItem.setListOrder(ApsRuleItem.getListOrder());
					ruleItem.setCode(ApsRuleItem.getCode());
					ruleItem.setScore(ApsRuleItem.getScore());
					ruleItem.setScale(ApsRuleItem.getScale());
					ruleItem.setIsDeleted(ApsRuleItem.getIsDeleted());
					ruleItem.setCreateDate(new Date());
					apsRuleItemDao.create(ruleItem);
				}
				//找到评价项目模板
				List<ApsRuleItem> ruleItemList = apsRuleItemDao.findByRuleId(rule.getId());
				//新增评价项目
				addItem(ruleItemList,task.getId());
				
			} else {
				//有数据，从该学校模板中复制数据并新建评价任务
				//判断输入的学期是够已有评价任务
				ApsTask apsTask = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_TEAM);
				if(apsTask != null){
					log.info("该学期已有班集体评价任务");
					throw new RuntimeException("不能重复添加评价任务");
				}
				//新建评价任务
				ApsTask task = new ApsTask();
				task.setRuleId(apsRule.getId());
				task.setSchoolId(schoolId);
				task.setObjectType(ApsTaskContants.OBJECT_TEAM);
				task.setName(apsRule.getName());
				task.setSchoolYear(schoolTerm.getSchoolYear());
				task.setTermCode(termCode);
				task.setStartDate(schoolTerm.getBeginDate());
				task.setFinishDate(schoolTerm.getFinishDate());
				task.setDescription(apsRule.getDescription());
				task.setIsDeleted(false);
				task.setCreateDate(new Date());
				apsTaskDao.create(task);
				
				//新建评价项目
				List<ApsRuleItem> ruleItemList = apsRuleItemDao.findByRuleId(apsRule.getId());
				addItem(ruleItemList,task.getId());
				
			}
			
			
			//新建班级评价奖项(流动红旗)
			//判断当前学校是否存在班级评价奖项
			List<ApsMedal> apsMedalList = apsMedalDao.findByschoolIdAndType(schoolId, ApsTaskContants.OBJECT_TEAM);
			//已有评价奖项，延用该奖项，不进行其他操作
			if(apsMedalList != null && apsMedalList.size()>0 ){
				return;
			}
			//没有奖项，从奖项模板表中复制数据到奖项表中
			List<ApsRuleMedal> apsRuleMedalList = apsRuleMedalDao.findByObjectType(ApsTaskContants.OBJECT_TEAM);
			for(ApsRuleMedal apsRuleMedal : apsRuleMedalList){
				ApsMedal medal = new ApsMedal();
				medal.setObjectType(ApsTaskContants.OBJECT_TEAM);
				medal.setSchoolId(schoolId);
				medal.setGradeCode(apsRuleMedal.getGradeCode());
				medal.setName(apsRuleMedal.getName());
				medal.setReachScore(apsRuleMedal.getReachScore());
				medal.setReachCount(apsRuleMedal.getReachCount());
				medal.setJudgeCriterion(apsRuleMedal.getJudgeCriterion());
				medal.setRunPeriod(apsRuleMedal.getRunPeriod());
				medal.setIsDeleted(apsRuleMedal.getIsDeleted());
				medal.setCreateDate(new Date());
				apsMedalDao.create(medal);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 复制班级评价项目
	 */
	public void addItem(List<ApsRuleItem> ruleItemList, Integer taskId) {
		for (ApsRuleItem ruleItem : ruleItemList) {
			ApsTaskItem taskItem = new ApsTaskItem();
			taskItem.setTaskId(taskId);
			taskItem.setRuleItemId(ruleItem.getId());
			taskItem.setName(ruleItem.getName());
			taskItem.setCategory(ruleItem.getCategory());
			taskItem.setCheckType(ruleItem.getCheckType());
			taskItem.setDescription(ruleItem.getDescription());
			taskItem.setListOrder(ruleItem.getListOrder());
			taskItem.setCode(ruleItem.getCode());
			taskItem.setScore(ruleItem.getScore());
			taskItem.setScale(ruleItem.getScale());
			taskItem.setEnable(true);
			taskItem.setIsDeleted(ruleItem.getIsDeleted());
			taskItem.setCreateDate(new Date());
			apsTaskItemDao.create(taskItem);
		}
	}
	
	/**
	 * 添加班级评价任务 自定义评价项目
	 */
	@Override
	public void addTeamEvaluationTask(Integer taskID, String name, String checkType, Float score) {

		if(taskID == null){
			log.error("评价任务Id不能为空");
			throw new IllegalArgumentException("新建班级评价项目失败");
		}
		if(name == null || "".equals(name)){
			log.error("项目名不能为空");
			throw new IllegalArgumentException("新建班级评价项目失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("新建班级评价项目失败");
		}
		if(score == null){
			log.error("得分不能为空");
			throw new IllegalArgumentException("新建班级评价项目失败");
		}
		
		try {
			// 通过taskID找到模版id
			//ApsTask apsTask = apsTaskDao.findById(taskID);
			
			// 判断对应模版是否有重名的项目
			//List<ApsRuleItem> apsRuleItems = apsRuleItemDao.findApsRuleItemByNameAndRuleID(apsTask.getRuleId(), name);
			List<ApsTaskItem> apsTaskItmes = apsTaskItemDao.findByItemName(taskID, name);
			if (apsTaskItmes != null && apsTaskItmes.size()>0) {
//				log.error("名字重复");
//				throw new IllegalArgumentException("新建班级评价项目失败");
				ApsTaskItem item = apsTaskItmes.get(0);
				if(item.getIsDeleted()){
					item.setIsDeleted(false);
					item.setEnable(true);
					item.setModifyDate(new Date());
					apsTaskItemDao.update(item);
				}
			} else if(apsTaskItmes == null || apsTaskItmes.size() == 0){
				// 模版添加选项
//				ApsRuleItem apsRuleItem = new ApsRuleItem();
//				apsRuleItem.setName(name);
//				apsRuleItem.setCreateDate(new Date());
//				apsRuleItem.setModifyDate(new Date());
//				apsRuleItem.setIsDeleted(false);
//				apsRuleItem.setCheckType(checkType);
//				apsRuleItem.setScore(score);
//				apsRuleItem.setCategory("");
//				apsRuleItem.setDescription("");
//				apsRuleItem.setCode("");
//				apsRuleItem.setScale((float) 0);
//				apsRuleItem.setListOrder(0);
//				apsRuleItem.setRuleId(apsTask.getRuleId());
//				apsRuleItemDao.create(apsRuleItem);
				// Task表添加选项
				ApsTaskItem apsTaskItem = new ApsTaskItem();
				apsTaskItem.setName(name);
				apsTaskItem.setCreateDate(new Date());
				apsTaskItem.setModifyDate(new Date());
				apsTaskItem.setIsDeleted(false);
				apsTaskItem.setCheckType(checkType);
				apsTaskItem.setScore(score);
				apsTaskItem.setCategory("");
				apsTaskItem.setDescription("");
				apsTaskItem.setCode("");
				apsTaskItem.setScale((float) 0);
				apsTaskItem.setListOrder(0);
				apsTaskItem.setTaskId(taskID);
//				apsTaskItem.setRuleItemId(apsRuleItem.getId());
				apsTaskItem.setRuleItemId(0);
				apsTaskItem.setEnable(true);

				apsTaskItemDao.create(apsTaskItem);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * 启用 评价项目
	 */
	@Override
	public void changeEnableOfItem(Integer taskItemId) {
		try{
			if (taskItemId == null) {
				throw new IllegalArgumentException("param 'id' is not null");
			}
			ApsTaskItem apsTaskItem = apsTaskItemDao.findById(taskItemId);
//			Boolean isDeleted = apsTaskItem.getIsDeleted();
//			if(isDeleted){
//				apsTaskItem.setIsDeleted(false);
//			}else{
//				apsTaskItem.setIsDeleted(true);
//			}
			Boolean enable = apsTaskItem.getEnable();
			if(enable){
				apsTaskItem.setEnable(false);
			}else{
				apsTaskItem.setEnable(true);
			}
			apsTaskItemDao.update(apsTaskItem);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除 评价项目(逻辑删除)
	 */
	@Override
	public void deleteTeamEvaItem(Integer taskItemId) {
		if (taskItemId == null) {
			throw new IllegalArgumentException("param 'id' is not null");
		}
		try {
			ApsTaskItem apsTaskItem = apsTaskItemDao.findById(taskItemId);
			Boolean isDeleted = apsTaskItem.getIsDeleted();
			if(isDeleted){
				apsTaskItem.setIsDeleted(false);
			}else{
				apsTaskItem.setIsDeleted(true);
			}
			apsTaskItemDao.update(apsTaskItem);
//			ApsTaskScore scoreVo = new ApsTaskScore();
//			scoreVo.setTaskItemId(taskItemId);
//			scoreVo.setObjectType(ApsTaskContants.OBJECT_TEAM);
//			List<ApsTaskScore> list = apsTaskScoreDao.findApsTaskScoreByCondition(scoreVo);
//			if(list != null && list.size() > 0){
//				for(ApsTaskScore score : list){
//					apsTaskScoreDao.delete(score);
//				}
//			}
//			apsTaskItemDao.delete(apsTaskItem);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改对应的评价项目名称
	 */
	@Override
	public void updateTeamEvaluationItemOfName(String name, Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("param 'id' is not null");
		}
		if(name == null || "".equals(name)){
			throw new IllegalArgumentException("param 'name' is not null");
		}
		ApsRuleItem apsRuleItem1 = apsRuleItemDao.findRuleItembyName(name);
		
		if(apsRuleItem1 != null){
			throw new IllegalArgumentException("名字重复");
		}
		try{
			ApsTaskItem apsTaskItem = apsTaskItemDao.findById(id);
//			ApsRuleItem apsRuleItem = apsRuleItemDao.findById(apsTaskItem.getRuleItemId());
			apsTaskItem.setName(name);
//			apsRuleItem.setName(name);
			apsTaskItemDao.update(apsTaskItem);
//			apsRuleItemDao.update(apsRuleItem);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改某个评价项目的分数
	 */
	@Override
	public void updateTeamEvaluationItemOfScore(Integer itemId, Float score) {
		if (itemId == null) {
			throw new IllegalArgumentException("param 'itemId' is not null");
		}
		if (score == null) {
			throw new IllegalArgumentException("param 'score' is not null");
		}
		try{
			ApsTaskItem apsTaskItem = apsTaskItemDao.findById(itemId);
//			ApsRuleItem apsRuleItem = apsRuleItemDao.findById(apsTaskItem.getRuleItemId());
			apsTaskItem.setScore(score);
//			apsRuleItem.setScore(score);
			apsTaskItemDao.update(apsTaskItem);
//			apsRuleItemDao.update(apsRuleItem);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置年级每周可获取流动红旗的标准分
	 */
	@Override
	public void setRedBannerWeeklyStandardScore(Integer gradeId, Float score,int count, String criterion) {
		if (gradeId == null) {
			throw new IllegalArgumentException( "param 'gradeId' is not null");
		}
		if (score == null) {
			throw new IllegalArgumentException( "param 'score' is not null");
		} 
		try{
			Grade grade = gradeService.findGradeById(gradeId);
			ApsMedal apsMedal = apsMedalDao.findMedals(grade.getSchoolId(),grade.getUniGradeCode(), 
					ApsTaskContants.REDBANNER,ApsTaskContants.OBJECT_TEAM, ApsTaskContants.RUN_WEEK);
			apsMedal.setReachScore(score);
			apsMedal.setReachCount(count);
			if(criterion != null && !"".equals(criterion)){
				apsMedal.setJudgeCriterion(criterion);
			}
			apsMedalDao.update(apsMedal);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 批量设置年级每周可获取流动红旗的标准分
	 */
	@Override
	public void batchSetRedBannerWeeklyStandardScore(List<EvaluationMedalData> evaluationMedalDatas) {
		try{
			if (evaluationMedalDatas == null || evaluationMedalDatas.size() <= 0) {
				throw new IllegalArgumentException( "param 'List<EvaluationMedalData> evaluationMedalDatas' is not null");
			} 
			
				
				for (EvaluationMedalData evaluationMedalData : evaluationMedalDatas) {
					setRedBannerWeeklyStandardScore(evaluationMedalData.getGradeId(), evaluationMedalData.getScore(),evaluationMedalData.getCount(), null);
				}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 评定当前班级是否可获取到流动红旗
	 */
	@Override
	public boolean evaluateWeeklyWinnerOfRedBanner(String termCode, Integer teamId, Date startDate, Date finishDate) {
		// 是否获得流动红旗
		boolean isRedBanner = false;
		ApsMedalWinner apsMedalWinner = new ApsMedalWinner();
		// 获取班级在这个学期的时间段扣分总分
		float a = getTeamMinusScoreOfTime(termCode, teamId, startDate,finishDate);
		// 获取班级在这个学期的时间段加分总分
		float b = getTeamAddScoreOfTime(termCode, teamId, startDate, finishDate);
		// 总分
		float score = 100 + b + a;
		SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
		schoolTermCondition.setCode(termCode);
		Team team = teamService.findTeamById(teamId);
		// 找到评分标准
		List<SchoolTerm> schoolTerms = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
		if (schoolTerms != null && schoolTerms.size() > 0) {
			GradeCondition gradeCondition = new GradeCondition();
			List<Grade> grades = gradeService.findGradeByCondition(gradeCondition, null, null);
			if (grades != null && grades.size() > 0) {
				ApsMedal apsMedal = apsMedalDao.findMedals(grades.get(0).getSchoolId(), grades.get(0).getUniGradeCode(),
						ApsTaskContants.REDBANNER, ApsTaskContants.OBJECT_TEAM, ApsTaskContants.RUN_WEEK);
				// 与评分标准做判断
				if (apsMedal.getReachScore() <= score) {

					apsMedalWinner.setCreateDate(new Date());
					apsMedalWinner.setFinishDate(finishDate);
					apsMedalWinner.setGradeId(grades.get(0).getId());
					apsMedalWinner.setIsDeleted(false);
					apsMedalWinner.setMedalId(apsMedal.getId());
					apsMedalWinner.setModifyDate(new Date());
					apsMedalWinner.setName(team.getName());
					apsMedalWinner.setObjectId(teamId);
					apsMedalWinner.setObjectType(ApsTaskContants.OBJECT_TEAM);
					apsMedalWinner.setPeriodCode(ApsTaskContants.REDBANNER);
					apsMedalWinner.setSchoolId(grades.get(0).getSchoolId());
					apsMedalWinner.setSchoolYear(grades.get(0).getSchoolYear());
					apsMedalWinner.setTermCode(termCode);
					apsMedalWinner = apsMedalWinnerDao.create(apsMedalWinner);

				}
			}
		}
		return isRedBanner;
	}

	/**
	 * 批量评定当前班级是否可获取到流动红旗
	 */
	@Override
	public void batchEvaluateWeeklyWinnerOfRedBanner(String termCode, List<Integer> teamId, Date startDate, Date finishDate) {
		if (teamId != null) {
			for (Integer id : teamId) {
				evaluateWeeklyWinnerOfRedBanner(termCode, id, startDate,
						finishDate);
			}
		}
	}
	
	/**
	 * 设置某个班级针对某个评价项目所得的分数
	 */
	@Override
	public void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, Integer teacherId, Float score, Date checkDate){
		try{
			setTeamEvaluationTaskScore(taskItemId, teamId,parentObjectId,teacherId,score,checkDate, "");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 获取某个班级针对某个评价项目
	 */
	@Override
	public ApsTaskScore getTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Date checkDate) {
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		if(teamId == null){
			log.error("班级Id不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		
		try{
			ApsTaskScore taskScore = apsTaskScoreDao.findUnique(taskItemId, teamId, checkDate);
			return taskScore;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 获取 某个评价项目的 完整信息（含备注、图片）
	 */
	@Override
	public TeamScoreData getTeamEvaScore(Integer taskItemId, Integer teamId, Date checkDate){
		
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		if(teamId == null){
			log.error("班级Id不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		
		TeamScoreData data = null;
		try{
			ApsTaskScore taskScore = apsTaskScoreDao.findUnique(taskItemId, teamId, checkDate);
			if(taskScore != null){
				
				List<String> list = null;
				List<ApsTaskScoreFile> files = apsTaskScoreFileDao.findByTaskScoreId(taskScore.getId());
				if(files != null && files.size()>0){
					list = new ArrayList<String>();
					for(ApsTaskScoreFile file : files){
						list.add(file.getFileId());
					}
				}
				
				data = new TeamScoreData();
				data.setTeamId(teamId);
				data.setItemId(taskItemId);
				data.setItemName(apsTaskItemDao.findById(taskItemId).getName());
				data.setScore(taskScore.getScore());
				data.setRemark(taskScore.getRemark());
				data.setFileUUIDs(list);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 修改某个班级针对某个评价项目的分数
	 */
	@Override
	public void modifyTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Float score, Date checkDate) {
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("修改评价项目失败");
		}
		if(teamId == null){
			log.error("班级Id不能为空");
			throw new IllegalArgumentException("修改评价项目失败");
		}
		if(score == null){
			log.error("得分不能为空");
			throw new IllegalArgumentException("修改评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("修改评价项目失败");
		}
		try{
//			ApsTaskScore taskScore = apsTaskScoreDao.findUnique(taskItemId, teamId,checkDate);
			ApsTaskScore taskScore = getTeamEvaluationTaskScore(taskItemId, teamId, checkDate);
			taskScore.setScore(score);
			apsTaskScoreDao.update(taskScore);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, 
			Integer teacherId, Float score, Date checkDate, String remark) {
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(teamId == null){
			log.error("班级Id不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(score == null){
			log.error("得分不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(parentObjectId == null){
			log.error("年级(上级)Id不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		
		try{
			ApsTaskItem taskItem = apsTaskItemDao.findById(taskItemId);
			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(taskItem.getTaskId());
			taskScore.setTaskItemId(taskItemId);
			taskScore.setJudgeId(teacherId);
			taskScore.setObjectType(ApsTaskContants.OBJECT_TEAM);
			taskScore.setObjectId(teamId);
			taskScore.setParentObjectId(parentObjectId);
			taskScore.setCheckType(taskItem.getCheckType());
			taskScore.setScore(score);
			taskScore.setCheckRange("");
			taskScore.setCheckDate(checkDate);
			taskScore.setRemark(remark);
			taskScore.setIsDeleted(false);
			taskScore.setCreateDate(new Date());
			apsTaskScoreDao.create(taskScore);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, 
			Integer teacherId, Float score, Date checkDate, List<String> fileUUIDs) {
		try{
			setTeamEvaluationTaskScore(taskItemId, teamId, parentObjectId,teacherId, score, checkDate, "", fileUUIDs);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setTeamEvaluationTaskScore(Integer taskItemId, Integer teamId, Integer parentObjectId, 
			Integer teacherId, Float score, Date checkDate, String remark, List<String> fileUUIDs) {
		
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(teamId == null){
			log.error("班级Id不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(score == null){
			log.error("得分不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(parentObjectId == null){
			log.error("年级(上级)Id不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("新建评价项目失败");
		}
		
		try{
			ApsTaskItem taskItem = apsTaskItemDao.findById(taskItemId);
			ApsTaskScore taskScore = apsTaskScoreDao.findUnique(taskItemId, teamId, checkDate);
			
			if(taskScore == null && score != 0){
				taskScore = new ApsTaskScore();
				taskScore.setTaskId(taskItem.getTaskId());
				taskScore.setTaskItemId(taskItemId);
				taskScore.setJudgeId(teacherId);
				taskScore.setObjectType(ApsTaskContants.OBJECT_TEAM);
				taskScore.setObjectId(teamId);
				taskScore.setParentObjectId(parentObjectId);
				taskScore.setCheckType(taskItem.getCheckType());
				taskScore.setScore(score);
				taskScore.setCheckRange("");
				taskScore.setCheckDate(checkDate);
				taskScore.setRemark(remark);
				taskScore.setIsDeleted(false);
				taskScore.setCreateDate(new Date());
				apsTaskScoreDao.create(taskScore);
			}else{
				if(score != 0 && (!score.toString().equals(taskScore.getScore().toString()) || !remark.equals(taskScore.getRemark()))){
					taskScore.setScore(score);
					taskScore.setRemark(remark);
					taskScore.setJudgeId(teacherId);
					taskScore.setModifyDate(new Date());
					apsTaskScoreDao.update(taskScore);
				}else if(score == 0 || score == null){
					apsTaskScoreDao.delete(taskScore);
				}
			}
			
			List<ApsTaskScoreFile> list = apsTaskScoreFileDao.findByTaskScoreId(taskScore.getId());
			for(ApsTaskScoreFile file : list){
				apsTaskScoreFileDao.delete(file);
			}
			if(score != 0 && fileUUIDs != null && fileUUIDs.size() > 0){
				for (String fileId : fileUUIDs) {
					ApsTaskScoreFile taskScoreFile = new ApsTaskScoreFile();
					taskScoreFile.setTaskScoreId(taskScore.getId());
					taskScoreFile.setFileId(fileId);
					taskScoreFile.setCreateDate(new Date());
					apsTaskScoreFileDao.create(taskScoreFile);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * 批量设置整个年级的一次评分
	 */
	@Override
	public void batchSetTeamEvaluationTaskScore(Integer teacherId, Integer parentObjectId, 
			Date checkDate, List<TeamScoreData> teamScoreDatas) {
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("批量设置得分失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("批量设置得分失败");
		}
		if(parentObjectId == null){
			log.error("年级(上级)Id不能为空");
			throw new IllegalArgumentException("批量设置得分失败");
		}
		
		try{
			if(teamScoreDatas != null && teamScoreDatas.size() > 0){
				for (TeamScoreData teamScoreDate : teamScoreDatas) {
					Integer itemId = teamScoreDate.getItemId();
					Integer teamId = teamScoreDate.getTeamId();
					Float score = teamScoreDate.getScore();
					
					ApsTaskScore taskScore = getTeamEvaluationTaskScore(itemId, teamId, checkDate);
					if(taskScore != null){
						if(score != 0 && !score.toString().equals(taskScore.getScore().toString())){
							taskScore.setScore(score);
							taskScore.setJudgeId(teacherId);
							taskScore.setModifyDate(new Date());
							apsTaskScoreDao.update(taskScore);
						}else if(score == 0 || score == null){
							apsTaskScoreDao.delete(taskScore);
						} 
					}else{
						if(score != 0){
							setTeamEvaluationTaskScore(itemId, teamId, parentObjectId,teacherId, score, checkDate);
						}
					}
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询整个年级的一次评分结果（扣分项）
	 */
	@Override
	public List<TeamScoreData> findTeamMinusScoresOfGrade(Integer schoolId,
			String termCode, Integer gradeId, Date checkDate) {

		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		if(termCode == null || "".endsWith(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		
		try{
			List<TeamScoreData> teamScoreDataList = new ArrayList<TeamScoreData>();
			// 找到对应的评价任务
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			// 找到所有的年级列表
			List<Team> teamList = teamService.findTeamOfGradeAndSchool(gradeId, schoolId);
			
			if(teamList !=null && teamList.size() > 0){
				List<ApsTaskScore> taskScoreList = null;
				for (Team team : teamList) {
					Integer teamId = team.getId();
					// 找到每个班级所有项目
					taskScoreList = apsTaskScoreDao.findScoresOfCheckType(task.getId(), teamId, checkDate, ApsTaskContants.CHECK_TYPE_MINUS);
					if(taskScoreList != null && taskScoreList.size() > 0){
						for(ApsTaskScore apsTaskScore : taskScoreList){
							TeamScoreData teamScoreData  = new TeamScoreData();
							teamScoreData.setTeamId(teamId);
							teamScoreData.setItemId(apsTaskScore.getTaskItemId());
							ApsTaskItem taskItem = apsTaskItemDao.findById(apsTaskScore.getTaskItemId());
							teamScoreData.setItemName(taskItem.getName());
							teamScoreData.setScore(apsTaskScore.getScore());
							teamScoreDataList.add(teamScoreData);
						}		
					}
				}
			} else{
				log.error("找不到对应的班级列表");
				throw new IllegalArgumentException("查询年级得分失败");
			}
			return teamScoreDataList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询整个年级的一次评分结果（加分项）
	 */
	@Override
	public List<TeamScoreData> findTeamAddScoresOfGrade(Integer schoolId,
			String termCode, Integer gradeId, Date checkDate) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		if(termCode == null || "".endsWith(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查询年级得分失败");
		}
		try{
			List<TeamScoreData> teamScoreDataList = new ArrayList<TeamScoreData>();
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			List<Team> teamList = teamService.findTeamOfGradeAndSchool(gradeId, schoolId);
					
			if(teamList !=null && teamList.size() > 0){
				List<ApsTaskScore> taskScoreList = null;
				for (Team team : teamList) {
					Integer teamId = team.getId();
					taskScoreList = apsTaskScoreDao.findScoresOfCheckType(task.getId(), teamId, checkDate, ApsTaskContants.CHECK_TYPE_ADD);
					if(taskScoreList != null && taskScoreList.size() > 0){
						for(ApsTaskScore apsTaskScore : taskScoreList){
							TeamScoreData teamScoreData  = new TeamScoreData();
							teamScoreData.setTeamId(teamId);
							teamScoreData.setItemId(apsTaskScore.getTaskItemId());
							ApsTaskItem taskItem = apsTaskItemDao.findById(apsTaskScore.getTaskItemId());
							teamScoreData.setItemName(taskItem.getName());
							teamScoreData.setScore(apsTaskScore.getScore());
							teamScoreDataList.add(teamScoreData);
						}		
					}
				}
			}
			return teamScoreDataList;
			
		}catch(Exception e){
			
		}
		return null;
	}
	
	/**
	 * 查看班级当天的所有减分项目
	 */
	@Override
	public List<TeamScoreData> getTeamMinusScore(String termCode,Integer teamId, Date checkDate) {
		
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(teamId == null){
			log.error("班级id不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamScoreData> teamScoreDataList = new ArrayList<TeamScoreData>();
			//找到对应的评价任务
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfCheckType(task.getId(), teamId, checkDate, ApsTaskContants.CHECK_TYPE_MINUS);
			if(taskScoreList != null && taskScoreList.size() > 0){
				for(ApsTaskScore apsTaskScore : taskScoreList){
					ApsTaskItem taskItem = apsTaskItemDao.findById(apsTaskScore.getTaskItemId());
					if(taskItem != null){
						TeamScoreData teamScoreData  = new TeamScoreData();
						teamScoreData.setTeamId(teamId);
						teamScoreData.setItemId(apsTaskScore.getTaskItemId());
						teamScoreData.setItemName(taskItem.getName());
						teamScoreData.setScore(apsTaskScore.getScore());
						teamScoreData.setRemark(apsTaskScore.getRemark());
						List<ApsTaskScoreFile> fileList = apsTaskScoreFileDao.findByTaskScoreId(apsTaskScore.getId());
						List<String> list = new ArrayList<String>();
						if(fileList != null && fileList.size() > 0){
							for(ApsTaskScoreFile file : fileList){
								list.add(file.getFileId());
							}
						}
						teamScoreData.setFileUUIDs(list);
						teamScoreData.setTeacherId(apsTaskScore.getJudgeId());
						Teacher teacher = teacherService.findTeacherById(apsTaskScore.getJudgeId());
						teamScoreData.setTeacherName(teacher != null ? teacher.getName() : null);
						teamScoreData.setJudgeDate(sdf.format(apsTaskScore.getModifyDate()));
						teamScoreDataList.add(teamScoreData);
					}
				}		
			}
			return teamScoreDataList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查看班级当天的所有加分项目
	 */
	@Override
	public List<TeamScoreData> getTeamAddScore(String termCode, Integer teamId,Date checkDate) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(teamId == null){
			log.error("班级id不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamScoreData> teamScoreDataList = new ArrayList<TeamScoreData>();
			//找到对应的评价任务
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfCheckType(task.getId(), teamId, checkDate, ApsTaskContants.CHECK_TYPE_ADD);
			if(taskScoreList != null && taskScoreList.size() > 0){
				for(ApsTaskScore apsTaskScore : taskScoreList){
					ApsTaskItem taskItem = apsTaskItemDao.findById(apsTaskScore.getTaskItemId());
					if(taskItem != null){
						TeamScoreData teamScoreData  = new TeamScoreData();
						teamScoreData.setTeamId(teamId);
						teamScoreData.setItemId(apsTaskScore.getTaskItemId());
						teamScoreData.setItemName(taskItem.getName());
						teamScoreData.setScore(apsTaskScore.getScore());
						teamScoreData.setRemark(apsTaskScore.getRemark());
						List<ApsTaskScoreFile> fileList = apsTaskScoreFileDao.findByTaskScoreId(apsTaskScore.getId());
						List<String> list = new ArrayList<String>();
						if(fileList != null && fileList.size() > 0){
							for(ApsTaskScoreFile file : fileList){
								list.add(file.getFileId());
							}
						}
						teamScoreData.setFileUUIDs(list);
						teamScoreData.setTeacherId(apsTaskScore.getJudgeId());
						Teacher teacher = teacherService.findTeacherById(apsTaskScore.getJudgeId());
						teamScoreData.setTeacherName(teacher != null ? teacher.getName() : null);
						teamScoreData.setJudgeDate(sdf.format(apsTaskScore.getModifyDate()));
						teamScoreDataList.add(teamScoreData);
					}	
				}
			}
			return teamScoreDataList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//获取班级评价的所有项目名单
	@Override
	public List<ApsTaskItem> findTeamTaskItems(String termCode) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			ApsTask task = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskItem> taskItemsList = null;
			if(task != null){
				taskItemsList = apsTaskItemDao.findAllItems(task.getId());
			}
			return taskItemsList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//已启用的项目
	@Override
	public List<ApsTaskItem> findTeamTaskItems(String termCode, String checkType) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			ApsTask task = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskItem> taskItemsList = null;
			if(task != null){
				taskItemsList = apsTaskItemDao.findEnableItems(task.getId(), checkType);
			}
			return taskItemsList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//未删除的项目名单
	@Override
	public List<ApsTaskItem> findAllTaskItemsOfType(String termCode,String checkType) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			ApsTask task = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskItem> taskItemsList = apsTaskItemDao.findOneTypeItems(task.getId(), checkType);
			return taskItemsList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<ApsTaskItem> findUnionItem(String termCode, String checkType, Date checkDate){
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		List<ApsTaskItem> taskItemsList = null;
		try{
			ApsTask task = getTask(termCode);
			taskItemsList = apsTaskItemDao.findUnionItem(task.getId(), checkType, checkDate, checkDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return taskItemsList;
	}
		
	
	//获取班级评价的所有项目名单
	@Override
	public List<ApsTaskItem> findTeamTaskItemsByTaskId(Integer taskId) {
		if(taskId == null){
			log.error("评价任务id不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			List<ApsTaskItem> taskItemsList = apsTaskItemDao.findAllItems(taskId);
			return taskItemsList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	// 获取班级评价的某种项目名单
	@Override
	public List<ApsTaskItem> findTeamTaskItemsByTaskId(Integer taskId,String checkType) {
		if(taskId == null){
			log.error("评价任务id不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			List<ApsTaskItem> taskItemsList = apsTaskItemDao.findOneTypeItems(taskId, checkType);
			return taskItemsList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public float addFloat(float a, float b){
		 BigDecimal b1 = new BigDecimal(String.valueOf(a));
		 BigDecimal b2 = new BigDecimal(String.valueOf(b));
		 BigDecimal b3 = b1.add(b2);
		 float f = b3.floatValue();
		 return f;
	}
	
	
	/**
	 * 查询班级某个时间段所有项目的总分
	 */
	@Override
	public List<TeamSummaryData> summaryTeamEvaluationTaskForTeam(String termCode,Integer teamId, Date beginDate, Date endDate) {
		
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查询班级汇总失败");
		}
		if(teamId == null){
			log.error("班级id不能为空");
			throw new IllegalArgumentException("查询班级汇总失败");
		}
		if(beginDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查询班级汇总失败");
		}
		try{
			List<TeamSummaryData> teamSummaryDataList = new ArrayList<TeamSummaryData>();
			ApsTask task = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_TEAM);
			
			//获取所有减分项目
			//List<ApsTaskItem> deductItems = findTeamTaskItemsByTaskId(task.getId(), ApsTaskContants.CHECK_TYPE_MINUS);
			List<ApsTaskItem> deductItems = apsTaskItemDao.findUnionItem(task.getId(), ApsTaskContants.CHECK_TYPE_MINUS, beginDate, endDate);
			List<TeamSummaryData> deductDatas = new ArrayList<TeamSummaryData>();
			float dScores = 0;
			for(ApsTaskItem item : deductItems){
				//获取每个项目的所有记录
				List<ApsTaskScore> taskScores = apsTaskScoreDao.findItemsOfTime(item.getId(), teamId, beginDate, endDate);
				if(taskScores != null && taskScores.size()>0){
					//获取每个项目的总分
					float deductScore = 0;
					for(ApsTaskScore taskScore : taskScores){
						//deductScore += taskScore.getScore();
						deductScore = addFloat(deductScore,taskScore.getScore());
					}
					//将每个项目存入
					TeamSummaryData data = new TeamSummaryData();
					data.setObjectId(item.getId());
					data.setObjectName(item.getName());
					data.setTotalScore(deductScore);
					data.setDeductScore(deductScore);
					data.setAddScore((float)0);
					deductDatas.add(data);
					//dScores += deductScore;
					dScores = addFloat(dScores, deductScore);
				}
			}
			//给每条记录添加百分比
			for(TeamSummaryData data : deductDatas){
				data.setDeductRatio(data.getDeductScore()/dScores);
				data.setAddRatio((float)0);
			}
			teamSummaryDataList.addAll(deductDatas);
			
			//获取所有加分项目
			//List<ApsTaskItem> addItems = findTeamTaskItemsByTaskId(task.getId(), ApsTaskContants.CHECK_TYPE_ADD);
			List<ApsTaskItem> addItems = apsTaskItemDao.findUnionItem(task.getId(), ApsTaskContants.CHECK_TYPE_ADD, beginDate, endDate);
			List<TeamSummaryData> addDatas = new ArrayList<TeamSummaryData>();
			float aScores = 0;
			for(ApsTaskItem item : addItems){
				//获取每个项目的所有记录
				List<ApsTaskScore> taskScores = apsTaskScoreDao.findItemsOfTime(item.getId(), teamId, beginDate, endDate);
				if(taskScores != null && taskScores.size()>0){
					//获取每个项目的总分
					float addScore = 0;
					for(ApsTaskScore taskScore : taskScores){
						//addScore += taskScore.getScore();
						addScore = addFloat(addScore, taskScore.getScore());
					}
					//将每个项目存入
					TeamSummaryData data = new TeamSummaryData();
					data.setObjectId(item.getId());
					data.setObjectName(item.getName());
					data.setTotalScore(addScore);
					data.setDeductScore((float)0);
					data.setAddScore(addScore);
					addDatas.add(data);
					//aScores += addScore;
					aScores = addFloat(aScores, addScore);
				}
			}
			//给每条记录添加百分比
			for(TeamSummaryData data : addDatas){
				data.setAddRatio(data.getAddScore()/aScores);
				data.setDeductRatio((float)0);
			}
			teamSummaryDataList.addAll(addDatas);
			
			return teamSummaryDataList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询整个年级所有班级某个时间段所有项目的总分
	 */
	@Override
	public List<TeamSummaryData> summaryTeamEvaluationTaskForGrade(Integer gradeId, String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查询年级汇总失败");
		}
		if(gradeId == null){
			log.error("年级id不能为空");
			throw new IllegalArgumentException("查询年级汇总失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查询年级汇总失败");
		}
		try{
			List<TeamSummaryData> teamSummaryDataList = new ArrayList<TeamSummaryData>();
			ApsTask task = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_TEAM);
			
			List<Team> teamList = teamService.findTeamOfGrade(gradeId);
			List<ApsTaskScore> taskScoreList = null;
			
			float dTScore = 0;
			float aTScore = 0;
			for(Team team : teamList){
				//找到班级在某一时间段的所有的减分项目
				taskScoreList = apsTaskScoreDao.findScoresOfTime(task.getId(), team.getId(), ApsTaskContants.CHECK_TYPE_MINUS, startDate, finishDate);
				//遍历得到减分总和,无数据则为0
				float deductScore = 0;
				if(taskScoreList != null && taskScoreList.size()>0){
					for(ApsTaskScore taskScore : taskScoreList){
						//deductScore += taskScore.getScore();
						deductScore = addFloat(deductScore, taskScore.getScore());
					}
				}
				
				//找到所有加分项
				taskScoreList = apsTaskScoreDao.findScoresOfTime(task.getId(), team.getId(), ApsTaskContants.CHECK_TYPE_ADD, startDate, finishDate);
				//遍历得到加分总和
				float addScore = 0;
				if(taskScoreList != null && taskScoreList.size()>0){
					for(ApsTaskScore taskScore : taskScoreList){
						//addScore += taskScore.getScore();
						addScore = addFloat(addScore, taskScore.getScore());
					}
				}
				
				//将每个班级的得分情况存入汇总对象中
				TeamSummaryData teamSummaryData = new TeamSummaryData();
				teamSummaryData.setObjectId(team.getId());
				teamSummaryData.setObjectName(team.getName());
				teamSummaryData.setTotalScore(addFloat(deductScore, addScore));
				teamSummaryData.setAddScore(addScore);
				teamSummaryData.setDeductScore(deductScore);
				teamSummaryData.setSequence(team.getTeamNumber());
				teamSummaryDataList.add(teamSummaryData);
				
				//dTScore += teamSummaryData.getDeductScore();
				//aTScore += teamSummaryData.getAddScore();
				dTScore = addFloat(dTScore, teamSummaryData.getDeductScore());
				aTScore = addFloat(aTScore, teamSummaryData.getAddScore());
			}
			
			//对数据进行排序、计算百分比 再存回集合中
			sortAndRatio(teamSummaryDataList, dTScore, aTScore);

			return teamSummaryDataList;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询所有年级某个时间段所有项目的总分
	 */
	@Override
	public List<TeamSummaryData> summaryTeamEvaluationTaskForSchool(Integer schoolId, String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查询汇总失败");
		}
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("查询汇总失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查询汇总失败");
		}
		try{
			List<TeamSummaryData> teamSummaryDataList = new ArrayList<TeamSummaryData>();
			//找到学校的年级列表
			//List<Grade> gradeList = gradeService.findGrageListBySchoolId(schoolId);
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId,schoolTerm.getSchoolYear());
	
			float dTScore = 0;
			float aTScore = 0;
			for(Grade grade : gradeList){
				//调用接口，得到每个年级的汇总数据
				List<TeamSummaryData> gradeDate = summaryTeamEvaluationTaskForGrade(grade.getId(), termCode, startDate, finishDate);
				float totalScore = 0;
				float addScore = 0;
				float deductScore = 0;
				if(gradeDate != null && gradeDate.size()>0){
					for(TeamSummaryData teamDate : gradeDate){
//						totalScore += teamDate.getTotalScore();
//						addScore += teamDate.getAddScore();
//						deductScore += teamDate.getDeductScore();
						totalScore = addFloat(totalScore, teamDate.getTotalScore());
						addScore = addFloat(addScore, teamDate.getAddScore());
						deductScore = addFloat(deductScore, teamDate.getDeductScore());
					}	
				}
				TeamSummaryData teamSummaryData = new TeamSummaryData();
				teamSummaryData.setObjectId(grade.getId());
				teamSummaryData.setObjectName(grade.getName());
				teamSummaryData.setTotalScore(totalScore);
				teamSummaryData.setAddScore(addScore);
				teamSummaryData.setDeductScore(deductScore);
				teamSummaryData.setSequence(Integer.parseInt(grade.getUniGradeCode()));
				teamSummaryDataList.add(teamSummaryData);
				
//				dTScore += teamSummaryData.getDeductScore();
//				aTScore += teamSummaryData.getAddScore();
				dTScore = addFloat(dTScore, teamSummaryData.getDeductScore());
				aTScore = addFloat(aTScore, teamSummaryData.getAddScore());
			}
			sortAndRatio(teamSummaryDataList, dTScore, aTScore);
			
			return teamSummaryDataList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 对集合进行排序并添加百分比
	 */
	public void sortAndRatio(List<TeamSummaryData> DataList, float dTScore, float aTScore){
		//按总分大小降序排列
//		Collections.sort(DataList, new Comparator<TeamSummaryData>(){  
//			public int compare(TeamSummaryData d1, TeamSummaryData d2) {  
//				 if(d1.getTotalScore()<d2.getTotalScore()){  
//	                    return 1;  
//	                }  
//	                if(d1.getTotalScore()==d2.getTotalScore()){  
//	                    return 0;  
//	                }  
//	                return -1;  
//			}
//		});
		//先按总分（降序），后按班级/年级（升序）
		Collections.sort(DataList);
		int rank = 1;
		for(int i=0;i<DataList.size();i++){
			TeamSummaryData data = DataList.get(i);
			if(i==0){
				data.setRank(1);
			}else{
				if(data.getTotalScore()<DataList.get(i-1).getTotalScore()){
					rank = i+1;
					data.setRank(i+1);
				}else{
					data.setRank(rank);
				}
			}
			
			if(dTScore == 0){
				data.setDeductRatio((float)0);
			}else{
				data.setDeductRatio(Math.abs(data.getDeductScore()/dTScore));
			}
			if(aTScore == 0){
				data.setAddRatio((float)0);
			}else{
				data.setAddRatio(data.getAddScore()/aTScore);
			}
		}
		//按项目的属性（teamNumber/gradeNumber)升序排列
//		Collections.sort(DataList, new Comparator<TeamSummaryData>(){  
//			public int compare(TeamSummaryData d1, TeamSummaryData d2) {  
//				 if(d1.getSequence()>d2.getSequence()){  
//	                    return 1;  
//	                }  
//	                if(d1.getSequence()==d2.getSequence()){  
//	                    return 0;  
//	                }  
//	                return -1;  
//			}
//		});
	}

	/**
	 * 获取某个学校的评价
	 */
	@Override
	public List<ApsRuleItem> findRuleItems(Integer schoolId) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			ApsRule apsRule = apsRuleDao.findBySchoolId(schoolId,ApsTaskContants.OBJECT_TEAM);
			List<ApsRuleItem> ruleItemList = apsRuleItemDao.findByRuleId(apsRule.getId());
			return ruleItemList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据学校ID以及评价项目类型获取某个学校的评价项目列表
	 */
	@Override
	public List<ApsRuleItem> findRuleItems(Integer schoolId, String checkType) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取项目名单失败");
		}
		try{
			ApsRule apsRule = apsRuleDao.findBySchoolId(schoolId,ApsTaskContants.OBJECT_TEAM);
			List<ApsRuleItem> ruleItemList = apsRuleItemDao.findItemsBycheckType(apsRule.getId(), checkType);
			return ruleItemList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;

	}


	/**
	 * 获取某个学校在某个学期下每周流动红旗的获奖者
	 */
	@Override
	public List<ApsMedalWinner> findWeeklyWinnerOfReadBanner(Integer schoolId,
			String termCode, Date startDate, Date finishDate) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取流动红旗名单失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取流动红旗名单失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取流动红旗名单失败");
		}
		try{
			List<ApsMedalWinner> apsMedalWinners = new ArrayList<ApsMedalWinner>();
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			List<SchoolTerm> schoolTerms = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
			if (schoolTerms != null && schoolTerms.size() > 0) {
				GradeCondition gradeCondition = new GradeCondition();
				List<Grade> grades = gradeService.findGradeByCondition(gradeCondition, null, null);
				if (grades != null && grades.size() > 0) {
					
					ApsMedal apsMedal = apsMedalDao.findMedals(grades.get(0).getSchoolId(), grades.get(0).getUniGradeCode(),
							ApsTaskContants.REDBANNER, ApsTaskContants.OBJECT_TEAM,ApsTaskContants.RUN_WEEK);
					apsMedalWinners = apsMedalWinnerDao.findApsMedalWinners(apsMedal.getId(), 
							schoolId, termCode, startDate,finishDate);
				}
			}
			
			return apsMedalWinners;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查询班级某个时间段所有项目的总分（扣分项）
	 */
	public Float getTeamMinusScoreOfTime(String termCode, Integer teamId,Date beginDate, Date endDate) {
		ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
		float Scores = 0;
		List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfTime(task.getId(), 
				teamId, ApsTaskContants.CHECK_TYPE_MINUS,beginDate, endDate);
		for (ApsTaskScore taskScore : taskScoreList) {
			if (taskScore.getScore() != null) {
				//Scores += taskScore.getScore();
				Scores = addFloat(Scores, taskScore.getScore());
			}
		}
		return Scores;
	}

	/**
	 * 查询班级某个时间段所有项目的总分（加分项）
	 */
	public Float getTeamAddScoreOfTime(String termCode, Integer teamId,Date beginDate, Date endDate) {
		ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
		float Scores = 0;
		List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfTime(task.getId(), 
				teamId, ApsTaskContants.CHECK_TYPE_ADD,beginDate, endDate);
		for (ApsTaskScore taskScore : taskScoreList) {
			if (taskScore.getScore() != null) {
				//Scores += taskScore.getScore();
				Scores = addFloat(Scores, taskScore.getScore());
			}
		}
		return Scores;
	}
	
	/**
	 * 评定年级红旗
	 */
	@Override
	public List<TeamSummaryData> evaluateWeeklyGradeRedBanner(String termCode,
			Integer gradeID, Date startDate, Date finishDate, String priodCode) {
		if(gradeID == null){
			log.error("年级id不能为空");
			throw new IllegalArgumentException("评定年级红旗失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("评定年级红旗失败");
		}
		if(priodCode == null || "".equals(priodCode)){
			log.error("评价阶段不能为空");
			throw new IllegalArgumentException("评定年级红旗失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("评定年级红旗失败");
		}
		try{
			
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			Grade grade = gradeService.findGradeById(gradeID);
			
			ApsMedal apsMedal = apsMedalDao.findMedals(grade.getSchoolId(),grade.getUniGradeCode(), ApsTaskContants.REDBANNER, ApsTaskContants.OBJECT_TEAM, ApsTaskContants.RUN_WEEK);
			List<TeamSummaryData> teamSummaryDatas = summaryTeamEvaluationTaskForGrade(gradeID, termCode, startDate, finishDate);
			List<ApsTeamSummary> old = apsTeamSummaryDao.findApsTeamSummaryByRedBanner(task.getId(),termCode, priodCode,apsMedal.getId(),null,null);
			List<ApsMedalWinner> apsMedalWinners = apsMedalWinnerDao.findApsMedalWinners(apsMedal.getId(), grade.getSchoolId(), termCode, startDate, finishDate);
			if(old != null && old.size()>0){
				for(ApsTeamSummary apsTeamSummary:old){
					apsTeamSummaryDao.delete(apsTeamSummary);
				}
			}
			if(apsMedalWinners != null && apsMedalWinners.size()>0){
				for(ApsMedalWinner apsMedalWinner:apsMedalWinners){
					apsMedalWinner.setIsDeleted(true);
					apsMedalWinnerDao.update(apsMedalWinner);
				}
			}
			
			for(TeamSummaryData teamSummaryData:teamSummaryDatas ){
				ApsTeamSummary apsTeamSummary=new ApsTeamSummary();
				apsTeamSummary.setAddScore(teamSummaryData.getAddScore());
				apsTeamSummary.setCreateDate(new Date());
				apsTeamSummary.setDeductScore(teamSummaryData.getDeductScore());
				apsTeamSummary.setIsDeleted(false);
				apsTeamSummary.setMedalId(apsMedal.getId());
				apsTeamSummary.setModifyDate(new Date());
				apsTeamSummary.setNormalScore((float)0);
				apsTeamSummary.setPeriodCode(priodCode);
				apsTeamSummary.setRank(teamSummaryData.getRank());
				apsTeamSummary.setSchoolId(grade.getSchoolId());
				apsTeamSummary.setTaskId(task.getId());
				apsTeamSummary.setTeamId(teamSummaryData.getObjectId());
				apsTeamSummary.setTermCode(termCode);
				apsTeamSummary.setRemark("");
				apsTeamSummaryDao.create(apsTeamSummary);
				if(apsMedal.getJudgeCriterion().equals("2")){
					
					if(apsMedal.getReachCount()>=teamSummaryData.getRank()){
						ApsMedalWinner apsMedalWinner=new ApsMedalWinner();
						apsMedalWinner.setCreateDate(new Date());
						apsMedalWinner.setFinishDate(finishDate);
						apsMedalWinner.setGradeId(gradeID);
						apsMedalWinner.setIsDeleted(false);
						apsMedalWinner.setMedalId(apsMedal.getId());
						apsMedalWinner.setModifyDate(new Date());
						apsMedalWinner.setName(ApsTaskContants.REDBANNER);
						apsMedalWinner.setObjectId(teamSummaryData.getObjectId());
						apsMedalWinner.setObjectType(ApsTaskContants.OBJECT_TEAM);
						apsMedalWinner.setPeriodCode(priodCode);
						apsMedalWinner.setSchoolId(grade.getSchoolId());
						apsMedalWinner.setSchoolYear(grade.getSchoolYear());
						apsMedalWinner.setStartDate(startDate);
						apsMedalWinner.setTermCode(termCode);
						apsMedalWinnerDao.create(apsMedalWinner);
					}
				}else{
				if(apsMedal.getReachScore() <= addFloat(teamSummaryData.getTotalScore(),100)){
					ApsMedalWinner apsMedalWinner=new ApsMedalWinner();
					apsMedalWinner.setCreateDate(new Date());
					apsMedalWinner.setFinishDate(finishDate);
					apsMedalWinner.setGradeId(gradeID);
					apsMedalWinner.setIsDeleted(false);
					apsMedalWinner.setMedalId(apsMedal.getId());
					apsMedalWinner.setModifyDate(new Date());
					apsMedalWinner.setName(ApsTaskContants.REDBANNER);
					apsMedalWinner.setObjectId(teamSummaryData.getObjectId());
					apsMedalWinner.setObjectType(ApsTaskContants.OBJECT_TEAM);
					apsMedalWinner.setPeriodCode(priodCode);
					apsMedalWinner.setSchoolId(grade.getSchoolId());
					apsMedalWinner.setSchoolYear(grade.getSchoolYear());
					apsMedalWinner.setStartDate(startDate);
					apsMedalWinner.setTermCode(termCode);
					
					apsMedalWinnerDao.create(apsMedalWinner);
				  }
				}
				
			}
			return teamSummaryDatas;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取红旗公示
	 */
	@Override
	public List<RedBannerVo> findWeeklyGradeRedBanner(String
	termCode, Integer gradeID, String priodCode, Page page, Order order) {
		List<RedBannerVo> redBannerVos = new ArrayList<RedBannerVo>();
		try {
			if (gradeID == null) {
				log.info("年级id不能为空");
				throw new Exception("获取红旗公示失败");
			}
			if (termCode == null || "".equals(termCode)) {
				log.info("学期代码不能为空");
				throw new Exception("获取红旗公示失败");
			}
			if (priodCode == null || "".equals(priodCode)) {
				log.info("评价阶段不能为空");
				throw new Exception("获取红旗公示失败");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ApsTask task = apsTaskDao.findUniqueTask(termCode,
					ApsTaskContants.OBJECT_TEAM);
			Grade grade = gradeService.findGradeById(gradeID);

			ApsMedal apsMedal = apsMedalDao.findMedals(grade.getSchoolId(),
					grade.getUniGradeCode(), ApsTaskContants.REDBANNER,
					ApsTaskContants.OBJECT_TEAM, ApsTaskContants.RUN_WEEK);
			List<ApsTeamSummary> apsTeamSummaries = apsTeamSummaryDao
					.findApsTeamSummaryByRedBanner(task.getId(), termCode,
							priodCode, apsMedal.getId(), page, order);
			if (apsTeamSummaries != null) {
				for (ApsTeamSummary apsTeamSummary : apsTeamSummaries) {
					RedBannerVo vo = new RedBannerVo();
					//float total = 100 + apsTeamSummary.getAddScore()+ apsTeamSummary.getDeductScore();
					float total = addFloat(100, addFloat(apsTeamSummary.getAddScore(), apsTeamSummary.getDeductScore()));
					vo.setAddScore(apsTeamSummary.getAddScore());
					String flag = apsMedal.getJudgeCriterion();
					if (flag.equals("1")) {
						if (total >= apsMedal.getReachScore()) {
							vo.setIsRed(1);
						} else {
							vo.setIsRed(0);
						}
					} else {
						if (apsTeamSummary.getRank() <= apsMedal
								.getReachCount()) {
							vo.setIsRed(1);
						} else {
							vo.setIsRed(0);
						}
					}
					vo.setRank(apsTeamSummary.getRank());
					vo.setReduceScore(apsTeamSummary.getDeductScore());
					vo.setTeamId(apsTeamSummary.getTeamId());
					Team team = this.teamService.findTeamById(apsTeamSummary
							.getTeamId());
					vo.setTeamName(team.getName());
					vo.setTeamId(team.getId());

					List<Teacher> teachers = this.teacherService
							.getMastersOfTeam(team.getId());
					if (teachers != null && teachers.size() > 0) {
						vo.setTeamTeacherName(teachers.get(0).getName());
					}
					vo.setTotalScore(total);
					vo.setRemark("");
					
					vo.setDate(sdf.format(apsTeamSummary.getModifyDate()));
					vo.setIsEvaluate(1);
					
					redBannerVos.add(vo);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return redBannerVos;
	}
	
	@Override
	public List<RedBannerVo> findWeeklyGradeRedBanner(String termCode, Integer gradeId, Date beginDate, Date endDate){
		List<RedBannerVo> redBannerVoList = new ArrayList<RedBannerVo>();
		
		List<TeamSummaryData> teamSummaryDatas = summaryTeamEvaluationTaskForGrade(gradeId, termCode, beginDate, endDate);
		for (TeamSummaryData teamSummaryData : teamSummaryDatas) {
			RedBannerVo vo = new RedBannerVo();
			vo.setAddScore(teamSummaryData.getAddScore());
			vo.setIsRed(0);
			vo.setRank(teamSummaryData.getRank());
			vo.setReduceScore(teamSummaryData.getDeductScore());
			vo.setTeamId(teamSummaryData.getObjectId());
			Team team = this.teamService.findTeamById(teamSummaryData.getObjectId());
			List<Teacher> teachers = this.teacherService.getMastersOfTeam(team.getId());
			vo.setTeamName(team.getName());
			vo.setTeamId(team.getId());
			if (teachers != null && teachers.size() > 0) {
				vo.setTeamTeacherName(teachers.get(0).getName());
			}
			vo.setRemark("");
			vo.setTotalScore(addFloat(teamSummaryData.getTotalScore(), 100));
			redBannerVoList.add(vo);
		}
		return redBannerVoList;
	}
	

	public TeamUserService getTeamUserService() {
		return teamUserService;
	}

	public void setTeamUserService(TeamUserService teamUserService) {
		this.teamUserService = teamUserService;
	}

	public TeamService getTeamService() {
		return teamService;
	}

	public ApsTeamSummaryDao getApsTeamSummaryDao() {
		return apsTeamSummaryDao;
	}

	@Override
	public List<RedBannerScore> findRedBannerScores(Integer schoolId,String schoolYear) {
		GradeCondition gradeCondition=new GradeCondition();
		gradeCondition.setSchoolYear(schoolYear);
		gradeCondition.setSchoolId(schoolId);
		List<Grade> grades=this.gradeService.findGradeByCondition(gradeCondition, null, null);
		List<RedBannerScore> bannerScores=new ArrayList<RedBannerScore>();
		if(grades!=null&&grades.size()>0){
			for(Grade grade:grades){
			ApsMedal apsMedal=new ApsMedal();
			RedBannerScore bannerScore=new RedBannerScore();
			apsMedal=this.apsMedalDao.findMedals(grade.getSchoolId(), grade.getUniGradeCode(), ApsTaskContants.REDBANNER, ApsTaskContants.OBJECT_TEAM,ApsTaskContants.RUN_WEEK);
			bannerScore.setGradeName(grade.getName());
			bannerScore.setGradeId(grade.getId());
			bannerScore.setScore(apsMedal.getReachScore());
			bannerScore.setReachCount(apsMedal.getReachCount());
			bannerScore.setGetWay(apsMedal.getJudgeCriterion());
			bannerScores.add(bannerScore);
			}
		}
		return bannerScores;
	}


	@Override
	public void setRedBannerWeeklyStandardWay(List<RedBannerScore> bannerScores) {
		
		if(bannerScores==null){
			throw new IllegalArgumentException( "param 'list' is not null");
		}
		try{
		for(RedBannerScore r:bannerScores){
			Grade grade=new Grade();
			grade=this.gradeService.findGradeById(r.getGradeId());
			ApsMedal apsMedal = apsMedalDao.findMedals(grade.getSchoolId(),grade.getUniGradeCode(), 
					ApsTaskContants.REDBANNER,ApsTaskContants.OBJECT_TEAM, ApsTaskContants.RUN_WEEK);
			apsMedal.setJudgeCriterion(r.getGetWay());
			apsMedalDao.update(apsMedal);
			
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public List<TeamEvaScoreData> getScoreOfAdd(String termCode,
			Integer teamId, Date checkDate) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(teamId == null){
			log.error("班级id不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		
		List<TeamEvaScoreData> dataList = null;
		try{
			dataList = new ArrayList<TeamEvaScoreData>();
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfCheckType(task.getId(), teamId, checkDate, ApsTaskContants.CHECK_TYPE_ADD);
			if(taskScoreList != null && taskScoreList.size() > 0){
				for(ApsTaskScore score : taskScoreList){
					TeamEvaScoreData data = new TeamEvaScoreData();
					data.setDate(new SimpleDateFormat("EEEE").format(checkDate.getTime()));
					data.setItemId(score.getTaskItemId());
					data.setItemName(apsTaskItemDao.findById(score.getTaskItemId()).getName());
					data.setScore(score.getScore());
					data.setTeacherId(score.getJudgeId());
					data.setTeacherName(teacherService.findTeacherById(score.getJudgeId()).getName());
					dataList.add(data);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public List<TeamEvaScoreData> getScoreOfMinus(String termCode,
			Integer teamId, Date checkDate) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(teamId == null){
			log.error("班级id不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("查看项目失败");
		}
		
		List<TeamEvaScoreData> dataList = null;
		try{
			dataList = new ArrayList<TeamEvaScoreData>();
			ApsTask task = apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
			List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfCheckType(task.getId(), teamId, checkDate, ApsTaskContants.CHECK_TYPE_MINUS);
			if(taskScoreList != null && taskScoreList.size() > 0){
				for(ApsTaskScore score : taskScoreList){
					TeamEvaScoreData data = new TeamEvaScoreData();
					data.setDate(new SimpleDateFormat("EEEE").format(checkDate.getTime()));
					data.setItemId(score.getTaskItemId());
					data.setItemName(apsTaskItemDao.findById(score.getTaskItemId()).getName());
					data.setScore(score.getScore());
					data.setTeacherId(score.getJudgeId());
					data.setTeacherName(teacherService.findTeacherById(score.getJudgeId()).getName());
					dataList.add(data);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return dataList;
	}

	@Override
	public ApsTask getTask(String termCode){
		return apsTaskDao.findUniqueTask(termCode,ApsTaskContants.OBJECT_TEAM);
	}
	
	
	//========================值日管理============================
	@Override
	public void setJudgeTeacher(String termCode, Integer gradeId,
			Integer teacherId, Integer userId, Date onDutyDate, String week) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(gradeId == null){
			log.error("年级id不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(teacherId == null){
			log.error("教师id不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(userId == null){
			log.error("用户id不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(onDutyDate == null){
			log.error("值日日期不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		try{
			String[] dayArr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
			ApsTask task = getTask(termCode);
			//ApsTaskJudge taskJudge = apsTaskJudgeDao.findUnique(task.getId(), gradeId, teacherId, currentDate);
			ApsTaskJudge taskJudge = new ApsTaskJudge();
			taskJudge.setTaskId(task.getId());
			taskJudge.setTermCode(termCode);
			taskJudge.setGradeId(gradeId);
			taskJudge.setTeacherId(teacherId);
			taskJudge.setUserId(userId);
			taskJudge.setOnDutyDate(onDutyDate);
			taskJudge.setDayOfWeek(dayArr[onDutyDate.getDay()]);
			taskJudge.setWeek(week);
			taskJudge.setIsFinished(false);
			taskJudge.setIsDeleted(false);
			taskJudge.setCreateDate(new Date());
			taskJudge.setModifyDate(new Date());
			apsTaskJudgeDao.create(taskJudge);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void batchSetJudgeTeacher(String termCode, Integer gradeId,
			Date beginDate, Date endDate, List<JudgeTeacher> teacherList, String week) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(gradeId == null){
			log.error("年级id不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(beginDate == null){
			log.error("值日日期不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		if(endDate == null){
			log.error("值日日期不能为空");
			throw new IllegalArgumentException("值日教师录入失败");
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ApsTask task = getTask(termCode);
			List<ApsTaskJudge> judgeList = apsTaskJudgeDao.findByGrade(task.getId(), gradeId, beginDate, endDate);
			//需保存的值日教师数据，逐个查找
			if(teacherList != null && teacherList.size() > 0){
				for(JudgeTeacher teacher : teacherList){
					List<ApsTaskJudge> list = apsTaskJudgeDao.findUnique(task.getId(), gradeId, 
							teacher.getTeacherId(), sdf.parse(teacher.getOnDutyDate()), sdf.parse(teacher.getOnDutyDate()));
					if(list != null && list.size() > 0){//表中有数据，不做处理，剔除删除集合
						ApsTaskJudge judge = list.get(0);
						for(ApsTaskJudge tj : judgeList){
							if(judge.getId().equals(tj.getId())){
								judgeList.remove(tj);
								break;
							}
						}
					}else{			//表中无数据，添加
						setJudgeTeacher(termCode, gradeId, teacher.getTeacherId(), teacher.getUserId(), sdf.parse(teacher.getOnDutyDate()), week);
					}
				}
			}
			//删除多余的
			for(ApsTaskJudge tj : judgeList){
				apsTaskJudgeDao.delete(tj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<ApsTaskJudge> findTaskJudge(String termCode,
			Integer gradeId, Date onDutyDate) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(gradeId == null){
			log.error("年级id不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(onDutyDate == null){
			log.error("值日日期不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		List<ApsTaskJudge> list = null;
		try{
			ApsTask task = getTask(termCode);
			list = apsTaskJudgeDao.findByGrade(task.getId(), gradeId, onDutyDate, onDutyDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ApsTaskJudge> findJudgeTeacher(String termCode,
			Integer teacherId, Date onDutyDate) {
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(teacherId == null){
			log.error("教师id不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(onDutyDate == null){
			log.error("值日日期不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		List<ApsTaskJudge> list = null;
		try{
			ApsTask task = getTask(termCode);
			list = apsTaskJudgeDao.findByTeacher(task.getId(), teacherId, onDutyDate, onDutyDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void finishedJudge(String termCode, Integer gradeId,
			Integer teacherId, Date onDutyDate) {
		try{
//			ApsTask task = getTask(termCode);
//			List<ApsTaskJudge> judgeList = apsTaskJudgeDao.findUnique(task.getId(), gradeId, teacherId, onDutyDate, onDutyDate);
			List<ApsTaskJudge> judgeList = findUniqueJudge(termCode, gradeId, teacherId, onDutyDate, onDutyDate);
			if(judgeList != null && judgeList.size() > 0){
				ApsTaskJudge judge = judgeList.get(0);
				judge.setIsFinished(true);
				judge.setModifyDate(new Date());
				apsTaskJudgeDao.update(judge);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public List<DutyTeacherStatData> dutyTeacherStatistics(Integer schoolId,
			String schoolYear, String termCode, Integer gradeId,
			Date beginDate, Date endDate, Page page, Order order) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取值日教师数据失败");
		}
		if(schoolYear == null || ("").equals(schoolYear)){
			log.error("学年不能为空");
			throw new IllegalArgumentException("获取值日教师数据失败");
		}
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取值日教师数据失败");
		}
		if(beginDate == null){
			log.error("日期不能为空");
			throw new IllegalArgumentException("获取值日教师数据失败");
		}
		if(endDate == null){
			log.error("日期不能为空");
			throw new IllegalArgumentException("获取值日教师数据失败");
		}
		List<DutyTeacherStatData> list = null;
		try {
			list = apsTaskJudgeDao.findTeachersForStat(schoolId, schoolYear, termCode, gradeId, beginDate, endDate, page, order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ApsTaskJudge> findUniqueJudge(String termCode, Integer gradeId,
			Integer teacherId, Date beginDate, Date endDate) {
		if(gradeId == null){
			log.error("年级id不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(teacherId == null){
			log.error("教师id不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(termCode == null || ("").equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(beginDate == null){
			log.error("日期不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		if(endDate == null){
			log.error("日期不能为空");
			throw new IllegalArgumentException("获取值日教师失败");
		}
		List<ApsTaskJudge> list = null;
		try {
			ApsTask task = getTask(termCode);
			list= apsTaskJudgeDao.findUnique(task.getId(), gradeId, teacherId, beginDate, endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public float[][] findAllItemScoreForTeam(String termCode, String checkType,
			Date checkDate, List<Team> teamList) {
		
		float [][] list = null;
		ApsTask task = getTask(termCode);
		
		if(task != null){
			List<ApsTaskItem> itemList = apsTaskItemDao.findUnionItem(task.getId(), checkType, checkDate, checkDate);
			List<TeamEvaScoreData> scoreList= null;
			if(teamList != null && teamList.size() > 0){
				list = new float[teamList.size()][itemList.size()];
				
				for(int i=0; i<teamList.size(); i++){
					scoreList = apsTaskScoreDao.findAllItemScoreForTeam(task.getId(), checkType, teamList.get(i).getId(), checkDate, checkDate);
					
					for(int j=0; j<scoreList.size(); j++){
						list[i][j] = scoreList.get(j).getScore();
					}
					
				}
				
			}
		}
		
		return list;
	}

}
