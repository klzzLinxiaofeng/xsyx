package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.ExtImportTeacherErrorMsg;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.ExtSyllabusDayVo;
import platform.szxyzxx.web.teach.client.vo.ExtSyllabusLessonVo;
import platform.szxyzxx.web.teach.client.vo.ExtSyllabusVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * @function 课表业务
 * @date 2016-2-19
 * @author panfei
 *
 */

@Controller
@RequestMapping("/school/syllabus")
public class ExtSyllabusController extends BaseController{
	private Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * @function 返回一个班级课表安排情况
	 * @param teamId  班级Id
	 * @param termCode  学期code码
	 * @date 2016-2-19
	 * @return
	 */
	@RequestMapping("/team/listTable")
	@ResponseBody
	public Object getSyllabusByTeam(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "termCode", required = false) String termCode){
		//单张课表的信息
		ExtSyllabusVo extSyllabusVo = null;
		
		//每天的课表安排
		ExtSyllabusDayVo extSyllabusDayVo = null;
		
		//多天的课程安排
		List<ExtSyllabusDayVo> extSyllabusDayVoList = new ArrayList<ExtSyllabusDayVo>();
		
		//每节课的安排
		ExtSyllabusLessonVo extSyllabusLessonVo = null;
		
		//多节课的安排
		List<ExtSyllabusLessonVo> extSyllabusLessonVoList = new ArrayList<ExtSyllabusLessonVo>();
		try{
			//判断是否传参
			if(teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			//判断是否传参
			if(termCode == null || "".equals(termCode)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("termCode");
				return new ResponseError("060111", info);
			}
			
			//根据学期和班级获取课表
			Syllabus syllabus = syllabusService.getTeamSyllabus(teamId,termCode);
			
			if(syllabus != null){
				extSyllabusVo = new ExtSyllabusVo();
				//课表ID
				extSyllabusVo.setSyllabusId(syllabus.getId());
				//早上节数
				extSyllabusVo.setMorning(syllabus.getLessonOfMorning());
				//下午节数
				extSyllabusVo.setAfternoon(syllabus.getLessonOfAfternoon());
				//晚上节数
				extSyllabusVo.setEvening(syllabus.getLessonOfEvening());
				
				Integer morning = syllabus.getLessonOfMorning();
				Integer afternoon = syllabus.getLessonOfAfternoon();
				
				String weekStr = syllabus.getDaysPlan();
				if(weekStr != null && !"".equals(weekStr)){
					String[] weeks = weekStr.split(",");
					//循环出每天
					for(String week : weeks){
						extSyllabusLessonVoList = new ArrayList<ExtSyllabusLessonVo>();
						extSyllabusDayVo = new ExtSyllabusDayVo();
						extSyllabusDayVo.setDay(week);
						
						SyllabusLessonCondition syllabusLessonCondition = new SyllabusLessonCondition();
						syllabusLessonCondition.setDayOfWeek(week);
						syllabusLessonCondition.setSyllabusId(syllabus.getId());
						List<SyllabusLesson> list = syllabusService.findSyllabusLessonByCondition(syllabusLessonCondition, null, null);
						if(list != null && list.size() > 0){
							for(SyllabusLesson sy : list){
								extSyllabusLessonVo = new ExtSyllabusLessonVo();
								if(sy.getTeacherId() != null){
									Teacher teacher = teacherService.findTeacherById(sy.getTeacherId());
									if(teacher != null){
										extSyllabusLessonVo.setTeacherName(teacher.getName());
									}
								}
								if(sy.getSubjectCode() != null && !"".equals(sy.getSubjectCode())){
									Subject subject = subjectService.findUnique(syllabus.getSchoolId(), sy.getSubjectCode());
									if(subject != null){
										extSyllabusLessonVo.setSubjectName(subject.getName());
									}
								}else{
									extSyllabusLessonVo.setSubjectName(sy.getSubjectName());
								}
								
								Integer lesson = sy.getLesson();
								/*if(lesson > SysContants.LESSON_COUNT_MORNING && lesson <= (SysContants.LESSON_COUNT_MORNING + SysContants.LESSON_COUNT_AFTERNOON)){
									lesson = morning + (lesson - SysContants.LESSON_COUNT_MORNING);
								}
								if(lesson > (SysContants.LESSON_COUNT_MORNING + SysContants.LESSON_COUNT_AFTERNOON)){
									lesson = morning + afternoon + lesson - (SysContants.LESSON_COUNT_MORNING + SysContants.LESSON_COUNT_AFTERNOON);
								}*/
								
								extSyllabusLessonVo.setLesson(lesson);
								extSyllabusLessonVo.setTeacherId(sy.getTeacherId());
								extSyllabusLessonVo.setSubjectCode(sy.getSubjectCode());
								
								extSyllabusLessonVoList.add(extSyllabusLessonVo);
							}
							extSyllabusDayVo.setLessons(extSyllabusLessonVoList);
						}
						extSyllabusDayVoList.add(extSyllabusDayVo);
					}
				}
				extSyllabusVo.setDays(extSyllabusDayVoList);
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("获取课表信息失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("获取课表信息信息异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		return new ResponseVo<ExtSyllabusVo>("0", extSyllabusVo);
	}
	
	/**
	 * @function 课表的导入功能 （新课表的导入   旧课表的覆盖）
	 * @param teamId
	 * @param termCode
	 * @param data
	 * @date 2016-3-10
	 * @return importErrorMess
	 */
	@RequestMapping("/team/importTable")
	@ResponseBody
	public Object importSyllabus(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "termCode", required = false) String termCode,
			@RequestParam(value = "data", required = false) String dataJson){
		
		//判断是否传参
		if(teamId == null || "".equals(teamId)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("teamId");
			return new ResponseError("060111", info);
		}
		
		Team team = teamService.findTeamById(teamId);
		if(team == null){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("所传参数teamId找不到对应班级...");
			info.setMsg("无效参数");
			info.setParam("teamId");
			return new ResponseError("060111", info);
		}
		
		//判断是否传参
		if(termCode == null || "".equals(termCode)) {
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("termCode");
			return new ResponseError("060111", info);
		}
		
		if(dataJson == null || "".equals(dataJson)){
			ResponseInfo info = new ResponseInfo();
			info.setDetail("参数不能为空...");
			info.setMsg("必填参数为空");
			info.setParam("data");
			return new ResponseError("060111", info);
		}
		
		//主表数据
		Syllabus syllabus = new Syllabus();
		//附表数据
		SyllabusLesson syllabusLesson = null;
		//附表数据集合
		List<SyllabusLesson> syllabusLessonList = new ArrayList<SyllabusLesson>();
		//教师
		Teacher teacher = null;
		try{
			//解析JSON数据
			JSONArray data = JSONArray.fromObject(dataJson);
			
			if(data != null && data.size() > 0){
				for(int i = 0; i < data.size(); i++){
					JSONObject jsonJ = data.getJSONObject(i);
					//课表Id  如果该值为0 表示这个是新建的课表  反之，是已存在的课表  导入的时候需要覆盖
					Integer syllabusId = jsonJ.getInt("syllabusId");
					//早上节次
					Integer morning = jsonJ.getInt("morning");
					//下午节次
					Integer afternoon = jsonJ.getInt("afternoon");
					//晚上节次
					Integer evening = jsonJ.getInt("evening");
					
					JSONArray jsonArrayOfDays = jsonJ.getJSONArray("days");
					
					//每周哪几天安排上课
					String daysPlan = "";
					
					//每周安排上课天数
					Integer days = jsonArrayOfDays.size();
					
					if(jsonArrayOfDays != null && jsonArrayOfDays.size() > 0){
						for(int j = 0; j < jsonArrayOfDays.size(); j++){
							JSONObject jsonDays = jsonArrayOfDays.getJSONObject(j);
							String day = jsonDays.getString("day");
							if(daysPlan==""){
								daysPlan += day;
							}else{
								daysPlan += "," + day;
							}
							
							JSONArray jsonArrayOfLesson = jsonDays.getJSONArray("lessons");
							if(jsonArrayOfLesson != null && jsonArrayOfLesson.size() > 0){
								for(int k = 0; k < jsonArrayOfLesson.size(); k++){
									teacher = null;
									JSONObject jsonLessons = jsonArrayOfLesson.getJSONObject(k);
									
									//2016-3-23 修改：教师Id可以为空值
									String lessonStr = jsonLessons.getString("lesson");
									Integer lesson = null;
									if(lessonStr != null && !"".equals(lessonStr)){
										lesson = Integer.valueOf(lessonStr);
									}
									
									String teacherId = jsonLessons.getString("teacherId");
									Integer teacherUserId = null;
									
									if(teacherId != null && !"".equals(teacherId)){
										teacherUserId = Integer.valueOf(teacherId);
									}
									
									String subjectCode = jsonLessons.getString("subjectCode");
									String subjectName = jsonLessons.getString("subjectName");
									if(teacherUserId != null){
										teacher = teacherService.findOfUser(team.getSchoolId(), teacherUserId);
									}
									
									//2016-3-23 添加  更准确的获取名字
									if(subjectCode != null && !"".equals(subjectCode)){
										Subject subject = subjectService.findUnique(team.getSchoolId(), subjectCode);
										if(subject != null){
											subjectName = subject.getName();
										}
									}
									
//									//2016-3-28 添加   如果转过来的数据是空值 或者自习   任课教师  默认为班主任
//									if(subjectName == null || "".equals(subjectName) || subjectName.equals("自习")){
//										List<Teacher> masterList = teacherService.getMasterOfTeam(teamId);
//										if(masterList != null && masterList.size() > 0){
//											teacher = masterList.get(0);
//										}
//									}
									
									//按实际来，第几节就是第几节 changed by JJ
									/*if(lesson > morning && lesson <= (morning + afternoon)){
										lesson = SysContants.LESSON_COUNT_MORNING + (lesson - morning);
									}
									if(lesson > (morning + afternoon)){
										lesson = SysContants.LESSON_COUNT_MORNING + SysContants.LESSON_COUNT_AFTERNOON + lesson - (morning + afternoon);
									}*/
									
									
									
									//附表数据
									syllabusLesson = new SyllabusLesson();
									syllabusLesson.setCreateDate(new Date());
									syllabusLesson.setDayOfWeek(day);
									syllabusLesson.setId(null);
									syllabusLesson.setLesson(lesson);
									syllabusLesson.setModifyDate(new Date());
									syllabusLesson.setSubjectCode(subjectCode);
									syllabusLesson.setSubjectName(subjectName);
									syllabusLesson.setSyllabusId(syllabusId);
									syllabusLesson.setTeacherId(teacher == null ? null : teacher.getId());
									syllabusLessonList.add(syllabusLesson);
								}
							}
						}
					}
					
					//主表数据
					syllabus.setCreateDate(new Date());
					syllabus.setDays(days);
					syllabus.setId(syllabusId);
					syllabus.setDaysPlan(daysPlan);
					syllabus.setLessonOfAfternoon(afternoon);
					syllabus.setLessonOfEvening(evening);
					syllabus.setLessonOfMorning(morning);
					syllabus.setLessonTimes(null);
					syllabus.setModifyDate(new Date());
					syllabus.setSchoolId(team.getSchoolId());
					syllabus.setSchoolYear(team.getSchoolYear());
					syllabus.setTeamId(teamId);
					syllabus.setTermCode(termCode);
					
					//2016-9-28 添加：目的为了防止客户端的校验出错，如本该修改的，变成新建导致数据错误的问题
					Syllabus syll = syllabusService.getTeamSyllabus(teamId,termCode);
					if(syll != null && syll.getId() != null){
						syllabus.setId(syll.getId());
					}else{
						//0表示课表不存在，需要新建
						syllabus.setId(0);
					}
					//===================================================================
					
					Syllabus sy = syllabusService.importSyllabus(syllabus,syllabusLessonList);
					if(sy != null){
						return new ResponseVo<List<ExtImportTeacherErrorMsg>>("0", null);
					}else{
						log.debug("导入失败");
						ResponseInfo info = new ResponseInfo();
						info.setDetail("导入失败");
						info.setMsg("未知错误");
						return new ResponseError("000001", info);
					}
				}
			}
		}catch(Exception e){
			log.debug("导入失败");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("导入失败");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
		return new ResponseVo<List<ExtImportTeacherErrorMsg>>("0", null);
	}

}
