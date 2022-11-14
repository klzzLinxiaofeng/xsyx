package platform.szxyzxx.web.schoolaffair.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.*;

@Controller
@RequestMapping("/schoolaffair/schoolDataStatistics")
public class SchoolDataStatisticsController extends BaseController {

    /**
     * 去到统计页面
     */
    private final static String viewBasePath = "/schoolaffair/schoolDataStatistics";

    /**
     * 教师统计 页面
     *
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/toTeacherGraphics_new", method = RequestMethod.GET)
    public ModelAndView teacherGraphicsIndex(Model model, @CurrentUser UserInfo user) {
        List<Subject> list = this.subjectService.findSubjectsOfSchool(user.getSchoolId());
        model.addAttribute("schoolName", user.getSchoolName());
        model.addAttribute("subjectList", list);
        model.addAttribute("teacherNum", teacherService.findTeacherNumBySchoolId(user.getSchoolId()));
        return new ModelAndView(structurePath("/teacherGraphics_new"), model.asMap());
    }

    /**
     * （目前弃用）
     *
     * @param model
     * @param user
     * @return
     */
//    @RequestMapping(value = "/toTeacherGraphics", method = RequestMethod.GET)
//    public ModelAndView index(Model model, @CurrentUser UserInfo user) {
//        List<Subject> list = this.subjectService.findSubjectsOfSchool(user.getSchoolId());
//        model.addAttribute("schoolName", user.getSchoolName());
//        model.addAttribute("subjectList", list);
//        return new ModelAndView(structurePath("/teacherGraphics"), model.asMap());
//    }

    /**
     * 获取性别统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherSexData")
    @ResponseBody
    public Map getTeacherSexData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        TeacherVo t = teacherService.findTeamTeacherNumberOfSexData(user.getSchoolId());
        String[] man = {"男 (" + t.getManNum() + "人)", t.getManNum()};
        String[] wom = {"女 (" + t.getWomanNum() + "人)", t.getWomanNum()};
        String[] noS = {"未标识 (" + t.getSumNum() + "人)", t.getSumNum()};
        map.put("man", man);
        map.put("wom", wom);
        map.put("noS", noS);
        return map;
    }

    /**
     * 获取党员、性别统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherDYData")
    @ResponseBody
    public Map getTeacherDYData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        TeacherVo t = teacherService.findTeamTeacherNumberOfPoliticalStatusData(user.getSchoolId(), "01");
        String[] man = {"男 (" + t.getManNum() + "人)", t.getManNum()};
        String[] wom = {"女 (" + t.getWomanNum() + "人)", t.getWomanNum()};
        String[] noS = {"未标识 (" + t.getSumNum() + "人)", t.getSumNum()};
        map.put("man", man);
        map.put("wom", wom);
        map.put("noS", noS);
        return map;
    }

    /**
     * 获取岗位、性别统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherPostsData")
    @ResponseBody
    public Map getTeacherPostsData(@CurrentUser UserInfo user, @RequestParam(value = "postStaffing", required = true) String postStaffing) {
        Map map = new HashMap();
        TeacherVo t = teacherService.findTeamTeacherNumberOfPoststaffingData(user.getSchoolId(), postStaffing);
        String[] man = {"男 (" + t.getManNum() + "人)", t.getManNum()};
        String[] wom = {"女 (" + t.getWomanNum() + "人)", t.getWomanNum()};
        String[] noS = {"未标识 (" + t.getSumNum() + "人)", t.getSumNum()};
        map.put("man", man);
        map.put("wom", wom);
        map.put("noS", noS);
        return map;
    }

    /**
     * 获取团员、性别统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherTYData")
    @ResponseBody
    public Map getTeacherTYData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        TeacherVo t = teacherService.findTeamTeacherNumberOfPoliticalStatusData(user.getSchoolId(), "03");
        String[] man = {"男 (" + t.getManNum() + "人)", t.getManNum()};
        String[] wom = {"女 (" + t.getWomanNum() + "人)", t.getWomanNum()};
        String[] noS = {"未标识 (" + t.getSumNum() + "人)", t.getSumNum()};
        map.put("man", man);
        map.put("wom", wom);
        map.put("noS", noS);
        return map;
    }

    /**
     * 获取团员、性别统计数据
     */
    @RequestMapping(value = "/getTeacherXLData")
    @ResponseBody
    public List<Map<String, Object>> getTeacherXLData(@CurrentUser UserInfo user) {
        List<TeacherVo> list = teacherService.findTeacherNumberOfQualificationData(user.getSchoolId(), "GB-XL");
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        if (list.size() > 0) {
            for (TeacherVo vo : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                String[] arr = {vo.getGBXLName(), vo.getGBXLNumber()};
                map.put("GBXL", arr);
                maps.add(map);
            }
        }
        return maps;
    }

    /**
     * 获取民族统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherRaceData")
    @ResponseBody
    public Map getTeacherRaceData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        PersonVo p = this.personService.findPersonNumberOfRaceData(user.getSchoolId());
        int notN = p.getNotNational() - p.getUnNationalMinority() - p.getNationalMinority();
        String[] nationalMinority = {"少数民族 (" + p.getNationalMinority() + "人)", p.getNationalMinority().toString()};
        String[] unNationalMinority = {"非少数民族 (" + p.getUnNationalMinority() + "人)", p.getUnNationalMinority().toString()};
        String[] notNational = {"未标识 (" + notN + "人)", notN + ""};
        map.put("nationalMinority", nationalMinority);
        map.put("unNationalMinority", unNationalMinority);
        map.put("notNational", notNational);
        return map;
    }

    /**
     * 获取政治面貌统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherPoliticsData")
    @ResponseBody
    public Map getTeacherPoliticsData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        TeacherVo t = this.teacherService.findTeacherNumberOfPoliticsData(user.getSchoolId());
        int other = Integer.valueOf(t.getOther()) - Integer.valueOf(t.getPatry()) - Integer.valueOf(t.getMember());
        String[] Patry = {"党员 (" + t.getPatry() + "人)", t.getPatry()};
        String[] Member = {"团员(" + t.getMember() + "人)", t.getMember()};
        String[] Other = {"其他 (" + other + "人)", other + ""};
        map.put("Patry", Patry);
        map.put("Member", Member);
        map.put("Other", Other);
        return map;
    }

    /**
     * 获取年龄段统计数据
     * （第一阶段：firstStage 20-30;第二阶段：secondStage 31-40;第三阶段：theThirdStage >50,其他（表示没有数据等））
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherAgeData")
    @ResponseBody
    public Map getTeacherAgeData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        List<TeacherVo> tList = this.teacherService.findTeacherNumberOfAgeData(user.getSchoolId());
        for (TeacherVo vo : tList) {
            if (vo.getAgeGroup() == null || "".equals(vo.getAgeGroup())) {
                String[] other = {"其他 (" + vo.getAgeNumber() + "人)", vo.getAgeNumber()};
                map.put("other", other);
            }
            if (vo.getAgeGroup() != null && vo.getAgeGroup().equals("firstStage")) {
                String[] firstStage = {"20 ~ 30 (" + vo.getAgeNumber() + "人)", vo.getAgeNumber()};
                map.put("firstStage", firstStage);
            }
            if (vo.getAgeGroup() != null && vo.getAgeGroup().equals("secondStage")) {
                String[] secondStage = {"31 ~ 50 (" + vo.getAgeNumber() + "人)", vo.getAgeNumber()};
                map.put("secondStage", secondStage);
            }
            if (vo.getAgeGroup() != null && vo.getAgeGroup().equals("theThirdStage")) {
                String[] theThirdStage = {"大于 50 (" + vo.getAgeNumber() + "人)", vo.getAgeNumber()};
                map.put("theThirdStage", theThirdStage);
            }
        }
        return map;
    }

    /**
     * 获取任课教师统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherSubjectData")
    @ResponseBody
    public Map getTeacherSubjectData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        List<TeamTeacherVo> tList = this.teamTeacherService.findTeacherNumberOfSubjectBySchoolId(user.getSchoolId());
        for (TeamTeacherVo t : tList) {
            map.put(t.getName() + "(" + t.getTeacherNumberOfSubject() + "人)", t.getTeacherNumberOfSubject());
        }
        return map;
    }

    /**
     * 获取任课教师统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherSubjectDataNew")
    @ResponseBody
    public Map getTeacherSubjectDataNew(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        List<TeamTeacherVo> tList = this.teamTeacherService.findTeacherNumberOfSubjectBySchoolId(user.getSchoolId());
        for (TeamTeacherVo t : tList) {
            map.put(t.getName(), t.getTeacherNumberOfSubject());
        }
        return map;
    }

    /**
     * 获取部门统计数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/getTeacherDepartmentData")
    @ResponseBody
    public Map getTeacherDepartmentData(@CurrentUser UserInfo user) {
        Map map = new HashMap();
        List<Department> tList = this.departmentService.finDepartmentPeopleCountBySchoolId(user.getSchoolId());
        for (Department t : tList) {
            map.put(t.getName() + "(" + t.getMemberCount() + "人)", t.getMemberCount());
        }
        return map;
    }

    /**
     * 获取部门统计数据
     */
    @RequestMapping(value = "/getTeacherDepartmentDataNew")
    @ResponseBody
    public Map<String, String[]> getTeacherDepartmentDataNew(@CurrentUser UserInfo user) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        List<TeacherVo> tList = this.departmentService.findDepartmentPeopleCountBySchoolIdAndSex(user.getSchoolId());
        if (tList.size() > 0) {
            for (TeacherVo t : tList) {
                String[] data = {t.getManNum(), t.getWomanNum()};
                map.put(t.getDepartmentName(), data);
            }
        }
        return map;
    }

    /**
     * 获取任课教师统计数据
     *
     * @param subPath
     * @return
     */
    @RequestMapping(value = "/getteamTeacherNumber")
    @ResponseBody
    public List<GradeVo> getteamTeacherNumber(@CurrentUser UserInfo user) {
        List<GradeVo> gVoList = new ArrayList<GradeVo>();
        GradeVo gVo = null;
        List<Grade> gList = getGrade(user.getSchoolId(), user.getSchoolYear());
        for (int i = 0; i < gList.size(); i++) {
            gVo = new GradeVo();
            //年级名称
            gVo.setName(gList.get(i).getName());
            //班级数量
            gVo.setClassNumber(getGradeOfClass(gList.get(i).getId(), user.getSchoolId()).size());
            //任课教师数量
            gVo.setTeacherNumber(getTeacherOfClass(user.getSchoolId(), gList.get(i).getId()).size());
            //班主任数量
            gVo.setHeadTeacherNumber(getHeadTeacherOfClass(user.getSchoolId(), gList.get(i).getId()).size());
            //各科目教师数量
            gVo.setSubjectList(getTeachingASubject(gList.get(i).getId(), user.getSchoolId()));
            gVoList.add(gVo);
        }
        return gVoList;
    }

    //获取所有年级
    public List<Grade> getGrade(Integer schoolId, String SchoolYear) {
        GradeCondition gCon = new GradeCondition();
        gCon.setSchoolId(schoolId);
        gCon.setSchoolYear(SchoolYear);
        gCon.setDelete(false);
        return this.gradeService.findGradeByCondition(gCon, null, null);
    }

    //获取所有班级
    public List<Team> getGradeOfClass(Integer gradeId, Integer schoolId) {
        return this.teamService.findTeamOfGradeAndSchool(gradeId, schoolId);
    }

    //获取年级下的所有老师
    public List<TeamTeacher> getTeacherOfClass(Integer schoolId, Integer gradeId) {
        TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
        teamTeacherCondition.setSchoolId(schoolId);
        teamTeacherCondition.setGradeId(gradeId);
        teamTeacherCondition.setType(2);
        List<TeamTeacher> teaList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
        teaList = removeRep(teaList);
        return teaList;
    }

    //获取年级下的所有班主任
    public List<TeamTeacher> getHeadTeacherOfClass(Integer schoolId, Integer gradeId) {
        TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
        teamTeacherCondition.setSchoolId(schoolId);
        teamTeacherCondition.setGradeId(gradeId);
        teamTeacherCondition.setType(1);
        List<TeamTeacher> teaList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
        teaList = removeRep(teaList);
        return teaList;
    }

    //年级下教某科目的老师数量
    public List<SubjectVo> getTeachingASubject(Integer gradeId, Integer schoolId) {
        List<SubjectVo> subjectList = new ArrayList<SubjectVo>();
        TeamTeacherCondition teamTeacherCondition = null;
        SubjectVo sub = null;
        List<Subject> sList = getSubjectBySchoolId(schoolId);
        for (Subject s : sList) {
            sub = new SubjectVo();
            teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setSchoolId(schoolId);
            teamTeacherCondition.setGradeId(gradeId);
            teamTeacherCondition.setSubjectCode(s.getCode());
            List<TeamTeacher> teaList = teamTeacherService.findTeamTeacherByCondition(teamTeacherCondition, null, null);
            teaList = removeRep(teaList);
            sub.setName(s.getName());
            sub.setTeacherNumber(teaList.size());
            subjectList.add(sub);
        }
        return subjectList;
    }

    //获取学校的科目
    public List<Subject> getSubjectBySchoolId(Integer schoolId) {
        return this.subjectService.findSubjectsOfSchool(schoolId);
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    /*==================学生统计部分开始==================*/

    /**
     * 去到学生统计页面
     */
    @RequestMapping(value = "/toStudentGraphics", method = RequestMethod.GET)
    public ModelAndView studentIndex(@CurrentUser UserInfo user, Model model) {
        model.addAttribute("schoolName", user.getSchoolName());
        return new ModelAndView(structurePath("/studentGraphics"), model.asMap());
    }

    /**
     * 去到学生统计页面 (NEW)
     */
    @RequestMapping(value = "/toStudentGraphics_new", method = RequestMethod.GET)
    public ModelAndView studentIndexNEW(@CurrentUser UserInfo user, Model model) {
//		StudentCondition condition = new StudentCondition();
//		condition.setSchoolId(user.getSchoolId());
//		Long count = studentService.count(condition);
        int count = 0;
        List<StatisticDate> list = studentService.findNumberOfGradeBySchoolId(user.getSchoolId(), user.getSchoolYear());
        for (StatisticDate date : list) {
            count += date.getNumber();
        }
        model.addAttribute("schoolName", user.getSchoolName());
        model.addAttribute("count", count);
        return new ModelAndView(structurePath("/studentGraphics_new"), model.asMap());
    }

    @RequestMapping(value = "getStudentNumberByAge")
    @ResponseBody
    public Map<String, Integer> getStudentNumberByAge(@CurrentUser UserInfo user) {
        List<Integer> list = this.personService.findPersonAgeGroupOfStudentBySchool(user.getSchoolId());
        StudentCondition condition = new StudentCondition();
        condition.setSchoolId(user.getSchoolId());
        List<Student> students = studentService.findStudentByCondition(condition, null, null);
        Map<String, Integer> result = studentGroupFilter(students, list);
        return result;
    }

    private Map<String, Integer> studentGroupFilter(List<Student> students, List<Integer> groups) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        if (students != null && students.size() > 0 && groups != null && groups.size() > 0) {
            for (Student stu : students) {
                Person person = personService.findPersonById(stu.getPersonId());
                if (person != null) {
                    Date birthday = person.getBirthday();
                    if (birthday != null) {
                        Integer age = getAge(birthday);
                        if (age != null) {
                            result.put("minVal", groups.get(0));
                            result.put("groupNum", groups.size());
                            for (Integer group : groups) {
                                if (age >= group && age < group + 1) {
                                    if (groups.get(0) == group) {
                                        if (result.containsKey("min")) {
                                            result.put("min", result.get("min") + 1);
                                        } else {
                                            result.put("min", 1);
                                        }
                                    } else {
                                        if (result.containsKey(group + "")) {
                                            result.put(group + "", result.get(group + "") + 1);
                                        } else {
                                            result.put(group + "", 1);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 根据出生日期获得年龄
     *
     * @param birthDate
     * @return
     */
    @SuppressWarnings("deprecation")
    private static Integer getAge(Date birthDate) {
        Integer age = null;
        if (birthDate == null) {
            throw new RuntimeException("出生日期不能为null");
        } else {
            Date now = new Date();
//			SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
//			SimpleDateFormat format_M = new SimpleDateFormat("MM");
//			String birth_year = format_y.format(birthDate);
//			String this_year = format_y.format(now);
//			String birth_month = format_M.format(birthDate);
//			String this_month = format_M.format(now);
//			// 初步，估算
//			age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
//			// 如果未到出生月份，则age - 1
//			if (this_month.compareTo(birth_month) < 0)
//				age -= 1;
//			if (age < 0)
//				age = 0;
            if (now.after(birthDate)) {
                age = now.getYear() - birthDate.getYear();
                age = now.getMonth() - birthDate.getMonth() < 0 ? age - 1 : age;
            }
        }
        return age;
    }

    /**
     * 学生男女人数统计
     */
    @RequestMapping(value = "/getStudentNumberOfSexData")
    @ResponseBody
    public Map<String, String[]> getStudentNumberOfSexDate(@CurrentUser UserInfo user) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        //0--未知 1--男 2--女 9--未说明
        StudentCondition condition = new StudentCondition();
        condition.setSchoolId(user.getSchoolId());
        condition.setIsDelete(false);
        //获得性别为男的人数
        condition.setSex("1");
        Long manNum = this.studentService.count(condition);
        //获得性别为女的人数
        condition.setSex("2");
        Long womanNum = this.studentService.count(condition);
        //获得性别未说明的人数
        condition.setSex(null);
        Long totalNum = this.studentService.count(condition);
        Long untoldNum = totalNum - manNum - womanNum;

        String[] man = {"男 (" + manNum + "人)", String.valueOf(manNum)};
        String[] woman = {"女 (" + womanNum + "人)", String.valueOf(womanNum)};
        String[] untold = {"未标识 (" + untoldNum + "人)", String.valueOf(untoldNum)};

        map.put("man", man);
        map.put("woman", woman);
        map.put("untold", untold);
        return map;
    }

    /**
     * 学生民族人数统计
     */
    @RequestMapping(value = "/getStudentNumberOfRaceData")
    @ResponseBody
    public Map<String, String[]> getStudentNumberOfRaceData(@CurrentUser UserInfo user) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        //01--汉族 其他都为少数民族
        StudentVo vo = this.studentService.findStudentNumberOfRaceData(user.getSchoolId());
        Long unNationalMinority = Long.valueOf(vo.getUnNationalMinority());
        Long nationalMinority = Long.valueOf(vo.getNationalMinority());
        Long totalNational = Long.valueOf(vo.getTotalNational());
        Long otherNational = totalNational - unNationalMinority - nationalMinority;
        String[] unMinority = {"非少数民族 (" + vo.getUnNationalMinority() + "人)", vo.getUnNationalMinority()};
        String[] minority = {"少数民族 (" + vo.getNationalMinority() + "人)", vo.getNationalMinority()};
        String[] other = {"未标识 (" + otherNational + "人)", String.valueOf(otherNational)};

        map.put("unMinority", unMinority);
        map.put("minority", minority);
        map.put("other", other);

        return map;
    }

    /**
     * 学生政治面貌统计（团员、其他）
     */
    @RequestMapping(value = "/getStudentNumberOfPoliticsData")
    @ResponseBody
    public Map<String, String[]> getStudentNumberOfPoliticsData(@CurrentUser UserInfo user) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        //03--团员
        StudentCondition condition = new StudentCondition();
        condition.setSchoolId(user.getSchoolId());
        condition.setIsDelete(false);
        //总人数
        Long totalNum = this.studentService.count(condition);
        //团员数
        condition.setPoliticalStatus("03");
        Long memberNum = this.studentService.count(condition);
        Long otherNum = totalNum - memberNum;

        String[] member = {"团员 (" + memberNum + "人)", String.valueOf(memberNum)};
        String[] other = {"其他 (" + otherNum + "人)", String.valueOf(otherNum)};

        map.put("member", member);
        map.put("other", other);

        return map;
    }

    /**
     * 学生是否住宿统计（走读、住宿、未标明）
     */
    @RequestMapping(value = "/getStudentNumberOfIsBoardedData")
    @ResponseBody
    public Map<String, String[]> getStudentNumberOfIsBoardedData(@CurrentUser UserInfo user) {
        Map<String, String[]> map = new HashMap<String, String[]>();
        //1--住宿  0--不住宿
        StudentCondition condition = new StudentCondition();
        condition.setSchoolId(user.getSchoolId());
        condition.setIsDelete(false);
        //总人数
        Long totalNum = this.studentService.count(condition);
        //住读
        condition.setIsBoarded("1");
        Long boardedNum = this.studentService.count(condition);
        //走读
        condition.setIsBoarded("0");
        Long unBoardedNum = this.studentService.count(condition);
        //未标明
        Long otherNum = totalNum - boardedNum - unBoardedNum;

        String[] boarded = {"住读 (" + boardedNum + "人)", String.valueOf(boardedNum)};
        String[] unBoarded = {"走读 (" + unBoardedNum + "人)", String.valueOf(unBoardedNum)};
        String[] other = {"其他 (" + otherNum + "人)", String.valueOf(otherNum)};

        map.put("boarded", boarded);
        map.put("unBoarded", unBoarded);
        map.put("other", other);

        return map;
    }

    /**
     * 学生年级统计
     */
    @RequestMapping(value = "/getStudentNumberOfGradeData")
    @ResponseBody
    public List<GradeVo> getStudentNumberOfGradeData(@CurrentUser UserInfo user) {
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
        String schoolYear = "";
        if (schoolTermCurrent != null) {
            schoolYear = schoolTermCurrent.getSchoolYear();
        }
        GradeCondition gradeCondition = new GradeCondition();
        gradeCondition.setSchoolId(user.getSchoolId());
        gradeCondition.setSchoolYear(schoolYear);
        List<Grade> gradeList = this.gradeService.findGradeByCondition(gradeCondition, null, null);

        List<GradeVo> gradeVoList = new ArrayList<GradeVo>();
        if (gradeList.size() > 0) {
            List<Team> teamList = new ArrayList<Team>();
            TeamCondition teamCondition = new TeamCondition();
            teamCondition.setSchoolId(user.getSchoolId());
            teamCondition.setIsDelete(false);

            StudentCondition studentCondition = new StudentCondition();
            studentCondition.setSchoolId(user.getSchoolId());
            studentCondition.setDelete(false);

            for (Grade grade : gradeList) {
                GradeVo gradeVo = new GradeVo();
                BeanUtils.copyProperties(grade, gradeVo);
                teamCondition.setGradeId(grade.getId());
//				Long teamNumber = this.teamService.count(teamCondition);
                teamList = this.teamService.findTeamByCondition(teamCondition, null, null);
                gradeVo.setTeamNumber(Long.valueOf(teamList.size()));

                Long studentNumber = 0l;
                Long manNumber = 0l;
                Long womanNumber = 0l;
                Long untoldNumber = 0l;
                for (Team team : teamList) {
                    studentCondition.setTeamId(team.getId());
                    studentCondition.setSex(null);
                    //获得本班级学生总数
                    Long studentNum = this.studentService.count(studentCondition);
                    //班级学生总数相加得到年级总数
                    studentNumber = studentNumber + studentNum;
                    //获得性别为男的人数
                    studentCondition.setSex("1");
                    Long manNum = this.studentService.count(studentCondition);
                    manNumber = manNumber + manNum;
                    //获得性别为女的人数
                    studentCondition.setSex("2");
                    Long womanNum = this.studentService.count(studentCondition);
                    womanNumber = womanNumber + womanNum;
                    //未标明
                    Long untoldNum = studentNum - manNum - womanNum;
                    untoldNumber = untoldNumber + untoldNum;
                }
                gradeVo.setStudentNumber(studentNumber);
                gradeVo.setWomanNumber(womanNumber);
                gradeVo.setManNumber(manNumber);
                gradeVo.setUntoldNumber(untoldNumber);
                gradeVoList.add(gradeVo);
            }
        }
        return gradeVoList;
    }

    //去掉List<TeamTeacher>里面的重复值
    public List<TeamTeacher> removeRep(List<TeamTeacher> teamTList) {
        List<TeamTeacher> list = new ArrayList<TeamTeacher>();
        for (int i = 0; i < teamTList.size(); i++) {
            boolean flag = true;
            for (TeamTeacher t2 : list) {
                if (teamTList.get(i).getTeacherId().equals(t2.getTeacherId())) {
                    flag = false;
                }
            }
            if (flag) {
                list.add(teamTList.get(i));
            }
        }
        return teamTList;
    }


    //TODO
    //--------------------------教师统计-----------
    /*
     * 	page用来区分是学校还是教师页面，
     *  type用来区分教师和学生的相同类型的图表（人数\性别\年龄\政治面貌\民族\户口性质）
     */
    @RequestMapping(value = "/teacherGraphicsIndex", method = RequestMethod.GET)
    public ModelAndView tGraphicsIndex(Model model,
                                       @RequestParam(value = "sub", required = false) String sub,
                                       @CurrentUser UserInfo user) {
        String page = "";
        if ("area".equals(sub)) {
            page = "area";
        } else {
            page = "school";
        }
        model.addAttribute("page", page);
        model.addAttribute("type", "teacher");

        return new ModelAndView(structurePath("/hadoop/teacherGraphics"), model.asMap());
    }


    @RequestMapping(value = "/teacher/getNumberOfPeopleData")
    @ResponseBody
    public List<StatisticDate> getNumberOfTeacherData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("grade".equals(sub)) {
            list = teacherService.findNumberOfGradeBySchoolId(user.getSchoolId(), user.getSchoolYear());
        }
        if ("school".equals(sub)) {
            list = teacherService.findNumberOfSchoolByAreaCode(areaCode);
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getSalaryData")
    @ResponseBody
    public List<StatisticDate> getTeacherSalaryData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        Date date = new Date();
        if (year == null || "".equals(year)) {
            year = date.getYear() + 1900;
        }
        if (month == null || "".equals(month)) {
            month = date.getMonth() + 1;
        }
        if ("all".equals(sub)) {
            list = teacherService.findSalaryByAreaCode(areaCode, String.valueOf(year), String.valueOf(month));
        } else {
            list = teacherService.findSalaryBySchoolId(user.getSchoolId(), String.valueOf(year), String.valueOf(month));
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getPostStaffingData")
    @ResponseBody
    public List<StatisticDate> getTeacherPostStaffingData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findPostStaffingByAreaCode(areaCode);
        } else {
            list = teacherService.findPostStaffingBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getQualificationData")
    @ResponseBody
    public List<StatisticDate> getTeacherQualificationData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findQualificationByAreaCode(areaCode);
        } else {
            list = teacherService.findQualificationBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getIdCardTypeData")
    @ResponseBody
    public List<StatisticDate> getTeacherIdCardTypeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findIdCardTypeByAreaCode(areaCode);
        } else {
            list = teacherService.findIdCardTypeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getMarriageData")
    @ResponseBody
    public List<StatisticDate> getTeacherMarriageData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findMarriageByAreaCode(areaCode);
        } else {
            list = teacherService.findMarriageBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getJobStateData")
    @ResponseBody
    public List<StatisticDate> getTeacherJobStateData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findJobStateByAreaCode(areaCode);
        } else {
            list = teacherService.findJobStateBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getSexData")
    @ResponseBody
    public List<StatisticDate> getTeacherSexData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findSexByAreaCode(areaCode);
        } else {
            list = teacherService.findSexBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getAgeData")
    @ResponseBody
    public List<StatisticDate> getTeacherAgeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findAgeByAreaCode(areaCode);
        } else {
            list = teacherService.findAgeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getPoliticalStatusData")
    @ResponseBody
    public List<StatisticDate> getTeacherPoliticalStatusData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findPoliticalStatusByAreaCode(areaCode);
        } else {
            list = teacherService.findPoliticalStatusBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getRaceData")
    @ResponseBody
    public List<StatisticDate> getTeacherRaceData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findRaceByAreaCode(areaCode);
        } else {
            list = teacherService.findRaceBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/teacher/getResidenceTypeData")
    @ResponseBody
    public List<StatisticDate> getTeacherResidenceTypeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = teacherService.findResidenceTypeByAreaCode(areaCode);
        } else {
            list = teacherService.findResidenceTypeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    //--------------------------教师统计  end-----------

    //TODO
    //--------------------------学生统计-----------

    @RequestMapping(value = "/studentGraphicsIndex", method = RequestMethod.GET)
    public ModelAndView sGraphicsIndex(Model model,
                                       @RequestParam(value = "sub", required = false) String sub,
                                       @CurrentUser UserInfo user) {
        String page = "";
        if ("area".equals(sub)) {
            page = "area";
        } else {
            page = "school";
        }
        model.addAttribute("page", page);
        model.addAttribute("type", "student");

        return new ModelAndView(structurePath("/hadoop/studentGraphics"), model.asMap());
    }


    @RequestMapping(value = "/student/getNumberOfPeopleData")
    @ResponseBody
    public List<StatisticDate> getNumberOfStudentData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("grade".equals(sub)) {
            list = studentService.findNumberOfGradeBySchoolId(user.getSchoolId(), user.getSchoolYear());
        }
        if ("school".equals(sub)) {
            list = studentService.findNumberOfSchoolByAreaCode(areaCode);
        }
        return list;
    }

    @RequestMapping(value = "/student/getTeamNumberData")
    @ResponseBody
    public List<StatisticDate> getTeamNumberData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findTeamNumberByAreaCode(areaCode, schoolYear);
        } else {
            list = studentService.findTeamNumberBySchoolId(user.getSchoolId(), user.getSchoolYear());
        }
        return list;
    }

    @RequestMapping(value = "/student/getNativePlaceData")
    @ResponseBody
    public List<StatisticDate> getStuNativePlaceData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findNativePlaceByAreaCode(areaCode);
        } else {
            list = studentService.findNativePlaceBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getHealthStatusData")
    @ResponseBody
    public List<StatisticDate> getStuHealthStatusData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findHealthStatusByAreaCode(areaCode);
        } else {
            list = studentService.findHealthStatusBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getEnrollTypeData")
    @ResponseBody
    public List<StatisticDate> getStuEnrollTypeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findEnrollTypeByAreaCode(areaCode);
        } else {
            list = studentService.findEnrollTypeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getAttendSchoolTypeData")
    @ResponseBody
    public List<StatisticDate> getStuAttendSchoolTypeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findAttendSchoolTypeByAreaCode(areaCode);
        } else {
            list = studentService.findAttendSchoolTypeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getStudentSourceData")
    @ResponseBody
    public List<StatisticDate> getStudentSourceData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findStudentSourceByAreaCode(areaCode);
        } else {
            list = studentService.findStudentSourceBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getSexData")
    @ResponseBody
    public List<StatisticDate> getStuSexData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findSexByAreaCode(areaCode);
        } else {
            list = studentService.findSexBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getAgeData")
    @ResponseBody
    public List<StatisticDate> getStuAgeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findAgeByAreaCode(areaCode);
        } else {
            list = studentService.findAgeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getPoliticalStatusData")
    @ResponseBody
    public List<StatisticDate> getStuPoliticalStatusData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findPoliticalStatusByAreaCode(areaCode);
        } else {
            list = studentService.findPoliticalStatusBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getRaceData")
    @ResponseBody
    public List<StatisticDate> getStuRaceData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findRaceByAreaCode(areaCode);
        } else {
            list = studentService.findRaceBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/student/getResidenceTypeData")
    @ResponseBody
    public List<StatisticDate> getStuResidenceTypeData(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "areaCode", required = false) String areaCode,
            @CurrentUser UserInfo user) {
        List<StatisticDate> list = null;
        if ("all".equals(sub)) {
            list = studentService.findResidenceTypeByAreaCode(areaCode);
        } else {
            list = studentService.findResidenceTypeBySchoolId(user.getSchoolId());
        }
        return list;
    }

    @RequestMapping(value = "/enlargement", method = RequestMethod.GET)
    public ModelAndView getEnlargement(Model model,
                                       @RequestParam(value = "type", required = false) String type,
                                       @RequestParam(value = "page", required = false) String page,
                                       @RequestParam(value = "areaCode", required = false) String areaCode,
                                       @CurrentUser UserInfo user) {

        List<StatisticDate> list = null;
        if ("teacher".equals(type)) {
            if ("school".equals(page)) {
                list = teacherService.findNumberOfGradeBySchoolId(user.getSchoolId(), user.getSchoolYear());
            } else if ("area".equals(page)) {
                list = teacherService.findNumberOfSchoolByAreaCode(areaCode);
            }
        } else if ("student".equals(type)) {
            if ("school".equals(page)) {
                list = studentService.findNumberOfGradeBySchoolId(user.getSchoolId(), user.getSchoolYear());
            } else if ("area".equals(page)) {
                list = studentService.findNumberOfSchoolByAreaCode(areaCode);
            }
        }
        model.addAttribute("list", list);
        return new ModelAndView(structurePath("/hadoop/enlargement"), model.asMap());
    }


}
