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
 * ????????????
 * @author zhoujin
 *
 */
@RestController
@RequestMapping("/teach/student")
public class StudentController extends BaseController{
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	/**
	 * ????????????
	 */
	private static String fileName;
	private static String address;
	private static String canteen;
	/**
	 * ????????????
	 */
	private static String HikvisionHost;
	private static String HikvisionAppKey;
	private static String HikvisionAppSecret;
	private static String HikvisionAddPersonUrl;
	/**
	 * ???????????????
	 */
	private static String libraryACnteen;
	private static String libraryLogin;
	private static String libraryCreate;

	static {
		fileName = "System.properties";
		// ??????
		address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
		canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");
		//??????
		HikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
		HikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
		HikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
		HikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");

		// ?????????
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
	 * ????????????
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
								log.error("??????????????????,url: {}","??????????????????");
							}
						}
					} catch (Exception e) {
						log.error("????????????????????????,url: {}","????????????");
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
			log.info("??????????????????????????????...");
			e.printStackTrace();
		}
		return mav;
	}
	/*
	* ???????????????????????????
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
	 * ????????????????????????
	 **/
	@RequestMapping("/downloadStudentXiuGai")
	public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
										  @RequestParam(value = "gradeId",required = false) Integer gradeId,
										  @RequestParam(value = "teamId",required = false) Integer teamId,
										  HttpServletResponse response,
										  HttpServletRequest request){
		//??????????????????
		List<Map<String,String>> titleList=new ArrayList<>();
		FengZhuan(titleList);
		//????????????ExamList
		String sql="select pg.school_year as schoolYear,pg.id as gradeId,pg.name as gradeName, ps.id as stuId,ps.name as stuName from pj_student ps inner join pj_team pt on pt.id=ps.team_id " +
				" inner join pj_grade pg on pg.id=pt.grade_id where  ps.is_delete=0  and ps.study_state='01' and  pg.school_year='"+schoolYear+"'";
		if(gradeId!=null && !gradeId.equals("")){
			sql+=" and pg.id="+gradeId;
		}if(teamId!=null && !teamId.equals("")){
			sql+=" and pt.id="+teamId;
		}
		sql+=" group by ps.id  order by ps.id";
		List<Map<String, Object>> map = basicSQLService.find(sql);
		String fileName1="??????????????????.xls";
		ExcelTool excelTool = new ExcelTool(fileName1,15,20);
		List<Column>  titleData=excelTool.columnTransformer(titleList);
		try {
			InputStream inputStream = excelTool.exportExcel(titleData, map, true, false);
			response.setCharacterEncoding("UTF-8");
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName1.getBytes() : fileName1.getBytes("UTF-8"); // name.getBytes("UTF-8")??????safari???????????????
			String name = new String(bytes, "ISO-8859-1"); // ???????????????????????????ISO??????
			response.setContentType("octets/stream");
			response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", name));
			ServletOutputStream outputStream = response.getOutputStream();
			//?????????
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
	 * ????????????????????????
	 *
	 */
	@RequestMapping("/upLoadStudentXiuGai")
	public Map<String, Object> updateLoadSubjectScore(@CurrentUser UserInfo userInfo,
												   @RequestParam("fileUpload") MultipartFile fileUpload){
		try {
			String fileName = fileUpload.getOriginalFilename();//???????????????
			InputStream is = null;
			is = fileUpload.getInputStream();
			Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
			Map<String,String> mapTop=new HashMap<>();
			mapTop.put("??????????????????????????????","schoolYear");
			mapTop.put("??????????????????????????????","gradeId");
			mapTop.put("????????????","gradeName");
			mapTop.put("??????id(????????????)","stuId");
			mapTop.put("????????????","stuName");
			mapTop.put("?????????","teamId");
			mapTop.put("?????????","newNumber");

			//??????????????????
			List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
			//??????????????????
			List<Map<String,Object>> errList=new ArrayList<>();
			//??????????????????
			List<Map<String,Object>> successList=new ArrayList<>();
			//??????????????????
			Map<String,Object> map=new HashMap<>();

			for(Map<String,Object> bb:list) {
				Student teamStudent = new Student();
				teamStudent.setId(Integer.parseInt(bb.get("stuId").toString()));

				teamStudent.setEmpCode(bb.get("newNumber").toString());
				Student studentinit = studentService.findStudentById(teamStudent.getId());
				//?????????????????????
				List<Map<String, Object>> teamList = basicSQLService.find("select id,name,team_number,grade_id from pj_team where grade_id=" + Integer.parseInt(bb.get("gradeId").toString()) + "  and is_delete=0  and team_number=" + Integer.parseInt(bb.get("teamId").toString().substring(0, 1)));
				teamStudent.setTeamId(Integer.parseInt(teamList.get(0).get("id").toString()));
				teamStudent.setTeamName(teamList.get(0).get("name").toString());
				//?????????????????????????????????
				EmployeeList employeeList = new EmployeeList();
				SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
				//??????
				employeeList.setEmp_birthday(ft.format(new Date()));
				System.out.println("??????" + employeeList.getEmp_birthday());
				//??????
				employeeList.setEmp_pycode(String.valueOf(studentinit.getUserId()));
				System.out.println(String.valueOf(studentinit.getUserId()));
				System.out.println("banji" + teamStudent.getTeamId());
				//??????
				employeeList.setEmp_name(studentinit.getName());
				System.out.println("??????" + studentinit.getName());
				if (studentinit.getSex() == "1") {
					employeeList.setEmp_sex("???");
				} else if (studentinit.getSex() == "2") {
					employeeList.setEmp_sex("???");
				} else {
					employeeList.setEmp_sex("???");
				}
				//??????
				employeeList.setEmp_code(teamStudent.getEmpCode());
				System.out.println(employeeList.getEmp_code());
				//
				String ShiTangName = banjimingchengzhuanhuan(teamStudent.getTeamName());
				System.out.println(ShiTangName);
				//??????
				employeeList.setDept_name(ShiTangName);
				//??????
				employeeList.setEmp_card(studentinit.getEmpCard());
				employeeList.setEmp_workdate(ft.format(new Date()));
				System.out.println("????????????"+employeeList.getEmp_workdate());
				//?????????
				System.out.println("??????"+studentinit.getEmpCard());
				//?????????
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
				//????????????????????????????????????
				if (httpRequestResult == null) {
					bb.put("message", "?????????????????????");
					errList.add(bb);
					log.info("?????????????????????");
				} else {
					if (200 == httpRequestResult.getCode()) {
						String responseText = httpRequestResult.getResponseText();
						if (("").equals(responseText) || responseText == null) {
							bb.put("message", "????????????????????????");
							errList.add(bb);
							System.out.println("????????????????????????");
						} else {
							com.alibaba.fastjson.JSONObject jsonObject2 = com.alibaba.fastjson.JSONObject.parseObject(responseText);

							if (jsonObject2.get("result").toString().equals("false")) {
								bb.put("message", jsonObject2.get("error").toString());
								errList.add(bb);
								System.out.println("????????????????????????");
								System.out.println("????????????" + responseText);
							} else {
								Student student = studentService.modify(teamStudent);
								if(student!=null){
									//?????????
									List<Map<String,Object>> teamstudentList=basicSQLService.find("select * from pj_team_student where is_delete=0 and student_id="+teamStudent.getId()+" and grade_id="+Integer.parseInt(bb.get("gradeId").toString()));
									Integer num=basicSQLService.update("UPDATE pj_team_student SET team_id="+teamStudent.getTeamId()+" WHERE id="+Integer.parseInt(teamstudentList.get(0).get("id").toString()));
													if(num>1) {
														bb.put("message", "success");
														successList.add(bb);
													}else {
														bb.put("message", "???????????????studentTeam????????????");
														errList.add(bb);
													}
												} else {
													bb.put("message", "???????????????student????????????");
													errList.add(bb);
												}
											}
												bb.put("message", "success");
												successList.add(bb);
												System.out.println("????????????" + responseText);
											}
									} else {
										bb.put("message", "????????????");
										errList.add(bb);
										System.out.println("????????????");
										log.error("????????????--???????????????????????????????????????, ???????????? {}", httpRequestResult);
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


	//??????????????????
	private String banjimingchengzhuanhuan(String teamName){
		if(teamName==null && teamName.equals("")){
			return null;
		}else {
			if (teamName.equals("?????????(1)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(2)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(3)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(4)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(5)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(6)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(7)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(8)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(9)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(10)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(11)???")) {
				String str = "??????????????????";
				return str;
			}
			if (teamName.equals("?????????(2)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(3)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(4)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(5)???")) {
				String str = "???????????????";
				return str;
			}if (teamName.equals("?????????(6)???")) {
				String str = "???????????????";
				return str;
			}if (teamName.equals("?????????(7)???")) {
				String str = "???????????????";
				return str;
			}if (teamName.equals("?????????(8)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(9)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(10)???")) {
				String str = "???????????????";
				return str;
			}if (teamName.equals("?????????(11)???")) {
				String str = "??????????????????";
				return str;
			}if (teamName.equals("?????????(1)???")) {
				String str = "???????????????";
				return str;
			}if (teamName.equals("?????????(1)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(2)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(3)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(4)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(5)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(6)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(7)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(8)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(9)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(10)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(11)???")) {
				String str = "??????????????????";
				return str;
			}if (teamName.equals("?????????(1)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(2)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(3)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(4)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(5)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(6)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(7)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(8)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(9)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(10)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(11)???")) {
				String str = "??????????????????";
				return str;
			}if (teamName.equals("?????????(1)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(2)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(3)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(4)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(5)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(6)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(7)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(8)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(9)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(10)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(11)???")) {
				String str = "??????????????????";
				return str;
			}if (teamName.equals("?????????(1)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(2)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(3)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(4)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(5)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(6)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(7)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(8)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(9)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(10)???")) {
				String str = "???????????????";
				return str;
			}
			if (teamName.equals("?????????(11)???")) {
				String str = "??????????????????";
				return str;
			}
		}
		return null;
	}

	/*
	 * ??????xls??????
	 *
	 */
	public List<Map<String,Object>> getJieXi(Workbook work,Map<String,String> map) throws Exception {
		if (null == work) {
			throw new Exception("??????Excel???????????????");
		}
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		// ????????????
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

		// ??????Excel????????????sheet
		for (int i = 0; i < work.getNumberOfSheets(); i++) {
			sheet = work.getSheetAt(i);
			if (sheet == null) {
				continue;
			}
			// ??????????????????
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
			// ????????????sheet???????????????
			for (int j = 2; j < sheet.getLastRowNum() + 1; j++) {
				row = sheet.getRow(j);
				Map<String, Object> m = new HashMap<String, Object>();
				// ??????????????????
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
	 * ??????????????????????????????????????????
	 *
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // ?????????number String??????
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // ???????????????
		DecimalFormat df2 = new DecimalFormat("0"); // ???????????????

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
		map1.put("??????????????????????????????","schoolYear");
		Map<String,String>  map2=new HashMap<String,String>();
		map2.put("??????????????????????????????","gradeId");
		Map<String,String>  map3=new HashMap<String,String>();
		map3.put("????????????","gradeName");
		Map<String,String>  map4=new HashMap<String,String>();
		map4.put("??????id(????????????)","stuId");
		Map<String,String>  map5=new HashMap<String,String>();
		map5.put("????????????","stuName");
		Map<String,String>  map6=new HashMap<String,String>();
		map6.put("?????????","teamId");
		Map<String,String>  map7=new HashMap<String,String>();
		map7.put("?????????","newNumber");
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
	}

	/**
	 * ????????????(?????????)
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
			log.info("??????????????????????????????...");
			e.printStackTrace();
		}
		return mav;
	}
	
	
	
	/**
	 * ????????????
	 * @param id
	 * @return
	 */
	@RequestMapping("/modifyStudent")
	public ModelAndView modifyStudent(@RequestParam(value = "id", required = true) String id,
									  Model model,
									  @RequestParam(value = "year",required = false) String year,
									  @CurrentUser UserInfo user){
		try{
			//2016-8-17 ????????????????????????????????????????????????????????? ?????????
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
			log.info("????????????????????????");
		}
		return new ModelAndView("/teach/student/modifyStudent", model.asMap());
	}


	/**
	 * ???????????????????????? ?????????????????????
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

			// ??????????????????????????????
//			map.put("canModify",canModifyStudentNumber);
			map.put("gradeName",grade.getName());
			map.put("teamName", team.getName());
			map.put("teamId", studentData.getArchive().getTeamId());
			map.put("gradeId", studentData.getArchive().getGradeId());
			map.put("studentData", studentData);
			map.put("parentlength", parentSize);
			map.put("studentId",id);

		}catch(Exception e){
			log.error("????????????????????????");
			return new ResponseInfomation(map, ResponseInfomation.OPERATION_ERROR);
		}
		return new ResponseInfomation(map, ResponseInfomation.OPERATION_SUC);
	}

	/**
	 * ??????????????????
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
			log.info("????????????????????????");
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
				return new ResponseInfomation("studentId:"+studentData.getStudentId()+"?????????");
			}
			Person person = personService.findPersonById(student.getPersonId());
			if ( person == null ) {
				return new ResponseInfomation("personId:"+student.getPersonId()+"?????????");
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
			log.info("??????????????????????????????");
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
	 * ????????????
	 * @return
	 */
	@RequestMapping("/updateStudent")
	@ResponseBody
	public ResponseInfomation updateStudent(@CurrentUser UserInfo userInfo,
			@ModelAttribute("studentData") StudentArchiveCompleteVo studentData,
			@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile){
	/*	try{*/
			//2016-9-5???????????? isBindingMobile ???????????????????????????
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			
			//2016-8-15?????????????????????????????????
//			String mselect exists(select 1 from pj_student ps  inner join pj_team pt on pt.id=ps.team_id inner join pj_grade pg on pt.grade_id=pg.id where ps.is_delete=0 and pt.is_delete=0 and ps.emp_code='"+num+"'  and pg.school_year='"+schoolYear+"') e;ess = checkNnmberAndStudentNUmber(studentData.getStudentId(),studentData.getTeamId(),studentData.getStudentNumber(),studentData.getNumber());
//			if(mess != ""){
//				return new ResponseInfomation(mess);
//			}


			if(basicSQLService.findUniqueLong("select exists(select 1 from pj_student ps  inner join pj_team pt on pt.id=ps.team_id inner join pj_grade pg on pt.grade_id=pg.id where ps.is_delete=0 and pt.is_delete=0 and ps.emp_code='"+studentData.getNumber()+"'  and pg.school_year='"+userInfo.getSchoolYear()+"'  and ps.study_state='01'  and ps.id !="+studentData.getStudentId()+") e")>0){
				return new ResponseInfomation("studentNumber2IsExit");
			}

			
			//2016-8-9??????????????????????????????
			if(studentData != null){
				if(studentData.getIsComplet() == null){
					studentData.setIsComplet(false);
				}
			}
			StudentArchiveComplete studentArchiveComplete = transferStudentArchiveDate(studentData);
			
			//????????????2016-8-9???????????????????????????????????????????????????????????????????????????????????????
			studentArchiveComplete = studentService.setStudentArchiveComplete(studentData.getStudentId(), studentArchiveComplete,studentData.getIsComplet(),isBindingMobile);
			if(studentArchiveComplete.getRemarks().getErrorCode() != null && !"".equals(studentArchiveComplete.getRemarks().getErrorCode())){
				if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.IDCARDNUMBER_EXIST)){
					return new ResponseInfomation("??????????????????");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.MOBILE_ALREADY_USED)){
					return new ResponseInfomation("????????????????????????");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.STUDENT_NOT_EXIST)){
					return new ResponseInfomation("???????????????");
				}
			}else{
				return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
			}
	/*	}catch(Exception e){
			log.info("????????????????????????");
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}*/
		
		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}




	/**
	 * ???????????? ???????????????
	 * @return
	 */
	@RequestMapping(value = "/updateAppletsStudent", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation updateAppletsStudent(@RequestBody StudentArchiveCompleteVo studentData,
											@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile){
	/*	try{*/
			//2016-9-5???????????? isBindingMobile ???????????????????????????
			if(isBindingMobile == null){
				isBindingMobile = false;
			}
			//2022??????
		/*	//2016-8-15?????????????????????????????????
			String mess = checkNnmberAndStudentNUmber(studentData.getStudentId(),studentData.getTeamId(),studentData.getStudentNumber(),studentData.getNumber());
			if(mess != ""){
				return new ResponseInfomation(mess);
			}*/
			//2016-8-9??????????????????????????????
			if(studentData != null){
				if(studentData.getIsComplet() == null){
					studentData.setIsComplet(false);
				}
			}
			StudentArchiveComplete studentArchiveComplete = transferStudentArchiveDate(studentData);

			//????????????2016-8-9???????????????????????????????????????????????????????????????????????????????????????
			studentArchiveComplete = studentService.setStudentArchiveComplete(studentData.getStudentId(), studentArchiveComplete,studentData.getIsComplet(),isBindingMobile);
			if(studentArchiveComplete.getRemarks().getErrorCode() != null && !"".equals(studentArchiveComplete.getRemarks().getErrorCode())){
				if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.IDCARDNUMBER_EXIST)){
					return new ResponseInfomation("??????????????????");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.MOBILE_ALREADY_USED)){
					return new ResponseInfomation("????????????????????????");
				}else if(studentArchiveComplete.getRemarks().getErrorCode().equals(StudentContants.STUDENT_NOT_EXIST)){
					return new ResponseInfomation("???????????????");
				}
			}else{
				return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
			}
		/*}catch(Exception e){
			log.info("???????????????????????? {}",e.toString());
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}*/

		return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
	}
	//?????????????????????????????????????????????
	private StudentArchiveComplete transferStudentArchiveDate(StudentArchiveCompleteVo studentData){
		StudentArchiveComplete studentArchiveComplete = new StudentArchiveComplete();
			StudentArchiveComplete.Parent parent  = studentArchiveComplete.new Parent();
			List<ParentMess> parentList = new ArrayList<ParentMess>();
			
			String parentData = studentData.getParentData();
			//????????????
			if(parentData != "" ){
				JSONArray data = JSONArray.fromObject(parentData);
				if(data != null && data.size() > 0){
					for(int i = 0; i< data.size(); i++){
						JSONObject jsonJ = data.getJSONObject(i);
						if(jsonJ.isNullObject()){
							System.out.println("jsonJ??????");
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
	 * ?????????????????????????????????
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
	 * ??????????????????????????????????????????
	 * @return
	 */
	@RequestMapping("/getAppletsInterrupteur")
	@ResponseBody
	public String getAppletsInterrupteur(@RequestParam(value = "name") String name){
		Boolean interrupteur = jobControlService.studentArchiveCanEditApplets(name);
		return interrupteur.toString();
	}

	/**
	 * ??????????????????????????????????????????
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
	 * ??????????????????
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
	 * ????????????????????????
	 */
	@RequestMapping("/addStudent")
	@ResponseBody
	public ResponseInfomation addStudent(@ModelAttribute("studentData") StudentArchiveCompleteVo studentData,
			@CurrentUser UserInfo user,@RequestParam(value = "isBindingMobile", required = false) Boolean isBindingMobile
				,UserDetailInfo userDetailInfo){
		//2016-9-5 ???????????? isBindingMobile ??????????????????????????????????????????
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
				// ???????????? ??????????????????????????????
				if (address != null && !address.equals("") && canteen != null && !canteen.equals("")){
					//httpService.addEmploye(null, address + canteen, null, 0 , null, 1, udi);
				} else {
					log.error("?????????????????????????????????????????????????????????????????????");
				}

//				// ?????????????????????
//				if (libraryACnteen != null && !("").equals(libraryACnteen) && libraryLogin != null && !("").equals(libraryLogin) && libraryCreate != null && !("").equals(libraryCreate)){
//					// status: 0: ???????????? 1????????? 2??????????????????????????????
//					httpService.addLibraryData(null, libraryACnteen, libraryLogin, libraryCreate, 0, null, udi , 1);
//				} else {
//					log.error("??????????????????????????????????????????????????????????????????????????????");
//				}

			}
		}catch(Exception e){
			log.error("??????????????????????????????:"+e.getMessage());
			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		}
		return new ResponseInfomation(udi,message);
	}
	
	//???????????????????????????????????????
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
		//userDetailInfo.setRegisterPlace(studentData.getResidenceAddress());		//???????????????
		//userDetailInfo.setResidenceAddress(studentData.getResidenceAddress());
		userDetailInfo.setRegister(studentData.getResidenceType());				//????????????
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
		
		userDetailInfo.setNowAddress(studentData.getAddress());		//??????????????????????????????
		//userDetailInfo.setAddress(studentData.getAddress());
		userDetailInfo.setHomeAddress(studentData.getHomeAddress());	//????????????
		userDetailInfo.setLiveAddress(studentData.getResideAddress());		//???????????????????????????
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
//			log.error("??????????????????????????????:"+e.getMessage());
//			//e.printStackTrace();
//			return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
//		}
//		return new ResponseInfomation(udi,message);
//	}
	
	/**
	 * ????????????????????????
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "checkerName", method = RequestMethod.GET)
	@ResponseBody
	public boolean checkerName(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dxlx", required = false) String dxlx,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id", required = false) Integer id) throws UnsupportedEncodingException {
		//????????????
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
	 * ???????????????????????????
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
	 * ????????????
	 * @param id
	 */
	@RequestMapping("/deleteStudent")
	@ResponseBody
	public String deleteStudent(@RequestParam(value="id",required=true) String id){
		try{
			//teacherService.updateUserDetailInforById(Integer.parseInt(id));
			//????????????
			Student student = this.studentService.findStudentById(Integer.parseInt(id));
			if(student != null){
				student.setIsDelete(true);
				student = this.studentService.modify(student);
				//??????????????????
				TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
				teamStudentCondition.setUserId(student.getUserId());
				List<TeamStudent> teamStudentList = this.teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
				if(teamStudentList != null && teamStudentList.size() > 0){
					for(TeamStudent t: teamStudentList){
						t.setIsDelete(true);
						this.teamStudentService.modify(t);
					}
				}
				//????????????
				User user =userService.findUserByUsername(student.getUserName());
				if(user != null){
					user.setIsDeleted(true);
					this.userService.modify(user);
				}
			}
			
		}catch(Exception e){
			log.info("??????????????????....");
			//e.printStackTrace();
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}
	
	/**
	 * ????????????????????????
	 */
	@RequestMapping("/upLoadStudentInfoPage")
	public ModelAndView upLoadStudentInfoPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/teach/student/upLoadStudentInfoPage");
		return mav;
	}
	
	/***
	 * ??????????????????
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
	 * ????????????????????????
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
	 * ??????????????????
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
			//????????????????????????????????????
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
			
			 //??????response  
            response.reset();  
            //??????response???Header  
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
			response.setHeader("Content-Disposition", "attachment;filename=\"studentInfo.xls" + "\"");
			HSSFWorkbook hssfWorkbook = buildExcel("??????????????????", title, colname, udList, map);
			OutputStream os = response.getOutputStream();
			hssfWorkbook.write(os);
			os.flush();
			os.close();
		}catch(Exception e){
			log.info("...????????????????????????...");
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
			// ?????????
			row = sheet.createRow( 0);
			//???????????????
			for (int i = 0; i < title.length; i++) {
				row.createCell((short) i).setCellValue(title[i]);
			}
			//????????????
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
									row.createCell((short) j).setCellValue("???");
								}else{
									row.createCell((short) j).setCellValue("???");
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
			log.info("??????????????????????????????");
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
	 * ???????????????EXCEL????????????
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
		         	 HSSFCell cell1 = row.getCell(1);//?????????
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
		        			isSuccess="??????";
		        			isState = "1";
		        		}else{
		        			isSuccess="??????????????????";
		        			isState = "2";
		        		}
		        	}else{
		        		isSuccess="?????????????????????";
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
	        // ???????????????webbook???????????????sheet,??????Excel????????????sheet  
	        HSSFSheet sheet_ = wb.createSheet("???????????????");  
	        // ???????????????sheet??????????????????0???,???????????????poi???Excel????????????????????????short  
	        HSSFRow row = sheet_.createRow((int) 0);  
	        // ???????????????????????????????????????????????? ??????????????????  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ????????????????????????  
	  
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("??????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("?????????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("??????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("????????????");
	        cell.setCellStyle(style);
	        
	        for (int i=0;i<successMeaageVoList.size();i++){  
	        	UserDetailInfoVo emv = successMeaageVoList.get(i);
	            row = sheet_.createRow((int) i + 1);  
	            // ??????????????????????????????????????????  
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
            // path????????????????????????????????????  
            File file = new File(path);  
            // ??????????????????  
            String filename = file.getName();  
            // ??????????????????????????????  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // ??????response  
            response.reset();  
            // ??????response???Header  
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
	 * ??????????????????
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
	         * ??????????????????????????????
	         */
	         upLoadInformationCondition.setSchoolId(userInfo.getSchoolId());
			 upLoadInformationCondition.setCreater(userInfo.getId().toString());
			 upLoadInformationCondition.setUserType("1");//1:?????? 2?????????
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
	         	 HSSFCell cell1 = row.getCell(1);//?????????
	         	 String name =  row.getCell(0)==null?"":row.getCell(0).toString();
	         	 String studentNum =  row.getCell(1)==null?"":row.getCell(1).toString();
	         	 String sexTemp =  row.getCell(2)==null?"":row.getCell(2).toString();
	         	//????????????
	         	 String mobileTemp =  row.getCell(3)==null?"":row.getCell(3).toString();
	         	 System.out.println("mobileTemp:"+mobileTemp);
	         	//????????????
	         	 String isBoardedTemp =  row.getCell(4)==null?"???":row.getCell(4).toString();
	         	 
	         	 if((name == null||"".equals(name))
	         			 &&(studentNum == null||"".equals(studentNum))
	         			&&(sexTemp == null||"".equals(sexTemp))
	         			
	         			 ){//???????????????????????????
	         		 continue;
	         	 }
	         	 String isSuccess = "";
	         	 String isState = "";
	        	 if(cell1!=null){
	        		studentCondition.setSchoolId(userInfo.getSchoolId());
	        		studentCondition.setStudentNumber(studentNum);
	        		List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, null, null);
	        		if(studentList.isEmpty()){
	        			isSuccess="??????";
	        			isState = "1";
	        		}else{
	        			isSuccess="??????????????????";
	        			isState = "2";
	        		}
	        	}else{
	        		isSuccess="?????????????????????";
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
			log.info("????????????????????????????????????");
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
	 * ??????????????????????????????
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
			 upLoadInformationCondition.setUserType("1");//1:?????? 2?????????
			 upLoadInformationCondition.setState("1");//1:??????
			 List<UpLoadInformation> upLoadInformationListSuccess =  this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
			 
			 upLoadInformationCondition.setState("2");//2:??????
			 List<UpLoadInformation> upLoadInformationListFail = this.upLoadInformationService.findUpLoadInformationByCondition(upLoadInformationCondition, null, null);
			// System.out.println("upLoadInformationListSuccess:"+upLoadInformationListSuccess.size()+"=upLoadInformationListFail="+upLoadInformationListFail.size());
			 map.put("upLoadInformationListSuccess", upLoadInformationListSuccess);
			 map.put("upLoadInformationListFail", upLoadInformationListFail);
		 }catch(Exception e){
			// e.printStackTrace();
			 log.info("??????????????????????????????...");
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
			 upLoadInformationCondition.setUserType("1");//1:?????? 2?????????
			 upLoadInformationCondition.setState("1");//1:??????
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
	        	//?????????????????????????????????????????????state????????????3
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
	        // ???????????????webbook???????????????sheet,??????Excel????????????sheet  
	        HSSFSheet sheet_ = wb.createSheet("???????????????");  
	        // ???????????????sheet??????????????????0???,???????????????poi???Excel????????????????????????short  
	        HSSFRow row = sheet_.createRow((int) 0);  
	        // ???????????????????????????????????????????????? ??????????????????  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ????????????????????????  
	  
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("??????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("?????????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("??????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("????????????");
	        cell.setCellStyle(style);
	        for (int i=0;i<errorMeaageVoList.size();i++){  
	        	ErroMessageVo emv = errorMeaageVoList.get(i);
	            row = sheet_.createRow((int) i + 1);  
	            // ??????????????????????????????????????????  
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
            // path????????????????????????????????????  
            File file = new File(path);  
            // ??????????????????  
            String filename = file.getName();  
            // ??????????????????????????????  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // ??????response  
            response.reset();  
            // ??????response???Header  
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
	        // ???????????????webbook???????????????sheet,??????Excel????????????sheet  
	        HSSFSheet sheet_ = wb.createSheet("???????????????");  
	        // ???????????????sheet??????????????????0???,???????????????poi???Excel????????????????????????short  
	        HSSFRow row = sheet_.createRow((int) 0);  
	        // ???????????????????????????????????????????????? ??????????????????  
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ????????????????????????  
	  
	        HSSFCell cell = row.createCell((short) 0);  
	        cell.setCellValue("??????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 1);  
	        cell.setCellValue("?????????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 2);  
	        cell.setCellValue("?????????");  
	        cell.setCellStyle(style);  
	        cell = row.createCell((short) 3);  
	        cell.setCellValue("????????????");
	        cell.setCellStyle(style);
	        for (int i=0;i<errorMeaageVoList.size();i++){  
	        	ErroMessageVo emv = errorMeaageVoList.get(i);
	            row = sheet_.createRow((int) i + 1);  
	            // ??????????????????????????????????????????  
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
            // path?????????????????????????????????
            File file = new File(path);
            // ??????????????????  
            String filename = file.getName();
            // ??????????????????????????????  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));  
            byte[] buffer = new byte[fis.available()];  
            fis.read(buffer);  
            fis.close();  
            // ??????response  
            response.reset();  
            // ??????response???Header  
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
		         	
		         	 HSSFCell cell1 = row.getCell(1);//?????????
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
		        			erroMessageVo = getErrorMessage(i,name,sexTemp,studentNum,"??????????????????");
		        		}
		        	}else{
		        		flag = false;
		        		erroMessageVo = getErrorMessage(i,name,sexTemp,studentNum,"?????????????????????");
		        	}
		        	//????????????
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
		         	
		         	 HSSFCell cell2 = row.getCell(2);//?????????
		         	 String name =  row.getCell(0)==null?"":row.getCell(0).toString();
		         	 String englistName =  row.getCell(1)==null?"":row.getCell(1).toString();
		         	 String username =  row.getCell(2)==null?"":row.getCell(2).toString();
		        	 if(cell2!=null){
		        		User user =this.userService.findUserByUsername(username);
		        		if(user==null){
		        			flag = true;
		        		}else{
		        			flag = false;
		        			erroMessageVo = getErrorMessage(i,name,englistName,username,"?????????????????????");
		        		}
		        	}else{
		        		flag = false;
		        		erroMessageVo = getErrorMessage(i,name,englistName,username,"?????????????????????");
		        	}
		        	
		        	//????????????
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
			
			HSSFCell cell1 = row.getCell(1);//?????????
	        udv.setStudentNum(cell1==null?"":cell1.toString());
	        
		    HSSFCell cell2 = row.getCell(2);//??????
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
          HSSFCell cell2 = row.getCell(2);//?????????
          udv.setUsername(cell2==null?"":cell2.toString());
          HSSFCell cell3 = row.getCell(3);//??????
          udv.setNation(cell3==null?"":getNationValue(cell3.toString()));
          HSSFCell cell4 = row.getCell(4);//????????????
          udv.setStudentType(cell4==null?"":getStudentTypeValue(cell4.toString()));
          HSSFCell cell5 = row.getCell(5);//?????????
          udv.setStudentNum(cell5==null?"":cell5.toString());
          HSSFCell cell6 = row.getCell(6);//??????
          udv.setPosition(cell6==null?"":cell6.toString());
          HSSFCell cell7 = row.getCell(7);//????????????
         
		  udv.setEnterDate(cell7==null? new Date():getDate(cell7.toString()));
          HSSFCell cell8 = row.getCell(8);//????????????
          udv.setEndDate(cell8==null?new Date():getDate(cell8.toString()));
         
         
          HSSFCell cell10 = row.getCell(10);//????????????
          udv.setHealthStatus(cell10==null?"":getHealthState(cell10.toString()));
          
          HSSFCell cell11 = row.getCell(11);//???????????????
          udv.setAbroadCode(cell11==null?"":cell11.toString());
          HSSFCell cell12 = row.getCell(12);//??????
          udv.setBloodType(cell12==null?"":getBloodTypeValue(cell12.toString()));
          HSSFCell cell13 = row.getCell(13);//????????????
          udv.setState(cell13==null?"":getStateValue(cell13.toString()));
          HSSFCell cell14 = row.getCell(14);//??????
          udv.setSex(cell14==null?"":getSexValue(cell14.toString().trim()));
          HSSFCell cell15 = row.getCell(15);//????????????
         
          udv.setBirthDate(cell15==null?new Date():getDate(cell15.toString()));
          HSSFCell cell16 = row.getCell(16);//????????????
          udv.setCertificateType(cell16==null?"1":getCertificateTypeValue(cell16.toString().trim()));
          HSSFCell cell17 = row.getCell(17);//????????????
          udv.setCertificateNum(cell17==null?"":cell17.toString());
          HSSFCell cell18 = row.getCell(18);//??????
          udv.setNationality(cell18==null?"":getNationlityTypeValue(cell18.toString().trim()));
          HSSFCell cell19 = row.getCell(19);//??????
          udv.setNativePlace(cell19==null?"":cell19.toString());
          HSSFCell cell20 = row.getCell(20);//?????????
          udv.setBirthPlace(cell20==null?"":cell20.toString());
          HSSFCell cell21 = row.getCell(21);//????????????
          udv.setRegister(cell21==null?"":getRegist(cell21.toString()));
          HSSFCell cell22 = row.getCell(22);//???????????????
          udv.setRegisterPlace(cell22==null?"":cell22.toString());
          HSSFCell cell23 = row.getCell(23);//??????
          udv.setProvince(cell23==null?"":cell23.toString());
          HSSFCell cell24 = row.getCell(24);//??????
          udv.setCity(cell24==null?"":cell24.toString());
          HSSFCell cell25 = row.getCell(25);//??????
          udv.setStreet(cell25==null?"":cell25.toString());
          HSSFCell cell26 = row.getCell(26);//?????????
          udv.setNowAddress(cell26==null?"":cell26.toString());
          HSSFCell cell27 = row.getCell(27);//????????????
          udv.setLiveAddress(cell27==null?"":cell27.toString());
          HSSFCell cell28 = row.getCell(28);//??????????????????
          udv.setIsFloatingPopulation(cell28==null?"":getFolatPeople(cell28.toString()));
          HSSFCell cell29 = row.getCell(29);//??????
          udv.setSpecialty(cell29==null?"":cell29.toString());
          HSSFCell cell30 = row.getCell(30);//????????????
          udv.setPolitical(cell30==null?"":getPoliticalValue(cell30.toString()));
          
          HSSFCell cell31 = row.getCell(31);//????????????
          udv.setReligiousBelief(cell31==null?"":getReligiousBelief(cell31.toString()));
          HSSFCell cell32 = row.getCell(32);//???????????????
          udv.setIsOnlyChild(cell32==null?false:getOnlyChildValue(cell32.toString()));
          HSSFCell cell33 = row.getCell(33);//??????
          udv.setTelephone(cell33==null?"":cell33.toString());
          HSSFCell cell34 = row.getCell(34);//??????
          udv.setCellPhone(cell34==null?"":cell34.toString());
          HSSFCell cell35 = row.getCell(35);//??????
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
	
	//???????????????
	public String getNationValue(String nation){
		switch(nation){
			case "??????": return "01";
			case "?????????": return "02";
			case "??????": return "03";
			case "??????": return "04";
			case "????????????": return "05";
			case "??????": return "06";
			case "??????": return "07";
			case "??????": return "08";
			case "?????????": return "09";
			case "?????????": return "10";
			case "??????": return "11";
			case "??????": return "12";
			case "??????": return "13";
			case "??????":return "14";
			case "?????????":return "15";
			case "?????????":return "16";
			case "????????????":return "17";
			case "??????":return "18";
			case "??????":return "19";
			case "?????????":return "20";
			case "??????":return "21";
			case "??????":return "22";
			case "?????????":return "23";
			case "?????????":return "24";
			case "??????":return "25";
			case "?????????":return "26";
			case "?????????":return "27";
			case "?????????":return "28";
			case "???????????????":return "29";
			case "??????":return "30";
			case "????????????":return "31";
			case "?????????":return "32";
			case "??????":return "33";
			case "?????????":return "34";
			case "?????????":return "35";
			case "?????????":return "36";
			case "?????????":return "37";
			case "?????????":return "38";
			case "?????????":return "39";
			case "?????????":return "40";
			case "????????????":return "41";
			case "??????":return "42";
			case "???????????????":return "43";
			case "????????????":return "44";
			case "????????????":return "45";
			case "?????????":return "46";
			case "?????????":return "47";
			case "?????????":return "48";
			case "??????":return "49";
			case "????????????":return "50";
			case "?????????":return "51";
			case "?????????":return "52";
			case "?????????":return "53";
			case "?????????":return "54";
			case "?????????":return "55";
			case "????????????":return "56";
			case "??????":return "57";
			case "???????????????????????????":return "58";
		}
		return "01";
	}
	//????????????
	public String getStudentTypeValue(String studentType){
		switch(studentType){
			case "??????": return "11";
			case "?????????": return "21";
			case "?????????": return "31";
			case "?????????": return "32";
			case "????????????": return "33";
			case "????????????": return "34";
			case "?????????": return "41";
			case "?????????": return "42";
			case "?????????": return "43";
			case "????????????": return "44";
			case "??????????????????": return "51";
			case "??????/??????/??????????????????": return "91";
			case "??????????????????": return "92";
			case "?????????????????????": return "93";
	    }
	  return "11";
	}
	
	public String getHealthState(String healthState){
		switch(healthState){
			case "???????????????": return "1";
			case "???????????????": return "2";
			case "????????????": return "3";
			case "??????": return "6";
		}
		return "1";
	}
	
	//??????
	public String getBloodTypeValue(String bloodType){
		switch(bloodType){
			case "A": return "0";
			case "B": return "1";
			case "O": return "2";
			case "AB": return "3";
		}
		return "0";
	}
	//????????????
	public String getStateValue(String state){
		switch(state){
			case "??????": return "01";
			case "??????": return "02";
			case "??????": return "03";
			case "??????": return "04";
			case "??????": return "05";
			case "??????": return "06";
			case "??????": return "07";
			case "??????": return "08";
			case "??????": return "09";
			case "??????": return "10";
			case "??????": return "11";
			case "??????????????????": return "12";
			case "????????????": return "13";
			case "??????": return "14";
			case "????????????": return "15";
			case "??????": return "99";
		}
		return "99";
	}
	//??????
	public String getSexValue(String sex){
		switch(sex){
			case "??????": return "0";
			case "???": return "1";
			case "???": return "2";
			case "?????????": return "9";
		}
		return "9";
	}
	
	public String getSexName(String val){
		switch(val){
		case "0": return "val";
		case "1": return "???";
		case "2": return "???";
		case "9": return "?????????";
	}
	return "?????????";
	}
	
	//????????????
	public String getCertificateTypeValue(String certificateType){
		switch(certificateType){
			case "?????????": return "0";
			case "?????????": return "1";
		}
		return "9";
	}
	
	//??????
	public String getNationlityTypeValue(String nationlity){
		switch(nationlity){
			case "??????": return "156";
			case "??????": return "0";
		}
		return "156";
	}
	//????????????
	public String getRegist(String regist){
		switch(regist){
			case "??????????????????": return "0";
			case "?????????????????????": return "1";
			case "??????????????????": return "2";
			case "?????????????????????": return "3";
			case "??????????????????": return "4";
			case "??????????????????": return "5";
			case "????????????": return "6";
			case "????????????": return "7";
			case "????????????": return "8";
		}
		return "8";
	}
	//?????????????????????
	public String getFolatPeople(String floatPeople){
		switch(floatPeople){
			case "???": return "1";
			case "???": return "0";
		}
		return "1";
	}
	//????????????
	public String getPoliticalValue(String political){
		switch(political){
			case "?????????????????????": return "01";
			case "???????????????????????????": return "02";
			case "?????????????????????????????????": return "03";
			case "????????????????????????????????????": return "04";
			case "????????????????????????": return "05";
			case "???????????????????????????": return "06";
			case "???????????????????????????": return "07";
			case "???????????????????????????": return "08";
			case "?????????????????????": return "09";
			case "??????????????????": return "10";
			case "??????????????????????????????": return "11";
			case "?????????????????????": return "12";
			case "??????": return "13";
		}
		return "13";
	}
	
	//????????????
	public String getReligiousBelief(String religiousBelief){
		switch(religiousBelief){
			case "???????????????": return "00";
			case "??????": return "10";
			case "?????????": return "20";
			case "??????": return "30";
			case "?????????": return "40";
			case "?????????": return "50";
			case "?????????": return "60";
			case "????????????": return "70";
			case "??????": return "99";
		}
		return "99";
	}
	//??????????????????
	public boolean getOnlyChildValue(String onlyChild){
		switch(onlyChild){
		case "???": return true;
		case "???": return false;
	}
	return true;
	}
	
	public String getValue(String str){
		String strValue="";
		if(str=="???" || "???".equals(str)){
			strValue="1";
		}else if(str=="???" || "???".equals(str)){
			strValue="0";
		}else{
			strValue="1";
		}
		return strValue;
	}
	
	/**
	 * ??????????????????
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
			log.info("??????????????????????????????...");
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * ??????????????????
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
			log.info("??????????????????????????????...");
			e.printStackTrace();
		}
		return mav;
	}
	
	/**
	 * ??????????????????
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
			log.info("??????????????????????????????...");
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * @function ????????????ID????????????????????????????????????????????????????????????????????????????????????
	 * 			  ??????????????????????????????????????????
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
//	 * ??????????????????
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
//			//????????????MimeType??? application/json
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
//			//????????????
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
