package platform.education.rest.learningPlan.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import platform.education.exam.service.ExamPublishService;
import platform.education.exam.service.ExamRelateService;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.PjGroup;
import platform.education.generalTeachingAffair.model.PjGroupStudent;
import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.PjGroupService;
import platform.education.generalTeachingAffair.service.PjGroupStudentService;
import platform.education.generalTeachingAffair.service.SchoolUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.PjGroupCondition;
import platform.education.generalTeachingAffair.vo.PjGroupStudentCondition;
import platform.education.generalcode.model.ResTextbook;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.generalcode.service.ResTextbookService;
import platform.education.generalcode.service.StageService;
import platform.education.generalcode.vo.ResTextbookCatalogCondition;
import platform.education.generalcode.vo.ResTextbookCatalogVo;
import platform.education.learningDesign.model.LearningPlan;
import platform.education.learningDesign.model.LearningPlanInterscore;
import platform.education.learningDesign.model.LpCatelog;
import platform.education.learningDesign.model.LpTask;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.model.LpTaskLock;
import platform.education.learningDesign.model.LpTaskUnitUser;
import platform.education.learningDesign.model.LpTaskUser;
import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.learningDesign.model.LpUnit;
import platform.education.learningDesign.model.LpUnitFile;
import platform.education.learningDesign.model.TaskUserActivityFiles;
import platform.education.learningDesign.service.LearningPlanHandlerService;
import platform.education.learningDesign.service.LearningPlanInterscoreService;
import platform.education.learningDesign.service.LearningPlanService;
import platform.education.learningDesign.service.LpCatelogService;
import platform.education.learningDesign.service.LpTaskActivityStateService;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.service.LpTaskLockService;
import platform.education.learningDesign.service.LpTaskService;
import platform.education.learningDesign.service.LpTaskUnitUserService;
import platform.education.learningDesign.service.LpTaskUserActivityService;
import platform.education.learningDesign.service.LpTaskUserService;
import platform.education.learningDesign.service.LpUnitFileService;
import platform.education.learningDesign.service.LpUnitService;
import platform.education.learningDesign.service.TaskUserActivityFilesService;
import platform.education.learningDesign.vo.*;
import platform.education.rest.jw.service.ConversationService;
import platform.education.rest.jw.service.GroupSummarizeService;
import platform.education.rest.jw.service.vo.LpTaskUserActivityVo;
import platform.education.micro.service.MicroLessonService;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.Task;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaperService;
import platform.education.paper.service.QuestionService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.resource.model.Resource;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.ResourceHandlerService;
import platform.education.resource.service.ResourceService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.jw.service.vo.LpTaskUserActivityInfo;
import platform.education.rest.jw.service.vo.LpTaskUserActivityJson;
import platform.education.rest.learningPlan.service.LearningPlanBaseService;
import platform.education.rest.learningPlan.service.LearningPlanRestService;
import platform.education.rest.paper.service.PaperTaskStudentRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserRoleService;
import platform.education.user.service.UserService;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

public class LearningPlanRestServiceImpl implements LearningPlanRestService{
	private static final Logger log = LoggerFactory.getLogger(LearningPlanBaseServiceImpl.class);
	@javax.annotation.Resource
	private TeacherService teacherService;
	@Autowired
	@Qualifier("learningPlanHandlerService")
	private LearningPlanHandlerService learningPlanHandlerService;
	
	@Autowired
	@Qualifier("learningPlanService")
	private LearningPlanService learningPlanService;
	
	@Autowired
	@Qualifier("lpTaskUnitUserService")
	private LpTaskUnitUserService lpTaskUnitUserService;
	
	@Autowired
	@Qualifier("lpUnitService")
	private LpUnitService lpUnitService;
	
	@Autowired
	@Qualifier("lpTaskUserService")
	private LpTaskUserService lpTaskUserService;
	
	@Autowired
	@Qualifier("lpTaskService")
	private LpTaskService lpTaskService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
	
	@Autowired
    @Qualifier("lpCatelogService")
	private LpCatelogService lpCatelogService;
	
	@Autowired
    @Qualifier("resourceHandlerService")
	private ResourceHandlerService resourceHandlerService;
	
	@Autowired
    @Qualifier("resTextbookCatalogService")
	private ResTextbookCatalogService resTextbookCatalogService;
	
	@Autowired
    @Qualifier("catalogResourceService")
	private CatalogResourceService catalogResourceService;
	
	@Autowired
    @Qualifier("resourceService")
	private ResourceService resourceService;
	
	@Autowired
    @Qualifier("lpUnitFileService")
	private LpUnitFileService lpUnitFileService;
	
	@Autowired
    @Qualifier("entityFileService")
	private EntityFileService entityFileService;
	
	@Autowired
    @Qualifier("microLessonService")
	private MicroLessonService microLessonService;
	
	@Autowired
    @Qualifier("userService")
	private UserService userService;
	
	@Autowired
    @Qualifier("personService")
	private PersonService personService;
	
	@Autowired
    @Qualifier("teamService")
	private TeamService teamService;

	@Autowired
    @Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
    @Qualifier("examService")
	private ExamService examService;
	
	@Autowired
    @Qualifier("papaperService")
	private PaperService paperService;
	
	@Autowired
    @Qualifier("lpTaskUserActivityService")
	private LpTaskUserActivityService lpTaskUserActivityService;
	
	@Autowired
    @Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
    @Qualifier("lpTaskActivityStateService")
	private LpTaskActivityStateService lpTaskActivityStateService;
	
	@Autowired
    @Qualifier("userPaperService")
	private UserPaperService userPaperService;
	
	@Autowired
    @Qualifier("userFileService")
	private UserFileService userFileService;
	
	@Autowired
    @Qualifier("questionService")
	private QuestionService questionService;
	
	@Autowired
    @Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;
	
	@Autowired
    @Qualifier("userQuestionService")
	private UserQuestionService userQuestionService;
	
	@Autowired
    @Qualifier("examPublishService")
	private ExamPublishService examPublishService;
	
	@Autowired
    @Qualifier("examRelateService")
	private ExamRelateService examRelateService;
	
	@Autowired
    @Qualifier("examStatService")
	private ExamStatService examStatService;
	
	@Autowired
    @Qualifier("lpTaskExamUnitService")
	private LpTaskExamUnitService lpTaskExamUnitService;
	
	@Autowired
    @Qualifier("learningPlanInterscoreService")
	private LearningPlanInterscoreService learningPlanInterscoreService;
	
	@Autowired
    @Qualifier("transactionManager")
	private DataSourceTransactionManager transactionManager;
	
	@Autowired
    @Qualifier("learningPlanBaseService")
	private LearningPlanBaseService learningPlanBaseService;
	
	@Autowired
    @Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	@Autowired
    @Qualifier("resTextbookService")
	private ResTextbookService resTextbookService;
	
	@Autowired
    @Qualifier("jcStageService")
	private StageService stageService;
	
	@Autowired
    @Qualifier("paperTaskStudentRestService")
	private PaperTaskStudentRestService paperTaskStudentRestService;
	@Autowired
    @Qualifier("taskService")
	private TaskService taskService;
	
	@Autowired
	@Qualifier("lpTaskLockService")
	private LpTaskLockService lpTaskLockService;
	
	
	@Autowired
	@Qualifier("taskUserActivityFilesService")
	private TaskUserActivityFilesService taskUserActivityFilesService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	@Autowired
	@Qualifier("pjGroupService")
	private PjGroupService pjGroupService;
	
	@Autowired
	@Qualifier("pjGroupStudentService")
	private PjGroupStudentService pjGroupStudentService;
	
	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;

	@Autowired
	@Qualifier("groupSummarizeService")
	private GroupSummarizeService groupSummarizeService;
	
	@Autowired
	@Qualifier("conversationService")
	private ConversationService conversationService;
	
	@Override
	public Object listByLearner(Integer userId, Integer schoolId, String appKey, String subjectCode, Integer pageSize, Integer pageNumber) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/**?????????????????????*/
			Page page = learningPlanBaseService.getPage(pageSize, pageNumber);
			/**??????????????????*/
			//Map<String, String> subjectMap = learningPlanBaseService.getSubjectMap(schoolId);
			if(subjectCode!=null&&subjectCode.equals("-1")){
				subjectCode=null;
			}
			Map<String, String> subjectMap =subjectService.getSubjectMap(schoolId);
			/**??????????????????????????????*/
			List<Map<String, Object>> taskList =  learningPlanHandlerService.findLpTaskByUserId(userId, subjectCode, subjectMap, page);
			
			if(taskList==null || taskList.size()==0) {
				return new ResponseVo<Object>("0",new ArrayList<Object>());//???????????????
			}
			
			for (Map<String, Object> map : taskList) {
				/**????????????code*/
				String catalogCode = (String) map.get("catalogCode");
				if(catalogCode==null) {
					map.remove("catalogCode");
					map.put("catalogName", "");
					log.info("???????????????????????????");
					continue;
				}
				/**?????????????????????*/
				String catalogName = resTextbookCatalogService.getFullNameByCode(catalogCode," ");
				map.put("catalogName", catalogName);
				map.remove("catalogCode");
			}
			
			return new ResponseVo<Object>("0",taskList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
		
	}
	
	@Override
	public Object listByCatalog(String appKey, Integer catalogId, Integer userId, Integer schoolId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/**??????????????????*/
			ResTextbookCatalog resTextbookCatalog = resTextbookCatalogService.findResTextbookCatalogById(catalogId);
			if(resTextbookCatalog==null) {
				return new ResponseVo<Object>("0",new HashMap<String, Object>());//???????????????
			}
			Integer textId=resTextbookCatalog.getTestBookId();
			ResTextbook rt=resTextbookService.findResTextbookById(textId);
			String subjectCode=rt.getSubjectCode();
			Map<String,String> smap=subjectService.findAllSubjectNameMap();
			String subjectName="?????????";
			if(smap.get(subjectCode)!=null){
				subjectName=smap.get(subjectCode);
			}
			
			/**?????????????????????*/
			List<Map<String, Object>> lpList = learningPlanBaseService.getLearningPlan(catalogId, userId, schoolId);
			
			if(lpList==null) {
				return new ResponseVo<Object>("0", lpList);//???????????????
			}
			for(Map<String,Object> map:lpList){
				map.put("subjectName", subjectName);
			}
			return new ResponseVo<Object>("0",lpList);
		} catch(Exception e) {
			e.printStackTrace();
	        ResponseInfo info = new ResponseInfo();
	        info.setDetail("???????????????????????????????????????");
	        info.setMsg("????????????");
	        return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object catalogList(String appKey, Integer textbookId, Integer userId, Integer schoolId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			ResTextbookCatalogVo resTextbookCatalogVo = resTextbookCatalogService.findResTextbookCatalogList(textbookId);
			
			if(resTextbookCatalogVo.getResTextbookCatalogVoList().size()==0) {
				return new ResponseVo<Object>("0",new HashMap<String, Object>());//???????????????
			}
			
			List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>();
			
			for (ResTextbookCatalogVo resTextbookCatalog : resTextbookCatalogVo.getResTextbookCatalogVoList()) {
				infoList.add(learningPlanBaseService.getCatalogMap(resTextbookCatalog));
				if(resTextbookCatalog.getResTextbookCatalogVoList()!=null && resTextbookCatalog.getResTextbookCatalogVoList().size()>0) {
					for (ResTextbookCatalogVo temp : resTextbookCatalog.getResTextbookCatalogVoList()) {
						List<Map<String, Object>> list = learningPlanBaseService.getLearningPlan(temp.getId(), userId, schoolId);
						if(list.size()>0) {
							infoList.add(learningPlanBaseService.getCatalogMap(temp));
						}
					}
				}
			}
			
			return new ResponseVo<Object>("0",infoList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object lpCatalogAndUnitList(String appKey, Integer learningPlanId, Integer userId, Integer taskId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/**???????????????????????????*/
			List<LpCatelog> list = lpCatelogService.findLpCatelogListByLpId(learningPlanId);
			
			if(list.size()==0) {
				return new ResponseVo<Object>("0", list);//???????????????
			}
			
			List<Map<String, Object>> catalogList = new ArrayList<Map<String, Object>>();

			/**?????????*/
			for (LpCatelog lpCatelog : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", lpCatelog.getId());
				map.put("title", lpCatelog.getTitle());
				map.put("listOrder", lpCatelog.getListOrder());
				/**???????????????????????????????????????*/
				List<Map<String, Object>> unitList = learningPlanBaseService.getUnitList(learningPlanId, lpCatelog.getId(),userId, taskId);
				if(unitList==null) {
					unitList = new ArrayList<Map<String, Object>>();
				}
				map.put("units", unitList);
				catalogList.add(map);
			}
			
			return new ResponseVo<Object>("0",catalogList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}

	@Override
	public Object lpCatalogAndUnitListWithConversation(String appKey, Integer learningPlanId, Integer userId, Integer taskId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}

			/**???????????????????????????*/
			List<LpCatelog> list = lpCatelogService.findLpCatelogListByLpId(learningPlanId);

			if(list.size()==0) {
				return new ResponseVo<Object>("0", list);//???????????????
			}

			List<Map<String, Object>> catalogList = new ArrayList<Map<String, Object>>();

			/**?????????*/
			for (LpCatelog lpCatelog : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", lpCatelog.getId());
				map.put("title", lpCatelog.getTitle());
				map.put("listOrder", lpCatelog.getListOrder());
				/**???????????????????????????????????????*/
				List<Map<String, Object>> unitList = learningPlanBaseService.getUnitList(learningPlanId, lpCatelog.getId(),userId, taskId);
				if(unitList==null) {
					unitList = new ArrayList<Map<String, Object>>();
				}
				map.put("units", unitList);
				catalogList.add(map);
			}

			List<Map<String, Object>> unitList = learningPlanBaseService.getUnitList(0, 0, userId, taskId);
			if(unitList != null && unitList.size() > 0){
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", 0);
				map.put("title", "?????????");
				map.put("listOrder", 0);
				map.put("units", unitList);
				catalogList.add(map);
			}

			return new ResponseVo<Object>("0",catalogList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}

	@Override
	public Object unlockLpUnit(String appKey, Integer unitId, Integer taskId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			LpTaskLockCondition lpTaskLockCondition = new LpTaskLockCondition();
			lpTaskLockCondition.setUnitId(unitId);
			lpTaskLockCondition.setTaskId(taskId);
			lpTaskLockCondition.setIsLocked(true);
			lpTaskLockCondition.setIsDeleted(false);
			List<LpTaskLock> lpTaskLocks = lpTaskLockService.findLpTaskLockByCondition(lpTaskLockCondition);
			LpTaskLock lpTaskLockUpdated=null;
			if (lpTaskLocks != null && !lpTaskLocks.isEmpty()) {
				LpTaskLock lpTaskLock = lpTaskLocks.get(0);
				lpTaskLock.setIsLocked(false);
				lpTaskLockUpdated=lpTaskLockService.modify(lpTaskLock);
			}
			
			if (lpTaskLockUpdated!=null&&!lpTaskLockUpdated.getIsLocked()) {
				map.put("success", true);
			} else {
				map.put("success", false);
			}
			
			return new ResponseVo<Object>("0", map);

		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("???????????????????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object getTaskUnitGet(String appKey, Integer unitId, Integer userId,Integer taskId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			// ?????? ??????????????????
			// ????????????userID???taskID
			if (userId != null && taskId != null) {
				LpTaskLockCondition lpTaskLockCondition = new LpTaskLockCondition();
				lpTaskLockCondition.setUnitId(unitId);
				lpTaskLockCondition.setTaskId(taskId);
				lpTaskLockCondition.setIsLocked(true);
				lpTaskLockCondition.setIsDeleted(false);
				if (lpTaskLockService.count(lpTaskLockCondition) > 0) {
					Map<String, Object> map=new HashMap<String, Object>();
					map.put("isLock", true);
					return new ResponseVo<Object>("0", map);
				}
			}

			Map<String, Object> map = new HashMap<String, Object>();
			
			LpUnit lpUnit = lpUnitService.findLpUnitById(unitId);
			
			if(lpUnit==null) {
				log.info("???????????????????????????");
				return new ResponseVo<Object>("0",map);
			}
			
			map.put("content", lpUnit.getContent());
			
			/**?????????????????????*/
			List<LpUnitFile> unitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(unitId);
			map.put("size", unitFileList.size());
			
			List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>(unitFileList.size());
			
			for (LpUnitFile lpUnitFile : unitFileList) {
				/**????????????*/
				Resource resource = resourceService.findResourceById(lpUnitFile.getResourceId());
				if(resource==null) {
					if(!LpUnitType.EXAM.equals(lpUnit.getUnitType())) {
						log.error("??????????????????resource????????????");
						continue;
					}
				}
				
				Map<String, Object> file = new HashMap<String, Object>();
				/**???????????????????????????*/
				file.put("url", fileService.relativePath2HttpUrlByUUID(lpUnitFile.getFileId()));
				file.put("iconUrl", "");
				file.put("totalScore", 0);
				file.put("userFileList", new ArrayList<Object>());
				
				/**????????????*/
				if(LpUnitType.MICRO.equals(lpUnit.getUnitType())) {
					file.put("title", resource.getTitle());
					String iconUrl = learningPlanBaseService.getMicroIconUrl(resource.getObjectId());
					file.put("iconUrl", iconUrl);
				}else if(LpUnitType.EXAM.equals(lpUnit.getUnitType())) {
					map.put("size", 1);
					PaPaper paPaper = paPaperService.findPaPaperByUUid(lpUnitFile.getObjectUuid());
					if(paPaper==null) {
						log.error("?????????????????????????????????paper??????");
						continue;
					}
					file.put("url", "");
					file.put("title", paPaper.getTitle());
					file.put("totalScore", paPaper.getScore());
					
					/**?????????url??????*/
					if(userId!=null && taskId!=null) {
						List<String> userFileList = learningPlanBaseService.findUserQuestionFileList(taskId, unitId, userId);
						file.put("userFileList", userFileList);
					}
				} else {
					file.put("title", resource.getTitle());
				}
				fileList.add(file);
			}


			/**??????????????????*/
		if(LpUnitType.GROUP_ACTIVITY.equals(lpUnit.getUnitType())) {
			map.put("summarize", ((ResponseVo)groupSummarizeService.findSummarize(appKey,taskId, unitId)).getData());
			/**??????????????????*/
		} else if(LpUnitType.CONVERSATION.equals(lpUnit.getUnitType())) {
			/**
			 * ????????????
			 * zhenxinghui
			 * 2018-11-5
			 */
			Integer lpId = lpUnit.getLpId();
//			LearningPlan lp = learningPlanService.findLearningPlanById(lpId);
//			Integer schoolId = lp.getOwnerId();//????????????ownerId???schoolId
			List<String> types = userRoleService.findRoleTypesByUserId(userId, 1);
			for(String str : types) {
				if(str.equals("1")) {//??????
					/**
					 * ???????????????????????????pj_group??????teacherId????????????
					 */
					List<Integer> groupIdList = new ArrayList<>();
					List<Teacher> teacherList = teacherService.findTeacherByUserId(userId);
					for(Teacher t : teacherList) {
						t.getId();
						PjGroupCondition pjGroupCondition = new PjGroupCondition();
						pjGroupCondition.setSchoolId(t.getSchoolId());
						pjGroupCondition.setTeacherId(t.getId());
						pjGroupCondition.setIsDeleted(false);
						List<PjGroup> pjGroupList = pjGroupService.findPjGroupByCondition(pjGroupCondition);
						for(PjGroup p : pjGroupList) {
							groupIdList.add(p.getId());
						}
					}
					map.put("groupId", groupIdList);
				}else if(str.equals("4")) {//??????
					/**
					 * ????????????pj_group_student??????studentId????????????
					 */
					Student student = studentService.findStudentByUserId(userId);
					Integer studentId = student.getId();
					PjGroupStudentCondition pjGroupStudentCondition = new PjGroupStudentCondition();
					pjGroupStudentCondition.setStudentId(studentId);
					pjGroupStudentCondition.setIsDeleted(false);
					List<PjGroupStudent> pjGroupStudentList = pjGroupStudentService.findPjGroupStudentByCondition(pjGroupStudentCondition);
					List<Integer> groupIdList = new ArrayList<>();
					for(PjGroupStudent ps : pjGroupStudentList) {
						groupIdList.add(ps.getId());
					}
					map.put("groupId", groupIdList);
				}
			}

			/**
			 * ????????????????????????
			 * zhenxinghui
			 * 2018-11-5
			 */
			LpTaskUserActivityCondition lpTaskUserActivityCondition = new LpTaskUserActivityCondition();
//			lpTaskUserActivityCondition.setLpId(lpId);
			lpTaskUserActivityCondition.setTaskId(taskId);
			lpTaskUserActivityCondition.setUnitId(unitId);
			lpTaskUserActivityCondition.setIsDeleted(false);
			List<LpTaskUserActivity> l = lpTaskUserActivityService.findLpTaskUserActivityByCondition(lpTaskUserActivityCondition);

			//???????????????????????????json
			List<LpTaskUserActivityJson> data = new ArrayList<LpTaskUserActivityJson>();

			for(LpTaskUserActivity lpTaskUserActivity : l) {
				if(lpTaskUserActivity.getFiles()==null) {
					lpTaskUserActivity.setFiles("");
				}
				//??????????????????????????????????????????list???
				LpTaskUserActivityInfo lpTaskUserActivityInfo = new LpTaskUserActivityInfo();
				String quotes = lpTaskUserActivity.getQuote();
				if(quotes != null) {
					String quote[] = quotes.split(",");
//					List<LpTaskUserActivityVo> quoteList = new ArrayList<LpTaskUserActivityVo>();
//					for(String q : quote) {
//						LpTaskUserActivityVo vo = new LpTaskUserActivityVo();
//						LpTaskUserActivity lt = lpTaskUserActivityService.findLpTaskUserActivityById(Integer.parseInt(q));
//						String name = "";
//						Student st = studentService.findOfUser(schoolId, lt.getUserId());
//						if(st!=null) {
//							name = st.getName();
//						}else {
//							name = teacherService.findOfUser(schoolId, lt.getUserId()).getName();
//						}
//						vo.setName(name);
//						
//						String files = lpTaskUserActivity.getFiles();
//						List<String> filePath = new ArrayList<String>();
//						if(files != null && !files.equals("")) {
//							String filesStr = files.substring(1, files.length()-1);
//							String filesSet[] = filesStr.split(",");
//							for(int j=0;j<filesSet.length;j++) {
//								EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//								if(ef != null) {
//									filePath.add(ef.getRelativePath());
//								}
//							}
//						}
//						vo.setFiles(filePath);
//						
//						BeanUtils.copyProperties(lt, vo);
//						quoteList.add(vo);
//					}
					List<LpTaskUserActivityInfo> quoteList = new ArrayList<>();
					quoteList = loopQuote(quote, lpTaskUserActivity, 0);
					/**
					 * ?????????????????????????????????
					 */
					Collections.sort(quoteList, new Comparator<LpTaskUserActivityInfo>(){
						public int compare(LpTaskUserActivityInfo info1, LpTaskUserActivityInfo info2) {
							Long date1 = info1.getCreateDate().getTime();
							Long date2 = info2.getCreateDate().getTime();
							int flag = date1.compareTo(date2);
							return flag;
						}
					});
					lpTaskUserActivityInfo.setQuoteList(quoteList);
//					lpTaskUserActivityInfo.setQuoteList(quoteList);
				}

				String files = lpTaskUserActivity.getFiles();
				List<String> filePath = new ArrayList<String>();
				if(files != null && !files.equals("")) {
//					String filesStr = files.substring(1, files.length()-1);
					String filesSet[] = files.split(",");
					for(int j=0;j<filesSet.length;j++) {
//						EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//						if(ef != null) {
//							filePath.add(ef.getRelativePath());
//						}
						String path = fileService.relativePath2HttpUrlByUUID(filesSet[j]);
						if(path != null && !"".equals(path)) {
							filePath.add(path);
						}
					}
				}
				lpTaskUserActivityInfo.setFilePath(filePath);

				String iocnPath = ImgUtil.getImgSrc(lpTaskUserActivity.getUserId(), profileService);
				SchoolUser su =schoolUserService.findSchoolUserByUserId(lpTaskUserActivity.getUserId());
				String role = "";
				if(su.getUserType().equals("1")) {
					role = "??????";
				}else if(su.getUserType().equals("2")) {
					role = "admin";
				}else if(su.getUserType().equals("3")){
					role = "??????";
				}else if(su.getUserType().equals("4")) {
					role = "??????";
				}
				String name = su.getName();
				lpTaskUserActivityInfo.setIocnPath(iocnPath);
				lpTaskUserActivityInfo.setRole(role);
				lpTaskUserActivityInfo.setName(name);

				BeanUtils.copyProperties(lpTaskUserActivity, lpTaskUserActivityInfo);

				LpTaskUserActivityJson json = new LpTaskUserActivityJson();
				json.setId(lpTaskUserActivityInfo.getId());
				json.setLpId(lpTaskUserActivityInfo.getLpId());
				json.setTaskId(lpTaskUserActivityInfo.getTaskId());
				json.setUnitId(lpTaskUserActivityInfo.getUnitId());
				json.setUserId(lpTaskUserActivityInfo.getUserId());
				json.setContent(lpTaskUserActivityInfo.getContent());
				json.setFiles(lpTaskUserActivityInfo.getFilePath());
				json.setDegree(lpTaskUserActivityInfo.getDegree());
				json.setCreateDate(lpTaskUserActivityInfo.getCreateDate());
				json.setModifyDate(lpTaskUserActivityInfo.getModifyDate());
				json.setIsDeleted(lpTaskUserActivityInfo.getIsDeleted());
				json.setQuote(lpTaskUserActivityInfo.getQuote());
				json.setQuoteList(lpTaskUserActivityInfo.getQuoteList());
				json.setIocnPath(lpTaskUserActivityInfo.getIocnPath());
				json.setRole(lpTaskUserActivityInfo.getRole());
				json.setName(lpTaskUserActivityInfo.getName());

				data.add(json);
			}
			map.put("conversationList", data);

		}

			map.put("files", fileList);
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("?????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	private List<LpTaskUserActivityInfo> loopQuote(String quote[], LpTaskUserActivity lpTaskUserActivity, Integer schoolId) {
		List<LpTaskUserActivityInfo> quoteList = new ArrayList<>();
		for(String q : quote) {
			LpTaskUserActivityInfo info = getQuote(q, lpTaskUserActivity, schoolId);
			quoteList.add(info);
			if(info.getQuote() != null && !info.getQuote().equals("")) {
				String qu[] = info.getQuote().split(",");
				List<LpTaskUserActivityInfo> quo = loopQuote(qu, lpTaskUserActivity, schoolId);
				info.setQuoteList(quo);
				for(LpTaskUserActivityInfo in : info.getQuoteList()) {
					quoteList.add(in);
				}
			}
		}
		return quoteList;
	}
	
	private LpTaskUserActivityInfo getQuote(String quote, LpTaskUserActivity lpTaskUserActivity, Integer schoolId) {
		LpTaskUserActivity lt = new LpTaskUserActivity();
		
		LpTaskUserActivityInfo info = new LpTaskUserActivityInfo();
		lt = lpTaskUserActivityService.findLpTaskUserActivityById(Integer.parseInt(quote));
		String name = "";
		Student st = studentService.findStudentByUserId(lt.getUserId());
		if(st!=null) {
			name = st.getName();
		}else {
			List<Teacher> t = teacherService.findTeacherByUserId(lt.getUserId());
			if(t != null && t.size() > 0) name = t.get(0).getName();
		}
		info.setName(name);
		String files = lpTaskUserActivity.getFiles();
		List<String> filePath = new ArrayList<String>();
		if(files != null && !files.equals("")) {
//				String filesStr = files.substring(1, files.length()-1);
			String filesSet[] = files.split(",");
			for(int j=0;j<filesSet.length;j++) {
//					EntityFile ef = entityFileService.findFileByUUID(filesSet[j]);
//					if(ef != null) {
//						filePath.add(ef.getRelativePath());
//					}
				String path = fileService.relativePath2HttpUrlByUUID(filesSet[j]);
				if(path != null && !"".equals(path)) {
					filePath.add(path);
				}
			}
		}
		info.setFilePath(filePath);
		BeanUtils.copyProperties(lt, info);
			
		return info;
	}
	
	@Override
	public Object unitGetAndStart(Integer userId, String appKey, Integer taskId, Integer unitId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/**???????????????????????????*/
			boolean flag = learningPlanHandlerService.unitStart(userId, taskId, unitId);
			if(!flag) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????");
				info.setMsg("???????????????????????????");
				info.setParam("taskId, unitId, userId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			
			
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object unitFinsh(Integer userId, String appKey, Integer taskId, Integer unitId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpUnit lpUnit = lpUnitService.findLpUnitById(unitId);
			if(lpUnit==null) {
				return new ResponseVo<Object>("0",new HashMap<String, Object>());//???????????????
			}
			
			learningPlanHandlerService.unitStart(userId, taskId, unitId);
			
			Map<String, Object> map = learningPlanHandlerService.unitFinished(userId, taskId, unitId);
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("?????????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
		
	}

	@Override
	public Object acitivityAdd(String appKey, Integer taskId, Integer unitId, Integer userId, String content,
			String files) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			learningPlanHandlerService.unitStart(userId, taskId, unitId);
			
			/**files???json??????, ?????????????????????,?????????????????????*/
			String fileString = null;
			try {
				if(files != null && !"".equals(files) && !"null".equals(files)) {
					
					fileString = learningPlanBaseService.parseFiles(files);
				}
			} catch (Exception e) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("files?????????????????????");
				info.setMsg("files?????????????????????");
				info.setParam("files");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			/**?????????????????????????????????????????????id?????????????????????*/
			LpTask task = lpTaskService.findLpTaskById(taskId);
			if(task==null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("??????????????????");
				info.setMsg("??????????????????");
				info.setParam("taskId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			/**?????????????????????????????????*/
			LpTaskUserActivity lpTaskUserActivity = new LpTaskUserActivity();
			lpTaskUserActivity.setContent(content);
			lpTaskUserActivity.setCreateDate(new Date());
			lpTaskUserActivity.setIsDeleted(false);
			lpTaskUserActivity.setLpId(task.getLpId());
			lpTaskUserActivity.setTaskId(taskId);
			lpTaskUserActivity.setUnitId(unitId);
			lpTaskUserActivity.setUserId(userId);
			lpTaskUserActivity.setFiles(fileString);
			/**???????????????????????????*/
			lpTaskUserActivity = lpTaskUserActivityService.add(lpTaskUserActivity);
			
			//?????????????????????????????????taskUserActivityFiles ??????
			if(fileString != null) {
				String[] taskUserActivityFiles = fileString.split(",");
				if(taskUserActivityFiles != null && taskUserActivityFiles.length > 0) {
					Date nowDate = new Date();
					for(int i=0;i<taskUserActivityFiles.length;i++) {
						TaskUserActivityFiles taf = new TaskUserActivityFiles();
						taf.setIsDeleted(0);
						taf.setCreateDate(nowDate);
						taf.setModifyDate(nowDate);
						taf.setSourceFileUuid(taskUserActivityFiles[i]);
						taf.setTaskUserActivityId(lpTaskUserActivity.getId());
						taskUserActivityFilesService.add(taf);
					}
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			if(lpTaskUserActivity.getId()!=null) {
				/**????????????????????????(????????????????????????????????????????????????)*/
				learningPlanHandlerService.unitStart(userId, taskId, unitId);
				/**??????????????????*/
				learningPlanHandlerService.unitFinished(userId, taskId, unitId);
				map.put("success", true);
			} else {
				map.put("success", false);
			}
			
			lpTaskActivityStateService.updateDataChangeByTaskId(taskId);
			
			return new ResponseVo<Object>("0", map);
		} catch(Exception e) {
			e.printStackTrace();
	        ResponseInfo info = new ResponseInfo();
	        info.setDetail("????????????????????????");
	        info.setMsg("????????????");
	        return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object acitivityUserList(String appKey, Integer taskId, Integer unitId, Integer userId,Integer pageSize, 
			Integer pageNumber) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			LpUnit lpUnit = lpUnitService.findLpUnitById(unitId);
			if(lpUnit==null || !LpUnitType.ACTIVITY.equals(lpUnit.getUnitType())) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????");
				info.setMsg("???????????????????????????");
				info.setParam("unitId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			if(lpUnit!=null) {
				if(lpUnit.getContent()!=null && !"".equals(lpUnit.getContent().trim())) {
					map.put("unitContent", lpUnit.getContent());
				} else {
					map.put("unitContent", "??????????????????????????????????????????????????????");
				}
				map.put("unitId", lpUnit.getId());
			}
			
			map.put("iconUrl", ImgUtil.getImgSrc(userId, profileService));
			
			//Page page = learningPlanBaseService.getPage(pageSize, pageNumber);
			
			/**????????????????????????????????????*/
			List<LpTaskUserActivity> lpTaskUserActivityList = lpTaskUserActivityService.findLpTaskUserActivityByCondition(taskId, unitId, userId, null);
			
			List<Map<String, Object>> contentList = new ArrayList<Map<String, Object>>(lpTaskUserActivityList.size());
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			for (LpTaskUserActivity lpTaskUserActivity : lpTaskUserActivityList) {
				/**??????????????????????????????*/
				Map<String, Object> content = new HashMap<String, Object>();
				/**????????????????????????*/
				content.put("content", lpTaskUserActivity.getContent());
				content.put("unitType", lpUnit.getUnitType());
				
				List<Map<String, Object>> fileMapList = new ArrayList<Map<String, Object>>();
				TaskUserActivityFilesCondition taskUserActivityFilesCondition = new TaskUserActivityFilesCondition();
				taskUserActivityFilesCondition.setIsDeleted(0);
				taskUserActivityFilesCondition.setTaskUserActivityId(lpTaskUserActivity.getId());
				List<TaskUserActivityFiles> taskUserActivityFilesList = this.taskUserActivityFilesService.findTaskUserActivityFilesByCondition(taskUserActivityFilesCondition);
				if(taskUserActivityFilesList != null && taskUserActivityFilesList.size() > 0) {
					for(TaskUserActivityFiles taskUserActivityFiles:taskUserActivityFilesList) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("activityFileId", taskUserActivityFiles.getId());
						//??????
						String sourceFileUrl = null;
						FileResult sourceFileUuidResult = fileService.findFileByUUID(taskUserActivityFiles.getSourceFileUuid());
						if(sourceFileUuidResult.getHttpUrl()!=null && !"".equals(sourceFileUuidResult.getHttpUrl())) {
							sourceFileUrl = sourceFileUuidResult.getHttpUrl();
						}
						
						String markedFileUrl = null;
						//??????????????????
						FileResult markedFileUuidResult = fileService.findFileByUUID(taskUserActivityFiles.getMarkedFileUuid());
						if(markedFileUuidResult.getHttpUrl()!=null && !"".equals(markedFileUuidResult.getHttpUrl())) {
							markedFileUrl =markedFileUuidResult.getHttpUrl(); 
						}
						
						params.put("markedFileUrl", markedFileUrl);
						params.put("sourceFileUrl", sourceFileUrl);
						fileMapList.add(params);
					}
				}
				
				
			/*	*//**?????????????????????*//*
				String files = lpTaskUserActivity.getFiles();
				*//**?????????????????????*//*
				String[] filesArray = files.split(",");
				List<String> imgList = new ArrayList<String>();
				
				*//**?????????????????????*//*
				for (String file : filesArray) {
					FileResult result = fileService.findFileByUUID(file);
					if(result.getHttpUrl()!=null && !"".equals(result.getHttpUrl())) {
						imgList.add(result.getHttpUrl());
					}
				}*/
				/**????????????????????????*/
				content.put("activityId", lpTaskUserActivity.getId());
				content.put("files", new String[] {});
				content.put("newFiles", fileMapList);
				
				content.put("createTime", dateFormat.format(lpTaskUserActivity.getCreateDate()));
				contentList.add(content);
			}
			
			map.put("activities",contentList);
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object listByTeam(Integer userId, Integer schoolId, Integer teamId, String appKey, String subjectCode,
			Integer pageSize, Integer pageNumber) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			Map<String,String>subjectMap=new HashMap<String, String>();
			subjectMap=subjectService.findAllSubjectNameMap();
			/**?????????????????????*/
			Page page = learningPlanBaseService.getPage(pageSize, pageNumber);
			/**??????????????????*/
			//Map<String, String> subjectMap = learningPlanBaseService.getSubjectMap(schoolId);
			if(subjectCode!=null&&subjectCode.equals("-1")){
				subjectCode=null;
			}
			
			/**??????????????????????????????*/
			List<Map<String, Object>> taskList =  learningPlanHandlerService.lpTaskByUserId(userId, subjectCode, subjectMap, teamId, page);
			
			if(taskList==null || taskList.size()==0) {
				return new ResponseVo<Object>("0",new ArrayList<Object>());//???????????????
			}
			
			/**????????????id??????*/
			Integer[] ids = new Integer[taskList.size()];
			Integer index = 0;
			for (Map<String, Object> map : taskList) {
				Integer taskUserId = (Integer) map.get("userId");
				ids[index] = taskUserId;
				index++;
			}
			
			//List<User> userList = userService.findUserByIds(ids);
			//Map<Integer, Person> personMap = learningPlanBaseService.getPersons(userList);
			for (Map<String, Object> map : taskList) {
				Integer taskUserId = (Integer) map.get("userId");
				/**???????????????????????????????????????*/
				if(taskUserId-userId==0) {
					map.put("isDelete", true);
				} else {
					map.put("isDelete", false);
				}
				
				//Person person = personMap.get(taskUserId);
				Teacher t=teacherService.findOfUser(schoolId, taskUserId);
				if(t!=null) {
					map.put("userName", t.getName());
				} else {
					log.info("userId=[" + taskUserId + "]??????????????????person??????");
					map.put("userName", "??????");
				}
				/**????????????*/
				//Integer taskTeamId = (Integer) map.get("objectId");
				//Team team = teamService.findTeamById(taskTeamId);
				/**??????????????????*/
				//if(team!=null) {
				//	map.put("className", team.getName());
				//} else {
				//	map.put("className", "");
				//}
				
				String lpUuid = (String) map.get("lpUuid");
				Resource resource = resourceService.findResourceByObjectid(lpUuid);
				if(resource!=null) {
					/**????????????code*/
					String catalogCode = resourceHandlerService.getResourceCatalogCode(resource.getId());
					/**??????????????????*/
					String catalogName = resTextbookCatalogService.getFullNameByCode(catalogCode," ");
					map.put("catalogName", catalogName);
				}
				map.remove("lpUuid");
				map.remove("userId");
				map.remove("objectId");
			}
			
			return new ResponseVo<Object>("0",taskList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("?????????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object taskDelete(Integer taskId, String appKey) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTask lpTask = lpTaskService.findLpTaskById(taskId);
			if(lpTask==null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????");
				info.setMsg("???????????????????????????");
				info.setParam("taskId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
	    	if(lpTask!=null) {
	    		/**????????????????????????*/
	    		lpTaskUnitUserService.removeByTaskId(lpTask.getId());
	    		/**??????????????????*/
	    		lpTaskUserService.removeByTaskId(lpTask.getId());
	    		/**????????????*/
	    		lpTaskService.remove(lpTask);
	    	}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", true);
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object activityList(String appKey, Integer learningPlanId, Integer unitType) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LearningPlan lp = learningPlanService.findLearningPlanById(learningPlanId);
			if(lp==null) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());//???????????????
			}
			
			/**???????????????id?????????????????????????????????*/
			List<LpUnit> lpUnitList = new ArrayList<>();
			lpUnitList.addAll(lpUnitService.findUnitListByLpIdAndUnitType(lp.getId(), Integer.parseInt(LpUnitType.ACTIVITY)));
			lpUnitList.addAll(lpUnitService.findUnitListByLpIdAndUnitType(lp.getId(), Integer.parseInt(LpUnitType.GROUP_ACTIVITY)));
			lpUnitList.addAll(lpUnitService.findUnitListByLpIdAndUnitType(lp.getId(), Integer.parseInt(LpUnitType.CONVERSATION)));

			/**?????????????????????*/
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if(lpUnitList.size()==0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());//???????????????
			}
			
			for (LpUnit lpUnit : lpUnitList) {
				Map<String, Object> map = new HashMap<String, Object>(1);
				map.put("unitId", lpUnit.getId());
				map.put("title", lpUnit.getTitle());
				map.put("unitType", lpUnit.getUnitType());
				list.add(map);
			}
			
			return new ResponseVo<Object>("0",list);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}


	@Override
	public Object activityUnitList(String appKey, Integer taskId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(taskId == null) return new ResponseVo<Object>("0",new ArrayList<>());


			/**???????????????id?????????????????????????????????*/

			List<LpTaskUnitUser> lpTaskUnitUserList = new ArrayList<>();
			LpTaskUnitUserCondition unitUserCondition = new LpTaskUnitUserCondition();
			unitUserCondition.setTaskId(taskId);
			lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(unitUserCondition);
			if(lpTaskUnitUserList.size()>1){
				unitUserCondition.setUserId(lpTaskUnitUserList.get(0).getUserId());
				lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(unitUserCondition);
			}

			/**?????????????????????*/
			Map<Integer, Object> result = new HashMap<>();

			if(lpTaskUnitUserList == null || lpTaskUnitUserList.size()==0){
				return new ResponseVo<Object>("0", new ArrayList<Object>());//???????????????
			}
			for (LpTaskUnitUser lpTaskUnitUser : lpTaskUnitUserList) {
				Map<String, Object> map = new HashMap<String, Object>();
				LpUnit lpUnit = lpUnitService.findLpUnitById(lpTaskUnitUser.getUnitId());
				if(lpUnit != null && (LpUnitType.ACTIVITY.equals(lpUnit.getUnitType()) || LpUnitType.GROUP_ACTIVITY.equals(lpUnit.getUnitType()) || LpUnitType.CONVERSATION.equals(lpUnit.getUnitType()) )) {
					map.put("unitId", lpUnit.getId());
					map.put("title", lpUnit.getTitle());
					map.put("unitType", lpUnit.getUnitType());
					result.put(lpUnit.getId(), map);
				}
			}

			return new ResponseVo<Object>("0",result.values());
		} catch(Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("???????????????????????????????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}

	@Override
	public Object taskActivityList(String appKey, Integer taskId, Integer unitId, Integer userId, Integer pageSize, Integer pageNumber) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			LpUnit lpUnit = lpUnitService.findLpUnitById(unitId);
			if(lpUnit==null || (!LpUnitType.ACTIVITY.equals(lpUnit.getUnitType()) && !LpUnitType.GROUP_ACTIVITY.equals(lpUnit.getUnitType()) && !LpUnitType.CONVERSATION.equals(lpUnit.getUnitType()))) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????");
				info.setMsg("???????????????????????????");
				info.setParam("unitId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			LpTask lpTask = lpTaskService.findLpTaskById(taskId);
			if(lpTask==null) {
				return new ResponseVo<Object>("0",new HashMap<String, Object>());//???????????????
			}
			
			Map<String, Object> map =  new HashMap<String, Object>();
			
			//Page page = learningPlanBaseService.getPage(pageSize, pageNumber);

			/**????????????????????????*/
			List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(taskId, unitId, null);
			/**????????????????????????*/
			List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByConditionTaskId(taskId);
			
			/**??????????????????*/
			Integer finishedCount = 0;
			/**??????id?????????*/
			Integer[] userIds = new Integer[lpTaskUserList.size()];
			for (int i = 0; i < lpTaskUserList.size(); i++) {
				userIds[i] = lpTaskUserList.get(i).getUserId();
			}
 			/**???????????????????????????*/
			Map<Integer, Boolean> finishedMap = new HashMap<Integer, Boolean>();
			
			for (int i = 0; i < lpTaskUnitUserList.size(); i++) {
 				LpTaskUnitUser lpTaskUnitUser = lpTaskUnitUserList.get(i);
 				/**??????????????????*/
 				if(lpTaskUnitUser.getHasFinished()) {
					/**??????????????????*/
 					finishedCount++;
					finishedMap.put(lpTaskUnitUser.getUserId(), lpTaskUnitUser.getHasFinished());
				}
			}
			
			/**??????Person?????????(?????????????????????name) map???id???userId, value???person*/
 			Map<Integer, Person> personMap = learningPlanBaseService.getPersonMapByLpTaskUserList(lpTaskUserList);
 			Map<Integer, Student> studentMap = new HashMap<Integer, Student>();
 			List<Student> slist=studentService.findbyUserIds(userIds);
 			for(Student s:slist){
 				studentMap.put(s.getUserId(), s);
 			}
 			
 			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
 			
 			/**?????????????????????????????????*/
 			List<Map<String, Object>> finishedStudents = new ArrayList<Map<String, Object>>();
 			/**????????????????????????????????????*/
 			List<Map<String, Object>> unfinishedStudents = new ArrayList<Map<String, Object>>();
 			
 			for (LpTaskUser lpTaskUser : lpTaskUserList) {
 				Map<String, Object> lpTaskUserMap = new HashMap<String, Object>();
 				Student s=studentMap.get(lpTaskUser.getUserId());
 				/**???map?????????person*/
 					lpTaskUserMap.put("studentId", lpTaskUser.getUserId());
 					lpTaskUserMap.put("studentName", (s==null)?"??????":s.getName());
 					if(s==null||s.getSex()==null || "".equals(s.getSex())) {
 						lpTaskUserMap.put("sex", "0");
 					} else {
 						lpTaskUserMap.put("sex", s.getSex());
 					}
 					/**??????????????????*/
 					String imgPath = imgMap.get(lpTaskUser.getUserId());
 					if(imgPath==null){
 						imgPath=SysContants.APP_DEFAULT_USERICON;
 					}
 					lpTaskUserMap.put("iconUrl", imgPath);
 				
 				/**?????????????????????????????????????????????????????????*/
 				if(finishedMap.get(lpTaskUser.getUserId())!=null) {
 					finishedStudents.add(lpTaskUserMap);
 				} else {
 					unfinishedStudents.add(lpTaskUserMap);
 				}
			}
 			
 			map.put("finishedStudents", finishedStudents);
			map.put("unfinishedStudents", unfinishedStudents);
 			/** ??????????????????????????? **/

			if(LpUnitType.ACTIVITY.equals(lpUnit.getUnitType())) {//??????????????????
				/**???????????????????????????(????????????????????????(?????????))*/
				if (lpUnit.getContent() != null && !"".equals(lpUnit.getContent().trim())) {
					map.put("unitContent", lpUnit.getContent());
				} else {
					map.put("unitContent", "??????????????????????????????????????????????????????");
				}

				/**???????????????*/
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

				/**??????????????????????????????*/
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

				//Map<String, FileResult> filesMap = learningPlanBaseService.getFileResult(lpTaskUserActivityList);

				/**??????????????????????????????*/
				List<LpTaskUserActivity> lpTaskUserActivityList = lpTaskUserActivityService.findLpTaskUserActivityByCondition(taskId, unitId, null, null);

				for (LpTaskUserActivity lpTaskUserActivity : lpTaskUserActivityList) {
					Map<String, Object> student = new HashMap<String, Object>(1);
					/**files??????????????????,??????????????????, ???????????????????????????*/
					//String files = lpTaskUserActivity.getFiles();
					//String[] filesArray = files.split(",");
					//List<String> fileList = new ArrayList<String>();

					List<Map<String, Object>> fileMapList = new ArrayList<Map<String, Object>>();
					TaskUserActivityFilesCondition taskUserActivityFilesCondition = new TaskUserActivityFilesCondition();
					taskUserActivityFilesCondition.setIsDeleted(0);
					taskUserActivityFilesCondition.setTaskUserActivityId(lpTaskUserActivity.getId());
					List<TaskUserActivityFiles> taskUserActivityFilesList = this.taskUserActivityFilesService.findTaskUserActivityFilesByCondition(taskUserActivityFilesCondition);
					if (taskUserActivityFilesList != null && taskUserActivityFilesList.size() > 0) {
						for (TaskUserActivityFiles taskUserActivityFiles : taskUserActivityFilesList) {
							Map<String, Object> params = new HashMap<String, Object>();
							params.put("activityFileId", taskUserActivityFiles.getId());
							//??????
							String sourceFileUrl = null;
							FileResult sourceFileUuidResult = fileService.findFileByUUID(taskUserActivityFiles.getSourceFileUuid());
							if (sourceFileUuidResult.getHttpUrl() != null && !"".equals(sourceFileUuidResult.getHttpUrl())) {
								sourceFileUrl = sourceFileUuidResult.getHttpUrl();
							}

							String markedFileUrl = null;
							//??????????????????
							FileResult markedFileUuidResult = fileService.findFileByUUID(taskUserActivityFiles.getMarkedFileUuid());
							if (markedFileUuidResult.getHttpUrl() != null && !"".equals(markedFileUuidResult.getHttpUrl())) {
								markedFileUrl = markedFileUuidResult.getHttpUrl();
							}

							params.put("markedFileUrl", markedFileUrl);
							params.put("sourceFileUrl", sourceFileUrl);

							fileMapList.add(params);
						}
					}
					//	List<TaskUserActivityFiles> taskUserActivityFilesList = this.taskUserActivityFilesServices.

					/**???????????????????????????*/
				/*for (String uuid : filesArray) {
					FileResult result = filesMap.get(uuid);
					
					if(result!=null && result.getHttpUrl()!=null && !"".equals(result.getHttpUrl())) {
						fileList.add(result.getHttpUrl());
					}
				}*/

					/**??????????????????*/
					String imgUrl = imgMap.get(lpTaskUserActivity.getUserId());
					/**??????person*/
//				Person person  = personMap.get(lpTaskUserActivity.getUserId());

					/**???????????????????????????????????????????????????*/
					student.put("unitType", lpUnit.getUnitType());
					Student person = studentMap.get(lpTaskUserActivity.getUserId());
					if (person != null) {
						student.put("studentId", lpTaskUserActivity.getUserId());
						student.put("studentName", person.getName());
						if (person.getSex() == null || "".equals(person.getSex())) {
							student.put("sex", "0");
						} else {
							student.put("sex", person.getSex());
						}
					}

					if (imgUrl != null) {
						student.put("iconUrl", imgUrl);
					} else {
						student.put("iconUrl", SysContants.APP_DEFAULT_USERICON);
					}

					student.put("content", lpTaskUserActivity.getContent());
					student.put("files", new String[]{});
					student.put("newFiles", fileMapList);
					student.put("createTime", dateFormat.format(lpTaskUserActivity.getCreateDate()));
					list.add(student);

				}
				map.put("activities", list);
			} else if(LpUnitType.GROUP_ACTIVITY.equals(lpUnit.getUnitType())){
				map.put("groupSummarize", ((ResponseVo)groupSummarizeService.findSummarize(appKey,taskId, unitId)).getData());
			} else if(LpUnitType.CONVERSATION.equals(lpUnit.getUnitType())) {
				map.put("conversationList", ((ResponseVo)conversationService.findConversationDate(appKey, lpUnit.getLpId(), taskId, unitId)).getData());
			}

			/**?????????????????????*/
			lpTaskActivityStateService.updateLpTaskActivityStateByCondition(taskId, userId);
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object acitivitydelete(String appKey, Integer activityId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			LpTaskUserActivity lpTaskUserActivity = lpTaskUserActivityService.findLpTaskUserActivityById(activityId);
			
			if(lpTaskUserActivity==null) {
				return new ResponseVo<Object>("0",new HashMap<String, Object>());//???????????????
			}
			/**???????????????????????????*/
			lpTaskUserActivityService.remove(lpTaskUserActivity);
			
			Integer taskId = lpTaskUserActivity.getTaskId();
			Integer userId = lpTaskUserActivity.getUserId();
			Integer unitId = lpTaskUserActivity.getUnitId();
			
			/**???????????????????????????????????????????????????*/
			List<LpTaskUserActivity> lpTaskUserActivityList = lpTaskUserActivityService.findLpTaskUserActivityByCondition(taskId, unitId, userId, null);
			
			/**????????????????????????????????????????????????, ????????????????????????????????????????????????*/
			if(lpTaskUserActivityList.size()==0) {
				/**????????????????????????*/
				List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(taskId, unitId, userId);
				
				/**????????????????????????????????? ??????????????????????????????*/
				if(lpTaskUnitUserList.size()>0) {
					LpTaskUnitUser lpTaskUnitUser = lpTaskUnitUserList.get(0);
					lpTaskUnitUser.setHasFinished(false);
					lpTaskUnitUserService.modify(lpTaskUnitUser);
				}
				
				/**??????????????????????????????????????????*/
				lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(taskId, null, userId);
				
				/**??????????????????????????????*/
				boolean state = false;
				
				for (LpTaskUnitUser lpTaskUnitUser : lpTaskUnitUserList) {
					state = state | lpTaskUnitUser.getHasFinished();
					/**????????????????????????????????????????????????*/
					if(state) {
						break;
					}
				}
				
				/**?????????????????????????????????????????? */
				LpTaskUser lpTaskUser = lpTaskUserService.findLpTaskUserByCondition(taskId, userId);
				
				/**??????????????????*/
				if(lpTaskUser != null) {
					/**????????????????????????????????????, ????????????????????????????????????*/
					if(state) {
						lpTaskUser.setState(LpState.LEARNING);
					} else {
						/**??????????????????????????????????????????????????????????????????????????????*/
						lpTaskUser.setState(LpState.START_LEARN);
					}
					/**???????????????????????????*/
					lpTaskUserService.modify(lpTaskUser);
				}
			}
			
			lpTaskActivityStateService.updateDataChangeByTaskId(lpTaskUserActivity.getTaskId());
			
			//????????????ID???????????????????????????????????????
			taskUserActivityFilesService.updateTaskUserActivityFilesBytaskUserActivityId(activityId);
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			map.put("success", true);
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object unitPaperGet(String appKey, Integer taskId, String subjectCode,Integer type) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			/**??????????????????????????????????????????*/
			List<Map<String, Object>> list =new ArrayList<Map<String,Object>>();
			if(type==null||type.intValue()==PaperType.LEARNING_PLAN){
				/**?????????????????????*/
				LpTask task = lpTaskService.findLpTaskById(taskId);
				if(task==null) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("???????????????????????????");
					info.setMsg("???????????????????????????");
					info.setParam("taskId");
					return new ResponseError(CommonCode.S$INVALID_DATA, info);
				}
				
				if(subjectCode!=null) {
					LearningPlan lp = learningPlanService.findLearningPlanById(task.getLpId());
					if(lp==null || !lp.getSubjectCode().equals(subjectCode)) {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("subjectcode?????????");
						info.setMsg("subjectcode?????????");
						info.setParam("subjectCode");
						return new ResponseError(CommonCode.S$INVALID_DATA, info);
					}
				}
				list =learningPlanBaseService.getExamUnitList(taskId, task.getLpId(),subjectCode);
			}else{
				Task task=taskService.findTaskById(taskId);
				if(task==null) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("????????????????????????");
					info.setMsg("????????????????????????");
					info.setParam("taskId");
					return new ResponseError(CommonCode.S$INVALID_DATA, info);
				}
				Integer paperIds[]={task.getPaperId()};
				Map<String,Object> unitMap=new HashMap<String, Object>();
				List<PaQuestionVo> questionList = paQuestionService.findPaperQuestionByPaperIds(paperIds);
				List<Map<String, Object>> qmList =new ArrayList<Map<String,Object>>();
				for (int i = 0; i < questionList.size(); i++) {
					Map<String,Object> questionMap=new HashMap<String, Object>();
					PaQuestionVo question=questionList.get(i);
					if(question.getPos()!=0){
						questionMap.put("pos", question.getPos());
						questionMap.put("questionUuid", question.getUuid());
						questionMap.put("questionType", question.getQuestionType());
						qmList.add(questionMap);
					}
				}
				unitMap.put("unitId", 0);
				unitMap.put("unitName", task.getTitle());
				unitMap.put("questions", qmList);
				list.add(unitMap);
			}
			
			return new ResponseVo<Object>("0",list);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}

	@Override
	public Object taskExamList(String appKey, Integer taskId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			LpTask lpTask = lpTaskService.findLpTaskById(taskId);
			if(lpTask==null) {
				return new ResponseVo<Object>("0",map);//???????????????
			}
			
			/**???????????????*/
			map.put("totalCount", lpTask.getUserCount());
			
			/**?????????????????????????????????*/
			List<LpInterscoreVo> LpInterscoreList = lpTaskExamUnitService.findLpInterscoreList(taskId);
			if(LpInterscoreList.size()==0) {
				map.put("units", LpInterscoreList);
				return new ResponseVo<Object>("0",map);//???????????????
			}
			
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(LpInterscoreList.size());
			for (LpInterscoreVo lpInterscoreVo : LpInterscoreList) {
				Map<String, Object> lpInterscore = new HashMap<String, Object>();
				lpInterscore.put("unitId", lpInterscoreVo.getUnitId());
				lpInterscore.put("unitName", lpInterscoreVo.getUnitName());
				lpInterscore.put("isInterscore", lpInterscoreVo.getIsInterscore());
				if(lpInterscoreVo.getStartTime()!=null) {
					lpInterscore.put("startTime", dataFormat.format(lpInterscoreVo.getStartTime()));
				}else {
					lpInterscore.put("startTime", "");
				}
				if(lpInterscoreVo.getFinishTime()!=null) {
					lpInterscore.put("finishTime", dataFormat.format(lpInterscoreVo.getFinishTime()));
				}else {
					lpInterscore.put("finishTime", "");
				}
				/**??????????????????????????????*/
				List<UserPaper> userPaperList = userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, lpInterscoreVo.getUnitId());
				/**????????????*/
				lpInterscore.put("submitCount", userPaperList.size());
				
				/**?????????????????????*/
				int interscoreCount = learningPlanInterscoreService.findInterscoreCountByTeamIdAndTaskExamUnitId(lpTask.getObjectId(), lpInterscoreVo.getTaskExamUnitId());
				lpInterscore.put("interscoreCount", interscoreCount);
				
				list.add(lpInterscore);
			}
			map.put("units", list);
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}

	@Override
	public Object taskIsInterscore(String appKey, Integer taskId, Integer unitId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			LpTask lpTask = lpTaskService.findLpTaskById(taskId);
			if(lpTask==null) {
				return new ResponseVo<Object>("0",map);//???????????????
			}
			
			/**????????????????????????????????????*/
			List<UserPaper> userPaperList = userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, unitId);
			/**????????????*/
			map.put("inExam", userPaperList.size());
			/**???????????????=???????????????-????????????*/
			map.put("missExam", lpTask.getUserCount()-userPaperList.size());
			
			/**????????????????????????????????????*/
			LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitByTaskIdAndUnitId(taskId, unitId);
			
			if(lpTaskExamUnit==null) {
				return new ResponseVo<Object>("0",map);//???????????????
			}
			
			/**????????????????????????????????????????????????*/
			if(lpTaskExamUnit.getInterscoreStartTime()==null) {
				map.put("isFirst", 1);
			} else {
				map.put("isFirst", 0);
			}
			
			/**????????????????????????*/
			List<LearningPlanInterscore> learningPlanInterscoreList = learningPlanInterscoreService.findLearningPlanInterscoreListByTeamIdAndTaskExamUnitId(lpTask.getObjectId(), lpTaskExamUnit.getId());
			int interscoreCount = 0;
			for (LearningPlanInterscore learningPlanInterscore : learningPlanInterscoreList) {
				if(learningPlanInterscore.getScoringTime()!=null) {
					interscoreCount++;
				}
			}
			map.put("interscore", interscoreCount);
			map.put("notInterscore", learningPlanInterscoreList.size()-interscoreCount);
			map.put("inEmpty", lpTask.getUserCount()-learningPlanInterscoreList.size());
			
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("?????????????????????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	

	@Override
	public Object taskIsInterscoreStart(String appKey, Integer taskId, Integer unitId, Integer isInterscore,
			String startTime,String finishTime) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			Map<String, Object> map = new HashMap<String, Object>();

			LpTask lpTask = lpTaskService.findLpTaskById(taskId);
			if(lpTask==null) {
				return new ResponseVo<Object>("0",map);//???????????????
			}
			Date date=new Date();
			SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			if(date.after(dataFormat.parse(startTime))){
			}else{
				date=dataFormat.parse(startTime);
				
			}
			if(date.after(dataFormat.parse(finishTime))){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("??????????????????????????????,?????????");
				info.setMsg("??????????????????????????????");
				info.setParam("startTime,finishTime");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			boolean state = isInterscore==1?true:false;
			
			if (state) {
				if(dataFormat.parse(finishTime)!=null && dataFormat.parse(finishTime).before(new Date())){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("????????????????????????????????????");
					info.setMsg("????????????????????????????????????");
					info.setParam("finishTime");
					return new ResponseError(CommonCode.S$INVALID_DATA, info);
				}
			}
			
			List<UserPaper> userPaperList = this.userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, unitId);
			
			if(userPaperList==null || userPaperList.size()<=1){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("????????????????????????2,????????????");
				info.setMsg("????????????????????????2,????????????");
				info.setParam("taskId");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitByTaskIdAndUnitId(taskId, unitId);
			
			if(lpTaskExamUnit==null) {
				return new ResponseVo<Object>("0",map);//???????????????
			}
			
			if(state) {
				if(lpTaskExamUnit.getInterscoreStartTime()==null){
					lpTaskExamUnit.setInterscoreStartTime(date);
				}
				System.out.println(dataFormat.parse(finishTime));
				lpTaskExamUnit.setInterscoreFinishTime(dataFormat.parse(finishTime));
			}
			
			lpTaskExamUnit.setInterscoring(state);
			
			/**?????????????????????????????????????????????????????????????????????????????????????????????*/
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
			TransactionStatus status = transactionManager.getTransaction(def);
			
			try {
				lpTaskExamUnitService.modify(lpTaskExamUnit);
				learningPlanBaseService.initExamInterscore(taskId, lpTaskExamUnit.getId(), unitId, lpTask.getObjectId());
				transactionManager.commit(status);
			} catch (Exception e) {
				e.printStackTrace();
				transactionManager.rollback(status);
			}
			map=new HashMap<String, Object>();
			map.put("success", true);
			map.put("startTime", dataFormat.format(date));
			return new ResponseVo<Object>("0",map);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("?????????????????????????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object interscoreListBySubject(Integer userId, String appKey, Integer pageNumber, Integer pageSize,
			String subjectCode) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			List<LearningPlanInterscoreVo> learningPlanInterscoreVoList = learningPlanInterscoreService.findInterscoreList(userId, subjectCode, null);
			
			SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			/**???????????????*/
			/**????????????????????????*/
			Map<String, Object> interscoreMap = new HashMap<String, Object>(1);
			/**??????????????????????????????*/
			List<Map<String, Object>> userTaskList = new ArrayList<Map<String, Object>>(5);
			
			for (int i = 0; i < learningPlanInterscoreVoList.size(); i++) {
				LearningPlanInterscoreVo learningPlanInterscoreVo = learningPlanInterscoreVoList.get(i);
				/**???????????????????????????*/
				interscoreMap.put("startTime", formatH.format(learningPlanInterscoreVo.getStartTime()));
				interscoreMap.put("finishTime", formatH.format(learningPlanInterscoreVo.getFinishTime()));
				/**???????????????????????????*/
				interscoreMap.put("unitId", learningPlanInterscoreVo.getUnitId());
				interscoreMap.put("unitTitle", learningPlanInterscoreVo.getUnitTitle());
				interscoreMap.put("taskId", learningPlanInterscoreVo.getTaskId());
				interscoreMap.put("subjectCode", learningPlanInterscoreVo.getSubjectCode());
				interscoreMap.put("scoredUserId", learningPlanInterscoreVo.getScoredUserId());
				
				int status = 0;
				int isInterscore = 0;
				
				if(learningPlanInterscoreVo.getScoringTime()!=null) {
					isInterscore = 1;
				}
				interscoreMap.put("isInterscore", isInterscore);
				
				if(learningPlanInterscoreVo.getStartTime().before(new Date())) {
					status = 1;
				}

				interscoreMap.put("status", status);
				userTaskList.add(interscoreMap);
			}
			
			return new ResponseVo<Object>("0",userTaskList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object interscoreDetails(String appKey, Integer taskId, Integer unitId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			LpTask lp=lpTaskService.findLpTaskById(taskId);
			List<TeamStudent> tsList=new ArrayList<TeamStudent>();
			tsList=teamStudentService.findByTeamId(lp.getObjectId());
			Map<Integer,String> tsMap=new HashMap<Integer, String>();
			for(TeamStudent ts:tsList){
				tsMap.put(ts.getUserId(), ts.getName());
			}
			
			LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
			lpTaskUserCondition.setTaskId(taskId);
			List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition);
			
			/**??????id?????????*/
			Integer[] userIds = new Integer[lpTaskUserList.size()];
			
			for (int i = 0; i < lpTaskUserList.size(); i++) {
				userIds[i] = lpTaskUserList.get(i).getUserId();
			}
			
			/**??????Person?????????(?????????????????????name) map???id???userId, value???person*/
 			Map<Integer, Person> personMap = learningPlanBaseService.getPersonMapByLpTaskUserList(lpTaskUserList);
 			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
 			
			Map<Integer, LearningPlanInterscore> map = learningPlanInterscoreService.findInterscoreDetails(taskId, unitId);
			
			/**???????????????*/
			List<Object> notEvaluationList = new ArrayList<Object>();
			/**??????????????????*/
			List<Object> evaluationList = new ArrayList<Object>();
			/**????????????*/
			List<Object> missExamList = new ArrayList<Object>();
			
			for (LpTaskUser lpTaskUser : lpTaskUserList) {
				Map<String, Object> temp = new HashMap<String, Object>();
				LearningPlanInterscore learningPlanInterscore = map.get(lpTaskUser.getUserId());
				
				temp.put("userId", lpTaskUser.getUserId());
				/**????????????*/
				if(learningPlanInterscore==null) {
//					Person missExam = personMap.get(lpTaskUser.getUserId());
					String imgUrl = imgMap.get(lpTaskUser.getUserId());
					if(imgUrl==null || "".equals(imgUrl)) {
						imgUrl = SysContants.APP_DEFAULT_USERICON;
					}
					temp.put("userIcon", imgUrl);
					temp.put("userName", tsMap.get(lpTaskUser.getUserId())==null?"??????":tsMap.get(lpTaskUser.getUserId()));
					missExamList.add(temp);
					continue;
				}
//				/**???????????????*/
//				Person scoringUser = personMap.get(learningPlanInterscore.getScoringUserId());
//				/**??????????????????*/
//				Person scoredUser = personMap.get(learningPlanInterscore.getScoredUserId());
				
				/**??????*/
				String evaluatedIcon = imgMap.get(learningPlanInterscore.getScoredUserId());
				if(evaluatedIcon==null || "".equals(evaluatedIcon)) {
					evaluatedIcon = SysContants.APP_DEFAULT_USERICON;
				}
				String evaluateingIcon = imgMap.get(learningPlanInterscore.getScoringUserId());
				if(evaluateingIcon==null || "".equals(evaluateingIcon)) {
					evaluateingIcon = SysContants.APP_DEFAULT_USERICON;
				}
				/**????????????*/
				temp.put("evaluatedIcon", evaluatedIcon);
				temp.put("evaluatedName",tsMap.get(learningPlanInterscore.getScoredUserId()));
				temp.put("evaluateingIcon", evaluateingIcon);
				temp.put("evaluateingName", tsMap.get(learningPlanInterscore.getScoringUserId()));
				
				if(learningPlanInterscore.getScoringTime()!=null) {
					LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitById(learningPlanInterscore.getTaskExamUnitId());
					/**????????????????????????????????????*/
					UserPaper userPaper = userPaperService.findUserPaperByCondition(lpTaskExamUnit.getTaskId(), 
							lpTaskExamUnit.getUnitId(), 
							learningPlanInterscore.getScoredUserId());
					if(userPaper==null) {
						temp.put("evaluatedScore", 0);
						log.error("????????????????????????..........");
					} else {
						temp.put("evaluatedScore", userPaper.getScore());
					}
					/**??????????????????*/
					evaluationList.add(temp);
				} else {
					/**???????????????*/
					notEvaluationList.add(temp);
				}
			}
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("missExam", missExamList);
			resultMap.put("notEvaluationList", notEvaluationList);
			resultMap.put("evaluationList", evaluationList);
			
			return new ResponseVo<Object>("0", resultMap);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("???????????????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object taskInterscoreFinish(String appKey, Integer taskId, Integer userId, Integer unitId, String type, String data) {
		try {
			if(unitId==null){
				return paperTaskStudentRestService.taskInterscoreFinish(appKey, taskId, userId, type, data);
			}
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			Map<String, Object> result = new HashMap<String, Object>();
			/** ???????????????????????????????????????????????????????????????????????????????????????data_change */
			boolean dataChange = false;

			JSONObject info = JSONObject.fromObject(data);
			/** ???????????????????????? */
			JSONArray files = info.getJSONArray("files");
			/** ??????????????? */
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
			
			/** ?????????????????? */
			UserPaper userPaper = userPaperService.findUserPaperByCondition(taskId, unitId, userId);
			if(userPaper==null) {
				log.info("????????????????????????.......");
				return new ResponseVo<Object>("0", result);//???????????????
			}
			
			LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitByTaskIdAndUnitId(taskId, unitId);
			if (lpTaskExamUnit == null) {
				log.info("????????????????????????");
				return new ResponseVo<Object>("0", result);// ???????????????
			}
			
			LearningPlanInterscore learningPlanInterscore = learningPlanInterscoreService.fibyScoredPaperId(userPaper.getId());
			
			if (learningPlanInterscore == null && !"teacher".equals(type)) {
				log.info("??????????????????");
				return new ResponseVo<Object>("0", result);// ???????????????
			}
			
			Date currentDate = new Date(System.currentTimeMillis());
			if("student".equals(type)) {
				if(!lpTaskExamUnit.isInterscoring()) {
					ResponseInfo responseInfo = new ResponseInfo();
					responseInfo.setDetail("?????????????????????????????????");
					responseInfo.setMsg("?????????????????????????????????");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
				if(currentDate.before(lpTaskExamUnit.getInterscoreStartTime())) {
					ResponseInfo responseInfo = new ResponseInfo();
					responseInfo.setDetail("??????????????????");
					responseInfo.setMsg("??????????????????");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
				if(currentDate.after(lpTaskExamUnit.getInterscoreFinishTime())) {
					ResponseInfo responseInfo = new ResponseInfo();
					responseInfo.setDetail("??????????????????");
					responseInfo.setMsg("??????????????????");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
			}
			
			/** ????????????????????????????????? */
			double totalScore = 0.0;

			String[] questionUUIDs = new String[questions.size()];
			for (int i = 0; i < questions.size(); i++) {
				questionUUIDs[i] = questions.getJSONObject(i).getString("questionUuid");
			}

			List<PaQuestionVo> paQuestionVos = this.paQuestionService.findPaperQuestionByUUIDs(questionUUIDs);

			Map<String, UserQuestion> userQuestionsMap = this.userQuestionService.findMapByOwnerIdAndUserId(taskId, userId, unitId);

			/** ?????????????????????????????? */
			for (int i = 0; i < paQuestionVos.size(); i++) {
				PaQuestionVo question = paQuestionVos.get(i);

				double score = questionScoreMap.get(question.getUuid());

				UserQuestion userQuestion = userQuestionsMap.get(question.getUuid());

				if (userQuestion != null) {
					totalScore = totalScore + score - userQuestion.getScore();
					userQuestion.setScore(score);
					/** ???????????????????????????????????? */
					if (question.getScore() - score == 0) {
						question.setRightAnswerCount(question.getRightAnswerCount()+1);
						userQuestion.setIsCorrect(true);
					} else {
						if(question.getRightAnswerCount()>0) {
							question.setRightAnswerCount(question.getRightAnswerCount()-1);
						}
						userQuestion.setIsCorrect(false);
					}
					/** ??????dataChange?????? */
					if (!dataChange) {
						dataChange = true;
					}
				}

			}
			
			// ?????????????????????userQuestion;
			Collection<UserQuestion> userQuestionCollection = userQuestionsMap.values();
			List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>(userQuestionCollection.size());
			Iterator<UserQuestion> iterator = userQuestionCollection.iterator();
			while (iterator.hasNext()) {
				userQuestionList.add(iterator.next());
			}

			this.userQuestionService.batchUpdateScoreAndIsCorrect(userQuestionList);
			paQuestionService.modifyRightAnswerCountBatch(paQuestionVos);

			/** ??????????????????????????? */
			userPaper.setScore(userPaper.getScore() + totalScore);
			userPaper.setScoreTime(new Date());
			/** ???????????? */
			userPaperService.modify(userPaper);
			/** ??????dataChange?????? */
			if (!dataChange) {
				dataChange = true;
			}

			List<UserFile> userFiles = new ArrayList<UserFile>();

			/** ???????????????????????? */
			for (int i = 0; i < files.size(); i++) {
				JSONObject file = files.getJSONObject(i);
				/** ??????????????????????????????id */
				Integer fileId = file.getInt("fileId");
				UserFile userFile = userFileService.findUserFileById(fileId);
				if (userFile != null) {
					String markedFileUuid = file.getString("markedFileUuid");
					/** ?????????????????? */
					userFile.setMarkedFileUuid(markedFileUuid);
					userFile.setModifyDate(new Date());
					/** ???????????? */
					userFiles.add(userFile);
				}
				/** ??????dataChange?????? */
				if (!dataChange) {
					dataChange = true;
				}
			}
			/** ?????????????????? */
			userFileService.batchUpdateMarkedFile(userFiles);

			if (dataChange) {
				/** ?????????????????? */
				ExamStat examStat = examStatService.findExamStatByExamId(lpTaskExamUnit.getExamId());
				if (examStat != null) {
					/** ????????????data_chage */
					examStat.setDataChanged(true);
					examStatService.modify(examStat);
				}
			}
			
			if("student".equals(type)) {
				if(learningPlanInterscore!=null) {
					learningPlanInterscore.setScoringTime(new Date());
					learningPlanInterscoreService.modifyScoringTime(learningPlanInterscore);
				}
			}
			
			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("????????????????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}

	@Override
	public Object findTextbookCatalogTree(Integer textbookId, String appKey, Integer userId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if(textbookId==null) {
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);//???????????????
			}
			
			List<ResTextbookCatalog> resTextbookCatalogList = resTextbookCatalogService.findResTextbookCatalogByTextbookIdAndLevel(textbookId, 1);
			
			List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
			
			for (ResTextbookCatalog resTextbookCatalog : resTextbookCatalogList) {
				/**???????????????????????????map???*/
				Map<String, Object> map = learningPlanBaseService.getCatalogInfo(resTextbookCatalog);
				
				/**??????????????????????????????(????????????)*/
				ResTextbookCatalogCondition resTextbookCatalogCondition = new ResTextbookCatalogCondition();
				resTextbookCatalogCondition.setParentId(resTextbookCatalog.getId());
				List<ResTextbookCatalog> list = resTextbookCatalogService.findResTextbookCatalogByCondition(resTextbookCatalogCondition);
				
				
				/**????????????????????????*/
				List<Map<String, Object>> info = new ArrayList<Map<String, Object>>();
				/**??????????????????*/
				if(list.size()>0) {
					for (ResTextbookCatalog catalog : list) {
						Map<String, Object> temp = learningPlanBaseService.getCatalogInfo(catalog);
						Long size = learningPlanService.findTaskCountByCatalogCode(catalog.getCode(), userId);
						
						/**????????????????????????*/
						List<ResTextbookCatalog> catalogList = new ArrayList<ResTextbookCatalog>();
						resTextbookCatalogService.findChildren(catalog.getId(), catalogList);
						for (ResTextbookCatalog child : catalogList) {
							size+=learningPlanService.findTaskCountByCatalogCode(child.getCode(), userId);
						}
						
						temp.put("taskSize", size);
						info.add(temp);
					}
				}
				
				/**????????????????????????????????????*/
				Map<String, Object> child = learningPlanBaseService.getCatalogInfo(resTextbookCatalog);
				Long taskSize = learningPlanService.findTaskCountByCatalogCode(resTextbookCatalog.getCode(), userId);
				child.put("name", "??????");
				child.put("taskSize", taskSize);
				info.add(child);
				
				Long size = learningPlanService.findTaskCountByCatalogCode(resTextbookCatalog.getCode(), userId);
				map.put("taskSize", size);
				/**?????????????????????????????????*/
				map.put("children", info);
				
				returnList.add(map);
			}
			
			List<ResTextbookCatalog> top = resTextbookCatalogService.findResTextbookCatalogByTextbookIdAndLevel(textbookId, 0);
			if(top.size()>0) {
				ResTextbookCatalog resTextbookCatalog = top.get(0);
				/**???????????????????????????map???*/
				Map<String, Object> map = learningPlanBaseService.getCatalogInfo(resTextbookCatalog);
				Long size = learningPlanService.findTaskCountByCatalogCode(resTextbookCatalog.getCode(), userId);
				map.put("name", "??????");
				map.put("taskSize", size);
				Map<String, Object> child =  learningPlanBaseService.getCatalogInfo(resTextbookCatalog);
				child.put("taskSize", size);
				child.put("name", "??????");
				List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
				children.add(child);
				map.put("children", children);
				returnList.add(map);
			}
			
			return new ResponseVo<Object>("0",returnList);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}

	@Override
	public Object acitivityFileModify(String appKey, Integer activityFileId, String markedFileUuid) {
		TaskUserActivityFiles taskUserActivityFiles = this.taskUserActivityFilesService.findTaskUserActivityFilesById(activityFileId);
		if(taskUserActivityFiles != null) {
			taskUserActivityFiles.setMarkedFileUuid(markedFileUuid);
			taskUserActivityFilesService.modify(taskUserActivityFiles);
			Map<String, Boolean> map = new HashMap<String, Boolean>();
			map.put("success", true);
			return new ResponseVo<Object>("0",map);
		}
		
		ResponseInfo info = new ResponseInfo();
        info.setDetail("????????????????????????");
        info.setMsg("????????????");
        return new ResponseError("000001", info);
	}

}