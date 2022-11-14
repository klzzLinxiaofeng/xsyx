package platform.education.commonResource.web.statistics.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ExcelExportUtil {

    /**
     * 页面导出Excel
     */
    public static void exportExcelToWebOfLP(
            String fileName, String headline,
            String sheetName1, String[] title1, String[][] content1,
            String sheetName2, String[] title2, String[][] content2,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            HSSFWorkbook hssfWorkbook = getLearningPlanHSSFWorkbook(null, headline, sheetName1, title1, content1, sheetName2, title2, content2);

            if ("IE".equals(getBrowser(request))) {
                fileName = new String(URLEncoder.encode(fileName, "UTF-8"));
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
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

    public static HSSFWorkbook getLearningPlanHSSFWorkbook(
            HSSFWorkbook wb, String headline,
            String sheetName1, String[] title1, String[][] content1,
            String sheetName2, String[] title2, String[][] content2
    ) {
        if (wb == null) {
            wb = new HSSFWorkbook();
        }

        //创建sheet1，班级统计
        //1.创建sheet
        HSSFSheet sheet1 = wb.createSheet(sheetName1);
        //2.设置单元格格式，居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        //设置宽度
        sheet1.setDefaultColumnWidth(11);       //设置默认列宽度

        //3.第一行
        HSSFRow row0 = sheet1.createRow(0);
        row0.setHeightInPoints(28f);     //设置行高
        //4.创建第一列，输入值，格式
        HSSFCell cell0 = row0.createCell(0);
        cell0.setCellValue(headline);
        cell0.setCellStyle(style);
        //5.合并单元格
        CellRangeAddress region0 = new CellRangeAddress(0, 0, (short) 0, (short) title1.length-1); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet1.addMergedRegion(region0);

        //6.第二行，标题
        HSSFRow row1 = sheet1.createRow(1);
        row1.setHeightInPoints(22f);
        HSSFCell cell1 = null;
        for (int i = 0; i < title1.length; i++) {
            cell1 = row1.createCell(i);
            cell1.setCellValue(title1[i]);
            cell1.setCellStyle(style);
        }

        //7.内容
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int i = 0; i < content1.length; i++) {
            row = sheet1.createRow(i + 2);
            row.setHeightInPoints(22f);
            for (int j = 0; j < content1[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(content1[i][j]);
                if (j == 0) {
                    cell.setCellStyle(style);
                }
            }
        }
        //8.合并单元格
        CellRangeAddress region1 = new CellRangeAddress(2, 4, (short) 0, (short) 0); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        CellRangeAddress region2 = new CellRangeAddress(5, 7, (short) 0, (short) 0); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        CellRangeAddress region3 = new CellRangeAddress(8, 10, (short) 0, (short) 0); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet1.addMergedRegion(region1);
        sheet1.addMergedRegion(region2);
        sheet1.addMergedRegion(region3);
        //第二行标题合并
        for (int i = 0; i < title1.length; i++) {
            if (i%2 == 1) {
                CellRangeAddress cra = new CellRangeAddress(1, 1, i, i+1); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
                sheet1.addMergedRegion(cra);
            }
        }

        //sheet2，个人统计
        //1.创建sheet
        HSSFSheet sheet2 = wb.createSheet(sheetName2);
        //2.设置单元格格式，居中
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  //垂直居中
        //设置宽度
        sheet2.setDefaultColumnWidth(11);       //设置默认列宽度

        //3.第一行
        HSSFRow row10 = sheet2.createRow(0);
        row10.setHeightInPoints(28f);     //设置行高
        //4.创建第一列，输入值，格式
        HSSFCell cell10 = row10.createCell(0);
        cell10.setCellValue(headline);
        cell10.setCellStyle(style2);
        //5.合并单元格
        CellRangeAddress region10 = new CellRangeAddress(0, 0, (short) 0, (short) title2.length-1); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
        sheet2.addMergedRegion(region10);

        //6.第二行，标题
        HSSFRow row11 = sheet2.createRow(1);
        row11.setHeightInPoints(22f);
        HSSFCell cell11 = null;
        for (int i = 0; i < title2.length; i++) {
            cell11 = row11.createCell(i);
            cell11.setCellValue(title2[i]);
            cell11.setCellStyle(style);
        }

        //7.内容
//        HSSFRow row = null;
//        HSSFCell cell = null;
        for (int i = 0; i < content2.length; i++) {
            row = sheet2.createRow(i + 2);
            row.setHeightInPoints(22f);
            for (int j = 0; j < content2[i].length; j++) {
                //将内容按顺序赋给对应的列对象
                cell = row.createCell(j);
                cell.setCellValue(content2[i][j]);
                if (j == 0 || j == 1) {
                    cell.setCellStyle(style);
                }
            }
        }
        //合并单元格
        for (int i = 0; i < content2.length; i++) {
            int index = i/5;
            CellRangeAddress ca1 = new CellRangeAddress(index * 5 + 2, index * 5 + 6, (short) 0, (short) 0); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
            CellRangeAddress ca2 = new CellRangeAddress(index * 5 + 2, index * 5 + 6, (short) 1, (short) 1); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
            sheet2.addMergedRegion(ca1);
            sheet2.addMergedRegion(ca2);
        }
        for (int i = 0; i < title2.length; i++) {
            if (i != 0 && i%2 == 0) {
                CellRangeAddress cra = new CellRangeAddress(1, 1, i, i+1); //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
                sheet2.addMergedRegion(cra);
            }
        }
        return wb;
    }

    /**
     * 判断浏览器
     *
     * @param request
     * @return
     */
    private static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("User-Agent").toLowerCase();
        if (UserAgent.indexOf("firefox") >= 0) {
            return "FF";
        } else if (UserAgent.indexOf("safari") >= 0) {
            return "Chrome";
        } else {
            return "IE";
        }
    }

}
