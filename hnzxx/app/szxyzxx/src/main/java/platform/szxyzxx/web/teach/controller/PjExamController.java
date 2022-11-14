package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Controller
@RequestMapping("/teach/pjExam")
public class PjExamController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/exam";
	/**
	 * 功能描述：跳转到成绩录入管理页面
	 * 2015-01-05
	 * @return
	 */
	@RequestMapping(value = "/entryManagement")
	public ModelAndView entryManagement() {
		return new ModelAndView(structurePath("/scoreEntryManagement"));
	}
	
	/**
	 * 功能描述：用于数据的展示    查询的数据
	 * 2015-01-06
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping(value = "/searchByCondition")
	public ModelAndView searchByCondition(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "searchType", required = false) String searchType,
			@ModelAttribute("condition") PjExamCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) throws IllegalAccessException, InvocationTargetException {
		String viewPath = null;
		List<PjExam> items = null;
		List<PjExamVo> voList = new ArrayList<PjExamVo>();
		SchoolTerm schoolTerm = null;
		String termValue = null;
		Subject subject = null;
		//获取学期的国标码
		schoolTerm = getSchoolTermByCode(condition.getTermCode());
		if(schoolTerm != null){
			termValue = schoolTerm.getGbCode();
		}
		if ("subject".equals(searchType)) {
			items = this.pjExamService.findExamsBySubject(condition.getSchoolYear(), termValue, condition.getTeamId(), condition.getSubjectCode());
			viewPath = structurePath("/showScoreListByType");
		} else {
			items = this.pjExamService.findExamsByType(condition.getSchoolYear(), termValue, condition.getTeamId(), condition.getExamType(), condition.getExamRound());
			viewPath = structurePath("/showScoreListBySubject");
		}
		
		//获取科目及应试人数等信息
		if(items.size() > 0){
			for(PjExam pjExam : items){
				//获取应试人数
				Integer studentCount = 0;
				ExamStat examStat = examStatService.findExamStatByExamId(pjExam.getId());
				if(examStat != null && examStat.getStudentCount() != null){
					studentCount = examStat.getStudentCount();
				}
				//获取科目
				subject = this.subjectService.findUnique(user.getSchoolId(), pjExam.getSubjectCode());
				PjExamVo vo = new PjExamVo();
				BeanUtils.copyProperties(vo, pjExam);
				vo.setSubjectName(subject.getName());
				vo.setStudentCount(studentCount);
				voList.add(vo);
			}
		}
		
		model.addAttribute("items", voList);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * 功能描述：跳转到成绩录入页面
	 * 2015-01-05
	 * @return
	 */
	@RequestMapping(value = "/toInputScorePage",method = RequestMethod.GET)
	public ModelAndView toInputScorePage(Model model,@CurrentUser UserInfo user,
			@ModelAttribute("condition") PjExamCondition condition
			) {
		
		//根据年级获取科目信息
		if(condition.getGradeId() != null && !"".equals(condition.getGradeId())){
			Grade grade = gradeService.findGradeById(condition.getGradeId());
			if(grade != null){
				SubjectGradeCondition subjectGradeCondition = new SubjectGradeCondition();
				subjectGradeCondition.setGradeCode(grade.getUniGradeCode());
				subjectGradeCondition.setSchoolId(user.getSchoolId());
				subjectGradeCondition.setDelete(false);
				List<SubjectGrade> list = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
				
				//一下代码的作用，获取科目名称，科目名称冗余数据可能有错
				List<Subject> subjectList = subjectService.findSubjectsOfSchool(grade.getSchoolId());
				Map<String,Subject> map = new HashMap<String,Subject>();
				String subjectName = "";
				if(list != null && list.size() > 0){
					for(Subject sub : subjectList){
						map.put(sub.getCode(), sub);
					}
				}
				if(list != null && list.size() > 0){
					for(SubjectGrade sg : list){
						if(sg != null && sg.getSubjectCode() != null && !"".equals(sg.getSubjectCode())){
							subjectName = map.get(sg.getSubjectCode())==null?"":map.get(sg.getSubjectCode()).getName();
							sg.setSubjectName(subjectName);
						}
					}
				}
				model.addAttribute("subjectList", list);
			}
		}
		
		//根据termCode获取学期
		if(condition.getTermCode() != null && !"".equals(condition.getTermCode()) && condition.getSchoolYear() != null && !"".equals(condition.getSchoolYear())){
			SchoolTermCondition schoolTermCondition =  new SchoolTermCondition();
			schoolTermCondition.setSchoolYear(condition.getSchoolYear());
			schoolTermCondition.setCode(condition.getTermCode());
			List<SchoolTermVo> list = this.schoolTermService.findSchoolTermByConditionMore(schoolTermCondition, null, null);
			model.addAttribute("termList", list);
			model.addAttribute("termCode", condition.getTermCode());
		}
		
		model.addAttribute("condition", condition);
		return new ModelAndView(structurePath("/addStudentScore"));
	}
	
	/**
	 * 功能描述：用于查询某个年级下的某个班级下的学生成绩
	 * 2015-01-07
	 * @return
	 */
	@RequestMapping(value = "/searchStudentScoreByCondition")
	public ModelAndView searchStudentScoreByCondition(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@ModelAttribute("condition") PjExamCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		String termValue = null;
		PjExam pjExam = null;
		List<ExamStudent> items = null;
		viewPath = structurePath("/studentScoreList");
		
		//获取学期的国标码
		SchoolTerm schoolTerm = getSchoolTermByCode(condition.getTermCode());
		if(schoolTerm != null){
			termValue = schoolTerm.getGbCode();
		}
		
		//查询数据
		pjExam = this.pjExamService.findUnique(condition.getSchoolYear(), termValue, condition.getTeamId(), condition.getExamType(), condition.getExamRound(), condition.getSubjectCode());
		if(pjExam != null){
			model.addAttribute("examId", pjExam.getId());
			items = examStudentService.findExamStudentsByExamId(pjExam.getId());
			if(items.size() > 0){
				for(ExamStudent es : items){
					if(es.getScore() == -1F){
						es.setScore(null);
					}
				}
			}
		}
		
		//获取统计信息  用于校验满分
		ExamStat examStat = null;
		if(pjExam != null){
			examStat = examStatService.findExamStatByExamId(pjExam.getId());
		}
		
		model.addAttribute("examStat", examStat);
		model.addAttribute("items", items);
		return new ModelAndView(viewPath, model.asMap());
	}
	
	/**
	 * 功能描述：初始化数据
	 * 2015-01-07
	 * @return
	 */
	@RequestMapping(value = "/initData")
	@ResponseBody
	public String initData(@ModelAttribute("condition") PjExam pjExam,@CurrentUser UserInfo user){
		String termValue = null;
		//获取学期的国标码
		SchoolTerm schoolTerm = getSchoolTermByCode(pjExam.getTermCode());
		if(schoolTerm != null){
			termValue = schoolTerm.getGbCode();
		}
		
		pjExam.setIsDelete(false);
		pjExam.setSchoolId(user.getSchoolId());
		pjExam.setExamDate(new Date());
		pjExam.setTermValue(termValue);
		return this.pjExamService.InitExamData(pjExam).getStatus();
	}
	
	/**
	 * 功能描述：清空学生成绩数据
	 * 2015-01-07
	 * @return
	 */
	@RequestMapping(value = "/clearExamScore")
	@ResponseBody
	public String clearExamScore(@RequestParam(value = "examId", required = true) Integer examId){
		String mess = "";
		try{
			examStudentService.clearExamScore(examId);
			mess = "success";
		}catch(Exception e){
			mess = "error";
		}
		return mess;
	}
	
	/**
	 * 功能描述：删除学生成绩数据
	 * 2015-01-07
	 * @return
	 */
	@RequestMapping(value = "/deletedExamScore")
	@ResponseBody
	public String deletedExamScore(@RequestParam(value = "examId", required = true) Integer examId){
		String mess = "";
		try{
			PjExam pjExam = pjExamService.findPjExamById(examId);
			if(pjExam != null){
				pjExamService.abandon(pjExam);
				mess = "success";
			}
		}catch(Exception e){
			mess = "error";
		}
		return mess;
	}
	
	/**
	 * 功能描述：保存学生成绩数据
	 * 2015-01-07
	 * @return
	 */
	@RequestMapping(value = "/saveExamScore", method = RequestMethod.POST)
	@ResponseBody
	public String saveExamScore(
			@RequestParam("examStudent") String examStudentList,
			@RequestParam("examId") Integer examId){
		String mess = "";
		try{
			JSONArray examStudentJsonArray = JSONArray.fromObject(examStudentList);
			Integer studentCount = 0;
			ExamStudent examStudent = null;
			List<ExamStudent> examStudengList = new ArrayList<ExamStudent>();
			
			if(examStudentJsonArray.size() > 0){
				for(int i=0;i<examStudentJsonArray.size(); i++){
					examStudent = new ExamStudent();
					
					JSONObject jsonJ = examStudentJsonArray.getJSONObject(i);
					
					Integer studentId = jsonJ.getInt("studentId");
					Float score = (float) jsonJ.getDouble("score");
					String testType = jsonJ.getString("testType");
					
					//不知道为什么转换的时候  01 变成了 1
					if(testType.equals("1") || testType == "1"){
						testType = "01";
					}else if(testType.equals("3") || testType == "3"){
						testType = "03";
					}
					
					examStudent.setExamId(examId);
					examStudent.setStudentId(studentId);
					examStudent.setScore(score);
					examStudent.setTestType(testType);
					
					examStudengList.add(examStudent);
					
					if(testType != "03"){
						studentCount++;
					}
				}
			}
			
			ExamStat examStat = examStatService.findExamStatByExamId(examId);
			examStat.setStudentCount(studentCount);
			examStatService.modify(examStat);
			
			examStudentService.addExamStudents(examStudengList);
			mess = "success";
		}catch(Exception e){
			mess = "error";
		}
		return mess;
	}
	
	/**
	 * 功能描述：根据年级获取科目信息
	 * 2015-01-07
	 * @return
	 */
	@RequestMapping(value = "/getSubjectByGradeId", method = RequestMethod.POST)
	@ResponseBody
	public List<SubjectGrade> getSubjectByGradeId(@RequestParam("gradeId") Integer gradeId){
		List<SubjectGrade> subjectList = null;
		if(gradeId != null && !"".equals(gradeId)){
			Grade grade = gradeService.findGradeById(gradeId);
			if(grade != null){
				SubjectGradeCondition subjectGradeCondition = new SubjectGradeCondition();
				subjectGradeCondition.setGradeCode(grade.getUniGradeCode());
				subjectGradeCondition.setSchoolId(grade.getSchoolId());
				subjectList = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
				
				//一下代码的作用，获取科目名称，科目名称冗余数据可能有错
				List<Subject> list = subjectService.findSubjectsOfSchool(grade.getSchoolId());
				Map<String,Subject> map = new HashMap<String,Subject>();
				String subjectName = "";
				if(list != null && list.size() > 0){
					for(Subject sub : list){
						map.put(sub.getCode(), sub);
					}
				}
				if(subjectList != null && subjectList.size() > 0){
					for(SubjectGrade sg : subjectList){
						if(sg != null && sg.getSubjectCode() != null && !"".equals(sg.getSubjectCode())){
							subjectName = map.get(sg.getSubjectCode())==null?"":map.get(sg.getSubjectCode()).getName();
							sg.setSubjectName(subjectName);
						}
					}
				}
			}
		}
		return subjectList;
	}
	
	//获取学期的国标码等信息
	public SchoolTerm getSchoolTermByCode(String code){
		SchoolTerm schoolTerm = null;
		SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
		schoolTermCondition.setCode(code);
		schoolTermCondition.setIsDelete(false);
		List<SchoolTerm> schoolTermList = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
		if(schoolTermList.size() > 0){
			schoolTerm = schoolTermList.get(0);
		}
		return schoolTerm;
	}
	
	//根据条件获取唯一考试记录
	public PjExam getExam(PjExamCondition condition){
		PjExam exam = null;
		List<PjExam> list = pjExamService.findPjExamByCondition(condition);
		if(list.size() > 0){
			exam = list.get(0);
		}
		return exam;
	}
	
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	
	//根据考试ID获取非缺考人数  即应试人数
	public Integer getStudentCountByEaxmId(Integer examId){
		int studentCount = 0;
		List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(examId);
		if(examStudentList.size() > 0){
			for(ExamStudent examStudent : examStudentList){
				if(examStudent.getTestType() != "03"){
					studentCount = studentCount + 1;
				}
			}
		}
		return studentCount;
	}
	
	/**
	 * 导出学生成绩模板
	 * @param user
	 * @param teamId  必传参数    用于获取班级下的学生
	 * @param schoolYear  可不传   用于文件命名
	 * @param termCode    可不传   用于文件命名
	 * @return success OR error
	 */
	@RequestMapping(value = "/downLoadFile")
	@ResponseBody
	public ResponseInfomation downLoadParent(@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false)String schoolYear,
			@RequestParam(value = "termCode", required = false)String termCode,
			@RequestParam(value = "teamId", required = true)Integer teamId,
			HttpServletResponse response,
			HttpServletRequest request){
		
		ResponseInfomation tesponseInfomation = null;
		List<Student> studentList = null;
		SchoolTermVo schoolTermVo = null;
		String fileName = "";
		List<Object> list = new ArrayList<Object>();
		
		if(teamId != null && !"".equals(teamId)){
			//根据termCode查找学年学期
			SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
			schoolTermCondition.setSchoolId(user.getSchoolId());
			schoolTermCondition.setSchoolYear(schoolYear);
			schoolTermCondition.setCode(termCode);
			List<SchoolTermVo> schoolTermList = this.schoolTermService.findSchoolTermByConditionMore(schoolTermCondition, null, null);
			
			if(schoolTermList.size() > 0){
				schoolTermVo = schoolTermList.get(0);
			}
			
			//根据teamId 查找班级
			Team team = this.teamService.findTeamById(teamId);
			
			//拼接excel表名  如: 2015-2016学年 春季学期 七年级（1）班 学生成绩导入模板.xls
			if(schoolYear != null && !"".equals(schoolYear) && schoolTermVo != null && schoolTermVo.getSchoolYearName() != null && schoolTermVo.getName() != null){
				fileName += schoolTermVo.getSchoolYearName()+schoolTermVo.getName();
			}
			if(team != null){
				fileName += team.getName();
			}
			fileName += "学生成绩导入模板.xls";
			
			//根据teamId查找学生
			studentList = studentService.findStudentByTeamId(teamId);
			
			ParseConfig config = SzxyExcelTookit.getConfig("examStudent");
			try {
				for (Student student : studentList) {
					StudentVo vo = new StudentVo();
					vo.setUserName(student.getUserName());
					vo.setName(student.getName());
					vo.setScore(null);
					list.add(vo);
				}
				SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
			} catch (UnsupportedEncodingException e) {
				tesponseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
				e.printStackTrace();
			}
		}else{
			tesponseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_ERROR);
		}
		
		tesponseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
		return tesponseInfomation;
	}
	
	/**
	 * @function 用户点击修改成绩时  进入到修改页面  并将考试的一些信息进行回显
	 * @param model
	 * @param examId
	 * @return
	 */
	@RequestMapping(value = "/modifyExamScore", method = RequestMethod.GET)
	public ModelAndView toModifyExamStudentPage(
			Model model,
			@RequestParam(value = "examId", required = true)Integer examId
			){
		String viewPath = structurePath("/addStudentScore");
		List<ExamStudent> items = null;
		
		//考试信息
		PjExam exam = pjExamService.findPjExamById(examId);
		
		//查询数据
		items = examStudentService.findExamStudentsByExamId(examId);
		if(items.size() > 0){
			for(ExamStudent es : items){
				if(es.getScore() == -1F){
					es.setScore(null);
				}
			}
		}
		
		//根据年级获取科目信息
		if(exam.getGradeId() != null && !"".equals(exam.getGradeId())){
			Grade grade = gradeService.findGradeById(exam.getGradeId());
			if(grade != null){
				SubjectGradeCondition subjectGradeCondition = new SubjectGradeCondition();
				subjectGradeCondition.setGradeCode(grade.getUniGradeCode());
				List<SubjectGrade> list = this.subjectGradeService.findSubjectGradeByCondition(subjectGradeCondition, null, null);
				model.addAttribute("subjectList", list);
			}
		}
		
		//根据termCode获取学期
		if(exam.getTermCode() != null && !"".equals(exam.getTermCode()) && exam.getSchoolYear() != null && !"".equals(exam.getSchoolYear())){
			SchoolTermCondition schoolTermCondition =  new SchoolTermCondition();
			schoolTermCondition.setSchoolYear(exam.getSchoolYear());
			schoolTermCondition.setCode(exam.getTermCode());
			List<SchoolTermVo> list = this.schoolTermService.findSchoolTermByConditionMore(schoolTermCondition, null, null);
			model.addAttribute("termList", list);
			model.addAttribute("termCode", exam.getTermCode());
		}
		
		//获取统计信息  用于校验满分
		ExamStat examStat = examStatService.findExamStatByExamId(examId);
		
		model.addAttribute("items", items);
		model.addAttribute("condition", exam);
		model.addAttribute("isModify", "isModify");
		model.addAttribute("examId", exam.getId());
		model.addAttribute("examStat", examStat);
		return new  ModelAndView(viewPath, model.asMap());
	}
	
}
