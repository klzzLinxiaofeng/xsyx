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
            logger.setMokuaiName("学科素养指标管理");
            logger.setType(1);
            logger.setMessage("新增学科素养指标" + literacyVo.getLiteracyName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return re;
    }

    /*修改跳转*/
    @RequestMapping("/inputmode")
    public ModelAndView update(@RequestParam(value = "id", required = false) int id,Model model){
        LiteracyVo literacyVo=literacyService.findById(id);
        model.addAttribute("literacyVo",literacyVo);
        ModelAndView modelAndView=new ModelAndView("/literacy/input",model.asMap());
        return modelAndView;
    }

    /*修改指标*/
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
            logger.setMokuaiName("学科素养指标管理");
            logger.setType(2);
            logger.setMessage("修改学科素养指标" + literacyVo.getLiteracyName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return re;
    }
    /*查看跳转*/
    @RequestMapping("/chakan")
    public ModelAndView chakan(@RequestParam(value = "id", required = false) int id,Model model){
        LiteracyVo literacyVo=literacyService.findById(id);
        model.addAttribute("literacyVo",literacyVo);
        ModelAndView modelAndView=new ModelAndView("/literacy/chakan",model.asMap());
        return modelAndView;
    }
    //删除
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
            logger.setMokuaiName("学科素养指标管理");
            logger.setType(3);
            logger.setMessage("删除学科素养指标" + literacyVo2.getLiteracyName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return PublicClassService.OPERATE_SUCCESS;
        }
        return PublicClassService.OPERATE_FAIL;

    }

    /*修改分数*/
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
            logger.setMokuaiName("学科素养");
            logger.setType(2);
            logger.setMessage("修改"+list.get(0).get("name")+"的"+list.get(0).get("subjectName")+"的学科素养学生评语" );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }
        return "re";
    }



    /*
     * 导出学科素养指标
     **/
    @RequestMapping("/downloadSubject")
    public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
                                          @RequestParam String schoolTrem,
                                          @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                          @RequestParam(value = "subjectId",required = false) Integer subjectId,
                                          HttpServletResponse response,
                                          HttpServletRequest request){
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        FengZhuan(titleList);
        //数据组装ExamList
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
        String fileName1="学科素养指标导入模板.xls";
        ExcelTool excelTool = new ExcelTool(fileName1,15,20);
        List<Column>  titleData=excelTool.columnTransformer(titleList);
        try {
            InputStream inputStream = excelTool.exportExcel(titleData, map, true, false);
            response.setCharacterEncoding("UTF-8");
            String userAgent = request.getHeader("User-Agent");
            byte[] bytes = userAgent.contains("MSIE") ? fileName1.getBytes() : fileName1.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
            String name = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
            response.setContentType("octets/stream");
            response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", name));
            ServletOutputStream outputStream = response.getOutputStream();
            //写文件
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
     * 导入学科素养指标
     *
     */
    @RequestMapping("/upLoadPingYuScore")
    public Map<String, Object> upLoadSubjectScore( @CurrentUser UserInfo userInfo,
                                                   @RequestParam("fileUpload") MultipartFile fileUpload){
        try {
            String fileName = fileUpload.getOriginalFilename();//获取文件名
            InputStream is = null;
            is = fileUpload.getInputStream();
            Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
            Map<String,String> mapTop=new HashMap<>();
            mapTop.put("学年标识（不可修改）","school_year");
            mapTop.put("学期标识（不可修改）","school_trem");
            mapTop.put("年级标识（不可修改）","gradeId");
            mapTop.put("年级名称","gradeName");
            mapTop.put("科目标识（不可修改）","subjectId");
            mapTop.put("科目名称","subjectName");
            mapTop.put("最大分数","maxScore");
            mapTop.put("指标名称","context");
            //解析表格数据
            List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
            //导入失败数据
            List<Map<String,Object>> errList=new ArrayList<>();
            //导入成功数据
            List<Map<String,Object>> successList=new ArrayList<>();
            //返回前端数据
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
                    bb.put("message", "指标名称重复");
                    errList.add(bb);
                }else {
                    Boolean num = literacyService.create(literacyVo, userInfo);
                    if (num) {
                        bb.put("message", "success");
                        successList.add(bb);
                    } else {
                        bb.put("message", "导入失败，请找管理员");
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
            logger.setMokuaiName("学科素养指标管理");
            logger.setType(1);
            logger.setMessage("导入学科素养指标" );
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
        map1.put("学年标识（不可修改）","school_year");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("学期标识（不可修改）","school_trem");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("年级标识（不可修改）","gradeId");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("年级名称","gradeName");
        Map<String,String>  map4=new HashMap<String,String>();
        map4.put("科目标识（不可修改）","subjectId");
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("科目名称","subjectName");
        Map<String,String>  map10=new HashMap<String,String>();
        map10.put("最大分数","maxScore");
        Map<String,String>  map12=new HashMap<String,String>();
        map12.put("指标名称","context");
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
     * 解析xls数据
     *
     */
    public List<Map<String,Object>> getJieXi(Workbook work,Map<String,String> map) throws Exception {
        if (null == work) {
            throw new Exception("创建Excel工作薄为空");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        // 返回数据
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            // 取第一行标题
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
            // 遍历当前sheet中的所有行
            for (int j = 2; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                Map<String, Object> m = new HashMap<String, Object>();
                // 遍历所有的列
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
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0.00"); // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00"); // 格式化数字

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
