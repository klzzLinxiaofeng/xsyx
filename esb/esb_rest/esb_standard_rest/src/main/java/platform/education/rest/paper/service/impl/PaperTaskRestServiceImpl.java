package platform.education.rest.paper.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.exam.model.ExamPublish;
import platform.education.exam.model.ExamRelate;
import platform.education.exam.vo.ExamRelateCondition;
import platform.education.generalTeachingAffair.model.ExamQuestion;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.ExamQuestionService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ExamQuestionWrongVo;
import platform.education.generalcode.model.KnowledgeCatalog;
import platform.education.generalcode.model.KnowledgeNode;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.service.JcCacheService;
import platform.education.generalcode.service.KnowledgeCatalogService;
import platform.education.generalcode.service.KnowledgeNodeService;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.learningDesign.model.LpTask;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.service.LpTaskService;
import platform.education.learningDesign.vo.LpInterscoreVo;
import platform.education.learningDesign.vo.LpTaskCondition;
import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaTaskFavorites;
import platform.education.paper.model.PaUserQuestion;
import platform.education.paper.model.Task;
import platform.education.paper.model.TaskInterscore;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.model.TaskUser;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaTaskFavoritesService;
import platform.education.paper.service.PaUserQuestionService;
import platform.education.paper.service.TaskInterscoreService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.TaskTeamService;
import platform.education.paper.service.TaskUserService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.PaPaperCatalogCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaTaskFavoritesCondition;
import platform.education.paper.vo.TaskCondition;
import platform.education.paper.vo.TaskInterscoreCondition;
import platform.education.paper.vo.TaskTeamCondition;
import platform.education.paper.vo.TaskUserCondition;
import platform.education.paper.vo.TaskVo;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.paper.service.PaperTaskRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

public class PaperTaskRestServiceImpl implements PaperTaskRestService {
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	@Autowired
	@Qualifier("jcCacheService")
	private JcCacheService jcCacheService;
	@Autowired
	@Qualifier("pjExamService")
	private PjExamService  pjExamService;
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	@Autowired
	@Qualifier("taskTeamService")
	private TaskTeamService taskTeamService;
	@Autowired
	@Qualifier("userPaperService")
	private UserPaperService userPaperService;
	@Autowired
	@Qualifier("taskInterscoreService")
	private TaskInterscoreService taskInterscoreService;
	@Autowired
	@Qualifier("taskUserService")
	private TaskUserService taskUserService;
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	@Autowired
	@Qualifier("paPaperCatalogService")
	private PaPaperCatalogService paPaperCatalogService;
	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	@Resource
	private PaTaskFavoritesService paTaskFavoritesService;
	@Resource
	private LpTaskService lpTaskService;
	@Resource
	private LpTaskExamUnitService lpTaskExamUnitService;
	@Resource
	private ExamQuestionService examQuestionService;
	@Resource
	private PaPaperService paPaperService;
	@Resource
	private PaUserQuestionService paUserQuestionService;
	@Resource
	private UserFileService userFileService;
	@Resource
	private TeamStudentService TeamStudentService;
	
	@Override
	public Object list(Integer relateId, String appKey, Integer userId,
			String subjectCode, Integer pageSize, Integer pageNumber) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String,String> subjectMap=subjectService.findAllSubjectNameMap();
		Team team  = this.teamService.findTeamById(relateId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");//小写的mm表示的是分钟  
		Map<String, Object> taskMap = new HashMap<String, Object>();
		List<Map<String,Object>>taskList=new ArrayList<Map<String,Object>>();
		Order order=new Order();
		order.setProperty("pt.start_time DESC");
		Page page = new Page();
		page.setPageSize(pageSize);
		page.setCurrentPage(pageNumber);
		page.setEnableGetTolaRows(false);
		String subjectName="";
		Integer status=0;
		if(subjectCode.equals("-1")){
			subjectCode=null;
		}
		PaPaperVo vo=new PaPaperVo();
		vo.setSubjectCode(subjectCode);
		List<TaskVo> volist=taskService.findTaskVoByTeamId(vo, relateId, page, null);
		 Date today=new Date();
		if(volist!=null){
		for(int i=0;i<volist.size();i++){
			TaskVo er=volist.get(i);
			subjectName="";
		   taskMap=new HashMap<String, Object>();
           taskMap.put("title",er.getTitle());
           taskMap.put("startTime",sdf.format(er.getStartTime()));
           taskMap.put("finishTime",sdf.format(er.getFinishTime()));
           taskMap.put("teamName",team.getName());
           if(er.getSubjectCode() !=null && !er.getSubjectCode().equals("0")){
        	   subjectName=subjectMap.get(er.getSubjectCode());
        	   if(subjectName==null){
        		   subjectName="";
        	   }
           }else{
        	   subjectName="多科目";
           }
           if(today.before(er.getStartTime())){
        	   
           }else if(today.after(er.getStartTime())&&today.before(er.getFinishTime())){
        	   status=1;

           }else{
        	   status=2;
           }
           taskMap.put("subjectName", subjectName);
           taskMap.put("publisherId", er.getPublisherId());
           taskMap.put("publisherName", er.getPublisherName()==null?"":er.getPublisherName());
           taskMap.put("taskId", er.getId());
           taskMap.put("examId",er.getExamId());
           taskMap.put("paperId", er.getPaperId());
           taskMap.put("status",status);
           if(er.getPublisherId().intValue()==userId.intValue()){
        	   taskMap.put("isDelete", 1);
           }else{
        	   taskMap.put("isDelete", 0);
           }
           taskMap.put("difficulity", er.getDifficultyString());
           taskMap.put("score", er.getScore());
			PaPaperCatalogCondition pc=new PaPaperCatalogCondition();
			pc.setPaperId(er.getPaperId());
		    List<PaPaperCatalog>	pclist=paPaperCatalogService.findPaPaperCatalogByCondition(pc);
		    String catalogName="";
		    if(pclist!=null&&pclist.size()==1){
		    	String catalogCode = pclist.get(0).getCatalogCode();
				/**获取目录名称*/
				 catalogName = resTextbookCatalogService.getFullNameByCode(catalogCode," ");
				
		    }
		    taskMap.put("catalogName", catalogName);
           taskList.add(taskMap);
		}
	}
		return new ResponseVo<Object>("0",taskList);
	}
	@Override
	public Object taskDelete(String appKey, Integer ExamId) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		try{
			PjExam pj=pjExamService.findPjExamById(ExamId);
			if(pj==null){
			    ResponseInfo info = new ResponseInfo();
	            info.setDetail("examId不存在");
	            info.setMsg("examId不存在");
	            return new ResponseError("000001", info);
			}
			TaskCondition condition=new TaskCondition();
			condition.setUuid(pj.getJointExamCode());
			condition.setIsDeleted(false);
			List<Task> tlist=new ArrayList<Task>();
			tlist=taskService.findTaskByCondition(condition);
			if(tlist==null||tlist.size()==0){
			    ResponseInfo info = new ResponseInfo();
	            info.setDetail("任务不存在异常");
	            info.setMsg("任务不存在错误");
	            return new ResponseError("000001", info);
			}
			TaskTeamCondition taskCondition =new TaskTeamCondition();
			taskCondition.setTaskId(tlist.get(0).getId());
			taskCondition.setTeamId(pj.getTeamId());
			taskCondition.setIsDeleted(false);
			List<TaskTeam> ttlist=new ArrayList<TaskTeam>();
			ttlist=taskTeamService.findTaskTeamByCondition(taskCondition);
			if(ttlist==null||ttlist.size()==0){
				    ResponseInfo info = new ResponseInfo();
		            info.setDetail("任务不存在异常");
		            info.setMsg("任务不存在错误");
		            return new ResponseError("000001", info);
			}
			TaskTeam tt=ttlist.get(0);
			PjExam pe=new PjExam();
			pe=pjExamService.findPjExamById(tt.getPjExamId());
			pe.setIsDelete(true);
			pjExamService.modify(pe);
			taskService.deleteTaskRelate(tlist.get(0).getId(),pj.getTeamId() );
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			return new ResponseVo<Object>("0",map);
		}catch (Exception e){
			e.printStackTrace();
			    ResponseInfo info = new ResponseInfo();
	            info.setDetail("删除异常");
	            info.setMsg("未知错误");
	            return new ResponseError("000001", info);
		}
	}
	@Override
	public Object taskExamList(String appKey, Integer taskId,Integer teamId) {

		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			 SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss " );
			Map<String, Object> map1 = new HashMap<String, Object>();
			
			Task  task =taskService.findTaskById(taskId);
			if(task==null) {
				return new ResponseVo<Object>("0",map1);//找不到数据
			}
			
		
			
			TaskTeamCondition condition=new TaskTeamCondition();
			condition.setTaskId(taskId);
			condition.setTeamId(teamId);
			condition.setIsDeleted(false);
		List<TaskTeam>tasklist	=taskTeamService.findTaskTeamByCondition(condition);
			if(tasklist==null||tasklist.size()==0) {
				map1 = new HashMap<String, Object>();
				return new ResponseVo<Object>("0",map1);//找不到数据
			}
		
				Map<String, Object> lpInterscore = new HashMap<String, Object>();
				lpInterscore.put("isInterscore", tasklist.get(0).getIsInterscoring());
				if(tasklist.get(0).getInterscoreStartTime()!=null) {
					lpInterscore.put("startTime", sdf.format(tasklist.get(0).getInterscoreStartTime()));
				}else {
					lpInterscore.put("startTime", "");
				}
				if(tasklist.get(0).getIsInterscoring()){
					
					lpInterscore.put("isInterscore", 1);
				}else{
					lpInterscore.put("isInterscore", 0);
				}
				if(tasklist.get(0).getInterscoreFinishTime()!=null) {
					lpInterscore.put("finishTime", sdf.format(tasklist.get(0).getInterscoreFinishTime()));
//					System.out.println(sdf.format(tasklist.get(0).getInterscoreFinishTime()));
				}else {
					lpInterscore.put("finishTime", "");
				}
				/**获取用户答卷情况列表*/
				List<UserPaper> userPaperList = userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, null);
				/**答卷人数*/
				lpInterscore.put("submitCount", userPaperList.size());
				List<TaskInterscore> list=taskInterscoreService.findScoringTimeTaskTaskInterscore(taskId, teamId);
				Integer interscoreCount=0;
				if(list!=null){
					interscoreCount=list.size();
				}
				/**已经互评的学生*/
				lpInterscore.put("interscoreCount", interscoreCount);
				lpInterscore.put("name", task.getTitle());
				/**班级总人数*/
				Map<String, Object> total = new HashMap<String, Object>();
				Map<Integer, String> allMap = new HashMap<Integer, String>();
				Map<Integer, String> copyMap = new HashMap<Integer, String>();
				List<Map<String, Object>> elist = new ArrayList<Map<String, Object>>();
				List<Map<String, Object>> nolist = new ArrayList<Map<String, Object>>();
		        List<UserPaper> uplist=new ArrayList<UserPaper>();
		    	List<Map<String, String>> missList = new ArrayList<Map<String, String>>();
		        UserPaperCondition upCondition=new UserPaperCondition();
		        upCondition.setOwnerId(taskId);
		        upCondition.setType(PaperType.EXAM);
		        uplist=userPaperService.findUserPaperByCondition(upCondition);

		        Map<Integer,Double> upmap=new HashMap<Integer, Double>();
		        for(UserPaper up:uplist){
		        	upmap.put(up.getId(), up.getScore());
		        }
				TaskUserCondition tuCondition = new TaskUserCondition();
				tuCondition.setTaskId(taskId);
				tuCondition.setTeamId(teamId);
				tuCondition.setIsDelete(false);
				List<TaskUser> eprlist = taskUserService.findTaskUserByCondition(tuCondition);
				lpInterscore.put("totalCount",eprlist.size());
	            if(uplist!=null&&uplist.size()>0&&eprlist!=null&&eprlist.size()>0){
	            	Integer[] userIds = new Integer[eprlist.size()];
	            	int i = 0;
	            	for (TaskUser epr : eprlist) {
	            		userIds[i] = epr.getUserId();
	            		allMap.put(epr.getUserId(), epr.getName());
	            		i++;
	            	}
	            	Map<Integer, String> iconMap = new HashMap<Integer, String>();
	            	copyMap.putAll(allMap);
	            	TaskInterscoreCondition tiCondition = new TaskInterscoreCondition();
	            	tiCondition.setTeamId(teamId);
	            	tiCondition.setTaskId(taskId);
	            	tiCondition.setIsDeleted(false);
	            	List<TaskInterscore> eilist = taskInterscoreService
	            			.findTaskInterscoreByCondition(tiCondition);
	            	iconMap = ImgUtil.getImgSrcByIds(userIds, profileService);
	            	if (eilist != null && eilist.size() > 0) {
	            		for (TaskInterscore ei : eilist) {
	            			String evaluateingName = allMap.get(ei.getScoringUserId());
	            			String evaluatedName = allMap.get(ei.getScoredUserId());
	            			String evaluateingIcon = icon(iconMap
	            					.get(ei.getScoringUserId()));
	            			String evaluatedIcon = icon(iconMap.get(ei.getScoredUserId()));
	            			Integer evaluatedUserId=ei.getScoredUserId();
	            			Map<String, Object> map = new HashMap<String, Object>();
	            			map.put("evaluateingName", evaluateingName);
	            			map.put("evaluatedName", evaluatedName);
	            			map.put("evaluateingIcon", evaluateingIcon);
	            			map.put("evaluatedIcon", evaluatedIcon);
	            			map.put("userId", evaluatedUserId);
	            			Double score=0.0;
	            			if(upmap.get(ei.getScoredPaperId())!=null){
	            				score=upmap.get(ei.getScoredPaperId());
	            			}
	            			map.put("evaluatedScore", score);
	            			if (ei.getScoringTime() != null) {
	            				elist.add(map);
	            			} else {
	            				nolist.add(map);
	            			}
	            			copyMap.remove(ei.getScoringUserId());
	            		}
	            	}
	            	for (Map.Entry<Integer, String> entry : copyMap.entrySet()) {
	            		Map<String, String> missMap = new HashMap<String, String>();
	            		String userIcon = icon(iconMap.get(entry.getKey()));
	            		missMap.put("userName", entry.getValue());
	            		missMap.put("userIcon", userIcon);
	            		missList.add(missMap);
	            		
	            	}
	            }
	            lpInterscore.put("evaluationList", elist);
	            lpInterscore.put("notEvaluationList", nolist);
	            lpInterscore.put("missExam", missList);
			return new ResponseVo<Object>("0",lpInterscore);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取试卷异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}
	@Override
	public Object wrong(String appKey, Integer taskId, Integer teamId) {
		if (taskId == null || appKey == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// 参数为空
		}
       Task task=taskService.findTaskById(taskId);
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (task == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("taskId不存在,请确认");
			info.setMsg("不存在该taskId");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>();
		List<Integer> gradeIds = new ArrayList<Integer>();
			TaskTeamCondition erc = new TaskTeamCondition();
			erc.setTaskId(taskId);
			List<TaskTeam> erlist = taskTeamService.findTaskTeamByCondition(erc);
			if (erlist != null && erlist.size() > 0) {
				for (int i = 0; i < erlist.size(); i++) {
					gradeIds.add(erlist.get(i).getPjExamId());
				}
			}
			Integer[] gradeId = new Integer[gradeIds.size()];
			for (int i = 0; i < gradeIds.size(); i++) {
				gradeId[i] = gradeIds.get(i);
			}
			List<ExamQuestionWrongVo> vos = pjExamService.findExamQuestionWrongbyExamId(gradeId, 2, 0.85f);
			if (vos != null && vos.size() > 0) {
				gradeList = copy(vos, 2);
			}
			if (teamId != null) {
				TaskTeamCondition erc1 = new TaskTeamCondition();
				erc1.setTaskId(taskId);
				erc1.setTeamId(teamId);
				List<TaskTeam> erlist1 =new ArrayList<TaskTeam>();
				erlist1=	taskTeamService.findTaskTeamByCondition(erc1);
				if (erlist1 != null && erlist1.size() > 0) {
					TaskTeam er = new TaskTeam();
						er=	erlist1.get(0);
					Integer[] tIds = { er.getPjExamId() };
					List<ExamQuestionWrongVo> vos1 = pjExamService.findExamQuestionWrongbyExamId(tIds, 1, 0.85f);
					if (vos1 != null && vos1.size() > 0) {
						teamList = copy(vos1, 1);
					}
				}
			} else {
				teamList = gradeList;
			}
		map.put("teamList", teamList);
		map.put("gradeList", gradeList);
		return new ResponseVo<Object>("0", map);
	}
	private List<Map<String, Object>> copy(List<ExamQuestionWrongVo> list, Integer type) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
//		knowledgeCatalogService.findKnowledgeNodeByCodes(codes);
//		String 
		Integer[] ids=new Integer[list.size()];
		int i=0;
		for (ExamQuestionWrongVo vo : list) {
			ids[i]=vo.getQuestionId();
			i++;
		}
		Map<Integer,Object>knMap=paQuestionService.findPaQuetionKnowledgeNodeNameByIds(ids);
		for (ExamQuestionWrongVo vo : list) {
			map = new HashMap<String, Object>();
			map.put("questionUuid", empty(vo.getQuestionUuid()));
			map.put("difficulty", vo.getDifficulity());
			if (type == 1) {
				map.put("scoringRate", vo.getTeamScoringRate());
			} else {
				map.put("scoringRate", vo.getGradeScoringRate());
			}
			map.put("parentType", vo.getParentType());
			map.put("examQuestionId", vo.getExamQuestionId());
			map.put("questionType", empty(vo.getQuestionType()));
			if (vo.getParentId()!=0) {
				map.put("isComplex", true);
			} else {
				map.put("isComplex", false);
			}
			map.put("averageTime", vo.getAverageTime()==null?0:vo.getAverageTime());
			float rate=0.0f;
			if(vo.getAnswerCount()==null||vo.getRightAnswerCount()==null||vo.getAnswerCount()==0||vo.getRightAnswerCount()==0){
				
			}else{
				rate=vo.getRightAnswerCount()/vo.getAnswerCount();
			}
			BigDecimal b = new BigDecimal(rate);
			float f1 = b.setScale(1, BigDecimal.ROUND_HALF_DOWN).floatValue();
			map.put("averageRate", f1);

			String complexTitle = vo.getComplexTitle();
			complexTitle = MqtPaperUtil.replaceDomain(complexTitle, domain);
			map.put("complexTitle", complexTitle);
			map.put("paperAnswer", MqtPaperUtil.StringToArray(vo.getAnswer(), domain));
			map.put("correctAnswer", MqtPaperUtil.StringToArray(vo.getCorrectAnswer(), domain));
			map.put("explanation", MqtPaperUtil.replaceDomain(vo.getExplanation(), domain));
			map.put("content", MqtPaperUtil.replaceDomain(vo.getContent(), domain));
			map.put("number", vo.getNumber());
			map.put("score", vo.getScore());
			map.put("paperTitle", vo.getPaperTitle());
			String[] name={};
			if(knMap.get(vo.getQuestionId())!=null){
				name=(String[]) knMap.get(vo.getQuestionId());
			}
			map.put("knowledges", name);
			maplist.add(map);
		}

		return maplist;
	}
	public String empty(String obj) {
		if (obj == null) {
			return "";
		} else {
			return obj;
		}

	}
	private String icon(String icon) {
		if (icon == null || "".equals(icon)) {
			return SysContants.APP_DEFAULT_USERICON;
		}
		return icon;
	}
	@Override
	public Object addFavorites(String appKey, Integer isFavorites,Integer userId,
			Integer userQuestionId) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		paTaskFavoritesService.modifyPaTaskFavoritesByUserQuestionId(userQuestionId, isFavorites, userId);
		Map<String, Object> map = new HashMap<String, Object>();
		return new ResponseVo<Object>("0",map);
	}
	@Override
	public Object wrong(String appKey, Integer taskId, Integer type,Integer userId,
			Integer teamId) {
        Integer pjExamId=0;
		if (taskId == null || appKey == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// 参数为空
		}
		List<TaskTeam> ttlist=new ArrayList<TaskTeam>();
		TaskTeamCondition tt = new TaskTeamCondition();
		tt.setTaskId(taskId);
		tt.setIsDeleted(false);
		tt.setTeamId(teamId);
		ttlist = taskTeamService.findTaskTeamByCondition(tt);
		List taskList=null;
		if(type==PaperContans.PAPERTYPE.intValue()){
			TaskTeamCondition erc = new TaskTeamCondition();
			erc.setTaskId(taskId);
			erc.setIsDeleted(false);
			taskList = taskTeamService.findTaskTeamByCondition(erc);
			if (ttlist.size()==0) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId不存在,请确认");
				info.setMsg("不存在该teamId");
				info.setParam("teamId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			pjExamId=ttlist.get(0).getPjExamId();
		}else{
			LpTask lt=lpTaskService.findLpTaskById(taskId);
			if (lt==null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("taskId不存在,请确认");
				info.setMsg("不存在该taskId");
				info.setParam("taskId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			LpTaskCondition conditon=new LpTaskCondition();
			conditon.setUuid(lt.getUuid());
			conditon.setIsDeleted(false);
		    List<LpTask> ltlist=	lpTaskService.findLpTaskByCondition(conditon);
			taskList=new ArrayList<LpTaskExamUnit>();
			for(LpTask lt1:ltlist){
				LpTaskExamUnitCondition luCondition=new LpTaskExamUnitCondition();
				luCondition.setTaskId(lt1.getId());
				List<LpTaskExamUnit>	taskList1=lpTaskExamUnitService.findLpTaskExamUnitByCondition(luCondition);
				taskList.addAll(taskList1);
			}
		}
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (taskList.size()==0) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("taskId不存在,请确认");
			info.setMsg("不存在该taskId");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> favList = new ArrayList<Map<String, Object>>();
		List<Integer> gradeIds = new ArrayList<Integer>();
		for (int i = 0; i < taskList.size(); i++) {
			Integer examId=0;
			if(type==PaperContans.PAPERTYPE.intValue()){
				TaskTeam task=(TaskTeam) taskList.get(i);
				examId=task.getPjExamId();
			}else{
				LpTaskExamUnit task=(LpTaskExamUnit) taskList.get(i);
				examId=task.getExamId();
				
			}
			gradeIds.add(examId);
		}
			Integer[] gradeId = new Integer[gradeIds.size()];
			for (int i = 0; i < gradeIds.size(); i++) {
				gradeId[i] = gradeIds.get(i);
			}
			List<ExamQuestionWrongVo> vos = pjExamService.findExamQuestionWrongbyExamId(gradeId, 2, 0.85f);
			if (vos != null && vos.size() > 0) {
				gradeList = copy(vos, 2);
			}
			if (teamId != null) {
				List<ExamQuestionWrongVo> vos1 = new ArrayList<ExamQuestionWrongVo>();
					if(type==PaperContans.PAPERTYPE.intValue()){
						Integer[] tIds = { pjExamId};
						 vos1 = pjExamService.findExamQuestionWrongbyExamId(tIds, 1, 0.85f);
					}else{
						LpTaskExamUnitCondition condition=new LpTaskExamUnitCondition();
						condition.setTaskId(taskId);
						List<LpTaskExamUnit> list=lpTaskExamUnitService.findLpTaskExamUnitByCondition(condition);
						Integer[] tIds = new Integer[list.size()];
						for(int i=0;i<list.size();i++){
							tIds[i]=list.get(i).getExamId();
						}
						 vos1 = pjExamService.findExamQuestionWrongbyExamId(tIds, 1, 0.85f);
					}
					if (vos1 != null && vos1.size() > 0) {
						teamList = copy(vos1, 1);
					}
			} else {
				teamList = gradeList;
			}
		List<ExamQuestionWrongVo> favlist=new ArrayList<ExamQuestionWrongVo>();
		PaTaskFavoritesCondition paTaskFavoritesCondition=new PaTaskFavoritesCondition();
		paTaskFavoritesCondition.setTaskId(taskId);
		paTaskFavoritesCondition.setType(type);
		paTaskFavoritesCondition.setUserId(userId);
		paTaskFavoritesCondition.setTeamId(teamId);
		paTaskFavoritesCondition.setIsDeleted(false);
		List<PaTaskFavorites> pflist=paTaskFavoritesService.findPaTaskFavoritesByCondition(paTaskFavoritesCondition);
		if(pflist.size()>0){
			Integer[] examQuestionId=new Integer[pflist.size()];
			int i=0;
			for(PaTaskFavorites pf:pflist){
				examQuestionId[i]=pf.getExamQuestionId();
				i++;
			}
			
			favlist=examQuestionService.findExamQuestionWrongbyExamIdAndQuestionUuids(examQuestionId);
			favList=copy(favlist, 1);
		}
		map.put("favorites", favList);
		map.put("teamList", teamList);
		map.put("gradeList", gradeList);
		return new ResponseVo<Object>("0", map);
	
	}
	@Override
	public Object wrongdetails(String appKey, Integer userId,Integer examQuestionId) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		ExamQuestion  eq=examQuestionService.findExamQuestionById(examQuestionId);
		if(eq==null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("examQuestionId不存在,请确认");
			info.setMsg("不存在该examQuestionId");
			info.setParam("examQuestionId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		PaTaskFavoritesCondition condition =new PaTaskFavoritesCondition();
		condition.setExamQuestionId(examQuestionId);
		condition.setUserId(userId);
		condition.setIsDeleted(false);
		List<PaTaskFavorites>list=paTaskFavoritesService.findPaTaskFavoritesByCondition(condition);
		if(list.size()==0){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId examQuestionId组合不对,请确认");
			info.setMsg("不存在该userId examQuestionId");
			info.setParam("userId examQuestionId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		  PjExam pj=pjExamService.findPjExamById(eq.getExamId());
		  PaPaper pa=paPaperService.findPaPaperByUUid(pj.getPaperUuid());
		  PaQuestionVo vo=paQuestionService.findPosQuestionDetail(pa.getId(), eq.getQuestionUuid());
		  Integer[] ids={vo.getId()};
		  Map<Integer,Object>knMap=paQuestionService.findPaQuetionKnowledgeNodeNameByIds(ids);
		  Map<String,Object> map=new HashMap<String, Object>();
			String[] name={};
			if(knMap.get(vo.getId())!=null){
				name=(String[]) knMap.get(vo.getId());
			}
		  map.put("score", vo.getScore());
		  String parentType="none";
		  if(vo.getParentId().intValue()!=0){
			  PaQuestion ptQuestion=paQuestionService.findPaQuestionById(vo.getParentId());
			  if(ptQuestion!=null){
				  parentType=ptQuestion.getQuestionType();
			  }
		  }
		  map.put("parentType", parentType);
		  map.put("knowledges", name);
		  map.put("questionType", vo.getQuestionType());
		  map.put("isComplex", vo.getParentId().intValue()!=0?1:0);
		  map.put("complexTitle", vo.getEcContent());
		  map.put("paperAnswer", vo.getAnswers());
		  map.put("correctAnswer", vo.getCorrectAnswers());
		  map.put("explanation", vo.getExplanation());
		  map.put("content", vo.getContent());
		  map.put("number", vo.getNodeOrder()+1);
		  map.put("correctAnswer", vo.getCorrectAnswers());
		  String[] userQuestionUuid=new String[list.size()];
		  int i=0;
		  Integer userIds[]=new Integer[list.size()];
		  Map<Integer,PaUserQuestion>idUUidMap= new HashMap<Integer, PaUserQuestion>();
		  for(PaTaskFavorites pf:list){
			  userIds[i]=pf.getStudentUserId();
			  PaUserQuestion pq=paUserQuestionService.findPaUserQuestionById(pf.getUserQuestionId());
			  idUUidMap.put(pq.getId(), pq);
			  userQuestionUuid[i]=pq.getUuid();
			  i++;
		  }
		  Map<Integer, String> imgMap =new HashMap<Integer, String>();
		  imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
		  List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuid);
		  Map<String,List<String>>uuIdmap=new HashMap<String, List<String>>();
		  List<String> sumList=new ArrayList<String>();
		  for(UserFile uf:userFileList){
			  List<String> imgList=new ArrayList<String>();
			  String uuid="";
			   if(uf.getMarkedFileUuid()!=null&&!uf.getMarkedFileUuid().equals("")){
				   uuid=uf.getMarkedFileUuid();
			   }else{
				   uuid=uf.getSourceFileUuid();
			   }
			   if(uuIdmap.get(uf.getUserQuestionUuid())!=null){
				   imgList=uuIdmap.get(uf.getUserQuestionUuid());
			   }
			   System.out.println();
			   imgList.add(uuid);
			   uuIdmap.put(uf.getUserQuestionUuid(), imgList);
			   sumList.add(uuid);
		  }
		 List<TeamStudent>tsList= TeamStudentService.findByTeamId(list.get(0).getTeamId());
		 Map<Integer,String> tsMap=new HashMap<Integer, String>();
		 for(TeamStudent ts:tsList){
			 tsMap.put(ts.getUserId(), ts.getName());
		 }
		 Map<String,FileResult> fileMap= new HashMap<String, FileResult>();
		 if(sumList.size()>0){
			 
			fileMap= fileService.findFileByUUIDs(sumList.toArray(new String[sumList.size()]));
		 }
		 List<Map<String,Object>>studentInfo=new ArrayList<Map<String,Object>>();
		  for(PaTaskFavorites pf:list){
			  Map<String,Object> studentMap=new HashMap<String, Object>();
			  studentMap.put("studentName",tsMap.get(pf.getStudentUserId()));
			  studentMap.put("icon", imgMap.get(pf.getStudentUserId())==null?SysContants.APP_DEFAULT_USERICON:imgMap.get(pf.getStudentUserId()));
			  PaUserQuestion pq=idUUidMap.get(pf.getUserQuestionId());
//			  PaQuestion paq=paQuestionService.findPaperQuestionByUuid(pq.getQuestionUuid());
			  studentMap.put("userAnswer", MqtPaperUtil.parseAnswer(pq.getAnswer(), domain));
			  List<String>imgList=new ArrayList<String>();
			  List<String> uuidList=uuIdmap.get(pq.getUuid());
			  if(uuidList!=null&&uuidList.size()>0){
				  for(String s:uuidList){
					  imgList.add(fileMap.get(s).getHttpUrl());
				  }
			  }
			  studentMap.put("imgList",imgList);
			  studentMap.put("score",pq.getScore());
			  studentInfo.add(studentMap);
		  }
		  map.put("studentInfo", studentInfo);
		  return new ResponseVo<Object>("0", map);
	}
	private Map<String, FileResult> getFileResultMap(List<UserFile> userFileList) {
		Map<String, FileResult> fileResultMap = new HashMap<String, FileResult>();
		if(userFileList!=null && userFileList.size()>0) {
			/**图片资源的uuid集合，因为存在源图和批注后的图片，所以size*2;*/
			String[] uuids = new String[userFileList.size()*2];
			/**遍历userFileList, 获取图片uuid集合*/
			int index = 0;
			for (int i = 0; i < userFileList.size(); i++) {
				uuids[index] = userFileList.get(i).getSourceFileUuid();
				uuids[index+1] = userFileList.get(i).getMarkedFileUuid();
				index=index+2;
			}
			/**根据图片uuid集合获取图片的其它信息集合，如md5,httpurl*/
			fileResultMap = fileService.findFileByUUIDs(uuids);
		}
		return fileResultMap;
	}
	private Map<String, Object> getFileMap(Map<String, FileResult> fileResultMap, UserFile userFile) {
		/**源文件图片信息*/
		FileResult sourceFileResult = fileResultMap.get(userFile.getSourceFileUuid());
		/**批注后图片信息*/
		FileResult markedFileResult = fileResultMap.get(userFile.getMarkedFileUuid());
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("fileId", userFile.getId());
		fileMap.put("sourceFileUuid", userFile.getSourceFileUuid());
		/**设置源文件和批注后的文件返回信息,包括md5, httpUrl*/
		if(sourceFileResult!=null) {
			if(sourceFileResult.getEntityFile()!=null) {
				fileMap.put("sourceFileMd5", sourceFileResult.getEntityFile().getMd5());
			} else {
				fileMap.put("sourceFileMd5", "");
			}
			fileMap.put("sourceFileUrl", sourceFileResult.getHttpUrl());
		} else {
			fileMap.put("sourceFileMd5", "");
			fileMap.put("sourceFileUrl", "");
		}
		
		fileMap.put("markedFileUuid", userFile.getMarkedFileUuid());
		if(markedFileResult!=null) {
			if(markedFileResult.getEntityFile()!=null) {
				fileMap.put("markedFileMd5", markedFileResult.getEntityFile().getMd5());
			} else {
				fileMap.put("markedFileMd5", "");
			}
			fileMap.put("markedFileUrl", markedFileResult.getHttpUrl());
		} else {
			fileMap.put("markedFileMd5", "");
			fileMap.put("markedFileUrl", "");
		}
		return fileMap;
	}
}
