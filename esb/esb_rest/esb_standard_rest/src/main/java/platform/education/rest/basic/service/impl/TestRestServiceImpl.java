package platform.education.rest.basic.service.impl;

import platform.education.rest.basic.service.TestRestService;

public class TestRestServiceImpl implements TestRestService {

	@Override
	public Object getTest(String name) {
		
		
		System.out.println(name);
		
		
		return name;
	}

}
