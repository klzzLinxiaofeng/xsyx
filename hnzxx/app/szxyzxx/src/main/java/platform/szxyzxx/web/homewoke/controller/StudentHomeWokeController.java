package platform.szxyzxx.web.homewoke.controller;


import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.homewoke.pojo.HomeWokePojo;
import platform.szxyzxx.homewoke.pojo.StudentHomeWoke;
import platform.szxyzxx.homewoke.service.HomeWokeService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student/home/woke")
public class StudentHomeWokeController {
    private Importer<StudentHomeWoke> importer;

    private Exporter exporter;

    public StudentHomeWokeController(){
        ExcelImportParam<StudentHomeWoke> param=new ExcelImportParam<>(StudentHomeWoke.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(StudentHomeWoke.class,"学生作业详情列表"));

    }
    @Autowired
    private HomeWokeService homeWokeService;

    /*
     *导出作业列表
     */
    @RequestMapping("/daochu")
    public void fingDaoChu(@RequestParam Integer id,
                           HttpServletResponse response) {
        try {
            //page传null不分页
            List<StudentHomeWoke> list =homeWokeService.findByAllStudentAll(id,null,null);
            for(StudentHomeWoke aa:list){
                if(aa.getZhuantai()==0){
                    aa.setZhuangzhongwen("待提交");
                }if(aa.getZhuantai()==1){
                    aa.setZhuangzhongwen("已交");
                }if(aa.getZhuantai()==2) {
                    aa.setZhuangzhongwen("缺交");
                }if(aa.getZhuantai()==3){
                    aa.setZhuangzhongwen("补交");
                }
            }
            Workbook wb = exporter.exportToNew(list);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("学生作业详情列表.xls", "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*下载学生作业详情模板*/
    @RequestMapping("/xiazai")
    public ResponseInfomation fingDaoChu(@RequestParam Integer id,
                                         @ModelAttribute("page") Page page,
                                         @ModelAttribute("order") Order order,
                                         HttpServletResponse response, HttpServletRequest request) {
        page.setPageSize(Integer.MAX_VALUE);

        ResponseInfomation tesponseInfomation = null;
        List<Object> list = new ArrayList<Object>();
        //查找数据
        List<StudentHomeWoke> list2 =homeWokeService.findByAllStudentAll(id,null,null);
        for(StudentHomeWoke aa:list2){
            if(aa.getZhuantai()==0){
                aa.setZhuangzhongwen("待提交");
            }if(aa.getZhuantai()==1){
                aa.setZhuangzhongwen("已交");
            }if(aa.getZhuantai()==2) {
                aa.setZhuangzhongwen("缺交");
            }if(aa.getZhuantai()==3){
                aa.setZhuangzhongwen("补交");
            }
        }
        ParseConfig config = SzxyExcelTookit.getConfig("Subject");

        StringBuffer excelName = new StringBuffer();
        excelName.append("学生作业详情模板.xls");
        String filename = excelName.toString();
        try {
            for (StudentHomeWoke studentScoreVo : list2) {
                list.add(studentScoreVo);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
        }
        tesponseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);


        return tesponseInfomation;

    }


    /*
    * 跳转导入页面
    */
    @RequestMapping("/daoruView")
    public ModelAndView getDaoRu(){
        return new ModelAndView("/homeWoke/daochuHomeWoke");
    }
    /*
    * 导入学生作业
    * */
    @RequestMapping("/upLoadScoreInfo")
    public Map<String, Object> upLoadScoreInfoByModel(
            @RequestParam("fileUpload") MultipartFile fileUpload,
            @CurrentUser UserInfo user) {
        //错误信息列表
        Map<String, Object> map = new HashMap<String, Object>();
        //错误列表
        List<StudentHomeWoke> errorScoreVoList = new ArrayList<StudentHomeWoke>();
        //正确列表
        List<StudentHomeWoke> successScoreVoList = new ArrayList<StudentHomeWoke>();

        String status = ResponseInfomation.OPERATION_SUC;
        InputStream is = null;
        try {
            is = fileUpload.getInputStream();
            List<StudentHomeWoke> excelList = excelDataToObject(is);
            for(StudentHomeWoke aa:excelList){
                if(aa.getJobId()!=null) {
                    HomeWokePojo homeWokePojo = homeWokeService.findById(aa.getJobId());
                    if (homeWokePojo.getIsStats() ==1) {
                       if(aa.getStudentId()!=null){
                           /*if(aa.getZhuangzhongwen()=="缺交"){}*/
                               Integer num=homeWokeService.updateDaoru(aa);
                               if(num>0){
                                   successScoreVoList.add(aa);
                               }else{
                                   aa.setErrorInfo("未知错误");
                                   errorScoreVoList.add(aa);
                               }
                       }else{
                           aa.setErrorInfo("学生标识有误");
                           errorScoreVoList.add(aa);
                       }
                    } else {
                        aa.setErrorInfo("该作业未收，请先收取作业");
                        errorScoreVoList.add(aa);
                    }
                }else{
                    aa.setErrorInfo("该学生作业标识有误");
                    errorScoreVoList.add(aa);
                }
            }
        }catch (Exception e){
            e.getMessage();
        }
        //errorParentVoList = getTest("失败");
        //successParentVoList = getTest("成功");
        map.put("status", status);
        map.put("error", errorScoreVoList);
        map.put("success", successScoreVoList);

        return map;
    }
    private List<StudentHomeWoke> excelDataToObject(InputStream is) {

        HSSFWorkbook workbook = null;
        DecimalFormat df = new DecimalFormat("#");
        List<StudentHomeWoke> excelList = new ArrayList<StudentHomeWoke>();
        try {
            workbook = new HSSFWorkbook(is);


            System.out.println("===SheetsNum===" + workbook.getNumberOfSheets()); // 获得Sheet数

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) { // 开始读取
                if (null != workbook.getSheetAt(i)) {

                    HSSFSheet sheet = workbook.getSheetAt(i);// 获得一个Sheet
                    System.out.println("共有" + sheet.getLastRowNum() + "行");

                    for (int rowNumOfSheet = 1; rowNumOfSheet <= sheet.getLastRowNum(); rowNumOfSheet++) {
                        if (null != sheet.getRow(rowNumOfSheet)) {
                            // 获得一个row
                            StudentHomeWoke vo = new StudentHomeWoke();
                            HSSFRow row = sheet.getRow(rowNumOfSheet);
                            try {
                                Cell c0=row.getCell(0);
                                if(c0==null || StringUtils.isEmpty(c0.getStringCellValue())){
                                    continue;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            System.out.print("第" + rowNumOfSheet + "行   ");
                            // System.out.print("一行有"+row.getLastCellNum()+"单元格");
                            for (short cellNumOfRow = 0; cellNumOfRow < row.getLastCellNum(); cellNumOfRow++) {
                                String strCell = "0";
                                HSSFCell cell = row.getCell(cellNumOfRow);
                                if(cell!=null){
                                    int cellType = cell.getCellType();
                                    switch (cellType) {
                                        case 0:// Numberic
                                            //String strCell = df.format(cell.getNumericCellValue());
                                            strCell = String.valueOf(cell.getNumericCellValue());
                                            System.out.print(strCell + " ");
                                            break;
                                        case 1:
                                            strCell = cell.getRichStringCellValue().getString();
                                            System.out.print(strCell + " ");
                                            break;
                                        default:
                                            System.out.println("数据格式不对！");
                                    }
                                }
                                try {
                                    switch (cellNumOfRow) {
                                        case 0:
                                            if (strCell == null || "".equals(strCell)) {
                                                vo.setJobId(null);
                                            } else {
                                                String big = new DecimalFormat("0").format(Double.parseDouble(strCell));
                                                vo.setJobId(Integer.parseInt(big.toString()));
                                            }
                                            break;
                                        case 1:
                                            if (strCell == null || "".equals(strCell)) {
                                                vo.setStudentId(null);
                                            } else {
                                                String big = new DecimalFormat("0").format(Double.parseDouble(strCell));
                                                vo.setStudentId(Integer.parseInt(big.toString()));
                                            }

                                            break;
                                        case 2:
                                            if (strCell == null || "".equals(strCell)) {
                                                vo.setStudentName(null);
                                            } else {
                                                vo.setStudentName(strCell);
                                            }
                                            break;
                                        case 3:
                                            vo.setZhuangzhongwen(strCell);
                                            break;
                                        case 4:
                                            vo.setFenzhi(Float.parseFloat(strCell.toString()));
                                            break;
                                        case 5:
                                            vo.setPingyu(strCell);
                                            break;
                                        default:
                                            System.out.println("列数不对！");
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    continue;

                                }

                            }
                            System.out.println("");
                            excelList.add(vo);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelList;
    }
}
