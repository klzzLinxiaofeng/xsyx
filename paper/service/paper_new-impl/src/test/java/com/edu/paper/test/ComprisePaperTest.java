package com.edu.paper.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaQuestion;
import platform.education.paper.service.PaPaperService;
import platform.education.paper.service.PaQuestionService;
import platform.education.paper.vo.PaQuestionVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class ComprisePaperTest {

	@Autowired
	private PaPaperService paPaperNewService;
	
	@Autowired
	private PaQuestionService paQuestionService;
	
	@Test
	public void test() {
		System.out.println("paPaperNewService的地址值："+paPaperNewService);
	}
	
	
	@Test
	public void testJson(){
		String[] uuidList = {"91f0c348-decb-4753-9869-e55dc70b0967","bd09ab02-f08c-4c5a-a361-686d523ebbe9"};
//		List<PaQuestionVo> PaQuestionList = paQuestionService.findPaQuestionByUUIDs(uuidList, 32);
		/*System.out.println("地址："+ PaQuestionList);
		PaQuestion pq = PaQuestionList.get(0);
		System.out.println(pq.getAnswer());
		System.out.println(pq.getContent());*/
		
		List<String> jsonDataFromPaQuestionByUUIDs = paQuestionService.getJsonDataFromPaQuestionByUUIDs(uuidList, 32);
		System.out.println(jsonDataFromPaQuestionByUUIDs.get(0));
		System.out.println(jsonDataFromPaQuestionByUUIDs.get(1));
	}
	
	@Test
	public void testPqJson(){
		String paQuestionById = paQuestionService.getJsonDataFromPaQuestionById(906);
		System.out.println(paQuestionById);
	}
	
	@Test
	public void testZuJuanShiJuan(){
		List<PaPaper> paperIds= new ArrayList<>();
		List<Integer> questionIds= new ArrayList<>();
		PaPaper pp = paPaperNewService.findPaPaperById(35);
		PaPaper pp1 = paPaperNewService.findPaPaperById(36);
		paperIds.add(pp);
		paperIds.add(pp1);
		questionIds.add(906);
		questionIds.add(907);
		paPaperNewService.consturctNewPaper(paperIds, questionIds);
	}
	
	@Test
	public void testZu(){
		List<PaQuestionVo> list = paQuestionService.findPaQuestionListbyPaPaperId(35);
		/*PaQuestionVo pq1 = list.get(0);
		System.out.println(pq1.getContent());
		System.out.println(pq1.getScore());*/
		/*PaQuestionVo vo = paQuestionService.findPaQuestionById(35,1144);
		System.out.println(vo.getScore());
		System.out.println(vo.getContent());*/
		System.out.println("长度"+list.size());
		list = paQuestionService.addQuestionToPaQuestionList(list,35,1144);
		PaQuestionVo vo = list.get(list.size()-1);
		System.out.println(vo.getScore());
		System.out.println(vo.getContent());
		System.out.println("长度"+list.size());
	}
	
}
