/**   
* @Title: PaperAnswerTest.java
* @Package com.pantq.test 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月24日 上午10:54:22 
* @version V1.0   
*/
package com.pantq.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import platform.education.paper.model.ResponseEntity;
import platform.education.paper.service.UserQuestionService;

/** 
* @ClassName: PaperAnswerTest 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author pantq
* @date 2017年2月24日 上午10:54:22 
*  
*/

@RunWith(SpringJUnit4ClassRunner.class) // = extends SpringJUnit4ClassRunner
@ContextConfiguration(locations = { "classpath:paper_app.xml" })
public class SpitPaperTest {
	@Resource
	private UserQuestionService userQuestionService ;
	
		public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
			List<ResponseEntity> list = new ArrayList<ResponseEntity> ();
			ResponseEntity responseEntity = new ResponseEntity();
			responseEntity.setFileMd5(UUID.randomUUID().toString());
			responseEntity.setFileUuid(UUID.randomUUID().toString());
			responseEntity.setFileUrl("http://cdn.test.studyo.cn/develop/test/2017-1-11/21ec41f6c50e98cd1bff47b3120e73d7.png");
			responseEntity.setIsExist(true);
			list.add(responseEntity);
			
			ResponseEntity responseEntity1 = new ResponseEntity();
			
			responseEntity1.setIsExist(false);
			
			list.add(responseEntity1);
			ObjectMapper mapper = new ObjectMapper();
			
			System.out.println(mapper.writeValueAsString(list));
		}

}
