/*package platform.szxyzxx.web.exam.controller;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import framework.generic.cache.redis.core.BaseRedisCache;
import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.PaperStatisticResult;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.service.ExamQuestionService;
import platform.education.generalTeachingAffair.vo.ExamQuestionCondition;
import platform.education.generalTeachingAffair.vo.ExamQuestionVo;
import platform.education.generalTeachingAffair.vo.ExamTeamNumber;
import platform.education.paper.model.PaperQuestionResult;
import platform.education.paper.model.TeamQuestionOptions;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.service.UserQuestionService;
import platform.education.resource.model.StatisticsTask;
import platform.education.resource.service.StatisticsTaskService;
import platform.education.resource.vo.StatisticsTaskCondition;
import platform.szxyzxx.services.statistic.service.StatisticService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
@Controller
@RequestMapping(value = "/examStatistics")
public class ExamStatisticsController extends BaseController  {
	
	@Autowired
	@Qualifier("paperQuestionService")
	private PaperQuestionService paperQuestionService;
	
	@Autowired
	@Qualifier("examQuestionService")
	private ExamQuestionService examQuestionService;
	
	@Autowired
	@Qualifier("statisticsTaskService")
	private StatisticsTaskService statisticsTaskService;
	
	@Autowired
	@Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;
	
	@Autowired
	@Qualifier("statisticService")
	private StatisticService statisticService;
	
	
	private static final String DIR = "exam/statisticsForH5";
	
	@RequestMapping(value = "/index/h5")
	public String resetDate(Model uiModel,
			@RequestParam("relateId")Integer relateId,
			@RequestParam("examId")Integer examId,
			@RequestParam("paperId")Integer paperId,
			@RequestParam("taskId")Integer taskId
			) {
		
		DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		
		//根据试卷id找到所有班级的分数排名
		//第二个参数  有值表示按平均分排名，null表示按班级排名
		List<PaperStatisticResult> list = this.examStatService.findPaperStatisticByExamId(examId,1);
		List<PaperStatisticResult> teamList = this.examStatService.findPaperStatisticByExamId(examId,null);
		
		float gradeAvg = 0;		//年级平均分
		float teamAvg = 0;		//班级平均分
		Integer gradeRank = 0;	//班级排名
		float sum = 0;			//总分
		int num = 0;			//实际参加考试的班级数
		
		for (PaperStatisticResult p : list) {
			float score = 0;
			if (p.getAverageScore() != null) { //只统计参加考试的班级，缺考不计入统计
				score = p.getAverageScore();
				num++;
			}
			sum += score;
			
			//判断是否是本班
			if(p.getTeamId() != null){
				if (p.getTeamId().intValue() == relateId) {
					float tAvg = 0;
					int gRank = 0;
					if (p.getAverageScore() != null) {
						tAvg = p.getAverageScore();
					}
					if (p.getRank() != null) {
						gRank = p.getRank();
					}
					
					//本班平均分
					teamAvg = tAvg;
					gradeRank = gRank;
				}
			}
		}
		
		if (num > 0) { //计算年级平均分 
			gradeAvg = sum / num;
		}
		
		String gradeAvgStr = decimalFormat.format(gradeAvg);
		String teamAvgStr = decimalFormat.format(teamAvg);
		
		uiModel.addAttribute("teamId", relateId);
		uiModel.addAttribute("examId", examId);
		uiModel.addAttribute("paperId", paperId);
		uiModel.addAttribute("taskId", taskId);
		
		uiModel.addAttribute("gradeAvg", gradeAvgStr);
		uiModel.addAttribute("teamAvg", teamAvgStr);
		uiModel.addAttribute("gradeRank", gradeRank);
		uiModel.addAttribute("list", list);
		uiModel.addAttribute("teamList", teamList);
		//=====以上整卷统计数据======
		
		//根据试卷id获取试卷的所有试题内容，用于单题分析中的题号和题目内容（问题、选项、答案、解释）
		List<PaperQuestionResult> qList = paperQuestionService.findPaperQuestionByPaperId(paperId, null);
		uiModel.addAttribute("qList", qList);
		
		//答题统计页面的数据
		//该班级每道题的作答人数、正答题、正答率、完成率
		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		uiModel.addAttribute("status", examStat.getDataChanged());
		
		return DIR + "/index";
	}
	
	//答题统计
	@RequestMapping(value = "/paperJson/h5")
	@ResponseBody
	public  Map  paperJson(@RequestParam("paperId")Integer paperId,@RequestParam("relateId")Integer relateId,@RequestParam("examStringId")Integer examIdString){
		List<Float> teamRightlist=new ArrayList<Float>();
		List<Float> gradeRightlist=new ArrayList<Float>();
		List<Integer> teamRanklist=new ArrayList<Integer>();
		DecimalFormat decimalFormat = new DecimalFormat("0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.
		Map<String,Object> map=new HashMap<String,Object>();
			ExamQuestionCondition ec=new ExamQuestionCondition();
			ec.setExamId(examIdString);
			List<ExamQuestion> eqlist=new ArrayList<ExamQuestion>();
			eqlist=examQuestionService.findExamQuestionByCondition(ec);
			if(eqlist!=null&&eqlist.size()>0){
				for(ExamQuestion eq:eqlist){
					float teamRate=0.0f;
					float gradeRate=0.0f;
					if(eq.getTeamScoringRate()!=null){
						teamRate=eq.getTeamScoringRate()*100;
				        DecimalFormat df = new DecimalFormat("0");
				        String num3 = df.format(teamRate);
				        teamRate=Float.parseFloat(num3);           
					}
					if(eq.getGradeScoringRate()!=null){
						gradeRate=eq.getGradeScoringRate()*100;
						DecimalFormat df = new DecimalFormat("0");
				        String num2 = df.format(gradeRate);
				        gradeRate=Float.parseFloat(num2);   
					}
					teamRightlist.add(teamRate);
					gradeRightlist.add(gradeRate);
					teamRanklist.add(eq.getGradeRank());
				}
			}
		map.put("team", teamRightlist);
		map.put("grade", gradeRightlist);
		map.put("rank", teamRanklist);
		return map;
	}
	
	@RequestMapping(value = "/checkStatisticsTaskState/h5")
	@ResponseBody
	public Integer checkStatisticsTaskState(@RequestParam(value = "ownerId", required = true)Integer ownerId){
		Integer state = null;
		StatisticsTaskCondition statisticsTaskCondition = new StatisticsTaskCondition(); 
		statisticsTaskCondition.setTaskId(ownerId);
		List<StatisticsTask> statisticsTaskList = statisticsTaskService.findStatisticsTaskByCondition(statisticsTaskCondition);
		if(statisticsTaskList != null &&statisticsTaskList.size() > 0){
			StatisticsTask statisticsTask = statisticsTaskList.get(0);
			state = statisticsTask.getState();
		}
		return state;
		
	}
	
	//单题分析
	@RequestMapping(value = "/single/h5")
	public String singleIndex(Model uiModel,
			@RequestParam("relateId")Integer relateId,
			@RequestParam("taskId")Integer taskId,
			@RequestParam("questionUuId")String questionUuId,
			@RequestParam("examIdString")Integer examIdString,
			@RequestParam("paperId")Integer paperId
			) {
		List<ExamQuestionVo> gradeVos = new ArrayList<ExamQuestionVo>();
		List<ExamQuestionVo> teamVos = new ArrayList<ExamQuestionVo>();
        List<TeamQuestionOptions> toList = this.userQuestionService.getTeamQuestionOptionsByQuestionUuIdAndOwnerId(relateId, taskId, questionUuId,null);
		float avg = 0;
//		List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examIdString);
		PjExam pj=this.pjExamService.findPjExamById(examIdString);
		List<ExamTeamNumber> teamlist=this.pjExamService.findTeamNumberOfCode(pj.getJointExamCode());
		Map<Integer,Integer> teamMap=new HashMap<Integer, Integer>();
		if(teamlist!=null&&teamlist.size()>0){
			for(ExamTeamNumber et:teamlist){
				teamMap.put(et.getExamId(), et.getTeamNumber());
			}
		}
		DecimalFormat decimalFormat = new DecimalFormat("0.00");// 
			List<ExamQuestion> eqlist=this.examQuestionService.findExamQuestionByJointExamCode(pj.getJointExamCode(), questionUuId);
			for(ExamQuestion eq:eqlist)
			{   
				String teamNumber="";
				int number=0;
				ExamQuestionVo vo=new ExamQuestionVo();
				if(teamMap.get(eq.getExamId())!=null){
					number=(Integer)teamMap.get(eq.getExamId());
					teamNumber=String.valueOf(number);
				}
				vo.setTeamName(teamNumber);
				float teamRate=0.0f;
				if(eq.getTeamScoringRate()!=null){
					teamRate=eq.getTeamScoringRate()*100;
			        DecimalFormat df = new DecimalFormat("0");
			        String num3 = df.format(teamRate);
			        teamRate=Float.parseFloat(num3);
				}
				if(eq.getGradeScoringRate()!=null){
					avg=eq.getGradeScoringRate()*100;
			        DecimalFormat df = new DecimalFormat("0");
			        String num2 = df.format(avg);
			        avg=Float.parseFloat(num2);
				}
				vo.setExamId(eq.getExamId());
				vo.setRightRate(teamRate);
				vo.setQuestionType(eq.getQuestionType());
				vo.setAnswerCount(eq.getAnswerCount());
				vo.setFullScore(eq.getFullScore());
				gradeVos.add(vo);
				if(eq.getExamId().intValue()==pj.getId().intValue()){
					teamVos.add(vo);
				}
			}
		List<PaperQuestionResult> qList = new ArrayList<PaperQuestionResult>();
		qList = this.paperQuestionService.findPaperQuestionByPaperId(paperId, questionUuId);
		uiModel.addAttribute("qList", qList);
		String gradeAvg = decimalFormat.format(avg);
		uiModel.addAttribute("gradeAnwer", gradeVos);
		uiModel.addAttribute("teamVos", teamVos);
		uiModel.addAttribute("avg", gradeAvg);
		uiModel.addAttribute("toList", toList);
		return DIR + "/topic-statistics";
	}
	
	*//**
	 * 统计方法入口
	 *//*
	@RequestMapping(value = "/generate/h5")
	@ResponseBody
	public String index(Model model,@CurrentUser UserInfo user, @RequestParam(value = "examId", required = false) Integer examId,
			@RequestParam(value = "paperId", required = false) Integer paperId,
			@RequestParam(value = "ownerId", required = false) Integer ownerId,
			@RequestParam(value = "type",required = false) Integer type) {
	
		List<StatisticsTask> statisticsTaskList = null;
		// 加入线程锁
		Lock lock = new ReentrantLock();
		lock.lock();
		long startTime=System.currentTimeMillis();   //获取开始时间  
		try {
			StatisticsTaskCondition statisticsTaskCondition = new StatisticsTaskCondition();
			statisticsTaskCondition.setTaskId(ownerId);
			//statisticsTaskCondition.setType(type);
			statisticsTaskList = this.statisticsTaskService.findStatisticsTaskByCondition(statisticsTaskCondition);
			if(statisticsTaskList != null && statisticsTaskList.size() > 0){
				StatisticsTask statisticsTask = statisticsTaskList.get(0);
				if(statisticsTask.getState() == 0){ //正在统计中。。。。
					return "0";
				}else{
					statisticsTask.setState(0);
					statisticsTask.setModifyDate(new Date());
					statisticsTaskService.modify(statisticsTask);
				}
			}else{
				StatisticsTask statisticsTask =new StatisticsTask();
				statisticsTask.setState(0);
				statisticsTask.setTaskId(ownerId);
			//	statisticsTask.setType(type);
				//statisticsTask.setUserId(user.getId());
				statisticsTask.setCreateDate(new Date());
				statisticsTaskService.add(statisticsTask);
				statisticsTaskList = this.statisticsTaskService.findStatisticsTaskByCondition(statisticsTaskCondition);
			}
		
			if(statisticService.statisticHandle(examId,paperId,ownerId,type)){
				if(statisticsTaskList != null && statisticsTaskList.size() > 0){
					StatisticsTask statisticsTask = statisticsTaskList.get(0);
					statisticsTask.setState(1); //统计完成 设置任务状态为已完成。
					this.statisticsTaskService.modify(statisticsTask);
			 }
			};
			
			
		} finally {
			// 释放锁
			 
			 lock.unlock();
			 long endTime = System.currentTimeMillis();  
			 System.out.println("程序运行时间： "+((endTime-startTime) )+"毫秒");  
			 
		}

		return "1";
	}
	
}
*/