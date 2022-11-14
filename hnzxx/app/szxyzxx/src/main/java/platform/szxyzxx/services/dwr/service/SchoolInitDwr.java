package platform.szxyzxx.services.dwr.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import framework.generic.cache.redis.core.BaseRedisCache;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.POIXMLException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.directwebremoting.*;
import org.directwebremoting.io.FileTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.EmployeeList;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalcode.service.JcCacheService;
import platform.education.generalcode.service.JcGcCacheService;
import platform.education.im.utils.UUIDUtil;
import platform.education.oa.utils.UtilDate;
import platform.education.user.model.Group;
import platform.education.user.service.GroupService;
import platform.education.user.service.UserService;
import platform.szxyzxx.web.common.contants.SysContants;
import platform.szxyzxx.web.common.job.HikvisonJob;
import platform.szxyzxx.web.common.job.UserCardJob;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SchoolInitDwr {
    private static final Logger log = LoggerFactory.getLogger(SchoolInitDwr.class);

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;
    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;
    @Autowired
    @Qualifier("schoolInitService")
    private SchoolInitService schoolInitService;
    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;
    @Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;
    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;
    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache<Object> baseRedisCache;

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;
    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;
    @Autowired
    @Qualifier("teamStudentService")
    private TeamStudentService teamStudentService;
    @Autowired
    @Qualifier("parentService")
    private ParentService parentService;
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    @Autowired
    private PersonService personService;

    @Autowired
    @Qualifier("jcGcCacheService")
    protected JcGcCacheService jcGcCacheService;

    @Autowired
    @Qualifier("jcCacheService")
    protected JcCacheService jcCacheService;

    @Autowired
    @Qualifier("libraryService")
    private LibraryService libraryService;

    //注入当个定时任务用于获取新增用户的食堂卡号和推送至海康门禁系统
    @Autowired
    private UserCardJob userCardJob;
    @Autowired
    private HikvisonJob hikvisonJob;

    /**
     * 食堂接口
     */
    private static String address;
    private static String canteen;

    /**
     * 图书馆接口
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;
    private static String canteenIp;
    private static String userListUrl;

    /**
     * 海康接口
     */
    private static String fileName;
    private static String hikvisionHost;
    private static String hikvisionAppKey;
    private static String hikvisionAppSecret;
    private static String hikvisionAddPersonUrl;
    private static String hikvisionBindCarUrl;
    private static String untieCarUrl;


    static {

        String fileName = "System.properties";
        // 食堂
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");

        // 图书馆
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

        // 食堂接口查询卡号信息
        canteenIp = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        userListUrl = PropertiesUtil.getProperty(fileName, "canteen.user.list.url");

        //闸机
        hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        hikvisionAddPersonUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.batch.addredss");
        hikvisionBindCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.bindings");
        untieCarUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.deletion");

    }

    @SuppressWarnings("unchecked")
    public Object importStudentByFile(InputStream inputStream, String fileName, Integer schoolId,
                                      String schoolYear, Integer userid, String func) throws Exception {
        /**推送文件上传成功状态*/
        String[] header = {"班内学号","卡号", "学生姓名", "年级", "班级", "监护人", "监护人手机"};

        String suffix = "";
        if (fileName.contains(".xlsx")) {
            suffix = "xlsx";
        } else if (fileName.contains(".xls")) {
            suffix = "xls";
        }

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            /**解析excel文件*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCL(inputStream, suffix, header);
            /**解析excel文件状态*/
            String status = (String) map.get("status");

            /**解析excel成功*/
            if ("success".equals(status)) {
                /**excel数据，key为表头，value为单元格数据*/
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
                /**推送文件解析成功状态*/
                //MassagePush.push("文件解析完毕，正在创建账号。账号总数为：" + data.size(), func);
                /**处理excel数据*/
                result = studentDataHandler(data, header, func, schoolId, schoolYear, userid);
            }

            result.put("status", status);
            String resultJson = JSON.toJSONString(result);

            return resultJson;
        } catch (POIXMLException e) {
            result.put("status", "FILE_SUFFIX_ERROR");
            String resultJson = JSON.toJSONString(result);
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error");
            String resultJson = JSON.toJSONString(result);
            return resultJson;
        }
    }

    @SuppressWarnings("unchecked")
    public Object importTeacherByFile(InputStream inputStream, String fileName, Integer schoolId,
                                      String schoolYear, Integer userid, String func) throws Exception {
        /**推送文件上传成功状态*/
        String[] header = {"姓名","工号","卡号", "性别", "别名", "手机号码", "部门", "职务"};

        Map<String, Object> result = new HashMap<String, Object>();

        String suffix = "";
        if (fileName.contains(".xlsx")) {
            suffix = "xlsx";
        } else if (fileName.contains(".xls")) {
            suffix = "xls";
        }

        try {
            /**解析excel文件*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCL(inputStream, suffix, header);
            /**解析excel文件状态*/
            String status = (String) map.get("status");

            /**解析excel成功*/
            if ("success".equals(status)) {
                /**excel数据，key为表头，value为单元格数据*/
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
                /**推送文件解析成功状态*/
                /**处理excel数据*/
                result = teacherDataHandler(data, header, func, schoolId, schoolYear, userid);
            }

            result.put("status", status);
            String resultJson = JSON.toJSONString(result);
            //Thread.sleep(1000*60*3);
            return resultJson;
        } catch (POIXMLException e) {
            result.put("status", "FILE_SUFFIX_ERROR");
            String resultJson = JSON.toJSONString(result);
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error");
            String resultJson = JSON.toJSONString(result);
            return resultJson;
        }
    }

    public FileTransfer exportStudent(String teams, String syllables,String isStutas) throws IOException {
        Integer[] teamIds = null;
        String[] headers = null;
        try {
            teamIds = JSON.parseObject(teams, Integer[].class);
            headers = JSON.parseObject(syllables, String[].class);
        } catch (Exception e) {
            log.error(e.toString());
        }

        List<List<Object>> result = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        Integer preGradeId = -1;
        String gradeName = "";
        //创建工作簿
        HSSFWorkbook workBook = new HSSFWorkbook();

        for (Integer teamId : teamIds) {
            Team team = teamService.findTeamById(teamId);
            String teamName = team.getName();

            Integer gradeId = team.getGradeId();
            if (gradeId - preGradeId != 0) {
                if (result != null && result.size() > 0) {
                    ExcelUtil.exportExcel(workBook, gradeName + "学生信息", headers, result, stream);
                }
                result = new ArrayList<List<Object>>();

                preGradeId = gradeId;
                Grade grade = gradeService.findGradeById(gradeId);
                gradeName = grade.getName();
            }

            List<TeamStudent> teamStudentList = teamStudentService.findByTeamId2(teamId,isStutas);
            for (TeamStudent teamStudent : teamStudentList) {
                List<Object> param = getParam(teamStudent, headers, teamName, gradeName);
                result.add(param);
            }
        }

        ExcelUtil.exportExcel(workBook, gradeName + "学生信息", headers, result, stream);
        workBook.write(stream);
        stream.flush();
        stream.close();

        String fileName = new String("学生信息.xls".getBytes("UTF-8"), "ISO-8859-1");
        FileTransfer fileResult = new FileTransfer(fileName, "application/vnd.ms-excel", stream.toByteArray());

        return fileResult;
    }

    private List<Object> getParam(TeamStudent teamStudent, String[] headers, String teamName, String gradeName) {
        Integer userid = teamStudent.getUserId();
        List<Object> param = new ArrayList<Object>();
        String pname = "";
        String paccount = "";
        String phone = "";
        // 获取的家长信息
        List<ParentVo> parents = parentService.findParentsByStudentUserId(userid);
        if (parents.size() > 0) {
            ParentVo parent = parents.get(0);
            paccount = parent.getUserName();
            pname = parent.getName();
            phone = parent.getMobile();
        }
        // 获取学生信息
        Person person = personService.findPersonByStuId(teamStudent.getStudentId());
        Student student = studentService.findStudentById(teamStudent.getStudentId());
        List<FamilyMember> familyMemberList = studentService.findFamilyMemberByStudentId(teamStudent.getStudentId());
        StudentArchive studentArchive = studentService.findStudentArchiveByStudentId(teamStudent.getStudentId());

        FamilyMember familyMember1 = new FamilyMember();
        FamilyMember familyMember2 = new FamilyMember();
        if (CollUtil.isNotEmpty(familyMemberList)) {
            familyMember1 = familyMemberList.get(0);
            if (familyMemberList.size() > 1) {
                familyMember2 = familyMemberList.get(1);
            }
        }

        for (String header : headers) {
            // 基本信息
            if ("学生姓名".equals(header)) {
                param.add(teamStudent.getName());
            } else if ("学生账号".equals(header)) {
                //User user = userService.findUserById(userid);
                param.add(student.getUserName());
            } else if ("家长登录账号".equals(header)) {
                param.add(paccount);
            }else if ("家长登录手机号".equals(header)) {
                param.add(phone);
            } else if ("性别".equals(header)) {
                param.add(person == null || person.getSex() == null ? "" : (getDictNameByValue("GB-XB", person.getSex())));
            } else if ("出生日期".equals(header)) {
                param.add(person == null ? "" : (person.getBirthday() == null ? "" : UtilDate.getDateFormatter(person.getBirthday(), "yyyy-MM-dd")));
            } else if ("政治面貌".equals(header)) {
                param.add(person == null || person.getPoliticalStatus() == null ? "" : (getDictNameByValue("GB-ZZMM", person.getPoliticalStatus())));
            } else if ("民族".equals(header)) {
                param.add(person == null || person.getRace() == null ? "" : (getDictNameByValue("GB-MZ", person.getRace())));
            } else if ("国籍/地区".equals(header)) {
                param.add(person == null || person.getNationality() == null ? "" : (getDictNameByValue("GB-GJ", person.getNationality())));
            } else if ("出生地".equals(header)) {
                param.add(person == null || person.getBirthPlace() == null || ("").equals(person.getBirthPlace()) ? "" : (getArea("code=" + person.getBirthPlace())));
            } else if ("籍贯".equals(header)) {
                param.add(person == null || person.getNativePlace() == null || ("").equals(person.getNativePlace()) ? "" : (getArea("code=" + person.getNativePlace())));
            } else if ("身份证件类型".equals(header)) {
                param.add(person == null || person.getIdCardType() == null ? "" : (getDictNameByValue("JY-SFZJLX", person.getIdCardType())));
            } else if ("身份证件号".equals(header)) {
                param.add(person == null ? "" : (person.getIdCardNumber() == null ? "" : person.getIdCardNumber()));
            } else if ("港澳台侨外".equals(header)) {
                param.add(person == null || person.getAbroadCode() == null ? "" : (getDictNameByValue("JY-GATQW", person.getAbroadCode())));
            } else if ("健康状况".equals(header)) {
                param.add(person == null || person.getHealthStatus() == null ? "" : (getDictNameByValue("GB-JKZK", person.getHealthStatus())));
            }
            // 辅助信息类
            else if ("姓名拼音".equals(header)) {
                param.add(person == null ? "" : (person.getPinyinName() == null ? "" : person.getPinyinName()));
            } else if ("曾用名".equals(header)) {
                param.add(person == null ? "" : (person.getUsedName() == null ? "" : person.getUsedName()));
            } else if ("户口性质".equals(header)) {
                param.add(person == null || person.getResidenceType() == null ? "" : (getDictNameByValue("GB-HKLB", person.getResidenceType())));
            } else if ("身份证有效期".equals(header)) {
                param.add(person == null ? "" : (person.getIdCardDate() == null ? "" : UtilDate.getDateFormatter(person.getIdCardDate(), "yyyy-MM-dd")));
            } else if ("户口所在地".equals(header)) {
                param.add(person == null || person.getResidenceAddress() == null || ("").equals(person.getResidenceAddress()) ? "" : (getArea("code=" + person.getResidenceAddress())));
            } else if ("特长".equals(header)) {
                param.add(person == null ? "" : (person.getSpecialSkill() == null ? "" : person.getSpecialSkill()));
            }
            //卡号
            else if ("卡号".equals(header)) {
                param.add(student.getEmpCard());
            }
            // 学籍信息类
            else if ("学籍辅号".equals(header)) {
                //student == null ? "" : (student.getStudentNumber() == null ? "" : student.getStudentNumber())
                param.add(student.getEmpCode());
            } else if ("入学时间".equals(header)) {
                param.add(student == null ? "" : (student.getEnrollDate() == null ? "" : UtilDate.getDateFormatter(student.getEnrollDate(), "yyyy-MM-dd")));
            } else if ("班内学号".equals(header)) {
                param.add(student.getEmpCode());
            } else if ("入学方式".equals(header)) {
                param.add(studentArchive == null || studentArchive.getEnrollType() == null ? "" : (getDictNameByValue("JY-RXFS", studentArchive.getEnrollType())));
            } else if ("年级".equals(header)) {
                param.add(gradeName);
            } else if ("班级".equals(header)) {
                param.add(teamName);
            } else if ("就读方式".equals(header)) {
                param.add(studentArchive == null || studentArchive.getAttendSchoolType() == null ? "" : (getDictNameByValue("JY-JDFS", studentArchive.getAttendSchoolType())));
            } else if ("学生来源".equals(header)) {
                param.add(studentArchive == null || studentArchive.getStudentSource() == null ? "" : (getDictNameByValue("JY-ZXXXSLY", studentArchive.getStudentSource())));
            } else if ("离校时间".equals(header)) {
                param.add(student == null ? "" : (student.getLeaveDate() == null ? "" : UtilDate.getDateFormatter(student.getLeaveDate(), "yyyy-MM-dd")));
            } else if ("学生在读状态".equals(header)) {
                param.add(student == null || student.getStudyState() == null ? "" : (getDictNameByValue("JY-XSDQZT", student.getStudyState())));
            }
            // 联系信息类
            else if ("现住址".equals(header)) {
                param.add(person == null ? "" : (person.getAddress() == null ? "" : person.getAddress()));
            } else if ("邮政编码".equals(header)) {
                param.add(person == null ? "" : (person.getZipCode() == null ? "" : person.getZipCode()));
            } else if ("电子邮箱".equals(header)) {
                param.add(person == null ? "" : (person.getEmail() == null ? "" : person.getEmail()));
            } else if ("主页地址".equals(header)) {
                param.add(person == null ? "" : (person.getHomepage() == null ? "" : person.getHomepage()));
            } else if ("家庭地址".equals(header)) {
                param.add(person == null ? "" : (person.getHomeAddress() == null ? "" : person.getHomeAddress()));
            } else if ("通信地址".equals(header)) {
                param.add(person == null ? "" : (person.getResideAddress() == null ? "" : person.getResideAddress()));
            } else if ("联系电话".equals(header)) {
                param.add(person == null ? "" : (person.getMobile() == null ? "" : person.getMobile()));
            }
            // 扩展信息类
            else if ("是否独生子女".equals(header)) {
                param.add(person == null || person.isOnlyChild() == null ? "" : (isFlgBoolean(person.isOnlyChild())));
            } else if ("是否受过学前教育".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsPreeducated() == null ? "" : (isFlgBoolean(studentArchive.getIsPreeducated())));
            } else if ("是否留守儿童".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsUnattendedChild() == null ? "" : (isFlgChild(studentArchive.getIsUnattendedChild())));
            } else if ("是否进城务工人员随迁子女".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsCityLabourChild() == null ? "" : (isFlgBoolean(studentArchive.getIsCityLabourChild())));
            } else if ("是否孤儿".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsOrphan() == null ? "" : (isFlgBoolean(studentArchive.getIsOrphan())));
            } else if ("是否烈士或优抚子女".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsMartyrChild() == null ? "" : (isFlgBoolean(studentArchive.getIsMartyrChild())));
            } else if ("随班就读".equals(header)) {
                param.add(studentArchive == null || studentArchive.getFollowStudy() == null ? "" : (getDictNameByValue("XY-JY-SBJD", studentArchive.getFollowStudy())));
            } else if ("残疾人类型".equals(header)) {
                param.add(studentArchive == null || studentArchive.getDisabilityType() == null ? "" : (getDictNameByValue("JY-CJLX", studentArchive.getDisabilityType())));
            } else if ("是否由政府购买学位".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsBuyedDegree() == null ? "" : (isFlgBoolean(studentArchive.getIsBuyedDegree())));
            } else if ("是否需要申请资助".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsSponsored() == null ? "" : (isFlgBoolean(studentArchive.getIsSponsored())));
            } else if ("是否享受一补".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsFirstRecommended() == null ? "" : (isFlgBoolean(studentArchive.getIsFirstRecommended())));
            }
            // 交通方式信息类
            else if ("上下学距离".equals(header)) {
                param.add(studentArchive == null || studentArchive.getSchoolDistance() == null ? "" : (getDictNameByValue("XY-JY-SXXJL", studentArchive.getSchoolDistance())));
            } else if ("上下学交通方式".equals(header)) {
                param.add(studentArchive == null || studentArchive.getTrafficType() == null ? "" : (getDictNameByValue("XY-JY-JTFS", studentArchive.getTrafficType())));
            } else if ("是否需要乘坐校车".equals(header)) {
                param.add(studentArchive == null || studentArchive.getBySchoolBus() == null ? "" : (isFlgBoolean(studentArchive.getBySchoolBus())));
            }
            // 备注信息
            else if ("备注".equals(header)) {
                param.add(person == null ? "" : (person.getRemark() == null ? "" : person.getRemark()));
            }

            // 家庭成员信息类
            else if ("家庭成员或监护人姓名一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getName() == null ? "" : familyMember1.getName()));
            } else if ("关系一".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getRelation() == null ? "" : (getDictNameByValue("XY-JY-XSJTGX", familyMember1.getRelation())));
            } else if ("联系电话一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getMobile() == null ? "" : familyMember1.getMobile()));
            } else if ("身份证件号一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getIdCardNumber() == null ? "" : familyMember1.getIdCardNumber()));
            } else if ("关系说明一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getRelationRemarks() == null ? "" : familyMember1.getRelationRemarks()));
            } else if ("是否监护人一".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getRank() == null ? "" : (getDictNameByValue("XY-JY-JZLB", familyMember1.getRank())));
            } else if ("民族一".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getRace() == null ? "" : (getDictNameByValue("GB-MZ", familyMember1.getRace())));
            } else if ("身份证类型一".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getIdCardType() == null ? "" : (getDictNameByValue("JY-SFZJLX", familyMember1.getIdCardType())));
            } else if ("工作单位一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getWorkingPlace() == null ? "" : familyMember1.getWorkingPlace()));
            } else if ("职务一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getPosition() == null ? "" : familyMember1.getPosition()));
            } else if ("现住址一".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getAddress() == null ? "" : familyMember1.getAddress()));
            } else if ("户口所在地一".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getResidenceAddress() == null || ("").equals(familyMember1.getResidenceAddress()) ? "" : (getArea("code=" + familyMember1.getResidenceAddress())));
            } else if ("家庭成员或监护人姓名二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getName() == null ? "" : familyMember2.getName()));
            } else if ("关系二".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getRelation() == null ? "" : (getDictNameByValue("XY-JY-XSJTGX", familyMember2.getRelation())));
            } else if ("联系电话二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getMobile() == null ? "" : familyMember2.getMobile()));
            } else if ("身份证件号二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getIdCardNumber() == null ? "" : familyMember2.getIdCardNumber()));
            } else if ("关系说明二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getRelationRemarks() == null ? "" : familyMember2.getRelationRemarks()));
            } else if ("是否监护人二".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getRank() == null ? "" : (getDictNameByValue("XY-JY-JZLB", familyMember2.getRank())));
            } else if ("民族二".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getRace() == null ? "" : (getDictNameByValue("GB-MZ", familyMember2.getRace())));
            } else if ("身份证类型二".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getIdCardType() == null ? "" : (getDictNameByValue("JY-SFZJLX", familyMember2.getIdCardType())));
            } else if ("工作单位二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getWorkingPlace() == null ? "" : familyMember2.getWorkingPlace()));
            } else if ("职务二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getPosition() == null ? "" : familyMember2.getPosition()));
            } else if ("现住址二".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getAddress() == null ? "" : familyMember2.getAddress()));
            } else if ("户口所在地二".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getResidenceAddress() == null || ("").equals(familyMember2.getResidenceAddress()) ? "" : (getArea("code=" + familyMember2.getResidenceAddress())));
            }
        }
        return param;
    }

    public String getDictNameByValue(String tableCode, String val) {
        if (val != "" || !"".equals(val)) {
            Object obj = this.jcGcCacheService.getNameByValue(tableCode, val);
            return obj == null ? "" : obj.toString();
        }
        return "";
    }

    public String isFlgBoolean(Boolean bool) {
        if (bool != null) {
            if (bool) {
                return "是";
            } else {
                return "否";
            }
        }
        return "未知";
    }


    public String isFlgChild(String bool) {
        if (StrUtil.isNotEmpty(bool)) {
            if (bool.equals("0")) {
                return "非留守儿童";
            } else if (bool.equals("1")) {
                return "单亲留守儿童";
            } else if (bool.equals("2")) {
                return "双亲留守儿童";
            }
        }
        return "未知";
    }

    /**
     * 获取多级地区信息
     *
     * @param tableCode
     * @return
     */
    public String getArea(String tableCode) {
        List<String> strings = new ArrayList<>(3);
        String level = "0";
        do {
            List<Map<String, Object>> jcRegion = jcCacheService.findByExpr("jc_region", tableCode);
            if (CollUtil.isNotEmpty(jcRegion)) {
                String jsonStr = JSONArray.fromObject(jcRegion).toString();
                JSONArray jsonArray = JSONArray.fromObject(jsonStr);
                net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(0);
                level = jsonObject.getString("level");
                String name = jsonObject.getString("name");
                tableCode = "code=" + jsonObject.getString("parent");
                strings.add(name);
            } else {
                return "";
            }
        } while (!level.equals("1"));
        // 倒序
        Collections.reverse(strings);
        String join = CollUtil.join(strings, "-");

        return join;
    }


    @SuppressWarnings("unchecked")
    public Object importParentByFile(InputStream inputStream, String fileName, Integer schoolId,
                                     String schoolYear, Integer userId, String func) throws Exception {

        Map<String, Object> result = new HashMap<String, Object>();
        String suffix = "";
        if (fileName.contains(".xlsx")) {
            suffix = "xlsx";
        } else if (fileName.contains(".xls")) {
            suffix = "xls";
        }

        try {
            /**解析excel文件*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCLWithoutHeader(inputStream, suffix, null);
            String status = (String) map.get("status");

            if ("success".equals(status)) {
                /**excel数据，key为表头，value为单元格数据*/
                String[] header = (String[]) map.get("header");
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");

                /**处理excel数据*/
//				System.out.println("----------------EXCEL数据输出  START--------------------");
//				System.out.println(Arrays.toString(header));
//				for (Map<String, Object> objectMap : data) {
//					System.out.println(objectMap.toString());
//				}
//				System.out.println("----------------EXCEL数据输出  END--------------------");

                result = parentDataHandler(data, header, func, schoolId, schoolYear, userId);
            }
            result.put("status", status);

            String resultJson = JSON.toJSONString(result);
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error");
            String resultJson = JSON.toJSONString(result);
            return resultJson;
        }
    }

    @SuppressWarnings("unchecked")
    public Object importTeamTeacherByFile(InputStream inputStream, String fileName, Integer schoolId,
                                          String schoolYear, Integer userId, String func) throws Exception {
        /**推送文件上传成功状态*/
        List<Subject> subjectList = subjectService.findSubjectsOfSchool(schoolId);
        String[] header = new String[subjectList.size() + 3];
        header[0] = "年级";
        header[1] = "班级";
        header[2] = "班主任";
        for (int i = 3; i < subjectList.size() + 3; i++) {
            header[i] = subjectList.get(i - 3).getName();
        }

        Map<String, Object> result = new HashMap<String, Object>();

        String suffix = "";
        if (fileName.contains(".xlsx")) {
            suffix = "xlsx";
        } else if (fileName.contains(".xls")) {
            suffix = "xls";
        }

        try {
            /**解析excel文件*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCLWithoutHeader(inputStream, suffix, header);
            /**解析excel文件状态*/
            String status = (String) map.get("status");

            /**解析excel成功*/
            if ("success".equals(status)) {
                /**excel数据，key为表头，value为单元格数据*/
                String[] newHeader = (String[]) map.get("header");
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
                /**推送文件解析成功状态*/
                /**处理excel数据*/
                result = teamTeacherDataHandler(data, newHeader, func, schoolId, schoolYear, userId);
            }

            result.put("status", status);
            String resultJson = JSON.toJSONString(result);
            //Thread.sleep(1000*10);
            return resultJson;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status", "error");
            String resultJson = JSON.toJSONString(result);
            return resultJson;
        }
    }

    private Map<String, Object> parentDataHandler(List<Map<String, Object>> data, String[] header, String func,
                                                  Integer schoolId, String schoolYear, Integer userId) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>(3);
        Integer successCount = 0;
        Integer warnCount = 0;
        Integer errorCount = 0;

        List<Object> resultList = new ArrayList<Object>();

        deleteParentTmpCache(userId);

        String code = UUIDUtil.getUUID();
        Integer appId = SysContants.SYSTEM_APP_ID;
        Group group = groupService.findUnique(schoolId, "1");

//		System.out.println("------------------数据输出  START-------------------");
        int count = (header.length - 4) / 3;
        Integer index = 1;
        Integer length = data.size() * count;
        System.out.println(length);

        for (Map<String, Object> map : data) {
            String gradeName = (String) map.get(header[0]);
            String teamName = (String) map.get(header[1]);
            String teamNumber = teamName;
            if (teamName.contains("班")) {
                teamNumber = teamName.substring(0, teamName.indexOf("班"));
            }
            Integer number = Integer.valueOf((String) map.get(header[2]));
            String studentName = (String) map.get(header[3]);
//			System.out.println("gradeName : " + gradeName + " / " + "teamName : " + teamNumber + " / " + "number : " + number + " / " + "studentName : " + studentName);

            for (int i = 1; i <= count; i++) {
                String mobile = (String) map.get("手机号码" + i);
                String parentName = (String) map.get("监护人" + i);
                String relation = (String) map.get("与学生关系" + i);

                if (relation == null || (!"父亲".equals(relation) && !"母亲".equals(relation))) {
                    relation = "其他";
                }

                Map<String, Object> entity = null;
                if (mobile != null && !"".equals(mobile)) {
                    if (parentName == null || "".equals(parentName)) {
                        parentName = studentName + "的家长";
                    }
                    entity = parentService.addParentFromExcelImport(gradeName, teamNumber, studentName, number, parentName, mobile, relation, schoolId, schoolYear, group.getId(), appId);
                } else if (parentName != null && !"".equals(parentName)) {
                    entity = new HashMap<String, Object>();
                    entity.put("gradeName", gradeName);
                    entity.put("teamNumber", teamNumber);
                    entity.put("studentName", studentName);
                    entity.put("number", number);
                    entity.put("parentName", parentName);
                    entity.put("mobile", mobile);
                    entity.put("status", 2);
                    entity.put("errorFiled", "mobile");
                    entity.put("errorInfo", "未填写手机号");
                }

                if (entity != null) {
//					System.out.println(entity.toString());
                    ParentStudentTmp tmp = JSONObject.parseObject(JSON.toJSONString(entity), ParentStudentTmp.class);
                    tmp.setCode(code);
                    Integer status = tmp.getStatus();

                    if (status == 0) {
                        successCount++;
                    } else if (status == 1) {
                        warnCount++;
                    } else {
                        errorCount++;
                    }
                    resultList.add(tmp);
                }

                Integer process = (int) ((index * 1.0 / length) * 100);
                this.addMessage(userId + "", process + "");
                index++;

            }
        }

        if (resultList != null && resultList.size() > 0) {
            schoolInitService.addParentStudentTmpBatch(resultList.toArray());
            String key = "parent_student_tmp_" + userId;
            Map<String, Object> value = new HashMap<String, Object>();
            value.put("code", code);
            value.put("data", new Date());
            baseRedisCache.setCacheObject(key, value);
        }

        Integer tab = Integer.parseInt((schoolId + schoolYear));
        Long size = schoolInitService.countParentStudentTmpByCodeAndStatus(null, tab);
        if (successCount > 0 && size == 0) {
            ParentStudentTmp tmp = new ParentStudentTmp();
            tmp.setStatus(tab);
            List<ParentStudentTmp> list = new ArrayList<>();
            list.add(tmp);
            schoolInitService.addParentStudentTmpBatch(list.toArray());
        }

        result.put("successCount", successCount);
        result.put("warnCount", warnCount);
        result.put("errorCount", errorCount);

//		System.out.println("------------------数据输出  END----------------------");

        return result;
    }

    private Map<String, Object> teamTeacherDataHandler(List<Map<String, Object>> data, String[] header, String func,
                                                       Integer schoolId, String schoolYear, Integer userId) throws Exception {
        Integer successCount = 0;
        Integer warnCount = 0;
        Integer errorCount = 0;
        Map<String, Object> result = new HashMap<String, Object>(3);
        List<Object> resultList = new ArrayList<Object>();

        deleteTeamTeacherTmpCache(userId);

        String code = UUIDUtil.getUUID();
        Integer appId = SysContants.SYSTEM_APP_ID;
        Group group = groupService.findUnique(schoolId, "1");

        Integer length = data.size() * (header.length - 2);
        Integer index = 1;

        for (Map<String, Object> map : data) {
            String gradeName = (String) map.get(header[0]);
            String teamNumber = (String) map.get(header[1]);
            for (int i = 2; i < header.length; i++) {
                String name = (String) map.get(header[i]);
                Integer type = 2;
                if (name != null && !"".equals(name)) {
                    if ("班主任".equals(header[i])) {
                        type = 1;
                    }
                    Map<String, Object> entity = teamTeacherService.addTeamTeacherFromExcelImport(gradeName, teamNumber, name, header[i], type, schoolId, schoolYear, group.getId(), appId);
                    System.out.println(entity);

                    SubjectTeacherTmp tmp = JSONObject.parseObject(JSON.toJSONString(entity), SubjectTeacherTmp.class);
                    tmp.setCode(code);
                    Integer status = tmp.getStatus();

                    if (!resultList.contains(tmp)) {
                        if (status == 0) {
                            successCount++;
                        } else if (status == 1) {
                            warnCount++;
                        } else {
                            errorCount++;
                        }
                        resultList.add(tmp);
                    }

                    Integer process = (int) ((index * 1.0 / length) * 100);
                    this.addMessage(userId + "", process + "");
                }
                System.out.println(index);
                index++;
            }
        }

        if (resultList != null && resultList.size() > 0) {
            schoolInitService.addSubjectTeacherTmpBatch(resultList.toArray());
            String key = "team_teacher_tmp_" + userId;
            Map<String, Object> value = new HashMap<String, Object>();
            value.put("code", code);
            value.put("data", new Date());
            baseRedisCache.setCacheObject(key, value);
        }

        Integer tab = Integer.parseInt((schoolId + schoolYear));
        Long size = schoolInitService.countSubjectTeacherTmpByCodeAndStatus(null, tab);
        if (successCount > 0 && size == 0) {
            SubjectTeacherTmp tmp = new SubjectTeacherTmp();
            tmp.setStatus(tab);
            List<SubjectTeacherTmp> list = new ArrayList<>();
            list.add(tmp);
            schoolInitService.addSubjectTeacherTmpBatch(list.toArray());
        }

        result.put("successCount", successCount);
        result.put("warnCount", warnCount);
        result.put("errorCount", errorCount);

        return result;
    }

    //教师导入
    private Map<String, Object> teacherDataHandler(List<Map<String, Object>> data, String[] header, String func,
                                                   Integer schoolId, String schoolYear, Integer userid) throws Exception {
        Integer successCount = 0;
        Integer warnCount = 0;
        Integer errorCount = 0;
        Map<String, Object> result = new HashMap<String, Object>(3);
        List<Object> resultList = new ArrayList<Object>();

        deleteTeacherTmpCache(userid);
        String code = UUIDUtil.getUUID();

        Integer length = data.size();
        Integer index = 1;
        final List<List<DetailVo>> postData = new ArrayList<>();
        List<HikvisionPerson> hikInfo = new ArrayList<>();



        for (Map<String, Object> map : data) {
            List<EmployeeList> list = new ArrayList<>();
            String name = (String) map.get("姓名");
            String sex = (String) map.get("性别");
            String alias = (String) map.get("别名");
            String phone = (String) map.get("手机号码");
            String department = (String) map.get("部门");
            String position = (String) map.get("职务");
            String gh=(String) map.get("工号");

            String kh=(String) map.get("卡号");

//			HikvisionPerson hik=new HikvisionPerson();
            Integer appid = SysContants.SYSTEM_APP_ID;
            String teacherType = SysContants.USER_TYPE_TEACHER;
            Map<String, Object> entity = null;

            synchronized (SchoolInitDwr.class) {
                entity = teacherService.addTeacherFromExcelImport(gh,kh,name, sex, alias,
                        phone, department, position, schoolId, appid, teacherType, list);
                if(StringUtils.isEmpty((String)entity.get("errorInfo"))) {
                    //推送到海康门禁
                    httpService.addUserToHk(false, gh, kh, HikvisonJob.getHkConfig());
                }
            }
            TeacherTmp tmp = JSONObject.parseObject(JSON.toJSONString(entity), TeacherTmp.class);
            tmp.setCode(code);
            Integer status = tmp.getStatus();

            if (status - 0 == 0) {
                successCount++;
                //postData.add(list);
//				hikInfo.add(hik);
            } else if (status - 1 == 0) {
                warnCount++;
                //postData.add(list);
//                hikInfo.add(hik);
            } else {
                errorCount++;
            }
            resultList.add(tmp);
            Integer proccess = (int) ((index * 1.0 / length) * 100);
            System.out.println("...."+userid+"...proccess"+proccess);
            this.addMessage(userid + "", proccess + "");
            MassagePush.push(proccess + "", func);
            index++;
        }

        if (resultList.size() > 0) {

            schoolInitService.addTeacherTmpBatch(resultList.toArray());
            String key = "teacher_tmp_" + userid;
            Map<String, Object> value = new HashMap<String, Object>();
            value.put("code", code);
            value.put("data", new Date());
            baseRedisCache.setCacheObject(key, value);
        }

        Integer tab = Integer.parseInt((schoolId + schoolYear));

        /**记录已经导入过了老师*/
        Long size = schoolInitService.countTeacherTmpByCodeAndStatus(null, tab);

        if (successCount > 0 && size == 0) {
            TeacherTmp tmp = new TeacherTmp();
            tmp.setStatus(tab);
            List<TeacherTmp> temp = new ArrayList<TeacherTmp>(1);
            temp.add(tmp);
            schoolInitService.addTeacherTmpBatch(temp.toArray());
        }
        result.put("successCount", successCount);
        result.put("warnCount", warnCount);
        result.put("errorCount", errorCount);
        if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
            // 发送数据到食堂接口
                // 批量添加数据 最多50条
            httpService.createShitan(1,address+canteen);
                //httpService.addEmploye(null, address + canteen, null, 1, postData, 0, null);


        } else {
            log.error("调用食堂远程接口失败，请检查配置接口信息是否正确！");
        }
        //图书馆数据后面需要修改
        // 新增时没有添加卡号功能，因为在补卡或者叫修改卡号的时候，也会重新推送用户，修改卡号和新增用户使用同一个接口（由于图书馆没有提供修改卡号功能）
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // 发送用户信息到图书馆
            if (postData != null && postData.size() > 0) {
                libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
            }
        } else {
            log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
        }

        // 发送到海康
//        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//            ArtemisConfig artemisConfig = new ArtemisConfig();
//            artemisConfig.setHost(hikvisionHost);
//            artemisConfig.setAppKey(hikvisionAppKey);
//            artemisConfig.setAppSecret(hikvisionAppSecret);
//            // 0：学生 1：教师
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
//        } else {
//            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
//        }
        return result;
    }

    private void deleteSudentTmpCache(Integer userid) {
        String key = "student_tmp_" + userid;
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if (value != null) {
            String code = (String) value.get("code");
            baseRedisCache.evict(key);
            schoolInitService.removeStudentTmpByCode(code);
        }
    }

    private void deleteTeacherTmpCache(Integer userid) {
        String key = "teacher_tmp_" + userid;
        @SuppressWarnings("unchecked")
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if (value != null) {
            String code = (String) value.get("code");
            baseRedisCache.evict(key);
            schoolInitService.removeTeacherTmpByCode(code);
        }
    }

    private void deleteTeamTeacherTmpCache(Integer userId) {
        String key = "team_teacher_tmp_" + userId;
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if (value != null) {
            String code = (String) value.get("code");
            baseRedisCache.evict(key);
            schoolInitService.removeSubjectTeacherTmpByCode(code);
        }
    }

    private void deleteParentTmpCache(Integer userId) {
        String key = "parent_student_tmp_" + userId;
        Map<String, Object> value = (Map<String, Object>) baseRedisCache.getCacheObject(key);
        if (value != null) {
            String code = (String) value.get("code");
            baseRedisCache.evict(key);
            schoolInitService.removeParentStudentTmpByCode(code);
        }
    }

    //学生导入
    private Map<String, Object> studentDataHandler(List<Map<String, Object>> data, String[] header,
                                                   String func, Integer schoolId, String schoolYear, Integer userid) throws Exception {
        Integer teamCount = 0;
        Integer studentCount = 0;
        Integer parentCount = 0;
        Integer parentError = 0;
        Integer studentError = 0;

        Map<String, Object> result = new HashMap<String, Object>(3);
        List<Object> resultList = new ArrayList<Object>();

        deleteSudentTmpCache(userid);
        String code = UUIDUtil.getUUID();
        Integer length = data.size();
        Integer index = 1;

        List<List<DetailVo>> postData = new ArrayList<>();

        for (Map<String, Object> map : data) {
            String num = (String) map.get("班内学号");
            String kh = (String) map.get("卡号");
            String name = (String) map.get("学生姓名");
            String grade = (String) map.get("年级");
            String team = (String) map.get("班级");
            String guardian = (String) map.get("监护人");
            String guardianPhone = (String) map.get("监护人手机");

            Integer appid = SysContants.SYSTEM_APP_ID;
            String studentType = SysContants.USER_TYPE_STUDENT;
            String parentType = SysContants.USER_TYPE_PARENT;

            Map<String, Object> entity = null;
            List<EmployeeList> list2 = new ArrayList<>();
            synchronized (SchoolInitDwr.class) {
                entity = studentService.addStudentFromExcelImport(kh,grade, team, num, name,
                        guardianPhone, guardian, schoolId, schoolYear, appid, studentType, parentType, list2);
                //推送至海康门禁
                System.out.println(StringUtils.isEmpty((String)entity.get("errorInfo")));
                if(StringUtils.isEmpty((String)entity.get("errorInfo"))) {
                    System.out.println("第二部"+StringUtils.isEmpty((String)entity.get("errorInfo")));
                   String str= httpService.addUserToHk(true, num, kh, HikvisonJob.getHkConfig());
                    System.out.println("str"+str);
                }
            }

            StudentTmp tmp = JSONObject.parseObject(JSON.toJSONString(entity), StudentTmp.class);
            tmp.setCode(code);
            Integer status = tmp.getStatus();

            if (status - 0 == 0) {
                if (entity.get("teamCount") != null) {
                    teamCount++;
                }
                if (entity.get("parent") != null) {
                    parentCount++;
                }
                studentCount++;
               /* if(list.size()>0) {
                    postData.add(list);
                }*/
//				hikInfo.add(hikvisionPerson);
            } else if (status - 1 == 0) {
                /**暂时没有处理这个count*/
            } else {
                if ("guardian".equals(tmp.getErrorFiled())) {
                    parentError++;
                } else if ("guardianPhone".equals(tmp.getErrorFiled())) {
                    parentError++;
                } else {
                    studentError++;
                }
            }
            resultList.add(tmp);
            Integer proccess = (int) ((index * 1.0 / length) * 100);
            this.addMessage(userid + "", proccess + "");
            //Thread.sleep(100);
            index++;
        }

        if (resultList.size() > 0) {
            schoolInitService.addStudentTmpBatch(resultList.toArray());
            String key = "student_tmp_" + userid;
            Map<String, Object> value = new HashMap<String, Object>();
            value.put("code", code);
            value.put("data", new Date());
            baseRedisCache.setCacheObject(key, value);
        }

        Integer tab = Integer.parseInt((schoolId + schoolYear));

        /**记录已经导入过了老师*/
        Long size = schoolInitService.countStudentTmpByCodeAndStatus(null, tab);
        if (studentCount > 0 && size == 0) {
            StudentTmp tmp = new StudentTmp();
            tmp.setStatus(tab);
            List<StudentTmp> temp = new ArrayList<StudentTmp>(1);
            temp.add(tmp);
            schoolInitService.addStudentTmpBatch(temp.toArray());
        }

        result.put("teamCount", teamCount);
        result.put("parentCount", parentCount);
        result.put("studentCount", studentCount);
        result.put("parentError", parentError);
        result.put("studentError", studentError);
        // 判断学生信息是否添加成功 调用远程接口发送数据
       /* if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {*/
        System.out.println("推送开始1");
        if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
            // 发送数据到食堂接口 2021,11有改动，统一的由我们系统推送学生到食堂，补卡开卡也是
            System.out.println("推送开始");
           String str= httpService.createShitan(0,address+canteen);
            System.out.println(str);
        } else {
            log.error("调用远程接口失败，请检查配置接口信息是否正确！");
        }
         //后面修改
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // 发送用户信息到图书馆
            log.info("发送图书馆");
            if (postData != null && postData.size() > 0) {
                libraryService.studentSend(libraryACnteen, libraryLogin, libraryCreate);
            }
        } else {
            log.error("调用图书馆远程接口失败，请检查配置接口信息是否正确！");
        }

        // 定时任务发送用户信息到海康
//        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//            ArtemisConfig artemisConfig = new ArtemisConfig();
//            artemisConfig.setHost(hikvisionHost);
//            artemisConfig.setAppKey(hikvisionAppKey);
//            artemisConfig.setAppSecret(hikvisionAppSecret);
//            // 0：学生 1：教师
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 0);
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
//        } else {
//            log.error("调用海康远程接口失败，请检查配置接口信息是否正确！");
//        }
        return result;
    }
    /**
     * 载入页面时调用，传入userid值作为推送的标识
     */
    public void onPageLoad(String uid) {
        ScriptSession session = WebContextFactory.get().getScriptSession();
        session.setAttribute("uid", uid);
    }

    /**
     * 获取proccess
     */
    public boolean getProccess() {
        return true;
    }

    public void addMessage(String userid, String message) {
        final String userId = userid;
        final String autoMessage = message;
        final ScriptSession session = WebContextFactory.get().getScriptSession();
        //通过ScriptSessionFilter筛选符合条件的ScriptSession
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            /*实现match方法，条件为真为筛选出来的session*/
            public boolean match(ScriptSession session) {
                String uid = null;
                if (session.getAttribute("uid") != null) {
                    uid = session.getAttribute("uid").toString();
                } else {
                    if (userId != null) {
                        onPageLoad(userId);
                    }
                }
                return userId == null ? false : userId.equals(uid);
            }
        }, new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();
            public void run() {
                /*设定前台接受消息的方法和参数*/
                script.appendCall("showMessage", autoMessage);
                /*向所有符合条件的页面推送消息*/
                session.addScript(script);
            }
        });
    }

}
