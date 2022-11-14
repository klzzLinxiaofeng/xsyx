/**   
* @Title: PaperRestServiceImpl.java
* @Package platform.education.rest.paper.service.impl 
* @Description: 题库对外暴露业务接口实现类 
* @author pantq   
* @date 2017年2月24日 下午1:51:45 
* @version V1.0   
*/
package platform.education.rest.paper.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

import framework.generic.dao.Page;
import platform.education.exam.model.ExamPublish;
import platform.education.exam.service.ExamPublishService;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.learningDesign.model.LpTaskUser;
import platform.education.learningDesign.service.LearningPlanHandlerService;
import platform.education.learningDesign.service.LpTaskUserService;
import platform.education.learningDesign.vo.LpTaskUserCondition;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.AnswerSituationResult;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.ResponseEntity;
import platform.education.paper.model.Task;
import platform.education.paper.model.UserPaper;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaperHandleService;
import platform.education.paper.service.PaperService;
import platform.education.paper.service.TaskService;
import platform.education.paper.service.UserPaperService;
import platform.education.paper.util.MqtStringUtil;
import platform.education.paper.vo.PaPaperCondition;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.resource.contants.ResourceType;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;

import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.exam.service.ExamRestService;
import platform.education.rest.learningPlan.service.LearningPlanRestService;
import platform.education.rest.paper.service.PaperRestService;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.service.storage.model.EntityFile;
import platform.service.storage.service.EntityFileService;
import platform.service.storage.service.FileService;

/** 
* @ClassName: PaperRestServiceImpl 
* @Description: 题库对外暴露业务接口实现类 
* @author pantq
* @date 2017年2月24日 下午1:51:45 
*  
*/
public class PaperRestServiceImpl implements PaperRestService{
	
	@Autowired
	@Qualifier("paperHandleService")
	private PaperHandleService paperHandleService;
	
	@Autowired
	@Qualifier("papaperService")
	private PaperService papaperService;
	
	@Autowired
	@Qualifier("userPaperService")
	private UserPaperService userPaperService;
	
	@Autowired
	@Qualifier("examService")
	private ExamService examService;
	
	@Autowired
	@Qualifier("entityFileService")
	private EntityFileService entityFileService;
	
	@Autowired
	@Qualifier("subjectService")
	private SubjectService subjectService;
	
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	
	@Autowired
	@Qualifier("examRestService")
	private ExamRestService examRestService;
	
	@Autowired
	@Qualifier("examPublishService")
	private ExamPublishService examPublishService;
	
	@Autowired
	@Qualifier("learningPlanRestService")
	private LearningPlanRestService learningPlanRestService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	@Autowired
	@Qualifier("lpTaskUserService")
	private LpTaskUserService lpTaskUserService;
	
	@Autowired
	@Qualifier("push_taskExecutor")
	private TaskExecutor taskExecutor;
	
	@Autowired
	@Qualifier("learningPlanHandlerService")
	private LearningPlanHandlerService learningPlanHandlerService;
	
	@Autowired
	@Qualifier("taskService")
	private TaskService taskService;
	
	@Autowired
	@Qualifier("paPaperService")
	private PaPaperService paPaperService;
	
	
	/* (非 Javadoc) 
	* <p>Title: uploadPaperAnswer</p> 
	* <p>Description: </p> 
	* @param appKey
	* @param paperUuId
	* @param userId
	* @param answers
	* @param  questionUuid
	* @param  answer
	* @param  answerTime
	* @return 
	* @see platform.education.rest.paper.service.PaperRestService#uploadPaperAnswer(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.Long) 
	*/
	
	@Override
	public Object uploadPaperAnswer(String appKey,String paperUuId,Integer teamId,
			Double score, final Integer userId, String answers,
			Integer type,final Integer ownerId,final Integer unitId) {
		 ResponseInfo info = new ResponseInfo();
		 ResponseError responseError = new ResponseError();
		 Date date=new Date();
		 Integer resourceType =null;
		 Integer schoolId = null;
		 String termCode = null;
		 String  schoolYear = null;
		 
		 try {
			// 1. 开始处理，判断必须字段不能为空
			if (MqtStringUtil.isNull((paperUuId)) && MqtStringUtil.isNull((userId))) {
				responseError.setResult("9999");
				info.setDetail("参数不能为空:" + paperUuId + userId);
			    info.setMsg("参数不能为空:" + paperUuId + userId);
			    responseError.setInfo(info);
			    return responseError;
			}
			if(teamId==null){
				responseError.setResult("9999");
				info.setDetail("teamID参数不能为空");
			    info.setMsg("teamID参数不能为空");
			    responseError.setInfo(info);
			    return responseError;
			}
			//2.appkey 验证
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
				
			//先判断是否已经做过。
			UserPaperCondition userPaperCondition = new UserPaperCondition();
			userPaperCondition.setPaperUuid(paperUuId);
			userPaperCondition.setUserId(userId);
			userPaperCondition.setType(type);
			userPaperCondition.setOwnerId(ownerId);
			userPaperCondition.setObjectId(unitId);
			List<UserPaper> userPaperList = userPaperService.findUserPaperByCondition(userPaperCondition);

			// 4. 做过题直接返回状态不往下执行。 只适合考试的业务，试卷只提交一次
			if (userPaperList != null && userPaperList.size() > 0) {
				responseError.setResult("-1");
				info.setDetail("该用户已提交过答案，不能重复提交答案!");
		        info.setMsg("该用户已提交过答案，不能重复提交答案!");
		        responseError.setInfo(info);
		        return responseError;
			}	
				
			//通过用户ID查找对应的学校ID
			Student student = this.studentService.findStudentByUserId(userId);
			if(student != null){
				schoolId = student.getSchoolId();
				//获取当前学年、当前学期
				SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
				if(schoolTermCurrent != null){
					termCode = schoolTermCurrent.getSchoolTermCode();
					schoolYear = schoolTermCurrent.getSchoolYear();
				}             
			}
			
			if(PaperType.LEARNING_PLAN == type){ // 导学案
				resourceType =  ResourceType.LEARNING_PLAN;
				paperHandleService.uploadPaperAnswer(paperUuId,teamId,score, userId, answers,type,ownerId,resourceType,schoolYear,termCode,unitId);
				learningPlanHandlerService.unitFinished(userId, ownerId, unitId);
				responseError.setResult("0");
			}else if(PaperType.EXAM == type){ //试卷要限制时间
			    /**
				* 判断学生提交的时间是否在考试允许时间范围内
				*/
				//ExamPublish examPublish = examPublishService.findExamPublishById(ownerId);
				 //判断作答时间时间
				//if (DateUtil.compareDate(endDate,examPublish.getFinishedDate())) {
					
					/*JSONArray  ja=JSONArray.fromObject(examPublish.getRealMicroList());
					JSONObject job = ja.getJSONObject(0);
					String examUuId= null;
					Integer examId = null;
					if(job != null){
						examUuId = job.get("id")+"";
						Exam em=this.examService.findExamByUuid(examUuId);
						examId = em.getId();
					}*/
				    Task task=taskService.findTaskById(ownerId);
				    if(task==null){
				    	info = new ResponseInfo();
						info.setDetail("ownerId不存在,请确认");
						info.setMsg("不存在该ownerId");
						info.setParam("ownerId");
						return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				    }
				    if(date.before(task.getStartTime())){
				    	responseError.setResult("020102");
						info.setDetail("还没到允许作答时间");
				        info.setMsg("还没到允许作答时间");
				        responseError.setInfo(info);
				    }
				    if(date.after(task.getFinishTime())){
				    	responseError.setResult("020102");
								info.setDetail("已超过允许作答时间");
						        info.setMsg("已超过允许作答时间");
						        responseError.setInfo(info);
				    }
					resourceType =  ResourceType.EXAM;
					paperHandleService.uploadPaperAnswer(paperUuId,teamId,score, userId, answers,type,ownerId,resourceType,schoolYear,termCode,unitId);
					//examRestService.unitFinsh(userId,appKey, ownerId, null, date);
					taskService.modifyFinished(ownerId, userId);
					responseError.setResult("0");
				/*
				}else{
					responseError.setResult("020102");
					info.setDetail("已超过允许作答时间");
			        info.setMsg("已超过允许作答时间");
			        responseError.setInfo(info);
				}
				*/
			 }
		} catch (Exception e) {
			e.printStackTrace();
		 	info.setDetail("移动端上传用户作答答案异常");
	        info.setMsg("未知错误");
	        responseError.setResult("000001");
	        responseError.setInfo(info);
	        return responseError;
		}
		return responseError;
	}
	
	
/*	 (非 Javadoc) 
	* <p>Title: isExistFile</p> 
	* <p>Description: </p> 
	* @param fileMd5
	* @param paperUuid
	* @return
	* @throws Exception 
	* @see platform.education.rest.paper.service.PaperRestService#isExistFile(java.lang.String, java.lang.String) 
	
	@Override
	public PaperResult isExistFile(String fileMd5, String paperUuid) throws Exception {
		PaperResult paperResult = new PaperResult();
		Exam exam = null;
		// 1. 判断文件MD5 是否存在服务器
		EntityFile entityFile = entityFileService.findFileByMD5(fileMd5);
		if (entityFile != null) { // 文件存在
			// 2. 判断试卷是否被使用过
			Paper paper = papaperService.findPaperByUuid(paperUuid);
			ExamCondition examCondition = new ExamCondition();
			examCondition.setPaperId(paper.getId());
			List<Exam> examList = examService.findExamByCondition(examCondition);
			if(examList != null && examList.size() > 0){
				exam = examList.get(0);
			}
				
			if (paper != null && exam != null && paper.getUsedCount() == 0) { // 文件存在并没有被使用过
				paperResult.setStatus(Constants.FILEEXISTNOTUSED);
				paperResult.setMsg(Constants.FILEEXISTNOTUSEDMSG);
			} else if (paper != null && exam != null && paper.getUsedCount() > 0) { // 已存在并且试卷被使用过
				paperResult.setStatus(Constants.FILEEXISTUSED);
				paperResult.setMsg(Constants.FILEEXISTUSEDMSG);
			}

		} else { // 文件不存在
			paperResult.setStatus(Constants.FILENOTEXIST);
			paperResult.setMsg(Constants.FILENOTEXISTMSG);
		}

		return paperResult;
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	/* (非 Javadoc) 
	* <p>Title: answerSituation</p> 
	* <p>Description: </p> 
	* @param appKey
	* @param userId
	* @param paperUuId
	* @param ownerId
	* @return
	* @throws Exception 
	* @see platform.education.rest.paper.service.PaperRestService#answerSituation(java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer) 
	*/
	@Override
	public Object answerSituation(String appKey, Integer userId, String paperUuId, Integer ownerId) throws Exception {
		ResponseInfo info = new ResponseInfo();
		List<AnswerSituationResult> answerSituationResultList = null;
		
		try{
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			answerSituationResultList =  paperHandleService.answerSituation(userId,paperUuId,ownerId);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return new ResponseVo<Object>("0",answerSituationResultList);
	}


	@Override
	public Object isExistFile(String appKey ,String fileMd5) throws Exception {
		List<ResponseEntity> responseEntityList = null;
		ResponseInfo info = new ResponseInfo();
		
		//判断APPKEY是否存在
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
		//判断md5是否有值，以","隔开
		if(fileMd5 != null){
			String []md5s = fileMd5.split(",");
			//循环判断该文件在服务器是否存在
			for(String md5:md5s){
				EntityFile entityFile = this.entityFileService.findFileByMD5(md5);
				//文件存在返回md5,uuid,url三个参数给移动端
				responseEntityList = new ArrayList<ResponseEntity>();
				if(entityFile != null){ //文件存在的情况
					ResponseEntity responseEntity = new ResponseEntity();
					String fileUuid = entityFile.getUuid();
					responseEntity.setFileMd5(md5);
					responseEntity.setFileUuid(fileUuid);
					String fileUrl = fileService.relativePath2HttpUrlByUUID(fileUuid);
					responseEntity.setFileUrl(fileUrl);
					responseEntity.setIsExist(true);
					responseEntityList.add(responseEntity);
				}else{//文件不存在的情况
					ResponseEntity responseEntity = new ResponseEntity();
					responseEntity.setIsExist(false);
					responseEntityList.add(responseEntity);
				}
				
			}
			
		}
		
		return new ResponseVo<Object>("0",responseEntityList);
	}


	@Override
	public Object test(String appKey) {
		
		Page page = new Page();
		page.setEnableGetTolaRows(false);
		LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
		lpTaskUserCondition.setUserId(Integer.valueOf(appKey));
		List<LpTaskUser> LpTaskUser = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition, page);
		//User user = this.userService.findUserByUsername("116001");
		
		return "单表查询" + LpTaskUser;
	}


	@Override
	public Object isExistPersonal(String paperUuid, Integer ownerMode) {
		Boolean flag = true;
		if(ownerMode != null) {
			if(ownerMode == 1) {
				ownerMode = 2;
			}else if(ownerMode == 2) {
				ownerMode = 1;
			}
		}
		PaPaperCondition paPaperCondition = new PaPaperCondition();
		paPaperCondition.setPaperUuid(paperUuid);
		paPaperCondition.setOwnerMode(ownerMode);
		List<PaPaper> paperList = this.paPaperService.findPaPaperByCondition(paPaperCondition);
		if(paperList != null && paperList.size() > 0) {
			flag = false;
		}
		return new ResponseVo<Object>("0",flag);
	}
	
}