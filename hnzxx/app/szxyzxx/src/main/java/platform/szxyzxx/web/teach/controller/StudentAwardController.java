package platform.szxyzxx.web.teach.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentAward;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import platform.education.generalTeachingAffair.vo.StudentAwardVo;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;




@Controller
@RequestMapping("/teach/studentAward")
public class StudentAwardController extends BaseController{ 
	
	private final static String viewBasePath = "/teach/studentAward";
	private static final Logger log = LoggerFactory.getLogger(StudentAwardController.class);

	@RequestMapping(value = "/studentAwardList")
	public ModelAndView getStudentAwardList(
			@CurrentUser UserInfo user,
			@RequestParam(value = "dm", required = false) String dm,
			@RequestParam(value = "sub", required = false) String sub,
			@ModelAttribute("condition") StudentAwardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order, Model model) {
		String viewPath = null;
		ModelAndView mov = new ModelAndView();
		order.setProperty("create_date");
		order.setAscending(false);
		condition.setSchoolId(user.getSchoolId());//添加学校查询条件
		List<StudentAward> studentAwardList = this.studentAwardService.findStudentAwardByCondition(condition, page, order);
		List<StudentAwardVo> studentAwardVoList = this.awardToAwardVo(studentAwardList,user);
		if ("list".equals(sub)) {
			viewPath = structurePath("/list");
		} else {
			viewPath = structurePath("/studentAwardList");
		}
		mov.addObject("StudentAwardList", studentAwardVoList);
		mov.setViewName(viewPath);
		return mov;
	}
	
	@RequestMapping(value = "/downLoadExcel")
	@ResponseBody
	public void downLoadExcel(
			@CurrentUser UserInfo user,
			@RequestParam(value = "schoolYear", required = false) String schoolYear,
			@RequestParam(value = "gradeId", required = false) Integer gradeId,
			@RequestParam(value = "teamId", required = false) Integer teamId,
			@RequestParam(value = "studentId", required = false) Integer studentId
			,HttpServletResponse response,
			HttpServletRequest request){
		
		    StudentAwardCondition condition = new StudentAwardCondition();
		    if(schoolYear != null&&!"".equals(schoolYear.trim())){
		    	condition.setSchoolYear(schoolYear);
		    }else{
		    	condition.setSchoolYear(null);
		    }
			condition.setGradeId(gradeId);
			condition.setTeamId(teamId);
			condition.setStudentId(studentId);
			condition.setSchoolId(user.getSchoolId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String time = sdf.format(new Date());
			String filename = "学生奖励信息"+time+".xls";
			condition.setSchoolId(user.getSchoolId());//添加学校查询条件
			List<StudentAward> studentAwardList = this.studentAwardService.findStudentAwardByCondition(condition, null, null);
			List<StudentAwardVo> studentAwardVoList = this.awardToAwardVo(studentAwardList,user);
			
		
			List<Object> list = new ArrayList<Object>();
			for (StudentAwardVo vo : studentAwardVoList) {
				if(vo.getId()>0){
					list.add(vo);
				}else{
					//去除错误的
				}
				
			}
			
			ParseConfig config = SzxyExcelTookit.getConfig("studentAwardVo");
			try {
				SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView view(
			@CurrentUser UserInfo user,
			@RequestParam(value = "studentId", required = true) Integer studentId,
			Model model) {
		String viewPath = null;
		StudentAwardCondition condition = new StudentAwardCondition();
		condition.setStudentId(studentId);
		condition.setIsDelete(false);
		List<StudentAward> studentAwardList = this.studentAwardService.findStudentAwardByCondition(condition);
		List<StudentAwardVo> studentAwardVoList = this.awardToAwardVo(studentAwardList, user);
		model.addAttribute("studentAwardVoList", studentAwardVoList);
		viewPath = structurePath("/viewList");
		return new ModelAndView(viewPath, model.asMap());
	}
	
	@RequestMapping(value = "/list/json", method = RequestMethod.GET)
	@ResponseBody
	public List<StudentAward> jsonList(
			@CurrentUser UserInfo user, 
			@ModelAttribute("condition") StudentAwardCondition condition,
			@ModelAttribute("page") Page page,
			@ModelAttribute("order") Order order,
			@RequestParam(value = "usePage", required = false) boolean usePage) {
		//conditionFilter(user, condition);
		page = usePage ? page : null;
		return this.studentAwardService.findStudentAwardByCondition(condition, page, order);
	}
	
	@RequestMapping(value = "/addStudentAwardPage")
	public ModelAndView addStudentAwardPage() {
		return new ModelAndView(structurePath("/addStudentAward"));
	}

	@RequestMapping(value = "/addStudentAward", method = RequestMethod.POST)
	@ResponseBody
	public ResponseInfomation addStudentAward(StudentAward studentAward, @CurrentUser UserInfo user) {
		
		StudentAward studentaward = new StudentAward();
		studentaward = this.studentAwardService.add(studentAward);
		ResponseInfomation responseInfomation = studentaward != null ? new ResponseInfomation(studentaward.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
		
		return responseInfomation;
	}

	@RequestMapping(value = "/modifyStudentAwardPage", method = RequestMethod.GET)
	public ModelAndView modifyStudentAwardPage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id) {
		ModelAndView mov = new ModelAndView();
		
		StudentAward studentAward = this.studentAwardService.findStudentAwardById(id);
		List<StudentAward> studentAwardList = new ArrayList<StudentAward>();
		studentAwardList.add(studentAward);
		List<StudentAwardVo> studentAwardVoList = this.awardToAwardVo(studentAwardList,user);
		StudentAwardVo studentAwardVo = studentAwardVoList.get(0);
		mov.addObject("studentaward", studentAwardVo);
		mov.setViewName(structurePath("/input"));
		
		return mov;
	}
	
	@RequestMapping(value = "/detailStudentAwardPage", method = RequestMethod.GET)
	public ModelAndView getStudentAwardPage(
			@CurrentUser UserInfo user,
			@RequestParam(value = "id", required = true) Integer id,
			Model model) {
		ModelAndView mov = new ModelAndView();
		StudentAward studentAward = this.studentAwardService.findStudentAwardById(id);
		List<StudentAward> studentAwardList = new ArrayList<StudentAward>();
		studentAwardList.add(studentAward);
		List<StudentAwardVo> studentAwardVoList = this.awardToAwardVo(studentAwardList,user);
		
		StudentAwardVo studentAwardVo = studentAwardVoList.get(0);
		mov.addObject("studentaward", studentAwardVo);
		mov.setViewName(structurePath("/detailStudentAward"));
		return mov;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteStudentAward(@PathVariable(value = "id") Integer id, StudentAward studentAward) {
		if (studentAward != null) {
			studentAward.setId(id);
		}
		try {
			this.studentAwardService.remove(studentAward);
		} catch (Exception e) {
			return ResponseInfomation.OPERATION_FAIL;
		}
		return ResponseInfomation.OPERATION_SUC;
	}

	@RequestMapping(value = "/updateStudentAward", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseInfomation updateStudentAward(StudentAward studentAward) {
		studentAward = this.studentAwardService.modify(studentAward);
		
		ResponseInfomation responseInfomation =  studentAward != null ? new ResponseInfomation(studentAward.getId(), ResponseInfomation.OPERATION_SUC)  : new ResponseInfomation();
		return responseInfomation;
	}
	
	/**
	 * 导入学生奖励信息页面
	 */
	@RequestMapping("/upLoadStudentAwardInfoPage")
	public ModelAndView upLoadStudentAwardInfoPage(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName(this.structurePath("/upLoadStudentAwardInfoPage"));
		return mav;
	}
	
	
	/**
	 * 导入学生奖励信息
	 * @throws IOException 
	 */
	@RequestMapping("/upLoadStudentAwardInfo")
	@ResponseBody
	public ResponseInfomation upLoadStudentAwardInfo(
			@CurrentUser UserInfo user,
			@RequestParam("fileUpload") MultipartFile fileUpload,
			@CurrentUser UserInfo userInfo,HttpServletResponse response){
		
		List<StudentAwardVo> errorStudentAwardVoList = new ArrayList<StudentAwardVo>();
		
		try {
			List<StudentAwardVo> studentAwardVoList =  excelToStudentAwardVo(fileUpload,errorStudentAwardVoList,userInfo);//从excel中取出值转换为 vo
			List<StudentAward> studentAwardList = filterStudentAwardVoList(studentAwardVoList,errorStudentAwardVoList);//对vo进行校验，处理，错误的单独处理，正确的返回list
			
			for (StudentAward studentAward : studentAwardList) {
				studentAward = this.studentAwardService.add(studentAward);//保存到数据库
				if(studentAward == null){
					List<StudentAward> studentAwardlist  = new ArrayList<StudentAward>();
					List<StudentAwardVo> AwardVoList = this.awardToAwardVo(studentAwardlist,user);
					StudentAwardVo AwardVo = AwardVoList.get(0);
					AwardVo.setErrorInfo("保存失败");
					errorStudentAwardVoList.add(AwardVo);
				}
			}
		} catch (Exception e) {
			List<StudentAward> studentAwardlist  = new ArrayList<StudentAward>();
			List<StudentAwardVo> AwardVoList = this.awardToAwardVo(studentAwardlist,user);
			StudentAwardVo AwardVo = AwardVoList.get(0);
			AwardVo.setErrorInfo("保存失败");
			errorStudentAwardVoList.add(AwardVo);
			
		}
		
		if(errorStudentAwardVoList.size()>0){
			studentAwardVoToExcel(errorStudentAwardVoList,response);
			
		}
		
		return new ResponseInfomation("", ResponseInfomation.OPERATION_SUC);
	}
	
	/**
	 * 根据解析出来的vo 生成对应的可以插入数据库的数据，错误的存放到错误列表
	 * @param studentAwardVoList 解析出来的vo列表
	 * @param errorStudentAwardVoList 错误的存放到错误列表
	 * @return
	 */
	private List<StudentAward> filterStudentAwardVoList(List<StudentAwardVo> studentAwardVoList,List<StudentAwardVo> errorStudentAwardVoList){
		List<StudentAward> studentAwardList = new ArrayList<StudentAward>();
		
		for (StudentAwardVo studentAwardvo : studentAwardVoList) {
			StudentAward studentAward = new StudentAward();
			//确定信息是否完整
			StudentAwardVo studentAwardVo = filterStudentAwardVo(studentAwardvo);
			if(studentAwardVo.getErrorInfo()==null|| "".equals(studentAwardVo.getErrorInfo())){
				
			}else{
				errorStudentAwardVoList.add(studentAwardVo);
				continue;
			}
			//确定学生的班级id
			int teamId = 0;
			int schoolId = studentAwardvo.getSchoolId();
			String schoolYear = studentAwardvo.getSchoolYear();
			GradeCondition gradeCondition = new GradeCondition();
			gradeCondition.setSchoolId(schoolId);
			gradeCondition.setSchoolYear(schoolYear);
			gradeCondition.setName(studentAwardvo.getGradeName());
			List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);
			Grade gra = new Grade();
			if(gradeList.size() != 1){
				studentAwardvo.setErrorInfo("信息有误：学年+年级");
				errorStudentAwardVoList.add(studentAwardvo);//学生信息有误
				continue;
			}else{
				gra = gradeList.get(0);
			}
			TeamCondition teamCondition = new TeamCondition();
			teamCondition.setSchoolId(schoolId);
			teamCondition.setSchoolYear(schoolYear);
			teamCondition.setGradeId(gra.getId());
			teamCondition.setName(studentAwardvo.getTeamName());
			this.teamService.findTeamByCondition(teamCondition, null, null);
		    //确定学生的id 重名需导入学号
			StudentCondition studentCondition = new StudentCondition();
			studentCondition.setTeamId(teamId);
			studentCondition.setName(studentAwardvo.getStudentName());
			studentCondition.setStudentNumber(studentAwardvo.getStudentNumber());
			Student stu = new Student();
			List<Student> stulist = studentService.findStudentUniqByCondition(studentCondition, null, null);
			//List<Student> stulist = this.studentService.findStudentByCondition(studentCondition, null, null);
			if(stulist.size() != 1){
				studentAwardvo.setErrorInfo("学生信息有误：学年+年级+班级+姓名+学号");
				errorStudentAwardVoList.add(studentAwardvo);//学生信息有误
				continue;
			}else{
				stu = stulist.get(0);
			}
			studentAward.setStudentId(stu.getId());
			//确定其他信息的正确度
			
			studentAwardList.add(studentAward);
		}
		
		
		return studentAwardList;
		
	}
	
	private List<StudentAwardVo> excelToStudentAwardVo(MultipartFile fileUpload,List<StudentAwardVo> errorStudentAwardVoList,UserInfo userInfo){
		List<StudentAwardVo> studentAwardVo = new ArrayList<StudentAwardVo>();
		int schoolId = userInfo.getSchoolId();
		//把文件流转换为对象
		 HSSFSheet sheet = null;
		try {
			InputStream is = fileUpload.getInputStream();
			POIFSFileSystem fs = new POIFSFileSystem(is);
	        HSSFWorkbook wb = new HSSFWorkbook(fs);
	        sheet = wb.getSheet(wb.getSheetName(0));
		} catch (IOException e) {
			log.error("学生奖励信息导入出错:"+e.getMessage());
			return null;
		}
		
	   //获取对象的每一行分别处理
		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
			StudentAwardVo awardVo = new StudentAwardVo();
			HSSFRow row = sheet.getRow(i);
			int j = 0;
			awardVo.setSchoolYear(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setGradeName(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setTeamName(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setNumInTeam(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setStudentName(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setAwardContent(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setAwardLevel(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setAwardRanking(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setAwardType(row.getCell(j)==null?"":row.getCell(j++).toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			try {
				awardVo.setAwardDay(sdf.parse(row.getCell(j)==null?"":row.getCell(j++).toString()));
			} catch (ParseException e) {
				log.error("日期转换格式出错:"+e.getMessage());
				awardVo.setErrorInfo("日期转换有问题");
				e.printStackTrace();
			}
			awardVo.setAwardUnit(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setRemark(row.getCell(j)==null?"":row.getCell(j++).toString());
			awardVo.setSchoolId(schoolId);
		}
		
		
		
		return studentAwardVo;
	}
	
	@SuppressWarnings("deprecation")
	private void studentAwardVoToExcel(List<StudentAwardVo> errorStudentAwardVoList,HttpServletResponse response){
		
		//创建一个表格对象
		HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet_ = wb.createSheet("错误信息表");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet_.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        
        String []sheetNames = {"学年","年级","班级","班内编号","姓名","奖励内容","奖励级别","奖励名次","奖励类型","奖励日期","奖励单位","备注","错误原因"};
        HSSFCell cell = null;
        for (int i = 0; i < sheetNames.length; i++) {
        	cell = row.createCell((short) i);//单元格位置
            cell.setCellValue(sheetNames[i]);//单元格内容
            cell.setCellStyle(style); //格式居中
		}
        
		//把具体的值写进表格对象中
        for (int i = 0; i < errorStudentAwardVoList.size(); i++) {
        	StudentAwardVo studentAwardVo = errorStudentAwardVoList.get(i);
        	row = sheet_.createRow((int) i + 1);
        	int j = 0;
        	row.createCell((short) j++).setCellValue(studentAwardVo.getSchoolYear());//学年
        	row.createCell((short) j++).setCellValue(studentAwardVo.getGradeName());//年级
        	row.createCell((short) j++).setCellValue(studentAwardVo.getTeamName());//班级
        	row.createCell((short) j++).setCellValue(studentAwardVo.getNumInTeam());//班内编号
        	row.createCell((short) j++).setCellValue(studentAwardVo.getStudentName());//姓名
        	row.createCell((short) j++).setCellValue(studentAwardVo.getAwardContent());//奖励内容
        	row.createCell((short) j++).setCellValue(studentAwardVo.getAwardLevel());//奖励级别
        	row.createCell((short) j++).setCellValue(studentAwardVo.getAwardRanking());//奖励名次
        	row.createCell((short) j++).setCellValue(studentAwardVo.getAwardType());//奖励类型
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	row.createCell((short) j++).setCellValue(sdf.format(studentAwardVo.getAwardDay()));//奖励日期
        	row.createCell((short) j++).setCellValue(studentAwardVo.getAwardUnit());//奖励单位
        	row.createCell((short) j++).setCellValue(studentAwardVo.getErrorInfo());//错误原因
        }
		
		
        try {
        	 //把对象输出成EXCEL文件
        	Date date = new Date();
        	String fileName = "学生奖励导入错误信息"+date.getTime();
        	String path = "E:/";
        	String intergerPath = path+fileName;//path是指欲下载的文件的路径。  
			FileOutputStream fout = new FileOutputStream(intergerPath);
			wb.write(fout);
			fout.close();
			//对错误文件进行下载// 以流的形式下载文件。
			File file = new File(intergerPath);  
			InputStream fis = new BufferedInputStream(new FileInputStream(path));  
			byte[] buffer = new byte[fis.available()];  
			fis.read(buffer);  
			fis.close();  
			
			response.reset();  // 清空response  
			response.addHeader("Content-Disposition", "attachment;filename="  // 设置response的Header
			        + new String(fileName.getBytes())); 
			response.addHeader("Content-Length", "" + file.length());  
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=gb2312");
			toClient.write(buffer);  
			toClient.flush();  
			toClient.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	/**
	 * 校验必输项
	 * @param studentAwardVo
	 * @return
	 */
	private StudentAwardVo filterStudentAwardVo(StudentAwardVo studentAwardVo){
		StringBuffer errorInfo = new StringBuffer();
		if(isNull(studentAwardVo.getSchoolYear())){
			errorInfo.append("学年不能为空,");
		}
		if(isNull(studentAwardVo.getTeamName())){
			errorInfo.append("班级不能为空,");	
		}
		if(isNull(studentAwardVo.getStudentName())){
			errorInfo.append("学生不能为空,");
		}
		if(isNull(studentAwardVo.getAwardContent())){
			errorInfo.append("奖励内容不能为空,");
		}
		if(isNull(studentAwardVo.getAwardLevel())){
			errorInfo.append("奖励级别不能为空,");
		}
		if(isNull(studentAwardVo.getAwardType())){
			errorInfo.append("奖励类型不能为空,");
		}
		
		if(isNull(studentAwardVo.getAwardUnit())){
			errorInfo.append("奖励单位不能为空,");
		}
		String error = errorInfo.toString();
		if(error.length()>0){
			studentAwardVo.setErrorInfo(error.substring(0,error.length()-1));
		}
		
		return studentAwardVo;
		
	}
	
	private boolean isNull(String str){
		boolean flag = true;
		if(str == null || "".equals(str)){
			flag = false;
		}
		return flag;
	}
	private String structurePath(String subPath) {
		return viewBasePath + subPath;
	}
	

	
	/**
	 * 把对应的实体类转换成对应的vo
	 * @param 
	 * @return
	 */
	private List<StudentAwardVo> awardToAwardVo(List<StudentAward> studentAwardlist,UserInfo user) {
		List<StudentAwardVo> studentAwardVoList = new ArrayList<StudentAwardVo>();
		Student student = new Student();
		Team team = new Team();
			for (StudentAward studentAward : studentAwardlist) {
				StudentAwardVo studentAwardVo = new StudentAwardVo();
				
				student = this.studentService.findStudentById(studentAward.getStudentId());//获取学生姓名
				if(student == null){
					student = new Student();
				}
				this.copyProperties( studentAwardVo,studentAward);
				studentAwardVo.setStudentName(student.getName());//学生姓名
				team = this.teamService.findTeamById(studentAward.getTeamId());//获取班级名称，年级名称，学校名称，学年
				if(team == null){
					team = new Team();
				}
				studentAwardVo.setSchoolYear(team.getSchoolYear());
				SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
				schoolYearCondition.setSchoolId(user.getSchoolId());
				schoolYearCondition.setYear(team.getSchoolYear());
				SchoolYear schoolyear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
				if(schoolyear == null){
					schoolyear = new SchoolYear();
				}
				studentAwardVo.setSchoolYearName(schoolyear.getName());
				
				studentAwardVo.setTeamName(team.getName());
				studentAwardVo.setSchoolId(team.getSchoolId());
				studentAwardVo.setGradeId(team.getGradeId());
				Grade grade = this.gradeService.findGradeById(team.getGradeId());//获取学年名称
				if(grade == null){
					grade = new Grade();
				}
				studentAwardVo.setGradeName(grade.getName());
				studentAwardVoList.add(studentAwardVo);
				studentAwardVo = null;
			}
		
		
		return studentAwardVoList;
	}

	private void copyProperties(StudentAwardVo studentAwardVo,StudentAward studentAward){

		studentAwardVo.setId(studentAward.getId());
		studentAwardVo.setStudentId(studentAward.getStudentId());
		studentAwardVo.setTeamId(studentAward.getTeamId());
		studentAwardVo.setAwardContent(studentAward.getAwardContent());
		studentAwardVo.setAwardDay(studentAward.getAwardDay());
		studentAwardVo.setAwardLevel(studentAward.getAwardLevel());
		studentAwardVo.setAwardRanking(studentAward.getAwardRanking());
		studentAwardVo.setAwardType(studentAward.getAwardType());
		studentAwardVo.setAwardUnit(studentAward.getAwardUnit());
		studentAwardVo.setNumInTeam(studentAward.getNumInTeam());
		studentAwardVo.setRemark(studentAward.getRemark());
		studentAwardVo.setIsDelete(studentAward.getIsDelete());
		studentAwardVo.setCreateDate(studentAward.getCreateDate());
		studentAwardVo.setModifyDate(studentAward.getModifyDate());

	}
	
//	public static void main(String[] args) {
//
//		//把文件流转换为对象
//		 HSSFSheet sheet = null;
//		try {
//			InputStream is =new  FileInputStream(new File("E:/studentaward_info.xlsx"));
//			POIFSFileSystem fs = new POIFSFileSystem(is);
//	        HSSFWorkbook wb = new HSSFWorkbook(fs);
//	       // XSSFWorkbook wb = new XSSFWorkbook(is);
//	       // sheet = wb.getSheet(wb.getSheetName(0));
//		} catch (IOException e) {
//			log.error("学生奖励信息导入出错:"+e.getMessage());
//			
//		}
//		
//	   //获取对象的每一行分别处理
//		for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {
//			StudentAwardVo awardVo = new StudentAwardVo();
//			HSSFRow row = sheet.getRow(i);
//			int j = 0;
//			awardVo.setSchoolYear(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setGradeName(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setTeamName(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setNumInTeam(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setStudentName(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setAwardContent(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setAwardLevel(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setAwardRanking(row.getCell(j)==null?"":row.getCell(j++).toString());
//			awardVo.setAwardType(row.getCell(j)==null?"":row.getCell(j++).toString());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			
//			try {
//				awardVo.setAwardDay(sdf.parse(row.getCell(j)==null?"":row.getCell(j++).toString()));
//			} catch (ParseException e) {
//				log.error("日期转换格式出错:"+e.getMessage());
//				awardVo.setErrorInfo("日期转换有问题");
//				e.printStackTrace();
//			}
//	}
//
//
//	}

}
