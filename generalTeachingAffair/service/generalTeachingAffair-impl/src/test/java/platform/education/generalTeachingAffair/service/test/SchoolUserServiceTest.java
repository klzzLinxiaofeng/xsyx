package platform.education.generalTeachingAffair.service.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.service.SchoolUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:generalTeachingAffair-test.xml")
public class SchoolUserServiceTest {

	@Autowired
	@Qualifier("schoolUserService")
	private SchoolUserService schoolUserService;

	@Test
	public void testAdd() {
		SchoolUser schoolUser = new SchoolUser();
		schoolUser.setCreateDate(new Date());
		schoolUser.setModifyDate(new Date());
		schoolUser.setName("name");
		schoolUser.setOwnerId(1);
		schoolUser.setSchoolId(1);
		schoolUser.setUserId(1);
		schoolUser.setUserType("1");
		// schoolUserService.add(schoolUser);

	}

	@Test
	public void testModify() {
		SchoolUser schoolUser = schoolUserService.findSchoolUserById(1);
		schoolUser.setCreateDate(new Date());
		schoolUser.setModifyDate(new Date());
		schoolUser.setName("name1");
		schoolUser.setOwnerId(2);
		schoolUser.setSchoolId(2);
		schoolUser.setUserId(2);
		schoolUser.setUserType("2");
		schoolUserService.modify(schoolUser);
	}

	@Test
	public void testFindById() {

	}

	@Test
	public void testRemove() {
		SchoolUser schoolUser = schoolUserService.findSchoolUserById(1);
		schoolUserService.remove(schoolUser);
	}

	@Test
	public void testFindByCondition() {

	}

}
