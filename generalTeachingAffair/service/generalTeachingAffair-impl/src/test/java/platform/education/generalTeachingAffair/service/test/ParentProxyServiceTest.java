package platform.education.generalTeachingAffair.service.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.service.ParentProxyService;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.StudentMergeParentVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class ParentProxyServiceTest {
	
	@Autowired
	@Qualifier("parentProxyService")
	private ParentProxyService parentProxyService;
	
	
	@Test
	public void testFindParentByGroupCondition() {
		ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
		//parentStudentCondition.setSchoolId(1);
		/*parentStudentCondition.setGradeId(1);
		parentStudentCondition.setTeamId(1);
		parentStudentCondition.setName("龙");
		parentStudentCondition.setUserName("12117");*/
		
//		Page page = new Page();
//		page.setPageSize(10);
//		List<Parent> parentList = this.parentProxyService.findParentByGroupCondition(parentStudentCondition,page);
//		System.out.println(parentList.size());
//		for (Parent parent : parentList) {
//			System.out.println(parent.getUserId()+"=="+parent.getUserName());
//		}
	}
	
	@Test
	public void testFindParentByCondition() {
		ParentVo parentvo = new ParentVo();
		parentvo.setSchoolYear("2015");
		parentvo.setMobile("122");
		Page page = new Page();
		page.setPageSize(10);
		List<StudentMergeParentVo> studentMergeParentVoListEnd = null;
		try {
			studentMergeParentVoListEnd = this.parentProxyService.findParentByStudent(parentvo, page, null);
			for (StudentMergeParentVo studentMergeParentVo : studentMergeParentVoListEnd) {
				System.out.println(studentMergeParentVo.getStudentName());
				List<ParentVo> list = studentMergeParentVo.getParentVoList();
				for (ParentVo parentVo2 : list) {
					System.out.println(parentVo2.getName()+"=="+parentVo2.getMobile());
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
