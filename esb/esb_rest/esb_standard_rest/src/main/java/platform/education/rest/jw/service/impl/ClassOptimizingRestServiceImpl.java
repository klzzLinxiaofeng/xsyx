package platform.education.rest.jw.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.vo.ClassEvaScoreData;
import platform.education.generalTeachingAffair.vo.ClassOptimizingSummaryData;
import platform.education.generalTeachingAffair.vo.StudentItemData;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamStudentCondition;
import platform.education.generalTeachingAffair.vo.TeamStudentVo;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.ClassOptimizingRestService;
import platform.education.rest.jw.service.vo.ClassEvaScoreDataVo;
import platform.education.rest.jw.service.vo.StudentEvaInfo;
import platform.education.rest.jw.service.vo.StudentItemDataVo;
import platform.education.rest.jw.service.vo.TeamScoreDataVo;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.ProfileService;

public class ClassOptimizingRestServiceImpl implements ClassOptimizingRestService{
	
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
	public Object findDeductMarksItems(String termCode) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			List<ApsTaskItem> list = studentApsService.findDeductMarksItems(termCode);
			return new ResponseVo<List<ApsTaskItem>>("0", list);
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("termCode");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}

	@Override
	public Object batchSetClassScores(Integer schoolId, Integer userId, String termCode,
			Integer teamId, Integer studentId, String checkDate,
			String checkRange, String stuItemDatas) {
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
			if(checkRange == null || "".equals(checkRange)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkRange参数必填");
				info.setMsg("checkRange参数不能为空");
				info.setParam("checkRange");
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
				
				studentApsService.deleteStudentItemDatas(termCode, studentId, ApsTaskContants.CHECK_TYPE_MINUS, 
						sdf.parse(checkDate), checkRange);
				
				studentApsService.batchSetClassScores(teacher.getId(), teamId, sdf.parse(checkDate), checkRange, list);
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
			info.setParam("termCode, studentId,checkRange");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object batchGetClassScores(String termCode, Integer studentId,
			String checkDate, String checkRange) {
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
			if(checkRange == null || "".equals(checkRange)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkRange参数必填");
				info.setMsg("checkRange参数不能为空");
				info.setParam("checkRange");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<StudentItemDataVo> dataList = new ArrayList<StudentItemDataVo>();
			
			Student student = studentService.findStudentById(studentId);
			if(student == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("studentId参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("studentId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			List<ApsTaskItem> itemList = studentApsService.findDeductMarksItems(termCode);
			if(itemList != null && itemList.size() > 0){
				for(ApsTaskItem item : itemList){
					StudentItemDataVo data  = new StudentItemDataVo();
					data.setStudentId(studentId);
					data.setStudentName(student.getName());
					data.setSex(student.getSex());
					data.setItemId(item.getId());
					data.setItemName(item.getName());
					
					ApsTaskScore score = studentApsService.getStudentTaskScore(item.getId(), studentId, sdf.parse(checkDate), checkRange);
					if(score != null){
						data.setIsChoose(1);
					}else{
						data.setIsChoose(0);
					}
					dataList.add(data);
				}
				return new ResponseVo<List<StudentItemDataVo>>("0", dataList);
				
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数内容不正确");
				info.setMsg("termCode参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
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
			info.setParam("termCode,studentId,checkDate,checkRange");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
	}

	@Override
	public Object findStudentsHaveClassEva(String schoolYear, String termCode, Integer teamId,String checkDate, String checkRange) {
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
			if(checkRange == null || "".equals(checkRange)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkRange参数必填");
				info.setMsg("checkRange参数不能为空");
				info.setParam("checkRange");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<StudentEvaInfo> list = new ArrayList<StudentEvaInfo>();
			
			List<TeamStudentVo> teamStudentList = teamStudentService.getTeamStudentsByTeamId(teamId);
			
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
			}
			
			ApsTaskScore taskScore = new ApsTaskScore();
			taskScore.setTaskId(task.getId());
			taskScore.setObjectType(ApsTaskContants.OBJECT_STUDENT);
			taskScore.setCheckType(ApsTaskContants.CHECK_TYPE_MINUS);
			taskScore.setCheckDate(sdf.parse(checkDate));
			taskScore.setCheckRange(checkRange);
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
			info.setParam("termCode,teamId,checkRange");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findClassScoresForTeam(String termCode,Integer teamId, String beginDate, String endDate) {
		try{
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
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sd = new SimpleDateFormat("EEEE");
			
			List<ClassEvaScoreData> list = studentApsService.findClassScoresForTeam(
					"0", termCode, teamId, sdf.parse(beginDate), sdf.parse(endDate), null);
			List<ClassEvaScoreDataVo> dataList = new ArrayList<ClassEvaScoreDataVo>();
			if(list != null && list.size() > 0){
				for(ClassEvaScoreData data : list){
					ClassEvaScoreDataVo dataVo = new ClassEvaScoreDataVo();
					dataVo.setCheckDate(sdf.format(data.getCheckDate())+"  "+sd.format(data.getCheckDate()));
					dataVo.setStudentId(data.getStudentId());
					dataVo.setStudentName(data.getName());
					Student student = studentService.findStudentById(data.getStudentId());
					String url = ImgUtil.getImgSrc(student.getUserId(), profileService);
					dataVo.setUserIcon(url);
					dataVo.setSex(student.getSex());
					dataVo.setCheckRange(data.getCheckRange());
					dataVo.setTeacherId(data.getTeacherId());
					dataVo.setTeacherIdName(data.getCourseTeacher());
					String behave = data.getBadBehaviours().replace("&nbsp;&nbsp;&nbsp;&nbsp;", "|");
					dataVo.setBadBehaviours(behave);
					dataList.add(dataVo);
				}
			}
			
			return new ResponseVo<List<ClassEvaScoreDataVo>>("0", dataList);
			
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
			info.setParam("termCode,teamId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findClassOptimizingCountForTeam(String termCode,
			Integer teamId, Integer studentId, String beginDate, String endDate) {
		try{
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
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//班级报表或是个人报表
			List<ClassOptimizingSummaryData> dataList = studentApsService.findClassOptimizingCountForTeam(
					teamId, studentId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			//个人报表且有数据
			if(studentId != null && dataList != null && dataList.size() > 0){
				List<ClassOptimizingSummaryData> tlist = studentApsService.findClassOptimizingCountForTeam(
						teamId, null, termCode, sdf.parse(beginDate), sdf.parse(endDate));
				
				if(tlist != null && tlist.size() > 0){
					for(ClassOptimizingSummaryData data : dataList){
						for(ClassOptimizingSummaryData tdata : tlist){
							if(data.getObjectId().equals(tdata.getObjectId())){
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
			return new ResponseVo<List<ClassOptimizingSummaryData>>("0", dataList);
			
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
	public Object findClassOptimizingCountForGrade(String termCode,
			Integer gradeId, String beginDate, String endDate) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(gradeId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("gradeId参数必填");
				info.setMsg("gradeId参数不能为空");
				info.setParam("gradeId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<ClassOptimizingSummaryData> dataList = studentApsService.findClassOptimizingCountForGrade(
					gradeId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			return new ResponseVo<List<ClassOptimizingSummaryData>>("0", dataList);
			
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
	public Object findClassOptimizingCountForSchool(String termCode,
			Integer schoolId, String beginDate, String endDate) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<ClassOptimizingSummaryData> dataList = studentApsService.findClassOptimizingCountForSchool(
					schoolId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			return new ResponseVo<List<ClassOptimizingSummaryData>>("0", dataList);
			
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
			info.setParam("termCode,schoolId");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findClassOptimizingCountForTeam(String termCode,
			Integer teamId, Integer studentId, String beginDate,
			String endDate, String jsonpCallback) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if (jsonpCallback == null || "".equals(jsonpCallback)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			long a = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//班级报表或是个人报表
			List<ClassOptimizingSummaryData> dataList = studentApsService.findClassOptimizingCountForTeam(
					teamId, studentId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			//个人报表且有数据
			if(studentId != null && dataList != null && dataList.size() > 0){
				List<ClassOptimizingSummaryData> tlist = studentApsService.findClassOptimizingCountForTeam(
						teamId, null, termCode, sdf.parse(beginDate), sdf.parse(endDate));
				
				if(tlist != null && tlist.size() > 0){
					for(ClassOptimizingSummaryData data : dataList){
						for(ClassOptimizingSummaryData tdata : tlist){
							if(data.getObjectId().equals(tdata.getObjectId())){
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
					return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
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
	public Object findClassOptimizingCountForGrade(String termCode,
			Integer gradeId, String beginDate, String endDate,
			String jsonpCallback) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(gradeId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("gradeId参数必填");
				info.setMsg("gradeId参数不能为空");
				info.setParam("gradeId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if (jsonpCallback == null || "".equals(jsonpCallback)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			long a = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<ClassOptimizingSummaryData> dataList = studentApsService.findClassOptimizingCountForGrade(
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
	public Object findClassOptimizingCountForSchool(String termCode,
			Integer schoolId, String beginDate, String endDate,
			String jsonpCallback) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(beginDate == null || "".equals(beginDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(endDate == null || "".equals(endDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if (jsonpCallback == null || "".equals(jsonpCallback)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			long a = System.currentTimeMillis();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<ClassOptimizingSummaryData> dataList = studentApsService.findClassOptimizingCountForSchool(
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

}
