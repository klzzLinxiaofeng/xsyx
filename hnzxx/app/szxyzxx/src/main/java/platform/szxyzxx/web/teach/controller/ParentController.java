package platform.szxyzxx.web.teach.controller;

import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.SchoolInitService;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.StudentVo;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.szxyzxx.core.pojo.BasicResult;
import platform.szxyzxx.excelhelper.exception.ExcelReadException;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ExcelUtil;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.teach.vo.ParentCarNoImportInfo;
import platform.szxyzxx.web.teach.vo.ParentStudentExportInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * 学生家长信息
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/parent")
public class ParentController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String viewBasePath = "/teach/parent";

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("schoolInitService")
    private SchoolInitService schoolInitService;

    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache<Object> baseRedisCache;

    private Exporter<ParentStudentExportInfo> exporter;

    private Importer<ParentCarNoImportInfo> importer;

    public ParentController(){
        ExcelExportParam param=new ExcelExportParam(ParentStudentExportInfo.class,"学生家长信息表");
        exporter=new DefaultExporter<>(param);

        ExcelImportParam importParam=new ExcelImportParam(ParentCarNoImportInfo.class);
        importParam.setStartRowIndex(34);
        importer=new DefaultImporter<>(importParam);
    }



    /**
     * 家长信息获取
     */
    @RequestMapping(value = "/parentList")
    public ModelAndView getParentList(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") ParentVo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        String viewPath = "";
        ModelAndView mav = new ModelAndView();
        List studentMergeParentVoList = null;//new ArrayList<StudentMergeParentVo>();
        try {
            condition.setSchoolId(user.getSchoolId());
            condition.setSystem_app_id(SysContants.SYSTEM_APP_ID);
            order.setProperty("create_date");
            order.setAscending(true);
            if(StringUtils.isNotEmpty(condition.getSchoolYear())) {
                studentMergeParentVoList = queryParents(condition, page);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/parentList");
        }
        mav.addObject("studentMergeParentVoList", studentMergeParentVoList);
        mav.setViewName(viewPath);

        return mav;
    }



    @RequestMapping("updateLp")
    @ResponseBody
    public BasicResult updateLicesePlateByExcel(MultipartFile file, String schoolYear){
        if(file==null || file.getSize()<=0){
            return  BasicResult.error("文件不可为空");
        }

        if(StringUtils.isEmpty(schoolYear)){
            return BasicResult.error("学年不可为空");
        }

        Set<String> errorMsgs=new HashSet<>(0);

        try {

            List<ParentCarNoImportInfo> stuCarRecords=null;
            if(file.getOriginalFilename().endsWith(".csv")){
                stuCarRecords=parseCvsFile(file);
            }else {
                stuCarRecords=importer.importBy(file.getInputStream());
            }

            if(stuCarRecords.size()==0){
                return BasicResult.error("表格中没有进出场记录");
            }
            //指定学年的学生
            List<Map<String,Object>> stuParentCarList=basicSQLService.find("SELECT s.id as stu_id, s.`name` as stu_name, t.name team_name, p.id as pid, p.license_plate FROM pj_parent_student ps INNER JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 INNER JOIN pj_student s ON s.user_id = ps.student_user_id AND s.is_delete = 0 AND s.school_id = 215 INNER JOIN pj_team_student pts ON s.id = pts.student_id and pts.is_delete=0 INNER JOIN pj_team t ON pts.team_id = t.id WHERE ps.is_delete = 0 and ps.rank=1 AND t.school_year = '"+schoolYear+"' ");
            //全部家长的车牌，判重使用
            List<Map<String,Object>> allParents=basicSQLService.find("select id,license_plate from pj_parent where is_delete=0");

            for (ParentCarNoImportInfo stuCarRecord : stuCarRecords) {
                if(StringUtils.isEmpty(stuCarRecord.getStuName()) || StringUtils.isEmpty(stuCarRecord.getCarNo())){
                    continue;
                }
                try {
                    Map<String,Object> stuParentCar=findStuParentCarBy(stuCarRecord.getStuName(),stuCarRecord.getOrg(),stuParentCarList);
                    String pid=stuParentCar.get("pid").toString();
                    if(stuParentCar==null){
                        errorMsgs.add(stuCarRecord.getStuName()+"，在系统中不存在");
                        continue;
                    }
                    String carNo=(String) stuParentCar.get("license_plate");
                    //更新的车牌和现在的车牌一致则忽略
                    if(StringUtils.isNotEmpty(carNo) && carNo.contains(stuCarRecord.getCarNo())){
                        continue;
                    }
                    if(existsCarNo(allParents,stuCarRecord.getCarNo(),pid)){
                        errorMsgs.add(stuCarRecord.getStuName()+"，的车牌【"+stuCarRecord.getCarNo()+"】已被其他人绑定");
                        continue;
                    }
                    if(StringUtils.isEmpty(carNo)){
                        carNo=stuCarRecord.getCarNo();
                    }else{
                        carNo+=","+stuCarRecord.getCarNo();
                    }
                    basicSQLService.update("update pj_parent set license_plate='"+carNo+"' where id="+pid);
                    stuParentCar.put("license_plate",carNo);
                    findParentById(allParents,pid).put("license_plate",carNo);
                }catch (IllegalArgumentException e) {
                    errorMsgs.add(stuCarRecord.getStuName()+"，名字在系统中存在多个");
                }
            }

            if(errorMsgs.size()==0){
                return BasicResult.success();
            }

            StringBuilder errorMsg=new StringBuilder();
            for (String msg : errorMsgs) {
                errorMsg.append(msg).append("</br>");
            }

            return new BasicResult(300,errorMsg.toString());

        }catch (ExcelReadException e){
            return BasicResult.error("文件不是有效的Excel、csv文件");
        }catch (IOException e) {
            e.printStackTrace();
            return BasicResult.error("文件读取失败");
        }catch (Exception e){
            e.printStackTrace();
            return BasicResult.error("更新失败,请检查文件格式");
        }

    }

    private List<ParentCarNoImportInfo> parseCvsFile(MultipartFile file) throws IOException {
        CSVFormat format = CSVFormat.DEFAULT;
        Reader in = new InputStreamReader(file.getInputStream(),"GBK");
        Iterable<CSVRecord> records = format.parse(in);
        List<ParentCarNoImportInfo> list=new ArrayList<>();
        int count=-1;
        for (CSVRecord record : records) {
            count++;
            if(count>33) {
                ParentCarNoImportInfo i = new ParentCarNoImportInfo();
                i.setStuName(record.get(9));
                i.setCarNo(record.get(0));
                i.setOrg(record.get(11));
                list.add(i);
            }
        }
        return list;
    }


    private Map<String,Object> findParentById(List<Map<String,Object>> list,String id){
        for (Map<String, Object> map : list) {
            if(map.get("id").toString().equals(id)){
                return map;
            }
        }
        return null;
    }

    private boolean existsCarNo(List<Map<String,Object>> list,String carNo,String pid){
        for (Map<String, Object> map : list) {
            String lp=(String) map.get("license_plate");
            if(StringUtils.isNotEmpty(lp) && lp.contains(carNo) && !pid.equals(map.get("id").toString())){
                return true;
            }
        }
        return false;
    }

    private Map<String,Object> findStuParentCarBy(String importStuName,String importOrgName,List<Map<String,Object>> stuParentCarList){
        List<Map<String, Object>> nameEqs=new ArrayList<>(1);
        for (Map<String, Object> map : stuParentCarList) {
            if(map.get("stu_name").equals(importStuName)){
                nameEqs.add(map);
            }
        }

        if(nameEqs.size()==1){
            return nameEqs.get(0);
        }

        List<Map<String, Object>> nameAndTeamEqs=new ArrayList<>(1);

        if(nameEqs.size()>1){
            for (Map<String, Object> map :nameEqs){
                if(orgNameEqteamName(importOrgName,map.get("team_name").toString())){
                     nameAndTeamEqs.add(map);
                }
            }
        }

        if(nameAndTeamEqs.size()==0){
            return null;
        }

        if(nameAndTeamEqs.size()==1){
            return nameAndTeamEqs.get(0);
        }


        throw new IllegalArgumentException();
    }





    /**
     * 家长信息下载
     */
    @RequestMapping(value = "/parentDownLoadList", method = RequestMethod.GET)
    public void getParentDownLoadList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") ParentVo condition,
            HttpServletResponse response
    ) throws IOException {

        OutputStream out=response.getOutputStream();
        try {
            List<ParentStudentExportInfo> list=translateMapToExportData(queryParents(condition,null));
            Workbook wb= exporter.exportToNew(list);
            response.setHeader("Content-disposition", "attachment; filename="+ URLEncoder.encode("学生家长信息.xls","UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
            out.write("export error".getBytes());
        }

    }


    /**
     * 添加家长信息页面
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/addParentPage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addParentPage(@RequestParam(value = "id", required = true) Integer id,
                                      @RequestParam(value = "schoolYear", required = true) String schoolYear
    ) {
        ModelAndView mav = new ModelAndView();
        ParentVo parentvoEnd = null;
        ParentVo parentvo = new ParentVo();
        parentvo.setStudentId(id);
        parentvo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
        parentvo.setSchoolYear(schoolYear);
        parentvoEnd = this.parentProxyService.findStudent(parentvo);
        mav.addObject("parentvo", parentvoEnd);
        mav.setViewName(structurePath("/addParentPage"));
        return mav;
    }

    /**
     * 功能描述：添加家长
     * 2015-11-21
     *
     * @param parentvo
     * @param user
     * @return
     */
    @RequestMapping(value = "/addParent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation addParent(ParentVo parentvo, @CurrentUser UserInfo user) {
        ParentVo parentVoEnd = null;
        String message = ResponseInfomation.OPERATION_SUC;
        try {
//			parentvo.setSchoolId(user.getSchoolId());
//			parentvo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
//			parentVoEnd = this.parentProxyService.addInfoForParent(parentvo);
//
//			if(parentVoEnd != null) {
//				if(parentVoEnd.getErrorInfo() != null && !"".equals(parentVoEnd.getErrorInfo().trim())) {
//					message = parentVoEnd.getErrorInfo();
//				}
//			}
            Student student = studentService.findStudentById(parentvo.getStudentId());
            if (student != null) {
                parentService.addParentFromExcelImport(user.getSchoolId(), SysContants.SYSTEM_APP_ID, parentvo.getGradeId(), parentvo.getTeamId(), student.getUserId(),
                        parentvo.getName(), parentvo.getMobile(), SysContants.USER_TYPE_PARENT, parentvo.getParentRelation(), parentvo.getRank(), parentvo.getEmail());
            }

        } catch (Exception e) {
            log.info("修改教师信息异常");
            e.printStackTrace();
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(parentVoEnd, message);
    }

    /**
     * 修改家长信息页面
     *
     * @param id
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/modifyParentPage", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView modifyParentPge(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "studentId", required = true) Integer studentId,
            @RequestParam(value = "schoolYear", required = true) String schoolYear
    ) {
        ParentVo parentvoEnd = null;
        try {
            ParentVo parentvo = new ParentVo();
            parentvo.setStudentId(studentId);
            parentvo.setId(id);
            parentvo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
            parentvo.setSchoolYear(schoolYear);
            parentvoEnd = this.parentProxyService.modifyPage(parentvo);
            parentvoEnd.setStudentId(studentId);
        } catch (Exception e) {
            log.error("修改家长信息查找信息出错" + this.errorMessage(e.getMessage()));
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("parentVo", parentvoEnd);
        mav.setViewName(structurePath("/modifyParentPage"));

        return mav;
    }


    /**
     * 删除家长信息
     *
     * @param id
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/deleteParent", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "studentId", required = true) Integer studentId
    ) {
        String message = ResponseInfomation.OPERATION_SUC;
        try {
            ParentVo parentvo = new ParentVo();
            parentvo.setStudentId(studentId);
            parentvo.setId(id);
            parentvo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
            this.parentProxyService.remove(parentvo);
        } catch (Exception e) {
            message = "删除家长信息出错：" + this.errorMessage(e.getMessage());
        }
        return message;
    }

    /**
     * 修改家长信息
     *
     * @param parentVo
     * @return
     */
    @RequestMapping(value = "/modifyParent", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation modifyParent(ParentVo parentVo) {
        ParentVo parentVoEnd = null;
        String errrMessage = "";
        try {
            parentVo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
            parentVoEnd = this.parentProxyService.modify(parentVo);
            errrMessage = parentVoEnd.getErrorInfo() == null ? ResponseInfomation.OPERATION_SUC : parentVoEnd.getErrorInfo();

//			if ( errrMessage.equals(ResponseInfomation.OPERATION_SUC) && parentVo.getName() != null && parentVo.getUserId() != null ) {
//				synchronizedTeacherInfo(parentVo);
//			}
        } catch (Exception e) {
            errrMessage = "" + this.errorMessage(e.getMessage());
            log.error(errrMessage);
            e.printStackTrace();
        }

        return new ResponseInfomation(parentVoEnd, errrMessage);
    }

    /**
     * 当家长为教师时,同步教师的姓名
     *
     * @param parentVo
     */
    private void synchronizedTeacherInfo(ParentVo parentVo) {
        TeacherCondition teacherCondition = new TeacherCondition();
        teacherCondition.setUserId(parentVo.getUserId());
        teacherCondition.setIsDelete(false);
        List<Teacher> teacherList = teacherService.findTeacherByCondition(teacherCondition, null, null);
        if (teacherList != null && teacherList.size() > 0) {
            for (Teacher teacher : teacherList) {
                // 修改教师表信息
                teacher.setName(parentVo.getName());
                teacherService.modify(teacher);

                TeacherDetailInfo teacherDetailInfo = new TeacherDetailInfo();
                teacherDetailInfo.setUserId(parentVo.getUserId());
                teacherDetailInfo.setName(parentVo.getName());
                teacherDetailInfo.setTeacherId(teacher.getId());
                teacherDetailInfo.setSchoolId(teacher.getSchoolId());

                // 修改个人信息表信息
                teacherService.modifyProfileForTeacher(teacherDetailInfo);
                // 修改学校用户表信息
                teacherService.modifySchoolUserForTeacher(teacherDetailInfo);
                // 修改班级教师表信息
                teacherService.modifyTeamTeacher(teacherDetailInfo);
                // 修改科任教师表信息
                teacherService.modifySubjectTeacher(teacherDetailInfo);
                // 修改部门教师表信息
                teacherService.modifyDepartmentTeacher(teacherDetailInfo);
            }
        }
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    /**
     * 导出家长信息模板页面
     *
     * @return
     */
    @RequestMapping(value = "/downLoadParentPage", method = RequestMethod.GET)
    public ModelAndView downLoadParentPage() {
        return new ModelAndView(structurePath("/downLoadParentPage"));
    }

    /**
     * 导出学生家长信息页面
     *
     * @return
     */
    @RequestMapping(value = "/downLoadStudentParentPage", method = RequestMethod.GET)
    public ModelAndView downLoadStudentParentPage() {
        return new ModelAndView(structurePath("/downLoadStudentParentPage"));
    }

    /**
     * 导出家长信息模板
     *
     * @param user
     * @param parentVo
     * @return
     */
    @RequestMapping(value = "/downLoadParentInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfomation downLoadParent(@CurrentUser UserInfo user, ParentVo parentVo, HttpServletResponse response, HttpServletRequest request) {

        ResponseInfomation tesponseInfomation = null;
        parentVo.setSchoolId(user.getSchoolId());
        List<Object> list = new ArrayList<Object>();
        //查找数据
        parentVo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
        List<ParentVo> parentVoList = this.parentProxyService.getParentTemplate(parentVo);

        ParseConfig config = SzxyExcelTookit.getConfig("parentvo");
        String filename = "家长信息模板" + ".xls";
        try {
            for (ParentVo parentVo2 : parentVoList) {
                list.add(parentVo2);
            }

            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            tesponseInfomation = new ResponseInfomation("用户下载家长信息模板失败：" + this.errorMessage(e.getMessage()));
            e.printStackTrace();
        }
        tesponseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);

        return tesponseInfomation;
    }


    /**
     * 导入家长信息页面
     */
    @RequestMapping("/upLoadParentInfoPage")
    public ModelAndView upLoadStudentAwardInfoPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(this.structurePath("/upLoadParentInfoPage"));
        return mav;
    }

    /**
     * 导入家长信息
     *
     * @throws IOException
     */
    @RequestMapping("/upLoadParentInfo")
    @ResponseBody
    public Map<String, Object> upLoadParentInfoByModel(
            @RequestParam("fileUpload") MultipartFile fileUpload,
            @RequestParam(value = "role", required = true) Integer role,
            @CurrentUser UserInfo userInfo, HttpServletResponse response, HttpServletRequest request) {
        //错误信息列表
        Map<String, Object> map = new HashMap<String, Object>();
        List<ParentVo> errorParentVoList = new ArrayList<ParentVo>();
        List<ParentVo> successParentVoList = new ArrayList<ParentVo>();
        String status = ResponseInfomation.OPERATION_SUC;
        String fileName = fileUpload.getOriginalFilename();//获取文件名
        InputStream is = null;
        try {
            is = fileUpload.getInputStream();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            ParseConfig config = SzxyExcelTookit.getConfig("parentvo");

            List<Object> parentVoList = SzxyExcelTookit.excelDataToModels(config, is, suffix);
            for (Object object : parentVoList) {
                ParentVo parentvo = (ParentVo) object;
                parentvo.setMobile(parentvo.getMobile() + "");
                parentvo.setSchoolId(userInfo.getSchoolId());
                parentvo.setSystem_app_id(SysContants.SYSTEM_APP_ID);
                parentvo.setRoleId(role);
                try {
                    if ((parentvo.getName() == null || "".equals(parentvo.getName().trim()))
                            && (parentvo.getRank() == null || "".equals(parentvo.getRank().trim()))
                            && (parentvo.getParentRelation() == null || "".equals(parentvo.getParentRelation().trim()))
                            && (parentvo.getParentRelation() == null || "".equals(parentvo.getParentRelation().trim()))
                            && (parentvo.getMobile() == null || "".equals(parentvo.getMobile().trim()))
                    ) {

                        continue;
                    }
                    ParentVo addResultVo = this.parentProxyService.addExcel(parentvo);
                    if (addResultVo != null && addResultVo.isAddStatus()) {
                        successParentVoList.add(addResultVo);
                    } else {
                        errorParentVoList.add(addResultVo);
                    }
                } catch (Exception e) {
                    parentvo.setErrorInfo("添加错误:" + this.errorMessage(e.getMessage()));
                    errorParentVoList.add(parentvo);
                    e.printStackTrace();
                }

            }


        } catch (IOException e) {
            status = "针对家长信息导入失败：" + this.errorMessage(e.getMessage());
            e.printStackTrace();
        }

        map.put("status", status);
        map.put("error", errorParentVoList);
        map.put("success", successParentVoList);


        return map;
    }

    /**
     * 报错提示问题
     *
     * @param message
     * @return
     */
    private String errorMessage(String message) {
        if (message == null) {
            message = ResponseInfomation.OPERATION_ERROR;
        }
        String errorMessage = message.length() > 100 ? message.substring(0, 100) : message;
        return errorMessage;
    }

    /**
     * 校验手机号码
     *
     * @param dxlx
     * @param mobile
     * @param id
     * @return
     */
    @RequestMapping(value = "checkMobile", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkMobile(
            @RequestParam(value = "dxlx", required = false) String dxlx,
            @RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "id", required = false) Integer id) {

        boolean isMobile = true;

        if ("mobile".equals(dxlx)) {
            isMobile = this.parentProxyService.checkMobile(mobile);
        } else {

        }

        return isMobile;
    }

    /**
     * 校验邮箱
     *
     * @param dxlx
     * @param email
     * @param id
     * @return
     */
    @RequestMapping(value = "checkEmail", method = RequestMethod.GET)
    @ResponseBody
    public boolean checkEmail(
            @RequestParam(value = "dxlx", required = false) String dxlx,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "id", required = false) Integer id) {

        boolean isEmail = false;
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if ("email".equals(dxlx)) {
            try {
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                isEmail = matcher.matches();
            } catch (Exception e) {
                isEmail = false;
            }
        } else {

        }

        return isEmail;
    }

    /**
     * 解除绑定
     *
     * @param id
     * @param studentId
     * @return
     */
    @RequestMapping(value = "/deleteRelate")
    @ResponseBody
    public String deleteRelate(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "studentId", required = true) Integer studentId
    ) {
        String message = ResponseInfomation.OPERATION_SUC;
        try {
            String parentType = SysContants.USER_TYPE_PARENT;
            message = parentService.deleteParentStudentRelate(id, studentId, parentType);
        } catch (Exception e) {
            message = "删除家长信息出错：" + this.errorMessage(e.getMessage());
        }
        return message;
    }

    /**
     * 批量导入家长页面
     *
     * @return
     */
    @RequestMapping(value = "/import")
    public Object importParentExcel(@CurrentUser UserInfo userInfo) {
        ModelAndView model = new ModelAndView();
        String key = "parent_student_tmp_" + userInfo.getId();
        Object value = baseRedisCache.getCacheObject(key);
        model.addObject("value", value);
        model.setViewName(structurePath("/import/import"));
        return model;
    }

    @RequestMapping(value = "/tmp/index")
    public Object toParentTmpPage(@CurrentUser UserInfo userInfo) {
        ModelAndView model = new ModelAndView();

        String key = "parent_student_tmp_" + userInfo.getId();
        Long errorSize = 0L;
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if (value != null && !"".equals(value)) {
            String code = (String) value.get("code");
            errorSize = schoolInitService.countParentStudentTmpByCodeAndStatus(code, 2);
        }
        model.addObject("errorSize", errorSize);
        model.addObject("value", value);
        model.setViewName(structurePath("/import/tmp"));
        return model;
    }

    @RequestMapping(value = "/tmp/list")
    public Object toParentTmpList(
            @CurrentUser UserInfo userInfo,
            @RequestParam(value = "status", defaultValue = "0") Integer status,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String path = structurePath("/import/tmp_list");

        List<ParentStudentTmp> list = new ArrayList<>();
        String key = "parent_student_tmp_" + userInfo.getId();
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if (value != null && !"".equals(value)) {
            String code = (String) value.get("code");
            list = schoolInitService.findParentStudentTmpByCodeAndStatus(code, status, page, order);
        }
        model.addAttribute("list", list);
        model.addAttribute("status", status);
        return new ModelAndView(path, model.asMap());
    }


    @RequestMapping(value = "/downLoadExcel")
    public void downLoadExcelModel(
            @CurrentUser UserInfo user, HttpServletResponse response, HttpServletRequest request,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId) {

        String fileName = "家长信息模板" + ".xls";
        String sheetName = "数据模板";
        String title[] = {"年级", "班级", "班内学号", "学生姓名", "监护人1", "手机号码1", "与学生关系1", "监护人2", "手机号码2", "与学生关系2"};

        List<StudentVo> studentList = studentService.findStudentVoByTeam(user.getSchoolId(), user.getSchoolYear(), gradeId, teamId);
        //填补班内学号
        for (StudentVo vo : studentList) {
            if (vo.getNumber() == null) {
                Integer number = teamStudentService.findMaxTeamNumberByTeamId(vo.getTeamId());
                number += 1;
                vo.setNumber(number);

                TeamStudent teamStudent = new TeamStudent(vo.getTeamStudentId());
                teamStudent.setNumber(number);
                teamStudent.setModifyDate(new Date());
                teamStudentService.modify(teamStudent);
            }
        }
        String[][] content = new String[studentList.size()][title.length];
        int i = 0;
        for (StudentVo vo : studentList) {
            content[i][0] = vo.getGradeName();
            content[i][1] = vo.getTeamNumber() + "班";
            content[i][2] = String.valueOf(vo.getNumber());
            content[i][3] = vo.getName();
            i++;
        }
        try {
            ExcelUtil.exportExcelToWEB(fileName, sheetName, title, content, null, response, "2");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Map<String, Object>> queryParents(ParentVo condition,Page page) {
        StringBuilder sql = new StringBuilder("SELECT p.id,p.`name` parent_name, p.email, p.mobile, p.user_name,p.license_plate,s.id  stu_id, s.`name` stu_name, t.name team_name, s.emp_card, ps.rank, ri.`name` parent_relation FROM pj_parent_student ps inner join jc_gc_item ri on ri.table_code='GB-JTGX' and ri.`value`=ps.parent_relation INNER JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 INNER JOIN pj_student s ON s.user_id = ps.student_user_id AND s.is_delete = 0 and s.school_id=215 inner JOIN pj_team_student pts ON s.id = pts.student_id and pts.is_delete=0 INNER JOIN pj_team t ON pts.team_id = t.id WHERE ps.is_delete =0 ");
        sql.append("and t.school_year='").append(condition.getSchoolYear()).append("'");
        if (condition.getTeamId() != null) {
            sql.append(" and t.id=").append(condition.getTeamId());
        } else if (condition.getGradeId() != null) {
            sql.append(" and t.grade_id=").append(condition.getGradeId());
        }
        if(StringUtils.isNotEmpty(condition.getStudentName())){
            sql.append(" and s.name like '%").append(condition.getStudentName()).append("%'");
        }

        if(StringUtils.isNotEmpty(condition.getMobile())){
            sql.append(" and p.mobile like '%").append(condition.getMobile()).append("%'");
        }

        if(StringUtils.isNotEmpty(condition.getLicensePlate())){
            sql.append(" and p.license_plate like '%").append(condition.getLicensePlate()).append("%'");
        }

        sql.append("group by p.id order by s.id");
        if(page==null){
            return basicSQLService.find(sql.toString());
        }
        return basicSQLService.findByPaging(page,sql.toString());
    }
    public List<ParentStudentExportInfo> translateMapToExportData(List<Map<String,Object>> list){
        List<ParentStudentExportInfo> exportList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            ParentStudentExportInfo i=new ParentStudentExportInfo();
            i.setStuName(getMapVal(map,"stu_name"));
            i.setTeamName(getMapVal(map,"team_name"));
            i.setEmpCard(getMapVal(map,"emp_card"));
            i.setParentName(getMapVal(map,"parent_name"));
            i.setRelation(getMapVal(map,"parent_relation"));
            i.setSfzJh(getMapVal(map,"rank"));
            i.setParetUserName(getMapVal(map,"user_name"));
            i.setMobile(getMapVal(map,"mobile"));
            i.setCarNo(getMapVal(map,"license_plate"));
            i.setEmail(getMapVal(map,"email"));
            exportList.add(i);
        }
        return exportList;
    }

    private boolean orgNameEqteamName(String orgName,String teamName){
        if(StringUtils.isEmpty(orgName) || StringUtils.isEmpty(teamName)){
            return false;
        }
        String[] orgParts=orgName.split("/");
        if(orgParts.length!=4){
            return false;
        }

        String orgTeamName=orgParts[3];

        if(orgTeamName.equals(teamName)){
            return true;
        }
        int s=teamName.indexOf("(");
        int e=teamName.indexOf(")");
        if(s==-1 || e==-1){
            return false;
        }
        String teamNum=teamName.substring(s+1,e);
        try {
            String teamNumCh=numberToCH(Integer.parseInt(teamNum));
            String teamCh=teamName.replace("("+teamNum+")",teamNumCh);
            return teamCh.equals(orgTeamName);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }


    private  String getMapVal(Map<String,Object> map,String key){
        Object val=map.get(key);
        if(val==null){
            return null;
        }
        return val.toString();
    }


    public  String numberToCH(int intInput) {
        String si = String.valueOf(intInput);
        String sd = "";
        if (si.length() == 1) // 個
        {
            sd += GetCH(intInput);
            return sd;
        } else if (si.length() == 2)// 十
        {
            if (si.substring(0, 1).equals("1"))
                sd += "十";
            else
                sd += (GetCH(intInput / 10) + "十");
            sd += numberToCH(intInput % 10);
        } else if (si.length() == 3)// 百
        {
            sd += (GetCH(intInput / 100) + "百");
            if (String.valueOf(intInput % 100).length() < 2)
                sd += "零";
            sd += numberToCH(intInput % 100);
        } else if (si.length() == 4)// 千
        {
            sd += (GetCH(intInput / 1000) + "千");
            if (String.valueOf(intInput % 1000).length() < 3)
                sd += "零";
            sd += numberToCH(intInput % 1000);
        } else if (si.length() == 5)// 萬
        {
            sd += (GetCH(intInput / 10000) + "萬");
            if (String.valueOf(intInput % 10000).length() < 4)
                sd += "零";
            sd += numberToCH(intInput % 10000);
        }

        return sd;
    }

    private  String GetCH(int input) {
        String sd = "";
        switch (input) {
            case 1:
                sd = "一";
                break;
            case 2:
                sd = "二";
                break;
            case 3:
                sd = "三";
                break;
            case 4:
                sd = "四";
                break;
            case 5:
                sd = "五";
                break;
            case 6:
                sd = "六";
                break;
            case 7:
                sd = "七";
                break;
            case 8:
                sd = "八";
                break;
            case 9:
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }



}
