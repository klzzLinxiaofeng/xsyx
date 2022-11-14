package platform.szxyzxx.web.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/**
 * <p>Title:IntoDataForExcel.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：处理Excel上传数据
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年8月4日
 */
public class IntoDataForExcel {
	private static Log logger=LogFactoryImpl.getLog(IntoDataForExcel.class);

	
	/**
	 * 根据导入文件路径，把Excel文件中数据转换成Bean实体对象集合
	 * @param sourcefileUrl 文件路径 
	 * @param avaliColumn 读取excel有效列
	 * @return
	 * @throws Exception
	 */
	public static List<List<String>> converExcelToList(String sourcefileUrl,int availColumn) throws Exception{
		//所有行集合
		List<List<String>> rowLists = new ArrayList<List<String>>();
		if(sourcefileUrl!=null){
			//获取文件上传后缀
			String suffix = sourcefileUrl.substring(sourcefileUrl.lastIndexOf("."), sourcefileUrl.length());
			int sheetCount = 0;
			//列集合
			List<String> columList = null;
			if(".xls".equalsIgnoreCase(suffix)){ // 2003格式
				//文件流
				InputStream is = new FileInputStream(sourcefileUrl); 
					HSSFWorkbook wb = new HSSFWorkbook(is);  
					sheetCount = wb.getNumberOfSheets();
					for(int i=0;i<sheetCount;i++){
						HSSFSheet hssfSheet = wb.getSheetAt(i);
						int hssfRows = hssfSheet.getLastRowNum();
						for(int j=1;j<=hssfRows;j++){
							//列集合
							columList = new ArrayList<String>();
							HSSFRow hssfRow = hssfSheet.getRow(j);
							for(int k=0;k<availColumn;k++){
								String cellValue = getHssfCellValue(hssfRow.getCell(k));
								//添加有效列的值
								columList.add(cellValue);
							}
							//另加一列保存工作表sheet+row行 + 有效列数的信息
							columList.add(i+"-"+j+"-"+availColumn);
							 
							//添加行信息
		   	   				rowLists.add(columList);
						}
						
					}
				} else if(".xlsx".equalsIgnoreCase(suffix)){ // 2007格式
					XSSFWorkbook wb = new XSSFWorkbook(sourcefileUrl); 
					sheetCount = wb.getNumberOfSheets();
					for(int i=0;i<sheetCount;i++){
						XSSFSheet xssfSheet = wb.getSheetAt(i);
						int xssfRows = xssfSheet.getLastRowNum();
						for(int j=1;j<=xssfRows;j++){
							//列集合
							columList = new ArrayList<String>();
							XSSFRow xssfRow = xssfSheet.getRow(j);
							for(int k=0;k<availColumn;k++){
								String cellValue = getXssfCellValue(xssfRow.getCell(k));
							//添加有效列的值
								columList.add(cellValue);
							}
							//另加一列保存工作表sheet+row行 + 有效列数的信息
							columList.add(i+"-"+j+"-"+availColumn);
							//添加行信息
		   	   				rowLists.add(columList);
						}
					}
				}
		}
		return rowLists;
	}
	/**
	 * 处理标示问题数据和重复数据
	 * @param sourceFileUrl 源文件路径
	 * @param targetFileUrl 目标文件路径
	 * @param targetFileName 目标文件名称
	 * @return targetFileName 目标文件名+后缀
	 * @throws Exception 
	 */
	public static String toSignExceIinvalidDate(String sourceFileUrl,String targetFileUrl,String targetFileName,List<Object> invalidList) throws Exception{
		if(sourceFileUrl!=null){
			//获取文件上传后缀
			String suffix = sourceFileUrl.substring(sourceFileUrl.lastIndexOf("."), sourceFileUrl.length());
			//标示excel数据
			if(".xls".equalsIgnoreCase(suffix)){ // 2003格式
				targetFileName += ".xls";
				targetFileUrl += File.separatorChar + targetFileName;
				//标示问题数据或重复数据
				invalidHssfDateSign(sourceFileUrl, targetFileUrl, invalidList);
			}else if(".xlsx".equalsIgnoreCase(suffix)){ // 2007格式
				targetFileName += ".xlsx";
				targetFileUrl += File.separatorChar + targetFileName;
				//标示问题数据或重复数据
				invalidXssfDateSign(sourceFileUrl, targetFileUrl, invalidList);
			}
		}
		logger.debug("-------------------------"+targetFileName);
		return targetFileName;
	}
	
	/**
	 * 根据单元格类型取值 HSSFCell
	 * @param cell 单元格
	 * @return
	 * @throws Exception
	 */
	public static String getHssfCellValue(HSSFCell cell) throws Exception{
		String cellValue = "";
		try {
			if(cell != null){
				//根据cell的类型取值
				switch (cell.getCellType()) {
					case HSSFCell.CELL_TYPE_STRING://字符串类型
						cellValue = cell.getStringCellValue();
						if(cellValue==null||cellValue==""){
							cellValue = " ";
						}
						break;
					case HSSFCell.CELL_TYPE_NUMERIC://数值类型
						if(HSSFDateUtil.isCellDateFormatted(cell)){//如果是时间类型
//							cellValue = DateUtil.convertDateToStr(cell.getDateCellValue(), "yyyy-MM-dd");
						}else{
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							cellValue = String.valueOf(cell.getStringCellValue());
						}
						break;
					case HSSFCell.CELL_TYPE_FORMULA://公式
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cellValue = String.valueOf(cell.getNumericCellValue());
						break;
					case HSSFCell.CELL_TYPE_BLANK:// 为空
						cellValue = " ";
						break;
					case HSSFCell.CELL_TYPE_BOOLEAN:// 布尔类型
						cellValue = String.valueOf(cell.getBooleanCellValue());
						break;
					case HSSFCell.CELL_TYPE_ERROR:// 错误
						cellValue = " ";
						break;
					default:
						cellValue = " ";
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}
	
	/**
	 * 根据单元格类型取值 XSSFCell
	 * @param cell 单元格
	 * @return
	 * @throws Exception
	 */
	public static String getXssfCellValue(XSSFCell cell) throws Exception{
		String cellValue = "";
		try {
			//根据cell的类型取值
			if(cell != null){
				switch (cell.getCellType()) {
				case XSSFCell.CELL_TYPE_STRING://字符串类型
					cellValue = cell.getStringCellValue();
					if(cellValue==null||cellValue==""){
						cellValue = " ";
					}
					break;
				case XSSFCell.CELL_TYPE_NUMERIC://数值类型
					if(HSSFDateUtil.isCellDateFormatted(cell)){//如果是时间类型
//						cellValue = DateUtil.convertDateToStr(cell.getDateCellValue(), "yyyy-MM-dd");
					}else{
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						cellValue = String.valueOf(cell.getStringCellValue());
					}
					break;
				case XSSFCell.CELL_TYPE_FORMULA://公式
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					cellValue = String.valueOf(cell.getNumericCellValue());
					break;
				case XSSFCell.CELL_TYPE_BLANK:// 为空
					cellValue = " ";
					break;
				case XSSFCell.CELL_TYPE_BOOLEAN:// 布尔类型
					cellValue = String.valueOf(cell.getBooleanCellValue());
					break;
				case XSSFCell.CELL_TYPE_ERROR:// 错误
					cellValue = " ";
					break;
				default:
					cellValue = " ";
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cellValue;
	}
	
	/**
	 * 处理2003格式数据excel
	 * @param sourceFileUrl 源文件文件路径
	 * @param targetFileUrl 目标文件路径
	 * @param invalidList 标示数据位置集合
	 * @throws Exception
	 */
	public static void invalidHssfDateSign(String sourceFileUrl,String targetFileUrl,List<Object> invalidList) throws Exception{
		//无效数据或重复数据处理
		if(invalidList!=null && invalidList.size() > 0 ){
			InputStream is = new FileInputStream(sourceFileUrl);
			HSSFWorkbook wb = new HSSFWorkbook(is);
		
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			font.setFontHeightInPoints((short)12);
//			font.setFontName("宋体");
			for(int i = 0 ; i <  invalidList.size(); i++){
				if(invalidList.get(i)!= null){
					String obj = invalidList.get(i).toString();
					String dataType = obj.split("@")[1];
					String post = obj.split("@")[0];
					//sheet
					int sheetNo = Integer.parseInt(post.split("-")[0]);
					//行
					int rowNo = Integer.parseInt(post.split("-")[1]);
					//列
					int columnNo = Integer.parseInt(post.split("-")[2]);
					HSSFSheet sheet = wb.getSheetAt(sheetNo);
					HSSFRow row = sheet.getRow(rowNo);
					HSSFCellStyle cellStyle = wb.createCellStyle();
					HSSFFont font = wb.createFont();
					if("invaliData".equals(dataType)){ //红色标示无效数据
						font.setColor(HSSFFont.COLOR_RED);
					}else{//蓝色标示重复数据
						font.setColor(HSSFColor.BLUE.index);
					}
					font.setFontName("宋体");
					cellStyle.setFont(font);
					for(int j=0 ; j<columnNo ; j++){
						if(row.getCell(j)!= null){
							//row.getCell(j).getCellStyle().setFont(font);
							cellStyle.setDataFormat(row.getCell(j).getCellStyle().getDataFormat());
							row.getCell(j).setCellValue(getHssfCellValue(row.getCell(j)));
							//设置单元格字体样式
							row.getCell(j).setCellStyle(cellStyle);
						}
					}
				}
			}
			try {
				
				//将文件保存在本地
				File localFile=new File(targetFileUrl);
				PrintStream writer = new PrintStream(localFile);
				wb.write(writer);
				writer.flush();
				writer.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 *  处理2007格式excel数据
	 * @param sourceFileUrl 源文件文件路径
	 * @param targetFileUrl 目标文件路径
	 * @param invalidList 标示数据位置集合
	 * @throws Exception
	 */
	public static void invalidXssfDateSign(String sourceFileUrl,String targetFileUrl,List<Object> invalidList) throws Exception{
		//无效数据或重复数据处理
		if(invalidList!=null && invalidList.size() > 0 ){
			InputStream is = new FileInputStream(sourceFileUrl);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			
//			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//			font.setFontHeightInPoints((short)12);
//			font.setFontName("宋体");
			for(int i = 0 ; i <  invalidList.size(); i++){
				if(invalidList.get(i)!= null){
					String obj = invalidList.get(i).toString();
					String dataType = obj.split("@")[1];
					String post = obj.split("@")[0];
					//sheet
					int sheetNo = Integer.parseInt(post.split("-")[0]);
					//行
					int rowNo = Integer.parseInt(post.split("-")[1]);
					//列
					int columnNo = Integer.parseInt(post.split("-")[2]);
					XSSFSheet sheet = wb.getSheetAt(sheetNo);
					XSSFRow row = sheet.getRow(rowNo);
					XSSFCellStyle cellStyle = wb.createCellStyle();
					XSSFFont font = wb.createFont();
					if("invaliData".equals(dataType)){ //红包标示无效数据
						font.setColor(HSSFColor.RED.index);
					}else{//蓝色标示重复数据
						font.setColor(HSSFColor.BLUE.index);
					}
					font.setFontName("宋体");
					cellStyle.setFont(font);
					for(int j=0 ; j<columnNo ; j++){
						if(row.getCell(j)!= null){
							cellStyle.setDataFormat(row.getCell(j).getCellStyle().getDataFormat());
							row.getCell(j).setCellValue(getXssfCellValue(row.getCell(j)));
							//设置单元格字体样式
							row.getCell(j).setCellStyle(cellStyle);
							//row.getCell(j).getCellStyle().setFont(font);
						}
					}
				}
			}
			try {
				//将文件保存在本地
				File localFile=new File(targetFileUrl);
				PrintStream writer = new PrintStream(localFile);
				wb.write(writer);
				writer.flush();
				writer.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}
	}
	

}
