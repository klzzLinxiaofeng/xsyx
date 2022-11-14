//package platform.education.generalTeachingAffair.service.test;
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import platform.education.generalTeachingAffair.dao.ExamTeamSubjectDao;
//import platform.education.generalTeachingAffair.model.ExamTeamSubject;
//import platform.education.generalTeachingAffair.vo.ExamTeamSubjectCondition;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
//public class ExamTeamSubjectServiceTest {
//	@Autowired
//	@Qualifier("examTeamSubjectDao")
//	private ExamTeamSubjectDao examTeamSubjectDao;
//	@Test
//	public void testFindExamTeamSubjectByCondition() {
//		ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
//		examTeamSubjectCondition.setSchoolId(1);
//		examTeamSubjectCondition.setTerm("1-2015-2");
//		examTeamSubjectCondition.setSchoolYear("2015");
//		examTeamSubjectCondition.setGradeId(2);
//		List<ExamTeamSubject> list = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition,null,null);
//		System.out.println("============");
//		for (ExamTeamSubject examTeamSubject : list) {
//			System.out.println(examTeamSubject.getExamName());
//		}
//	}
//
//}
