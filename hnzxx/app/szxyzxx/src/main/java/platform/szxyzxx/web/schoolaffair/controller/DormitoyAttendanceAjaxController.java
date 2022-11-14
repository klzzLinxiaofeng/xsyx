package platform.szxyzxx.web.schoolaffair.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.school.affair.model.Dormitory;
import platform.education.school.affair.model.DormitoryPerson;
import platform.education.school.affair.model.Floor;
import platform.education.school.affair.vo.DormitoryPersonCondition;
import platform.education.school.affair.vo.DormitoryPersonVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;


@Controller
@RequestMapping("/schoolaffair/dormitoryAttendance/ajax")
public class DormitoyAttendanceAjaxController extends BaseController {

	
	
	/**
	 * 异步获取班级列表
	 * @param user
	 * @param gradeId
	 * @return
	 */
	@RequestMapping(value = "/getTeamList", method = RequestMethod.POST)
	@ResponseBody
	public List <Team>getTeamList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = true) String gradeId
			
			){
		// 获取该学校当前学年
		SchoolTermCurrent stc = this.schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		//根据学校id、当前学年、所属年级在宿舍人员表中查找对应的班级列表
				DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
				dpCondition.setSchoolId(user.getSchoolId());
				dpCondition.setSchoolYearId(stc.getSchoolYearId());
				dpCondition.setGradeId(Integer.parseInt(gradeId));
				List<DormitoryPerson>dpList = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition,null,null);
				List<Team>teamList = new ArrayList<Team>();
				if(dpList.size()>0){
					for(DormitoryPerson person : dpList){
						Team teams = new Team();
						Team team = this.teamService.findTeamById(person.getTeamNumber());
						teams.setId(team.getId());
						teams.setGradeId(team.getGradeId());
						teams.setName(team.getName());
						teamList.add(teams);
					}
				}
		
		return teamList;
	}
	
	
	/**
	 * 异步获取学生列表
	 * @param user
	 * @param gradeId
	 * @param teamNumber
	 * @return
	 */
	@RequestMapping(value = "/getStudentList", method = RequestMethod.POST)
	@ResponseBody
	public List <Student>getStudentList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "teamNumber", required = true) String teamNumber
			
			){
		// 获取该学校当前学年
		SchoolTermCurrent stc = this.schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		//根据学校id、当前学年、所属年级、所在班级在宿舍人员表中查找对应的学生列表
				DormitoryPersonCondition dpCondition = new DormitoryPersonCondition();
				dpCondition.setSchoolId(user.getSchoolId());
				dpCondition.setSchoolYearId(stc.getSchoolYearId());
				dpCondition.setGradeId(Integer.parseInt(gradeId));
				dpCondition.setTeamNumber(Integer.parseInt(teamNumber));
				List<DormitoryPerson>dpList = this.dormitoryPersonService.findDormitoryPersonByCondition(dpCondition,null,null);
				List<Student>studentList = new ArrayList<Student>();
				if(dpList.size()>0){
					for(DormitoryPerson person : dpList){
						Student students = new Student();
						Student student = this.studentService.findStudentById(person.getStudentId());
						students.setId(student.getId());
						students.setTeamId(student.getTeamId());
						students.setName(student.getName());
						studentList.add(students);
					}
				}
		
		return studentList;
	}
	
	
	/**
	 * 异步获取宿舍楼号和宿舍号 
	 * @param user
	 * @param gradeId
	 * @param teamNumber
	 * @param studentId
	 * @return
	 */
	@RequestMapping(value = "/getFloorAndDormitory", method = RequestMethod.POST)
	@ResponseBody
	public DormitoryPersonVo getFloorAndDormitory(
			@CurrentUser UserInfo user,
			@RequestParam(value = "gradeId", required = true) String gradeId,
			@RequestParam(value = "teamNumber", required = true) String teamNumber,
			@RequestParam(value = "studentId", required = true) String studentId
			
			){
		// 获取该学校当前学年
		SchoolTermCurrent stc = this.schoolTermCurrentService
				.findSchoolTermCurrentBySchoolId(user.getSchoolId());
		//根据学校id、当前学年、所属年级、所在班级、学生id在宿舍人员表中查找对应的宿舍楼号 
		DormitoryPerson person = new DormitoryPerson();
		person.setSchoolId(user.getSchoolId());
		person.setSchoolYearId(stc.getSchoolYearId());
		person.setGradeId(Integer.parseInt(gradeId));
		person.setTeamNumber(Integer.parseInt(teamNumber));
		person.setStudentId(Integer.parseInt(studentId));
		DormitoryPersonVo personsVo = this.dormitoryPersonService.findUnique(person);
		if(personsVo!=null){
			Dormitory d = this.dormitoryService.findDormitoryById(personsVo.getDormitoryId());
			    personsVo.setDormitoryCode(d.getDormitoryCode());
			if(d!=null){
				Floor f = this.floorService.findFloorByCode(user.getSchoolId(), d.getFloorCode());
				personsVo.setFloorName(f==null?"":f.getName());
				
			}
		}
		
		
		return personsVo;
	}
	
}
