package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ExamStudentDao;
import platform.education.generalTeachingAffair.model.ExamStat;
import platform.education.generalTeachingAffair.model.ExamStudent;
import platform.education.generalTeachingAffair.model.PjExam;
import platform.education.generalTeachingAffair.service.ExamStatService;
import platform.education.generalTeachingAffair.service.ExamStudentService;
import platform.education.generalTeachingAffair.utils.DateUtil;
import platform.education.generalTeachingAffair.utils.StatisticsUtil;
import platform.education.generalTeachingAffair.vo.CommonScoreRank;
import platform.education.generalTeachingAffair.vo.ExamStudentCondition;
import platform.education.generalTeachingAffair.vo.ExamStudentVo;

import java.util.*;

public class ExamStudentServiceImpl implements ExamStudentService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private ExamStudentDao examStudentDao;

    /**
     * 班级学生考试统计
     */
    private ExamStatService examStatService;


    public void setExamStudentDao(ExamStudentDao examStudentDao) {
        this.examStudentDao = examStudentDao;
    }

    public void setExamStatService(ExamStatService examStatService) {
        this.examStatService = examStatService;
    }

    @Override
    public ExamStudent findExamStudentById(Integer id) {
        try {
            return examStudentDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public ExamStudent add(ExamStudent examStudent) {
        if (examStudent == null) {
            return null;
        }
        Date createDate = examStudent.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        examStudent.setCreateDate(createDate);
        examStudent.setModifyDate(createDate);
        return examStudentDao.create(examStudent);
    }

    @Override
    public ExamStudent modify(ExamStudent examStudent) {
        if (examStudent == null) {
            return null;
        }
        Date modify = examStudent.getModifyDate();
        examStudent.setModifyDate(modify != null ? modify : new Date());
        return examStudentDao.update(examStudent);
    }

    @Override
    public void remove(ExamStudent examStudent) {
        try {
            examStudentDao.delete(examStudent);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", examStudent.getId(), e);
            }
        }
    }

    @Override
    public List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Page page, Order order) {
        return examStudentDao.findExamStudentByCondition(examStudentCondition, page, order);
    }

    @Override
    public List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition) {
        return examStudentDao.findExamStudentByCondition(examStudentCondition, null, null);
    }

    @Override
    public List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Page page) {
        return examStudentDao.findExamStudentByCondition(examStudentCondition, page, null);
    }

    @Override
    public List<ExamStudent> findExamStudentByCondition(ExamStudentCondition examStudentCondition, Order order) {
        return examStudentDao.findExamStudentByCondition(examStudentCondition, null, order);
    }

    @Override
    public Long count() {
        return this.examStudentDao.count(null);
    }

    @Override
    public Long count(ExamStudentCondition examStudentCondition) {
        return this.examStudentDao.count(examStudentCondition);
    }

    /**
     * 功能描述：根据examId查询出与其相关的所有学生成绩名单
     * 2016-01-07
     *
     * @param examId
     * @return
     */
    @Override
    public List<ExamStudent> findExamStudentsByExamId(Integer examId) {
        return this.examStudentDao.findExamStudentsByExamId(examId);
    }

    /**
     * 功能描述：根据examId查询出与其相关的所有学生成绩名单
     * 2016-01-07
     *
     * @param SchoolYear
     * @param termValue
     * @param teamId
     * @param subjectCode
     * @return
     */
    @Override
    public List<ExamStudent> findExamStudents(String SchoolYear,
                                              String termValue, Integer teamId, String subjectCode) {
        return this.examStudentDao.findExamStudents(SchoolYear, termValue, teamId, subjectCode);
    }

    /**
     * 功能描述：根据examId查询并清空所有学生的成绩记录，更改pj_exam_stat对应的实际应试人数 student_count = 0
     * 2016-01-07
     *
     * @param examId
     */
    @Override
    public void clearExamScore(Integer examId) {
        List<ExamStudent> examStudentList = this.examStudentDao.findExamStudentsByExamId(examId);
        for (ExamStudent examStudent : examStudentList) {
            examStudent.setScore(-1F);
            examStudent.setDegree(null);
            examStudent.setTeamRank(0);
            examStudent.setGradeRank(0);
            examStudent.setSourceScore("0");
            examStudent.setAverageScore(0.00F);
            examStudent.setAnswerCount(0);
            examStudent.setRightAnswerCount(0);
            examStudent.setModifyDate(new Date());
            examStudent = this.examStudentDao.update(examStudent);
        }
        ExamStat examStat = examStatService.findExamStatByExamId(examId);
        if (examStat != null) {
            examStat.setStudentCount(0);
            examStat.setDataChanged(true);
            examStat = examStatService.modify(examStat);
        }
    }

    /**
     * 功能描述：删除某一科成绩后传入参数（examId）查询pj_exam_student表中与其相关记录并删除
     * 2016-01-07
     */
    @Override
    public void removeExamStudents(Integer examId) {
//		List<ExamStudent> examStudentList = this.examStudentDao.findExamStudentsByExamId(examId);
//		for(ExamStudent examStudent : examStudentList) {
//			examStudentDao.delete(examStudent);
//		}
        examStudentDao.deleteByExamId(examId);
    }

    @Override
    public void addExamStudents(List<ExamStudent> examStudentList) {
        Integer examId = null;
        ExamStudent examStu = null;
        ExamStudentCondition examStudentCondition = null;
        try {
            Integer studentCount = 0;
            if (examStudentList != null) {
                examId = examStudentList.get(0).getExamId();
//				PjExam exam = pjExamService.findPjExamById(examId);
                for (ExamStudent examStudent : examStudentList) {
                    examStudentCondition = new ExamStudentCondition();
                    examStudentCondition.setExamId(examId);
                    //这些条件不设置  有默认值  导致查询不出数据  保存失败
                    examStudentCondition.setTeamRank(null);
                    examStudentCondition.setGradeRank(null);
                    examStudentCondition.setSourceScore(null);
                    examStudentCondition.setAverageScore(null);
                    examStudentCondition.setAnswerCount(null);
                    examStudentCondition.setRightAnswerCount(null);
                    ///=======
                    examStudentCondition.setStudentId(examStudent.getStudentId());
                    List<ExamStudent> list = examStudentDao.findExamStudentByCondition(examStudentCondition, null, null);
                    if (list.size() > 0) {
                        examStu = list.get(0);
                        if (examStudent.getScore() != null) {
                            examStu.setScore(examStudent.getScore());
                            examStu.setTestType(examStudent.getTestType());
                            examStu.setModifyDate(new Date());
                            if (examStu.getSourceScore() == "0" || examStu.getSourceScore() == null) {
                                examStu.setSourceScore(examStudent.getScore() + "");
                            } else {
                                examStu.setSourceScore(examStu.getSourceScore() + "," + examStudent.getScore());
                            }
                            examStudentDao.update(examStu);
                            if (examStu.getTestType() != "03") {
                                studentCount++;
                            }
                        }
                    }
//					初始化 还没有该学生记录  导入时  新加学生记录  导致该新生的成绩到不进去  去掉注释即可
//					else{
//						Student student = studentService.findStudentById(examStudent.getStudentId());
//						if(student != null){
//							if(student.getTeamId() != null){
//								if(student.getTeamId().equals(exam.getTeamId())){
//									ExamStudent es = new ExamStudent();
//									
//									TeamStudent teamStudent = teamStudentService.findUnique(exam.getTeamId(), examStudent.getStudentId());
//									if(teamStudent != null) {
//										es.setNumber(teamStudent.getNumber());        //学生班内编号（顺序编号）
//									}
//									
//									es.setExamId(examId);
//									es.setStudentId(student.getId());
//									es.setUserId(student.getUserId());
//									es.setName(student.getName());   //姓名（如果同班有同名用别名）
//									es.setTestType(examStudent.getTestType());   //01--正常考试
//									es.setScore(examStudent.getScore());    //学生考试分数
//									es.setCreateDate(new Date());
//									es.setModifyDate(new Date());
//									es = examStudentDao.create(es);
//								}
//							}
//						}
//					}
                }

                ExamStat examStat = examStatService.findExamStatByExamId(examId);
                examStat.setDataChanged(true);
                examStat.setStudentCount(studentCount);
                examStatService.modify(examStat);

            }
        } catch (Exception e) {
            log.debug("添加成绩失败");
        }
    }

    @Override
    public Long countTeamTotalScore(Integer examId) {
        return this.examStudentDao.countTeamTotalScore(examId);
    }

    @Override
    public Long countTotalStudent(Integer examId) {
        return this.examStudentDao.countTotalStudent(examId);
    }

    @Override
    public CommonScoreRank countScoreRate(Integer examId, Float fullScore, Float highScore, Float lowScore,
                                          Float passScore) {
        return this.examStudentDao.countScoreRate(examId, fullScore, highScore, lowScore, passScore);
    }

    @Override
    public void updateTeamRank(Integer examId) {
        this.examStudentDao.updateTeamRank(examId);
    }

    @Override
    public void updateGradeRank(Integer[] ids) {
        this.examStudentDao.updateGradeRank(ids);

    }

    @Override
    public List<ExamStudent> findExamStudentsByExamId(Integer examId, Order order) {
        return this.examStudentDao.findExamStudentsByExamId(examId, order);
    }

    /* (非 Javadoc)
     * <p>Title: findExamStudentByExamIdAndUserId</p>
     * <p>Description: </p>
     * @param examId
     * @param userId
     * @return
     * @see platform.education.generalTeachingAffair.service.ExamStudentService#findExamStudentByExamIdAndUserId(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public ExamStudent findExamStudentByExamIdAndUserId(Integer examId, Integer userId) {
        return examStudentDao.findExamStudentByExamIdAndUserId(examId, userId);
    }

    /* (非 Javadoc)
     * <p>Title: updateExamStudents</p>
     * <p>Description: </p>
     * @param paperUuid
     * @param ownerId
     * @param type
     * @param examId
     * @see platform.education.generalTeachingAffair.service.ExamStudentService#updateExamStudents(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public void updateExamStudents(String paperUuid, Integer ownerId, Integer type, Integer examId) {
        this.examStudentDao.updateExamStudents(paperUuid, ownerId, type, examId);
    }

    /* (非 Javadoc)
     * <p>Title: updateExamStudentAnswerCount</p>
     * <p>Description: </p>
     * @param paperUuid
     * @param ownerId
     * @param type
     * @param examId
     * @see platform.education.generalTeachingAffair.service.ExamStudentService#updateExamStudentAnswerCount(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @Override
    public void updateExamStudentAnswerCount(String paperUuid, Integer ownerId, Integer type, Boolean isCorrect, Integer examId) {
        this.examStudentDao.updateExamStudentAnswerCount(paperUuid, ownerId, type, isCorrect, examId);
    }

    @Override
    public Float findExamStudentHighestScoreByExamId(Integer examId) {

        return examStudentDao.findExamStudentHighestScoreByExamId(examId);
    }

    @Override
    public Float findExamStudentLowestScoreByExamId(Integer examId) {
        return examStudentDao.findExamStudentLowestScoreByExamId(examId);
    }

    @Override
    public void batchUpdateExamStudents(Object[] list) {
        examStudentDao.batchUpdateExamStudents(list);

    }

    @Override
    public void batchUpdateTeamRank(Object[] list) {
        examStudentDao.batchUpdateTeamRank(list);

    }

    @Override
    public void batchUpdateExamStudentCorrectAnswerCount(Object[] list) {
        examStudentDao.batchUpdateExamStudentCorrectAnswerCount(list);

    }

    @Override
    public void batchUpdateExamStudentAnswerCount(Object[] list) {
        examStudentDao.batchUpdateExamStudentAnswerCount(list);

    }

    @Override
    public List<Map<String, Object>> findExamStudentTeamRank(Integer examId) {

        return examStudentDao.findExamStudentTeamRank(examId);
    }

    @Override
    public void batchUpdateExamStudent(List<Map<String, Object>> examStudentsList, List<Map<String, Object>> examStudentTeamRankList,
                                       List<Map<String, Object>> examStudentCorrectAnswerCountList, List<Map<String, Object>> examStudentAnswerCountList) {

        if (examStudentsList != null && examStudentsList.size() > 0) {
            long startTime1 = System.currentTimeMillis();
            for (int i = 0, len = examStudentsList.size(); i < len; i++) {
                Map<String, Object> map = examStudentsList.get(i);

                if (map != null && map.size() > 0) {
                    if (map.get("userId") != null && map.get("examId") != null && map.get("score") != null) {
                        Integer userId = Integer.parseInt(map.get("userId") + "");
                        Float score = Float.parseFloat(map.get("score") + "");
                        Integer examId = Integer.parseInt(map.get("examId") + "");
                        ExamStudentCondition examStudentCondition = new ExamStudentCondition();
                        examStudentCondition.setExamId(examId);
                        examStudentCondition.setUserId(userId);
                        List<ExamStudent> examStudentList = examStudentDao.findExamStudentByCondition(examStudentCondition, null, null);
                        if (examStudentList != null && examStudentList.size() > 0) {
                            ExamStudent examStudent = examStudentList.get(0);
                            examStudent.setScore(score);
                            examStudentDao.update(examStudent);
                        }

                    }
                }

            }

            long endTime1 = System.currentTimeMillis();
            System.out.println("examStudentsList程序运行时间： " + ((endTime1 - startTime1)) + "毫秒");
        }


        if (examStudentTeamRankList != null && examStudentTeamRankList.size() > 0) {
            long startTime2 = System.currentTimeMillis();
            for (int i = 0, len = examStudentTeamRankList.size(); i < len; i++) {
                Map<String, Object> map = examStudentTeamRankList.get(i);

                if (map != null && map.size() > 0) {
                    if (map.get("userId") != null && map.get("examId") != null && map.get("rank") != null) {

                        Integer userId = Integer.parseInt(map.get("userId") + "");
                        Integer teamRank = Integer.parseInt(map.get("rank") + "");
                        Integer examId = Integer.parseInt(map.get("examId") + "");
                        ExamStudentCondition examStudentCondition = new ExamStudentCondition();
                        examStudentCondition.setExamId(examId);
                        examStudentCondition.setUserId(userId);
                        List<ExamStudent> examStudentList = examStudentDao.findExamStudentByCondition(examStudentCondition, null, null);
                        if (examStudentList != null && examStudentList.size() > 0) {
                            ExamStudent examStudent = examStudentList.get(0);
                            examStudent.setTeamRank(teamRank);
                            examStudentDao.update(examStudent);
                        }

                    }
                }

            }
            long endTime2 = System.currentTimeMillis();
            System.out.println("examStudentTeamRankList程序运行时间： " + ((endTime2 - startTime2)) + "毫秒");

        }


        if (examStudentCorrectAnswerCountList != null && examStudentCorrectAnswerCountList.size() > 0) {
            long startTime3 = System.currentTimeMillis();
            for (int i = 0, len = examStudentCorrectAnswerCountList.size(); i < len; i++) {
                Map<String, Object> map = examStudentCorrectAnswerCountList.get(i);

                if (map != null && map.size() > 0) {
                    if (map.get("userId") != null && map.get("examId") != null && map.get("answerCount") != null) {
                        Integer userId = Integer.parseInt(map.get("userId") + "");
                        Integer rightAnswerCount = Integer.parseInt(map.get("answerCount") + "");
                        Integer examId = Integer.parseInt(map.get("examId") + "");

                        ExamStudentCondition examStudentCondition = new ExamStudentCondition();
                        examStudentCondition.setExamId(examId);
                        examStudentCondition.setUserId(userId);
                        List<ExamStudent> examStudentList = examStudentDao.findExamStudentByCondition(examStudentCondition, null, null);
                        if (examStudentList != null && examStudentList.size() > 0) {
                            ExamStudent examStudent = examStudentList.get(0);
                            examStudent.setRightAnswerCount(rightAnswerCount);
                            examStudentDao.update(examStudent);
                        }

                    }
                }

            }
            long endTime3 = System.currentTimeMillis();
            System.out.println("examStudentCorrectAnswerCountList程序运行时间： " + ((endTime3 - startTime3)) + "毫秒");
        }

        if (examStudentAnswerCountList != null && examStudentAnswerCountList.size() > 0) {

            long startTime4 = System.currentTimeMillis();
            for (int i = 0, len = examStudentAnswerCountList.size(); i < len; i++) {
                Map<String, Object> map = examStudentAnswerCountList.get(i);

                if (map != null && map.size() > 0) {
                    if (map.get("userId") != null && map.get("examId") != null && map.get("answerCount") != null) {

                        Integer userId = Integer.parseInt(map.get("userId") + "");
                        Integer answerCount = Integer.parseInt(map.get("answerCount") + "");
                        Integer examId = Integer.parseInt(map.get("examId") + "");

                        ExamStudentCondition examStudentCondition = new ExamStudentCondition();
                        examStudentCondition.setExamId(examId);
                        examStudentCondition.setUserId(userId);
                        List<ExamStudent> examStudentList = examStudentDao.findExamStudentByCondition(examStudentCondition, null, null);
                        if (examStudentList != null && examStudentList.size() > 0) {
                            ExamStudent examStudent = examStudentList.get(0);
                            examStudent.setAnswerCount(answerCount);
                            examStudentDao.update(examStudent);
                        }

                    }
                }

            }
            long endTime4 = System.currentTimeMillis();
            System.out.println("examStudentAnswerCountList程序运行时间： " + ((endTime4 - startTime4)) + "毫秒");
        }

    }

    @Override
    public void batchUpdateExamStudentScore(Object[] list) {
        if (list != null && list.length > 0) {
            examStudentDao.batchUpdateExamStudentScore(list);
        }

    }

    @Override
    public void batchUpdateExamStudentTeamRank(List<PjExam> pjExamList) {
        if (pjExamList != null && pjExamList.size() > 0) {
            //	examStudentDao.batchUpdateExamStudentTeamRank(examIdObj);
        }
    }

    @Override
    public void batchUpdateExamStudentCorrectAnswerCountNew(List<PjExam> pjExamList, String paperUuid, Integer ownerId,
                                                            Integer paperType, Integer correct) {
        if (pjExamList != null && pjExamList.size() > 0) {
            //examStudentDao.batchUpdateExamStudentCorrectAnswerCountNew(examIdObj, paperUuid, ownerId, paperType, correct);
        }
    }

	/*@Override
	public void batchUpdateExamStudentAnswerCountNew(Object[] list) {
		if(list != null && list.length > 0) {
		//examStudentDao.batchUpdateExamStudentAnswerCountNew(examIdObj, paperUuid, ownerId, paperType);
		}
	}
*/
	/*@Override
	public void batchUpdateExamStudentGradeRank(List<PjExam> pjExamList) {
		if(examIdObj != null && examIdObj.length > 0) {
		examStudentDao.batchUpdateExamStudentGradeRank(examIdObj);
		}
	}*/

    @Override
    public Map<Integer, Long> countTotalStudentByExamIds(Object[] examIdObj) {
        Map<Integer, Long> map = null;
        if (examIdObj != null && examIdObj.length > 0) {
            int len = examIdObj.length;
            map = new HashMap<Integer, Long>();
            for (int i = 0; i < len; i++) {
                int examId = (Integer) examIdObj[i];
                Long sumStudent = examStudentDao.countTotalStudent(examId);
                map.put(examId, sumStudent);
            }
        }


        return map;
    }


    @Override

    public Map<Integer, List<ExamStudent>> findExamStudentByExamIdObj(Object[] examIdObj) {
        Map<Integer, List<ExamStudent>> mp = null;
        if (examIdObj != null && examIdObj.length > 0) {
            int len = examIdObj.length;
            if (examIdObj != null && len > 0) {
                mp = new HashMap<Integer, List<ExamStudent>>();
                for (int i = 0; i < len; i++) {
                    Integer examId = (Integer) examIdObj[i];
                    List<ExamStudent> examStudentList = examStudentDao.findExamStudentsByExamId(examId);
                    mp.put(examId, examStudentList);
                }

            }
        }

        return mp;
    }

    @Override
    public Map<Integer, Float> findExamStudentHighestScoreByExamIdObj(Object[] examIdObj) {
        Map<Integer, Float> mp = null;
        if (examIdObj != null && examIdObj.length > 0) {

            int len = examIdObj.length;
            mp = new HashMap<Integer, Float>();
            for (int i = 0; i < len; i++) {
                Integer examId = (Integer) examIdObj[i];
                Float highestScore = examStudentDao.findExamStudentHighestScoreByExamId(examId);
                mp.put(examId, highestScore);
            }

        }

        return mp;
    }

    @Override
    public Map<Integer, Float> findExamStudentLowestScoreByExamIdObj(Object[] examIdObj) {

        Map<Integer, Float> mp = null;
        if (examIdObj != null && examIdObj.length > 0) {

            int len = examIdObj.length;
            mp = new HashMap<Integer, Float>();
            for (int i = 0; i < len; i++) {
                Integer examId = (Integer) examIdObj[i];
                Float lowestScore = examStudentDao.findExamStudentLowestScoreByExamId(examId);
                mp.put(examId, lowestScore);
            }
        }


        return mp;
    }

    @Override
    public void createBatch(ExamStudent[] eslist) {
        if (eslist.length > 0) {
            examStudentDao.createBatch(eslist);
        }

    }

    @Override
    public List<ExamStudent> findExamStudentFinishByExamIds(Integer[] examIds) {
        return examStudentDao.findExamStudentFinishByExamIds(examIds);
    }

    @Override
    public void batchUpdateExamStudentGradeRankByScore(Object[] list) {
        examStudentDao.batchUpdateExamStudentGradeRankByScore(list);
    }

    @Override
    public List<ExamStudent> findExamStudentByJointExamCode(String jointExamCode, String subjectCode) {
        return examStudentDao.findExamStudentByJointExamCode(jointExamCode, subjectCode);
    }

    @Override
    public Integer findGradeStudentCountByGradeId(Integer gradeId) {
        return examStudentDao.findGradeStudentCountByGradeId(gradeId);
    }

    @Override
    public List<ExamStudent> findGradeRankByScoreJointCode(float score, String jointExamCode, String subjectCode) {
        return examStudentDao.findGradeRankByScoreJointCode(score, jointExamCode, subjectCode);
    }

    @Override
    public List<ExamStudentVo> findExamStudentVoByExamIds(Integer[] examIds) {
        List<ExamStudentVo> examStudentVoByExamIds = examStudentDao.findExamStudentVoByExamIds(examIds);
        for (ExamStudentVo examStudentVo : examStudentVoByExamIds) {
            // 插入完成所用时间
            if (examStudentVo.getIsFinished()) {
                String time = DateUtil.secondsToTime(examStudentVo.getTotalTime());
                examStudentVo.setTimeStr(time);
            }
            String score = StatisticsUtil.subZeroAndDot(examStudentVo.getSumScore());
            examStudentVo.setSumScore(score);
        }
        // 根据分数对列表进行降序排序
        Collections.sort(examStudentVoByExamIds, new Comparator<ExamStudentVo>() {
            @Override
            public int compare(ExamStudentVo o1, ExamStudentVo o2) {
                float n1 = Float.parseFloat(o1.getSumScore());
                float n2 = Float.parseFloat(o2.getSumScore());
                if (n1 < n2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return examStudentVoByExamIds;
    }

    @Override
    public List<ExamStudentVo> findExamStudentVoByExamIdsWithType(Integer[] examIds, Integer groupByType) {
        return examStudentDao.findExamStudentVoByExamIdsWithType(examIds, groupByType);
    }
}
