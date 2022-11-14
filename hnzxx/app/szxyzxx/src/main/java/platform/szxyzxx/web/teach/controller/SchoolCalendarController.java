package platform.szxyzxx.web.teach.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import platform.education.generalTeachingAffair.model.SchoolCalendar;
import platform.education.generalTeachingAffair.vo.SchoolCalendarCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;

/**
 *
 * @author 罗志明
 */
@Controller
@RequestMapping("/teach/calendar")
public class SchoolCalendarController extends BaseController {

	private static final Logger log = LoggerFactory
			.getLogger(SchoolCalendarController.class);

	private static final String DIR = "teach/schoolCalendar";

	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {
		SchoolCalendarCondition cc = new SchoolCalendarCondition();
		cc.setSchoolId(user.getSchoolId());
		List<SchoolCalendar> ccList = this.schoolCalendarService
				.findSchoolCalendarByCondition(cc, null,
						Order.desc("create_date"));
		request.setAttribute("calendarList", ccList);
		return DIR + "/index";
	}

    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(HttpServletRequest request, HttpServletResponse response, @CurrentUser UserInfo user) throws ParseException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String bds = request.getParameter("beginDate");
        String fds = request.getParameter("finishDate");
        String remark = request.getParameter("remark");
        SchoolCalendar sc;
        if (id != null && !"".equals(id)) {
            sc = this.schoolCalendarService.findSchoolCalendarById(Integer.parseInt(id));
            sc.setModifyDate(new Date());
        } else {
            sc = new SchoolCalendar();
            sc.setCreateDate(new Date());
            sc.setSchoolId(user.getSchoolId());
        }
        if (bds != null && !"".equals(bds)) {
            sc.setBeginDate((bds == null || "".equals(bds)) ? null : new SimpleDateFormat("yyyy-MM-dd").parse(bds));
        }
        if (fds != null && !"".equals(fds)) {
            sc.setFinishDate((fds == null || "".equals(fds)) ? null : new SimpleDateFormat("yyyy-MM-dd").parse(fds));
        }
        if (name != null && !"".equals(name)) {
            sc.setName(name);
        }
        if (remark != null && !"".equals(remark)) {
            sc.setRemark(remark);
        }
        
        //2016-5-24 在添加或者修改之前添加校验
        SchoolCalendarCondition schoolCalendarCondition = new SchoolCalendarCondition();
        schoolCalendarCondition.setSchoolId(user.getSchoolId());
        schoolCalendarCondition.setName(name);
        List<SchoolCalendar> list = schoolCalendarService.findSchoolCalendarByCondition(schoolCalendarCondition);
        boolean notCanModify = false;
        boolean notCanAdd = false;
        if(list != null && list.size() > 0){
        	notCanAdd = true;
    		for(SchoolCalendar schoolCalendar : list){
    			if(id != null && !"".equals(id)){
    				if(schoolCalendar.getId().equals(id)){
    					notCanModify = true;
    				}
    			}
    		}
    	}
        
        if (id != null && !"".equals(id) && !notCanModify) {
            this.schoolCalendarService.modify(sc);
            return "success";
        } else if(id == null && !notCanAdd) {
            this.schoolCalendarService.add(sc);
            return "success";
        }
        return "error";
    }
	@RequestMapping(value = "/loadCalendar")
	public String loadCalendar(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {
		String id = request.getParameter("id");
		SchoolCalendar cc = this.schoolCalendarService
				.findSchoolCalendarById(Integer.parseInt(id));
		String remark = cc.getRemark();
		if (remark != null && !"".equals(remark)) {
			remark = remark.replaceAll("\r\n", "<br/>");
			remark = remark.replaceAll("\n", "<br/>");
			cc.setRemark(remark);
		}
		request.setAttribute("ca", cc);
		return DIR + "/loadCalendar";
	}

	@RequestMapping(value = "/downLoadCalendarInfo", method = RequestMethod.GET)
	@ResponseBody
	public void downLoadCalendarInfo(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user,
			SchoolCalendar sc,
			@RequestParam(value = "id", required = true) Integer id
			) throws Exception {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat sdfc = new SimpleDateFormat("yyyy-MM-dd");
		SchoolCalendar scs = this.schoolCalendarService
				.findSchoolCalendarById(id);

		if(scs!=null){
			
		
		// 开始时间
		String beginDate = sdf.format(scs.getBeginDate());
		// 结束时间
		String finishDate = sdf.format(scs.getFinishDate());

		// 开始 年、月
		String bymd[] = beginDate.split("-");
		// 结束 年、月
		String fymd[] = finishDate.split("-");

		// 开始 年
		int beginYear = Integer.parseInt(bymd[0]);
		// 开始月
		int beginMonth = Integer.parseInt(bymd[1]);

		// 结束 年
		int finishYear = Integer.parseInt(fymd[0]);
		// 结束月
		int finishMonth = Integer.parseInt(fymd[1]);

		try {
			
			
			response.setCharacterEncoding("UTF-8");
			
			String name = scs.getName()+".xls";
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? name.getBytes() : name.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
			name = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
			
			// 创建HSSFWorkbook对象(excel的文档对象)
			HSSFWorkbook wb = new HSSFWorkbook();
			// 建立新的sheet对象（excel的表单）
			HSSFSheet sheet = wb.createSheet("校历表");
			
			// 设置excel每列宽度
			sheet.setColumnWidth(0, 1800);
			sheet.setColumnWidth(1, 1100);
		 	sheet.setColumnWidth(2, 1500);
			sheet.setColumnWidth(3, 1500);
			sheet.setColumnWidth(4, 1500);
			sheet.setColumnWidth(5, 1500);
			sheet.setColumnWidth(6, 1500);
			sheet.setColumnWidth(7, 1500);
			sheet.setColumnWidth(8, 1500);
			
			//sheet样式
			HSSFCellStyle sheetStyle = wb.createCellStyle(); 
			sheetStyle.setFillForegroundColor(HSSFColor.WHITE.index);
			sheetStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			//设置列的样式
			for(int i=0;i<=9;i++){
				sheet.setDefaultColumnStyle((short) i, sheetStyle);
			}

			// 设置字体
			HSSFFont headfont = wb.createFont();
			
			headfont.setFontName("黑体");   
			headfont.setFontHeightInPoints((short) 16);// 字体大小  
			headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			//另一个样式
			HSSFCellStyle headstyle = wb.createCellStyle();   
			headstyle.setFont(headfont);   
			headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
			headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
			headstyle.setLocked(true);   
			headstyle.setWrapText(true);// 自动换行   
			
			//另一个字体样式
			HSSFFont columnHeadFont = wb.createFont();   
			columnHeadFont.setFontName("宋体");   
			columnHeadFont.setFontHeightInPoints((short) 11);   
			columnHeadFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			
			// 列头的样式   
			HSSFCellStyle columnHeadStyle = wb.createCellStyle();
			columnHeadStyle.setFont(columnHeadFont);
			columnHeadStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
			columnHeadStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
			columnHeadStyle.setLocked(true);
			columnHeadStyle.setWrapText(true);
			columnHeadStyle.setTopBorderColor(HSSFColor.BLACK.index); //上边框的颜色
			columnHeadStyle.setBorderTop((short)1); //边框的大小
			columnHeadStyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
			columnHeadStyle.setBorderLeft((short) 1);// 边框的大小
			columnHeadStyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
			columnHeadStyle.setBorderRight((short) 1);// 边框的大小
			columnHeadStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
			columnHeadStyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色

			HSSFFont font = wb.createFont();
			font.setFontName("宋体");
			font.setFontHeightInPoints((short) 12);
			// 普通单元格样式
			HSSFFont styleFont = wb.createFont();
			styleFont.setFontName("宋体");
			styleFont.setFontHeightInPoints((short) 12);
			styleFont.setColor(HSSFColor.WHITE.index);
			
			HSSFCellStyle style = wb.createCellStyle();
			style.setFont(styleFont);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
			style.setLocked(true);
			style.setWrapText(true);
			style.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
			style.setBorderLeft((short) 1);// 边框的大小
			style.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
			style.setBorderRight((short) 1);// 边框的大小
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
			style.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
			style.setFillForegroundColor(HSSFColor.RED.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			// 另一个样式
			HSSFCellStyle centerstyle = wb.createCellStyle();
			centerstyle.setFont(font);
			centerstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
			centerstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
			centerstyle.setLocked(true);
			centerstyle.setWrapText(true);
			centerstyle.setLeftBorderColor(HSSFColor.BLACK.index);// 左边框的颜色
			centerstyle.setBorderLeft((short) 1);// 边框的大小
			centerstyle.setRightBorderColor(HSSFColor.BLACK.index);// 右边框的颜色
			centerstyle.setBorderRight((short) 1);// 边框的大小
			centerstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 设置单元格的边框为粗体
			centerstyle.setBottomBorderColor(HSSFColor.BLACK.index); // 设置单元格的边框颜色
			//另一个样式
			HSSFCellStyle secondstyle = wb.createCellStyle();   
			secondstyle.setFont(font);   
			secondstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
			secondstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中   
			secondstyle.setLocked(true);   
			secondstyle.setWrapText(true);// 自动换行  
			
			// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow row_0 = sheet.createRow(0);
			//设置行高
			row_0.setHeight((short) 1000);
			// 创建第一列  创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			HSSFCell cell = row_0.createCell(0);
			// 设置单元格内容
			cell.setCellValue(new HSSFRichTextString(scs.getName()));
			cell.setCellStyle(headstyle);

			  /**  
			    * 合并单元格  
			    *    第一个参数：第一个单元格的行数（从0开始）  
			    *    第二个参数：第二个单元格的行数（从0开始）  
			    *    第三个参数：第一个单元格的列数（从0开始）  
			    *    第四个参数：第二个单元格的列数（从0开始）  
			    */  
			CellRangeAddress range = new CellRangeAddress(0, 0, 0, 8);   
			   sheet.addMergedRegion(range);   
			// 创建第二行
			HSSFRow row_1 = sheet.createRow(1);
			row_1.setHeight((short) 600);
			cell = row_1.createCell(0);
			cell.setCellValue(new HSSFRichTextString("本校历时间段:"+sdfc.format(scs.getBeginDate())+"至"+sdfc.format(scs.getFinishDate())));
			cell.setCellStyle(secondstyle);
			// 合并单元格
			CellRangeAddress range_1 = new CellRangeAddress(1, 1, 0, 8);
			sheet.addMergedRegion(range_1);   
			// 在sheet里创建第三行
			HSSFRow row_2 = sheet.createRow(2);
			row_2.setHeight((short) 600);
			// 创建单元格并设置单元格内容
			cell = row_2.createCell(0); 
			cell.setCellValue(new HSSFRichTextString("周序"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(1); 
			cell.setCellValue(new HSSFRichTextString("月份"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(2); 
			cell.setCellValue(new HSSFRichTextString("一"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(3); 
			cell.setCellValue(new HSSFRichTextString("二"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(4); 
			cell.setCellValue(new HSSFRichTextString("三"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(5); 
			cell.setCellValue(new HSSFRichTextString("四"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(6); 
			cell.setCellValue(new HSSFRichTextString("五"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(7); 
			cell.setCellValue(new HSSFRichTextString("六"));
			cell.setCellStyle(columnHeadStyle); 
			cell = row_2.createCell(8); 
			cell.setCellValue(new HSSFRichTextString("七"));
			cell.setCellStyle(columnHeadStyle);
			/*
			 * 1、先判断所要导出的校历中的为平/闰年
			 * 2、判断所要导出的校历中所对应的年份中各个月有多少天
			 * 3、判断每个月开始时是星期几
			 * 4、每个月的总天数/7
			 * 5、（每个月的总天数+每个月开始时是星期几）%7 如果==0则行数为（每个月的总天数/7），如果为1~7之间则行数为（每个月的总天数/7）+1
			 * 6、合并对应的行数并把月份填上
			 * 7、周序往下加
			 */
			//导出的校历中的开始的周序为1
			int weekOrder = 1;
			//导出的校历中开始的行数为第四行 
			int initCell = 3;
			for(int year=beginYear;year<=finishYear;year++){
				//每个月开始时是星期几
				int monthBeginWeek = 0;
//				//每个月的总天数
				int monthAllDay = 0;
//				//导出的校历中每个月所占的行数
				int monthTotalCell = 0;
				if(year==beginYear){
					if(beginYear==finishYear){
						for(int month=beginMonth;month<=finishMonth;month++){
							monthAllDay = monthDay(year, month);
							monthBeginWeek = firstMonth(year, month)==0?(firstMonth(year, month)+8):(monthBeginWeek = firstMonth(year, month)+1);
							monthTotalCell = returnMonthTotalCell( monthAllDay, monthBeginWeek);
							int calendar = 1;
							for(int i=1;i<=monthTotalCell;i++){
								HSSFRow row_initCell = sheet.createRow(initCell);
								addCell(row_initCell,month, sheet, initCell, cell, weekOrder, centerstyle);
								calendar = creatCell(year, month, monthAllDay, monthBeginWeek, monthTotalCell, sheet, initCell, cell, weekOrder, style, centerstyle, row_initCell, calendar);
								initCell++;
								weekOrder++;
								monthBeginWeek=2;
							}
					   
						// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
						sheet.addMergedRegion(new CellRangeAddress((initCell-monthTotalCell), initCell-1, 1, 1));
						}
						
					}else{
						for(int month=beginMonth;month<=12;month++){
							monthAllDay = monthDay(year, month);
							monthBeginWeek = firstMonth(year, month)==0?(firstMonth(year, month)+8):(monthBeginWeek = firstMonth(year, month)+1);
							monthTotalCell = returnMonthTotalCell( monthAllDay, monthBeginWeek);
							int calendar = 1;
							for(int i=1;i<=monthTotalCell;i++){
								HSSFRow row_initCell = sheet.createRow(initCell);
								addCell(row_initCell,month, sheet, initCell, cell, weekOrder, centerstyle);
								calendar = creatCell(year, month, monthAllDay, monthBeginWeek, monthTotalCell, sheet, initCell, cell, weekOrder, style, centerstyle, row_initCell, calendar);
								initCell++;
								weekOrder++;
								monthBeginWeek=2;
							}
							// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
							sheet.addMergedRegion(new CellRangeAddress((initCell-monthTotalCell), initCell-1, 1, 1));
						}
						
					}
					
				}else if(year==finishYear){
					if(finishMonth==12){
						for(int month=1;month<=12;month++){
							
							monthAllDay = monthDay(year, month);
							monthBeginWeek = firstMonth(year, month)==0?(firstMonth(year, month)+8):(monthBeginWeek = firstMonth(year, month)+1);
							monthTotalCell = returnMonthTotalCell( monthAllDay, monthBeginWeek);
							int calendar = 1;
							for(int i=1;i<=monthTotalCell;i++){
								HSSFRow row_initCell = sheet.createRow(initCell);
								addCell(row_initCell,month, sheet, initCell, cell, weekOrder, centerstyle);
								calendar = creatCell(year, month, monthAllDay, monthBeginWeek, monthTotalCell, sheet, initCell, cell, weekOrder, style, centerstyle, row_initCell, calendar);
								
								initCell++;
								weekOrder++;
								monthBeginWeek=2;
							}
							// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
							sheet.addMergedRegion(new CellRangeAddress((initCell-monthTotalCell), initCell-1, 1, 1));
							
						
						}
					}else{
						for(int month=1;month<=finishMonth;month++){
							
							monthAllDay = monthDay(year, month);
							monthBeginWeek = firstMonth(year, month)==0?(firstMonth(year, month)+8):(monthBeginWeek = firstMonth(year, month)+1);
							monthTotalCell = returnMonthTotalCell( monthAllDay, monthBeginWeek);
							int calendar = 1;
							for(int i=1;i<=monthTotalCell;i++){
								HSSFRow row_initCell = sheet.createRow(initCell);
								addCell(row_initCell,month, sheet, initCell, cell, weekOrder, centerstyle);
								calendar = creatCell(year, month, monthAllDay, monthBeginWeek, monthTotalCell, sheet, initCell, cell, weekOrder, style, centerstyle, row_initCell, calendar);	
								initCell++;
								weekOrder++;
								monthBeginWeek=2;
							}
							// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
							sheet.addMergedRegion(new CellRangeAddress((initCell-monthTotalCell), initCell-1, 1, 1));
						}
					}
				}else{
					for(int month=1;month<=12;month++){
						monthAllDay = monthDay(year, month);
						monthBeginWeek = firstMonth(year, month)==0?(firstMonth(year, month)+8):(monthBeginWeek = firstMonth(year, month)+1);
						monthTotalCell = returnMonthTotalCell( monthAllDay, monthBeginWeek);
						int calendar = 1;
						for(int i=1;i<=monthTotalCell;i++){
							HSSFRow row_initCell = sheet.createRow(initCell);
							addCell(row_initCell,month, sheet, initCell, cell, weekOrder, centerstyle);
							calendar = creatCell(year, month, monthAllDay, monthBeginWeek, monthTotalCell, sheet, initCell, cell, weekOrder, style, centerstyle, row_initCell, calendar);
						    initCell++;
						    weekOrder++;
						    monthBeginWeek=2;
					}
						// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
						sheet.addMergedRegion(new CellRangeAddress((initCell-monthTotalCell), initCell-1, 1, 1));
				}
			}

		}
			
			
			// 取得输出流
	        OutputStream out = response.getOutputStream();
	        // 清空输出流
	        response.reset();
	        // 设置响应头和下载保存的文件名
	        response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", name)); // 文件名外的双引号处理firefox的空格截断问题
	        FileOutputStream fos = new FileOutputStream(name);
	        // 定义输出类型
	        response.setContentType("application/vnd.ms-excel");
//	        response.setContentType("octets/stream");
	        wb.write(out);
	        out.flush();
	        out.close();
	        // 这一行非常关键，否则在实际中有可能出现莫名其妙的问题！！！
	        response.flushBuffer();// 强行将响应缓存中的内容发送到目的地
			wb.write(fos);
			fos.close();

		} catch (Exception e) {
			log.info("...导出校历数据异常...");
		}
		  }
	}

	
	
	
	

	@RequestMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response,
			@CurrentUser UserInfo user) {

		return DIR + "/add";
	}

	@RequestMapping(value = "/loadExportPage")
	public String loadExportPage(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {
		SchoolCalendarCondition cc = new SchoolCalendarCondition();
		cc.setSchoolId(user.getSchoolId());
		List<SchoolCalendar> ccList = this.schoolCalendarService
				.findSchoolCalendarByCondition(cc, null,
						Order.desc("create_date"));
		request.setAttribute("calendarList", ccList);
		return DIR + "/loadExportPage";
	}

	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response, @CurrentUser UserInfo user) {
		String caId = request.getParameter("id");
		SchoolCalendarCondition cc = new SchoolCalendarCondition();
		cc.setId(Integer.parseInt(caId));
		this.schoolCalendarService.remove(cc);
		return null;
	}

	// private PrintWriter setAjaxResponse(HttpServletRequest request,
	// HttpServletResponse response) throws UnsupportedEncodingException,
	// IOException {
	// response.setContentType("text/html;charset=UTF-8");
	// request.setCharacterEncoding("utf-8");
	// response.setHeader("Cache-Control", "no-cache");
	// response.setHeader("Pragma", "no-cache");
	// response.setDateHeader("Expires", -1);
	// return response.getWriter();
	// }

	/* 判断是否是闰年 */
	public static boolean isLeapYear(int y) {
		if ((y % 4 == 0 && y % 100 != 0) || y % 400 == 0)
			return true;
		else
			return false;
	}
	
	
	/* 判断一个月第一天星期几 */

	public static int firstMonth(int year,int m) {
		int temp = firstDay(year);
		for (int i = 1; i < m; i++) {
			temp += monthDay(year,i);
		}
		return temp % 7;
	}
	
	
	/* 判断一年的第一天星期几 */
	public static int firstDay(int y) {
		long n = y * 365;
		for (int i = 1; i < y; i++)
			if (isLeapYear(i))
				n++;
		return (int) (n % 7);
	}
	/* 判断一个月多少天 */

	public static int monthDay(int year,int m) {
		switch (m) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			if (isLeapYear(year))
				return 29;
			else
				return 28;
		default:
			return 0;
		}
	}

	
	/* 月份转换 */
	public static String monthToString(int m) {
		switch (m) {
		case 1:
			return "一";
		case 3:
			return "三";
		case 5:
			return "五";
		case 7:
			return "七";
		case 8:
			return "八";
		case 10:
			return "十";
		case 12:
			return "十二";
		case 4:
			return "四";
		case 6:
			return "六";
		case 9:
			return "九";
		case 11:
			return "十一";
		case 2:
			return "二";
		default:
			return "";
		}
	}

	
	/**
	 * 判断当前日期是否为节假日
	 * @param month
	 * @param calendar
	 * @param style
	 * @param centerstyle
	 * @return
	 */
	public static boolean isFestival( Integer month,Integer calendar,HSSFCellStyle style,HSSFCellStyle centerstyle){

		boolean isFestival = false;
		
		if(month==1){
			if(calendar==1 ||calendar==2 || calendar==3){
				isFestival= true;
			}else{
				isFestival= false;
			}
			
		}else if(month==2){
			if(calendar==18 || calendar==19 ||calendar==20 ||calendar==21 || calendar==22 ||calendar==23||calendar==24){
				isFestival= true;
			}else{
				isFestival= false;
			}
			
		}else if(month==4){
			if(calendar==4 || calendar==5 || calendar==6){
				isFestival= true;
			}else{
				isFestival= false;
			}
		}else if(month==5){
			if(calendar==1 || calendar==2 || calendar==3){
				isFestival= true;
			}else{
				isFestival= false;
			}
		}else if(month==6){
			if(calendar==20 || calendar==21 || calendar==22){
				isFestival= true;
			}else{
				isFestival= false;
			}
		}else if(month==9){
			if(calendar==26 || calendar==27){
				isFestival= true;
			}else{
				isFestival= false;
			}
		}else if(month==10){
			if(calendar==1 || calendar==2 || calendar==3 || calendar==4 || calendar==5 || calendar==6 || calendar==7){
				isFestival= true;
			}else{
				isFestival= false;
			}
		}else{
			isFestival= false;
		}
		
		
		return isFestival;
		
	}
	
	/**
	 * 将日期填入对应的单元格,并返回对应的日期
	 * @param year
	 * @param month
	 * @param monthAllDay
	 * @param monthBeginWeek
	 * @param monthTotalCell
	 * @param sheet
	 * @param initCell
	 * @param cell
	 * @param weekOrder
	 * @param style
	 * @param centerstyle
	 * @param row_initCell
	 * @param calendar
	 * @return
	 */
	public static Integer creatCell(Integer year,Integer month,Integer monthAllDay,Integer monthBeginWeek,Integer monthTotalCell,HSSFSheet sheet,Integer initCell,HSSFCell cell,Integer weekOrder,HSSFCellStyle style,HSSFCellStyle centerstyle,HSSFRow row_initCell,Integer calendar){
		
		if(monthBeginWeek<=8){
			for(int otherCell=(monthBeginWeek-1);otherCell>=2;otherCell--){
				cell = row_initCell.createCell(otherCell); 
				cell.setCellValue(new HSSFRichTextString(""));
				cell.setCellStyle(centerstyle);
			}
			cell = row_initCell.createCell(monthBeginWeek); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			
			if(calendar<=monthAllDay){
				calendar++;
			}
		}
	
		if((monthBeginWeek+1)<=8){
			cell = row_initCell.createCell(monthBeginWeek+1); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			
			if(calendar<=monthAllDay){
				calendar++;
				
			}
		}
	
		if((monthBeginWeek+2)<=8){
			cell = row_initCell.createCell(monthBeginWeek+2); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			
			if(calendar<=monthAllDay){
				
				calendar++;
			}
		}
	
		if((monthBeginWeek+3)<=8){
			cell = row_initCell.createCell(monthBeginWeek+3); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			if(calendar<=monthAllDay){
				
				calendar++;
			}
		}
	
		if((monthBeginWeek+4)<=8){
			cell = row_initCell.createCell(monthBeginWeek+4); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			
			if(calendar<=monthAllDay){
				calendar++;
				
			}
		}
	
		if((monthBeginWeek+5)<=8){
			cell = row_initCell.createCell(monthBeginWeek+5); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			if(calendar<=monthAllDay){
				calendar++;
				
			}
		}
	
		if((monthBeginWeek+6)<=8){
			cell = row_initCell.createCell(monthBeginWeek+6); 
			cell.setCellValue(new HSSFRichTextString(String.valueOf((calendar<=monthAllDay)?calendar:"")));
			cell.setCellStyle(isFestival( month, calendar, style, centerstyle)?style:centerstyle);
			
			if(calendar<=monthAllDay){
				
				calendar++;
			}
		}
		
		return calendar;
	}
	
	/**
	 * 返回每个月所占的行数
	 * @param monthTotalCell
	 * @return
	 */
	public static Integer returnMonthTotalCell(Integer monthAllDay,Integer monthBeginWeek){
		Integer monthTotalCell = (monthAllDay+ monthBeginWeek-1)%7==0?((monthAllDay+ monthBeginWeek-1)/7):((monthAllDay+ monthBeginWeek-1)/7+1);
		if(monthBeginWeek==6 && monthAllDay==31){
			monthTotalCell= monthTotalCell-1;
		}
		if(monthBeginWeek==7 && monthAllDay==30){
			monthTotalCell= monthTotalCell-1;
		}
		return monthTotalCell;
	}
	
	/**
	 * 添加单元格 
	 * @param row_initCell
	 * @param month
	 * @param sheet
	 * @param initCell
	 * @param cell
	 * @param weekOrder
	 * @param centerstyle
	 */
	public static void addCell(HSSFRow row_initCell,Integer month,HSSFSheet sheet,Integer initCell,HSSFCell cell,Integer weekOrder,HSSFCellStyle centerstyle){
		row_initCell.setHeight((short) 400);
		cell = row_initCell.createCell(0); 
		cell.setCellValue(new HSSFRichTextString(String.valueOf(weekOrder)));
		cell.setCellStyle(centerstyle); 
		cell = row_initCell.createCell(1); 
		cell.setCellValue(new HSSFRichTextString(monthToString(month)+"月"));
		cell.setCellStyle(centerstyle); 
		
	}
	
	
}
