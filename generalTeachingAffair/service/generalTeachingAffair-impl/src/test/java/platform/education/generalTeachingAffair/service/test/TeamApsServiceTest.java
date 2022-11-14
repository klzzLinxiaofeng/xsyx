package platform.education.generalTeachingAffair.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.vo.RedBannerVo;
import platform.education.generalTeachingAffair.model.ApsRuleItem;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.model.ApsTeamSummary;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class TeamApsServiceTest{
	
	@Autowired
	@Qualifier("teamApsService")
	private TeamApsService teamApsService;
	
//	@Test
//	public void testa() throws ParseException{
////	
//		List<TeamSummaryData> teamSummaryDataList = teamApsService.summaryTeamEvaluationTaskForTeam("99-2015-2", 1126,
//		new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-10"), new SimpleDateFormat("yyyy-MM-dd").parse("2016-09-14"));
//		System.out.println(teamSummaryDataList.size());
//		for(TeamSummaryData teamSummaryData : teamSummaryDataList){
//			System.out.println(teamSummaryData);
//		}
//	}
//	
//	
//	@Test
//	public void testAddTask(){
//		try{
//			teamApsService.AddTeamTask(1, "1-2015-2");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testAddNewItem(){
//		try{
//			teamApsService.addTeamEvaluationTask(1, "课后卫生", "3", (float)1);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testDeleteItem(){
//		try{
//			teamApsService.deleteTeamEvaluationItem(17);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testUpdateItemName(){
//		try{
//			teamApsService.updateTeamEvaluationItemOfName("早读", 18);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testUpdataItemScore(){
//		try{
//			teamApsService.updateTeamEvaluationItemOfScore(18, (float)5);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testSetScore(){
//		try{
////			teamApsService.setTeamEvaluationTaskScore(taskItemId, teamId, parentObjectId, teacherId, score, checkDate);
//			teamApsService.setTeamEvaluationTaskScore(8, 1131, 226, 1139, (float)3, new Date());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetScore(){
//		try{
////			teamApsService.getTeamEvaluationTaskScore(taskItemId, teamId, checkDate)
//			ApsTaskScore taskScore  = teamApsService.getTeamEvaluationTaskScore(1, 1125, new Date());
//			System.out.println(taskScore.getScore());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testModifyScore(){
//		try{
////			teamApsService.modifyTeamEvaluationTaskScore(taskItemId, teamId, score, checkDate);
//			teamApsService.modifyTeamEvaluationTaskScore(2, 1125, (float)6 , new Date());
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testSetScoreFile(){
//		try{
//			List<String> fileUUIDs = new ArrayList<String>();
//			fileUUIDs.add("sadfafa");
//			fileUUIDs.add("asdfasdfasdfasdf");
//			fileUUIDs.add("scvjh");
////			teamApsService.setTeamEvaluationTaskScore(taskItemId, teamId, parentObjectId, teacherId, score, checkDate, remark, fileUUIDs);
//			teamApsService.setTeamEvaluationTaskScore(13, 1127, 225, 1139, (float)-2.5, new Date(), "hello", fileUUIDs);	
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testSetGradeScores(){
//		try{
//			List<TeamScoreData> teamScoreDatas = new ArrayList<TeamScoreData>();
//			TeamScoreData ts = new TeamScoreData();
//			ts.setItemId(3);
//			ts.setTeamId(1127);
//			ts.setScore((float)10);
//			teamScoreDatas.add(ts);
//			TeamScoreData ts1 = new TeamScoreData();
//			ts1.setItemId(4);
//			ts1.setTeamId(1128);
//			ts1.setScore((float)9);
//			teamScoreDatas.add(ts1);
//			TeamScoreData ts2 = new TeamScoreData();
//			ts2.setItemId(5);
//			ts2.setTeamId(1129);
//			ts2.setScore((float)8);
//			teamScoreDatas.add(ts2);
////			teamApsService.batchSetTeamEvaluationTaskScore(teacherId, parentObjectId, checkDate, teamScoreDatas);
//			teamApsService.batchSetTeamEvaluationTaskScore(1135, 225, new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-18"), teamScoreDatas);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetMinusScore(){
//		try{
////			teamApsService.findTeamMinusScoresOfGrade(schoolId, termCode, gradeId, checkDate);
//			List<TeamScoreData> teamScoreDataList = teamApsService.findTeamMinusScoresOfGrade(99, "99-2015-2", 225, new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-18"));
//			for(TeamScoreData teamScoreData : teamScoreDataList){
//				System.out.println("项目id"+teamScoreData.getItemId());
//				System.out.println("班级id"+teamScoreData.getTeamId());
//				System.out.println("项目名"+teamScoreData.getItemName());
//				System.out.println("得分"+teamScoreData.getScore());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testGetAddScore(){
//		try{
////			teamApsService.findTeamMinusScoresOfGrade(schoolId, termCode, gradeId, checkDate);
//			List<TeamScoreData> teamScoreDataList = teamApsService.findTeamAddScoresOfGrade(99, "99-2015-2", 225, new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-18"));
//			for(TeamScoreData teamScoreData : teamScoreDataList){
//				System.out.println("项目id"+teamScoreData.getItemId());
//				System.out.println("班级id"+teamScoreData.getTeamId());
//				System.out.println("项目名"+teamScoreData.getItemName());
//				System.out.println("得分"+teamScoreData.getScore());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testFindTeamTaskItems(){
//		try{
//			List<ApsTaskItem> taskItemsList = teamApsService.findTeamTaskItems("99-2015-2");
//			System.out.println(taskItemsList.size());
//			for(ApsTaskItem apsTaskItem :taskItemsList){
//				System.out.println(apsTaskItem.getName());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testFindOneTeamTaskItems(){
//		try{
//			List<ApsTaskItem> taskItemsList = teamApsService.findTeamTaskItems("99-2015-2",ApsTaskContants.CHECK_TYPE_ADD);
//			System.out.println(taskItemsList.size());
//			for(ApsTaskItem apsTaskItem :taskItemsList){
//				System.out.println(apsTaskItem.getName());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	
//	@Test
//	public void testSummaryTeamEvaluationTaskForTeam(){
//		try{
////			teamApsService.getTeamTotalScore(termCode, teamId, checkDate)
////			List<TeamSummaryData> teamSummaryDataList = teamApsService.getTeamTotalScore("99-2015-2", 1125, new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-18"));
//			List<TeamSummaryData> teamSummaryDataList = teamApsService.summaryTeamEvaluationTaskForTeam("99-2015-2", 1126, 
//					new SimpleDateFormat("yyyy-MM-dd").parse("2016-07-10"), new SimpleDateFormat("yyyy-MM-dd").parse("2016-09-14"));
//			System.out.println(teamSummaryDataList.size());
//			for(TeamSummaryData data : teamSummaryDataList){
//				System.out.println("TeamSummaryData [rank=" + data.getRank() + ", objectId=" + data.getObjectId()
//						+ ", objectName=" + data.getObjectName() + ", totalScore=" + data.getTotalScore()
//						+ ", addScore=" + data.getAddScore() + ", deductScore=" + data.getDeductScore()
//						+ ", addRatio=" + data.getAddRatio() + ", deductRatio=" + data.getDeductRatio()
//						+ "]");
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testSummaryTeamEvaluationTaskForGrade(){
//		try{
////			teamApsService.summaryTeamEvaluationTaskForGrade(gradeId, termCode, startDate, finishDate)
//			List<TeamSummaryData> teamSummaryDataList = teamApsService.summaryTeamEvaluationTaskForGrade(225, "99-2015-2",
//					new SimpleDateFormat("yyyy-MM-dd").parse("2016-07-10"), new SimpleDateFormat("yyyy-MM-dd").parse("2016-09-14"));
//			System.out.println(teamSummaryDataList.size());
//			for(TeamSummaryData data : teamSummaryDataList){
//				System.out.println("TeamSummaryData [rank=" + data.getRank() + ", objectId=" + data.getObjectId()
//						+ ", objectName=" + data.getObjectName() + ", totalScore=" + data.getTotalScore()
//						+ ", addScore=" + data.getAddScore() + ", deductScore=" + data.getDeductScore()
//						+ ", addRatio=" + data.getAddRatio() + ", deductRatio=" + data.getDeductRatio()
//						+ "]");
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testSummaryTeamEvaluationTaskForSchool(){
//		try{
////			teamApsService.summaryTeamEvaluationTaskForSchool(schoolId, termCode, startDate, finishDate)
//			List<TeamSummaryData> teamSummaryDataList = teamApsService.summaryTeamEvaluationTaskForSchool(99, "99-2015-2", 
//					new SimpleDateFormat("yyyy-MM-dd").parse("2016-07-13"), new SimpleDateFormat("yyyy-MM-dd").parse("2016-07-25"));
//			System.out.println(teamSummaryDataList.size());
//			for(TeamSummaryData data : teamSummaryDataList){
//				System.out.println("TeamSummaryData [rank=" + data.getRank() + ", objectId=" + data.getObjectId()
//						+ ", objectName=" + data.getObjectName() + ", totalScore=" + data.getTotalScore()
//						+ ", addScore=" + data.getAddScore() + ", deductScore=" + data.getDeductScore()
//						+ ", addRatio=" + data.getAddRatio() + ", deductRatio=" + data.getDeductRatio()
//						+ "]");
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testfindRuleItems(){
//		try{
//			List<ApsRuleItem> ruleItemList  = teamApsService.findRuleItems(99);
//			for(ApsRuleItem apsRuleItem : ruleItemList){
//				System.out.println(apsRuleItem.getName());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testsetRedBannerWeeklyStandardScore(){
//		try{
//			teamApsService.setRedBannerWeeklyStandardScore(225, (float)90,10,"");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testbatchSetRedBannerWeeklyStandardScore(){
//		try{
//			List<EvaluationMedalData> evaluationMedalDatas = new ArrayList<EvaluationMedalData>();
//			EvaluationMedalData d1 = new EvaluationMedalData();
//			d1.setGradeId(225);
//			d1.setScore((float)91);
//			evaluationMedalDatas.add(d1);
//			EvaluationMedalData d2 = new EvaluationMedalData();
//			d2.setGradeId(227);
//			d2.setScore((float)95);
//			evaluationMedalDatas.add(d2);
//			EvaluationMedalData d3 = new EvaluationMedalData();
//			d3.setGradeId(228);
//			d3.setScore((float)99);
//			evaluationMedalDatas.add(d3);
//			teamApsService.batchSetRedBannerWeeklyStandardScore(evaluationMedalDatas);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testEvaluateWeeklyGradeRedBanner(){
//		try{
//			List<TeamSummaryData> list = teamApsService.evaluateWeeklyGradeRedBanner("99-2015-2", 225, new Date(), new Date(), "M01");
//			System.out.println(list.size());
//			for(TeamSummaryData teamSummaryData : list){
//				System.out.println(teamSummaryData);
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void testfindWeeklyGradeRedBanner(){
//		try{
//			List<RedBannerVo> apsTeamSummaries  = teamApsService.findWeeklyGradeRedBanner("99-2015-2", 225, "M01",null,null);
//			System.out.println(apsTeamSummaries.size());
//			for(RedBannerVo apsTeamSummary:apsTeamSummaries){
//				System.out.println(apsTeamSummary.getRank());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void testgetTeamMinusScore(){
//		try{
//			List<TeamScoreData> teamScoreDataList = teamApsService.getTeamMinusScore("99-2015-2", 1125, new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-18"));
//			for(TeamScoreData teamScoreData : teamScoreDataList){
//				System.out.println(teamScoreData.getItemName());
//				System.out.println(teamScoreData.getScore());
//			}
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	@Test
//	public void test(){
//		try{
////		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");	
////		Date date = format.parse("2016-08-18");
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-18");
//		Date finishDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-22");
//		Long day = (finishDate.getTime()-startDate.getTime())/(24*60*60*1000);
//		for(int i=0; i<=day; i++){
//			Date checkDate = new Date();
//			checkDate.setTime(startDate.getTime()+(24*60*60*1000)*i);
//			System.out.println(checkDate);
//		}
////		Long startTime = startDate.getTime();
////		Long finishtime = finishDate.getTime();
////		for(Long time=startTime; time<=finishtime;){
////			time += startTime+24*60*60*1000;
////			System.out.println(time);
////			Date checkDate = new Date();
////			checkDate.setTime(time);
////			System.out.println(checkDate);
////		}
//		System.out.println((float)3/5);
//		System.out.println((float)(3/5));
//		
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
//	
//	
}
