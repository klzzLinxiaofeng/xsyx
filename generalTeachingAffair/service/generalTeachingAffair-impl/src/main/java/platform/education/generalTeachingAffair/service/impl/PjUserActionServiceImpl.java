package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.PjUserAction;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.vo.PjUserActionCondition;
import platform.education.generalTeachingAffair.vo.PjUserActionHonorScoreVo;
import platform.education.generalTeachingAffair.vo.PjUserActionTestVo;
import platform.education.generalTeachingAffair.service.PjUserActionService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.contants.UserActionContans;
import platform.education.generalTeachingAffair.dao.PjUserActionDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjUserActionServiceImpl implements PjUserActionService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjUserActionDao pjUserActionDao;
	
	@Resource
	private TeamStudentService teamStudentService;
	@Resource
	private TeamService teamService;
	@Resource
	private SchoolTermCurrentService schoolTermCurrentService;

	public void setPjUserActionDao(PjUserActionDao pjUserActionDao) {
		this.pjUserActionDao = pjUserActionDao;
	}
	
	@Override
	public PjUserAction findPjUserActionById(Integer id) {
		try {
			return pjUserActionDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjUserAction add(PjUserAction pjUserAction) {
		if(pjUserAction == null) {
    		return null;
    	}
    	Date createDate = pjUserAction.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjUserAction.setCreateDate(createDate);
    	pjUserAction.setModifyDate(createDate);
		return pjUserActionDao.create(pjUserAction);
	}

	@Override
	public PjUserAction modify(PjUserAction pjUserAction) {
		if(pjUserAction == null) {
    		return null;
    	}
    	Date modify = pjUserAction.getModifyDate();
    	pjUserAction.setModifyDate(modify != null ? modify : new Date());
		return pjUserActionDao.update(pjUserAction);
	}
	
	@Override
	public void remove(PjUserAction pjUserAction) {
		try {
			pjUserActionDao.delete(pjUserAction);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjUserAction.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Page page, Order order) {
		return pjUserActionDao.findPjUserActionByCondition(pjUserActionCondition, page, order);
	}
	
	@Override
	public List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition) {
		return pjUserActionDao.findPjUserActionByCondition(pjUserActionCondition, null, null);
	}
	
	@Override
	public List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Page page) {
		return pjUserActionDao.findPjUserActionByCondition(pjUserActionCondition, page, null);
	}
	
	@Override
	public List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Order order) {
		return pjUserActionDao.findPjUserActionByCondition(pjUserActionCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjUserActionDao.count(null);
	}

	@Override
	public Long count(PjUserActionCondition pjUserActionCondition) {
		return this.pjUserActionDao.count(pjUserActionCondition);
	}

	@Override
	public void addJSONData(String data) {
		try{
			JSONObject obj=JSONObject.fromObject(data);
			Integer teamId=Integer.valueOf(obj.get("teamId").toString());
			List<TeamStudent> tsList=teamStudentService.findByTeamId(teamId);
			Team t=teamService.findTeamById(teamId);
			Integer schoolId=t.getSchoolId();
			List<SchoolTermCurrent> stc= schoolTermCurrentService.findCurrentSchoolYear(schoolId);
			SchoolTermCurrent st=stc.get(0);
			String schoolYear=st.getSchoolYear();
			String termCode=st.getSchoolTermCode();
			String subjectCode=obj.getString("subject_code");
			Date date=new Date();
			Map<Integer,TeamStudent> tsMap=new HashMap<Integer,TeamStudent>();
			String[] keys={"demonCounter","miraCounter","drawLotsCounter","answerCounter","reviewCounter","likeCounter"};
			Map<Integer,Integer> rightCountMap=new HashMap<Integer, Integer>();
			Map<Integer,Integer> anwerCountMap=new HashMap<Integer, Integer>();
			
			
			if(tsList.size()>0){
				Integer questionCount=0;
				Integer teamCount=tsList.size();
				for(TeamStudent ts:tsList){
					tsMap.put(ts.getId(), ts);
					rightCountMap.put(ts.getId(), 0);
					anwerCountMap.put(ts.getId(), 0);
				}
				List<PjUserAction> uaList=new ArrayList<PjUserAction>();
				JSONArray ja=(JSONArray) obj.get("students");
				JSONArray qa=(JSONArray) obj.get("qa");
				
				for(int i=0;i<ja.size();i++){
					JSONObject jb=ja.getJSONObject(i);
					Integer id=jb.getInt("teamStudentId");
					if(tsMap.get(id)!=null){
						//学生行为
						TeamStudent ts=tsMap.get(id);
						for(String key:keys){
							String count =jb.get(key).toString();
							PjUserAction pa=new PjUserAction();
							pa.setAppId(1);
							pa.setCreateDate(date);
							pa.setDocument("");
							pa.setModifyDate(date);
							pa.setSchoolId(schoolId);
							pa.setSchoolYear(schoolYear);
							pa.setSubjectCode(subjectCode);
							pa.setTag("小奇校园");
							pa.setTeamId(teamId);
							pa.setTermCode(termCode);
							pa.setType(key);
							pa.setUserId(ts.getUserId());
							pa.setValue(count);
							pa.setUuid("test");
							uaList.add(pa);
						}
						JSONArray ja1=jb.getJSONArray("honor");
						for(int j=0;j<ja1.size();j++){
							String key="honor";
							JSONObject ob1=ja1.getJSONObject(j);
							String tag="";
							if(ob1.get("tag")!=null){
								tag=ob1.get("tag").toString();
							}
							String count =ob1.get("score").toString();
							PjUserAction pa=new PjUserAction();
							pa.setAppId(1);
							pa.setCreateDate(date);
							pa.setDocument(tag);
							pa.setModifyDate(date);
							pa.setSchoolId(schoolId);
							pa.setSchoolYear(schoolYear);
							pa.setSubjectCode(subjectCode);
							pa.setTag("小奇校园");
							pa.setTeamId(teamId);
							pa.setTermCode(termCode);
							pa.setType(key);
							pa.setUserId(ts.getUserId());
							pa.setValue(count);
							pa.setUuid("test");
							uaList.add(pa);
						}
					String key1="honorScore";
					String	honorScore=jb.get(key1).toString();
					PjUserAction pa=new PjUserAction();
					pa.setAppId(1);
					pa.setCreateDate(date);
					pa.setDocument("");
					pa.setModifyDate(date);
					pa.setSchoolId(schoolId);
					pa.setSchoolYear(schoolYear);
					pa.setSubjectCode(subjectCode);
					pa.setTag("小奇校园");
					pa.setTeamId(teamId);
					pa.setTermCode(termCode);
					pa.setType(key1);
					pa.setUserId(ts.getUserId());
					pa.setValue(honorScore);
					pa.setUuid("test");
					uaList.add(pa);
					}
				}
				for(int i=0;i<qa.size();i++){
				JSONObject q=qa.getJSONObject(i);
				String type=q.getString("qaType");
				if(type.equals("subjective")||type.equals("snapshot")){
					
				}else{
					String corrAnwer=q.get("qaKey").toString();
					JSONArray qaAnswers=q.getJSONArray("qaAnswers");
					for(int j=0;j<qaAnswers.size();j++){
						JSONObject stuAnwer=qaAnswers.getJSONObject(j);
						String an=stuAnwer.get("answer").toString();
						Integer id=stuAnwer.getInt("teamStudentId");
						if(tsMap.get(id)!=null){
							anwerCountMap.put(id, anwerCountMap.get(id)+1);
							if(an.equals(corrAnwer)){
								rightCountMap.put(id, rightCountMap.get(id)+1);
							}
						}
					}
					questionCount++;
				}
				}
				String key2="anwerCount";
				for(Entry<Integer, Integer>  entry:anwerCountMap.entrySet()){
					TeamStudent ts=tsMap.get(entry.getKey());
					PjUserAction pa=new PjUserAction();
					pa.setAppId(1);
					pa.setCreateDate(date);
					pa.setDocument("");
					pa.setModifyDate(date);
					pa.setSchoolId(schoolId);
					pa.setSchoolYear(schoolYear);
					pa.setSubjectCode(subjectCode);
					pa.setTag("小奇校园");
					pa.setTeamId(teamId);
					pa.setTermCode(termCode);
					pa.setType(key2);
					pa.setUserId(ts.getUserId());
					pa.setValue(entry.getValue().toString());
					pa.setUuid("test");
					uaList.add(pa);
				}
				 key2="rightCount";
				for(Entry<Integer, Integer>  entry:rightCountMap.entrySet()){
					TeamStudent ts=tsMap.get(entry.getKey());
					PjUserAction pa=new PjUserAction();
					pa.setAppId(1);
					pa.setCreateDate(date);
					pa.setDocument("");
					pa.setModifyDate(date);
					pa.setSchoolId(schoolId);
					pa.setSchoolYear(schoolYear);
					pa.setSubjectCode(subjectCode);
					pa.setTag("小奇校园");
					pa.setTeamId(teamId);
					pa.setTermCode(termCode);
					pa.setType(key2);
					pa.setUserId(ts.getUserId());
					pa.setValue(entry.getValue().toString());
					pa.setUuid("test");
					uaList.add(pa);
				}
				 	key2="questionCount";
					PjUserAction pa=new PjUserAction();
					pa.setAppId(1);
					pa.setCreateDate(date);
					pa.setDocument("");
					pa.setModifyDate(date);
					pa.setSchoolId(schoolId);
					pa.setSchoolYear(schoolYear);
					pa.setSubjectCode(subjectCode);
					pa.setTag("小奇校园");
					pa.setTeamId(teamId);
					pa.setTermCode(termCode);
					pa.setType(key2);
					pa.setUserId(0);
					pa.setValue(questionCount.toString());
					pa.setUuid("test");
					uaList.add(pa);
				 	key2="teamCount";
					pa=new PjUserAction();
					pa.setAppId(1);
					pa.setCreateDate(date);
					pa.setDocument("");
					pa.setModifyDate(date);
					pa.setSchoolId(schoolId);
					pa.setSchoolYear(schoolYear);
					pa.setSubjectCode(subjectCode);
					pa.setTag("小奇校园");
					pa.setTeamId(teamId);
					pa.setTermCode(termCode);
					pa.setType(key2);
					pa.setUserId(0);
					pa.setValue(teamCount.toString());
					pa.setUuid("test");
					uaList.add(pa);
					key2="json";
					pa=new PjUserAction();
					pa.setAppId(1);
					pa.setCreateDate(date);
					pa.setDocument("");
					pa.setModifyDate(date);
					pa.setSchoolId(schoolId);
					pa.setSchoolYear(schoolYear);
					pa.setSubjectCode(subjectCode);
					pa.setTag("小奇校园");
					pa.setTeamId(teamId);
					pa.setTermCode(termCode);
					pa.setType(key2);
					pa.setUserId(0);
					pa.setValue(data);
					pa.setUuid("test");
					uaList.add(pa);
					JSONObject statistics=obj.getJSONObject("statistics");
					for(Object key:statistics.keySet()){
						String keyStr="teacher_"+key.toString();
						String value=statistics.getString(key.toString());
						pa=new PjUserAction();
						pa.setAppId(1);
						pa.setCreateDate(date);
						pa.setDocument("");
						pa.setModifyDate(date);
						pa.setSchoolId(schoolId);
						pa.setSchoolYear(schoolYear);
						pa.setSubjectCode(subjectCode);
						pa.setTag("小奇校园");
						pa.setTeamId(teamId);
						pa.setTermCode(termCode);
						pa.setType(keyStr);
						pa.setUserId(0);
						pa.setValue(value);
						pa.setUuid("test");
						uaList.add(pa);
					}
					System.out.println(uaList.size());
					PjUserAction[]	pjUserActions=uaList.toArray(new PjUserAction[uaList.size()]);
					pjUserActionDao.createBatch(pjUserActions);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void createBatch(PjUserAction[] pjUserActions) {
		pjUserActionDao.createBatch(pjUserActions);
	}

	@Override
	public Float findSumCountByType(String type, Integer userId,
			String startDate, String FinishDate,String subjectCode,String termCode) {
		return pjUserActionDao.findSumCountByType(type, userId, startDate, FinishDate, subjectCode,termCode);
	}

	@Override
	public Map<String, Object> findCoursePerformance(Integer userId,
			String startDate, String FinishDate, String subjectCode,String termCode) {
	
		return null;
	}

	@Override
	public List<PjUserActionHonorScoreVo> findHonorScore(Integer args1, Date args2, Date args3, String args4, String args5, Integer teamId) {
		List<PjUserActionHonorScoreVo> p = new ArrayList<PjUserActionHonorScoreVo>();
		if(args5.equals("1")) {
			p = pjUserActionDao.findHonorScoreByDay(UserActionContans.HONORSCORE, args1, args2,  args3, args4, teamId);
		}else if(args5.equals("2") || args5.equals("3")) {
			p = pjUserActionDao.findHonorScoreSum(UserActionContans.HONORSCORE, args1, args2,  args3, args4, teamId);
		}
		return p;
	}

	@Override
	public Double findStudentPractice(Integer userId, Date args1, Date args2, String subjectCode, Integer teamId) {
		Double anwerCount = pjUserActionDao.findStudentResByTypeSum(userId, UserActionContans.ANWERCOUNT, args1, args2, subjectCode ,teamId);
		Double questionCount = pjUserActionDao.findStudentResByTypeSum(0, UserActionContans.QUESTIONCOUNT, args1, args2, subjectCode ,teamId);
		Double res = 0.0;
		if(questionCount != null && questionCount != 0.0) {
			if(anwerCount != null) {
				res = anwerCount / questionCount;
			}else {
				res = 0.0 / questionCount;
			}
			return res;
		}else {
			return 0.0;
		}
	}

	@Override
	public Double findStudentAchievement(Integer userId, Date args1, Date args2, String subjectCode, Integer teamId) {
		Double studentRes = pjUserActionDao.findStudentAchievementSum(userId, args1, args2, subjectCode, UserActionContans.DEMONCOUNTER, UserActionContans.MIRACOUNTER ,teamId);
		Double teacherRes = pjUserActionDao.findStudentAchievementSum(0, args1, args2, subjectCode, UserActionContans.TEACHER_DEMONCOUNTER, UserActionContans.TEACHER_MIRACOUNTER ,teamId);
		Double res = 0.0;
		if(teacherRes != null && teacherRes != 0.0) {
			if(studentRes != null) {
				res = studentRes / teacherRes;
			}else {
				res = 0.0 / teacherRes;
			}
			return res;
		}else {
			return 0.0;
		}
	}

	@Override
	public Double findStudentAnswer(Integer userId, Date args1, Date args2, String subjectCode, Integer teamId) {
		Double studentAnswer = pjUserActionDao.findStudentResByTypeSum(userId, UserActionContans.ANSWERCOUNTER, args1, args2, subjectCode ,teamId);
		Double tescherAnswer = pjUserActionDao.findStudentResByTypeSum(0, UserActionContans.TEACHER_ANSWERCOUNTER, args1, args2, subjectCode ,teamId);
		Double res = 0.0;
		if(tescherAnswer != null && tescherAnswer != 0.0) {
			if(studentAnswer != null) {
				res = studentAnswer / tescherAnswer;
			}else {
				res = 0.0 / tescherAnswer;
			}
			return res;
		}else {
			return 0.0;
		}
	}

	@Override
	public Double findQuestionCountSumByUserId(Integer userId, Integer teamId, Date date1, Date date2,
			String subjectCode) {
		Double res = pjUserActionDao.findQuestionCountSumByUserId(userId, teamId, UserActionContans.QUESTIONCOUNT, date1, date2, subjectCode);
		return res;
	}

	@Override
	public PjUserActionTestVo findStudentTestSumByTeamId(Integer teamId, String subjectCode, Date date1,
			Date date2, Integer userId) {
		return pjUserActionDao.findStudentTestSumByTeamId(teamId, UserActionContans.RIGHTCOUNT, subjectCode, date1, date2, userId);
	}
	
	@Override
	public List<Integer> findUserIdListByTeamId(Integer teamId, String subjectCode, Date date1, Date date2){
		return pjUserActionDao.findUserIdListByTeamId(teamId, UserActionContans.RIGHTCOUNT, subjectCode, date1, date2);
	}
	
	@Override
	public List<String> findPjUserActionByUserId(Integer userId, Integer teamId, Date date1, Date date2) {
		return pjUserActionDao.findPjUserActionByUserId(userId, teamId, date1, date2);
	}

	@Override
	public List<Integer> findClassAssessmentStudentList(Integer teamId, String type, String subjectCode, Date date1,
			Date date2) {
		return pjUserActionDao.findAppraiseStudentList(teamId, type, subjectCode, date1, date2);
	}

	@Override
	public Integer findAppraiseSum(Integer teamId, Integer userId, String type, String subjectCode, Date date1,
			Date date2) {
		Integer res = pjUserActionDao.findAppraiseSum(teamId, userId, type, subjectCode, date1, date2);
		if(res != null) {
			return res;
		}else {
			return 0;
		}
	}
	
}
