package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.dao.*;
import platform.education.generalTeachingAffair.model.ApsMedal;
import platform.education.generalTeachingAffair.model.ApsMedalWinner;
import platform.education.generalTeachingAffair.model.ApsRule;
import platform.education.generalTeachingAffair.model.ApsRuleItem;
import platform.education.generalTeachingAffair.model.ApsRuleMedal;
import platform.education.generalTeachingAffair.model.ApsStuSummary;
import platform.education.generalTeachingAffair.model.ApsTask;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.StudentApsService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.*;

public class StudentApsServiceImpl implements StudentApsService {

private Logger log = LoggerFactory.getLogger(getClass());

	private ApsTaskDao apsTaskDao;

	private ApsTaskItemDao apsTaskItemDao;

	private ApsTaskScoreDao apsTaskScoreDao;

	private ApsMedalDao apsMedalDao;

	private ApsMedalWinnerDao apsMedalWinnerDao;

	private ApsRuleDao apsRuleDao;

	private ApsRuleItemDao apsRuleItemDao;

	private ApsRuleMedalDao apsRuleMedalDao;

	private ApsStuSummaryDao apsStuSummaryDao;

	private SchoolTermDao schoolTermDao;

	private StudentService studentService;

	private TeacherService teacherService;

	private GradeService gradeService;

	private TeamService teamService;

	private TeamStudentService teamStudentService;

	private StudentApsDao studentApsDao;

	public void setStudentApsDao(StudentApsDao studentApsDao) {
		this.studentApsDao = studentApsDao;
	}

	public void setApsTaskDao(ApsTaskDao apsTaskDao) {
		this.apsTaskDao = apsTaskDao;
	}

	public void setApsTaskItemDao(ApsTaskItemDao apsTaskItemDao) {
		this.apsTaskItemDao = apsTaskItemDao;
	}

	public void setApsTaskScoreDao(ApsTaskScoreDao apsTaskScoreDao) {
		this.apsTaskScoreDao = apsTaskScoreDao;
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

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setTeamService(TeamService teamService) {
		this.teamService = teamService;
	}

	public void setTeamStudentService(TeamStudentService teamStudentService) {
		this.teamStudentService = teamStudentService;
	}

	public void setApsStuSummaryDao(ApsStuSummaryDao apsStuSummaryDao) {
		this.apsStuSummaryDao = apsStuSummaryDao;
	}

	/**
	 * 添加学生评价任务
	 */
	@Override
	public Boolean addStudentEvaluationTask(Integer schoolId, String termCode) {

		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("新建学生评价任务失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("新建学生评价任务失败");
		}

		Boolean flag = false;
		try{
			//通过学校id和学期号找到学期
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			if(schoolTerm == null){
				log.info("找不到对应的学期");
				throw new Exception("新建学生评价任务失败");
			}

			//判断评价任务模板是否有该学校的模板
			ApsRule apsRule = apsRuleDao.findBySchoolId(schoolId,ApsTaskContants.OBJECT_STUDENT);

			//没有数据，复制内置模板
			if(apsRule == null){
				//找到内置任务模板
				apsRule = apsRuleDao.findBySchoolId(ApsTaskContants.XUNYUN_SCHOOL_ID, ApsTaskContants.OBJECT_STUDENT);
				//新增学校模板
				ApsRule rule = new ApsRule();
				rule.setSchoolId(schoolId);
				rule.setName(apsRule.getName());
				rule.setVersion(apsRule.getVersion());
				rule.setObjectType(ApsTaskContants.OBJECT_STUDENT);
				rule.setDescription(apsRule.getDescription());
				rule.setIsDeleted(false);
				rule.setCreateDate(new Date());
				apsRuleDao.create(rule);
				//新建评价任务
				ApsTask task = addTask(schoolId,termCode, rule,schoolTerm);

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
				addItems(ruleItemList,task.getId());

			}else{
				//有数据，直接复制模板数据
				//判断输入的学期是够已有评价任务
				ApsTask apsTask = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_STUDENT);
				if(apsTask != null){
					log.info("该学期已有学生评价任务");
					throw new RuntimeException("不能重复添加评价任务");
				}
				//新建评价任务
				ApsTask task = addTask(schoolId, termCode, apsRule, schoolTerm);

				//新建评价项目
				List<ApsRuleItem> ruleItemList = apsRuleItemDao.findByRuleId(apsRule.getId());
				addItems(ruleItemList,task.getId());
			}

			//新建学生评价奖项（星级个人）
			//判断当前学校是否存在学生评价奖项
			List<ApsMedal> apsMedalList = apsMedalDao.findByschoolIdAndType(schoolId, ApsTaskContants.OBJECT_STUDENT);
			//已有评价奖项，延用该奖项，不进行其他操作
			if(apsMedalList != null && apsMedalList.size()>0 ){
				flag = true;
				return flag;
			}
			//没有奖项，从奖项模板表中复制数据到奖项表中
			List<ApsRuleMedal> apsRuleMedalList = apsRuleMedalDao.findByObjectType(ApsTaskContants.OBJECT_STUDENT);
			for(ApsRuleMedal apsRuleMedal : apsRuleMedalList){
				ApsMedal medal = new ApsMedal();
				medal.setObjectType(ApsTaskContants.OBJECT_STUDENT);
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
			flag = true;
			return flag;

		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 复制学生评级任务
	 */
	public ApsTask addTask(Integer schoolId, String termCode, ApsRule rule, SchoolTerm schoolTerm){
		ApsTask task = new ApsTask();
		task.setRuleId(rule.getId());
		task.setSchoolId(schoolId);
		task.setObjectType(ApsTaskContants.OBJECT_STUDENT);
		task.setName(rule.getName());
		task.setSchoolYear(schoolTerm.getSchoolYear());
		task.setTermCode(termCode);
		task.setStartDate(schoolTerm.getBeginDate());
		task.setFinishDate(schoolTerm.getFinishDate());
		task.setDescription(rule.getDescription());
		task.setIsDeleted(false);
		task.setCreateDate(new Date());
		apsTaskDao.create(task);
		return task;
	}

	/**
	 * 复制学生评价项目
	 */
	public void addItems(List<ApsRuleItem> ruleItemList, Integer taskId) {
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
			taskItem.setIsDeleted(ruleItem.getIsDeleted());
			taskItem.setCreateDate(new Date());
			apsTaskItemDao.create(taskItem);
		}
	}

	/**
	 * 根据学期代码找到学生评价任务
	 */
	@Override
	public ApsTask getApsTask(String termCode){
		ApsTask apsTask = apsTaskDao.findUniqueTask(termCode, ApsTaskContants.OBJECT_STUDENT);
		return apsTask;
	}

	/**
	 * 获取某个学校下某个学期的所有学生评价项目
	 */
	@Override
	public List<ApsTaskItem> findTaskItems(String termCode) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取学生评价项目失败");
		}

		List<ApsTaskItem> taskItemList = null;
		try {
			ApsTask task = getApsTask(termCode);
			taskItemList = apsTaskItemDao.findAllItems(task.getId());
			return taskItemList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskItemList;
	}

	/**
	 * 根据评价项目类型获取某个学校下某个学期的学生评价项目
	 */
	@Override
	public List<ApsTaskItem> findTaskItemsByCheckType(String termCode, String checkType) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取学生评价项目失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取学生评价项目失败");
		}

		List<ApsTaskItem> apsTaskItemList = null;
		try {
			ApsTask task = getApsTask(termCode);
			apsTaskItemList = apsTaskItemDao.findOneTypeItems(task.getId(), checkType);
			return apsTaskItemList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apsTaskItemList;
	}

	/**
	 * 获取某个学校某个学期下的减分评价项目，如课堂优化项目
	 */
	@Override
	public List<ApsTaskItem> findDeductMarksItems(String termCode) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化项目失败");
		}
		try {
			return findTaskItemsByCheckType(termCode, ApsTaskContants.CHECK_TYPE_MINUS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某个学校某个学期下的加分评价项目，如激励评价项目
	 */
	@Override
	public List<ApsTaskItem> findAwardedMarksItems(String termCode) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取激励评价项目失败");
		}
		try {
			return findTaskItemsByCheckType(termCode, ApsTaskContants.CHECK_TYPE_ADD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某个学校某个学期下的常规评价项目，如发展评价项目
	 */
	@Override
	public List<ApsTaskItem> findNormalItems(String termCode) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取发展评价项目失败");
		}
		try {
			return findTaskItemsByCheckType(termCode, ApsTaskContants.CHECK_TYPE_NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某个学校某个学期下 某一类型的 激励评价项目
	 */
	@Override
	public List<ApsTaskItem> findAwardedItemsOfCategory(String termCode,String category) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取学生评价项目失败");
		}
		if(category == null || "".equals(category)){
			log.error("项目分类不能为空");
			throw new IllegalArgumentException("获取学生评价项目失败");
		}

		List<ApsTaskItem> apsTaskItemList = null;
		try {
			ApsTask task = getApsTask(termCode);
			apsTaskItemList = apsTaskItemDao.findItemsOfCategory(task.getId(), ApsTaskContants.CHECK_TYPE_ADD, category);
			return apsTaskItemList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return apsTaskItemList;
	}


	/**
	 * 获取某个学生评价任务的评价项目
	 */
	@Override
	public List<ApsTaskItem> findTaskItems(Integer taskId) {
		if(taskId == null){
			log.error("评级任务Id不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		try {
			return apsTaskItemDao.findAllItems(taskId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某个学生评价任务的某种评价项目类型的评价项目
	 */
	@Override
	public List<ApsTaskItem> findTaskItemsByCheckType(Integer taskId, String checkType) {
		if(taskId == null){
			log.error("评级任务Id不能为空");
			throw new IllegalArgumentException("获取评价项目失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取学生评价项目失败");
		}
		try {
			return apsTaskItemDao.findOneTypeItems(taskId, checkType);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取某个学生评价任务的减分项目，如课堂优化项目
	 */
	@Override
	public List<ApsTaskItem> findDeductMarksItems(Integer taskId) {
		return findTaskItemsByCheckType(taskId, ApsTaskContants.CHECK_TYPE_MINUS);
	}

	/**
	 * 获取某个学生评价任务的加分项目，如激励评价项目
	 */
	@Override
	public List<ApsTaskItem> findAwardedMarksItems(Integer taskId) {
		return findTaskItemsByCheckType(taskId, ApsTaskContants.CHECK_TYPE_ADD);
	}

	/**
	 * 获取某个学生评价任务的常规项目，如发展卡评价项目
	 */
	@Override
	public List<ApsTaskItem> findNormalItems(Integer taskId) {
		return findTaskItemsByCheckType(taskId, ApsTaskContants.CHECK_TYPE_NORMAL);
	}

	/**
	 * 获取某个学校的学生评价任务对应的评价项目模板数据
	 */
	@Override
	public List<ApsRuleItem> findRuleItems(Integer schoolId) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取评价项目模板数据失败");
		}

		List<ApsRuleItem> apsRuleItemList = null;
		try {
			ApsRule apsRule = apsRuleDao.findBySchoolId(schoolId, ApsTaskContants.OBJECT_STUDENT);
			apsRuleItemList = apsRuleItemDao.findByRuleId(apsRule.getId());
			return apsRuleItemList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return apsRuleItemList;
	}

	/**
	 * 根据评价项目类型获取某个学校的学生评价任务对应的评价项目模板数据
	 */
	@Override
	public List<ApsRuleItem> findRuleItems(Integer schoolId, String checkType) {
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取评价项目模板数据失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核类型不能为空");
			throw new IllegalArgumentException("获取评价项目模板数据失败");
		}

		List<ApsRuleItem> apsRuleItemList = null;
		try {
			ApsRule apsRule = apsRuleDao.findBySchoolId(schoolId, ApsTaskContants.OBJECT_STUDENT);
			apsRuleItemList = apsRuleItemDao.findItemsBycheckType(apsRule.getId(),checkType);
			return apsRuleItemList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return apsRuleItemList;
	}


	//=================================================================================

	/**
	 * 进行课堂评价，针对具体某个学生在某节课上的不良行为进行评价
	 */
	@Override
	public Boolean setClassScores(Integer taskItemId, Integer studentId,
			Integer teamId, Integer teacherId, Date checkDate, String checkRange) {
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(studentId == null){
			log.error("学生Id不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(checkRange == null || "".equals(checkRange)){
			log.error("节数不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}

		Boolean flag = false;
		try {
			ApsTaskItem taskItem = apsTaskItemDao.findById(taskItemId);

			ApsTaskScore ApsTaskScore = apsTaskScoreDao.findUniqueByCheckRange(taskItemId, studentId, checkDate, checkRange);
			if(ApsTaskScore != null){
				log.info("课堂记录已存在");
				return flag;
			}

			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(taskItem.getTaskId());
			taskScore.setTaskItemId(taskItemId);
			taskScore.setJudgeId(teacherId);
			taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
			taskScore.setObjectId(studentId);
			taskScore.setParentObjectId(teamId);
			taskScore.setCheckType(ApsTaskContants.CHECK_TYPE_MINUS);
			taskScore.setScore(ApsTaskContants.DEFAULT_SCORE);
			taskScore.setCheckRange(checkRange);
			taskScore.setCheckDate(checkDate);
			taskScore.setRemark("");
			taskScore.setIsDeleted(false);
			taskScore.setCreateDate(new Date());
			apsTaskScoreDao.create(taskScore);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取课堂优化项目 单人单项单节数
	 */
	@Override
	public ApsTaskScore getStudentTaskScore(Integer taskItemId, Integer studentId, Date checkDate, String checkRange){
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("获取课堂优化项目失败");
		}
		if(studentId == null){
			log.error("学生Id不能为空");
			throw new IllegalArgumentException("获取课堂优化项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取课堂优化项目失败");
		}
		if(checkRange == null || "".equals(checkRange)){
			log.error("节数不能为空");
			throw new IllegalArgumentException("获取课堂优化项目失败");
		}

		ApsTaskScore taskScore = null;
		try {
			taskScore =  apsTaskScoreDao.findUniqueByCheckRange(taskItemId, studentId, checkDate, checkRange);
			if(taskScore == null){
				log.info("记录不存在");
			}
			return taskScore;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskScore;
	}


	/**
	 * 进行批量课堂评价，针对具体某个班级的所有学生在某节课上的不良行为进行评价
	 */
	@Override
	public Boolean batchSetClassScores(Integer teacherId, Integer teamId,
			Date checkDate, String checkRange, List<StudentItemData> studentItemDatas) {
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}
		if(checkRange == null || "".equals(checkRange)){
			log.error("节数不能为空");
			throw new IllegalArgumentException("新建课堂优化项目失败");
		}

		Boolean flag = false;
		try {
			if(studentItemDatas != null && studentItemDatas.size() > 0){
				for(StudentItemData studentItemData : studentItemDatas){
					Integer itemId = studentItemData.getItemId();
					Integer studentId = studentItemData.getStudentId();

//					ApsTaskScore taskScore = apsTaskScoreDao.findUniqueByCheckRange(itemId, studentId, checkDate, checkRange);
//					if(taskScore != null){
//						apsTaskScoreDao.delete(taskScore);
//					}
					setClassScores(itemId, studentId, teamId, teacherId, checkDate, checkRange);
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 根据评价项目ID 获取某个班的 课堂优化已经评价的学生列表 用于回显录入列表（某班某节课某个项目的所有学生）
	 */
	@Override
	public List<StudentItemData> findStudentsByItemIdForClassOptimizing(
			Integer teamId, Integer itemId, Date checkDate, String checkRange) {
		if(itemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(checkRange == null || "".equals(checkRange)){
			log.error("节数不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		List<StudentItemData> studentItemDataList = null;
		try {
			studentItemDataList = new ArrayList<StudentItemData>();
			//根据班级id获得该评价项目下的所有记录
			List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findStudentsOfItemId(itemId, teamId, checkDate, checkRange);
			if(taskScoreList != null && taskScoreList.size() > 0){
				//遍历获得所有的学生id
				for(ApsTaskScore taskScore : taskScoreList){
					StudentItemData studentItemData = new StudentItemData();
					studentItemData.setItemId(itemId);
					studentItemData.setStudentId(taskScore.getObjectId());
					studentItemDataList.add(studentItemData);
				}
			}
			return studentItemDataList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentItemDataList;
	}

	/**
	 * 获取某个班已经被差评的学生列表，用于回显录入列表（某班某次所有项目所有的学生）
	 */
	@Override
	public List<StudentItemData> findStudentsForClassOptimizing(String termCode, Integer teamId, Date checkDate, String checkRange) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(checkRange == null || "".equals(checkRange)){
			log.error("节数不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}

		List<StudentItemData> studentItemDataList = null;
		try {
			studentItemDataList = new ArrayList<StudentItemData>();
			ApsTask task = getApsTask(termCode);
			//根据taskId获得所有的减分项目
			List<ApsTaskItem> taskItemList = findDeductMarksItems(task.getId());
			for(ApsTaskItem taskItem : taskItemList){
				List<StudentItemData> list = findStudentsByItemIdForClassOptimizing(teamId, taskItem.getId(), checkDate, checkRange);
				if(list != null && list.size()>0 ){
					studentItemDataList.addAll(list);
				}
			}
			return studentItemDataList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentItemDataList;
	}

	/**
	 * 批量删除某班 某天某节课的激励评价
	 */
	@Override
	public Boolean deleteItemScoresOfOptimizing(String termCode, Integer teamId, Date checkDate, String checkRange) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(checkRange == null || "".equals(checkRange)){
			log.error("节数不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}

		Boolean flag = false;
		try {
			ApsTask task = getApsTask(termCode);
			List<ApsTaskItem> taskItemList = findDeductMarksItems(task.getId());
			for(ApsTaskItem item : taskItemList){
				List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findStudentsOfItemId(item.getId(), teamId, checkDate, checkRange);
				if(taskScoreList != null && taskScoreList.size()>0){
					for(ApsTaskScore taskScore : taskScoreList){
						apsTaskScoreDao.delete(taskScore);
					}
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	/**
	 * 获取某一天某个班级被差评的学生以及对应的差评项目
	 */
	@Override
	public List<ClassEvaScoreData> findClassScoresForTeam(String schoolYear, String termCode, Integer teamId, Date checkDate) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}

		List<ClassEvaScoreData> classEvaScoreDataList = null;
		try {
			classEvaScoreDataList = new ArrayList<ClassEvaScoreData>();
			ApsTask task = getApsTask(termCode);

			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(schoolYear);
			List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);

			for(TeamStudent teamStudent : teamStudentList){
				List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfCheckType(
						task.getId(), teamStudent.getStudentId(), checkDate, ApsTaskContants.CHECK_TYPE_MINUS);

				if(taskScoreList != null && taskScoreList.size() > 0){
					//根据节数分组
					//List<String> checkRangeList = new ArrayList<String>();		//用于存储不同的checkRange
					Map<String,List<ApsTaskScore>> map = new HashMap<String,List<ApsTaskScore>>();	//key是checkRange，value是对应的集合

					for(int i=0; i<taskScoreList.size(); i++){
						String checkRange = taskScoreList.get(i).getCheckRange();
						List<ApsTaskScore> one = new ArrayList<ApsTaskScore>();		//用于存储每一次新出现的checkRange对应的数据
						List<ApsTaskScore> list = map.get(checkRange);		//用于存储已有的checkRange对应的数据
						if(list != null && list.size()>0){
							//添加新的记录，并覆盖原集合
							list.add(taskScoreList.get(i));
							map.put(checkRange,list);
						}else{
							//checkRangeList.add(checkRange);
							//将新出现的checkRange和第一个记录存入map
							one.add(taskScoreList.get(i));
							map.put(checkRange, one);
						}
					}

					//获取组内的项目名，统计次数，拼成字符串
					//遍历map的key-value, key值为节次chenckRange
					Set<Entry<String,List<ApsTaskScore>>> entries = map.entrySet();
					for(Entry<String,List<ApsTaskScore>> entry: entries){
						//遍历value值（list）中的每个对象的itemName，拼成一个String
						String badBehaviours = "";
						Integer teacherId = 0;
						for(ApsTaskScore taskScore : entry.getValue()){
							ApsTaskItem taskItem = apsTaskItemDao.findById(taskScore.getTaskItemId());
							String name = taskItem.getName();
							badBehaviours += name+"&nbsp;&nbsp;&nbsp;&nbsp;";
							teacherId = taskScore.getJudgeId();
						}

						ClassEvaScoreData data = new ClassEvaScoreData();
						data.setCheckDate(checkDate);
//						data.setNumber(student.getStudentNumber());
//						data.setName(student.getName());
						data.setNumber(studentService.findStudentById(teamStudent.getStudentId()).getStudentNumber());
						data.setName(teamStudent.getName());
						data.setCheckRange(entry.getKey());
						data.setBadBehaviours(badBehaviours);
						data.setCourseTeacher(teacherService.findTeacherById(teacherId).getName());

						//每遍历一次，存储一个对象
						classEvaScoreDataList.add(data);
					}

				}

			}
			//按节次排序
			Collections.sort(classEvaScoreDataList, new Comparator<ClassEvaScoreData>(){
				public int compare(ClassEvaScoreData d1, ClassEvaScoreData d2) {
					String cr1 = d1.getCheckRange();
					Integer num1 = Integer.parseInt(cr1.substring(1, cr1.indexOf("节")));
					String cr2 = d2.getCheckRange();
					Integer num2 = Integer.parseInt(cr2.substring(1, cr2.indexOf("节")));
					if(num1 > num2){
						return 1;
					}
					if(num1 == num2){
						return 0;
					}
					return -1;
				}
			});

			return classEvaScoreDataList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return classEvaScoreDataList;
	}

	/**
	 * 获取某个学期每周某个班所被差评的学生以及对应的差评项目
	 */
	@Override
	public List<ClassEvaScoreData> findClassScoresForTeam(String schoolYear, String termCode,
			Integer teamId, Date startDate, Date finishDate, String name) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学年不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}
		if(startDate == null || finishDate == null ){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取课堂优化信息失败");
		}

		List<ClassEvaScoreData> classEvaScoreDataList = null;
		try {
			ApsTask task = getApsTask(termCode);
			classEvaScoreDataList = apsTaskScoreDao.findClassScoresForTeam(task.getId(), teamId, startDate, finishDate);

			if(classEvaScoreDataList != null && classEvaScoreDataList.size()>0){
				for(ClassEvaScoreData data : classEvaScoreDataList){
					Student student = studentService.findStudentById(data.getStudentId());
					data.setNumber(student.getStudentNumber());
					data.setName(student.getName());
					data.setCourseTeacher(teacherService.findTeacherById(data.getTeacherId()).getName());

					String badBehaviours = data.getBadBehaviours();
					badBehaviours = badBehaviours.replaceAll(",", "&nbsp;&nbsp;&nbsp;&nbsp;");
					data.setBadBehaviours(badBehaviours);
				}

				//根据学生姓名筛选
				if(name != null && !"".equals(name)){
					List<ClassEvaScoreData> dataList = new ArrayList<ClassEvaScoreData>();
					String regex = ".*"+name+".*";
					for(ClassEvaScoreData data : classEvaScoreDataList){
						if(data.getName().matches(regex)){
							dataList.add(data);
						}
					}
					return dataList;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return classEvaScoreDataList;
	}


	/**
	 * 获取 某时间段内 学校各年级 的课堂优化统计情况
	 */
	@Override
	public List<ClassOptimizingSummaryData> findClassOptimizingCountForSchool(
			Integer schoolId, String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}
		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}
		if(startDate == null || finishDate == null ){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}

		List<ClassOptimizingSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<ClassOptimizingSummaryData>();
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolTerm.getSchoolYear());
			Integer totalCount = 0;
			for(Grade grade : gradeList){
				List<ClassOptimizingSummaryData> datas = findClassOptimizingCountForGrade(grade.getId(), termCode, startDate, finishDate);

				Integer count = 0;
				for(ClassOptimizingSummaryData data : datas){
					count += data.getCount();
				}

				ClassOptimizingSummaryData data = new ClassOptimizingSummaryData();
				data.setObjectId(grade.getId());
				data.setObjectName(grade.getName());
				data.setCount(count);
				summaryDatas.add(data);
				totalCount += count;
			}
			//添加百分比
			for(ClassOptimizingSummaryData data : summaryDatas){
				if(totalCount != 0){
					data.setRatio((float)data.getCount()/totalCount);
				}else{
					data.setRatio((float)0);
				}
			}
			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 *  获取 某时间段内 某年级各个班级 的课堂优化统计情况
	 */
	@Override
	public List<ClassOptimizingSummaryData> findClassOptimizingCountForGrade(
			Integer gradeId, String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}
		if(startDate == null || finishDate == null ){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}

		List<ClassOptimizingSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<ClassOptimizingSummaryData>();
			List<Team> teamList = teamService.findTeamOfGrade(gradeId);
			Integer totalCount = 0;
			for(Team team : teamList){
				List<ClassOptimizingSummaryData> datas = findClassOptimizingCountForTeam(team.getId(), null, termCode, startDate, finishDate);
				//遍历每一个项目的次数，获得班级总次数
				Integer count = 0;
				if(datas != null && datas.size()>0){
					for(ClassOptimizingSummaryData data : datas){
						count += data.getCount();
					}
				}

				ClassOptimizingSummaryData data = new ClassOptimizingSummaryData();
				data.setObjectId(team.getId());
				data.setObjectName(team.getName());
				data.setCount(count);
				summaryDatas.add(data);
				totalCount += count;
			}
			//添加百分比
			for(ClassOptimizingSummaryData data : summaryDatas){
				if(totalCount != 0){
					data.setRatio((float)data.getCount()/totalCount);
				}else{
					data.setRatio((float)0);
				}
			}
			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 * 获取 某时间段内 某班级各个项目 的课堂优化统计情况
	 */
	@Override
	public List<ClassOptimizingSummaryData> findClassOptimizingCountForTeam(
			Integer teamId, Integer studentId, String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}
		if(startDate == null || finishDate == null ){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取课堂优化统计情况失败");
		}

		List<ClassOptimizingSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<ClassOptimizingSummaryData>();

			ApsTask task = getApsTask(termCode);

			//获取所有的减分项目
			List<ApsTaskItem> apsTaskItemList = findDeductMarksItems(task.getId());
			Integer totalCount = 0;
			for(ApsTaskItem taskItem : apsTaskItemList){
				//获取每个项目的在班级的所有记录
				List<ApsTaskScore> taskScoreList = null;
				if(studentId == null){
					taskScoreList = apsTaskScoreDao.findCountForTeam(taskItem.getId(), teamId, startDate, finishDate);
				}else{
					taskScoreList = apsTaskScoreDao.findItemsOfTime(taskItem.getId(), studentId, startDate, finishDate);
				}

				if(taskScoreList != null && taskScoreList.size()>0){
					ClassOptimizingSummaryData data = new ClassOptimizingSummaryData();
					data.setObjectId(taskItem.getId());
					data.setObjectName(taskItem.getName());
					data.setCount(taskScoreList.size());
					summaryDatas.add(data);
					totalCount += taskScoreList.size();
				}
			}
			//添加百分比
			if(summaryDatas != null && summaryDatas.size()>0){
				for(ClassOptimizingSummaryData data : summaryDatas){
					if(totalCount != 0){
						data.setRatio((float)data.getCount()/totalCount);
					}else{
						data.setRatio((float)0);
					}
				}
			}
			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return summaryDatas;
	}

	//=============================================================================

	/**
	 * 进行激励评价，针对具体某个学生某一天的某项优秀行为进行评价
	 */
	@Override
	public Boolean setIncreaseScores(Integer taskItemId, Integer studentId,
			Integer teamId, Integer teacherId, Date checkDate) {
		if(taskItemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}
		if(studentId == null){
			log.error("学生Id不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}

		Boolean flag = false;
		try {
			ApsTaskItem taskItem = apsTaskItemDao.findById(taskItemId);

			ApsTaskScore ApsTaskScore = apsTaskScoreDao.findUnique(taskItemId, studentId, checkDate);
			if(ApsTaskScore != null){
				log.info("课堂记录已存在");
				return flag;
			}

			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(taskItem.getTaskId());
			taskScore.setTaskItemId(taskItemId);
			taskScore.setJudgeId(teacherId);
			taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
			taskScore.setObjectId(studentId);
			taskScore.setParentObjectId(teamId);
			taskScore.setCheckType(ApsTaskContants.CHECK_TYPE_ADD);
			taskScore.setScore(ApsTaskContants.DEFAULT_SCORE);
			taskScore.setCheckRange("");
			taskScore.setCheckDate(checkDate);
			taskScore.setRemark("");
			taskScore.setIsDeleted(false);
			taskScore.setCreateDate(new Date());
			apsTaskScoreDao.create(taskScore);

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 批量进行激励评价，针对具体某班的所有学生某一天的所有优秀行为进行评价
	 */
	@Override
	public Boolean batchSetIncreaseScores(Integer teamId, Integer teacherId,
			Date checkDate,List<StudentItemData> studentItemDatas) {
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}
		if(teacherId == null){
			log.error("评价教师不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("新建激励评价项目失败");
		}

		Boolean flag = false;
		try {
			for(StudentItemData data : studentItemDatas){
				Integer itemId = data.getItemId();
				Integer studentId = data.getStudentId();

//				ApsTaskScore taskScore = apsTaskScoreDao.findUnique(itemId, studentId, checkDate);
//				if(taskScore != null){
//					apsTaskScoreDao.delete(taskScore);
//				}
				setIncreaseScores(itemId, studentId, teamId, teacherId, checkDate);
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取某个班级 某天 某个优秀行为（激励评价）的记录
	 */
	@Override
	public List<StudentItemData> findStudentsByItemIdForIncrease(
			Integer teamId, Integer itemId, Date checkDate) {
		if(itemId == null){
			log.error("评价项目Id不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}

		List<StudentItemData> studentItemDataList = null;
		try {
			studentItemDataList = new ArrayList<StudentItemData>();
			//根据班级id获得该评价项目下的所有记录
			List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findStudentOfIncrease(itemId, teamId, checkDate);
			if(taskScoreList != null && taskScoreList.size() > 0){
				for(ApsTaskScore taskScore : taskScoreList){
					StudentItemData data = new StudentItemData();
					data.setItemId(itemId);
					data.setStudentId(taskScore.getObjectId());
					studentItemDataList.add(data);
				}
			}
			return studentItemDataList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return studentItemDataList;
	}

	/**
	 * 获取某个班级 某天 所有优秀行为（激励评价）的记录
	 */
	@Override
	public List<StudentItemData> findStudentsByItemIdForIncrease(
			String termCode, Integer teamId, Date checkDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取学生列表失败");
		}

		List<StudentItemData> studentItemDataList = null;
		try {
			studentItemDataList = new ArrayList<StudentItemData>();
			ApsTask task = getApsTask(termCode);
			//根据taskId获得所有的加分项目
			List<ApsTaskItem> taskItemList = findAwardedMarksItems(task.getId());
			for(ApsTaskItem taskItem : taskItemList){
				List<StudentItemData> list = findStudentsByItemIdForIncrease(teamId, taskItem.getId(), checkDate);
				studentItemDataList.addAll(list);
			}
			return studentItemDataList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentItemDataList;
	}

	/**
	 * 批量删除某天某班的激励评价
	 */
	@Override
	public Boolean deleteItemScoresOfIncrease(String termCode, Integer teamId,Date checkDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}

		Boolean flag = false;
		try {
			ApsTask task = getApsTask(termCode);
			List<ApsTaskItem> taskItemList = findAwardedMarksItems(task.getId());
			for(ApsTaskItem item : taskItemList){
				List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findStudentOfIncrease(item.getId(), teamId, checkDate);
				if(taskScoreList != null && taskScoreList.size()>0){
					for(ApsTaskScore taskScore : taskScoreList){
						apsTaskScoreDao.delete(taskScore);
					}
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/**
	 * 统计班级每个学生的 激励评价总数
	 */
	@Override
	public List<IncreaseEvaScoreData> findIncreaseScoresForTeam(String schoolYear,
			String termCode, Integer teamId, Date startDate, Date finishDate) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取评价报表失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取评价报表失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取评价报表失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取评价报表失败");
		}

		List<IncreaseEvaScoreData> increaseDatas = null;
		try {
			increaseDatas = new ArrayList<IncreaseEvaScoreData>();
			ApsTask task = getApsTask(termCode);

			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(schoolYear);
			List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);

			for(TeamStudent teamStudent : teamStudentList){
				List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfTime(
						task.getId(), teamStudent.getStudentId(), ApsTaskContants.CHECK_TYPE_ADD, startDate, finishDate);


				IncreaseEvaScoreData data = new IncreaseEvaScoreData();
				data.setNumber(teamStudent.getNumber());;
				data.setStudentId(teamStudent.getStudentId());
				data.setStudentName(teamStudent.getName());
				if(taskScoreList != null && taskScoreList.size()>0){
					data.setCount(taskScoreList.size());
				}else{
					data.setCount(0);
				}
				increaseDatas.add(data);
			}
			return increaseDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return increaseDatas;
	}

	/**
	 * 获取 某时间段内 学校各年级 的激励评价统计情况
	 */
	@Override
	public List<IncreaseSummaryData> findIncreaseCountForSchool(
			Integer schoolId, String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}

		List<IncreaseSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<IncreaseSummaryData>();
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolTerm.getSchoolYear());
			Integer totalCount = 0;
			for(Grade grade : gradeList){
				List<IncreaseSummaryData> datas = findIncreaseCountForGrade(grade.getId(),termCode, startDate, finishDate);
				Integer count = 0;
				for(IncreaseSummaryData data : datas){
					count += data.getCount();
				}
				IncreaseSummaryData data = new IncreaseSummaryData();
				data.setObjectId(grade.getId());
				data.setObjectName(grade.getName());
				data.setCount(count);
				summaryDatas.add(data);
				totalCount += count;
			}

			for(IncreaseSummaryData data : summaryDatas){
				if(totalCount != 0){
					data.setRatio( (float)data.getCount()/totalCount );
				}else{
					data.setRatio((float)0);
				}
			}
			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 * 获取 某时间段内 某年级各班级 的激励评价统计情况
	 */
	@Override
	public List<IncreaseSummaryData> findIncreaseCountForGrade(Integer gradeId,
			String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		List<IncreaseSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<IncreaseSummaryData>();
			List<Team> teamList = teamService.findTeamOfGrade(gradeId);
			Integer totalCount = 0;
			for(Team team : teamList){
				List<IncreaseSummaryData> datas = findIncreaseCountForTeam(team.getId(), null, termCode, startDate, finishDate);
				Integer count = 0;
				if(datas != null && datas.size()>0){
					for(IncreaseSummaryData data : datas){
						count += data.getCount();
					}
				}
				IncreaseSummaryData data = new IncreaseSummaryData();
				data.setObjectId(team.getId());
				data.setObjectName(team.getName());
				data.setCount(count);
				summaryDatas.add(data);
				totalCount += count;
			}

			for(IncreaseSummaryData data : summaryDatas){
				if(totalCount != 0){
					data.setRatio( (float)data.getCount()/totalCount );
				}else{
					data.setRatio((float)0);
				}
			}
			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 * 获取 某时间段内 某班级各项目组 的激励评价统计情况
	 */
	@Override
	public List<IncreaseSummaryData> findIncreaseCountForTeam(Integer teamId, Integer studentId,
			String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取激励评价统计数据失败");
		}

		List<IncreaseSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<IncreaseSummaryData>();
			ApsTask task = getApsTask(termCode);

			Integer totalCount = 0;
			//品德发展评价卡
			List<ApsTaskScore> pindeDates = new ArrayList<ApsTaskScore>();
			//学业发展评价卡
			List<ApsTaskScore> xueyeDates = new ArrayList<ApsTaskScore>();
			//身心发展评价卡
			List<ApsTaskScore> shenxinDates = new ArrayList<ApsTaskScore>();
			//兴趣特长评价卡
			List<ApsTaskScore> xingquDates = new ArrayList<ApsTaskScore>();
			//实践操作评价卡
			List<ApsTaskScore> shijianDates = new ArrayList<ApsTaskScore>();
			if(studentId == null){
				pindeDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						null, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_PINDE);
				xueyeDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						null, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_XUEYE);
				shenxinDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						null, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_SHENXIN);
				xingquDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						null, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_XINGQU);
				shijianDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						null, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_SHIJIAN);
			}else{
				pindeDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						studentId, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_PINDE);
				xueyeDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						studentId, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_XUEYE);
				shenxinDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						studentId, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_SHENXIN);
				xingquDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						studentId, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_XINGQU);
				shijianDates = apsTaskScoreDao.findIncreaseCountForTeam(task.getId(), teamId,
						studentId, startDate, finishDate, ApsTaskContants.CHECK_TYPE_ADD, ApsTaskContants.CATEGORY_SHIJIAN);
			}

			IncreaseSummaryData pindeData = new IncreaseSummaryData();
			pindeData.setObjectName(ApsTaskContants.CATEGORY_PINDE);
			pindeData.setCount(pindeDates.size());
			summaryDatas.add(pindeData);
			totalCount += pindeDates.size();

			IncreaseSummaryData xueyeData = new IncreaseSummaryData();
			xueyeData.setObjectName(ApsTaskContants.CATEGORY_XUEYE);
			xueyeData.setCount(xueyeDates.size());
			summaryDatas.add(xueyeData);
			totalCount += xueyeDates.size();

			IncreaseSummaryData shenxinData = new IncreaseSummaryData();
			shenxinData.setObjectName(ApsTaskContants.CATEGORY_SHENXIN);
			shenxinData.setCount(shenxinDates.size());
			summaryDatas.add(shenxinData);
			totalCount += shenxinDates.size();

			IncreaseSummaryData xingquDate = new IncreaseSummaryData();
			xingquDate.setObjectName(ApsTaskContants.CATEGORY_XINGQU);
			xingquDate.setCount(xingquDates.size());
			summaryDatas.add(xingquDate);
			totalCount += xingquDates.size();

			IncreaseSummaryData shijianDate = new IncreaseSummaryData();
			shijianDate.setObjectName(ApsTaskContants.CATEGORY_SHIJIAN);
			shijianDate.setCount(shijianDates.size());
			summaryDatas.add(shijianDate);
			totalCount += shijianDates.size();

			for(IncreaseSummaryData data : summaryDatas){
				if(totalCount != 0){
					data.setRatio((float)data.getCount()/totalCount);
				}else{
					data.setRatio((float)0);
				}
			}

			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	//===================================================================================


	/**
	 * 设置发展评价卡，针对具体某个学生某一个月的优秀行为进行评分
	 */
	@Override
	public Boolean setNormalScores(String termCode, Integer studentId, Integer teamId, Float score,
			Integer teacherId, Date checkDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(studentId == null){
			log.error("学生Id不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(score == null){
			log.error("得分不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(teacherId == null){
			log.error("教师不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}

		Boolean flag = false;
		try {
			ApsTask task = getApsTask(termCode);
			List<ApsTaskItem> taskItems = findNormalItems(task.getId());

			ApsTaskScore ApsTaskScore = apsTaskScoreDao.findNormalScore(taskItems.get(0).getId(), studentId, checkDate);
			if(ApsTaskScore == null && score != 0){
				ApsTaskScore taskScore = new ApsTaskScore();
				taskScore.setTaskId(task.getId());
				taskScore.setTaskItemId(taskItems.get(0).getId());
				taskScore.setJudgeId(teacherId);
				taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
				taskScore.setObjectId(studentId);
				taskScore.setParentObjectId(teamId);
				taskScore.setCheckType(ApsTaskContants.CHECK_TYPE_NORMAL);
				taskScore.setScore(score);
				taskScore.setCheckRange("");
				taskScore.setCheckDate(checkDate);
				taskScore.setRemark("");
				taskScore.setIsDeleted(false);
				taskScore.setCreateDate(new Date());
				apsTaskScoreDao.create(taskScore);

			}else{
				if(score != 0){
					ApsTaskScore.setScore(score);
					ApsTaskScore.setJudgeId(teacherId);
					ApsTaskScore.setModifyDate(new Date());
					apsTaskScoreDao.update(ApsTaskScore);
				}else{
					apsTaskScoreDao.delete(ApsTaskScore);
				}
			}

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 批量设置发展评价卡，针对具体某个班级的一批学生某一个月的优秀行为进行评分
	 */
	@Override
	public Boolean batchSetNormalScores(String termCode, Integer teamId, Integer teacherId,
			List<StudentScoreData> studentScoreDatas, Date checkDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(teacherId == null){
			log.error("教师不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("设置发展评价卡失败");
		}

		Boolean flag = false;
		try {

			if(studentScoreDatas != null && studentScoreDatas.size()>0){
				for(StudentScoreData data : studentScoreDatas){
					Integer studentId = data.getStudentId();
					Float score = data.getScore();
//					ApsTask task = getApsTask(termCode);
//					List<ApsTaskItem> taskItems = findNormalItems(task.getId());
//					ApsTaskScore taskScore = apsTaskScoreDao.findNormalScore(taskItems.get(0).getId(), studentId, checkDate);
//					if(taskScore != null){
//						apsTaskScoreDao.delete(taskScore);
//					}
					setNormalScores(termCode, studentId, teamId, score, teacherId, checkDate);
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 获取某个班级每个学生的发展卡总数
	 */
	@Override
	public List<NormalEvaScoreData> findNormalScoresForTeam(String schoolYear, String termCode,
			Integer teamId, Date startDate, Date finishDate) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取发展评价卡失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取发展评价卡失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取发展评价卡失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取发展评价卡失败");
		}

		List<NormalEvaScoreData> dataList = null;
		try {

			dataList = new ArrayList<NormalEvaScoreData>();

			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(schoolYear);
			List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);

			ApsTask task = getApsTask(termCode);
			for(TeamStudent teamStudent : teamStudentList){
				List<ApsTaskScore> taskScoreList = apsTaskScoreDao.findScoresOfTime(task.getId(),
						teamStudent.getStudentId(), ApsTaskContants.CHECK_TYPE_NORMAL, startDate, finishDate);

				float count = 0;
				Date checkDate = null;
				if(taskScoreList != null && taskScoreList.size()>0){
					for(ApsTaskScore taskScore : taskScoreList){
						count += taskScore.getScore();
					}
					checkDate = taskScoreList.get(0).getModifyDate();
				}

				NormalEvaScoreData data = new NormalEvaScoreData();
				data.setNumber(teamStudent.getNumber());
				data.setStudentId(teamStudent.getStudentId());
				data.setStudentName(teamStudent.getName());
				data.setCount(count);
				data.setCheckDate(checkDate);
				dataList.add(data);


			}
			return dataList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	/**
	 * 获取 某时间段内 学校各年级 的发展评价统计情况
	 */
	@Override
	public List<NormalSummaryData> findNormalCountForSchool(Integer schoolId,
			String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}
		if(schoolId == null){
			log.error("学校id不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}

		List<NormalSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<NormalSummaryData>();
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolTerm.getSchoolYear());
			float totalCount = 0;
			for(Grade grade : gradeList){
				List<NormalSummaryData> datas = findNormalCountForGrade(grade.getId(), termCode, startDate, finishDate);
				float count = 0;
				for(NormalSummaryData data : datas){
					count += data.getCount();
				}
				NormalSummaryData data = new NormalSummaryData();
				data.setObjectId(grade.getId());
				data.setObjectName(grade.getName());
				data.setCount(count);
				summaryDatas.add(data);
				totalCount += count;
			}

			for(NormalSummaryData data : summaryDatas){
				if(totalCount != 0 ){
					data.setRatio( (float)data.getCount()/totalCount );
				}else{
					data.setRatio((float)0);
				}
			}
			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 * 获取 某时间段内 某年级各班级 的发展评价统计情况
	 */
	@Override
	public List<NormalSummaryData> findNormalCountForGrade(Integer gradeId,
			String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}

		List<NormalSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<NormalSummaryData>();
			List<Team> teamList = teamService.findTeamOfGrade(gradeId);
			float totalCount = 0;
			for(Team team : teamList){
				List<NormalSummaryData> datas = findNormalCountForTeam(team.getId(), null, termCode, startDate, finishDate);
				float count = 0;
				for(NormalSummaryData data : datas){
					count += data.getCount();
				}
				NormalSummaryData data = new NormalSummaryData();
				data.setObjectId(team.getId());
				data.setObjectName(team.getName());
				data.setCount(count);
				summaryDatas.add(data);
				totalCount += count;
			}

			for(NormalSummaryData data : summaryDatas){
				if(totalCount != 0){
					data.setRatio( (float)data.getCount()/totalCount );
				}else{
					data.setRatio((float)0);
				}
			}
			return summaryDatas;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 *  获取 某时间段内 某班级 的发展评价统计情况
	 */
	@Override
	public List<NormalSummaryData> findNormalCountForTeam(Integer teamId, Integer studentId,
			String termCode, Date startDate, Date finishDate) {
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}
		if(teamId == null){
			log.error("班级（上级）Id不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}
		if(startDate == null || finishDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("获取发展评价统计数据失败");
		}

		List<NormalSummaryData> summaryDatas = null;
		try {
			summaryDatas = new ArrayList<NormalSummaryData>();

			ApsTask task = getApsTask(termCode);
			List<ApsTaskItem> taskItems = findNormalItems(task.getId());
			ApsTaskItem taskItem = taskItems.get(0);

			List<ApsTaskScore> taskScoreList = null;
			if(studentId == null){
				taskScoreList = apsTaskScoreDao.findCountForTeam(taskItem.getId(), teamId, startDate, finishDate);
			}else{
				taskScoreList = apsTaskScoreDao.findItemsOfTime(taskItem.getId(), studentId, startDate, finishDate);
			}
			float count = 0;
			if(taskScoreList != null && taskScoreList.size()>0){
				for(ApsTaskScore taskScore : taskScoreList){
					count += taskScore.getScore();
				}
			}

			NormalSummaryData data = new NormalSummaryData();
			data.setObjectId(taskItem.getId());
			data.setObjectName(taskItem.getName());
			data.setCount(count);
			if(count == 0){
				data.setRatio((float)0);
			}else{
				data.setRatio((float)1);
			}
			summaryDatas.add(data);

			return summaryDatas;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	//==============================================================================


	//==================================================================================

	/**
	 * 设置 班级之星的 获取人数
	 */
	@Override
	public Boolean setTeamStarReachCount(Integer gradeId, Integer reachCount) {
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("设置班级之星人数失败");
		}
		if(reachCount == null){
			log.error("获奖人数不能为空");
			throw new IllegalArgumentException("设置班级之星人数失败");
		}

		Grade grade = gradeService.findGradeById(gradeId);
		if(grade == null){
			log.error("找不到该年级");
			throw new IllegalArgumentException("设置班级之星人数失败");
		}

		Boolean flag = false;
		try {

			ApsMedal medal = apsMedalDao.findMedalsOfStudent(grade.getSchoolId(),
					grade.getUniGradeCode(), ApsTaskContants.STAR_OF_TEAM, ApsTaskContants.OBJECT_STUDENT);
			if(medal != null){
				medal.setReachCount(reachCount);
				apsMedalDao.update(medal);
				flag = true;
			}else{
				log.error("找不到奖项数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 批量设置 班级之星的 获取人数
	 */
	@Override
	public Boolean bacthSetTeamStarReachCount(List<EvaluationMedalData> evaluationMedalDatas) {
		Boolean flag = false;
		try {
			if(evaluationMedalDatas != null && evaluationMedalDatas.size()>0){
				for(EvaluationMedalData data : evaluationMedalDatas){
					setTeamStarReachCount(data.getGradeId(), data.getTeamCount());
				}
			}else{
				log.error("输入的数据为空");
				throw new IllegalArgumentException("设置班级之星人数失败");
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 设置 年级之星的 获取人数
	 */
	@Override
	public Boolean setGradeStarReachCount(Integer gradeId, Integer reachCount) {
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("设置年级之星人数失败");
		}
		if(reachCount == null){
			log.error("获奖人数不能为空");
			throw new IllegalArgumentException("设置年级之星人数失败");
		}
		Grade grade = gradeService.findGradeById(gradeId);
		if(grade == null){
			log.error("找不到该年级");
			throw new IllegalArgumentException("设置年级之星人数失败");
		}

		Boolean flag = false;
		try {

			ApsMedal medal = apsMedalDao.findMedalsOfStudent(grade.getSchoolId(),
					grade.getUniGradeCode(), ApsTaskContants.STAR_OF_GRADE, ApsTaskContants.OBJECT_STUDENT);
			if(medal != null){
				medal.setReachCount(reachCount);
				apsMedalDao.update(medal);
				flag = true;
			}else{
				log.error("找不到奖项数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag ;
	}

	/**
	 * 批量设置 年级之星的 获取人数
	 */
	@Override
	public Boolean batchSetGradeStarReachCount(List<EvaluationMedalData> evaluationMedalDatas) {
		Boolean flag = false;
		try {
			if(evaluationMedalDatas != null && evaluationMedalDatas.size()>0){
				for(EvaluationMedalData data : evaluationMedalDatas){
					setGradeStarReachCount(data.getGradeId(), data.getGradeCount());
				}
			}else{
				log.error("输入的数据为空");
				throw new IllegalArgumentException("设置年级之星人数失败");
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	//----------------------\\

	/**
	 * 设置 全校之星的 获取人数
	 */
	@Override
	public Boolean setSchoolStarReachCount(Integer schoolId, Integer reachCount) {
		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("设置全校之星人数失败");
		}
		if(reachCount == null){
			log.error("获奖人数不能为空");
			throw new IllegalArgumentException("设置全校之星人数失败");
		}
		Boolean flag = false;
		try {
			ApsMedal medal = apsMedalDao.findMedalsOfStudent(schoolId, "",
					ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
			if(medal != null){
				medal.setReachCount(reachCount);
				apsMedalDao.update(medal);
				flag = true;
			}else{
				log.error("找不到奖项数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 设置星级个人的 获奖人数
	 */
	@Override
	public Boolean setStarReachCount(Integer gradeId, String name, Integer reachCount) {
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("设置人数失败");
		}
		if(name == null || "".equals(name)){
			log.error("名称不能为空");
			throw new IllegalArgumentException("设置人数失败");
		}
		if(reachCount == null){
			log.error("获奖人数不能为空");
			throw new IllegalArgumentException("设置人数失败");
		}

		Boolean flag = false;
		try {
			Grade grade = gradeService.findGradeById(gradeId);
			if(grade == null){
				log.info("找不到该年级");
				throw new Exception("设置人数失败");
			}

			ApsMedal medal = apsMedalDao.findMedalsOfStudent(grade.getSchoolId(),
					grade.getUniGradeCode(), name, ApsTaskContants.OBJECT_STUDENT);
			if(medal != null){
				medal.setReachCount(reachCount);
				apsMedalDao.update(medal);
				flag = true;
			}else{
				log.error("找不到奖项数据");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 批量设置 班级之星和年级之星的 获奖人数
	 * @param datas
	 */
	@Override
	public void batchsetStarReachCount(List<EvaluationMedalData> datas){

		try {
			if(datas != null && datas.size()>0){
				for(EvaluationMedalData data : datas){
					//设置班级之星
					setStarReachCount(data.getGradeId(), ApsTaskContants.STAR_OF_TEAM, data.getTeamCount());
					//设置年级之星
					setStarReachCount(data.getGradeId(), ApsTaskContants.STAR_OF_GRADE, data.getGradeCount());
				}
			}else{
				log.error("输入的数据为空");
				throw new IllegalArgumentException("设置人数失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取 星级个人的 获奖人数
	 * @return
	 */
	@Override
	public ApsMedal getStarReachCount(Integer gradeId, String name) {
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("获取人数失败");
		}
		if(name == null || "".equals(name)){
			log.error("名称不能为空");
			throw new IllegalArgumentException("获取人数失败");
		}
		Grade grade = gradeService.findGradeById(gradeId);
		if(grade == null){
			log.error("找不到该年级");
			throw new IllegalArgumentException("获取人数失败");
		}

		ApsMedal medal = null;
		try {
			medal = apsMedalDao.findMedalsOfStudent(grade.getSchoolId(),
					grade.getUniGradeCode(), name, ApsTaskContants.OBJECT_STUDENT);
			return medal;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medal;
	}

	/**
	 * 获取 全校之星的 获奖人数
	 * @param schoolId
	 * @return
	 */
	@Override
	public ApsMedal getSchooltStarReachCount(Integer schoolId){
		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("获取人数失败");
		}
		ApsMedal medal = null;
		try {
			medal = apsMedalDao.findMedalsOfStudent(schoolId, "",
					ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return medal;
	}

	/**
	 * 批量获取 年纪之星和班级之星的 获奖人数
	 * @return
	 */
	@Override
	public List<EvaluationMedalData> batchgetStarReachCount(Integer schoolId, String termCode){
		if(schoolId == null){
			log.error("学校Id不能为空");
			throw new IllegalArgumentException("获取人数失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("获取人数失败");
		}

		List<EvaluationMedalData> dataList = null;
		try {

			dataList = new ArrayList<EvaluationMedalData>();
			SchoolTerm schoolTerm = schoolTermDao.findSchoolYearBySchoolTerm(schoolId, termCode);
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolTerm.getSchoolYear());

			if(gradeList != null && gradeList.size()>0){
				for(Grade grade : gradeList){
					ApsMedal teamMedal = getStarReachCount(grade.getId(), ApsTaskContants.STAR_OF_TEAM);
					ApsMedal gradeMedal = getStarReachCount(grade.getId(), ApsTaskContants.STAR_OF_GRADE);

					EvaluationMedalData data = new EvaluationMedalData();
					data.setGradeId(grade.getId());
					data.setGradeName(grade.getName());
					data.setTeamStar(ApsTaskContants.STAR_OF_TEAM);
					data.setTeamCount(teamMedal.getReachCount());
					data.setGradeStar(ApsTaskContants.STAR_OF_GRADE);
					data.setGradeCount(gradeMedal.getReachCount());
					dataList.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	//----------------//

	/**
	 * 获取每个学生的评价总分
	 */
//	public List<StarEvaluateData> getStudentDate(List<Student> studentList,
//			Integer taskId, Date startDate, Date endDate){
//		List<StarEvaluateData> starDataList = new ArrayList<StarEvaluateData>();
//		for(Student student : studentList){
//			//激励评价项目列表，集合长度就是分数
//			List<ApsTaskScore> addScoreList = apsTaskScoreDao.findScoresOfTime(
//					taskId, student.getId(), ApsTaskContants.CHECK_TYPE_ADD, startDate, endDate);
//			//发展评价项目列表（有且仅有一项）的分数和
//			List<ApsTaskScore> normalScoreList = apsTaskScoreDao.findScoresOfTime(taskId,
//					student.getId(), ApsTaskContants.CHECK_TYPE_NORMAL, startDate, endDate);
//			float normalCount = 0;
//			if(normalScoreList != null && normalScoreList.size()>0){
//				for(ApsTaskScore taskScore : normalScoreList){
//					normalCount += taskScore.getScore();
//				}
//			}
//			//将数据存入 汇总集合中
//			StarEvaluateData data = new StarEvaluateData();
//			data.setStudentName(student.getName());
//			data.setStudentId(student.getId());
//			data.setTeamId(student.getTeamId());
//			data.setTeamName(student.getTeamName());
//			data.setAddScore(addScoreList.size());
//			data.setNormalScore((int)normalCount);
//			data.setTotalScore(addScoreList.size()+(int)normalCount);
//			starDataList.add(data);
//		}
//		return starDataList;
//	}

	public List<StarEvaluateData> getTeamStudentDate(List<TeamStudent> TeamStudentList,
			Integer taskId, Date startDate, Date endDate){
		List<StarEvaluateData> starDataList = new ArrayList<StarEvaluateData>();
		for(TeamStudent teamStudent : TeamStudentList){
			//激励评价项目列表，集合长度就是分数
			List<ApsTaskScore> addScoreList = apsTaskScoreDao.findScoresOfTime(
					taskId, teamStudent.getStudentId(), ApsTaskContants.CHECK_TYPE_ADD, startDate, endDate);
			//发展评价项目列表（有且仅有一项）的分数和
			List<ApsTaskScore> normalScoreList = apsTaskScoreDao.findScoresOfTime(taskId,
					teamStudent.getStudentId(), ApsTaskContants.CHECK_TYPE_NORMAL, startDate, endDate);
			float normalCount = 0;
			if(normalScoreList != null && normalScoreList.size()>0){
				for(ApsTaskScore taskScore : normalScoreList){
					normalCount += taskScore.getScore();
				}
			}
			Team team = teamService.findTeamById(teamStudent.getTeamId());
			//将数据存入 汇总集合中
			StarEvaluateData data = new StarEvaluateData();
			data.setStudentName(teamStudent.getName());
			data.setStudentId(teamStudent.getStudentId());
			data.setTeamId(teamStudent.getTeamId());
			data.setTeamName(team.getName());
			data.setAddScore(addScoreList.size());
			data.setNormalScore((int)normalCount);
			data.setTotalScore(addScoreList.size()+(int)normalCount);
			data.setTeamCode(team.getCode());
			starDataList.add(data);
		}
		return starDataList;
	}

	/**
	 * 对集合进行排序
	 */
	public void sortStarData(List<StarEvaluateData> starDataList){
		//降序排列
//		Collections.sort(starDataList, new Comparator<StarEvaluateData>(){
//			public int compare(StarEvaluateData d1, StarEvaluateData d2) {
//				if(d1.getTotalScore()<d2.getTotalScore()){
//					return 1;
//				}
//				if(d1.getTotalScore()==d2.getTotalScore()){
//					return 0;
//				}
//				return -1;
//			}
//		});
		Collections.sort(starDataList);
		int rank = 1;
		for(int i=0;i<starDataList.size();i++){
			StarEvaluateData data = starDataList.get(i);
			if(i==0){
				//第一个最大，序号为1
				data.setRank(1);
			}else{
				if(data.getTotalScore()<starDataList.get(i-1).getTotalScore()){
					//当前数字比上一个小，序号+1
					rank = i+1;
					data.setRank(rank);
				}else{
					data.setRank(rank);
				}
			}
		}
	}

	/**
	 * 获取月度 的班级之星
	 */
	@Override
	public List<StarEvaluateData> getMonthlyTeamStar(String schoolYear, String termCode, Integer teamId,
			Date startDate, Date endDate) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("班级之星获取失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("班级之星获取失败");
		}
		if(teamId == null){
			log.error("班级Id不能为空");
			throw new IllegalArgumentException("班级之星获取失败");
		}
		if(startDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("班级之星获取失败");
		}

		List<StarEvaluateData> dataList = null;
		try {
			ApsTask task = getApsTask(termCode);

			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setSchoolYear(schoolYear);
			List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);

			if(teamStudentList != null && teamStudentList.size()>0){
				List<StarEvaluateData> starDataList = getTeamStudentDate(teamStudentList, task.getId(), startDate, endDate);

				//对集合进行排序
				sortStarData(starDataList);
				//获取获奖人数
				Team team = teamService.findTeamById(teamId);
				ApsMedal medal = getStarReachCount(team.getGradeId(),ApsTaskContants.STAR_OF_TEAM);

				dataList = new ArrayList<StarEvaluateData>();
				for(int i=0;i<medal.getReachCount();i++){
					StarEvaluateData data = starDataList.get(i);
					//将符合获奖人数的前n名取出返回
					dataList.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	/**
	 * 获取月度 的年级之星
	 */
	@Override
	public List<StarEvaluateData> getMonthlyGradeStar(String schoolYear, String termCode, Integer gradeId,
			Date startDate, Date endDate) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("年级之星获取失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("年级之星获取失败");
		}
		if(gradeId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("年级之星获取失败");
		}
		if(startDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("年级之星获取失败");
		}

		List<StarEvaluateData> dataList = null;
		try {
			ApsTask task = getApsTask(termCode);
			List<Team> teamList = teamService.findTeamOfGrade(gradeId);

			List<StarEvaluateData> starDataList = new ArrayList<StarEvaluateData>();
			if(teamList != null && teamList.size()>0){
				for(Team team : teamList){
					//遍历每个班，统计总卡数

					TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
					teamStudentCondition.setTeamId(team.getId());
					teamStudentCondition.setSchoolYear(schoolYear);
					List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);

					List<StarEvaluateData> starDatas = null;
					if(teamStudentList != null && teamStudentList.size()>0){
						starDatas = getTeamStudentDate(teamStudentList, task.getId(), startDate, endDate);
						starDataList.addAll(starDatas);
					}
				}
			}
			if(starDataList != null && starDataList.size()>0){
				sortStarData(starDataList);

				ApsMedal medal = getStarReachCount(gradeId, ApsTaskContants.STAR_OF_GRADE);

				dataList = new ArrayList<StarEvaluateData>();
				for(int i=0;i<medal.getReachCount();i++){
					StarEvaluateData data = starDataList.get(i);
					dataList.add(data);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	/**
	 * 获取月度 的全校之星
	 */
	@Override
	public List<StarEvaluateData> getMonthlySchoolStar(String schoolYear, String termCode, Integer schoolId,
			Date startDate, Date endDate) {
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("全校之星获取失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("全校之星获取失败");
		}
		if(schoolId == null){
			log.error("年级Id不能为空");
			throw new IllegalArgumentException("全校之星获取失败");
		}
		if(startDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("全校之星获取失败");
		}

		List<StarEvaluateData> dataList = null;
		try {
			ApsTask task = getApsTask(termCode);

			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setSchoolId(schoolId);
			teamStudentCondition.setSchoolYear(schoolYear);
			List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);

			//获取所有学生的数据
			if(teamStudentList != null && teamStudentList.size()>0){
				List<StarEvaluateData> starDataList = getTeamStudentDate(teamStudentList, task.getId(), startDate, endDate);
				sortStarData(starDataList);

				ApsMedal medal = apsMedalDao.findMedalsOfStudent(schoolId, "",
						ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
				dataList = new ArrayList<StarEvaluateData>();
				for(int i=0;i<medal.getReachCount();i++){
					StarEvaluateData data = starDataList.get(i);
					dataList.add(data);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	/**
	 * 获取星级个人（未评定）
	 */
	@Override
	public List<ApsStuSummary> getPersonalStar(String schoolYear, String termCode, Integer objectId, String type, Date startDate, Date endDate){

		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(startDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(objectId == null){
			log.error("Id不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(type == null || "".equals(type)){
			log.error("星级类型不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}

		List<ApsStuSummary> dataList = null;
		ApsMedal medal = null;
		List<StarEvaluateData> starDataList  = null;
		try {
			dataList = new ArrayList<ApsStuSummary>();
			ApsTask task = getApsTask(termCode);

//			if ("team".equals(type)) {
//				Team team = teamService.findTeamById(objectId);
//				starDataList = studentApsDao.findPersonalStarScore(task.getId(), task.getSchoolId(), schoolYear, team.getGradeId(), objectId, null, startDate, endDate);
//				medal = getStarReachCount(team.getGradeId(),ApsTaskContants.STAR_OF_TEAM);
//			}
//			if ("grade".equals(type)) {
//				starDataList = studentApsDao.findPersonalStarScore(task.getId(), task.getSchoolId(), schoolYear,  objectId, null, null, startDate, endDate);
//				medal = getStarReachCount(objectId, ApsTaskContants.STAR_OF_GRADE);
//			}
//			if ("school".equals(type)) {
//				starDataList = studentApsDao.findPersonalStarScore(task.getId(), task.getSchoolId(), schoolYear,  null, null, null, startDate, endDate);
//				medal = apsMedalDao.findMedalsOfStudent(objectId, "", ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
//			}

			if ("team".equals(type)) {
				Team team = teamService.findTeamById(objectId);
				List<TeamStudentVo> teamStudentList = teamStudentService.findTeamStudentVo(team.getSchoolId(), team.getSchoolYear(), team.getGradeId(), team.getId());
                List<StarEvaluateData> evaluateData = studentApsDao.findPersonalStarScore(task.getId(), task.getSchoolId(), schoolYear, team.getGradeId(), objectId, null, startDate, endDate);
                starDataList = getScoreToPersonStar(teamStudentList, evaluateData);
                medal = getStarReachCount(team.getGradeId(),ApsTaskContants.STAR_OF_TEAM);
            }
            if ("grade".equals(type)) {
                Grade grade = gradeService.findGradeById(objectId);
                List<TeamStudentVo> teamStudentList = teamStudentService.findTeamStudentVo(grade.getSchoolId(), grade.getSchoolYear(), grade.getId(), null);
                List<StarEvaluateData> evaluateData = studentApsDao.findPersonalStarScore(task.getId(), task.getSchoolId(), schoolYear, grade.getId(), null, null, startDate, endDate);
                starDataList = getScoreToPersonStar(teamStudentList, evaluateData);
                medal = getStarReachCount(objectId, ApsTaskContants.STAR_OF_GRADE);
            }
            if ("school".equals(type)) {
                List<TeamStudentVo> teamStudentList = teamStudentService.findTeamStudentVo(task.getSchoolId(), task.getSchoolYear(), null, null);
                List<StarEvaluateData> evaluateData = studentApsDao.findPersonalStarScore(task.getId(), task.getSchoolId(), schoolYear,  null, null, null, startDate, endDate);
                starDataList = getScoreToPersonStar(teamStudentList, evaluateData);
                medal = apsMedalDao.findMedalsOfStudent(objectId, "", ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
            }
//			if("team".equals(type)){
//				//获取所有学生列表
//				TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
//				teamStudentCondition.setTeamId(objectId);
//				teamStudentCondition.setSchoolYear(schoolYear);
//				List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);
//
//				//获取所有学生分数数据
//				if(teamStudentList != null && teamStudentList.size()>0){
//					starDataList = getTeamStudentDate(teamStudentList, task.getId(), startDate, endDate);
//				}
//				//获取获奖人数
//				Team team = teamService.findTeamById(objectId);
//				medal = getStarReachCount(team.getGradeId(),ApsTaskContants.STAR_OF_TEAM);
//			}
//
//			if("grade".equals(type)){
//				List<Team> teamList = teamService.findTeamOfGrade(objectId);
//				starDataList = new ArrayList<StarEvaluateData>();
//				if(teamList != null && teamList.size()>0){
//					for(Team team : teamList){
//						//遍历每个班，统计总卡数
//
//						TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
//						teamStudentCondition.setTeamId(team.getId());
//						teamStudentCondition.setSchoolYear(schoolYear);
//						List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);
//
//						List<StarEvaluateData> starDatas = null;
//						if(teamStudentList != null && teamStudentList.size()>0){
//							starDatas = getTeamStudentDate(teamStudentList, task.getId(), startDate, endDate);
//							starDataList.addAll(starDatas);
//						}
//					}
//				}
//				medal = getStarReachCount(objectId, ApsTaskContants.STAR_OF_GRADE);
//			}
//
//			if("school".equals(type)){
//				TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
//				teamStudentCondition.setSchoolId(objectId);
//				teamStudentCondition.setSchoolYear(schoolYear);
//				List<TeamStudent> teamStudentList = teamStudentService.findCurrentTeamStudentByCondition(teamStudentCondition, null, null);
//
//				//获取所有学生的数据
//				if(teamStudentList != null && teamStudentList.size()>0){
//					starDataList = getTeamStudentDate(teamStudentList, task.getId(), startDate, endDate);
//				}
//				medal = apsMedalDao.findMedalsOfStudent(objectId, "",
//						ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
//			}

			if(starDataList != null && starDataList.size()>0){

				//对集合进行排序
				sortStarData(starDataList);
				for(int i=0; i<starDataList.size(); i++){
					StarEvaluateData data = starDataList.get(i);
					//排名小于名次时存入，大于名次时结束
					if(data.getRank() <= medal.getReachCount()){
						ApsStuSummary summary = new ApsStuSummary();
						summary.setStudentId(data.getStudentId());
						summary.setStudentName(data.getStudentName());
						summary.setTeamId(data.getTeamId());
						summary.setTeamName(data.getTeamName());
						summary.setAddScore(data.getAddScore());
						summary.setNormalScore(data.getNormalScore());
						summary.setTotalScore(data.getTotalScore());
						summary.setRank(data.getRank());
						dataList.add(summary);
					}else{
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}


	private List<StarEvaluateData> getScoreToPersonStar(List<TeamStudentVo> teamStudentList, List<StarEvaluateData> dataList){
        List<StarEvaluateData> list = new ArrayList<>();
        StarEvaluateData data = null;
        if (teamStudentList != null && teamStudentList.size() > 0) {
            for (TeamStudentVo vo : teamStudentList) {
                boolean hasScore = false;
                if (dataList != null && dataList.size() > 0) {
                    for (StarEvaluateData starEvaluateData : dataList) {
                        if (vo.getStudentId().equals(starEvaluateData.getStudentId())) {
                            list.add(starEvaluateData);
                            hasScore = true;
                            break;
                        }
                    }
                }

                if (!hasScore) {
                    data = new StarEvaluateData();
                    data.setStudentName(vo.getName());
                    data.setStudentId(vo.getStudentId());
                    data.setTeamId(vo.getTeamId());
                    data.setTeamName(vo.getTeamName());
                    data.setAddScore(0);
                    data.setNormalScore(0);
                    data.setTotalScore(0);
                    data.setTeamCode(vo.getTeamCode());
                    list.add(data);
                }

            }
        }

        return list;
    }

	/**
	 * 获取星级个人（评定后）
	 * @return
	 */
	@Override
	public List<ApsStuSummary> getEvaluateStar(String termCode, Integer objectId, String type,
			Date startDate, Date endDate, String periodCode){
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(startDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(objectId == null){
			log.error("Id不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(type == null || "".equals(type)){
			log.error("星级类型不能为空");
			throw new IllegalArgumentException("星级个人获取失败");
		}
		if(periodCode == null || "".equals(periodCode)){
			log.error("评比阶段不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}

		List<ApsStuSummary> summaryDatas = null;
		try {

			ApsMedal medal = null;
			Integer schoolId = null;
			Integer gradeId = null;
			Integer teamId = null;
			if("team".equals(type)){
				teamId = objectId;
				Team team = teamService.findTeamById(objectId);
				medal = getStarReachCount(team.getGradeId(),ApsTaskContants.STAR_OF_TEAM);
			}
			if("grade".equals(type)){
				gradeId = objectId;
				medal = getStarReachCount(objectId, ApsTaskContants.STAR_OF_GRADE);
			}
			if("school".equals(type)){
				schoolId = objectId;
				medal = apsMedalDao.findMedalsOfStudent(objectId, "",ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
			}

			ApsTask task = getApsTask(termCode);

			ApsStuSummary summaryVo = new ApsStuSummary();
			summaryVo.setTaskId(task.getId());
			summaryVo.setMedalId(medal.getId());
			summaryVo.setTermCode(termCode);
			summaryVo.setPeriodCode(periodCode);
			summaryVo.setSchoolId(schoolId);
			summaryVo.setGradeId(gradeId);
			summaryVo.setTeamId(teamId);
			summaryDatas= apsStuSummaryDao.findStuSummaryOfStar(summaryVo, null, null);


		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDatas;
	}

	/**
	 * 评定星级个人
	 */
	@Override
	public List<ApsStuSummary> evaluatePersonalStar(String schoolYear, String termCode, Integer objectId, String type,
			Date startDate, Date endDate,String periodCode){
		if(schoolYear == null || "".equals(schoolYear)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}
		if(startDate == null || endDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}
		if(periodCode == null || "".equals(periodCode)){
			log.error("评比阶段不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}
		if(objectId == null){
			log.error("Id不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}
		if(type == null || "".equals(type)){
			log.error("星级类型不能为空");
			throw new IllegalArgumentException("星级个人评定失败");
		}


		//List<StarEvaluateData> datas = null;
		List<ApsStuSummary> dataList = null;
		List<ApsStuSummary> summaryDates = null;
		try {
			ApsMedal medal = null;
			Integer schoolId = null;
			Integer gradeId = null;
			Integer teamId = null;
			if("team".equals(type)){
				teamId = objectId;
				Team team = teamService.findTeamById(objectId);
				medal = getStarReachCount(team.getGradeId(),ApsTaskContants.STAR_OF_TEAM);
				schoolId = team.getSchoolId();
				gradeId = team.getGradeId();
				//datas = getMonthlyTeamStar(schoolYear, termCode, objectId, startDate, endDate);
			}
			if("grade".equals(type)){
				gradeId = objectId;
				Grade grade = gradeService.findGradeById(gradeId);
				medal = getStarReachCount(objectId, ApsTaskContants.STAR_OF_GRADE);
				schoolId = grade.getSchoolId();
				//datas = getMonthlyGradeStar(schoolYear, termCode, objectId, startDate, endDate);
			}
			if("school".equals(type)){
				schoolId = objectId;
				medal = apsMedalDao.findMedalsOfStudent(objectId, "",ApsTaskContants.STAR_OF_SCHOOL, ApsTaskContants.OBJECT_STUDENT);
				//datas = getMonthlySchoolStar(schoolYear, termCode, objectId, startDate, endDate);
			}
			//获取未评定前的数据
			dataList = getPersonalStar(schoolYear, termCode, objectId, type, startDate, endDate);

			ApsTask task = getApsTask(termCode);
			if(dataList != null && dataList.size()>0){
				//找到stuSummary表中 原有数据并删除
				ApsStuSummary summaryVo = new ApsStuSummary();
				summaryVo.setTaskId(task.getId());
				summaryVo.setMedalId(medal.getId());
				summaryVo.setTermCode(termCode);
				summaryVo.setPeriodCode(periodCode);
				summaryVo.setSchoolId(schoolId);
				summaryVo.setGradeId(gradeId);
				summaryVo.setTeamId(teamId);
				List<ApsStuSummary> stuSummaryList = apsStuSummaryDao.findStuSummaryOfStar(summaryVo, null, null);
				if(stuSummaryList != null && stuSummaryList.size()>0){
					for(ApsStuSummary stu : stuSummaryList){
						apsStuSummaryDao.delete(stu);
					}
				}

				summaryDates = new ArrayList<ApsStuSummary>();
				for(ApsStuSummary data : dataList){
					Team team = teamService.findTeamById(data.getTeamId());
					//将数据存入summary表中
					ApsStuSummary summary = new ApsStuSummary();
					summary.setTaskId(task.getId());
					summary.setMedalId(medal.getId());
					summary.setSchoolId(schoolId);
					summary.setTermCode(termCode);
					if("school".equals(type)){
						summary.setGradeId(team.getGradeId());
					}else{
						summary.setGradeId(gradeId);
					}
					summary.setTeamId(data.getTeamId());
					summary.setTeamName(data.getTeamName());
					summary.setStudentId(data.getStudentId());
					summary.setStudentName(data.getStudentName());
					summary.setPeriodCode(periodCode);
					summary.setRank(data.getRank());
					summary.setTotalScore(data.getTotalScore());
					summary.setAddScore(data.getAddScore());
					summary.setNormalScore(data.getNormalScore());
					summary.setDeductScore(0);
					summary.setRemark("");
					summary.setIsDeleted(false);
					summary.setCreateDate(new Date());
					apsStuSummaryDao.create(summary);
					summaryDates.add(summary);
				}

			}

//			if(datas != null && datas.size()>0){
//				//找到stuSummary表中数据
//				ApsStuSummary summaryVo = new ApsStuSummary();
//				summaryVo.setTaskId(task.getId());
//				summaryVo.setMedalId(medal.getId());
//				summaryVo.setTermCode(termCode);
//				summaryVo.setPeriodCode(periodCode);
//				summaryVo.setSchoolId(schoolId);
//				summaryVo.setGradeId(gradeId);
//				summaryVo.setTeamId(teamId);
//				List<ApsStuSummary> stuSummaryList = apsStuSummaryDao.findStuSummaryOfStar(summaryVo, null, null);
//				if(stuSummaryList != null && stuSummaryList.size()>0){
//					for(ApsStuSummary stu : stuSummaryList){
//						apsStuSummaryDao.delete(stu);
//					}
//				}
//
//				summaryDates = new ArrayList<ApsStuSummary>();
//				for(StarEvaluateData data : datas){
//
//					Team team = teamService.findTeamById(data.getTeamId());
//					//将数据存入summary表中
//					ApsStuSummary summary = new ApsStuSummary();
//					summary.setTaskId(task.getId());
//					summary.setMedalId(medal.getId());
//					summary.setSchoolId(schoolId);
//					summary.setTermCode(termCode);
//					if("school".equals(type)){
//						summary.setGradeId(team.getGradeId());
//					}else{
//						summary.setGradeId(gradeId);
//					}
//					summary.setTeamId(data.getTeamId());
//					summary.setTeamName(data.getTeamName());
//					summary.setStudentId(data.getStudentId());
//					summary.setStudentName(data.getStudentName());
//					summary.setPeriodCode(periodCode);
//					summary.setRank(data.getRank());
//					summary.setTotalScore(data.getTotalScore());
//					summary.setAddScore(data.getAddScore());
//					summary.setNormalScore(data.getNormalScore());
//					summary.setDeductScore(0);
//					summary.setRemark("");
//					summary.setIsDeleted(false);
//					summary.setCreateDate(new Date());
//					apsStuSummaryDao.create(summary);
//					summaryDates.add(summary);
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return summaryDates;
	}

	@Override
	public Boolean deleteStudentItemDatas(String termCode, Integer studentId,
			String checkType, Date checkDate, String checkRange){
		if(termCode == null || "".equals(termCode)){
			log.error("学期代码不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(studentId == null){
			log.error("学生Id不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(checkType == null || "".equals(checkType)){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		if(checkDate == null){
			log.error("考核日期不能为空");
			throw new IllegalArgumentException("删除学生列表失败");
		}
		Boolean flag = false;
		try{
			ApsTask task = getApsTask(termCode);
			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(task.getId());
			taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
			taskScore.setObjectId(studentId);
			taskScore.setCheckType(checkType);
			taskScore.setCheckDate(checkDate);
			taskScore.setIsDeleted(false);
			if(ApsTaskContants.CHECK_TYPE_MINUS.equals(checkType) && checkRange !=null && !"".equals(checkRange)){
				taskScore.setCheckRange(checkRange);
			}
			List<ApsTaskScore> list = apsTaskScoreDao.findApsTaskScoreByCondition(taskScore);
			if(list != null && list.size() > 0){
				for(ApsTaskScore score : list){
					apsTaskScoreDao.delete(score);
				}
			}
			flag = true;

		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<ApsTaskScore> findApsTaskScoreByCondition(ApsTaskScore apsTaskScore) {
		List<ApsTaskScore> list = apsTaskScoreDao.findApsTaskScoreByCondition(apsTaskScore);
		return list;
	}


}
