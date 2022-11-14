package platform.szxyzxx.office;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.ExcelColumnImport;

public class SubstituteObj {
    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("申请人姓名")
    private String userName;
    @ExcelColumnImport(index = 1)
    @ExcelColumnExport("接收者姓名")
    private Integer receiverName;
    @ExcelColumnImport(index = 2)
    @ExcelColumnExport("代课人姓名")
    private Integer status;
/*    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("学生姓名")
    private Date startTime;
    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("学生姓名")
    private Date endTime;
    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("学生姓名")
    private String description;
    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("学生姓名")
    private Date createDate;
    @ExcelColumnImport(index = 0)
    @ExcelColumnExport("学生姓名")*/
    private String senderName;
    private String feedback;
    private Integer daikeId ;
    private String daikeName;
}
