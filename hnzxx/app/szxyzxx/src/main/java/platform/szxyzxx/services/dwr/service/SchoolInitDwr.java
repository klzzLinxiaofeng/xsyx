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

    //?????????????????????????????????????????????????????????????????????????????????????????????
    @Autowired
    private UserCardJob userCardJob;
    @Autowired
    private HikvisonJob hikvisonJob;

    /**
     * ????????????
     */
    private static String address;
    private static String canteen;

    /**
     * ???????????????
     */
    private static String libraryACnteen;
    private static String libraryLogin;
    private static String libraryCreate;
    private static String canteenIp;
    private static String userListUrl;

    /**
     * ????????????
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
        // ??????
        address = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        canteen = PropertiesUtil.getProperty(fileName, "canteen.server.url");

        // ?????????
        libraryACnteen = PropertiesUtil.getProperty(fileName, "library.server.address");
        libraryLogin = PropertiesUtil.getProperty(fileName, "library.server.Jwt_Login.url");
        libraryCreate = PropertiesUtil.getProperty(fileName, "library.server.create.url");

        // ??????????????????????????????
        canteenIp = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        userListUrl = PropertiesUtil.getProperty(fileName, "canteen.user.list.url");

        //??????
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
        /**??????????????????????????????*/
        String[] header = {"????????????","??????", "????????????", "??????", "??????", "?????????", "???????????????"};

        String suffix = "";
        if (fileName.contains(".xlsx")) {
            suffix = "xlsx";
        } else if (fileName.contains(".xls")) {
            suffix = "xls";
        }

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            /**??????excel??????*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCL(inputStream, suffix, header);
            /**??????excel????????????*/
            String status = (String) map.get("status");

            /**??????excel??????*/
            if ("success".equals(status)) {
                /**excel?????????key????????????value??????????????????*/
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
                /**??????????????????????????????*/
                //MassagePush.push("????????????????????????????????????????????????????????????" + data.size(), func);
                /**??????excel??????*/
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
        /**??????????????????????????????*/
        String[] header = {"??????","??????","??????", "??????", "??????", "????????????", "??????", "??????"};

        Map<String, Object> result = new HashMap<String, Object>();

        String suffix = "";
        if (fileName.contains(".xlsx")) {
            suffix = "xlsx";
        } else if (fileName.contains(".xls")) {
            suffix = "xls";
        }

        try {
            /**??????excel??????*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCL(inputStream, suffix, header);
            /**??????excel????????????*/
            String status = (String) map.get("status");

            /**??????excel??????*/
            if ("success".equals(status)) {
                /**excel?????????key????????????value??????????????????*/
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
                /**??????????????????????????????*/
                /**??????excel??????*/
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
        //???????????????
        HSSFWorkbook workBook = new HSSFWorkbook();

        for (Integer teamId : teamIds) {
            Team team = teamService.findTeamById(teamId);
            String teamName = team.getName();

            Integer gradeId = team.getGradeId();
            if (gradeId - preGradeId != 0) {
                if (result != null && result.size() > 0) {
                    ExcelUtil.exportExcel(workBook, gradeName + "????????????", headers, result, stream);
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

        ExcelUtil.exportExcel(workBook, gradeName + "????????????", headers, result, stream);
        workBook.write(stream);
        stream.flush();
        stream.close();

        String fileName = new String("????????????.xls".getBytes("UTF-8"), "ISO-8859-1");
        FileTransfer fileResult = new FileTransfer(fileName, "application/vnd.ms-excel", stream.toByteArray());

        return fileResult;
    }

    private List<Object> getParam(TeamStudent teamStudent, String[] headers, String teamName, String gradeName) {
        Integer userid = teamStudent.getUserId();
        List<Object> param = new ArrayList<Object>();
        String pname = "";
        String paccount = "";
        String phone = "";
        // ?????????????????????
        List<ParentVo> parents = parentService.findParentsByStudentUserId(userid);
        if (parents.size() > 0) {
            ParentVo parent = parents.get(0);
            paccount = parent.getUserName();
            pname = parent.getName();
            phone = parent.getMobile();
        }
        // ??????????????????
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
            // ????????????
            if ("????????????".equals(header)) {
                param.add(teamStudent.getName());
            } else if ("????????????".equals(header)) {
                //User user = userService.findUserById(userid);
                param.add(student.getUserName());
            } else if ("??????????????????".equals(header)) {
                param.add(paccount);
            }else if ("?????????????????????".equals(header)) {
                param.add(phone);
            } else if ("??????".equals(header)) {
                param.add(person == null || person.getSex() == null ? "" : (getDictNameByValue("GB-XB", person.getSex())));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getBirthday() == null ? "" : UtilDate.getDateFormatter(person.getBirthday(), "yyyy-MM-dd")));
            } else if ("????????????".equals(header)) {
                param.add(person == null || person.getPoliticalStatus() == null ? "" : (getDictNameByValue("GB-ZZMM", person.getPoliticalStatus())));
            } else if ("??????".equals(header)) {
                param.add(person == null || person.getRace() == null ? "" : (getDictNameByValue("GB-MZ", person.getRace())));
            } else if ("??????/??????".equals(header)) {
                param.add(person == null || person.getNationality() == null ? "" : (getDictNameByValue("GB-GJ", person.getNationality())));
            } else if ("?????????".equals(header)) {
                param.add(person == null || person.getBirthPlace() == null || ("").equals(person.getBirthPlace()) ? "" : (getArea("code=" + person.getBirthPlace())));
            } else if ("??????".equals(header)) {
                param.add(person == null || person.getNativePlace() == null || ("").equals(person.getNativePlace()) ? "" : (getArea("code=" + person.getNativePlace())));
            } else if ("??????????????????".equals(header)) {
                param.add(person == null || person.getIdCardType() == null ? "" : (getDictNameByValue("JY-SFZJLX", person.getIdCardType())));
            } else if ("???????????????".equals(header)) {
                param.add(person == null ? "" : (person.getIdCardNumber() == null ? "" : person.getIdCardNumber()));
            } else if ("???????????????".equals(header)) {
                param.add(person == null || person.getAbroadCode() == null ? "" : (getDictNameByValue("JY-GATQW", person.getAbroadCode())));
            } else if ("????????????".equals(header)) {
                param.add(person == null || person.getHealthStatus() == null ? "" : (getDictNameByValue("GB-JKZK", person.getHealthStatus())));
            }
            // ???????????????
            else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getPinyinName() == null ? "" : person.getPinyinName()));
            } else if ("?????????".equals(header)) {
                param.add(person == null ? "" : (person.getUsedName() == null ? "" : person.getUsedName()));
            } else if ("????????????".equals(header)) {
                param.add(person == null || person.getResidenceType() == null ? "" : (getDictNameByValue("GB-HKLB", person.getResidenceType())));
            } else if ("??????????????????".equals(header)) {
                param.add(person == null ? "" : (person.getIdCardDate() == null ? "" : UtilDate.getDateFormatter(person.getIdCardDate(), "yyyy-MM-dd")));
            } else if ("???????????????".equals(header)) {
                param.add(person == null || person.getResidenceAddress() == null || ("").equals(person.getResidenceAddress()) ? "" : (getArea("code=" + person.getResidenceAddress())));
            } else if ("??????".equals(header)) {
                param.add(person == null ? "" : (person.getSpecialSkill() == null ? "" : person.getSpecialSkill()));
            }
            //??????
            else if ("??????".equals(header)) {
                param.add(student.getEmpCard());
            }
            // ???????????????
            else if ("????????????".equals(header)) {
                //student == null ? "" : (student.getStudentNumber() == null ? "" : student.getStudentNumber())
                param.add(student.getEmpCode());
            } else if ("????????????".equals(header)) {
                param.add(student == null ? "" : (student.getEnrollDate() == null ? "" : UtilDate.getDateFormatter(student.getEnrollDate(), "yyyy-MM-dd")));
            } else if ("????????????".equals(header)) {
                param.add(student.getEmpCode());
            } else if ("????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getEnrollType() == null ? "" : (getDictNameByValue("JY-RXFS", studentArchive.getEnrollType())));
            } else if ("??????".equals(header)) {
                param.add(gradeName);
            } else if ("??????".equals(header)) {
                param.add(teamName);
            } else if ("????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getAttendSchoolType() == null ? "" : (getDictNameByValue("JY-JDFS", studentArchive.getAttendSchoolType())));
            } else if ("????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getStudentSource() == null ? "" : (getDictNameByValue("JY-ZXXXSLY", studentArchive.getStudentSource())));
            } else if ("????????????".equals(header)) {
                param.add(student == null ? "" : (student.getLeaveDate() == null ? "" : UtilDate.getDateFormatter(student.getLeaveDate(), "yyyy-MM-dd")));
            } else if ("??????????????????".equals(header)) {
                param.add(student == null || student.getStudyState() == null ? "" : (getDictNameByValue("JY-XSDQZT", student.getStudyState())));
            }
            // ???????????????
            else if ("?????????".equals(header)) {
                param.add(person == null ? "" : (person.getAddress() == null ? "" : person.getAddress()));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getZipCode() == null ? "" : person.getZipCode()));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getEmail() == null ? "" : person.getEmail()));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getHomepage() == null ? "" : person.getHomepage()));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getHomeAddress() == null ? "" : person.getHomeAddress()));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getResideAddress() == null ? "" : person.getResideAddress()));
            } else if ("????????????".equals(header)) {
                param.add(person == null ? "" : (person.getMobile() == null ? "" : person.getMobile()));
            }
            // ???????????????
            else if ("??????????????????".equals(header)) {
                param.add(person == null || person.isOnlyChild() == null ? "" : (isFlgBoolean(person.isOnlyChild())));
            } else if ("????????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsPreeducated() == null ? "" : (isFlgBoolean(studentArchive.getIsPreeducated())));
            } else if ("??????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsUnattendedChild() == null ? "" : (isFlgChild(studentArchive.getIsUnattendedChild())));
            } else if ("????????????????????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsCityLabourChild() == null ? "" : (isFlgBoolean(studentArchive.getIsCityLabourChild())));
            } else if ("????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsOrphan() == null ? "" : (isFlgBoolean(studentArchive.getIsOrphan())));
            } else if ("???????????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsMartyrChild() == null ? "" : (isFlgBoolean(studentArchive.getIsMartyrChild())));
            } else if ("????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getFollowStudy() == null ? "" : (getDictNameByValue("XY-JY-SBJD", studentArchive.getFollowStudy())));
            } else if ("???????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getDisabilityType() == null ? "" : (getDictNameByValue("JY-CJLX", studentArchive.getDisabilityType())));
            } else if ("???????????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsBuyedDegree() == null ? "" : (isFlgBoolean(studentArchive.getIsBuyedDegree())));
            } else if ("????????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsSponsored() == null ? "" : (isFlgBoolean(studentArchive.getIsSponsored())));
            } else if ("??????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getIsFirstRecommended() == null ? "" : (isFlgBoolean(studentArchive.getIsFirstRecommended())));
            }
            // ?????????????????????
            else if ("???????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getSchoolDistance() == null ? "" : (getDictNameByValue("XY-JY-SXXJL", studentArchive.getSchoolDistance())));
            } else if ("?????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getTrafficType() == null ? "" : (getDictNameByValue("XY-JY-JTFS", studentArchive.getTrafficType())));
            } else if ("????????????????????????".equals(header)) {
                param.add(studentArchive == null || studentArchive.getBySchoolBus() == null ? "" : (isFlgBoolean(studentArchive.getBySchoolBus())));
            }
            // ????????????
            else if ("??????".equals(header)) {
                param.add(person == null ? "" : (person.getRemark() == null ? "" : person.getRemark()));
            }

            // ?????????????????????
            else if ("?????????????????????????????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getName() == null ? "" : familyMember1.getName()));
            } else if ("?????????".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getRelation() == null ? "" : (getDictNameByValue("XY-JY-XSJTGX", familyMember1.getRelation())));
            } else if ("???????????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getMobile() == null ? "" : familyMember1.getMobile()));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getIdCardNumber() == null ? "" : familyMember1.getIdCardNumber()));
            } else if ("???????????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getRelationRemarks() == null ? "" : familyMember1.getRelationRemarks()));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getRank() == null ? "" : (getDictNameByValue("XY-JY-JZLB", familyMember1.getRank())));
            } else if ("?????????".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getRace() == null ? "" : (getDictNameByValue("GB-MZ", familyMember1.getRace())));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getIdCardType() == null ? "" : (getDictNameByValue("JY-SFZJLX", familyMember1.getIdCardType())));
            } else if ("???????????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getWorkingPlace() == null ? "" : familyMember1.getWorkingPlace()));
            } else if ("?????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getPosition() == null ? "" : familyMember1.getPosition()));
            } else if ("????????????".equals(header)) {
                param.add(familyMember1 == null ? "" : (familyMember1.getAddress() == null ? "" : familyMember1.getAddress()));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember1 == null || familyMember1.getResidenceAddress() == null || ("").equals(familyMember1.getResidenceAddress()) ? "" : (getArea("code=" + familyMember1.getResidenceAddress())));
            } else if ("?????????????????????????????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getName() == null ? "" : familyMember2.getName()));
            } else if ("?????????".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getRelation() == null ? "" : (getDictNameByValue("XY-JY-XSJTGX", familyMember2.getRelation())));
            } else if ("???????????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getMobile() == null ? "" : familyMember2.getMobile()));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getIdCardNumber() == null ? "" : familyMember2.getIdCardNumber()));
            } else if ("???????????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getRelationRemarks() == null ? "" : familyMember2.getRelationRemarks()));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getRank() == null ? "" : (getDictNameByValue("XY-JY-JZLB", familyMember2.getRank())));
            } else if ("?????????".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getRace() == null ? "" : (getDictNameByValue("GB-MZ", familyMember2.getRace())));
            } else if ("??????????????????".equals(header)) {
                param.add(familyMember2 == null || familyMember2.getIdCardType() == null ? "" : (getDictNameByValue("JY-SFZJLX", familyMember2.getIdCardType())));
            } else if ("???????????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getWorkingPlace() == null ? "" : familyMember2.getWorkingPlace()));
            } else if ("?????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getPosition() == null ? "" : familyMember2.getPosition()));
            } else if ("????????????".equals(header)) {
                param.add(familyMember2 == null ? "" : (familyMember2.getAddress() == null ? "" : familyMember2.getAddress()));
            } else if ("??????????????????".equals(header)) {
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
                return "???";
            } else {
                return "???";
            }
        }
        return "??????";
    }


    public String isFlgChild(String bool) {
        if (StrUtil.isNotEmpty(bool)) {
            if (bool.equals("0")) {
                return "???????????????";
            } else if (bool.equals("1")) {
                return "??????????????????";
            } else if (bool.equals("2")) {
                return "??????????????????";
            }
        }
        return "??????";
    }

    /**
     * ????????????????????????
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
        // ??????
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
            /**??????excel??????*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCLWithoutHeader(inputStream, suffix, null);
            String status = (String) map.get("status");

            if ("success".equals(status)) {
                /**excel?????????key????????????value??????????????????*/
                String[] header = (String[]) map.get("header");
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");

                /**??????excel??????*/
//				System.out.println("----------------EXCEL????????????  START--------------------");
//				System.out.println(Arrays.toString(header));
//				for (Map<String, Object> objectMap : data) {
//					System.out.println(objectMap.toString());
//				}
//				System.out.println("----------------EXCEL????????????  END--------------------");

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
        /**??????????????????????????????*/
        List<Subject> subjectList = subjectService.findSubjectsOfSchool(schoolId);
        String[] header = new String[subjectList.size() + 3];
        header[0] = "??????";
        header[1] = "??????";
        header[2] = "?????????";
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
            /**??????excel??????*/
            Map<String, Object> map = ExcelUtil.readNoSheetEXCLWithoutHeader(inputStream, suffix, header);
            /**??????excel????????????*/
            String status = (String) map.get("status");

            /**??????excel??????*/
            if ("success".equals(status)) {
                /**excel?????????key????????????value??????????????????*/
                String[] newHeader = (String[]) map.get("header");
                List<Map<String, Object>> data = (List<Map<String, Object>>) map.get("data");
                /**??????????????????????????????*/
                /**??????excel??????*/
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

//		System.out.println("------------------????????????  START-------------------");
        int count = (header.length - 4) / 3;
        Integer index = 1;
        Integer length = data.size() * count;
        System.out.println(length);

        for (Map<String, Object> map : data) {
            String gradeName = (String) map.get(header[0]);
            String teamName = (String) map.get(header[1]);
            String teamNumber = teamName;
            if (teamName.contains("???")) {
                teamNumber = teamName.substring(0, teamName.indexOf("???"));
            }
            Integer number = Integer.valueOf((String) map.get(header[2]));
            String studentName = (String) map.get(header[3]);
//			System.out.println("gradeName : " + gradeName + " / " + "teamName : " + teamNumber + " / " + "number : " + number + " / " + "studentName : " + studentName);

            for (int i = 1; i <= count; i++) {
                String mobile = (String) map.get("????????????" + i);
                String parentName = (String) map.get("?????????" + i);
                String relation = (String) map.get("???????????????" + i);

                if (relation == null || (!"??????".equals(relation) && !"??????".equals(relation))) {
                    relation = "??????";
                }

                Map<String, Object> entity = null;
                if (mobile != null && !"".equals(mobile)) {
                    if (parentName == null || "".equals(parentName)) {
                        parentName = studentName + "?????????";
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
                    entity.put("errorInfo", "??????????????????");
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

//		System.out.println("------------------????????????  END----------------------");

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
                    if ("?????????".equals(header[i])) {
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

    //????????????
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
            String name = (String) map.get("??????");
            String sex = (String) map.get("??????");
            String alias = (String) map.get("??????");
            String phone = (String) map.get("????????????");
            String department = (String) map.get("??????");
            String position = (String) map.get("??????");
            String gh=(String) map.get("??????");

            String kh=(String) map.get("??????");

//			HikvisionPerson hik=new HikvisionPerson();
            Integer appid = SysContants.SYSTEM_APP_ID;
            String teacherType = SysContants.USER_TYPE_TEACHER;
            Map<String, Object> entity = null;

            synchronized (SchoolInitDwr.class) {
                entity = teacherService.addTeacherFromExcelImport(gh,kh,name, sex, alias,
                        phone, department, position, schoolId, appid, teacherType, list);
                if(StringUtils.isEmpty((String)entity.get("errorInfo"))) {
                    //?????????????????????
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

        /**??????????????????????????????*/
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
            // ???????????????????????????
                // ?????????????????? ??????50???
            httpService.createShitan(1,address+canteen);
                //httpService.addEmploye(null, address + canteen, null, 1, postData, 0, null);


        } else {
            log.error("???????????????????????????????????????????????????????????????????????????");
        }
        //?????????????????????????????????
        // ??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // ??????????????????????????????
            if (postData != null && postData.size() > 0) {
                libraryService.teacherSend(libraryACnteen, libraryLogin, libraryCreate);
            }
        } else {
            log.error("??????????????????????????????????????????????????????????????????????????????");
        }

        // ???????????????
//        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//            ArtemisConfig artemisConfig = new ArtemisConfig();
//            artemisConfig.setHost(hikvisionHost);
//            artemisConfig.setAppKey(hikvisionAppKey);
//            artemisConfig.setAppSecret(hikvisionAppSecret);
//            // 0????????? 1?????????
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
//        } else {
//            log.error("???????????????????????????????????????????????????????????????????????????");
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

    //????????????
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
            String num = (String) map.get("????????????");
            String kh = (String) map.get("??????");
            String name = (String) map.get("????????????");
            String grade = (String) map.get("??????");
            String team = (String) map.get("??????");
            String guardian = (String) map.get("?????????");
            String guardianPhone = (String) map.get("???????????????");

            Integer appid = SysContants.SYSTEM_APP_ID;
            String studentType = SysContants.USER_TYPE_STUDENT;
            String parentType = SysContants.USER_TYPE_PARENT;

            Map<String, Object> entity = null;
            List<EmployeeList> list2 = new ArrayList<>();
            synchronized (SchoolInitDwr.class) {
                entity = studentService.addStudentFromExcelImport(kh,grade, team, num, name,
                        guardianPhone, guardian, schoolId, schoolYear, appid, studentType, parentType, list2);
                //?????????????????????
                System.out.println(StringUtils.isEmpty((String)entity.get("errorInfo")));
                if(StringUtils.isEmpty((String)entity.get("errorInfo"))) {
                    System.out.println("?????????"+StringUtils.isEmpty((String)entity.get("errorInfo")));
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
                /**????????????????????????count*/
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

        /**??????????????????????????????*/
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
        // ???????????????????????????????????? ??????????????????????????????
       /* if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {*/
        System.out.println("????????????1");
        if (address != null && !address.equals("") && canteen != null && !canteen.equals("")) {
            // ??????????????????????????? 2021,11??????????????????????????????????????????????????????????????????????????????
            System.out.println("????????????");
           String str= httpService.createShitan(0,address+canteen);
            System.out.println(str);
        } else {
            log.error("?????????????????????????????????????????????????????????????????????");
        }
         //????????????
        if (StrUtil.isNotEmpty(libraryACnteen) && StrUtil.isNotEmpty(libraryLogin) && StrUtil.isNotEmpty(libraryCreate)) {
            // ??????????????????????????????
            log.info("???????????????");
            if (postData != null && postData.size() > 0) {
                libraryService.studentSend(libraryACnteen, libraryLogin, libraryCreate);
            }
        } else {
            log.error("??????????????????????????????????????????????????????????????????????????????");
        }

        // ???????????????????????????????????????
//        if (StrUtil.isNotEmpty(hikvisionAddPersonUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey)  && StrUtil.isNotEmpty(hikvisionAppSecret) && StrUtil.isNotEmpty(hikvisionBindCarUrl)) {
//            ArtemisConfig artemisConfig = new ArtemisConfig();
//            artemisConfig.setHost(hikvisionHost);
//            artemisConfig.setAppKey(hikvisionAppKey);
//            artemisConfig.setAppSecret(hikvisionAppSecret);
//            // 0????????? 1?????????
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 0);
//            httpService.addHikvisionPerson(artemisConfig, hikvisionAddPersonUrl, hikvisionBindCarUrl, untieCarUrl, 1);
//        } else {
//            log.error("???????????????????????????????????????????????????????????????????????????");
//        }
        return result;
    }
    /**
     * ??????????????????????????????userid????????????????????????
     */
    public void onPageLoad(String uid) {
        ScriptSession session = WebContextFactory.get().getScriptSession();
        session.setAttribute("uid", uid);
    }

    /**
     * ??????proccess
     */
    public boolean getProccess() {
        return true;
    }

    public void addMessage(String userid, String message) {
        final String userId = userid;
        final String autoMessage = message;
        final ScriptSession session = WebContextFactory.get().getScriptSession();
        //??????ScriptSessionFilter?????????????????????ScriptSession
        Browser.withAllSessionsFiltered(new ScriptSessionFilter() {
            /*??????match???????????????????????????????????????session*/
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
                /*??????????????????????????????????????????*/
                script.appendCall("showMessage", autoMessage);
                /*??????????????????????????????????????????*/
                session.addScript(script);
            }
        });
    }

}
