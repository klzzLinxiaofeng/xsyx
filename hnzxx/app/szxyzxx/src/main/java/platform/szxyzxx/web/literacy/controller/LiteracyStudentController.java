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
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.literacy.service.LiteracyService;
import platform.szxyzxx.literacy.service.LiteracyStudentService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.pingyumoban.service.PingYuMoBanService;
import platform.szxyzxx.pingyumoban.vo.PingYuMoBan;
import platform.szxyzxx.util.Column;
import platform.szxyzxx.util.ExcelTool;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Literacy/student")
public class LiteracyStudentController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LiteracyStudentService liStuService;
    @Autowired
    private PingYuMoBanService pingYuMoBanService;
    @Autowired
    private LiteracyService literacyService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/list")
    public ModelAndView feindByList(
            @ModelAttribute("page") Page page,
            @CurrentUser UserInfo user,
            @RequestParam(value = "xn", required = false) String xn,
            @RequestParam(value = "nj", required = false) Integer nj,
            @RequestParam(value = "bj", required = false) Integer bj,
            @RequestParam(value = "km", required = false) Integer km,
            @RequestParam(value = "sub", required = false) String sub,
            Model model){
        List<LiteracyStudent> list=liStuService.findByAll(xn,nj,bj,km,page,user);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/literacy/sujectlist";
        }else{
            bathUrl="/literacy/subjectSuyang";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }


    @RequestMapping("/pinjialist")
    public List<LiteracyStudent> feindByPinfen(
            @CurrentUser UserInfo user,
             Integer studentId,
             Integer subjectId,
             String schoolYear,
             String schoolTrem){
        List<LiteracyStudent> list=liStuService.findByStudent(user.getSchoolId(),studentId,subjectId,schoolYear,schoolTrem,null);
        return list;
    }
    //?????????????????????????????????
    @RequestMapping("/feindBySubjectView")
    public ModelAndView feindBySubjectView(){
        return new ModelAndView("performanceAnalysis/xuekesuyangfenxi/index");
    }


    //???????????????????????????????????????????????????????????????
    @RequestMapping("/getAllSubjectZhibiao")
    public  List<Map<String,Object>> getAllSubjectZhibiao(
            @CurrentUser UserInfo user,
            @RequestParam("xn")String xn,
            @RequestParam("xq")String xq,
            @RequestParam("gradeId")Integer gradeId,
            @RequestParam("subjectId")Integer subjectId){
        String sql="select * from pj_literacy where xn='"+xn+"' and is_delete=0 and xq='"+xq+"' and grade_id="+gradeId+" and subject_id="+subjectId;
       List<Map<String,Object>> list2= basicSQLService.find(sql);
        return list2;
    }
    //??????????????????
    @RequestMapping("/feindBySubjectFenxin")
    public List<Map<String, Object>> feindBySubjectFenxin(
            @ModelAttribute("page") Page page,
            @CurrentUser UserInfo user,
            @RequestParam("xn")String xn,
            @RequestParam("xq")String xq,
            @RequestParam("gradeId")Integer gradeId,
            @RequestParam("subjectId")Integer subjectId,
            Model model){
            //?????????????????????
        List<Map<String,Object>> subjectFenXin=new ArrayList<>();
        //???????????????????????????
        String sql="SELECT team_id FROM `pj_team_student` where is_delete=0 and  grade_id="+gradeId+"  group by team_id";
        List<Map<String,Object>> list =basicSQLService.find(sql);
        //???????????????????????????????????????
        String sql2="select * from pj_literacy where xn='"+xn+"' and is_delete=0 and xq='"+xq+"' and grade_id="+gradeId+" and subject_id="+subjectId;
        List<Map<String,Object>> list2= basicSQLService.find(sql2);
        for(Map<String,Object> team:list){
            Integer str=Integer.valueOf(team.get("team_id").toString());
            //???????????????????????????
            String sql3="select * from pj_student where is_delete=0 and team_id="+str+" and study_state='01'  and   school_id="+user.getSchoolId();
            List<Map<String,Object>> studentList=basicSQLService.find(sql3);
            if(studentList.size()>0){
               Map<String,Object> sugList=getStudent(studentList,list2);
                subjectFenXin.add(sugList);
            }
        }
        model.addAttribute("list",subjectFenXin);
        ModelAndView modelAndView=new ModelAndView("performanceAnalysis/xuekesuyangfenxi/list",model.asMap());
        return subjectFenXin;
    }

    //?????????????????????????????????
    public Map<String,Object> getStudent(List<Map<String,Object>> studetList,List<Map<String,Object>> list2){
        int zongrenshu=studetList.size();
        Map<String,Object> map=new HashMap();
        List<Double> number=new ArrayList();
        List<Double> lisanduList=new ArrayList();
        map.put("teamName",studetList.get(0).get("team_name").toString());
        //????????????
        for(Map<String,Object> bb:list2) {
            Integer [] array =new Integer[studetList.size()];
            int a=0;
            int b=0;
            Integer literacyId=Integer.valueOf(bb.get("id").toString());
            for (Map<String, Object> aa : studetList) {
                if (aa.get("id") != null) {
                    Integer id=Integer.valueOf(aa.get("id").toString());
                    //????????????id?????????id??????????????????
                    String sql="select pls.* from pj_literacy_student pls inner join pj_student ps on ps.id=pls.student_id where pls.is_delete=0 and ps.is_delete=0 and ps.study_state='01' and pls.student_id="+id+" and literacy_id="+literacyId;
                    List<Map<String,Object>> fenshuList=basicSQLService.find(sql);
                    if(fenshuList.size()>0){
                        int fenshu=Integer.parseInt(fenshuList.get(0).get("fenshu").toString());
                        a=a+fenshu;
                        array[b]=fenshu;
                        b++;
                    }
                }
            }
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(4);
            //?????????
            String sss=nf.format(a/zongrenshu);
            double svg=Double.parseDouble(sss);
            //??????
            String fangcha=getDouble(array,svg);
            double fangs=Double.parseDouble(fangcha);
           //????????? nb
            String sdfsd=nf.format(Math.sqrt(fangs));
            double biaozhuncha=Double.parseDouble(sdfsd);
           //?????????
            double sjsjjs=0;
            if(svg!=0){
                String lisandu=nf.format(biaozhuncha/svg);
                 sjsjjs=Double.parseDouble(lisandu);
            }
            lisanduList.add(sjsjjs);
            number.add(svg);
        }
        map.put("fenshu",number);
        map.put("lisanduList",lisanduList);
        return map;
    }

    //????????????
    public String getDouble(Integer [] array,double avg){
        int siz=array.length;
        double var=0;
        for(int a=0;a<array.length;a++){
            var+=(array[a]-avg)*(array[a]-avg);
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(4);
        return nf.format(var/siz);
    } 
    /*
     * ???????????????????????????
     **/
    @RequestMapping("/findBypingyu")
    public ModelAndView findBypingyu(@CurrentUser UserInfo userInfo,
                                     @RequestParam Integer id,
                                      Model model){
        LiteracyStudent literacyStudent=liStuService.findByLiteracyId(userInfo.getSchoolId(),id);
        model.addAttribute("literacyStudent",literacyStudent);
        ModelAndView modelAndView=new ModelAndView("/literacy/pinfen/updatepingyu",model.asMap());
        return modelAndView;
    }


    /*
     * ?????????????????????
     * */
    @RequestMapping("/findByPingMoBan")
    public PingYuMoBan findByPingMoBan(@RequestParam String  fenshu,
                                  @RequestParam Integer gradeId,
                                  @RequestParam Integer subjectId,
                                   @RequestParam Integer licatyId,
                                       @RequestParam String schoolYear,
                                       @RequestParam String  schoolTrem) {
        List<PingYuMoBan> pingYuMoBan = pingYuMoBanService.findByIds("????????????", gradeId, subjectId, licatyId,schoolYear,schoolTrem);
        if (pingYuMoBan.size() > 0) {
            for (PingYuMoBan aa : pingYuMoBan) {
                if (aa.getMinScore() <= Double.parseDouble(fenshu) && Double.parseDouble(fenshu) <= aa.getMaxScore()) {
                    return aa; 
                }
            }
            return null;
        }else{
           return null;
        }
    }

    /*
    * ????????????
    * */
    @RequestMapping("/updatePingyu")
    public String updatePingyu(@RequestParam Integer id,
                               @RequestParam String pingyu
                                      ){
         int num=liStuService.updatePingyu(id,pingyu);
         if(num>0){
             return "success";
         }else{
             return "error";
         }
    }

    /*
    * ????????????
    */
    @RequestMapping("/UpdatePingyuyi")
    public String UpdatePingyuyi(@CurrentUser UserInfo userInfo,
                                 @RequestParam(value ="schoolYear" ,required = false) String schoolYear,
                                 @RequestParam(value ="schoolTrem" ,required = false) String schoolTrem,
                                 @RequestParam(value ="gradeId" ,required = false) Integer gradeId,
                                 @RequestParam(value ="subjectId" ,required = false) Integer subjectId
                                 ){
        Loggers logger = new Loggers();
        logger.setCaozuoId(userInfo.getTeacherId());
        logger.setName(userInfo.getRealName());
        logger.setUsername(userInfo.getUserName());
        logger.setMobile(userInfo.getTelephone());
        logger.setMokuaiName("????????????");
        logger.setType(2);
        logger.setMessage("????????????" );
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);

        String sql="select pl.*,pls.id as plsId,pls.fenshu from pj_literacy_student pls " +
                "inner join pj_literacy pl on pl.id=pls.literacy_id"+
               " where pl.xn="+schoolYear+" and pl.xq='"+schoolTrem+
                "'   and pls.is_delete=0 and pl.is_delete=0 ";
        if(gradeId!=null && !gradeId.equals("")){
            sql+=" and pl.grade_id="+gradeId;
        }
        if(subjectId!=null && !subjectId.equals("")){
            sql+=" and pl.subject_id="+subjectId;
        }
        List<Map<String, Object>> list=basicSQLService.find(sql);
         for(Map<String,Object> aa:list){
             List<PingYuMoBan> pingYuMoBan = pingYuMoBanService.findByIds("????????????", Integer.parseInt(aa.get("grade_id").toString()), Integer.parseInt(aa.get("subject_id").toString()), Integer.parseInt(aa.get("id").toString()),aa.get("xn").toString(),aa.get("xq").toString());
             if (pingYuMoBan.size() > 0) {
                 for (PingYuMoBan bb : pingYuMoBan) {
                     if (bb.getMinScore() <=Integer.parseInt(aa.get("fenshu").toString()) && Integer.parseInt(aa.get("fenshu").toString()) <= bb.getMaxScore()) {
                         updatePingyu(Integer.parseInt(aa.get("plsId").toString()),bb.getText());
                     }
                 }
             }
         }
         return "success";
    }


    /*
     * ??????????????????????????????
     **/
    @RequestMapping("/downloadSubject")
    public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
                                          @RequestParam String schoolTrem,
                                          @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                          @RequestParam(value = "teamId",required = false) Integer teamId,
                                          @RequestParam(value = "subjectId",required = false) Integer subjectId,
                                          @RequestParam(value = "zhibiaoId",required = false) Integer zhibiaoId,
                                          HttpServletResponse response,
                                          HttpServletRequest request){
        //??????????????????
        List<Map<String,String>> titleList=new ArrayList<>();
        FengZhuan(titleList);
        //????????????ExamList
        String sql="select pl.xn as school_year,pl.xq as school_trem,pl.grade_id as gradeId,pg.name as gradeName, " +
                "  pt.id as teamId,pt.name as teamName,pl.subject_id as subjectId,pss.name as subjectName," +
                "  pl.id as zhiBiaoId,pl.literacy_name as zhibiaoName,ps.id as stuId,ps.name as stuName  from pj_literacy pl  " +
                " inner join pj_literacy_student pls on pls.literacy_id=pl.id " +
                " inner join pj_grade pg on pg.id=pl.grade_id"+
                " inner join pj_subject pss on pss.id=pl.subject_id"+
                " left join pj_student ps on ps.id=pls.student_id " +
                " left join pj_team_student pts on pts.student_id=ps.id " +
                " left join pj_team pt on pt.id=pts.team_id " +
                " where pl.is_delete=0 and pls.is_delete=0 and  pl.xn="+schoolYear+" and pl.xq='"+schoolTrem+"'" +
                " and ps.study_state='01' and pl.grade_id="+gradeId+"  and pt.id="+teamId;
        if(zhibiaoId!=null && !zhibiaoId.equals("")){
            sql+=" and pl.id="+zhibiaoId;
        }
        if(subjectId!=null && !subjectId.equals("")){
            sql+=" and pl.subject_id="+subjectId;
        }
        sql+=" order by pl.id, ps.emp_code asc";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        String fileName1="????????????????????????????????????.xls";
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
     * ??????????????????????????????
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
            Map<String,String> map=new HashMap<>();
            map.put("??????????????????????????????","school_year");
            map.put("??????????????????????????????","school_trem");
            map.put("??????????????????????????????","gradeId");
            map.put("????????????","gradeName");
            map.put("??????????????????????????????","teamId");
            map.put("????????????","teamName");
            map.put("??????????????????????????????","subjectId");
            map.put("????????????","subjectName");
            map.put("??????????????????????????????","zhiBiaoId");
            map.put("????????????","zhibiaoName");
            map.put("??????????????????????????????","stuId");
            map.put("????????????","stuName");
            map.put("??????","context");
            //??????????????????
            List<Map<String,Object>> list=getJieXi(readexeclC,map);
            //??????????????????
            List<Map<String,Object>> errList=new ArrayList<>();
            //??????????????????
            List<Map<String,Object>> successList=new ArrayList<>();
            //??????????????????
            Map<String,Object> mapTop=new HashMap<>();

            for(Map<String,Object> bb:list){
                LiteracyVo literacyVo=literacyService.findById(Double.valueOf(bb.get("zhiBiaoId").toString()).intValue());
                if(literacyVo.getScore()>=Double.valueOf(bb.get("context").toString()).intValue()){
                    Integer num=liStuService.updatePingFen(Integer.parseInt(bb.get("stuId").toString()),Integer.parseInt(bb.get("zhiBiaoId").toString()),Double.valueOf(bb.get("context").toString()).intValue());
                    if(num>0){
                        bb.put("message","success");
                        successList.add(bb);
                    }else{
                        bb.put("message","?????????????????????????????????");
                        errList.add(bb);
                    }
                }else{
                    bb.put("message","??????????????????????????????");
                    errList.add(bb);
                }
            }
            mapTop.put("status","success");
            mapTop.put("error",errList);
            mapTop.put("success",successList);
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("????????????");
            logger.setType(2);
            logger.setMessage("??????????????????????????????" );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return  mapTop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping(value = "/XiaZaiView")
    public ModelAndView XiaZaiView(){
        String bathUrl="/literacy/studentXiazai";
        ModelAndView modelAndView=new ModelAndView(bathUrl);
        return modelAndView;
    }

    @RequestMapping(value = "/DaoRuView")
    public ModelAndView DaoRuView(){
        String bathUrl="/literacy/studentdaoru";
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
        map4.put("??????????????????????????????","teamId");
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("????????????","teamName");
        Map<String,String>  map6=new HashMap<String,String>();
        map6.put("??????????????????????????????","subjectId");
        Map<String,String>  map7=new HashMap<String,String>();
        map7.put("????????????","subjectName");
        Map<String,String>  map8=new HashMap<String,String>();
        map8.put("??????????????????????????????","zhiBiaoId");
        Map<String,String>  map9=new HashMap<String,String>();
        map9.put("????????????","zhibiaoName");
        Map<String,String>  map11=new HashMap<String,String>();
        map11.put("??????????????????????????????","stuId");
        Map<String,String>  map12=new HashMap<String,String>();
        map12.put("????????????","stuName");
        Map<String,String>  map13=new HashMap<String,String>();
        map13.put("??????","context");
        list.add(map1);
        list.add(mapTop);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map11);
        list.add(map12);
        list.add(map13);
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
