package platform.szxyzxx.web.teach.controller;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.ExamQuestionVoService;
import platform.education.generalTeachingAffair.service.ExamTeamSubjectService;
import platform.szxyzxx.util.Column;
import platform.szxyzxx.util.ExcelTool;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/examquestion")
public class ExamQuestionController extends BaseController {
    Logger log = LoggerFactory.getLogger(ExamQuestionController.class);
    @Autowired
    private ExamQuestionVoService examTeamSubjectService;

    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private ExamTeamSubjectService examService;

    @RequestMapping("/viewMdel")
    public ModelAndView findView(@RequestParam Integer examId, Model model){
        model.addAttribute("examId",examId);
        return new ModelAndView("/teach/examTeamSubject/question/QuestionIndex",model.asMap());
    }


    @RequestMapping("/findByAll")
    public List<ExamQuestionVo> findByAll(@RequestParam Integer examId){

        return examTeamSubjectService.findByAll(examId);
    }
    @RequestMapping("/updateFenzhi")
    public String updateFenzhi(@RequestParam Integer examId,
                               @RequestParam String fenzhi){
       Integer num= examTeamSubjectService.updateFenzhi(examId,fenzhi);
       if(num>0){
           return "success";
       }else{
           return "error";
       }
    }

    @RequestMapping("/findByDonwNoled")
    public ModelAndView findByDonwNoled(@RequestParam Integer examId, Model model){
        model.addAttribute("examId",examId);
        ExamTeamSubject examTeamSubject=examService.findByExamId(examId);
        model.addAttribute("exam",examTeamSubject);
        String sql="select * from pj_exam_team_subject_question where is_delete=0 and  exam_team_subject_id="+examId;
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        for(Map<String,Object> aa:mapList){
            if(aa.get("fenzhi").toString().equals("") || Double.valueOf(aa.get("fenzhi").toString()).intValue()==0){
                model.addAttribute("fenzhiScore","error");
            }
        }
        return new ModelAndView("/teach/examTeamSubject/question/donkdeTiMu",model.asMap());
    }



    /**
     * 下载成绩分析导入成绩模板
     *
     * @throws IOException
     */
    @RequestMapping(value = "/downLoadChnegJiScore")
    public String downLoadSubject(@CurrentUser UserInfo user,
                                  @RequestParam("xn") String xn,
                                  @RequestParam("xq") String xq,
                                  @RequestParam("gradeId") Integer gradeId,
                                  @RequestParam("teamId") Integer teamId,
                                  @RequestParam("subjectCode") String subjectCode,
                                  @RequestParam("examCode") String examCode,
                                  @RequestParam("examType") String examType,
                                  @RequestParam("examId") Integer examId,
                                  HttpServletResponse response, HttpServletRequest request) {
        ExamQuestionVo subjectScorePojo=new ExamQuestionVo();
        subjectScorePojo.setSchoolYear(xn);
        subjectScorePojo.setSchoolTrem(xq);
        subjectScorePojo.setGradeId(gradeId);
        subjectScorePojo.setSubjectCode(subjectCode);
        subjectScorePojo.setTeamId(teamId);
        subjectScorePojo.setExamType(examType);
        subjectScorePojo.setExamName(examCode);
        subjectScorePojo.setExamTeamSubjectId(examId);
        Subject subject=subjectService.findUnique(user.getSchoolId(),null,subjectCode);
        Team team=teamService.findTeamById(teamId);
        Grade grade=gradeService.findGradeById(gradeId);
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("考试日程标识(不可修改)","id");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("学生程序标识(不可修改)","studentId");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("学号(不可修改)","empCode");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("姓名","stuName");//d_name
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        titleList.add(map3);
        //查出表头
        List<ExamQuestionVo> literacyVoList=examTeamSubjectService.findByTop(subjectScorePojo);
        for (int i=0;i<literacyVoList.size();i++) {
            Map<String,String>  map4=new HashMap<String,String>();
            Integer showName = literacyVoList.get(i).getOrder();
            map4.put("第"+showName+"题","data"+i);
            titleList.add(map4);
        }
        log.info("长度"+titleList.size());
        //数据组装
        //查询数据
        List<Map<String,String>> fromDataList =getSubjects(examId,user,subjectScorePojo);
        String fileName1=team.getName()+"("+subject.getName()+")"+examCode+"考试模板.xls";
        ExcelTool excelTool = new ExcelTool(fileName1,15,20);
        List<Column>  titleData=excelTool.columnTransformer(titleList);
        try {
            InputStream inputStream = excelTool.exportExcel(titleData, fromDataList, true, false);
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


    @RequestMapping("/findByDaoRu")
    public ModelAndView findByDaoRu(@CurrentUser UserInfo user,
                                    @RequestParam("xn") String xn,
                                    @RequestParam("xq") String xq,
                                    @RequestParam("gradeId") Integer gradeId,
                                    @RequestParam("teamId") Integer teamId,
                                    @RequestParam("subjectCode") String subjectCode,
                                    @RequestParam("examCode") String examCode,
                                    @RequestParam("examType") String examType,
                                    @RequestParam("examId") Integer examId, Model model){
        model.addAttribute("examId",examId);
        model.addAttribute("xn",xn);
        model.addAttribute("xq",xq);
        model.addAttribute("gradeId",gradeId);
        model.addAttribute("teamId",teamId);
        model.addAttribute("subjectCode",subjectCode);
        model.addAttribute("examCode",examCode);
        model.addAttribute("examType",examType);
        return new ModelAndView("/teach/examTeamSubject/question/daoru",model.asMap());
    }
    //组装模板导出所需的数据
    private List<Map<String,String>> getSubjects(Integer examId,UserInfo user,ExamQuestionVo examQuestionVo){
        List<ExamQuestionVo> literacyVoList=examTeamSubjectService.findByTop(examQuestionVo);
        log.info("literacyVoList长度:"+literacyVoList.size());
        List<Map<String,String>> literacyStudents=new ArrayList<>();
        List<Student> studentList=studentService.findStudentOfTeam2(examQuestionVo.getTeamId(),examQuestionVo.getGradeId());
        for(Student aa: studentList){
            Map<String, String> map=new HashMap<>();
            map.put("id",examId.toString());
            map.put("studentId",aa.getId().toString());
            map.put("empCode",aa.getEmpCode());
            map.put("stuName",aa.getName());
            for (int i=0;i<literacyVoList.size();i++) {
                map.put("data"+i,"");
            }
            literacyStudents.add(map);
        }
        return literacyStudents;
    }


    /*
     * 导入考试题目成绩
     *
     */
    @RequestMapping("/upLoadSubjectScore")
    @ResponseBody
    public Map<String, Object> upLoadSubjectScore( @RequestParam("fileUpload") MultipartFile fileUpload,
                                                   @ModelAttribute("examQuestionVo") ExamQuestionVo examQuestionVo,
                                                   @CurrentUser UserInfo user){
        Team team=teamService.findTeamById(examQuestionVo.getTeamId());
        try {
            String fileName = fileUpload.getOriginalFilename();//获取文件名
            String gardeIds=fileName.substring(0,3);
            InputStream is = null;
            is = fileUpload.getInputStream();
            Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
            Map<String,String> mapTop=new HashMap<>();
            mapTop.put("考试日程标识(不可修改)","id");
            mapTop.put("学生程序标识(不可修改)","studentId");
            mapTop.put("学号(不可修改)","empCode");
            mapTop.put("姓名","stuName");
            //查出表头
            examQuestionVo.setSchoolTrem(null);
            List<ExamQuestionVo> literacyVoList=examTeamSubjectService.findByTop(examQuestionVo);
            log.info("表头"+literacyVoList.size());
            for(int a=0;a<literacyVoList.size();a++){
                mapTop.put("第"+literacyVoList.get(a).getOrder()+"题","data"+a);
            }
            List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
            //导入失败数据
            List<Map<String,Object>> errList=new ArrayList<>();
            //导入成功数据
            List<Map<String,Object>> successList=new ArrayList<>();
            //返回前端数据
            Map<String,Object> map=new HashMap<>();

            for(Map<String,Object> bb:list){
                Integer studentId=Integer.parseInt(bb.get("studentId").toString());
                int i=0;
                Double num=0.00;
                 for(int a=0;a<literacyVoList.size();a++){
                    //题目的最大分数
                    Integer maxScore=literacyVoList.get(a).getFenzhi();
                    //导入的分数
                    double score=Double.parseDouble(bb.get("data"+a).toString());
                    if(score<=0){
                        if(score+maxScore>=0){
                            //此处id为examId
                            Boolean falg= examTeamSubjectService.updateScore(studentId,literacyVoList.get(a).getId(),maxScore+score);
                            if(falg){
                                num+=score+maxScore;
                            }else{
                                bb.put("error","未查询到此考试的"+literacyVoList.get(a).getOrder()+"信息");
                                errList.add(bb);
                                i++;
                                break;
                            }
                        }else{
                            System.out.println("姓名"+bb.get("stuName"));
                            String liName="第"+literacyVoList.get(a).getOrder()+"题";
                            bb.put("error",liName+"分值不能小于-"+maxScore);
                            errList.add(bb);
                            i++;
                            break;
                        }

                    }else{
                        System.out.println("姓名"+bb.get("stuName"));
                        String liName="第"+literacyVoList.get(a).getOrder()+"题";
                        bb.put("error",liName+"分值不能大于0");
                        errList.add(bb);
                        i++;
                        break;
                    }
                }
                 if(i==0){
                     bb.put("success","添加成功");
                     successList.add(bb);
                     StudentScore studentScore2=new StudentScore();
                     studentScore2.setScore(num.toString());
                     studentScore2.setExamTeamSubjectId(Integer.parseInt(bb.get("id").toString()));
                     studentScore2.setStudentId(studentId);
                     StudentScore studentScore=examTeamSubjectService.findByStudentScore(studentId, Integer.parseInt(bb.get("id").toString()));
                     if(studentScore!=null){
                         examTeamSubjectService.updateScores(studentScore2);
                     }else{
                         studentScore2.setSubjectCode(examQuestionVo.getSubjectCode());
                         studentScore2.setCreateDate(new Date());
                         studentScore2.setModifyDate(new Date());
                         studentScore2.setSchoolYear(examQuestionVo.getSchoolYear());
                         if(literacyVoList.size()>0){
                             studentScore2.setTermCode(literacyVoList.get(0).getSchoolTrem());
                         }else{
                             studentScore2.setTermCode(user.getSchoolTermCode());
                         }
                         studentScore2.setExamName(examQuestionVo.getExamName());
                         studentScore2.setExamType(examQuestionVo.getExamType());
                         studentScore2.setGradeId(examQuestionVo.getGradeId());
                         studentScore2.setTeamId(examQuestionVo.getTeamId());
                         studentScore2.setSchoolId(user.getSchoolId());
                        studentScoreService.add(studentScore2);
                     }
                 }
            }
            if(list.size()>0){
                examTeamSubjectService.updateTaskRate(Integer.parseInt(list.get(0).get("id").toString()));
            }
             map.put("status","success");
            map.put("error",errList);
            map.put("success",successList);
            map.put("biaotou",literacyVoList);
            log.info("errList"+errList.size()+"++++success"+successList.size()+"----"+literacyVoList.size());
            return  map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
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
