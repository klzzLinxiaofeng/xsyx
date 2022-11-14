package platform.education.generalTeachingAffair.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;













import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.aspectj.weaver.loadtime.definition.Definition.ConcreteAspect;
import org.omg.CORBA.OMGVMCID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.PjAptsTask;
import platform.education.generalTeachingAffair.model.PjAptsTaskItem;
import platform.education.generalTeachingAffair.model.PjAptsTaskItemSummary;
import platform.education.generalTeachingAffair.model.PjAptsTaskJudge;
import platform.education.generalTeachingAffair.model.PjAptsTaskScore;
import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.utils.ArabicToChineseUtils;
import platform.education.generalTeachingAffair.utils.DateUtil;
import platform.education.generalTeachingAffair.vo.AptsTeamStuentCount;
import platform.education.generalTeachingAffair.vo.AssessmentItemVo;
import platform.education.generalTeachingAffair.vo.AssessmentScoreVo;
import platform.education.generalTeachingAffair.vo.PjAptsTaskCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskItemSummaryCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskScoreCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserCondition;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserVo;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherScoreVo;
import platform.education.generalTeachingAffair.service.PjAptsTaskItemService;
import platform.education.generalTeachingAffair.service.PjAptsTaskItemSummaryService;
import platform.education.generalTeachingAffair.service.PjAptsTaskJudgeService;
import platform.education.generalTeachingAffair.service.PjAptsTaskScoreService;
import platform.education.generalTeachingAffair.service.PjAptsTaskService;
import platform.education.generalTeachingAffair.service.PjAptsTaskUserService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.contants.AssessmentCantants;
import platform.education.generalTeachingAffair.dao.PjAptsTaskDao;
import platform.education.user.service.UserService;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjAptsTaskServiceImpl implements PjAptsTaskService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private PjAptsTaskDao pjAptsTaskDao;
	@Resource
	private PjAptsTaskItemService pjAptsTaskItemService;
	@Resource
	private StudentService studentService;
	@Resource
	private TeamService teamService;
	@Resource
	private TeamTeacherService teamTeacherService;
	@Resource
	private UserService userService;
	@Resource
	private PjAptsTaskUserService pjAptsTaskUserService;
	@Resource
	private PjAptsTaskScoreService pjAptsTaskScoreService;
	@Resource
	private TeamStudentService teamStudentService;
	@Resource
	private PjAptsTaskJudgeService pjAptsTaskJudgeService;
	@Resource
	private SchoolTermService  schoolTermService;
	@Resource
	private PjAptsTaskItemSummaryService pjAptsTaskItemSummaryService;

	public void setPjAptsTaskDao(PjAptsTaskDao pjAptsTaskDao) {
		this.pjAptsTaskDao = pjAptsTaskDao;
	}

	@Override
	public PjAptsTask findPjAptsTaskById(Integer id) {
		try {
			return pjAptsTaskDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public PjAptsTask add(PjAptsTask pjAptsTask) {
		if (pjAptsTask == null) {
			return null;
		}
		Date createDate = pjAptsTask.getCreateDate();
		if (createDate == null) {
			createDate = new Date();
		}
		pjAptsTask.setCreateDate(createDate);
		pjAptsTask.setModifyDate(createDate);
		return pjAptsTaskDao.create(pjAptsTask);
	}

	@Override
	public PjAptsTask modify(PjAptsTask pjAptsTask) {
		if (pjAptsTask == null) {
			return null;
		}
		Date modify = pjAptsTask.getModifyDate();
		pjAptsTask.setModifyDate(modify != null ? modify : new Date());
		return pjAptsTaskDao.update(pjAptsTask);
	}

	@Override
	public void remove(PjAptsTask pjAptsTask) {
		try {
			pjAptsTaskDao.delete(pjAptsTask);
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjAptsTask.getId(), e);
			}
		}
	}

	@Override
	public List<PjAptsTask> findPjAptsTaskByCondition(
			PjAptsTaskCondition pjAptsTaskCondition, Page page, Order order) {
		return pjAptsTaskDao.findPjAptsTaskByCondition(pjAptsTaskCondition,
				page, order);
	}

	@Override
	public List<PjAptsTask> findPjAptsTaskByCondition(
			PjAptsTaskCondition pjAptsTaskCondition) {
		return pjAptsTaskDao.findPjAptsTaskByCondition(pjAptsTaskCondition,
				null, null);
	}

	@Override
	public List<PjAptsTask> findPjAptsTaskByCondition(
			PjAptsTaskCondition pjAptsTaskCondition, Page page) {
		return pjAptsTaskDao.findPjAptsTaskByCondition(pjAptsTaskCondition,
				page, null);
	}

	@Override
	public List<PjAptsTask> findPjAptsTaskByCondition(
			PjAptsTaskCondition pjAptsTaskCondition, Order order) {
		return pjAptsTaskDao.findPjAptsTaskByCondition(pjAptsTaskCondition,
				null, order);
	}

	@Override
	public Long count() {
		return this.pjAptsTaskDao.count(null);
	}

	@Override
	public Long count(PjAptsTaskCondition pjAptsTaskCondition) {
		return this.pjAptsTaskDao.count(pjAptsTaskCondition);
	}

	@Override
	public PjAptsTask addPjAptsTaskRelete(PjAptsTask pjAptsTask,
			List<String> items) {

		if (items.size() == 0) {
			return null;
		}
		PjAptsTask pat = pjAptsTaskDao.create(pjAptsTask);
		PjAptsTaskItem[] piList = new PjAptsTaskItem[items.size()];
		int i = 0;
		for (String str : items) {
			PjAptsTaskItem obj = new PjAptsTaskItem();
			obj.setAptsTaskId(pat.getId());
			obj.setListOrder(i);
			obj.setFullScore(10);
			obj.setName(str);
			piList[i] = obj;
			i++;
		}
		pjAptsTaskItemService.createBatch(piList);
		return pat;
	}


	@Override
	public List<Map<String,Object>> findTodayAssessment(Integer userId)  {
		try {
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			
			Student student = studentService.findStudentByUserId(userId);
			SchoolTerm	schoolTerm=schoolTermService.findSchoolYearByToday(student.getSchoolId(), date);
			TeamStudentCondition teamStudentCondition=new TeamStudentCondition();
//			teamStudentCondition.setSchoolYear(schoolTerm.getSchoolYear());
			teamStudentCondition.setUserId(userId);
			teamStudentCondition.setIsDelete(false);
			teamStudentCondition.setInState(true);
			List<TeamStudent> tsuList=teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
			Team team=new Team();
			for(TeamStudent ts:tsuList){
				Team team1 = teamService.findTeamById(ts.getTeamId());
				if(team1.getSchoolYear().equals(schoolTerm.getSchoolYear())){
					team=team1;
					break;
				}
			}
			if(team.getId()==null){
				return new ArrayList<Map<String,Object>>();
			}
//			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
//					.findSchoolTermCurrentBySchoolId(student.getSchoolId());
			PjAptsTaskCondition sb = new PjAptsTaskCondition();
			sb.setSchoolId(student.getSchoolId());
//			sb.setTermCode(schoolTermCurrent.getSchoolTermCode());
			sb.setEvType(AssessmentCantants.SUBJECT);
			List<PjAptsTask> sblist = pjAptsTaskDao.findPjAptsTaskByCondition(sb,
					null, null);
			if(sblist.size()==0){
				sblist.add(initDefaultTask(student.getSchoolId(), AssessmentCantants.SUBJECT));
			}
			PjAptsTaskCondition mzr = new PjAptsTaskCondition();
			mzr.setSchoolId(student.getSchoolId());
//			mzr.setTermCode(schoolTermCurrent.getSchoolTermCode());
			mzr.setEvType(AssessmentCantants.MASTER);
			List<PjAptsTask> mzrlist = pjAptsTaskDao.findPjAptsTaskByCondition(mzr,
					null, null);
			if(mzrlist.size()==0){
				mzrlist.add(initDefaultTask(student.getSchoolId(), AssessmentCantants.MASTER));
			}
			List<TeamStudent> tsList=teamStudentService.findByTeamId(team.getId());
			TeamTeacherCondition ttCondition = new TeamTeacherCondition();
			ttCondition.setTeamId(team.getId());
			ttCondition.setDelete(false);
			Order o = new Order();
			o.setProperty("type");
			o.setAscending(true);
			List<TeamTeacher> ttlist = teamTeacherService
					.findTeamTeacherByCondition(ttCondition, null, o);
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			List<Integer>subjectAssessmentIds=new ArrayList<Integer>();
			List<Integer>masterAssessmentIds=new ArrayList<Integer>();
			List<String>subjectItem=new ArrayList<String>();
			List<String>masterItem=new ArrayList<String>();
			for (TeamTeacher tt : ttlist) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (tt.getType() == AssessmentCantants.MASTER
						&& mzrlist.size() != 0) {
					PjAptsTask pt=mzrlist.get(0);
					Map<String, Object> timeMap;
					timeMap = DateUtil.getWeekMonAndSun();
					Integer isAssessment = 0;
					Date  start=(Date)timeMap.get("monday");
					Date finish=(Date)timeMap.get("sunday");
					PjAptsTaskUserCondition condition=new PjAptsTaskUserCondition();
					
					condition.setAptsTaskId(mzrlist.get(0).getId());
					condition.setStartDate(start);
					condition.setTeacherId(tt.getId());
					List<PjAptsTaskUser> ptuList=pjAptsTaskUserService.findPjAptsTaskUserByCondition(condition);
					Integer AssessmentId=0;
					if(ptuList.size()==0){
						PjAptsTaskUser patu=new PjAptsTaskUser();
						patu.setAptsTaskId(mzrlist.get(0).getId());
						patu.setCreateDate(date);
						patu.setModifyDate(date);
						patu.setStartDate(start);
						patu.setFinishDate(finish);
						patu.setGradeId(tt.getGradeId());
						patu.setSchoolId(student.getSchoolId());
						patu.setSchoolYear(schoolTerm.getSchoolYear());
						patu.setScore(0.0d);
						patu.setTeacherId(tt.getId());
						patu.setTeamId(tt.getTeamId());
						patu.setTotalUserCount(tsList.size());
						patu.setTermCode(schoolTerm.getCode());
						patu.setTotalUserCount(tsList.size());
	                    patu.setScoredUserCount(0);
	                    patu.setIsValid(false);
	                    patu.setEvType(pt.getEvType());
	                    patu.setScoringType(pt.getScoringType());
	                    patu.setValidPercent(pt.getValidPercent());
	                    patu.setPeriod(pt.getPeriod());
	                    pjAptsTaskUserService.add(patu);
	                    AssessmentId=patu.getId();
	                    masterAssessmentIds.add(AssessmentId);
					}else{
						AssessmentId=ptuList.get(0).getId();
					}
					List<AssessmentScoreVo> volist= pjAptsTaskScoreService.findAssessmentCount(
							AssessmentId,student.getId());
					if (volist.size() != 0) {
						isAssessment = 1;
					}

					
					map.put("name", tt.getName());
					map.put("type", tt.getType());
					map.put("subjectName",
							tt.getSubjectName() == null ? "" : tt.getSubjectName());
					map.put("isAssessment", isAssessment);
					map.put("period", pt.getPeriod());
					map.put("userId", tt.getUserId());
					map.put("assessmentId", AssessmentId);
					list.add(map);
				}
				if (tt.getType() == AssessmentCantants.SUBJECT
						&& sblist.size() != 0) {
					PjAptsTask pt=sblist.get(0);
					String todayStr = sf1.format(date)+" 00:00:00";
					String todayStrAfter=sf1.format(date)+" 23:59:00";
					PjAptsTaskUserCondition condition=new PjAptsTaskUserCondition();
					condition.setAptsTaskId(sblist.get(0).getId());
					condition.setStartDate(sf.parse(todayStr));
					condition.setTeacherId(tt.getId());
					List<PjAptsTaskUser> ptuList=pjAptsTaskUserService.findPjAptsTaskUserByCondition(condition);
					Integer AssessmentId=0;
					if(ptuList.size()==0){
						PjAptsTaskUser patu=new PjAptsTaskUser();
						patu.setAptsTaskId(sblist.get(0).getId());
						patu.setCreateDate(date);
						patu.setModifyDate(date);
						patu.setStartDate(sf.parse(todayStr));
						patu.setFinishDate(sf.parse(todayStrAfter));
						patu.setGradeId(tt.getGradeId());
						patu.setSchoolId(student.getSchoolId());
						patu.setSchoolYear(schoolTerm.getSchoolYear());
						patu.setScore(0.0d);
						patu.setTeacherId(tt.getId());
						patu.setTeamId(tt.getTeamId());
						patu.setTermCode(schoolTerm.getCode());
						patu.setTotalUserCount(tsList.size());
	                    patu.setScoredUserCount(0);
	                    patu.setIsValid(false);
	                    patu.setEvType(pt.getEvType());
	                    patu.setScoringType(pt.getScoringType());
	                    patu.setValidPercent(pt.getValidPercent());
	                    patu.setPeriod(pt.getPeriod());
	                    pjAptsTaskUserService.add(patu);
	                    AssessmentId=patu.getId();
	                    subjectAssessmentIds.add(AssessmentId);
					}else{
						AssessmentId=ptuList.get(0).getId();
					}
					
					Integer isAssessment = 0;
					List<AssessmentScoreVo> volist= pjAptsTaskScoreService.findAssessmentCount(
							AssessmentId,student.getId());
					if (volist.size() != 0) {
						isAssessment = 1;
					}
					map.put("name", tt.getName());
					map.put("type", tt.getType());
					map.put("subjectName",
							tt.getSubjectName() == null ? "" : tt.getSubjectName());
					map.put("period", pt.getPeriod());
					map.put("userId", tt.getUserId());
					map.put("isAssessment", isAssessment);
					map.put("assessmentId", AssessmentId);
					list.add(map);
				}
			}
			List<PjAptsTaskItem> pilist=new ArrayList<PjAptsTaskItem>();
			List<PjAptsTaskItem> pilist1=new ArrayList<PjAptsTaskItem>();
			if(mzrlist.size()!=0){
				PjAptsTaskItemCondition piCondition=new PjAptsTaskItemCondition();
				piCondition.setAptsTaskId(mzrlist.get(0).getId());
				piCondition.setIsDelete(false);
				 pilist=pjAptsTaskItemService.findPjAptsTaskItemByCondition(piCondition);
				
			}
			if(sblist.size()!=0){
				PjAptsTaskItemCondition piCondition=new PjAptsTaskItemCondition();
				piCondition.setAptsTaskId(sblist.get(0).getId());
				piCondition.setIsDelete(false);
				 pilist1=pjAptsTaskItemService.findPjAptsTaskItemByCondition(piCondition);
			}
			if(subjectAssessmentIds.size()!=0){
				initScoreSummary(subjectAssessmentIds, pilist1);
			}
			if(masterAssessmentIds.size()!=0){
				initScoreSummary(masterAssessmentIds, pilist);
			}
			return list;
		} catch (Exception e) {
		  return new ArrayList<Map<String,Object>>();
		}
	}

	@Override
	public Map<String, Object> findAssessmentBoard(Integer userId,
			Integer AssessmentId) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Student student = studentService.findStudentByUserId(userId);
		Map<String,Object> map=new HashMap<String, Object>();
		List<AssessmentScoreVo> volist= pjAptsTaskScoreService.findAssessmentCount(
				AssessmentId,student.getId());
		PjAptsTaskUser tu=pjAptsTaskUserService.findPjAptsTaskUserById(AssessmentId);
//		TeamTeacher tt=teamTeacherService.findTeamTeacherById(tu.getTeacherId());
		TeamTeacherCondition ttcondition=new TeamTeacherCondition();
		ttcondition.setId(tu.getTeacherId());
		List<TeamTeacher> ttlist=teamTeacherService.findTeamTeacherByConditionIncludeDelete(ttcondition, null, null);
		String name="";
		Integer type=1;
		String subjectName="";
		Integer ttUserId=1;
		if(ttlist!=null){
			TeamTeacher  tt=ttlist.get(0);
			subjectName=tt.getSubjectName();
			type=tt.getType();
			name=tt.getName();
			ttUserId=tt.getUserId();
		}
		PjAptsTaskItemSummaryCondition condition=new PjAptsTaskItemSummaryCondition();
		condition.setAptsTaskUserId(AssessmentId);
		List<PjAptsTaskItemSummary>  ptList=pjAptsTaskItemSummaryService.findPjAptsTaskItemSummaryByCondition(condition);
		Team t=teamService.findTeamById(tu.getTeamId());
		List<Map<String,Object>>itemLIst=new ArrayList<Map<String,Object>>();
		Integer judgeId=0;
		String description="";
		int level=5;
		int number=2;
		
		if(tu.getScoringType()==11){
			 level=1;
			 number=10;
		}
		if(volist.size()==0){
			for(PjAptsTaskItemSummary item:ptList){
				Map<String,Object> itemMap=new HashMap<String, Object>();
				itemMap.put("itemId", item.getId());
				itemMap.put("name", item.getTaskItemName());
				itemMap.put("number", 0);
				itemMap.put("level",level );
				itemLIst.add(itemMap);
			}
		}else{
			for(AssessmentScoreVo item:volist){
				Map<String,Object> itemMap=new HashMap<String, Object>();
				itemMap.put("itemId", item.getItemId());
				itemMap.put("name", item.getItemName());
				itemMap.put("number", item.getScore()/number);
				itemMap.put("level",level );
				judgeId=item.getJudgeId();
				description=item.getDescription();
				itemLIst.add(itemMap);
			}
		}
		map.put("itemList", itemLIst);
		map.put("startDate",sf.format(tu.getStartDate()));
		map.put("finishDate", sf.format(tu.getFinishDate()));
		map.put("teacherName", name);
		map.put("type", type);
		map.put("subjectName", subjectName==null?"":subjectName);
		map.put("teamName", t==null?"未知":t.getName());
		map.put("scoringType", tu.getScoringType());
		map.put("period", tu.getPeriod());
		map.put("judgeId", judgeId);
		map.put("description", description);
		map.put("userId",ttUserId);
		return map;
	}

	@Override
	public String addAssessment(Integer AssessmentId, Integer userId,
			String description, List<AssessmentItemVo> scoreList) {
		Student student = studentService.findStudentByUserId(userId);
		PjAptsTaskUser  tu=pjAptsTaskUserService.findPjAptsTaskUserById(AssessmentId);
		List<AssessmentScoreVo> volist= pjAptsTaskScoreService.findAssessmentCount(
				AssessmentId,student.getId());
		int count=0;
		int myscore=0;
		Date date=new Date();
		int score=0;
		if(volist.size()==0){
			int j=0;
		       for(AssessmentItemVo vo:scoreList){
		    	   score+=vo.getScore();
		    	   j++;
		       }
		    score=score/j;
	       PjAptsTaskJudge pj=new PjAptsTaskJudge();
	       pj.setAptsTaskUserId(AssessmentId);
	       pj.setCheckTime(new Date());
	       pj.setDescription(description);
	       pj.setCreateDate(date);
	       pj.setCheckTime(date);
	       pj.setModifyDate(date);
	       pj.setMyScore(score);
	       pj.setJudgeId(student.getId());
	       pjAptsTaskJudgeService.add(pj);
	       PjAptsTaskScore[] pslist=new PjAptsTaskScore[scoreList.size()];
	       int i=0;
	       for(AssessmentItemVo vo:scoreList){
	    	   PjAptsTaskScore ps=new PjAptsTaskScore();
	    	   ps.setSummaryId(vo.getItemId());
	    	   ps.setAptsTaskJudgeId(pj.getId());
	    	   ps.setAptsTaskUserId(AssessmentId);
	    	   ps.setItemScore(vo.getScore());
	    	   ps.setCreateDate(date);
	    	   ps.setModifyDate(date);
	    	   pslist[i]=ps;
	    	   i++;
	       }
	       pjAptsTaskScoreService.createBatch(pslist);
	       count=1;
		}else{
			Map<Integer,Integer> iteamScoreMap=new HashMap<Integer, Integer>();
			int j=0;
			 for(AssessmentItemVo vo:scoreList){
				 iteamScoreMap.put(vo.getItemId(), vo.getScore());
				 score+=vo.getScore();
				 j++;
			 }
			Integer JudgeId=0;
			JudgeId=volist.get(0).getJudgeId();
			PjAptsTaskScoreCondition condition =new PjAptsTaskScoreCondition();
			condition.setAptsTaskJudgeId(JudgeId);
			condition.setAptsTaskUserId(AssessmentId);
			condition.setIsDelete(false);
			List<PjAptsTaskScore> pslist=new ArrayList<PjAptsTaskScore>();
			pslist=pjAptsTaskScoreService.findPjAptsTaskScoreByCondition(condition);
			if(pslist.size()!=0){
				for(PjAptsTaskScore ps:pslist){
					ps.setItemScore(iteamScoreMap.get(ps.getSummaryId()));
					pjAptsTaskScoreService.modify(ps);
				}
			}
			score=score/j;
			PjAptsTaskJudge pj=pjAptsTaskJudgeService.findPjAptsTaskJudgeById(JudgeId);
			myscore=pj.getMyScore();
			pj.setMyScore(score);
			pj.setDescription(description);
			pj.setCheckTime(date);
			pjAptsTaskJudgeService.modify(pj);
		}    
		   PjAptsTaskScoreCondition condition =new PjAptsTaskScoreCondition();
		   condition.setAptsTaskUserId(AssessmentId);
		   List<PjAptsTaskScore> pslist=new ArrayList<PjAptsTaskScore>();
			pslist=pjAptsTaskScoreService.findPjAptsTaskScoreByCondition(condition);
			
			Integer sumScore=0;
			for(PjAptsTaskScore pts:pslist){
				sumScore+=pts.getItemScore();
			}
		   Double d=0.0d;
		   if(pslist.size()!=0){
			   BigDecimal big4 =new BigDecimal(sumScore*1.0d);
			   BigDecimal big5=new BigDecimal(pslist.size()*1.0d);
			   d=big4.divide(big5,2,BigDecimal.ROUND_HALF_UP).doubleValue();
			   d=new BigDecimal(d).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		   }
		   tu.setScore(d);
	       tu.setScoredUserCount(tu.getScoredUserCount()+count);
	   	BigDecimal big2=new BigDecimal(tu.getScoredUserCount()+"");
		BigDecimal big3=new BigDecimal((tu.getTotalUserCount()==null?0:tu.getTotalUserCount())+"");
		double f=big2.divide(big3,2,0).doubleValue();
		if(f>=tu.getValidPercent()){
			tu.setIsValid(true);
		}
		pjAptsTaskUserService.modify(tu);
		return "success";
	}
    private void initScoreSummary(List<Integer> assessmentIds,List<PjAptsTaskItem>items){
    	Date date=new Date();
    	int size=assessmentIds.size()*items.size();
    	int i=0;
    	PjAptsTaskItemSummary[]  pjAptsTaskItemSummaryList=new PjAptsTaskItemSummary[size];
    	for(Integer AssessmentId:assessmentIds){
    		for(PjAptsTaskItem item:items){
    			PjAptsTaskItemSummary ps=new PjAptsTaskItemSummary();
    			ps.setAptsTaskUserId(AssessmentId);
    			ps.setCreateDate(date);
    			ps.setEffectiveScore(0);
    			ps.setIsDelete(false);
    			ps.setModifyDate(date);
    			ps.setScore(0);
    			ps.setTaskItemName(item.getName());
    			ps.setListOrder(item.getListOrder());
    			pjAptsTaskItemSummaryList[i]=ps;
    			i++;
    		}
    	}
    	pjAptsTaskItemSummaryService.createBatch(pjAptsTaskItemSummaryList);
    }

	@Override
	public void modifyTaskItem(List<String> items,List<Integer>listOrder, Integer taskId) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PjAptsTask pt=pjAptsTaskDao.findById(taskId);
		 PjAptsTaskItemCondition condition=new PjAptsTaskItemCondition();
		 condition.setAptsTaskId(taskId);
		 condition.setIsDelete(false);
		 List<PjAptsTaskItem>list=pjAptsTaskItemService.findPjAptsTaskItemByCondition(condition);
		 Date create=list.get(0).getEvDate();
		 Date date=new Date();
		 Date evDate=new Date();
		 if(pt.getEvType()==AssessmentCantants.MASTER){
			try {
				if(!date.after(create)){
					pjAptsTaskItemService.modifyIsDeleteByTaskId(taskId);
				}else{
					initAptsTaskUserOfToday(pt.getSchoolId(), pt.getEvType());
					pjAptsTaskItemService.modifyIsDeleteByTaskId(taskId);
				}
				evDate=(Date) DateUtil.getWeekMonAndSun().get("sunday");
			} catch (Exception e) {
			}
		 }else{
			 try {
				if(!date.after(create)){
					    pjAptsTaskItemService.modifyIsDeleteByTaskId(taskId);
				 }else{
						initAptsTaskUserOfToday(pt.getSchoolId(), pt.getEvType());
						pjAptsTaskItemService.modifyIsDeleteByTaskId(taskId);
				 }
				evDate=sdf1.parse(sdf.format(date)+" 23:59:59");
			} catch (Exception e) {
				
			}
		 }
		if(items.size()==listOrder.size()){
			try{
				for(int i=0;i<items.size();i++){
					PjAptsTaskItem pi=new PjAptsTaskItem();
					pi.setAptsTaskId(taskId);
					pi.setFullScore(10);
					pi.setListOrder(listOrder.get(i));
					pi.setName(items.get(i));
					pi.setIsDelete(false);
					pi.setCreateDate(date);
					pi.setEvDate(evDate);
					pjAptsTaskItemService.add(pi);
				}
					
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}


	@Override
	public LinkedHashMap<String,Object> findStatisticsByCondition(
			PjAptsTaskUserCondition condition,Date StartDate,Date FinishDate) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		Map<Integer,PjAptsTaskUser> map=DateUtil.fl(StartDate, FinishDate);
		Map<Integer,Map<String,Object>> teacherMap=new HashMap<Integer, Map<String,Object>>();
		List<List<TeamTeacherScoreVo>>xhList=new ArrayList<List<TeamTeacherScoreVo>>();
		for(Entry<Integer, PjAptsTaskUser> t:map.entrySet()){
			condition.setStartDate(t.getValue().getStartDate());
			condition.setFinishDate(t.getValue().getFinishDate());
//			condition.setStartDate(df.parse("2018-02-28"));
//			condition.setFinishDate(df.parse("2018-05-01"));
			List<TeamTeacherScoreVo> volist=pjAptsTaskDao.findStatisticsByCondition(condition);
			for(TeamTeacherScoreVo vo:volist){
				BigDecimal big=new BigDecimal(vo.getAvgScore()+"");
				Float f=big.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
//				f=new BigDecimal(f).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
				vo.setAvgScore(f);
			}
			float score=0;
			int  num=0;
			for(TeamTeacherScoreVo tts:volist){
				score+=tts.getAvgScore();
				num++;
				if(!teacherMap.containsKey(tts.getTeacherId())){
					Map<String,Object> userMap=new HashMap<String, Object>();
					userMap.put("teacherName", tts.getTeacherName());
					userMap.put("teamName", tts.getTeamName());
					teacherMap.put(tts.getTeacherId(), userMap);
				}
			}
			if(num!=0){
				TeamTeacherScoreVo vo=new TeamTeacherScoreVo();
				float f=0;
				vo.setAvgScore(score/num);
	    		BigDecimal big=new BigDecimal(score+"");
	    		BigDecimal big2=new BigDecimal(num*1.0+"");
	    		if(num!=0){
	    			f=big.divide(big2, 2,BigDecimal.ROUND_HALF_UP).floatValue();
	    			f=new BigDecimal(f+"").setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	    		}
	    		vo.setAvgScore(f);
				vo.setTeamName("");
				vo.setTeacherName("参考均分");
				volist.add(0, vo);
			}
			xhList.add(volist);
		}
		List<Map<String,Object>>yhList=new ArrayList<Map<String,Object>>();
		int size=0;
		for(List<TeamTeacherScoreVo>volist:xhList){
			Map<Integer,Float> fMap=new HashMap<Integer, Float>();
			for(TeamTeacherScoreVo tts:volist){
				fMap.put(tts.getTeacherId(), tts.getAvgScore());
			}
			for(Entry<Integer, Map<String,Object>> t1:teacherMap.entrySet()){
						Map<String,Object> t3=(Map<String, Object>) t1.getValue();
						List<Float>flist=(List<Float>) t3.get("list");
						if(flist==null){
							flist=new ArrayList<Float>();
						}
						Float score=0.0f;
						if(fMap.get(t1.getKey())!=null){
							score=fMap.get(t1.getKey());
						}
						flist.add(score);
						t3.put("list", flist);
						size=flist.size();
				}
			}
		for(Entry<Integer, Map<String,Object>> t1:teacherMap.entrySet()){
			
			yhList.add(t1.getValue());
		}
	    if(yhList.size()>0){
	    	 Map<String,Object> frist=new HashMap<String,Object>();
	    	 frist.put("teamName", "");
	    	 frist.put("teacherName","参考均分");
	    	 List<Float> list=new ArrayList<Float>();
	    	for(int i=0;i<size;i++){
	    		float f=0;
	    		float sum=0;
	    		for(Entry<Integer, Map<String,Object>> t1:teacherMap.entrySet()){
	    			List<Float> f1=(List<Float>) t1.getValue().get("list");
	    			if(f1!=null&&f1.size()>0){
	    				sum+=f1.get(i);
	    			}
	    		}
	    		BigDecimal big=new BigDecimal(sum);
	    		BigDecimal big2=new BigDecimal(teacherMap.size()+"");
	    		if(teacherMap.size()!=0){
	    			f=big.divide(big2, 2,BigDecimal.ROUND_HALF_UP).floatValue();
	    			f=new BigDecimal(f).setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	    		}
	    		list.add(f);
	    	}
	    	frist.put("list", list);
	    	yhList.add(0, frist);
	    }
		LinkedHashMap<String,Object> hsMap=new LinkedHashMap<String, Object>();
		hsMap.put("everyWeek",xhList);
		hsMap.put("week", yhList);
		hsMap.put("startDate", df.format(StartDate));
		hsMap.put("finishDate", df.format(FinishDate));
		hsMap.put("today", df.format(date));
		return hsMap;
	}

	@Override
	public List<AptsTeamStuentCount> findTeamStuentCount(Integer schoolId,
			String schoolYear) {
		
		return pjAptsTaskDao.findTeamStuentCount(schoolId, schoolYear);
	}

	@Override
	public void initAptsTaskUserOfToday(Integer schoolId,Integer type) throws Exception {
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Integer>subjectAssessmentIds=new ArrayList<Integer>();
		List<Integer>masterAssessmentIds=new ArrayList<Integer>();
		List<String>subjectItem=new ArrayList<String>();
		List<String>masterItem=new ArrayList<String>();
		Date date=new Date();
		SchoolTerm schoolTerm=schoolTermService.findSchoolYearByToday(schoolId, date);
		PjAptsTaskCondition condition =new PjAptsTaskCondition();
		condition.setSchoolId(schoolId);
		condition.setEvType(type);
		condition.setIsDelete(false);
		List<PjAptsTask>ptList=pjAptsTaskDao.findPjAptsTaskByCondition(condition, null, null);
		if(ptList.size()>0){
			PjAptsTask pt=ptList.get(0);
			SchoolTerm	schoolTerm1=schoolTermService.findSchoolYearByToday(schoolId, date);
			List<TeamTeacher> ttList=teamTeacherService.findTeamTeacherBySchoolId(pt.getSchoolId(), pt.getEvType(),schoolTerm1.getSchoolYear());
			List<AptsTeamStuentCount> aptsList=findTeamStuentCount(pt.getSchoolId(),schoolTerm1.getSchoolYear());
			Map<Integer,Integer>aptsMap=new HashMap<Integer, Integer>();
			for(AptsTeamStuentCount  atsc:aptsList){
				aptsMap.put(atsc.getTeamId(), atsc.getCount());
			}
			for (TeamTeacher tt : ttList) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (tt.getType() == AssessmentCantants.MASTER
						&& pt.getEvType()==1) {
					Map<String, Object> timeMap;
					timeMap = DateUtil.getWeekMonAndSun();
					PjAptsTaskUserCondition ptuCondition=new PjAptsTaskUserCondition();
					ptuCondition.setAptsTaskId(pt.getId());
					ptuCondition.setStartDate((Date)timeMap.get("monday"));
					ptuCondition.setTeacherId(tt.getId());
					List<PjAptsTaskUser> ptuList=pjAptsTaskUserService.findPjAptsTaskUserByCondition(ptuCondition);
					Integer AssessmentId=0;
					if(ptuList.size()==0){
						PjAptsTaskUser patu=new PjAptsTaskUser();
						patu.setAptsTaskId(pt.getId());
						patu.setCreateDate(date);
						patu.setModifyDate(date);
						patu.setStartDate((Date)timeMap.get("monday"));
						patu.setFinishDate((Date)timeMap.get("sunday"));
						patu.setGradeId(tt.getGradeId());
						patu.setSchoolId(schoolId);
						patu.setSchoolYear(schoolTerm.getSchoolYear());
						patu.setScore(0.0d);
						patu.setTeacherId(tt.getId());
						patu.setTeamId(tt.getTeamId());
						patu.setTotalUserCount(aptsMap.get(tt.getTeamId()));
						patu.setTermCode(schoolTerm.getCode());
	                    patu.setScoredUserCount(0);
	                    patu.setIsValid(false);
	                    patu.setEvType(pt.getEvType());
	                    patu.setScoringType(pt.getScoringType());
	                    patu.setValidPercent(pt.getValidPercent());
	                    patu.setPeriod(pt.getPeriod());
	                    pjAptsTaskUserService.add(patu);
	                    AssessmentId=patu.getId();
	                    masterAssessmentIds.add(AssessmentId);
					}else{
						AssessmentId=ptuList.get(0).getId();
					}
				}
				if (tt.getType() == AssessmentCantants.SUBJECT
						&& pt.getEvType()==2) {
					String todayStr = sf1.format(date)+" 00:00:00";
					String todayStrAfter=sf1.format(date)+" 23:59:00";
					PjAptsTaskUserCondition ptuCondition=new PjAptsTaskUserCondition();
					ptuCondition.setAptsTaskId(pt.getEvType());
					ptuCondition.setStartDate(sf.parse(todayStr));
					ptuCondition.setTeacherId(tt.getId());
					List<PjAptsTaskUser> ptuList=pjAptsTaskUserService.findPjAptsTaskUserByCondition(ptuCondition);
					Integer AssessmentId=0;
					if(ptuList.size()==0){
						PjAptsTaskUser patu=new PjAptsTaskUser();
						patu.setAptsTaskId(pt.getId());
						patu.setCreateDate(date);
						patu.setModifyDate(date);
						patu.setStartDate(sf.parse(todayStr));
						patu.setFinishDate(sf.parse(todayStrAfter));
						patu.setGradeId(tt.getGradeId());
						patu.setSchoolId(schoolId);
						patu.setSchoolYear(schoolTerm.getSchoolYear());
						patu.setScore(0.0d);
						patu.setTeacherId(tt.getId());
						patu.setTeamId(tt.getTeamId());
						patu.setTermCode(schoolTerm.getCode());
						patu.setTotalUserCount(aptsMap.get(tt.getTeamId()));
	                    patu.setScoredUserCount(0);
	                    patu.setIsValid(false);
	                    patu.setEvType(pt.getEvType());
	                    patu.setScoringType(pt.getScoringType());
	                    patu.setValidPercent(pt.getValidPercent());
	                    patu.setPeriod(pt.getPeriod());
	                    pjAptsTaskUserService.add(patu);
	                    AssessmentId=patu.getId();
	                    subjectAssessmentIds.add(AssessmentId);
					}else{
						AssessmentId=ptuList.get(0).getId();
					}
				}
			}
			List<PjAptsTaskItem> pilist=new ArrayList<PjAptsTaskItem>();
			List<PjAptsTaskItem> pilist1=new ArrayList<PjAptsTaskItem>();
			if(pt.getEvType()==1){
				PjAptsTaskItemCondition piCondition=new PjAptsTaskItemCondition();
				piCondition.setAptsTaskId(pt.getId());
				piCondition.setIsDelete(false);
				 pilist=pjAptsTaskItemService.findPjAptsTaskItemByCondition(piCondition);
			}
			if(pt.getEvType()==2){
				PjAptsTaskItemCondition piCondition=new PjAptsTaskItemCondition();
				piCondition.setAptsTaskId(pt.getId());
				piCondition.setIsDelete(false);
			    pilist1=pjAptsTaskItemService.findPjAptsTaskItemByCondition(piCondition);
			}
			if(subjectAssessmentIds.size()!=0){
				initScoreSummary(subjectAssessmentIds, pilist1);
			}
			if(masterAssessmentIds.size()!=0){
				initScoreSummary(masterAssessmentIds, pilist);
			}
		}
		
	}

	@Override
	public PjAptsTask initDefaultTask(Integer schoolId,Integer type) {
		PjAptsTaskCondition condition=new PjAptsTaskCondition();
		condition.setSchoolId(schoolId);
		condition.setIsDelete(false);
		condition.setEvType(type);
        List<PjAptsTask>ptList=pjAptsTaskDao.findPjAptsTaskByCondition(condition, null, null);
        Integer taskId=0;
        PjAptsTask pt=new PjAptsTask();
        if(ptList.size()==0){
        	Date evDate=new Date();
        	Date date=new Date();
        	pt.setSchoolId(schoolId);
        	pt.setEvType(type);
        	pt.setScoringType(15);
        	pt.setPeriod(type==AssessmentCantants.MASTER?4:5);
        	pt.setValidPercent(type==1?0:0.5);
        	pt.setCreateDate(date);
        	pjAptsTaskDao.create(pt);
        	 taskId=pt.getId();
        	 
        	if(type==AssessmentCantants.MASTER){
        		try {
					evDate=(Date) DateUtil.getWeekMonAndSun().get("sunday");
				} catch (Exception e) {
					e.printStackTrace();
				}
        		PjAptsTaskItem item=new PjAptsTaskItem();
        		item.setAptsTaskId(taskId);
        		item.setFullScore(10);
        		item.setListOrder(0);
        		item.setName("管理能力");
        		item.setCreateDate(date);
        		item.setRealScore(0);
        		item.setScale(0);
        		item.setEvDate(evDate);
        		pjAptsTaskItemService.add(item);
        		PjAptsTaskItem item1=new PjAptsTaskItem();
        		item1.setAptsTaskId(taskId);
        		item1.setFullScore(10);
        		item1.setListOrder(1);
        		item1.setName("班级纪律");
        		item1.setCreateDate(date);
        		item1.setRealScore(0);
        		item1.setScale(0);
        		item1.setEvDate(evDate);
        		pjAptsTaskItemService.add(item1);
        		PjAptsTaskItem item2=new PjAptsTaskItem();
        		item2.setAptsTaskId(taskId);
        		item2.setFullScore(10);
        		item2.setListOrder(2);
        		item2.setName("责任心");
        		item2.setCreateDate(date);
        		item2.setRealScore(0);
        		item2.setScale(0);
        		item2.setEvDate(evDate);
        		pjAptsTaskItemService.add(item2);
        	}else{
       		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		try {
					evDate=sdf1.parse(sdf.format(date)+" 23:59:59");
				} catch (ParseException e) {
					e.printStackTrace();
				}
        		PjAptsTaskItem item2=new PjAptsTaskItem();
        		item2.setAptsTaskId(taskId);
        		item2.setFullScore(10);
        		item2.setListOrder(0);
        		item2.setName("教学评价");
        		item2.setCreateDate(date);
        		item2.setRealScore(0);
        		item2.setScale(0);
        		item2.setEvDate(evDate);
        		pjAptsTaskItemService.add(item2);
        	}
        }else{
        	pt=ptList.get(0);
        }
		return pt;
	}

	@Override
	public Map<String, Object> findAptsTaskUserMapByAssessmentId(Integer assessmentId) {
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			PjAptsTaskUserVo pulist=pjAptsTaskUserService.findAptsTaskUserVoById(assessmentId);
			if(pulist==null){
				return new HashMap<String, Object>();
			}
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			Date date=new Date();
			SchoolTerm st=schoolTermService.findSchoolTermByCode(pulist.getSchoolId(), pulist.getTermCode());
			map.put("subjectName", pulist.getSubjectName()==null?"":pulist.getSubjectName());
			map.put("weekCount", "第"+ArabicToChineseUtils.foematInteger(DateUtil.findWeekCount(st.getBeginDate(),st.getFinishDate(), pulist.getStartDate()))+"周");
			map.put("startDate", sf.format(pulist.getStartDate()));
			map.put("finishDate", sf.format(pulist.getFinishDate()));
			map.put("teamName", pulist.getTeamName());
			map.put("weekNum", DateUtil.findWeekNum(pulist.getStartDate()));
			map.put("assessmentId", assessmentId);
			map.put("teacherName", pulist.getTeacherName());
			map.put("score", pulist.getScore());
			map.put("totalCount", pulist.getTotalUserCount());
			map.put("scoredCount", pulist.getScoredUserCount());
			map.put("isValid", pulist.getIsValid()==true?1:0);
			map.put("period", pulist.getPeriod());
			map.put("userId", pulist.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> findAptsTaskUserMapListByUserId(
			String termCode, Integer type, Integer userId,Page page,Order order) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Date date=new Date();
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");

			try {
				List<PjAptsTaskUserVo> volist=pjAptsTaskUserService.findAptsTaskUserVoByUserId(termCode, type, userId, page, order);
				String[] str=termCode.split("-");
				SchoolTerm st=schoolTermService.findSchoolTermByCode(Integer.valueOf(str[0]), termCode);
				if(volist.size()==0){
					return list;
				}
				if(st==null){
					return list;
				}
				for(PjAptsTaskUserVo pulist:volist){
					Map<String,Object>map=new HashMap<String,Object>();
					map.put("subjectName", pulist.getSubjectName());
				    map.put("weekCount", "第"+ArabicToChineseUtils.foematInteger(DateUtil.findWeekCount(st.getBeginDate(),st.getFinishDate(), pulist.getStartDate()))+"周");
				    map.put("startDate", sf.format(pulist.getStartDate()));
					map.put("finishDate", sf.format(pulist.getFinishDate()));
					map.put("teamName", pulist.getTeamName());
					map.put("weekNum", DateUtil.findWeekNum(pulist.getStartDate()));
					map.put("assessmentId", pulist.getId());
//					map.put("teacherName", pulist.getTeacherName());
//					map.put("score", pulist.getScore());
//					map.put("totalCount", pulist.getTotalUserCount());
//					map.put("scoredCount", pulist.getScoredUserCount());
//					map.put("isValid", pulist.getIsValid()==true?1:0);
//					map.put("period ", pulist.getPeriod());
//					map.put("userId", pulist.getUserId());
					list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Map<String,Object>>();
			}
			
		return list;
	}

}
