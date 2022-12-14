package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentScore;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.vo.ExamScoreVo;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectVo;
import platform.education.generalTeachingAffair.vo.StudentScoreCondition;
import platform.education.generalTeachingAffair.vo.StudentScoreSort;
import platform.education.generalTeachingAffair.vo.StudentScoreVo;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.SubjectSort;
import platform.education.generalcode.model.Item;
import platform.education.generalcode.vo.ItemCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

@Controller
@RequestMapping("/teach/scoreSelect")
public class ScoreSelectController extends BaseController {

    private final static String viewBasePath = "/teach/scoreSelect/student";


    @RequestMapping(value = "/student/index")
    public ModelAndView studentIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StudentScoreCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        String viewPath = null;
        ModelAndView mav = new ModelAndView();


        page.setPageSize(Integer.MAX_VALUE);
        List<ExamScoreVo> examScoreVoList = new ArrayList<ExamScoreVo>();
        condition.setSchoolId(user.getSchoolId());
        List<SubjectSort> subjectSortList = getSortSubject(user, condition);//?????????????????????????????????

        if (condition.getSchoolYear() == null || condition.getGradeId() == null || condition.getTeamId() == null || condition.getStudentId() == null) {

        } else {

            ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
            examTeamSubjectCondition.setSchoolId(user.getSchoolId());
            examTeamSubjectCondition.setSchoolYear(condition.getSchoolYear());
            examTeamSubjectCondition.setTerm(condition.getTermCode());
            examTeamSubjectCondition.setTeamId(condition.getTeamId());
            examTeamSubjectCondition.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);
            examTeamSubjectCondition.setTaskRate(true);
            examTeamSubjectCondition.setExamType(condition.getExamType());
            page.setPageSize(Integer.MAX_VALUE);
            List<ExamTeamSubjectVo> examTeamSubjectVoList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(teamService, studentService, examTeamSubjectCondition, page, null);
            if (examTeamSubjectVoList == null) {
                examTeamSubjectVoList = new ArrayList<ExamTeamSubjectVo>();
            }
            Map<String, List<StudentScore>> examNameScoreList = new HashMap<String, List<StudentScore>>();
            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {

                if (!examTeamSubjectVo.getTaskRate()) {//??????????????????????????????????????????
                    continue;//???????????????????????????
                }

                StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
                studentScoreCondition.setStudentId(condition.getStudentId());
                studentScoreCondition.setExamTeamSubjectId(examTeamSubjectVo.getId());
                studentScoreCondition.setSchoolId(user.getSchoolId());
                //List<StudentScore> stuScoreList = getStudentScoreListFromCondition(studentScoreCondition);
                List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
                if (stuScoreList == null) {
                    stuScoreList = new ArrayList<StudentScore>();
                }
                String key = getMapKey(examTeamSubjectVo);
                List<StudentScore> list = examNameScoreList.get(key);
                if (stuScoreList != null && stuScoreList.size() > 0) {
                    if (list == null) {
                        examNameScoreList.put(key, stuScoreList);
                    } else {
                        for (StudentScore studentScore : list) {
                            stuScoreList.add(studentScore);
                        }
                        examNameScoreList.put(key, stuScoreList);
                    }
                } else {

                }

            }

            Map<String, Integer> contains = new HashMap<String, Integer>();
            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {//????????????????????????
                ExamScoreVo examScoreVo = new ExamScoreVo();
                String key = getMapKey(examTeamSubjectVo);
                Integer value = contains.get(key);
                int count = value == null ? 0 : value;
                if (count == 0) {
                    examScoreVo.setExamName(examTeamSubjectVo.getExamName());
                    examScoreVo.setExamType(examTeamSubjectVo.getExamType());
                    List<StudentScore> list = examNameScoreList.get(getMapKey(examTeamSubjectVo));
                    List<StudentScoreSort> studentScoreSortList = sortStudentScoreByOne(list);

                    examScoreVo.setStudentScoreSort(studentScoreSortList);
                    examScoreVoList.add(examScoreVo);
                    contains.put(key, 1);
                } else {


                    contains.put(key, contains.get(key) + 1);
                    continue;
                }
            }


        }

        for (ExamScoreVo examSoreVo : examScoreVoList) {//????????????????????????

            List<StudentScoreSort> studentScortList = examSoreVo.getStudentScoreSort();
            BigDecimal total = new BigDecimal(0);
            int stuCount = 0;
            for (StudentScoreSort studentScoreSort : studentScortList) {
                if (studentScoreSort.getScore() == null || "".equals(studentScoreSort.getScore().trim())) {
                    studentScoreSort.setScore("0");
                    studentScoreSort.setScoreZero(false);
                }
                total = total.add(new BigDecimal(studentScoreSort.getScore()));
                stuCount++;
                if (!studentScoreSort.isScoreZero()) {
                    studentScoreSort.setScore("");
                }
            }
            BigDecimal average = new BigDecimal(0);
            if (stuCount > 0) {
                average = total.divide(new BigDecimal(stuCount + ""), 2, BigDecimal.ROUND_HALF_EVEN);
            }
            examSoreVo.setStuAverage(average.toString());
            examSoreVo.setStuTotal(total.toString());

            String stuScoreTotal = examSoreVo.getStuTotal();//???????????????????????????????????????
            condition.setExamName(examSoreVo.getExamName());
            condition.setExamType(examSoreVo.getExamType());
            StudentScoreCondition studentScoreCondition = getGradeCondition(condition);//???????????????????????????????????????????????????
            studentScoreCondition.setSchoolId(user.getSchoolId());
            List<StudentScore> gradeStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);

            Map<Integer, String> stuIdScoreList = getPerStuTotalScoreMap(gradeStuScoreList);//?????????????????????????????????????????????????????????

            examSoreVo.setStuGradeRank(getRankInSortArray(stuScoreTotal, mapToSortArray(stuIdScoreList)) + "");
            studentScoreCondition.setTeamId(condition.getTeamId());
            List<StudentScore> teamStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
            Map<Integer, String> stuIdTeamScoreList = getPerStuTotalScoreMap(teamStuScoreList);//?????????????????????????????????????????????????????????

            examSoreVo.setStuTeamRank(getRankInSortArray(stuScoreTotal, mapToSortArray(stuIdTeamScoreList)) + "");

        }
        for (ExamScoreVo vo : examScoreVoList) {

            vo.setScoreNum(vo.getStudentScoreSort().size());
        }

        viewPath = "/teach/scoreSelect/student/index";
        mav.addObject("ScoreCondition", condition);
        mav.addObject("subjectList", subjectSortList);
        mav.addObject("examScoreVoList", examScoreVoList);
        mav.setViewName(viewPath);

        return mav;
    }


    @RequestMapping(value = "/student/downLoadExcel")
    public void studentDownLoadExcel(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @RequestParam(value = "termCode", required = true) String termCode,
            @RequestParam(value = "gradeId", required = true) Integer gradeId,
            @RequestParam(value = "teamId", required = true) Integer teamId,
            @RequestParam(value = "studentId", required = true) Integer studentId,
            @RequestParam(value = "examType", required = true) String examType,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            HttpServletResponse response,
            HttpServletRequest request) {

        StudentScoreCondition condition = new StudentScoreCondition();
        condition.setSchoolYear(schoolYear);
        condition.setTermCode(termCode);
        condition.setGradeId(gradeId);
        condition.setTeamId(teamId);
        condition.setStudentId(studentId);
        condition.setExamType(examType);
        condition.setSchoolId(user.getSchoolId());


        List<ExamScoreVo> examScoreVoList = new ArrayList<ExamScoreVo>();
        //????????????begin
        List<SubjectSort> subjectSortList = getSortSubject(user, condition);//?????????????????????????????????
        //????????????end

        if (condition.getSchoolYear() == null || condition.getGradeId() == null || condition.getTeamId() == null || condition.getStudentId() == null) {

        } else {

            ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
            examTeamSubjectCondition.setSchoolId(user.getSchoolId());
            examTeamSubjectCondition.setSchoolYear(condition.getSchoolYear());
            examTeamSubjectCondition.setTerm(condition.getTermCode());
            examTeamSubjectCondition.setGradeId(condition.getGradeId());
            examTeamSubjectCondition.setTeamId(condition.getTeamId());
            examTeamSubjectCondition.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);
            examTeamSubjectCondition.setTaskRate(true);
            examTeamSubjectCondition.setExamType(condition.getExamType());
            page.setPageSize(Integer.MAX_VALUE);
            List<ExamTeamSubjectVo> examTeamSubjectVoList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(teamService, studentService, examTeamSubjectCondition, page, null);
            if (examTeamSubjectVoList == null) {
                examTeamSubjectVoList = new ArrayList<ExamTeamSubjectVo>();
            }
            Map<String, List<StudentScore>> examNameScoreList = new HashMap<String, List<StudentScore>>();

            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {

                if (!examTeamSubjectVo.getTaskRate()) {//??????????????????????????????????????????
                    continue;//???????????????????????????
                }

                StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
                studentScoreCondition.setStudentId(condition.getStudentId());
                studentScoreCondition.setExamTeamSubjectId(examTeamSubjectVo.getId());
                studentScoreCondition.setSchoolId(user.getSchoolId());
                //List<StudentScore> stuScoreList = getStudentScoreListFromCondition(studentScoreCondition);
                List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
                if (stuScoreList == null) {
                    stuScoreList = new ArrayList<StudentScore>();
                }
                String key = getMapKey(examTeamSubjectVo);
                List<StudentScore> list = examNameScoreList.get(key);
                if (stuScoreList != null && stuScoreList.size() > 0) {
                    if (list == null) {
                        examNameScoreList.put(key, stuScoreList);
                    } else {
                        for (StudentScore studentScore : list) {
                            stuScoreList.add(studentScore);
                        }
                        examNameScoreList.put(key, stuScoreList);
                    }
                } else {

                }

            }

            Map<String, Integer> contains = new HashMap<String, Integer>();
            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {//????????????????????????
                ExamScoreVo examScoreVo = new ExamScoreVo();
                String key = getMapKey(examTeamSubjectVo);
                Integer value = contains.get(key);
                int count = value == null ? 0 : value;
                if (count == 0) {
                    examScoreVo.setExamName(examTeamSubjectVo.getExamName());
                    examScoreVo.setExamType(examTeamSubjectVo.getExamType());
                    List<StudentScore> list = examNameScoreList.get(getMapKey(examTeamSubjectVo));
                    List<StudentScoreSort> studentScoreSortList = sortStudentScoreByOne(list);

                    examScoreVo.setStudentScoreSort(studentScoreSortList);
                    examScoreVoList.add(examScoreVo);
                    contains.put(key, 1);
                } else {


                    contains.put(key, contains.get(key) + 1);
                    continue;
                }
            }


        }

        for (ExamScoreVo examSoreVo : examScoreVoList) {//????????????????????????

            List<StudentScoreSort> studentScortList = examSoreVo.getStudentScoreSort();
            BigDecimal total = new BigDecimal(0);
            int stuCount = 0;
            for (StudentScoreSort studentScoreSort : studentScortList) {
                if (studentScoreSort.getScore() == null || "".equals(studentScoreSort.getScore().trim())) {
                    studentScoreSort.setScore("0");
                    studentScoreSort.setScoreZero(false);
                }
                total = total.add(new BigDecimal(studentScoreSort.getScore()));
                stuCount++;
                if (!studentScoreSort.isScoreZero()) {
                    studentScoreSort.setScore("");
                }
            }
            BigDecimal average = new BigDecimal(0);
            if (stuCount > 0) {
                average = total.divide(new BigDecimal(stuCount + ""), 2, BigDecimal.ROUND_HALF_EVEN);
            }
            examSoreVo.setStuAverage(average.toString());
            examSoreVo.setStuTotal(total.toString());

            String stuScoreTotal = examSoreVo.getStuTotal();//???????????????????????????????????????
            condition.setExamName(examSoreVo.getExamName());
            condition.setExamType(examSoreVo.getExamType());
            StudentScoreCondition studentScoreCondition = getGradeCondition(condition);//???????????????????????????????????????????????????
            studentScoreCondition.setSchoolId(user.getSchoolId());
            List<StudentScore> gradeStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
            if (gradeStuScoreList == null) {
                gradeStuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, String> stuIdScoreList = getPerStuTotalScoreMap(gradeStuScoreList);//?????????????????????????????????????????????????????????

            examSoreVo.setStuGradeRank(getRankInSortArray(stuScoreTotal, mapToSortArray(stuIdScoreList)) + "");
            studentScoreCondition.setTeamId(condition.getTeamId());
            List<StudentScore> teamStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
            if (teamStuScoreList == null) {
                teamStuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, String> stuIdTeamScoreList = getPerStuTotalScoreMap(teamStuScoreList);//?????????????????????????????????????????????????????????

            examSoreVo.setStuTeamRank(getRankInSortArray(stuScoreTotal, mapToSortArray(stuIdTeamScoreList)) + "");

        }
        for (ExamScoreVo vo : examScoreVoList) {
            vo.setScoreNum(vo.getStudentScoreSort().size());
        }

        examScoreVoList = formatList(examScoreVoList, subjectSortList);

        ParseConfig config = SzxyExcelTookit.getConfig();
        List<Object> maps = new ArrayList<Object>();
        List<String> titleList = new ArrayList<String>();
        List<String> fieldList = new ArrayList<String>();
        for (SubjectSort subjectSort : subjectSortList) {
            titleList.add(subjectSort.getName());
            fieldList.add(subjectSort.getCode());
        }

        String[] titleFirst = {"????????????", "???????????? "};
        String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
        String[] titleThree = {"?????????", "??????", "????????????", "????????????"};


        String[] fieldFirst = {"examType", "examName"};
        String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
        String[] fieldThree = {"average", "total", "teamRank", "gradeRank"};

        String[] titles = merge(titleFirst, titleTwo, titleThree);
        String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
        config.setTitles(titles);
        config.setFieldNames(fieldNames);
        config.setSheetTitle("??????????????????");
        // ????????????	1
        //????????????	2
        //????????????	3

        Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
        Map<Object, Object> xxjbxxMaps = new HashMap<Object, Object>();
        xxjbxxMaps.put("1", "????????????");
        xxjbxxMaps.put("2", "????????????");
        xxjbxxMaps.put("3", "????????????");
        codeWithValueMaps.put("examType", xxjbxxMaps);
        config.setCodeWithValueMaps(codeWithValueMaps);

        for (ExamScoreVo vo : examScoreVoList) {

            Map<String, Object> map = new HashMap<String, Object>();
            if (vo.getExamType() != null && "1".equals(vo.getExamType())) {
                vo.setExamTypeName("????????????");
            } else if (vo.getExamType() != null && "2".equals(vo.getExamType())) {
                vo.setExamTypeName("????????????");
            } else if (vo.getExamType() != null && "3".equals(vo.getExamType())) {
                vo.setExamTypeName("????????????");
            }
            map.put("examType", vo.getExamTypeName());
            map.put("examName", vo.getExamName());

            List<StudentScoreSort> scoreList = vo.getStudentScoreSort();
            for (StudentScoreSort studentScoreSort : scoreList) {
                map.put(studentScoreSort.getSubjectCode(), studentScoreSort.getScore());
            }

            map.put("average", vo.getStuAverage());
            map.put("total", vo.getStuTotal());
            map.put("teamRank", vo.getStuTeamRank());
            map.put("gradeRank", vo.getStuGradeRank());

            maps.add(map);
        }

        String filename = "??????????????????.xls";
        try {
            SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * ????????????
     *
     * @param a
     * @param b
     * @return
     */
    private String[] merge(String[] a, String[] b, String[] c) {

        String[] mergeStr = null;
        int length = 0;
        if (a == null || b == null || c == null) {
            mergeStr = new String[0];
            return mergeStr;
        } else {
            mergeStr = new String[a.length + b.length + c.length];

            for (int i = 0; i < a.length; i++) {
                mergeStr[length++] = a[i];

            }

            for (int i = 0; i < b.length; i++) {
                mergeStr[length++] = b[i];

            }

            for (int i = 0; i < c.length; i++) {
                mergeStr[length++] = c[i];

            }
        }

        return mergeStr;
    }

    /**
     * ??????????????????list
     *
     * @param examScoreVoList
     * @param subjectSortList
     * @return
     */
    List<ExamScoreVo> formatList(List<ExamScoreVo> examScoreVoList, List<SubjectSort> subjectSortList) {

        for (ExamScoreVo examScoreVo : examScoreVoList) {
            List<StudentScoreSort> resultScoreList = new ArrayList<StudentScoreSort>();
            Map<String, StudentScoreSort> map = new HashMap<String, StudentScoreSort>();
            List<StudentScoreSort> midScoreList = examScoreVo.getStudentScoreSort();
            for (SubjectSort subjectSort : subjectSortList) {
                StudentScoreSort temp = new StudentScoreSort();
                temp.setSubjectCode(subjectSort.getCode());
                temp.setScore(null);
                temp.setStudentId(examScoreVo.getStudentId());
                map.put(subjectSort.getCode(), temp);
            }

            for (StudentScoreSort midScoreSort : midScoreList) {
                map.put(midScoreSort.getSubjectCode(), midScoreSort);
            }

            Set<String> set = map.keySet();

            for (String key : set) {
                resultScoreList.add(map.get(key));
            }
            Collections.sort(resultScoreList);
            examScoreVo.setStudentScoreSort(resultScoreList);
        }

        return examScoreVoList;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(
            @CurrentUser UserInfo user,
            @RequestParam(value = "studentId", required = true) Integer studentId,
            @ModelAttribute("condition") StudentScoreCondition condition,
            Model model) {
        String viewPath = null;
		/*try {
			if(condition!=null&&condition.getExamName()!=null&&!"".equals(condition.getExamName()) ){
				String examName=new String((condition.getExamName()).getBytes("iso-8859-1"),"utf-8");
				condition.setExamName(examName);
			}
			
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        List<ExamScoreVo> examScoreVoList = new ArrayList<ExamScoreVo>();
        condition.setSchoolId(user.getSchoolId());
        List<SubjectSort> subjectSortList = getSortSubject(user, condition);//?????????????????????????????????
        if (condition.getSchoolYear() == null || condition.getTermCode() == null) {

        } else {
            StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
            studentScoreCondition.setSchoolId(user.getSchoolId());
            studentScoreCondition.setStudentId(studentId);
            studentScoreCondition.setSchoolYear(condition.getSchoolYear());
            studentScoreCondition.setTermCode(condition.getTermCode());
            Page page = new Page();
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
//			List<StudentScore> scoreResultList = new ArrayList<StudentScore>();
            if (stuScoreList == null) {
                stuScoreList = new ArrayList<StudentScore>();
            }
            Map<String, List<StudentScoreSort>> mapSort = new HashMap<String, List<StudentScoreSort>>();
            if (stuScoreList.size() > 0) {
                for (StudentScore stuScore : stuScoreList) {
                    String key = stuScore.getExamName();
                    List<StudentScoreSort> list = mapSort.get(key);
                    if (list == null) {
                        list = new ArrayList<StudentScoreSort>();
                    }
                    StudentScoreSort sortTemp = new StudentScoreSort();
                    sortTemp.setSubjectCode(stuScore.getSubjectCode());
                    sortTemp.setScore(stuScore.getScore());
                    list.add(sortTemp);
                    mapSort.put(key, list);
                }

                Set<String> set = mapSort.keySet();
//				Map<String, StudentScoreSort> mapTotal = new HashMap<String, StudentScoreSort>();
                for (String key : set) {
                    List<StudentScoreSort> list = mapSort.get(key);
                    Collections.sort(list);
                    ExamScoreVo examScoreVo = new ExamScoreVo();
                    examScoreVo.setExamName(key);
                    examScoreVo.setStudentScoreSort(list);

                    BigDecimal total = new BigDecimal(0);
                    int stuCount = 0;
                    for (StudentScoreSort studentScoreSort : list) {
                        if (studentScoreSort.getScore() == null || "".equals(studentScoreSort.getScore())) {
                            total = total.add(new BigDecimal("0"));
                        } else {
                            total = total.add(new BigDecimal(studentScoreSort.getScore()));
                        }
                        stuCount++;
                    }
                    BigDecimal average = new BigDecimal(0);
                    if (stuCount > 0) {
                        average = total.divide(new BigDecimal(stuCount + ""), 2, BigDecimal.ROUND_HALF_EVEN);
                    }

                    examScoreVo.setStuTotal(total.toString());
                    examScoreVo.setStuAverage(average.toString());
                    examScoreVoList.add(examScoreVo);
//					StudentScoreSort totalTemp = new StudentScoreSort();
//					totalTemp.setSubjectCode((Integer.MAX_VALUE)+"");
//					totalTemp.setScore(total.toString());
//					list.add(totalTemp);
//					mapSort.put(key, list);
//					StudentScoreSort averageTemp = new StudentScoreSort();
//					averageTemp.setSubjectCode((Integer.MAX_VALUE - 1)+"");
//					averageTemp.setScore(average.toString());
//					list.add(averageTemp);
//					mapSort.put(key, list);
                }


            }
        }

        model.addAttribute("subjectList", subjectSortList);
        model.addAttribute("examScoreVoList", examScoreVoList);
        viewPath = structurePath("/viewList");
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/simpleTeacher/index")
    public ModelAndView simpleTeacherIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StudentScoreCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        ModelAndView mav = new ModelAndView();
        String viewPath = null;
        String subjectName = "??????";
		/*try {
			if(condition!=null&&condition.getExamName()!=null&&!"".equals(condition.getExamName()) ){
				String examName=new String((condition.getExamName()).getBytes("iso-8859-1"),"utf-8");
				condition.setExamName(examName);
			}
			
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        List<StudentScoreVo> studentScoreVoList = new ArrayList<StudentScoreVo>();
        if (condition.getSchoolYear() == null || condition.getTermCode() == null || condition.getGradeId() == null ||
                condition.getExamType() == null || condition.getExamName() == null || condition.getSubjectCode() == null) {

        } else {
            StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
            studentScoreCondition.setSchoolYear(condition.getSchoolYear());
            studentScoreCondition.setTermCode(condition.getTermCode());
            studentScoreCondition.setGradeId(condition.getGradeId());
            studentScoreCondition.setTeamId(condition.getTeamId());
            studentScoreCondition.setStudentId(condition.getStudentId());
            studentScoreCondition.setExamType(condition.getExamType());
            studentScoreCondition.setExamName(condition.getExamName());
            studentScoreCondition.setSubjectCode(condition.getSubjectCode());
            studentScoreCondition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page);
            if (stuScoreLlist == null) {
                stuScoreLlist = new ArrayList<StudentScore>();
            }
            List<StudentScore> stuScoreGradeLlist = new ArrayList<StudentScore>();
            if (condition.getTeamId() != null && condition.getTeamId() > 0) {
                StudentScoreCondition studentScoreGradeCondition = new StudentScoreCondition();
                studentScoreGradeCondition.setSchoolYear(condition.getSchoolYear());
                studentScoreGradeCondition.setTermCode(condition.getTermCode());
                studentScoreGradeCondition.setGradeId(condition.getGradeId());
                studentScoreGradeCondition.setExamType(condition.getExamType());
                studentScoreGradeCondition.setExamName(condition.getExamName());
                studentScoreGradeCondition.setSubjectCode(condition.getSubjectCode());
                studentScoreGradeCondition.setSchoolId(user.getSchoolId());
                page.setPageSize(Integer.MAX_VALUE);
                stuScoreGradeLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreGradeCondition, page);
                if (stuScoreGradeLlist == null) {
                    stuScoreGradeLlist = new ArrayList<StudentScore>();
                }

            } else {
                stuScoreGradeLlist = stuScoreLlist;
            }

            //????????????????????????
            Map<Integer, List<StudentScore>> teamScoreList = new HashMap<Integer, List<StudentScore>>();
            int gradeId = condition.getGradeId();
            for (StudentScore studentScore : stuScoreGradeLlist) {
                if (gradeId == studentScore.getGradeId()) {
                    int teamId = studentScore.getTeamId();
                    List<StudentScore> teamList = teamScoreList.get(studentScore.getTeamId());
                    List<StudentScore> list = null;
                    if (teamList == null) {
                        list = new ArrayList<StudentScore>();

                    } else {
                        list = teamScoreList.get(teamId);

                    }

                    list.add(studentScore);
                    teamScoreList.put(teamId, list);
                } else {
                    //????????????????????????????????????????????????????????????????????????????????????????????????????????????
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//?????????????????????????????????????????????????????????
            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                //??????????????????
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//?????????????????????????????????????????????????????????
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //??????????????????
                vo.setGradeRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(gradeStuIdScoreList)) + "");

                Student student = this.studentService.findStudentById(studentScore.getStudentId());
                vo.setStudentName(student.getName());
                vo.setStudentNumber(student.getStudentNumber());
                studentScoreVoList.add(vo);
            }

            subjectName = this.subjectService.findUnique(user.getSchoolId(), condition.getSubjectCode()).getName();
        }

        viewPath = "/teach/scoreSelect/simpleTeacher/index";
        mav.addObject("ScoreCondition", condition);
        mav.addObject("subjectName", subjectName);
        mav.addObject("studentScoreVoList", studentScoreVoList);
        mav.setViewName(viewPath);

        return mav;
    }

    @RequestMapping(value = "/simpleTeacher/downLoadExcel")
    public void simpleTeacherDownLoadExcel(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @RequestParam(value = "termCode", required = true) String termCode,
            @RequestParam(value = "gradeId", required = true) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "studentId", required = false) Integer studentId,
            @RequestParam(value = "examType", required = true) String examType,
            @RequestParam(value = "examName", required = true) String examName,
            @RequestParam(value = "subjectCode", required = true) String subjectCode,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            HttpServletResponse response,
            HttpServletRequest request) {

        StudentScoreCondition condition = new StudentScoreCondition();
        condition.setSchoolYear(schoolYear);
        condition.setTermCode(termCode);
        condition.setGradeId(gradeId);
        condition.setTeamId(teamId);
        condition.setStudentId(studentId);
        condition.setExamType(examType);
		/*try {
			examName=new String((request.getParameter("examName")).getBytes("iso-8859-1"),"utf-8");
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        condition.setExamName(examName);
        condition.setSubjectCode(subjectCode);
        condition.setSchoolId(user.getSchoolId());

        List<StudentScoreVo> studentScoreVoList = new ArrayList<StudentScoreVo>();
        if (condition.getSchoolYear() == null || condition.getTermCode() == null || condition.getGradeId() == null ||
                condition.getExamType() == null || condition.getExamName() == null || condition.getSubjectCode() == null) {

        } else {
            StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
            studentScoreCondition.setSchoolYear(condition.getSchoolYear());
            studentScoreCondition.setTermCode(condition.getTermCode());
            studentScoreCondition.setGradeId(condition.getGradeId());
            studentScoreCondition.setTeamId(condition.getTeamId());
            studentScoreCondition.setStudentId(condition.getStudentId());
            studentScoreCondition.setExamType(condition.getExamType());
            studentScoreCondition.setExamName(condition.getExamName());
            studentScoreCondition.setSubjectCode(condition.getSubjectCode());
            studentScoreCondition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page);
            if (stuScoreLlist == null) {
                stuScoreLlist = new ArrayList<StudentScore>();
            }
            List<StudentScore> stuScoreGradeLlist = new ArrayList<StudentScore>();
            if (condition.getTeamId() != null && condition.getTeamId() > 0) {
                StudentScoreCondition studentScoreGradeCondition = new StudentScoreCondition();
                studentScoreGradeCondition.setSchoolYear(condition.getSchoolYear());
                studentScoreGradeCondition.setTermCode(condition.getTermCode());
                studentScoreGradeCondition.setGradeId(condition.getGradeId());
                studentScoreGradeCondition.setExamType(condition.getExamType());
                studentScoreGradeCondition.setExamName(condition.getExamName());
                studentScoreGradeCondition.setSubjectCode(condition.getSubjectCode());
                studentScoreGradeCondition.setSchoolId(user.getSchoolId());
                page.setPageSize(Integer.MAX_VALUE);
                stuScoreGradeLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreGradeCondition, page);

            } else {
                stuScoreGradeLlist = stuScoreLlist;
            }

            //????????????????????????
            Map<Integer, List<StudentScore>> teamScoreList = new HashMap<Integer, List<StudentScore>>();
            int midGradeId = condition.getGradeId();
            for (StudentScore studentScore : stuScoreGradeLlist) {
                if (midGradeId == studentScore.getGradeId()) {
                    int midTeamId = studentScore.getTeamId();
                    List<StudentScore> teamList = teamScoreList.get(studentScore.getTeamId());
                    List<StudentScore> list = null;
                    if (teamList == null) {
                        list = new ArrayList<StudentScore>();

                    } else {
                        list = teamScoreList.get(midTeamId);

                    }

                    list.add(studentScore);
                    teamScoreList.put(midTeamId, list);
                } else {
                    //????????????????????????????????????????????????????????????????????????????????????????????????????????????
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//?????????????????????????????????????????????????????????
            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                vo.setSubjectCode(studentScore.getSubjectCode());
                //??????????????????
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//?????????????????????????????????????????????????????????
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //??????????????????
                vo.setGradeRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(gradeStuIdScoreList)) + "");

                Student student = this.studentService.findStudentById(studentScore.getStudentId());
                vo.setStudentName(student.getName());
                vo.setStudentNumber(student.getStudentNumber());
                studentScoreVoList.add(vo);
            }

        }

        //examScoreVoList = formatList(examScoreVoList,subjectSortList);
        Subject subject = this.subjectService.findUnique(user.getSchoolId(), condition.getSubjectCode());
        if (subject == null) {
            subject = new Subject();
        }
        ParseConfig config = SzxyExcelTookit.getConfig();
        List<Object> maps = new ArrayList<Object>();
        /*??????	?????????	??????	??????	????????????	????????????*/
        List<String> titleList = new ArrayList<String>();
        List<String> fieldList = new ArrayList<String>();
        //List<StudentScoreVo>
        if (subject != null && subject.getName() != null && subject.getCode() != null && !"".equals(subject.getName()) && !"".equals(subject.getCode())) {
            titleList.add(subject.getName());
            fieldList.add(subject.getCode());
        } else {
            titleList.add("??????");
            fieldList.add("999");
        }


        String[] titleFirst = {"????????? ", "??????"};
        String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
        String[] titleThree = {"????????????", "????????????"};


        String[] fieldFirst = {"studentNumber", "studentName"};
        String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
        String[] fieldThree = {"teamRank", "gradeRank"};

        String[] titles = merge(titleFirst, titleTwo, titleThree);
        String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
        config.setTitles(titles);
        config.setFieldNames(fieldNames);
        config.setSheetTitle("??????????????????");


        for (StudentScoreVo vo : studentScoreVoList) {

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("studentNumber", vo.getStudentNumber());
            map.put("studentName", vo.getStudentName());


            map.put(vo.getSubjectCode(), vo.getScore());

            map.put("teamRank", vo.getTeamRank());
            map.put("gradeRank", vo.getGradeRank());

            maps.add(map);
        }

        String filename = "??????????????????.xls";
        try {
            SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/headTeacher/index")
    public ModelAndView headTeacherIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StudentScoreCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        ModelAndView mav = new ModelAndView();
        String viewPath = null;
        String subjectName = "??????";
        int resultType = 1;

        viewPath = "/teach/scoreSelect/headTeacher/index";
        mav.addObject("ScoreCondition", condition);
        mav.setViewName(viewPath);
		
		/*try {
			if(condition!=null&&condition.getExamName()!=null&&!"".equals(condition.getExamName()) ){
				String examName=new String((condition.getExamName()).getBytes("iso-8859-1"),"utf-8");
				condition.setExamName(examName);
			}
			
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/

        List<StudentScoreVo> studentScoreVoList = new ArrayList<StudentScoreVo>();
        List<ExamScoreVo> examScoreVoList = new ArrayList<ExamScoreVo>();
        condition.setSchoolId(user.getSchoolId());
        if (condition.getSchoolYear() == null || condition.getTermCode() == null || condition.getGradeId() == null ||
                condition.getExamType() == null || condition.getExamName() == null || condition.getSubjectCode() == null) {
            mav.addObject("resultType", 3);
            return mav;
        }
        if (condition.getSubjectCode() != null && !"".equals(condition.getSubjectCode())) {
            resultType = 1;
            StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
            studentScoreCondition.setSchoolYear(condition.getSchoolYear());
            studentScoreCondition.setTermCode(condition.getTermCode());
            studentScoreCondition.setGradeId(condition.getGradeId());
            studentScoreCondition.setTeamId(condition.getTeamId());
            studentScoreCondition.setStudentId(condition.getStudentId());
            studentScoreCondition.setExamType(condition.getExamType());
            studentScoreCondition.setExamName(condition.getExamName());
            studentScoreCondition.setSubjectCode(condition.getSubjectCode());
            studentScoreCondition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page);
            if (stuScoreLlist == null) {
                stuScoreLlist = new ArrayList<StudentScore>();
            }
            //????????????????????????
            List<StudentScore> stuScoreGradeLlist = new ArrayList<StudentScore>();
            if (condition.getTeamId() != null && condition.getTeamId() > 0) {
                StudentScoreCondition studentScoreGradeCondition = new StudentScoreCondition();
                studentScoreGradeCondition.setSchoolYear(condition.getSchoolYear());
                studentScoreGradeCondition.setTermCode(condition.getTermCode());
                studentScoreGradeCondition.setGradeId(condition.getGradeId());
                studentScoreGradeCondition.setExamType(condition.getExamType());
                studentScoreGradeCondition.setExamName(condition.getExamName());
                studentScoreGradeCondition.setSubjectCode(condition.getSubjectCode());
                studentScoreGradeCondition.setSchoolId(user.getSchoolId());
                stuScoreGradeLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreGradeCondition, page);
                if (stuScoreGradeLlist == null) {
                    stuScoreGradeLlist = new ArrayList<StudentScore>();
                }

            } else {
                stuScoreGradeLlist = stuScoreLlist;
            }
            Map<Integer, List<StudentScore>> teamScoreList = new HashMap<Integer, List<StudentScore>>();
            int gradeId = condition.getGradeId();
            for (StudentScore studentScore : stuScoreGradeLlist) {
                if (gradeId == studentScore.getGradeId()) {
                    int teamId = studentScore.getTeamId();
                    List<StudentScore> teamList = teamScoreList.get(studentScore.getTeamId());
                    List<StudentScore> list = null;
                    if (teamList == null) {
                        list = new ArrayList<StudentScore>();

                    } else {
                        list = teamScoreList.get(teamId);

                    }

                    list.add(studentScore);
                    teamScoreList.put(teamId, list);
                } else {
                    //????????????????????????????????????????????????????????????????????????????????????????????????????????????
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//?????????????????????????????????????????????????????????


            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                //??????????????????
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//?????????????????????????????????????????????????????????
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //??????????????????
                vo.setGradeRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(gradeStuIdScoreList)) + "");

                Student student = this.studentService.findStudentById(studentScore.getStudentId());
                vo.setStudentName(student.getName());
                vo.setStudentNumber(student.getStudentNumber());
                studentScoreVoList.add(vo);
            }

            subjectName = this.subjectService.findUnique(user.getSchoolId(), condition.getSubjectCode()).getName();
            mav.addObject("subjectName", subjectName);
            mav.addObject("studentScoreVoList", studentScoreVoList);

        } else if (condition.getSubjectCode() == null || "".equals(condition.getSubjectCode())) {
            resultType = 2;
            condition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> gradeScoreList = this.studentScoreService.findStudentScoreByCondition(getGradeCondition(condition), page);//????????????????????????????????????????????????

            String[] sortGradeArray = mapToSortArray(getPerStuTotalScoreMap(gradeScoreList));//????????????????????????????????????????????????????????????.,,,??????????????????
            Map<Integer, String[]> teamArrayMap = getTeamScoreArrayByScoreList(gradeScoreList);//???????????????????????????????????????????????????????????????
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(getStudentScoreByConditon(user, condition), page);
            if (stuScoreList == null) {
                stuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, List<StudentScore>> stuIdScoreListMap = getstuIdScoreListMap(stuScoreList);//????????????????????????????????????

            Set<Integer> set = new HashSet<Integer>();
            set = stuIdScoreListMap.keySet();
            for (int key : set) {

                List<StudentScore> list = stuIdScoreListMap.get(key);
                Student stu = this.studentService.findStudentById(key);
                if (stu == null) {
                    stu = new Student();
                }
                ExamScoreVo vo = new ExamScoreVo();
                vo.setStudentId(key);//??????
                vo.setStudentName(stu.getName());//????????????
                vo.setStudentNumber(stu.getStudentNumber());//?????????
                BigDecimal averageScore = new BigDecimal("0");
                BigDecimal totalScore = calculateTotalScoreFromPerStuListScore(list);//????????????????????????????????????
                vo.setStuTotal(totalScore.toString());//?????????
                averageScore = totalScore.divide(new BigDecimal(list.size() + ""), 2, BigDecimal.ROUND_HALF_EVEN);//???????????????
                vo.setStuAverage(averageScore.toString());//?????????
                vo.setStuGradeRank(getRankInSortArray(vo.getStuTotal(), sortGradeArray) + "");//????????????
                vo.setStuTeamRank(getRankInSortArray(vo.getStuTotal(), teamArrayMap.get(list.get(0).getTeamId())) + "");//????????????
                vo.setStudentScoreSort(sortStudentScoreByOne(list));//????????????????????????????????????????????????????????????List
                examScoreVoList.add(vo);
            }


            List<SubjectSort> subjectSortList = getSortSubject(user, condition);//?????????????????????????????????
            mav.addObject("subjectList", subjectSortList);
            for (ExamScoreVo vo : examScoreVoList) {
                vo.setScoreNum(vo.getStudentScoreSort().size());
            }
            mav.addObject("examScoreVoList", examScoreVoList);
        }


        mav.addObject("resultType", resultType);
        return mav;
    }

    @RequestMapping(value = "/headTeacher/downLoadExcel")
    public void headTeacherDownLoadExcel(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "schoolYear", required = true) String schoolYear,
            @RequestParam(value = "termCode", required = true) String termCode,
            @RequestParam(value = "gradeId", required = true) Integer gradeId,
            @RequestParam(value = "teamId", required = true) Integer teamId,
            @RequestParam(value = "studentId", required = false) Integer studentId,
            @RequestParam(value = "examType", required = true) String examType,
            @RequestParam(value = "examName", required = true) String examName,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            HttpServletResponse response,
            HttpServletRequest request) {


        StudentScoreCondition condition = new StudentScoreCondition();
        condition.setSchoolYear(schoolYear);
        condition.setTermCode(termCode);
        condition.setGradeId(gradeId);
        condition.setTeamId(teamId);
        condition.setStudentId(studentId);
        condition.setExamType(examType);
		/*try {
			//URLDecoder.decode(examName,"UTF-8");
			examName=new String((request.getParameter("examName")).getBytes("iso-8859-1"),"utf-8");
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        condition.setExamName(examName);
        condition.setSubjectCode(subjectCode);
        condition.setSchoolId(user.getSchoolId());

        List<StudentScoreVo> studentScoreVoList = new ArrayList<StudentScoreVo>();
        List<ExamScoreVo> examScoreVoList = new ArrayList<ExamScoreVo>();
        condition.setSchoolId(user.getSchoolId());
        if (condition.getSchoolYear() == null || condition.getTermCode() == null || condition.getGradeId() == null ||
                condition.getExamType() == null || condition.getExamName() == null || condition.getSubjectCode() == null) {
        }
        //??????????????????
        if (condition.getSubjectCode() != null && !"".equals(condition.getSubjectCode())) {
            StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
            studentScoreCondition.setSchoolYear(condition.getSchoolYear());
            studentScoreCondition.setTermCode(condition.getTermCode());
            studentScoreCondition.setGradeId(condition.getGradeId());
            studentScoreCondition.setTeamId(condition.getTeamId());
            studentScoreCondition.setStudentId(condition.getStudentId());
            studentScoreCondition.setExamType(condition.getExamType());
            studentScoreCondition.setExamName(condition.getExamName());
            studentScoreCondition.setSubjectCode(condition.getSubjectCode());
            studentScoreCondition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page);
            if (stuScoreLlist == null) {
                stuScoreLlist = new ArrayList<StudentScore>();
            }
            //????????????????????????
            List<StudentScore> stuScoreGradeLlist = new ArrayList<StudentScore>();
            if (condition.getTeamId() != null && condition.getTeamId() > 0) {
                StudentScoreCondition studentScoreGradeCondition = new StudentScoreCondition();
                studentScoreGradeCondition.setSchoolYear(condition.getSchoolYear());
                studentScoreGradeCondition.setTermCode(condition.getTermCode());
                studentScoreGradeCondition.setGradeId(condition.getGradeId());
                studentScoreGradeCondition.setExamType(condition.getExamType());
                studentScoreGradeCondition.setExamName(condition.getExamName());
                studentScoreGradeCondition.setSubjectCode(condition.getSubjectCode());
                studentScoreGradeCondition.setSchoolId(user.getSchoolId());
                stuScoreGradeLlist = this.studentScoreService.findStudentScoreByCondition(studentScoreGradeCondition, page);
                if (stuScoreGradeLlist == null) {
                    stuScoreGradeLlist = new ArrayList<StudentScore>();
                }

            } else {
                stuScoreGradeLlist = stuScoreLlist;
            }
            Map<Integer, List<StudentScore>> teamScoreList = new HashMap<Integer, List<StudentScore>>();
            int midGradeId = condition.getGradeId();
            for (StudentScore studentScore : stuScoreGradeLlist) {
                if (midGradeId == studentScore.getGradeId()) {
                    int midTeamId = studentScore.getTeamId();
                    List<StudentScore> teamList = teamScoreList.get(studentScore.getTeamId());
                    List<StudentScore> list = null;
                    if (teamList == null) {
                        list = new ArrayList<StudentScore>();

                    } else {
                        list = teamScoreList.get(midTeamId);

                    }

                    list.add(studentScore);
                    teamScoreList.put(midTeamId, list);
                } else {
                    //????????????????????????????????????????????????????????????????????????????????????????????????????????????
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//?????????????????????????????????????????????????????????


            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                vo.setSubjectCode(studentScore.getSubjectCode());
                //??????????????????
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//?????????????????????????????????????????????????????????
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //??????????????????
                vo.setGradeRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(gradeStuIdScoreList)) + "");

                Student student = this.studentService.findStudentById(studentScore.getStudentId());
                vo.setStudentName(student.getName());
                vo.setStudentNumber(student.getStudentNumber());
                studentScoreVoList.add(vo);
            }

            Subject subject = this.subjectService.findUnique(user.getSchoolId(), condition.getSubjectCode());
            if (subject == null) {
                subject = new Subject();
            }
            ParseConfig config = SzxyExcelTookit.getConfig();
            List<Object> maps = new ArrayList<Object>();
            /*??????	?????????	??????	??????	????????????	????????????*/
            List<String> titleList = new ArrayList<String>();
            List<String> fieldList = new ArrayList<String>();
            //List<StudentScoreVo>
            if (subject != null && subject.getName() != null && subject.getCode() != null && !"".equals(subject.getName()) && !"".equals(subject.getCode())) {
                titleList.add(subject.getName());
                fieldList.add(subject.getCode());
            } else {
                titleList.add("??????");
                fieldList.add("999");
            }

            String[] titleFirst = {"????????? ", "??????"};
            String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
            String[] titleThree = {"????????????", "????????????"};


            String[] fieldFirst = {"studentNumber", "studentName"};
            String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
            String[] fieldThree = {"teamRank", "gradeRank"};

            String[] titles = merge(titleFirst, titleTwo, titleThree);
            String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
            config.setTitles(titles);
            config.setFieldNames(fieldNames);
            config.setSheetTitle("??????????????????");


            for (StudentScoreVo vo : studentScoreVoList) {

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("studentNumber", vo.getStudentNumber());
                map.put("studentName", vo.getStudentName());


                map.put(vo.getSubjectCode(), vo.getScore());

                map.put("teamRank", vo.getTeamRank());
                map.put("gradeRank", vo.getGradeRank());

                maps.add(map);
            }

            String filename = "??????????????????.xls";
            try {
                SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        } else if (condition.getSubjectCode() == null || "".equals(condition.getSubjectCode())) {//??????????????????
            condition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> gradeScoreList = this.studentScoreService.findStudentScoreByCondition(getGradeCondition(condition), page);//????????????????????????????????????????????????

            String[] sortGradeArray = mapToSortArray(getPerStuTotalScoreMap(gradeScoreList));//????????????????????????????????????????????????????????????.,,,??????????????????
            Map<Integer, String[]> teamArrayMap = getTeamScoreArrayByScoreList(gradeScoreList);//???????????????????????????????????????????????????????????????
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(getStudentScoreByConditon(user, condition), page);
            if (stuScoreList == null) {
                stuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, List<StudentScore>> stuIdScoreListMap = getstuIdScoreListMap(stuScoreList);//????????????????????????????????????

            Set<Integer> set = new HashSet<Integer>();
            set = stuIdScoreListMap.keySet();
            for (int key : set) {

                List<StudentScore> list = stuIdScoreListMap.get(key);
                Student stu = this.studentService.findStudentById(key);
                if (stu == null) {
                    stu = new Student();
                }
                ExamScoreVo vo = new ExamScoreVo();
                vo.setStudentId(key);//??????
                vo.setStudentName(stu.getName());//????????????
                vo.setStudentNumber(stu.getStudentNumber());//?????????
                BigDecimal averageScore = new BigDecimal("0");
                BigDecimal totalScore = calculateTotalScoreFromPerStuListScore(list);//????????????????????????????????????
                vo.setStuTotal(totalScore.toString());//?????????
                averageScore = totalScore.divide(new BigDecimal(list.size() + ""), 2, BigDecimal.ROUND_HALF_EVEN);//???????????????
                vo.setStuAverage(averageScore.toString());//?????????
                vo.setStuGradeRank(getRankInSortArray(vo.getStuTotal(), sortGradeArray) + "");//????????????
                vo.setStuTeamRank(getRankInSortArray(vo.getStuTotal(), teamArrayMap.get(list.get(0).getTeamId())) + "");//????????????
                vo.setStudentScoreSort(sortStudentScoreByOne(list));//????????????????????????????????????????????????????????????List
                examScoreVoList.add(vo);
            }


            List<SubjectSort> subjectSortList = getSortSubject(user, condition);//?????????????????????????????????
            for (ExamScoreVo vo : examScoreVoList) {
                vo.setScoreNum(vo.getStudentScoreSort().size());
            }

            examScoreVoList = formatList(examScoreVoList, subjectSortList);

            ParseConfig config = SzxyExcelTookit.getConfig();
            List<Object> maps = new ArrayList<Object>();
            List<String> titleList = new ArrayList<String>();
            List<String> fieldList = new ArrayList<String>();
            for (SubjectSort subjectSort : subjectSortList) {
                titleList.add(subjectSort.getName());
                fieldList.add(subjectSort.getCode());
            }

            String[] titleFirst = {"?????????", "?????? "};
            String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
            String[] titleThree = {"?????????", "??????", "????????????", "????????????"};


            String[] fieldFirst = {"studentNumber", "studentName"};
            String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
            String[] fieldThree = {"average", "total", "teamRank", "gradeRank"};

            String[] titles = merge(titleFirst, titleTwo, titleThree);
            String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
            config.setTitles(titles);
            config.setFieldNames(fieldNames);
            config.setSheetTitle("??????????????????");


            for (ExamScoreVo vo : examScoreVoList) {

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("studentNumber", vo.getStudentNumber());
                map.put("studentName", vo.getStudentName());

                List<StudentScoreSort> scoreList = vo.getStudentScoreSort();
                for (StudentScoreSort studentScoreSort : scoreList) {
                    map.put(studentScoreSort.getSubjectCode(), studentScoreSort.getScore());
                }

                map.put("average", vo.getStuAverage());
                map.put("total", vo.getStuTotal());
                map.put("teamRank", vo.getStuTeamRank());
                map.put("gradeRank", vo.getStuGradeRank());

                maps.add(map);
            }

            String filename = "??????????????????.xls";
            try {
                SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }


    }


    /**
     * ????????????????????????????????????
     *
     * @param list
     * @return
     */
    private BigDecimal calculateTotalScoreFromPerStuListScore(
            List<StudentScore> list) {
        BigDecimal totalScore = new BigDecimal("0");
        for (StudentScore studentScore : list) {
            boolean isScoreZero = true;
            if (studentScore.getScore() == null || "".equals(studentScore.getScore().trim())) {
                studentScore.setScore("0");
                isScoreZero = false;
            }
            BigDecimal score = new BigDecimal(studentScore.getScore());
            totalScore = totalScore.add(score);//
            if (!isScoreZero) {
                studentScore.setScore("");
            }
        }
        return totalScore;
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????
     *
     * @param list
     * @return
     */
    private List<StudentScoreSort> sortStudentScoreByOne(List<StudentScore> list) {

        List<StudentScoreSort> studentScoreSortList = new ArrayList<StudentScoreSort>();
        if (list == null) {
            return studentScoreSortList;
        }
        try {
            for (StudentScore studentScore : list) {
                StudentScoreSort studentScoreSort = new StudentScoreSort();
                BeanUtils.copyProperties(studentScoreSort, studentScore);
                studentScoreSortList.add(studentScoreSort);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(studentScoreSortList);//??????????????????????????????????????????
        return studentScoreSortList;
    }

    private StudentScoreCondition getStudentScoreByConditon(UserInfo user,
                                                            StudentScoreCondition condition) {
        StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
        studentScoreCondition.setSchoolId(user.getSchoolId());
        studentScoreCondition.setSchoolYear(condition.getSchoolYear());
        studentScoreCondition.setTermCode(condition.getTermCode());
        studentScoreCondition.setGradeId(condition.getGradeId());
        studentScoreCondition.setTeamId(condition.getTeamId());
        studentScoreCondition.setStudentId(condition.getStudentId());
        studentScoreCondition.setExamType(condition.getExamType());
        studentScoreCondition.setExamName(condition.getExamName());
        return studentScoreCondition;
    }

    /**
     * ????????????????????????????????????
     *
     * @param stuScoreList
     * @return
     */
    private Map<Integer, List<StudentScore>> getstuIdScoreListMap(
            List<StudentScore> stuScoreList) {
        Map<Integer, List<StudentScore>> stuIdScoreListMap = new HashMap<Integer, List<StudentScore>>();//Map??????????????????list???
        for (StudentScore studentScore : stuScoreList) {
            List<StudentScore> stuScoreMidList = stuIdScoreListMap.get(studentScore.getStudentId());
            if (stuScoreMidList == null) {
                stuScoreMidList = new ArrayList<StudentScore>();
            }
            stuScoreMidList.add(studentScore);
            stuIdScoreListMap.put(studentScore.getStudentId(), stuScoreMidList);
        }
        return stuIdScoreListMap;
    }

    /**
     * ??????????????????????????????
     *
     * @param gradeScoreList
     * @return
     */
    private Map<Integer, String[]> getTeamScoreArrayByScoreList(
            List<StudentScore> gradeScoreList) {
        Map<Integer, String[]> teamArrayMap = new HashMap<Integer, String[]>();//???????????????????????????????????????????????????
        Map<Integer, List<StudentScore>> teamScoreListMap = new HashMap<Integer, List<StudentScore>>();//?????????????????????????????????
        for (StudentScore stuScore : gradeScoreList) {
            List<StudentScore> teamList = teamScoreListMap.get(stuScore.getTeamId());
            if (teamList == null) {
                teamList = new ArrayList<StudentScore>();
            }
            teamList.add(stuScore);
            teamScoreListMap.put(stuScore.getTeamId(), teamList);
        }
        Set<Integer> teamSet = new HashSet<Integer>();
        teamSet = teamScoreListMap.keySet();
        for (Integer key : teamSet) {
            List<StudentScore> list = teamScoreListMap.get(key);
            Map<Integer, String> teamTotalScoreList = getPerStuTotalScoreMap(list);//?????????????????????????????????????????????????????????
            String[] sortTeamArray = mapToSortArray(teamTotalScoreList);
            teamArrayMap.put(key, sortTeamArray);
        }
        return teamArrayMap;
    }


    /**
     * ?????????????????????????????????????????????????????????
     *
     * @param stuScoreList
     * @return
     */
    private Map<Integer, String> getPerStuTotalScoreMap(
            List<StudentScore> stuScoreList) {
        Map<Integer, String> stuIdScoreList = new HashMap<Integer, String>();
        for (StudentScore stuScore : stuScoreList) {//??????????????????????????????
            boolean isScoreZero = true;
            String scoreSum = stuIdScoreList.get(stuScore.getStudentId());
            if (scoreSum == null) {
                scoreSum = "0";
            }

            BigDecimal totalScore = new BigDecimal(scoreSum);
            if (stuScore.getScore() == null || "".equals(stuScore.getScore().trim())) {
                stuScore.setScore("0");
                isScoreZero = false;

            }
            totalScore = totalScore.add(new BigDecimal(stuScore.getScore()));
            stuIdScoreList.put(stuScore.getStudentId(), totalScore.toString());
            if (!isScoreZero) {
                stuScore.setScore("");
            }
        }
        return stuIdScoreList;
    }


    /**
     * ???map???????????????????????????
     *
     * @param stuIdScoreList
     * @return
     */
    private String[] mapToSortArray(Map<Integer, String> stuIdScoreList) {
        //stuIdScoreList.put(0, "0");
        List<String> list = new ArrayList<String>(stuIdScoreList.values());//???map?????????list
        String[] sortArray = (String[]) list.toArray(new String[list.size()]);//???list?????????array
        String endArray[] = this.Arrays(sortArray);
        //Arrays.sort(sortArray);//???????????????
        return endArray;
    }

    private String[] Arrays(String[] sortArray) {
        //int intArray[] =  new int[];
        String[] endArray = new String[sortArray.length];
        BigDecimal[] bigArray = new BigDecimal[sortArray.length];
        for (int i = 0; i < sortArray.length; i++) {
            bigArray[i] = new BigDecimal(sortArray[i]);
        }

        Arrays.sort(bigArray);
        for (int i = 0; i < bigArray.length; i++) {
            endArray[i] = bigArray[i].toString();
        }

        return endArray;
    }

    /**
     * ???????????????????????????????????????
     *
     * @param stuScoreTotal
     * @param sortArray
     * @return
     */
    private int getRankInSortArray(String stuScoreTotal, String[] sortArray) {
        int rankMiddle = this.binarySearch(sortArray, stuScoreTotal);//??????????????????
        if (rankMiddle < 0) {
            return 0;
        }
        while (rankMiddle != sortArray.length - 1//??????????????????????????????????????????
                && sortArray[rankMiddle].equals(sortArray[rankMiddle + 1])//????????????????????? 12234???2??????????????????
        ) {
            rankMiddle++;
        }

        int rank = sortArray.length - rankMiddle;
        return rank;
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param examTeamSubjectVo
     * @return
     */
    private String getMapKey(ExamTeamSubjectVo examTeamSubjectVo) {
        return examTeamSubjectVo.getSchoolYear() + examTeamSubjectVo.getTerm() + examTeamSubjectVo.getExamType() + examTeamSubjectVo.getExamName();
    }

    /**
     * ??????????????????????????????????????????????????????
     *
     * @param condition
     * @return
     */
    private StudentScoreCondition getGradeCondition(
            StudentScoreCondition condition) {
        StudentScoreCondition studentScoreCondition = new StudentScoreCondition();

        if (condition.getExamName() == null || "".equals(condition.getExamName())) {

        } else {
            studentScoreCondition.setExamName(condition.getExamName());
        }
        if (condition.getExamType() == null || "".equals(condition.getExamType())) {

        } else {
            studentScoreCondition.setExamName(condition.getExamType());
        }
        studentScoreCondition.setSchoolYear(condition.getSchoolYear());
        studentScoreCondition.setTermCode(condition.getTermCode());
        studentScoreCondition.setGradeId(condition.getGradeId());
        studentScoreCondition.setExamName(condition.getExamName());
        //?????????????????????????????????????????????????????????
        //studentScoreCondition.setSchoolId(schoolId);
        //studentScoreCondition.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);
        //studentScoreCondition.setExamType(condition.getExamType());
        return studentScoreCondition;
    }

    /**
     * ?????????????????????????????????
     *
     * @param user
     * @param condition
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<SubjectSort> getSortSubject(UserInfo user,
                                             StudentScoreCondition condition) {
        SubjectCondition subjectCondition = new SubjectCondition();
        subjectCondition.setSchoolId(user.getSchoolId());
        Page page = new Page();
        page.setPageSize(Integer.MAX_VALUE);
        List<Subject> subjectList = this.subjectService.findSubjectByCondition(subjectCondition, page, null);
        //???????????????
        Map<String, Subject> map = new HashMap<String, Subject>();
        for (Subject subject : subjectList) {
            map.put(subject.getCode(), subject);
        }
        Set<String> set = map.keySet();
        List<Subject> subjectListMid = new ArrayList<Subject>();
        for (String key : set) {

            subjectListMid.add(map.get(key));
        }
        List<SubjectSort> subjectSortList = new ArrayList<SubjectSort>();
        try {
            for (Subject subject : subjectListMid) {
                SubjectSort subjectSort = new SubjectSort();
                BeanUtils.copyProperties(subjectSort, subject);
                subjectSortList.add(subjectSort);
            }
        } catch (Exception e) {
            condition.setErrorMessage("??????????????????");
            e.printStackTrace();
        }
        Collections.sort(subjectSortList);
        return subjectSortList;
    }

    @RequestMapping(value = "/termCode")
    @ResponseBody
    public Map<String, String> termCode(
            @CurrentUser UserInfo user,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "examType", required = false) String examType,
            @RequestParam(value = "examName", required = false) String examName,
            @RequestParam(value = "type", required = false) String type
    ) {
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(schoolYear);
        examTeamSubjectCondition.setTerm(termCode);
        examTeamSubjectCondition.setGradeId(gradeId);
        examTeamSubjectCondition.setTeamId(teamId);
        examTeamSubjectCondition.setExamType(examType);
		
		/*try {
			if(examName!=null&&!"".equals(examName) ){
				examName=new String((examName).getBytes("iso-8859-1"),"utf-8");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/

        examTeamSubjectCondition.setExamName(examName);
        //examTeamSubjectCondition.setCode(examName);
        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        List<ExamTeamSubjectVo> voList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (type == null || "".equals(type)) {
            type = "?????????";
        }
        map.put(type, "");
        for (ExamTeamSubjectVo examTeamSubjectVo : voList) {
            String resultExamName = examTeamSubjectVo.getExamName();
            //String code = examTeamSubjectVo.getCode();
            map.put(resultExamName, resultExamName);
        }

        return map;
    }

    @RequestMapping(value = "/subjectCode")
    @ResponseBody
    public Map<String, String> subjectCode(
            @CurrentUser UserInfo user,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "examType", required = false) String examType,
            @RequestParam(value = "examName", required = false) String examName,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "type", required = false) String type) {
        Map<String, String> map = new HashMap<String, String>();
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(schoolYear);
        examTeamSubjectCondition.setTerm(termCode);
        examTeamSubjectCondition.setGradeId(gradeId);
        examTeamSubjectCondition.setTeamId(teamId);
        examTeamSubjectCondition.setExamType(examType);
		/*try {
			if(examName!=null&&!"".equals(examName) ){
				examName=new String((examName).getBytes("iso-8859-1"),"utf-8");
			}
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        examTeamSubjectCondition.setExamName(examName);
        //examTeamSubjectCondition.setCode(examName);
        examTeamSubjectCondition.setSubjectCode(subjectCode);
        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        List<ExamTeamSubjectVo> voList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        for (ExamTeamSubjectVo examTeamSubjectVo : voList) {
            String resultSubjectCode = examTeamSubjectVo.getSubjectCode();
            map.put(resultSubjectCode, resultSubjectCode);
        }


        Set<String> set = map.keySet();
        map = new LinkedHashMap<String, String>();
        if (type == null || "".equals(type)) {
            type = "?????????";
        }
        map.put(type, "");

        for (String key : set) {
            if (key == null || type.equals(key)) {

            } else {
                Subject subject = this.subjectService.findUnique(user.getSchoolId(), key);
                map.put(subject.getName(), key);
            }

        }

        return map;
    }


    @RequestMapping(value = "/examType")
    @ResponseBody
    public Map<String, String> examType(
            @CurrentUser UserInfo user,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "examType", required = false) String examType,
            @RequestParam(value = "type", required = false) String type) {
        Map<String, String> map = new HashMap<String, String>();
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(schoolYear);
        examTeamSubjectCondition.setTerm(termCode);
        examTeamSubjectCondition.setGradeId(gradeId);
        examTeamSubjectCondition.setTeamId(teamId);
        examTeamSubjectCondition.setExamType(examType);

        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        List<ExamTeamSubjectVo> voList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        for (ExamTeamSubjectVo examTeamSubjectVo : voList) {
            String examTypeTemp = examTeamSubjectVo.getExamType();
            map.put(examTypeTemp, examTypeTemp);
        }


        Set<String> set = map.keySet();
        map = new LinkedHashMap<String, String>();
        if (type == null || "".equals(type)) {
            type = "?????????";
        }
        map.put(type, "");

        for (String key : set) {
            if (key == null || type.equals(key)) {

            } else {
                ItemCondition condition = new ItemCondition();
                //condition.setName("name");
                condition.setTableCode(StudentScoreCondition.NEW_TABLECODE_EXTYPE);
                condition.setValue(key);
                List<Item> items = this.itemService.findItemByCondition(condition, null, null);
                Item item = new Item();
                if (items != null && items.size() > 0) {
                    item = items.get(0);
                    map.put(item.getName(), key);
                }

            }

        }

        return map;
    }

    private int binarySearch(String[] array, String total) {
        BigDecimal[] bigArray = new BigDecimal[array.length];
        for (int i = 0; i < array.length; i++) {
            bigArray[i] = new BigDecimal(array[i]);
        }
        if (total == null || "".equals(total)) {
            total = "0";
        }
        int midNum = Arrays.binarySearch(bigArray, new BigDecimal(total));
        return midNum;
    }

    public static void main(String[] args) {
        ScoreSelectController sc = new ScoreSelectController();

        String array[] = {"108", "21", "33", "45", "57"};
        String endArray[] = sc.Arrays(array);
        for (int i = 0; i < endArray.length; i++) {
            System.out.println(endArray[i]);
        }
        int rankMiddle = sc.binarySearch(endArray, "108");

        //int rankMiddle =  Arrays.binarySearch(endArray, "108");//??????????????????
        System.out.println(rankMiddle);
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

}
