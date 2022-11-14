package platform.education.rest.jw.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import platform.education.generalTeachingAffair.model.ApsTask;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.StudentApsService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.vo.NormalEvaScoreData;
import platform.education.generalTeachingAffair.vo.NormalSummaryData;
import platform.education.generalTeachingAffair.vo.StudentScoreData;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.NormalEvaRestService;
import platform.education.rest.jw.service.vo.NormalScoreVo;
import platform.education.rest.jw.service.vo.NormalStuSumData;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.ProfileService;

public class NormalEvaRestServiceImpl implements NormalEvaRestService {

	@Autowired
	@Qualifier("studentApsService")
	private StudentApsService studentApsService;

	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	

	@Override
	public Object batchSetNormalScores(Integer userId, Integer schoolId,
			String termCode, Integer teamId, String checkDate,
			String studentScoreDatas) {
		try {
			if (userId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("userId参数必填");
				info.setMsg("userId参数不能为空");
				info.setParam("userId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (schoolId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (termCode == null || "".equals(termCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (teamId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (checkDate == null || "".equals(checkDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (studentScoreDatas == null || "".equals(studentScoreDatas)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("studentScoreDatas参数必填");
				info.setMsg("studentScoreDatas参数不能为空");
				info.setParam("studentScoreDatas");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
			if(teacher == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据不正确");
				info.setMsg("输入的数据不正确");
				info.setParam("schoolId, userId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			Date[] date = monthTime(checkDate);
			JSONArray array = JSONArray.fromObject(studentScoreDatas);
			List<StudentScoreData> newList = new ArrayList<StudentScoreData>();
			List<StudentScoreData> list = JSONArray.toList(array, new StudentScoreData(), new JsonConfig());
			for (StudentScoreData data : list) {
				float score = 0;
				StudentScoreData newData = new StudentScoreData();
				if (data.getScore() != null) {
					score = data.getScore();
					newData.setScore(score);
					newData.setStudentId(data.getStudentId());
					newList.add(newData);
				}
			}
			studentApsService.batchSetNormalScores(termCode, teamId, teacher.getId(),newList, date[1]);
		} catch (JSONException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("studentScoreDatas");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("checkDate");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("teamId,termCode");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}

		return new ResponseVo<String>("0", "保存成功");
	}

	@Override
	public Object findNormalScoresForTeam(String schoolYear, String termCode,
			Integer teamId, String checkDate) {
		try {
			if (schoolYear == null || "".equals(schoolYear)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (termCode == null || "".equals(termCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (teamId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (checkDate == null || "".equals(checkDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<NormalScoreVo> newList = new ArrayList<NormalScoreVo>();
			List<NormalEvaScoreData> list = new ArrayList<NormalEvaScoreData>();
			Date[] date = monthTime(checkDate);
			list = studentApsService.findNormalScoresForTeam(schoolYear,termCode, teamId, date[0], date[1]);
			if(list != null && list.size() > 0){
				for(NormalEvaScoreData data : list){
					NormalScoreVo vo = new NormalScoreVo();
					if(data.getCheckDate()!=null){
						vo.setCheckDate(sdf.format(data.getCheckDate()));
					}
					vo.setCount(data.getCount());
					vo.setNumber(data.getNumber());
					vo.setStudentId(data.getStudentId());
					vo.setStudentName(data.getStudentName());
					newList.add(vo);
				}
			}
			return new ResponseVo<List<NormalScoreVo>>("0", newList);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("schoolYear,termCode,teamId,checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object findNormalCountForTeam(Integer teamId, Integer studentId,
			String termCode, String beginDate, String endDate) {
		try {
			if (teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (termCode == null || "".equals(termCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (beginDate == null || "".equals(beginDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (endDate == null || "".equals(endDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(endDate);
			List<NormalSummaryData> list = studentApsService
					.findNormalCountForTeam(teamId, studentId, termCode, begin,end);
			if(studentId != null && list != null && list.size() > 0){
				List<NormalSummaryData> tlist = studentApsService
						.findNormalCountForTeam(teamId, null, termCode, begin,end);
				float totalCount = 0;
				for(NormalSummaryData tdata : tlist){
					totalCount += tdata.getCount();
				}
				for(NormalSummaryData data : list){
					data.setRatio(data.getCount()/totalCount);
				}
			}
			return new ResponseVo<List<NormalSummaryData>>("0", list);
		} catch (ParseException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("teamId,termCode, beginDate, endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object findNormalCountForGrade(Integer gradeId, String termCode,
			String beginDate, String endDate) {
		try {
			if (gradeId == null ) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("gradeId参数必填");
				info.setMsg("gradeId参数不能为空");
				info.setParam("gradeId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (termCode == null || "".equals(termCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (beginDate == null || "".equals(beginDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (endDate == null || "".equals(endDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(endDate);
			List<NormalSummaryData> list = studentApsService
					.findNormalCountForGrade(gradeId, termCode, begin, end);
			return new ResponseVo<List<NormalSummaryData>>("0", list);
		} catch (ParseException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("gradeId,termCode, beginDate, endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object findNormalCountForSchool(Integer schoolId, String termCode,
			String beginDate, String endDate) {
		try {
			if (schoolId == null) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (termCode == null || "".equals(termCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (beginDate == null || "".equals(beginDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("beginDate参数必填");
				info.setMsg("beginDate参数不能为空");
				info.setParam("beginDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if (endDate == null || "".equals(endDate)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("endDate参数必填");
				info.setMsg("endDate参数不能为空");
				info.setParam("endDate");
				return new ResponseError(
						CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(endDate);
			List<NormalSummaryData> list = studentApsService
					.findNormalCountForSchool(schoolId, termCode, begin, end);
			return new ResponseVo<List<NormalSummaryData>>("0", list);
		} catch (ParseException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("schoolId,termCode, beginDate, endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	
	@Override
	public Object findNormalCountForStudent(Integer teamId,
			String schoolYear, String termCode, String beginDate,
			String endDate, String jsonpCallback) {
		try {
			if (jsonpCallback == null || "".equals(jsonpCallback)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("jsonpCallback参数必填");
				info.setMsg("jsonpCallback参数不能为空");
				info.setParam("jsonpCallback");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
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
			List<NormalStuSumData> list = new ArrayList<NormalStuSumData>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<NormalEvaScoreData> scoreList = studentApsService.findNormalScoresForTeam(
					schoolYear, termCode, teamId, sdf.parse(beginDate), sdf.parse(endDate));
			Float totalCount = (float) 0;
			for(NormalEvaScoreData score : scoreList){
				NormalStuSumData data = new NormalStuSumData();
				data.setStudentId(score.getStudentId());
				data.setStudentName(score.getStudentName());
				data.setCount(score.getCount());
				Student student = studentService.findStudentById(score.getStudentId());
				data.setUrl(ImgUtil.getImgSrc(student.getUserId(), profileService));
				list.add(data);
				totalCount += score.getCount();
			}
			for(NormalStuSumData data : list){
				if(totalCount != 0){
					data.setRatio(data.getCount()/totalCount);
				}else{
					data.setRatio((float) 0);
				}
			}
			String json = JSON.toJSONString(list);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
			
		} catch (ParseException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数异常");
			info.setMsg("找不到数据");
			info.setParam("teamId,schoolYear,termCode");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
		}

	}

	
	
	@Override
	public Object findNormalCountForTeam(Integer teamId, Integer studentId,
			String termCode, String beginDate, String endDate,
			String jsonpCallback) {
		try {
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
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
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<NormalSummaryData> list = studentApsService
					.findNormalCountForTeam(teamId, studentId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			if(studentId != null && list != null && list.size() > 0){
				List<NormalSummaryData> tlist = studentApsService
						.findNormalCountForTeam(teamId, null, termCode, sdf.parse(beginDate), sdf.parse(endDate));
				float totalCount = 0;
				for(NormalSummaryData tdata : tlist){
					totalCount += tdata.getCount();
				}
				for(NormalSummaryData data : list){
					data.setRatio(data.getCount()/totalCount);
				}
			}
			String json = JSON.toJSONString(list);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
		} catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";			
		} catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode, teamId, beginDate, endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		}
	}

	@Override
	public Object findNormalCountForGrade(Integer gradeId, String termCode,
			String beginDate, String endDate, String jsonpCallback) {
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
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<NormalSummaryData> list = studentApsService.findNormalCountForGrade(gradeId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			String json = JSON.toJSONString(list);
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
			info.setParam("termCode, gradeId, beginDate, endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		}
	}

	@Override
	public Object findNormalCountForSchool(Integer schoolId, String termCode,
			String beginDate, String endDate, String jsonpCallback) {
		try {
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) + ")";
			}
			if(schoolId == null ){
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
			if(endDate == null || "".equals(schoolId)){
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
			ApsTask task = studentApsService.getApsTask(termCode);
			if(task == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("termCode");
				return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info) + ")";
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<NormalSummaryData> list = studentApsService
					.findNormalCountForSchool(schoolId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			String json = JSON.toJSONString(list);
			return jsonpCallback + "(" + new ResponseVo<String>("0", json) + ")";
		} catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		} catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode, schoolId, beginDate, endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		}
	}
	
	//得到给定月份的第一天和最后一天
		public Date[] monthTime(String month) throws ParseException{//月份  2016年6月
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
