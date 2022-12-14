package platform.szxyzxx.web.literacy.controller;


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
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.literacy.service.LiteracyService;
import platform.szxyzxx.literacy.service.LiteracyStudentService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.util.Column;
import platform.szxyzxx.util.ExcelTool;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

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

@RestController
@RequestMapping("/literacy")
public class LiteracyController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LiteracyService literacyService;
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private LiteracyStudentService liStuService;

    @RequestMapping("/findExamSubject")
    public List findExamSubject(@CurrentUser UserInfo userInfo,
                                @RequestParam Integer nj){
        return basicSQLService.find("select ps.id as subjectId, psg.subject_code as code,psg.subject_name as subjectName from pj_subject_grade  psg " +
                "inner join pj_grade pg on pg.uni_grade_code=psg.grade_code" +
                " inner join pj_subject ps on ps.code=psg.subject_code " +
                "where  pg.id="+nj+" and psg.is_deleted=0 and psg.school_id="+userInfo.getSchoolId()+" group by ps.id");
    }


    @RequestMapping("/findExambj")
    public List findExambj(@CurrentUser UserInfo user,String xn, Integer nj){
        return basicSQLService.find("select * from pj_team  where  school_id="+user.getSchoolId()+" and grade_id="+nj+"  and school_year="+xn+"  and is_delete=0");
    }

    @RequestMapping("/list")
    public ModelAndView feindByList(
                                    @ModelAttribute("page") Page page,
                                    @RequestParam(value = "xq", required = false) String xq,
                                    @RequestParam(value = "xn", required = false) Integer xn,
                                    @RequestParam(value = "nj", required = false) Integer nj,
                                    @RequestParam(value = "km", required = false) Integer km,
                                    @RequestParam(value = "sub", required = false) String sub,
                                    Model model){
        List<LiteracyVo> list=literacyService.findByAll(xq, xn, nj, km, page);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/literacy/list";
        }else{
            bathUrl="/literacy/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping("/input")
    public ModelAndView createLiertacy(){
        ModelAndView modelAndView=new ModelAndView("/literacy/add");
        return modelAndView;
    }
    @RequestMapping("/input/create")
    public ResponseInfomation createLiertacys( LiteracyVo literacyVo,
                                               @CurrentUser UserInfo userInfo){
        Boolean flag=literacyService.create(literacyVo,userInfo);
        ResponseInfomation re=new ResponseInfomation();
        if(flag){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(1);
            logger.setMessage("????????????????????????" + literacyVo.getLiteracyName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return re;
    }

    /*????????????*/
    @RequestMapping("/inputmode")
    public ModelAndView update(@RequestParam(value = "id", required = false) int id,Model model){
        LiteracyVo literacyVo=literacyService.findById(id);
        model.addAttribute("literacyVo",literacyVo);
        ModelAndView modelAndView=new ModelAndView("/literacy/input",model.asMap());
        return modelAndView;
    }

    /*????????????*/
    @RequestMapping("/update")
    public ResponseInfomation update(@CurrentUser UserInfo userInfo,
                                     LiteracyVo literacyVo){
        Boolean flag=literacyService.update(literacyVo);
        ResponseInfomation re=new ResponseInfomation();
        if(flag){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(2);
            logger.setMessage("????????????????????????" + literacyVo.getLiteracyName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return re;
    }
    /*????????????*/
    @RequestMapping("/chakan")
    public ModelAndView chakan(@RequestParam(value = "id", required = false) int id,Model model){
        LiteracyVo literacyVo=literacyService.findById(id);
        model.addAttribute("literacyVo",literacyVo);
        ModelAndView modelAndView=new ModelAndView("/literacy/chakan",model.asMap());
        return modelAndView;
    }
    //??????
    @RequestMapping("/delete")
    public String delete(@CurrentUser UserInfo userInfo,
                         @RequestParam(value = "id", required = false) int id){
        LiteracyVo literacyVo2=literacyService.findById(id);
       LiteracyVo literacyVo=new LiteracyVo();
       literacyVo.setId(id);
       literacyVo.setIsDelete(1);
        Boolean fale=literacyService.update(literacyVo);
        if(fale){
            literacyService.updatestudent(id);
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(3);
            logger.setMessage("????????????????????????" + literacyVo2.getLiteracyName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return PublicClassService.OPERATE_SUCCESS;
        }
        return PublicClassService.OPERATE_FAIL;

    }

    /*????????????*/
    @RequestMapping("/pingjias")
    public String update(@CurrentUser UserInfo userInfo,
                         @RequestParam Integer id,
                         @RequestParam Integer score,
                         @RequestParam String pingyu){
        Boolean flag=literacyService.updatefenshu(id,score,pingyu);
        List<Map<String,Object>> list=basicSQLService.find(" select  ps.name,pb.name as subjectName   from pj_literacy_student pls " +
                "        inner join pj_student ps on ps.id=pls.student_id " +
                "        inner join pj_literacy pl on pl.id=pls.literacy_id " +
                "        inner join pj_subject pb on pb.id=pl.subject_id " +
                "        where  pls.id="+id+" and pls.is_delete=0 and ps.school_id="+userInfo.getSchoolId()+" and ps.study_state='01' ");
        if(flag){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????");
            logger.setType(2);
            logger.setMessage("??????"+list.get(0).get("name")+"???"+list.get(0).get("subjectName")+"???????????????????????????" );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }
        return "re";
    }



    /*
     * ????????????????????????
     **/
    @RequestMapping("/downloadSubject")
    public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
                                          @RequestParam String schoolTrem,
                                          @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                          @RequestParam(value = "subjectId",required = false) Integer subjectId,
                                          HttpServletResponse response,
                                          HttpServletRequest request){
        //??????????????????
        List<Map<String,String>> titleList=new ArrayList<>();
        FengZhuan(titleList);
        //????????????ExamList
        String sql="select pg.school_year, '"+schoolTrem+"' as school_trem, pg.id as gradeId," +
                "pg.name as gradeName,ps.id as subjectId,ps.name as subjectName " +
                "from pj_subject_grade psg  inner join  pj_grade pg on pg.uni_grade_code=psg.grade_code  " +
                "inner join pj_subject ps on  ps.code=psg.subject_code  " +
                "where pg.is_deleted=0 and  pg.school_year="+schoolYear+" and pg.id="+gradeId+
                " and psg.is_deleted=0 and  ps.is_delete=0 ";
        if(subjectId!=null && !subjectId.equals("")){
            sql+=" and ps.id="+subjectId;
        }
        sql+=" group by ps.id";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        String fileName1="??????????????????????????????.xls";
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
    @RequestMapping("/upLoadPingYuScore")
    public Map<String, Object> upLoadSubjectScore( @CurrentUser UserInfo userInfo,
                                                   @RequestParam("fileUpload") MultipartFile fileUpload){
        try {
            String fileName = fileUpload.getOriginalFilename();//???????????????
            InputStream is = null;
            is = fileUpload.getInputStream();
            Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
            Map<String,String> mapTop=new HashMap<>();
            mapTop.put("??????????????????????????????","school_year");
            mapTop.put("??????????????????????????????","school_trem");
            mapTop.put("??????????????????????????????","gradeId");
            mapTop.put("????????????","gradeName");
            mapTop.put("??????????????????????????????","subjectId");
            mapTop.put("????????????","subjectName");
            mapTop.put("????????????","maxScore");
            mapTop.put("????????????","context");
            //??????????????????
            List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
            //??????????????????
            List<Map<String,Object>> errList=new ArrayList<>();
            //??????????????????
            List<Map<String,Object>> successList=new ArrayList<>();
            //??????????????????
            Map<String,Object> map=new HashMap<>();

            for(Map<String,Object> bb:list){

                LiteracyVo literacyVo=new LiteracyVo();
                literacyVo.setLiteracyName(bb.get("context").toString());
                literacyVo.setXn(bb.get("school_year").toString());
                literacyVo.setXq(bb.get("school_trem").toString());
                literacyVo.setGradeId(Integer.parseInt(bb.get("gradeId").toString()));
                literacyVo.setSubjectId(Integer.parseInt(bb.get("subjectId").toString()));
                literacyVo.setScore(Double.valueOf(bb.get("maxScore").toString()).intValue());
                List<LiteracyVo> literacyVoList=literacyService.findByName(literacyVo);
                if(literacyVoList.size()>0){
                    bb.put("message", "??????????????????");
                    errList.add(bb);
                }else {
                    Boolean num = literacyService.create(literacyVo, userInfo);
                    if (num) {
                        bb.put("message", "success");
                        successList.add(bb);
                    } else {
                        bb.put("message", "??????????????????????????????");
                        errList.add(bb);
                    }
                }
            }
            map.put("status","success");
            map.put("error",errList);
            map.put("success",successList);
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????????????????");
            logger.setType(1);
            logger.setMessage("????????????????????????" );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return  map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/XiaZaiView")
    public ModelAndView XiaZaiView(){
        String bathUrl="/literacy/xiazai";
        ModelAndView modelAndView=new ModelAndView(bathUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/DaoRuView")
    public ModelAndView DaoRuView(){
        String bathUrl="/literacy/daoru";
        ModelAndView modelAndView=new ModelAndView(bathUrl);
        return modelAndView;
    }


    public  void FengZhuan(List<Map<String,String>> list){
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("??????????????????????????????","school_year");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("??????????????????????????????","school_trem");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("??????????????????????????????","gradeId");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("????????????","gradeName");
        Map<String,String>  map4=new HashMap<String,String>();
        map4.put("??????????????????????????????","subjectId");
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("????????????","subjectName");
        Map<String,String>  map10=new HashMap<String,String>();
        map10.put("????????????","maxScore");
        Map<String,String>  map12=new HashMap<String,String>();
        map12.put("????????????","context");
        list.add(map1);
        list.add(mapTop);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map10);
        list.add(map12);
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
