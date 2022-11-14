package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.service.PersonService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;

public class JwPersonServiceTest extends BaseTest {

	@Autowired
	@Qualifier("jwPersonService")
	private PersonService jwPersonService;

	@Test
	public void testAdd() {

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

	@Test
	public void testFindByCondition() {

	}

}
