package platform.education.rest.ps.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.map.HashedMap;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import framework.generic.dao.Page;
import platform.education.exam.vo.ExamPublishVo;
import platform.education.generalTeachingAffair.model.PjAptsTask;
import platform.education.generalTeachingAffair.model.PjAptsTaskUser;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.PjAptsTaskService;
import platform.education.generalTeachingAffair.service.PjAptsTaskUserService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.vo.AssessmentItemVo;
import platform.education.generalTeachingAffair.vo.PjAptsTaskUserVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.ps.service.AptsTaskRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.user.model.AppEdition;
import platform.education.user.service.AppEditionService;
import platform.education.user.service.ProfileService;

public class AptsTaskRestServiceImpl  implements AptsTaskRestService{
@Resource
private PjAptsTaskService  pjAptsTaskService;
@Resource
private AppEditionService appEditionService;
@Resource
private ProfileService profileService;
@Resource
private  StudentService studentService;
@Resource
private PjAptsTaskUserService  pjAptsTaskUserService;


	@Override
	public Object todayAssessment(String appKey, Integer userId)
			throws Exception {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Student s=studentService.findStudentByUserId(userId);
		if(s == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId?????????,?????????");
			info.setMsg("????????????userId");
			info.setParam("userId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		List<Map<String,Object>> list=pjAptsTaskService.findTodayAssessment(userId);
		Integer[] userIds=new Integer[list.size()];
		int i=0;
		for(Map<String,Object> map:list){
			userIds[i]=(Integer) map.get("userId");
			i++;
		}
		if(userIds.length!=0){
			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
			for(Map<String,Object> map:list){
				if(imgMap.get(map.get("userId"))!=null) {
					map.put("icon", imgMap.get(map.get("userId")));
				} else {
					/**????????????????????????????????????*/
					map.put("icon", SysContants.APP_DEFAULT_USERICON);
				}
				map.remove("userId");
			}
		}
		return new ResponseVo<Object>("0",list);
	}

	@Override
	public Object assessmentBoard(String appKey, Integer userId,
			Integer assessmentId) throws Exception {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Student s=studentService.findStudentByUserId(userId);
		if(s == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId?????????,?????????");
			info.setMsg("????????????userId");
			info.setParam("userId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		PjAptsTaskUser pu=pjAptsTaskUserService.findPjAptsTaskUserById(assessmentId);
		if(pu==null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("assessmentId?????????,?????????");
			info.setMsg("????????????assessmentId");
			info.setParam("assessmentId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Map<String,Object>map=pjAptsTaskService.findAssessmentBoard(userId, assessmentId);
		Integer[] userIds={(Integer) map.get("userId")};
		if(userIds.length!=0){
			Map<Integer, String> imgMap = ImgUtil.getImgSrcByIds(userIds, profileService);
			if(imgMap.get(map.get("userId"))!=null) {
				map.put("icon", imgMap.get(map.get("userId")));
			} else {
				/**????????????????????????????????????*/
				map.put("icon", SysContants.APP_DEFAULT_USERICON);
			}
			map.remove("userId");
		}
		return new ResponseVo<Object>("0",map);
	}

	@Override
	public Object addAssessmen(String appKey, String assessmenDate)
			throws Exception {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		JSONObject ob;
		Integer num1=0;
		Integer level=5;
		Integer realScore=2;
		Integer sumScore=0;
		Map<String,Object>map=new HashMap<String, Object>();
		try{
			Date date=new Date();
			 ob=JSONObject.parseObject(assessmenDate);
			 Integer assessmentId=Integer.valueOf(ob.get("assessmentId").toString());
				PjAptsTaskUser pu=pjAptsTaskUserService.findPjAptsTaskUserById(assessmentId);
				if(pu==null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("assessmentId?????????,?????????");
					info.setMsg("????????????assessmentId");
					info.setParam("assessmentId");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				}
			    Integer userId=(Integer) Integer.valueOf(ob.get("userId").toString());
				Student s=studentService.findStudentByUserId(userId);
				if(s == null){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("userId?????????,?????????");
					info.setMsg("????????????userId");
					info.setParam("userId");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				}
			 String description=ob.get("description").toString();
			 if(description.length()>100){
					ResponseInfo info = new ResponseInfo();
					info.setDetail("description????????????100");
					info.setMsg("description????????????100");
					info.setParam("description");
					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			 }
//			 if(description.equals("")){
//				    ResponseInfo info = new ResponseInfo();
//					info.setDetail("description????????????");
//					info.setMsg("description????????????");
//					info.setParam("description");
//					return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
//			 }
			 if(date.after(pu.getFinishDate())){
					    ResponseInfo info = new ResponseInfo();
						info.setDetail("????????????????????????????????????");
						info.setMsg("????????????????????????????????????");
						info.setParam("assessmentId");
						return new ResponseError(CommonCode.S$INVALID_DATA, info);
			 }
			 JSONArray itemList=JSONArray.parseArray(ob.get("itemList").toString());
			 List<AssessmentItemVo> scoreList=new ArrayList<AssessmentItemVo>();
				int number=2;
				
				if(pu.getScoringType()==11){
					 number=10;
					 level=1;
				}
			 for(int i=0;i<itemList.size();i++){
				 AssessmentItemVo vo=new AssessmentItemVo();
				 JSONObject obj=itemList.getJSONObject(i);
				 if(obj.getInteger("itemId")==null){
						ResponseInfo info = new ResponseInfo();
						info.setDetail("itemId????????????");
						info.setMsg("itemId????????????");
						info.setParam("itemId");
						return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				 } 
				 vo.setItemId(obj.getInteger("itemId"));
				 if(obj.getInteger("number")==null){
					    ResponseInfo info = new ResponseInfo();
						info.setDetail("number????????????");
						info.setMsg("number????????????");
						info.setParam("number");
						return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				 }
				 int num=obj.getInteger("number");
				 int score=num*number;
				 sumScore=sumScore+score;
				 if(number>10||number<0){
					 ResponseInfo info = new ResponseInfo();
						info.setDetail("number????????????");
						info.setMsg("number????????????");
						info.setParam("number");
						return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
				 }
				 vo.setScore(score);
				 scoreList.add(vo);
			 }
			 pjAptsTaskService.addAssessment(assessmentId, userId, description, scoreList);
			 map.put("level", level);
			 map.put("number", sumScore/scoreList.size()/number);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("assessmenDate????????????");
			info.setMsg("assessmenDate????????????");
			info.setParam("assessmenDate");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		
		return new ResponseVo<Object>("0",map);
	}

	@Override
	public Object myAssessmentTask(String appKey, Integer userId, Integer pageSize,Integer pageNumber)
			throws Exception {
		AppEdition app = this.appEditionService.findByAppKey(appKey);
		if(app == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("appkey?????????,?????????");
			info.setMsg("????????????appKey");
			info.setParam("appKey");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Student s=studentService.findStudentByUserId(userId);
		if(s == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("userId?????????,?????????");
			info.setMsg("????????????userId");
			info.setParam("userId");
			return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
		}
		Page page =new Page();
		page.setCurrentPage(pageNumber);
		page.setEnableGetTolaRows(false);
		page.setPageSize(pageSize);
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		List<Object> taskInfoList = new ArrayList<Object>();
		List<PjAptsTaskUserVo> pulist=pjAptsTaskUserService.findPjAptsTaskUserByJudgeId(s.getId(), page, null);
		List<Integer> userIds=new ArrayList<Integer>();
		for(PjAptsTaskUserVo vo:pulist){
			userIds.add(vo.getUserId());
		}
		Map<Integer, String> imgMap =new HashMap<Integer, String>();
		Integer[] userIdList=userIds.toArray(new Integer[userIds.size()]);
		if(userIdList.length!=0){
			 imgMap = ImgUtil.getImgSrcByIds(userIdList, profileService);
			for(Map<String,Object> map:list){
				if(imgMap.get(map.get("userId"))!=null) {
					map.put("icon", imgMap.get(map.get("userId")));
				} else {
					/**????????????????????????????????????*/
					map.put("icon", SysContants.APP_DEFAULT_USERICON);
				}
				map.remove("userId");
			}
		}
		/** ??????????????? */
		String preTime = "";
		/** ???????????????????????? */
		Map<String, Object> taskMap = new HashMap<String, Object>();
		/** ?????????????????????????????? */
		List<Map<String, Object>> userTaskList = new ArrayList<Map<String, Object>>();
		/** ??????????????????????????????????????? */
		Map<String, Object> timeAndUserTaskMap = new HashMap<String, Object>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatH = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        for(int i=0;i<pulist.size();i++){
        	PjAptsTaskUserVo ep = pulist.get(i);
			if (ep.getSortDate() != null) {
				int level=5;
				int number=2;
				
				if(ep.getScoringType()==11){
					 level=1;
					 number=10;
				}
				taskMap.put("assessmentId", ep.getId());
//				taskMap.put("timeLine", formatH.format(ep.getStartDate())+"~"+formatH.format(ep.getFinishDate()));
				taskMap.put("startDate", formatH.format(ep.getStartDate()));
				taskMap.put("finishDate", formatH.format(ep.getFinishDate()));
				if(imgMap.get(ep.getUserId())!=null) {
					taskMap.put("icon", imgMap.get(ep.getUserId()));
				} else {
					/**????????????????????????????????????*/
					taskMap.put("icon", SysContants.APP_DEFAULT_USERICON);
				}
				taskMap.put("period", ep.getPeriod());
				taskMap.put("teamName", ep.getTeamName());
				taskMap.put("teacherName", ep.getTeacherName());
				taskMap.put("level",level );
				taskMap.put("number",ep.getJudgeScore()/number);
				taskMap.put("subjectName", ep.getSubjectName());
				taskMap.put("type", ep.getEvType());
				taskMap.put("scoringType", ep.getScoringType());
				if (i == 0) {
					/** ??????????????????????????? */
					preTime = format.format(ep.getSortDate());
				}
				}
				/** ????????????????????????????????????????????????????????? */

				if (preTime.equals(format.format(ep.getSortDate()))) {
					/** ??????????????????????????????????????????????????? */
					userTaskList.add(taskMap);
					/** ???????????????map???????????????????????????????????? */
					taskMap = new HashMap<String, Object>();
					/** ????????????????????????????????????????????? */
					if (i == pulist.size() - 1) {
						/** ????????????????????????????????????list??? */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("task", userTaskList);
						/** ?????????????????????????????? */
						taskInfoList.add(timeAndUserTaskMap);
					}
				} else {
					/** ????????????????????? */
					timeAndUserTaskMap.put("time", preTime);
					timeAndUserTaskMap.put("task", userTaskList);
					/** ????????????????????????????????????????????????????????? */
					taskInfoList.add(timeAndUserTaskMap);

					/** ????????????????????????map,???????????????????????????????????? */
					timeAndUserTaskMap = new HashMap<String, Object>();
					/** ???????????????????????????,???????????????????????? */
					userTaskList = new ArrayList<Map<String, Object>>();
					userTaskList.add(taskMap);

					/** ???????????????????????????????????? */
					preTime = format.format(ep.getSortDate());

					/** ??????????????????????????? */
					if (i == pulist.size() - 1) {
						/** ?????????????????????????????????????????? */
						timeAndUserTaskMap.put("time", preTime);
						timeAndUserTaskMap.put("task", userTaskList);
						taskInfoList.add(timeAndUserTaskMap);
					} else {
						/** ??????????????????, ????????????????????????????????????????????????????????? */
						taskMap = new HashMap<String, Object>();
					}
				}
			}
        return new ResponseVo<Object>("0", taskInfoList);
	}

}
