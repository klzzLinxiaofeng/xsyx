package platform.education.rest.jw.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.EvaluationMedalData;
import platform.education.generalTeachingAffair.vo.RedBannerScore;
import platform.education.generalTeachingAffair.vo.RedBannerVo;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.RedBannerRestService;
import platform.education.rest.jw.service.vo.RedBannerScoreVo;

public class RedBannerRestServiceImpl implements RedBannerRestService{

	@Autowired
	@Qualifier("teamApsService")
	private TeamApsService teamApsService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	
	@Override
	public Object findWeeklyGradeRedBanner(String termCode, Integer gradeId,
			String beginDate, String endDate, String periodCode) {
		
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
			if(periodCode == null || "".equals(periodCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("periodCode参数必填");
				info.setMsg("periodCode参数不能为空");
				info.setParam("periodCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(periodCode.length() == 1){
				periodCode = "W0" + periodCode;
			}else if(periodCode.length() == 2){
				periodCode = "W" + periodCode;
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数在2位数以内");
				info.setMsg("输入的参数错误");
				info.setParam("periodCode");
				return new ResponseError(CommonCode.$PARAMETER_LENGTH_OUT_OF_RANGE, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(endDate);
			//已评定
			List<RedBannerVo> redBannerList = teamApsService.findWeeklyGradeRedBanner(termCode, gradeId, periodCode,null,null);
			if(redBannerList == null || redBannerList.size() <= 0){
				redBannerList = new ArrayList<RedBannerVo>();
				//未评定
				List<TeamSummaryData> summaryList = teamApsService.summaryTeamEvaluationTaskForGrade
						(gradeId, termCode, begin, end);
				if(summaryList != null && summaryList.size() > 0){
					
					for(TeamSummaryData data : summaryList){
						RedBannerVo redBanner = new RedBannerVo();
						redBanner.setAddScore(data.getAddScore());
						redBanner.setReduceScore(data.getDeductScore());
						redBanner.setTotalScore(addFloat(data.getTotalScore(), 100));
						redBanner.setRank(data.getRank());
						redBanner.setIsRed(0);
						redBanner.setRemark("");
						redBanner.setDate(null);
						redBanner.setIsEvaluate(0);
						
						Team team = teamService.findTeamById(data.getObjectId());
						String name = team.getName().replaceAll("年级|班", "");
						redBanner.setTeamName(name);
						redBanner.setTeamId(team.getId());
						List<Teacher> teacherList = teacherService.getMastersOfTeam(team.getId());
						if (teacherList != null && teacherList.size() > 0) {
							redBanner.setTeamTeacherName(teacherList.get(0).getName());
						}
						redBannerList.add(redBanner);
					}
					
				}
				
			}else{
				for(RedBannerVo vo : redBannerList){
					vo.setTeamName(vo.getTeamName().replaceAll("年级|班", ""));
				}
			}
			return new ResponseVo<List<RedBannerVo>>("0", redBannerList);
		
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
			info.setParam("termCode,gradeId,periodCode");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object evaluateWeeklyGradeRedBanner(String termCode,
			Integer gradeId, String beginDate, String endDate, String periodCode) {
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
			if(periodCode == null || "".equals(periodCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("periodCode参数必填");
				info.setMsg("periodCode参数不能为空");
				info.setParam("periodCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if(periodCode.length() == 1){
				periodCode = "W0" + periodCode;
			}else if(periodCode.length() == 2){
				periodCode = "W" + periodCode;
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数在2位数以内");
				info.setMsg("输入的参数错误");
				info.setParam("periodCode");
				return new ResponseError(CommonCode.$PARAMETER_LENGTH_OUT_OF_RANGE, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			teamApsService.evaluateWeeklyGradeRedBanner(
					termCode, gradeId,sdf.parse(beginDate), sdf.parse(endDate), periodCode);
			
			List<RedBannerVo> redBannerList = teamApsService.findWeeklyGradeRedBanner(
					termCode, gradeId,periodCode,null,null);
			if(redBannerList != null && redBannerList.size() > 0){
				for(RedBannerVo vo : redBannerList){
					vo.setTeamName(vo.getTeamName().replaceAll("年级|班", ""));
				}
				return new ResponseVo<List<RedBannerVo>>("0", redBannerList);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据内容不正确");
				info.setMsg("输入的数据不正确");
				info.setParam("termCode,gradeId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
		
		}catch(ParseException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("termCode,gradeId,periodCode");
			Log.info("评定红旗班级失败=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object batchSetRedBannerWeeklyStandardScore(String scoreDatas) {
		try{
			if(scoreDatas == null || "".equals(scoreDatas)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("scoreDatas参数必填");
				info.setMsg("scoreDatas参数不能为空");
				info.setParam("scoreDatas");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			JSONArray jsonArray = JSONArray.fromObject(scoreDatas);
			String criterion = null;
			for(int i=0; i<jsonArray.size(); i++){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				RedBannerScoreVo emData = JSON.parseObject(jsonObject.toString(), RedBannerScoreVo.class);
				if(criterion == null && emData.getCriterion() != null && !"".equals(emData.getCriterion())){
					criterion = emData.getCriterion();
				}
				teamApsService.setRedBannerWeeklyStandardScore(emData.getGradeId(), emData.getScore(), emData.getCount(), criterion);
			}
			return new ResponseVo<String>("0", "保存成功");
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("scoreDatas");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		}
	}

	@Override
	public Object findRedBannerScores(Integer schoolId, String schoolYear) {
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			List<RedBannerScoreVo> list = new ArrayList<RedBannerScoreVo>(); 
			List<RedBannerScore> scoreList = teamApsService.findRedBannerScores(schoolId, schoolYear);
			if(scoreList != null && scoreList.size() > 0){
				for(RedBannerScore score : scoreList){
					RedBannerScoreVo vo = new RedBannerScoreVo();
					vo.setGradeId(score.getGradeId());
					vo.setGradeName(score.getGradeName());
					vo.setScore(score.getScore());
					vo.setCount(score.getReachCount());
					vo.setCriterion(score.getGetWay());
					list.add(vo);
				}
				return new ResponseVo<List<RedBannerScoreVo>>("0", list);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("数据内容不正确");
				info.setMsg("输入的数据不正确");
				info.setParam("schoolId, schoolYear");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("schoolId,schoolYear");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object batchSetJudgeCriterion(Integer schoolId, String schoolYear, String criterion) {
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(schoolYear == null || "".equals(schoolYear)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolYear参数必填");
				info.setMsg("schoolYear参数不能为空");
				info.setParam("schoolYear");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(criterion == null || "".equals(criterion)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("criterion参数必填");
				info.setMsg("criterion参数不能为空");
				info.setParam("criterion");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			if("1".equals(criterion)){
				criterion = "1";
			}else{
				criterion = "2";
			}
			List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, schoolYear);
			if(gradeList != null && gradeList.size() > 0 ){
				List<RedBannerScore> dataList = new ArrayList<RedBannerScore>();
				for(Grade grade : gradeList){
					RedBannerScore data = new RedBannerScore();
					data.setGradeId(grade.getId());
					data.setGetWay(criterion);
					dataList.add(data);
				}
				
				teamApsService.setRedBannerWeeklyStandardWay(dataList);
				return new ResponseVo<String>("0", "设置评比标准成功");
				
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到年级数据");
				info.setMsg("schoolId,schoolYear参数异常");
				info.setParam("schoolId,schoolYear");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("schoolId,schoolYear");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
			
		}
	}
	
	public float addFloat(float a, float b){
		 BigDecimal b1 = new BigDecimal(String.valueOf(a));
		 BigDecimal b2 = new BigDecimal(String.valueOf(b));
		 BigDecimal b3 = b1.add(b2);
		 float f = b3.floatValue();
		 return f;
	}

}
