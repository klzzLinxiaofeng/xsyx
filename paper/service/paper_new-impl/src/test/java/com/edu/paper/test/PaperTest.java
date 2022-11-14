package com.edu.paper.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import platform.education.paper.service.PaPaperService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class PaperTest {

	@Autowired
	private PaPaperService paPaperNewService;
	
	@Test
	public void test() {
		System.out.println(paPaperNewService);
	}
	
}
