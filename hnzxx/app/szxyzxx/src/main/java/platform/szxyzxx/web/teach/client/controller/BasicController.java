package platform.szxyzxx.web.teach.client.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.teach.client.vo.ExtGradeVo;
import platform.szxyzxx.web.teach.client.vo.ExtSchoolTermVo;
import platform.szxyzxx.web.teach.client.vo.ExtSchoolYearVo;
import platform.szxyzxx.web.teach.client.vo.ExtSubjectVo;
import platform.szxyzxx.web.teach.client.vo.ExtTeamVo;
import platform.szxyzxx.web.teach.client.vo.ResponseError;
import platform.szxyzxx.web.teach.client.vo.ResponseInfo;
import platform.szxyzxx.web.teach.client.vo.ResponseVo;

/**
 * API接口模块
 * 数校--基础业务类
 * @date 2016-01-12
 * @author xiemeijie
 *
 */

@Controller
@RequestMapping("/school/basic")
public class BasicController extends BaseController{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 功能描述：返回学校所有的学年和学期记录
	 * 2016-01-12
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/term/listAll")
	@ResponseBody
	public Object listAll(@RequestParam(value = "schoolId", required = false) Integer schoolId) {
		
		List<SchoolYear> yearList = new ArrayList<SchoolYear>();
		List<SchoolTerm> termList = new ArrayList<SchoolTerm>();
		List<ExtSchoolYearVo> years = new ArrayList<ExtSchoolYearVo>();
		List<ExtSchoolTermVo> terms = new ArrayList<ExtSchoolTermVo>();
		
		try{
			
			if(schoolId == null || "".equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}
			
			//获得该学校下的当前学年值、当前学期值
			String syCurrent = "";   //当前学年值  schoolYear
			String tcCurrent = "";   //当前学期值   teamCode
			SchoolTermCurrent termCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(termCurrent != null) {
				syCurrent = termCurrent.getSchoolYear();   
				tcCurrent = termCurrent.getSchoolTermCode();  
			}
			
			yearList = this.schoolYearService.findSchoolYearOfSchool(schoolId);
			for(SchoolYear year : yearList) {
				//获取该学年下的所有学期
				SchoolTermCondition condition = new SchoolTermCondition();
				condition.setSchoolId(schoolId);
				condition.setSchoolYear(year.getYear());
				condition.setIsDelete(false);
				termList = this.schoolTermService.findSchoolTermByCondition(condition, null, null);
				for(SchoolTerm term : termList) {
					//判断该学期是否是当前学期
					Boolean isCurrentTerm = false;
					if(!"".equals(tcCurrent)) {
						if(tcCurrent.equals(term.getCode())) {
							isCurrentTerm = true;
						}
					}
					ExtSchoolTermVo termVo = new ExtSchoolTermVo();
					termVo.setCode(term.getCode());
					termVo.setName(term.getName());
					termVo.setValue(term.getGbCode());
					termVo.setIsCurrent(isCurrentTerm);
					terms.add(termVo);
				}
				
				//判断该学年是否是当前学年
				Boolean isCurrentYear = false;
				if(!"".equals(syCurrent)) {
					if(syCurrent.equals(year.getYear())) {
						isCurrentYear = true;
					}
				}
				
				ExtSchoolYearVo yearVo = new ExtSchoolYearVo();
				yearVo.setSchoolYear(year.getYear());
				yearVo.setName(year.getName());
				yearVo.setIsCurrent(isCurrentYear);
				yearVo.setTerms(terms);
				years.add(yearVo);
			}
			
			return new ResponseVo<List<ExtSchoolYearVo>>("0", years);
			
		}catch(Exception e){
			log.info("通过学校ID获取学校学年学期列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过学校ID获取学校学年学期列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * 功能描述：获取学校当前学年的年级
	 * 2016-01-12
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/grade/list")
	@ResponseBody
	public Object getGradeList(@RequestParam(value = "schoolId", required = false) Integer schoolId) {
		
		List<Grade> gradeList = new ArrayList<Grade>();
		List<ExtGradeVo> grades = new ArrayList<ExtGradeVo>();
		
		try{
			
			if(schoolId == null || "".equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}
			
			SchoolTermCurrent termCurrent = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
			if(termCurrent != null) {
				String schoolYear = termCurrent.getSchoolYear();
				gradeList = this.gradeService.findGradeBySchoolYear(schoolId, schoolYear);
				for(Grade grade : gradeList) {
					ExtGradeVo gradeVo  = new ExtGradeVo();
					gradeVo.setId(grade.getId());
					gradeVo.setName(grade.getName());
					grades.add(gradeVo);
				}
			}
			
			return new ResponseVo<List<ExtGradeVo>>("0", grades);
			
		}catch(Exception e){
			log.info("通过学校ID获取学校当前学年的年级列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过学校ID获取学校当前学年的年级列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}

	/**
	 * 功能描述：获取学校所有学年下的年级
	 * 2017-08-24
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/grade/list/all")
	@ResponseBody
	public Object getAllGradeList(@RequestParam(value = "schoolId", required = false) Integer schoolId) {

		List<SchoolYear> yearList = new ArrayList<SchoolYear>();
		List<Grade> gradeList = new ArrayList<Grade>();
		List<ExtSchoolYearVo> years = new ArrayList<ExtSchoolYearVo>();
		List<ExtGradeVo> grades = null;

		try{

			if(schoolId == null || "".equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}

			yearList = this.schoolYearService.findSchoolYearOfSchool(schoolId);
			if(yearList != null) {
				for(SchoolYear year : yearList) {
					gradeList = this.gradeService.findGradeBySchoolYear(schoolId, year.getYear());
					grades = new ArrayList<ExtGradeVo>();
					if(gradeList != null) {
						for(Grade grade : gradeList) {
							ExtGradeVo gradeVo  = new ExtGradeVo();
							gradeVo.setId(grade.getId());
							gradeVo.setName(grade.getName());
							gradeVo.setSchoolYear(grade.getSchoolYear());
							gradeVo.setCode(grade.getCode());
							grades.add(gradeVo);
						}
					}

					ExtSchoolYearVo yearVo = new ExtSchoolYearVo();
					yearVo.setSchoolYear(year.getYear());
					yearVo.setName(year.getName());
					yearVo.setGrades(grades);
					years.add(yearVo);
				}
			}

			return new ResponseVo<List<ExtSchoolYearVo>>("0", years);

		}catch(Exception e){
			log.info("通过学校ID获取学校所有学年下的年级列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过学校ID获取学校所有学年下的年级列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * 功能描述：获取年级的班级
	 * 2016-01-12
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/team/listByGrade")
	@ResponseBody
	public Object getTeamList(@RequestParam(value = "gradeId", required = false) Integer gradeId) {
		
		List<Team> teamList = new ArrayList<Team>();
		List<ExtTeamVo> teams = new ArrayList<ExtTeamVo>();
		
		try{
			
			if(gradeId == null || "".equals(gradeId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("gradeId");
				return new ResponseError("060111", info);
			}
			
			teamList = this.teamService.findTeamOfGrade(gradeId);
			if(teamList != null){
				for (Team team : teamList) {
					ExtTeamVo teamVo = new ExtTeamVo();
					teamVo.setId(team.getId());
					teamVo.setName(team.getName());
					teams.add(teamVo);
				}
			}
			
			return new ResponseVo<List<ExtTeamVo>>("0", teams);
			
		}catch(Exception e){
			e.printStackTrace();
			log.info("通过年级ID获取年级的班级列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过年级ID获取年级的班级列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * 功能描述：获取学校当前在用的所有科目（不分年级）
	 * 2016-01-13
	 * @param schoolId
	 * @return
	 */
	@RequestMapping("/subject/list")
	@ResponseBody
	public Object getSubjectList(@RequestParam(value = "schoolId", required = false) Integer schoolId) {
		
		List<Subject> subjectList = new ArrayList<Subject>();
		List<ExtSubjectVo> subjects = new ArrayList<ExtSubjectVo>();
		
		try{
			
			if(schoolId == null || "".equals(schoolId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("schoolId");
				return new ResponseError("060111", info);
			}
			
			subjectList = this.subjectService.findSubjectsOfSchool(schoolId);
			for(Subject subject : subjectList) {
				ExtSubjectVo subjectVo = new ExtSubjectVo();
				subjectVo.setName(subject.getName());
				subjectVo.setCode(subject.getCode());
				subjects.add(subjectVo);
			}
			
			return new ResponseVo<List<ExtSubjectVo>>("0", subjects);
			
		}catch(Exception e){
			log.info("通过学校ID获取学校当前在用的所有科目（不分年级）列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过学校ID获取学校当前在用的所有科目（不分年级）列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * 功能描述：获取一个班级所有的课程（科目）
	 * 2016-01-13
	 * @param teamId
	 * @return
	 */
	@RequestMapping("/subject/getByTeam")
	@ResponseBody
	public Object getSubjectsByTeam(@RequestParam(value = "teamId", required = false) Integer teamId) {
		
		List<SubjectGrade> subjectGradeList = new ArrayList<SubjectGrade>();
		List<ExtSubjectVo> subjects = new ArrayList<ExtSubjectVo>();
		
		try{
			
			if(teamId == null || "".equals(teamId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			Team team = this.teamService.findTeamById(teamId);
			if(team != null) {
				if(team.getGradeId() != null) {
					Grade grade = this.gradeService.findGradeById(team.getGradeId());
					if(grade != null) {
						subjectGradeList = this.subjectGradeService.findSubjectGradeByGradeCode(grade.getSchoolId(), grade.getUniGradeCode());
					}
				}
			}
			
			if(subjectGradeList != null && subjectGradeList.size() > 0){
				for(SubjectGrade subjectGrade : subjectGradeList) {
					ExtSubjectVo subjectVo = new ExtSubjectVo();
					subjectVo.setName(subjectGrade.getSubjectName());
					subjectVo.setCode(subjectGrade.getSubjectCode());
					subjects.add(subjectVo);
				}
			}
			
			return new ResponseVo<List<ExtSubjectVo>>("0", subjects);
			
		}catch(Exception e){
			log.info("通过班级ID获取一个班级所有的课程（科目）列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过班级ID获取一个班级所有的课程（科目）列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
	/**
	 * 功能描述：获取一个年级所有的课程（科目）
	 * 2016-02-19
	 * @param gradeId
	 * @return
	 */
	@RequestMapping("/subject/getByGrade")
	@ResponseBody
	public Object getSubjectsByGrade(@RequestParam(value = "gradeId", required = false) Integer gradeId) {
		
		List<ExtSubjectVo> subjects = new ArrayList<ExtSubjectVo>();
		ExtSubjectVo subjectVo = null;
		try{
			
			if(gradeId == null || "".equals(gradeId)) {
				ResponseInfo info = new ResponseInfo();
				info.setDetail("参数不能为空...");
				info.setMsg("必填参数为空");
				info.setParam("teamId");
				return new ResponseError("060111", info);
			}
			
			//获取年级信息
			Grade grade = gradeService.findGradeById(gradeId);
			if(grade != null){
				List<SubjectGrade> subjectGradeList = subjectGradeService.findSubjectGradeByGradeCode(grade.getSchoolId(), grade.getUniGradeCode());
				if(subjectGradeList != null && subjectGradeList.size() > 0){
					for(SubjectGrade subjectGrade : subjectGradeList){
						Subject subject = subjectService.findUnique(grade.getSchoolId(), subjectGrade.getSubjectCode());
						subjectVo = new ExtSubjectVo();
						subjectVo.setCode(subjectGrade.getSubjectCode());
						subjectVo.setName(subject == null ? "未知科目" : subject.getName());
						subjects.add(subjectVo);
					}
				}
			}
			
			return new ResponseVo<List<ExtSubjectVo>>("0", subjects);
			
		}catch(Exception e){
			log.info("通过年级ID获取所有的课程（科目）列表异常");
			ResponseInfo info = new ResponseInfo();
			info.setDetail("通过年级ID获取所有的课程（科目）列表异常...");
			info.setMsg("未知错误");
			return new ResponseError("000001", info);
		}
	}
	
}
