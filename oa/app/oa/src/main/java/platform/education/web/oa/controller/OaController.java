package platform.education.web.oa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oa")
public class OaController {
	
	@RequestMapping("/index")
	public void test() {
		System.out.println("index --------------- ");
	}
	
}
