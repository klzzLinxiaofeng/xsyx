package platform.education.rest.paper.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.sun.mail.util.QEncoderStream;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.service.ExamStudentService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.vo.ExamQuestionWrongVo;
import platform.education.generalTeachingAffair.vo.ExamStudentCondition;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.learningDesign.model.LearningPlanInterscore;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.model.LpUnitFile;
import platform.education.learningDesign.service.LearningPlanInterscoreService;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.service.LpUnitFileService;
import platform.education.learningDesign.vo.LearningPlanInterscoreCondition;
import platform.education.learningDesign.vo.LearningPlanInterscoreVo;
import platform.education.paper.constants.PaperContans;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.PaTaskFavorites;
import platform.education.paper.model.Task;
import platform.education.paper.model.TaskInterscore;
import platform.education.paper.model.TaskTeam;
import platform.education.paper.model.TaskUser;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.PaPaperCatalogService;
import platform.education.paper.service.PaPaperHandleService;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaTaskFavoritesService;
import platform.education.paper.service.TaskInterscoreService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.TaskTeamService;
import platform.education.paper.service.TaskUserService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.service.UserWrongService;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.PaPaperCatalogCondition;
import platform.education.paper.vo.PaPaperVo;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.PaTaskFavoritesCondition;
import platform.education.paper.vo.TaskInterscoreCondition;
import platform.education.paper.vo.TaskInterscoreVo;
import platform.education.paper.vo.TaskTeamCondition;
import platform.education.paper.vo.TaskUserCondition;
import platform.education.paper.vo.TaskVo;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.paper.vo.UserPaperCorrectVo;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.ResourceHandlerService;
import platform.education.resource.service.ResourceService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.learningPlan.service.LearningPlanBaseService;
import platform.education.rest.paper.service.PaperTaskStudentRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.model.User;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

public class PaperTaskStudentRestServiceImpl implements PaperTaskStudentRestService {
	private static final Logger log = LoggerFactory.getLogger(PaperTaskStudentRestServiceImpl.class);
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	@Autowired
	@Qualifier("paPaperHandleService")
	private PaPaperHandleService paPaperHandleService;
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	@Autowired
	@Qualifier("catalogResourceService")
	private CatalogResourceService catalogResourceService;
	@Autowired
	@Qualifier("resourceHandlerService")
	private ResourceHandlerService resourceHandlerService;

	@Autowired
	@Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;

	@Autowired
	@Qualifier("resourceService")
	private ResourceService resourceService;

	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	@Autowired
	@Qualifier("examStudentService")
	private ExamStudentService examStudentService;

	@Autowired
	@Qualifier("pjExamService")
	private PjExamService pjExamService;

	@Autowired
	@Qualifier("personService")
	private PersonService personService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("userPaperService")
	private UserPaperService userPaperService;

	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;

	@Autowired
	@Qualifier("userFileService")
	private UserFileService userFileService;

	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;

	@Autowired
	@Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;

	@Autowired
	@Qualifier("examStatService")
	private ExamStatService examStatService;

	@Autowired
	@Qualifier("userWrongService")
	private UserWrongService userWrongService;
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	@Autowired
	@Qualifier("taskTeamService")
	private TaskTeamService taskTeamService;
	@Autowired
	@Qualifier("taskInterscoreService")
	private TaskInterscoreService taskInterscoreService;
	@Autowired
	@Qualifier("learningPlanInterscoreService")
	private LearningPlanInterscoreService learningPlanInterscoreService;

	@Autowired
	@Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	@Autowired
	@Qualifier("taskUserService")
	private TaskUserService taskUserService;
	@Autowired
	@Qualifier("lpUnitFileService")
	private LpUnitFileService  lpUnitFileService;
	@Autowired
    @Qualifier("learningPlanBaseService")
	private LearningPlanBaseService learningPlanBaseService;
	@Autowired
    @Qualifier("lpTaskExamUnitService")
	private LpTaskExamUnitService lpTaskExamUnitService;
	@Autowired
    @Qualifier("teamUserService")
	private TeamUserService teamUserService;
	@Autowired
	@Qualifier("paPaperCatalogService")
	private PaPaperCatalogService paPaperCatalogService;
	@Resource 
	private PaTaskFavoritesService paTaskFavoritesService;

	@Override
	public Object examListBySubject(Integer userId, String appKey,
			Integer pageNumber, Integer pageSize, String subjectCode)
			throws ParseException {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		Integer status = 0;
		if(subjectCode.equals("-1")){
			subjectCode=null;
		}
		// Integer isCheck=0;
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (userId == null) {
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);// 找不到数据
		}
		Order order = new Order();
		order.setProperty("pt.start_time DESC");
		Page page = new Page();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		page.setEnableGetTolaRows(false);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		/** 保存用户任务信息 */
		Map<String, Object> taskMap = new HashMap<String, Object>();
		/** 保存用户任务信息列表 */
		List<Map<String, Object>> tasklist = new ArrayList<Map<String, Object>>();
		PaPaperVo vo = new PaPaperVo();
		vo.setSubjectCode(subjectCode);
        order.setProperty("start_time");		
		List<TaskVo> recordList = taskService.findTaskVoOfReceiveByPaperVo(vo,
				userId, page, order);

		if (recordList == null || recordList.size() == 0) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		if (recordList.size() > 0 && recordList.get(0).getStartTime() == null) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		Date date = new Date();
		List<String> uuidList = new ArrayList<String>();
		Integer[] ownerIds = new Integer[recordList.size()];
		Map<Integer, Object> imgMap = new HashMap<Integer, Object>();
		int j = 0;
//		List<Subject> slist = subjectService.findAllSubjectName();
		Map<String,String> map=subjectService.findAllSubjectNameMap();
		for (int i = 0; i < recordList.size(); i++) {
			taskMap=new HashMap<String, Object>();
			TaskVo ep = recordList.get(i);
			if (ep.getStartTime() != null) {
				if (date.before(ep.getStartTime())) {
					status = 0;
				} else if (date.after(ep.getStartTime())
						&& date.before(ep.getFinishTime())) {
					status = 1;
				} else {
					status = 2;
				}
				taskMap.put("startTime", formatH.format(ep.getStartTime()));
				taskMap.put("finishTime", formatH.format(ep.getFinishTime()));
				taskMap.put("status", status);
				taskMap.put("isCheck", ep.getIsCheck());
				if(ep.getFinishedDate()!=null){
					taskMap.put("finishDate", formatH.format(ep.getFinishedDate()));
				}else {
					taskMap.put("finishDate","");
				}
				String subjectName = "多科目";
				subjectCode = "";
				if (ep.getSubjectCode() != null
						&& !ep.getSubjectCode().equals("0")) {
					subjectCode = ep.getSubjectCode();
					subjectName=map.get(subjectCode);
					if(subjectName==null){
						subjectName="";
					}
				}
				taskMap.put("taskId", ep.getId());
				taskMap.put("subjectName", subjectName);
				taskMap.put("publisherName", ep.getPublisherName() == null ? ""
						: ep.getPublisherName());
				taskMap.put("publishedFlag", ep.getPublishFlag());
				taskMap.put("score",ep.getScore());
				taskMap.put("difficulity",ep.getDifficultyString());
				taskMap.put("name", ep.getTitle());
				taskMap.put("isCheck", ep.getIsCheck());
				PaPaperCatalogCondition pc=new PaPaperCatalogCondition();
				pc.setPaperId(ep.getPaperId());
			    List<PaPaperCatalog>	pclist=paPaperCatalogService.findPaPaperCatalogByCondition(pc);
			    String catalogName="";
			    if(pclist!=null&&pclist.size()==1){
			    	String catalogCode = pclist.get(0).getCatalogCode();
					/**获取目录名称*/
					 catalogName = resTextbookCatalogService.getFullNameByCode(catalogCode," ");
					
			    }
			    taskMap.put("catalogName", catalogName);
				tasklist.add(taskMap);
			}
		}
		return new ResponseVo<Object>("0", tasklist);
	}
	
	

	@Override
	public Object getCorrectListByTeam(Integer taskId, String appKey,
			Integer teamId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			/** 试卷发布到班级详情 */
			TaskTeamCondition ttCondition = new TaskTeamCondition();
			ttCondition.setTaskId(taskId);
			ttCondition.setTeamId(teamId);
			List<TaskTeam> examRelateList = new ArrayList<TaskTeam>();
			examRelateList = taskTeamService
					.findTaskTeamByCondition(ttCondition);
			if (examRelateList.size() == 0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// 找不到数据
			}
			/** 获取测试详情 */
			PjExam pjExam = pjExamService.findPjExamById(examRelateList.get(0)
					.getPjExamId());

			if (pjExam == null) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// 找不到数据
			}

			/** 获取参与测试学习情况列表 */
			ExamStudentCondition examStudentCondition = new ExamStudentCondition();
			examStudentCondition.setExamId(pjExam.getId());
			List<ExamStudent> examStudentList = examStudentService
					.findExamStudentByCondition(examStudentCondition);

			if (examStudentList.size() == 0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// 找不到数据
			}

			/** 用户id的数组 */
			Integer[] userIds = new Integer[examStudentList.size()];
			for (int i = 0; i < examStudentList.size(); i++) {
				userIds[i] = examStudentList.get(i).getUserId();
			}

			/** 获取用户的集合 */
			List<User> userList = userService.findUserByIds(userIds);

			/** 获取Person的集合(用于获取用户的name) map的key为userId, value为person */
			Map<Integer, Person> personMap = getPersons(userList);

			/** 获取学生答卷列表 */
			UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setTeamId(teamId);
			userPaperCondition.setOwnerId(taskId);
			List<UserPaper> userPaperList = userPaperService
					.findUserPaperByCondition(userPaperCondition);

			/** userPaperMap，以userId为key, userPeper为value */
			Map<Integer, UserPaper> userPaperMap = new HashMap<Integer, UserPaper>();
			for (UserPaper userPaper : userPaperList) {
				userPaperMap.put(userPaper.getUserId(), userPaper);
			}

			/** 缺考人数列表 */
			List<Map<String, Object>> examineList = new ArrayList<Map<String, Object>>();
			/** 已批卷列表 */
			List<Map<String, Object>> scoreFinishedList = new ArrayList<Map<String, Object>>();
			/** 未批卷列表 */
			List<Map<String, Object>> scoreunFinishList = new ArrayList<Map<String, Object>>();
			for (ExamStudent examStudent : examStudentList) {
				Map<String, Object> map = new HashMap<String, Object>();
				Person person = personMap.get(examStudent.getUserId());
				/** 获取用户头像 */
				String imgPath = ImgUtil.getImgSrc(examStudent.getUserId(),
						profileService);

				map.put("studentId", person.getId());
				map.put("studentName", examStudent.getName());
				map.put("userId", examStudent.getUserId());
				map.put("iconUrl", imgPath);

				/** score为-1为缺考 */
				/*
				 * if(examStudent.getScore()-0 == -1) { examineList.add(map); }
				 * else {
				 */
				/** 根据userId获取userPaper */
				UserPaper userPaper = userPaperMap.get(examStudent.getUserId());
				if (userPaper != null) {
					/** 卷面得分 */
					map.put("score", userPaper.getScore());
					/** 是否完成批卷 */
					if (userPaper.getScoreFinished()) {
						/** 已完成 */
						scoreFinishedList.add(map);
					} else {
						/** 未完成 */
						scoreunFinishList.add(map);
					}
				} else {
					/** 缺考 */
					examineList.add(map);
				}
				/* } */
			}
			String startTime = "";
			String finishTime = "";
			Integer isInterscore = 0;
			if (examRelateList.get(0).getInterscoreStartTime() != null) {
				startTime = formatH.format(examRelateList.get(0)
						.getInterscoreStartTime());
			}

			if (examRelateList.get(0).getInterscoreFinishTime() != null) {
				finishTime = formatH.format(examRelateList.get(0)
						.getInterscoreFinishTime());
			}
			if (examRelateList.get(0).getIsInterscoring() != null
					&& examRelateList.get(0).getIsInterscoring()) {
				isInterscore = 1;
			}
			TaskInterscoreCondition examInterscoreCondition = new TaskInterscoreCondition();

			examInterscoreCondition
					.setTaskId(examRelateList.get(0).getTaskId());
			examInterscoreCondition.setTeamId(teamId);
			List<TaskInterscore> examInterscoreList = taskInterscoreService
					.findTaskInterscoreByCondition(examInterscoreCondition);

			int interscoredCount = 0;

			for (TaskInterscore examInterscore : examInterscoreList) {
				if (examInterscore.getScoringTime() != null) {
					interscoredCount++;
				}
			}

			/** 构建返回值数据结构 */
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("userCount", examStudentList.size());
			result.put("finishedCount", scoreFinishedList.size());
			result.put("interscoredCount", interscoredCount);
			result.put("examineList", examineList);
			result.put("finishedList", scoreFinishedList);
			result.put("unFinishList", scoreunFinishList);
			result.put("isInterscore", isInterscore);
			result.put("startTime", startTime);
			result.put("finishTime", finishTime);

			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取班级评卷情况异常");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}

	@Override
	public Object getCorrectListByLearner( String appKey, Integer taskId, Integer userId, Integer unitId) {
		try {
			String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			/** 获取用户答题详情 */
			UserPaper userPaper = userPaperService.findUniqueUserPaper(taskId, userId, unitId);
			
			if (userPaper == null) {
				log.error("用户答卷情况为空....");
				return new ResponseVo<Object>("0", new HashMap<String, Object>());// 找不到数据
			}
			/**获取用户答题详情*/
			List<UserPaperCorrectVo> userPaperCorrectVoList = userPaperService.findUserPaperCorrectByTaskId(taskId, unitId, userId, null,null);
			String[] userQuestionUuids = new String[userPaperCorrectVoList.size()];
			
			/**提交答案处理*/
			for (int i = 0; i < userPaperCorrectVoList.size(); i++) {
				UserPaperCorrectVo userPaperCorrectVo = userPaperCorrectVoList.get(i);
				userQuestionUuids[i] = userPaperCorrectVo.getUserQuestionUuId();
				
				String[] answers = MqtPaperUtil.parseAnswer(userPaperCorrectVo.getDbAnswer(), domain);
				userPaperCorrectVo.setAnswer(answers);
				String[] correctAnswer = MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbCorrectAnswer(), domain);
				userPaperCorrectVo.setCorrectAnswer(correctAnswer);
			}
			/**根据题号绑定图片*/
			List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuids);
			
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
			
			for (UserPaperCorrectVo userPaperCorrectVo : userPaperCorrectVoList) {
				Map<String, Object> vo = new HashMap<>();
				vo.put("questionType", userPaperCorrectVo.getQuestionType());
				vo.put("questionUuid", userPaperCorrectVo.getQuestionUuid());
				vo.put("pos", userPaperCorrectVo.getPos());
				vo.put("correctAnswer", userPaperCorrectVo.getCorrectAnswer());
				vo.put("answer", userPaperCorrectVo.getAnswer());
				vo.put("correctScore", userPaperCorrectVo.getCorrectScore());
				
				vo.put("score", userPaperCorrectVo.getScore());
				if(userPaperCorrectVo.getQuestionProperty().intValue()==2){
					vo.put("IsSubjective", 1);
				}else{
					vo.put("IsSubjective", 0);
				}
				List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
				for (UserFile userFile : userFileList) {
					if(userFile.getUserQuestionUuid().equals(userPaperCorrectVo.getUserQuestionUuId())) {
						Map<String, Object> map = getUserFileDetail(userFile);
						fileList.add(map);
					}
				}
				vo.put("files", fileList);
				resultList.add(vo);
			}

			/**获取被互评人的id和名字*/
			//int scoredUserId = getScoredUserId(userPaper.getId(), unitId);
			//String scoredUserName = getScoredUserName(scoredUserId);
			
			Map<String, Object> result = new HashMap<>();
			
			BigDecimal bd = new BigDecimal(userPaper.getScore());    
			bd = bd.setScale(1,4);
			
			result.put("totalScore", bd.floatValue());
			result.put("answers", resultList);

			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取个人答卷情况异常");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}

	private Map<String, Object> getUserFileDetail(UserFile userFile) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileId", userFile.getId());
		/** 图片详情 */
		FileResult result = fileService.findFileByUUID(userFile.getSourceFileUuid());
		if (result != null) {
			/** 源图片（学生上传）uuid */
			map.put("sourceFileUuid", userFile.getSourceFileUuid());
			if (result.getEntityFile() != null) {
				/** 源图片md5 */
				map.put("sourceFileMd5", result.getEntityFile().getMd5());
			}
			/** 源图片下载地址 */
			map.put("sourceFileUrl", result.getHttpUrl());
		}
		result = fileService.findFileByUUID(userFile.getMarkedFileUuid());
		if (result != null) {
			/** 批注后的图片(老师上传) */
			map.put("markedFileUuid", userFile.getMarkedFileUuid());
			/** 批注图片md5 */
			if (result.getEntityFile() != null) {
				map.put("markedFileMd5", result.getEntityFile().getMd5());
			}
			/** 批注图片下载地址 */
			map.put("markedFileUrl", result.getHttpUrl());
		}
		return map;
	}

	private String getScoredUserName(int scoredUserId) {
		if (scoredUserId != 0) {
			User user = userService.findUserById(scoredUserId);
			Person person = personService.findPersonById(user.getPersonId());
			if (person != null) {
				return person.getName();
			}
		}
		return "";
	}

	private int getScoredUserId(Integer userPaperId, Integer unitId) {
		int scoredUserId = 0;
		if (unitId == null) {
			TaskInterscoreCondition examInterscoreCondition = new TaskInterscoreCondition();
			examInterscoreCondition.setScoredPaperId(userPaperId);
			List<TaskInterscore> examInterscoreList = taskInterscoreService.findTaskInterscoreByCondition(examInterscoreCondition);
			if (examInterscoreList.size() > 0) {
				TaskInterscore examInterscore = examInterscoreList.get(0);
				scoredUserId = examInterscore.getScoredUserId();
			}
		} else {
			LearningPlanInterscoreCondition condition = new LearningPlanInterscoreCondition();
			condition.setScoredPaperId(userPaperId);
			List<LearningPlanInterscore> list = learningPlanInterscoreService.findLearningPlanInterscoreByCondition(condition);
			if (list.size() > 0) {
				LearningPlanInterscore learningPlanInterscore = list.get(0);
				scoredUserId = learningPlanInterscore.getScoredUserId();
			}
		}
		return scoredUserId;
	}

	@Override
	public Object taskCorrectFinish(String appKey, Integer taskId, String students, String questionUuid, Integer unitId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			Date date=new Date();
			Map<String, Object> result = new HashMap<String, Object>();
			
			/**用户userId的集合*/
			JSONArray usersArray1 = JSONArray.fromObject(students);
			
			/**答题记录或者答卷记录是否被更改，如果被更改，刚需要更新统计data_change*/
			boolean dataChange = false;
			
			/**为了复用接口构造了一个长度的数组，里面装题目的uuid*/
			String[] questionUUIDs = new String[]{questionUuid};
			/**获取试卷单题信息*/
			List<PaQuestionVo> questionList = paQuestionService.findPaperQuestionByUUIDs(questionUUIDs);
			
			if(questionList.size()==0) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("该题目已经被删除，无法进行评卷操作");
				info.setMsg("该题目已经被删除，无法进行评卷操作");
				info.setParam("questionUuid");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			/**获取该任务用户答题情况集合*/
			Map<String, UserQuestion> userQuestionsMap = this.userQuestionService.findQuestionAnswerList(taskId, unitId, questionUuid);

			/**用户答卷情况*/
			List<UserPaper> userPaperList = userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, unitId);
			
			if(userPaperList.size()==0) {
				log.info("学生答卷情况为空.....");
				return new ResponseVo<Object>("0",result);
			}
			
			Integer teamId = userPaperList.get(0).getTeamId();
			
			/**构建用户id和答卷情况一对一数据结构*/
			Map<Integer, UserPaper> userPaperMap = new HashMap<Integer, UserPaper>();
			for (UserPaper userPaper : userPaperList) {
				userPaperMap.put(userPaper.getUserId(), userPaper);
			}
			
			List<UserFile> userFiles = new ArrayList<UserFile>();
			JSONArray usersArray=new JSONArray();
			//拿出已修改的记录
			for (int i = 0; i < usersArray1.size(); i++) {
				JSONObject user = usersArray1.getJSONObject(i);
				if(user.get("isUpdate")!=null&&user.getInt("isUpdate")!=0){
					usersArray.add(user);
				}
			}
			/**更新批卷后的得分记录*/
			for (int i = 0; i < usersArray.size(); i++) {
				PaQuestionVo paperQuestion = questionList.get(0);
				JSONObject user = usersArray.getJSONObject(i);
				
				/**图片数组*/
				JSONArray fileArrays = user.getJSONArray("files");
				/**处理图片数组*/
				List<UserFile> userFileList = handleUserFile(fileArrays);
				userFiles.addAll(userFileList);
				
				Integer uId = user.getInt("studentId");
				Double score = user.getDouble("score");
				
				/**单题得分*/
				UserQuestion userQuestion = userQuestionsMap.get(uId+paperQuestion.getUuid());
				if(userQuestion==null) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("评卷失败, 用户id: "+uId+"对应的答题记录为空");
					info.setMsg("评卷失败, 用户id: "+uId+"对应的答题记录为空");
					info.setParam("studentId");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				}
				/**获取答卷记录*/
				UserPaper userPaper = userPaperMap.get(uId);
				if(userPaper==null) {
					log.error("用户答卷记录为空");
				} else {
					/**把批卷后的分数加上*/
					userPaper.setScore(userPaper.getScore() + score - userQuestion.getScore());
					userPaper.setScoreTime(new Date());
					/**更新记录*/
					userPaperService.modify(userPaper);
				}
				
				/**获得该道题的总分才算正确*/
				if(paperQuestion.getScore() - score==0) {
					if(!userQuestion.getIsCorrect()){
						if(paperQuestion.getScore()-userQuestion.getScore()!=0) {
							paperQuestion.setRightAnswerCount(paperQuestion.getRightAnswerCount()+1);
						}
						userQuestion.setIsCorrect(true);
					}
				} else {
					if(userQuestion.getIsCorrect()){
					if(paperQuestion.getRightAnswerCount()>0) {
						if(paperQuestion.getScore()-userQuestion.getScore()==0) {
							paperQuestion.setRightAnswerCount(paperQuestion.getRightAnswerCount()-1);
						}
					}
					userQuestion.setIsCorrect(false);
				}
				}
				if(paperQuestion.getAnswerCount()!=0){
					paperQuestion.setDifficulity(paperQuestion.getRightAnswerCount() /(1.0f*paperQuestion.getAnswerCount()));
				}
				userQuestion.setScore(score);
				
				/**更新dataChange标志*/
				if(!dataChange) {
					dataChange = true;
				}
				userQuestion.setModifyDate(date);
				userQuestionService.modify(userQuestion);
			}
			
			if(userFiles.size()>0) {
				/**一次更新图片记录*/
				userFileService.batchUpdateMarkedFile(userFiles);
			}
			
			/**做一次批量更新userQuestion;*/
			Collection<UserQuestion> userQuestionCollection = userQuestionsMap.values();
			List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>(userQuestionCollection.size());
			Iterator<UserQuestion> iterator = userQuestionCollection.iterator();
			while (iterator.hasNext()) {
				userQuestionList.add(iterator.next());
			}
//			this.userQuestionService.batchUpdateScoreAndIsCorrect(userQuestionList);
			paQuestionService.modifyRightAnswerCountBatch(questionList);

			if(dataChange) {
				updateExamStat(taskId, teamId, unitId);
			}
 			
 			return new ResponseVo<Object>("0",result);
		}catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("提交评卷接口异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
	}
	
	private void updateExamStat(Integer taskId, Integer teamId, Integer unitId) {
		Integer examId = null;
		if(unitId==null) {
			TaskTeam taskTeam = taskTeamService.findTaskTeamUnique(taskId, teamId);
			examId = taskTeam.getPjExamId();
		} else {
			LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitByTaskIdAndUnitId(taskId, unitId);
			examId = lpTaskExamUnit.getExamId();
		}
		if(examId!=null) {
			/**获取统计情况*/
			ExamStat examStat = examStatService.findExamStatByExamId(examId);
			if(examStat!=null) {
				/**更新统计data_chage*/
				examStat.setDataChanged(true);
				examStatService.modify(examStat);
			}
		}
	}

	private List<UserFile> handleUserFile(JSONArray fileArrays) {
		List<UserFile> userFiles = new ArrayList<UserFile>();
		/**更新批注后的图片*/
		for (int i = 0; i < fileArrays.size(); i++) {
			JSONObject file = fileArrays.getJSONObject(i);
			/**用户提交图片的记录的id*/
			Integer fileId = file.getInt("fileId");
			UserFile userFile = userFileService.findUserFileById(fileId);
			if(userFile!=null) {
				String markedFileUuid = file.getString("markedFileUuid");
				/**批注后的图片*/
				userFile.setMarkedFileUuid(markedFileUuid);
				userFile.setModifyDate(new Date());
				/**更新记录*/
				userFiles.add(userFile);
			}
		}
		return userFiles;
	}

	@Override
	public Object wrong(String appKey, Integer taskId, Integer teamId) {
		if (taskId == null || appKey == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					null);// 参数为空
		}

		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>();
		List<Integer> gradeIds = new ArrayList<Integer>();
		Task task=taskService.findTaskById(taskId);
		TaskTeamCondition erc = new TaskTeamCondition();
		erc.setTaskId(taskId);
		erc.setIsDeleted(false);
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
		List<ExamQuestionWrongVo> vos = pjExamService
				.findExamQuestionWrongbyExamId(gradeId, 2, 0.85f);
		if (vos != null && vos.size() > 0) {
			gradeList = copy(vos, 2);
		}
		if (teamId != null) {
			TaskTeamCondition ttconCondition = new TaskTeamCondition();
			ttconCondition.setTaskId(taskId);
			ttconCondition.setTeamId(teamId);
			List<TaskTeam> erlist1 = taskTeamService
					.findTaskTeamByCondition(ttconCondition);
			if (erlist1 != null && erlist1.size() > 0) {
				TaskTeam er = erlist1.get(0);
				Integer[] tIds = { er.getPjExamId() };
				List<ExamQuestionWrongVo> vos1 = pjExamService
						.findExamQuestionWrongbyExamId(tIds, 1, 0.85f);
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

	@Override
	public Object interscoreStart(String appKey, Integer taskId,
			Integer teamId, String startTime, String finishTime,
			Integer isInterscore) throws ParseException {
		if (taskId == null || appKey == null || teamId == null
				|| startTime == null || finishTime == null
				|| isInterscore == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					null);// 参数为空
		}
		Date date=new Date();
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		boolean on = false;
		if (isInterscore == 1) {
			on = true;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if(date.after(sdf.parse(startTime))){
		}else{
			date=sdf.parse(startTime);
			
		}
		if (date.after(sdf.parse(finishTime))) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("开始时间大于结束时间,请确认");
			info.setMsg("开始时间大于结束时间");
			info.setParam("startTime,finishTime");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		TaskTeamCondition ttcondition = new TaskTeamCondition();
		ttcondition.setTaskId(taskId);
		ttcondition.setTeamId(teamId);
		List<TaskTeam> erlist = taskTeamService
				.findTaskTeamByCondition(ttcondition);
		if (erlist != null && erlist.size() > 0) {
			TaskTeam er = erlist.get(0);
			if (on) {
				if (sdf.parse(finishTime) != null
						&& sdf.parse(finishTime).before(new Date())) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("结束时间不能早于当前时间");
					info.setMsg("结束时间不能早于当前时间");
					info.setParam("finishTime");
					return new ResponseError(CommonCode.S$INVALID_DATA, info);
				}
				if (er.getInterscoreStartTime() == null) {
					er.setInterscoreStartTime(date);
				}
				er.setInterscoreFinishTime(sdf.parse(finishTime));
			}
			er.setIsInterscoring(on);
			UserPaperCondition upc1 = new UserPaperCondition();
			upc1.setOwnerId(taskId);
			upc1.setType(2);
			upc1.setTeamId(teamId);
			List<UserPaper> uplist = this.userPaperService
					.findUserPaperByCondition(upc1);
			if (uplist == null || uplist.size() <= 1) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("班级答题人数小于2,无法互评");
				info.setMsg("班级答题人数小于2,无法互评");
				info.setParam("taskId,teamId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			taskTeamService.modify(er);
			InitExamInterscore(taskId, teamId);

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("startTime", sdf.format(date));
		return new ResponseVo<Object>("0", map);
	}

	@Override
	public Object interscoreListBySubject(Integer userId, String appKey, String subjectCode)
			throws ParseException {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if(subjectCode.equals("-1")){
			subjectCode=null;
		}
		/**试卷互评*/
		List<TaskInterscoreVo> taskInterscoreVoList = taskInterscoreService.findExamScoringTask(userId, subjectCode, null, null);
		/**导学案互评*/
		List<LearningPlanInterscoreVo> learningPlanInterscoreVoList = learningPlanInterscoreService.findInterscoreList(userId, subjectCode, null);
		
		int paperIndex = 0;
		int lpIndex = 0;
		int taskSize = taskInterscoreVoList.size()+learningPlanInterscoreVoList.size();
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(taskSize);
		
		while(taskSize!=(paperIndex+lpIndex)) {
			TaskInterscoreVo taskInterscoreVo = null;
			if(taskInterscoreVoList.size()>0 && taskInterscoreVoList.size()!=paperIndex) {
				taskInterscoreVo = taskInterscoreVoList.get(paperIndex);
			}
			LearningPlanInterscoreVo learningPlanInterscoreVo = null;
			if(learningPlanInterscoreVoList.size()>0 && learningPlanInterscoreVoList.size()!=lpIndex) {
				learningPlanInterscoreVo = learningPlanInterscoreVoList.get(lpIndex);
			}
			
			Map<String, Object> taskMap = null;
			Map<String,String> subjectMap=new HashMap<String, String>();
			subjectMap=subjectService.findAllSubjectNameMap();
			if(taskInterscoreVo==null) {
				taskMap = getLearningPlanInterscoreResultMap(learningPlanInterscoreVo,subjectMap);
				lpIndex++;
			} else if(learningPlanInterscoreVo==null) {
				taskMap = getTaskInterscoreResultMap(taskInterscoreVo,subjectMap);
				paperIndex++;
			} else {
				if(taskInterscoreVo.getStartTime().before(learningPlanInterscoreVo.getStartTime())) {
					taskMap = getTaskInterscoreResultMap(taskInterscoreVo,subjectMap);
					paperIndex++;
				} else {
					taskMap = getLearningPlanInterscoreResultMap(learningPlanInterscoreVo,subjectMap);
					lpIndex++;
				}
			}
			result.add(taskMap);
		}
		
		return new ResponseVo<Object>("0", result);
	}

	private Map<String, Object> getTaskInterscoreResultMap(TaskInterscoreVo taskInterscoreVo,Map<String,String>subjectMap) {
		/** 时间格式化 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> taskMap = new HashMap<String, Object>();
		
		int status = 0;
		if(taskInterscoreVo.getStartTime().before(new Date())) {
			status = 1;
		}
		int isInterscore = 0;
		if(taskInterscoreVo.getScoringTime()!=null) {
			isInterscore = 1;
		}
		taskMap.put("taskId", taskInterscoreVo.getTaskId());
		taskMap.put("startTime", dateFormat.format(taskInterscoreVo.getStartTime()));
		taskMap.put("finishTime", dateFormat.format(taskInterscoreVo.getFinishTime()));
		taskMap.put("status", status);
		taskMap.put("isInterscore", isInterscore);
		taskMap.put("type", "paper");
		taskMap.put("title", taskInterscoreVo.getTitle());
		taskMap.put("scoredUserId", taskInterscoreVo.getScoredUserId());
		taskMap.put("score", taskInterscoreVo.getScore());
		taskMap.put("difficulity", taskInterscoreVo.getDifficultyString());
		String catalogName = "";
		if(!taskInterscoreVo.getCatalogCode().equals("0")){
			
			catalogName = resTextbookCatalogService.getFullNameByCode(taskInterscoreVo.getCatalogCode(), " ");
		}
		String subjectName="多科目";
		if(!taskInterscoreVo.getSubjectCode().equals("0")){
			subjectName=subjectMap.get(taskInterscoreVo.getSubjectCode());
		}
		taskMap.put("catalogName", catalogName);
		taskMap.put("subjectName", subjectName);
		return taskMap;
	}

	private Map<String, Object> getLearningPlanInterscoreResultMap(LearningPlanInterscoreVo learningPlanInterscoreVo,Map<String,String>subjectMap) {
		/** 时间格式化 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Map<String, Object> taskMap = new HashMap<String, Object>();
		
		int status = 0;
		if(learningPlanInterscoreVo.getStartTime().before(new Date())) {
			status = 1;
		}
		int isInterscore = 0;
		if(learningPlanInterscoreVo.getScoringTime()!=null) {
			isInterscore = 1;
		}
		taskMap.put("taskId", learningPlanInterscoreVo.getTaskId());
		taskMap.put("startTime", dateFormat.format(learningPlanInterscoreVo.getStartTime()));
		taskMap.put("finishTime", dateFormat.format(learningPlanInterscoreVo.getFinishTime()));
		taskMap.put("status", status);
		taskMap.put("isInterscore", isInterscore);
		taskMap.put("type", "learningPlan");
		taskMap.put("title", learningPlanInterscoreVo.getUnitTitle());
		taskMap.put("unitId", learningPlanInterscoreVo.getUnitId());
		taskMap.put("scoredUserId", learningPlanInterscoreVo.getScoredUserId());
		String catalogName = resTextbookCatalogService.getFullNameByCode(learningPlanInterscoreVo.getCatalogCode(), " ");
		String subjectName="";
	    subjectName=subjectMap.get(learningPlanInterscoreVo.getSubjectCode());
		taskMap.put("catalogName", catalogName);
		taskMap.put("subjectName", subjectName);
		return taskMap;
	}

	@Override
	public Object interscoreDetails(String appKey, Integer taskId,
			Integer teamId) {
		if (taskId == null || appKey == null || teamId == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					null);// 参数为空
		}
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String, Object> total = new HashMap<String, Object>();
		Map<Integer, String> allMap = new HashMap<Integer, String>();
		Map<Integer, String> copyMap = new HashMap<Integer, String>();
		List<Map<String, Object>> elist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> nolist = new ArrayList<Map<String, Object>>();
        List<UserPaper> uplist=new ArrayList<UserPaper>();
        UserPaperCondition upCondition=new UserPaperCondition();
        upCondition.setOwnerId(taskId);
        upCondition.setType(PaperType.EXAM);
        uplist=userPaperService.findUserPaperByCondition(upCondition);
        if(uplist==null||uplist.size()==0){
        	ResponseInfo info = new ResponseInfo();
			info.setDetail("无作答记录，请确认");
			info.setMsg("不存在该teamId");
			info.setParam("teamId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        }
        Map<Integer,Double> upmap=new HashMap<Integer, Double>();
        for(UserPaper up:uplist){
        	upmap.put(up.getId(), up.getScore());
        }
		TaskUserCondition tuCondition = new TaskUserCondition();
		tuCondition.setTaskId(taskId);
		tuCondition.setTeamId(teamId);
		tuCondition.setIsDelete(false);
		List<TaskUser> eprlist = taskUserService
				.findTaskUserByCondition(tuCondition);
		if (eprlist == null || eprlist.size() == 0) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("互评任务不存在,请确认");
			info.setMsg("不存在该互评任务");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
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
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("evaluateingName", evaluateingName);
				map.put("evaluatedName", evaluatedName);
				map.put("evaluateingIcon", evaluateingIcon);
				map.put("evaluatedIcon", evaluatedIcon);
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
		List<Map<String, String>> missList = new ArrayList<Map<String, String>>();
		for (Map.Entry<Integer, String> entry : copyMap.entrySet()) {
			Map<String, String> missMap = new HashMap<String, String>();
			String userIcon = icon(iconMap.get(entry.getKey()));
			missMap.put("userName", entry.getValue());
			missMap.put("userIcon", userIcon);
			missList.add(missMap);

		}
		total.put("evaluationList", elist);
		total.put("notEvaluationList", nolist);
		total.put("missExam", missList);

		return new ResponseVo<Object>("0", total);
	}

	@Override
	public Object InitInterscore(String appKey, Integer taskId, Integer teamId)
			throws ParseException {
		Integer missExam = 0;
		Integer notInterscore = 0;
		Integer interscore = 0;
		Integer isFirst = 1;
		Integer isEmpty = 0;
		TaskTeamCondition ttCondition = new TaskTeamCondition();
		ttCondition.setTaskId(taskId);
		ttCondition.setTeamId(teamId);
		List<TaskTeam> erlist = taskTeamService
				.findTaskTeamByCondition(ttCondition);
		if (erlist == null || erlist.size() == 0) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("互评任务不存在,请确认");
			info.setMsg("不存在该互评任务");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		TaskTeam er = erlist.get(0);
		TaskUserCondition tuCondition = new TaskUserCondition();
		tuCondition.setTaskId(taskId);
		tuCondition.setTeamId(teamId);
		List<TaskUser> eprlist = taskUserService
				.findTaskUserByCondition(tuCondition);
		UserPaperCondition upc = new UserPaperCondition();
		upc.setOwnerId(taskId);
		upc.setType(2);
		upc.setTeamId(teamId);
		List<UserPaper> uplist = new ArrayList<UserPaper>();
		uplist = this.userPaperService.findUserPaperByCondition(upc);
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<TaskInterscore> eilist = new ArrayList<TaskInterscore>();

		TaskInterscoreCondition tiCondition = new TaskInterscoreCondition();
		tiCondition.setTaskId(taskId);
		tiCondition.setTeamId(teamId);
		tiCondition.setIsDeleted(false);
		eilist = taskInterscoreService
				.findTaskInterscoreByCondition(tiCondition);

		missExam = eprlist.size() - uplist.size();

		if (eilist == null || eilist.size() == 0) {

		} else {

			for (TaskInterscore ei : eilist) {
				if (ei.getScoringTime() == null) {
					notInterscore++;
				} else {
					interscore++;
				}
			}

			isEmpty = eprlist.size() - eilist.size();
		}
		if (er.getInterscoreStartTime() != null) {
			isFirst = 0;
		}
		map.put("inExam", uplist.size());
		map.put("missExam", missExam);
		map.put("notInterscore", notInterscore);
		map.put("interscore", interscore);
		map.put("isFirst", isFirst);
		map.put("inEmpty", isEmpty);

		return new ResponseVo<Object>("0", map);
	}

	private Map<Integer, Object> findUserFileMap(Integer userId,
			Integer[] owerIds) {

		List<UserPaper> uplist = new ArrayList<UserPaper>(owerIds.length);
		List<UserFile> uflist = new ArrayList<UserFile>();
		List<String> markImgList = new ArrayList<String>();
		Map<String, FileResult> markImgMap = new HashMap<String, FileResult>();
		Map<Integer, Object> upMap = new HashMap<Integer, Object>();
		Map<Integer, Object> ownerMap = new HashMap<Integer, Object>();
		uplist = userPaperService.findUserPaperByOwnerIdsAndUserId(owerIds,
				null, PaperType.EXAM, userId);
		if (uplist != null && uplist.size() > 0) {
			owerIds = new Integer[uplist.size()];
			int i = 0;
			for (UserPaper up : uplist) {
				owerIds[i] = up.getId();
				i++;
			}
			// long start1 = System.currentTimeMillis();
			uflist = userFileService.findUserFileByUserPaperIds(owerIds);
			// long end1 = System.currentTimeMillis();
			// System.out.println("userPaper共花费"+(end1-start1)+"毫秒");
			if (uflist != null && uflist.size() > 0) {
				for (UserFile userFile : uflist) {
					if (userFile.getMarkedFileUuid() != null
							&& !userFile.getMarkedFileUuid().equals("")) {
						markImgList.add(userFile.getMarkedFileUuid());
					} else {
						markImgList.add(userFile.getSourceFileUuid());
					}
				}
				if (uflist != null && uflist.size() > 0) {
					String[] mark = new String[markImgList.size()];
					mark = markImgList.toArray(mark);
					// long start = System.currentTimeMillis();
					markImgMap = this.fileService.findFileByUUIDs(mark);
					// long end = System.currentTimeMillis();
					// System.out.println("找文件总共花费"+(end-start)+"毫秒");
				}
				for (UserFile userFile : uflist) {
					FileResult result = null;
					/** 如果存在老师批注后的图片，则返回批注后的图片 */
					if (userFile.getMarkedFileUuid() != null) {
						// result =
						// fileService.findFileByUUID(userFile.getMarkedFileUuid());
						result = markImgMap.get(userFile.getMarkedFileUuid());
					} else {
						/** 不存在批注后的图片，直接返回源图 */
						// result =
						// fileService.findFileByUUID(userFile.getSourceFileUuid());
						result = markImgMap.get(userFile.getSourceFileUuid());
					}

					List<String> list = (List<String>) upMap.get(userFile
							.getUserPaperId());
					if (list != null && list.size() > 0) {

					} else {
						list = new ArrayList<String>();
					}
					if (result != null) {
						list.add(result.getHttpUrl());
					}
					upMap.put(userFile.getUserPaperId(), list);
				}
				for (UserPaper up : uplist) {
					List<String> list = (List<String>) upMap.get(up.getId());
					if (list != null && list.size() > 0) {
					} else {
						list = new ArrayList<String>();
					}
					ownerMap.put(up.getOwnerId(), list);
				}
			}
		}
		return ownerMap;
	}

	/**
	 * 获取Person的集合， 以useId为id, Person为value的map集合
	 * 
	 * @param userList
	 * @return
	 */
	private Map<Integer, Person> getPersons(List<User> userList) {
		Integer[] personIds = new Integer[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			personIds[i] = userList.get(i).getPersonId();
		}

		List<Person> personList = personService.findbyIds(personIds);

		Map<Integer, Person> map = new HashMap<Integer, Person>();
		for (User user : userList) {
			for (Person person : personList) {
				if (person.getId() - user.getPersonId() == 0) {
					map.put(user.getId(), person);
				}
			}
		}
		return map;
	}

	private List<Map<String, Object>> copy(List<ExamQuestionWrongVo> list,
			Integer type) {
		String domain = this.fileService.getHttpPrefix() + "/"
				+ this.fileService.getSpaceName();
		List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		for (ExamQuestionWrongVo vo : list) {
			map = new HashMap<String, Object>();
			map.put("questionUuid", empty(vo.getQuestionUuid()));
			map.put("difficulty", vo.getDifficulity());
			if (type == 1) {
				map.put("scoringRate", vo.getTeamScoringRate());
			} else {
				map.put("scoringRate", vo.getGradeScoringRate());
			}
			map.put("questionType", empty(vo.getQuestionType()));
			if (vo.getGroupId() != null) {
				map.put("isComplex", true);
			} else {
				map.put("isComplex", false);
			}

			String complexTitle = vo.getComplexTitle();
			complexTitle = MqtPaperUtil.replaceDomain(complexTitle, domain);
			map.put("complexTitle", complexTitle);
			map.put("paperAnswer",
					MqtPaperUtil.parseAnswer(vo.getAnswer(), domain));
			map.put("correctAnswer",
					MqtPaperUtil.StringToArray(vo.getCorrectAnswer(), domain));
			map.put("explanation",
					MqtPaperUtil.replaceDomain(vo.getExplanation(), domain));
			map.put("content",
					MqtPaperUtil.replaceDomain(vo.getContent(), domain));
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

	private void InitExamInterscore(Integer taskId, Integer teamId) {
		TaskInterscoreCondition tiCondition = new TaskInterscoreCondition();
		tiCondition.setTaskId(taskId);
		tiCondition.setTeamId(teamId);
		tiCondition.setIsDeleted(false);
		List<TaskInterscore> eilist = taskInterscoreService
				.findTaskInterscoreByCondition(tiCondition);
		Task task=taskService.findTaskById(taskId);
		if (eilist == null || eilist.size() == 0) {
			UserPaperCondition upc = new UserPaperCondition();
			upc.setOwnerId(taskId);
			upc.setType(2);
			upc.setTeamId(teamId);
			List<UserPaper> ups = this.userPaperService
					.findUserPaperByCondition(upc);
			if (ups != null && ups.size() > 1) {
				Date date = new Date();
				int[] a = randomArray(0, ups.size() - 1, ups.size());
				TaskInterscore[] eslist = new TaskInterscore[ups.size()];
				TaskInterscore es = new TaskInterscore();
				int i = 0;
				for (UserPaper up : ups) {
					es = new TaskInterscore();
					es.setCreateDate(date);
					es.setModifyDate(date);
					es.setIsDeleted(false);
					es.setTaskId(taskId);
					es.setScoredPaperId(up.getId());
					es.setScoredUserId(up.getUserId());
					es.setScoringUserId(ups.get(a[i]).getUserId());
					es.setTeamId(teamId);
					es.setPaperId(task.getPaperId());
					eslist[i] = es;
					i++;
				}
				taskInterscoreService.createBatch(eslist);
			}
		}

	}

	public static int[] randomArray(int min, int max, int n) {
		int len = max - min + 1;

		if (max < min || n > len) {
			return null;
		}

		// 初始化给定范围的待选数组
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// 待选数组0到(len-2)随机一个下标
			index = Math.abs(rd.nextInt() % len);
			while (i == source[index]) {
				index = Math.abs(rd.nextInt() % len);
				if (source[len - 1] == max && source[0] == max) {
					return randomArray(min, max, n);
				}
			}
			len--;
			// 将随机到的数放入结果集
			result[i] = source[index];
			// 将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
			source[index] = source[len];
		}
		return result;
	}

	private String icon(String icon) {
		if (icon == null || "".equals(icon)) {
			return SysContants.APP_DEFAULT_USERICON;
		}
		return icon;
	}

	@Override
	public Object getPaper(String appKey, Integer taskId, Integer userId,
			Integer unitId) throws ParseException {
		if (appKey == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL,
					null);// 参数为空
		}
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Integer paperId=0;
		if(unitId==null){
			Task task=taskService.findTaskById(taskId);
			if(task==null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("taskId不存在,请确认");
				info.setMsg("不存在该taskId");
				info.setParam("taskId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			paperId=task.getPaperId();
		}else{
			List<LpUnitFile> lflist=lpUnitFileService.findLpUnitFileByLpUnitId(unitId);
			if(lflist==null||lflist.size()!=1){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("unit不存在,请确认");
				info.setMsg("不存在该unit");
				info.setParam("unit");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			String uuid=lflist.get(0).getObjectUuid();
			paperId=paPaperService.findPaPaperByUUid(uuid).getId();
		}
		com.alibaba.fastjson.JSONObject json=paPaperHandleService.findPaperJsonByPaperId(paperId, taskId, userId, unitId);
//		com.alibaba.fastjson.JSONObject json=paPaperHandleService.findFullPaperJsonByPaperId(paperId, taskId, userId, unitId);
		return new ResponseVo<Object>("0", json);
	}

	@Override
	public Object correctListByQuestion(String appKey, Integer taskId,
			Integer unitId, Integer teamId, String questionUuid,
			Integer pageSize, Integer pageNumber) {

		try {
			String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			Integer type=4;
			if(unitId==null){
				type=2;
			}
			PaTaskFavoritesCondition paTaskFavoritesCondition=new PaTaskFavoritesCondition();
			paTaskFavoritesCondition.setTaskId(taskId);
			paTaskFavoritesCondition.setType(type);
			if(unitId==null){
				paTaskFavoritesCondition.setUnitId(unitId);
			}
			paTaskFavoritesCondition.setTeamId(teamId);
			List<PaTaskFavorites> pflist=paTaskFavoritesService.findPaTaskFavoritesByCondition(paTaskFavoritesCondition);
			Map<Integer,Integer>pfmap=new HashMap<Integer, Integer>();
			for(PaTaskFavorites pf:pflist){
				pfmap.put(pf.getUserQuestionId(), pf.getIsDeleted()?1:0);
			}
			
			Page page = learningPlanBaseService.getPage(pageSize, pageNumber);
			
			/**获取该任务的单题答卷情况*/
			List<UserPaperCorrectVo> userPaperCorrectVoList = userPaperService.findUserPaperCorrectByTaskIdAndTeamId(taskId, unitId, null, teamId,questionUuid, page);
			if(userPaperCorrectVoList.size()==0) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("该道题暂时无人作答");
				info.setMsg("该道题暂时无人作答");
				info.setParam("questionUuid,taskId,unitId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			List<TeamUser> teamUserList = teamUserService.findTeamUserOfAll(teamId);
			
			/**用户id的数组*/
			Integer[] userIds = new Integer[teamUserList.size()];
			/**添加用户的id*/
			for (int i = 0; i < teamUserList.size(); i++) {
 				userIds[i] = teamUserList.get(i).getUserId();
			}
			int IsSubjective=-1;
			/**获取用户的集合*/
			List<User> userList = userService.findUserByIds(userIds);
			
			/**获取Person的集合(用于获取用户的name) map的id为userId, value为person*/
 			Map<Integer, Person> personMap = learningPlanBaseService.getPersonMapByLpTaskUserList(userList);
 			/**用户头像*/
 			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
 			/**返回值列表*/
 			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(userPaperCorrectVoList.size());
 			
 			/**用户答题情况id数组*/
			String[] userQuestionUuids=new String[userPaperCorrectVoList.size()];
			for (int i = 0; i < userPaperCorrectVoList.size(); i++) {
				userQuestionUuids[i] = userPaperCorrectVoList.get(i).getUserQuestionUuId();
			}
			/**根据答题id数组获取提交的图片答案*/
			List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuids);
			
			Map<String, FileResult> fileResultMap = getFileResultMap(userFileList);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			boolean isFirst = true;
			
			/**遍历用户答题情况，构建返回数据结构*/
 			for (UserPaperCorrectVo userPaperCorrectVo : userPaperCorrectVoList) {
 				Map<String, Object> map = new HashMap<String, Object>();
 				
 				if(isFirst) {
 					/**题目正确的答案*/
 					resultMap.put("correctAnswer", MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbCorrectAnswer(), domain));
 					/**正确答案*/
 					resultMap.put("correctScore", userPaperCorrectVo.getCorrectScore());
 					isFirst=false;
 				}
 				
 				/**图片答案列表数据结构*/
 				List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>(9);
 				
 				Person person = personMap.get(userPaperCorrectVo.getUserId());
 				
 				if(person==null) {
 					map.put("studentName", "匿名");
 				} else {
 					map.put("studentName", person.getName());
 				}
 				
				/**studentId实际我返回了userId*/
 				map.put("studentId", userPaperCorrectVo.getUserId());
 				map.put("questionUuid", userPaperCorrectVo.getQuestionUuid());
				
				/**学生的分数*/
				map.put("score", userPaperCorrectVo.getScore());
				
				/**头像*/
				if(imgMap.get(userPaperCorrectVo.getUserId())!=null) {
					map.put("studentIcon", imgMap.get(userPaperCorrectVo.getUserId()));
				} else {
					/**如果不存在则使用默认头像*/
					map.put("studentIcon", SysContants.APP_DEFAULT_USERICON);
				}
				
				map.put("answer", MqtPaperUtil.parseAnswer(userPaperCorrectVo.getDbAnswer(), domain));
				
				if(userPaperCorrectVo.getQuestionProperty().intValue()==2){
					IsSubjective=1;
				}else{
					IsSubjective=0;
				}
				if(userFileList!=null) {
					for (UserFile userFile : userFileList) {
						/**如果该文件属于当前用户*/
						if(userFile.getUserId()-userPaperCorrectVo.getUserId()==0) {
							Map<String, Object> fileMap = getFileMap(fileResultMap, userFile);
							/**设置源文件和批注后的文件返回信息,包括md5, httpUrl*/
							fileList.add(fileMap);
						}
					}
				}
				
				map.put("files", fileList);
				if(pfmap.containsKey(userPaperCorrectVo.getUserQuestionId())&&pfmap.get(userPaperCorrectVo.getUserQuestionId())==0){
					map.put("isFavorites", 1);
				}else{
					map.put("isFavorites", 0);
				}
				resultList.add(map);
			}
 			resultMap.put("IsSubjective", IsSubjective);
 			resultMap.put("answers", resultList);
 			return new ResponseVo<Object>("0",resultMap);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("获取单题评卷列表异常");
            info.setMsg("未知错误");
            return new ResponseError("000001", info);
		}
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

	@Override
	public Object taskInterscoreFinish(String appKey, Integer taskId, Integer userId, String type, String data) {

		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/** 答题记录或者答卷记录是否被更改，如果被更改，刚需要更新统计data_change */
			boolean dataChange = false;

			JSONObject info = JSONObject.fromObject(data);
			/** 批注后的文件列表 */
			JSONArray files = info.getJSONArray("files");
			/** 批改的题目 */
			JSONArray questions = info.getJSONArray("questions");

			Map<String, Double> questionScoreMap = new HashMap<String, Double>(questions.size());
			for (int i = 0; i < questions.size(); i++) {
				JSONObject question = questions.getJSONObject(i);
				if (!question.isNullObject()) {
					String questionUuid = question.getString("questionUuid");
					Double score = question.getDouble("score");
					if (questionUuid != null && !"".equals(questionUuid)) {
						questionScoreMap.put(questionUuid, score);
					}
				}
			}
			
			Map<String, Object> result = new HashMap<String, Object>();
			/** 用户答卷情况 */
			UserPaper userPaper = userPaperService.findUserPaperByCondition(taskId, null, userId);
			if(userPaper==null) {
				log.info("用户答卷情况为空.....");
				return new ResponseVo<Object>("0", result);//找不到数据
			}
			
			Integer teamId=userPaper.getTeamId();
			if("student".equals(type)) {
				TaskInterscore examInterscore = taskInterscoreService.findByScoredPaperId(userPaper.getId());
				if(examInterscore==null) {
					log.info("互评情况为空.....");
					return new ResponseVo<Object>("0", result);//找不到数据
				}
				
				/** 试卷发布的班级情况 */
				TaskTeam taskTeam = taskTeamService.findTaskTeamByTaskIdAndTeamId(taskId, examInterscore.getTeamId());
				if(taskTeam==null && "student".equals(type)) {
					log.info("互评情况为空.....");
					return new ResponseVo<Object>("0", result);//找不到数据
				}
				Date currentDate = new Date(System.currentTimeMillis());
				if(!taskTeam.getIsInterscoring()) {
					ResponseInfo responseInfo = new ResponseInfo();
					responseInfo.setDetail("互评未打开，不允许互评");
					responseInfo.setMsg("互评未打开，不允许互评");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
				if(currentDate.before(taskTeam.getInterscoreStartTime())) {
					ResponseInfo responseInfo = new ResponseInfo();
					responseInfo.setDetail("互评还未开始");
					responseInfo.setMsg("互评还未开始");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
				if(currentDate.after(taskTeam.getInterscoreFinishTime())) {
					ResponseInfo responseInfo = new ResponseInfo();
					responseInfo.setDetail("互评已经结束");
					responseInfo.setMsg("互评已经结束");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
					if(examInterscore!=null) {
						examInterscore.setScoringTime(new Date());
						taskInterscoreService.modify(examInterscore);
					}
			}
			
			/** 此次批卷学生获得的分数 */
			double totalScore = 0.0;

			String[] questionUUIDs = new String[questions.size()];
			for (int i = 0; i < questions.size(); i++) {
				questionUUIDs[i] = questions.getJSONObject(i).getString("questionUuid");
			}

			List<PaQuestionVo> paQuestionVos = this.paQuestionService.findPaperQuestionByUUIDs(questionUUIDs);

			Map<String, UserQuestion> userQuestionsMap = this.userQuestionService.findMapByOwnerIdAndUserId(taskId, userId, null);

			/** 更新批卷后的得分记录 */
			for (int i = 0; i < paQuestionVos.size(); i++) {
				PaQuestionVo question = paQuestionVos.get(i);
				double score = questionScoreMap.get(question.getUuid());
				UserQuestion userQuestion = userQuestionsMap.get(question.getUuid());

				if (userQuestion != null) {
					totalScore = totalScore + score - userQuestion.getScore();
					userQuestion.setScore(score);
					/** 获得该道题的总分才算正确 */
					if (question.getScore() - score == 0) {
						if(!userQuestion.getIsCorrect()){
							question.setRightAnswerCount(question.getRightAnswerCount()+1);
							userQuestion.setIsCorrect(true);
						}
					} else {
						if(userQuestion.getIsCorrect()){
						if(question.getRightAnswerCount()>0) {
							question.setRightAnswerCount(question.getRightAnswerCount()-1);
						}
						userQuestion.setIsCorrect(false);
					}
				}   
					if(question.getAnswerCount()!=0){
						
						question.setDifficulity(question.getRightAnswerCount() /(1.0f*question.getAnswerCount()));
					}
					/** 更新dataChange标志 */
					if (!dataChange) {
						dataChange = true;
					}
				}

			}
			
			/**做一次批量更新userQuestion*/
			Collection<UserQuestion> userQuestionCollection = userQuestionsMap.values();
			List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>(userQuestionCollection.size());
			Iterator<UserQuestion> iterator = userQuestionCollection.iterator();
			while (iterator.hasNext()) {
				userQuestionList.add(iterator.next());
			}

			this.userQuestionService.batchUpdateScoreAndIsCorrect(userQuestionList);
			paQuestionService.modifyRightAnswerCountBatch(paQuestionVos);

			/** 把批卷后的分数加上 */
			userPaper.setScore(userPaper.getScore() + totalScore);
			userPaper.setScoreTime(new Date());
			/** 更新记录 */
			userPaperService.modify(userPaper);
			/** 更新dataChange标志 */
			if (!dataChange) {
				dataChange = true;
			}

			List<UserFile> userFiles = new ArrayList<UserFile>();

			/** 更新批注后的图片 */
			for (int i = 0; i < files.size(); i++) {
				JSONObject file = files.getJSONObject(i);
				/** 用户提交图片的记录的id */
				Integer fileId = file.getInt("fileId");
				UserFile userFile = userFileService.findUserFileById(fileId);
				if (userFile != null) {
					String markedFileUuid = file.getString("markedFileUuid");
					/** 批注后的图片 */
					userFile.setMarkedFileUuid(markedFileUuid);
					userFile.setModifyDate(new Date());
					/** 更新记录 */
					userFiles.add(userFile);
				}
				/** 更新dataChange标志 */
				if (!dataChange) {
					dataChange = true;
				}
			}
			/** 一次更新记录 */
			userFileService.batchUpdateMarkedFile(userFiles);

			if (dataChange) {
				/** 获取统计情况 */
				TaskTeam taskTeam=taskTeamService.findTaskTeamUnique(taskId, teamId);
				ExamStat examStat = examStatService.findExamStatByExamId(taskTeam.getPjExamId());
				if (examStat != null) {
					/** 更新统计data_chage */
					examStat.setDataChanged(true);
					examStatService.modify(examStat);
				}
			}
			
			

			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("提交评卷接口异常");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}



	@Override
	public Object responseCheck(String appKey, Integer taskId, Integer teamId, Integer type) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			List<UserPaper> userPaperList = userPaperService.findPaperResponseCheck(taskId, teamId);
			
			Map<String, Object> result = new HashMap<String, Object>();
			Integer hasResponse = 0;
			Map<String, Object> paper = new HashMap<String, Object>();
			paper.put("hasResponse", hasResponse);
			result.put("paper", paper);
			
			if(type-2==0) {
				if(userPaperList.size()>0) {
					hasResponse = 1;
				}
				paper.put("hasResponse", hasResponse);
				result.put("paper", paper);
				result.put("unitId", new ArrayList<Integer>());
			} else if(type-4==0) {
				Integer[] unitIds = new Integer[userPaperList.size()];
				Integer index = 0;
				for (UserPaper userPaper : userPaperList) {
					if(userPaper.getObjectId()!=null) {
						unitIds[index] = userPaper.getObjectId();
						index++;
					}
				}
				if(index==0) {
					result.put("unitId", new ArrayList<Integer>());
				} else {
					result.put("unitId", unitIds);
				}
				
			} else {
				ResponseInfo responseInfo = new ResponseInfo();
				responseInfo.setDetail("参数type不在指定范围之内");
				responseInfo.setMsg("参数type不在指定范围之内");
				responseInfo.setParam("type");
				return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
			}
			
			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("提交评卷接口异常");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}



	@Override
	public Object correctListByQuestion(String appKey, Integer taskId,
			Integer unitId, Integer teamId, Integer userId,
			String questionUuid, Integer pageSize, Integer pageNumber) {
	try {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Integer type=4;
		if(unitId==null){
			type=2;
		}
		PaTaskFavoritesCondition paTaskFavoritesCondition=new PaTaskFavoritesCondition();
		paTaskFavoritesCondition.setTaskId(taskId);
		paTaskFavoritesCondition.setType(type);
		if(unitId==null){
			paTaskFavoritesCondition.setUnitId(unitId);
		}
		paTaskFavoritesCondition.setUserId(userId);
		paTaskFavoritesCondition.setTeamId(teamId);
		List<PaTaskFavorites> pflist=paTaskFavoritesService.findPaTaskFavoritesByCondition(paTaskFavoritesCondition);
		Map<Integer,Integer>pfmap=new HashMap<Integer, Integer>();
		for(PaTaskFavorites pf:pflist){
			pfmap.put(pf.getUserQuestionId(), pf.getIsDeleted()?1:0);
		}
		
		Page page = learningPlanBaseService.getPage(pageSize, pageNumber);
		
		/**获取该任务的单题答卷情况*/
		List<UserPaperCorrectVo> userPaperCorrectVoList = userPaperService.findUserPaperCorrectByTaskIdAndTeamId(taskId, unitId, null, teamId,questionUuid, page);
		if(userPaperCorrectVoList.size()==0) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("该道题暂时无人作答");
			info.setMsg("该道题暂时无人作答");
			info.setParam("questionUuid,taskId,unitId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
		List<TeamUser> teamUserList = teamUserService.findTeamUserOfAll(teamId);
		List<TeamStudent> tsList=new ArrayList<TeamStudent>();
		tsList=teamStudentService.findByTeamId(teamId);
		Map<Integer,String> str=new HashMap<Integer, String>();
		for(TeamStudent ts:tsList){
			str.put(ts.getUserId(), ts.getName());
		}
		
		/**用户id的数组*/
		Integer[] userIds = new Integer[teamUserList.size()];
		/**添加用户的id*/
		for (int i = 0; i < teamUserList.size(); i++) {
				userIds[i] = teamUserList.get(i).getUserId();
		}
		int IsSubjective=-1;
		/**获取用户的集合*/
		List<User> userList = userService.findUserByIds(userIds);
		
		/**获取Person的集合(用于获取用户的name) map的id为userId, value为person*/
			Map<Integer, Person> personMap = learningPlanBaseService.getPersonMapByLpTaskUserList(userList);
			/**用户头像*/
			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
			/**返回值列表*/
			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(userPaperCorrectVoList.size());
			
			/**用户答题情况id数组*/
		String[] userQuestionUuids=new String[userPaperCorrectVoList.size()];
		for (int i = 0; i < userPaperCorrectVoList.size(); i++) {
			userQuestionUuids[i] = userPaperCorrectVoList.get(i).getUserQuestionUuId();
		}
		/**根据答题id数组获取提交的图片答案*/
		List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuids);
		
		Map<String, FileResult> fileResultMap = getFileResultMap(userFileList);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		boolean isFirst = true;
		
		/**遍历用户答题情况，构建返回数据结构*/
			for (UserPaperCorrectVo userPaperCorrectVo : userPaperCorrectVoList) {
				Map<String, Object> map = new HashMap<String, Object>();
				
				if(isFirst) {
					/**题目正确的答案*/
					resultMap.put("correctAnswer", MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbCorrectAnswer(), domain));
					/**正确答案*/
					resultMap.put("correctScore", userPaperCorrectVo.getCorrectScore());
					isFirst=false;
				}
				
				/**图片答案列表数据结构*/
				List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>(9);
				
//				Person person = personMap.get(userPaperCorrectVo.getUserId());
				
				if(str.get(userPaperCorrectVo.getUserId())==null) {
					map.put("studentName", "匿名");
				} else {
					map.put("studentName", str.get(userPaperCorrectVo.getUserId()));
				}
				
			/**studentId实际我返回了userId*/
				map.put("studentId", userPaperCorrectVo.getUserId());
				map.put("questionUuid", userPaperCorrectVo.getQuestionUuid());
				map.put("userQuestionId", userPaperCorrectVo.getUserQuestionId());
				SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
				int isScored=0;
				if(!sf.format(userPaperCorrectVo.getCreateTime()).equals(sf.format(userPaperCorrectVo.getModifyTime()))){
					isScored=1;
				}
				map.put("isScored", isScored);
			/**学生的分数*/
			map.put("score", userPaperCorrectVo.getScore());
			
			/**头像*/
			if(imgMap.get(userPaperCorrectVo.getUserId())!=null) {
				map.put("studentIcon", imgMap.get(userPaperCorrectVo.getUserId()));
			} else {
				/**如果不存在则使用默认头像*/
				map.put("studentIcon", SysContants.APP_DEFAULT_USERICON);
			}
			
			map.put("answer", MqtPaperUtil.parseAnswer(userPaperCorrectVo.getDbAnswer(), domain));
			
			if(userPaperCorrectVo.getQuestionProperty().intValue()==2){
				IsSubjective=1;
			}else{
				IsSubjective=0;
			}
			if(userFileList!=null) {
				for (UserFile userFile : userFileList) {
					/**如果该文件属于当前用户*/
					if(userFile.getUserId()-userPaperCorrectVo.getUserId()==0) {
						Map<String, Object> fileMap = getFileMap(fileResultMap, userFile);
						/**设置源文件和批注后的文件返回信息,包括md5, httpUrl*/
						fileList.add(fileMap);
					}
				}
			}
			
			map.put("files", fileList);
			if(pfmap.containsKey(userPaperCorrectVo.getUserQuestionId())&&pfmap.get(userPaperCorrectVo.getUserQuestionId())==0){
				map.put("isFavorites", 1);
			}else{
				map.put("isFavorites", 0);
			}
			resultList.add(map);
		}
			resultMap.put("IsSubjective", IsSubjective);
			resultMap.put("answers", resultList);
			return new ResponseVo<Object>("0",resultMap);
	} catch(Exception e) {
		e.printStackTrace();
        ResponseInfo info = new ResponseInfo();
        info.setDetail("获取单题评卷列表异常");
        info.setMsg("未知错误");
        return new ResponseError("000001", info);
	}
	}
}
