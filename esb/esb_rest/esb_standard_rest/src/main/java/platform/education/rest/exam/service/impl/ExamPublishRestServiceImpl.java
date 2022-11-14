package platform.education.rest.exam.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import framework.generic.dao.Page;
import platform.education.exam.service.ExamPrepareService;
import platform.education.exam.service.ExamPublishedRecordService;
import platform.education.exam.vo.ExamRelateVo;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalcode.service.JcCacheService;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.exam.service.ExamPublishRestService;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.service.storage.service.FileService;

public class ExamPublishRestServiceImpl implements ExamPublishRestService{
	@Autowired
	@Qualifier("appEditionService")
	private AppEditionService appEditionService;
	@Autowired
	@Qualifier("examPrepareService")
	private ExamPrepareService examPrepareService;
	@Autowired
	@Qualifier("jcCacheService")
	private JcCacheService jcCacheService;
	@Autowired
	@Qualifier("pjExamService")
	private PjExamService  pjExamService;
	@Autowired
	@Qualifier("examPublishedRecordService")
	private ExamPublishedRecordService examPublishedRecordService;
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
	@Override
	public Object list(Integer relateId, String appKey,Integer userId,String subjectCode,Integer pageSize,Integer pageNumber) {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey不存在,请确认");
			info.setMsg("不存在该appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Team team  = this.teamService.findTeamById(relateId);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");//小写的mm表示的是分钟  
		/**时间格式化*/
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String preTime = "";
		/**保存用户任务信息*/
		Map<String, Object> taskMap = new HashMap<String, Object>();
		/**保存用户任务信息列表*/
		List<Map<String, Object>> userTaskList = new ArrayList<Map<String, Object>>();
		/**保存任务时间和用户信息列表*/
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
		List<Object> taskInfoList = new ArrayList<Object>();
		Page page = new Page();
		page.setPageSize(pageSize);
		page.setCurrentPage(pageNumber);
		page.setEnableGetTolaRows(false);
		String subjectName="";
		Integer status=0;
		List<ExamRelateVo> mlrList = this.examPrepareService.searchPublishedLesson(null, relateId, page,"01",subjectCode);
		Map<String,Object> map=new HashMap<String, Object>();
		 Date today=new Date();
		if(mlrList!=null){
		for(int i=0;i<mlrList.size();i++){
			ExamRelateVo er=mlrList.get(i);
			subjectName="";
		   taskMap=new HashMap<String, Object>();
           taskMap.put("title",er.getExamTitle());
           taskMap.put("startTime",sdf.format(er.getStartDate()));
           taskMap.put("finishTime",sdf.format(er.getFinishedDate()));
           taskMap.put("teamName",er.getRelateName());
           if(er.getSubjectCode() !=null && team != null){
        	   Subject object=subjectService.findUnique(team.getSchoolId(), er.getSubjectCode());
        	   if (object != null) {
        		   subjectName = object.getName();
        	   }
           }
           if(today.before(er.getStartDate())){
        	   
           }else if(today.after(er.getStartDate())&&today.before(er.getFinishedDate())){
        	   status=1;

           }else{
        	   status=2;
           }
           if(i==0) {
				/**初始上一个任务时间*/
				preTime = format.format(er.getStartDate());
			}
           taskMap.put("subjectName", subjectName);
           taskMap.put("publisherId", er.getPublisherId());
           taskMap.put("publisherName", er.getPublisherName());
           taskMap.put("taskId", er.getTaskId());
           taskMap.put("microPublishId", er.getPublishMicroLessonId());          
           taskMap.put("examId",er.getPjExamId());
           taskMap.put("paperId", er.getPaperId());
           taskMap.put("relateId",er.getRelateId());
           taskMap.put("url", fileService.relativePath2HttpUrlByUUID(er.getEntityId()));
           taskMap.put("status",status);
           if(er.getPublisherId().intValue()==userId.intValue()){
        	   taskMap.put("isDelete", 1);
           }else{
        	   taskMap.put("isDelete", 0);
           }
	    	if(preTime.equals(format.format(er.getStartDate()))) {
	    		/**如果为同一天把信息添加用户任务列表*/
	    		userTaskList.add(taskMap);
	    		/**重新初始化map，用于保存下一个任务信息*/
	    		taskMap = new HashMap<String, Object>();
	    		/**如果为最后一个并且是同一天任务*/
	    		if(i==mlrList.size()-1) {
	    			/**把时间保存到时间和任务的list中*/
	    			timeAndUserTaskMap.put("time",preTime);
	    			timeAndUserTaskMap.put("question",userTaskList);
	    			/**添加到任务信息列表中*/
	    			taskInfoList.add(timeAndUserTaskMap);
	    		}
	    	} else {
	    		/**如果不为同一天*/
	    		timeAndUserTaskMap.put("time",preTime);
	    		timeAndUserTaskMap.put("question",userTaskList);
	    		/**把上一天的任务列表添加到任务信息列表中*/
	    		taskInfoList.add(timeAndUserTaskMap);
	    		
	    		/**初始化时间和任务map,用于存储当天的时间和任务*/
	    		timeAndUserTaskMap = new HashMap<String, Object>();
	    		/**初始化用户任务列表,用于存储当天任务*/
	    		userTaskList = new ArrayList<Map<String, Object>>();
	    		userTaskList.add(taskMap);
	    		
	    		/**当天的时间变成上一天时间*/
	    		preTime=format.format(er.getStartDate());
	    		
	    		/**如果是最后一个任务*/
	    		if(i==mlrList.size()-1) {
	    			/**最后一个任务为这天的任务总数*/
	    			timeAndUserTaskMap.put("time",preTime);
	    			timeAndUserTaskMap.put("question",userTaskList);
	    			taskInfoList.add(timeAndUserTaskMap);
	    		} else {
	    			/**如果不是最后, 初始化任务信息列表，用于存储下一个任务*/
	    			taskMap = new HashMap<String, Object>();
	    		}
	    	}
		 }
		}
		if(taskInfoList.size()==0) {
			return new ResponseVo<Object>("0",new ArrayList<Object>());
		}
		List<Object> sortList = new ArrayList<Object>();
		for(int i=0;i<taskInfoList.size();i++){
			Map<String, Object> frist=new HashMap<String, Object>();
			frist=(Map)taskInfoList.get(i);
			List<Map<String, Object>> second= new ArrayList<Map<String, Object>>();
			timeAndUserTaskMap = new HashMap<String, Object>();
			second=(List)frist.get("question");
//			Collections.reverse(second);  
			timeAndUserTaskMap.put("question",second);
			sortList.add(frist);
			
		}
		return new ResponseVo<Object>("0",sortList);
	 }
	@Override
	public Object taskDelete(Integer examId, String appKey, String microPublishId,
			Integer relateId) {
		try{
			List<Integer> userId=new ArrayList<Integer>();
			List<Integer> receiverIdList=new ArrayList<Integer>();
			AppEdition app = this.appEditionService.findByAppKey(appKey);
			if(app == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("appkey不存在,请确认");
				info.setMsg("不存在该appKey");
				info.setParam("appKey");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
			if(examId==null || microPublishId ==null || relateId==null) {
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, null);//参数为空
			}
			
			receiverIdList.add(relateId);
			Team team=teamService.findTeamById(relateId);
			this.examPrepareService.deletePublish(microPublishId, relateId);
			PjExam pj=this.pjExamService.findPjExamById(examId);
			if(pj!=null){
				this.pjExamService.abandon(pj);
			}
			if(team!=null){
				List<Student> studentList = studentService.findStudentListByListId(receiverIdList, "1", team.getSchoolId());
				if(studentList!=null){
					for(Student s:studentList){
						userId.add(s.getUserId());
					}
				}
			}
			if(userId.size()>0){
				Integer[] userIds=userId.toArray(new Integer[userId.size()]);
				this.examPublishedRecordService.deleteOfTeam(microPublishId, userIds);
			}
			return new ResponseVo<Object>("0",true);
		}catch (Exception e) {
			
			e.printStackTrace();
			 ResponseInfo info = new ResponseInfo();
	            info.setDetail("删除异常");
	            info.setMsg("未知错误");
	            return new ResponseError("000001", info);
		}  
	}
}
