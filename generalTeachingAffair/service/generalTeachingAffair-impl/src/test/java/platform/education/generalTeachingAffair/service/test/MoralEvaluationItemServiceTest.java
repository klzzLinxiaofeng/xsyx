package platform.education.generalTeachingAffair.service.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.MoralEvaluationItem;
import platform.education.generalTeachingAffair.service.MoralEvaluationItemService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.MoralEvaluationItemCondition;

public class MoralEvaluationItemServiceTest extends BaseTest{
	
	@Autowired
	@Qualifier("moralEvaluationItemService")
	private MoralEvaluationItemService moralEvaluationItemService;
	
	@Test
	public void testAdd() {
		MoralEvaluationItem item = new MoralEvaluationItem();
		item.setName("心理健康");
		item.setClassification("心理方向");
		item.setSchoolId(10);
		item.setRemark("很好");
		item.setIsDelete(false);
		item.setCreateDate(new Date());
		item.setModifyDate(new Date());
		moralEvaluationItemService.add(item);
	}
	
	@Test
	public void testModify() {
		
	}
	
	@Test
	public void testFindById() {
		MoralEvaluationItem item = this.moralEvaluationItemService.findMoralEvaluationItemById(2);
		System.out.println("******" + item.getName());
	}
	
	@Test
	public void testRemove() {

	}
	
	@Test
	public void testFindByCondition() {
		MoralEvaluationItemCondition condition = new MoralEvaluationItemCondition();
		condition.setSchoolId(10);
		condition.setIsDelete(false);
		List<MoralEvaluationItem> items = moralEvaluationItemService.findMoralEvaluationItemByCondition(condition);
		System.out.println("**************:" + items.size());
	}
	
}
