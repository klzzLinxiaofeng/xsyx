package platform.szxyzxx.web.wu.pindemoban.controller;

import framework.generic.dao.Page;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.util.Column;
import platform.szxyzxx.util.ExcelTool;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wuyu.development.service.DevelopmentIndicatorsService;
import platform.szxyzxx.wuyu.development.vo.DevelopmentIndicators;
import platform.szxyzxx.wuyu.pindemoban.service.PinDeMoBanService;
import platform.szxyzxx.wuyu.pindemoban.vo.PinDeMoBan;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyong
 * @Date 2022/11/14 17:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/PinDeMoBan")
public class PinDeMoBanController {
    Logger log = LoggerFactory.getLogger(PinDeMoBanController.class);
    @Autowired
    private PinDeMoBanService pinDeMoBanService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;

    @Autowired
    private DevelopmentIndicatorsService developmentIndicatorsService;

    /*
    * ?????????????????????
    **/
    @RequestMapping("/findByDevel")
    public List<DevelopmentIndicators> findByAll(@RequestParam(value = "schoolYear",required = false) String schoolYear,
                                                 @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                                                 @CurrentUser UserInfo userInfo){
        List<DevelopmentIndicators> developmentIndicators=new ArrayList<>();
        if(schoolYear!=null && schoolTrem!=null){
             developmentIndicators=developmentIndicatorsService.findByAll(null,schoolTrem,schoolTrem,null);
        }else{
             developmentIndicators=developmentIndicatorsService.findByAll(null,schoolTrem,schoolTrem,null);
        }
       return developmentIndicators;
    }

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@RequestParam(value = "schoolYear",required = false) String schoolYear,
                                  @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                                  @RequestParam(value = "zhibiaoId",required = false) Integer zhibiaoId,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  @CurrentUser UserInfo userInfo,
                                  @ModelAttribute("page")Page page,
                                  Model model){
        List<PinDeMoBan> list=new ArrayList<>();
        if(schoolYear==null || schoolTrem==null){
             list=pinDeMoBanService.findByAll(zhibiaoId,userInfo.getSchoolYear(),userInfo.getSchoolTermCode(),page);
        }else {
             list = pinDeMoBanService.findByAll(zhibiaoId,schoolYear, schoolTrem, page);

        }
        String url="";
        if(sub.equals("list")){
            url="/wuyu/development/socialindicatorsstudent/list";
        }else{
            url="/wuyu/development/socialindicatorsstudent/index";
        }
        model.addAttribute("list",list);
        return new ModelAndView(url,model.asMap());

    }

    @RequestMapping(value = "/createOrUpdate")
    public ModelAndView createOrUpdate(
            @RequestParam(value = "id",required = false) Integer id,
            Model model){
        if(id!=null && !id.equals("")){
            PinDeMoBan pinDeMoBan=pinDeMoBanService.findById(id);
            model.addAttribute("pinDeMoBan", pinDeMoBan);
        }

        String bathUrl="";
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping("/create")
    public String create(@CurrentUser UserInfo userInfo,
                         PinDeMoBan pinDeMoBan){
        Integer num=pinDeMoBanService.create(pinDeMoBan);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(1);
            logger.setMessage("??????"+pinDeMoBan.getPingYuTypeName()+"???????????????");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/update")
    public String update(@CurrentUser UserInfo userInfo,
                         PinDeMoBan pinDeMoBan){
        Integer num=pinDeMoBanService.update(pinDeMoBan);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(2);
            logger.setMessage("??????"+pinDeMoBan.getPingYuTypeName()+"???????????????");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/delete")
    public String updateDelete(@CurrentUser UserInfo userInfo,
                               @RequestParam(value = "id") Integer id){
        PinDeMoBan pinDeMoBan=pinDeMoBanService.findById(id);
        Integer num=pinDeMoBanService.updateDelete(id);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(3);
            logger.setMessage("??????"+pinDeMoBan.getPingYuTypeName()+"???????????????");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }




    /*
     * ????????????????????????
     **/
    @RequestMapping("/downloadPingYuMoBan")
    public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
                                          @RequestParam String schoolTrem,
                                          @RequestParam Integer mobanTypeId,
                                          @RequestParam String mobanTypeName,
                                          HttpServletResponse response,
                                          HttpServletRequest request){
        //??????????????????
        List<Map<String,String>> titleList=new ArrayList<>();
        FengZhuan(titleList);
        //????????????ExamList
        String sql=" select school_year as schoolYear,schoolTrem as schoolTrem,id as zhibiaoId ,name as zhibiao_name from zy_development_indicators " +
                " where is_delete=0 and school_year="+schoolYear+" and school_trem'"+schoolTrem+"'";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        String fileName1="??????????????????.xls";
        List<Map<String, Object>> arrayList =new ArrayList<>();
        for(Map<String, Object> aa:map){
            aa.put("zy_type_id",mobanTypeId);
            aa.put("ztName",mobanTypeName);
            for(int a=0;a<3;a++){
                if(a==0){
                    aa.put("dengci","??????");
                }else if(a==1){
                    aa.put("dengci","??????");
                }else{
                    aa.put("dengci","??????");
                }
                aa.put("text",null);
                arrayList.add(aa);
            }
        }
        ExcelTool excelTool = new ExcelTool(fileName1,15,20);
        List<Column>  titleData=excelTool.columnTransformer(titleList);
        try {
            InputStream inputStream = excelTool.exportExcel(titleData, arrayList, true, false);
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
     * ??????????????????
     *
     */
    @RequestMapping("/upLoadPingYuScore")
    public Map<String, Object> upLoadSubjectScore( @RequestParam("fileUpload") MultipartFile fileUpload){
        try {
            String fileName = fileUpload.getOriginalFilename();//???????????????
            InputStream is = null;
            is = fileUpload.getInputStream();
            Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
            Map<String,String> mapTop=new HashMap<>();
            mapTop.put("??????????????????????????????","schoolYear");
            mapTop.put("??????????????????????????????","schoolTrem");
            mapTop.put("????????????????????????????????????","zy_type_id");
            mapTop.put("??????????????????","ztName");
            mapTop.put("??????????????????????????????","zhibiaoId");
            mapTop.put("????????????","zhibiao_name");
            mapTop.put("??????","dengji");
            mapTop.put("??????????????????","text");
            //??????????????????
            List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
            //??????????????????
            List<Map<String,Object>> errList=new ArrayList<>();
            //??????????????????
            List<Map<String,Object>> successList=new ArrayList<>();
            //??????????????????
            Map<String,Object> map=new HashMap<>();

            for(Map<String,Object> bb:list){
                PinDeMoBan pinDeMoBan=new PinDeMoBan();
                pinDeMoBan.setPingYuId(Integer.parseInt(bb.get("zy_type_id").toString()));
                pinDeMoBan.setText(bb.get("context").toString());
                pinDeMoBan.setZhibiaoId(Integer.parseInt(bb.get("zy_type_id").toString()));
                if(bb.get("dengci").toString().equals("??????")){
                    pinDeMoBan.setDengji(1);
                }else if(bb.get("dengci").toString().equals("??????")){
                    pinDeMoBan.setDengji(2);
                }else{
                    pinDeMoBan.setDengji(3);
                }
                pinDeMoBan.setSchoolYear(bb.get("schoolYear").toString());
                pinDeMoBan.setSchoolTrem(bb.get("schoolTrem").toString());
                Integer num=pinDeMoBanService.create(pinDeMoBan);
                if(num>0){
                    bb.put("message","success");
                    successList.add(bb);
                }else{
                    bb.put("message","??????????????????????????????");
                    errList.add(bb);
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



    @RequestMapping(value = "/XiaZaiView")
    public ModelAndView XiaZaiView(){
        String bathUrl="/pingyu/pingyumoban/xiazai";
        ModelAndView modelAndView=new ModelAndView(bathUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/DaoRuView")
    public ModelAndView DaoRuView(){
        String bathUrl="/pingyu/pingyumoban/daoru";
        ModelAndView modelAndView=new ModelAndView(bathUrl);
        return modelAndView;
    }


    public  void FengZhuan(List<Map<String,String>> list){
        Map<String,String>  map4=new HashMap<String,String>();
        map4.put("??????","schoolYear");
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("??????","schoolTrem");
        Map<String,String>  map6=new HashMap<String,String>();
        map6.put("????????????????????????????????????","zy_type_id");
        Map<String,String>  map7=new HashMap<String,String>();
        map7.put("??????????????????","ztName");
        Map<String,String>  map8=new HashMap<String,String>();
        map8.put("??????????????????????????????","zhibiaoId");
        Map<String,String>  map9=new HashMap<String,String>();
        map9.put("????????????","zhibiao_name");
        Map<String,String>  map10=new HashMap<String,String>();
        map10.put("??????","dengji");
        Map<String,String>  map12=new HashMap<String,String>();
        map12.put("??????????????????","text");
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        list.add(map12);
    }
    /*
     * ??????xls??????
     *
     */
    public List<Map<String,Object>> getJieXi(Workbook work, Map<String,String> map) throws Exception {
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
        DecimalFormat df = new DecimalFormat("0.00"); // ?????????number String??????
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // ???????????????
        DecimalFormat df2 = new DecimalFormat("0.00"); // ???????????????

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
}
