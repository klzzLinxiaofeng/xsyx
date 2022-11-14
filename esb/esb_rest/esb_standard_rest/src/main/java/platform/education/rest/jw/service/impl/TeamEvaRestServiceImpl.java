package platform.education.rest.jw.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import platform.education.generalTeachingAffair.contants.ApsTaskContants;
import platform.education.generalTeachingAffair.model.ApsTaskItem;
import platform.education.generalTeachingAffair.model.ApsTaskJudge;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SchoolTermCurrentService;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamApsService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.DayInfo;
import platform.education.generalTeachingAffair.vo.DutyTeacherStatData;
import platform.education.generalTeachingAffair.vo.JudgeTeacher;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;
import platform.education.generalTeachingAffair.vo.TeamScoreData;
import platform.education.generalTeachingAffair.vo.TeamSummaryData;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.jw.service.TeamEvaRestService;
import platform.education.rest.jw.service.vo.ApsTaskItemVo;
import platform.education.rest.jw.service.vo.DutyTeacherStatDataVo;
import platform.education.rest.jw.service.vo.GradeInfo;
import platform.education.rest.jw.service.vo.ScoreFile;
import platform.education.rest.jw.service.vo.TeamEvaData;
import platform.education.rest.jw.service.vo.TeamInfo;
import platform.education.rest.jw.service.vo.TeamScoreDataVo;
import platform.education.rest.util.ImgUtil;
import platform.education.user.service.ProfileService;


public class TeamEvaRestServiceImpl implements TeamEvaRestService{
	
	@Autowired
	@Qualifier("teamApsService")
	private TeamApsService teamApsService;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	@Autowired
	@Qualifier("schoolTermCurrentService")
	private SchoolTermCurrentService schoolTermCurrentService;
	
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	
	@Autowired
	@Qualifier("gradeService")
	private GradeService gradeService;
	
	@Autowired
	@Qualifier("teamService")
	private TeamService teamService;
	
	@Autowired
	@Qualifier("profileService")
	private ProfileService profileService;
	
	@Autowired
	@Qualifier("schoolTermService")
	private SchoolTermService schoolTermService;
	
	@Override
	public Object findTeamTaskAddItems(String termCode) {

		try {
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			List<ApsTaskItemVo> list = new ArrayList<ApsTaskItemVo>();
			List<ApsTaskItem> itemList = teamApsService.findTeamTaskItems(termCode, ApsTaskContants.CHECK_TYPE_ADD);
			if(itemList != null && itemList.size()>0){
				for(ApsTaskItem item : itemList){
					ApsTaskItemVo itemVo = new ApsTaskItemVo();
					itemVo.setItemId(item.getId());
					itemVo.setItemName(item.getName());
					itemVo.setCategory(item.getCategory());
					itemVo.setCheckType(item.getCheckType());
					itemVo.setCode(item.getCode());
					list.add(itemVo);
				}
			}
			return new ResponseVo<List<ApsTaskItemVo>>("0", list);
			
		} catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("termCode");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}

	}

	@Override
	public Object findTeamTaskMinusItems(String termCode) {
		try {
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			List<ApsTaskItemVo> list = new ArrayList<ApsTaskItemVo>();
			List<ApsTaskItem> itemList = teamApsService.findTeamTaskItems(termCode, ApsTaskContants.CHECK_TYPE_MINUS);
			if(itemList != null && itemList.size()>0){
				for(ApsTaskItem item : itemList){
					ApsTaskItemVo itemVo = new ApsTaskItemVo();
					itemVo.setItemId(item.getId());
					itemVo.setItemName(item.getName());
					itemVo.setCategory(item.getCategory());
					itemVo.setCheckType(item.getCheckType());
					itemVo.setCode(item.getCode());
					list.add(itemVo);
				}
			}
			return new ResponseVo<List<ApsTaskItemVo>>("0", list);
			
		} catch (Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式错误");
			info.setMsg("参数转换异常");
			info.setParam("termCode");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR,info);
		}
	}
	
	@Override
	public  Object batchSetTeamEvaScore(Integer schoolId, Integer userId, Integer teamId,
			Integer gradeId, String checkDate, String teamScoreDatas) {
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
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(gradeId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("gradeId参数必填");
				info.setMsg("gradeId参数不能为空");
				info.setParam("gradeId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(teamScoreDatas == null || "".equals(teamScoreDatas)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamScoreDatas参数必填");
				info.setMsg("teamScoreDatas参数不能为空");
				info.setParam("teamScoreDatas");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamScoreData> dataList = new ArrayList<TeamScoreData>();
			
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
			if(teacher != null){
				JSONArray jsonArray = JSONArray.fromObject(teamScoreDatas);
				for(int i=0; i<jsonArray.size(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					TeamScoreData data = JSON.parseObject(jsonObject.toString(), TeamScoreData.class);
					data.setTeamId(teamId);
					if(data.getScore() != null && !"".equals(data.getScore())){
						dataList.add(data);
					}
				}
				teamApsService.batchSetTeamEvaluationTaskScore(teacher.getId(), gradeId, sdf.parse(checkDate), dataList);
				
				Grade grade = gradeService.findGradeById(gradeId);
				SchoolTermCondition stc = new SchoolTermCondition();
				stc.setSchoolId(schoolId);
				stc.setSchoolYear(grade.getSchoolYear());
				List<SchoolTerm> termList = schoolTermService.findSchoolTermByCondition(stc, null, null);
				for(SchoolTerm term : termList){
					teamApsService.finishedJudge(term.getCode(), gradeId, teacher.getId(), sdf.parse(checkDate));
				}
				//teamApsService.finishedJudge(termCode, gradeId, teacher.getId(), sdf.parse(checkDate));
				return new ResponseVo<String>("0", "保存成功");
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId,userId参数异常");
				info.setMsg("schoolId,userId参数异常");
				info.setParam("schoolId,userId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
		}catch(JSONException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("teamScoreDatas");
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
			info.setParam("teacherId,teamId,gradeId,checkDate,teamScoreDatas");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
	}
	

	
	@Override
	public Object batchGetTeamAddEvaScore(String termCode, Integer teamId,String checkDate) {
		
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
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamScoreData> datas = teamApsService.getTeamAddScore(termCode, teamId, sdf.parse(checkDate));
			
			List<TeamScoreDataVo> list = new ArrayList<TeamScoreDataVo>();
			//List<ApsTaskItem> itemList = teamApsService.findTeamTaskItems(termCode, ApsTaskContants.CHECK_TYPE_ADD);
			List<ApsTaskItem> itemList = teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_ADD, sdf.parse(checkDate));
			if(itemList != null && itemList.size() > 0){
				
				for(ApsTaskItem item : itemList){
					TeamScoreDataVo dataVo = new TeamScoreDataVo();
					dataVo.setTeamId(teamId);
					dataVo.setItemId(item.getId());
					dataVo.setItemName(item.getName());
					dataVo.setCode(item.getCode());
					Boolean flag = true;
					if(datas != null && datas.size() > 0){
						for(TeamScoreData data : datas){
							if(data.getItemId().equals(item.getId())){
								dataVo.setScore(data.getScore());
								dataVo.setRemark(data.getRemark());
								
								List<ScoreFile> files = new ArrayList<ScoreFile>();
								if(data.getFileUUIDs() != null && data.getFileUUIDs().size() > 0){
									for(String uuid : data.getFileUUIDs()){
										ScoreFile scoreFile = new ScoreFile();
										scoreFile.setUUID(uuid);
										scoreFile.setUrl(ImgUtil.getImgUrl(uuid));
										files.add(scoreFile);
									}
								}
								dataVo.setFileUUIDs(files);
								flag = false;
								break;
							}
						}
					}
					if(flag){
						dataVo.setScore((float)0);
						dataVo.setRemark("");
						dataVo.setFileUUIDs(null);
					}
					list.add(dataVo);
				}
				return new ResponseVo<List<TeamScoreDataVo>>("0", list);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数内容不正确");
				info.setMsg("输入的数据不正确");
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
			info.setParam("termCode,teamId,checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
	}

	@Override
	public Object batchGetTeamMinusEvaScore(String termCode, Integer teamId,String checkDate) {
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
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamScoreData> datas = teamApsService.getTeamMinusScore(termCode, teamId, sdf.parse(checkDate));
			
			List<TeamScoreDataVo> list = new ArrayList<TeamScoreDataVo>();
			//List<ApsTaskItem> itemList = teamApsService.findTeamTaskItems(termCode, ApsTaskContants.CHECK_TYPE_MINUS);
			List<ApsTaskItem> itemList = teamApsService.findUnionItem(termCode, ApsTaskContants.CHECK_TYPE_MINUS, sdf.parse(checkDate));
			if(itemList != null && itemList.size() > 0){
				
				for(ApsTaskItem item : itemList){
					TeamScoreDataVo dataVo = new TeamScoreDataVo();
					dataVo.setTeamId(teamId);
					dataVo.setItemId(item.getId());
					dataVo.setItemName(item.getName());
					dataVo.setCode(item.getCode());
					Boolean flag = true;
					if(datas != null && datas.size() > 0){
						for(TeamScoreData data : datas){
							if(data.getItemId().equals(item.getId())){
								dataVo.setScore(data.getScore());
								dataVo.setRemark(data.getRemark());
								
								List<ScoreFile> files = new ArrayList<ScoreFile>();
								if(data.getFileUUIDs() != null && data.getFileUUIDs().size() > 0){
									for(String uuid : data.getFileUUIDs()){
										ScoreFile scoreFile = new ScoreFile();
										scoreFile.setUUID(uuid);
										scoreFile.setUrl(ImgUtil.getImgUrl(uuid));
										files.add(scoreFile);
									}
								}
								dataVo.setFileUUIDs(files);
								flag = false;
								break;
							}
						}
					}
					if(flag){
						dataVo.setScore((float)0);
						dataVo.setRemark("");
						dataVo.setFileUUIDs(null);
					}
					list.add(dataVo);
				}
				return new ResponseVo<List<TeamScoreDataVo>>("0", list);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数内容不正确");
				info.setMsg("输入的数据不正确");
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
			info.setParam("termCode,teamId,checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}
	

	@Override
	public Object setTeamEvaScore(Integer taskItemId, Integer teamId,
			Integer gradeId, Integer schoolId, Integer userId, Float score, String checkDate,
			String remark, String fileUUIDs) {
		try{
			if(taskItemId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("taskItemId参数必填");
				info.setMsg("taskItemId参数不能为空");
				info.setParam("taskItemId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
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
			if(teamId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("teamId参数必填");
				info.setMsg("teamId参数不能为空");
				info.setParam("teamId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(gradeId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("gradeId参数必填");
				info.setMsg("gradeId参数不能为空");
				info.setParam("gradeId");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			if(score == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("score参数必填");
				info.setMsg("score参数不能为空");
				info.setParam("score");
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
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
			if(teacher != null){
				List<String> list = null;
				if(fileUUIDs != null && !"".equals(fileUUIDs)){
					list = JSON.parseArray(fileUUIDs, String.class);
				}
				if(remark == null){
					remark = "";
				}
				
				teamApsService.setTeamEvaluationTaskScore(taskItemId, teamId, gradeId, teacher.getId(), score, sdf.parse(checkDate), remark, list);
				
				Grade grade = gradeService.findGradeById(gradeId);
				SchoolTermCondition stc = new SchoolTermCondition();
				stc.setSchoolId(schoolId);
				stc.setSchoolYear(grade.getSchoolYear());
				List<SchoolTerm> termList = schoolTermService.findSchoolTermByCondition(stc, null, null);
				for(SchoolTerm term : termList){
					teamApsService.finishedJudge(term.getCode(), gradeId, teacher.getId(), sdf.parse(checkDate));
				}
				//teamApsService.finishedJudge(termCode, gradeId, teacher.getId(), sdf.parse(checkDate));
				return new ResponseVo<String>("0", "保存成功");
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId,userId参数异常");
				info.setMsg("schoolId,userId参数异常");
				info.setParam("schoolId,userId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
		
		}catch(JSONException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("数据格式不正确");
			info.setMsg("输入的数据格式不正确");
			info.setParam("fileUUIDs");
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
			info.setParam("taskItemId,teamId,gradeId,teacherId,score,checkDate,remark,fileUUIDs");
			Log.info("保存失败,失败信息=======", e);
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
	}

	@Override
	public Object getTeamEvaScore(Integer taskItemId, Integer teamId, String checkDate) {
		try{
			if(taskItemId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("taskItemId参数必填");
				info.setMsg("taskItemId参数不能为空");
				info.setParam("taskItemId");
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
			TeamScoreData data = teamApsService.getTeamEvaScore(taskItemId, teamId, sdf.parse(checkDate));
			TeamScoreDataVo dataVo = new TeamScoreDataVo();
			if(data != null){
				dataVo.setItemId(data.getItemId());
				dataVo.setItemName(data.getItemName());
				dataVo.setTeamId(data.getTeamId());
				dataVo.setScore(data.getScore());
				dataVo.setRemark(data.getRemark());
				List<ScoreFile> files = new ArrayList<ScoreFile>();
				if(data.getFileUUIDs() != null && data.getFileUUIDs().size() > 0){
					for(String uuid : data.getFileUUIDs()){
						ScoreFile scoreFile = new ScoreFile();
						scoreFile.setUUID(uuid);
						scoreFile.setUrl(ImgUtil.getImgUrl(uuid));
						files.add(scoreFile);
					}
				}
				dataVo.setFileUUIDs(files);
			}
			
			return new ResponseVo<TeamScoreDataVo>("0", dataVo);
			
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
			info.setParam("taskItemId,teamId,checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object summaryTeamEvaluationTaskForTeam(String termCode,
			Integer teamId, String beginDate, String endDate,String jsonpCallback) {
		
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamSummaryData> dataList = teamApsService.summaryTeamEvaluationTaskForTeam(termCode, teamId, sdf.parse(beginDate), sdf.parse(endDate));
			String json = JSON.toJSONString(dataList);
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
			info.setParam("termCode, teamId, beginDate, endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		}
		
	}

	@Override
	public Object summaryTeamEvaluationTaskForGrade(String termCode,
			Integer gradeId, String beginDate, String endDate, String jsonpCallback) {

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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamSummaryData> dataList = teamApsService.summaryTeamEvaluationTaskForGrade(gradeId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			String json = JSON.toJSONString(dataList);
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
			info.setParam("termCode, teamId, beginDate, endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		}
	}

	@Override
	public Object summaryTeamEvaluationTaskForSchool(String termCode,
			Integer schoolId, String beginDate, String endDate, String jsonpCallback) {

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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamSummaryData> dataList = teamApsService.summaryTeamEvaluationTaskForSchool(schoolId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			String json = JSON.toJSONString(dataList);
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
			info.setParam("termCode, teamId, beginDate, endDate");
			return jsonpCallback + "(" + new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info) + ")";
		}
		
	}

	@Override
	public Object summaryTeamEvaluationTaskForTeam(String termCode,
			Integer teamId, String beginDate, String endDate) {
		try{
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info) ;
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
			List<TeamSummaryData> dataList = teamApsService.summaryTeamEvaluationTaskForTeam(termCode, teamId, sdf.parse(beginDate), sdf.parse(endDate));
			return new ResponseVo<List<TeamSummaryData>>("0", dataList);
			
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
			info.setParam("termCode, teamId, beginDate, endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object summaryTeamEvaluationTaskForGrade(String termCode,
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
			List<TeamSummaryData> dataList = teamApsService.summaryTeamEvaluationTaskForGrade(gradeId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			if(dataList != null && dataList.size() > 0){
				return new ResponseVo<List<TeamSummaryData>>("0", dataList);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到评分数据");
				info.setMsg("查询结果为空");
				info.setParam("termCode, teamId, beginDate, endDate");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
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
			info.setParam("termCode, teamId, beginDate, endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object summaryTeamEvaluationTaskForSchool(String termCode,
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
			List<TeamSummaryData> dataList = teamApsService.summaryTeamEvaluationTaskForSchool(schoolId, termCode, sdf.parse(beginDate), sdf.parse(endDate));
			if(dataList != null && dataList.size() > 0){
				return new ResponseVo<List<TeamSummaryData>>("0", dataList);
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到评分数据");
				info.setMsg("查询结果为空");
				info.setParam("termCode, teamId, beginDate, endDate");
				return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
			}
			
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
			info.setParam("termCode, teamId, beginDate, endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
		
	}

	@Override
	public Object batchGetAddScore(String termCode, Integer teamId,String checkDate) {
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
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamEvaScoreData> dataList = teamApsService.getScoreOfAdd(termCode, teamId, sdf.parse(checkDate));
			
			return new ResponseVo<List<TeamEvaScoreData>>("0", dataList);
		
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
			info.setParam("termCode,teamId,checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object batchGetMinusScore(String termCode, Integer teamId,
			String checkDate) {
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
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			List<TeamEvaScoreData> dataList = teamApsService.getScoreOfMinus(termCode, teamId, sdf.parse(checkDate));
			
			return new ResponseVo<List<TeamEvaScoreData>>("0", dataList);
		
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
			info.setParam("termCode,teamId,checkDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
		}
	}

	@Override
	public Object batchGetScore(String termCode, Integer teamId, String checkDate) {
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
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TeamEvaData teamEvaData = new TeamEvaData();
			
			List<TeamEvaScoreData> minusList = teamApsService.getScoreOfMinus(termCode, teamId, sdf.parse(checkDate));
			List<TeamEvaScoreData> addList = teamApsService.getScoreOfAdd(termCode, teamId, sdf.parse(checkDate));
			float minusScore = 0;
			float addScore = 0;
			if(minusList !=null && minusList.size() > 0){
				for(TeamEvaScoreData data : minusList){
					minusScore = addFloat(minusScore,data.getScore());
				}
			}
			if(addList !=null && addList.size() > 0){
				for(TeamEvaScoreData data : addList){
					addScore = addFloat(addScore,data.getScore());
				}
			}
			
			teamEvaData.setMinusScoreList(minusList);
			teamEvaData.setAddScoreList(addList);
			teamEvaData.setMinusScore(Math.abs(minusScore));
			teamEvaData.setAddScore(addScore);
			return new ResponseVo<TeamEvaData>("0", teamEvaData);
		
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
	public Object batchGetAllScore(String termCode, Integer teamId, String checkDate) {
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
			if(checkDate == null || "".equals(checkDate)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("checkDate参数必填");
				info.setMsg("checkDate参数不能为空");
				info.setParam("checkDate");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TeamEvaData teamEvaData = new TeamEvaData();

			List<TeamEvaScoreData> minusList = teamApsService.getScoreOfMinus(termCode, teamId, sdf.parse(checkDate));
			List<TeamEvaScoreData> addList = teamApsService.getScoreOfAdd(termCode, teamId, sdf.parse(checkDate));
			float minusScore = 0;
			float addScore = 0;
			if(minusList !=null && minusList.size() > 0){
				for(TeamEvaScoreData data : minusList){
					minusScore = addFloat(minusScore,data.getScore());
				}
			}
			if(addList !=null && addList.size() > 0){
				for(TeamEvaScoreData data : addList){
					addScore = addFloat(addScore,data.getScore());
				}
			}
			List<TeamEvaScoreData> all = new ArrayList<TeamEvaScoreData>();
			for(TeamEvaScoreData min : minusList){
				all.add(min);
			}
			for(TeamEvaScoreData add : addList){
				all.add(add);
			}
			return new ResponseVo<List<TeamEvaScoreData>>("0", all);

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

	public float addFloat(float a, float b){
		 BigDecimal b1 = new BigDecimal(String.valueOf(a));
		 BigDecimal b2 = new BigDecimal(String.valueOf(b));
		 BigDecimal b3 = b1.add(b2);
		 float f = b3.floatValue();
		 return f;
	}

	@Override
	public Object getJudgeTeachers(String role, Integer schoolId, Integer userId, Integer gradeId,
			String beginDate, String endDate) {
		
		List<DayInfo> list = null;
		try{
			if(role == null || "".equals(role)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("role参数必填");
				info.setMsg("role参数不能为空");
				info.setParam("role");
				return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
			}
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
			
			Boolean isAll = true; 
			if("SCHOOL_LEADER".equals(role) || "SCHOOL_MASTER".equals(role)){
				isAll = true; 
			}else if("CLASS_MASTER".equals(role) || "SUBJECT_TEACHER".equals(role)){
				isAll = false; 
			}else{
				ResponseInfo info = new ResponseInfo();
				info.setDetail("role参数异常");
				info.setMsg("role参数异常");
				info.setParam("role");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			
			list = new ArrayList<DayInfo>();
			String[] dayArr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(endDate);
			if(begin.getTime() > end.getTime()){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("日期参数异常");
				info.setMsg("输入的参数异常");
				info.setParam("begin,endDate");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
//			if(begin.getDay() < 1){		
//				begin.setTime(begin.getTime()+86400000);
//			}
//			if(end.getDay() > 5){			
//				end.setTime(end.getTime()-86400000);
//			}
			
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			Teacher teacherOne = teacherService.findOfUser(schoolId, userId);
			if(!isAll && teacherOne == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("找不到教师(用户)");
				info.setMsg("输入的参数异常");
				info.setParam("schoolId,userId");
				return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
			}
			//List<TeamTeacher> teamTeacherList = teamTeacherService.getTeachersOfGrade(gradeId);	
			List<Teacher> teacherList = teacherService.findGradeOfTeacher(gradeId, schoolTermCurrent.getSchoolYear(),true);
			List<ApsTaskJudge> judgeList = null;	//值日教师数据
			List<JudgeTeacher> judgeTeacherList = null;
			
			Date currentDate = new Date();
			Date today = new Date();
			int n = (int) ((end.getTime()-begin.getTime())/86400000)+1;
			for(int i=0; i<n; i++){
				DayInfo dutyTeacher = new DayInfo();
				currentDate.setTime(begin.getTime()+86400000*i);
				dutyTeacher.setDate(sdf.format(currentDate));
				dutyTeacher.setDayOfWeek(dayArr[currentDate.getDay()]);
				if(currentDate.getTime() <= today.getTime() && today.getTime() < (currentDate.getTime()+86400000)){
					dutyTeacher.setIsCurrent(1);
				}else{
					dutyTeacher.setIsCurrent(0);
				}
				
				judgeTeacherList = new ArrayList<JudgeTeacher>();  //年级所有教师转存数据
				judgeList = teamApsService.findTaskJudge(schoolTermCurrent.getSchoolTermCode(), gradeId, currentDate);
				
				if(isAll){		//全部教师列表
					//将年级所有教师的数据转入judgeTeacherList中
					for(Teacher teacher : teacherList){
						JudgeTeacher judgeTeacher = new JudgeTeacher();
						judgeTeacher.setTeacherId(teacher.getId());
						judgeTeacher.setTeacherName(teacher.getName());
						judgeTeacher.setUserId(teacher.getUserId());
						judgeTeacher.setOnDutyDate(sdf.format(currentDate));
						judgeTeacher.setUrl(ImgUtil.getImgSrc(teacher.getUserId(), profileService));
						judgeTeacher.setSex(teacher.getSex());
						
						Boolean flag = false;
						//遍历judge表中教师，判断是否与教师相同
						if(judgeList != null && judgeList.size() > 0){
							for(int j=0; j<judgeList.size(); j++){
								if(judgeTeacher.getTeacherId().equals(judgeList.get(j).getTeacherId())){
									flag = true;
									judgeList.remove(j);
									break;
								}
							}
						}
						if(flag){
							judgeTeacher.setIsChoose(1);
						}else{
							judgeTeacher.setIsChoose(0);
						}
						judgeTeacherList.add(judgeTeacher);
					}
					dutyTeacher.setTeacherList(judgeTeacherList);
					
				}else{		//值日教师列表
					if(judgeList != null && judgeList.size() > 0){
						for(ApsTaskJudge taskJudge : judgeList){
							JudgeTeacher judgeTeacher = new JudgeTeacher();
							Teacher teacher = teacherService.findTeacherById(taskJudge.getTeacherId());
							judgeTeacher.setTeacherId(taskJudge.getTeacherId());
							judgeTeacher.setTeacherName(teacher.getName());
							judgeTeacher.setUserId(taskJudge.getUserId());
							judgeTeacher.setOnDutyDate(sdf.format(currentDate));
							judgeTeacher.setUrl(ImgUtil.getImgSrc(judgeTeacher.getUserId(), profileService));
							judgeTeacher.setSex(teacher.getSex());
							if(judgeTeacher.getTeacherId().equals(teacherOne.getId())){
								judgeTeacher.setIsChoose(1);
							}else{
								judgeTeacher.setIsChoose(0);
							}
							judgeTeacherList.add(judgeTeacher);
						}
					}
					dutyTeacher.setTeacherList(judgeTeacherList);
					
				}
				list.add(dutyTeacher);
			}
			
			
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
			info.setDetail("找不到数据");
			info.setMsg("参数异常");
			info.setParam("schoolId,gradeId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
		}
		return new ResponseVo<List<DayInfo>>("0", list);
	}

	@Override
	public Object batchSetJudgeTeachers(Integer schoolId, Integer gradeId,
			String beginDate, String endDate, String teacherData, String periodCode) {
		
		try{
			if(schoolId == null){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("schoolId参数必填");
				info.setMsg("schoolId参数不能为空");
				info.setParam("schoolId");
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
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			List<JudgeTeacher> list = new ArrayList<JudgeTeacher>();
			if(teacherData != null && !"".equals(teacherData)){
				JSONArray jsonArray = JSONArray.fromObject(teacherData);
				for(int i=0; i<jsonArray.size(); i++){
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					JudgeTeacher data = JSON.parseObject(jsonObject.toString(), JudgeTeacher.class);
					list.add(data);
				}
			}
			String week  = "第" + periodCode + "周";
			teamApsService.batchSetJudgeTeacher(schoolTermCurrent.getSchoolTermCode(), 
					gradeId, sdf.parse(beginDate), sdf.parse(endDate), list, week);
			return new ResponseVo<String>("0", "保存成功");
			
		}catch(net.sf.json.JSONException e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数异常");
			info.setMsg("输入的数据格式不正确");
			info.setParam("teacherData");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
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
			info.setDetail("参数异常");
			info.setMsg("保存失败");
			info.setParam("schoolId,gradeId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
		}
	}

	@Override
	public Object findJudgeTeacher(Integer schoolId, Integer userId) {
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
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
			List<ApsTaskJudge> list = teamApsService.findJudgeTeacher(
					schoolTermCurrent.getSchoolTermCode(), teacher.getId(), new Date());
			Boolean isOnDuty = false;
			if(list != null && list.size() > 0){
				isOnDuty = true;
			}
			return new ResponseVo<Boolean>("0",isOnDuty);
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("找不到数据");
			info.setMsg("参数异常");
			info.setParam("schoolId,userId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
		}
	}

	@Override
	public Object getJudgeGrades(Integer schoolId, Integer userId) {
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
			SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			Teacher teacher = teacherService.findOfUser(schoolId, userId);
			List<ApsTaskJudge> list = teamApsService.findJudgeTeacher(
					schoolTermCurrent.getSchoolTermCode(), teacher.getId(), new Date());
			List<Grade> gradeList = new ArrayList<Grade>();
			List<GradeInfo> gradeInfoList = null;
			if(list != null && list.size() > 0){
				gradeInfoList = new ArrayList<GradeInfo>();
				for(ApsTaskJudge judge : list){
					Grade grade = gradeService.findGradeById(judge.getGradeId());
					gradeList.add(grade);
				}
				sortGrade(gradeList);
				for(Grade grade : gradeList){
					GradeInfo gradeInfo = new GradeInfo();
					gradeInfo.setGradeId(grade.getId());
					gradeInfo.setGradeName(grade.getName());
					gradeInfo.setGradeCode(grade.getUniGradeCode());
					
					List<TeamInfo> teamInfoList = new ArrayList<TeamInfo>();
					List<Team> teamList = teamService.findTeamOfGradeByAsc(grade.getId());
					sortTeam(teamList);
					if(teamList != null && teamList.size() > 0){
						for(Team team : teamList){
							TeamInfo teamInfo = new TeamInfo();
							teamInfo.setTeamId(team.getId());
							teamInfo.setTeamName(team.getName());
							teamInfo.setName(team.getTeamNumber()+"班");
							teamInfo.setTeamNumber(team.getTeamNumber());
							teamInfoList.add(teamInfo);
						}
					}
					
					gradeInfo.setTeamList(teamInfoList);
					gradeInfoList.add(gradeInfo);
				}
			}
			return new ResponseVo<List<GradeInfo>>("0", gradeInfoList);
			
		}catch(Exception e){
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("找不到数据");
			info.setMsg("参数异常");
			info.setParam("schoolId,userId");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
		}
	}
	
	public void sortGrade(List<Grade> gradeList){
		Collections.sort(gradeList, new Comparator<Grade>(){  
			public int compare(Grade d1, Grade d2) {  
				Integer n1 = Integer.parseInt(d1.getUniGradeCode());
				Integer n2 = Integer.parseInt(d2.getUniGradeCode());
				if(n1 > n2){  
					return 1;  
				}  
				if(n1 == n2){  
					return 0;  
				}  
				return -1;  
			}
		});
	}
	
	public void sortTeam(List<Team> teamList){
		Collections.sort(teamList, new Comparator<Team>(){  
			public int compare(Team d1, Team d2) {  
				if(d1.getTeamNumber()>d2.getTeamNumber()){  
					return 1;  
				}  
				if(d1.getTeamNumber()==d2.getTeamNumber()){  
					return 0;  
				}  
				return -1;  
			}
		});
	}

	@Override
	public Object getDutyTeacherStatistics(
			String termCode, Integer gradeId, 
			String beginDate, String endDate) {
		try {
			if(termCode == null || "".equals(termCode)){
				ResponseInfo info = new ResponseInfo();
				info.setDetail("termCode参数必填");
				info.setMsg("termCode参数不能为空");
				info.setParam("termCode");
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
			Date begin = sdf.parse(beginDate);
			Date end = sdf.parse(endDate);
			String[] arr = termCode.split("-");
 			List<DutyTeacherStatData> teacherList = teamApsService.dutyTeacherStatistics(
					Integer.parseInt(arr[0]), arr[1], termCode, gradeId, begin, end, null, null);	
			List<DutyTeacherStatDataVo> list = null;
			if(teacherList != null && teacherList.size() > 0){
				list = new ArrayList<DutyTeacherStatDataVo>();
				for(DutyTeacherStatData data : teacherList){
					DutyTeacherStatDataVo vo = new DutyTeacherStatDataVo();
					vo.setName(data.getName());
					vo.setGradeName(data.getGradeName());
					vo.setSex(data.getSex());
					vo.setUrl(ImgUtil.getImgSrc(data.getUserId(), profileService));
					vo.setDutyDayCount(data.getDutyDayCount());
					vo.setFinishedDayCount(data.getFinishedDayCount());
					list.add(vo);
				}
			}
			return new ResponseVo<List<DutyTeacherStatDataVo>>("0", list);
		
		} catch (ParseException e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数格式异常");
			info.setMsg("参数异常");
			info.setParam("beginDate,endDate");
			return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);	
		} catch (Exception e) {
			e.printStackTrace();
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数异常");
			info.setMsg("参数异常");
			info.setParam("termCode");
			return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);	
		}
	}

	//得到给定月份的第一天和最后一天
	public Date[] monthTime(String month) throws ParseException{//月份  2016年6月
		String year = month.substring(0,4);
		String month1 = month.substring(5);
		int yearDateTime = Integer.parseInt(year);
		int monthDateTime = Integer.parseInt(month1);
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");
		
		//当月第一天
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, yearDateTime);
        cal1.set(Calendar.MONTH, monthDateTime-1);
        cal1.set(Calendar.DAY_OF_MONTH,cal1.getMinimum(Calendar.DATE));
        String str1 = new SimpleDateFormat("yyyy-MM-dd").format(cal1.getTime());
        Date beginDate = sdf.parse(str1);
        
		//当月最后一天
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, yearDateTime);
		cal2.set(Calendar.MONTH, monthDateTime-1);
		cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DATE));
		String str2 = new SimpleDateFormat("yyyy-MM-dd").format(cal2.getTime());
		Date endDate = sdf.parse(str2);
        
        Date[] monthDate = {beginDate,endDate};
        
		return monthDate;
	}

}
