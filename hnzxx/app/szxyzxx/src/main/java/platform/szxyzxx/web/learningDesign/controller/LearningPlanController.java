/*package platform.szxyzxx.web.learningDesign.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.ExamQuestionService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ExamQuestionCondition;
import platform.education.generalTeachingAffair.vo.ExamQuestionVo;
import platform.education.generalTeachingAffair.vo.ExamResult;
import platform.education.generalTeachingAffair.vo.ExamTeamNumber;
import platform.education.generalTeachingAffair.vo.PjExamCondition;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.im.utils.UUIDUtil;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.model.LpTask;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.model.LpTaskUnitUser;
import platform.education.learningDesign.model.LpTaskUser;
import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.learningDesign.model.LpUnit;
import platform.education.learningDesign.model.LpUnitFile;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.learningDesign.service.LpCatelogService;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.service.LpTaskService;
import platform.education.learningDesign.service.LpTaskUnitUserService;
import platform.education.learningDesign.service.LpTaskUserActivityService;
import platform.education.learningDesign.service.LpTaskUserService;
import platform.education.learningDesign.service.LpUnitFileService;
import platform.education.learningDesign.service.LpUnitService;
import platform.education.learningDesign.vo.LpTaskCondition;
import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
import platform.education.learningDesign.vo.LpTaskUnitUserCondition;
import platform.education.learningDesign.vo.LpTaskUserActivityCondition;
import platform.education.learningDesign.vo.LpTaskUserCondition;
import platform.education.learningDesign.vo.LpUnitCondition;
import platform.education.learningDesign.vo.LpUnitType;
import platform.education.learningDesign.vo.TaskVo;
import platform.education.micro.model.MiMicroPersonGroup;
import platform.education.micro.service.MiMicroPersonGroupService;
import platform.education.micro.service.MiMicroPersonGroupUserIdService;
import platform.education.micro.vo.MiMicroPersonGroupCondition;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaperQuestionResult;
import platform.education.paper.model.TeamQuestionOptions;
import platform.education.paper.service.PaPaperHandleService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaUserQuestionService;
import platform.education.resource.service.ResourceService;
import platform.education.user.model.User;
import platform.education.user.service.UserService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.services.statistic.service.StatisticService;
import platform.szxyzxx.web.bbx.client.vo.ResponseNormal;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.UserIconUtil;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.message.contans.TagsTypeContans;
import platform.szxyzxx.web.teach.contants.ExamStatisticsType;

*//**
 *
 * @author ?????????
 *//*
@Controller
@RequestMapping(value = "/learningPlan")
public class LearningPlanController extends BaseController {
    private static final String DIR = "learningDesign/learningPlan";
    @Resource
    private MiMicroPersonGroupService miMicroPersonGroupService;
    
    @Resource
    private MiMicroPersonGroupUserIdService microPersonGroupUserIdService;
   
    @Resource
    private LearningPlanService learningPlanService;
   
    @Resource
    private TeamStudentService teamStudentService;
    
    @Resource
    private PaPaperHandleService paPaperHandleService;
   
    @Resource
    private LpUnitService lpUnitService;
    
    @Resource
    private ResourceService resourceService;
    
    @Resource
    private ExamService examService;
    
    @Resource
    private StudentService studentService;
   
    @Resource
    private LpTaskService lpTaskService;
    
    @Resource
    private LpUnitFileService lpUnitFileService;
    
    @Resource
    private LpTaskUserService lpTaskUserService;
    
    @Resource
    private LpTaskUnitUserService lpTaskUnitUserService;
    
    @Resource
    private LpCatelogService lpCatelogService;
    
    @Resource
    private LpTaskUserActivityService lpTaskUserActivityService;
    
    @Resource
	private StatisticService statisticService;
   
    @Resource
	private LpTaskExamUnitService lpTaskExamUnitService;
    
    @Resource
    private PjExamService pjExamService;
   
    @Resource
    private PaPaperService papaperService;
    
    @Resource
    private BaseRedisCache baseRedisCache;
   
    @Resource(name = "learningPlan_taskExecutor")
	private TaskExecutor taskExecutor;
   
    @Autowired
	@Qualifier("paperQuestionService")
	private PaQuestionService paperQuestionService;
    
    @Autowired
	@Qualifier("examQuestionService")
	private ExamQuestionService examQuestionService;
	
    @Autowired
	@Qualifier("userQuestionService")
	private PaUserQuestionService userQuestionService;
	
    @RequestMapping(value = "/prepare")
    public String prepareLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user,
    		@RequestParam(value="jumpfrom", required=false, defaultValue="other")String jumpfrom) {
    	String lpId = request.getParameter("lpId");
    	*//**???????????????????????????(????????????)*//*
    	List<Map<String, Object>> myTeachList = getMyTeachTeam(user);
    	*//**??????????????????(???????????????)*//*
    	List<Map<String, Object>> allList = getAllTeam(user);
    	
    	request.setAttribute("allList", allList);
        request.setAttribute("myTeachList", myTeachList);
        request.setAttribute("lpId", lpId);
        request.setAttribute("jumpfrom", jumpfrom);
       
        return DIR + "/lp_publish";
    }
    
    @RequestMapping(value = "/publish")
    @ResponseBody
    public Object publishLesson(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws ParseException, IOException {
    	 long startTime = System.currentTimeMillis();
    	String data = request.getParameter("teamIds");
        *//**????????????????????????*//*
        JSONArray classList =JSONArray.fromObject(data);
        String lpId = request.getParameter("lpId");
        
        String uuid = UUIDUtil.getUUID();
        
        LearningPlan lp = learningPlanService.findLearningPlanById(Integer.parseInt(lpId));
        
        Map<String, String> map= null;
        
        if(lp!=null) {
        	LpUnitCondition lpUnitCondition = new LpUnitCondition();
        	lpUnitCondition.setLpId(lp.getId());
        	lpUnitCondition.setUnitType(LpUnitType.EXAM);
        	lpUnitCondition.setIsDeleted(false);
        	List<LpUnit> lpUnitList = lpUnitService.findLpUnitByCondition(lpUnitCondition);
        	
        	if(lpUnitList.size()>0) {
        		List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByUnitIds(lpUnitList);
        		map = new HashMap<String, String>(lpUnitFileList.size());
        		for (LpUnitFile lpUnitFile : lpUnitFileList) {
        			platform.education.resource.model.Resource resource = resourceService.findResourceById(lpUnitFile.getResourceId());
        			if(resource!=null) {
        				map.put(lpUnitFile.getLpUnitId()+","+resource.getObjectId(), UUIDUtil.getUUID());
        			}
				}
        	}
        }
        List<Integer> teamIDS=new ArrayList<Integer>();
        *//**???????????????userId???userName???list*//*
        String taskUuid="";
        for (int i=0; i<classList.size(); i++) {
        	Integer teamId = classList.getInt(i);
        	teamIDS.add(teamId);
        	TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
        	teamStudentCondition.setTeamId(teamId);
        	*//**??????????????????????????????*//*
            List<TeamStudentVo> studentList = teamStudentService.findTeamStudentByConditionStudent(teamStudentCondition, null, null);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(studentList.size());
            for (TeamStudentVo teamStudentVo : studentList) {
            	Map<String, Object> studentMap = new HashMap<String, Object>();
				studentMap.put("userId", teamStudentVo.getUserId()+"");
				studentMap.put("userName", teamStudentVo.getUserName());
				list.add(studentMap);
            }
            *//**???????????????*//*
            LpTask task = learningPlanService.publish(lp, list, user.getId(),teamId,uuid);
            
//            if(map!=null && map.size()>0) {
//            	initExamStatistics(teamId, user.getSchoolId(), user.getTeacherId(), map, task.getId());
//            }
            taskUuid=task.getUuid();
        }
        if(map!=null && map.size()>0) {
        batchInitExamStatistics(teamIDS, user.getSchoolId(), user.getTeacherId(), map, taskUuid);
        }
        *//**????????????????????????*//*
		if(lp.getUsedCount()!=null) {
			lp.setUsedCount(lp.getUsedCount()+1);
		} else {
			lp.setUsedCount(1);
		}
		
		learningPlanService.modify(lp);
		 long end = System.currentTimeMillis();
		 System.out.println(end-startTime+"ms");
        return "success";
    }
    
    public void initExamStatistics(final Integer teamId, final Integer schoolId,
			final Integer teacherId, final Map<String, String> map, final Integer taskId) {
    	
    	taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
	            while(iterator.hasNext()) {
	            	Map.Entry<String, String> entry = iterator.next();
	            	String key = entry.getKey();
	            	Integer lpUnitId = Integer.parseInt(key.split(",")[0]);
	            	String examUuid = key.split(",")[1];
	            	
	            	ExamResult examResult = statisticService.initExamStatistics(teamId, schoolId, teacherId, ExamStatisticsType.LEARNINGPLAN, examUuid, entry.getValue());
	            	LpTaskExamUnit lpTaskExamUnit = new LpTaskExamUnit();
	            	lpTaskExamUnit.setJoinExamCode(entry.getValue());
	            	lpTaskExamUnit.setCreateDate(new Date());
	            	lpTaskExamUnit.setModifyDate(new Date());
	            	lpTaskExamUnit.setDeleted(false);
	            	lpTaskExamUnit.setExamId(examResult.getPjExam().getId());
	            	lpTaskExamUnit.setTaskId(taskId);
	            	lpTaskExamUnit.setUnitId(lpUnitId);
	            	lpTaskExamUnitService.add(lpTaskExamUnit);
	            }
			}
		});
    	
    }
    
    *//**
     * ???????????????
     * @param request
     * @param response
     * @param page
     * @param order
     * @param user
     * @return
     *//*
    @RequestMapping(value = "/index")
	public ModelAndView findTaskVo(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("page") Page page, @ModelAttribute("order") Order order, @CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm) {
    	ModelAndView model = new ModelAndView();
    	String relateId = request.getParameter("relateId");
    	String subjectCode = request.getParameter("subjectCode");
    	*//**??????index?????????list????????????*//*
    	String path = request.getParameter("index");

		if (path == null || path.equals("")) {
			*//**????????????????????? *//*
			SubjectCondition subjectCondition = new SubjectCondition();
			subjectCondition.setSchoolId(user.getSchoolId());
			List<Subject> subjectList = subjectService.findSubjectByCondition(subjectCondition, null, null);
			model.addObject("subjectList",subjectList);
			
			*//**??????????????????*//*
			@SuppressWarnings("rawtypes")
			Map classGradeMap = getClassGradeMap(user, false, false,true);
			model.addObject("classGradeMap", classGradeMap);
			
			path = "/manage_index";
		} else {
			*//**?????????????????????????????????*//*
			List<TaskVo> lists = new ArrayList<TaskVo>();
			if(relateId!=null && !"".equals(relateId)) {
				Integer code = null;
				if(subjectCode!=null && !"".equals(subjectCode)) {
					code = Integer.parseInt(subjectCode);
				}
				*//**?????????????????????????????????*//*
				List<TaskVo> list = this.lpTaskService.findLpTaskVo(Integer.parseInt(relateId), code, page, null);
				
				*//**??????????????????*//*
				Integer[] teamIds = new Integer[list.size()];
				for (int i = 0; i < list.size(); i++) {
					teamIds[i] = list.get(i).getTeamId();
				}
				List<Team> teamList = teamService.findByIds(teamIds);
				
				*//**??????????????????*//*
				List<Subject> subjectList = subjectService.findSubjectsOfSchool(user.getSchoolId());
				
				for (TaskVo vo : list) {
					*//**?????????????????????*//*
					LpCatelogCondition lpCatelogCondition = new LpCatelogCondition();
					lpCatelogCondition.setLpId(vo.getLpId());
					List<LpCatelog> lpCatalogList = lpCatelogService.findLpCatelogByCondition(lpCatelogCondition);
					
					*//**???????????????????????????, ??????????????????????????????????????????*//*
					if(lpCatalogList.size()>0) {
						vo.setHasCatalog(true);
					} else {
						vo.setHasCatalog(false);
					}
					
					LpUnitCondition lpUnitCondition = new LpUnitCondition();
					lpUnitCondition.setLpId(vo.getLpId());
					lpUnitCondition.setUnitType(LpUnitType.ACTIVITY);
					Long unitSize = lpUnitService.count(lpUnitCondition);
					
					if(unitSize>0) {
						vo.setHasActivity(true);
					} else {
						vo.setHasActivity(false);
					}
					
					*//**????????????*//*
					for (Team team : teamList) {
						if(team.getId()-vo.getTeamId()==0) {
							vo.setTeamName(team.getName());
						}
						
					}
					
					*//**????????????????????? *//*
					for (Subject subject : subjectList) {
						if(subject.getCode().equals(""+vo.getSubjectCode())) {
							vo.setSubjectName(subject.getName());
						}
					}
					lists.add(vo);
				}
				model.addObject("list", lists);
			}
			path = "/manage_list";
		}
		
		*//**??????????????????*//*
		model.addObject("relateId", relateId);
		model.setViewName(DIR + path);
		model.addObject("userId", user.getId());
		model.addObject("dm", dm);
		
		return model;
	}
    
    *//***
     * ????????????????????????
     * @param request
     * @param response
     * @param user
     * @param id
     * @return
     *//*
    @RequestMapping(value = "/task/detail", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView taskDetail(HttpServletRequest request, HttpServletResponse response,@RequestParam("taskId")Integer id) {
    	ModelAndView model = new ModelAndView();
    	LpTask lpTask = lpTaskService.findLpTaskById(id);
    	if(lpTask!=null) {
    		*//**??????????????????????????????????????????*//*
    		Map<String, Object> teamsOrder = geTeamOrderList(lpTask.getUuid(), lpTask.getId());
    		*//**??????????????????*//*
    		model.addObject("teamsOrder", teamsOrder.get("list"));
    		*//**?????????????????????*//*
    		model.addObject("teamOrder",teamsOrder.get("teamOrder"));
    		*//**???????????????*//*
    		model.addObject("teamPercent",teamsOrder.get("teamPercent"));
    		*//**??????????????????*//*
    		model.addObject("teamCount",teamsOrder.get("teamCount"));
    		*//**???????????????*//*
    		model.addObject("totalPercent",teamsOrder.get("totalPercent"));
    		
    		*//**??????????????????????????????*//*
    		Map<String, Object> detail = getTeamTaskList(lpTask.getId());
    		*//**????????????????????????*//*
    		model.addObject("list", detail.get("list"));
    		*//**?????????*//*
    		model.addObject("finishPercent",detail.get("finishPercent"));
    		*//**????????????*//*
    		model.addObject("unFinishPercent",detail.get("unFinishPercent"));
    		*//**????????????*//*
    		model.addObject("finishCount",detail.get("finishCount"));
    		*//**???????????????*//*
    		model.addObject("unFinishCount",detail.get("noFinishCount"));
    		
    		LearningPlan lp = learningPlanService.findLearningPlanById(lpTask.getLpId());
    		if(lp!=null) {
    			model.addObject("lpName",lp.getTitle());
    		}
    	}
    	
    	model.setViewName(DIR + "/task_detail");
    	return model;
    }
    
    *//***
     * ????????????????????????
     * @param request
     * @param response
     * @param user
     * @param id
     * @return
     *//*
    @RequestMapping(value = "/task/statistics/h5", method=RequestMethod.GET)
    @ResponseBody
    public ModelAndView taskStatistics(HttpServletRequest request, HttpServletResponse response,@RequestParam("taskId")Integer id) {
    	ModelAndView model = new ModelAndView();
    	LpTask lpTask = lpTaskService.findLpTaskById(id);
    	if(lpTask!=null) {
    		*//**??????????????????????????????????????????*//*
			Map<String, Object> teamsOrder = geTeamOrderList(lpTask.getUuid(), lpTask.getId());
			*//**??????????????????*//*
			model.addObject("teamsOrder", teamsOrder.get("list"));
			*//**?????????????????????*//*
			model.addObject("teamOrder",teamsOrder.get("teamOrder"));
			*//**???????????????*//*
			model.addObject("teamPercent",teamsOrder.get("teamPercent"));
			*//**??????????????????*//*
			model.addObject("teamCount",teamsOrder.get("teamCount"));
			*//**???????????????*//*
			model.addObject("totalPercent",teamsOrder.get("totalPercent"));
			
			*//**??????????????????????????????*//*
			Map<String, Object> detail = getTeamTaskList(lpTask.getId());
			*//**????????????????????????*//*
			model.addObject("list", detail.get("list"));
			*//**?????????*//*
			model.addObject("finishPercent",detail.get("finishPercent"));
			*//**????????????*//*
			model.addObject("unFinishPercent",detail.get("unFinishPercent"));
			*//**????????????*//*
			model.addObject("finishCount",detail.get("finishCount"));
			*//**???????????????*//*
			model.addObject("unFinishCount",detail.get("noFinishCount"));
			
			LearningPlan lp = learningPlanService.findLearningPlanById(lpTask.getLpId());
			if(lp!=null) {
				model.addObject("lpName",lp.getTitle());
			}
    		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
    		Map<String,Object> qMap=new HashMap<String, Object>();
    		Map<Integer,Object> unitMap=new HashMap<Integer, Object>();
    		List<Map<Integer,Object>> unitlist=new ArrayList<Map<Integer,Object>>();
    		LpTaskExamUnitCondition lpTaskExamUnitCondition=new LpTaskExamUnitCondition();
    		lpTaskExamUnitCondition.setDeleted(false);
    		lpTaskExamUnitCondition.setTaskId(id);
    		List<LpTaskExamUnit> ltelist=lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
    		List<LpTaskExamUnit> orderlist=new ArrayList<LpTaskExamUnit>();
    		boolean status=false;
    	    LpUnitCondition luc=new LpUnitCondition();
    	    luc.setLpId(lpTask.getLpId());
    	    luc.setUnitType(LpUnitType.EXAM);
    	    Order order=new Order();
    	    order.setProperty("list_order");
    	    order.setAscending(true);
    	   List<LpUnit> lulist= this.lpUnitService.findLpUnitByCondition(luc, order);
    		if(ltelist!=null&&ltelist.size()>0&&lulist!=null&&lulist.size()>0){
    			for(LpUnit lu:lulist){
    				for(LpTaskExamUnit lte:ltelist){
    					if(lu.getId().intValue()==lte.getUnitId().intValue()){
    						orderlist.add(lte);
    						break;
    					}
    				}
    			}
    			for(LpTaskExamUnit lte:orderlist)
    			{   
//    			    long start = System.currentTimeMillis();
    				LpUnit unit=new LpUnit();
    				unit=lpUnitService.findLpUnitById(lte.getUnitId());
    				DecimalFormat decimalFormat=new DecimalFormat("0");//???????????????????????????????????????????????????2???,??????0??????.
    				List<ExamQuestionVo> newVos = new ArrayList<ExamQuestionVo>();
    				PjExam pj =new PjExam();
				    pj=pjExamService.findPjExamById(lte.getExamId());
				    List<ExamQuestion> eqlist=new ArrayList<ExamQuestion>();
					PaPaper pa=new PaPaper();
					//pa=papaperService.findPaperByUuid(pj.getPaperUuid());
//    				List<PaperQuestionResult> qList = this.paperQuestionService.findPaperQuestionByPaperId(pa.getId(), null);
    				ExamQuestionCondition eqc=new ExamQuestionCondition();
    				eqc.setExamId(pj.getId());
    				eqlist =this.examQuestionService.findExamQuestionByCondition(eqc);
    				ExamStat examStat = examStatService.findExamStatByExamId(pj.getId());
    				Integer totalCount = 0;
    				if(examStat != null){
    					totalCount = examStat.getStudentCount();
    					if (totalCount == null) {
    						totalCount = 0;
    					}	
    				}
    				List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(lte.getExamId());
    				int totalStudent = 0;
    				if(examStudentList !=null && examStudentList.size() >0){
    					totalStudent = examStudentList.size();
    				}
    				if(eqlist != null && eqlist.size() >0){
    					qMap=new HashMap<String, Object>();
    					unitMap=new HashMap<Integer, Object>();
    					qMap.put(unit.getTitle(),eqlist);
    					unitMap.put(lte.getExamId(), qMap);
    					unitlist.add(unitMap);
    			    	for(ExamQuestion eq:eqlist){
    			    		ExamQuestionVo vo=new ExamQuestionVo();
    			    		vo.setQuestionType(eq.getQuestionType());
    			    		if(eq.getAnswerCount()==null||eq.getAnswerCount()==0){
    			    			vo.setAnswerCount(0);
    			    			vo.setFinishRate(0.0f);
    			    		}else{
    			    			DecimalFormat decimalFormat2=new DecimalFormat("0.0");
    			    			vo.setAnswerCount(eq.getAnswerCount());
    			    			String finishRate = decimalFormat2.format((eq.getAnswerCount() / Float.valueOf(totalStudent + "")) * 100);
								vo.setFinishRate(Float.valueOf(finishRate));
    			    		}
    			    		if(eq.getRightAnswerCount()==null){
    			    			vo.setRightAnswerCount(0);
    			    		}else{
    			    			vo.setRightAnswerCount(eq.getRightAnswerCount());
    			    		}
    			    		float teamRate=0.0f;
    			    		if(eq.getTeamScoringRate()!=null){
								teamRate=eq.getTeamScoringRate()*100;
						        DecimalFormat df = new DecimalFormat("0");
						        String num3 = df.format(teamRate);
						        teamRate=Float.parseFloat(num3); 
							}
    			    		vo.setTeamScoringRate(teamRate);
    			    		newVos.add(vo);
    			    	}
    				
    					}
    				if(examStat.getDataChanged()){
    					status=true;
    				}
    				Map<String,Object> map=new HashMap<String, Object>();
    				map.put(unit.getTitle(), newVos);
    				list.add(map);
//    				 long end = System.currentTimeMillis();
//    				 System.out.println("????????????"+(end-start)+"??????");
    			}
    			model.addObject("teamQuestionlist",list);
    			model.addObject("unitlist",unitlist);
    			model.addObject("status", status);
    			model.setViewName(DIR + "/dxa_tj");
    		}else{
    			model.setViewName(DIR + "/dxa_teacher");
    		}
    	}
    	return model;
    }
    
    *//**
     * ?????????????????????
     * @param request
     * @param response
     * @param page
     * @param user
     * @param title
     * @param index
     * @return
     *//*
    @RequestMapping(value = "/task/mypublish")
    public ModelAndView mypublish(HttpServletRequest request, HttpServletResponse response,
    		@ModelAttribute("page") Page page,
    		@CurrentUser UserInfo user, @RequestParam(value="title", required=false)String title, @RequestParam(value="index",required=false)String index) {
    	ModelAndView model = new ModelAndView();
    	*//**?????????????????????????????????*//*
    	LpTaskCondition lpTaskCondition = new LpTaskCondition();
    	lpTaskCondition.setUserId(user.getId());
    	lpTaskCondition.setTitle(title);
    	List<TaskVo> voList = lpTaskService.findMyTaskByCondition(lpTaskCondition, page, Order.desc("create_date"));
    	
    	model.addObject("list", voList);
    	
    	*//**??????index?????????list????????????*//*
		if (index == null || index.equals("")) {
			index = "/my_publish_index";
		} else {
			index = "/my_publish_list";
		}
		model.setViewName(DIR + index);
		return model;
    }
    
    *//***
     * ?????????task????????????????????????
     * @param request
     * @param response
     * @param taskId
     * @return
     *//*
    @RequestMapping(value = "/task/modify/view")
    public ModelAndView taskModifyView(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId")Integer taskId) {
    	ModelAndView model = new ModelAndView();
    	*//**?????????????????????????????????*//*
    	LpTask task = lpTaskService.findLpTaskById(taskId);
    	LpTaskCondition lpTaskCondition = new LpTaskCondition();
    	lpTaskCondition.setUuid(task.getUuid());
    	List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);
    	
    	StringBuffer buffer = new StringBuffer();
    	
    	*//**???????????????????????????????????????*//*
    	for (LpTask lpTask : taskList) {
    		*//**??????????????????*//*
    		Team team = teamService.findTeamById(lpTask.getObjectId());
    		*//**???????????????????????????*//*
			if(buffer==null || "".equals(buffer.toString())) {
				buffer.append(team.getName());
			} else {
				*//**??????????????????????????????*//*
				buffer.append("???"+team.getName());
			}
			model.addObject("title", lpTask.getTitle());
		}
    	model.addObject("taskId", task.getId());
    	model.addObject("teamNames", buffer.toString());
    	model.setViewName(DIR + "/lp_task_modify");
		return model;
    }
    
    *//***
     * ??????????????????
     * @param request
     * @param response
     * @param taskId
     * @param title
     * @return
     *//*
    @RequestMapping(value = "/task/modify")
    @ResponseBody
    public Object taskModify(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId")Integer taskId, @RequestParam("title")String title) {
    	*//**???????????????????????????*//*
    	LpTask task = lpTaskService.findLpTaskById(taskId);
    	LpTaskCondition lpTaskCondition = new LpTaskCondition();
    	lpTaskCondition.setUuid(task.getUuid());
    	List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);
    	
    	*//**???????????????????????????????????????*//*
    	for (LpTask lpTask : taskList) {
    		lpTask.setTitle(title);
    		lpTaskService.modify(lpTask);
		}
		return "success";
    }
    
    *//***
     * ??????????????????????????????????????????
     * @param request
     * @param response
     * @param taskId
     * @return
     *//*
    @RequestMapping(value = "/task/delete/view")
    public ModelAndView taskDeleteView(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId")Integer taskId) {
    	ModelAndView model = new ModelAndView();
    	*//**?????????????????????????????????*//*
    	LpTask task = lpTaskService.findLpTaskById(taskId);
    	LpTaskCondition lpTaskCondition = new LpTaskCondition();
    	lpTaskCondition.setUuid(task.getUuid());
    	List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);
    	
    	StringBuffer buffer = new StringBuffer();
    	
    	*//**???????????????????????????????????????*//*
    	for (LpTask lpTask : taskList) {
    		Team team = teamService.findTeamById(lpTask.getObjectId());
    		*//**???????????????????????????*//*
			if(buffer==null || "".equals(buffer.toString())) {
				buffer.append(team.getName());
			} else {
				*//**??????????????????????????????*//*
				buffer.append("???"+team.getName());
			}
		}
    	model.addObject("title", task.getTitle());
    	model.addObject("taskId", task.getId());
    	model.addObject("teamNames", buffer.toString());
    	model.setViewName(DIR + "/lp_task_delete");
		return model;
    }
    
    *//***
     * ?????????????????????????????????
     * @param request
     * @param response
     * @param taskId
     * @return
     *//*
    @RequestMapping(value = "/task/delete")
    @ResponseBody
    public Object taskDelete(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId")Integer taskId) {
    	*//**?????????????????????????????????*//*
    	LpTask task = lpTaskService.findLpTaskById(taskId);
    	LpTaskCondition lpTaskCondition = new LpTaskCondition();
    	lpTaskCondition.setUuid(task.getUuid());
    	List<LpTask> taskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);
    	
    	*//**????????????????????????????????????????????????????????????????????????????????????*//*
    	for (LpTask lpTask : taskList) {
    		*//**?????????????????????????????????*//*
    		lpTaskUnitUserService.removeByTaskId(lpTask.getId());
    		*//**???????????????????????????*//*
    		lpTaskUserService.removeByTaskId(lpTask.getId());
    		*//**????????????*//*
    		lpTaskService.remove(lpTask);
		}
    	return "success";
    }
    
    *//**
     * ?????????????????????(??????)
     * @param request
     * @param response
     * @param user
     * @param id
     * @return
     *//*
	@RequestMapping(value = "/delete", method=RequestMethod.DELETE)
    @ResponseBody
    public Object delete(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user, @RequestParam("id")Integer id) {
    	LpTask lpTask = lpTaskService.findLpTaskById(id);
    	if(lpTask!=null) {
    		*//**????????????????????????*//*
    		lpTaskUnitUserService.removeByTaskId(lpTask.getId());
    		*//**??????????????????*//*
    		lpTaskUserService.removeByTaskId(lpTask.getId());
    		*//**????????????*//*
    		lpTaskService.remove(lpTask);
    	}
    	
    	return new ResponseNormal("0");
    }
    
    *//**
     * ????????????????????????????????????????????????
     * @param request
     * @param response
     * @return
     *//*
    @RequestMapping(value = "/team/exit")
    @ResponseBody
    public Object exitTeams(HttpServletRequest request, HttpServletResponse response) {
    	String teamIds = request.getParameter("teamIds");
    	String lpId = request.getParameter("lpId");
    	JSONArray teamArray = JSONArray.fromObject(teamIds);
    	*//**??????????????????????????????*//*
    	List<Integer> teamIdList = lpTaskService.findIsExitInTeams(Integer.parseInt(lpId), teamArray);
    	*//**????????????????????? ?????????????????????????????????*//*
    	StringBuffer buffer = new StringBuffer();
    	for (Integer teamId : teamIdList) {
			Team team = teamService.findTeamById(teamId);
			*//**???????????????????????????????????????????????????????????????????????????*//*
			if(team!=null && !buffer.toString().contains(team.getName())) {
				*//**???????????????????????????*//*
				if(buffer==null || "".equals(buffer.toString())) {
					buffer.append(team.getName());
				} else {
					*//**??????????????????????????????*//*
					buffer.append("???"+team.getName());
				}
			}
		}
    	*//**???????????????*//*
    	Map<String, String> teamNames = new HashMap<String, String>();
    	*//**???????????????????????????????????????????????????????????????*//*
    	teamNames.put("size", buffer.length()+"");
    	*//**??????????????????????????????*//*
    	teamNames.put("teamNames", buffer.toString());
    	return teamNames;
	}
    
    *//**
     * ?????????????????????????????????????????????????????????
     * @param request
     * @param response
     * @param unitId
     * @param taskId
     * @return
     *//*
    @RequestMapping(value = "/unit/get")
    @ResponseBody
    public Object unitGet(HttpServletRequest request, HttpServletResponse response, @RequestParam("unitId") Integer unitId,@RequestParam("taskId") Integer taskId) {
    	LpUnit lpUnit = lpUnitService.findLpUnitById(unitId);
    	LpTask lpTask = lpTaskService.findLpTaskById(taskId);
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	if(lpUnit!=null || lpTask!=null) {
    		*//**???????????????????????????????????????*//*
    		LpTaskUnitUserCondition lpTaskUnitUserCondition = new LpTaskUnitUserCondition();
			lpTaskUnitUserCondition.setTaskId(taskId);
			lpTaskUnitUserCondition.setUnitId(unitId);
			List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTaskUnitUserCondition);
			
			*//**????????????*//*
			Integer finishedCount = 0;
			for (int i = 0; i < lpTaskUnitUserList.size(); i++) {
 				LpTaskUnitUser lpTaskUnitUser = lpTaskUnitUserList.get(i);
 				*//**????????????*//*
 				if(lpTaskUnitUser.getHasFinished()) {
					*//**??????????????????*//*
 					finishedCount++;
				}
			}
			*//**????????????*//*
			map.put("finishedCount", finishedCount);
			*//**???????????????*//*
			map.put("unfinishCount", lpTask.getUserCount()-finishedCount);
    		*//**?????????id*//*
    		map.put("id", lpUnit.getId());
    		*//**????????????*//*
    		if(lpUnit.getContent()==null) {
    			map.put("unitContent", "");
    		} else {
    			map.put("unitContent", lpUnit.getContent());
    		}
    	}
    	return map;
	}
    
    *//**
     * ??????????????????????????????????????????
     * @param request
     * @param response
     * @param taskId
     * @return
     *//*
    @RequestMapping(value = "/activity/list")
    public ModelAndView activity(HttpServletRequest request, HttpServletResponse response, @RequestParam("taskId") Integer taskId) {
    	ModelAndView model = new ModelAndView();
    	LpTask task = lpTaskService.findLpTaskById(taskId);
    	if(task!=null) {
    		*//**???????????????*//*
    		LearningPlan lp = learningPlanService.findLearningPlanById(task.getLpId());
    		*//**?????????????????????*//*
    		model.addObject("title", lp.getTitle());
    		*//**?????????????????????????????????*//*
    		LpUnitCondition lpUnitCondition = new LpUnitCondition();
    		lpUnitCondition.setLpId(lp.getId());
    		lpUnitCondition.setUnitType(LpUnitType.ACTIVITY);
    		List<LpUnit> lpUnitList = lpUnitService.findSpecialUnitByCondition(lpUnitCondition);
    		*//**????????????????????????*//*
    		model.addObject("lpUnitList", lpUnitList);
    		*//**??????taskid*//*
    		model.addObject("taskId", task.getId());
    	}
    	model.setViewName(DIR + "/lp_task_activity_index");
    	return model;
	}
    
    *//**
     * ????????????????????????
     * @param request
     * @param response
     * @param taskId
     * @param unitId
     * @param page
     * @return
     *//*
    @RequestMapping(value = "/task/activity/user/list")
    public ModelAndView taskActivity(HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam("taskId") Integer taskId, @RequestParam("unitId") Integer unitId,
    		@ModelAttribute("page") Page page) {
    	ModelAndView model = new ModelAndView();
    	LpTask lpTask = lpTaskService.findLpTaskById(taskId);
    	
    	if(lpTask!=null) {
    		*//**??????????????????????????????*//*
    		LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
    		lpTaskUserActivityCondition.setTaskId(taskId);
    		lpTaskUserActivityCondition.setUnitId(unitId);
    		page.setPageSize(3);
    		List<LpTaskUserActivity> lpTaskUserActivityList = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition, page, Order.desc("create_date"));
    	
    		*//**userId??????*//*
    		Integer[] userIds = new Integer[lpTaskUserActivityList.size()];
    		
    		*//**??????userId*//*
    		for (int i = 0; i < lpTaskUserActivityList.size(); i++) {
 				userIds[i] = lpTaskUserActivityList.get(i).getUserId();
			}
    		
    		*//**??????????????????*//*
    		List<User> userList = new ArrayList<User>(userIds.length);
    		
			if(userIds.length>0) {
				userList = userService.findUserByIds(userIds);
			} 
    		
			*//**???????????????*//*
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    		
    		*//**??????person??????, key???userId??? value???person???map*//*
    		Map<Integer, Person> personMap = getPersons(userList);
    		
    		*//**?????????????????????????????????????????????*//*
    		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(lpTaskUserActivityList.size());
			for (LpTaskUserActivity lpTaskUserActivity : lpTaskUserActivityList) {
				Map<String, Object> student = new HashMap<String, Object>();
				
				*//**?????????????????????????????? files??????,???????????????????????????????????????????????????*//*
				String files = lpTaskUserActivity.getFiles();
				String[] filesArray = files.split(",");
				List<String> fileList = new ArrayList<String>();
				
				*//**????????????????????????????????????*//*
				for (String file : filesArray) {
					FileResult result = fileService.findFileByUUID(file);
					if(result.getHttpUrl()!=null && !"".equals(result.getHttpUrl())) {
						fileList.add(result.getHttpUrl());
					}
				}
				*//**?????????????????????*//*
				String imgUrl = UserIconUtil.getImgSrc(lpTaskUserActivity.getUserId(), request, profileService);
				Person person  = personMap.get(lpTaskUserActivity.getUserId());
				
				*//**???????????????????????????*//*
				student.put("studentName", person.getName());
				student.put("userId", lpTaskUserActivity.getUserId());
				student.put("iconUrl", imgUrl);
				student.put("content", lpTaskUserActivity.getContent());
				student.put("files", fileList);
				student.put("createTime", dateFormat.format(lpTaskUserActivity.getCreateDate()));
				student.put("id", lpTaskUserActivity.getId());
				
				list.add(student);
			}
    		
    		model.addObject("lpTaskUserActivityList", list);
    		
    	}
    	model.setViewName(DIR + "/lp_task_activity_list");
    	return model;
	}
    
    *//***
     * ??????????????????????????????????????????
     * @param request
     * @param response
     * @param taskId
     * @param unitId
     * @return
     *//*
    @RequestMapping(value = "/task/activity/detail")
    public ModelAndView activityFinishedDetail(HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam("taskId") Integer taskId, @RequestParam("unitId") Integer unitId) {
    	ModelAndView model = new ModelAndView();
    	
    	*//**????????????????????????????????????????????????*//*
    	LpTaskUnitUserCondition lpTaskUnitUserCondition = new LpTaskUnitUserCondition();
		lpTaskUnitUserCondition.setTaskId(taskId);
		lpTaskUnitUserCondition.setUnitId(unitId);
		List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTaskUnitUserCondition);
    	
		*//**????????????????????????*//*
    	LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
		lpTaskUserCondition.setTaskId(taskId);
		List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition);
		
		if(lpTaskUserList.size()>0) {
			*//**?????????????????????user*//*
			Map<Integer, Boolean> finishedMap = new HashMap<Integer, Boolean>();
			for (int i = 0; i < lpTaskUnitUserList.size(); i++) {
					LpTaskUnitUser lpTaskUnitUser = lpTaskUnitUserList.get(i);
					if(lpTaskUnitUser.getHasFinished()) {
					finishedMap.put(lpTaskUnitUser.getUserId(), lpTaskUnitUser.getHasFinished());
				}
			}
			*//**??????id??????*//*
			Integer[] userIds = new Integer[lpTaskUserList.size()];

			*//**????????????id*//*
			for (int i = 0; i < lpTaskUserList.size(); i++) {
				userIds[i] = lpTaskUserList.get(i).getUserId();
			}
			*//**??????????????????*//*
	    	List<User> userList = userService.findUserByIds(userIds);
	    	*//**??????person??????, ???userId???key??? person???value???map*//*
	 		Map<Integer, Person> personMap = getPersons(userList);
	 		
	 		*//**??????????????????*//*
	 		List<Map<String, Object>> finishedStudents = new ArrayList<Map<String, Object>>(20);
	 		*//**??????????????????*//*
	 		List<Map<String, Object>> unfinishStudents = new ArrayList<Map<String, Object>>(20);
	 		
	 		for (LpTaskUser lpTaskUser : lpTaskUserList) {
	 			Map<String, Object> lpTaskUserMap = new HashMap<String, Object>();
	 			*//**??????person*//*
	 			Person person = personMap.get(lpTaskUser.getUserId());
	 			if(person!=null) {
	 				lpTaskUserMap.put("userId", lpTaskUser.getUserId());
	 				lpTaskUserMap.put("studentName", person.getName());
	 			}
	 			*//**???????????????????????????????????????????????????list*//*
	 			if(finishedMap.get(lpTaskUser.getUserId())!=null) {
	 				finishedStudents.add(lpTaskUserMap);
	 			} else {
	 				*//**???????????????*//*
	 				unfinishStudents.add(lpTaskUserMap);
	 			}
	 		}
	 		
			model.addObject("finishedStudents", finishedStudents);
			model.addObject("unfinishStudents", unfinishStudents);
		}
		
		model.setViewName(DIR + "/lp_task_activity_finished_detail");
		
    	return model;
	}
    
    
	private Map<Integer, Person> getPersons(List<User> userList) {
		
		Integer[] personIds = new Integer[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			personIds[i] = userList.get(i).getPersonId();
		}
		
		List<Person> personList = personService.findbyIds(personIds);
		
		Map<Integer, Person> map = new HashMap<Integer, Person>();
		for (User user : userList) {
			for (Person person : personList) {
				if(person.getId()-user.getPersonId()==0) {
					map.put(user.getId(), person);
				}
			}
		}
		
		return map;
	}
    
    *//***
     * ????????????????????????
     * @param user
     * @return
     *//*
	private List<Map<String, Object>> getMyTeachTeam(UserInfo user) {
		*//**????????????????????????*//*
		TeamTeacherCondition TeamTeacherByCondition = new TeamTeacherCondition();
		TeamTeacherByCondition.setUserId(user.getId());
		TeamTeacherByCondition.setSchoolYear(user.getSchoolYear());
		List<TeamTeacher> list = teamTeacherService.findTeamTeacherByCondition(TeamTeacherByCondition, null, Order.asc("grade_id"));
		
		*//**?????????????????????id*//*
		Set<Integer> gradeIds = new LinkedHashSet<Integer>();
		for (TeamTeacher teamTeacher : list) {
			gradeIds.add(teamTeacher.getGradeId());
		}
		
		List<Map<String, Object>> gradeList = new ArrayList<Map<String,Object>>();
		
		for (Integer gradeId : gradeIds) {
			*//**??????????????????  *//*
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gradeId", gradeId);
			map.put("gradeName", gradeService.findGradeById(gradeId).getName());
			List<Map<String, Object>> teamList = new ArrayList<Map<String,Object>>();
			for (TeamTeacher teamTeacher : list) {
				if(teamTeacher.getGradeId()-gradeId==0 && teamTeacher.getSubjectCode()!=null) {
					Map<String, Object> teamMap = new HashMap<String, Object>();
					teamMap.put("teamId", teamTeacher.getTeamId());
					String teamName = teamService.findTeamById(teamTeacher.getTeamId()).getName();
					teamMap.put("teamName", teamName+"["+teamTeacher.getSubjectName()+"]");
					teamList.add(teamMap);
				}
			}
			map.put("teamList", teamList);
			gradeList.add(map);
		}
		return gradeList;
	}
	
	*//***
	 * ??????????????????
	 * @param user
	 * @return
	 *//*
	private List<Map<String, Object>> getAllTeam(UserInfo user) {
		List<Team> teamList = teamService.findCurrentTeamOfSchoolAndYear(user.getSchoolId(), user.getSchoolYear());
		Set<Integer> gradeSet = new LinkedHashSet<Integer>();
		
		for (Team team : teamList) {
			gradeSet.add(team.getGradeId());
		}
		
		List<Map<String, Object>> gradeList = new ArrayList<Map<String,Object>>();
		
		for (Integer gradeId : gradeSet) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("gradeId", gradeId);
			map.put("gradeName", gradeService.findGradeById(gradeId).getName());
			
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for (Team team : teamList) {
				if(team.getGradeId()-gradeId==0) {
					Map<String, Object> teamMap = new HashMap<String, Object>();
					teamMap.put("teamId", team.getId());
					teamMap.put("teamName", team.getName());
					list.add(teamMap);
				}
			}
			map.put("teamList", list);
			gradeList.add(map);
		}
		
		return gradeList;
	}
	
    private Map<String, Object> geTeamOrderList(String uuid, Integer taskId) {
    	*//**??????????????????*//*
    	Map<String, Object> teamsOrder = new HashMap<String, Object>();
    	*//**??????????????????????????????*//*
    	List<TaskVo> taskVoList = this.lpTaskService.findLpTaskListOrder(uuid);
    	*//**???????????????????????????*//*
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(taskVoList.size());
    	*//**??????*//*
    	Integer order = 1;
    	*//**??????????????????????????????*//*
    	*//**?????????????????????*//*
    	String prePercent = "";
    	
    	Integer totalCount = 0;
    	Integer totalFinishCount = 0;
    	
    	Map<Integer, Team> teams = getTeams(taskVoList);
    	
    	for (TaskVo taskVo : taskVoList) {
    		*//**?????????????????????????????????????????????*//*
    		if("".equals(prePercent)) {
    			prePercent = taskVo.getPercent().toString();
			}
    		*//**?????????????????????????????????++*//*
    		if(!prePercent.equals(taskVo.getPercent().toString())) {
    			order++;
    			prePercent = taskVo.getPercent().toString();
    		}
    		*//**????????????????????????????????????*//*
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("order", order);
			*//**????????????????????????*//*
			if(taskVo.getId()-taskId==0) {
				teamsOrder.put("teamOrder", order);
				teamsOrder.put("teamPercent", taskVo.getPercent());
				teamsOrder.put("teamCount", taskVo.getFinishCount());
			}
			*//**???????????????*//*
			map.put("percent", taskVo.getPercent());
			*//**??????????????????*//*
			map.put("finishCount", taskVo.getFinishCount());
			
			totalFinishCount += taskVo.getFinishCount();
			
			Team team = teams.get(taskVo.getTeamId());
			
			Integer total = taskVo.getStudentCount();
			if(total!=null) {
				totalCount += total;
			}
			
			if(team!=null) {
				map.put("teamName", team.getName());
			}
			list.add(map);
		}
    	*//**????????????????????????????????????*//*
    	if(totalCount-0!=0) {
    		teamsOrder.put("totalPercent", totalFinishCount*1.0/totalCount * 100);
    	}
    	teamsOrder.put("list", list);
		return teamsOrder;
	}
	
	private Map<String, Object> getTeamTaskList(Integer taskId) {
		*//**??????????????????*//*
		LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
		lpTaskUserCondition.setTaskId(taskId);
		List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition);
		
		Map<String, Object> detail = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(lpTaskUserList.size());
		
		*//**????????????*//*
		Integer finishCount = 0;
		*//**???????????????*//*
		Integer unFinishCount = 0;
		
		Map<Integer, Student> students = getStudents(lpTaskUserList);
		
		for (LpTaskUser lpTaskUser : lpTaskUserList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("finishTime", lpTaskUser.getFinishTime());
			if("2".equals(lpTaskUser.getState())) {
				map.put("hasFinish", "?????????");
				*//**??????????????????*//*
				finishCount++;
			} else {
				map.put("hasFinish", "?????????");
				*//**?????????????????????*//*
				unFinishCount++;
			}
			
			*//**??????????????????*//*
    		Student student = students.get(lpTaskUser.getUserId());
    		if(student==null) {
    			continue;
    		}
    		*//**??????*//*
    		if(student.getStudentNumber()!=null && !"".equals(student.getStudentNumber())) {
    			map.put("studentNum", student.getStudentNumber());
    		} else {
    			map.put("studentNum", student.getStudentNumber());
    		}
    		
    		*//**??????*//*
    		map.put("studentName", student.getName());
    		list.add(map);
		}
		*//**?????????*//*
		double finishPercent = finishCount*1.0/(unFinishCount+finishCount);
		detail.put("finishPercent", finishPercent);
		*//**????????????*//*
		detail.put("unFinishPercent", (1.0-finishPercent));
		*//**????????????*//*
		detail.put("finishCount", finishCount);
		*//**???????????????*//*
		detail.put("noFinishCount", unFinishCount);
		detail.put("list", list);
		
		return detail;
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getClassGradeMap(UserInfo user, boolean includeSubject, boolean includeSameClass,boolean includeType) {
    	Map classGradeMap = new LinkedHashMap();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
        Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(), user.getId());
        if (teacher != null && schoolTermCurrent != null) {
        	TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setTeacherId(teacher.getId());
            *//**1 ?????????  2 ????????????*//*
            teamTeacherCondition.setType(2);
            teamTeacherCondition.setSchoolYear(schoolTermCurrent.getSchoolYear());
            List<TeamTeacher> teamTeacherList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, Order.asc("grade_id"));
            MiMicroPersonGroupCondition miMicroPersonGroupCondition = new MiMicroPersonGroupCondition();
            miMicroPersonGroupCondition.setUserId(teacher.getUserId());
            for (TeamTeacher tt : teamTeacherList) {
                Grade grade = this.gradeService.findGradeById(tt.getGradeId());
                if (grade != null) {
                    List<Map> classList = new ArrayList();
                    String viewName = grade.getName() + "&&" + grade.getUniGradeCode();
                    if (classGradeMap.containsKey(viewName)) {
                        classList = (List<Map>) classGradeMap.get(viewName);
                    }
                    miMicroPersonGroupCondition.setGradeId(tt.getGradeId());
                	List<MiMicroPersonGroup> personGroups = this.miMicroPersonGroupService.findMiMicroPersonGroupByCondition(miMicroPersonGroupCondition);
                	if (!personGroups.isEmpty() && personGroups != null) {
                		if (!classGradeMap.containsKey(viewName)) {
                			for (MiMicroPersonGroup personGroup : personGroups) {
                				Map mmap = new HashMap();
                				String personGroupName = personGroup.getName();
                				if (includeType) {
									personGroupName = personGroupName + "&&"+TagsTypeContans.GROUP_TYPE;
								}
                    			mmap.put(personGroup.getId(), personGroupName);
                    			classList.add(mmap);
        					}
                        }
                		
					}
                    Team team = this.teamService.findTeamById(tt.getTeamId());
                    if (team != null) {
                        Map map = new HashMap();
                        String classSubjectName = team.getName();
                        if (includeSubject) {
                            classSubjectName = classSubjectName + "   [" + tt.getSubjectName() + "]";
                        }
                        if (includeType) {
                        	classSubjectName = classSubjectName + "&&" + TagsTypeContans.TEAM_TYPE;
						}
                        map.put(team.getId(), classSubjectName);
                        if (includeSameClass) {
                            classList.add(map);
                        } else if (!classList.contains(map)) {
                            classList.add(map);
                        }
                    }
                    classGradeMap.put(viewName, classList);
                }
            }
        }
        return classGradeMap;
    }
    
    private Map<Integer, Team> getTeams(List<TaskVo> taskVoList) {
		Integer[] ids = new Integer[taskVoList.size()];
		for (int i=0; i<taskVoList.size(); i++) {
			ids[i] = taskVoList.get(i).getTeamId();
		}
		
		List<Team> teams = teamService.findByIds(ids);
		Map<Integer, Team> teamsMap = new HashMap<Integer, Team>(taskVoList.size());
		
		for (Team team : teams) {
			teamsMap.put(team.getId(), team);
		}
		
		return teamsMap;
	}
	
	private Map<Integer, Student> getStudents(List<LpTaskUser> lpTaskUserList) {
		if(lpTaskUserList.size()==0) {
			return null;
		}
		Integer[] ids = new Integer[lpTaskUserList.size()];
		for (int i=0; i<lpTaskUserList.size(); i++) {
			ids[i] = lpTaskUserList.get(i).getUserId();
		}
		
		List<Student> students = studentService.findbyUserIds(ids);
		Map<Integer, Student> studentsMap = new HashMap<Integer, Student>(lpTaskUserList.size());
		
		for (Student student : students) {
			studentsMap.put(student.getUserId(), student);
		}
		
		return studentsMap;
	}
	
	//????????????
		@RequestMapping(value = "/paperJson/h5")
		@ResponseBody
		public  List<Map<String,Object>>  paperJson(@RequestParam("taskId")Integer taskId){
			Integer paperId=0;
			Integer examIdString=0;
			float zero=0.0f;
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			LpTask lpTask = lpTaskService.findLpTaskById(taskId);
			LpTaskExamUnitCondition lpTaskExamUnitCondition=new LpTaskExamUnitCondition();
			lpTaskExamUnitCondition.setDeleted(false);
			lpTaskExamUnitCondition.setTaskId(taskId);
			List<LpTaskExamUnit> ltelist=lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
			List<LpTaskExamUnit> orderlist=new ArrayList<LpTaskExamUnit>();
			 LpUnitCondition luc=new LpUnitCondition();
	    	    luc.setLpId(lpTask.getLpId());
	    	    luc.setUnitType(LpUnitType.EXAM);
	    	    Order order=new Order();
	    	    order.setProperty("list_order");
	    	    order.setAscending(true);
	    	   List<LpUnit> lulist= this.lpUnitService.findLpUnitByCondition(luc, order);
	    		if(ltelist!=null&&ltelist.size()>0&&lulist!=null&&lulist.size()>0){
	    			for(LpUnit lu:lulist){
	    				for(LpTaskExamUnit lte:ltelist){
	    					if(lu.getId().intValue()==lte.getUnitId().intValue()){
	    						orderlist.add(lte);
	    						break;
	    					}
	    				}
	    			}
	    		}
			if(ltelist!=null&&ltelist.size()>0){
				for(LpTaskExamUnit  lte:orderlist){
					examIdString=lte.getExamId();
					PjExam pj =new PjExam();
				    pj=pjExamService.findPjExamById(examIdString);
					PaPaper pa=new PaPaper();
					//pa=papaperService.findPaperByUuid(pj.getPaperUuid());
					paperId=pa.getId();
					float sum = 0;
					float avg = 0;
					int num=0;
					List<Float> teamRightlist=new ArrayList<Float>();
					List<Float> gradeRightlist=new ArrayList<Float>();
					List<Integer> teamRanklist=new ArrayList<Integer>();
					List<PaperQuestionResult> qList = null;
							//this.paperQuestionService.findPaperQuestionByPaperId(paperId, null);
					DecimalFormat decimalFormat = new DecimalFormat("0.0");// ???????????????????????????????????????????????????2???,??????0??????.
					Map<String,Object> map=new HashMap<String,Object>();
					ExamQuestionCondition eqc=new ExamQuestionCondition();
    				eqc.setExamId(pj.getId());
 				    List<ExamQuestion> eqlist=new ArrayList<ExamQuestion>();
     					eqlist =this.examQuestionService.findExamQuestionByCondition(eqc);
					if(eqlist!=null){
						for(ExamQuestion eq:eqlist){
							float teamRate=0.0f;
							float gradeRate=0.0f;
							if(eq.getGradeRank()!=null&&eq.getGradeRank()!=0){
								teamRanklist.add(eq.getGradeRank());
							}else{
								teamRanklist.add(0);
							}
							if(eq.getTeamScoringRate()!=null){
								teamRate=eq.getTeamScoringRate()*100;
						        DecimalFormat df = new DecimalFormat("0");
						        String num3 = df.format(teamRate);
						        teamRate=Float.parseFloat(num3); 
						        teamRightlist.add(teamRate);
							}else{
								teamRightlist.add(0.0f);
							}
							if(eq.getGradeScoringRate()!=null){
								gradeRate=eq.getGradeScoringRate()*100;
								DecimalFormat df = new DecimalFormat("0");
						        String num2 = df.format(gradeRate);
						        gradeRate=Float.parseFloat(num2); 
						        gradeRightlist.add(gradeRate);
							}else{
								gradeRightlist.add(0.0f);
							}
						}
					}
					LpUnit unit=new LpUnit();
					unit=lpUnitService.findLpUnitById(lte.getUnitId());
					map.put("title", unit.getTitle());
					map.put("team", teamRightlist);
					map.put("grade", gradeRightlist);
					map.put("rank", teamRanklist);
					list.add(map);
				}
			}
			return list;
		}
		//????????????
		@RequestMapping(value = "/single/h5")
		public String singleIndex(Model uiModel,
				@RequestParam("questionUuId")String questionUuId,
				@RequestParam("examId")Integer examId
				) {
			LpTaskExamUnitCondition ltec=new LpTaskExamUnitCondition();
			ltec.setDeleted(false);
			ltec.setExamId(examId);
			List<LpTaskExamUnit> ltulist=new ArrayList<LpTaskExamUnit>();
			ltulist=lpTaskExamUnitService.findLpTaskExamUnitByCondition(ltec);
			Integer taskId=0;
			Integer teamId=0;
			Integer paperId=0;
			Integer unitId=0;
			String code="";
			if(ltulist!=null&&ltulist.size()>0){
				taskId=ltulist.get(0).getTaskId();
				unitId=ltulist.get(0).getUnitId();
				PjExam pj =new PjExam();
			    pj=pjExamService.findPjExamById(examId);
			    teamId=pj.getTeamId();
				PaPaper pa=new PaPaper();
				//pa=papaperService.findPaperByUuid(pj.getPaperUuid());
				paperId=pa.getId();
				code = ltulist.get(0).getJoinExamCode();
			}
			List<ExamTeamNumber> teamlist=this.pjExamService.findTeamNumberOfCode(code);
			Map<Integer,Integer> teamMap=new HashMap<Integer, Integer>();
			if(teamlist!=null&&teamlist.size()>0){
				for(ExamTeamNumber et:teamlist){
					teamMap.put(et.getExamId(), et.getTeamNumber());
				}
			}
			List<ExamQuestionVo> gradeVos = new ArrayList<ExamQuestionVo>();
			List<ExamQuestionVo> teamVos = new ArrayList<ExamQuestionVo>();
			List<ExamQuestion> eqlist=new ArrayList<ExamQuestion>();
			eqlist=this.examQuestionService.findExamQuestionByJointExamCode(code, questionUuId);
			String teamNumber="";
			int number=0;
			DecimalFormat decimalFormat = new DecimalFormat("0.00");// 
			float avg = 0.0f;
			if(eqlist!=null&&eqlist.size()>0){
				for(ExamQuestion eq:eqlist){
					 teamNumber="";
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
					ExamQuestionVo vo=new ExamQuestionVo();
					if(teamMap.get(eq.getExamId())!=null){
						number=(Integer)teamMap.get(eq.getExamId());
						teamNumber=String.valueOf(number);
					}
					vo.setTeamName(teamNumber);
					vo.setRightRate(teamRate);
					vo.setQuestionType(eq.getQuestionType());
					vo.setAnswerCount(eq.getAnswerCount());
					vo.setFullScore(eq.getFullScore());
					gradeVos.add(vo);
					if(eq.getExamId().intValue()==examId.intValue()){
						teamVos.add(vo);
					}
				}
			}
			//List<TeamQuestionOptions> toList = userQuestionService.getTeamQuestionOptionsByQuestionUuIdAndOwnerId(teamId, taskId, questionUuId,unitId);
			List<PaperQuestionResult> qList = new ArrayList<PaperQuestionResult>();
			//qList = this.paperQuestionService.findPaperQuestionByPaperId(paperId, questionUuId);
			uiModel.addAttribute("qList", qList);
			String gradeAvg = decimalFormat.format(avg);
			uiModel.addAttribute("gradeAnwer", gradeVos);
			uiModel.addAttribute("teamVos", teamVos);
			uiModel.addAttribute("avg", gradeAvg);
			//uiModel.addAttribute("toList", toList);
			return DIR + "/topic-statistics";
		}
	private void batchInitExamStatistics(List<Integer> teamIds,Integer schoolId, Integer teacherId, Map<String,String>map,String taskUuid){
		    LpTaskCondition lc=new LpTaskCondition();
	        // lp_task ??? uuid
	        lc.setUuid(taskUuid);
	        List<LpTask> tasks = lpTaskService.findLpTaskByCondition(lc);
	        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()) {
            	Map.Entry<String, String> entry = iterator.next();
            	String key = entry.getKey();
            	String examUuid = key.split(",")[1];
            	//statisticService.initBatchExamStatistics(teamIds, schoolId, teacherId, ExamStatisticsType.LEARNINGPLAN, examUuid, entry.getValue());
            }
            List<LpTaskExamUnit> ltelist=new ArrayList<LpTaskExamUnit>();
            Iterator<Map.Entry<String, String>> iterator1 = map.entrySet().iterator();
            while(iterator1.hasNext()) {
            	Map.Entry<String, String> entry = iterator1.next();
            	String key = entry.getKey();
            	Integer lpUnitId = Integer.parseInt(key.split(",")[0]);
            	PjExamCondition pjc=new PjExamCondition();
            	List<PjExam> pjList=new ArrayList<PjExam>();
            	pjc.setJointExamCode(entry.getValue());
            	pjc.setExamType(ExamStatisticsType.LEARNINGPLAN);
            	pjList=pjExamService.findPjExamByCondition(pjc);
            	if(pjList!=null&&pjList.size()>0){
            		Map<Integer,Integer> teamMap=new HashMap<Integer, Integer>();
            		for(PjExam pj:pjList){
            			teamMap.put(pj.getTeamId(), pj.getId());
            		}
            		for(LpTask lt:tasks){
            			LpTaskExamUnit lpTaskExamUnit = new LpTaskExamUnit();
            			lpTaskExamUnit.setJoinExamCode(entry.getValue());
            			lpTaskExamUnit.setCreateDate(new Date());
            			lpTaskExamUnit.setModifyDate(new Date());
            			lpTaskExamUnit.setDeleted(false);
            			lpTaskExamUnit.setExamId(teamMap.get(lt.getObjectId()));
            			lpTaskExamUnit.setTaskId(lt.getId());
            			lpTaskExamUnit.setUnitId(lpUnitId);
            			ltelist.add(lpTaskExamUnit);
            		}
            	}
            }
            lpTaskExamUnitService.createBatch(ltelist);
	}
}
*/