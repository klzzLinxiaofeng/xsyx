//package platform.szxyzxx.web.exam.controller;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.core.task.TaskExecutor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import framework.generic.dao.Order;
//import framework.generic.dao.Page;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONNull;
//import net.sf.json.JSONObject;
//import platform.education.exam.contants.ExamType;
//import platform.education.exam.contants.PublishedFlag;
//import platform.education.exam.model.Exam;
//import platform.education.exam.model.ExamPublish;
//import platform.education.exam.model.ExamPublishedRecord;
//import platform.education.exam.model.ExamRelate;
//import platform.education.exam.model.ExamUserRecord;
//import platform.education.exam.service.ExamPublishService;
//import platform.education.exam.service.ExamPublishedRecordService;
//import platform.education.exam.service.ExamRelateService;
//import platform.education.exam.service.ExamService;
//import platform.education.exam.service.ExamUserRecordService;
//import platform.education.exam.vo.ExamCondition;
//import platform.education.exam.vo.ExamPublishCondition;
//import platform.education.exam.vo.ExamPublishedRecordCondition;
//import platform.education.exam.vo.ExamRelateCondition;
//import platform.education.exam.vo.ExamRelateVo;
//import platform.education.exam.vo.ExamStudyListVo;
//import platform.education.exam.vo.ExamStudyRecordVo;
//import platform.education.generalTeachingAffair.model.ExamQuestion;
//import platform.education.generalTeachingAffair.model.ExamStudent;
//import platform.education.generalTeachingAffair.model.Grade;
//import platform.education.generalTeachingAffair.model.PjExam;
//import platform.education.generalTeachingAffair.model.School;
//import platform.education.generalTeachingAffair.model.SchoolTerm;
//import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
//import platform.education.generalTeachingAffair.model.Student;
//import platform.education.generalTeachingAffair.model.Teacher;
//import platform.education.generalTeachingAffair.model.Team;
//import platform.education.generalTeachingAffair.model.TeamTeacher;
//import platform.education.generalTeachingAffair.service.ExamQuestionService;
//import platform.education.generalTeachingAffair.service.PjExamService;
//import platform.education.generalTeachingAffair.service.SchoolService;
//import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
//import platform.education.generalTeachingAffair.service.SchoolTermService;
//import platform.education.generalTeachingAffair.service.StudentService;
//import platform.education.generalTeachingAffair.service.TeamService;
//import platform.education.generalTeachingAffair.vo.ExamResult;
//import platform.education.generalTeachingAffair.vo.ExamStudentCondition;
//import platform.education.generalTeachingAffair.vo.PjExamCondition;
//import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
//import platform.education.generalTeachingAffair.vo.TeamCondition;
//import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
//import platform.education.message.contans.StatusDefaultContans;
//import platform.education.message.model.Message;
//import platform.education.micro.model.MiMicroPersonGroup;
//import platform.education.micro.model.MiMicroPersonGroupUserId;
//import platform.education.micro.service.MiMicroPersonGroupService;
//import platform.education.micro.service.MiMicroPersonGroupUserIdService;
//import platform.education.micro.vo.MiMicroPersonGroupCondition;
//import platform.education.micro.vo.MiMicroPersonGroupUserIdCondition;
//import platform.education.paper.model.PaperQuestionResult;
//import platform.education.paper.service.PaPaperHandleService;
//import platform.education.paper.service.PaPaperService;
//import platform.education.paper.service.PaUserQuestionService;
//import platform.education.resource.contants.ResourceType;
//import platform.education.resource.contants.StudyFinishedFlag;
//import platform.education.resource.model.Resource;
//import platform.education.resource.model.ResourceLibrary;
//import platform.education.resource.service.ResourceHandlerService;
//import platform.education.resource.service.ResourceService;
//import platform.education.resource.utils.DownloadUtil;
//import platform.education.resource.utils.IconUtil;
//import platform.education.resource.utils.UUIDUtil;
//import platform.education.resource.vo.ResourceCondition;
//import platform.education.resource.vo.ResourceLibraryCondition;
//import platform.education.resource.vo.ResourceVo;
//import platform.education.resource.vo.ResourceVoCondition;
//import platform.service.storage.model.EntityFile;
//import platform.service.storage.vo.FileResult;
//import platform.szxyzxx.services.statistic.service.StatisticService;
//import platform.szxyzxx.web.common.annotation.CurrentUser;
//import platform.szxyzxx.web.common.contants.MessageCenterContants;
//import platform.szxyzxx.web.common.contants.SysContants;
//import platform.szxyzxx.web.common.controller.base.BaseController;
//import platform.szxyzxx.web.common.util.PushMessageUtil;
//import platform.szxyzxx.web.common.util.ResponseInfomation;
//import platform.szxyzxx.web.common.vo.UserInfo;
//import platform.szxyzxx.web.message.contans.TagsTypeContans;
//import platform.szxyzxx.web.micro.contants.ContansOfResource;
//import platform.szxyzxx.web.resource.vo.MyResourceVo;
//import platform.szxyzxx.web.teach.contants.ExamStatisticsType;
//
///**
// *
// * @author 罗志明
// */
//@Controller
//@RequestMapping(value = "/exampublish")
//public class ExamPublishController extends BaseController {
//	@javax.annotation.Resource
//	ExamUserRecordService examUserRecordService;
//	// 试卷发布记录
//	@javax.annotation.Resource
//	private ExamPublishedRecordService examPublishedRecordService;
//	// 人员分组
//	@javax.annotation.Resource
//	private MiMicroPersonGroupService miMicroPersonGroupService;
//	@javax.annotation.Resource
//	private PaPaperHandleService paperHandleService;
//	@javax.annotation.Resource
//	private MiMicroPersonGroupUserIdService microPersonGroupUserIdService;
//	@javax.annotation.Resource
//	private SchoolService schoolService;
//	@javax.annotation.Resource
//	private ExamPublishService examPublishService;
//	@javax.annotation.Resource
//	private ExamRelateService examRelateService;
//	@javax.annotation.Resource
//	private PaPaperService papaperService;
//	@javax.annotation.Resource
//	private ResourceHandlerService resourceHandlerService;
//	@javax.annotation.Resource
//	ResourceService resourceSerivce;
//	@javax.annotation.Resource(name = "publishExam_taskExecutor")
//	private TaskExecutor taskExecutor;
//	private static final String COMMON_DIR = "exam/common";
//	private static final String DIR = "exam/publish";
//	
//
//	@javax.annotation.Resource
//	private PaUserQuestionService userQuestionService;
//
//	@javax.annotation.Resource
//	private ExamQuestionService examQuestionService;
//	public ExamQuestionService getExamQuestionService() {
//		return examQuestionService;
//	}
//
//	public void setExamQuestionService(ExamQuestionService examQuestionService) {
//		this.examQuestionService = examQuestionService;
//	}
//	
//	
//	@javax.annotation.Resource
//	private StatisticService statisticService;
//
//	@RequestMapping(value = "/deletePublished")
//	public String deletePublished(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String publishId = request.getParameter("publishId");
//		String relateId = request.getParameter("relateId");
//		String pjExamId = request.getParameter("pjExamId");
//		this.examPrepareService.deletePublish(publishId,
//				Integer.parseInt(relateId));
//		List<Integer> reList = new ArrayList<Integer>();
//		List<Integer> examList = new ArrayList<Integer>();
//		reList.add(Integer.valueOf(relateId));
//		if (pjExamId != null && !pjExamId.equals("")) {
//			examList.add(Integer.valueOf(pjExamId));
//		}
//		delForStudent(reList,null, publishId, user.getSchoolId(), studentService,
//				examPublishedRecordService, examList, pjExamService,paperHandleService,examQuestionService,
//				taskExecutor);
//		return null;
//	}
//
//	@RequestMapping(value = "/resetDate")
//	public String resetDate(HttpServletRequest request,
//			HttpServletResponse response) {
//		String publishId = request.getParameter("publishId");
//		String relateId = request.getParameter("relateId");
//		ExamPublish ep = this.examPublishService
//				.findExamPublishByUuid(publishId);
//		request.setAttribute("publishId", publishId);
//		request.setAttribute("startDate", ep.getStartDate());
//		request.setAttribute("finishedDate", ep.getFinishedDate());
//		request.setAttribute("relateId", relateId);
//		return DIR + "/resetDate";
//	}
//
//	@RequestMapping(value = "/updatePublishedDate")
//	public String updatePublishedDate(HttpServletRequest request,
//			HttpServletResponse response) throws ParseException {
//		String publishId = request.getParameter("publishId");
//		String relateId = request.getParameter("relateId");
//		String startDate = request.getParameter("startDate");
//		String finishedDate = request.getParameter("finishedDate");
//		String startClock = request.getParameter("startClock");
//		String finishedClock = request.getParameter("finishedClock");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		Date sd = sdf.parse(startDate);
//		Date fd = sdf.parse(finishedDate);
//		ExamRelate mlp = this.examPrepareService.updatePublishedDate(publishId,
//				Integer.parseInt(relateId), sd, fd);
//		ExamPublish ep = this.examPublishService
//				.findExamPublishByUuid(publishId);
//		ep.setStartDate(sd);
//		ep.setFinishedDate(fd);
//		this.examPublishService.modify(ep);
//		return null;
//	}
//
//	@RequestMapping(value = "/preview")
//	public String preview(HttpServletRequest request,
//			HttpServletResponse response) {
//		String objId = request.getParameter("objId");
//		String canReturn = request.getParameter("canReturn");
//		if (objId != null && !objId.equals("")) {
//			Exam exam = this.examService.findExamByUuid(objId);
//			if (exam != null && exam.getPaperId() != null) {
//				List<PaperQuestionResult> qList = this.paperQuestionService
//						.findPaperQuestionByPaperId(exam.getPaperId(), null);
//				request.setAttribute("canReturn", canReturn);
//				request.setAttribute("qList", qList);
//				return "exam/statistics" + "/paper";
//			}
//		}
//		playMicroImpl(request);
//		return DIR + "/preview";
//	}
//
//	@RequestMapping(value = "/play")
//	public String play(HttpServletRequest request, HttpServletResponse response) {
//		String objId = request.getParameter("objId");
//		String study = request.getParameter("study");
//		String microPublishedId = request.getParameter("microPublishedId");
//		Exam exam = null;
//		Resource r = new Resource();
//		if (microPublishedId != null && !"".equals(microPublishedId)) {
//			if ((objId == null || objId.equals(""))) {
//
//				JSONArray array = this.examPrepareService
//						.getPublishedPlayJson(microPublishedId);
//				if (array.size() > 0) {
//					// 只播第一个
//					JSONObject micro = (JSONObject) array.get(0);
//					String resourceid = (String) micro.get("resourceId");
//					r = this.resourceService.findResourceById(Integer
//							.valueOf(resourceid));
//					exam = this.examService.findExamByUuid(r.getObjectId());
//				}
//			} else {
//
//				JSONArray array = examPrepareService
//						.getPublishedPlayJson(microPublishedId);
//				if (array.size() != 0) {
//					// 寻找object对应的resourceId
//					for (int i = 0; i < array.size(); i++) {
//						JSONObject micro = (JSONObject) array.get(i);
//						String obId = (String) micro.get("id");
//						if (obId.equals(objId)) {
//							String resourceid = (String) micro
//									.get("resourceId");
//							r = this.resourceService.findResourceById(Integer
//									.valueOf(resourceid));
//						}
//
//					}
//
//				}
//				exam = this.examService.findExamByUuid(objId);
//			}
//
//		}
//		if (exam != null && r != null) {
//			MyResourceVo mrvo = new MyResourceVo();
//			mrvo.setResEnt(r);
//			String entityId = exam.getEntityId();
//			if (entityId != null) {
//				EntityFile entity = this.entityFileService
//						.findFileByUUID(entityId);
//				if (entity != null) {
//					mrvo.setThumbnailUrl(entity.getThumbnailUrl());
//					mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
//					request.setAttribute("entity", entity);
//				}
//			}
//			request.setAttribute("res", mrvo);
//		}
//		if (study != null) {
//			request.setAttribute("study", study);
//		}
//		request.setAttribute("microPublishedId", microPublishedId);
//		return COMMON_DIR + "/play";
//	}
//
//	private void playMicroImpl(HttpServletRequest request) {
//		String objId = request.getParameter("objId");
//		String study = request.getParameter("study");
//		String microPublishedId = request.getParameter("microPublishedId");
//		Exam exam = null;
//		if (microPublishedId != null && !"".equals(microPublishedId)
//				&& (objId == null || "".equals(objId))) {
//			JSONArray array = examPrepareService
//					.getPublishedPlayJson(microPublishedId);
//			if (array.size() > 0) {
//				// 只播第一个
//				JSONObject micro = (JSONObject) array.get(0);
//				exam = this.examService
//						.findExamByUuid((String) micro.get("id"));
//			}
//		} else {
//			exam = this.examService.findExamByUuid(objId);
//		}
//		if (exam != null) {
//			MyResourceVo mrvo = new MyResourceVo();
//			mrvo.setResEnt(exam);
//			String entityId = exam.getEntityId();
//			if (entityId != null) {
//				EntityFile entity = this.entityFileService
//						.findFileByUUID(entityId);
//				if (entity != null) {
//					mrvo.setThumbnailUrl(entity.getThumbnailUrl());
//					mrvo.setIconType(IconUtil.setIcon(entity.getExtension()));
//					request.setAttribute("entity", entity);
//				}
//			}
//			request.setAttribute("res", mrvo);
//		}
//		if (study != null) {
//			request.setAttribute("study", study);
//		}
//		request.setAttribute("em", exam);
//		request.setAttribute("microPublishedId", microPublishedId);
//	}
//
//	@RequestMapping(value = "/ajaxGetStudyJson")
//	public String ajaxGetStudyJson(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user)
//			throws IOException {
//		String microPublishId = request.getParameter("microPublishId");
//		JSONArray array = examPrepareService
//				.getPublishedPlayJson(microPublishId);
//		List voList = new ArrayList();
//		Integer finishedCount = 0;
//		for (int i = 0; i < array.size(); i++) {
//			MyResourceVo mrvo = new MyResourceVo();
//			ExamStudyListVo vo = new ExamStudyListVo();
//			JSONObject obj = array.getJSONObject(i);
//			String microId = (String) obj.get("id");
//			ExamUserRecord ur = this.examUserRecordService.getUniqueRecord(
//					user.getId(), microId, microPublishId);
//			vo.setExam(obj);
//			vo.setRecord(ur);
//			if (ur != null && ur.getLastPlayTime() != null) {
//				Long lpt = ur.getLastPlayTime().longValue();
//				String sd = timeMillisToString(lpt);
//				vo.setFormatLastPlayTime(sd);
//			}
//			if (ur != null && ur.getFinishedFlag() != null) {
//				if (ur.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
//					finishedCount++;
//				}
//			}
//			Exam em = this.examService.findExamByUuid(microId);
//			mrvo.setResEnt(vo);
//			if (em != null) {
//				EntityFile ent = this.entityFileService.findFileByUUID(em
//						.getEntityId());
//				mrvo.setThumbnailUrl(ent.getThumbnailUrl());
//				mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
//			}
//			voList.add(mrvo);
//		}
//		request.setAttribute("microPublishId", microPublishId);
//		request.setAttribute("finishedCount", finishedCount);
//		request.setAttribute("microList", voList);
//		return DIR + "/studyMicroList";
//	}
//
//	public String timeMillisToString(long timeMillis) {
//		int minute;
//		int second;
//		String m;
//		String s;
//		timeMillis = timeMillis / 1000;
//		minute = (int) (timeMillis / 60);
//		second = (int) (timeMillis % 60);
//		if (minute < 10) {
//			m = "0" + minute;
//		} else {
//			m = String.valueOf(minute);
//		}
//		if (second < 10) {
//			s = "0" + second;
//		} else {
//			s = String.valueOf(second);
//		}
//		return m + ":" + s;
//	}
//
//	@RequestMapping(value = "/publishLesson")
//	public String publishLesson(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user)
//			throws ParseException, IOException {
//		String publishData = request.getParameter("publishData");
//		JSONObject obj = JSONObject.fromObject(publishData);
//		String startDate = (String) obj.get("startDate");
//		String finishedDate = (String) obj.get("finishedDate");
//		String startClock = (String) obj.get("startClock");
//		String finishedClock = (String) obj.get("finishedClock");
//		String grade = (String) obj.get("gradeId");
//		Integer gradeId = Integer.valueOf(grade);
//		JSONArray microList = (JSONArray) obj.get("microList");
//		JSONArray classList = (JSONArray) obj.get("classList");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String isCheckString = (String) obj.get("isCheck");
//		Integer isCheck = Integer.valueOf(isCheckString);
//		Date sd = sdf.parse(startDate + " " + startClock);
//		Date fd = sdf.parse(finishedDate + " " + finishedClock);
//		List<Integer> receiverIdList = new ArrayList<Integer>();
//		List<Integer> receiverIdList2 = new ArrayList<Integer>();
//		for (int i = 0; i < classList.size(); i++) {
//			JSONObject receivers = (JSONObject) classList.get(i);
//			String receiverId = (String) receivers.get("relateId");
//			String relateType = (String) receivers.get("relateType");
//			if (relateType == TagsTypeContans.GROUP_TYPE
//					|| relateType.equals(TagsTypeContans.GROUP_TYPE)) {
//				receiverIdList2.add(Integer.parseInt(receiverId));
//			} else if (relateType == TagsTypeContans.TEAM_TYPE
//					|| relateType.equals(TagsTypeContans.TEAM_TYPE)) {
//				receiverIdList.add(Integer.parseInt(receiverId));
//			}
//		}
//		if (receiverIdList != null && !receiverIdList.isEmpty()) {
//			publishForStudent(microList, classList, user, studentService,
//					examPublishedRecordService, taskExecutor,
//					examPublishService, sd, fd,
//					examRelateService, isCheck,
//					resourceSerivce,statisticService);
//		} else if (receiverIdList2 != null && !receiverIdList2.isEmpty()) {
//			List<Integer> studentIds = new ArrayList<Integer>();
//			for (Integer id : receiverIdList2) {
//				MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
//				miMicroPersonGroupUserIdCondition.setMicroPersonGroupId(id);
//				List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService
//						.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
//				for (MiMicroPersonGroupUserId MiMicroPersonGroupUserId : list) {
//					studentIds.add(MiMicroPersonGroupUserId.getUserId());
//					MiMicroPersonGroupUserId.setType("1");
//					microPersonGroupUserIdService
//							.modify(MiMicroPersonGroupUserId);
//				}
//			}
//		}
//		return null;
//	}
//
//	private static List<Integer> getUserId(List<Student> studentList) {
//		List<Integer> idList = new ArrayList<Integer>();
//		if (studentList.size() > 0 && studentList != null) {
//			for (Student student : studentList) {
//				idList.add(student.getUserId());
//			}
//		}
//		return idList;
//	}
//
//	@RequestMapping(value = "/publishManager")
//	public String publishManager(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) throws ParseException {
//		String relateId = request.getParameter("relateId");
//		String relateType = request.getParameter("relateType");
//		String gradeId = request.getParameter("gradeId");
//		Integer relateIdint = Integer.parseInt(relateId);
//		List<Student> stList = new ArrayList<Student>();
//		if (relateType == TagsTypeContans.TEAM_TYPE
//				|| relateType.equals(TagsTypeContans.TEAM_TYPE)) {
//			stList = this.studentService.findStudentOfTeam(relateIdint);
//		} else if (relateType == TagsTypeContans.GROUP_TYPE
//				|| relateType.equals(TagsTypeContans.GROUP_TYPE)) {
//			MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
//			miMicroPersonGroupUserIdCondition
//					.setMicroPersonGroupId(relateIdint);
//			List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService
//					.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
//			for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
//				Student student = studentService.findOfUser(user.getSchoolId(),
//						miMicroPersonGroupUserId.getUserId());
//				stList.add(student);
//			}
//		}
//		List<ExamRelateVo> mlrList = this.examPrepareService
//				.searchPublishedLesson(user.getId(),
//						Integer.parseInt(relateId), page, relateType, null);
//		List<ExamRelateVo> reMlrList = new ArrayList<ExamRelateVo>();
//		// for (ExamRelateVo rv : mlrList) {
//		// Integer finishedCount = 0;
//		// Integer unFinishedCount = 0;
//		// for (Student st : stList) {
//		// //
//		// this.examPrepareService.searchUserPublishedRecord(rv.getPublishMicroLessonId(),
//		// st.getUserId(), st.getName(), st.getStudentNumber());
//		// ExamPublishedRecordCondition mprc = new
//		// ExamPublishedRecordCondition();
//		// mprc.setUserId(st.getUserId());
//		// mprc.setPublishedMicroId(rv.getPublishMicroLessonId());
//		// List<ExamPublishedRecord> mprList =
//		// this.examPublishedRecordService.findExamPublishedRecordByCondition(mprc);
//		// if (mprList.size() > 0) {
//		// ExamPublishedRecord mpr = mprList.get(0);
//		// if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
//		// finishedCount++;
//		// } else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
//		// unFinishedCount++;
//		// } else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED)
//		// {
//		// finishedCount++;
//		// }
//		// }
//		//
//		//
//		// }
//		// rv.setFinishedCount(finishedCount);
//		// rv.setUnFinishedCount(unFinishedCount);
//		// reMlrList.add(rv);
//		// }
//		if (gradeId != null && !gradeId.equals("")) {
//			request.setAttribute("gradeId", gradeId);
//		}
//		request.setAttribute("relateId", relateId);
//		request.setAttribute("relateType", relateType);
//		request.setAttribute("mlrList", mlrList);
//		request.setAttribute("userId", user.getId());
//		return DIR + "/publishManager";
//	}
//
//	@RequestMapping(value = "/publishManagerIndex")
//	public String publishManagerIndex(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) throws ParseException {
//		Map classGradeMap = getClassGradeMap(user, false, false, true);
//		Map allClassMap = findAllClassByUser(user.getSchoolId());
//		request.setAttribute("relateId", request.getParameter("relateId"));
//		request.setAttribute("relateType", request.getParameter("relateType"));
//		request.setAttribute("classGradeMap", classGradeMap);
//		request.setAttribute("allClassMap", allClassMap);
//		return DIR + "/publishManagerIndex";
//	}
//
//	@RequestMapping(value = "/prepareLesson")
//	public String prepareLesson(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		Map classGradeMap = getClassGradeMap(user, true, true, true);
//		Map allClassMap = findAllClassByUser(user.getSchoolId());
//		request.setAttribute("allClassMap", allClassMap);
//		request.setAttribute("classGradeMap", classGradeMap);
//		// Resource r= this.resourceHandlerService.createResourceOfType(4, 1,
//		// "1", "1", 1, "1","doc" ,1);
//		// System.out.println(r.getId());
//		return DIR + "/prepareLesson";
//	}
//
//	@RequestMapping(value = "/batchReviews")
//	public String batchReviews(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String microPublishId = request.getParameter("microPublishedId");
//		String relateId = request.getParameter("relateId");
//		Integer relateIdint = Integer.parseInt(relateId);
//		String relateType = request.getParameter("relateType");
//		List<Student> fst = new ArrayList();
//		List<Student> ust = new ArrayList();
//		List<Student> pst = new ArrayList();
//		List<Student> stList = new ArrayList<Student>();
//		if (relateType != null
//				&& (relateType == TagsTypeContans.TEAM_TYPE || relateType
//						.equals(TagsTypeContans.TEAM_TYPE))) {
//			stList = this.studentService.findStudentOfTeam(relateIdint);
//		} else if (relateType != null
//				&& (relateType == TagsTypeContans.GROUP_TYPE || relateType
//						.equals(TagsTypeContans.GROUP_TYPE))) {
//			MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
//			miMicroPersonGroupUserIdCondition
//					.setMicroPersonGroupId(relateIdint);
//			List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService
//					.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
//			for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
//				Student student = studentService.findOfUser(user.getSchoolId(),
//						miMicroPersonGroupUserId.getUserId());
//				stList.add(student);
//			}
//		}
//		if (stList != null && stList.size() > 0) {
//			for (Student st : stList) {
//				ExamPublishedRecord mpr = this.examPrepareService
//						.searchUserPublishedRecord(microPublishId,
//								st.getUserId(), st.getName(),
//								st.getStudentNumber());
//				if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
//					fst.add(st);
//				} else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
//					ust.add(st);
//				} else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
//					pst.add(st);
//				}
//			}
//		}
//		request.setAttribute("microPublishId", microPublishId);
//		request.setAttribute("relateId", relateId);
//		request.setAttribute("allList", stList);
//		request.setAttribute("finishedList", fst);
//		request.setAttribute("unFinishedList", ust);
//		request.setAttribute("partFinishedList", pst);
//		request.setAttribute("relateType", relateType);
//		return DIR + "/batchReviews";
//	}
//
//	@RequestMapping(value = "/saveReviews")
//	public String saveReviews(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String data = request.getParameter("reviewData");
//		JSONObject obj = JSONObject.fromObject(data);
//		String reviews = (String) obj.get("reviews");
//		String microPublishId = (String) obj.get("microPublishId");
//		Integer reward = (Integer) obj.get("reward");
//		JSONArray studentList = (JSONArray) obj.get("studentList");
//		for (int i = 0; i < studentList.size(); i++) {
//			Integer userId = (Integer) studentList.get(i);
//			ExamPublishedRecord mpr = this.examPrepareService
//					.updateUserPublishedRecord(microPublishId, userId, reviews,
//							reward);
//		}
//		return null;
//	}
//
//	@RequestMapping(value = "/editReviews")
//	public String editReviews(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String microPublishId = request.getParameter("microPublishedId");
//		Integer userId = Integer.parseInt(request.getParameter("userId"));
//		ExamPublishedRecord mpr = this.examPrepareService
//				.searchUserPublishedRecord(microPublishId, userId, null, null);
//		request.setAttribute("mpr", mpr);
//		return DIR + "/editReviews";
//	}
//
//	@RequestMapping(value = "/watchReviews")
//	public String watchReviews(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String microPublishId = request.getParameter("microPublishedId");
//		Integer userId = Integer.parseInt(request.getParameter("userId"));
//		ExamPublishedRecord mpr = this.examPrepareService
//				.searchUserPublishedRecord(microPublishId, userId, null, null);
//		request.setAttribute("mpr", mpr);
//		return DIR + "/watchReviews";
//	}
//
//	@RequestMapping(value = "/publishDetails")
//	public String publishDetails(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) {
//		String microPublishId = request.getParameter("microPublishedId");
//		String relateId = request.getParameter("relateId");
//		String relateType = request.getParameter("relateType");
//		String index = request.getParameter("index");
//		Integer relateIdint = Integer.parseInt(relateId);
//		Integer finishedCount = 0;
//		Integer unFinishedCount = 0;
//		Integer partFinishedCount = 0;
//		List<ExamPublishedRecord> recordList = new ArrayList<ExamPublishedRecord>();
//		List<Student> stList = new ArrayList<Student>();
//		ExamRelateVo rvo = new ExamRelateVo();
//		if (relateType == TagsTypeContans.TEAM_TYPE
//				|| relateType.equals(TagsTypeContans.TEAM_TYPE)) {
//			stList = this.studentService.findStudentOfTeam(relateIdint);
//			rvo = this.examPrepareService.searchPublishedLesson(microPublishId,
//					Integer.parseInt(relateId), relateType);
//		} else if (relateType == TagsTypeContans.GROUP_TYPE
//				|| relateType.equals(TagsTypeContans.GROUP_TYPE)) {
//			MiMicroPersonGroupUserIdCondition miMicroPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
//			miMicroPersonGroupUserIdCondition
//					.setMicroPersonGroupId(relateIdint);
//			List<MiMicroPersonGroupUserId> list = microPersonGroupUserIdService
//					.findMiMicroPersonGroupUserIdByCondition(miMicroPersonGroupUserIdCondition);
//			for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : list) {
//				Student student = studentService.findOfUser(user.getSchoolId(),
//						miMicroPersonGroupUserId.getUserId());
//				stList.add(student);
//			}
//			rvo = this.examPrepareService.searchPublishedLesson(microPublishId,
//					Integer.parseInt(relateId), relateType);
//		}
//
//		if (stList != null && stList.size() > 0) {
//			for (Student st : stList) {
//				ExamPublishedRecord mpr = this.examPrepareService
//						.searchUserPublishedRecord(microPublishId,
//								st.getUserId(), st.getName(),
//								st.getStudentNumber());
//				if (mpr.getFinishedFlag() == StudyFinishedFlag.FINISHED) {
//					finishedCount++;
//				} else if (mpr.getFinishedFlag() == StudyFinishedFlag.NOT_FINISHED) {
//					unFinishedCount++;
//				} else if (mpr.getFinishedFlag() == StudyFinishedFlag.PART_FINISHED) {
//					partFinishedCount++;
//				}
//				recordList.add(mpr);
//			}
//		}
//		request.setAttribute("micro", rvo);
//		request.setAttribute("finishedCount", finishedCount);
//		request.setAttribute("partFinishedCount", partFinishedCount);
//		request.setAttribute("unFinishedCount", unFinishedCount);
//		request.setAttribute("relateType", relateType);
//		request.setAttribute("recordList", recordList);
//		if (index != null && !"".equals(index)) {
//			return DIR + "/publishDetailsIndex";
//		} else {
//			return DIR + "/publishDetailsList";
//		}
//	}
//
//	@RequestMapping(value = "/loadDetail")
//	public String loadDetail(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user)
//			throws IOException {
//		request.setCharacterEncoding("utf-8");
//		String microId = request.getParameter("microId");
//		String publishLessonId = request.getParameter("publishLessonId");
//		String publishedMicro = request.getParameter("publishedMicro");
//		Integer userId = Integer.parseInt(request.getParameter("userId"));
//		ExamUserRecord ur = this.examUserRecordService.getUniqueRecord(userId,
//				microId, publishLessonId);
//		List<FileResult> resultList = new ArrayList<FileResult>();
//		if (ur != null) {
//			String fileIds = ur.getEntityId();
//			if (fileIds != null && !"".equals(fileIds)) {
//				if (fileIds.contains(",")) {
//					String[] fileId = fileIds.split(",");
//					for (String fi : fileId) {
//						FileResult result = fileService.findFileByUUID(fi);
//						if (result != null) {
//							resultList.add(result);
//						}
//					}
//				} else {
//					FileResult result = fileService.findFileByUUID(fileIds);
//					if (result != null) {
//						resultList.add(result);
//					}
//				}
//			}
//		}
//		request.setAttribute("ur", ur);
//		request.setAttribute("publishedMicro", publishedMicro);
//		request.setAttribute("resultList", resultList);
//		return DIR + "/detailList";
//	}
//
//	@RequestMapping(value = "/getRecord")
//	public String getRecord(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user)
//			throws IOException {
//		String microId = request.getParameter("microId");
//		String publishLessonId = request.getParameter("publishLessonId");
//		Integer userId = user.getId();
//		ExamUserRecord ur = this.examUserRecordService.getUniqueRecord(userId,
//				microId, publishLessonId);
//		PrintWriter pw = setAjaxResponse(request, response);
//		if (ur != null) {
//			if (ur.getLastPlayTime() != null) {
//				pw.print(ur.getLastPlayTime());
//			} else {
//				pw.print("fail");
//			}
//		} else {
//			pw.print("fail");
//		}
//		return null;
//	}
//
//	@RequestMapping(value = "/saveRecord")
//	public String saveRecord(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String microId = request.getParameter("microId");
//		String lastPlayTime = request.getParameter("lastPlayTime");
//		String finishedFlag = request.getParameter("finishedFlag");
//		String publishLessonId = request.getParameter("publishLessonId");
//		String publisherId = request.getParameter("publisherId");
//		Integer userId = user.getId();
//		String content = request.getParameter("content");
//		String entityId = request.getParameter("entityId");
//		ExamUserRecord ur = this.examUserRecordService.getUniqueRecord(userId,
//				microId, publishLessonId);
//		if (ur != null) {
//			ur.setModifyDate(new Date());
//			ur.setPlayTime(ur.getPlayTime() + 1);
//			ur.setContent(content);
//			ur.setEntityId(entityId);
//			if (finishedFlag != null && !"".equals(finishedFlag)) {
//				int flag = Integer.parseInt(finishedFlag);
//				if (flag == StudyFinishedFlag.FINISHED) {
//					ur.setFinishedDate(new Date());
//
//					// 推送消息
//					if (publisherId != null && !"".equals(publisherId)) {
//						Message message = new Message();
//						message.setAppId(SysContants.SYSTEM_APP_ID);
//						message.setContent("您好，您的学生" + user.getRealName()
//								+ "完成了您布置的试卷作业！");
//						message.setPosterId(user.getId());
//						message.setPosterName(user.getRealName());
//						message.setRecordStatus(StatusDefaultContans.ZERO);
//						message.setTag(MessageCenterContants.FINISHED_PATH_CODE_EXAM);
//						PushMessageUtil.sendMessage(message,
//								Integer.parseInt(publisherId));
//						PushMessageUtil.pushMessage(Integer
//								.parseInt(publisherId));
//					}
//				}
//				ur.setFinishedFlag(flag);
//			}
//			if (lastPlayTime != null && !"".equals(lastPlayTime)
//					&& !"0".equals(lastPlayTime)) {
//				ur.setLastPlayTime(Double.parseDouble(lastPlayTime));
//			}
//			this.examUserRecordService.modify(ur);
//		} else {
//			ur = new ExamUserRecord();
//			ur.setCreateDate(new Date());
//			ur.setModifyDate(new Date());
//			ur.setMicroId(microId);
//			ur.setUserId(user.getId());
//			ur.setUserName(user.getRealName());
//			ur.setPlayTime(1);
//			ur.setContent(content);
//			ur.setEntityId(entityId);
//			ur.setPublishLessonId(publishLessonId);
//			if (finishedFlag != null && !"".equals(finishedFlag)) {
//				int flag = Integer.parseInt(finishedFlag);
//				if (flag == StudyFinishedFlag.FINISHED) {
//					ur.setFinishedDate(new Date());
//					// 推送消息
//					/*
//					 * if (publisherId != null && !"".equals(publisherId)) {
//					 * Message message = new Message();
//					 * message.setAppId(SysContants.SYSTEM_APP_ID);
//					 * message.setContent("您好，您的学生" + user.getRealName() +
//					 * "完成了您布置的作业！"); message.setPosterId(user.getId());
//					 * message.setPosterName(user.getRealName());
//					 * message.setRecordStatus(StatusDefaultContans.ZERO);
//					 * message
//					 * .setTag(MessageCenterContants.FINISHED_PATH_CODE_HOMEWORK
//					 * ); PushMessageUtil.sendMessage(message,
//					 * Integer.parseInt(publisherId));
//					 * PushMessageUtil.pushMessage
//					 * (Integer.parseInt(publisherId)); }
//					 */
//				}
//				ur.setFinishedFlag(flag);
//			}
//			this.examUserRecordService.add(ur);
//		}
//
//		return null;
//	}
//
//	@RequestMapping(value = "/downloadFile")
//	public String downloadFile(HttpServletRequest request,
//			HttpServletResponse response) throws IOException {
//		String entId = request.getParameter("entityId");
//		String downloadTitle = null;
//		String suffix = null;
//		if (entId != null && !"".equals(entId)) {
//			EntityFile ent = this.entityFileService.findFileById(Integer
//					.parseInt(entId));
//			downloadTitle = ent.getFileName();
//			if (ent != null) {
//				suffix = ent.getExtension();
//				String filename = DownloadUtil.encodeFilenameForDownload(
//						request, URLDecoder.decode(downloadTitle, "UTF-8"));
//				request.setCharacterEncoding("UTF-8");
//				response.setCharacterEncoding("UTF-8");
//				response.setContentType("application/x-download");
//				response.setContentLength(ent.getSize().intValue());
//				response.setHeader("Content-Disposition",
//						"attachment;filename=" + filename);
//				String flag = this.fileService.download(ent.getUuid(),
//						response.getOutputStream());
//			} else {
//				PrintWriter pw = this.setAjaxResponse(request, response);
//				pw.print("<script type=\"text/javascript\">alert(\"源文件不存在\")</script>");
//			}
//		}
//		return null;
//	}
//
//	@RequestMapping(value = "/myExam")
//	public String myExam(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) {
//		String index = request.getParameter("index");
//		page.setPageSize(5);
//		List<MyResourceVo> reslist = new ArrayList<MyResourceVo>();
//		ResourceCondition condition = new ResourceCondition();
//		condition.setUserId(user.getId());
//		condition.setIsPersonal(true);
//		condition.setResType(ResourceType.EXAM);
//		condition.setIsDeleted(false);
//		List<Resource> resourceList = this.resourceService
//				.findResourceByCondition(condition, page,
//						Order.desc("create_date"));
//		reslist = findVoByType(resourceList);
//		request.setAttribute("reslist", reslist);
//		if (index != null && !"".equals(index)) {
//			return DIR + "/myExamIndex";
//		} else {
//			return DIR + "/myExamList";
//		}
//	}
//
//	@RequestMapping(value = "/studyList")
//	public String studyList(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) throws IOException {
//		Student s = this.studentService.findStudentById(user.getStudentId());
//		if (s != null) {
//			List<Integer> relateId = new ArrayList<Integer>();
//			MiMicroPersonGroupUserIdCondition microPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
//			microPersonGroupUserIdCondition.setUserId(user.getId());
//			microPersonGroupUserIdCondition.setType("1");
//			List<MiMicroPersonGroupUserId> microPersonGroupUserIds = microPersonGroupUserIdService
//					.findMiMicroPersonGroupUserIdByCondition(microPersonGroupUserIdCondition);
//			for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : microPersonGroupUserIds) {
//				if (miMicroPersonGroupUserId != null) {
//					relateId.add(miMicroPersonGroupUserId
//							.getMicroPersonGroupId());
//				}
//			}
//			Integer teamId = s.getTeamId();
//			String teamName = s.getTeamName();
//			if (teamId != null) {
//				relateId.add(teamId);
//				Date fdate = new Date();
//				List<ExamRelateVo> mlrvList = this.examPrepareService
//						.searchHistoryPublishedLesson(null, relateId, null,
//								fdate, false, page);
//				request.setAttribute("microList", mlrvList);
//			} else {
//				request.setAttribute("errorFlag", "no_class");
//			}
//		} else {
//			request.setAttribute("errorFlag", "not_a_student");
//		}
//		return DIR + "/studyList";
//	}
//
//	@RequestMapping(value = "/studyHistory")
//	public String studyHistory(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) throws IOException {
//		Student s = this.studentService.findStudentById(user.getStudentId());
//		String index = request.getParameter("index");
//		if (s != null) {
//			Integer teamId = s.getTeamId();
//			List<Integer> relateId = new ArrayList<Integer>();
//			MiMicroPersonGroupUserIdCondition microPersonGroupUserIdCondition = new MiMicroPersonGroupUserIdCondition();
//			microPersonGroupUserIdCondition.setUserId(user.getId());
//			microPersonGroupUserIdCondition.setType("1");
//			List<MiMicroPersonGroupUserId> microPersonGroupUserIds = microPersonGroupUserIdService
//					.findMiMicroPersonGroupUserIdByCondition(microPersonGroupUserIdCondition);
//			for (MiMicroPersonGroupUserId miMicroPersonGroupUserId : microPersonGroupUserIds) {
//				if (miMicroPersonGroupUserId != null) {
//					relateId.add(miMicroPersonGroupUserId
//							.getMicroPersonGroupId());
//				}
//			}
//			if (teamId != null) {
//				relateId.add(teamId);
//				Date fdate = new Date();
//				List<ExamStudyRecordVo> msrvList = new ArrayList();
//				List<String> subjectList = this.examPrepareService
//						.findSubjectNameByExam(null, relateId, fdate, true);
//				String subjectName = request.getParameter("subjectName");
//				// 搜索全部
//				if ("all".equals(subjectName)) {
//					subjectName = null;
//				}
//				List<ExamRelateVo> mlrvList = this.examPrepareService
//						.searchHistoryPublishedLesson(null, relateId,
//								subjectName, fdate, true, page);
//				if (mlrvList != null && mlrvList.size() > 0) {
//					for (ExamRelateVo mr : mlrvList) {
//						ExamPublishedRecord mpr = this.examPrepareService
//								.searchUserPublishedRecord(
//										mr.getPublishMicroLessonId(),
//										user.getId(), s.getName(),
//										s.getStudentNumber());
//						// String rsn =
//						// mr.getRelateName().substring(mr.getRelateName().indexOf("[")
//						// + 1, mr.getRelateName().indexOf("]"));
//						ExamStudyRecordVo vo = new ExamStudyRecordVo();
//						vo.setMlrv(mr);
//						vo.setMpr(mpr);
//						msrvList.add(vo);
//					}
//				}
//				request.setAttribute("subjectList", subjectList);
//				request.setAttribute("recordList", msrvList);
//			} else {
//				request.setAttribute("errorFlag", "no_class");
//			}
//		} else {
//			request.setAttribute("errorFlag", "not_a_student");
//		}
//		if (index != null && !"".equals(index)) {
//			return DIR + "/studyHistoryIndex";
//		} else {
//			return DIR + "/studyHistoryList";
//		}
//	}
//
//	private Map getClassGradeMap(UserInfo user, boolean includeSubject,
//			boolean includeSameClass, boolean includeType) {
//		Map classGradeMap = new LinkedHashMap();
//		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
//				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
//		Teacher teacher = this.teacherService.findOfUser(user.getSchoolId(),
//				user.getId());
//		if (teacher != null && schoolTermCurrent != null) {
//			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
//			teamTeacherCondition.setTeacherId(teacher.getId());
//			// 1 班主任 2 任课教师
//			teamTeacherCondition.setType(2);
//			teamTeacherCondition.setSchoolYear(schoolTermCurrent
//					.getSchoolYear());
//			List<TeamTeacher> teamTeacherList = teamTeacherService
//					.findTeamTeacherByCondition(teamTeacherCondition, null,
//							Order.asc("team_id"));
//			MiMicroPersonGroupCondition miMicroPersonGroupCondition = new MiMicroPersonGroupCondition();
//			miMicroPersonGroupCondition.setUserId(teacher.getUserId());
//			for (TeamTeacher tt : teamTeacherList) {
//				Grade grade = this.gradeService.findGradeById(tt.getGradeId());
//				if (grade != null) {
//					List<Map> classList = new ArrayList();
//					String viewName = grade.getName() + "&&"
//							+ grade.getUniGradeCode();
//					if (classGradeMap.containsKey(viewName)) {
//						classList = (List<Map>) classGradeMap.get(viewName);
//					}
//					miMicroPersonGroupCondition.setGradeId(tt.getGradeId());
//					List<MiMicroPersonGroup> personGroups = this.miMicroPersonGroupService
//							.findMiMicroPersonGroupByCondition(miMicroPersonGroupCondition);
//					if (!personGroups.isEmpty() && personGroups != null) {
//						if (!classGradeMap.containsKey(viewName)) {
//							for (MiMicroPersonGroup personGroup : personGroups) {
//								Map mmap = new HashMap();
//								String personGroupName = personGroup.getName();
//								if (includeType) {
//									personGroupName = personGroupName + "&&"
//											+ TagsTypeContans.GROUP_TYPE;
//								}
//								mmap.put(personGroup.getId(), personGroupName);
//								classList.add(mmap);
//							}
//						}
//
//					}
//					Team team = this.teamService.findTeamById(tt.getTeamId());
//					if (team != null) {
//						Map map = new HashMap();
//						String classSubjectName = team.getName();
//						if (includeSubject) {
//							classSubjectName = classSubjectName + "   ["
//									+ tt.getSubjectName() + "]";
//						}
//						if (includeType) {
//							classSubjectName = classSubjectName + "&&"
//									+ TagsTypeContans.TEAM_TYPE;
//						}
//						map.put(team.getId(), classSubjectName);
//						if (includeSameClass) {
//							classList.add(map);
//						} else if (!classList.contains(map)) {
//							classList.add(map);
//						}
//					}
//					classGradeMap.put(viewName, classList);
//				}
//			}
//		}
//		return classGradeMap;
//	}
//
//	private PrintWriter setAjaxResponse(HttpServletRequest request,
//			HttpServletResponse response) throws UnsupportedEncodingException,
//			IOException {
//		response.setContentType("text/html;charset=UTF-8");
//		request.setCharacterEncoding("utf-8");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Expires", -1);
//		return response.getWriter();
//	}
//
//	/**
//	 * 通过资源集合和类型封装
//	 * 
//	 * @param resourceList
//	 * @param typeint
//	 * @return
//	 */
//	private List<MyResourceVo> findVoByType(List<Resource> resourceList) {
//		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
//		for (Resource r : resourceList) {
//			MyResourceVo mrvo = new MyResourceVo();
//			EntityFile ent = new EntityFile();
//			mrvo.setResEnt(r);
//			Exam em = new Exam();
//			em = this.examService.findExamByUuid(r.getObjectId());
//			if (em != null) {
//
//				ent = this.entityFileService.findFileByUUID(em.getEntityId());
//			}
//			if (ent != null && ent.getThumbnailUrl() != null) {
//				mrvo.setThumbnailUrl(ent.getThumbnailUrl());
//				mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
//			} else {
//				mrvo.setThumbnailUrl("");
//			}
//			list.add(mrvo);
//		}
//
//		return list;
//	}
//
//	private List<MyResourceVo> findVoToMyResourceVo(
//			List<ResourceVo> resourceList) {
//		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
//		for (ResourceVo r : resourceList) {
//			MyResourceVo mrvo = new MyResourceVo();
//			EntityFile ent = new EntityFile();
//			mrvo.setResEnt(r);
//			Exam em = new Exam();
//			em = this.examService.findExamByUuid(r.getObjectId());
//			if (em != null) {
//
//				ent = this.entityFileService.findFileByUUID(em.getEntityId());
//			}
//			if (ent != null && ent.getThumbnailUrl() != null) {
//				mrvo.setThumbnailUrl(ent.getThumbnailUrl());
//				mrvo.setIconType(IconUtil.setIcon(ent.getExtension()));
//			} else {
//				mrvo.setThumbnailUrl("");
//			}
//			list.add(mrvo);
//		}
//
//		return list;
//	}
//
//	/**
//	 * 我上传的资源
//	 * 
//	 * @param
//	 */
//	@RequestMapping(value = "/myResource")
//	public String myResource(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) {
//		String index = request.getParameter("index");
//		String title = request.getParameter("title");
//		String resType = request.getParameter("resType");
//		String personType = request.getParameter("personType");
//		if (personType == null) {
//			personType = ContansOfResource.SCHOOLRESOURCE;
//		}
//		// Integer relateAppId = this.getRelateApp(request);
//		page.setPageSize(5);
//		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
//		Integer typeint = Integer.parseInt(resType);
//		ResourceLibraryCondition resourceLibraryCondition = new ResourceLibraryCondition();
//		resourceLibraryCondition.setOwerId(user.getSchoolId());
//		List<ResourceLibrary> resourceLibraryList = this.resourceLibraryService
//				.findResourceLibraryByCondition(resourceLibraryCondition);
//		ResourceLibrary resourceLibrary = new ResourceLibrary();
//		if (resourceLibraryList != null && resourceLibraryList.size() > 0) {// 如果存在取对应的uui
//			resourceLibrary = resourceLibraryList.get(0);
//		} else {// 如果不存在添加对应的记录
//			ResourceLibrary resourceLibraryAdd = new ResourceLibrary();
//			resourceLibraryAdd.setOwerId(user.getSchoolId());
//			resourceLibraryAdd.setUuid(UUIDUtil.getUUID());// 获取唯一值uuid
//			School school = schoolService.findSchoolById(user.getSchoolId());
//			resourceLibraryAdd.setName(school.getName());
//			// resourceLibrary.setAppId(relateAppId);
//			resourceLibrary = this.resourceLibraryService
//					.add(resourceLibraryAdd);
//		}
//		ResourceCondition resResourceCondition = new ResourceCondition();
//		resResourceCondition.setUserId(user.getId());
//
//		if (resType != null && !resType.equals("")) {
//
//			resResourceCondition.setResType(Integer.valueOf(resType));
//		}
//		resResourceCondition.setTitle(title);
//		// 校本资源筛选
//		if (personType.equals(ContansOfResource.SCHOOLRESOURCE)) {
//			resResourceCondition.setLibraryId(resourceLibrary.getUuid());
//			resResourceCondition.setIsPersonal(false);
//			resResourceCondition
//					.setVerify(ResourceCondition.DEFAULT_UPLOAD_YES);
//		}
//		// 各人资源筛选
//		if (personType.equals(ContansOfResource.PERSONRESOURCE)) {
//			resResourceCondition.setIsPersonal(true);
//		}
//		ResourceVoCondition condition = new ResourceVoCondition();
//		condition.setRelateSchoolId(user.getSchoolId());
//		condition.setUserId(user.getId());
//		condition.setPersonal(true);
//		condition.setAppId(1);
//		condition.setResType(ResourceType.EXAM);
//		if (personType != null && !"".equals(personType)) {
//			condition.setVerify(personType);
//			if (personType.equals(ContansOfResource.SHARERESOURCE)) {
//				condition.setPersonal(false);
//			}
//		} else {
//			/** 校本资源 */
//			condition.setVerify(ContansOfResource.SCHOOLRESOURCE);
//			condition.setPersonal(false);
//		}
//
//		page.setPageSize(5);
//		/** 查找我的资源: 我上传的, 我共享的, 我收藏的 */
//		List<ResourceVo> vos = this.resourceService
//				.findExpResourceVoByCondition(condition, page,
//						Order.desc("create_date"));
//		list = findVoToMyResourceVo(vos);
//		String Url = "/myResource";
//		if (ContansOfResource.SHARERESOURCE.equals(personType)) {
//			Url = "/myResource/myShare";
//		} else if (ContansOfResource.FAVRESOURCE.equals(personType)) {
//			Url = "/favResource";
//		}
//		request.setAttribute("Url", Url);
//		request.setAttribute("reslist", list);
//		request.setAttribute("resType", resType);
//		request.setAttribute("personType", personType);
//
//		if (index != null && !"".equals(index)) {
//			return DIR + "/myExamIndex";
//		} else {
//			return DIR + "/myExamList";
//		}
//	}
//
//	/**
//	 * 我收藏的资源
//	 * 
//	 * @param
//	 */
//	@RequestMapping(value = "/favResource")
//	public String favResource(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) {
//		String index = request.getParameter("index");
//		String title = request.getParameter("title");
//		String resType = request.getParameter("resType");
//		String personType = request.getParameter("personType");
//		page.setPageSize(5);
//		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
//		// String playUrl = "";
//		List<Resource> resources = new ArrayList<Resource>();
//		/** 查找个人试卷(xep) */
//		ResourceVoCondition condition = new ResourceVoCondition();
//		condition.setRelateSchoolId(user.getSchoolId());
//		condition.setUserId(user.getId());
//		condition.setPersonal(true);
//		condition.setAppId(1);
//		if (personType != null && !"".equals(personType)) {
//			condition.setVerify(personType);
//		} else {
//			/** 校本资源 */
//			condition.setVerify(ContansOfResource.SCHOOLRESOURCE);
//			condition.setPersonal(false);
//		}
//
//		page.setPageSize(5);
//		/** 查找我的资源: 我上传的, 我共享的, 我收藏的 */
//		List<ResourceVo> vos = this.resourceService
//				.findExpResourceVoByCondition(condition, page,
//						Order.desc("create_date"));
//		list = findVoToMyResourceVo(vos);
//		String Url = "/myResource";
//		if (ContansOfResource.SHARERESOURCE.equals(personType)) {
//			Url = "/myResource/myShare";
//		} else if (ContansOfResource.FAVRESOURCE.equals(personType)) {
//			Url = "/favResource";
//		}
//		request.setAttribute("reslist", list);
//		request.setAttribute("resType", resType);
//		request.setAttribute("personType", personType);
//		request.setAttribute("Url", Url);
//		if (index != null && !"".equals(index)) {
//			return DIR + "/myExamIndex";
//		} else {
//			return DIR + "/myExamList";
//		}
//	}
//
//	/**
//	 * 我的共享
//	 * 
//	 * @param
//	 */
//	@RequestMapping(value = "/myResource/myShare")
//	public String myShare(HttpServletRequest request,
//			HttpServletResponse response, @ModelAttribute("page") Page page,
//			@CurrentUser UserInfo user) {
//		String index = request.getParameter("index");
//		String resType = request.getParameter("resType");
//		String personType = request.getParameter("personType");
//		String title = request.getParameter("title");
//		List<MyResourceVo> list = new ArrayList<MyResourceVo>();
//		ResourceVoCondition condition = new ResourceVoCondition();
//		condition.setRelateSchoolId(user.getSchoolId());
//		condition.setUserId(user.getId());
//		condition.setPersonal(true);
//		condition.setAppId(1);
//
//		if (personType != null && !"".equals(personType)) {
//			condition.setVerify(personType);
//			if (personType.equals(ContansOfResource.SHARERESOURCE)) {
//				condition.setPersonal(false);
//			}
//		} else {
//			/** 校本资源 */
//			condition.setVerify(ContansOfResource.SCHOOLRESOURCE);
//			condition.setPersonal(false);
//		}
//
//		page.setPageSize(5);
//		/** 查找我的资源: 我上传的, 我共享的, 我收藏的 */
//		List<ResourceVo> vos = this.resourceService
//				.findExpResourceVoByCondition(condition, page,
//						Order.desc("create_date"));
//		list = findVoToMyResourceVo(vos);
//		String Url = "/myResource";
//		if (ContansOfResource.SHARERESOURCE.equals(personType)) {
//			Url = "/myResource/myShare";
//		} else if (ContansOfResource.FAVRESOURCE.equals(personType)) {
//			Url = "/favResource";
//		}
//		request.setAttribute("Url", Url);
//		request.setAttribute("reslist", list);
//		request.setAttribute("resType", resType);
//		request.setAttribute("personType", personType);
//		if (index != null && !"".equals(index)) {
//			return DIR + "/myExamIndex";
//		} else {
//			return DIR + "/myExamList";
//		}
//	}
//
//	private Map findAllClassByUser(Integer schoolId) {
//		List<Grade> grades = new ArrayList<Grade>();
//		Map classGradeMap = new LinkedHashMap();
//		SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService
//				.findSchoolTermCurrentBySchoolId(schoolId);
//		grades = this.gradeService.findGradeBySchoolYear(schoolId,
//				schoolTermCurrent.getSchoolYear());
//		for (Grade g : grades) {
//
//			List<Map> classList = new ArrayList();
//			String viewName = g.getName() + "&&" + g.getUniGradeCode();
//			if (classGradeMap.containsKey(viewName)) {
//				classList = (List<Map>) classGradeMap.get(viewName);
//			}
//			List<Team> teams = this.teamService.findTeamOfGrade(g.getId());
//			for (Team team : teams) {
//				Map map = new HashMap();
//				map.put(team.getId(), team.getName());
//				classList.add(map);
//
//			}
//			classGradeMap.put(viewName, classList);
//		}
//		return classGradeMap;
//	}
//
//	/**
//	 * 查看题目的正确率
//	 * 
//	 * @param paperId
//	 *            xep试卷id
//	 * @param teamId
//	 *            班级id
//	 * @param subject
//	 *            相关科目
//	 * @param time
//	 *            导学案学习时间范围
//	 * @param title
//	 *            导学案标题
//	 */
//	@RequestMapping(value = "/question")
//	public ModelAndView questionDeteil(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user,
//			@RequestParam("paperId") Integer paperId,
//			@RequestParam("teamId") Integer teamId,
//			@RequestParam("time") String time,
//			@RequestParam("title") String title,
//			@RequestParam("taskId") Integer taskId)
//			throws UnsupportedEncodingException {
//		ModelAndView modelAndView = new ModelAndView();
//
//		// 而get方式请求，若url含非西欧编码必然会乱码，处理方式：
//		request.setCharacterEncoding("utf-8");
//
//		if (title != null && !title.equals("")) {
//			// title=new String(title.getBytes("ISO-8859-1"), "UTF-8");
//			modelAndView.addObject("title", title);
//		}
//		if (time != null && !time.equals("")) {
//			// time=new String(time.getBytes("ISO-8859-1"), "UTF-8");
//			modelAndView.addObject("time", time);
//		}
//
//		if (teamId != null) {
//			/** 获取班级名称 */
//			Team team = teamService.findTeamById(teamId);
//			if (team != null) {
//				modelAndView.addObject("teamName", team.getName());
//			}
//		}
//		/** 获取题目的正确率 */
//		List<UserRank> rankList = new ArrayList<UserRank>();
//		rankList = this.paperHandleService
//				.findPaperQuestionCorrectRateByPaperId(paperId, PaperType.EXAM,
//						taskId, teamId);
//		modelAndView.addObject("items", rankList);
//		modelAndView.setViewName(DIR + "/question");
//		return modelAndView;
//	}
//
//	/**
//	 * 我发布的任务
//	 * 
//	 * @param request
//	 * @param response
//	 * @param user
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping(value = "/myPublish")
//	public ModelAndView myPublish(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user,
//			@ModelAttribute("page") Page page) {
//		Order order = new Order();
//		ModelAndView modelAndView = new ModelAndView();
//		order.setProperty("create_date");
//		String title = request.getParameter("title");
//		String index = request.getParameter("index");
//		String path = "/myPublish/index";
//		ExamPublishCondition condition = new ExamPublishCondition();
//		condition.setTitle(title);
//		condition.setPublisherId(user.getId());
//		List<ExamPublish> list = this.examPublishService
//				.findExamPublishByCondition(condition, page, order);
//		if (!index.equals("") && index.equals("list")) {
//			path = "/myPublish/list";
//		}
//		modelAndView.addObject("items", list);
//		modelAndView.setViewName(DIR + path);
//		return modelAndView;
//
//	}
//
//	/**
//	 * 发布任务编辑页面
//	 * 
//	 * @param request
//	 * @param response
//	 * @param user
//	 * @param page
//	 * @return
//	 */
//	@RequestMapping(value = "/publishEdit")
//	public String publishEdit(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user,
//			@ModelAttribute("page") Page page) {
//		String teamList = "";
//		String uuid = request.getParameter("taskUuId");
//		ExamPublish ep = this.examPublishService.findExamPublishByUuid(uuid);
//		ExamRelateCondition condition = new ExamRelateCondition();
//		condition.setPublishMicroLessonId(uuid);
//		List<ExamRelate> erlist = this.examRelateService
//				.findExamRelateByCondition(condition);
//		if (erlist != null && erlist.size() > 0) {
//			for (ExamRelate er : erlist) {
//				teamList += er.getRelateName() + "、";
//			}
//		}
//		if (teamList.length() > 0) {
//			teamList = teamList.substring(0, teamList.length() - 1);
//		}
//		request.setAttribute("emexampublish", ep);
//		request.setAttribute("teamList", teamList);
//		String path = "/myPublish/input";
//		return DIR + path;
//	}
//
//	@RequestMapping(value = "/{id}")
//	@ResponseBody
//	public ResponseInfomation edit(@PathVariable(value = "id") Integer id,
//			@RequestParam("publishData") String publishData) throws Exception {
//		JSONObject obj = JSONObject.fromObject(publishData);
//		String title = (String) obj.get("title");
//		String startDate = (String) obj.get("startDate");
//		String finishedDate = (String) obj.get("finishedDate");
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		String isCheckString = (String) obj.get("isCheck");
//		Integer isCheck = Integer.valueOf(isCheckString);
//		Date sd = sdf.parse(startDate);
//		Date sf = sdf.parse(finishedDate);
//		ExamPublish emExamPublish = this.examPublishService
//				.findExamPublishById(id);
//		emExamPublish.setStartDate(sd);
//		emExamPublish.setFinishedDate(sf);
//		emExamPublish.setIsCheck(isCheck);
//		emExamPublish.setTitle(title);
//		emExamPublish = this.examPublishService.modify(emExamPublish);
//		ExamRelateCondition mlrc = new ExamRelateCondition();
//		mlrc.setPublishMicroLessonId(emExamPublish.getUuid());
//		List<ExamRelate> mlrList = this.examRelateService
//				.findExamRelateByCondition(mlrc);
//		if (mlrList != null && mlrList.size() > 0) {
//			for (ExamRelate er : mlrList) {
//				er.setStartDate(sd);
//				er.setFinishedDate(sf);
//				this.examRelateService.modify(er);
//			}
//		}
//		return emExamPublish != null ? new ResponseInfomation(
//				emExamPublish.getId(), ResponseInfomation.OPERATION_SUC)
//				: new ResponseInfomation();
//	}
//
//	/**
//	 * 获取学生得分情况
//	 * 
//	 * @param learningPlanId
//	 *            导学案id
//	 * @param teamId
//	 *            班级id
//	 * @param subject
//	 *            相关科目
//	 * @param time
//	 *            导学案学习时间范围
//	 * @param title
//	 *            导学案标题
//	 */
//	@RequestMapping(value = "/scope")
//	public ModelAndView scropDeteil(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user,
//			@RequestParam("paperId") Integer paperId,
//			@RequestParam("teamId") Integer teamId,
//			@RequestParam("time") String time,
//			@RequestParam("title") String title,
//			@RequestParam("taskId") Integer taskId)
//			throws UnsupportedEncodingException {
//		ModelAndView modelAndView = new ModelAndView();
//
//		// 而get方式请求，若url含非西欧编码必然会乱码，处理方式：
//		request.setCharacterEncoding("utf-8");
//
//		if (time != null && !time.equals("")) {
//			// time=new String(time.getBytes("ISO-8859-1"), "UTF-8");
//			modelAndView.addObject("time", time);
//		}
//		if (title != null && !title.equals("")) {
//			// title=new String(title.getBytes("ISO-8859-1"), "UTF-8");
//			modelAndView.addObject("title", title);
//		}
//
//		/** 查找班级 */
//		Team team = teamService.findTeamById(teamId);
//		if (team != null) {
//			modelAndView.addObject("teamName", team.getName());
//		}
//
//		/** 获取学生得分情况 */
//		List<UserRank> rankList = paperHandleService.findUserPaperByPaperId(
//				paperId, PaperType.EXAM, taskId, teamId);
//		if (rankList != null) {
//			for (UserRank userRank : rankList) {
//				/** 获取学生信息 */
//				Student student = studentService.findStudentByUserId(userRank
//						.getUserId());
//				if (student == null) {
//					continue;
//				}
//				/** 学号 */
//				if (student.getStudentNumber() != null
//						&& !"".equals(student.getStudentNumber())) {
//					userRank.setStudentNum(student.getStudentNumber());
//				} else {
//					userRank.setStudentNum(student.getStudentNumber2());
//				}
//
//				/** 姓名 */
//				userRank.setStudentName(student.getName());
//			}
//			modelAndView.addObject("items", rankList);
//		}
//		modelAndView.setViewName(DIR + "/scope");
//		return modelAndView;
//	}
//
//	@RequestMapping(value = "/deletePublishedOfTask")
//	@ResponseBody
//	public String deletePublishedOfGrade(HttpServletRequest request,
//			HttpServletResponse response, @CurrentUser UserInfo user) {
//		String publishId = request.getParameter("publishId");
//		ExamRelateCondition condition = new ExamRelateCondition();
//		condition.setPublishMicroLessonId(publishId);
//		List<ExamRelate> erlist = this.examRelateService
//				.findExamRelateByCondition(condition);
//		ExamPublish ep = this.examPublishService
//				.findExamPublishByUuid(publishId);
////		this.paperHandleService.deletePaperInfo(ep.getId(), PaperType.EXAM);
//		if (ep != null) {
//
//			this.examPublishService.remove(ep);
//		}
//		List<Integer> classList = new ArrayList<Integer>();
//		List<Integer> examList = new ArrayList<Integer>();
//		if (erlist != null && erlist.size() > 0) {
//			for (ExamRelate er : erlist) {
//				classList.add(er.getRelateId());
//				examList.add(er.getPjExamId());
//				this.examPrepareService.deletePublish(publishId,
//						er.getRelateId());
//			}
//		}
//		if (classList.size() > 0) {
//			delForStudent(classList,ep.getId(),publishId, user.getSchoolId(),
//					studentService, examPublishedRecordService, examList,
//					pjExamService, paperHandleService,examQuestionService,taskExecutor);
//		}
//		return ResponseInfomation.OPERATION_SUC;
//	}
//
//	/**
//	 * 发布试卷时数据太多，丢到线程
//	 * 
//	 * @param receiverIdList
//	 * @param uuid
//	 * @param schoolId
//	 * @param studentService
//	 * @param examPublishedRecordService
//	 * @param taskExecutor
//	 */
//	public static void publishForStudent(final JSONArray microList,
//			final JSONArray classList, final UserInfo user,
//			final StudentService studentService,
//			final ExamPublishedRecordService examPublishedRecordService,
//			TaskExecutor taskExecutor,
//			final ExamPublishService examPublishService, final Date sd,
//			final Date fd, 
//			final ExamRelateService examRelateService, final Integer isCheck,
//			final ResourceService resourceService,
//			final  StatisticService statisticService
//			) {
//
//		taskExecutor.execute(new Runnable() {
//
//			public void run() {
//				try {
//					List<Integer> receiverIdList = new ArrayList<Integer>();
//					ExamPublish mlp = publishOfPjExam(microList, classList, sd,
//							fd, null, user);
//					mlp.setIsCheck(isCheck);
//					examPublishService.modify(mlp);
//					for (int i = 0; i < classList.size(); i++) {
//						JSONObject receivers = (JSONObject) classList.get(i);
//						String receiverId = (String) receivers.get("relateId");
//						receiverIdList.add(Integer.parseInt(receiverId));
//					}
//					List<Student> studentList = studentService.findStudentListByListId(
//							receiverIdList, TagsTypeContans.TEAM_ID,
//							user.getSchoolId());
//					if (studentList != null) {
//						Date date = new Date();
//						ExamPublishedRecord[] erlist = new ExamPublishedRecord[studentList
//								.size()];
//						int i = 0;
//						for (Student s : studentList) {
//							ExamPublishedRecord mpr = new ExamPublishedRecord();
//							mpr.setCreateDate(date);
//							mpr.setModifyDate(date);
//							mpr.setUserId(s.getUserId());
//							mpr.setUserName(s.getName());
//							mpr.setStudentNumber(s.getStudentNumber());
//							mpr.setPublishedMicroId(mlp.getUuid());
//							mpr.setFinishedFlag(2);
//							mpr.setTeamId(s.getTeamId());
//							// mpr = examPublishedRecordService.add(mpr);
//							erlist[i] = mpr;
//							i++;
//						}
//						examPublishedRecordService.createBatch(erlist);
//
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			private ExamPublish publishOfPjExam(JSONArray microList,
//					JSONArray publishObjectList, Date startDate,
//					Date finishedDate, String title, UserInfo user) {
//
//				JSONObject publishObject = (JSONObject) microList.get(0);
//				String uuid = (String) publishObject.get("id");
//				Object resourceId =  publishObject.get("resourceId");
//				Resource r = new Resource();
//				if(resourceId instanceof Integer){
//					r = resourceService.findResourceById(Integer
//							.valueOf(new Integer(resourceId.toString())));
//				}else{
//					 r = resourceService.findResourceById(Integer
//							.valueOf(Integer.valueOf((String)resourceId)));
//				}
//
//				ExamPublish mlp = new ExamPublish();
//				mlp.setTitle(r.getTitle());
//				if (user.getRealName() != null
//						&& !"".equals(user.getRealName())) {
//					mlp.setPublisherName(user.getRealName());
//				}
//				mlp.setStartDate(startDate);
//				mlp.setFinishedDate(finishedDate);
//				mlp.setUuid(UUIDUtil.getUUID());
//				mlp.setPublisherId(user.getId());
//				mlp.setRealMicroList(microList.toString());
//				mlp.setPublishedFlag(PublishedFlag.PUBLISHED);
//				mlp = examPublishService.add(mlp);
//				Integer pjExamId = 0;
//				for (int i = 0; i < publishObjectList.size(); i++) {
//					JSONObject mlrj = (JSONObject) publishObjectList.get(i);
//					ExamRelate mlr = new ExamRelate();
//					String relateId = (String) mlrj.get("relateId");
//					Object relateName = mlrj.get("relateName");
//					String relateType = (String) mlrj.get("relateType");
//					mlr.setRelateType(relateType);
//					mlr.setRelateId(Integer.parseInt(relateId));
//					mlr.setPublishMicroLessonId(mlp.getUuid());
//					mlr.setStartDate(mlp.getStartDate());
//					mlr.setFinishedDate(mlp.getFinishedDate());
//					mlr.setPublisherId(mlp.getPublisherId());
//					mlr.setPublisherName(mlp.getPublisherName());
//					mlr.setRealMicroList(mlp.getRealMicroList());
//					if (relateName != null && !(relateName instanceof JSONNull)) {
//						mlr.setRelateName(relateName.toString());
//					}
//					mlr = examRelateService.add(mlr);
//						ExamResult examResult=statisticService.initExamStatistics(Integer.valueOf(relateId), user.getSchoolId(), user.getTeacherId(), ExamStatisticsType.EXAM, uuid, mlp.getUuid());
//						if (examResult != null) {
//							PjExam p = examResult.getPjExam();
//							if (p != null) {
//								pjExamId = p.getId();
//							}
//							mlr.setPjExamId(pjExamId);
//							mlr = examRelateService.modify(mlr);
//						}
//
//				}
//				return mlp;
//			}
//
//		});
//
//	}
//
//	public static void delForStudent(final List<Integer> receiverIdList,final Integer taskId,
//			final String taskUuid, final Integer schoolId,
//			final StudentService studentService,
//			final ExamPublishedRecordService examPublishedRecordService,
//			final List<Integer> examList, final PjExamService pjExamService,
//			final PaperHandleService paperHandleService,
//			final ExamQuestionService examQuestionService,
//			TaskExecutor taskExecutor) {
//
//		taskExecutor.execute(new Runnable() {
//
//			public void run() {
//				try {
//					for(Integer teamId :receiverIdList){
//						if(teamId!=null){
//						//	examPublishedRecordService.deleteBytaskUuid(taskUuid, teamId);
//						}
//					}
//				    if(taskId!=null){
//				    	paperHandleService.deletePaperInfo(taskId, PaperType.EXAM);
//				    }
//					if (examList != null && examList.size() > 0) {
//						for (int i = 0; i < examList.size(); i++) {
//							PjExam pjExam = new PjExam();
//							pjExam = pjExamService.findPjExamById(examList
//									.get(i));
////							examQuestionService.findExamErrorByExamId(pjExam.getId());
//							examQuestionService.deleteByExamId(pjExam.getId());
//							pjExamService.abandon(pjExam);
//						}
//					}
//					System.out.print("success");
//					// PushMessageUtil.pushMessageList(receiverIdList);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//
//	}
//	/**
//	 * 发布补录
//	 */
//	@RequestMapping(value = "/rePublic")
//	@ResponseBody
//	public String republic(@RequestParam(value = "userId", required = true)Integer userId,
//			               @RequestParam(value = "teamId", required = true)Integer teamId) {
//		
//		Student s=new Student();
//		s=this.studentService.findStudentByUserId(userId);
//		ExamRelateCondition erc=new ExamRelateCondition();
//		erc.setRelateId(teamId);
//		List<ExamRelate> erlist=this.examRelateService.findExamRelateByCondition(erc);
//	    if(erlist!=null&&erlist.size()>0){
//	    List<ExamStudent> eslist=new ArrayList<ExamStudent>();
//	    for(ExamRelate er:erlist){
//	    	ExamPublishedRecord epr=new ExamPublishedRecord();
//	    	ExamPublishedRecordCondition erpc=new ExamPublishedRecordCondition();
//	    	erpc.setUserId(userId);
//	    	erpc.setPublishedMicroId(er.getPublishMicroLessonId());
//	    	List<ExamPublishedRecord> eprlist=this.examPublishedRecordService.findExamPublishedRecordByCondition(erpc);
//	    	if(eprlist==null||eprlist.size()==0){
//	    		epr.setCreateDate(new Date());
//	    		epr.setModifyDate(new Date());
//	    		epr.setPublishedMicroId(er.getPublishMicroLessonId());
//	    		epr.setFinishedFlag(2);
//	    		epr.setUserId(userId);
//	    		epr.setTeamId(teamId);
//	    		epr.setUserName(s.getName());
//	    		epr.setStudentNumber(s.getStudentNumber());
//	    		this.examPublishedRecordService.add(epr);
//	    		if(er.getPjExamId()!=null){
//	    			ExamStudentCondition esc=new ExamStudentCondition();
//	    			esc.setExamId(er.getPjExamId());
//	    			esc.setUserId(userId);
//	    			eslist=this.examStudentService.findExamStudentByCondition(esc);
//	    			if(eslist==null||eslist.size()==0){
//	    				ExamStudent es=new ExamStudent();
//	    				es.setCreateDate(new Date());
//	    				es.setModifyDate(new Date());
//	    				es.setExamId(er.getPjExamId());
//	    				es.setUserId(userId);
//	    				es.setStudentId(s.getId());
//	    				es.setName(s.getName());
//	    				es.setTestType("01");
//	    				es.setScore(-1f);
//	    				examStudentService.add(es);
//	    			}
//	    		}
//	    	}
//	      }
//	    }
//		    
//		return "success";
//	
//	}
//	/**
//	 * 把这个班的发布任务，每次任务的班级添加
//	 */
////	@RequestMapping(value = "/tab")
////	@ResponseBody
////	public String tab() {
////		ExamRelateCondition erc=new ExamRelateCondition();
////		erc.setRelateId(2440);
////        List<Team> teamlist=teamService.findTeamOfGrade(525);
////        List<Team> teams=new ArrayList<Team>();
////        for(Team t :teamlist){
////        	if(t.getId()!=2440&&t.getId()!=2441){
////        		teams.add(t);
////        	}
////        }
////		List<ExamRelate> erlist=examRelateService.findExamRelateByCondition(erc);
////		if(erlist!=null){
////			for(ExamRelate er:erlist){
////				PjExam pj =new PjExam();
////				pj=this.pjExamService.findPjExamById(er.getPjExamId());
////				this.pjExamService.abandon(pj);
////				String subjectCode = "";
////				String termValue = "";
////				List<UserQuestionResult> userQuestionResultList = null;
////				Paper  pp=papaperService.findPaperByUuid(pj.getPaperUuid());
////					userQuestionResultList = userQuestionService.findUserQuestionByPaperId(pp.getId());
////				for(Team team:teams){
////					ExamRelate vo=new ExamRelate();
////					vo.setFinishedDate(er.getFinishedDate());
////					vo.setInterscoreFinishTime(er.getInterscoreFinishTime());
////					vo.setInterscoreStartTime(er.getInterscoreStartTime());
////					vo.setIsInterscoring(er.getIsInterscoring());
////					vo.setPublisherId(er.getPublisherId());
////					vo.setPublisherName(er.getPublisherName());
////					vo.setPublishMicroLessonId(er.getPublishMicroLessonId());
////					vo.setRealMicroList(er.getRealMicroList());
////					vo.setRelateId(team.getId());
////					vo.setRelateName(team.getName());
////					vo.setTitle(er.getTitle());
////					vo.setRelateType("01");
////					vo.setStartDate(er.getStartDate());
////					pj.setId(null);
////					pj.setTeamId(team.getId());
////					pj.setIsDelete(false);
////					PjExam pgone =pjExamService.InitExamData(pj).getPjExam();
////					vo.setPjExamId(pgone.getId());
////					examRelateService.add(vo);
////					List<ExamQuestion> examQuestionList = new ArrayList<ExamQuestion>();
////					if (userQuestionResultList != null && userQuestionResultList.size() > 0) {
////						for (UserQuestionResult userQuestionResult : userQuestionResultList) {
////							// 初始化ExamQuestion表
////							ExamQuestion examQuestion = new ExamQuestion();
////							examQuestion.setQuestionUuid(userQuestionResult.getQuestionUuid());
////							examQuestion.setExamId(pgone.getId());
////							examQuestion.setQuestionType(userQuestionResult.getQuestionType());
////							examQuestion.setAnswerCount(0);
////							examQuestion.setRightAnswerCount(0);
////							examQuestion.setEmptyCount(0);
////							examQuestion.setScore(0d);
////							examQuestion.setAverageScore(0f);
////							// 添加初始化参数
////							examQuestion.setSubjectCode(userQuestionResult.getSubjectCode());
////							examQuestion.setKnowledgeId(userQuestionResult.getKnowledgeId());
////							if (userQuestionResult.getDifficulity() != null) {
////								examQuestion.setDifficulity(Float.valueOf(userQuestionResult.getDifficulity() + ""));
////							}
////							examQuestion.setCognition(userQuestionResult.getCognition());
////							examQuestion.setFullScore(userQuestionResult.getScore());
////							examQuestion.setTeamRank(0);
////							examQuestion.setGradeRank(0);
////							examQuestion.setTotalTime(0);
////							examQuestion.setAverageTime(0);
////							examQuestion.setIsDeleted(false);
////							examQuestion.setCreateDate(new Date());
////							examQuestionList.add(examQuestion);
////							// examQuestionService.add(examQuestion);
////
////						}
////					}
////
////					examQuestionService.InitExamQuestionData(examQuestionList);
////				}
////				examRelateService.remove(er);
////			}
////		}
////		erc.setRelateId(2441);
////		erlist=examRelateService.findExamRelateByCondition(erc);
////		if(erlist!=null){
////			for(ExamRelate er:erlist){
////				PjExam pj=new PjExam();
////				pj=pjExamService.findPjExamById(er.getPjExamId());
////				pjExamService.abandon(pj);
////				examRelateService.remove(er);
////			}
////		}
////		return "success";
////		
////	}
////	@RequestMapping(value = "/realDelete")
////	@ResponseBody
////	public String realDelete(Integer userId) {
////		ExamPublishCondition ec=new ExamPublishCondition();
////		ec.setPublisherId(userId);
////		List<ExamPublish> eplist=examPublishService.findExamPublishByCondition(ec);
////		if(userId==null&&eplist==null){
////			return "fail";
////		}
////		for(ExamPublish ep:eplist){
////			String uuid =ep.getUuid();
////			examPrepareService.deleteAllPublish(ep.getId());
////			PjExamCondition pc=new PjExamCondition();
////			pc.setJointExamCode(uuid);
////			List<PjExam>pjlist= pjExamService.findPjExamByCondition(pc);
////			for(PjExam pj:pjlist){
////				examQuestionService.deleteByExamId(pj.getId());
////				pjExamService.abandon(pj);
////			}
////			paperHandleService.deletePaperInfo(ep.getId(), PaperType.EXAM);
////		}
////		
////		return "success";
////		
////	}
//}