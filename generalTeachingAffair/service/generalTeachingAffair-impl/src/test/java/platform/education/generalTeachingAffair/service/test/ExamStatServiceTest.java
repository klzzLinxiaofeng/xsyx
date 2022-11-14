package platform.education.generalTeachingAffair.service.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import platform.education.generalTeachingAffair.dao.ExamStatDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class ExamStatServiceTest {
	@Autowired
	@Qualifier("examStatDao")
	private ExamStatDao examStatDao;
//	@Autowired
//	@Qualifier("pjExamService")
//	private PjExamService pjExamService;
	@Test
	public void testFindExamTeamSubjectByCondition() {
		Integer param = 2;
		Integer sorce = examStatDao.countHighAndLowAndPass(param, 1);
		System.out.println(sorce);
//		Integer[] args={1170,1171,1172,1173};
//		List<ExamQuestionWrongVo>list=pjExamService.findExamQuestionWrongbyExamId(args, 1, 0.15f);
//		System.out.println(list.size());
	}

}
