package platform.education.generalTeachingAffair.service.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.TeamStudent;
import platform.education.generalTeachingAffair.service.TeamStudentService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class TeamStudentServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("teamStudentService")
	private TeamStudentService teamStudentService;
	
	@Test
	public void testAdd() {
		TeamStudent teamStudent = new TeamStudent();
		teamStudent.setCreateDate(new Date());
		teamStudent.setFinishDate(new Date());
		teamStudent.setGradeId(1);
		teamStudent.setJoinDate(new Date());
		teamStudent.setModifyDate(new Date());
		teamStudent.setName("name1");
		teamStudent.setNumber(1);
		teamStudent.setPosition("p1");
		teamStudent.setRecordDate(new Date());
		teamStudent.setStudentId(1);
		teamStudent.setTeamId(1);
		teamStudentService.add(teamStudent);
		
	}
	
	@Test
	public void testModify() {
		TeamStudent teamStudent = teamStudentService.findTeamStudentById(1);
		teamStudent.setCreateDate(new Date());
		teamStudent.setFinishDate(new Date());
		teamStudent.setGradeId(2);
		teamStudent.setJoinDate(new Date());
		teamStudent.setModifyDate(new Date());
		teamStudent.setName("name2");
		teamStudent.setNumber(2);
		teamStudent.setPosition("p2");
		teamStudent.setRecordDate(new Date());
		teamStudent.setStudentId(1);
		teamStudent.setTeamId(1);
		teamStudentService.modify(teamStudent);
	}
	
	@Test
	public void testFindById() {

	}
	
	@Test
	public void testRemove() {
		TeamStudent teamStudent = teamStudentService.findTeamStudentById(2);
		teamStudentService.remove(teamStudent);
	}
	
	@Test
	public void testFindByCondition() {

	}
	
}
