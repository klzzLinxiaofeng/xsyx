/**   
* @Title: StatisticsController.java
* @Package platform.szxyzxx.web.statistic 
* @Description: 统计控制器
* @author pantq   
* @date 2017年3月23日 上午9:38:31 
* @version V1.0   
*/
package platform.szxyzxx.web.statistic.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.exam.model.ExamPublish;
import platform.education.exam.model.ExamRelate;
import platform.education.exam.service.ExamPublishService;
import platform.education.exam.service.ExamRelateService;
import platform.education.exam.vo.ExamRelateCondition;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ExamQuestionVo;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.generalcode.model.KnowledgeNode;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.*;
import platform.education.paper.service.*;
import platform.education.paper.vo.CognitionCountVo;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.TaskTeamCondition;
import platform.education.paper.vo.UserQuestionCondition;
import platform.education.resource.model.StatisticsTask;
import platform.education.resource.service.KnowledgeResourceSummaryService;
import platform.education.resource.service.StatisticsTaskService;
import platform.education.resource.vo.StatisticsTaskCondition;
import platform.szxyzxx.services.statistic.service.StatisticService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.statistic.vo.TeamBasicVo;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: StatisticsController
 * @Description: 统计控制器
 * @author pantq
 * @date 2017年3月23日 上午9:38:31
 * 
 */

@Controller
@RequestMapping("/statistic")
public class StatisticsController extends BaseController {

	@Autowired
	@Qualifier("paperHandleService")
	private PaperHandleService paperHandleService;

	@Autowired
	@Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;

	@Autowired
	@Qualifier("examQuestionService")
	private ExamQuestionService examQuestionService;
	@Autowired
	@Qualifier("paperQuestionService")
	private PaperQuestionService paperQuestionService;

	@Autowired
	@Qualifier("userPaperService")
	private UserPaperService userPaperService;
	
	@Autowired
	@Qualifier("userWrongService")
	private UserWrongService userWrongService;
	
	@Autowired
	@Qualifier("questionService")
	private QuestionService questionService;
	
	@Autowired
	@Qualifier("papaperService")
	private PaperService paperService;
	
	
	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
	@Qualifier("examStatService")
	private ExamStatService examStatService;
	
	@Autowired
	@Qualifier("statisticsTaskService")
	private StatisticsTaskService statisticsTaskService;
	
	@Autowired
	@Qualifier("statisticService")
	private StatisticService statisticService;

	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;

	@Autowired
	@Qualifier("pjExamService")
	private PjExamService pjExamService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;

	@Autowired
	@Qualifier("knowledgeNodeService")
	private KnowledgeNodeService knowledgeNodeService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@Autowired
	@Qualifier("examStudentService")
	private ExamStudentService examStudentService;

	@Autowired
	@Qualifier("knowledgeResourceSummaryService")
	private KnowledgeResourceSummaryService knowledgeResourceSummaryService;

	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	@Autowired
	@Qualifier("lpTaskExamUnitService")
	private LpTaskExamUnitService lpTaskExamUnitService;
	
	@Autowired
	@Qualifier("examPublishService")
	private ExamPublishService examPublishService;
	@Autowired
	@Qualifier("examRelateService")
	private ExamRelateService examRelateService;
	
	@Autowired
	@Qualifier("taskTeamService")
	private TaskTeamService taskTeamService;
	
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	
	
	private static final String DIR = "exam/statistics";

	/**
	 * 查看问卷
	 * @param uiModel
	 * @param paperId
	 * @param canReturn 是否需要返回按钮,默认需要要,导学案预览试卷时不需要
	 * @return
	 */
	@RequestMapping(value = "/paper")
	public String paperIndex(Model uiModel ,@RequestParam("paperId")Integer paperId, @RequestParam(value="canReturn", required=false, defaultValue="true") Boolean canReturn) {
		List<PaperQuestionResult> qList = this.paperQuestionService.findPaperQuestionByPaperId(paperId, null);
		uiModel.addAttribute("canReturn", canReturn);
		uiModel.addAttribute("qList", qList);
		return DIR + "/paper";
	}

	@RequestMapping(value = "/ggg/kkk")
	public String countKnoledgeNode(@RequestParam("treeId")Integer rootId){
		List<KnowledgeNode> list = knowledgeNodeService.findKnowledgeNodeByTreeId(rootId,null,null);
		if(list != null && list.size() > 0){
			for(KnowledgeNode kk : list){
				if(kk != null){
					System.out.println("============开始统计节点  "+kk.getName()+"   的信息。。。=============");
					knowledgeResourceSummaryService.saveOrUpdate(kk.getId(),null);
				}
			}
			System.out.println("统计完成");
		}
		return "SUCCESS";
	}
	
	
	/**
	 * 查看问卷
	 * 
	 * @param paper_uuid
	 * @return
	 */
	@RequestMapping(value = "/updateData")
	public String updateData(@RequestParam("paper_uuid") String paper_uuid) {
			UserQuestionCondition condition = new UserQuestionCondition();
			condition.setPaperUuid(paper_uuid);
			System.out.println("paper_uuid================" + paper_uuid);
			final List<UserQuestion> uqs = this.userQuestionService.findUserQuestionByCondition(condition);
			new Thread(new Runnable(){  
				public void run(){  
					int i=0;
	            	for (UserQuestion uq : uqs) {
	    				System.out.println("循环====第"+i+"====次");
	    				Question question = questionService.findQuestionByUuid(uq.getQuestionUuid());
	    				if (uq.getAnswer().equals(question.getCorrectAnswer())) {
	    					uq.setIsCorrect(true);
	    					uq.setScore(question.getScore());
	    				} else {
	    					uq.setIsCorrect(false);
	    					uq.setScore(0d);
	    				}
	    				userQuestionService.modify(uq);
	    				i++;
	    			}
	            	
	            }}).start(); 
			
			return null;
		}

	
	

	/**
	 * 查看问卷
	 * 
	 * @param relateId
	 * @param examIdString
	 * @return
	 */
	@RequestMapping(value = "/volume")
	public String volumeIndex(Model uiModel,@RequestParam("relateId")Integer relateId,@RequestParam("examIdString")Integer examIdString,@RequestParam("paperId")Integer paperId) {
		
		//数据格式化
		DecimalFormat decimalFormat=new DecimalFormat("0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		DecimalFormat decimalFormat1=new DecimalFormat("0.00");
		List<PaperStatisticResult> list = this.examStatService.findPaperStatisticByExamId(examIdString,1);
		List<PaperStatisticResult> teamList = this.examStatService.findPaperStatisticByExamId(examIdString,null);
		//通过examId查找改次任务的学生信息
		List<ExamStudent> svos = this.examStudentService.findExamStudentsByExamId(examIdString);
		float gradeAvg = 0;
		float teamAvg = 0;
		Integer gradeRank = 0;
		float sum = 0;
		int num = 0;
		
		List<ExamQuestionVo> vos = null;
		List<ExamQuestionVo> newVos = new ArrayList<ExamQuestionVo>();
		for (PaperStatisticResult p : list) {
			float score = 0;
			if (p.getAverageScore() != null) { //只统计参加考试的班级，缺考不计入统计
				score = p.getAverageScore();
				num++;
			}
			//计算班级总分
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
		if (num > 0) { //计算年级平均分   所有参考考试的班级平均分之和 / 参考考试的班级数
			gradeAvg = sum / num;
		}
		List<PaperQuestionResult> qList = this.paperQuestionService.findPaperQuestionByPaperId(paperId, null);
		uiModel.addAttribute("qList", qList);
		String gradeAvgStr = decimalFormat1.format(gradeAvg);
		String teamAvgStr = decimalFormat1.format(teamAvg);
		ExamStat et=examStatService.findExamStatByExamId(examIdString);
		uiModel.addAttribute("status", et.getDataChanged());
		uiModel.addAttribute("teamId", relateId);
		uiModel.addAttribute("gradeAvg", gradeAvgStr);
		uiModel.addAttribute("teamAvg", teamAvgStr);
		uiModel.addAttribute("gradeRank", gradeRank);
		uiModel.addAttribute("list", list);
		uiModel.addAttribute("teamList", teamList);
		uiModel.addAttribute("svos", svos);
		ExamStat examStat = examStatService.findExamStatByExamId(examIdString);
		Integer totalCount = 0;
		if(examStat != null){
			totalCount = examStat.getStudentCount();
			if (totalCount == null) {
				totalCount = 0;
			}	
		}
		
		List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examIdString);
		int totalStudent = 0;
		if(examStudentList !=null && examStudentList.size() >0){
			totalStudent = examStudentList.size();
		}
		PjExam pj=new PjExam();
		pj=pjExamService.findPjExamById(examIdString);
			ExamQuestionCondition condion =new ExamQuestionCondition();
			condion.setExamId(examIdString);
			List<ExamQuestion> eqlist=this.examQuestionService.findExamQuestionByCondition(condion);
			if(eqlist!=null&&eqlist.size()>0){
				for(ExamQuestion eq:eqlist){
					ExamQuestionVo vo=new ExamQuestionVo();
					vo.setQuestionType(eq.getQuestionType());
					if(eq.getAnswerCount()==null||eq.getAnswerCount()==0){
						vo.setAnswerCount(0);
						vo.setFinishRate(0.0f);
						vo.setRightRate(0.0f);
					}else{
						vo.setAnswerCount(eq.getAnswerCount());
						String finishRate = decimalFormat.format((eq.getAnswerCount() / Float.valueOf(totalStudent + "")) * 100);
						String rightRate = decimalFormat.format(eq.getTeamScoringRate() * 100);
						vo.setFinishRate(Float.valueOf(finishRate));
						vo.setRightRate(Float.valueOf(rightRate));
					}
					if(eq.getRightAnswerCount()==null){
						vo.setRightAnswerCount(0);
					}else{
						vo.setRightAnswerCount(eq.getRightAnswerCount());
					}
					newVos.add(vo);
				}
			}
		uiModel.addAttribute("eqs", newVos);
		return DIR + "/statisticspage";

	}

	/**
	 * 题目应答情况
	 * 
	 * @param relateId
	 * @param examIdString
	 * @return
	 */
	@RequestMapping(value = "/question")
	public String questionIndex(Model uiModel,@RequestParam("relateId")Integer relateId,@RequestParam("examIdString")Integer examIdString) {

		ExamStat examStat = examStatService.findExamStatByExamId(examIdString);
		Integer totalCount = 0;
		if(examStat != null){
			totalCount = examStat.getStudentCount();
			if (totalCount == null) {
				totalCount = 0;
			}	
		}
		
		List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examIdString);
		int totalStudent = 0;
		if(examStudentList !=null && examStudentList.size() >0){
			totalStudent = examStudentList.size();
		}
		
		
		
		List<ExamQuestionVo> vos = this.examQuestionService.findExamQuestionByExamIdAndTeamId(relateId,examIdString, null);
		// 正答率
		DecimalFormat decimalFormat = new DecimalFormat("0.00");// 构造方法的字符格式这里如果小数不足2位,会以0补足.

		if (vos != null && vos.size() > 0) {
			for (ExamQuestionVo examQuestionVo : vos) {
				Integer answerCount = examQuestionVo.getAnswerCount();
				if (answerCount == null) {
					answerCount = 0;
				} 
				if (answerCount != 0 && totalCount != 0) {
					
					String rightRate = decimalFormat
							.format((examQuestionVo.getRightAnswerCount() / Float.valueOf(totalCount + "")) * 100);
					String finishRate = decimalFormat
							.format((examQuestionVo.getAnswerCount() / Float.valueOf(totalStudent + "")) * 100);
					examQuestionVo.setRightRate(Float.valueOf(rightRate));
					examQuestionVo.setFinishRate(Float.valueOf(finishRate));
				}
			}
		}

		uiModel.addAttribute("list", vos);
		return DIR + "/question";

	}

	@RequestMapping(value = "/single")
	public String singleIndex(Model uiModel,@RequestParam("relateId")Integer relateId,@RequestParam("taskId")Integer taskId,@RequestParam("questionUuId")String questionUuId,@RequestParam("examIdString")Integer examIdString,@RequestParam("paperId")Integer paperId) {
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
	@RequestMapping(value = "/paperJson")
	@ResponseBody
	public  Map<String,Object>  paperJson(@RequestParam("paperId")Integer paperId,@RequestParam("relateId")Integer relateId,@RequestParam("examStringId")Integer examIdString){
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
	
	
	
	
	 /**
     * 根据学号查询该学生的排名
     * @param list
     * @param studentCode
     * @return
     */
    private static int getSort(List<ExamQuestionVo> examQuestionVoList, String studentCode) {
        //因为是排名,所以降序排序
        Collections.sort(examQuestionVoList, new Comparator<ExamQuestionVo>() {
            @Override
            public int compare(ExamQuestionVo o1, ExamQuestionVo o2) {
                if (o1.getAverageScore() > o2.getAverageScore()) {
                    return -1;
                } else if (o1.getAverageScore() == o2.getAverageScore()) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        int sort = 0;
        //因为list从0开始,所以要+1
        for (int i = 0; i < examQuestionVoList.size(); i++) {
            if(examQuestionVoList.get(i).getAverageScore().equals(studentCode)){
                sort = i+1;
                break;
            }
        }
        return sort;
    }
	
	
	
	/**
	 * 统计方法入口
	 * @param model
	 * @param user
	 * @param examId
	 * @param paperId
	 * @param ownerId 
	 * @return
	 */
	@RequestMapping(value = "/index")
	@ResponseBody
	public String index(Model model,@CurrentUser UserInfo user, @RequestParam(value = "examId") Integer examId,
			@RequestParam(value = "paperId") Integer paperId,
			@RequestParam(value = "ownerId", required = true) Integer ownerId) {
	
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
				if(user != null) {
					statisticsTask.setUserId(user.getId());
				}
				statisticsTask.setCreateDate(new Date());
				statisticsTaskService.add(statisticsTask);
				statisticsTaskList = this.statisticsTaskService.findStatisticsTaskByCondition(statisticsTaskCondition);
			}
		
			if(statisticService.statisticHandle(examId,paperId,ownerId,null)){
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
	
	
	
	
	/**
	 * 
	 * @Title: index @author pantq @Description: 统计入口 @param model @param examId
	 * pj_exam 表的ID @return 设定文件 @return ModelAndView 返回类型 @throws
	 *//*
	@RequestMapping(value = "/index")
	@ResponseBody
	public String index(Model model, @RequestParam(value = "examId", required = true) Integer examId,
			@RequestParam(value = "paperId", required = true) Integer paperId,
			@RequestParam(value = "ownerId", required = true) Integer ownerId) {

		List<ExamStat> examStatList = new ArrayList<ExamStat>(); 
		List<ExamStudent> examStudentList = new ArrayList<ExamStudent>();
		List<ExamQuestion> examQuestionList = new ArrayList<ExamQuestion>();
		
		
		// 加入线程锁
		Lock lock = new ReentrantLock();
		lock.lock();
		long startTime=System.currentTimeMillis();   //获取开始时间  
		try {
			// 1. 根据examId去pj_exam表查找记录
			
			long startTime1=System.currentTimeMillis();
			PjExam newExam = this.pjExamService.findPjExamById(examId);
			long endTime1=System.currentTimeMillis();
			// System.out.println("findPjExamById 程序运行时间： "+((endTime1-startTime1) )+"毫秒");  
			
			// 2. 根据joint_exam_code值去查找同一一次任务多个班级的记录
			PjExamCondition newpjExamCondition = new PjExamCondition();
			newpjExamCondition.setJointExamCode(newExam.getJointExamCode());
			long endTime2=System.currentTimeMillis();
			List<PjExam> pjExamList = this.pjExamService.findPjExamByCondition(newpjExamCondition);
			long startTime2=System.currentTimeMillis();
			// System.out.println("findPjExamByCondition 程序运行时间： "+((endTime2-startTime2) )+"毫秒");
			if (pjExamList != null && pjExamList.size() > 0) {
				for (PjExam pjExam : pjExamList) {
					// 更新 pj_exam_stat 表的记录
					
					long startTime3=System.currentTimeMillis();
					ExamStat examStat = this.examStatService.findExamStatByExamId(pjExam.getId());
					long endTime3=System.currentTimeMillis();
					// System.out.println("findExamStatByExamId 程序运行时间： "+((endTime3-startTime3) )+"毫秒");
					
					if (examStat != null && examStat.getDataChanged() == true) {
						// 查找班级总分
						long startTime4=System.currentTimeMillis();
						Float sumScore = this.userPaperService.countUserPaperTeamTotalScore(ownerId, pjExam.getTeamId(),null,PaperType.EXAM);
						long endTime4=System.currentTimeMillis();
					//	 System.out.println("countUserPaperTeamTotalScore 程序运行时间： "+((endTime4-startTime4) )+"毫秒");
						
						if(sumScore != null){
							examStat.setTotalScore((float) sumScore);// 班级总分
						}

						// 查询用户作答情况。
						UserPaperCondition userPaperCondition = new UserPaperCondition();
						userPaperCondition.setOwnerId(ownerId);
						userPaperCondition.setType(PaperType.EXAM); //考试类型
						userPaperCondition.setTeamId(pjExam.getTeamId());
						Integer answerCount = 0;
						long startTime5=System.currentTimeMillis();
						List<UserPaper> userPaperList = this.userPaperService.findUserPaperAnswerCountByCondition(userPaperCondition);
						long endTime5=System.currentTimeMillis();
					//	System.out.println("findUserPaperAnswerCountByCondition 程序运行时间： "+((endTime5-startTime5) )+"毫秒");
						if (userPaperList != null && userPaperList.size() > 0) {
							answerCount = userPaperList.size();
							examStat.setStudentCount(answerCount);// 已作答总人数
							examStat.setAverageScore((float) sumScore / answerCount);// 平均分 = 班级总分 / 班级作答人数
						}
						
						examStatList.add(examStat);

					}

					// 更新 pj_exam_student表
					long startTime6=System.currentTimeMillis();
					List<UserRank> userRankList = paperHandleService.findUserPaperByPaperId(paperId, PaperType.EXAM, ownerId, null);
					long endTime6=System.currentTimeMillis();
					//System.out.println("findUserPaperByPaperId 程序运行时间： "+((endTime6-startTime6) )+"毫秒");
					
					if (userRankList != null && userRankList.size() > 0) {
						for (UserRank userRank : userRankList) {
							
							ExamStudent examStudent = this.examStudentService.findExamStudentByExamIdAndUserId(pjExam.getId(), userRank.getUserId());
							if(examStudent != null){
								// 学生总分
								if(userRank.getScore() != null){
									examStudent.setScore(Float.valueOf(userRank.getScore() + ""));
								}else{
									examStudent.setScore(0f);
								}
								// 正答总数
								UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
								userQuestionCondition.setIsCorrect(true);
								userQuestionCondition.setOwnerId(ownerId);
								userQuestionCondition.setUserId(userRank.getUserId());
								
								long startTime7=System.currentTimeMillis();
								Long rightAnswerCount = userQuestionService.count(userQuestionCondition);
								long endTime7=System.currentTimeMillis();
								System.out.println("正答总数 程序运行时间： "+((endTime7-startTime7) )+"毫秒");
								examStudent.setRightAnswerCount(Integer.parseInt(rightAnswerCount + ""));

								// 总应答题数
								UserQuestionCondition uQuestionCondition = new UserQuestionCondition();
								uQuestionCondition.setOwnerId(ownerId);
								uQuestionCondition.setUserId(userRank.getUserId());
								
								long startTime8=System.currentTimeMillis();
								Long answerCount = userQuestionService.count(uQuestionCondition);
								long endTime8=System.currentTimeMillis();
								System.out.println("总应答题数 程序运行时间： "+((endTime8-startTime8) )+"毫秒");
								examStudent.setRightAnswerCount(Integer.parseInt(rightAnswerCount + ""));
								
								
								examStudent.setAnswerCount(Integer.parseInt(answerCount + ""));
								// 更新班级排名
								examStudent.setTeamRank(userRank.getRank());
								examStudentList.add(examStudent);
								Integer examQuestionAnswerCount = 0;
								Integer emptyCount = 0;
								Integer examQuestionRightAnswerCount = 0;
								float totalScore = 0;

								if (pjExam != null) {
									
									long startTime9=System.currentTimeMillis();
									List<UserQuestionResult> userQuestionResultList = this.userQuestionService.findUserQuestionByOwnerId(ownerId,null,PaperType.EXAM);
									long endTime9=System.currentTimeMillis();
									System.out.println("findUserQuestionByOwnerId 程序运行时间： "+((endTime9-startTime9) )+"毫秒");
									if (userQuestionResultList != null && userQuestionResultList.size() > 0) {
									
										for (UserQuestionResult userQuestionResult : userQuestionResultList) {
											List<ExamQuestionVo> list = examQuestionService.findExamQuestionByExamIdAndTeamId(pjExam.getTeamId(), pjExam.getId(),userQuestionResult.getQuestionUuid());

											for (ExamQuestionVo vo : list) {
												this.examQuestionService.remove(vo);
											}
											UserQuestionCondition userQuestionConditionAnswerCount = new UserQuestionCondition();
											userQuestionConditionAnswerCount.setQuestionUuid(userQuestionResult.getQuestionUuid());
											userQuestionConditionAnswerCount.setOwnerId(ownerId);
											userQuestionConditionAnswerCount.setTeamId(pjExam.getTeamId());
											userQuestionConditionAnswerCount.setType(PaperType.EXAM);
											examQuestionAnswerCount = Integer
													.valueOf(this.userQuestionService.count(userQuestionConditionAnswerCount) + "");
											
											UserQuestionCondition userQuestionConditionEmptyCount = new UserQuestionCondition();
											userQuestionConditionEmptyCount.setQuestionUuid(userQuestionResult.getQuestionUuid());
											userQuestionConditionEmptyCount.setOwnerId(ownerId);
											userQuestionConditionEmptyCount.setTeamId(pjExam.getTeamId());
											userQuestionConditionEmptyCount.setType(PaperType.EXAM);
											userQuestionConditionEmptyCount.setAnswer("[]");
											emptyCount = Integer
													.valueOf(this.userQuestionService.count(userQuestionConditionEmptyCount) + "");

											UserQuestionCondition uqA = new UserQuestionCondition();
											uqA.setQuestionUuid(userQuestionResult.getQuestionUuid());
											uqA.setOwnerId(ownerId);
											uqA.setTeamId(pjExam.getTeamId());
											uqA.setIsCorrect(true);
											examQuestionRightAnswerCount = Integer
													.valueOf(this.userQuestionService.count(uqA) + "");

											Long total = this.userQuestionService.findSumScore(ownerId, pjExam.getTeamId(),
													userQuestionResult.getQuestionUuid());
											if (total != null) {
												totalScore = Float.valueOf(total);
											}
											
											ExamQuestion examQuestion = new ExamQuestion();
											if (answerCount != 0) {//本班总得分 / 总作答人数
												examQuestion.setAverageScore((totalScore / answerCount));
											} 

											examQuestion.setQuestionUuid(userQuestionResult.getQuestionUuid());
											examQuestion.setExamId(pjExam.getId());
											examQuestion.setQuestionType(userQuestionResult.getQuestionType());
											examQuestion.setAnswerCount(examQuestionAnswerCount);
											examQuestion.setEmptyCount(emptyCount);
											examQuestion.setScore(Double.valueOf(totalScore));
											examQuestion.setRightAnswerCount(examQuestionRightAnswerCount);
											
											examQuestion.setCreateDate(new Date());
											examQuestion.setModifyDate(new Date());
											examQuestion.setIsDeleted(false);
											examQuestionList.add(examQuestion);
											
										}
									}

								}
							}
								
							}
					}
				}
			}

		} finally {
			// 释放锁
			lock.unlock();
			
			 long endTime = System.currentTimeMillis();  
			 System.out.println("整个程序运行时间： "+((endTime-startTime) )+"毫秒");  
			 
		}

		return "success";
	}
	*/
	/**
	 * 定时检查统计任务状态
	 * @param ownerId 任务ID
	 * @return
	 */
	@RequestMapping(value = "/checkStatisticsTaskState")
	@ResponseBody
	public Integer checkStatisticsTaskState(@RequestParam(value = "ownerId", required = true)Integer ownerId){
		Integer state = null;
		StatisticsTaskCondition statisticsTaskCondition = new StatisticsTaskCondition(); 
		statisticsTaskCondition.setTaskId(ownerId);
		List<StatisticsTask> statisticsTaskList = this.statisticsTaskService.findStatisticsTaskByCondition(statisticsTaskCondition);
		if(statisticsTaskList != null &&statisticsTaskList.size() > 0){
			StatisticsTask statisticsTask = statisticsTaskList.get(0);
			state = statisticsTask.getState();
		}
		return state;
		
	}

	/**
	 * @function 添加URL可以添加参数，当是超级管理员登录的时候，
	 * 			 可以指定一所学校来选择
	 * @param model
	 * @param schoolId
	 * @param userInfo
     * @return
     */
	@RequestMapping(value = "/checkTeamTaskCount")
	public ModelAndView checkTeamTaskCount(
			Model model,
			@RequestParam(value = "schoolId",required = false) Integer schoolId,
			@CurrentUser UserInfo userInfo){
		if(userInfo != null){
			if(schoolId != null){
				School school = schoolService.findSchoolById(schoolId);
				if(school != null){
					model.addAttribute("schoolId",school.getId());
				}
			}else {
				if(userInfo.getSchoolId() != null){
					School school = schoolService.findSchoolById(userInfo.getSchoolId());
					model.addAttribute("school",school);
				}
			}
		}
		return new ModelAndView(DIR + "/teamExamSelect",model.asMap());
	}

	@RequestMapping(value = "/getTeamTaskData")
	public ModelAndView getTeamTaskData(
			Model model,
			@ModelAttribute("condition") PjExamCondition pjExamCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order
			){
		pjExamCondition.setIsDelete(false);
		List<PjExamVo> list = pjExamService.findPjExamMoreByCondition(pjExamCondition,page,Order.desc("exam_date"));
		model.addAttribute("exams",list);
		return new ModelAndView(DIR + "/teamExamSelectList",model.asMap());
	}

	@RequestMapping(value = "/toStatisticPage")
	public ModelAndView toStatisticPage(
			Model model,
			@RequestParam(value = "examId", required = true)Integer examId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);

		//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		//Object result = this.jcGcCacheService.getNameByValue("XY-JY-KSLX", pjExam.getExamType());

		String schoolYear = "";
		SchoolTermCondition stc = new SchoolTermCondition();
		stc.setSchoolYear(pjExam.getSchoolYear());
		stc.setCode(pjExam.getTermCode());
		stc.setIsDelete(false);
		List<SchoolTermVo> list = schoolTermService.findSchoolTermByConditionMore(stc,null,null);

		Paper paper = null;
		if(pjExam != null){
			paper = paperService.findPaperByUuid(pjExam.getPaperUuid());
		}

		if(list != null && list.size() > 0){
			schoolYear = pjExam.getSchoolYear()+"年"+list.get(0).getName();
		}

		model.addAttribute("paper",paper);
		model.addAttribute("schoolYear",schoolYear);
		model.addAttribute("exam",pjExam);
		return new ModelAndView(DIR + "/statisticPage",model.asMap());
	}

	public Integer[] getExamIds(String jointExamCode,Integer gradeId){
		PjExamCondition pjExamCondition = new PjExamCondition();
		pjExamCondition.setJointExamCode(jointExamCode);
		pjExamCondition.setGradeId(gradeId);
		pjExamCondition.setIsDelete(false);
		List<PjExam> pjExamList = pjExamService.findPjExamByCondition(pjExamCondition);

		Integer[] examIds = new Integer[pjExamList.size()];
		if(pjExamList != null && pjExamList.size() > 0){
			for(int i = 0; i < pjExamList.size(); i++){
				examIds[i] = pjExamList.get(i).getId();
			}
		}
		return examIds;
	}

	/**
	 * @function 根据一个考试ID获取这个班级的基本考试统计情况
	 * @param examId
     * @return
     */
	@RequestMapping(value = "/teamBasic")
	@ResponseBody
	public Object getTeamBasic(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		TeamBasicVo teamBasicVo = new TeamBasicVo();
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			teamBasicVo.setSubjectCode(pjExam.getSubjectCode());
			teamBasicVo.setTeacherId(pjExam.getTeacherId());

			Teacher teacher = teacherService.findTeacherById(pjExam.getTeacherId());
			if(teacher != null){
				teamBasicVo.setTeacherName(teacher.getName());
			}

			Object object = jcCacheService.findUniqueByParam("jc_subject","code",pjExam.getSubjectCode(),"name");
			if(object != null){
				teamBasicVo.setSubjectName(object.toString());
			}
		}

		if(pjExam != null){
			Team team = teamService.findTeamById(pjExam.getTeamId());
			if(team != null){
				teamBasicVo.setTeamId(team.getId());
				teamBasicVo.setTeamName(team.getName());
				teamBasicVo.setExamDate(pjExam.getExamDate());
			}
		}

		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		if(examStat != null){
			teamBasicVo.setAvgScore(examStat.getAverageScore());
			teamBasicVo.setPassScore(examStat.getPassScore());
			teamBasicVo.setStudentCount(examStat.getStudentCount());
			teamBasicVo.setFullScore(examStat.getFullScore());
			teamBasicVo.setQuestionCount(examStat.getQuestionCount());
			teamBasicVo.setHightScore(examStat.getHighestScore());
			teamBasicVo.setLowScore(examStat.getLowestScore());
			teamBasicVo.setGradeRank(examStat.getGradeRank());
			teamBasicVo.setSdScore(examStat.getSdScore());
			teamBasicVo.setMadValue(examStat.getMadValue());
			teamBasicVo.setMovValue(examStat.getMovValue());
		}

		return teamBasicVo;
	}

	@RequestMapping(value = "/teamScoreCompare")
	@ResponseBody
	public Object getTeamScoreCompare(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		Map map = new HashMap();
		PjExam pjExam = pjExamService.findPjExamById(examId);
		Float gradeAvg = 0f;
		Float teamScore = 0f;
		Float hightScore = 0f;
		Float lowScore = 0f;

		//获取联考信息
		if(pjExam != null){
			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());

			List<ExamStatVo> list = examStatService.findExamStatByExamIds(examIds);
			int teamSize = 0;
			if(list != null && list.size() > 0){
				for(ExamStat examStat : list){
					if(examId.equals(examStat.getExamId())){
						teamScore = examStat.getAverageScore();
						hightScore = examStat.getHighestScore();
						lowScore = examStat.getLowestScore();
					}

					if(examStat.getAverageScore() != null){
						gradeAvg += examStat.getAverageScore();
					}

					int sc = examStat.getStudentCount() == null ? 0 : examStat.getStudentCount();
					if(sc != 0){
						teamSize++;
					}
				}
				gradeAvg = gradeAvg/teamSize;
			}
		}

		map.put("gradeAvg",gradeAvg);
		map.put("lowScore",lowScore);
		map.put("teamAvg",teamScore);
		map.put("hightScore",hightScore);
		return map;
	}

	@RequestMapping(value = "/teamScoreCompareMe")
	@ResponseBody
	public Object getTeamScoreCompareMe(
			@RequestParam(value = "examId", required = true)Integer examId,
			@RequestParam(value = "studentId", required = true)Integer studentId
	){
		Map map = new HashMap();
		PjExam pjExam = pjExamService.findPjExamById(examId);
		Float gradeAvg = 0f;
		Float teamScore = 0f;
		Float hightScore = 0f;
		Float lowScore = 0f;
		Float mineScore = 0f;

		//获取联考信息
		if(pjExam != null){
			Student student = studentService.findStudentById(studentId);
			if(student != null){
				ExamStudent examStudent = examStudentService.findExamStudentByExamIdAndUserId(examId,student.getUserId());
				if(examStudent != null){
					mineScore = examStudent.getScore();
				}
			}

			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());

			List<ExamStatVo> list = examStatService.findExamStatByExamIds(examIds);
			int teamSize = 0;
			if(list != null && list.size() > 0){
				for(ExamStat examStat : list){
					if(examId.equals(examStat.getExamId())){
						teamScore = examStat.getAverageScore();
						hightScore = examStat.getHighestScore();
						lowScore = examStat.getLowestScore();
					}

					if(examStat.getAverageScore() != null){
						gradeAvg += examStat.getAverageScore();
					}

					int sc = examStat.getStudentCount() == null ? 0 : examStat.getStudentCount();
					if(sc != 0){
						teamSize++;
					}
				}
				gradeAvg = gradeAvg/teamSize;
			}
		}

		map.put("gradeAvg",gradeAvg);
		map.put("lowScore",lowScore);
		map.put("teamAvg",teamScore);
		map.put("hightScore",hightScore);
		map.put("mineScore",mineScore);
		return map;
	}

	//计算优秀人数百分比
	@RequestMapping(value = "/teamScoreSection")
	@ResponseBody
	public Object getTeamScoreSection(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		Map map = new HashMap();
		Float lowCount = 0f;
		Float passCount = 0f;
		Float hightCount = 0f;
		Float noPassCount = 0f;

		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		if(examStat != null){
			hightCount = examStat.getHighCount() == null ? hightCount : examStat.getHighCount()/examStat.getStudentCount();
			lowCount = examStat.getLowCount() == null ? lowCount : (examStat.getLowCount()-hightCount*examStat.getStudentCount())/examStat.getStudentCount();
			passCount = examStat.getPassCount() == null ? passCount : (examStat.getPassCount()-examStat.getLowCount())/examStat.getStudentCount();
			noPassCount = examStat.getStudentCount() == null ? noPassCount : 1-(passCount+lowCount+hightCount);
		}

		map.put("lowCount",lowCount*100);
		map.put("passCount",passCount*100);
		map.put("hightCount",hightCount*100);
		map.put("noPassCount",noPassCount*100);
		return map;
	}

	//计算优秀人数百分比
	@RequestMapping(value = "/teamScoreSectionConta")
	@ResponseBody
	public Object teamScoreSectionConta(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		Map map = new HashMap();
		Float lowCount = 0f;
		Float passCount = 0f;
		Float hightCount = 0f;
		Float noPassCount = 0f;

		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		if(examStat != null){
			hightCount = examStat.getHighCount() == null ? hightCount : (examStat.getHighCount()*100.0f)/examStat.getStudentCount();
			lowCount = examStat.getLowCount() == null ? lowCount : ((examStat.getLowCount())*100.0f)/examStat.getStudentCount();
			passCount = examStat.getPassCount() == null ? passCount : ((examStat.getPassCount())*100.0f)/examStat.getStudentCount();
			noPassCount = examStat.getStudentCount() == null ? noPassCount : 100-passCount;
		}

		map.put("lowCount",lowCount);
		map.put("passCount",passCount);
		map.put("hightCount",hightCount);
		map.put("noPassCount",noPassCount);
		return map;
	}

	@RequestMapping(value = "/teamScoreSectionfsd")
	@ResponseBody
	public Object getTeamScoreSectionfsd(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		Map map = new HashMap();
		Float lowCount = 0f;
		Float passCount = 0f;
		Float hightCount = 0f;
		Float noPassCount = 0f;

		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		if(examStat != null){
			hightCount = examStat.getHighCount() == null ? hightCount : examStat.getHighCount()*100.0f/examStat.getStudentCount();
			lowCount = examStat.getLowCount() == null ? lowCount : ((examStat.getLowCount() - examStat.getHighCount())*100.0f)/examStat.getStudentCount();
			passCount = examStat.getPassCount() == null ? passCount : ((examStat.getPassCount()-examStat.getLowCount())*100.0f)/examStat.getStudentCount();
			noPassCount = examStat.getStudentCount() == null ? noPassCount : 100-(passCount+lowCount+hightCount);
		}
		map.put("lowCount",lowCount);
		map.put("lowName",(examStat.getLowScore() == null ? "良好" : examStat.getLowScore()) +"-"+(examStat.getHighScore() == null ? "优秀" : examStat.getHighScore()));
		map.put("passCount",passCount);
		map.put("passName",(examStat.getPassScore() == null ? "及格" : examStat.getPassScore()) +"-"+(examStat.getLowScore() == null ? "良好" : examStat.getLowScore()));
		map.put("hightCount",hightCount);
		map.put("hightName",(examStat.getHighScore() == null ? "优秀" : examStat.getHighScore())+"-"+(examStat.getFullScore() == null ? "满分" : examStat.getFullScore()));
		map.put("noPassCount",noPassCount);
		map.put("noPassName",(examStat.getPassScore() == null ? "不及格" : examStat.getPassScore())+"以下");
		return map;
	}


	@RequestMapping(value = "/teamGradeSection")
	@ResponseBody
	public Object getTeamGradeSection(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		List list = new ArrayList();
		//本班
		Map mapOfTeamHigh = new HashMap();
		//年级最高
		Map mapOfGradeHigh = new HashMap();
		//年级最低
		Map mapOfGradeLow = new HashMap();

		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());
			Order order = new Order();
			order.setProperty("hight");
			order.setAscending(true);

			List<ThreeRatiosVo> threeRatiosVos = examStatService.findExamStatTreeRatiosByExamIds(examIds,order);
			if(threeRatiosVos != null && threeRatiosVos.size() > 0){
				//年级优秀率最低
				mapOfGradeLow.put("gradeHightOfLow",threeRatiosVos.get(0).getHight());
				//年级优秀率最高
				mapOfGradeHigh.put("gradeHightOfHight",threeRatiosVos.get(threeRatiosVos.size()-1).getHight());

				//获取本班
				for(ThreeRatiosVo tryv : threeRatiosVos){
					if(examId.equals(tryv.getExamId())){
						mapOfTeamHigh.put("teamHight",tryv.getHight());
						mapOfTeamHigh.put("teamLow",tryv.getLow());
						mapOfTeamHigh.put("teamPass",tryv.getPass());
						mapOfTeamHigh.put("teamNoPass",tryv.getNoPass());
						break;
					}
				}
			}

			order.setProperty("low");
			threeRatiosVos = examStatService.findExamStatTreeRatiosByExamIds(examIds,order);
			if(threeRatiosVos != null && threeRatiosVos.size() > 0){
				//年级良好率最低
				mapOfGradeLow.put("gradeLowOfLow",threeRatiosVos.get(0).getLow());
				//年级良好率最高
				mapOfGradeHigh.put("gradeLowOfHight",threeRatiosVos.get(threeRatiosVos.size()-1).getLow());
			}

			order.setProperty("pass");
			threeRatiosVos = examStatService.findExamStatTreeRatiosByExamIds(examIds,order);
			if(threeRatiosVos != null && threeRatiosVos.size() > 0){
				//年级及格率最低
				mapOfGradeLow.put("gradePassOfLow",threeRatiosVos.get(0).getPass());
				//年级及格率最高
				mapOfGradeHigh.put("gradePassOfHight",threeRatiosVos.get(threeRatiosVos.size()-1).getPass());
			}

			order.setProperty("noPass");
			threeRatiosVos = examStatService.findExamStatTreeRatiosByExamIds(examIds,order);
			if(threeRatiosVos != null && threeRatiosVos.size() > 0){
				//年级不及格率最低
				mapOfGradeLow.put("gradeNoPassOfLow",threeRatiosVos.get(0).getNoPass());
				//年级不及格率最高
				mapOfGradeHigh.put("gradeNoPassOfHight",threeRatiosVos.get(threeRatiosVos.size()-1).getNoPass());
			}
		}

		list.add(mapOfTeamHigh);
		list.add(mapOfGradeHigh);
		list.add(mapOfGradeLow);
		return list;
	}

	@RequestMapping(value = "/teamGradeScoreSection")
	@ResponseBody
	public Object getTeamGradeScoreSection(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		Map map = new HashMap();
		Float gradeHight = 0f;
		Float gradeLow = 0f;
		Float gradePass = 0f;
		Float gradeNoPass = 0f;

		Float teamHight = 0f;
		Float teamLow = 0f;
		Float teamPass = 0f;
		Float teamNoPass = 0f;

		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());
			List<ThreeRatiosVo> threeRatiosVoList = examStatService.findExamStatTreeRatiosByExamIds(examIds,null);
			if(threeRatiosVoList != null && threeRatiosVoList.size() > 0){
				int size = 0;
				for(ThreeRatiosVo threeRatiosVo : threeRatiosVoList){
					if(threeRatiosVo.getHight() == null || threeRatiosVo.getLow() == null || threeRatiosVo.getNoPass() == null || threeRatiosVo.getPass() == null){

					}else{
						if(examId.equals(threeRatiosVo.getExamId()) || examId == threeRatiosVo.getExamId()){
							teamHight = threeRatiosVo.getHight() == null ? teamHight : threeRatiosVo.getHight();
							teamLow = threeRatiosVo.getLow() == null ? teamLow : threeRatiosVo.getLow();
							teamPass = threeRatiosVo.getPass() == null ? teamPass : threeRatiosVo.getPass();
							teamNoPass = threeRatiosVo.getNoPass() == null ? teamNoPass : threeRatiosVo.getNoPass();
						}

						Float f1 = (threeRatiosVo.getHight() == null ? 0 : threeRatiosVo.getHight());
						Float f2 = (threeRatiosVo.getLow() == null ? 0 : threeRatiosVo.getLow());
						Float f3 = (threeRatiosVo.getPass() == null ? 0 : threeRatiosVo.getPass());
						Float f4 = (threeRatiosVo.getNoPass() == null ? 0 : threeRatiosVo.getNoPass());
						gradeHight = gradeHight + f1;
						gradeLow = gradeLow + f2;
						gradePass = gradePass + f3;
						gradeNoPass = gradeNoPass + f4;
						size++;
					}
				}

				gradeHight = gradeHight/size;
				gradeLow = gradeLow/size;
				gradePass = gradePass/size;
				gradeNoPass = gradeNoPass/size;
			}
		}

		map.put("gradeHight",gradeHight);
		map.put("gradeLow",gradeLow);
		map.put("gradePass",gradePass);
		map.put("gradeNoPass",gradeNoPass);
		map.put("teamHight",teamHight);
		map.put("teamLow",teamLow);
		map.put("teamPass",teamPass);
		map.put("teamNoPass",teamNoPass);

		return map;
	}

	/**
	 * @function 获取知识点难度的得分情况
	 * @param examId
	 * @return
     */
	@RequestMapping(value = "/teachingEvaluationTeamSubject")
	@ResponseBody
	public Object getTeachingEvaluationTeamSubject(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		List<ScoringAverageVo> scoringAverageVos = new ArrayList<>();
		if(pjExam != null){
			List<ScoringAverageVo> examQuestionVoList = examQuestionService.findScoringAverageByExamId(examId);
			if(examQuestionVoList != null && examQuestionVoList.size() > 0){
				for(ScoringAverageVo scoringAverageVo : examQuestionVoList){
					if(scoringAverageVo != null){
						if(scoringAverageVo.getKnowledgeId() != null){
							KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(scoringAverageVo.getKnowledgeId());
							if(knowledgeNode != null){
								scoringAverageVo.setKnowledgeName(knowledgeNode.getName());
							}
						}
						if(scoringAverageVo.getPercent() == null){
							scoringAverageVo.setPercent(0f);
						}
						scoringAverageVos.add(scoringAverageVo);
					}
				}
			}
		}
		return scoringAverageVos;
	}

	/**
	 * @function 获取错题答题情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/teachingEvaluationError")
	@ResponseBody
	public Object getTeachingEvaluationError(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		List<ExamErrorVo> examErrorVoList = examQuestionService.findExamErrorByExamId(examId);
		if(examErrorVoList != null && examErrorVoList.size() > 0){
			for(ExamErrorVo examErrorVo : examErrorVoList){
				Object object = jcCacheService.findUniqueByParam("jc_knowledge_node","id",examErrorVo.getKnowledgeId()+"","name");
				if(object != null){
					examErrorVo.setKnowledgeName(object == null ? "" : object.toString());
				}
			}
		}
		return examErrorVoList;
	}

	/**
	 * @function 获取难度得分率统计分布情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/teachingEvaluationDiff")
	@ResponseBody
	public Object getTeachingEvaluationWrongKnoledge(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			List<KnolwdgeDiffVo> list = examQuestionService.findKnowledgeDiffByExamId(examId);
			return list;
		}
		return null;
	}

	/**
	 * @function 获取题目认知度率统计分布情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/teachingEvaluationCognition")
	@ResponseBody
	public Object getTeachingEvaluationCognition(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			return examQuestionService.findKnowledgeCognitionByExamId(examId);
		}
		return null;
	}

	/**
	 * @function 获取题目类型得分情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/teachingEvaluationQuestType")
	@ResponseBody
	public Object getTeachingEvaluationQuestType(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			return examQuestionService.findCountQuestiontTypeByExamId(examId);
		}
		return null;
	}

	/**
	 * @function 获取学生
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/getStudent")
	@ResponseBody
	public Object getStudent(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null && pjExam.getTeamId() != null){
			return studentService.findStudentByTeamId(pjExam.getTeamId());
		}
		return null;
	}

	/**
	 * @function 获取学生基本考试情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/getStudentBasic")
	@ResponseBody
	public Object getStudentBasic(
			@RequestParam(value = "examId", required = true)Integer examId,
			@RequestParam(value = "studentId", required = true)Integer studentId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		Student student = studentService.findStudentById(studentId);
		if(pjExam != null && student != null){
			return examStudentService.findExamStudentByExamIdAndUserId(pjExam.getId(),student.getUserId());
		}
		return null;
	}

	/**
	 * @function 获取年级统计信息情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/gradeCountBasic")
	@ResponseBody
	public Object getGradeCountBasic(
			@RequestParam(value = "examId", required = true)Integer examId,
			@CurrentUser UserInfo userInfo
	){
		Map map = new HashMap();
		PjExam pjExam = pjExamService.findPjExamById(examId);
		Integer studentCount = 0;
		Integer teamCount = 0;
		Float avgCount = 0f;
		Float hightCount = 0f;
		Float lowCount = 1000f;
		Float adCount = 0f;
		if(pjExam != null){
			Grade grade = gradeService.findGradeById(pjExam.getGradeId());
			if(grade != null){
				map.put("gradeName",grade.getName());
			}

			Subject subject = subjectService.findUnique(userInfo.getSchoolId(),pjExam.getSubjectCode());
			if(subject != null) {
				map.put("subjectName",subject.getName());
			}

			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());
			List<ExamStatVo> list = examStatService.findExamStatByExamIds(examIds);
			Float[] arr = new Float[list.size()];
			if(list != null && list.size() > 0){
				teamCount = list.size();
				for(int i = 0 ;i < teamCount; i++){
					studentCount += list.get(i).getStudentCount() == null ? 0 : list.get(i).getStudentCount();
					avgCount += list.get(i).getAverageScore() == null ? 0 : list.get(i).getAverageScore();
					hightCount = hightCount >= (list.get(i).getHighestScore() == null ? 0 : list.get(i).getHighestScore()) ? hightCount : (list.get(i).getHighestScore() == null ? 0 : list.get(i).getHighestScore());
					lowCount = lowCount <= (list.get(i).getLowestScore() == null ? 0 : list.get(i).getLowestScore()) ? lowCount : (list.get(i).getLowestScore() == null ? 0 : list.get(i).getLowestScore());
					arr[i] = list.get(i).getAverageScore();
				}

				avgCount = avgCount/teamCount;
				adCount = getAdScore(arr,avgCount);


				map.put("teamCount",teamCount);
				map.put("studentCount",studentCount);
				map.put("avgCount",avgCount);
				map.put("hightCount",hightCount);
				map.put("lowCount",lowCount);
				map.put("sdCount",adCount);
			}
		}
		return map;
	}

	/**
	 * @function 获取标准差
	 * @param array
	 * @return
	 * @date 2016年1月26日
	 */
	private Float getAdScore(Float[] array,Float avg){
		double sum = 0f;
		for(int i = 0;i < array.length;i++){
			sum += Math.sqrt(((double)array[i] -avg) * (array[i] -avg));
		}
		//return (float)(sum / (array.length - 1));
		return (float)(sum / (array.length));
	}

	/**
	 * @function 获取排名情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/getGradeTeamRank")
	@ResponseBody
	public Object getGradeTeamRank(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		List<ExamStatVo> examStatList = null;
				PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());
			examStatList = examStatService.findExamStatRankByExamIds(examIds);
		}
		return examStatList;
	}

	/**
	 * @function 获取排名情况
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/gradeAvgLd")
	@ResponseBody
	public Object getGradeAvgLd(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		List<ExamStatVo> examStatList = null;
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());
			examStatList = examStatService.findExamStatByExamIds(examIds);
		}
		return examStatList;
	}

	/**
	 * @function 获取全年级三率综合推挤
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/getAllGradeTreeRia")
	@ResponseBody
	public Object getAllGradeTreeRia(
			@RequestParam(value = "examId", required = true)Integer examId
	){
		List<ThreeRatiosVo> examStatList = null;
		PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			Integer[] examIds = getExamIds(pjExam.getJointExamCode(),pjExam.getGradeId());
			examStatList = examStatService.findExamStatTreeRatiosByExamIds(examIds,null);
		}
		return examStatList;
	}


	/**
	 * 获取三率位置图
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/getTeamStudentSLWZT")
	@ResponseBody
	public Object getTeamStudentSLWZT(
			@RequestParam(value = "examId", required = true)Integer examId,
			@RequestParam(value = "studentId", required = true)Integer studentId
	){
		Map<String,Float> map = new HashMap<String, Float>();
		Float full = 100f;
		Float high = 80f;
		Float low = 75f;
		Float pass = 60f;
		Float teamAvg = 0f;
		Float mine = 0f;

		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		if(examStat != null){
			full = examStat.getFullScore();
			high = examStat.getHighScore();
			low = examStat.getLowScore();
			pass = examStat.getPassScore();
			teamAvg = examStat.getAverageScore();
		}
		if(studentId != null){
			Student student = studentService.findStudentById(studentId);
			if(student != null){
				ExamStudent examStudent = examStudentService.findExamStudentByExamIdAndUserId(examId,student.getUserId());
				if(examStudent != null){
					mine = examStudent.getScore();
				}
			}
		}

		map.put("full",full);
		map.put("high",high);
		map.put("low",low);
		map.put("pass",pass);
		map.put("teamAvg",teamAvg);
		map.put("mine",mine);

		return map;
	}

	/**
	 * @function 获取班级及学生的错题知识点分布情况
	 * @param examId
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value="getTeamStudentCTZSDFB")
	@ResponseBody
	public Object getTeamStudentCTZSDFB(
			@RequestParam(value = "examId", required = true)Integer examId,
			@RequestParam(value = "studentId", required = true)Integer studentId
	){
		PjExam pjExam = pjExamService.findPjExamById(examId);
		Student student = studentService.findStudentById(studentId);
		List<KnowledgeCountVo> kcvl = getStudentKnoledgeScore(pjExam,student);;
		return kcvl;
	}

	/**
	 * @function 知识点答题情况统计，雷达图
	 * @param examId
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "getTeamStudentZSDDTQK")
	@ResponseBody
	public Object KnowlegeCountDTQKTJ(
			@RequestParam(value = "examId", required = true)Integer examId,
			@RequestParam(value = "studentId", required = true)Integer studentId
	){
		Map<String,Object> map = new HashMap<String,Object>();
		//获取班级知识点得分情况
		List<ScoringAverageVo> list = examQuestionService.findTeamKnoledgeScoreByExamId(examId);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i++){
				KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(list.get(i).getKnowledgeId());
				if(knowledgeNode != null){
					list.get(i).setKnowledgeName(knowledgeNode.getName());
				}
			}
		}
		PjExam pjExam = pjExamService.findPjExamById(examId);
		Student student = studentService.findStudentById(studentId);

		//获取学生知识点的风情况PjExam pjExam 
		List<KnowledgeCountVo> kcvl = getStudentKnoledgeScore(pjExam,student);

		map.put("mine",kcvl);
		map.put("team",list);

		return map;
	}

	public List<KnowledgeCountVo> getStudentKnoledgeScore(PjExam pjExam, Student student){
		List<KnowledgeCountVo> kcvl = null;
		if(pjExam != null){
			//先获取该考试的知识点
			PaPaper paper = paPaperService.findPaPaperByUUid(pjExam.getPaperUuid());
			if(paper != null) {
				
				List<KnowledgeCountVo> knowledgeCountVoList = paPaperService.findKnowledgeCountByPaperUuid(paper.getId());
				//paperService.findKnowledgeCountByPaperUuid(pjExam.getPaperUuid());
				if(knowledgeCountVoList != null && knowledgeCountVoList.size() > 0){
					Integer[] knoledgeIds = new Integer[knowledgeCountVoList.size()];
					for(int i = 0; i < knowledgeCountVoList.size(); i++){
						knoledgeIds[i] = knowledgeCountVoList.get(i).getKnowledgeId();
					}
					//获取该学生每个知识点的得分率
					
					Map<String,Integer> map = getOwnerId(pjExam.getId());
					if(map != null && map.size() >0) {
						Integer ownerId = map.get("ownerId");
						Integer type = map.get("type");
						Integer objId = map.get("objId");
						Integer userId = student == null ? 0 : student.getUserId();
						kcvl = userQuestionService.findStudentAllScoreByKnoledgeId(pjExam.getPaperUuid(),knoledgeIds,userId,ownerId,type,objId);
						
						if(kcvl != null && kcvl.size() > 0){
							for(int i = 0; i < kcvl.size(); i++){
								Integer kId = kcvl.get(i).getKnowledgeId();
								KnowledgeNode knowledgeNode = knowledgeNodeService.findKnowledgeNodeById(kId);
								if(knowledgeNode != null){
									kcvl.get(i).setKnowledgeName(knowledgeNode.getName());
								}
							}
						}
					}
					
					
				}
			}
		}
		return kcvl;
	}

	/**
	 * @function 获取班级与我认知度统计
	 * @param examId
	 * @param studentId
     * @return
     */
	@RequestMapping(value = "getTeamStudentRZDDTQKTJ")
	@ResponseBody
	public Object getTeamStudentCongition(
			@RequestParam(value = "examId", required = true)Integer examId,
			@RequestParam(value = "studentId", required = true)Integer studentId
	){
		//先获取班级的认知度情况统计
		List<ScoringAverageVo> scoringAverageVoList = null;
		List<CognitionCountVo> cognitionCountVoList = null;
		Map<String,Object> map = new HashMap<String,Object>();
				PjExam pjExam = pjExamService.findPjExamById(examId);
		if(pjExam != null){
			scoringAverageVoList = examQuestionService.findKnowledgeCognitionByExamId(examId);
			
			Integer ownerId = null;
			Integer type = null;
			Integer objId = null;
			if(studentId != null){
				Student student = studentService.findStudentById(studentId);
				if(student != null){
					
					PjExam pj=pjExamService.findPjExamById(examId);
					if(pj.getExamType().equals("11")){
						ExamRelateCondition examRelateCondition = new ExamRelateCondition();
						examRelateCondition.setPjExamId(examId);
						List<ExamRelate> examRelateList = examRelateService.findExamRelateByCondition(examRelateCondition);
						if(examRelateList != null && examRelateList.size() > 0){
							ExamRelate examRelate = examRelateList.get(0);
							ExamPublish examPublish = this.examPublishService.findExamPublishByUuid(examRelate.getPublishMicroLessonId());
							if(examPublish != null){
								ownerId = examPublish.getId();
								type=PaperType.EXAM;
							}
						}
					}else{
					      LpTaskExamUnitCondition lc=new LpTaskExamUnitCondition();
					      lc.setExamId(examId);
					      List<LpTaskExamUnit> lclist=lpTaskExamUnitService.findLpTaskExamUnitByCondition(lc);
					      if(lclist==null||lclist.size()==0){
					    	  return map;
					      }
					      ownerId=lclist.get(0).getTaskId();
					      type=PaperType.LEARNING_PLAN;
					      objId=lclist.get(0).getUnitId();
					}
					
					
					cognitionCountVoList = userQuestionService.findUserCognitionCount(student.getUserId(),pjExam.getPaperUuid(),ownerId,objId,type);
				}
			}

			map.put("team",scoringAverageVoList);
			map.put("mine",cognitionCountVoList);
		}
		return map;
	}

	
	
	
	
	private Map<String,Integer> getOwnerId(Integer examId){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		Integer ownerId = null;
		Integer type = null;
		Integer objId = null;
		PjExam pj=pjExamService.findPjExamById(examId);
		if(pj.getExamType().equals("11")){
		
			TaskTeamCondition taskTeamCondition = new TaskTeamCondition();
			taskTeamCondition.setPjExamId(examId);
			List<TaskTeam> taskTeamList = this.taskTeamService.findTaskTeamByCondition(taskTeamCondition);
			
			if(taskTeamList != null && taskTeamList.size() > 0){
				TaskTeam examRelate = taskTeamList.get(0);
				
				if(examRelate != null){
					ownerId = examRelate.getTaskId();
					type=PaperType.EXAM;
				}
			}
		}else{
		      LpTaskExamUnitCondition lc=new LpTaskExamUnitCondition();
		      lc.setExamId(examId);
		      List<LpTaskExamUnit> lclist=lpTaskExamUnitService.findLpTaskExamUnitByCondition(lc);
		      if(lclist==null||lclist.size()==0){
		    	  return map;
		      }
		      ownerId=lclist.get(0).getTaskId();
		      type=PaperType.LEARNING_PLAN;
		      objId=lclist.get(0).getUnitId();
		}
		
		map.put("ownerId", ownerId);
		map.put("type", type);
		map.put("objId", objId);
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "getPersonExamDeail",method = RequestMethod.POST)
	@ResponseBody
	public Object getPersonExamDeail(@RequestParam("examId") Integer examId,
			@RequestParam("studentId") Integer studentId
	){
		Student student = null;
		List<PaperAnswerTime> list=new ArrayList<PaperAnswerTime>();
		if(studentId != null){
			student = studentService.findStudentById(studentId);
		}else{
			return list;
		}
		Integer ownerId=null;
		Integer type=null;
		Integer objId=null;
		PjExam pj=new PjExam();
		
		pj=pjExamService.findPjExamById(examId);
		if(pj.getExamType().equals("11")){
			
			TaskTeamCondition taskTeamCondition = new TaskTeamCondition();
			taskTeamCondition.setPjExamId(examId);
			List<TaskTeam> taskTeamList = this.taskTeamService.findTaskTeamByCondition(taskTeamCondition);
			
			if(taskTeamList != null && taskTeamList.size() > 0){
				TaskTeam examRelate = taskTeamList.get(0);
				
				if(examRelate != null){
					ownerId = examRelate.getTaskId();
					type=PaperType.EXAM;
				}else{
					return list;
				}
			}
			
		}
		
		long s = System.currentTimeMillis();
		list = userQuestionService.findUserQuestionAnswerTimeByUserIdAndExamId(student.getUserId(),pj.getTeamId(),type,ownerId,objId);
		long e = System.currentTimeMillis();
		System.out.println("总共花费"+(e-s));
		if(list != null && list.size() > 0){
			List<String> questionUuids = new ArrayList<String>();
			for(PaperAnswerTime pat:list) {
				questionUuids.add(pat.getQuestionUuid());
			}
			ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
			examQuestionCondition.setIsDeleted(false);
			examQuestionCondition.setExamId(examId);
			Map<String,ExamQuestion> map = this.examQuestionService.findCountQuestiontByExamIdAndQuestionUuids(examId,questionUuids.toArray());
			
			if(map != null && map.size() > 0) {
			for(int i = 0; i < list.size(); i++){
				PaperAnswerTime pat = list.get(i);
				ExamQuestion examQuestion = map.get(pat.getQuestionUuid());
				
				Integer answerCount = examQuestion.getAnswerCount();
				Integer avgAnswerTime = null;
				if(answerCount == null || examQuestion.getAnswerCount() == 0) {
					avgAnswerTime = 0;
				}else {
					avgAnswerTime = examQuestion.getTotalTime() /  answerCount;
				}
				pat.setAvgAnswerTime(avgAnswerTime);
				
			}
		}
		}
		return list;
	}

/*	*//**
	 * 模拟试卷提交答案
	 * @param
	 * @return
	 *//*
	@RequestMapping(value = "/simulationSubmitAnswer")
	public String simulationSubmitAnswer(@RequestParam("gradeId") Integer gradeId,@RequestParam("ownerId") Integer ownerId,@RequestParam("paperUuid") String paperUuid,@RequestParam("paperId") Integer paperId){

		//1. 根据年级ID 查询该年级下所有班级，所有人员
		List<Team> teamList = teamService.findTeamOfGradeByAsc(gradeId);
		if(teamList != null && teamList.size() > 0){
			
			for(Team team : teamList){
				
				//2.循环查询每个班级的学生238
				if(team != null){
					
					Integer teamId = team.getId();
					List<Student> studentList = studentService.findStudentByTeamId(teamId);
					if(studentList != null && studentList.size() > 0){
						
						for(Student student:studentList){
							
							if(student != null){
								Integer studentUserId = student.getUserId();
								appendData(teamId,studentUserId,paperUuid,ownerId,paperId);
							}
						}
					}
				}
			}
		}
		
		
		//调统计方法
		
		return null;
		
	}
	
	private String appendData(Integer teamId,Integer studentUserId,String paperUuid,Integer ownerId,Integer paperId){
		
		String  termCode = null;
		String  schoolYear = null;
		try {
		//通过用户ID查找对应的学校ID
		Student student = this.studentService.findStudentByUserId(studentUserId);
		if(student != null){
			Integer schoolId = student.getSchoolId();
			//获取当前学年、当前学期
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(schoolTermCurrent != null){
				termCode = schoolTermCurrent.getSchoolTermCode();
				schoolYear = schoolTermCurrent.getSchoolYear();
			}
			
		}
		
		//1. 根据试卷uuid查询该份试卷下的所有题目
		List<DemoEntity> demoEntityList = new ArrayList<DemoEntity>();
		List<UserQuestionResult> userQuestionResultList  = userQuestionService.findUserQuestionByPaperId(paperId);
		if(userQuestionResultList != null && userQuestionResultList.size() > 0){
			for(UserQuestionResult userQuestionResult :userQuestionResultList){
				DemoEntity demoEntity = new DemoEntity();
				String [] answer = {appendAnswer()};
				demoEntity.setAnswer(answer);
				demoEntity.setUuid(userQuestionResult.getQuestionUuid());
				demoEntity.setIsCorrect(appendCorrect());
				demoEntity.setAnswerTime(appendAnswerTime());
				demoEntityList.add(demoEntity);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
			String answers = mapper.writeValueAsString(demoEntityList);
			paperHandleService.uploadPaperAnswer(paperUuid, teamId, null, studentUserId, answers, PaperType.EXAM, ownerId, null, schoolYear, termCode,null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	//随机生成答案
	private static String appendAnswer(){
	    String[] A_z = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		Random r = new Random();
        int sub = r.nextInt(3);
        String answer = ""+A_z[sub]+"";
		return answer;
	}
	
		//随机生成答案
		private static Integer appendCorrect(){
			
			return Math.abs((new Random().nextInt())%2);
		}
		
		
		//随机生成作答时间
		private static int appendAnswerTime(){
			int max =300;
			int min = 100;
			int date =(int) Math.round(Math.random()*(max-min)+min);
			return date;
		}
	*/
}
