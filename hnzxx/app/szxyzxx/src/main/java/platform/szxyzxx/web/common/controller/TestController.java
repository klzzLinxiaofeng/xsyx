package platform.szxyzxx.web.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import platform.education.user.contants.GroupContants;
import platform.education.user.contants.UserContants;
import platform.education.user.service.UserService;
import platform.education.user.vo.UserVo;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import framework.generic.facility.poi.excel.config.ParseConfig;
import framework.generic.facility.poi.excel.main.ExcelTookit;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	
//	@Qualifier("jwKbService")
//	private JwKbService jwKbService;
	
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	@RequestMapping("/test")
	@SuppressWarnings("rawtypes")
	public void test() {
		ParseConfig config = SzxyExcelTookit.getConfig("test");
//		config.setIgnoreRowKeys(new String[] { "006", "007" });
		// config.setDataStartRow(3);
		// config.setDataEndRow(6);
		// config.setTitleAndDataStartCol(1);
		// config.setTitleAndDataEndCol(7);
		Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
		Map<Object, Object> xxjbxxMaps = new HashMap<Object, Object>();
		xxjbxxMaps.put("男", "001");
		xxjbxxMaps.put("女", "001");
		codeWithValueMaps.put("xb", xxjbxxMaps);
		config.setCodeWithValueMaps(codeWithValueMaps);
		List models = SzxyExcelTookit.excelDataToModels(config, "/Users/clouds/Documents/test.xls");
		System.out.println(models);
//		for (Object model : models) {
//			Test xl = (Test) model;
//			System.out.println(xl);
//			System.out.println(xl.getXlDm());
//		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("/test/import")
	public void testImport(@RequestParam("file") MultipartFile file, String jsessionId, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		InputStream is;
		try {
			is = file.getInputStream();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			ParseConfig config = SzxyExcelTookit.getConfig("test");
//			config.setIgnoreRowKeys(new String[] { "006", "007" });
			// config.setDataStartRow(3);
			// config.setDataEndRow(6);
			// config.setTitleAndDataStartCol(1);
			// config.setTitleAndDataEndCol(7);
			Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
			Map<Object, Object> xxjbxxMaps = new HashMap<Object, Object>();
			xxjbxxMaps.put("男", "001");
			xxjbxxMaps.put("女", "001");
			codeWithValueMaps.put("xb", xxjbxxMaps);
			config.setCodeWithValueMaps(codeWithValueMaps);
			List models = SzxyExcelTookit.excelDataToModels(config, is, suffix);
			System.out.println(models);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping("test1")
	public void test1() {
//		ParseConfig config = SzxyExcelTookit.getConfig("dept");
//		List<Object> dataset = new ArrayList<Object>();
//		DeptModel model = new DeptModel();
//		model.setId("panweiliang");
//		model.setName("潘维良");
//		model.setAge(20);
//		model.setSex(true);
//		model.setBrithDay(new Date());
//
//		DeptModel model1 = new DeptModel();
//		model1.setId("lixiaowen");
//		model1.setName("李晓雯");
//		model1.setAge(21);
//		model1.setSex(false);
//		model1.setBrithDay(new Date());
//
//		dataset.add(model);
//		dataset.add(model1);
//		ExcelTookit.exportExcel(dataset, config, "/Users/clouds/Documents/test.xls");
		ParseConfig config = SzxyExcelTookit.getConfig("test");
		Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
		Map<Object, Object> xxjbxxMaps = new HashMap<Object, Object>();
		xxjbxxMaps.put("1", "男");
		xxjbxxMaps.put("2", "女");
		codeWithValueMaps.put("xb", xxjbxxMaps);
		config.setCodeWithValueMaps(codeWithValueMaps);
		
		List<Object> dataset = new ArrayList<Object>();
//		
//		Test test1 = new Test();
//		test1.setId(1);
//		test1.setCode("1");
//		test1.setName("潘维良");
//		test1.setBirthDate(new Date());
//		
//		Test test2 = new Test();
//		test2.setId(2);
//		test2.setCode("2");
//		test2.setName("李晓雯");
//		test2.setBirthDate(new Date());
//		dataset.add(test1);
//		dataset.add(test2);
		ExcelTookit.exportExcel(dataset, config, "/Users/clouds/Documents/test1.xls");
	}

	@RequestMapping("test2")
	public void test2() {
		ParseConfig config = SzxyExcelTookit.getConfig();
		List<Object> maps = new ArrayList<Object>();
		String[] titles = { "编号", "名称", "年龄", "生日日期" };
		String[] fieldNames = { "id", "name", "age", "brith" };
		config.setTitles(titles);
		config.setFieldNames(fieldNames);
		config.setSheetTitle("学生信息");
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("id", "panweiliang");
		map1.put("name", "潘维良");
		map1.put("age", 1111111111);
		map1.put("brith", new Date());
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("id", "lixiaowen");
		map2.put("name", "李晓雯");
		Double age1 = 1234567.334343434311111111111111111d;
		map2.put("age", age1);
		map2.put("brith", new Date());
		maps.add(map1);
		maps.add(map2);
		SzxyExcelTookit.exportExcel(maps, config, "/Users/clouds/Documents/test3.xls");

	}
	
	@RequestMapping("/test3")
	public void test3() {
//		jwKbService.getKbDataFromExcel("", "001");
		UserVo vo = new UserVo();
		vo.setGroupOwnerId(222);
		vo.setGroupType(GroupContants.TYPE_SCHOOL);
		vo.setIsDeleted(false);
		vo.setPassword("999999");
		vo.setState(UserContants.STATE_NORMAL);
		vo.setUserName("PANWEILIANG");
		vo.setValidDate(new Date());
		try {
			this.userService.addUser(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		User use = this.userService.getUser(310);
	}
	
	@RequestMapping("/test4")
	public void test4() {
		Subject current = SecurityUtils.getSubject();
		current.hasRole("super_admin");
	}
	
	@RequestMapping("/test5")
	public void test5() {
//		ParseConfig config = SzxyExcelTookit.getConfig("test");
//		Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
//		Map<Object, Object> xxjbxxMaps = new HashMap<Object, Object>();
//		xxjbxxMaps.put("男", "001");
//		xxjbxxMaps.put("女", "001");
//		codeWithValueMaps.put("xb", xxjbxxMaps);
//		config.setCodeWithValueMaps(codeWithValueMaps);
//		List<Object> models = SzxyExcelTookit.excelDataToModels(config, "/Users/clouds/Documents/parent.xls");
//		List<ParseFeedbackInfo> errorInfos = config.getFeedbackInfos();
//		for (Object vo : models) {
//			if(vo instanceof ParentVo) {
//				ParentVo pv = (ParentVo) vo;
//				System.out.println(pv);
//			}
//		}
//		
//		for(ParseFeedbackInfo errorInfo : errorInfos) {
//			System.out.println(errorInfo);
//		}
		
	}

}
