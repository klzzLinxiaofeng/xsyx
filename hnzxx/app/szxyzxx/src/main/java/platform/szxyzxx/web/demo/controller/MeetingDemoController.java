package platform.szxyzxx.web.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.poi.util.SystemOutLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/d/meeting")
public class MeetingDemoController {

	private final static String viewBasePath = "/demo/meeting";

	private static final Logger log = LoggerFactory.getLogger(MeetingDemoController.class);

	@Resource
	private HttpClient meetingClientHttpClient;

	public void setMeetingClientHttpClient(HttpClient meetingClientHttpClient) {
		this.meetingClientHttpClient = meetingClientHttpClient;
	}

	@RequestMapping("/index")
	public String index() {
		return structurePath("/index");
	}

	@RequestMapping("/user/add")
	public String addUser() {
		PostMethod postMethod = new PostMethod("http://www.uyin.in/appws/app/cmpuser/adduser");
		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("requestId", "1");
		parameterMap.put("acceptType", "1");
		parameterMap.put("opname", "3249493001");
		parameterMap.put("passwd", "123456");
		parameterMap.put("username", "admin");
		parameterMap.put("mobile", "18688025344");
		parameterMap.put("email", "492943101@qq.com");
		parameterMap.put("roleId", "1");
		parameterMap.put("timezoneID", "46");
		parameterMap.put("languageID", "1");
		parameterMap.put("password", "123456");
		postMethod.setRequestHeader("Content-Type","application/json;charset=utf-8");
		// 构造键值对参数
		if (parameterMap.size() > 0) {
			NameValuePair[] params = new NameValuePair[parameterMap.size()];
			int i = 0;
			String paramName;
			String value;
			for (Iterator<String> it = parameterMap.keySet().iterator(); it
					.hasNext();) {
				paramName = it.next();
				value = parameterMap.get(paramName);
				params[i] = new NameValuePair(paramName, value);
				i++;
			}
			postMethod.setRequestBody(params);


		}
		try {
			// 执行
			meetingClientHttpClient.executeMethod(postMethod);
			System.out.println(postMethod.getResponseBodyAsString());
			return "";
		} catch (HttpException e) {
			log.error("Do request to ucenter server error:" + e.getMessage());
		} catch (IOException e) {
			log.error("Do request to ucenter server error:" + e.getMessage());
		} finally {
			postMethod.releaseConnection();
		}
		return "";

	}

	@RequestMapping("/open")
	public String open() {
		return structurePath("/login_jiaoxueyun");
	}

	@RequestMapping("/creator")
	public String creator() {
		return structurePath("/input");
	}

	@RequestMapping("/editor")
	public String editor() {
		return structurePath("/input");
	}


	@RequestMapping("/test/tm")
	public void test(@RequestParam("timeNum") Long num) {
		System.out.println("timeout start ===========================================================================");
		try {
			Thread.sleep(num == null ? 10l : num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("timeout end ===========================================================================");
	}



	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
}
