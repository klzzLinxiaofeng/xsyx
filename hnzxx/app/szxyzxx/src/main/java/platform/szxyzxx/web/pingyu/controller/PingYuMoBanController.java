package platform.szxyzxx.web.pingyu.controller;


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
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.pingyumoban.service.PingYuMoBanService;
import platform.szxyzxx.pingyumoban.service.PingYuTypeService;
import platform.szxyzxx.pingyumoban.vo.PingYuMoBan;
import platform.szxyzxx.pingyumoban.vo.PingYuType;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pingyumoban")
public class PingYuMoBanController {
    Logger log = LoggerFactory.getLogger(PingYuMoBanController.class);
    @Autowired
    private PingYuMoBanService pingYuMoBanService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private PingYuTypeService pingYuTypeService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;


    @RequestMapping(value = "/allType")
    public ModelAndView findByStudent(
            @ModelAttribute("page") Page page,
            @RequestParam(value = "gradeId",required = false) Integer gradeId,
            @RequestParam(value = "pingyuId",required = false) Integer pingyuId,
            @RequestParam(value = "schoolYear",required = false) String schoolYear,
            @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
            @RequestParam(value = "subjectId",required = false) Integer subjectId,
            @RequestParam(value = "sub", required = false) String sub,
            Model model){
        List<PingYuMoBan> list = pingYuMoBanService.findByAll(pingyuId,gradeId,schoolYear,schoolTrem,subjectId, page);
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/pingyu/pingyumoban/list";
        } else {
            bathUrl = "/pingyu/pingyumoban/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping(value = "/createOrUpdate")
    public ModelAndView createOrUpdate(
            @RequestParam(value = "id",required = false) Integer id,
            Model model){
        if(id!=null && !id.equals("")){
            PingYuMoBan pingYuMoBan=pingYuMoBanService.findById(id);
            model.addAttribute("pingYuType", pingYuMoBan);
        }

        String bathUrl="/pingyu/pingyumoban/input";
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping("/create")
    public String create(@CurrentUser UserInfo userInfo,
                         PingYuMoBan pingYuMoBan){
        Integer num=pingYuMoBanService.create(pingYuMoBan);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("评语模板");
            logger.setType(1);
            logger.setMessage("新增"+pingYuMoBan.getPingYuTypeName()+"的评语模板");
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
                         PingYuMoBan pingYuMoBan){
        Integer num=pingYuMoBanService.update(pingYuMoBan);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("评语模板");
            logger.setType(2);
            logger.setMessage("修改"+pingYuMoBan.getPingYuTypeName()+"的评语模板");
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
        PingYuMoBan pingYuMoBan=pingYuMoBanService.findById(id);
        Integer num=pingYuMoBanService.updateDelete(id);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("评语模板");
            logger.setType(3);
            logger.setMessage("删除"+pingYuMoBan.getPingYuTypeName()+"的评语模板");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/findByGrade")
    public List<Grade> findByGrade(@CurrentUser UserInfo userInfo){
        List<Grade> list=gradeService.findGradeBySchoolYear(userInfo.getSchoolId(),userInfo.getSchoolYear());
        return list;
    }

    @RequestMapping("/findBypingyu")
    public List<PingYuType> findByPingJia(){
        List<PingYuType> list=pingYuTypeService.findByAll(null,null);
        return list;
    }


    //根据年级和科目获取所有学科素养指标
    @RequestMapping("/findByZhiBiao")
    public List<Map<String,Object>> findByPingJia(@RequestParam String schoolYear,
                                                  @RequestParam String schooolTrem,
                                                  @RequestParam Integer gradeId,
                                                  @RequestParam Integer subjectId){
        String sql="select pl.id,pl.literacy_name,pl.max_score from pj_literacy pl "+
        " inner join pj_subject  ps on ps.id=pl.subject_id "+
        " inner join pj_grade pg on pg.id=pl.grade_id  where pl.is_delete=0"+
              " and  pl.xq='"+schooolTrem+
              "' and   pl.xn="+schoolYear+
              " and   pl.grade_id="+gradeId+
              " and   pl.subject_id="+subjectId;
        List<Map<String,Object>> list=basicSQLService.find(sql);
        return list;
    }

    /*
    * 导出评语导入模板
    **/
    @RequestMapping("/downloadPingYuMoBan")
    public String findByTeamSubjectDaoChu(@RequestParam String schoolYear,
                                          @RequestParam String schoolTrem,
                                          @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                          @RequestParam(value = "subjectId",required = false) Integer subjectId,
                                          @RequestParam Integer mobanTypeId,
                                          HttpServletResponse response,
                                          HttpServletRequest request){
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        FengZhuan(titleList);
        //数据组装ExamList
        String sql="select  pl.xn as schoolYear,pl.xq as schoolTrem,pl.grade_id,pg.name as gradeName,pl.subject_id,ps.name as  subjectName," +
                " (select id from zy_pingyutype where is_delete=0 and id="+mobanTypeId+") as  zy_type_id ," +
                " (select name from zy_pingyutype where is_delete=0 and id="+mobanTypeId+") as mobanTypeName , " +
                " pl.id as zhibiaoId,pl.literacy_name from  pj_literacy pl " +
                " inner join pj_grade pg  on pg.id=pl.grade_id" +
                " inner join pj_subject ps on ps.id=pl.subject_id " +
                " where pl.grade_id="+gradeId+
                " and pl.is_delete=0 and pl.xn="+schoolYear+" and pl.xq='"+schoolTrem+"'";
        if(subjectId!=null && !subjectId.equals("")){
           sql+=" and pl.subject_id="+subjectId;
        }
        sql+=" group by pl.id";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        String fileName1="评语导入模板.xls";
        List<Map<String, Object>> arrayList =new ArrayList<>();
        for(Map<String, Object> aa:map){
            for(int a=0;a<3;a++){
                arrayList.add(aa);
            }
        }
        ExcelTool excelTool = new ExcelTool(fileName1,15,20);
        List<Column>  titleData=excelTool.columnTransformer(titleList);
        try {
            InputStream inputStream = excelTool.exportExcel(titleData, arrayList, true, false);
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
     * 导入评语模板
     *
     */
    @RequestMapping("/upLoadPingYuScore")
    public Map<String, Object> upLoadSubjectScore( @RequestParam("fileUpload") MultipartFile fileUpload){
        try {
            String fileName = fileUpload.getOriginalFilename();//获取文件名
            InputStream is = null;
            is = fileUpload.getInputStream();
            Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
            Map<String,String> mapTop=new HashMap<>();
            mapTop.put("学年标识（不可修改）","schoolYear");
            mapTop.put("学期标识（不可修改）","schoolTrem");
            mapTop.put("年级标识（不可修改）","grade_id");
            mapTop.put("年级名称","gradeName");
            mapTop.put("科目标识（不可修改）","subject_id");
            mapTop.put("科目名称","subjectName");
            mapTop.put("模板类型标识（不可修改）","zy_type_id");
            mapTop.put("模板类型","mobanTypeName");
            mapTop.put("指标标识（不可修改）","zhibiaoId");
            mapTop.put("指标名称","literacy_name");
            mapTop.put("起始分数","startScore");
            mapTop.put("结束分数","endScore");
            mapTop.put("评语模板内容","context");
            //解析表格数据
            List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
            //导入失败数据
            List<Map<String,Object>> errList=new ArrayList<>();
            //导入成功数据
            List<Map<String,Object>> successList=new ArrayList<>();
            //返回前端数据
            Map<String,Object> map=new HashMap<>();

            for(Map<String,Object> bb:list){
                  PingYuMoBan pingYuMoBan=new PingYuMoBan();
                  pingYuMoBan.setPingYuId(Integer.parseInt(bb.get("zy_type_id").toString()));
                  pingYuMoBan.setGradeId(Integer.parseInt(bb.get("grade_id").toString()));
                  pingYuMoBan.setText(bb.get("context").toString());
                  pingYuMoBan.setSubjectId(Integer.parseInt(bb.get("subject_id").toString()));
                  pingYuMoBan.setSubjctZhiBiaoId(Integer.parseInt(bb.get("zhibiaoId").toString()));
                  pingYuMoBan.setMinScore(Double.valueOf(bb.get("startScore").toString()).intValue());
                  pingYuMoBan.setMaxScore(Double.valueOf(bb.get("endScore").toString()).intValue());
                  pingYuMoBan.setSchoolYear(bb.get("schoolYear").toString());
                  pingYuMoBan.setSchoolTrem(bb.get("schoolTrem").toString());
                Integer num=pingYuMoBanService.create(pingYuMoBan);
                if(num>0){
                    bb.put("message","success");
                    successList.add(bb);
                }else{
                    bb.put("message","导入失败，请找管理员");
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
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("学年标识（不可修改）","schoolYear");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("学期标识（不可修改）","schoolTrem");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("年级标识（不可修改）","grade_id");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("年级名称","gradeName");
        Map<String,String>  map4=new HashMap<String,String>();
        map4.put("科目标识（不可修改）","subject_id");
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("科目名称","subjectName");
        Map<String,String>  map6=new HashMap<String,String>();
        map6.put("模板类型标识（不可修改）","zy_type_id");
        Map<String,String>  map7=new HashMap<String,String>();
        map7.put("模板类型","mobanTypeName");
        Map<String,String>  map8=new HashMap<String,String>();
        map8.put("指标标识（不可修改）","zhibiaoId");
        Map<String,String>  map9=new HashMap<String,String>();
        map9.put("指标名称","literacy_name");
        Map<String,String>  map10=new HashMap<String,String>();
        map10.put("起始分数","startScore");
        Map<String,String>  map11=new HashMap<String,String>();
        map11.put("结束分数","endScore");
        Map<String,String>  map12=new HashMap<String,String>();
        map12.put("评语模板内容","context");
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
        list.add(map10);
        list.add(map11);
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
