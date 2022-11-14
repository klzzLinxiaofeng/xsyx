package platform.szxyzxx.web.common.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 处理EXCEL单元格数据
 * Created by panfei on 2017/6/5.
 */
public class ExcelUtil {
    public static String parseExcel(Cell cell) {
        String result = new String();
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:// 数字类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                            .getBuiltinFormat("h:mm")) {
                        sdf = new SimpleDateFormat("HH:mm");
                    } else {// 日期
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                } else if (cell.getCellStyle().getDataFormat() == 58) {
                    // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil
                            .getJavaDate(value);
                    result = sdf.format(date);
                } else {
                    double value = cell.getNumericCellValue();
                    result = value+"";
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:// String类型
                result = cell.getRichStringCellValue().toString();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                result = "";
            default:
                result = "";
                break;
        }
        return result;
    }


    /**
     * 页面导出Excel
     * @param fileName  文件名
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @param response
     */
    public static void exportExcelToWEB(String fileName, String sheetName, String []title, String [][]values, HSSFWorkbook wb, HttpServletResponse response, String cellStyle){
        try {
            HSSFWorkbook hssfWorkbook = getHSSFWorkbook(sheetName, title, values, wb, cellStyle);

            fileName = new String(fileName.getBytes(),"ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");

            OutputStream os = response.getOutputStream();
            hssfWorkbook.write(os);
            os.flush();
            os.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param values 内容
     * @param wb HSSFWorkbook对象
     * @return
     */
    public static HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, String [][]values, HSSFWorkbook wb, String cellStyle){

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        //声明列对象
        HSSFCell cell = null;

        //创建标题
        for(int i=0;i<title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        //创建内容
        for(int i=0;i<values.length;i++){
            row = sheet.createRow(i + 1);
            for(int j=0;j<values[i].length;j++){
                //将内容按顺序赋给对应的列对象
                row.createCell(j).setCellValue(values[i][j]);
            }
        }

        if (cellStyle != null && !"".equals(cellStyle)) {
            if ("1".equals(cellStyle)) {
                addExplainOfTeamTeacher(wb);
            }
            if ("2".equals(cellStyle)) {
                addExplainOfParentStudent(wb);
            }
        }

        return wb;
    }

    private static void addExplainOfParentStudent(HSSFWorkbook wb){
        HSSFSheet sheet1 = wb.getSheetAt(0);
        sheet1.setDefaultColumnWidth(12);    //设置默认宽度
//        sheet1.setColumnWidth(0, 0);    //首列宽度为0

        HSSFFont font1 = wb.createFont();
        font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //加粗
        font1.setFontHeightInPoints((short)11);     //字体大小

        HSSFCellStyle cellStyle1 = wb.createCellStyle();
        cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
        cellStyle1.setFont(font1);

        //循环设置表头
        HSSFRow row1 = sheet1.getRow(0);
        short cellNum = row1.getLastCellNum();
        for (int i=0; i<cellNum; i++) {
            row1.getCell(i).setCellStyle(cellStyle1);
        }

        HSSFSheet sheet2 = wb.createSheet("模板说明");
        //字体样式
        HSSFFont font2 = wb.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 16);

        //单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setFont(font2);

        HSSFRow row2 = sheet2.createRow(0);
        HSSFCell cell2 = row2.createCell(0);
        String value = "操作说明 : " + "\n"
                + "1.请勿修改模板原有的字段名称以及内容" + "\n"
                + "2.“监护人”：请填写监护人姓名，只能填写中英文" + "\n"
                + "   " + "注意：如果该生在系统中无监护人信息，则该表格【监护人1】的信息将设为该学生的主监护人" + "\n"
                + "3.“手机号码”：请填写11位数字组成的手机号码" + "\n"
                + "4.“与学生关系”：请在“父亲、母亲、其他”选择一个关系填写，且只能填写一个关系；该项不是必填项" + "\n"
                + "5.【家长信息模板】默认提供两个监护人相关信息的填写，如若需要填写2个以上的监护人，请在表格第一行“与学生关系2”后一格填入“【监护人3】、【手机号码3】、【与学生关系3】、...”，以此类推" + "\n"
                + "6.手机号码为必填项，如若填写了【监护人】的姓名，则必须在相对应的【手机号码】栏填写号码，【与学生关系】可不填写"
                ;
        //单元格内某些字段标红色
        HSSFRichTextString richTextString = new HSSFRichTextString(value);  //富文本字符串
        HSSFFont fontRed = wb.createFont();
        fontRed.setFontName("宋体");
        fontRed.setFontHeightInPoints((short) 16);
        fontRed.setColor(HSSFFont.COLOR_RED);   //红色
        richTextString.applyFont(font2);
        richTextString.applyFont(10, 27, fontRed);
        richTextString.applyFont(56, 58, fontRed);
        richTextString.applyFont(78, 99, fontRed);
        richTextString.applyFont(136, 146, fontRed);
        richTextString.applyFont(235, 264, fontRed);
        richTextString.applyFont(272, 280, fontRed);

        cell2.setCellValue(richTextString);
        cell2.setCellStyle(cellStyle);

        //合并单元格
        CellRangeAddress region1 = new CellRangeAddress(0, 15, (short) 0, (short) 15); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet2.addMergedRegion(region1);
    }

    private static void addExplainOfTeamTeacher(HSSFWorkbook wb){
        HSSFSheet sheet2 = wb.createSheet("模板说明");

        //字体样式
        HSSFFont font2 = wb.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 16);

        //单元格样式
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setFont(font2);

        HSSFRow row2 = sheet2.createRow(0);
        HSSFCell cell2 = row2.createCell(0);
        cell2.setCellValue(
                "任课教师安排模板注意事项：\n" +
                        "（1）“年级”、“班级”为必填项；\n" +
                        "（2）请在年级班级对应的“科目”项内填写教师姓名；如果存在同名的教师，教师姓名填写格式为“姓名(别名)”，括号为英文半角括号\n" +
                        "（3）请勿修改工作表(Sheet)名称");
        cell2.setCellStyle(cellStyle);

        //合并单元格
        CellRangeAddress region1 = new CellRangeAddress(0, 10, (short) 0, (short) 6); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet2.addMergedRegion(region1);
    }

}
