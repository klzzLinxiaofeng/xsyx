package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class TeamTeacherServiceTest{
	
	@Autowired
	@Qualifier("teamTeacherService")
	private TeamTeacherService teamTeacherService;
	
	@Test
	public void testAdd() {
		TeamTeacher teamTeacher = new TeamTeacher();
		teamTeacher.setCreateDate(new Date());
		teamTeacher.setFinishDate(new Date());
		teamTeacher.setGradeId(1);
		teamTeacher.setJoinDate(new Date());
		teamTeacher.setModifyDate(new Date());
		teamTeacher.setName("name1");
		teamTeacher.setSchoolYear("09-11");
		teamTeacher.setSubjectCode("1");
		teamTeacher.setSubjectName("subjectName");
		teamTeacher.setTeacherId(1);
		teamTeacher.setTeamId(1);
		teamTeacher.setType(1);
		teamTeacherService.add(teamTeacher);
	}
	
	@Test
	public void testModify() {
		
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {

	}
	
/*	and  pjt.school_id = 1 
			and ptt.grade_id = 1 
			and ptt.team_id = 1 
			and ptt.subject_name = '数学'
			and ptt.`name` = '王康'
			and ptt.subject_code = '14'
			and ptt.teacher_id = 2
			and ptt.school_year = '2015'*/
	
	@Test
	public void testFindByCondition() {
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setSchoolId(1);
		teamTeacherCondition.setGradeId(1);
		teamTeacherCondition.setTeamId(1);
		teamTeacherCondition.setSubjectName("数学");
		teamTeacherCondition.setName("王康");
		
		teamTeacherCondition.setSubjectCode("14");
		teamTeacherCondition.setTeacherId(2);
		//teamTeacherCondition.setSchoolYear("2015");
		
		List<TeamTeacher> teamTeacherList = this.teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
		for (TeamTeacher teamTeacher : teamTeacherList) {
			System.out.println(teamTeacher.getTeacherId()+":"+teamTeacher.getName());
		}
	}
	
	@Test
	public void testFindVo(){
		TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
		teamTeacherCondition.setTeacherId(1);
		teamTeacherCondition.setType(2);
		List<TeamTeacherVo> teamTeacherList = this.teamTeacherService.findTeamTeacherVoByCondition(teamTeacherCondition);
		for(TeamTeacherVo vo : teamTeacherList){
			System.out.println(vo.getTeamName()+":"+vo.getName());
		}
	}
	
}
