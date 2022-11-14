package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ExamWorksTeamSubjectDao;
import platform.education.generalTeachingAffair.model.ExamWorksTeamSubject;
import platform.education.generalTeachingAffair.service.ExamWorksTeamSubjectService;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExamWorksTeamSubjectServiceImpl implements ExamWorksTeamSubjectService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ExamWorksTeamSubjectDao examWorksTeamSubjectDao;

	public void setExamWorksTeamSubjectDao(ExamWorksTeamSubjectDao examWorksTeamSubjectDao) {
		this.examWorksTeamSubjectDao = examWorksTeamSubjectDao;
	}
	
	@Override
	public ExamWorksTeamSubject findExamWorksTeamSubjectById(Integer id) {
		try {
			return examWorksTeamSubjectDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ExamWorksTeamSubject add(ExamWorksTeamSubject examWorksTeamSubject) {
		if(examWorksTeamSubject == null) {
    		return null;
    	}
    	Date createDate = examWorksTeamSubject.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	examWorksTeamSubject.setCreateDate(createDate);
    	examWorksTeamSubject.setModifyDate(createDate);
		return examWorksTeamSubjectDao.create(examWorksTeamSubject);
	}

	@Override
	public ExamWorksTeamSubject modify(ExamWorksTeamSubject examWorksTeamSubject) {
		if(examWorksTeamSubject == null) {
    		return null;
    	}
    	Date modify = examWorksTeamSubject.getModifyDate();
    	examWorksTeamSubject.setModifyDate(modify != null ? modify : new Date());
		return examWorksTeamSubjectDao.update(examWorksTeamSubject);
	}
	
	@Override
	public void remove(ExamWorksTeamSubject examWorksTeamSubject) {
		try {
			examWorksTeamSubjectDao.delete(examWorksTeamSubject);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", examWorksTeamSubject.getId(), e);
			}
		}
	}
	
	@Override
	public List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Page page, Order order) {
		return examWorksTeamSubjectDao.findExamWorksTeamSubjectByCondition(examWorksTeamSubjectCondition, page, order);
	}
	
	@Override
	public List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition) {
		return examWorksTeamSubjectDao.findExamWorksTeamSubjectByCondition(examWorksTeamSubjectCondition, null, null);
	}
	
	@Override
	public List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Page page) {
		return examWorksTeamSubjectDao.findExamWorksTeamSubjectByCondition(examWorksTeamSubjectCondition, page, null);
	}
	
	@Override
	public List<ExamWorksTeamSubject> findExamWorksTeamSubjectByCondition(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition, Order order) {
		return examWorksTeamSubjectDao.findExamWorksTeamSubjectByCondition(examWorksTeamSubjectCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.examWorksTeamSubjectDao.count(null);
	}

	@Override
	public Long count(ExamWorksTeamSubjectCondition examWorksTeamSubjectCondition) {
		return this.examWorksTeamSubjectDao.count(examWorksTeamSubjectCondition);
	}


	@Override
	public List<Map<String, Object>> findExamWorksBySubjectCode(Integer teamId, String examType, Integer userId,
			String subjectCode) {
		
		return examWorksTeamSubjectDao.findExamWorksBySubjectCode(teamId, examType, userId, subjectCode);
	}

	@Override
	public ExamWorksTeamSubject findUnique(Integer examWorksId, Integer teamId, String subjectCode) {
		return this.examWorksTeamSubjectDao.findUnique(examWorksId, teamId, subjectCode);
	}

	@Override
	public List<ExamWorksTeamSubjectVo> findExamWorksContext(Integer examWorksId) {
		return this.examWorksTeamSubjectDao.findExamWorksContext(examWorksId);
	}

	@Override
	public void createBatch(ExamWorksTeamSubject[] examWorksTeamSubjects) {
		if (examWorksTeamSubjects.length > 0) {
			this.examWorksTeamSubjectDao.createBatch(examWorksTeamSubjects);
		}
	}


	@Override
	public List<Map<String, Object>> findGradeRankByGradeId(Integer examWorksId, Integer gradeId) {
		return examWorksTeamSubjectDao.findGradeRankByGradeId(examWorksId,gradeId);
	}

	@Override
	public Float findGradeTotalScoreBySubjectCode(String jointExamCode, String subjectCode) {
		return examWorksTeamSubjectDao.findGradeTotalScoreBySubjectCode(jointExamCode, subjectCode, null);
	}

	@Override
	public List<Map<String, Object>> findGradeRankBySubjectCode(Integer gradeId, String subjectCode,
			Integer examWorksId) {
		return examWorksTeamSubjectDao.findGradeRankBySubjectCode(gradeId, subjectCode, examWorksId);
	}

	@Override
	public List<Map<String, Object>> findMajorExamWorksByTeam(Integer examWorksId, Integer teamId) {
		return this.examWorksTeamSubjectDao.findMajorExamWorksByTeam(examWorksId, teamId, null, true);
	}

	@Override
	public Map<String, Object> findMajorExamWorksByTeam(Integer examWorksId, Integer teamId, Integer examWorksTeamSubjectId) {
		List<Map<String, Object>> list = this.examWorksTeamSubjectDao.findMajorExamWorksByTeam(examWorksId, teamId, examWorksTeamSubjectId, null);
		Map<String, Object> map = null;
		if (list != null && list.size() > 0) {
			map = list.get(0);
		}
		return map;
	}

	@Override
	public Integer findGradeStudentCountBySubjectCode(String jointExamCode, String subjectCode) {
		
		return examWorksTeamSubjectDao.findGradeStudentCountBySubjectCode(jointExamCode, subjectCode, null);
	}

	@Override
	public List<Map<String, Object>> findClassExamWorksByTeam(Integer schoolId, String schoolYear, String termCode, Integer teacherId, Integer teamId, Page page, Order order) {
		return this.examWorksTeamSubjectDao.findClassExamWorksByTeam(schoolId, schoolYear, termCode, teacherId, teamId, null, page, order);
	}

	@Override
	public Float findGradeMaxScore(String jointExamCode, String subjectCode) {
		return examWorksTeamSubjectDao.findGradeMaxScore(jointExamCode, subjectCode);
	}

	@Override
	public List<Map<String, Object>> findScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId, String subjectCode) {
		return this.examWorksTeamSubjectDao.findScoreOfStudent(examWorksId, gradeId, teamId, subjectCode, null, null);
	}

	@Override
	public List<Map<String, Object>> findScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId, String subjectCode, Page page, Order order) {
		return this.examWorksTeamSubjectDao.findScoreOfStudent(examWorksId, gradeId, teamId, subjectCode, page, order);
	}

	@Override
	public List<Map<String, Object>> findTotalScoreOfStudent(Integer examWorksId, Integer gradeId, Integer teamId) {
		return this.examWorksTeamSubjectDao.findScoreOfStudent(examWorksId, gradeId, teamId, null, null, null);
	}

	@Override
	public List<Map<String, Object>> findAvgScoreOfGrade(Integer schoolId, Boolean isJoint, Integer gradeId, Integer teamId, String subjectCode) {
		return this.examWorksTeamSubjectDao.findAvgScoreOfGrade(schoolId, isJoint, gradeId, teamId, subjectCode);
	}

	@Override
	public Float findTeamTotalScoreBySubjectCode(String jointExamCode, String subjectCode, Integer teamId) {
		return this.examWorksTeamSubjectDao.findGradeTotalScoreBySubjectCode(jointExamCode, subjectCode, teamId);
	}

	@Override
	public Integer findTeamStudentCountBySubjectCode(String jointExamCode, String subjectCode, Integer teamId) {
		return this.examWorksTeamSubjectDao.findGradeStudentCountBySubjectCode(jointExamCode, subjectCode, teamId);
	}

	@Override
	public List<Map<String, Object>> findAvgScoreOfStudent(Integer userId, Integer teamId, String subjectCode, Boolean isEnter) {
		return this.examWorksTeamSubjectDao.findAvgScoreOfStudent(userId, teamId, subjectCode, isEnter);
	}

	@Override
	public List<Map<String, Object>> findExamWorkStudentRankAndTotalScore(Integer examWorksId, Integer gradeId) {
		if(examWorksId==null || gradeId==null) {
			return new ArrayList<Map<String, Object>>();
		}
		return examWorksTeamSubjectDao.findExamWorkStudentRankAndTotalScore(examWorksId, gradeId);
	}
	
	@Override
	public List<Integer> findExamWorkStudentRank(Integer examWorksId, Integer gradeId) {
		if(examWorksId==null || gradeId==null) {
			return new ArrayList<Integer>();
		}
		return examWorksTeamSubjectDao.findExamWorkStudentRank(examWorksId, gradeId);
	}

	@Override
	public List<Map<String, Object>> findStudentScore(Integer examWorksId, Integer gradeId, Integer studentId) {
		if(examWorksId==null || gradeId==null || studentId==null) {
			return null;
		}
		return examWorksTeamSubjectDao.findStudentScore(examWorksId, gradeId, studentId);
	}
}
