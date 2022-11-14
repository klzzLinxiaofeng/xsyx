package platform.szxyzxx.web.teach.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.ExamQuestionVo;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.ExamQuestionVoService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.szxyzxx.exam.service.ExamScoreService;
import platform.szxyzxx.exam.vo.ExamQuery;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/scoreFenXi")
public class ExamScoreFenXiController extends BaseController {
    Logger log = LoggerFactory.getLogger(ExamScoreFenXiController.class);
    @Autowired
   private ExamScoreService examScoreService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private ExamQuestionVoService examQuestionVoService;
    @Autowired
    private TeamService teamService;

    /*
     *个人题目得分成绩分析
     */
    @RequestMapping("/findByscoreFenXiGeRen")
    public ModelAndView findByscoreFenXi(@RequestParam String sub){
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/grrenindex");
    }



    @RequestMapping("/findByscoreFenXi")
    public  Map<String, Object> findByscoreFenXi(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
                Map<String, Object> map = examScoreService.findByGrrenScore(userInfo, examQuery, null);
        return map;
    }

    /*
    *学生成绩
    */
    @RequestMapping("/findByscoreStudentScoreView")
    public ModelAndView findByscoreStudentScoreView(@CurrentUser UserInfo userInfo, Model model){
        String sql="select yr.* from yh_user_role yur inner join yh_role yr on yur.role_id=yr.id where yur.is_deleted=0 and yur.user_id="+userInfo.getId();
        List<Map<String,Object>> map=basicSQLService.find(sql);
        for(Map<String,Object> aa:map){
            if(aa!=null){
                if(aa.get("code").toString().equals("JIAODAO_ZHUREN")||aa.get("code").toString().equals("CHENGJIFENXI_GUANLIYUAN")){
                    model.addAttribute("juese","success");
                }
            }
        }
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/studentScore");
    }
    /*
     *学生成绩列表
     */
    @RequestMapping("/findByscoreStudentScore")
    public List<Map<String, Object>> findByscoreStudentScore(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
        List<Map<String, Object>> map = examScoreService.findByscoreStudentScore(userInfo, examQuery);
        return map;
    }


    /*
     *班级题目得分成绩分析
     */
    @RequestMapping("/findByTiMuFenXiView")
    public ModelAndView findByTiMuFenXiView(){
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/teamindex");
    }
    /*
    * 班级题目得分
    */
    @RequestMapping("/findByTiMuFenXi")
    public List<Map<String, Object>> findByTiMuFenXi(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
        List<Map<String, Object>> map = examScoreService.findByTeamScore(userInfo, examQuery);
        return map;
    }
    /*
     *年级题目得分成绩分析
     */
    @RequestMapping("/findByTiMuGradeFenXiView")
    public ModelAndView findByTiMuGradeFenXiView(){
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/gradeindex");
    }
    /*
     * 年级题目得分
     */
    @RequestMapping("/findByGradeTiMuFenXi")
    public Map<String, Object> findByGradeTiMuFenXi(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
        Map<String, Object> map = examScoreService.findByGradeScore(userInfo, examQuery);
        return map;
    }

    /*
     *分数段分析跳转
     */
    @RequestMapping("/findByFenShuDuanFenXiView")
    public ModelAndView findByFenShuDuanFenXiView(){
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/fenshuDuan");
    }
    /*
     * 分数段分析
     */
    @RequestMapping("/findByFenShuDuanFenXi")
    public List<Map<String, Object>> findByFenShuDuanFenXi(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
        List<Map<String, Object>> map = examScoreService.findByFenShuDuanFenXi(userInfo, examQuery);
        return map;
    }


    @RequestMapping("/findByExamType")
    public List<Map<String, Object>> findByExamType(@CurrentUser UserInfo userInfo,
                                                    @RequestParam String schoolYear,
                                                    @RequestParam String schoolTrem,
                                                    @RequestParam(value = "teamId",required = false) Integer teamId,
                                                    @RequestParam(value = "gradeId",required = false) Integer gradeId) {
        String sql="select * from pj_exam_team_subject where is_delete=0 and school_id="+userInfo.getSchoolId()+" and school_year="+schoolYear+" and term_code='"+schoolTrem+"'";
        if(teamId!=null){
            sql+=" and team_id="+teamId;
        }
        if(gradeId!=null){
            List<Team> list=teamService.findTeamOfGrade(gradeId);
            sql+=" and team_id in (";
            for(int a=0;a<list.size();a++){
                if(list.size()-a==1){
                    sql+=list.get(a).getId()+")";
                }else{
                    sql+=list.get(a).getId()+",";
                }

            }
        }
        sql+=" group by exam_type";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }

    @RequestMapping("/findBySubject")
    public List<Map<String, Object>> findBySubject(@CurrentUser UserInfo userInfo,
                                                    @RequestParam String schoolYear,
                                                    @RequestParam String schoolTrem,
                                                   @RequestParam(value = "teamId",required = false) Integer teamId,
                                                    @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                                   @RequestParam(value = "type",required = false) Integer type) {
        String sql="select ps.* from pj_exam_team_subject  pets inner join pj_subject ps  on ps.code=pets.subject_code where pets.is_delete=0 and pets.school_id="+userInfo.getSchoolId()+" and pets.school_year="+schoolYear+" and pets.term_code='"+schoolTrem+"'  and pets.exam_type="+type;
        if(gradeId!=null){
            List<Team> list=teamService.findTeamOfGrade(gradeId);
            sql+=" and pets.team_id in (";
            for(int a=0;a<list.size();a++){
                if(list.size()-a==1){
                    sql+=list.get(a).getId()+")";
                }else{
                    sql+=list.get(a).getId()+",";
                }
            }
        }
        if(teamId!=null){
            sql+=" and pets.team_id="+teamId ;
        }
        sql+=" group by pets.subject_code";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


    @RequestMapping("/findByExamName")
    public List<Map<String, Object>> findByExamName(@CurrentUser UserInfo userInfo,
                                                    @RequestParam String schoolYear,
                                                    @RequestParam String schoolTrem,
                                                    @RequestParam Integer teamId,
                                                    @RequestParam Integer gradeId,
                                                    @RequestParam Integer examType){
        String sql="select * from pj_exam_team_subject where is_delete=0 and school_id="+userInfo.getSchoolId()+" and school_year="+schoolYear+" and term_code='"+schoolTrem+"' and team_id="+teamId+" and exam_type="+examType;

        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


    /*
     * 导出个人题目得分分析
     */
    @RequestMapping("/daochuGeRenFenXi")
    public String daochuGeRenFenXi(@CurrentUser UserInfo userInfo,
                                   @RequestParam String schoolYear,
                                   @RequestParam String schoolTrem,
                                   @RequestParam Integer gradeId,
                                   @RequestParam Integer teamId,
                                   @RequestParam Integer examId,
                                   @RequestParam String subjectCode,
                                   HttpServletResponse response,
                                   HttpServletRequest request){
        ExamQuery examQuery=new ExamQuery();
        examQuery.setSchoolYear(schoolYear);
        examQuery.setSchoolTrem(schoolTrem);
        examQuery.setGradeId(gradeId);
        examQuery.setTeamId(teamId);
        examQuery.setExamId(examId);
        examQuery.setSubjectCode(subjectCode);
        ExamQuestionVo subjectScorePojo=new ExamQuestionVo();
        subjectScorePojo.setSchoolYear(examQuery.getSchoolYear());
        subjectScorePojo.setSchoolTrem(examQuery.getSchoolTrem());
        subjectScorePojo.setGradeId(examQuery.getGradeId());
        subjectScorePojo.setSubjectCode(examQuery.getSubjectCode());
        subjectScorePojo.setTeamId(examQuery.getTeamId());
        subjectScorePojo.setExamName(examQuery.getExamName());
        Subject subject=subjectService.findUnique(userInfo.getSchoolId(),null,examQuery.getSubjectCode());
        Team  team=teamService.findTeamById(examQuery.getTeamId());
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("学号","empCode");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("姓名","stuName");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("总分","zongfen");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("教师姓名","teacherName");//d_name
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        //查出表头
        List<ExamQuestionVo> literacyVoList=examQuestionVoService.findByTop(subjectScorePojo);
        for (int i=0;i<literacyVoList.size();i++) {
            Map<String,String>  map4=new HashMap<String,String>();
            Integer showName = literacyVoList.get(i).getOrder();
            map4.put("第"+showName+"题","data_"+i);
            titleList.add(map4);
        }
        titleList.add(map3);
        //数据组装
        //查询数据
        List<Map<String,String>> fromDataList =(List<Map<String,String>>)examScoreService.findByGrrenScoreDaochu(userInfo, examQuery, null).get("list");
        String fileName1="("+team.getName()+subject.getName()+")个人题目得分分析.xls";
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


    /*
     * 导出班级题目得分分析
     */
    @RequestMapping("/daochuTeamFenXi")
    public String daochuTeamFenXi(@CurrentUser UserInfo userInfo,
                                  @RequestParam String schoolYear,
                                  @RequestParam String schoolTrem,
                                  @RequestParam Integer gradeId,
                                  @RequestParam Integer teamId,
                                  @RequestParam Integer examId,
                                  @RequestParam String subjectCode,
                                  HttpServletResponse response,
                                  HttpServletRequest request){
        ExamQuery examQuery=new ExamQuery();
        examQuery.setSchoolYear(schoolYear);
        examQuery.setSchoolTrem(schoolTrem);
        examQuery.setGradeId(gradeId);
        examQuery.setTeamId(teamId);
        examQuery.setExamId(examId);
        examQuery.setSubjectCode(subjectCode);
        Grade grade=gradeService.findGradeById(examQuery.getGradeId());
        Team  team=teamService.findTeamById(examQuery.getTeamId());
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("题号","tihao");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("分值","fenzhi");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("班级百分比","teamBaifenbi");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("满分率","mfl");//d_name
        Map<String,String>  map4=new HashMap<String,String>();
        map4.put("优秀率","yxl");//d_name
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("合格率","hgl");//d_name
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        titleList.add(map3);
        titleList.add(map4);
        titleList.add(map5);
        List<Map<String, Object>> map = examScoreService.findByTeamScoreDaoChu(userInfo, examQuery);
        String fileName1=grade.getName()+"("+team.getName()+")班级题目得分分析.xls";
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
     * 导出年级题目得分分析
     */
    @RequestMapping("/daochuGradeFenXi")
    public String daochuGradeFenXi(@CurrentUser UserInfo userInfo,
                                   @RequestParam String schoolYear,
                                   @RequestParam String schoolTrem,
                                   @RequestParam Integer gradeId,
                                   @RequestParam String examType,
                                   @RequestParam(value ="examName",required = false) String examName,
                                   @RequestParam String subjectCode,
                                   HttpServletResponse response,
                                   HttpServletRequest request){
        ExamQuery examQuery=new ExamQuery();
        examQuery.setSchoolYear(schoolYear);
        examQuery.setSchoolTrem(schoolTrem);
        examQuery.setGradeId(gradeId);
        if(examName!=null){
            examQuery.setExamName(examName);
        }
        examQuery.setExamType(Integer.parseInt(examType));
        examQuery.setSubjectCode(subjectCode);

        Subject subject=subjectService.findUnique(userInfo.getSchoolId(),null,examQuery.getSubjectCode());
        Grade grade=gradeService.findGradeById(examQuery.getGradeId());
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop6=new HashMap<String,String>();
        mapTop6.put("考试名称","examName");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("班级名称","teamName");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("班级人数","teamSize");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("参考人数","cankao");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("总分","zongfen");//d_name
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("任课教师","teacher");//d_name
        titleList.add(mapTop6);
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        titleList.add(map3);
        //查出表头
        Map<String,Object> maps =examScoreService.findByGradeScoreDaochu(userInfo, examQuery);
        List<ExamQuestionVo> literacyVoList=(List<ExamQuestionVo>)maps.get("ExamList");
        log.info("大小"+literacyVoList.size());
        for (int i=0;i<literacyVoList.size();i++) {
            Map<String,String>  map4=new HashMap<String,String>();
            Integer showName = literacyVoList.get(i).getOrder();
            map4.put("第"+showName+"题","data_"+i);
            titleList.add(map4);
        }
        titleList.add(map5);
        //数据组装ExamList
        //查询数据

        List<Map<String,Object>> fromDataList=(List<Map<String,Object>>)maps.get("list");
        String fileName1="("+grade.getName()+subject.getName()+")题目得分分析.xls";
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
    /*
     * 导出分数段
     */
    @RequestMapping("/daochuFenShuDuan")
    public String daochuFenShuDuan(@CurrentUser UserInfo userInfo,
                                   @RequestParam String schoolYear,
                                   @RequestParam String schoolTrem,
                                   @RequestParam(value ="gradeId",required = false) Integer gradeId,
                                   @RequestParam Integer examType,
                                   @RequestParam(value ="examName",required = false) String examName,
                                   @RequestParam String subjectCode,
                                   HttpServletResponse response,
                                   HttpServletRequest request){
        ExamQuery examQuery=new ExamQuery();
        examQuery.setSchoolYear(schoolYear);
        examQuery.setSchoolTrem(schoolTrem);
        examQuery.setGradeId(gradeId);
        examQuery.setExamType(examType);
        if(examName!=null){
            examQuery.setExamName(examName);
        }
        examQuery.setSubjectCode(subjectCode);
        Subject subject=subjectService.findUnique(userInfo.getSchoolId(),null,subjectCode);
        String fileName="";
        if(gradeId!=null){
            Grade grade=gradeService.findGradeById(gradeId);
            fileName=grade.getName();
        }else{
            fileName="全校";
        }
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop12=new HashMap<String,String>();
        mapTop12.put("考试名称","examName");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("班级名称","teamName");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("班级人数","teamSize");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("参考人数","cankao");

        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("总分","zongfen");//d_name
        Map<String,String>  map4=new HashMap<String,String>();
        map4.put("平均分","svg");//d_name
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("A++","A1");//d_name
        Map<String,String>  map6=new HashMap<String,String>();
        map6.put("A+","A2");//d_name
        Map<String,String>  map7=new HashMap<String,String>();
        map7.put("A","A");//d_name
        Map<String,String>  map8=new HashMap<String,String>();
        map8.put("B","B");//d_name
        Map<String,String>  map9=new HashMap<String,String>();
        map9.put("C","C");//d_name
        Map<String,String>  map10=new HashMap<String,String>();
        map10.put("D","D");//d_name
        Map<String,String>  map11=new HashMap<String,String>();
        map11.put("任课教师","teacher");//d_name
        titleList.add(mapTop12);
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        titleList.add(map3);
        titleList.add(map4);
        titleList.add(map5);
        titleList.add(map6);
        titleList.add(map7);
        titleList.add(map8);
        titleList.add(map9);
        titleList.add(map10);
        titleList.add(map11);
        //数据组装
        //查询数据
        List<Map<String, Object>> map = examScoreService.findByFenShuDuanFenXi(userInfo, examQuery);
        String fileName1="("+fileName+subject.getName()+")分数段.xls";
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
     * 导出学生成绩
     */
    @RequestMapping("/daochuStudentFenXi")
    public String daochuStudentFenXi(@CurrentUser UserInfo userInfo,
                                     @RequestParam String schoolYear,
                                     @RequestParam String schoolTrem,
                                     @RequestParam Integer gradeId,
                                     @RequestParam Integer teamId,
                                     @RequestParam Integer examId,
                                     @RequestParam Integer examType,
                                     @RequestParam String subjectCode,
                                   HttpServletResponse response,
                                   HttpServletRequest request){
        ExamQuery examQuery=new ExamQuery();
        examQuery.setSchoolYear(schoolYear);
        examQuery.setSchoolTrem(schoolTrem);
        examQuery.setGradeId(gradeId);
        examQuery.setExamId(examId);
        examQuery.setExamType(examType);
        examQuery.setTeamId(teamId);
        examQuery.setSubjectCode(subjectCode);
        ExamQuestionVo subjectScorePojo=new ExamQuestionVo();
        subjectScorePojo.setSchoolYear(examQuery.getSchoolYear());
        subjectScorePojo.setSchoolTrem(examQuery.getSchoolTrem());
        subjectScorePojo.setGradeId(examQuery.getGradeId());
        subjectScorePojo.setSubjectCode(examQuery.getSubjectCode());
        subjectScorePojo.setTeamId(examQuery.getTeamId());
        subjectScorePojo.setExamName(examQuery.getExamName());
        Subject subject=subjectService.findUnique(userInfo.getSchoolId(),null,examQuery.getSubjectCode());
        Grade grade=gradeService.findGradeById(examQuery.getGradeId());
        Team  team=teamService.findTeamById(examQuery.getTeamId());
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("学号","empCode");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("姓名","stuName");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("得分","zongfen");
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        //数据组装
        //查询数据
        List<Map<String, Object>> map = examScoreService.findByscoreStudentScore(userInfo, examQuery);
        String fileName1="("+team.getName()+subject.getName()+")成绩.xls";
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
    * 成绩锁定功能 0未锁定，1锁定
    */
    @RequestMapping("/updateSuoDing")
    public  String suoding(@RequestParam Integer examId,
                           @RequestParam Integer zhuangtai){
        int num=examScoreService.updateExamSubject(examId,zhuangtai);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }


    /*
     * 下拉框年级列表（成绩分析专用）
     */
    @RequestMapping("/findByGradeId")
    public  List<Map<String,Object>> findByGradeId(@CurrentUser UserInfo userInfo,
                           @RequestParam String schoolYear){
        String sql = "select yr.* from pj_teacher pt inner join  yh_user_role yur on yur.user_id=pt.user_id inner join yh_role yr on yr.id=yur.role_id where pt.user_id="+userInfo.getId();

        List<Map<String,Object>> roleList=basicSQLService.find(sql);
        Boolean flag=false;
        for(Map<String,Object> aa:roleList){
            if(aa.get("code").toString().equals("CHENGJIFENXI_GUANLIYUAN")
                    || aa.get("code").toString().equals("SCHOOL_MASTER")
                    ||  aa.get("code").toString().equals("SCHOOL_MANAGER")
                    || aa.get("code").toString().equals("NIANJIZU")
                    || aa.get("code").toString().equals("XING_ZHENG_REN_YUAN")){
                flag=true;
                break;
            }
        }
        if(flag){
            List<Map<String,Object>> gradeList=basicSQLService.find("select id,name from pj_grade where school_year='"+schoolYear+"'  and is_deleted=0 order by grade_number" );
            return gradeList;
        }else{
            List<Map<String,Object>> gradeList=basicSQLService.find("select pg.id,pg.name from pj_team_teacher ptt inner join pj_grade pg on ptt.grade_id=pg.id  where ptt.user_id="+userInfo.getId()+" and  ptt.school_year='"+schoolYear+"' and ptt.is_delete=0 group by ptt.grade_id order by grade_number" );
            return gradeList;
        }
    }
    /*
     *各班成绩分析
     */
    @RequestMapping("/findByTeamView")
    public ModelAndView findByTeamView(){
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/Index");
    }
    /*
     * 班级得分分析
     */
    @RequestMapping("/findByTeamFenXi")
    public List<Map<String, Object>> findByTeamFenXi(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
        String sql="select pt.id,pt.name,pets.exam_name as examName, sum(pss.score) as zongfen," +
                " CONVERT(sum(pss.score)/count( IF( pss.score != 0, 1, NULL )),DECIMAL(15,2)) as pingjunfen," +
                " count(IF( pss.score != 0 and pss.score>=60, 1, NULL )) as jgrs ," +
                " count( IF( pss.score != 0 and pss.score>=85, 1, NULL )) as yxrs," +
                " count( IF( pss.score != 0 and pss.score=100, 1, NULL )) as mfrs," +
                " CONVERT(count( IF( pss.score != 0 and pss.score>=85, 1, NULL ))/count( IF( pss.score != 0, 1, NULL ))*100,DECIMAL(15,2)) as yxl," +
                " CONVERT(count( IF( pss.score != 0 and pss.score>=60, 1, NULL ))/count( IF( pss.score != 0, 1, NULL ))*100,DECIMAL(15,2)) as jgl from  pj_student_score pss " +
                " inner join pj_team pt on pt.id=pss.team_id " +
                " inner join pj_exam_team_subject  pets on pets.id=pss.exam_team_subject_id"+
                "  where pss.school_year="+examQuery.getSchoolYear()+" and pss.term_code='"+examQuery.getSchoolTrem()+"' and pss.exam_type="+examQuery.getExamType()+" and pss.subject_code='"+examQuery.getSubjectCode()+"'  and pss.is_delete=0 and pets.is_delete=0   ";

        if(examQuery.getExamName()!=null){
            sql+=" and pss.exam_name like '%"+examQuery.getExamName()+"%'";
        }
        if(examQuery.getGradeId()==null){

        }else{
           sql+=" and pss.grade_id="+examQuery.getGradeId();
        }
        sql+=" group by pets.id";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }

    /*
     * 导出班级分析
     */
    @RequestMapping("/daochuTeam")
    public String daochuTeam(@RequestParam String schoolYear,
                                   @RequestParam String schoolTrem,
                                   @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                   @RequestParam String examType,
                                    @RequestParam(value = "examName",required = false) String examName,
                                   @RequestParam String subjectCode,
                                   HttpServletResponse response,
                                   HttpServletRequest request){
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String> mapTop01=new HashMap<String,String>();
        mapTop01.put("考试名称","examName");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("班级名称","name");
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("总分","zongfen");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("平均分","pingjunfen");
        Map<String,String>  map3=new HashMap<String,String>();
        map3.put("及格率","jgl");//d_name
        Map<String,String>  map5=new HashMap<String,String>();
        map5.put("优秀率","yxl");//d_name
        Map<String,String>  map6=new HashMap<String,String>();
        map5.put("满分人数","mfrs");//d_name
        Map<String,String>  map7=new HashMap<String,String>();
        map5.put("优秀人数","yxrs");//d_name
        Map<String,String>  map8=new HashMap<String,String>();
        map5.put("及格人数","jgrs");//d_name
        titleList.add(mapTop01);
        titleList.add(mapTop);
        titleList.add(map1);
        titleList.add(map2);
        titleList.add(map3);
        titleList.add(map5);
        titleList.add(map6);
        titleList.add(map7);
        titleList.add(map8);
        //数据组装ExamList
        //查询数据
        String sql="select pt.id,pt.name,pets.exam_name as examName, sum(pss.score) as zongfen," +
                " CONVERT(sum(pss.score)/count( IF( pss.score != 0, 1, NULL )),DECIMAL(15,2)) as pingjunfen," +
                " count(IF( pss.score != 0 and pss.score>=60, 1, NULL )) as jgrs ," +
                " count( IF( pss.score != 0 and pss.score>=85, 1, NULL )) as yxrs," +
                " count( IF( pss.score != 0 and pss.score=100, 1, NULL )) as mfrs," +
                " CONVERT(count( IF( pss.score != 0 and pss.score>=85, 1, NULL ))/count( IF( pss.score != 0, 1, NULL ))*100,DECIMAL(15,2)) as yxl," +
                " CONVERT(count( IF( pss.score != 0 and pss.score>=60, 1, NULL ))/count( IF( pss.score != 0, 1, NULL ))*100,DECIMAL(15,2)) as jgl from  pj_student_score pss " +
                " inner join pj_team pt on pt.id=pss.team_id " +
                " inner join pj_exam_team_subject  pets on pets.id=pss.exam_team_subject_id"+
                " where pss.school_year="+schoolYear+" and pss.term_code='"+schoolTrem+"' and pss.exam_type="+examType+" and pss.subject_code='"+subjectCode+"'  and pss.is_delete=0 ";

        if(examName!=null){
            sql+=" and pss.exam_name like '%"+examName+"%' ";
        }
        if(gradeId==null){

        }else{
            sql+=" and pss.grade_id="+gradeId;
        }
        sql+=" group by pets.id";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        String fileName1="班级得分分析.xls";
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
    * 班级各科成绩统计
    */
    @RequestMapping("/findByTeamSubjectView")
    public ModelAndView findByTeamSubjectView(){
        return new ModelAndView("/teach/examTeamSubject/chengjiFenXi/teamSubjectIndex");
    }

    /*
     * 班级各科成绩统计
     */
    @RequestMapping("/findByTeamSubject")
    public List<Map<String, Object>> findByTeamSubject(ExamQuery examQuery, @CurrentUser UserInfo userInfo){
        String sql="select nmps.name,ps.emp_code, sum(pss.score) as zongfen from pj_exam_team_subject pets " +
                " inner join pj_student_score pss on pss.exam_team_subject_id=pets.id " +
                " INNER JOIN pj_student ps on ps.id=pss.student_id " +
                " where pets.is_delete=0 and  pets.exam_type="+examQuery.getExamType()+" and pets.subject_code in ("+examQuery.getSubjectCode()+") and pets.school_year="+examQuery.getSchoolYear() +
                " and pets.term_code='"+examQuery.getSchoolTrem()+"'  and pets.team_id="+examQuery.getTeamId()+" and pets.exam_name like '%"+examQuery.getExamName()+"%'  group by ps.id order by  zongfen desc";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        return map;
    }


    /*
     * 导出班级各科成绩统计
     */
    @RequestMapping("/findByTeamSubjectDaoChu")
    public String findByTeamSubjectDaoChu(@CurrentUser UserInfo userInfo,
                                         @RequestParam String schoolYear,
                                         @RequestParam String schoolTrem,
                                         @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                          @RequestParam Integer teamId ,
                                         @RequestParam String examType,
                                          @RequestParam String examName,
                                         @RequestParam String subjectCode,
                             HttpServletResponse response,
                             HttpServletRequest request){
        //表格头部组装
        List<Map<String,String>> titleList=new ArrayList<>();
        Map<String,String>  map1=new HashMap<String,String>();
        map1.put("姓名","name");
        Map<String,String> mapTop=new HashMap<String,String>();
        mapTop.put("学号","emp_code");
        Map<String,String>  map2=new HashMap<String,String>();
        map2.put("总分","zongfen");
        titleList.add(map1);
        titleList.add(mapTop);
        titleList.add(map2);
        //数据组装ExamList
        String sql="select ps.name,ps.emp_code, sum(pss.score) as zongfen from pj_exam_team_subject pets " +
                " inner join pj_student_score pss on pss.exam_team_subject_id=pets.id " +
                " INNER JOIN pj_student ps on ps.id=pss.student_id " +
                " where pets.is_delete=0 and  pets.exam_type="+examType+" and pets.subject_code in ("+subjectCode+") and pets.school_year="+schoolYear +
                " and pets.term_code='"+schoolTrem+"'  and pets.team_id="+teamId+" and pets.exam_name like '%"+examName+"%'  group by ps.id order by  zongfen desc";
        List<Map<String, Object>> map = basicSQLService.find(sql);
        String fileName1="班级统计得分分析.xls";
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
}
