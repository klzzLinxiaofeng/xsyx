package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import platform.education.generalTeachingAffair.model.SubjectTeacherTmp;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.SubjectTeacherTmpCondition;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class StudentInitServiceTest {

	@Autowired
	@Qualifier("schoolInitService")
	private SchoolInitService schoolInitService;
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	@Autowired
	@Qualifier("subjectTeacherService")
	private SubjectTeacherService subjectTeacherService;
	@Autowired
	@Qualifier("syllabusService")
	private SyllabusService syllabusService;
	@Autowired
	@Qualifier("teamUserService")
	private TeamUserService teamUserService;
	
	@Test
	public void test() {
		TeamUser teamUser = new TeamUser();
		teamUser.setId(2440);
		teamUser.setIsDeleted(true);
		teamUserService.modify(teamUser);
	}


	@Test
	public void testAddSubjectTeacherTmpBatch() {
		ArrayList<SubjectTeacherTmp> array = new ArrayList<SubjectTeacherTmp>();
		for (int i = 0; i < 10; i++) {
			SubjectTeacherTmp tmp = new SubjectTeacherTmp();
			tmp.setAlias("abc");
			tmp.setCode("123456");
			tmp.setErrorFiled("errorFiled");
			tmp.setErrorInfo("info");
			tmp.setGradeId(1);
			tmp.setGradeName("一年级");
			tmp.setPhone("12345678998");
			tmp.setStatus(0);
			tmp.setSubjectName("科目一");
			tmp.setSubjectTeacherId(1);
			tmp.setTeacherName("wang mr");
			tmp.setTeamId(1);
			tmp.setTeamNumber("1");
			array.add(tmp);
		}
		schoolInitService.addSubjectTeacherTmpBatch(array.toArray());
	}
	
	@Test
	public void testRemoveSubjectTeacherTmpByCode() {
		schoolInitService.removeSubjectTeacherTmpByCode("123456");
	}
	
	@Test
	public void testFindSubjectTeacherByCodeAndStatus() {
		List<SubjectTeacherTmp> tmpList = schoolInitService.findSubjectTeacherByCodeAndStatus("123456", 1, null, null);
		for (SubjectTeacherTmp subjectTeacherTmp : tmpList) {
			System.err.println("subjectTeacherTmpId: " + subjectTeacherTmp.getId());
		}
	}
	
	@Test
	public void testDeleteSubjectTeacherTmpBySubjectTeacherId() {
		schoolInitService.deleteSubjectTeacherTmpBySubjectTeacherId(1);
	}
	
	
	@Test
	public void testFindSubjectTeacherTmpById() {
		SubjectTeacherTmp SubjectTeacherTmp = schoolInitService.findSubjectTeacherTmpById(22);
		System.err.println(SubjectTeacherTmp.getGradeName());
	}
	
	@Test
	public void countSubjectTeacherTmpByCodeAndStatus() {
		System.err.println(schoolInitService.countSubjectTeacherTmpByCodeAndStatus("123456", 0));
	}
	
	@Test
	public void modifySubjectTeacherTmp() {
		SubjectTeacherTmp SubjectTeacherTmp = schoolInitService.findSubjectTeacherTmpById(42);
		SubjectTeacherTmp.setAlias("1111111");
		schoolInitService.modifySubjectTeacherTmp(SubjectTeacherTmp, 0);
	}
	
	@Test
	public void findSubjectTeacherTmpByCondition() {
		SubjectTeacherTmpCondition conditon = new SubjectTeacherTmpCondition();
		conditon.setGradeId(1);
		List<SubjectTeacherTmp> tmplist = schoolInitService.findSubjectTeacherTmpByCondition(conditon, null, null);
		System.out.println(tmplist.size());
	}
	
	@Test
	public void testDeleteStudent() {
//		studentService.removeAllStudentInfoById(29, "4");
	}
	
	@Test
	public void testDeleteTeacher() {
		teacherService.deleteTeacherAllInfoById(460, "1");
	}
	
}
