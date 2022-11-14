package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import platform.education.generalTeachingAffair.service.ExamStudentService;
import platform.education.generalTeachingAffair.service.ExamTeamSubjectService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.vo.CommonScoreRank;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectVo;

import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class CopyOfExamTeamSubjectServiceTest {
	@Autowired
	@Qualifier("studentService")
	private StudentService studentService;
	
	@Autowired
	@Qualifier("examTeamSubjectService")
	private ExamTeamSubjectService examTeamSubjectService;
	
	@Autowired
	@Qualifier("examStudentService")
	protected ExamStudentService examStudentService;
	
	@Test
	public void testFindExamTeamSubjectByCondition() {
		ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
		examTeamSubjectCondition.setSchoolId(1);
		examTeamSubjectCondition.setTerm("1-2015-2");
		examTeamSubjectCondition.setSchoolYear("2015");
		List<ExamTeamSubjectVo> list = examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService,examTeamSubjectCondition);
		for (ExamTeamSubjectVo examTeamSubject : list) {
			System.out.println(examTeamSubject.getExamName());
		}
	}
	
	@Test
	public void testScoreRate() {
		CommonScoreRank csr = this.examStudentService.countScoreRate(39, 100f, 90f, 75f, 60f);
	}
	
	
}
