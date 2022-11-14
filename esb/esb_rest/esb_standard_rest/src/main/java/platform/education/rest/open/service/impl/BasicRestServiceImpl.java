package platform.education.rest.open.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.generalcode.service.JcCacheService;
import platform.education.rest.common.ResponseDto;
import platform.education.rest.common.ResponseError;
import platform.education.rest.common.ResponseInfo;
import platform.education.rest.common.ResponseVo;
import platform.education.rest.common.constants.CommonCode;
import platform.education.rest.common.constants.SysContants;
import platform.education.rest.open.service.BasicRestService;
import platform.education.rest.util.ImgUtil;
import platform.education.rest.util.encoderUtil;
import platform.education.user.model.*;
import platform.education.user.service.*;
import platform.education.user.vo.UserVo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/11/10.
 */
public class BasicRestServiceImpl implements BasicRestService {

    @Autowired
    @Qualifier("jcCacheService")
    private JcCacheService jcCacheService;

    @Autowired
    @Qualifier("schoolService")
    private SchoolService schoolService;

    @Autowired
    @Qualifier("appEditionService")
    private AppEditionService appEditionService;

    @Autowired
    @Qualifier("groupService")
    private GroupService groupService;

    @Autowired
    @Qualifier("groupUserService")
    private GroupUserService groupUserService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("profileService")
    private ProfileService profileService;

    @Autowired
    @Qualifier("userRoleService")
    private UserRoleService userRoleService;

    @Autowired
    @Qualifier("roleService")
    private RoleService roleService;

    @Autowired
    @Qualifier("teacherService")
    private TeacherService teacherService;

    @Autowired
    @Qualifier("departmentTeacherService")
    private DepartmentTeacherService departmentTeacherService;

    @Autowired
    @Qualifier("subjectTeacherService")
    private SubjectTeacherService subjectTeacherService;

    @Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;

    @Autowired
    @Qualifier("schoolTermCurrentService")
    private SchoolTermCurrentService schoolTermCurrentService;

    @Autowired
    @Qualifier("gradeService")
    private GradeService gradeService;

    @Autowired
    @Qualifier("teamService")
    private TeamService teamService;

    @Autowired
    @Qualifier("studentService")
    private StudentService studentService;

    @Autowired
    @Qualifier("parentService")
    private ParentService parentService;

    @Autowired
    @Qualifier("parentStudentService")
    private ParentStudentService parentStudentService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("schoolYearService")
    private SchoolYearService schoolYearService;
    
    @Autowired
    @Qualifier("subjectService")
    private SubjectService subjectService;

    @Autowired
    @Qualifier("teamAdminService")
    private  TeamAdminService teamAdminService;

    @Autowired
    @Qualifier("schoolTermService")
    private  SchoolTermService schoolTermService;

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 判断参数是否为空
     * @param object 参数
     * @param param  参数名
     * @param detail 解释
     */
    private Object judgeNull(Object object, String param, String detail) {
        if (object == null || "".equals(object)) {
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数为空");
            info.setDetail(detail + "不能为空");
            info.setParam(param);
            return new ResponseError(CommonCode.$REQUIRED_PARAMETER_IS_NULL, info);
        }
        return null;
    }

    /**
     * 接口公用异常返回
     * @return  060103  接口调用发生异常
     */
    private Object interfacesAbnormal(){
        ResponseInfo info = new ResponseInfo();
        info.setMsg("调用异常");
        info.setDetail("接口调用发生错误");
        info.setParam("");
        return new ResponseError(CommonCode.$ABNORMAL_INTERFACE_CALL, info);
    }

    /**
     * 提取版本号的信息--timeStamp和id
     * @return 数据正确，返回Object[]；数据错误，返回错误信息
     */
    private Object getVersionMsg(String version) {
        Object[] objects = new Object[3];
        try {
            if (version != null && !"".equals(version) && !"0".equals(version)) {
                String[] strs = version.split("&");
                String regexPattern = "^[0-9]*$";
                Pattern pattern = Pattern.compile(regexPattern);
                Matcher matcher = pattern.matcher(strs[0]);
                if (!matcher.matches()) {
                    objects[0] = sdf.parse(strs[0]);
                    objects[2] = false;
                } else {
                    objects[0] = new Date(Long.parseLong(strs[0]));
                    objects[2] = true;
                }
                objects[1] = Integer.valueOf(strs[1]);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数格式错误");
            info.setDetail("参数前段{TimeStamp}格式有误");
            info.setParam("version");
            return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数格式错误");
            info.setDetail("参数后段id格式有误");
            info.setParam("version");
            return new ResponseError(CommonCode.$PARAMETER_FORMAT_ERROR, info);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseInfo info = new ResponseInfo();
            info.setMsg("参数错误");
            info.setDetail("version参数内容有误");
            info.setParam("version");
            return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
        }
        return objects;
    }

    /**
     * 获取版本号
     */
    private String getVersion(List<Date> times, List<Integer> ids, String version, Integer objectId, Boolean isTimeStamp) {
        String version_back = "";
        if (isTimeStamp == null) {
            isTimeStamp = true;
        }
        String timeStamp = getMaxModifyDate(times, isTimeStamp);
        Integer id = getMaxId(ids);
        //增量时，最大ID从新增列表中获取，若有修改时间而无id，即只删改而无新增，返回原ID
        if (timeStamp != null && !"".equals(timeStamp)) {
            if (id != null ) {
                version_back = timeStamp + "&" + id;
            } else {
                version_back = timeStamp + "&" + objectId;
            }
        }
        //当两次同步之间无数据改动时，返回原版本号
        if ("".equals(version_back)) {
            version_back = version;
        }
        return version_back;
    }

    private String getMaxModifyDate(List<Date> times, boolean isTimeStamp){
        String time = "";
        if (times != null && times.size() > 0 ) {
            Date maxDate = times.get(0);
            for (int i = 0; i < times.size(); i++) {
                if (times.get(i).after(maxDate)) {
                    maxDate = times.get(i);
                }
            }
            if (isTimeStamp) {
                time = maxDate.getTime() + "";
            } else {
                time = sdf.format(maxDate);
            }


        }
        return time;
    }

    private Integer getMaxId(List<Integer> ids){
        Integer maxId = null;
        if (ids != null && ids.size() > 0) {
            maxId = ids.get(0);
            for (int i = 0; i < ids.size(); i++) {
                if (ids.get(i) > maxId) {
                    maxId = ids.get(i);
                }
            }
        }
        return maxId;
    }

    /**
     * 返回结果排序--最后修改时间升序
     */
    private void sortList(List<Object> list){
        Collections.sort(list, new Comparator<Object>() {
            public int compare(Object o1, Object o2){
                Map<String, Object> m1 = (Map) o1;
                Map<String, Object> m2 = (Map) o2;
                Date t1 = null;
                Date t2 = null;
                try {
                    t1 = sdf.parse((String) m1.get("modifiedTime"));
                    t2 = sdf.parse((String) m2.get("modifiedTime"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (t1.after(t2)) {
                    return 1;
                } else if (t1.before(t2)){
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }
 
    @Override
    public Object getSchoolList(String sign, String appKey, String timeStamp, String version) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            Date modifyTime = null;
            Integer schoolId = null;

            //1.获取version中的数据
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                schoolId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }

            //2.判断是否初次全量获取
            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                //增量获取 -- 版本号中的 最后修改时间和ID必传
                //新增的
                List<School> added = schoolService.findIncrementData(false, modifyTime, schoolId,true);
                //更新的
                List<School> updated = schoolService.findIncrementData(false, modifyTime, schoolId,false);
                //删除的
                List<School> deleted = schoolService.findIncrementData(true, modifyTime, schoolId,false);

                addSchoolInfo(list, added, times, ids, "added");
                addSchoolInfo(list, updated, times, ids, "updated");
                addSchoolInfo(list, deleted, times, ids, "deleted");
                sortList(list);

            } else {
                //全量获取
                List<School> schoolList = schoolService.findIncrementData(false,null,null,null);
                addSchoolInfo(list, schoolList, times, ids, "added");
            }
            version_back = getVersion(times, ids, version, schoolId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0", version_back, list);
    }

    private void addSchoolInfo(List<Object> list, List<School> schoolList, List<Date> times, List<Integer> ids, String status) {
        if (schoolList != null && schoolList.size() > 0) {
            //将学校信息转存在返回的集合中
            for (School school : schoolList) {
                String stageScope = school.getStageScope();
                stageScope = stageScope.replaceAll("-1,|,-1", "");
                String province = getRegionName(school.getProvince());
                String city = getRegionName(school.getCity());
                String district = getRegionName(school.getDistrict());
                String area = province + city + district;
                area = area.substring(2, area.length());
                Map<String, Object> map = new HashMap<>();
                map.put("schoolId", school.getId());
                map.put("schoolName", school.getName());
                map.put("stageScope", stageScope);
                map.put("area", area);
                map.put("code", school.getCode());
                map.put("address", school.getAddress());
                map.put("schoolType", school.getSchoolType());
                map.put("schoolBadge", ImgUtil.getImgUrl(school.getEntityId_badge()));
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(school.getModifyDate()));

                list.add(map);
            }
            //根据status的状态来设置times和ids集合的数据
            //status: all=初次(全量) add=新增 update=修改 delete=删除
            //全量，增量时，都已排序，只拿最后一条记录
            times.add(schoolList.get(schoolList.size()-1).getModifyDate());
            //全量和新增时，获取全部id排序，其他情况不用
            if ("all".equals(status) || "added".equals(status)) {
                for (School school : schoolList) {
                    ids.add(school.getId());
                }
            }

        }
    }

    /**
     *  获取地区码对应的地区名
     */
    private String getRegionName(String code) {
        String name = "";
        if (code != null && !"".equals(code)) {
            code = String.valueOf(jcCacheService.findById("jc_region", code, "name"));
        }
        if (code != null) {
            name = ", " + code;
        }
        return name;
    }



    @Override
    public Object getAccountList(String sign, String appKey, String timeStamp, String version, Integer schoolId, String type) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            //1.判断schoolId是否为空
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;
            //2.判断type内容是否正确
            if (type == null || "".equals(type)) {
                type = "0";
            } else if (!"0".equals(type) && !"1".equals(type) && !"2".equals(type) && !"3".equals(type)
                    && !"4".equals(type) && !"5".equals(type) && !"6".equals(type) && !"7".equals(type)) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("type参数内容有误");
                info.setParam("type");
                return new ResponseError(CommonCode.$PARAMETER_VALUE_CONTENT_ERROR, info);
            }
            //3.获取version版本数据
            Date modifyTime = null;
            Integer userId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                userId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }

            //4.判断学校是否存在
            Group group = groupService.findUnique(schoolId, "1");
            if (group == null) {
                ResponseInfo info = new ResponseInfo();
                info.setMsg("参数错误");
                info.setDetail("输入的参数有误，找不到学校");
                info.setParam("schoolId");
                return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
            }
            //5.根据type获取用户类型
            String[] userTypes = getUserTypes(type);

            //6.判断全量/增量
            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                //新增
                List<UserVo> added = userService.findUserByUserTypes(group.getId(), userTypes, modifyTime, userId, true, false);
                //更新
                List<UserVo> updated = userService.findUserByUserTypes(group.getId(), userTypes, modifyTime, userId, false, false);
                //删除
                List<UserVo> deleted = userService.findUserByUserTypes(group.getId(), userTypes, modifyTime, userId, false , true);
                addUserInfo(list, added, times, ids, "added");
                addUserInfo(list, updated, times, ids, "updated");
                addUserInfo(list, deleted, times, ids, "deleted");
                sortList(list);
            } else {
                List<UserVo> userVos = userService.findUserByUserTypes(group.getId(), userTypes, null, null, null, false);
                addUserInfo(list, userVos, times, ids, "added");

            }
            version_back = getVersion(times, ids, version, userId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }

    private void addUserInfo(List<Object> list, List<UserVo> userVos, List<Date> times, List<Integer> ids, String status) {
        if (userVos != null && userVos.size() > 0) {
            for (UserVo user : userVos) {
                Profile profile = profileService.findByUserId(user.getId());
                String nickName = "";
                String icon = "";
                if (profile != null) {
                    nickName = profile.getNickname() != null ? profile.getNickname() : "";
                    icon = ImgUtil.getImgUrl(profile.getIcon());
                }

                Map<String, Object> map = new HashMap<>();
                map.put("userId", user.getId());
                map.put("userName", user.getUserName());
                map.put("nickname", nickName);
                map.put("icon", icon);
                map.put("state", user.getState());
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(user.getModifyDate()));

                List<Object> roleList = new ArrayList<>();
                if (user.getRoleInfo() != null && !"".equals(user.getRoleInfo())){
                    String[] roleInfos = user.getRoleInfo().split(";");
                    for (String info : roleInfos) {
                        String[] roles = info.split(",");
                        Map<String, Object> roleMap = new HashMap<>();
                        roleMap.put("roleId", roles[0]);
                        roleMap.put("roleCode", roles[1]);
//                    roleMap.put("roleName", roles[2]);
                        roleMap.put("type", roles[3]);
                        roleList.add(roleMap);
                    }
                }
                map.put("roleList", roleList);

                list.add(map);
            }
            //按修改时间排序，最后一条为最大值
            times.add(userVos.get(userVos.size()-1).getModifyDate());
            if ("all".equals(status) || "added".equals(status)) {
                for (UserVo user : userVos) {
                    ids.add(user.getId());
                }
            }

        }
    }

    /**
     * yh_role.user_type：  1=教师 2=管理员 3=家长 4=学生
     * type： 0=全部 1=教师 2=家长 3=学生 4=教/家 5=教/学 6=家/学 7=学校管理员(SCHOOL_MANAGER)
     */
    private String[] getUserTypes(String type) {
        String[] userTypes = null;
        if ("0".equals(type)) {
            userTypes = new String[]{"1", "3", "4", "2"};
        } else if ("1".equals(type)) {
            userTypes = new String[]{"1"};
        } else if ("2".equals(type)) {
            userTypes = new String[]{"3"};
        } else if ("3".equals(type)) {
            userTypes = new String[]{"4"};
        } else if ("4".equals(type)) {
            userTypes = new String[]{"1", "3"};
        } else if ("5".equals(type)) {
            userTypes = new String[]{"1", "4"};
        } else if ("6".equals(type)) {
            userTypes = new String[]{"3", "4"};
        } else if ("7".equals(type)){
            userTypes = new String[]{"2"};
        }
        return userTypes;
    }

    /**
     * 判断是否有当前学期（学年）
     */
    private Object judgeSchoolTermCurrent(SchoolTermCurrent current){
        if (current == null) {
            ResponseInfo info = new ResponseInfo();
            info.setMsg("找不到数据");
            info.setDetail("该学校未设置当前学年");
            info.setParam("");
            return new ResponseError(CommonCode.D$DATA_NOT_FOUND, info);
        } else {
            return null;
        }
    }
    
    @Override
    public Object getTeacherList(String sign, String appKey, String timeStamp, String version, Integer schoolId) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            //1.判断schooId是否为空
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;
            //2.获取版本信息
            Date modifyTime = null;
            Integer teacherId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                teacherId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }

            //3.获取当前学年
            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }
            //4.增量 或 全量 获取数据
            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                List<TeacherVo> added = teacherService.findIncrementDataByModifyDate(schoolId, false, modifyTime, teacherId, true);
                List<TeacherVo> updated = teacherService.findIncrementDataByModifyDate(schoolId, false, modifyTime, teacherId, false);
                List<TeacherVo> deleted = teacherService.findIncrementDataByModifyDate(schoolId, true, modifyTime, teacherId, false);
                addTeacherInfo(schoolId, schoolYear, list, added, modifyTime, times, ids, "added");
                addTeacherInfo(schoolId, schoolYear, list, updated, modifyTime, times, ids, "updated");
                addTeacherInfo(schoolId, schoolYear, list, deleted, modifyTime, times, ids, "deleted");
                sortList(list);
            } else {
                List<TeacherVo> teacherList = teacherService.findIncrementDataByModifyDate(schoolId, false, null, null, null);
                addTeacherInfo(schoolId, schoolYear, list, teacherList, modifyTime, times, ids, "added");

            }
            version_back = getVersion(times, ids, version, teacherId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }
    
    private void addTeacherInfo(Integer schoolId, String schoolYear, List<Object> list, List<TeacherVo> teacherList, Date timeStamp, List<Date> times, List<Integer> ids, String status) throws ParseException {
        if (teacherList != null && teacherList.size() > 0) {
            for (TeacherVo teacher : teacherList) {
                Map<String, Object> map = new HashMap<>();
                //基本信息
                map.put("userId", teacher.getUserId());
                map.put("name", teacher.getName());
                map.put("sex", teacher.getSex() != null && !"".equals(teacher.getSex()) ? teacher.getSex() : "0");
                map.put("mobile", teacher.getMobile() != null ? teacher.getMobile() : "");
                map.put("email", teacher.getEmail() != null ? teacher.getEmail() : "");
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(teacher.getModifyDate()));

                //部门信息
                List<Object> departList = new ArrayList<>();
                List<DepartmentTeacher> departmentTeachers = departmentTeacherService.findDepartmentTeacherByTeacherIdAndSchoolId(teacher.getId(), schoolId);
                for (DepartmentTeacher department : departmentTeachers) {
                    Map<String, Object> departMap = new HashMap<>();
                    departMap.put("departmentId", department.getDepartmentId());
                    departList.add(departMap);
                }
                map.put("departmentList", departList);

                //获取修改时间后的department_teacher记录，若有记录表示有改动；全量时timeStamp=null，默认有改动
                Boolean isModify_dept = false;
                if (timeStamp != null) {
                    List<DepartmentTeacher> departmentTeacherList = departmentTeacherService.findIncrementByTeacherId(schoolId, teacher.getId(), timeStamp, null);
                    if (departmentTeacherList != null && departmentTeacherList.size() > 0) {
                        isModify_dept = true;
                    }
                } else {
                    isModify_dept = true;
                }

                //班级、科目信息
                List<Object> teamList = new ArrayList<>();
                TeamTeacherCondition ttCondition = new TeamTeacherCondition();
                ttCondition.setSchoolId(schoolId);
                ttCondition.setSchoolYear(schoolYear);
                ttCondition.setTeacherId(teacher.getId());
                ttCondition.setUserId(teacher.getUserId());
                List<TeamTeacherVo> teamTeacherVos = teamTeacherService.findVoWithSubjectInfo(ttCondition);
                for (TeamTeacherVo vo : teamTeacherVos) {
                    Map<String, Object> teamMap = new HashMap<>();
                    teamMap.put("teamId", vo.getTeamId());
                    teamMap.put("teamCode", vo.getTeamCode());
                    teamMap.put("schoolYear", schoolYear);
                    teamMap.put("type", getTeacherType(vo.getTypes()));

                    List<Object> subjectList = new ArrayList<>();
                    if (vo.getSubjectInfo() != null && !"".equals(vo.getSubjectInfo())) {
                        String[] subjectInfo = vo.getSubjectInfo().split(";");
                        for (String s : subjectInfo) {
                            String[] subject = s.split(",");
                            Map<String, Object> subjectMap = new HashMap<>();
                            subjectMap.put("subjectCode", subject[0]);
                            subjectMap.put("subjectName", subject[1]);
                            subjectList.add(subjectMap);
                        }
                    }

                    teamMap.put("subjectList", subjectList);
                    teamList.add(teamMap);
                }
                map.put("teamList", teamList);

                //添加班级管理员信息（级长）
                List<Object> manageTeams = getTeamAdminList(schoolId,schoolYear, teacher.getId());
                map.put("manageTeams", manageTeams);

                //任教班级是否有改动
                Boolean isModify_team = false;
                if (timeStamp != null) {
                    List<TeamTeacher> teamTeacherList = teamTeacherService.findIncrementByTeacherId(schoolId, schoolYear, teacher.getId(), timeStamp, null);
                    if (teamTeacherList != null && teamTeacherList.size() > 0) {
                        isModify_team = true;
                    }
                    List<TeamAdmin> teamAdmins = teamAdminService.findIncrementByTeacherId(schoolId, schoolYear, teacher.getId(), timeStamp, null);
                    if (teamAdmins != null && teamAdmins.size() > 0) {
                        isModify_team = true;
                    }
                } else {
                    isModify_team = true;
                }

                //数据修改范围：根据teacher、department_teacher、team_teacher表的修改时间来判断
                //数据有变动，teacher表修改时间必动，所以必定返回1=基础数据
                String modifiedType = "1";
                if (isModify_dept && isModify_team) {
                    modifiedType = "1,2,3";
                } else if (isModify_dept) {
                    modifiedType = "1,2";
                } else if (isModify_team){
                    modifiedType = "1,3";
                }
                map.put("modifiedType", modifiedType);

                list.add(map);
            }
            times.add(teacherList.get(teacherList.size() - 1).getModifyDate());
            if ("all".equals(status) || "added".equals(status)) {
                for (TeacherVo vo : teacherList) {
                    ids.add(vo.getId());
                }
            }
        }
    }

    private String getTeacherType(String types) {
        String type = "";
        if (types.contains("1") && types.contains("2")) {
            type = "3";
        } else if (types.contains("1")) {
            type = "1";
        } else if (types.contains("2")) {
            type = "2";
        }
        return type;
    }

    private List<Object> getTeamAdminList(Integer schoolId, String schoolYear, Integer teacherId) {
        List<Object> list = new ArrayList<>();
        List<TeamAdminVo> teamAdminList = teamAdminService.findByTeacherId(schoolId, schoolYear, teacherId);
        Map<Integer, List<TeamAdminVo>> gMap = new HashMap<>();
        List<TeamAdminVo> teamAdminVos = null;
        for (TeamAdminVo vo : teamAdminList) {
            Integer gradeId = vo.getGradeId();
            if (gMap.containsKey(gradeId)) {
                teamAdminVos = gMap.get(gradeId);
            } else {
                teamAdminVos = new ArrayList<>();
            }
            teamAdminVos.add(vo);
            gMap.put(gradeId, teamAdminVos);
        }
        for (Integer gradeId : gMap.keySet()) {
            List<Object> teamList = new ArrayList<>();
            String uniGradeCode = "";
            for (TeamAdminVo vo : teamAdminList) {
                if (gradeId.equals(vo.getGradeId())) {
                    Map<String, Object> teamMap = new HashMap<>();
                    uniGradeCode = vo.getGradeCode();
                    teamMap.put("teamId", vo.getTeamId());
                    teamMap.put("teamCode", vo.getTeamCode());
                    teamList.add(teamMap);
                }
            }

            Map<String, Object> gradeMap = new HashMap<>();
            gradeMap.put("gradeId", gradeId);
            gradeMap.put("gradeCode", getGradeCode(schoolYear, uniGradeCode));
            gradeMap.put("gbGradeCode", uniGradeCode);
            gradeMap.put("schoolYear", schoolYear);
            gradeMap.put("teamList", teamList);
            list.add(gradeMap);
        }
        return list;
    }


    @Override
    public Object getStudentList(String sign, String appKey, String timeStamp, String version, Integer schoolId) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;

            Date modifyTime = null;
            Integer studentId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                studentId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }
            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }

            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                List<StudentVo> added = studentService.findIncrementDataByModifyDate(schoolId, schoolYear, false, modifyTime, studentId, true);
                List<StudentVo> updated = studentService.findIncrementDataByModifyDate(schoolId, schoolYear, false, modifyTime, studentId, false);
                List<StudentVo> deleted = studentService.findIncrementDataByModifyDate(schoolId, schoolYear, true, modifyTime, studentId, false);
                addStudentInfo(schoolYear, list, added, times, ids, "added");
                addStudentInfo(schoolYear, list, updated, times, ids, "updated");
                addStudentInfo(schoolYear, list, deleted, times, ids, "deleted");
                sortList(list);

            } else {
                List<StudentVo> studentVoList = studentService.findIncrementDataByModifyDate(schoolId, schoolYear, false, null, null, null);
                addStudentInfo(schoolYear, list, studentVoList, times, ids, "added");
            }
            version_back = getVersion(times, ids, version, studentId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }

    private void addStudentInfo(String schoolYear,List<Object> list, List<StudentVo> studentVoList, List<Date> times, List<Integer> ids, String status) {
        if (studentVoList != null && studentVoList.size() > 0) {
            for (StudentVo student : studentVoList) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", student.getUserId());
                map.put("name", student.getName());
                map.put("sex", student.getSex() != null && !"".equals(student.getSex()) ? student.getSex() : "0");
                map.put("email", student.getEmail() != null ? student.getEmail() : "");
                map.put("schoolYear", schoolYear);
                map.put("teamId", student.getTeamId());
                map.put("teamCode", student.getTeamCode());
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(student.getModifyDate()));

                list.add(map);
            }
            times.add(studentVoList.get(studentVoList.size()-1).getModifyDate());
            if ("all".equals(status) || "added".equals(status)) {
                for (StudentVo vo : studentVoList) {
                    ids.add(vo.getId());
                }
            }
        }
    }

    @Override
    public Object getParentList(String sign, String appKey, String timeStamp, String version, Integer schoolId) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
             list = new ArrayList<>();
             Object o1 = judgeNull(schoolId, "schoolId", "学校id");
             if (o1 != null) return o1;

            Date modifyTime = null;
            Integer parentId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                parentId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }

            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                List<ParentVo> added = parentService.findIncrementDataByModifyDate(schoolId, false, modifyTime, parentId, true);
                List<ParentVo> updated = parentService.findIncrementDataByModifyDate(schoolId, false, modifyTime, parentId, false);
                List<ParentVo> deleted = parentService.findIncrementDataByModifyDate(schoolId, true, modifyTime, parentId, false);
                addParentInfo(list, added, times, ids, schoolId, modifyTime, "added");
                addParentInfo(list, updated, times, ids, schoolId, modifyTime, "updated");
                addParentInfo(list, deleted, times, ids, schoolId, modifyTime, "deleted");
                sortList(list);
            } else {
                List<ParentVo> parentVoList = parentService.findIncrementDataByModifyDate(schoolId, false, null, null, null);
                addParentInfo(list, parentVoList, times, ids, schoolId, modifyTime, "added");
            }
            version_back = getVersion(times, ids, version, parentId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }

    private void addParentInfo(List<Object> list, List<ParentVo> parentVoList, List<Date> times, List<Integer> ids, Integer schoolId, Date timeStamp, String status) {
        if (parentVoList != null && parentVoList.size() > 0) {
            for (ParentVo parent : parentVoList) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", parent.getUserId());
                map.put("name", parent.getName());
                map.put("sex", parent.getSex() != null && !"".equals(parent.getSex()) ? parent.getSex() : "0");
                map.put("mobile", parent.getMobile() != null ? parent.getMobile() : "");
                map.put("email", parent.getEmail() != null ? parent.getEmail() : "");
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(parent.getModifyDate()));

                List<Object> studentList = new ArrayList<>();
                if (parent.getStudentInfo() != null && !"".equals(parent.getStudentInfo())) {
                    String[] studentInfo = parent.getStudentInfo().split(";");
                    for (String str : studentInfo) {
                        String[] info = str.split(",");
                        Map<String, Object> stuMap = new HashMap<>();
                        stuMap.put("studentUserId", info[0]);
//                        stuMap.put("studentName", info[1]);
                        stuMap.put("parentRelation", info[2]);
                        stuMap.put("rank", info[3]);
                        studentList.add(stuMap);
                    }
                }
                map.put("studentList", studentList);

                //全量时timeStamp为null，默认全改动；增量时，判断是否改动
                Boolean isModify = false;
                if (timeStamp != null) {
                    List<ParentStudent> parentStudents = parentStudentService.findIncrementByParentUserId(schoolId, parent.getUserId(), timeStamp, null);
                    if (parentStudents != null && parentStudents.size() > 0) {
                        isModify = true;
                    }
                } else {
                    isModify = true;
                }
                String modifiedType = "1";
                if (isModify) {
                    modifiedType = "1,2";
                }
                map.put("modifiedType", modifiedType);

                list.add(map);
            }
            times.add(parentVoList.get(parentVoList.size()-1).getModifyDate());
            if ("all".equals(status) || "added".equals(status)) {
                for (ParentVo vo : parentVoList) {
                    ids.add(vo.getId());
                }
            }
        }
    }

    @Override
    public Object getGradeList(String sign, String appKey, String timeStamp, String version, Integer schoolId) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }

        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;

            Date modifyTime = null;
            Integer gardeId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                gardeId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }

            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }

            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                List<Grade> added = gradeService.findIncrementByModifyDate(schoolId, schoolYear, false, modifyTime, gardeId, true);
                List<Grade> updated = gradeService.findIncrementByModifyDate(schoolId, schoolYear, false, modifyTime, gardeId, false);
                List<Grade> deleted = gradeService.findIncrementByModifyDate(schoolId, schoolYear, true, modifyTime, gardeId, false);
                addGradeInfo(list, added, times, ids, "added");
                addGradeInfo(list, updated, times, ids, "updated");
                addGradeInfo(list, deleted, times, ids, "deleted");
                sortList(list);

            } else {
                List<Grade> gradeList = gradeService.findIncrementByModifyDate(schoolId, schoolYear, false, null,null, null);
                addGradeInfo(list, gradeList, times, ids, "added");
            }
            version_back = getVersion(times, ids, version, gardeId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }

    private void addGradeInfo(List<Object> list, List<Grade> gradeList, List<Date> times, List<Integer> ids, String status) {
        if (gradeList != null && gradeList.size() > 0) {
            for (Grade grade : gradeList) {
                Map<String, Object> map = new HashMap<>();
                map.put("gradeId", grade.getId());
                map.put("gradeName", grade.getName());
                map.put("stageCode", grade.getStageCode());
                map.put("gbGradeCode", grade.getUniGradeCode());
                map.put("gradeCode", getGradeCode(grade.getSchoolYear(), grade.getUniGradeCode()));
                map.put("schoolYear", grade.getSchoolYear());
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(grade.getModifyDate()));
                list.add(map);

                times.add(grade.getModifyDate());
            }
            if ("all".equals(status) || "added".equals(status)) {
                for (Grade grade : gradeList) {
                    ids.add(grade.getId());
                }
            }
        }
    }

    //年级的校内唯一码
    private String getGradeCode(String schoolYear, String uniGradeCode){
        String gradeCode = "";
        int year = Integer.parseInt(schoolYear);
        int code = Integer.parseInt(uniGradeCode);
        if (code >= 21 && code <=26) {
            gradeCode = "X" + String.valueOf(year - (code - 21)).substring(2);
        } else if (code >= 31 && code <= 33){
            gradeCode = "C" + String.valueOf(year - (code - 31)).substring(2);
        } else if (code >= 41 && code <= 43){
            gradeCode = "G" + String.valueOf(year - (code - 41)).substring(2);
        }
        return gradeCode;
    }

    @Override
    public Object getTeamList(String sign, String appKey, String timeStamp, String version, Integer schoolId) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimeStamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;

            Date modifyTime = null;
            Integer teamId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                teamId = (Integer) objects[1];
                isTimeStamp = (Boolean) objects[2];
            } else {
                return object;
            }

            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }

            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
//            if (version != null && !"".equals(version) && modifyTime != null) {
//                List<TeamVo> added = teamService.findIncrementByModifyDate(schoolId, schoolYear, false, modifyTime, teamId, true, true);
//                List<TeamVo> updated = teamService.findIncrementByModifyDate(schoolId, schoolYear, false, modifyTime, teamId, false, true);
//                List<TeamVo> deleted = teamService.findIncrementByModifyDate(schoolId, schoolYear, true, modifyTime, teamId, false, true);
//                addTeamInfo(list, added, times, ids, "added");
//                addTeamInfo(list, updated, times, ids, "updated");
//                addTeamInfo(list, deleted, times, ids, "deleted");
//            } else {
//                List<TeamVo> teamVoList = teamService.findIncrementByModifyDate(schoolId, schoolYear, false, null, null, null, null);
//                addTeamInfo(list, teamVoList, times, ids, "added");
//            }
            List<TeamVo> teamVoList = teamService.findIncrementByModifyDate(schoolId, schoolYear, false, null, null, null, null);
            addTeamInfo(list, teamVoList, times, ids, "added");
            //班级做全量返回，但仍需根据修改时间返回修改时间内有删除的记录
            if (version != null && !"".equals(version) && modifyTime != null) {
                List<TeamVo> deleted = teamService.findIncrementByModifyDate(schoolId, schoolYear, true, modifyTime, teamId, false, true);
                addTeamInfo(list, deleted, times, ids, "deleted");
                if (deleted != null && deleted.size() > 0) {
                    sortList(list);
                }
            }
            version_back = getVersion(times, ids, version, teamId, isTimeStamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }

    private void addTeamInfo(List<Object> list, List<TeamVo> teamVoList, List<Date> times, List<Integer> ids, String status) {
        if (teamVoList != null && teamVoList.size() > 0) {
            for (TeamVo team : teamVoList) {
                Map<String, Object> map = new HashMap<>();
                map.put("teamId", team.getId());
                map.put("teamName", team.getName());
                map.put("teamCode", team.getCode2());
                map.put("gradeId", team.getGradeId());
                map.put("gradeCode", getGradeCode(team.getSchoolYear(), team.getUniGradeCode()));
                map.put("gbGradeCode", team.getUniGradeCode());
                map.put("schoolYear", team.getSchoolYear());
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(team.getModifyDate()));
                list.add(map);

                times.add(team.getModifyDate());
            }
            if ("all".equals(status) || "added".equals(status)) {
                for (TeamVo vo : teamVoList) {
                    ids.add(vo.getId());
                }
            }
        }
    }

    @Override
    public Object getDepartmentList(String sign, String appKey, String timeStamp, String version, Integer schoolId) {
//        Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimestamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;

            Date modifyTime = null;
            Integer departmentId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                departmentId = (Integer) objects[1];
                isTimestamp = (Boolean) objects[2];
            } else {
                return object;
            }

            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            if (version != null && !"".equals(version) && modifyTime != null) {
                List<Department> added = departmentService.findIncrementByModifyDate(schoolId, false, modifyTime, departmentId, true);
                List<Department> updated = departmentService.findIncrementByModifyDate(schoolId, false, modifyTime, departmentId, false);
                List<Department> deleted = departmentService.findIncrementByModifyDate(schoolId, true, modifyTime, departmentId, false);
                addDepartmentInfo(list, added, times, ids, "added");
                addDepartmentInfo(list, updated, times, ids, "updated");
                addDepartmentInfo(list, deleted, times, ids, "deleted");
                sortList(list);

            } else {
                List<Department> departmentList = departmentService.findIncrementByModifyDate(schoolId, false, null, null, null);
                addDepartmentInfo(list, departmentList, times, ids, "added");
            }
            version_back = getVersion(times, ids, version, departmentId, isTimestamp);

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
    }

    private void addDepartmentInfo(List<Object> list, List<Department> departmentList, List<Date> times, List<Integer> ids, String status) {
        if (departmentList != null && departmentList.size() > 0) {
            for (Department department : departmentList) {
                Map<String, Object> map = new HashMap<>();
                map.put("departmentId", department.getId());
                map.put("departmentName", department.getName());
                map.put("listOrder", department.getListOrder());
                map.put("parentId", department.getParentId());
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(department.getModifyDate()));
                list.add(map);
            }
            times.add(departmentList.get(departmentList.size()-1).getModifyDate());
            if ("all".equals(status) || "added".equals(status)) {
                for (Department department : departmentList) {
                    ids.add(department.getId());
                }
            }
        }
    }

    private String getStatus(String status){
        if ("added".equals(status) || "updated".equals(status)) {
            status = "addedOrUpdated";
        }
        return status;
    }

	@Override
	public Object getGraduationTeamList(String sign, String appKey, String timeStamp, Integer schoolId) {
//		Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        List<Object> list = null;
        try {
            list = new ArrayList<>();
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;

            String schoolYear = "";
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            Object o2 = judgeSchoolTermCurrent(current);
            if (o2 != null) {
                return o2;
            } else {
                schoolYear = current.getSchoolYear();
            }

            //判断是否有上一学年，若无，直接返回空
            String lastYear = String.valueOf(Integer.parseInt(schoolYear) - 1);
            SchoolYear lastSchoolYear = schoolYearService.findByYearAndSchoolId(schoolId, lastYear);
            if (lastSchoolYear == null) {
                new ResponseVo<Object>("0", list);
            }

            //map键：stageCode 值：毕业学年
            Map<String, String> stageYear = getStageCodeAndGaduateYear(schoolId, schoolYear);
            for (Map.Entry<String, String> entry : stageYear.entrySet()) {
                Map<String, Object> map = new HashMap<>();

                String graduate = entry.getValue();
                String gbG = entry.getKey();
                String gradCode = selectGradeCode(gbG, graduate);     //输出
                String gbGradeCode = getgbGradeCode(gbG);     //输出

                GradeCondition gc = new GradeCondition();
                gc.setSchoolId(schoolId);
                gc.setSchoolYear(lastYear);
                gc.setUniGradeCode(gbGradeCode);
                List<Grade> findByCondition = gradeService.findGradeByCondition(gc,null,null);
                if(findByCondition == null || findByCondition.size() == 0){
                    continue;
                }
                Grade grade = findByCondition.get(0);
                Integer gradeId = null;  // 输出
                if(grade != null){
                    gradeId = grade.getId();
                }
                map.put("gradeId", gradeId);
                map.put("gradeCode", gradCode);
                map.put("gbGradeCode", gbGradeCode);
                map.put("schoolYear", schoolYear);
                List<Object> teamList = new ArrayList<>();
                List<Team> list2 = teamService.findTeamOfGradeAndSchool(gradeId,schoolId);
                for (Team team : list2) {
                    Map<String, Object> teamMap = new HashMap<>();
                    teamMap.put("teamId", team.getId());
                    teamMap.put("teamCode", team.getCode2());
                    teamList.add(teamMap);
                }
                map.put("teamList", teamList);
                list.add(map);
            }

        }catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseVo<Object>("0", list);
    }

    private String getgbGradeCode(String gbG) {
        String gbGradeCode = null;
        if("2".equals(gbG)){
            gbGradeCode = gbG + "6";
        }else if ("3".equals(gbG) || "4".equals(gbG)) {
            gbGradeCode = gbG + "3";
        }
        return gbGradeCode;
    }

    private String selectGradeCode(String gbG,String graduate) {
        String gradCode = null;
        if("2".equals(gbG)){
            gradCode = "X" + graduate.substring(2);
        }else if ("3".equals(gbG)) {
            gradCode = "C" + graduate.substring(2);
        }else if ("4".equals(gbG)) {
            gradCode = "G" + graduate.substring(2);
        }
        return gradCode;
    }

	private Map<String, String> getStageCodeAndGaduateYear(Integer schoolId, String schoolYear) {
		List<Grade> list = gradeService.findGrageListBySchoolId(schoolId);
		Map<String, String> map = new HashMap<>();
		for (Grade grade : list) {
			map.put(grade.getStageCode(), null);
		}
		for (Map.Entry<String, String> gradeMap : map.entrySet()) {
			String stage = gradeMap.getKey();
			if(stage != null && !"".equals(stage)){
				if(stage.equals("2")){
					map.put(gradeMap.getKey(), String.valueOf(Integer.parseInt(schoolYear) - 6));
				}else if(stage.equals("3") || stage.equals("4")){
					map.put(gradeMap.getKey(), String.valueOf(Integer.parseInt(schoolYear) - 3));
				}
			}else {
				return null;
			}
		}
		return map;
	}

	@Override
	public Object getSubjectList(String sign, String appKey, String timeStamp, String version, Integer schoolId,
			String subjectClass) {
//		Object verify = verifySign(sign, appKey, timeStamp);
//        if (verify != null) {
//            return verify;
//        }
        Boolean isTimestamp = true;
        List<Object> list = null;
        String version_back = "";
        try {
            list = new ArrayList<>();
            Object o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;
            
            Object o2 = judgeNull(subjectClass, "subjectClass", "课程级别");
            if (o2 != null) return o2;

            Date modifyTime = null;
            Integer subjectId = null;
            Object object = getVersionMsg(version);
            if (object instanceof Object[]) {
                Object[] objects = (Object[]) object;
                modifyTime = (Date) objects[0];
                subjectId = (Integer) objects[1];
                isTimestamp = (Boolean) objects[2];
            } else {
                return object;
            }
            
            List<Date> times = new ArrayList<>();
            List<Integer> ids = new ArrayList<>();
            int [] i = {1};
            String[] subjectClassArr = subjectClass.split(",");
            for (String string : subjectClassArr) {
            	if (version != null && !"".equals(version) && modifyTime != null) {
            		List<SubjectVo> added = subjectService.findIncrementDataByModifyDate(schoolId, string,false, modifyTime, subjectId, true);
            		List<SubjectVo> updated = subjectService.findIncrementDataByModifyDate(schoolId, string,false, modifyTime, subjectId, false);
            		List<SubjectVo> deleted = subjectService.findIncrementDataByModifyDate(schoolId,  string,true, modifyTime, subjectId, false);
            		addSubjectInfo(list, added, times, ids, "added",i);
            		addSubjectInfo(list, updated, times, ids, "updated",i);
            		addSubjectInfo(list, deleted, times, ids, "deleted",i);
            		sortList(list);
            		
            	} else {
            		List<SubjectVo> subjectVoList = subjectService.findIncrementDataByModifyDate(schoolId, string, false, null, null, null);
            		addSubjectInfo(list, subjectVoList, times, ids, "added",i);
            	}
            }
            
            version_back = getVersion(times, ids, version, subjectId, isTimestamp);
            
        }catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseDto<Object>("0",version_back, list);
	}

	private void addSubjectInfo(List<Object> list, List<SubjectVo> subjectVoList, List<Date> times, List<Integer> ids,String status,int [] i) {
		if (subjectVoList != null && subjectVoList.size() > 0) {
			for (SubjectVo subject : subjectVoList) {
                Map<String, Object> map = new HashMap<>();
                map.put("subjectId", subject.getId());
                map.put("subjectName", subject.getName());
                map.put("subjectCode", subject.getCode());
                map.put("subjectClass", subject.getSubjectClass());
                map.put("listOrder", subject.getListOrder());
                map.put("stageCode", subject.getStageCode());
                map.put("dataStatus", getStatus(status));
                map.put("modifiedTime", sdf.format(subject.getModifyDate()));
                
                list.add(map);
            }
            times.add(subjectVoList.get(subjectVoList.size()-1).getModifyDate());
            if ("all".equals(status) || "added".equals(status)) {
                for (SubjectVo vo : subjectVoList) {
                    ids.add(vo.getId());
                }
            }
        }
	}

    @Override
    public Object getYearAndTermList(String version, Integer schoolId) {
//        Boolean isTimestamp = true;
//        List<Object> list = null;
//        String version_back = "";

        List<Object> yearList = new ArrayList<>();
        Object o1 = null;
        try {
            o1 = judgeNull(schoolId, "schoolId", "学校id");
            if (o1 != null) return o1;

//            Date modifyTime = null;
//            Integer subjectId = null;
//            Object object = getVersionMsg(version);
//            if (object instanceof Object[]) {
//                Object[] objects = (Object[]) object;
//                modifyTime = (Date) objects[0];
//                subjectId = (Integer) objects[1];
//                isTimestamp = (Boolean) objects[2];
//            } else {
//                return object;
//            }

            List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
            SchoolTermCurrent current = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            List<SchoolTerm> schoolTermList = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Object> termList = null;
            Map<String, Object> yearMap = null;
            Map<String, Object> termMap = null;
            if (schoolYearList != null && schoolYearList.size() > 0) {
                for (SchoolYear schoolYear : schoolYearList) {

                    termList = new ArrayList<>();
                    schoolTermList = schoolTermService.getSchoolTermOfYear(schoolId, schoolYear.getId());
                    if (schoolTermList != null && schoolTermList.size() > 0) {
                        Collections.reverse(schoolTermList);
                        for (SchoolTerm schoolTerm : schoolTermList) {
                            termMap = new HashMap<>();
                            if (current != null && current.getSchoolTermCode().equals(schoolTerm.getCode())) {
                                termMap.put("isCurrent", true);
                            } else {
                                termMap.put("isCurrent", false);
                            }
                            termMap.put("name", schoolTerm.getName());
                            termMap.put("termCode", schoolTerm.getCode());
                            termMap.put("beginDate", sdf.format(schoolTerm.getBeginDate()));
                            termMap.put("finishDate", sdf.format(schoolTerm.getFinishDate()));
                            termList.add(termMap);
                        }
                    }

                    yearMap = new HashMap<>();
                    yearMap.put("termList", termList);
                    yearMap.put("name", schoolYear.getName());
                    yearMap.put("year", schoolYear.getYear());
                    yearMap.put("beginDate", sdf.format(schoolYear.getBeginDate()));
                    yearMap.put("finishDate", sdf.format(schoolYear.getFinishDate()));
                    yearList.add(yearMap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return interfacesAbnormal();
        }
        return new ResponseVo<Object>("0", yearList);
    }
}
