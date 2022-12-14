package platform.education.rest.exam.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Page;
import platform.education.exam.model.Exam;
import platform.education.exam.model.ExamInterscore;
import platform.education.exam.model.ExamPublish;
import platform.education.exam.model.ExamPublishedRecord;
import platform.education.exam.model.ExamRelate;
import platform.education.exam.model.ExamUserRecord;
import platform.education.exam.service.ExamInterscoreService;
import platform.education.exam.service.ExamPrepareService;
import platform.education.exam.service.ExamPublishService;
import platform.education.exam.service.ExamPublishedRecordService;
import platform.education.exam.service.ExamRelateService;
import platform.education.exam.service.ExamService;
import platform.education.exam.service.ExamUserRecordService;
import platform.education.exam.vo.ExamInterscoreCondition;
import platform.education.exam.vo.ExamPublishVo;
import platform.education.exam.vo.ExamPublishedRecordCondition;
import platform.education.exam.vo.ExamRelateCondition;
import platform.education.exam.vo.ExamScoringTaskVo;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.Person;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.Student;
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
import platform.education.generalTeachingAffair.vo.PersonCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalcode.model.ResTextbookCatalog;
import platform.education.generalcode.service.ResTextbookCatalogService;
import platform.education.learningDesign.model.LearningPlanInterscore;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.model.LpTaskUser;
import platform.education.learningDesign.service.LearningPlanInterscoreService;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.vo.LearningPlanInterscoreCondition;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.model.PaperQuestion;
import platform.education.paper.model.QuestionJson;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.service.PaperQuestionService;
import platform.education.paper.service.PaperService;
import platform.education.paper.service.UserFileService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.service.UserQuestionService;
import platform.education.paper.service.UserWrongService;
import platform.education.paper.util.HtmlUtils;
import platform.education.paper.util.MqtPaperUtil;
import platform.education.paper.vo.PaQuestionVo;
import platform.education.paper.vo.UserFileCondition;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.paper.vo.UserPaperCorrectVo;
import platform.education.paper.vo.UserQuestionCondition;
import platform.education.resource.contants.ResourceType;
import platform.education.resource.model.CatalogResource;
import platform.education.resource.service.CatalogResourceService;
import platform.education.resource.service.ResourceHandlerService;
import platform.education.resource.service.ResourceService;
import platform.education.resource.vo.CatalogResourceCondition;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.exam.service.ExamRestService;
import platform.education.rest.learningPlan.service.LearningPlanBaseService;
import platform.education.rest.learningPlan.service.impl.LearningPlanBaseServiceImpl;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.model.User;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;
import platform.education.user.service.UserService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;

public class ExamRestServiceImpl implements ExamRestService {
	private static final Logger log = LoggerFactory.getLogger(ExamRestServiceImpl.class);

	@Autowired
	@Qualifier("examPublishedRecordService")
	private ExamPublishedRecordService examPublishedRecordService;
	@Autowired
	@Qualifier("examPublishService")
	private ExamPublishService examPublishService;
	@Autowired
	@Qualifier("examService")
	private ExamService examService;
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	@Autowired
	@Qualifier("examUserRecordService")
	private ExamUserRecordService examUserRecordService;
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
	@Qualifier("examRelateService")
	private ExamRelateService examRelateService;

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
	@Qualifier("papaperService")
	private PaperService paperService;

	@Autowired
	@Qualifier("paperQuestionService")
	private PaperQuestionService paperQuestionService;
	@Autowired
	@Qualifier("examInterscoreService")
	private ExamInterscoreService examInterscoreService;

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
	@Qualifier("examPrepareService")
	private ExamPrepareService examPrepareService;
	
	@Autowired
	@Qualifier("teamUserService")
	private TeamUserService teamUserService;
	
	@Autowired
    @Qualifier("learningPlanBaseService")
	private LearningPlanBaseService learningPlanBaseService;
	
	@Autowired
    @Qualifier("learningPlanInterscoreService")
	private LearningPlanInterscoreService learningPlanInterscoreService;
	
	@Autowired
    @Qualifier("lpTaskExamUnitService")
	private LpTaskExamUnitService lpTaskExamUnitService;
	
	@Autowired
    @Qualifier("paQuestionService")
	private PaQuestionService paQuestionService;

	@Override
	public Object unitFinsh(Integer userId, String appKey, Integer taskId, Integer examId, String finishTime) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;

		try {
			date = sdf.parse(finishTime);
//			Exam em = this.examService.findExamById(examId);
			ExamPublish ep = this.examPublishService.findExamPublishById(taskId);
			if (ep == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("????????????????????????,?????????");
				info.setMsg("???????????????????????? examId,taskId");
				info.setParam("examId,taskId");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			// ExamUserRecord ur = this.examUserRecordService.getUniqueRecord(
			// userId, em.getUuid(), ep.getUuid());
			// if (ur != null) {
			// ur.setModifyDate(new Date());
			// ur.setPlayTime(ur.getPlayTime() + 1);
			// ur.setEntityId(em.getEntityId());
			// ur.setFinishedFlag(1);
			// this.examUserRecordService.modify(ur);
			// } else {
			// ExamUserRecord eur = new ExamUserRecord();
			// eur = new ExamUserRecord();
			// eur.setCreateDate(new Date());
			// eur.setModifyDate(new Date());
			// eur.setMicroId(em.getUuid());
			// eur.setUserId(userId);
			// TeamStudent ts = new TeamStudent();
			// TeamStudentCondition tsc = new TeamStudentCondition();
			// tsc.setUserId(userId);
			// ts = this.teamStudentService.findTeamStudentByCondition(tsc,
			// null, null).get(0);
			// eur.setUserName(ts.getName());
			// eur.setPlayTime(1);
			// eur.setEntityId(em.getEntityId());
			// eur.setPublishLessonId(ep.getUuid());
			// this.examUserRecordService.add(eur);
			// }
			ExamPublishedRecordCondition condition = new ExamPublishedRecordCondition();
			condition.setUserId(userId);
			condition.setPublishedMicroId(ep.getUuid());
			ExamPublishedRecord epr = this.examPublishedRecordService.findExamPublishedRecordByCondition(condition).get(0);
			this.examPublishedRecordService.modifyByflag(epr.getId(), 1, date);
			return new ResponseVo<Object>("0", true);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}

	@Override
	public Object examListBySubject(Integer userId, String appKey, Integer pageIndex, Integer pageSize, String subjectCode) throws ParseException {

		AppEdition app = this.appEditionService.findByAppKey(appKey);
		Integer status = 0;
		// Integer isCheck=0;
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (userId == null) {
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);// ???????????????
		}
		// Student s=this.studentService.findStudentByUserId(userId);
		Page page = new Page();
		page.setCurrentPage(pageIndex);
		page.setPageSize(pageSize);
		page.setEnableGetTolaRows(false);
		List<Object> taskInfoList = new ArrayList<Object>();
		/** ??????????????? */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		/** ??????????????? */
		String preTime = "";
		/** ???????????????????????? */
		Map<String, Object> taskMap = new HashMap<String, Object>();
		/** ?????????????????????????????? */
		List<Map<String, Object>> userTaskList = new ArrayList<Map<String, Object>>();
		/** ??????????????????????????????????????? */
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
		// long start = System.currentTimeMillis();
		List<ExamPublishVo> recordList = this.examPublishService.findExamOfPublishByUserId(userId, subjectCode, page, null);
		// long end = System.currentTimeMillis();
		// System.out.println("????????????"+(end-start)+"??????");

		if (recordList == null || recordList.size() == 0) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		if (recordList.size() > 0 && recordList.get(0).getStartDate() == null) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		Date date = new Date();
		List<String> uuidList = new ArrayList<String>();
		Integer[] ownerIds = new Integer[recordList.size()];
		Map<Integer, Object> imgMap = new HashMap<Integer, Object>();
		int j = 0;
		for (ExamPublishVo epv : recordList) {
			uuidList.add(epv.getEntityId());
			ownerIds[j] = epv.getId();
			j++;
		}
		imgMap = findUserFileMap(userId, ownerIds);
		String[] uuids = new String[uuidList.size()];
		uuids = uuidList.toArray(uuids);
		Map<String, FileResult> resultMap = new HashMap<String, FileResult>();
		List<Subject> slist = subjectService.findAllSubjectName();
		for (int i = 0; i < recordList.size(); i++) {
			ExamPublishVo ep = recordList.get(i);
			if (ep.getStartDate() != null) {
				if (date.before(ep.getStartDate())) {
					status = 0;
				} else if (date.after(ep.getStartDate()) && date.before(ep.getFinishedDate())) {
					status = 1;
				} else {
					status = 2;
				}
				taskMap.put("startTime", formatH.format(ep.getStartDate()));
				taskMap.put("finishTime", formatH.format(ep.getFinishedDate()));
				taskMap.put("status", status);
				taskMap.put("isCheck", ep.getIsCheck());

				JSONArray ja = JSONArray.fromObject(ep.getRealMicroList());
				if (i == 0) {
					/** ??????????????????????????? */
					preTime = format.format(ep.getStartDate());
				}
				String subjectName = "";
				subjectCode = "";
				if (ja.size() > 0) {
					JSONObject job = ja.getJSONObject(0);
					String id = (String) job.get("id");
					if (id != null && !id.equals("")) {
						if (ep.getSubjectCode() != null && !ep.getSubjectCode().equals("")) {
							subjectCode = ep.getSubjectCode();
							for (Subject s1 : slist) {
								if (s1.getCode().equals(subjectCode)) {
									subjectName = s1.getName();
									break;
								}
							}
						}
					}
					taskMap.put("examType", ep.getType());
					taskMap.put("taskId", ep.getId());
					taskMap.put("examId", ep.getExamId());
					taskMap.put("subjectCode", subjectCode);
					taskMap.put("subjectName", subjectName);
					taskMap.put("publisherName", ep.getPublisherName());
					taskMap.put("publishedFlag", ep.getFinishedFlag());
					taskMap.put("imgMax", 9);
					if (resultMap.size() <= 0) {
						resultMap = this.fileService.findFileByUUIDs(uuids);
						taskMap.put("url", resultMap.get(ep.getEntityId()).getHttpUrl());
					} else {
						taskMap.put("url", resultMap.get(ep.getEntityId()).getHttpUrl());
					}
					taskMap.put("name", ep.getTitle());

					/** ????????????????????? */
					List<String> list = new ArrayList<String>();
					list = (List<String>) imgMap.get(ep.getId());
					if (list == null) {
						list = new ArrayList<String>();
					}
					taskMap.put("userFileList", list);

				}
				/** ????????????????????????????????????????????????????????? */

				if (preTime.equals(format.format(ep.getStartDate()))) {
					/** ??????????????????????????????????????????????????? */
					userTaskList.add(taskMap);
					/** ???????????????map???????????????????????????????????? */
					taskMap = new HashMap<String, Object>();
					/** ????????????????????????????????????????????? */
					if (i == recordList.size() - 1) {
						/** ????????????????????????????????????list??? */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("question", userTaskList);
						/** ?????????????????????????????? */
						taskInfoList.add(timeAndUserTaskMap);
					}
				} else {
					/** ????????????????????? */
					timeAndUserTaskMap.put("time", preTime);
					timeAndUserTaskMap.put("question", userTaskList);
					/** ????????????????????????????????????????????????????????? */
					taskInfoList.add(timeAndUserTaskMap);

					/** ????????????????????????map,???????????????????????????????????? */
					timeAndUserTaskMap = new HashMap<String, Object>();
					/** ???????????????????????????,???????????????????????? */
					userTaskList = new ArrayList<Map<String, Object>>();
					userTaskList.add(taskMap);

					/** ???????????????????????????????????? */
					preTime = format.format(ep.getStartDate());

					/** ??????????????????????????? */
					if (i == recordList.size() - 1) {
						/** ?????????????????????????????????????????? */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("question", userTaskList);
						taskInfoList.add(timeAndUserTaskMap);
					} else {
						/** ??????????????????, ????????????????????????????????????????????????????????? */
						taskMap = new HashMap<String, Object>();
					}
				}
			}

		}
		if (taskInfoList.size() == 0) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		List<Object> sortList = new ArrayList<Object>();
		for (int i = 0; i < taskInfoList.size(); i++) {
			Map<String, Object> frist = new HashMap<String, Object>();
			frist = (Map) taskInfoList.get(i);
			List<Map<String, Object>> second = new ArrayList<Map<String, Object>>();
			timeAndUserTaskMap = new HashMap<String, Object>();
			second = (List) frist.get("question");
			Collections.reverse(second);
			timeAndUserTaskMap.put("question", second);
			sortList.add(frist);

		}
		return new ResponseVo<Object>("0", sortList);
	}

	public Object getExam(Integer userId, String appKey, Integer catalogId) {
		if (appKey == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (userId == null) {
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);// ???????????????
		}
		if (catalogId == null) {
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);// ???????????????
		}
		List<Map<String, Object>> examlist = new ArrayList<Map<String, Object>>();
		ResTextbookCatalog resTextbookCatalog = resTextbookCatalogService.findResTextbookCatalogById(catalogId);
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String subjectName = "";
		String subjectCode = "";
		if (resTextbookCatalog == null) {
			return null;
		}
		/** ????????????????????????????????? */
		List<ResTextbookCatalog> list = new ArrayList<ResTextbookCatalog>();
		list.add(resTextbookCatalog);
		/** ?????????????????????????????? */
		resTextbookCatalogService.findChildren(resTextbookCatalog.getId(), list);

		String codes[] = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			codes[i] = list.get(i).getCode();
		}

		/** ????????????????????????????????????????????? */

		List<CatalogResource> catalogResourceList = catalogResourceService.findCatalogResourceByCatalogCodes(codes, ResourceType.EXAM);
		List<String> uuid = new ArrayList<String>();
		for (CatalogResource cr : catalogResourceList) {
			if (cr.getObjectId() != null) {
				uuid.add(cr.getObjectId());
			}
		}
		String[] uuids = uuid.toArray(new String[uuid.size()]);
		List<ExamPublishVo> lists = examPublishService.findExamOfPublishByUserIdAndUuids(userId, uuids, null, null);
		for (ExamPublishVo ep : lists) {
			Map<String, Object> taskMap = new HashMap<String, Object>();
			taskMap.put("startTime", formatH.format(ep.getStartDate()));
			taskMap.put("finishTime", formatH.format(ep.getFinishedDate()));
			// taskMap.put("status", ep.get);
			JSONArray ja = JSONArray.fromObject(ep.getRealMicroList());
			if (ja.size() > 0) {
				JSONObject job = ja.getJSONObject(0);
				String resId = (String) job.get("resourceId");
				if (resId != null && !resId.equals("")) {
					CatalogResourceCondition condition = new CatalogResourceCondition();
					condition.setResourceId(Integer.valueOf(resId));
					CatalogResource catalogResource = this.catalogResourceService.findCatalogResourceByCondition(condition).get(0);
					if (catalogResource != null) {
						subjectCode = catalogResource.getSubjectCode();
						subjectName = catalogResource.getSubjectName();
						String catalogCode = resourceHandlerService.getResourceCatalogCode(catalogResource.getResourceId());
						String catalogName = resTextbookCatalogService.getFullNameByCode(catalogCode, " ");
						taskMap.put("catalogName", catalogName);
					}
				}
			}
			taskMap.put("status", ep.getIsFinished());
			taskMap.put("isCheck", ep.getIsCheck());
			taskMap.put("examType", ep.getType());
			taskMap.put("taskId", ep.getId());
			taskMap.put("examId", ep.getExamId());
			taskMap.put("subjectCode", subjectCode);
			taskMap.put("subjectName", subjectName);
			taskMap.put("publisherName", ep.getPublisherName());
			taskMap.put("url", fileService.relativePath2HttpUrlByUUID(ep.getEntityId()));
			taskMap.put("name", ep.getTitle());
			examlist.add(taskMap);
		}

		return new ResponseVo<Object>("0", examlist);
	}

	@Override
	public Object examList(Integer userId, String appKey, Integer currentPage, Integer index) throws ParseException {
		String subjectCode = "";
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		Integer status = 0;
		Integer isCheck = 0;
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (userId == null) {
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, null);// ???????????????
		}

		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setPageSize(index);
		page.setEnableGetTolaRows(false);
		List<Object> taskInfoList = new ArrayList<Object>();
		/** ??????????????? */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		/** ??????????????? */
		String preTime = "";
		/** ???????????????????????? */
		Map<String, Object> taskMap = new HashMap<String, Object>();
		/** ?????????????????????????????? */
		List<Map<String, Object>> userTaskList = new ArrayList<Map<String, Object>>();
		/** ??????????????????????????????????????? */
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
		ExamPublishedRecordCondition examCondition = new ExamPublishedRecordCondition();
		examCondition.setUserId(userId);
		/**
		 * ????????????id????????????????????????????????????
		 */
		List<ExamPublishVo> recordList = this.examPublishService.findExamOfPublishByUserId(userId, subjectCode, page, null);
		if (recordList == null || recordList.size() == 0) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		if (recordList.size() > 0 && recordList.get(0).getStartDate() == null) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		Student s = this.studentService.findStudentByUserId(userId);
		Date date = new Date();
		/*
		 * ????????????????????????????????????????????????????????????????????????????????????
		 */
		for (int i = 0; i < recordList.size(); i++) {
			ExamPublishVo ep = recordList.get(i);
			if (ep.getStartDate() != null) {
				if (date.before(ep.getStartDate())) {
					status = 0;
				} else if (date.after(ep.getStartDate()) && date.before(ep.getFinishedDate())) {
					status = 1;
				} else {
					status = 2;
				}
				taskMap.put("startTime", formatH.format(ep.getStartDate()));
				taskMap.put("finishTime", formatH.format(ep.getFinishedDate()));
				taskMap.put("status", status);
				taskMap.put("isCheck", ep.getIsCheck());
				JSONArray ja = JSONArray.fromObject(ep.getRealMicroList());
				if (i == 0) {
					/** ??????????????????????????? */
					preTime = format.format(ep.getStartDate());
				}
				String subjectName = "";
				subjectCode = "";
				if (ja.size() > 0) {
					JSONObject job = ja.getJSONObject(0);
					String id = (String) job.get("id");
					if (id != null && !id.equals("")) {

						if (ep.getSubjectCode() != null && !ep.getSubjectCode().equals("")) {
							subjectCode = ep.getSubjectCode();
							if (s != null) {
								subjectName = this.subjectService.findUnique(s.getSchoolId(), subjectCode).getName();
							}
						}
					}
					taskMap.put("examType", ep.getType());
					taskMap.put("taskId", ep.getId());
					taskMap.put("examId", ep.getExamId());
					taskMap.put("subjectCode", subjectCode);
					taskMap.put("subjectName", subjectName);
					taskMap.put("publisherName", ep.getPublisherName());
					taskMap.put("url", fileService.relativePath2HttpUrlByUUID(ep.getEntityId()));
					taskMap.put("name", ep.getTitle());
				}
				/** ????????????????????????????????????????????????????????? */

				if (preTime.equals(format.format(ep.getStartDate()))) {
					/** ??????????????????????????????????????????????????? */
					userTaskList.add(taskMap);
					/** ???????????????map???????????????????????????????????? */
					taskMap = new HashMap<String, Object>();
					/** ????????????????????????????????????????????? */
					if (i == recordList.size() - 1) {
						/** ????????????????????????????????????list??? */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("question", userTaskList);
						/** ?????????????????????????????? */
						taskInfoList.add(timeAndUserTaskMap);
					}
				} else {
					/** ????????????????????? */
					timeAndUserTaskMap.put("time", preTime);
					timeAndUserTaskMap.put("question", userTaskList);
					/** ????????????????????????????????????????????????????????? */
					taskInfoList.add(timeAndUserTaskMap);

					/** ????????????????????????map,???????????????????????????????????? */
					timeAndUserTaskMap = new HashMap<String, Object>();
					/** ???????????????????????????,???????????????????????? */
					userTaskList = new ArrayList<Map<String, Object>>();
					userTaskList.add(taskMap);

					/** ???????????????????????????????????? */
					preTime = format.format(ep.getStartDate());

					/** ??????????????????????????? */
					if (i == recordList.size() - 1) {
						/** ?????????????????????????????????????????? */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("question", userTaskList);
						taskInfoList.add(timeAndUserTaskMap);
					} else {
						/** ??????????????????, ????????????????????????????????????????????????????????? */
						taskMap = new HashMap<String, Object>();
					}
				}
			}

		}
		if (taskInfoList.size() == 0) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());
		}
		/**
		 * ????????????????????????
		 */
		List<Object> sortList = new ArrayList<Object>();
		for (int i = 0; i < taskInfoList.size(); i++) {
			Map<String, Object> frist = new HashMap<String, Object>();
			frist = (Map) taskInfoList.get(i);
			List<Map<String, Object>> second = new ArrayList<Map<String, Object>>();
			timeAndUserTaskMap = new HashMap<String, Object>();
			second = (List) frist.get("question");
			Collections.reverse(second);
			timeAndUserTaskMap.put("question", second);
			sortList.add(frist);

		}
		return new ResponseVo<Object>("0", sortList);
	}

	@Override
	public Object unitFinsh1(Integer userId, String appKey, Integer taskId, Integer examId, String finishTime) {
		return this.unitFinsh(userId, appKey, taskId, examId, finishTime);
	}

	@Override
	public Object getCorrectListByTeam(Integer taskId, String appKey, Integer teamId) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if (taskId == null || teamId == null) {
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
			}
			SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			/** ???????????????????????? */
			ExamPublish examPublish = examPublishService.findExamPublishById(taskId);
			if (examPublish == null) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}
			/** ??????????????????????????? */
			ExamRelateCondition examRelateCondition = new ExamRelateCondition();
			examRelateCondition.setRelateId(teamId);
			examRelateCondition.setPublishMicroLessonId(examPublish.getUuid());
			List<ExamRelate> examRelateList = examRelateService.findExamRelateByCondition(examRelateCondition);

			if (examRelateList.size() == 0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}
			/** ?????????????????? */
			PjExam pjExam = pjExamService.findPjExamById(examRelateList.get(0).getPjExamId());

			if (pjExam == null) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}

			/** ???????????????????????????????????? */
			ExamStudentCondition examStudentCondition = new ExamStudentCondition();
			examStudentCondition.setExamId(pjExam.getId());
			List<ExamStudent> examStudentList = examStudentService.findExamStudentByCondition(examStudentCondition);

			if (examStudentList.size() == 0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}

			/** ??????id????????? */
			Integer[] userIds = new Integer[examStudentList.size()];
			for (int i = 0; i < examStudentList.size(); i++) {
				userIds[i] = examStudentList.get(i).getUserId();
			}

			/** ????????????????????? */
			List<User> userList = userService.findUserByIds(userIds);

			/** ??????Person?????????(?????????????????????name) map???key???userId, value???person */
			Map<Integer, Person> personMap = getPersons(userList);

			/** ???????????????????????? */
			UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setTeamId(teamId);
			userPaperCondition.setOwnerId(taskId);
			List<UserPaper> userPaperList = userPaperService.findUserPaperByCondition(userPaperCondition);

			/** userPaperMap??????userId???key, userPeper???value */
			Map<Integer, UserPaper> userPaperMap = new HashMap<Integer, UserPaper>();
			for (UserPaper userPaper : userPaperList) {
				userPaperMap.put(userPaper.getUserId(), userPaper);
			}

			/** ?????????????????? */
			List<Map<String, Object>> examineList = new ArrayList<Map<String, Object>>();
			/** ??????????????? */
			List<Map<String, Object>> scoreFinishedList = new ArrayList<Map<String, Object>>();
			/** ??????????????? */
			List<Map<String, Object>> scoreunFinishList = new ArrayList<Map<String, Object>>();
			for (ExamStudent examStudent : examStudentList) {
				Map<String, Object> map = new HashMap<String, Object>();
				Person person = personMap.get(examStudent.getUserId());
				/** ?????????????????? */
				String imgPath = ImgUtil.getImgSrc(examStudent.getUserId(), profileService);

				map.put("studentId", person.getId());
				map.put("studentName", examStudent.getName());
				map.put("userId", examStudent.getUserId());
				map.put("iconUrl", imgPath);

				/** score???-1????????? */
				/*
				 * if(examStudent.getScore()-0 == -1) { examineList.add(map); }
				 * else {
				 */
				/** ??????userId??????userPaper */
				UserPaper userPaper = userPaperMap.get(examStudent.getUserId());
				if (userPaper != null) {
					/** ???????????? */
					map.put("score", userPaper.getScore());
					/** ?????????????????? */
					if (userPaper.getScoreFinished()) {
						/** ????????? */
						scoreFinishedList.add(map);
					} else {
						/** ????????? */
						scoreunFinishList.add(map);
					}
				} else {
					/** ?????? */
					examineList.add(map);
				}
				/* } */
			}
			String startTime = "";
			String finishTime = "";
			Integer isInterscore = 0;
			if (examRelateList.get(0).getInterscoreStartTime() != null) {
				startTime = formatH.format(examRelateList.get(0).getInterscoreStartTime());
			}

			if (examRelateList.get(0).getInterscoreFinishTime() != null) {
				finishTime = formatH.format(examRelateList.get(0).getInterscoreFinishTime());
			}
			if (examRelateList.get(0).getIsInterscoring() != null && examRelateList.get(0).getIsInterscoring()) {
				isInterscore = 1;
			}

			ExamInterscoreCondition examInterscoreCondition = new ExamInterscoreCondition();
			examInterscoreCondition.setExamPublishId(examRelateList.get(0).getPublishMicroLessonId());
			examInterscoreCondition.setTeamId(teamId);
			List<ExamInterscore> examInterscoreList = examInterscoreService.findExamInterscoreByCondition(examInterscoreCondition);

			int interscoredCount = 0;

			for (ExamInterscore examInterscore : examInterscoreList) {
				if (examInterscore.getScoringTime() != null) {
					interscoredCount++;
				}
			}

			/** ??????????????????????????? */
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
			info.setDetail("??????????????????????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}

	@Override
	public Object getCorrectListByLearner(Integer taskId, String appKey, Integer userId, Integer unitId) {
		try {
			String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if (taskId == null || userId == null) {
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
			}

			/** ???????????????????????? */
			List<UserPaperCorrectVo> userPaperCorrectVoList = userPaperService.findUserPaperCorrectByTaskId(taskId, unitId, userId, null, null);
			
			// ??????????????????
			if (userPaperCorrectVoList != null && userPaperCorrectVoList.size() > 0) {
				for (UserPaperCorrectVo userPaperCorrectVo : userPaperCorrectVoList) {

					userPaperCorrectVo.setAnswer(MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbAnswer(), domain));
					userPaperCorrectVo.setCorrectAnswer(MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbCorrectAnswer(), domain));
					// System.out.println(userPaperCorrectVo.getCorrectAnswer());
				}
			}

			/** ???????????????????????? */
//			UserPaperCondition userPaperCondition = new UserPaperCondition();
//			userPaperCondition.setOwnerId(taskId);
//			userPaperCondition.setUserId(userId);
//			userPaperCondition.setObjectId(unitId);
//			List<UserPaper> userPaperList = userPaperService.findUserPaperByCondition(userPaperCondition);
//			if (userPaperList.size() == 0) {
//				return new ResponseVo<Object>("0", new HashMap<String, Object>());// ???????????????
//			}
//
//			UserPaper userPaper = userPaperList.get(0);
//
//			if (userPaper == null) {
//				return new ResponseVo<Object>("0", new HashMap<String, Object>());// ???????????????
//			}

			List<Map<String, Object>> userPaperlist = new ArrayList<Map<String, Object>>(userPaperCorrectVoList.size());
			for (UserPaperCorrectVo userPaperCorrectVo : userPaperCorrectVoList) {
				Map<String, Object> userPaperMap = new HashMap<String, Object>();
				userPaperMap.put("questionType", userPaperCorrectVo.getQuestionType());
				//userPaperMap.put("questionUuid", userPaperCorrectVo.getQuestionUuid());
				userPaperMap.put("questionUuid", "bd09ab02-f08c-4c5a-a361-686d523ebbe9");
				userPaperMap.put("pos", userPaperCorrectVo.getPos());
				userPaperMap.put("correctAnswer", userPaperCorrectVo.getCorrectAnswer());
				//userPaperMap.put("answer", userPaperCorrectVo.getAnswer());
				userPaperMap.put("answer", new String[]{"A","B"});
				userPaperMap.put("correctScore", userPaperCorrectVo.getCorrectScore());
				//userPaperMap.put("score", userPaperCorrectVo.getScore());
				userPaperMap.put("score", 5);
				List<Map<String, Object>> files = this.getUserFile(userPaperCorrectVo.getUserQuestionUuId());
				userPaperMap.put("files", files);
				userPaperlist.add(userPaperMap);
			}

			int scoredUserId = 0;

//			if (unitId == null) {
//				ExamInterscoreCondition examInterscoreCondition = new ExamInterscoreCondition();
//				examInterscoreCondition.setScoredPaperId(userPaper.getId());
//				List<ExamInterscore> examInterscoreList = examInterscoreService.findExamInterscoreByCondition(examInterscoreCondition);
//				if (examInterscoreList.size() > 0) {
//					ExamInterscore examInterscore = examInterscoreList.get(0);
//					scoredUserId = examInterscore.getScoredUserId();
//				}
//			} else {
//				LearningPlanInterscoreCondition condition = new LearningPlanInterscoreCondition();
//				condition.setScoredPaperId(userPaper.getId());
//				List<LearningPlanInterscore> list = learningPlanInterscoreService.findLearningPlanInterscoreByCondition(condition);
//				if (list.size() > 0) {
//					LearningPlanInterscore learningPlanInterscore = list.get(0);
//					scoredUserId = learningPlanInterscore.getScoredUserId();
//				}
//			}

			/** ??????????????????????????? */
			Map<String, Object> result = new HashMap<String, Object>();

			result.put("interScoreUser", "???");
			if (scoredUserId != 0) {
				User user = userService.findUserById(scoredUserId);
				Person person = personService.findPersonById(user.getPersonId());
				if (person != null) {
					result.put("interScoreUser", person.getName());
				}
			}
			
			result.put("answers", userPaperlist);
			
			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}

	private List<Map<String, Object>> getUserFile(String userQuestionUuId) {
		String[] questionUuIds = new String[]{userQuestionUuId};
		
		List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(questionUuIds);
		
		List<Map<String, Object>> files = new ArrayList<Map<String, Object>>(userFileList.size());
		/** ??????????????????????????? */
		for (UserFile userFile : userFileList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("fileId", userFile.getId());
			/** ???????????? */
			FileResult result = fileService.findFileByUUID(userFile.getSourceFileUuid());
			if (result != null) {
				/** ???????????????????????????uuid */
				map.put("sourceFileUuid", userFile.getSourceFileUuid());
				if (result.getEntityFile() != null) {
					/** ?????????md5 */
					map.put("sourceFileMd5", result.getEntityFile().getMd5());
				}
				/** ????????????????????? */
				map.put("sourceFileUrl", result.getHttpUrl());
			}
			result = fileService.findFileByUUID(userFile.getMarkedFileUuid());
			if (result != null) {
				/** ??????????????????(????????????) */
				map.put("markedFileUuid", userFile.getMarkedFileUuid());
				/** ????????????md5 */
				if (result.getEntityFile() != null) {
					map.put("markedFileMd5", result.getEntityFile().getMd5());
				}
				/** ???????????????????????? */
				map.put("markedFileUrl", result.getHttpUrl());
			}
			files.add(map);
		}
		return files;
	}

	@Override
	public Object taskCorrectFinish(String appKey, Integer taskId, Integer userId, String userType, String data) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if (app == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			if (taskId == null || userId == null || data == null) {
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
			}

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

			/** ?????????????????????????????? */
			Object scoreFinish = info.get("scoreFinished");
			Boolean scoreFinished = false;
			if (scoreFinish != null) {
				if (scoreFinish instanceof Boolean) {
					scoreFinished = (Boolean) scoreFinish;
				} else if (scoreFinish instanceof Integer) {
					scoreFinished = ((Integer) scoreFinish == 1 ? true : false);
				}
			}

			/** ????????????????????? */
			UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setOwnerId(taskId);
			userPaperCondition.setUserId(userId);
			List<UserPaper> userPaperList = userPaperService.findUserPaperByCondition(userPaperCondition);

			if (userPaperList.size() == 0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}
			UserPaper userPaper = userPaperList.get(0);

			/** ???????????????????????? */
			ExamPublish examPublish = examPublishService.findExamPublishById(taskId);
			if (examPublish == null) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}

			/** ????????????id */
			Integer teamId = userPaper.getTeamId();

			/** ??????????????????????????? */
			ExamRelateCondition examRelateCondition = new ExamRelateCondition();
			examRelateCondition.setRelateId(teamId);
			examRelateCondition.setPublishMicroLessonId(examPublish.getUuid());
			List<ExamRelate> examRelateList = examRelateService.findExamRelateByCondition(examRelateCondition);

			if (examRelateList.size() == 0) {
				return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
			}

			ExamRelate examRelate = examRelateList.get(0);

			if ("student".equals(userType)) {
				ExamInterscoreCondition examInterscoreCondition = new ExamInterscoreCondition();
				examInterscoreCondition.setScoredPaperId(userPaper.getId());
				List<ExamInterscore> examInterscoreList = examInterscoreService.findExamInterscoreByCondition(examInterscoreCondition);

				Date currentDate = new Date(System.currentTimeMillis());
				ResponseInfo responseInfo = new ResponseInfo();
				if (!examRelate.getIsInterscoring()) {
					responseInfo.setDetail("?????????????????????????????????");
					responseInfo.setMsg("?????????????????????????????????");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
				if (currentDate.before(examRelate.getInterscoreStartTime())) {
					responseInfo.setDetail("??????????????????");
					responseInfo.setMsg("??????????????????");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}
				if (currentDate.after(examRelate.getInterscoreFinishTime())) {
					responseInfo.setDetail("??????????????????");
					responseInfo.setMsg("??????????????????");
					return new ResponseError(CommonCode.S$INVALID_DATA, responseInfo);
				}

				ExamInterscore examInterscore = examInterscoreList.get(0);

				if (examInterscore != null) {
					if (scoreFinished) {
						examInterscore.setScoringTime(new Date());
					} else {
						examInterscore.setScoringTime(null);
					}
					examInterscoreService.modifyScoringTime(examInterscore);
				}

			}

			/** ????????????????????????????????? */
			double totalScore = 0.0;

			String[] questionUUIDs = new String[questions.size()];
			for (int i = 0; i < questions.size(); i++) {
				questionUUIDs[i] = questions.getJSONObject(i).getString("questionUuid");
			}

			List<PaperQuestion> paperQuestions = this.paperQuestionService.findPaperQuestionByUUIDs(questionUUIDs);

			Map<String, UserQuestion> userQuestionsMap = this.userQuestionService.findMapByOwnerIdAndUserId(taskId, userId, 0);

			/** ?????????????????????????????? */
			for (int i = 0; i < paperQuestions.size(); i++) {
				PaperQuestion paperQuestion = paperQuestions.get(i);

				double score = questionScoreMap.get(paperQuestion.getQuestionUuid());

				UserQuestion userQuestion = userQuestionsMap.get(paperQuestion.getQuestionUuid());

				if (userQuestion != null) {
					totalScore = totalScore + score - userQuestion.getScore();
					userQuestion.setScore(score);
					/** ???????????????????????????????????? */
					if (paperQuestion.getScore() - score == 0) {
						userQuestion.setIsCorrect(true);
					} else {
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

			/** ??????????????????????????? */
			userPaper.setScore(userPaper.getScore() + totalScore);
			userPaper.setScoreTime(new Date());
			/** ?????????????????? */
			if (scoreFinished != null) {
				userPaper.setScoreFinished(scoreFinished);
			}
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
					if(file.has("markedFileUuid")) {
						String markedFileUuid = file.getString("markedFileUuid");
						/** ?????????????????? */
						userFile.setMarkedFileUuid(markedFileUuid);
						userFile.setModifyDate(new Date());
						/** ???????????? */
						userFiles.add(userFile);
					}
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
				ExamStat examStat = examStatService.findExamStatByExamId(examRelate.getPjExamId());
				if (examStat != null) {
					/** ????????????data_chage */
					examStat.setDataChanged(true);
					examStatService.modify(examStat);
				}
			}

			Map<String, Object> result = new HashMap<String, Object>();

			return new ResponseVo<Object>("0", result);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("????????????????????????");
			info.setMsg("????????????");
			return new ResponseError("000001", info);
		}
	}

	/**
	 * ??????Person???????????? ???useId???id, Person???value???map??????
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

	@Override
	public Object wrong(String appKey, Integer taskId, Integer teamId) {
		if (taskId == null || appKey == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
		}

		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> teamList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> gradeList = new ArrayList<Map<String, Object>>();
		List<Integer> gradeIds = new ArrayList<Integer>();
		ExamPublish ep = examPublishService.findExamPublishById(taskId);
		if (ep != null) {
			ExamRelateCondition erc = new ExamRelateCondition();
			erc.setPublishMicroLessonId(ep.getUuid());
			List<ExamRelate> erlist = examRelateService.findExamRelateByCondition(erc);
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
				ExamRelateCondition erc1 = new ExamRelateCondition();
				erc1.setPublishMicroLessonId(ep.getUuid());
				erc1.setRelateId(teamId);
				List<ExamRelate> erlist1 = examRelateService.findExamRelateByCondition(erc1);
				if (erlist1 != null && erlist1.size() > 0) {
					ExamRelate er = erlist1.get(0);
					Integer[] tIds = { er.getPjExamId() };
					List<ExamQuestionWrongVo> vos1 = pjExamService.findExamQuestionWrongbyExamId(tIds, 1, 0.85f);
					if (vos1 != null && vos1.size() > 0) {
						teamList = copy(vos1, 1);
					}
				}
			} else {
				teamList = gradeList;
			}
		}
		map.put("teamList", teamList);
		map.put("gradeList", gradeList);
		return new ResponseVo<Object>("0", map);
	}

	private List<Map<String, Object>> copy(List<ExamQuestionWrongVo> list, Integer type) {
		String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
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
			map.put("paperAnswer", MqtPaperUtil.StringToArray(vo.getAnswer(), domain));
			map.put("correctAnswer", MqtPaperUtil.StringToArray(vo.getCorrectAnswer(), domain));
			map.put("explanation", MqtPaperUtil.replaceDomain(vo.getExplanation(), domain));
			map.put("content", MqtPaperUtil.replaceDomain(vo.getContent(), domain));
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

	private String findSubjectNameByCode(String code) {

		List<Subject> slist = subjectService.findAllSubjectName();
		for (Subject s : slist) {
			if (s.getCode().equals(code)) {
				return s.getName();
			}
		}
		return "";
	}

	@Override
	public Object interscoreStart(String appKey, Integer taskId, Integer teamId, String startTime, String finishTime, Integer isInterscore) throws ParseException {
		if (taskId == null || appKey == null || teamId == null || startTime == null || finishTime == null || isInterscore == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
		}
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		boolean on = false;
		if (isInterscore == 1) {
			on = true;
		}
		ExamPublish ep = this.examPublishService.findExamPublishById(taskId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (sdf.parse(startTime).after(sdf.parse(finishTime))) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("??????????????????????????????,?????????");
			info.setMsg("??????????????????????????????");
			info.setParam("startTime,finishTime");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		if (ep != null) {
			ExamRelateCondition ec = new ExamRelateCondition();
			ec.setPublishMicroLessonId(ep.getUuid());
			ec.setRelateId(teamId);
			List<ExamRelate> erlist = this.examRelateService.findExamRelateByCondition(ec);
			if (erlist != null && erlist.size() > 0) {
				ExamRelate er = erlist.get(0);
				if (on) {
					if (sdf.parse(finishTime) != null && sdf.parse(finishTime).before(new Date())) {
						ResponseInfo info = new ResponseInfo();
						info.setDetail("????????????????????????????????????");
						info.setMsg("????????????????????????????????????");
						info.setParam("finishTime");
						return new ResponseError(CommonCode.S$INVALID_DATA, info);
					}
					if (er.getInterscoreStartTime() == null) {
						er.setInterscoreStartTime(sdf.parse(startTime));
					}
					er.setInterscoreFinishTime(sdf.parse(finishTime));
				}
				er.setIsInterscoring(on);
				UserPaperCondition upc1 = new UserPaperCondition();
				upc1.setOwnerId(taskId);
				upc1.setType(2);
				upc1.setTeamId(teamId);
				List<UserPaper> uplist = this.userPaperService.findUserPaperByCondition(upc1);
				if (uplist == null || uplist.size() <= 1) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("????????????????????????2,????????????");
					info.setMsg("????????????????????????2,????????????");
					info.setParam("taskId,teamId");
					return new ResponseError(CommonCode.S$INVALID_DATA, info);
				}
				this.examRelateService.modify(er);
				InitExamInterscore(ep.getId(), ep.getUuid(), teamId, PaperType.EXAM);

			}

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		return new ResponseVo<Object>("0", map);
	}

	@Override
	public Object interscoreListBySubject(Integer userId, String appKey, Integer pageNumber, Integer pageSize, String subjectCode) throws ParseException {
		if (userId == null || appKey == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
		}
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Page page = new Page();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		page.setEnableGetTolaRows(false);
		List<ExamScoringTaskVo> eilist = this.examInterscoreService.findExamScoringTask(userId, subjectCode, PaperType.EXAM, page);
		List<Subject> slist = subjectService.findAllSubjectName();
		/** ??????????????? */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		/** ??????????????? */
		String preTime = "";
		/** ???????????????????????? */
		Map<String, Object> taskMap = new HashMap<String, Object>();
		/** ?????????????????????????????? */
		List<Map<String, Object>> userTaskList = new ArrayList<Map<String, Object>>();
		/** ??????????????????????????????????????? */
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
		List<Object> taskInfoList = new ArrayList<Object>();
		Date date = new Date();
		if (eilist == null || eilist.size() == 0) {
			return new ResponseVo<Object>("0", new ArrayList<Object>());// ???????????????
		}
		for (int i = 0; i < eilist.size(); i++) {
			ExamScoringTaskVo vo = eilist.get(i);
			Integer isInterscore = 0;
			if (vo.getScoringTime() != null) {
				isInterscore = 1;
			}
			if (i == 0) {
				/** ??????????????????????????? */
				preTime = format.format(vo.getStartDate());
			}
			String subjectName = "";
			subjectCode = "";
			if (vo.getSubjectCode() != null && !vo.getSubjectCode().equals("")) {
				subjectCode = vo.getSubjectCode();
				for (Subject s1 : slist) {
					if (s1.getCode().equals(subjectCode)) {
						subjectName = s1.getName();
						break;
					}
				}
			}
			taskMap.put("title", vo.getTitle());
			taskMap.put("taskId", vo.getTaskId());
			taskMap.put("subjectCode", subjectCode);
			taskMap.put("subjectName", subjectName);
			taskMap.put("scoredUserId", vo.getScoredUserId());
			taskMap.put("isInterscore", isInterscore);
			taskMap.put("startTime", formatH.format(vo.getStartDate()));
			taskMap.put("finishTime", formatH.format(vo.getFinishedDate()));
			Date today = new Date();
			int status = 0;
			if (today.after(vo.getStartDate()) && today.before(vo.getFinishedDate())) {
				status = 1;
			} else if (today.after(vo.getFinishedDate())) {
				status = 2;
			}
			taskMap.put("status", status);
			/** ????????????????????????????????????????????????????????? */

			if (preTime.equals(format.format(vo.getStartDate()))) {
				/** ??????????????????????????????????????????????????? */
				userTaskList.add(taskMap);
				/** ???????????????map???????????????????????????????????? */
				taskMap = new HashMap<String, Object>();
				/** ????????????????????????????????????????????? */
				if (i == eilist.size() - 1) {
					/** ????????????????????????????????????list??? */
					timeAndUserTaskMap.put("time", preTime);
					timeAndUserTaskMap.put("question", userTaskList);
					/** ?????????????????????????????? */
					taskInfoList.add(timeAndUserTaskMap);
				}
			} else {
				/** ????????????????????? */
				timeAndUserTaskMap.put("time", preTime);
				timeAndUserTaskMap.put("question", userTaskList);
				/** ????????????????????????????????????????????????????????? */
				taskInfoList.add(timeAndUserTaskMap);

				/** ????????????????????????map,???????????????????????????????????? */
				timeAndUserTaskMap = new HashMap<String, Object>();
				/** ???????????????????????????,???????????????????????? */
				userTaskList = new ArrayList<Map<String, Object>>();
				userTaskList.add(taskMap);

				/** ???????????????????????????????????? */
				preTime = format.format(vo.getStartDate());

				/** ??????????????????????????? */
				if (i == eilist.size() - 1) {
					/** ?????????????????????????????????????????? */
					timeAndUserTaskMap.put("time", preTime);
					timeAndUserTaskMap.put("question", userTaskList);
					taskInfoList.add(timeAndUserTaskMap);
				} else {
					/** ??????????????????, ????????????????????????????????????????????????????????? */
					taskMap = new HashMap<String, Object>();
				}
			}
		}

		List<Object> sortList = new ArrayList<Object>();
		for (int i = 0; i < taskInfoList.size(); i++) {
			Map<String, Object> frist = new HashMap<String, Object>();
			frist = (Map) taskInfoList.get(i);
			List<Map<String, Object>> second = new ArrayList<Map<String, Object>>();
			timeAndUserTaskMap = new HashMap<String, Object>();
			second = (List) frist.get("question");
			Collections.reverse(second);
			timeAndUserTaskMap.put("question", second);
			sortList.add(frist);

		}
		return new ResponseVo<Object>("0", sortList);
	}

	private void InitExamInterscore(Integer taskId, String taskUuid, Integer teamId, Integer objectType) {
		ExamInterscoreCondition eci = new ExamInterscoreCondition();
		eci.setExamPublishId(taskUuid);
		eci.setTeamId(teamId);
		eci.setIsDeleted(false);
		List<ExamInterscore> eilist = this.examInterscoreService.findExamInterscoreByCondition(eci);
		if (eilist == null || eilist.size() == 0) {
			UserPaperCondition upc = new UserPaperCondition();
			upc.setOwnerId(taskId);
			upc.setType(2);
			upc.setTeamId(teamId);
			List<UserPaper> ups = this.userPaperService.findUserPaperByCondition(upc);
			if (ups != null && ups.size() > 1) {
				Date date = new Date();
				int[] a = randomArray(0, ups.size() - 1, ups.size());
				ExamInterscore[] eslist = new ExamInterscore[ups.size()];
				ExamInterscore es = new ExamInterscore();
				int i = 0;
				for (UserPaper up : ups) {
					es = new ExamInterscore();
					es.setCreateDate(date);
					es.setModifyDate(date);
					es.setIsDeleted(false);
					es.setExamPublishId(taskUuid);
					es.setScoredPaperId(up.getId());
					es.setScoredUserId(up.getUserId());
					es.setScoringUserId(ups.get(a[i]).getUserId());
					es.setTeamId(teamId);
					es.setObjectType(objectType);
					eslist[i] = es;
					i++;
				}
				this.examInterscoreService.createBatch(eslist);
			}
		}

	}

	public static int[] randomArray(int min, int max, int n) {
		int len = max - min + 1;

		if (max < min || n > len) {
			return null;
		}

		// ????????????????????????????????????
		int[] source = new int[len];
		for (int i = min; i < min + len; i++) {
			source[i - min] = i;
		}

		int[] result = new int[n];
		Random rd = new Random();
		int index = 0;
		for (int i = 0; i < result.length; i++) {
			// ????????????0???(len-2)??????????????????
			index = Math.abs(rd.nextInt() % len);
			while (i == source[index]) {
				index = Math.abs(rd.nextInt() % len);
				if (source[len - 1] == max && source[0] == max) {
					return randomArray(min, max, n);
				}
			}
			len--;
			// ?????????????????????????????????
			result[i] = source[index];
			// ??????????????????????????????????????????????????????(len-1)????????????????????????
			source[index] = source[len];
		}
		return result;
	}

	@Override
	public Object interscoreDetails(String appKey, Integer taskId, Integer teamId) {
		if (taskId == null || appKey == null || teamId == null) {
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);// ????????????
		}
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if (app == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		ExamPublish ep = this.examPublishService.findExamPublishById(taskId);
		if (ep == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("taskId?????????,?????????");
			info.setMsg("????????????taskId");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String, Object> total = new HashMap<String, Object>();
		Map<Integer, String> allMap = new HashMap<Integer, String>();
		Map<Integer, String> copyMap = new HashMap<Integer, String>();
		List<Map<String, String>> elist = new ArrayList<Map<String, String>>();
		List<Map<String, String>> nolist = new ArrayList<Map<String, String>>();
		ExamPublishedRecordCondition erc = new ExamPublishedRecordCondition();
		erc.setPublishedMicroId(ep.getUuid());
		erc.setTeamId(teamId);
		List<ExamPublishedRecord> eprlist = this.examPublishedRecordService.findExamPublishedRecordByCondition(erc);
		if (eprlist == null || eprlist.size() == 0) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????,?????????");
			info.setMsg("????????????????????????");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Integer[] userIds = new Integer[eprlist.size()];
		int i = 0;
		for (ExamPublishedRecord epr : eprlist) {
			userIds[i] = epr.getUserId();
			allMap.put(epr.getUserId(), epr.getUserName());
			i++;
		}
		Map<Integer, String> iconMap = new HashMap<Integer, String>();
		copyMap.putAll(allMap);
		ExamInterscoreCondition eic = new ExamInterscoreCondition();
		eic.setTeamId(teamId);
		eic.setExamPublishId(ep.getUuid());
		eic.setObjectType(PaperType.EXAM);
		eic.setIsDeleted(false);
		List<ExamInterscore> eilist = this.examInterscoreService.findExamInterscoreByCondition(eic);
		iconMap = ImgUtil.getImgSrcByIds(userIds, profileService);
		if (eilist != null && eilist.size() > 0) {
			for (ExamInterscore ei : eilist) {
				String evaluateingName = allMap.get(ei.getScoringUserId());
				String evaluatedName = allMap.get(ei.getScoredUserId());
				String evaluateingIcon = icon(iconMap.get(ei.getScoringUserId()));
				String evaluatedIcon = icon(iconMap.get(ei.getScoredUserId()));
				Map<String, String> map = new HashMap<String, String>();
				map.put("evaluateingName", evaluateingName);
				map.put("evaluatedName", evaluatedName);
				map.put("evaluateingIcon", evaluateingIcon);
				map.put("evaluatedIcon", evaluatedIcon);
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

	private String icon(String icon) {
		if (icon == null || "".equals(icon)) {
			return SysContants.APP_DEFAULT_USERICON;
		}
		return icon;
	}

	@Override
	public Object InitInterscore(String appKey, Integer taskId, Integer teamId) throws ParseException {
		Integer missExam = 0;
		Integer notInterscore = 0;
		Integer interscore = 0;
		Integer isFirst = 1;
		Integer isEmpty = 0;
		ExamPublish ep = new ExamPublish();
		ep = this.examPublishService.findExamPublishById(taskId);
		if (ep == null) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????,?????????");
			info.setMsg("????????????????????????");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		ExamRelateCondition erc = new ExamRelateCondition();
		erc.setPublishMicroLessonId(ep.getUuid());
		erc.setRelateId(teamId);
		List<ExamRelate> erlist = this.examRelateService.findExamRelateByCondition(erc);
		if (erlist == null || erlist.size() == 0) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("?????????????????????,?????????");
			info.setMsg("????????????????????????");
			info.setParam("taskId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		ExamRelate er = erlist.get(0);
		ExamPublishedRecordCondition eprc = new ExamPublishedRecordCondition();
		eprc.setPublishedMicroId(ep.getUuid());
		eprc.setTeamId(teamId);
		List<ExamPublishedRecord> eprlist = new ArrayList<ExamPublishedRecord>();
		eprlist = this.examPublishedRecordService.findExamPublishedRecordByCondition(eprc);
		UserPaperCondition upc = new UserPaperCondition();
		upc.setOwnerId(taskId);
		upc.setType(2);
		upc.setTeamId(teamId);
		List<UserPaper> uplist = new ArrayList<UserPaper>();
		uplist = this.userPaperService.findUserPaperByCondition(upc);
		Map<String, Integer> map = new HashMap<String, Integer>();
		List<ExamInterscore> eilist = new ArrayList<ExamInterscore>();
		ExamInterscoreCondition eic = new ExamInterscoreCondition();
		eic.setExamPublishId(ep.getUuid());
		eic.setTeamId(teamId);
		eic.setObjectType(PaperType.EXAM);
		eic.setIsDeleted(false);
		eilist = this.examInterscoreService.findExamInterscoreByCondition(eic);

		missExam = eprlist.size() - uplist.size();

		if (eilist == null || eilist.size() == 0) {

		} else {

			for (ExamInterscore ei : eilist) {
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

	private Map<Integer, Object> findUserFileMap(Integer userId, Integer[] owerIds) {

		List<UserPaper> uplist = new ArrayList<UserPaper>(owerIds.length);
		List<UserFile> uflist = new ArrayList<UserFile>();
		List<String> markImgList = new ArrayList<String>();
		Map<String, FileResult> markImgMap = new HashMap<String, FileResult>();
		Map<Integer, Object> upMap = new HashMap<Integer, Object>();
		Map<Integer, Object> ownerMap = new HashMap<Integer, Object>();
		uplist = userPaperService.findUserPaperByOwnerIdsAndUserId(owerIds, null, PaperType.EXAM, userId);
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
			// System.out.println("userPaper?????????"+(end1-start1)+"??????");
			if (uflist != null && uflist.size() > 0) {
				for (UserFile userFile : uflist) {
					if (userFile.getMarkedFileUuid() != null && !userFile.getMarkedFileUuid().equals("")) {
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
					// System.out.println("?????????????????????"+(end-start)+"??????");
				}
				for (UserFile userFile : uflist) {
					FileResult result = null;
					/** ?????????????????????????????????????????????????????????????????? */
					if (userFile.getMarkedFileUuid() != null) {
						// result =
						// fileService.findFileByUUID(userFile.getMarkedFileUuid());
						result = markImgMap.get(userFile.getMarkedFileUuid());
					} else {
						/** ???????????????????????????????????????????????? */
						// result =
						// fileService.findFileByUUID(userFile.getSourceFileUuid());
						result = markImgMap.get(userFile.getSourceFileUuid());
					}

					List<String> list = (List<String>) upMap.get(userFile.getUserPaperId());
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
	
	
	@Override
	public Object correctListByQuestion(String appKey, Integer taskId, Integer unitId, Integer teamId,
			String questionUuid, Integer pageSize, Integer pageNumber) {
		try {
			String domain = this.fileService.getHttpPrefix() + "/" + this.fileService.getSpaceName();
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/**????????????????????????????????????*/
			List<UserPaperCorrectVo> userPaperCorrectVoList = userPaperService.findUserPaperCorrectByTaskId(taskId, unitId, null, questionUuid, null);
			if(userPaperCorrectVoList.size()==0) {
				log.info("???????????????????????????");
	 			return new ResponseVo<Object>("0",new HashMap<String, Object>());
			}
			
			List<TeamUser> teamUserList = teamUserService.findTeamUserOfAll(teamId);
			
			/**??????id?????????*/
			Integer[] userIds = new Integer[teamUserList.size()];
			/**???????????????id*/
			for (int i = 0; i < teamUserList.size(); i++) {
 				userIds[i] = teamUserList.get(i).getUserId();
			}
			
			/**?????????????????????*/
			List<User> userList = userService.findUserByIds(userIds);
			
			/**??????Person?????????(?????????????????????name) map???id???userId, value???person*/
 			Map<Integer, Person> personMap = learningPlanBaseService.getPersonMapByLpTaskUserList(userList);
 			/**????????????*/
 			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
 			/**???????????????*/
 			List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(userPaperCorrectVoList.size());
 			
 			/**??????????????????id??????*/
			String[] userQuestionUuids=new String[userPaperCorrectVoList.size()];
			for (int i = 0; i < userPaperCorrectVoList.size(); i++) {
				userQuestionUuids[i] = userPaperCorrectVoList.get(i).getUserQuestionUuId();
			}
			/**????????????id?????????????????????????????????*/
			List<UserFile> userFileList = userFileService.findUserFileByUserQuestionUuIds(userQuestionUuids);
			
			Map<String, FileResult> fileResultMap = getFileResultMap(userFileList);
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			boolean isFirst = true;
			
			/**???????????????????????????????????????????????????*/
 			for (UserPaperCorrectVo userPaperCorrectVo : userPaperCorrectVoList) {
 				Map<String, Object> map = new HashMap<String, Object>();
 				
 				if(isFirst) {
 					/**?????????????????????*/
 					resultMap.put("correctAnswer", MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbCorrectAnswer(), domain));
 					/**????????????*/
 					resultMap.put("correctScore", userPaperCorrectVo.getCorrectScore());
 					isFirst=false;
 				}
 				
 				/**??????????????????????????????*/
 				List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>(9);
 				
 				Person person = personMap.get(userPaperCorrectVo.getUserId());
 				
 				if(person==null) {
 					continue;
 				}
 				
				/**studentId??????????????????userId*/
 				map.put("studentId", userPaperCorrectVo.getUserId());
				map.put("studentName", person.getName());
				/**???????????????*/
				map.put("score", userPaperCorrectVo.getScore());
				
				/**??????*/
				if(imgMap.get(userPaperCorrectVo.getUserId())!=null) {
					map.put("studentIcon", imgMap.get(userPaperCorrectVo.getUserId()));
				} else {
					/**????????????????????????????????????*/
					map.put("studentIcon", SysContants.APP_DEFAULT_USERICON);
				}
				/**???????????????*/
				map.put("studentAnswer", MqtPaperUtil.StringToArray(userPaperCorrectVo.getDbAnswer(), domain));
				
				if(userFileList!=null) {
					for (UserFile userFile : userFileList) {
						/**?????????????????????????????????*/
						if(userFile.getUserId()-userPaperCorrectVo.getUserId()==0) {
							Map<String, Object> fileMap = getFileMap(fileResultMap, userFile);
							/**????????????????????????????????????????????????,??????md5, httpUrl*/
							fileList.add(fileMap);
						}
					}
				}
				
				map.put("files", fileList);
				resultList.add(map);
			}

 			resultMap.put("answers", resultList);
 			return new ResponseVo<Object>("0",resultMap);
		} catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("??????????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}
	
	@Override
	public Object taskCorrectFinish(String appKey, Integer taskId, String students, String questionUuid, Integer unitId, String files) {
		try {
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey?????????,?????????");
				info.setMsg("????????????appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			/**??????userId?????????*/
			JSONArray usersArray = JSONArray.fromObject(students);
			
			/**???????????????????????????????????????????????????????????????????????????????????????data_change*/
			boolean dataChange = false;
			
			/**?????????????????????????????????????????????????????????????????????uuid*/
			String[] questionUUIDs = new String[]{questionUuid};
			/**????????????????????????*/
			List<PaQuestionVo> questionList = paQuestionService.findPaperQuestionByUUIDs(questionUUIDs);
			
			if(questionList.size()==0) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("???????????????????????????????????????????????????");
				info.setMsg("???????????????????????????????????????????????????");
				info.setParam("questionUuid");
				return new ResponseError(CommonCode.S$INVALID_DATA, info);
			}
			
			/**???????????????????????????????????????*/
			Map<String, UserQuestion> userQuestionsMap = this.userQuestionService.findMapByOwnerIdAndUserId(taskId, unitId);

			/**??????????????????*/
			List<UserPaper> userPaperList = userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, unitId);
			
			if(userPaperList.size()==0) {
				userPaperList = userPaperService.findUserPaperByOwnerIdAndObjectId(taskId, null);
			}
			
			/**????????????id????????????????????????????????????*/
			Map<Integer, UserPaper> userPaperMap = new HashMap<Integer, UserPaper>();
			for (UserPaper userPaper : userPaperList) {
				userPaperMap.put(userPaper.getUserId(), userPaper);
			}
			
			/**??????????????????????????????*/
			for (int i = 0; i < usersArray.size(); i++) {
				PaQuestionVo paperQuestion = questionList.get(0);
				JSONObject user = usersArray.getJSONObject(i);
				Integer uId = user.getInt("studentId");
				Double score = user.getDouble("score");
				/**????????????*/
				UserQuestion userQuestion = userQuestionsMap.get(uId+paperQuestion.getUuid());
				if(userQuestion==null) {
					ResponseInfo info = new ResponseInfo();
					info.setDetail("??????id???????????????????????????");
					info.setMsg("??????id???????????????????????????");
					info.setParam("studentId");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				}
				
				UserPaper userPaper = userPaperMap.get(uId);
				if(userPaper==null) {
					System.out.println("????????????????????????");
					continue;
				}
				/**???????????????????????????*/
				userPaper.setScore(userPaper.getScore() + score - userQuestion.getScore());
				userPaper.setScoreTime(new Date());
				/**????????????*/
				userPaperService.modify(userPaper);
				/**??????dataChange??????*/
				if(!dataChange) {
					dataChange = true;
				}
				
				if (userQuestion != null) {
					userQuestion.setScore(score);
					/**????????????????????????????????????*/
					if(paperQuestion.getScore() - score==0) {
						userQuestion.setIsCorrect(true);
					} else {
						userQuestion.setIsCorrect(false);
					}
					/**??????dataChange??????*/
					if(!dataChange) {
						dataChange = true;
					}
				}
			}
			
			/**?????????????????????userQuestion;*/
			Collection<UserQuestion> userQuestionCollection = userQuestionsMap.values();
			List<UserQuestion> userQuestionList = new ArrayList<UserQuestion>(userQuestionCollection.size());
			Iterator<UserQuestion> iterator = userQuestionCollection.iterator();
			while (iterator.hasNext()) {
				userQuestionList.add(iterator.next());
			}
			
			this.userQuestionService.batchUpdateScoreAndIsCorrect(userQuestionList);

			if(files!=null && !"".equals(files)) {
				/**????????????????????????*/
				JSONArray fileArrays = JSONArray.fromObject(files);
				List<UserFile> userFiles = new ArrayList<UserFile>();
				/**????????????????????????*/
				for (int i = 0; i < fileArrays.size(); i++) {
					JSONObject file = fileArrays.getJSONObject(i);
					/**??????????????????????????????id*/
					Integer fileId = file.getInt("fileId");
					UserFile userFile = userFileService.findUserFileById(fileId);
					if(userFile!=null) {
						String markedFileUuid = file.getString("markedFileUuid");
						/**??????????????????*/
						userFile.setMarkedFileUuid(markedFileUuid);
						userFile.setModifyDate(new Date());
						/**????????????*/
						userFiles.add(userFile);
					}
					/**??????dataChange??????*/
					if(!dataChange) {
						dataChange = true;
					}
				}
				/**??????????????????*/
				userFileService.batchUpdateMarkedFile(userFiles);
			}

			if(dataChange) {
				LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitByTaskIdAndUnitId(taskId, unitId);
				
				if(lpTaskExamUnit!=null) {
					/**??????????????????*/
					ExamStat examStat = examStatService.findExamStatByExamId(lpTaskExamUnit.getExamId());
					if(examStat!=null) {
						/**????????????data_chage*/
						examStat.setDataChanged(true);
						examStatService.modify(examStat);
					}
				}
			}
			
 			Map<String, Object> result = new HashMap<String, Object>();
 			
 			return new ResponseVo<Object>("0",result);
		}catch(Exception e) {
			e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setDetail("????????????????????????");
            info.setMsg("????????????");
            return new ResponseError("000001", info);
		}
	}

	
	private Map<String, Object> getFileMap(Map<String, FileResult> fileResultMap, UserFile userFile) {
		/**?????????????????????*/
		FileResult sourceFileResult = fileResultMap.get(userFile.getSourceFileUuid());
		/**?????????????????????*/
		FileResult markedFileResult = fileResultMap.get(userFile.getMarkedFileUuid());
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("fileId", userFile.getId());
		fileMap.put("sourceFileUuid", userFile.getSourceFileUuid());
		/**????????????????????????????????????????????????,??????md5, httpUrl*/
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
			/**???????????????uuid?????????????????????????????????????????????????????????size*2;*/
			String[] uuids = new String[userFileList.size()*2];
			/**??????userFileList, ????????????uuid??????*/
			int index = 0;
			for (int i = 0; i < userFileList.size(); i++) {
				uuids[index] = userFileList.get(i).getSourceFileUuid();
				uuids[index+1] = userFileList.get(i).getMarkedFileUuid();
				index=index+2;
			}
			/**????????????uuid?????????????????????????????????????????????md5,httpurl*/
			fileResultMap = fileService.findFileByUUIDs(uuids);
		}
		return fileResultMap;
	}
	
}
