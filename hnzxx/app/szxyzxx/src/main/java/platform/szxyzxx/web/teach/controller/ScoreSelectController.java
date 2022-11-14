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
        List<SubjectSort> subjectSortList = getSortSubject(user, condition);//获取学校科目并进行排序

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

                if (!examTeamSubjectVo.getTaskRate()) {//这个考试科目是否已经录入成绩
                    continue;//未录入走下一条记录
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
            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {//去掉考务重复内容
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

        for (ExamScoreVo examSoreVo : examScoreVoList) {//添加平均分和排名

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

            String stuScoreTotal = examSoreVo.getStuTotal();//计算年级中一个学生的总成绩
            condition.setExamName(examSoreVo.getExamName());
            condition.setExamType(examSoreVo.getExamType());
            StudentScoreCondition studentScoreCondition = getGradeCondition(condition);//年级中各个学生各科的成绩的查询条件
            studentScoreCondition.setSchoolId(user.getSchoolId());
            List<StudentScore> gradeStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);

            Map<Integer, String> stuIdScoreList = getPerStuTotalScoreMap(gradeStuScoreList);//把所有的考试成绩汇总为每个学生的总成绩

            examSoreVo.setStuGradeRank(getRankInSortArray(stuScoreTotal, mapToSortArray(stuIdScoreList)) + "");
            studentScoreCondition.setTeamId(condition.getTeamId());
            List<StudentScore> teamStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
            Map<Integer, String> stuIdTeamScoreList = getPerStuTotalScoreMap(teamStuScoreList);//把所有的考试成绩汇总为每个学生的总成绩

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
        //各类科目begin
        List<SubjectSort> subjectSortList = getSortSubject(user, condition);//获取学校科目并进行排序
        //各类科目end

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

                if (!examTeamSubjectVo.getTaskRate()) {//这个考试科目是否已经录入成绩
                    continue;//未录入走下一条记录
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
            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {//去掉考务重复内容
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

        for (ExamScoreVo examSoreVo : examScoreVoList) {//添加平均分和排名

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

            String stuScoreTotal = examSoreVo.getStuTotal();//计算年级中一个学生的总成绩
            condition.setExamName(examSoreVo.getExamName());
            condition.setExamType(examSoreVo.getExamType());
            StudentScoreCondition studentScoreCondition = getGradeCondition(condition);//年级中各个学生各科的成绩的查询条件
            studentScoreCondition.setSchoolId(user.getSchoolId());
            List<StudentScore> gradeStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
            if (gradeStuScoreList == null) {
                gradeStuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, String> stuIdScoreList = getPerStuTotalScoreMap(gradeStuScoreList);//把所有的考试成绩汇总为每个学生的总成绩

            examSoreVo.setStuGradeRank(getRankInSortArray(stuScoreTotal, mapToSortArray(stuIdScoreList)) + "");
            studentScoreCondition.setTeamId(condition.getTeamId());
            List<StudentScore> teamStuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
            if (teamStuScoreList == null) {
                teamStuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, String> stuIdTeamScoreList = getPerStuTotalScoreMap(teamStuScoreList);//把所有的考试成绩汇总为每个学生的总成绩

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

        String[] titleFirst = {"考试类型", "考试名称 "};
        String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
        String[] titleThree = {"平均分", "总分", "班级排名", "年级排名"};


        String[] fieldFirst = {"examType", "examName"};
        String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
        String[] fieldThree = {"average", "total", "teamRank", "gradeRank"};

        String[] titles = merge(titleFirst, titleTwo, titleThree);
        String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
        config.setTitles(titles);
        config.setFieldNames(fieldNames);
        config.setSheetTitle("学生成绩记录");
        // 期中考试	1
        //期末考试	2
        //平时考试	3

        Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
        Map<Object, Object> xxjbxxMaps = new HashMap<Object, Object>();
        xxjbxxMaps.put("1", "期中考试");
        xxjbxxMaps.put("2", "期末考试");
        xxjbxxMaps.put("3", "平时考试");
        codeWithValueMaps.put("examType", xxjbxxMaps);
        config.setCodeWithValueMaps(codeWithValueMaps);

        for (ExamScoreVo vo : examScoreVoList) {

            Map<String, Object> map = new HashMap<String, Object>();
            if (vo.getExamType() != null && "1".equals(vo.getExamType())) {
                vo.setExamTypeName("期中考试");
            } else if (vo.getExamType() != null && "2".equals(vo.getExamType())) {
                vo.setExamTypeName("期末考试");
            } else if (vo.getExamType() != null && "3".equals(vo.getExamType())) {
                vo.setExamTypeName("平时考试");
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

        String filename = "学生成绩记录.xls";
        try {
            SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数组合并
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
     * 修改结果展示list
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
        List<SubjectSort> subjectSortList = getSortSubject(user, condition);//获取学校科目并进行排序
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
        String subjectName = "科目";
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

            //前台展示内容填充
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
                    //不在同一个年级的情况，前台查询条件限制了必须送年级，所以这个问题不会出现
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//把所有的考试成绩汇总为每个学生的总成绩
            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                //添加班级排名
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//把所有的考试成绩汇总为每个学生的总成绩
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //添加年级排名
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

            //前台展示内容填充
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
                    //不在同一个年级的情况，前台查询条件限制了必须送年级，所以这个问题不会出现
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//把所有的考试成绩汇总为每个学生的总成绩
            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                vo.setSubjectCode(studentScore.getSubjectCode());
                //添加班级排名
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//把所有的考试成绩汇总为每个学生的总成绩
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //添加年级排名
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
        /*序号	学籍号	姓名	科目	班级排名	年级排名*/
        List<String> titleList = new ArrayList<String>();
        List<String> fieldList = new ArrayList<String>();
        //List<StudentScoreVo>
        if (subject != null && subject.getName() != null && subject.getCode() != null && !"".equals(subject.getName()) && !"".equals(subject.getCode())) {
            titleList.add(subject.getName());
            fieldList.add(subject.getCode());
        } else {
            titleList.add("科目");
            fieldList.add("999");
        }


        String[] titleFirst = {"学籍号 ", "姓名"};
        String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
        String[] titleThree = {"班级排名", "年级排名"};


        String[] fieldFirst = {"studentNumber", "studentName"};
        String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
        String[] fieldThree = {"teamRank", "gradeRank"};

        String[] titles = merge(titleFirst, titleTwo, titleThree);
        String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
        config.setTitles(titles);
        config.setFieldNames(fieldNames);
        config.setSheetTitle("科目成绩记录");


        for (StudentScoreVo vo : studentScoreVoList) {

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("studentNumber", vo.getStudentNumber());
            map.put("studentName", vo.getStudentName());


            map.put(vo.getSubjectCode(), vo.getScore());

            map.put("teamRank", vo.getTeamRank());
            map.put("gradeRank", vo.getGradeRank());

            maps.add(map);
        }

        String filename = "科目成绩记录.xls";
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
        String subjectName = "科目";
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
            //前台展示内容填充
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
                    //不在同一个年级的情况，前台查询条件限制了必须送年级，所以这个问题不会出现
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//把所有的考试成绩汇总为每个学生的总成绩


            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                //添加班级排名
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//把所有的考试成绩汇总为每个学生的总成绩
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //添加年级排名
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
            List<StudentScore> gradeScoreList = this.studentScoreService.findStudentScoreByCondition(getGradeCondition(condition), page);//年级中这次考试的每个人的每科成绩

            String[] sortGradeArray = mapToSortArray(getPerStuTotalScoreMap(gradeScoreList));//对年级中每个人的总成绩进行排序，组成数组.,,,年级成绩排序
            Map<Integer, String[]> teamArrayMap = getTeamScoreArrayByScoreList(gradeScoreList);//对每个班进行成绩排名，，，，。班级成绩排序
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(getStudentScoreByConditon(user, condition), page);
            if (stuScoreList == null) {
                stuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, List<StudentScore>> stuIdScoreListMap = getstuIdScoreListMap(stuScoreList);//把各科成绩整合到学生名下

            Set<Integer> set = new HashSet<Integer>();
            set = stuIdScoreListMap.keySet();
            for (int key : set) {

                List<StudentScore> list = stuIdScoreListMap.get(key);
                Student stu = this.studentService.findStudentById(key);
                if (stu == null) {
                    stu = new Student();
                }
                ExamScoreVo vo = new ExamScoreVo();
                vo.setStudentId(key);//学号
                vo.setStudentName(stu.getName());//学生姓名
                vo.setStudentNumber(stu.getStudentNumber());//学籍号
                BigDecimal averageScore = new BigDecimal("0");
                BigDecimal totalScore = calculateTotalScoreFromPerStuListScore(list);//获取一个学生各科成绩之和
                vo.setStuTotal(totalScore.toString());//总成绩
                averageScore = totalScore.divide(new BigDecimal(list.size() + ""), 2, BigDecimal.ROUND_HALF_EVEN);//获取平均分
                vo.setStuAverage(averageScore.toString());//平均分
                vo.setStuGradeRank(getRankInSortArray(vo.getStuTotal(), sortGradeArray) + "");//年级排名
                vo.setStuTeamRank(getRankInSortArray(vo.getStuTotal(), teamArrayMap.get(list.get(0).getTeamId())) + "");//班级排名
                vo.setStudentScoreSort(sortStudentScoreByOne(list));//为每个学生添加按照考试科目完成排序的成绩List
                examScoreVoList.add(vo);
            }


            List<SubjectSort> subjectSortList = getSortSubject(user, condition);//查询学校科目并进行排序
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
        //查询单个科目
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
            //前台展示内容填充
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
                    //不在同一个年级的情况，前台查询条件限制了必须送年级，所以这个问题不会出现
                }
            }

            Map<Integer, String> gradeStuIdScoreList = getPerStuTotalScoreMap(stuScoreGradeLlist);//把所有的考试成绩汇总为每个学生的总成绩


            for (StudentScore studentScore : stuScoreLlist) {
                StudentScoreVo vo = new StudentScoreVo();
                vo.setStudentId(studentScore.getStudentId());
                vo.setScore(studentScore.getScore());
                vo.setSubjectCode(studentScore.getSubjectCode());
                //添加班级排名
                Map<Integer, String> teamStuIdScoreList = getPerStuTotalScoreMap(teamScoreList.get(studentScore.getTeamId()));//把所有的考试成绩汇总为每个学生的总成绩
                vo.setTeamRank(getRankInSortArray(studentScore.getScore() == null ? "0" : studentScore.getScore(), mapToSortArray(teamStuIdScoreList)) + "");
                //添加年级排名
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
            /*序号	学籍号	姓名	科目	班级排名	年级排名*/
            List<String> titleList = new ArrayList<String>();
            List<String> fieldList = new ArrayList<String>();
            //List<StudentScoreVo>
            if (subject != null && subject.getName() != null && subject.getCode() != null && !"".equals(subject.getName()) && !"".equals(subject.getCode())) {
                titleList.add(subject.getName());
                fieldList.add(subject.getCode());
            } else {
                titleList.add("科目");
                fieldList.add("999");
            }

            String[] titleFirst = {"学籍号 ", "姓名"};
            String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
            String[] titleThree = {"班级排名", "年级排名"};


            String[] fieldFirst = {"studentNumber", "studentName"};
            String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
            String[] fieldThree = {"teamRank", "gradeRank"};

            String[] titles = merge(titleFirst, titleTwo, titleThree);
            String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
            config.setTitles(titles);
            config.setFieldNames(fieldNames);
            config.setSheetTitle("班级成绩记录");


            for (StudentScoreVo vo : studentScoreVoList) {

                Map<String, Object> map = new HashMap<String, Object>();

                map.put("studentNumber", vo.getStudentNumber());
                map.put("studentName", vo.getStudentName());


                map.put(vo.getSubjectCode(), vo.getScore());

                map.put("teamRank", vo.getTeamRank());
                map.put("gradeRank", vo.getGradeRank());

                maps.add(map);
            }

            String filename = "班级成绩记录.xls";
            try {
                SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


        } else if (condition.getSubjectCode() == null || "".equals(condition.getSubjectCode())) {//查询多个科目
            condition.setSchoolId(user.getSchoolId());
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> gradeScoreList = this.studentScoreService.findStudentScoreByCondition(getGradeCondition(condition), page);//年级中这次考试的每个人的每科成绩

            String[] sortGradeArray = mapToSortArray(getPerStuTotalScoreMap(gradeScoreList));//对年级中每个人的总成绩进行排序，组成数组.,,,年级成绩排序
            Map<Integer, String[]> teamArrayMap = getTeamScoreArrayByScoreList(gradeScoreList);//对每个班进行成绩排名，，，，。班级成绩排序
            page.setPageSize(Integer.MAX_VALUE);
            List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(getStudentScoreByConditon(user, condition), page);
            if (stuScoreList == null) {
                stuScoreList = new ArrayList<StudentScore>();
            }
            Map<Integer, List<StudentScore>> stuIdScoreListMap = getstuIdScoreListMap(stuScoreList);//把各科成绩整合到学生名下

            Set<Integer> set = new HashSet<Integer>();
            set = stuIdScoreListMap.keySet();
            for (int key : set) {

                List<StudentScore> list = stuIdScoreListMap.get(key);
                Student stu = this.studentService.findStudentById(key);
                if (stu == null) {
                    stu = new Student();
                }
                ExamScoreVo vo = new ExamScoreVo();
                vo.setStudentId(key);//学号
                vo.setStudentName(stu.getName());//学生姓名
                vo.setStudentNumber(stu.getStudentNumber());//学籍号
                BigDecimal averageScore = new BigDecimal("0");
                BigDecimal totalScore = calculateTotalScoreFromPerStuListScore(list);//获取一个学生各科成绩之和
                vo.setStuTotal(totalScore.toString());//总成绩
                averageScore = totalScore.divide(new BigDecimal(list.size() + ""), 2, BigDecimal.ROUND_HALF_EVEN);//获取平均分
                vo.setStuAverage(averageScore.toString());//平均分
                vo.setStuGradeRank(getRankInSortArray(vo.getStuTotal(), sortGradeArray) + "");//年级排名
                vo.setStuTeamRank(getRankInSortArray(vo.getStuTotal(), teamArrayMap.get(list.get(0).getTeamId())) + "");//班级排名
                vo.setStudentScoreSort(sortStudentScoreByOne(list));//为每个学生添加按照考试科目完成排序的成绩List
                examScoreVoList.add(vo);
            }


            List<SubjectSort> subjectSortList = getSortSubject(user, condition);//查询学校科目并进行排序
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

            String[] titleFirst = {"学籍号", "姓名 "};
            String[] titleTwo = (String[]) titleList.toArray(new String[titleList.size()]);
            String[] titleThree = {"平均分", "总分", "班级排名", "年级排名"};


            String[] fieldFirst = {"studentNumber", "studentName"};
            String[] fieldTwo = (String[]) fieldList.toArray(new String[fieldList.size()]);
            String[] fieldThree = {"average", "total", "teamRank", "gradeRank"};

            String[] titles = merge(titleFirst, titleTwo, titleThree);
            String[] fieldNames = merge(fieldFirst, fieldTwo, fieldThree);
            config.setTitles(titles);
            config.setFieldNames(fieldNames);
            config.setSheetTitle("班级成绩记录");


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

            String filename = "班级成绩记录.xls";
            try {
                SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }


    }


    /**
     * 获取一个学生各科成绩之和
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
     * 对一个人的学生成绩按照科目进行排序，方便前台进行展示
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
        Collections.sort(studentScoreSortList);//对每个学生的各科成绩进行排序
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
     * 把各科成绩整合到学生名下
     *
     * @param stuScoreList
     * @return
     */
    private Map<Integer, List<StudentScore>> getstuIdScoreListMap(
            List<StudentScore> stuScoreList) {
        Map<Integer, List<StudentScore>> stuIdScoreListMap = new HashMap<Integer, List<StudentScore>>();//Map（学生，成绩list）
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
     * 对每个班进行成绩排名
     *
     * @param gradeScoreList
     * @return
     */
    private Map<Integer, String[]> getTeamScoreArrayByScoreList(
            List<StudentScore> gradeScoreList) {
        Map<Integer, String[]> teamArrayMap = new HashMap<Integer, String[]>();//对每个班级的学生总成绩进行班级排序
        Map<Integer, List<StudentScore>> teamScoreListMap = new HashMap<Integer, List<StudentScore>>();//按照班级分学生考试成绩
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
            Map<Integer, String> teamTotalScoreList = getPerStuTotalScoreMap(list);//把所有的考试成绩汇总为每个学生的总成绩
            String[] sortTeamArray = mapToSortArray(teamTotalScoreList);
            teamArrayMap.put(key, sortTeamArray);
        }
        return teamArrayMap;
    }


    /**
     * 把所有的考试成绩汇总为每个学生的总成绩
     *
     * @param stuScoreList
     * @return
     */
    private Map<Integer, String> getPerStuTotalScoreMap(
            List<StudentScore> stuScoreList) {
        Map<Integer, String> stuIdScoreList = new HashMap<Integer, String>();
        for (StudentScore stuScore : stuScoreList) {//计算每个学生的总成绩
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
     * 把map值转换成排序的数组
     *
     * @param stuIdScoreList
     * @return
     */
    private String[] mapToSortArray(Map<Integer, String> stuIdScoreList) {
        //stuIdScoreList.put(0, "0");
        List<String> list = new ArrayList<String>(stuIdScoreList.values());//把map转换成list
        String[] sortArray = (String[]) list.toArray(new String[list.size()]);//把list转换成array
        String endArray[] = this.Arrays(sortArray);
        //Arrays.sort(sortArray);//对成绩排序
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
     * 获取数值在排序数组中的位置
     *
     * @param stuScoreTotal
     * @param sortArray
     * @return
     */
    private int getRankInSortArray(String stuScoreTotal, String[] sortArray) {
        int rankMiddle = this.binarySearch(sortArray, stuScoreTotal);//找到成绩位置
        if (rankMiddle < 0) {
            return 0;
        }
        while (rankMiddle != sortArray.length - 1//当为最后一个时，数组越界情况
                && sortArray[rankMiddle].equals(sortArray[rankMiddle + 1])//排除二分查找中 12234，2排名第三情况
        ) {
            rankMiddle++;
        }

        int rank = sortArray.length - rankMiddle;
        return rank;
    }

    /**
     * 根据考务，设置一次考试的唯一值
     *
     * @param examTeamSubjectVo
     * @return
     */
    private String getMapKey(ExamTeamSubjectVo examTeamSubjectVo) {
        return examTeamSubjectVo.getSchoolYear() + examTeamSubjectVo.getTerm() + examTeamSubjectVo.getExamType() + examTeamSubjectVo.getExamName();
    }

    /**
     * 查询全年级各个学生各科成绩的查询条件
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
        //后期需要修改表字段，防止出现不一致情况
        //studentScoreCondition.setSchoolId(schoolId);
        //studentScoreCondition.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);
        //studentScoreCondition.setExamType(condition.getExamType());
        return studentScoreCondition;
    }

    /**
     * 获取学校科目并进行排序
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
        //去除重复的
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
            condition.setErrorMessage("查询信息错误");
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
            type = "请选择";
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
            type = "请选择";
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
            type = "请选择";
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

        //int rankMiddle =  Arrays.binarySearch(endArray, "108");//找到成绩位置
        System.out.println(rankMiddle);
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

}
