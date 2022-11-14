package platform.education.rest.jw.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.model.ApsTask;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskScore;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.StudentApsService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ClassOptimizingSummaryData;
import platform.education.generalTeachingAffair.vo.IncreaseEvaScoreData;
import platform.education.generalTeachingAffair.vo.IncreaseSummaryData;
import platform.education.generalTeachingAffair.vo.StudentItemData;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.IncentiveEvaRestService;
import platform.education.rest.jw.service.vo.IncCategory;
import platform.education.rest.jw.service.vo.IncItemData;
import platform.education.rest.jw.service.vo.IncentiveItemData;
import platform.education.rest.jw.service.vo.IncentiveStuData;
import platform.education.rest.jw.service.vo.StudentEvaInfo;
import platform.education.rest.jw.service.vo.StudentItemDataVo;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.ProfileService;

public class IncentiveEvaRestServiceImpl implements IncentiveEvaRestService{
	
	@Autowired
	@Qualifier("studentApsService")
	private StudentApsService studentApsService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	

	@Override
	public Object findStudentsHaveIncEva(String schoolYear, String termCode,
			Integer teamId, String checkDate) {
		try{
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<StudentEvaInfo> list = new ArrayList<StudentEvaInfo>();
			
			List<TeamStudentVo> teamStudentList = teamStudentService.getTeamStudentsByTeamId(teamId);
			
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据不正确");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
			}
			
			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(task.getId());
			taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
			taskScore.setCheckType(ApsTaskContants.CHECK_TYPE_ADD);
			taskScore.setCheckDate(sdf.parse(checkDate));
			taskScore.setIsDeleted(false);
			
			List<ApsTaskScore> scoreList = null;
			if(teamStudentList != null && teamStudentList.size() > 0){
				for(TeamStudentVo studentVo : teamStudentList){
					taskScore.setObjectId(studentVo.getStudentId());
					scoreList = studentApsService.findApsTaskScoreByCondition(taskScore);
					String url = ImgUtil.getImgSrc(studentVo.getUserId(), profileService);
					
					StudentEvaInfo info = new StudentEvaInfo();
					info.setStudentId(studentVo.getStudentId());
					info.setStudentName(studentVo.getName());
					info.setTeamId(studentVo.getTeamId());
					info.setSex(studentVo.getSex());
					info.setUserIcon(url);
					if(scoreList !=null && scoreList.size() > 0){
						info.setIsEvaluate(1);
					}else{
						info.setIsEvaluate(0);
					}
					list.add(info);
				}
				
			}
			return new ResponseVo<List<StudentEvaInfo>>("0", list);
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
			
	}

	@Override
	public Object batchSetIncreaseScores(Integer schoolId, Integer userId,String termCode,
			Integer teamId, Integer studentId, String checkDate, String stuItemDatas) {
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(userId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(studentId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("studentId参数必填");
				info.setMsg("studentId参数不能为空");
				info.setParam("studentId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(stuItemDatas == null || "".equals(stuItemDatas)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("stuItemDatas参数必填");
				info.setMsg("stuItemDatas参数不能为空");
				info.setParam("stuItemDatas");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
				
			if(teacher != null){
				List<StudentItemData> list = new ArrayList<StudentItemData>();
				JSONArray jsonArray = JSONArray.fromObject(stuItemDatas);
				for(int i=0; i<jsonArray.size(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					StudentItemDataVo data = JSON.parseObject(jsonObject.toString(), StudentItemDataVo.class);
					
					if(data.getIsChoose().equals(1)){
						StudentItemData itemData = new StudentItemData();
						itemData.setItemId(data.getItemId());
						itemData.setStudentId(data.getStudentId());
						list.add(itemData);
					}
				}
				
				studentApsService.deleteStudentItemDatas(termCode, studentId, ApsTaskContants.CHECK_TYPE_ADD, 
						sdf.parse(checkDate), null);
				
				studentApsService.batchSetIncreaseScores(teamId, teacher.getId(), sdf.parse(checkDate), list);
				return new ResponseVo<String>("0", "保存成功");
				
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId,userId参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("schoolId,userId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
		
		}catch(JSONException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("stuItemDatas");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch(net.sf.json.JSONException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("stuItemDatas");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("checkDate");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode, studentId");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}
		

	@Override
	public Object batchGetIncreaseScores(String termCode, Integer studentId, String checkDate) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(studentId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("studentId参数必填");
				info.setMsg("studentId参数不能为空");
				info.setParam("studentId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Student student = studentService.findStudentById(studentId);
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("studentId参数异常");
				info.setMsg("studentId参数异常");
				info.setParam("studentId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
			List<ApsTaskItem> pd = studentApsService.findAwardedItemsOfCategory(termCode, ApsTaskContants.CATEGORY_PINDE);
			List<ApsTaskItem> xy = studentApsService.findAwardedItemsOfCategory(termCode, ApsTaskContants.CATEGORY_XUEYE);
			List<ApsTaskItem> sx = studentApsService.findAwardedItemsOfCategory(termCode, ApsTaskContants.CATEGORY_SHENXIN);
			List<ApsTaskItem> xq = studentApsService.findAwardedItemsOfCategory(termCode, ApsTaskContants.CATEGORY_XINGQU);
			List<ApsTaskItem> sj = studentApsService.findAwardedItemsOfCategory(termCode, ApsTaskContants.CATEGORY_SHIJIAN);
			if(pd == null || pd.size() <= 0){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("termCode参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
		
			ApsTask task = studentApsService.getApsTask(termCode);
			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(task.getId());
			taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
			taskScore.setObjectId(studentId);
			taskScore.setCheckType(ApsTaskContants.CHECK_TYPE_ADD);
			taskScore.setCheckDate(sdf.parse(checkDate));
			taskScore.setIsDeleted(false);
			List<ApsTaskScore> list = studentApsService.findApsTaskScoreByCondition(taskScore);
			Boolean flag = false;
			if(list != null && list.size() > 0){
				flag = true;
			}
			
			List<IncCategory> categoryList = new ArrayList<IncCategory>();
			IncCategory pinde = getItemData(pd, list, "2-02-01", flag);
			IncCategory xueye = getItemData(xy, list, "2-02-02", flag);
			IncCategory shenxin = getItemData(sx, list, "2-02-03", flag);
			IncCategory xingqu = getItemData(xq, list, "2-02-04", flag);
			IncCategory shijian = getItemData(sj, list, "2-02-05", flag);
			categoryList.add(pinde);
			categoryList.add(xueye);
			categoryList.add(shenxin);
			categoryList.add(xingqu);
			categoryList.add(shijian);
			
			IncentiveStuData data = new IncentiveStuData();
			data.setStudentId(studentId);
			data.setStudentName(student.getName());
			data.setCategoryList(categoryList);
			
			return new ResponseVo<IncentiveStuData>("0", data);
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);		
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,studentId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	public IncCategory getItemData(List<ApsTaskItem> itemList, List<ApsTaskScore> list, String code, Boolean flag){
		
		IncCategory incCategory = new IncCategory();
		List<IncItemData> dataList = new ArrayList<IncItemData>();
		for(ApsTaskItem item : itemList){
			IncItemData data = new IncItemData();
			data.setItemId(item.getId());
			data.setItemName(item.getName());
			if(flag){
				for(ApsTaskScore taskScore : list){
					if(taskScore.getTaskItemId().equals(data.getItemId())){
						data.setIsChoose(1);
						break;
					}else{
						data.setIsChoose(0);
					}
				}
			}else{
				data.setIsChoose(0);
			}
			dataList.add(data);
		}
		
		incCategory.setCategory(itemList.get(0).getCategory());
		incCategory.setName(incCategory.getCategory().substring(0, 4));
		incCategory.setCategoryCode(code);
		incCategory.setItemList(dataList);
		
		return incCategory;
	}
	
	
	@Override
	public Object findIncreaseScoresForTeam(String schoolYear, String termCode,
			Integer teamId, String checkDate) {
		try{
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			Date[] date = monthTime(checkDate);
			List<IncreaseEvaScoreData> dataList = studentApsService.findIncreaseScoresForTeam(
					schoolYear, termCode, teamId, date[0], date[1]);
			return new ResponseVo<List<IncreaseEvaScoreData>>("0", dataList);
		
		}catch(NumberFormatException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}

	}

	@Override
	public Object findIncreaseCountForTeam(Integer teamId, Integer studentId, String termCode,
			String beginDate, String endDate) {
		try{
			if(teamId == null){
				return judgeNull("teamId");
			}
			if(termCode == null || "".equals(termCode)){
				return judgeNull("termCode");
			}
			if(beginDate == null || "".equals(beginDate)){
				return judgeNull("beginDate");
			}
			if(endDate == null || "".equals(endDate)){
				return judgeNull("endDate");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<IncreaseSummaryData> dataList = studentApsService.findIncreaseCountForTeam(
					teamId, studentId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			
			if(studentId != null && dataList != null && dataList.size() > 0){
				List<IncreaseSummaryData> tlist = studentApsService.findIncreaseCountForTeam(
						teamId, null, termCode, sdf.parse(beginDate), sdf.parse(endDate));
				
				if(tlist != null && tlist.size() > 0){
					for(IncreaseSummaryData data : dataList){
						for(IncreaseSummaryData tdata : tlist){
							if(data.getObjectName().equals(tdata.getObjectName())){
								if(tdata.getCount() != 0){
									data.setRatio((float)data.getCount()/tdata.getCount());
								}else{
									data.setRatio((float)0);
								}
								break;
							}
						}
					}
					
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("数据不正确");
					info.setMsg("输入的数据不正确");
					info.setParam("teamId,studentId");
					return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
				}
			}
			return new ResponseVo<List<IncreaseSummaryData>>("0", dataList);
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据不正确");
			info.setMsg("输入的数据不正确");
			info.setParam("termCode");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findIncreaseCountForGrade(Integer gradeId, String termCode,
			String beginDate, String endDate) {
		try{
			if(gradeId == null){
				return judgeNull("gradeId");
			}
			if(termCode == null || "".equals(termCode)){
				return judgeNull("termCode");
			}
			if(beginDate == null || "".equals(beginDate)){
				return judgeNull("beginDate");
			}
			if(endDate == null || "".equals(endDate)){
				return judgeNull("endDate");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<IncreaseSummaryData> dataList = studentApsService.findIncreaseCountForGrade(
					gradeId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			
			return new ResponseVo<List<IncreaseSummaryData>>("0", dataList);
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,gradeId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findIncreaseCountForSchool(Integer schoolId, String termCode,
			String beginDate, String endDate) {
		try{
			if(schoolId == null){
				return judgeNull("gradeId");
			}
			if(termCode == null || "".equals(termCode)){
				return judgeNull("termCode");
			}
			if(beginDate == null || "".equals(beginDate)){
				return judgeNull("beginDate");
			}
			if(endDate == null || "".equals(endDate)){
				return judgeNull("endDate");
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<IncreaseSummaryData> dataList = studentApsService.findIncreaseCountForSchool(
					schoolId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			
			return new ResponseVo<List<IncreaseSummaryData>>("0", dataList);
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,gradeId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findIncreaseCountForTeam(Integer teamId, Integer studentId, String termCode,
			String beginDate, String endDate, String jsonpCallback) {
		try{
			if(jsonpCallback == null || "".equals(jsonpCallback)){
				return jsonpNull("jsonpCallback", jsonpCallback);
			}
			if(teamId == null){
				return jsonpNull("teamId", jsonpCallback);
			}
			if(termCode == null || "".equals(termCode)){
				return jsonpNull("termCode", jsonpCallback);
			}
			if(beginDate == null || "".equals(beginDate)){
				return jsonpNull("beginDate", jsonpCallback);
			}
			if(endDate == null || "".equals(endDate)){
				return jsonpNull("endDate", jsonpCallback);
			}
			long a = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<IncreaseSummaryData> dataList = studentApsService.findIncreaseCountForTeam(
					teamId, studentId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			
			if(studentId != null && dataList != null && dataList.size() > 0){
				List<IncreaseSummaryData> tlist = studentApsService.findIncreaseCountForTeam(
						teamId, null, termCode, sdf.parse(beginDate), sdf.parse(endDate));
				
				if(tlist != null && tlist.size() > 0){
					for(IncreaseSummaryData data : dataList){
						for(IncreaseSummaryData tdata : tlist){
							if(data.getObjectName().equals(tdata.getObjectName())){
								if(tdata.getCount() != 0){
									data.setRatio((float)data.getCount()/tdata.getCount());
								}else{
									data.setRatio((float)0);
								}
								break;
							}
						}
					}
					
				}else{
					ResponseInfo info = new ResponseInfo();
					info.setDetail("数据不正确");
					info.setMsg("输入的数据不正确");
					info.setParam("teamId,studentId");
					return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info) + ")";	
				}
			}
			String json = JSON.toJSONString(dataList);
			long b = System.currentTimeMillis();
			System.out.println("============================时间===============================");
			System.out.println(b-a);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据不正确");
			info.setMsg("输入的数据不正确");
			info.setParam("termCode");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";	
		}
	}

	@Override
	public Object findIncreaseCountForGrade(Integer gradeId, String termCode,
			String beginDate, String endDate, String jsonpCallback) {
		try{
			if(jsonpCallback == null || "".equals(jsonpCallback)){
				return jsonpNull("jsonpCallback", jsonpCallback);
			}
			if(gradeId == null){
				return jsonpNull("gradeId", jsonpCallback);
			}
			if(termCode == null || "".equals(termCode)){
				return jsonpNull("termCode", jsonpCallback);
			}
			if(beginDate == null || "".equals(beginDate)){
				return jsonpNull("beginDate", jsonpCallback);
			}
			if(endDate == null || "".equals(endDate)){
				return jsonpNull("endDate", jsonpCallback);
			}
			long a = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<IncreaseSummaryData> dataList = studentApsService.findIncreaseCountForGrade(
					gradeId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			
			String json = JSON.toJSONString(dataList);
			long b = System.currentTimeMillis();
			System.out.println("============================时间===============================");
			System.out.println(b-a);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,gradeId");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";	
		}
	}

	@Override
	public Object findIncreaseCountForSchool(Integer schoolId, String termCode,
			String beginDate, String endDate, String jsonpCallback) {
		try{
			if(jsonpCallback == null || "".equals(jsonpCallback)){
				return jsonpNull("jsonpCallback", jsonpCallback);
			}
			if(schoolId == null){
				return jsonpNull("schoolId", jsonpCallback);
			}
			if(termCode == null || "".equals(termCode)){
				return jsonpNull("termCode", jsonpCallback);
			}
			if(beginDate == null || "".equals(beginDate)){
				return jsonpNull("beginDate", jsonpCallback);
			}
			if(endDate == null || "".equals(endDate)){
				return jsonpNull("endDate", jsonpCallback);
			}
			long a = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<IncreaseSummaryData> dataList = studentApsService.findIncreaseCountForSchool(
					schoolId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			
			String json = JSON.toJSONString(dataList);
			long b = System.currentTimeMillis();
			System.out.println("============================时间===============================");
			System.out.println(b-a);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";	
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,schoolId");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";	
		}
	}
	
	public Object judgeNull(String name){
			ResponseInfo info = new ResponseInfo();
			info.setDetail(name + "参数必填");
			info.setMsg(name + "参数不能为空");
			info.setParam(name);
			return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
	}
	
	public Object jsonpNull(String name, String jsonpCallback){
		ResponseInfo info = new ResponseInfo();
		info.setDetail(name + "参数必填");
		info.setMsg(name + "参数不能为空");
		info.setParam(name);
		return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
	}
	
	public Date[] monthTime(String month) throws ParseException{//月份  2016年6月 2016-06
		String year = month.substring(0,4);
		String month1 = month.substring(5);
		int yearDateTime = Integer.parseInt(year);
		int monthDateTime = Integer.parseInt(month1);
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd ");
		
		//当月第一天
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, yearDateTime);
        cal1.set(Calendar.MONTH, monthDateTime-1);
        cal1.set(Calendar.DAY_OF_MONTH,cal1.getMinimum(Calendar.DATE));
        String str1 = new SimpleDateFormat( "yyyy-MM-dd ").format(cal1.getTime());
        Date beginDate = sdf.parse(str1);
        
		//当月最后一天
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, yearDateTime);
		cal2.set(Calendar.MONTH, monthDateTime-1);
		cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DATE));
		String str2 = new SimpleDateFormat( "yyyy-MM-dd ").format(cal2.getTime());
		Date endDate = sdf.parse(str2);
        
        Date[] monthDate = {beginDate,endDate};
        
		return monthDate;
	}
}
