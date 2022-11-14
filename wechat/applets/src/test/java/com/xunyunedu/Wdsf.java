package com.xunyunedu;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class Wdsf {
    public static void main(String[] args) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();//建立HSSFWorkbook对象

        HSSFSheet sheet = wb.createSheet("new sheet");//建立sheet对象

// Create a row and put some cells in it. Rows are 0 based.

        HSSFRow row = sheet.createRow((short)0);//建立新行

// Create a cell and put a value in it.

        HSSFCell cell = row.createCell((short)0);//建立新cell

        cell.setCellValue(1);//设置cell的整数类型的

// Or do it on one line.

        row.createCell((short)1).setCellValue(1.2);//设置cell浮点类型的值

        row.createCell((short)2).setCellValue("test");//设置cell类型的值

        row.createCell((short)3).setCellValue(true);//设置cell布尔类型的值

        HSSFCellStyle cellStyle = wb.createCellStyle();//建立新的cell样式

        //cellStyle.setDataFormat(HSSFDataFormat.getFormat(Short.parseShort("m/d/yy h:mm")));//设置cell样式为定制的日期格式

        HSSFCell dCell =row.createCell((short)4);

        dCell.setCellValue(new Date());//设置cell为日期类型的值

        dCell.setCellStyle(cellStyle); //设置该cell日期的显示格式

        HSSFCell csCell =row.createCell(5);

        //csCell.setEncoding(HSSFCell.ENCODING_UTF_16);//设置cell编码解决中文高位字节截断

        csCell.setCellValue("中文测试_Chinese Words Test");//设置中西文结合字符串

        row.createCell((short)6).setCellType(HSSFCell.CELL_TYPE_ERROR);//建立错误cell

// Write the output to a file

        FileOutputStream fileOut = new FileOutputStream("workbook.xls");

        wb.write(fileOut);

        fileOut.close();
    }
}
