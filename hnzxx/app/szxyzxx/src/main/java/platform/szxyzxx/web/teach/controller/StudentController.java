package platform.szxyzxx.web.teach.controller;

import cn.hutool.core.util.StrUtil;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.contants.StudentContants;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpEntityType;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestConfig;
import platform.education.generalTeachingAffair.utils.httpclient.core.HttpRequestResult;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.oa.utils.UtilDate;
import platform.education.user.contants.GroupContants;
import platform.education.user.contants.SysDefRole;
import platform.education.user.model.*;
import platform.education.user.service.AppReleaseService;
import platform.education.user.vo.AppReleaseCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.util.Column;
import platform.szxyzxx.util.ExcelTool;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.contants.StudentArchiveContants;
import platform.szxyzxx.web.teach.util.PropertiesUtil;
import platform.szxyzxx.web.teach.util.RegLevelUtil;
import platform.szxyzxx.web.teach.vo.ErroMessageVo;
import platform.szxyzxx.web.teach.vo.StudentVo;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/***
 * 学生管理
 * @author zhoujin
 *
 */
@RestController
@RequestMapping("/teach/student")
public class StudentController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	/**
	 * 食堂接口
	 */
	private static String fileName;
	private static String address;
	private static String canteen;
	/**
	 * 闸机接口
	 */
	private static String HikvisionHost;
	private static String HikvisionAppKey;
	private static String HikvisionAppSecret;
	private static String HikvisionAddPersonUrl;
	/**
	 * 图书馆接口
	 */
	private static String libraryACnteen;
	private static String libraryLogin;
	private static String libraryCreate;

	static {
		fileName = "System.properties";
		// 食堂
		address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
		canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");
		//闸机
		HikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
		HikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
		HikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
		HikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");

		// 图书馆
		libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
		libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
		libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");
	}

	
	@Resource
	private AppReleaseService appReleaseService;

	@Autowired
	@Qualifier("httpService")
	private HttpService httpService;

	@Autowired
	private BasicSQLService basicSQLService;

	/**
	 * 学生列表
	 * @param sub
	 * @param dm
	 * @param user
	 * @param studentCondition
	 * @param schoolYearCondition
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/studentList")
	public ModelAndView getStudentList(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			@ModelAttribute("schoolYearCondition") SchoolYearCondition schoolYearCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			studentCondition.setSchoolId(user.getSchoolId());
			List<Student> studentList = null;
			List<StudentVo> list = new ArrayList<StudentVo>();
			Long manNum = null;
			Long womanNum = null;
			Long totalNum = null;
			Boolean interrupteur = true;
			if(studentCondition != null){
				if(studentCondition.getTeamId() != null){
					interrupteur = jobControlService.studentArchiveCanEdit(studentCondition.getTeamId());
					
					studentCondition.setSex("1");
					manNum = studentService.count(studentCondition);
					studentCondition.setSex("2");
					womanNum = studentService.count(studentCondition);
					studentCondition.setSex(null);
					totalNum = studentService.count(studentCondition);
				}
			}
			
			if("list".equals(sub)){
				viewPath="/teach/student/list";
				studentList = studentService.findStudentByCondition(studentCondition, page, Order.desc("create_date"));
				for(Student student:studentList){
					try {
						HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/list?emp_code="+student.getStudentNumber())
								.addHeader("Content-Type", "application/json");
						HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
						String responseText = httpRequestResult.getResponseText();
						if (!StrUtil.hasEmpty(responseText)) {
							com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(responseText);
							List<Map<String, Object>> list2 = (List<Map<String, Object>>) jsonObject.get("data");
							if(list2.size()==1){
								student.setShiTangCard(list2.get(0).get("emp_card").toString());
							}else{
								log.error("请求食堂接口,url: {}","未获取到数据");
							}
						}
					} catch (Exception e) {
						log.error("请求食堂接口失败,url: {}","获取卡号");
					}
					StudentVo vo = new StudentVo();
					BeanUtils.copyProperties(student, vo);
					Person person = this.personService.findPersonById(student.getPersonId());
					if (person != null) {
						vo.setUuid(person.getPhotoUuid());
					}
					list.add(vo);
				}
			}else{
				viewPath="/teach/student/studentList";
			}
			mav.addObject("manNum", manNum);
			mav.addObject("womanNum", womanNum);
			mav.addObject("totalNum", totalNum);
			mav.addObject("studentList", list);
			mav.addObject("interrupteur",interrupteur);
			//mav.addObject("schoolYearList",schoolYearList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询学生分页列表异常...");
			e.printStackTrace();
		}
		return mav;
	}
	/*
	* 跳转到下载模板界面
	*/
	@RequestMapping(value = "/XiaZaiView")
	public ModelAndView XiaZaiView(){
		String bathUrl="/teach/student/xiazai";
		ModelAndView modelAndView=new ModelAndView(bathUrl);
		return modelAndView;
	}

	@RequestMapping(value = "/DaoRuView")
	public ModelAndView DaoRuView(){
		String bathUrl="/teach/student/daoru";
		ModelAndView modelAndView=new ModelAndView(bathUrl);
		return modelAndView;
	}


	/*
	 * 导出学生修改模板
	 **/
	@RequestMapping("/downloadStudentXiuGai")
	public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
										  @RequestParam(value = "gradeId",required = false) Integer gradeId,
										  @RequestParam(value = "teamId",required = false) Integer teamId,
										  HttpServletResponse response,
										  HttpServletRequest request){
		//表格头部组装
		List<Map<String,String>> titleList=new ArrayList<>();
		FengZhuan(titleList);
		//数据组装ExamList
		String sql="select pg.school_year as schoolYear,pg.id as gradeId,pg.name as gradeName, ps.id as stuId,ps.name as stuName from pj_student ps inner join pj_team pt on pt.id=ps.team_id " +
				" inner join pj_grade pg on pg.id=pt.grade_id where  ps.is_delete=0  and ps.study_state='01' and  pg.school_year='"+schoolYear+"'";
		if(gradeId!=null && !gradeId.equals("")){
			sql+=" and pg.id="+gradeId;
		}if(teamId!=null && !teamId.equals("")){
			sql+=" and pt.id="+teamId;
		}
		sql+=" group by ps.id  order by ps.id";
		List<Map<String, Object>> map = basicSQLService.find(sql);
		String fileName1="学生修改模板.xls";
		ExcelTool excelTool = new ExcelTool(fileName1,15,20);
		List<Column>  titleData=excelTool.columnTransformer(titleList);
		try {
			InputStream inputStream = excelTool.exportExcel(titleData, map, true, false);
			response.setCharacterEncoding("UTF-8");
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName1.getBytes() : fileName1.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
			String name = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
			response.setContentType("octets/stream");
			response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", name));
			ServletOutputStream outputStream = response.getOutputStream();
			//写文件
			int b;
			while((b=inputStream.read())!= -1) {
				outputStream.write(b);
			}
			inputStream.close();
			outputStream.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return "success";
	}
	/*
	 * 导入学生修改模板
	 *
	 */
	@RequestMapping("/upLoadStudentXiuGai")
	public Map<String, Object> updateLoadSubjectScore(@CurrentUser UserInfo userInfo,
												   @RequestParam("fileUpload") MultipartFile fileUpload){
		try {
			String fileName = fileUpload.getOriginalFilename();//获取文件名
			InputStream is = null;
			is = fileUpload.getInputStream();
			Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
			Map<String,String> mapTop=new HashMap<>();
			mapTop.put("学年标识（不可修改）","schoolYear");
			mapTop.put("年级标识（不可修改）","gradeId");
			mapTop.put("年级名称","gradeName");
			mapTop.put("学生id(不可修改)","stuId");
			mapTop.put("学生姓名","stuName");
			mapTop.put("班级号","teamId");
			mapTop.put("新学号","newNumber");

			//解析表格数据
			List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
			//导入失败数据
			List<Map<String,Object>> errList=new ArrayList<>();
			//导入成功数据
			List<Map<String,Object>> successList=new ArrayList<>();
			//返回前端数据
			Map<String,Object> map=new HashMap<>();

			for(Map<String,Object> bb:list) {
				Student teamStudent = new Student();
				teamStudent.setId(Integer.parseInt(bb.get("stuId").toString()));

				teamStudent.setEmpCode(bb.get("newNumber").toString());
				Student studentinit = studentService.findStudentById(teamStudent.getId());
				//查询对应的班级
				List<Map<String, Object>> teamList = basicSQLService.find("select id,name,team_number,grade_id from pj_team where grade_id=" + Integer.parseInt(bb.get("gradeId").toString()) + "  and is_delete=0  and team_number=" + Integer.parseInt(bb.get("teamId").toString().substring(0, 1)));
				teamStudent.setTeamId(Integer.parseInt(teamList.get(0).get("id").toString()));
				teamStudent.setTeamName(teamList.get(0).get("name").toString());
				//修改食堂工号和班级部门
				EmployeeList employeeList = new EmployeeList();
				SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
				//生日
				employeeList.setEmp_birthday(ft.format(new Date()));
				System.out.println("生日" + employeeList.getEmp_birthday());
				//必传
				employeeList.setEmp_pycode(String.valueOf(studentinit.getUserId()));
				System.out.println(String.valueOf(studentinit.getUserId()));
				System.out.println("banji" + teamStudent.getTeamId());
				//姓名
				employeeList.setEmp_name(studentinit.getName());
				System.out.println("姓名" + studentinit.getName());
				if (studentinit.getSex() == "1") {
					employeeList.setEmp_sex("男");
				} else if (studentinit.getSex() == "2") {
					employeeList.setEmp_sex("女");
				} else {
					employeeList.setEmp_sex("男");
				}
				//工号
				employeeList.setEmp_code(teamStudent.getEmpCode());
				System.out.println(employeeList.getEmp_code());
				//
				String ShiTangName = banjimingchengzhuanhuan(teamStudent.getTeamName());
				System.out.println(ShiTangName);
				//必传
				employeeList.setDept_name(ShiTangName);
				//必传
				employeeList.setEmp_card(studentinit.getEmpCard());
				employeeList.setEmp_workdate(ft.format(new Date()));
				System.out.println("工作时间"+employeeList.getEmp_workdate());
				//身份证
				System.out.println("卡号"+studentinit.getEmpCard());
				//身份证
				employeeList.setEmp_idcard(studentinit.getUserName());
				Object object = com.alibaba.fastjson.JSONObject.toJSON(employeeList);
				com.alibaba.fastjson.JSONObject param = new com.alibaba.fastjson.JSONObject();
				System.out.println(param.toJSONString());
				param.put("sign_name", "kksss");
				param.put("tran_code", "emp_update");
				param.put("employeeList", object);
				HttpRequestResult httpRequestResult = null;
				//10.191.109.85
				HttpRequestConfig config = HttpRequestConfig.create().url("http://10.170.76.29:8090/api/mobile/VipUser/UserEmployeeUpdate")
						.addHeader("content-type", "application/json")
						.httpEntityType(HttpEntityType.ENTITY_STRING);
				config.json(param.toString());
				httpRequestResult = HttpClientUtils.post(config);
				//判断食堂修改接口返回信息
				if (httpRequestResult == null) {
					bb.put("message", "食堂无返回信息");
					errList.add(bb);
					log.info("食堂无返回信息");
				} else {
					if (200 == httpRequestResult.getCode()) {
						String responseText = httpRequestResult.getResponseText();
						if (("").equals(responseText) || responseText == null) {
							bb.put("message", "食堂返回信息为空");
							errList.add(bb);
							System.out.println("食堂返回信息为空");
						} else {
							com.alibaba.fastjson.JSONObject jsonObject2 = com.alibaba.fastjson.JSONObject.parseObject(responseText);

							if (jsonObject2.get("result").toString().equals("false")) {
								bb.put("message", jsonObject2.get("error").toString());
								errList.add(bb);
								System.out.println("食堂返回信息有误");
								System.out.println("返回信息" + responseText);
							} else {
								Student student = studentService.modify(teamStudent);
								if(student!=null){
									//要改造
									List<Map<String,Object>> teamstudentList=basicSQLService.find("select * from pj_team_student where is_delete=0 and student_id="+teamStudent.getId()+" and grade_id="+Integer.parseInt(bb.get("gradeId").toString()));
									Integer num=basicSQLService.update("UPDATE pj_team_student SET team_id="+teamStudent.getTeamId()+" WHERE id="+Integer.parseInt(teamstudentList.get(0).get("id").toString()));
													if(num>1) {
														bb.put("message", "success");
														successList.add(bb);
													}else {
														bb.put("message", "导入失败，studentTeam修改失败");
														errList.add(bb);
													}
												} else {
													bb.put("message", "导入失败，student修改失败");
													errList.add(bb);
												}
											}
												bb.put("message", "success");
												successList.add(bb);
												System.out.println("返回信息" + responseText);
											}
									} else {
										bb.put("message", "食堂有误");
										errList.add(bb);
										System.out.println("食堂有误");
										log.error("食堂接口--修改用户信息到远程接口失败, 错误信息 {}", httpRequestResult);
									}
				}
			}
			map.put("status","success");
			map.put("error",errList);
			map.put("success",successList);
			log.info("errList"+errList.size()+"++++success"+successList.size());
			return  map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}


	//班级格式转换
	private String banjimingchengzhuanhuan(String teamName){
		if(teamName==null && teamName.equals("")){
			return null;
		}else {
			if (teamName.equals("一年级(1)班")) {
				String str = "一年级一班";
				return str;
			}
			if (teamName.equals("一年级(2)班")) {
				String str = "一年级二班";
				return str;
			}
			if (teamName.equals("一年级(3)班")) {
				String str = "一年级三班";
				return str;
			}
			if (teamName.equals("一年级(4)班")) {
				String str = "一年级四班";
				return str;
			}
			if (teamName.equals("一年级(5)班")) {
				String str = "一年级五班";
				return str;
			}
			if (teamName.equals("一年级(6)班")) {
				String str = "一年级六班";
				return str;
			}
			if (teamName.equals("一年级(7)班")) {
				String str = "一年级七班";
				return str;
			}
			if (teamName.equals("一年级(8)班")) {
				String str = "一年级八班";
				return str;
			}
			if (teamName.equals("一年级(9)班")) {
				String str = "一年级九班";
				return str;
			}
			if (teamName.equals("一年级(10)班")) {
				String str = "一年级十班";
				return str;
			}
			if (teamName.equals("一年级(11)班")) {
				String str = "一年级十一班";
				return str;
			}
			if (teamName.equals("二年级(2)班")) {
				String str = "二年级二班";
				return str;
			}
			if (teamName.equals("二年级(3)班")) {
				String str = "二年级三班";
				return str;
			}
			if (teamName.equals("二年级(4)班")) {
				String str = "二年级四班";
				return str;
			}
			if (teamName.equals("二年级(5)班")) {
				String str = "二年级五班";
				return str;
			}if (teamName.equals("二年级(6)班")) {
				String str = "二年级六班";
				return str;
			}if (teamName.equals("二年级(7)班")) {
				String str = "二年级七班";
				return str;
			}if (teamName.equals("二年级(8)班")) {
				String str = "二年级八班";
				return str;
			}
			if (teamName.equals("二年级(9)班")) {
				String str = "二年级九班";
				return str;
			}
			if (teamName.equals("二年级(10)班")) {
				String str = "二年级十班";
				return str;
			}if (teamName.equals("二年级(11)班")) {
				String str = "二年级十一班";
				return str;
			}if (teamName.equals("二年级(1)班")) {
				String str = "二年级一班";
				return str;
			}if (teamName.equals("三年级(1)班")) {
				String str = "三年级一班";
				return str;
			}
			if (teamName.equals("三年级(2)班")) {
				String str = "三年级二班";
				return str;
			}
			if (teamName.equals("三年级(3)班")) {
				String str = "三年级三班";
				return str;
			}
			if (teamName.equals("三年级(4)班")) {
				String str = "三年级四班";
				return str;
			}
			if (teamName.equals("三年级(5)班")) {
				String str = "三年级五班";
				return str;
			}
			if (teamName.equals("三年级(6)班")) {
				String str = "三年级六班";
				return str;
			}
			if (teamName.equals("三年级(7)班")) {
				String str = "三年级七班";
				return str;
			}
			if (teamName.equals("三年级(8)班")) {
				String str = "三年级八班";
				return str;
			}
			if (teamName.equals("三年级(9)班")) {
				String str = "三年级九班";
				return str;
			}
			if (teamName.equals("三年级(10)班")) {
				String str = "三年级十班";
				return str;
			}
			if (teamName.equals("三年级(11)班")) {
				String str = "三年级十一班";
				return str;
			}if (teamName.equals("四年级(1)班")) {
				String str = "四年级一班";
				return str;
			}
			if (teamName.equals("四年级(2)班")) {
				String str = "四年级二班";
				return str;
			}
			if (teamName.equals("四年级(3)班")) {
				String str = "四年级三班";
				return str;
			}
			if (teamName.equals("四年级(4)班")) {
				String str = "四年级四班";
				return str;
			}
			if (teamName.equals("四年级(5)班")) {
				String str = "四年级五班";
				return str;
			}
			if (teamName.equals("四年级(6)班")) {
				String str = "四年级六班";
				return str;
			}
			if (teamName.equals("四年级(7)班")) {
				String str = "四年级七班";
				return str;
			}
			if (teamName.equals("四年级(8)班")) {
				String str = "四年级八班";
				return str;
			}
			if (teamName.equals("四年级(9)班")) {
				String str = "四年级九班";
				return str;
			}
			if (teamName.equals("四年级(10)班")) {
				String str = "四年级十班";
				return str;
			}
			if (teamName.equals("四年级(11)班")) {
				String str = "四年级十一班";
				return str;
			}if (teamName.equals("五年级(1)班")) {
				String str = "五年级一班";
				return str;
			}
			if (teamName.equals("五年级(2)班")) {
				String str = "五年级二班";
				return str;
			}
			if (teamName.equals("五年级(3)班")) {
				String str = "五年级三班";
				return str;
			}
			if (teamName.equals("五年级(4)班")) {
				String str = "五年级四班";
				return str;
			}
			if (teamName.equals("五年级(5)班")) {
				String str = "五年级五班";
				return str;
			}
			if (teamName.equals("五年级(6)班")) {
				String str = "五年级六班";
				return str;
			}
			if (teamName.equals("五年级(7)班")) {
				String str = "五年级七班";
				return str;
			}
			if (teamName.equals("五年级(8)班")) {
				String str = "五年级八班";
				return str;
			}
			if (teamName.equals("五年级(9)班")) {
				String str = "五年级九班";
				return str;
			}
			if (teamName.equals("五年级(10)班")) {
				String str = "五年级十班";
				return str;
			}
			if (teamName.equals("五年级(11)班")) {
				String str = "五年级十一班";
				return str;
			}if (teamName.equals("六年级(1)班")) {
				String str = "六年级一班";
				return str;
			}
			if (teamName.equals("六年级(2)班")) {
				String str = "六年级二班";
				return str;
			}
			if (teamName.equals("六年级(3)班")) {
				String str = "六年级三班";
				return str;
			}
			if (teamName.equals("六年级(4)班")) {
				String str = "六年级四班";
				return str;
			}
			if (teamName.equals("六年级(5)班")) {
				String str = "六年级五班";
				return str;
			}
			if (teamName.equals("六年级(6)班")) {
				String str = "六年级六班";
				return str;
			}
			if (teamName.equals("六年级(7)班")) {
				String str = "六年级七班";
				return str;
			}
			if (teamName.equals("六年级(8)班")) {
				String str = "六年级八班";
				return str;
			}
			if (teamName.equals("六年级(9)班")) {
				String str = "六年级九班";
				return str;
			}
			if (teamName.equals("六年级(10)班")) {
				String str = "六年级十班";
				return str;
			}
			if (teamName.equals("六年级(11)班")) {
				String str = "六年级十一班";
				return str;
			}
		}
		return null;
	}

	/*
	 * 解析xls数据
	 *
	 */
	public List<Map<String,Object>> getJieXi(Workbook work,Map<String,String> map) throws Exception {
		if (null == work) {
			throw new Exception("创建Excel工作薄为空");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		// 返回数据
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

		// 遍历Excel中所有的sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// 取第一行标题
			row = sheet.getRow(0);
			String title[] = null;
			if (row != null) {
				title = new String[row.getLastCellNum()];
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					title[y] = (String) getCellValue(cell);
				}

			} else{
				continue;
			}
			// 遍历当前sheet中的所有行
			for (int j = 2; j < sheet.getLastRowNum() + 1; j++) {
				row = sheet.getRow(j);
				Map<String, Object> m = new HashMap<String, Object>();
				// 遍历所有的列
				for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
					cell = row.getCell(y);
					String key = title[y];
					// log.info(JSON.toJSONString(key));
					m.put(map.get(key), getCellValue(cell));
				}
				ls.add(m);
			}

		}
		return ls;
	}
	/**
	 * 描述：对表格中数值进行格式化
	 *
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字

		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if ("General".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());
				} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
					value = sdf.format(cell.getDateCellValue());
				} else {
					value = df2.format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				break;
		}
		return value;
	}



	public  void FengZhuan(List<Map<String,String>> list){
		Map<String,String>  map1=new HashMap<String,String>();
		map1.put("学年标识（不可修改）","schoolYear");
		Map<String,String>  map2=new HashMap<String,String>();
		map2.put("年级标识（不可修改）","gradeId");
		Map<String,String>  map3=new HashMap<String,String>();
		map3.put("年级名称","gradeName");
		Map<String,String>  map4=new HashMap<String,String>();
		map4.put("学生id(不可修改)","stuId");
		Map<String,String>  map5=new HashMap<String,String>();
		map5.put("学生姓名","stuName");
		Map<String,String>  map6=new HashMap<String,String>();
		map6.put("班级号","teamId");
		Map<String,String>  map7=new HashMap<String,String>();
		map7.put("新学号","newNumber");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
	}

	/**
	 * 学生列表(班主任)
	 * @param sub
	 * @param dm
	 * @param user
	 * @param studentCondition
	 * @param schoolYearCondition
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/studentListForTeacher")
	public ModelAndView getStudentListForTeacher(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			@ModelAttribute("schoolYearCondition") SchoolYearCondition schoolYearCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			studentCondition.setSchoolId(user.getSchoolId());
			List<Student> studentList = null;
			
			TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
			teamTeacherCondition.setSchoolId(user.getSchoolId());
			teamTeacherCondition.setTeacherId(user.getTeacherId());
			teamTeacherCondition.setSchoolYear(schoolYearCondition.getYear());
			teamTeacherCondition.setType(1);
			
			List<TeamTeacherVo> gradeList = teamTeacherService.findTeamTeacherGradeByCondition(teamTeacherCondition);
			
			Long manNum = null;
			Long womanNum = null;
			Long totalNum = null;
			Boolean interrupteur = true;
			if(studentCondition != null){
				if(studentCondition.getTeamId() != null){
					interrupteur = jobControlService.studentArchiveCanEdit(studentCondition.getTeamId());
					
					studentCondition.setSex("1");
					manNum = studentService.count(studentCondition);
					studentCondition.setSex("2");
					womanNum = studentService.count(studentCondition);
					studentCondition.setSex(null);
					totalNum = studentService.count(studentCondition);
				}
			}
			
			if("list".equals(sub)){
				viewPath="/teach/student/listForTeacher";
				studentList = studentService.findStudentByCondition(studentCondition, page, Order.desc("create_date"));
			}else{
				viewPath="/teach/student/studentListForTeacher";
			}
			
			mav.addObject("gradeList", gradeList);
			mav.addObject("manNum", manNum);
			mav.addObject("womanNum", womanNum);
			mav.addObject("totalNum", totalNum);
			mav.addObject("studentList", studentList);
			mav.addObject("interrupteur",interrupteur);
			//mav.addObject("schoolYearList",schoolYearList);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询学生分页列表异常...");
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	/**
	 * 修改学生
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifyStudent")
	public ModelAndView modifyStudent(@RequestParam(value = "id", required = true) String id,
									  Model model,
									  @RequestParam(value = "year",required = false) String year,
									  @CurrentUser UserInfo user){
		try{
			//2016-8-17 新增除了管理员之外，其他角色均不可编辑 学籍号
			Boolean canModifyStudentNumber = false;
			String types = user.getUserTypes();
			if(types != null && !"".equals(types)){
				String[] typeArr = types.split(",");
				canModifyStudentNumber = Arrays.asList(typeArr).contains(SysContants.USER_TYPE_ADMIN);
			}
			StudentArchiveComplete studentData=new StudentArchiveComplete();
			if(year!=null){
				studentData = studentService.getStudentArchiveComplete2(Integer.parseInt(id),year);
			}else{
				studentData = studentService.getStudentArchiveComplete2(Integer.parseInt(id),user.getSchoolYear());
			}
			int parentSize = 0;
			if(studentData != null){
				if(studentData.getParent() != null && studentData.getParent().getParentMess() != null && studentData.getParent().getParentMess().size() > 0){
					parentSize = studentData.getParent().getParentMess().size();
					
					for(int i=0; i<parentSize; i++){
						String[] code = getRegionCode(studentData.getParent().getParentMess().get(i).getResidenceAddressCode());
						studentData.getParent().getParentMess().get(i).setResidenceAddressCode(code[0]+","+code[1]+","+code[2]);
					}
				}
				
				if(studentData.getBasic() != null){
					String[] birthPlaceCodeArr = getRegionCode(studentData.getBasic().getBirthPlaceCode());
					model.addAttribute("birthGradeCode", birthPlaceCodeArr[0]);
					model.addAttribute("birthParentCode", birthPlaceCodeArr[1]);
					model.addAttribute("birthCode", birthPlaceCodeArr[2]);
					
					String[] nativePlaceCode = getRegionCode(studentData.getBasic().getNativePlaceCode());
					model.addAttribute("nativeGradeCode", nativePlaceCode[0]);
					model.addAttribute("nativeParentCode", nativePlaceCode[1]);
					model.addAttribute("nativeCode", nativePlaceCode[2]);
				}
				
				if(studentData.getAssist() != null){
					String[] residenceAddressCode = getRegionCode(studentData.getAssist().getResidenceAddressCode());
					model.addAttribute("RAGradeCode", residenceAddressCode[0]);
					model.addAttribute("RAParentCode", residenceAddressCode[1]);
					model.addAttribute("residenceAddressCode", residenceAddressCode[2]);
				}
				if(studentData.getExtra().getPictureUuid()!=null){
					FileResult file = fileService.findFileByUUID(studentData.getExtra().getPictureUuid());
					if (file != null) {
						studentData.getExtra().setPictureUrl(file.getHttpUrl());

					}
				}
			}
			
			Grade grade = gradeService.findGradeById(studentData.getArchive().getGradeId());
			Team team = teamService.findTeamById(studentData.getArchive().getTeamId());
			
			model.addAttribute("canModify",canModifyStudentNumber);
			model.addAttribute("gradeName",grade.getName());
			model.addAttribute("teamName", team.getName());
			model.addAttribute("teamId", studentData.getArchive().getTeamId());
			model.addAttribute("gradeId", studentData.getArchive().getGradeId());
			model.addAttribute("studentData", studentData);
			model.addAttribute("parentlength", parentSize);
			model.addAttribute("studentId",id);
			model.addAttribute("year",year);
			
		}catch(Exception e){
			log.info("修改学生信息异常");
		}
		return new ModelAndView("/teach/student/modifyStudent", model.asMap());
	}


	/**
	 * 修改小程序端学生 和上面方法相同
	 *
	 * @param id
	 * @Date: 2020/10/12 9:49
	 * @return
	 */
	@RequestMapping("/modifyAppletsStudent")
	@ResponseBody
	public ResponseInfomation modifyStudent(@RequestParam(value = "id", required = true) String id){
		Map<String, Object> map = new HashMap<>();
		try{

			StudentArchiveComplete studentData = studentService.getStudentArchiveComplete(Integer.parseInt(id));
			int parentSize = 0;
			if(studentData != null){
				if(studentData.getParent() != null && studentData.getParent().getParentMess() != null && studentData.getParent().getParentMess().size() > 0){
					parentSize = studentData.getParent().getParentMess().size();

					for(int i=0; i<parentSize; i++){
						String[] code = getRegionCode(studentData.getParent().getParentMess().get(i).getResidenceAddressCode());
						studentData.getParent().getParentMess().get(i).setResidenceAddressCode(code[0]+","+code[1]+","+code[2]);
					}
				}

				if(studentData.getBasic() != null){
					String[] birthPlaceCodeArr = getRegionCode(studentData.getBasic().getBirthPlaceCode());
					map.put("birthGradeCode", birthPlaceCodeArr[0]);
					map.put("birthParentCode", birthPlaceCodeArr[1]);
					map.put("birthCode", birthPlaceCodeArr[2]);

					String[] nativePlaceCode = getRegionCode(studentData.getBasic().getNativePlaceCode());
					map.put("nativeGradeCode", nativePlaceCode[0]);
					map.put("nativeParentCode", nativePlaceCode[1]);
					map.put("nativeCode", nativePlaceCode[2]);
				}

				if(studentData.getAssist() != null){
					String[] residenceAddressCode = getRegionCode(studentData.getAssist().getResidenceAddressCode());
					map.put("RAGradeCode", residenceAddressCode[0]);
					map.put("RAParentCode", residenceAddressCode[1]);
					map.put("residenceAddressCode", residenceAddressCode[2]);
				}

				if(studentData.getExtra().getPictureUuid()!=null){
					FileResult file = fileService.findFileByUUID(studentData.getExtra().getPictureUuid());
					if (file != null) {
						studentData.getExtra().setPictureUrl(file.getHttpUrl());

					}
				}
			}
			Grade grade = gradeService.findGradeById(studentData.getArchive().getGradeId());
			Team team = teamService.findTeamById(studentData.getArchive().getTeamId());

			// 小程序端没有权限修改
//			map.put("canModify",canModifyStudentNumber);
			map.put("gradeName",grade.getName());
			map.put("teamName", team.getName());
			map.put("teamId", studentData.getArchive().getTeamId());
			map.put("gradeId", studentData.getArchive().getGradeId());
			map.put("studentData", studentData);
			map.put("parentlength", parentSize);
			map.put("studentId",id);

		}catch(Exception e){
			log.error("修改学生信息异常");
			return new ResponseInfomation(map, ResponseInfomation.OPERATION_ERROR);
		}
		return new ResponseInfomation(map, ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * 修改学生头像
	 * @param id
	 * @param model
	 * @param user
	 * @return
	 */
	@RequestMapping("/modifyStudentPhoto")
	public ModelAndView modifyStudentPhoto(@RequestParam(value = "id", required = true) String id, Model model, @CurrentUser UserInfo user){
		StudentArchiveComplete studentData = studentService.getStudentArchiveComplete(Integer.parseInt(id));
		model.addAttribute("studentData", studentData);
		model.addAttribute("studentId",id);
		return new ModelAndView("/teach/student/modifyStudentPhoto", model.asMap());
	}
	
	
	public String[] getRegionCode(String code){
		String[] codeArray = new String []{"","",""};
		try{
			Integer.parseInt(code);
			if(code != null && !"".equals(code)){
				int level = RegLevelUtil.getLevel(code);
				if(level == 1){
					codeArray[0] = code;
					codeArray[1] = "0";
					codeArray[2] = "0";
				}
				if(level == 2){
					Object parentCode = this.jcCacheService.findUniqueByParam("jc_region", "code", code, "parent");
					if(parentCode != null){
						codeArray[0] = parentCode.toString();
						codeArray[1] = code;
						codeArray[2] = "0";
					}
				}
				if(level == 3){
					Object parentCode = this.jcCacheService.findUniqueByParam("jc_region", "code", code, "parent");
					if(parentCode != null){
						Object gradeCode = this.jcCacheService.findUniqueByParam("jc_region", "code", parentCode.toString(), "parent");
						if(gradeCode != null){
							codeArray[0] = gradeCode.toString();
							codeArray[1] = parentCode.toString();
							codeArray[2] = code;
						}
					}
				}
			}
		}catch(IllegalArgumentException e){
			log.error(e.toString());
		}
		return codeArray;
	}
	
	@RequestMapping("/view")
	public ModelAndView view(@RequestParam(value = "id", required = true) String id,Model model){
		try{
			StudentArchiveComplete studentData = studentService.getStudentArchiveComplete(Integer.parseInt(id));
			int parentSize = 0;
			if(studentData != null){
				if(studentData.getParent() != null && studentData.getParent().getParentMess() != null && studentData.getParent().getParentMess().size() > 0){
					parentSize = studentData.getParent().getParentMess().size();
					for(int i=0; i<parentSize; i++){
						String[] code = getRegionCode(studentData.getParent().getParentMess().get(i).getResidenceAddressCode());
						studentData.getParent().getParentMess().get(i).setResidenceAddressCode(code[0]+","+code[1]+","+code[2]);
					}
				}
				
				if(studentData.getBasic() != null){
					String[] birthPlaceCodeArr = getRegionCode(studentData.getBasic().getBirthPlaceCode());
					model.addAttribute("birthGradeCode", birthPlaceCodeArr[0]);
					model.addAttribute("birthParentCode", birthPlaceCodeArr[1]);
					model.addAttribute("birthCode", birthPlaceCodeArr[2]);
					
					String[] nativePlaceCode = getRegionCode(studentData.getBasic().getNativePlaceCode());
					model.addAttribute("nativeGradeCode", nativePlaceCode[0]);
					model.addAttribute("nativeParentCode", nativePlaceCode[1]);
					model.addAttribute("nativeCode", nativePlaceCode[2]);
				}
				
				if(studentData.getAssist() != null){
					String[] residenceAddressCode = getRegionCode(studentData.getAssist().getResidenceAddressCode());
					model.addAttribute("RAGradeCode", residenceAddressCode[0]);
					model.addAttribute("RAParentCode", residenceAddressCode[1]);
					model.addAttribute("residenceAddressCode", residenceAddressCode[2]);
				}
				
			}
			Grade grade = gradeService.findGradeById(studentData.getArchive().getGradeId());
			Team team = teamService.findTeamById(studentData.getArchive().getTeamId());
			
			model.addAttribute("gradeName",grade.getName());
			model.addAttribute("teamName", team.getName());
			model.addAttribute("studentData", studentData);
			model.addAttribute("parentlength", parentSize);
			model.addAttribute("studentId",id);
			
		}catch(Exception e){
			log.info("查看学生信息异常");
			e.printStackTrace();
		}
		return new ModelAndView("/teach/student/view", model.asMap());
	} 
	
	@RequestMapping("/updateStudentPhoto")
	@ResponseBody
	public ResponseInfomation updateStudentPhoto(@ModelAttribute("studentData") StudentArchiveCompleteVo studentData){
		try {
			Student student = studentService.findStudentById(studentData.getStudentId());
			if ( student == null ) {
				return new ResponseInfomation("studentId:"+studentData.getStudentId()+"不存在");
			}
			Person person = personService.findPersonById(student.getPersonId());
			if ( person == null ) {
				return new ResponseInfomation("personId:"+student.getPersonId()+"不存在");
			}
			person.setPhotoUuid(studentData.getPhotoUuid());
			personService.modify(person);
			
			Profile profile = profileService.findByUserId(student.getUserId());
			if ( profile != null ) {
				profile.setIcon(person.getPhotoUuid());
				profile.setModifyDate(new Date());
				profileService.modify(profile);
			} else {
				createProfileByStudent(student, person);
			}
		} catch (Exception e) {
			log.info("修改学生头像信息异常");
			e.printStackTrace();
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}

	private void createProfileByStudent(Student student, Person person) {
		Profile profile;
		profile = new Profile();
		if ( person.getAddress() != null ) {
			profile.setAddress(person.getAddress());
		}
		if ( person.getEmail() != null ) {
			profile.setEmail(person.getEmail());
		}
		if ( student.getMobile() != null ) {
			profile.setMobile(student.getMobile());
		}
		if ( person.getBirthday() != null ) {
			profile.setBirthday(person.getBirthday());
		}
		if ( person.getPhotoUuid() != null ) {
			profile.setIcon(person.getPhotoUuid());
		}
		profile.setCreateDate(new Date());
		profile.setIsDeleted(false);
		profile.setModifyDate(new Date());
		if ( student.getName() != null ) {
			profile.setName(student.getName());
		}
		if ( student.getSex() != null ) {
			profile.setSex(student.getSex());
		}
		if ( student.getUserName() != null ) {
			profile.setUserName(student.getUserName());
		}
		profile.setUserId(student.getUserId());
		profileService.add(profile);
	}
	
	/**
	 * 更新学生
	 * @return
	 */
	@RequestMapping("/updateStudent")
	@ResponseBody
	public ResponseInfomation updateStudent(@CurrentUser UserInfo userInfo,
			@ModelAttribute("studentData") StudentArchiveCompleteVo studentData,
			@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile){
	/*	try{*/
			//2016-9-5新增参数 isBindingMobile 判断是否绑定手机号
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			
			//2016-8-15校验学号与学籍号的问题
//			String mselect exists(select 1 from pj_student ps  inner join pj_team pt on pt.id=ps.team_id inner join pj_grade pg on pt.grade_id=pg.id where ps.is_delete=0 and pt.is_delete=0 and ps.emp_code='"+num+"'  and pg.school_year='"+schoolYear+"') e;ess = checkNnmberAndStudentNUmber(studentData.getStudentId(),studentData.getTeamId(),studentData.getStudentNumber(),studentData.getNumber());
//			if(mess != ""){
//				return new ResponseInfomation(mess);
//			}


			if(basicSQLService.findUniqueLong("select exists(select 1 from pj_student ps  inner join pj_team pt on pt.id=ps.team_id inner join pj_grade pg on pt.grade_id=pg.id where ps.is_delete=0 and pt.is_delete=0 and ps.emp_code='"+studentData.getNumber()+"'  and pg.school_year='"+userInfo.getSchoolYear()+"'  and ps.study_state='01'  and ps.id !="+studentData.getStudentId()+") e")>0){
				return new ResponseInfomation("studentNumber2IsExit");
			}

			
			//2016-8-9添加学生完成与未完成
			if(studentData != null){
				if(studentData.getIsComplet() == null){
					studentData.setIsComplet(false);
				}
			}
			StudentArchiveComplete studentArchiveComplete = transferStudentArchiveDate(studentData);
			
			//该方法于2016-8-9添加一个参数，用于记录页面端传过来的学生档案是否完成了填写
			studentArchiveComplete = studentService.setStudentArchiveComplete(studentData.getStudentId(), studentArchiveComplete,studentData.getIsComplet(),isBindingMobile);
			if(studentArchiveComplete.getRemarks().getErrorCode() != null && !"".equals(studentArchiveComplete.getRemarks().getErrorCode())){
				if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.IDCARDNUMBER_EXIST)){
					return new ResponseInfomation("身份证已存在");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.MOBILE_ALREADY_USED)){
					return new ResponseInfomation("手机号已经被使用");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.STUDENT_NOT_EXIST)){
					return new ResponseInfomation("学生不存在");
				}
			}else{
				return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
			}
	/*	}catch(Exception e){
			log.info("修改学生信息异常");
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}*/
		
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}




	/**
	 * 更新学生 小程序使用
	 * @return
	 */
	@RequestMapping(value = "/updateAppletsStudent", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation updateAppletsStudent(@RequestBody StudentArchiveCompleteVo studentData,
											@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile){
	/*	try{*/
			//2016-9-5新增参数 isBindingMobile 判断是否绑定手机号
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			//2022注释
		/*	//2016-8-15校验学号与学籍号的问题
			String mess = checkNnmberAndStudentNUmber(studentData.getStudentId(),studentData.getTeamId(),studentData.getStudentNumber(),studentData.getNumber());
			if(mess != ""){
				return new ResponseInfomation(mess);
			}*/
			//2016-8-9添加学生完成与未完成
			if(studentData != null){
				if(studentData.getIsComplet() == null){
					studentData.setIsComplet(false);
				}
			}
			StudentArchiveComplete studentArchiveComplete = transferStudentArchiveDate(studentData);

			//该方法于2016-8-9添加一个参数，用于记录页面端传过来的学生档案是否完成了填写
			studentArchiveComplete = studentService.setStudentArchiveComplete(studentData.getStudentId(), studentArchiveComplete,studentData.getIsComplet(),isBindingMobile);
			if(studentArchiveComplete.getRemarks().getErrorCode() != null && !"".equals(studentArchiveComplete.getRemarks().getErrorCode())){
				if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.IDCARDNUMBER_EXIST)){
					return new ResponseInfomation("身份证已存在");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.MOBILE_ALREADY_USED)){
					return new ResponseInfomation("手机号已经被使用");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.STUDENT_NOT_EXIST)){
					return new ResponseInfomation("学生不存在");
				}
			}else{
				return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
			}
		/*}catch(Exception e){
			log.info("修改学生信息异常 {}",e.toString());
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}*/

		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	//页面接收的学生档案数据进行转存
	private StudentArchiveComplete transferStudentArchiveDate(StudentArchiveCompleteVo studentData){
		StudentArchiveComplete studentArchiveComplete = new StudentArchiveComplete();
			StudentArchiveComplete.Parent parent  = studentArchiveComplete.new Parent();
			List<ParentMess> parentList = new ArrayList<ParentMess>();
			
			String parentData = studentData.getParentData();
			//家庭成员
			if(parentData != "" ){
				JSONArray data = JSONArray.fromObject(parentData);
				if(data != null && data.size() > 0){
					for(int i = 0; i< data.size(); i++){
						JSONObject jsonJ = data.getJSONObject(i);
						if(jsonJ.isNullObject()){
							System.out.println("jsonJ为空");
						}else {
							ParentMess parentMess = new ParentMess();
							System.out.println(jsonJ.get("name"));
							parentMess.setName(jsonJ.get("name").toString());
							parentMess.setParentRelation(jsonJ.getString("parentRelation"));
							parentMess.setPrealtionRemarks(jsonJ.getString("prealtionRemarks"));
							parentMess.setRace(jsonJ.getString("race"));
							parentMess.setWorkingPlace(jsonJ.getString("workingPlace"));
							parentMess.setAddress(jsonJ.getString("address"));
							//parentMess.setResidenceAddress(jsonJ.getString("residenceAddress"));
							parentMess.setResidenceAddressCode(jsonJ.getString("residenceAddressCode"));
							parentMess.setMobile(jsonJ.getString("mobile"));
							parentMess.setRank(jsonJ.getString("rank"));
							parentMess.setIdCardType(jsonJ.getString("idCardType"));
							parentMess.setIdCardNumber(jsonJ.getString("idCardNumber"));
							parentMess.setPosition(jsonJ.getString("position"));
							//parentMess.setParentUserId(Integer.parseInt(jsonJ.getString("parentUserId")));
							parentList.add(parentMess);
						}
					}
				}
			}
			parent.setParentMess(parentList);
			studentArchiveComplete.setParent(parent);
			
			StudentArchiveComplete.Basic basic = studentArchiveComplete.new Basic();
			basic.setName(studentData.getName());
			basic.setSex(studentData.getSex());
			basic.setAbroadCode(studentData.getAbroadCode());
			basic.setBirthday(studentData.getBirthday());
			basic.setBirthPlaceCode(studentData.getBirthPlaceCode());
			basic.setBloodType(studentData.getBloodType());
			basic.setHealthStatus(studentData.getHealthStatus());
			basic.setIdCardNumber(studentData.getIdCardNumber());
			basic.setIdCardType(studentData.getIdCardType());
			basic.setNationality(studentData.getNationality());
			basic.setNativePlaceCode(studentData.getNativePlaceCode());
			basic.setPhotoUuid(studentData.getPhotoUuid());
			basic.setPoliticalStatus(studentData.getPoliticalStatus());
			basic.setRace(studentData.getRace());
			studentArchiveComplete.setBasic(basic);
			
			StudentArchiveComplete.Assist assist = studentArchiveComplete.new Assist();
			assist.setIdCardDate(studentData.getIdCardDate());
			assist.setPinyinName(studentData.getPinyinName());
			//assist.setResidenceAddress(studentData.getResidenceAddress());
			assist.setResidenceAddressCode(studentData.getResidenceAddressCode());
			assist.setResidenceType(studentData.getResidenceType());
			assist.setSpecialSkill(studentData.getSpecialSkill());
			assist.setUsedName(studentData.getUsedName());
			studentArchiveComplete.setAssist(assist);
			
			StudentArchiveComplete.Archive archive = studentArchiveComplete.new Archive();
			archive.setAttendSchoolType(studentData.getAttendSchoolType());
			archive.setEnrollDate(studentData.getEnrollDate());
			archive.setEnrollType(studentData.getEnrollType());
			archive.setGradeId(studentData.getGradeId());
			archive.setLeaveDate(studentData.getLeaveDate());
			archive.setNumber(studentData.getNumber());
			archive.setStudentNumber(studentData.getStudentNumber());
			archive.setStudentSource(studentData.getStudentSource());
			archive.setStudentType(studentData.getStudentType());
			archive.setTeamId(studentData.getTeamId());
			archive.setOrdelgradeId(studentData.getOrdelGradeId());
			archive.setStudyState(studentData.getStudyState());
			studentArchiveComplete.setArchive(archive);
			
			StudentArchiveComplete.Relation relation = studentArchiveComplete.new Relation();
			relation.setAddress(studentData.getAddress());
			relation.setEmail(studentData.getEmail());
			relation.setHomeAddress(studentData.getHomeAddress());
			relation.setHomepage(studentData.getHomepage());
			relation.setMobile(studentData.getMobile());
			relation.setResideAddress(studentData.getResideAddress());
			relation.setTelephone(studentData.getTelephone());
			relation.setZipCode(studentData.getZipCode());
			studentArchiveComplete.setRelation(relation);

			StudentArchiveComplete.Extra extra = studentArchiveComplete.new Extra();
			extra.setDisabilityType(studentData.getDisabilityType());
			extra.setFollowStudy(studentData.getFollowStudy());
			extra.setHouseAuthority(studentData.getHouseAuthority());
			extra.setIsBuyedDegree(studentData.getIsBuyedDegree());
			extra.setIsCityLabourChild(studentData.getIsCityLabourChild());
			extra.setIsFirstRecommended(studentData.getIsFirstRecommended());
			extra.setIsMartyrChild(studentData.getIsMartyrChild());
			extra.setIsOnlyChild(studentData.getIsOnlyChild());
			extra.setIsOrphan(studentData.getIsOrphan());
			extra.setIsPreeducated(studentData.getIsPreeducated());
			extra.setIsSponsored(studentData.getIsSponsored());
			extra.setIsUnattendedChild(studentData.getIsUnattendedChild());
			extra.setNeedSpecialCare(studentData.getNeedSpecialCare());
			extra.setAihao(studentData.getAihao());
			extra.setXingge(studentData.getXingge());
			extra.setZuoyouming(studentData.getZuoyouming());
			extra.setJingpei(studentData.getJingpei());
			extra.setLikeBook(studentData.getLikeBook());
			extra.setGanyan(studentData.getGanyan());
			extra.setGanyan1(studentData.getGanyan1());
			extra.setPictureUuid(studentData.getPictureUuid());
			studentArchiveComplete.setExtra(extra);
			
			StudentArchiveComplete.Traffic traffic = studentArchiveComplete.new Traffic();
			traffic.setBySchoolBus(studentData.getBySchoolBus());
			traffic.setSchoolDistance(studentData.getSchoolDistance());
			traffic.setTrafficType(studentData.getTrafficType());
			studentArchiveComplete.setTraffic(traffic);
			
			StudentArchiveComplete.Remarks remarks = studentArchiveComplete.new Remarks();
			remarks.setRemark(studentData.getRemark());
			studentArchiveComplete.setRemarks(remarks);
		return studentArchiveComplete;
	}
	
	
	/**
	 * 学生档案公开给个人修改
	 * @param teamId
	 * @return
	 */
	@RequestMapping("/modifyInterrupteur")
	@ResponseBody
	public String modifyInterrupteur(@RequestParam(value = "teamId", required = true) String teamId){
		
		Integer teamID = Integer.parseInt(teamId);
		Boolean interrupteur = jobControlService.studentArchiveCanEdit(teamID);
		if(interrupteur == true){
			jobControlService.enableStudentArchiveEditing(teamID, false);
			return "false";
		} else {
			jobControlService.enableStudentArchiveEditing(teamID, true);
			return "true";
		}
	}



	/**
	 * 学生档案公开给小程序个人修改
	 * @return
	 */
	@RequestMapping("/getAppletsInterrupteur")
	@ResponseBody
	public String getAppletsInterrupteur(@RequestParam(value = "name") String name){
		Boolean interrupteur = jobControlService.studentArchiveCanEditApplets(name);
		return interrupteur.toString();
	}

	/**
	 * 学生档案公开给小程序个人修改
	 * @return
	 */
	@RequestMapping("/modifyAppletsInterrupteur")
	@ResponseBody
	public String modifyAppletsInterrupteur(@RequestParam(value = "boo") Boolean boo, @RequestParam(value = "name") String name){
		jobControlService.modifyAppletsInterrupteur(boo, name);
		Boolean interrupteur = jobControlService.studentArchiveCanEditApplets(name);
		return interrupteur.toString();
	}


	
	/**
	 * 新增学生页面
	 * @return
	 */
	@RequestMapping("/addStudentPage")
	public ModelAndView addStudentPage(@CurrentUser UserInfo user){
		ModelAndView mav = new ModelAndView();
		Group group = groupService.findUnique(user.getSchoolId(), GroupContants.TYPE_SCHOOL);
		Role role = null;
		if(group != null){
			role = roleService.findUniqueInGroup(SysContants.SYSTEM_APP_ID, group.getId(), SysDefRole.STUDENT);
		}
		mav.addObject("role", role);
		mav.setViewName("/teach/student/addStudentPage");
		return mav;
	}
	
	/**
	 * 保存学生详细信息
	 */
	@RequestMapping("/addStudent")
	@ResponseBody
	public ResponseInfomation addStudent(@ModelAttribute("studentData") StudentArchiveCompleteVo studentData,
			@CurrentUser UserInfo user,@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile
				,UserDetailInfo userDetailInfo){
		//2016-9-5 新增参数 isBindingMobile 是否将学生手机号绑定为登录号
		if(isBindingMobile == null){
			isBindingMobile = false;
		}
		String mess = checkNnmberAndStudentNUmber(null,studentData.getTeamId(),studentData.getStudentNumber(),studentData.getNumber());
		if(mess != ""){
			return new ResponseInfomation(mess);
		}
		userDetailInfo = transferUserDetailInfo(studentData, userDetailInfo);
		
		UserDetailInfo udi = null;
		String message = ResponseInfomation.OPERATION_SUC;
		try{
			userDetailInfo.setSchoolId(user.getSchoolId());
			udi = studentService.saveUserInfo(userDetailInfo,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_STUDENT, SysContants.SYSTEM_APP_ID,isBindingMobile);
			if(udi != null&&udi.getMessage()!=null&&!"".equals(udi.getMessage().trim())){
				message = udi.getMessage();
			} else {
				// 食堂接口 调用远程接口发送数据
				if (address != null && !address.equals("") && canteen != null && !canteen.equals("")){
					//httpService.addEmploye(null, address + canteen, null, 0 , null, 1, udi);
				} else {
					log.error("调用远程接口失败，请检查配置接口信息是否正确！");
				}

//				// 调用图书馆接口
//				if (libraryACnteen != null && !("").equals(libraryACnteen) && libraryLogin != null && !("").equals(libraryLogin) && libraryCreate != null && !("").equals(libraryCreate)){
//					// status: 0: 单条数据 1：批量 2：单条（已获取数据）
//					httpService.addLibraryData(null, libraryACnteen, libraryLogin, libraryCreate, 0, null, udi , 1);
//				} else {
//					log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
//				}

			}
		}catch(Exception e){
			log.error("保存学生详细信息异常:"+e.getMessage());
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(udi,message);
	}
	
	//学生档案数据转存进用户信息
	private UserDetailInfo transferUserDetailInfo(StudentArchiveCompleteVo studentData ,UserDetailInfo userDetailInfo){
		userDetailInfo.setName(studentData.getName());
		userDetailInfo.setSex(studentData.getSex());
		userDetailInfo.setBirthDate(studentData.getBirthday());
		userDetailInfo.setBirthPlace(studentData.getBirthPlaceCode());
		userDetailInfo.setNativePlace(studentData.getNativePlaceCode());
		userDetailInfo.setNation(studentData.getRace());
		userDetailInfo.setNationality(studentData.getNationality());
		userDetailInfo.setCertificateType(studentData.getIdCardType());
		userDetailInfo.setCertificateNum(studentData.getIdCardNumber());
		userDetailInfo.setAbroadCode(studentData.getAbroadCode());
		userDetailInfo.setPolitical(studentData.getPoliticalStatus());
		userDetailInfo.setHealthStatus(studentData.getHealthStatus());
		userDetailInfo.setBloodType(studentData.getBloodType());
		userDetailInfo.setPhotoUuid(studentData.getPhotoUuid());
		
		userDetailInfo.setPinyinName(studentData.getPinyinName());
		userDetailInfo.setUsedName(studentData.getUsedName());
		userDetailInfo.setIdCardDate(studentData.getIdCardDate());
		userDetailInfo.setRegisterPlace(studentData.getResidenceAddressCode());
		//userDetailInfo.setRegisterPlace(studentData.getResidenceAddress());		//户口所在地
		//userDetailInfo.setResidenceAddress(studentData.getResidenceAddress());
		userDetailInfo.setRegister(studentData.getResidenceType());				//户口类别
		//userDetailInfo.setResidenceType(studentData.getResidenceType());
		userDetailInfo.setSpecialty(studentData.getSpecialSkill());
		
		userDetailInfo.setStudentType(studentData.getStudentType());
		userDetailInfo.setEnrollType(studentData.getEnrollType());
		userDetailInfo.setAttendSchoolType(studentData.getAttendSchoolType());
		userDetailInfo.setStudentSource(studentData.getStudentSource());
		userDetailInfo.setStudentNum(studentData.getStudentNumber());
		userDetailInfo.setNumber(studentData.getNumber()==""?null:Integer.parseInt(studentData.getNumber()));
		userDetailInfo.setGradeId(String.valueOf(studentData.getGradeId()));
		userDetailInfo.setStudentTeamId(studentData.getTeamId());
		//userDetailInfo.setTeamId(studentData.getTeamId());
		userDetailInfo.setEnterDate(studentData.getEnrollDate());
		userDetailInfo.setEndDate(studentData.getLeaveDate());
		
		userDetailInfo.setNowAddress(studentData.getAddress());		//现地址（身份证地址）
		//userDetailInfo.setAddress(studentData.getAddress());
		userDetailInfo.setHomeAddress(studentData.getHomeAddress());	//家庭地址
		userDetailInfo.setLiveAddress(studentData.getResideAddress());		//居住地址，通信地址
		//userDetailInfo.setResideAddress(studentData.getResideAddress());
		userDetailInfo.setCellPhone(studentData.getMobile());
		//userDetailInfo.setMobile(studentData.getMobile());
		userDetailInfo.setTelephone(studentData.getTelephone());
		userDetailInfo.setZipCode(studentData.getZipCode());
		userDetailInfo.setEmail(studentData.getEmail());
		userDetailInfo.setHomepage(studentData.getHomepage());
		
		userDetailInfo.setOnlyChild(studentData.getIsOnlyChild());
		//userDetailInfo.setIsOnlyChild(studentData.getIsOnlyChild());
		userDetailInfo.setIsPreeducated(studentData.getIsPreeducated());
		userDetailInfo.setIsUnattendedChild(studentData.getIsUnattendedChild());
		userDetailInfo.setIsCityLabourChild(studentData.getIsCityLabourChild());
		userDetailInfo.setIsOrphan(studentData.getIsOrphan());
		userDetailInfo.setIsMartyrChild(studentData.getIsMartyrChild());
		userDetailInfo.setFollowStudy(studentData.getFollowStudy());
		userDetailInfo.setDisabilityType(studentData.getDisabilityType());
		userDetailInfo.setIsBuyedDegree(studentData.getIsBuyedDegree());
		userDetailInfo.setIsSponsored(studentData.getIsSponsored());
		userDetailInfo.setIsFirstRecommended(studentData.getIsFirstRecommended());
		userDetailInfo.setHouseAuthority(studentData.getHouseAuthority());
		userDetailInfo.setNeedSpecialCare(studentData.getNeedSpecialCare());
		
		userDetailInfo.setSchoolDistance(studentData.getSchoolDistance());
		userDetailInfo.setTrafficType(studentData.getTrafficType());
		userDetailInfo.setBySchoolBus(studentData.getBySchoolBus());
		
		userDetailInfo.setRemark(studentData.getRemark());
		userDetailInfo.setRole(String.valueOf(studentData.getRoleId()));
		
		return userDetailInfo;
	}
	
//	public ResponseInfomation addStudent(
//			UserDetailInfo userDetailInfo,
//			@CurrentUser UserInfo user){
//		UserDetailInfo udi = null;
//		String message = ResponseInfomation.OPERATION_SUC;
//		try{
//			userDetailInfo.setSchoolId(user.getSchoolId());
//			udi = studentService.saveUserInfo(userDetailInfo, SysContants.SYSTEM_APP_ID);
//			if(udi != null&&udi.getMessage()!=null&&!"".equals(udi.getMessage().trim())){
//				message = udi.getMessage();
//			}
//		}catch(Exception e){
//			log.error("保存学生详细信息异常:"+e.getMessage());
//			//e.printStackTrace();
//			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
//		}
//		return new ResponseInfomation(udi,message);
//	}
	
	/**
	 * 检查学生名字重复
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "checkerName", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerName(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id", required = false) Integer id) throws UnsupportedEncodingException {
		//中文转换
		String stuName = new String(name.getBytes("iso8859-1"),"UTF-8"); 
		boolean isExist = false;
		if ("name".equals(dxlx)) {
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setName(stuName);
			studentCondition.setSchoolId(user.getSchoolId());
			List<Student> studentList = this.studentService.findStudentUniqByCondition(studentCondition, null, null);
			if (!studentList.isEmpty()) {
				Student student = studentList.get(0);
				Integer currentId = student.getId();
				if (currentId != null && currentId == id) {
					isExist = false;
				} else {
					isExist = true;
				}
			} else {
				isExist = true;
			}
		}
		return isExist;
	}
	/***
	 * 检查学籍号是否重复
	 * @return
	 */
	@RequestMapping(value = "checkerStudentNum", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerStudentNum(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "studentNum") String studentNum,
			@RequestParam(value = "id", required = false) Integer id)throws UnsupportedEncodingException{
				boolean isExist = false;
				if ("studentNum".equals(dxlx)) {
					StudentCondition studentCondition = new StudentCondition();
					studentCondition.setStudentNumber(studentNum);
					studentCondition.setSchoolId(user.getSchoolId());
					List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
					if (!studentList.isEmpty()) {
						Student student = studentList.get(0);
						Integer currentId = student.getId();
						if (currentId != null && currentId == id) {
							isExist = true;
						} else {
							isExist = false;
						}
					} else {
						isExist = true;
					}
				}
				return isExist;
	}
	
	/**
	 * 删除学生
	 * @param id
	 */
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public String deleteStudent(@RequestParam(value="id",required=true) String id){
		try{
			//teacherService.updateUserDetailInforById(Integer.parseInt(id));
			//删除学生
			Student student = this.studentService.findStudentById(Integer.parseInt(id));
			if(student != null){
				student.setIsDelete(true);
				student = this.studentService.modify(student);
				//删除年级学生
				TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
				teamStudentCondition.setUserId(student.getUserId());
				List<TeamStudent> teamStudentList = this.teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
				if(teamStudentList != null && teamStudentList.size() > 0){
					for(TeamStudent t: teamStudentList){
						t.setIsDelete(true);
						this.teamStudentService.modify(t);
					}
				}
				//删除用户
				User user =userService.findUserByUsername(student.getUserName());
				if(user != null){
					user.setIsDeleted(true);
					this.userService.modify(user);
				}
			}
			
		}catch(Exception e){
			log.info("删除学生异常....");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * 导入学生信息页面
	 */
	@RequestMapping("/upLoadStudentInfoPage")
	public ModelAndView upLoadStudentInfoPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/teach/student/upLoadStudentInfoPage");
		return mav;
	}
	
	/***
	 * 导出学生信息
	 */
	@RequestMapping("/exportStudentInfoPage")
	public ModelAndView exportStudentInfoPage(
			@RequestParam(value="schoolYear",required=false) String schoolYear,
			@RequestParam(value="gradeId",required=false) String gradeId,
			@RequestParam(value="teamId",required=false) String teamId,
			@RequestParam(value="name",required=false) String name
			){
		
		ModelAndView mav = new ModelAndView();
	
		mav.addObject("schoolYear", schoolYear);
		mav.addObject("gradeId", gradeId);
		mav.addObject("teamId", teamId);
		
		/*try {
		if(name!=null&&!"".equals(name) ){
			name=new String((name).getBytes("iso-8859-1"),"utf-8");
		}
	} catch (UnsupportedEncodingException e1) {
		e1.printStackTrace();
	}*/
		
		mav.addObject("name", name);
		mav.setViewName("/teach/student/exportStudentInfoPage");
		return mav;
	}

	/***
	 * 导入工具下载入口
	 */
	@RequestMapping("/exportUtil")
	public ModelAndView exportUtil(Model model){
		AppReleaseCondition appReleaseCondition = new AppReleaseCondition();
		appReleaseCondition.setAppKey("xunyun#educloud#utilpc");
		appReleaseCondition.setIsDeleted(false);
		appReleaseCondition.setIsCurrent(true);
		List<AppRelease> appReleases = appReleaseService.findAppReleaseByCondition(appReleaseCondition);
		if(appReleases.size() > 0){
			AppRelease ar = appReleases.get(0);
			if(ar != null && ar.getSetupFile() != null && !"".equals(ar.getSetupFile())){
				FileResult file = fileService.findFileByUUID(ar.getSetupFile());
				model.addAttribute("url", file.getHttpUrl());
			}
		}
		return new ModelAndView("/teach/student/dowloadPage", model.asMap());
	}

	
	/***
	 * 导出学生信息
	 */
	@RequestMapping("/exportStudentInfo")
	public void exportStudentInfo(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="schoolYear",required=false) String schoolYear,
			@RequestParam(value="gradeId",required=false) Integer gradeId,
			@RequestParam(value="teamId",required=false) Integer teamId,
			@RequestParam(value="name",required=false) String name,
			@CurrentUser UserInfo user){
		try{
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setSchoolYear(schoolYear);
			studentCondition.setGradeId(gradeId);
			studentCondition.setTeamId(teamId);
			studentCondition.setName(name);
			List<UserDetailInfo> udList = this.studentService.findStudentDetailInfo(studentCondition);
			List<String> title_list = new ArrayList<String>();
			List<String> colname_list = new ArrayList<String>();
			//前台传过来的选择项的参数
			String str_options=request.getParameter("ids");
			String[] szoptions = str_options.split(",");
			if(szoptions != null && szoptions.length>0){
				for(int i=0;i<szoptions.length;i++){
					String option = szoptions[i];
					colname_list.add(option.split(":")[0]);
					title_list.add(option.split(":")[1]);
				}
			}
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("name", "name");
			map.put("sex", "sex");
			map.put("studentNum", "studentNum");
			map.put("mobile", "mobile");
			map.put("position", "position");
			map.put("englishName", "englishName");
			map.put("username", "username");
			map.put("enterDate", "enterDate");
			map.put("endDate", "endDate");
			map.put("studentType", "studentType");
			map.put("healthStatus", "healthStatus");
			map.put("bloodType", "bloodType");
			map.put("state", "state");
			map.put("birthDate", "birthDate");
			map.put("certificateType", "certificateType");
			map.put("certificateNum", "certificateNum");
			map.put("nationality", "nationality");
			map.put("nation", "nation");
			map.put("nativePlace", "nativePlace");
			map.put("birthPlace", "birthPlace");
			map.put("register", "register");
			map.put("registerPlace", "registerPlace");
			map.put("province", "province");
			map.put("district", "district");
			map.put("street", "street");
			map.put("liveAddress", "liveAddress");
			map.put("isFloatingPopulation", "isFloatingPopulation");
			map.put("nowAddress", "nowAddress");
			map.put("specialty", "specialty");
			map.put("political", "political");
			map.put("religiousBelief", "religiousBelief");
			map.put("isOnlyChild", "isOnlyChild");
			map.put("email", "email");
			map.put("remark", "remark");
			
			String[] title = (String[])title_list.toArray(new String[title_list.size()]);
			String[] colname = (String[])colname_list.toArray(new String[colname_list.size()]);
			
			 //清空response  
            response.reset();  
            //设置response的Header  
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
			response.setHeader("Content-Disposition", "attachment;filename=\"studentInfo.xls" + "\"");
			HSSFWorkbook hssfWorkbook = buildExcel("学生基本信息", title, colname, udList, map);
			OutputStream os = response.getOutputStream();
			hssfWorkbook.write(os);
			os.flush();
			os.close();
		}catch(Exception e){
			log.info("...学生导出数据异常...");
			//e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public HSSFWorkbook buildExcel(String sheetname,String[] title, String[] colname, List<UserDetailInfo> udList, HashMap<String,String> mapVal){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetname);
		HSSFRow row = null;
		try{
			//HSSFCell cell = null;
			// 写表头
			row = sheet.createRow( 0);
			//产生标题列
			for (int i = 0; i < title.length; i++) {
				row.createCell((short) i).setCellValue(title[i]);
			}
			//填充数据
			if (udList != null && udList.size() > 0) {
				UserDetailInfo udi = null;
				//Iterator lit = null;
				for (int i = 0; i < udList.size(); i++) {
					udi = udList.get(i);
					row = sheet.createRow(i + 1);
					//Object obj = null;
					for (int j = 0; j < colname.length; j++) {
						if (mapVal != null && mapVal.get(colname[j]) != null) {
							if ("name".equals(colname[j])) {
								row.createCell((short) j).setCellValue(udi.getName());  
							}else if("sex".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-XB",udi.getSex()));
							}else if("studentNum".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getStudentNum());  
							}else if("mobile".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getTelephone());  
							}else if("position".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getPosition());
							}else if("englishName".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getEnglishName());
							}else if("username".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getUsername());
							}else if("enterDate".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getEnterDate()==null?"":UtilDate.getDateFormatter(udi.getEnterDate(),"yyyy-MM-dd"));
							}else if("endDate".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getEndDate()==null?"":UtilDate.getDateFormatter(udi.getEndDate(),"yyyy-MM-dd"));
							}else if("studentType".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("JY-XSLB",udi.getStudentType()));
							}else if("healthStatus".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-JKZK",udi.getHealthStatus()));
							}else if("bloodType".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("JY-XX",udi.getBloodType()));
							}else if("state".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("JY-XSDQZT",udi.getState()));
							}else if("birthDate".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getBirthDate()==null?"":UtilDate.getDateFormatter(udi.getBirthDate(),"yyyy-MM-dd"));
							}else if("certificateType".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("JY-SFZJLX",udi.getCertificateType()));
							}else if("certificateNum".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getCertificateNum());
							}else if("nationality".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-GJ",udi.getNationality()));
							}else if("nation".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-MZ",udi.getNation()));
							}else if("nativePlace".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getNativePlace());
							}else if("birthPlace".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getBirthPlace());
							}else if("register".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-HKLB",udi.getRegister()));
							}else if("registerPlace".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getRegisterPlace());
							}else if("province".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getProvince());
							}else if("district".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getDistrict());
							}else if("street".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getStreet());
							}else if("liveAddress".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getLiveAddress());
							}else if("isFloatingPopulation".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("JY-SFBZ",udi.getIsFloatingPopulation()));
							}else if("nowAddress".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getNowAddress());
							}else if("specialty".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getSpecialty());
							}else if("political".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-ZZMM",udi.getPolitical()));
							}else if("religiousBelief".equals(colname[j])){
								row.createCell((short) j).setCellValue(getDictNameByValue("GB-ZJXY",udi.getReligiousBelief()));
							}else if("isOnlyChild".equals(colname[j])){
								if(udi.getIsOnlyChild()==true){
									row.createCell((short) j).setCellValue("是");
								}else{
									row.createCell((short) j).setCellValue("否");
								}
							}else if("email".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getEmail());
							}else if("remark".equals(colname[j])){
								row.createCell((short) j).setCellValue(udi.getRemark());
							}
							
						}
						
					}
				}
			}
		}catch(Exception e){
			log.info("导出学生创建表格异常");
			e.printStackTrace();
		}
		return wb;
	}
	
	public String getDictNameByValue(String tableCode,String val){
		if(val!="" || !"".equals(val)){
			Object obj = this.jcGcCacheService.getNameByValue(tableCode, val);
			return obj==null?"":obj.toString();
		}
		return "";
	}
	
	
	/**
	 * 检验要改的EXCEL是否合法
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/checkFileUpload")
	@ResponseBody
	public String checkHSSFSheetResult(
			@RequestParam("checkFileUpload") MultipartFile checkFileUpload,
			@CurrentUser UserInfo userInfo,HttpServletResponse response) throws IOException{
			InputStream is = checkFileUpload.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(is);
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        HSSFSheet sheet = wb.getSheet(wb.getSheetName(0));
		   //List<UserDetailInfoVo> successMeaageVoList = new ArrayList<UserDetailInfoVo>();
		   try{
			   for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
		        	 HSSFRow row = sheet.getRow(i);
		        	 //UserDetailInfoVo userDetailInfoVo =  null;
		         	 HSSFCell cell1 = row.getCell(1);//学籍号
		         	 String name =  row.getCell(0)==null?"":row.getCell(0).toString();
		         	 String studentNum =  row.getCell(1)==null?"":row.getCell(1).toString();
		         	 String sexTemp =  row.getCell(2)==null?"":row.getCell(2).toString();
		         	 String isSuccess = "";
		         	 String isState = "";
		        	 if(cell1!=null){
		        		StudentCondition studentCondition = new StudentCondition();
		        		studentCondition.setSchoolId(userInfo.getSchoolId());
		        		studentCondition.setStudentNumber(studentNum);
		        		List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
		        		if(studentList.isEmpty()){
		        			isSuccess="合格";
		        			isState = "1";
		        		}else{
		        			isSuccess="学籍号已存在";
		        			isState = "2";
		        		}
		        	}else{
		        		isSuccess="学籍号不能为空";
		        		isState = "2";
		        	}
		        	 UpLoadInformation upLoadInformation = new UpLoadInformation();
		        	 upLoadInformation.setCreateDate(new Date());
		        	 upLoadInformation.setCreater(userInfo.getId().toString());
		        	 upLoadInformation.setName(name);
		        	 upLoadInformation.setSex(sexTemp);
		        	 upLoadInformation.setSchoolId(userInfo.getSchoolId());
		        	 upLoadInformation.setStudentNumber(studentNum);
		        	 upLoadInformation.setUserType("1");
		        	 upLoadInformation.setState(isState);
		        	 upLoadInformation.setMessage(isSuccess);
		        	this.upLoadInformationService.add(upLoadInformation);

		        }  
		   }catch(Exception e){
			   e.printStackTrace();
			   return ResponseInfomation.OPERATION_FAIL;
		   }
	   return ResponseInfomation.OPERATION_SUC; 
	}
	
	@SuppressWarnings("deprecation")
	public void writeMessageHSSFSheetResult(List<UserDetailInfoVo> successMeaageVoList,HttpServletResponse response){
		 try{ 
			HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet_ = wb.createSheet("错误信息表");  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet_.createRow((int) 0);  
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	  
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("姓名");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("学籍号");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("性别");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("错误信息");
	        cell.setCellStyle(style);
	        
	        for (int i=0;i<successMeaageVoList.size();i++){  
	        	UserDetailInfoVo emv = successMeaageVoList.get(i);
	            row = sheet_.createRow((int) i + 1);  
	            // 第四步，创建单元格，并设置值  
	            row.createCell((short) 0).setCellValue(emv.getName());  
	            row.createCell((short) 1).setCellValue(emv.getStudentNum());  
	            row.createCell((short) 2).setCellValue(emv.getSex());  
	            cell = row.createCell((short) 3);
	            cell.setCellValue(emv.getMessage());
	        }
            FileOutputStream fout = new FileOutputStream("E:/students_error.xls");
            wb.write(fout);
            fout.close();
            
            String path="E:/students_error.xls";
            // path是指欲下载的文件的路径。  
            File file = new File(path);  
            // 取得文件名。  
            String filename = file.getName();  
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // 清空response  
            response.reset();  
            // 设置response的Header  
            response.addHeader("Content-Disposition", "attachment;filename="  
                    + new String(filename.getBytes())); 
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close(); 
        }catch (Exception e){  
            e.printStackTrace();  
        }  

	}
	
	/**
	 * 导入学生信息
	 * @throws IOException 
	 */
	@RequestMapping(value = "/upLoadStudentInfo", method = RequestMethod.POST)
	@ResponseBody
	public String upLoadStudentInfo(
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@RequestParam(value="role",required=true) String role,
			@CurrentUser UserInfo userInfo,
			HttpServletResponse response,
			HttpServletRequest request,
			@ModelAttribute("upLoadInformationCondition") UpLoadInformationCondition upLoadInformationCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order) throws IOException{
		InputStream is = null;
		try{
			is = fileUpload.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(is);
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        HSSFSheet sheet = wb.getSheet(wb.getSheetName(0));
	        /***
	         * 先清空临时表中的数据
	         */
	         upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
			 upLoadInformationCondition.setCreater(userInfo.getId().toString());
			 upLoadInformationCondition.setUserType("1");//1:学生 2：教师
			 List<UpLoadInformation> upLoadInformationList =  this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
		     if(!upLoadInformationList.isEmpty()){
	        	for(UpLoadInformation u : upLoadInformationList){
	        		upLoadInformationService.remove(u);
	        	}
		      }
		     StudentCondition studentCondition = new StudentCondition();
		     UpLoadInformation upLoadInformation = null;
		    int endRow =  sheet.getLastRowNum();
		    
			 for (int i = 2; i < endRow+1; i++) {
			  
	        	 HSSFRow row = sheet.getRow(i);
	         	 HSSFCell cell1 = row.getCell(1);//学籍号
	         	 String name =  row.getCell(0)==null?"":row.getCell(0).toString();
	         	 String studentNum =  row.getCell(1)==null?"":row.getCell(1).toString();
	         	 String sexTemp =  row.getCell(2)==null?"":row.getCell(2).toString();
	         	//家长手机
	         	 String mobileTemp =  row.getCell(3)==null?"":row.getCell(3).toString();
	         	 System.out.println("mobileTemp:"+mobileTemp);
	         	//是否住宿
	         	 String isBoardedTemp =  row.getCell(4)==null?"是":row.getCell(4).toString();
	         	 
	         	 if((name == null||"".equals(name))
	         			 &&(studentNum == null||"".equals(studentNum))
	         			&&(sexTemp == null||"".equals(sexTemp))
	         			
	         			 ){//防止出现非常规个事
	         		 continue;
	         	 }
	         	 String isSuccess = "";
	         	 String isState = "";
	        	 if(cell1!=null){
	        		studentCondition.setSchoolId(userInfo.getSchoolId());
	        		studentCondition.setStudentNumber(studentNum);
	        		List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
	        		if(studentList.isEmpty()){
	        			isSuccess="合格";
	        			isState = "1";
	        		}else{
	        			isSuccess="学籍号已存在";
	        			isState = "2";
	        		}
	        	}else{
	        		isSuccess="学籍号不能为空";
	        		isState = "2";
	        	}
	        	 if((name!="" || !"".equals(name)) && (studentNum!="" || !"".equals(studentNum)) &&(sexTemp!="" ||  !"".equals(sexTemp))){
	        		 upLoadInformation = new UpLoadInformation();
		        	 upLoadInformation.setCreateDate(new Date());
		        	 upLoadInformation.setCreater(userInfo.getId().toString());
		        	 upLoadInformation.setName(name);
		        	 upLoadInformation.setSex(sexTemp);
		        	 upLoadInformation.setSchoolId(userInfo.getSchoolId());
		        	 upLoadInformation.setStudentNumber(studentNum);
		        	 upLoadInformation.setUserType("1");
		        	 upLoadInformation.setRole(role);
		        	 upLoadInformation.setState(isState);
		        	 upLoadInformation.setMobile(mobileTemp);
		        	 upLoadInformation.setIsBoarded(isBoardedTemp);
		        	 upLoadInformation.setMessage(isSuccess);
		        	 upLoadInformationService.add(upLoadInformation);
	        	 }
		   }
		}catch(Exception e){
			log.info("导入学生信息到临时表异常");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}finally{  
			if(is!=null){
				is.close(); 
			}
		} 
		return  ResponseInfomation.OPERATION_SUC;
	}
	
	/***
	 * 异步加载导入学生信息
	 * @param userInfo
	 * @param response
	 * @param upLoadInformationCondition
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/getUploadInformation", method = RequestMethod.POST)  
	@ResponseBody
	public Map<String,List<UpLoadInformation>> getUploadInformation(
			@CurrentUser UserInfo userInfo,
			HttpServletResponse response,
			@ModelAttribute("upLoadInformationCondition") UpLoadInformationCondition upLoadInformationCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		 Map<String,List<UpLoadInformation>> map = new HashMap<String,List<UpLoadInformation>>();
		 try{
			 upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
			 upLoadInformationCondition.setCreater(userInfo.getId().toString());
			 upLoadInformationCondition.setUserType("1");//1:学生 2：教师
			 upLoadInformationCondition.setState("1");//1:成功
			 List<UpLoadInformation> upLoadInformationListSuccess =  this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
			 
			 upLoadInformationCondition.setState("2");//2:失败
			 List<UpLoadInformation> upLoadInformationListFail = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
			// System.out.println("upLoadInformationListSuccess:"+upLoadInformationListSuccess.size()+"=upLoadInformationListFail="+upLoadInformationListFail.size());
			 map.put("upLoadInformationListSuccess", upLoadInformationListSuccess);
			 map.put("upLoadInformationListFail", upLoadInformationListFail);
		 }catch(Exception e){
			// e.printStackTrace();
			 log.info("异步加载导入学生异常...");
			 return null;
		 }
		return map;
	}
	
	
	@RequestMapping(value = "/saveUploadStudentInformation", method = RequestMethod.POST)  
	@ResponseBody
	public String saveUploadStudentInformation(
			@CurrentUser UserInfo userInfo,
			HttpServletResponse response,
			@ModelAttribute("upLoadInformationCondition") UpLoadInformationCondition upLoadInformationCondition,
			@ModelAttribute("isBindingMobile") Boolean isBindingMobile){
		if(isBindingMobile == null){
			isBindingMobile = false;
		}
		String message = ResponseInfomation.OPERATION_SUC;
		try{
			
			 upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
			 upLoadInformationCondition.setCreater(userInfo.getId().toString());
			 upLoadInformationCondition.setUserType("1");//1:学生 2：教师
			 upLoadInformationCondition.setState("1");//1:成功
			 upLoadInformationCondition.setCreater(userInfo.getId().toString());
			 UserDetailInfoVo userDetailInfoVo = null;
			 List<UpLoadInformation> upLoadInformationListSuccess =  this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
			 for(UpLoadInformation upLoadInformation : upLoadInformationListSuccess){
				userDetailInfoVo = new UserDetailInfoVo();
	        	userDetailInfoVo.setSchoolId(userInfo.getSchoolId());
	        	userDetailInfoVo.setName(upLoadInformation.getName());
	        	userDetailInfoVo.setStudentNum(upLoadInformation.getStudentNumber());
	        	userDetailInfoVo.setSex(getSexValue(upLoadInformation.getSex()));
	        	userDetailInfoVo.setRole(upLoadInformation.getRole());
	        	userDetailInfoVo.setIsBoarded(getValue(upLoadInformation.getIsBoarded()));
	        	userDetailInfoVo.setParentCellPhone(upLoadInformation.getMobile());
	        	UserDetailInfo studentDetail = studentService.saveUserInfo(userDetailInfoVo,SysContants.DEFAULT_PASSWORD,SysContants.USER_TYPE_STUDENT, SysContants.SYSTEM_APP_ID,isBindingMobile);
	        	if(studentDetail != null&&studentDetail.getMessage()!=null&&!"".equals(studentDetail.getMessage().trim())){
					message = studentDetail.getMessage();
				}
	        	//将已同步到学生表中的数据后，将state状态设为3
	        	upLoadInformation.setState("3");
	        	this.upLoadInformationService.modify(upLoadInformation);
			 }
		}catch(Exception e){
			e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return message;
	}
	
	 
	
	@SuppressWarnings("deprecation")
	public void writeErrorMessageHSSFSheetResult(List<ErroMessageVo> errorMeaageVoList,HttpServletResponse response){
		 try{ 
			HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet_ = wb.createSheet("错误信息表");  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet_.createRow((int) 0);  
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	  
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("姓名");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("学籍号");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("性别");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("错误信息");
	        cell.setCellStyle(style);
	        for (int i=0;i<errorMeaageVoList.size();i++){  
	        	ErroMessageVo emv = errorMeaageVoList.get(i);
	            row = sheet_.createRow((int) i + 1);  
	            // 第四步，创建单元格，并设置值  
	            row.createCell((short) 0).setCellValue(emv.getName());  
	            row.createCell((short) 1).setCellValue(emv.getStudentNum());  
	            row.createCell((short) 2).setCellValue(emv.getSex());  
	            cell = row.createCell((short) 3);
	            cell.setCellValue(emv.getErrorMessage());  
	        }
            FileOutputStream fout = new FileOutputStream("E:/students_error.xls");
            wb.write(fout);
            fout.close();
            
            String path="E:/students_error.xls";
            // path是指欲下载的文件的路径。  
            File file = new File(path);  
            // 取得文件名。  
            String filename = file.getName();  
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // 清空response  
            response.reset();  
            // 设置response的Header  
            response.addHeader("Content-Disposition", "attachment;filename="  
                    + new String(filename.getBytes())); 
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close(); 
        }catch (Exception e){  
            e.printStackTrace();  
        }  

	}
	
	@SuppressWarnings("deprecation")
	public void writeErrorMessageHSSFSheetResultAll(List<ErroMessageVo> errorMeaageVoList,HttpServletResponse response){
		 try{ 
			HSSFWorkbook wb = new HSSFWorkbook();  
	        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
	        HSSFSheet sheet_ = wb.createSheet("错误信息表");  
	        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
	        HSSFRow row = sheet_.createRow((int) 0);  
	        // 第四步，创建单元格，并设置值表头 设置表头居中  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
	  
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("姓名");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("英文名");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("用户名");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("错误信息");
	        cell.setCellStyle(style);
	        for (int i=0;i<errorMeaageVoList.size();i++){  
	        	ErroMessageVo emv = errorMeaageVoList.get(i);
	            row = sheet_.createRow((int) i + 1);  
	            // 第四步，创建单元格，并设置值  
	            row.createCell((short) 0).setCellValue(emv.getName());  
	            row.createCell((short) 1).setCellValue(emv.getEnglistName());  
	            row.createCell((short) 2).setCellValue(emv.getUsername());  
	            cell = row.createCell((short) 3);
	            cell.setCellValue(emv.getErrorMessage());  
	        }
            FileOutputStream fout = new FileOutputStream("E:/students_error.xls");
            wb.write(fout);
            fout.close();
            
            String path="E:/students_error.xls";
            // path是指欲下载的文件的路径
            File file = new File(path);
            // 取得文件名。  
            String filename = file.getName();
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // 清空response  
            response.reset();  
            // 设置response的Header  
            response.addHeader("Content-Disposition", "attachment;filename="  
                    + new String(filename.getBytes())); 
            response.addHeader("Content-Length", "" + file.length());  
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);  
            toClient.flush();  
            toClient.close(); 
        }catch (Exception e){  
            e.printStackTrace();  
        }  

	}
	
	public Map<String,Object> getHSSFSheetResult(HSSFSheet sheet,UserInfo userInfo){
		   Map<String,Object> map = new HashMap<String,Object>();
		   List<ErroMessageVo> errorMeaageVoList = new ArrayList<ErroMessageVo>();
		   List<UserDetailInfoVo> successMeaageVoList = new ArrayList<UserDetailInfoVo>();
		   try{
			   for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
		        	 HSSFRow row = sheet.getRow(i);
		        	 
		        	 UserDetailInfoVo userDetailInfoVo =  null;
		         	 ErroMessageVo erroMessageVo  = null;
		         	 boolean flag = false;
		         	
		         	 HSSFCell cell1 = row.getCell(1);//学籍号
		         	 String name =  row.getCell(0)==null?"":row.getCell(0).toString();
		         	 String studentNum =  row.getCell(1)==null?"":row.getCell(1).toString();
		         	 String sexTemp =  row.getCell(2)==null?"":row.getCell(2).toString();
		        	 if(cell1!=null){
		        		StudentCondition studentCondition = new StudentCondition();
		        		studentCondition.setSchoolId(userInfo.getSchoolId());
		        		studentCondition.setStudentNumber(studentNum);
		        		List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
		        		if(studentList.isEmpty()){
		        			flag = true;
		        		}else{
		        			flag = false;
		        			erroMessageVo = getErrorMessage(i,name,sexTemp,studentNum,"学籍号已存在");
		        		}
		        	}else{
		        		flag = false;
		        		erroMessageVo = getErrorMessage(i,name,sexTemp,studentNum,"学籍号不能为空");
		        	}
		        	//符合条件
		        	if(flag){
		        		userDetailInfoVo = getHssfCell(row);
		        		userDetailInfoVo.setSchoolId(userInfo.getSchoolId());
		        		successMeaageVoList.add(userDetailInfoVo);
		        	}else{
		        		errorMeaageVoList.add(erroMessageVo);
		        	}
		        }  
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		map.put("ERROR", errorMeaageVoList);
		map.put("SUCCESS", successMeaageVoList);
	   return map;
	}
	
	public Map<String,Object> getHSSFSheetResultAll(HSSFSheet sheet,UserInfo userInfo){
		   Map<String,Object> map = new HashMap<String,Object>();
		   List<ErroMessageVo> errorMeaageVoList = new ArrayList<ErroMessageVo>();
		   List<UserDetailInfoVo> successMeaageVoList = new ArrayList<UserDetailInfoVo>();
		   try{
			   for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
		        	 HSSFRow row = sheet.getRow(i);
		        	 
		        	 UserDetailInfoVo userDetailInfoVo =  null;
		         	 ErroMessageVo erroMessageVo  = null;
		         	 boolean flag = false;
		         	
		         	 HSSFCell cell2 = row.getCell(2);//用户名
		         	 String name =  row.getCell(0)==null?"":row.getCell(0).toString();
		         	 String englistName =  row.getCell(1)==null?"":row.getCell(1).toString();
		         	 String username =  row.getCell(2)==null?"":row.getCell(2).toString();
		        	 if(cell2!=null){
		        		User user =this.userService.findUserByUsername(username);
		        		if(user==null){
		        			flag = true;
		        		}else{
		        			flag = false;
		        			erroMessageVo = getErrorMessage(i,name,englistName,username,"用户名不能重复");
		        		}
		        	}else{
		        		flag = false;
		        		erroMessageVo = getErrorMessage(i,name,englistName,username,"用户名不能为空");
		        	}
		        	
		        	//符合条件
		        	if(flag){
		        		userDetailInfoVo = getHssfCell(row);
		        		userDetailInfoVo.setSchoolId(userInfo.getSchoolId());
		        		successMeaageVoList.add(userDetailInfoVo);
		        	}else{
		        		errorMeaageVoList.add(erroMessageVo);
		        	}
		        	
		        }
				map.put("ERROR", errorMeaageVoList);
				map.put("SUCCESS", successMeaageVoList);  
		   }catch(Exception e){
			   e.printStackTrace();
		   }
		   return map;
		}
	
	public ErroMessageVo getErrorMessage(Integer rowId,String name,String sexTemp,String studentNumTemp,String errorMessage){
		ErroMessageVo erroMessageVo= new ErroMessageVo();
		erroMessageVo.setRowId(rowId);
		erroMessageVo.setErrorMessage(errorMessage);
		erroMessageVo.setName(name);
		erroMessageVo.setSex(sexTemp);
		erroMessageVo.setStudentNum(studentNumTemp);
		return erroMessageVo;
	}
	
	public UserDetailInfoVo getHssfCell(HSSFRow row) throws Exception{
		UserDetailInfoVo udv = new UserDetailInfoVo();
		try {
			HSSFCell cell0 = row.getCell(0);
			udv.setName(cell0==null?"":cell0.toString());
			
			HSSFCell cell1 = row.getCell(1);//学籍号
	        udv.setStudentNum(cell1==null?"":cell1.toString());
	        
		    HSSFCell cell2 = row.getCell(2);//性别
	        udv.setSex(cell2==null?"":getSexValue(cell2.toString().trim()));
	     
          } catch (Exception e) {
  			e.printStackTrace();
  			throw e;
  		}
          return udv;
	}
	
	public UserDetailInfoVo getHssfCellAll(HSSFRow row){
		UserDetailInfoVo udv = new UserDetailInfoVo();
		try {
		  HSSFCell cell0 = row.getCell(0);
		  udv.setName(cell0==null?"":cell0.toString());
          HSSFCell cell1 = row.getCell(1);
          udv.setEnglishName(cell1==null?"":cell1.toString());
          HSSFCell cell2 = row.getCell(2);//用户名
          udv.setUsername(cell2==null?"":cell2.toString());
          HSSFCell cell3 = row.getCell(3);//民族
          udv.setNation(cell3==null?"":getNationValue(cell3.toString()));
          HSSFCell cell4 = row.getCell(4);//学生类别
          udv.setStudentType(cell4==null?"":getStudentTypeValue(cell4.toString()));
          HSSFCell cell5 = row.getCell(5);//学籍号
          udv.setStudentNum(cell5==null?"":cell5.toString());
          HSSFCell cell6 = row.getCell(6);//职务
          udv.setPosition(cell6==null?"":cell6.toString());
          HSSFCell cell7 = row.getCell(7);//入学时间
         
		  udv.setEnterDate(cell7==null? new Date():getDate(cell7.toString()));
          HSSFCell cell8 = row.getCell(8);//离校时间
          udv.setEndDate(cell8==null?new Date():getDate(cell8.toString()));
         
         
          HSSFCell cell10 = row.getCell(10);//健康状况
          udv.setHealthStatus(cell10==null?"":getHealthState(cell10.toString()));
          
          HSSFCell cell11 = row.getCell(11);//港澳台侨码
          udv.setAbroadCode(cell11==null?"":cell11.toString());
          HSSFCell cell12 = row.getCell(12);//血型
          udv.setBloodType(cell12==null?"":getBloodTypeValue(cell12.toString()));
          HSSFCell cell13 = row.getCell(13);//在读状态
          udv.setState(cell13==null?"":getStateValue(cell13.toString()));
          HSSFCell cell14 = row.getCell(14);//性别
          udv.setSex(cell14==null?"":getSexValue(cell14.toString().trim()));
          HSSFCell cell15 = row.getCell(15);//出生日期
         
          udv.setBirthDate(cell15==null?new Date():getDate(cell15.toString()));
          HSSFCell cell16 = row.getCell(16);//证件类型
          udv.setCertificateType(cell16==null?"1":getCertificateTypeValue(cell16.toString().trim()));
          HSSFCell cell17 = row.getCell(17);//证件号码
          udv.setCertificateNum(cell17==null?"":cell17.toString());
          HSSFCell cell18 = row.getCell(18);//国籍
          udv.setNationality(cell18==null?"":getNationlityTypeValue(cell18.toString().trim()));
          HSSFCell cell19 = row.getCell(19);//籍贯
          udv.setNativePlace(cell19==null?"":cell19.toString());
          HSSFCell cell20 = row.getCell(20);//出生地
          udv.setBirthPlace(cell20==null?"":cell20.toString());
          HSSFCell cell21 = row.getCell(21);//户口性质
          udv.setRegister(cell21==null?"":getRegist(cell21.toString()));
          HSSFCell cell22 = row.getCell(22);//户口所在地
          udv.setRegisterPlace(cell22==null?"":cell22.toString());
          HSSFCell cell23 = row.getCell(23);//省份
          udv.setProvince(cell23==null?"":cell23.toString());
          HSSFCell cell24 = row.getCell(24);//城市
          udv.setCity(cell24==null?"":cell24.toString());
          HSSFCell cell25 = row.getCell(25);//街道
          udv.setStreet(cell25==null?"":cell25.toString());
          HSSFCell cell26 = row.getCell(26);//现在址
          udv.setNowAddress(cell26==null?"":cell26.toString());
          HSSFCell cell27 = row.getCell(27);//居住地址
          udv.setLiveAddress(cell27==null?"":cell27.toString());
          HSSFCell cell28 = row.getCell(28);//是否流动人口
          udv.setIsFloatingPopulation(cell28==null?"":getFolatPeople(cell28.toString()));
          HSSFCell cell29 = row.getCell(29);//特长
          udv.setSpecialty(cell29==null?"":cell29.toString());
          HSSFCell cell30 = row.getCell(30);//政治面貌
          udv.setPolitical(cell30==null?"":getPoliticalValue(cell30.toString()));
          
          HSSFCell cell31 = row.getCell(31);//宗教信仰
          udv.setReligiousBelief(cell31==null?"":getReligiousBelief(cell31.toString()));
          HSSFCell cell32 = row.getCell(32);//是否独生子
          udv.setIsOnlyChild(cell32==null?false:getOnlyChildValue(cell32.toString()));
          HSSFCell cell33 = row.getCell(33);//电话
          udv.setTelephone(cell33==null?"":cell33.toString());
          HSSFCell cell34 = row.getCell(34);//手机
          udv.setCellPhone(cell34==null?"":cell34.toString());
          HSSFCell cell35 = row.getCell(35);//邮件
          udv.setEmail(cell35==null?"":cell35.toString());
          } catch (ParseException e) {
  			e.printStackTrace();
  		}
          return udv;
	}
	
	public Date getDate(String date) throws ParseException{
		SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		return d;
	}
	
	//获取民族值
	public String getNationValue(String nation){
		switch(nation){
			case "汉族": return "01";
			case "蒙古族": return "02";
			case "回族": return "03";
			case "藏族": return "04";
			case "维吾尔族": return "05";
			case "苗族": return "06";
			case "彝族": return "07";
			case "壮族": return "08";
			case "布依族": return "09";
			case "朝鲜族": return "10";
			case "满族": return "11";
			case "侗族": return "12";
			case "瑶族": return "13";
			case "白族":return "14";
			case "土家族":return "15";
			case "哈尼族":return "16";
			case "哈萨克族":return "17";
			case "傣族":return "18";
			case "黎族":return "19";
			case "傈僳族":return "20";
			case "佤族":return "21";
			case "畲族":return "22";
			case "高山族":return "23";
			case "拉祜族":return "24";
			case "水族":return "25";
			case "东乡族":return "26";
			case "纳西族":return "27";
			case "景颇族":return "28";
			case "柯尔克孜族":return "29";
			case "土族":return "30";
			case "达斡尔族":return "31";
			case "仫佬族":return "32";
			case "羌族":return "33";
			case "布朗族":return "34";
			case "撒拉族":return "35";
			case "毛难族":return "36";
			case "仡佬族":return "37";
			case "锡伯族":return "38";
			case "阿昌族":return "39";
			case "普米族":return "40";
			case "塔吉克族":return "41";
			case "怒族":return "42";
			case "乌孜别克族":return "43";
			case "俄罗斯族":return "44";
			case "鄂温克族":return "45";
			case "德昂族":return "46";
			case "保安族":return "47";
			case "裕固族":return "48";
			case "京族":return "49";
			case "塔塔尔族":return "50";
			case "独龙族":return "51";
			case "赫哲族":return "52";
			case "门巴族":return "53";
			case "珞巴族":return "54";
			case "基诺族":return "55";
			case "穿青人族":return "56";
			case "其他":return "57";
			case "外国血统中国籍人士":return "58";
		}
		return "01";
	}
	//学生类别
	public String getStudentTypeValue(String studentType){
		switch(studentType){
			case "幼儿": return "11";
			case "小学生": return "21";
			case "初中生": return "31";
			case "高中生": return "32";
			case "中职学生": return "33";
			case "工读学生": return "34";
			case "专科生": return "41";
			case "本科生": return "42";
			case "研究生": return "43";
			case "学位学生": return "44";
			case "特殊教育学生": return "51";
			case "自考/预科/研究生课程类": return "91";
			case "进修及培训类": return "92";
			case "职业技术培训类": return "93";
	    }
	  return "11";
	}
	
	public String getHealthState(String healthState){
		switch(healthState){
			case "健康或良好": return "1";
			case "一般或较弱": return "2";
			case "有慢性病": return "3";
			case "残疾": return "6";
		}
		return "1";
	}
	
	//血型
	public String getBloodTypeValue(String bloodType){
		switch(bloodType){
			case "A": return "0";
			case "B": return "1";
			case "O": return "2";
			case "AB": return "3";
		}
		return "0";
	}
	//在读状态
	public String getStateValue(String state){
		switch(state){
			case "在读": return "01";
			case "休学": return "02";
			case "退学": return "03";
			case "停学": return "04";
			case "复学": return "05";
			case "流失": return "06";
			case "毕业": return "07";
			case "结业": return "08";
			case "肄业": return "09";
			case "转学": return "10";
			case "死亡": return "11";
			case "保留入学资格": return "12";
			case "公派出国": return "13";
			case "开除": return "14";
			case "下落不明": return "15";
			case "其他": return "99";
		}
		return "99";
	}
	//性别
	public String getSexValue(String sex){
		switch(sex){
			case "未知": return "0";
			case "男": return "1";
			case "女": return "2";
			case "未说明": return "9";
		}
		return "9";
	}
	
	public String getSexName(String val){
		switch(val){
		case "0": return "val";
		case "1": return "男";
		case "2": return "女";
		case "9": return "未说明";
	}
	return "未说明";
	}
	
	//证书类型
	public String getCertificateTypeValue(String certificateType){
		switch(certificateType){
			case "身份证": return "0";
			case "一卡通": return "1";
		}
		return "9";
	}
	
	//国籍
	public String getNationlityTypeValue(String nationlity){
		switch(nationlity){
			case "中国": return "156";
			case "其它": return "0";
		}
		return "156";
	}
	//户口性质
	public String getRegist(String regist){
		switch(regist){
			case "未落常住户口": return "0";
			case "非农业家庭户口": return "1";
			case "农业家庭户口": return "2";
			case "非农业集体户口": return "3";
			case "农业集体户口": return "4";
			case "自理口粮户口": return "5";
			case "寄住人口": return "6";
			case "暂住人口": return "7";
			case "其他户口": return "8";
		}
		return "8";
	}
	//是否是流动人口
	public String getFolatPeople(String floatPeople){
		switch(floatPeople){
			case "是": return "1";
			case "否": return "0";
		}
		return "1";
	}
	//政治面貌
	public String getPoliticalValue(String political){
		switch(political){
			case "中国共产党党员": return "01";
			case "中国共产党预备党员": return "02";
			case "中国共产主义青年团团员": return "03";
			case "中国国民党革命委员会会员": return "04";
			case "中国民主同盟盟员": return "05";
			case "中国民主建国会会员": return "06";
			case "中国民主促进会会员": return "07";
			case "中国农工民主党党员": return "08";
			case "中国致公党党员": return "09";
			case "九三学社社员": return "10";
			case "台湾民主自治同盟盟员": return "11";
			case "无党派民主人士": return "12";
			case "群众": return "13";
		}
		return "13";
	}
	
	//宗教信仰
	public String getReligiousBelief(String religiousBelief){
		switch(religiousBelief){
			case "无宗教信仰": return "00";
			case "佛教": return "10";
			case "喇嘛教": return "20";
			case "道教": return "30";
			case "天主教": return "40";
			case "基督教": return "50";
			case "东正教": return "60";
			case "伊斯兰教": return "70";
			case "其他": return "99";
		}
		return "99";
	}
	//是否是独生子
	public boolean getOnlyChildValue(String onlyChild){
		switch(onlyChild){
		case "是": return true;
		case "否": return false;
	}
	return true;
	}
	
	public String getValue(String str){
		String strValue="";
		if(str=="是" || "是".equals(str)){
			strValue="1";
		}else if(str=="否" || "否".equals(str)){
			strValue="0";
		}else{
			strValue="1";
		}
		return strValue;
	}
	
	/**
	 * 学生统计列表
	 * @param user
	 * @return
	 */
	@RequestMapping("/studentTJ")
	public ModelAndView getStudentTJ(@CurrentUser UserInfo user,@RequestParam(value = "type", required = false) String type){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			

				TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
				teamTeacherCondition.setSchoolId(user.getSchoolId());
				teamTeacherCondition.setTeacherId(user.getTeacherId());
				teamTeacherCondition.setType(1);
				List<TeamTeacherVo> gradeList = teamTeacherService.findTeamTeacherGradeByCondition(teamTeacherCondition);
				mav.addObject("gradeList", gradeList);

			
			viewPath="/teach/student/studentListTJ";
			mav.addObject("type", type);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询学生分页列表异常...");
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 学生统计列表
	 * @param sub
	 * @param dm
	 * @param user
	 * @param studentCondition
	 * @param schoolYearCondition
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/studentTJUnfinish")
	public ModelAndView getStudentTJUnfinish(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			@ModelAttribute("schoolYearCondition") SchoolYearCondition schoolYearCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setFinishName(StudentArchiveContants.finishName);
			studentCondition.setInterrupteur(false);
			List<Student> studentListunfinish = studentService.findFinishState(studentCondition, null, Order.desc("create_date"));
			viewPath="/teach/student/listTJ1";
			mav.addObject("unfinishNum", studentListunfinish == null ? 0 : studentListunfinish.size());
			mav.addObject("studentListunfinish", studentListunfinish);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询学生分页列表异常...");
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * 学生统计列表
	 * @param sub
	 * @param dm
	 * @param user
	 * @param studentCondition
	 * @param schoolYearCondition
	 * @param page
	 * @param order
	 * @return
	 */
	@RequestMapping("/studentTJfinish")
	public ModelAndView getStudentTJfinish(
			@RequestParam(value = "sub", required = false) String sub,
			@RequestParam(value = "dm", required = false) String dm,
			@CurrentUser UserInfo user,
			@ModelAttribute("studentCondition") StudentCondition studentCondition,
			@ModelAttribute("schoolYearCondition") SchoolYearCondition schoolYearCondition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order){
		ModelAndView mav = null;
		String viewPath = "";
		try{
			mav = new ModelAndView();
			studentCondition.setSchoolId(user.getSchoolId());
			studentCondition.setFinishName(StudentArchiveContants.finishName);
			studentCondition.setInterrupteur(true);
			List<Student> studentListfinish = studentService.findFinishState(studentCondition, null, Order.desc("create_date"));
			viewPath="/teach/student/listTJ";
			mav.addObject("finishNum", studentListfinish == null ? 0 : studentListfinish.size());
			mav.addObject("studentListfinish", studentListfinish);
			mav.setViewName(viewPath);
		}catch(Exception e){
			log.info("查询学生分页列表异常...");
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * @function 根据学生ID去查询全表是否存在过学籍辅助号，班级中是否存在过班内学号
	 * 			  返回不同的值，在判断是否重复
	 * @param studentId
	 * @param studentNumber2
	 * @param teamNumber
	 * @return Str
	 */
	public String checkNnmberAndStudentNUmber(Integer studentId,Integer teamId,String studentNumber2,String teamNumber){
		Boolean isEixt = false;
		if(studentNumber2 != null && studentNumber2 != ""){
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setStudentNumber(studentNumber2);
			studentCondition.setIsDelete(false);
			List<Student> studentList = studentService.findStudentByCondition(studentCondition, null, null);
			if(studentList != null && studentList.size() > 0){
				if(studentId != null){
					for(Student stu : studentList){
						if(!stu.getId().equals(studentId) && studentNumber2.equals(stu.getStudentNumber())){
							isEixt = true;
						}
					}
					if(isEixt){
						return "studentNumber2IsExit";
					}
				}else{
					return "studentNumber2IsExit";
				}
			}
		}

		if(teamNumber != null && teamNumber != ""){
			TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
			teamStudentCondition.setNumber(Integer.parseInt(teamNumber));
			teamStudentCondition.setTeamId(teamId);
			teamStudentCondition.setIsDelete(false);
			List<TeamStudent> teamStudentList = teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
			if(teamStudentList != null && teamStudentList.size() > 0){
				if(studentId != null){
					for(TeamStudent ts : teamStudentList){
						if(!ts.getStudentId().equals(studentId) && ts.getNumber() == Integer.parseInt(teamNumber)){
							isEixt = true;
						}
					}
					if(isEixt){
						return "teamNumberIsExit";
					}
				}else{
					return "teamNumberIsExit";
				}
			}
		}
		return "";
	}

//	/**
//	 * 班级导入学生
//	 * @return
//	 */
//	public boolean importStudent(){
//		List<StudentBo> studentBos = studentService.findBoNotSendSeewo();
//		Map<Integer,List<StudentApiBindStudentToClassParam.MisThirdStudentDto>> teamMap = conversion(studentBos);
//
//		for (Integer teamId : teamMap.keySet()) {
//
//
//			StudentApiBindStudentToClassParam param = new StudentApiBindStudentToClassParam();
//			//请求体，MimeType为 application/json
//			StudentApiBindStudentToClassParam.JSONRequestBody requestBody = StudentApiBindStudentToClassParam.JSONRequestBody.builder()
//					.build();
//			param.setRequestBody(requestBody);
//			//
//			StudentApiBindStudentToClassParam.MisThirdStudentQueryDto query = StudentApiBindStudentToClassParam.MisThirdStudentQueryDto.builder()
//					.classId(teamId.toString())
//					.schoolUid(SCHOOL_UID)
//					.appId(APPID)
//					.build();
//			requestBody.setQuery(query);
//			//学生信息
//
//			query.setStudents(teamMap.get(teamId));
//			param.setRequestBody(requestBody);
//
//			StudentApiBindStudentToClassResult result = MySeewoClient.request(StudentApiBindStudentToClassRequest.class).param(param).invoke();
//			if(result.getStatusCode() == 200 ){
//				studentService.updateAsSendSeewoByTeamId(teamId);
//
//			}
//
//
//		}
//
//		return true;
//
//	}
//
//	private Map<Integer,List<StudentApiBindStudentToClassParam.MisThirdStudentDto>> conversion(List<StudentBo> studentBos){
//
//
//		Map<Integer,List<StudentApiBindStudentToClassParam.MisThirdStudentDto>> teamMap = new HashMap<>();
//
//		for (StudentBo studentBo : studentBos) {
//
//			StudentApiBindStudentToClassParam.MisThirdStudentDto
//					misThirStuddentDto = new StudentApiBindStudentToClassParam.MisThirdStudentDto();
//
//			misThirStuddentDto.setCardCode(studentBo.getEmpCard());
//			misThirStuddentDto.setParentName(studentBo.getParentName());
//			misThirStuddentDto.setParentPhone(studentBo.getPersonMobile());
//			misThirStuddentDto.setStudentCode(studentBo.getNumber());
//			misThirStuddentDto.setStudentName(studentBo.getName());
//
//			if(teamMap.containsKey(studentBo.getEmpCard())){
//
//				teamMap.get(studentBo.getTeamId()).add(misThirStuddentDto);
//			}else{
//
//				List<StudentApiBindStudentToClassParam.MisThirdStudentDto> list =
//						new ArrayList<StudentApiBindStudentToClassParam.MisThirdStudentDto>();
//				list.add(misThirStuddentDto);
//				teamMap.put(studentBo.getTeamId(),list);
//			}
//
//		}
//
//		return teamMap;
//
//
//	}
}
