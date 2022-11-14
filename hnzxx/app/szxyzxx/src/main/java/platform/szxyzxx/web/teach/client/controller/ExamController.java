package platform.szxyzxx.web.teach.client.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.PjExamService;
import platform.education.generalTeachingAffair.vo.ExamResult;
import platform.education.generalTeachingAffair.vo.PjExamCondition;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/school/exam")
public class ExamController extends BaseController{
	
	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * @function 获取一个班级单科的一次考试的成绩数据
	 * @param teamId
	 * @param schoolYear
	 * @param termValue
	 * @param subjectCode
	 * @param examType
	 * @param examRound  , method = RequestMethod.POST
	 * @return
	 */
	@RequestMapping(value = "/data/getByTeamSubject")
	@ResponseBody
	public Object getByTeamSubject(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termValue", required = false) String termValue,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "examType", required = false) String examType,
			@RequestParam(value = "examRound", required = false,defaultValue="1") Integer examRound
			){
		ExtExamStudentVo examStudentVo = null;
		Student student = null;
		List<ExtExamStudentVo> data = new ArrayList<ExtExamStudentVo>();
		
		ResponseInfo info = null;
		boolean teamIdIsNull = teamId == null || "".equals(teamId);
		boolean schoolYearIsNull = schoolYear == null || "".equals(schoolYear);
		boolean termValueIsNull = termValue == null || "".equals(termValue);
		boolean subjectCodeIsNull = subjectCode == null || "".equals(subjectCode);
		boolean examTypeIsNull = examType == null || "".equals(examType);
		
		if(teamIdIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("班级为空");
			info.setParam("teamId");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(schoolYearIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学年为空");
			info.setParam("schoolYear");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(termValueIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学期为空");
			info.setParam("termValue");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(subjectCodeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("科目为空");
			info.setParam("subjectCode");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(examTypeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("考试类型为空");
			info.setParam("examType");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		try{
			PjExamCondition pjExamCondition = new PjExamCondition();
			pjExamCondition.setTeamId(teamId);
			pjExamCondition.setSchoolYear(schoolYear);
			pjExamCondition.setTermValue(termValue);
			pjExamCondition.setSubjectCode(subjectCode);
			pjExamCondition.setExamType(examType);
			pjExamCondition.setExamRound(examRound);
			List<PjExam> examList = pjExamService.findPjExamByCondition(pjExamCondition);
			if(examList.size() > 0){
				PjExam exam = examList.get(0);
				List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(exam.getId());
				if(examStudentList.size() > 0){
					for(ExamStudent es : examStudentList){
						student = studentService.findStudentById(es.getStudentId());
						if(student != null){
							examStudentVo = new ExtExamStudentVo();
							examStudentVo.setId(student.getUserId());
							examStudentVo.setName(student.getName());
							examStudentVo.setScore(es.getScore()==-1F?null:es.getScore());
							examStudentVo.setType(es.getTestType());
							data.add(examStudentVo);
						}
					}
				}
			}
		}catch(Exception e){
			log.debug("数据获取异常");
			return new ResponseVo<List<ExtExamStudentVo>>("000001", null);//未知错误
		}
		
		if(data.size() < 1){
			return new ResponseVo<List<ExtExamStudentVo>>("020101", null);//没有数据
		}
		
		return new ResponseVo<List<ExtExamStudentVo>>("0", data);
	}
	
	
	
	@RequestMapping(value = "/data/importByTeamSubject")
	@ResponseBody
	public ResponseError importByTeamSubject(
			@RequestParam(value = "schoolId", required = false) Integer schoolId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termValue", required = false) String termValue,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "examType", required = false) String examType,
			@RequestParam(value = "examRound", required = false, defaultValue="1") Integer examRound,
			@RequestParam(value = "data", required = false) String dataJson
			){
		ResponseInfo info = null;
		boolean schoolIdIsNull = schoolId == null || "".equals(schoolId);
		boolean teamIdIsNull = teamId == null || "".equals(teamId);
		boolean schoolYearIsNull = schoolYear == null || "".equals(schoolYear);
		boolean termValueIsNull = termValue == null || "".equals(termValue);
		boolean subjectCodeIsNull = subjectCode == null || "".equals(subjectCode);
		boolean examTypeIsNull = examType == null || "".equals(examType);
		
		if(schoolIdIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学校参数为空");
			info.setParam("schoolId");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(teamIdIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("班级为空");
			info.setParam("teamId");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(schoolYearIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学年为空");
			info.setParam("schoolYear");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(termValueIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学期为空");
			info.setParam("termValue");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(subjectCodeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("科目为空");
			info.setParam("subjectCode");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(examTypeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("考试类型为空");
			info.setParam("examType");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		//判断是否存在学校
		School school = schoolService.findSchoolById(schoolId);
		if(school==null){
			return new ResponseError("020101", null);
		}
		
		try{
			ExamResult examResult =	initData(schoolId,teamId,subjectCode,examType,examRound,schoolYear,termValue);

			if (examResult.getStatus().equals(PjExamService.OPERATE_ERROR) || examResult.getStatus().equals(PjExamService.OPERATE_FAIL)) {
				return new ResponseError("020101", null);//找不到数据
			}

			//获取初始化后的考试Id
			PjExam exam = pjExamService.findUnique(schoolYear, termValue, teamId, examType, examRound, subjectCode);
			if(exam == null){
				return new ResponseError("020101", null);//找不到数据
			}
			
			//开始获取data中的数据  进行保存操作
			ExamStudent examStudent = null;  //用于存放单个学生的信息
			List<ExamStudent> examStudengList = new ArrayList<ExamStudent>();   //用于存放导入的学生的信息
			
			JSONArray data = JSONArray.fromObject(dataJson);
			if(data != null && data.size() > 0){
				for(int i = 0; i < data.size(); i++){
					
					JSONObject jsonJ = data.getJSONObject(i);
					Integer userId = jsonJ.getInt("id");
					Float score = (float) jsonJ.getDouble("score");
					String testType = jsonJ.getString("type");
					
					//判断数据格式
					if(!checkIsId(userId.toString()) && !checkIsScore(score.toString())){
						return new ResponseError("060113", null);  //参数值内容错误
					}
					
					//不知道为什么转换的时候  01 变成了 1
					if(testType.equals("1") || testType == "1"){
						testType = "01";
					}else if(testType.equals("3") || testType == "3"){
						testType = "03";
					}
					
					//根据userId查找studentId
					Student student = studentService.findOfUser(schoolId, userId);
					
					if(student != null){
						examStudent = new ExamStudent();
						examStudent.setExamId(exam.getId());
						examStudent.setStudentId(student.getId());
						examStudent.setScore(score == null? -1F : score);
						examStudent.setTestType(testType);
						examStudengList.add(examStudent);
					}
					
				}
			}
			
			examStudentService.addExamStudents(examStudengList);
		}catch(Exception e){
			log.debug("导入失败");
			return new ResponseError("060112", null);  //参数值格式错误
		}
		
		return new ResponseError("0", null);//成功标志
	}
	
	/**
	 * 初始化数据表
	 */
	public ExamResult initData(Integer schoolId, Integer teamId, String subjectCode, String examType, Integer examRound, String schoolYear, String termValue){
		//判断参数是否为空
		if(schoolId == null){
			return null;
		}
		if(termValue == null || "".equals(termValue)){
			return null;
		}
		
		//获取学期代码
		SchoolTerm schoolTerm = null;
		SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
		schoolTermCondition.setGbCode(termValue);
		schoolTermCondition.setSchoolId(schoolId);
		schoolTermCondition.setSchoolYear(schoolYear);
		List<SchoolTerm> list = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
		
		if(list.size() > 0){
			schoolTerm = list.get(0);
		}
		
		Team team = teamService.findTeamById(teamId);
		
		if(team != null && schoolTerm != null){
			//初始化数据表
			PjExam pjExam = new PjExam();
			pjExam.setExamRound(examRound);
			pjExam.setExamType(examType);
			pjExam.setGradeId(team.getGradeId());
			pjExam.setIsDelete(false);
			pjExam.setSchoolId(schoolId);
			pjExam.setExamDate(new Date());
			pjExam.setTermValue(termValue);
			pjExam.setSchoolYear(schoolYear);
			pjExam.setTermCode(schoolTerm.getCode());
			pjExam.setTeamId(teamId);
			pjExam.setSubjectCode(subjectCode);
			return this.pjExamService.InitExamData(pjExam);
		}
		return null;
	}
	
	//判断参数是否是分数
	public Boolean checkIsScore(String str){
		try{
			Double.parseDouble(str);
		}catch(Exception e){
			return false;
		}
		return true;
	} 
	
	//判断参数是否是ID
	public Boolean checkIsId(String str){
		try{
			Integer.valueOf(str);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	/**
	 * @function 获取班级单科考试的一些设置和统计信息
	 * @param teamId 考试班级ID
	 * @param schoolYear 考试学年
	 * @param termValue 考试学期
	 * @param subjectCode 考试科目
	 * @param examType 考试类型
	 * @param examRound 考试轮次
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/data/getExamInfo")
	@ResponseBody
	public Object importByTeamSubject(
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termValue", required = false) String termValue,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "examType", required = false) String examType,
			@RequestParam(value = "examRound", required = false) Integer examRound){
		boolean teamIdIsNull = teamId == null;
		boolean schoolYearIsNull = schoolYear == null || "".equals(schoolYear);
		boolean termValueIsNull = termValue == null || "".equals(termValue);
		boolean subjectCodeIsNull = subjectCode == null || "".equals(subjectCode);
		boolean examTypeIsNull = examType == null || "".equals(examType);
		boolean examRoundIsNull = examRound == null || "".equals(examRound);
		ResponseInfo info = null;
		ExtExamInfo examInfo = null;
		String teacherName = null;
		String examDate = null;
		String fullScore = null;
		String lowScore = null;
		String passScore = null;
		Integer studentCount = 0;
		String hightScore = null;
		//返回参数不能为空
		if(teamIdIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("班级为空");
			info.setParam("teamId");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(schoolYearIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学年为空");
			info.setParam("schoolYear");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(termValueIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("学期为空");
			info.setParam("termValue");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(subjectCodeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("科目为空");
			info.setParam("subjectCode");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(examTypeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("考试类型为空");
			info.setParam("examType");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(examRoundIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("考试轮次为空");
			info.setParam("examRound");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		PjExam exam = this.pjExamService.findUnique(schoolYear, termValue, teamId, examType, examRound, subjectCode);
		
		if(exam != null){
			ExamStat examStat = examStatService.findExamStatByExamId(exam.getId());
			if(exam.getTeacherId() != null){
				Teacher teacher = teacherService.findTeacherById(exam.getTeacherId());
				if(teacher != null){
					teacherName = teacher.getName();
				}
			}
			
			if(examStat != null){
				examDate = exam.getExamDate() == null ? examDate : exam.getExamDate().toLocaleString();
				fullScore = examStat.getFullScore() == null ? fullScore : examStat.getFullScore().toString();
				lowScore = examStat.getLowScore() == null ? lowScore : examStat.getLowScore().toString();
				passScore = examStat.getPassScore() == null ? passScore : examStat.getPassScore().toString();
				studentCount = examStat.getStudentCount() == null ? studentCount : examStat.getStudentCount();
				hightScore = examStat.getHighScore() == null ? hightScore : examStat.getHighScore().toString();
				
				examInfo = new ExtExamInfo();
				examInfo.setId(exam.getId());
				examInfo.setExamDate(examDate);
				examInfo.setFullScore(fullScore);
				examInfo.setHightScore(hightScore);
				examInfo.setLowScore(lowScore);
				examInfo.setPassScore(passScore);
				examInfo.setStudentCount(studentCount);
				examInfo.setTeacherName(teacherName);
			}
			
			if(examInfo == null){
				return new ResponseError("020101",null);//根据条件获取得到的数据为空
			}
		}
		return new ResponseVo<ExtExamInfo>("0", examInfo);
	}
	
	@RequestMapping(value = "/data/importScore")
	@ResponseBody
	public ResponseError importScore(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "type", required = false) String testType,
			@RequestParam(value = "score", required = false) Float score,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "termValue", required = false) String termValue,
			@RequestParam(value = "subjectCode", required = false) String subjectCode,
			@RequestParam(value = "examType", required = false) String examType,
			@RequestParam(value = "examRound", required = false, defaultValue="1") Integer examRound,
			@RequestParam(value = "data", required = false) String dataJson
			){
		ResponseInfo info = null;
		boolean userIdIsNull = userId == null;
		boolean testTypeIsNull = testType == null || "".equals(testType);
		boolean schoolYearIsNull = schoolYear == null || "".equals(schoolYear);
		boolean termValueIsNull = termValue == null || "".equals(termValue);
		boolean subjectCodeIsNull = subjectCode == null || "".equals(subjectCode);
		boolean examTypeIsNull = examType == null || "".equals(examType);
		
		if(userIdIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("用户ID为空");
			info.setParam("userId");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(testTypeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("测试类型为空");
			info.setParam("testType");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(subjectCodeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("科目为空");
			info.setParam("subjectCode");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		if(examTypeIsNull){
			info = new ResponseInfo();
			info.setMsg("必填参数为空");
			info.setDetail("考试类型为空");
			info.setParam("examType");
			return new ResponseError("060111", info);//必填参数为空
		}
		
		Student student = studentService.findStudentByUserId(userId);
		
		if(student == null){
			info = new ResponseInfo();
			info.setMsg("学生不存在");
			info.setDetail("学生不存在");
			info.setParam("userId");
			return new ResponseError("020101", info);//必填参数为空
		}
		
		//如果学年为空，默认当前
		if(schoolYearIsNull || termValueIsNull){
			SchoolTermCurrent stc = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(student.getSchoolId());
			if(stc != null){
				schoolYear = stc.getSchoolYear();
				
				SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
				schoolTermCondition.setCode(stc.getSchoolTermCode());
				schoolTermCondition.setIsDelete(false);
				List<SchoolTerm> schoolTermList = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
				if(schoolTermList != null && schoolTermList.size() > 0){
					termValue = schoolTermList.get(0).getCode();
				}
			}
		}
		
		if(schoolYearIsNull){
			info = new ResponseInfo();
			info.setMsg("学年不存在");
			info.setDetail("学年不存在");
			info.setParam("schoolYear");
			return new ResponseError("020101", info);//必填参数为空
		}
		
		if(termValueIsNull){
			info = new ResponseInfo();
			info.setMsg("学期不存在");
			info.setDetail("学期不存在");
			info.setParam("termValue");
			return new ResponseError("020101", info);//必填参数为空
		}
		try{
			//初始化数据表
			ExamResult examResult = initData(student.getSchoolId(),student.getTeamId(),subjectCode,examType,examRound,schoolYear,termValue);
			if(examResult.getStatus().equals(PjExamService.OPERATE_FAIL) || examResult.getStatus().equals(pjExamService.OPERATE_ERROR)){
				return new ResponseError("020101", null);//找不到数据
			}
			
			//获取初始化后的考试Id
			PjExam exam = pjExamService.findUnique(schoolYear, termValue, student.getTeamId(), examType, examRound, subjectCode);
			if(exam == null){
				return new ResponseError("020101", null);//找不到数据
			}
			
			//开始获取data中的数据  进行保存操作
			ExamStudent examStudent = null;  //用于存放单个学生的信息
			List<ExamStudent> examStudengList = new ArrayList<ExamStudent>();   //用于存放导入的学生的信息
			
			//判断数据格式
			if(!checkIsId(userId.toString()) && !checkIsScore(score.toString())){
				return new ResponseError("060113", null);  //参数值内容错误
			}
			
			//不知道为什么转换的时候  01 变成了 1
			if(testType.equals("1") || testType == "1"){
				testType = "01";
			}else if(testType.equals("3") || testType == "3"){
				testType = "03";
			}
			
			if(student != null){
				examStudent = new ExamStudent();
				examStudent.setExamId(exam.getId());
				examStudent.setStudentId(student.getId());
				examStudent.setScore(score == null? -1F : score);
				examStudent.setTestType(testType);
				examStudengList.add(examStudent);
			}
					
			examStudentService.addExamStudents(examStudengList);
		}catch(Exception e){
			log.debug("导入失败");
			return new ResponseError("060112", null);  //参数值格式错误
		}
		
		return new ResponseError("0", null);//成功标志
	}
	
}
