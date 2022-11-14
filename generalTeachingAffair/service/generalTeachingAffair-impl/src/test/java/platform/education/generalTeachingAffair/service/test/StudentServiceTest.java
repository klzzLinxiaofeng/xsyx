package platform.education.generalTeachingAffair.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.vo.ParentCondition;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeamCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class StudentServiceTest {

	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;

	/*
	 * @Test public void testAdd() { Team team = new Team();
	 * team.setBeginDate(new Date()); team.setCreateDate(new Date());
	 * team.setSchoolId(1); team.setGradeId(2); team.setMemberCount(1);
	 * team.setModifyDate(new Date()); team.setName("name");
	 * team.setSchoolId(1); team.setTeamNumber(23);
	 * 
	 * StudentService.add(team); }
	 * 
	 * @Test public void testModify() { Team team =
	 * StudentService.findTeamById(1); team.setBeginDate(new Date());
	 * team.setCreateDate(new Date()); team.setSchoolId(11);
	 * team.setGradeId(21); team.setMemberCount(11); team.setModifyDate(new
	 * Date()); team.setName("name1"); team.setSchoolId(1);
	 * team.setTeamNumber(231); StudentService.modify(team); }
	 */

	@Test
	public void testFindById() {
		// List<Team> ist = StudentService.find
		// System.out.println("======ist====="+ist.size());
	}

	@Test
	public void testRemove() {

	}

	@Test
	public void testFindByCondition() {
		// List<Team> ll = StudentService.getTeamOfGrade(2);
		// System.out.println(ll.size()+"==Team==");
	}

	@Test
	public void testGetTeamByTeacher() {
		// List<Team> ll = StudentService.getTeamByTeacher(1, "09-11");
		// System.out.println("===="+ll.size());
	}

	@Test
	public void testGetCurrentTeamByTeacher() {
		// List<Team> aa = StudentService.getCurrentTeamByTeacher(1);
		// System.out.println("===aa=="+aa.size());
	}

	@Test
	public void testGetStudentOfTeam() {
		TeamCondition teamCondition = new TeamCondition();
		teamCondition.setGradeId(60);
		// teamCondition.setSchoolYear("2003");
		// List<Team> teamList =
		// StudentService.findTeamByCondition(teamCondition, null, null);
		StudentCondition studentCondition = new StudentCondition();
		studentCondition.setTeamId(69);
		studentCondition.setSchoolYear("2013");
		// List<Student> studentList =
		// studentService.findStudentByCondition(studentCondition, null, null);
		// List<Student> studentList = studentService.getStudentOfTeam(69);
		System.out.println("===========1===============");

		// System.out.println(studentList.size());
		System.out.println("==========2================");

	}
	
	/*and pt.school_id = 1 
			and pt.grade_id =1 
			AND pt.school_year = '2015' 
			and pt.id = 2
			and pstu.id = 13
and pstu.user_id=31
					and ppa.mobile = '12121154564'*/
	@Test
	public void testFindStudentByParent(){
		ParentCondition parentCondition = new ParentCondition();
		parentCondition.setSchoolId(1);
		parentCondition.setGradeId(1);
		parentCondition.setTeamId(2);
		parentCondition.setStudentId(13);
		parentCondition.setStudentUserId(31);
		parentCondition.setSchoolYear("2015");
		parentCondition.setParentMobile("1212115456");
		parentCondition.setParentMobileLike(true);
		Page page = new Page();
		page.setPageSize(10);
		List<Student> studentList = this.studentService.findStudentByParent(parentCondition, page, null);
		for (Student student : studentList) {
			System.out.println(student.getUserId()+":"+student.getName());
		}
	}
	
	@Test
	public void testFindStudentByTeam(){
		StudentCondition studentCondition = new StudentCondition();
		studentCondition.setSchoolId(1);
		studentCondition.setGradeId(1);
		studentCondition.setTeamId(1);
		studentCondition.setSchoolYear("2015");
		studentCondition.setId(16);
		Page page = new Page();
		page.setPageSize(10);
		List<Student> studentList = this.studentService.findStudentByTeam(studentCondition, page, null);
		for (Student student : studentList) {
			System.out.println(student.getUserId()+":"+student.getName());
		}
	}
	@Test
	public void testFindStudentByOnlyCondition(){
		Page page = new Page();
		page.setPageSize(10);
		StudentCondition studentCondition = new StudentCondition();
		studentCondition.setNameLike(false);
		studentCondition.setName("瑞文");
		///177508 瑞文
		studentCondition.setUserNameLike(true);
		studentCondition.setUserName("17750");
		List<Student> studentList = this.studentService.findStudentByOnlyCondition(studentCondition, page, null);
		for (Student student : studentList) {
			System.out.println(student.getUserId()+":"+student.getUserName()+":"+student.getName());
		}
		
		//assert(false);
	}

}
