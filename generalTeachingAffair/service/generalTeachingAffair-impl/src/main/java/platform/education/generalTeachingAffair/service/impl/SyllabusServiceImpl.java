package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.model.Syllabus;
import platform.education.generalTeachingAffair.model.SyllabusLesson;
import platform.education.generalTeachingAffair.vo.SyllabusCondition;
import platform.education.generalTeachingAffair.vo.SyllabusLessonCondition;
import platform.education.generalTeachingAffair.vo.SyllabusVo;
import platform.education.generalTeachingAffair.vo.SyllabusVoCondition;
import platform.education.generalTeachingAffair.service.SyllabusService;
import platform.education.generalTeachingAffair.dao.SyllabusDao;
import platform.education.generalTeachingAffair.dao.SyllabusLessonDao;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.SyllabusRuleFilterChain;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.DataCarrier;
import platform.education.generalTeachingAffair.ext.syllabus.rule.filter.vo.ResponseDataCarrier;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SyllabusServiceImpl implements SyllabusService {

	private Logger log = LoggerFactory.getLogger(getClass());

	private SyllabusDao syllabusDao;

	private SyllabusLessonDao syllabusLessonDao;
	
	@Autowired
	@Qualifier("syllabusRuleFilterChain")
	private SyllabusRuleFilterChain<Syllabus> syllabusRuleFilterChain;

	public void setSyllabusDao(SyllabusDao syllabusDao) {
		this.syllabusDao = syllabusDao;
	}

	public void setSyllabusLessonDao(SyllabusLessonDao syllabusLessonDao) {
		this.syllabusLessonDao = syllabusLessonDao;
	}

	@Override
	public Syllabus findSyllabusById(Integer id) {
		try {
			return syllabusDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} Syllabus 对象", id);
		}
		return null;
	}

	@Override
	public SyllabusLesson findSyllabusLessonById(Integer id) {
		try {
			return syllabusLessonDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} SyllabusLesson 对象", id);
		}
		return null;
	}

	@Override
	public Syllabus adjustClass(Syllabus syllabus) {
		syllabus.setCreateDate(new Date());
		syllabus.setModifyDate(new Date());
		try {
			return syllabusDao.create(syllabus);
		} catch (Exception e) {
			if (log.isInfoEnabled()) {
				log.info("课表添加失败，异常信息为:", e);
			}
		}
		return null;
	}

	@Override
	public Syllabus add(Syllabus syllabus) {
		if (syllabus == null) {
			return null;
		}
		Integer teamId = syllabus.getTeamId();
		String termCode = syllabus.getTermCode();

		if (teamId != null && termCode != null) {
			Syllabus syll = this.syllabusDao.findTeamSyllabus(teamId, termCode);
			if (syll != null) {
				return syll;
			}
			Date createDate = syllabus.getCreateDate();
			if (createDate == null) {
				createDate = new Date();
			}
			syllabus.setCreateDate(createDate);
			syllabus.setModifyDate(createDate);
			try {
				return syllabusDao.create(syllabus);
			} catch (Exception e) {
				if (log.isInfoEnabled()) {
					log.info("课表添加失败，异常信息为:", e);
				}
			}
		}
		return null;
	}

	@Override
	public Syllabus modify(Syllabus syllabus) {
		if (syllabus == null) {
			return null;
		}
		Date modify = syllabus.getModifyDate();
		syllabus.setModifyDate(modify != null ? modify : new Date());
		this.syllabusLessonDao.deleteBySyllabusId(syllabus.getId());
		return syllabusDao.update(syllabus);
	}

	@Override
	public Syllabus findSyllabusByAdjust(Integer teamId, Date applyDate) {
		return syllabusDao.findSyllabusByAdjust(teamId, applyDate);
	}

	@Override
	public void remove(Syllabus syllabus) {
		syllabusDao.delete(syllabus);
	}

	@Override
	public List<Syllabus> findSyllabusByCondition(
			SyllabusCondition syllabusCondition, Page page, Order order) {
		return syllabusDao.findSyllabusByCondition(syllabusCondition, page,
				order);
	}

	@Override
	public List<Syllabus> findSyllabusByCondition(
			SyllabusCondition syllabusCondition) {
		return syllabusDao.findSyllabusByCondition(syllabusCondition, null,
				null);
	}

	@Override
	public List<Syllabus> findSyllabusByCondition(
			SyllabusCondition syllabusCondition, Page page) {
		return syllabusDao.findSyllabusByCondition(syllabusCondition, page,
				null);
	}

	@Override
	public List<Syllabus> findSyllabusByCondition(
			SyllabusCondition syllabusCondition, Order order) {
		return syllabusDao.findSyllabusByCondition(syllabusCondition, null,
				order);
	}

	@Override
	public Long count() {
		return this.syllabusDao.count(null);
	}

	@Override
	public Long count(SyllabusCondition syllabusCondition) {
		return this.syllabusDao.count(syllabusCondition);
	}

	@Override
	public ResponseDataCarrier verifyRules(Syllabus syllabus) {
		DataCarrier<Syllabus> data = new DataCarrier<Syllabus>();
		data.setData(syllabus);
		return syllabusRuleFilterChain.doFilter(data);
	}

	@Override
	public Syllabus getTeamSyllabus(Integer teamId, String termCode) {
		return this.syllabusDao.findTeamSyllabus(teamId, termCode);
	}

	@Override
	public List<SyllabusLesson> getSyllabusLessonBySyllabusId(Integer syllabusId) {
		return this.syllabusLessonDao.findBySyllabusId(syllabusId);
	}

	@Override
	public List<SyllabusLesson> getTeacherSyllabus(Integer teacherId,
			String termCode) {
		return this.syllabusLessonDao.findTeacherSyllabus(teacherId, termCode);
	}

	@Override
	public SyllabusLesson addSyllabusLesson(SyllabusLesson syllabusLesson) {
		if (syllabusLesson == null) {
			return null;
		}
		Date createDate = syllabusLesson.getCreateDate();
		if (createDate == null) {
			createDate = new Date();
		}
		syllabusLesson.setCreateDate(createDate);
		syllabusLesson.setModifyDate(createDate);
		return syllabusLessonDao.create(syllabusLesson);
	}

	@Override
	public SyllabusLesson modifySyllabusLesson(SyllabusLesson syllabusLesson) {
		if (syllabusLesson == null) {
			return null;
		}
		Date modify = syllabusLesson.getModifyDate();
		syllabusLesson.setModifyDate(modify != null ? modify : new Date());
		return syllabusLessonDao.update(syllabusLesson);
	}

	@Override
	public void removeSyllabusLesson(SyllabusLesson syllabusLesson) {
		this.syllabusLessonDao.delete(syllabusLesson);
	}

	@Override
	public List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition, Page page, Order order) {
		return this.syllabusDao.findSyllabusVoByCondition(syllabusCondition, page, order);
	}

	@Override
	public List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition) {
		return this.syllabusDao.findSyllabusVoByCondition(syllabusCondition, null, null);
	}

	@Override
	public List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition, Page page) {
		return this.syllabusDao.findSyllabusVoByCondition(syllabusCondition, page, null);
	}

	@Override
	public List<SyllabusVo> findSyllabusVoByCondition(SyllabusVoCondition syllabusCondition, Order order) {
		return this.syllabusDao.findSyllabusVoByCondition(syllabusCondition, null, order);
	}

	@Override
	public Long findSyllabusOfCount() {
		return this.syllabusDao.findSyllabusOfCount(null);
	}

	@Override
	public Long findSyllabusOfCount(SyllabusVoCondition condition) {
		return this.syllabusDao.findSyllabusOfCount(condition);
	}

	@Override
	public Integer removeSyllabusLessonBySyllabusId(Integer syllabusId) {
		return this.syllabusLessonDao.deleteBySyllabusId(syllabusId);
	}

	/**
	 * @function 根据条件查询详细课表的情况  只查syllabus_lesson表
	 * @date 2016-3-9
	 * 
	 */
	@Override
	public List<SyllabusLesson> findSyllabusLessonByCondition(SyllabusLessonCondition syllabusLessonCondition,Page page,Order order){
		return syllabusLessonDao.findSyllabusLessonByCondition(syllabusLessonCondition, page, order);
	}

	/**
	 * @function 客户端使用的课表导入功能
	 * @date 2016-3-10
	 */
	@Override
	public Syllabus importSyllabus(Syllabus syllabus,
			List<SyllabusLesson> syllabusLessonList) {
		Syllabus sy = null;
		if(syllabus == null){
			return sy;
		}
		//表示新增课程表
		if(syllabus.getId().equals(0) || syllabus.getId() == 0){
			syllabus.setId(null);
			sy = syllabusDao.create(syllabus);
			if(syllabusLessonList != null && syllabusLessonList.size() > 0){
				for(SyllabusLesson syllabusLesson : syllabusLessonList){
					syllabusLesson.setSyllabusId(sy.getId());
					syllabusLessonDao.create(syllabusLesson);
				}
			}
		}else{
		//表示修改课程表
			sy = syllabusDao.findById(syllabus.getId());
			if(sy == null){
				return sy;
			}else{
				sy = syllabusDao.update(syllabus);
				List<SyllabusLesson> list = syllabusLessonDao.findBySyllabusId(sy.getId());
				if(list != null && list.size() > 0){
					for(SyllabusLesson syl : list){
						syllabusLessonDao.delete(syl);
					}
				}
				if(syllabusLessonList != null && syllabusLessonList.size() > 0){
					for(SyllabusLesson syllabusLesson : syllabusLessonList){
						syllabusLesson.setSyllabusId(sy.getId());
						syllabusLessonDao.create(syllabusLesson);
					}
				}
			}
		}
		return sy;
	}

	@Override
	public void removeByTeacher(Integer teacherid) {
		Date date = new Date();
		SyllabusLessonCondition condtion = new SyllabusLessonCondition();
		condtion.setTeacherId(teacherid);
		List<SyllabusLesson> result = syllabusLessonDao.findSyllabusLessonByCondition(condtion, null, null);
		for (SyllabusLesson syllabusLesson : result) {
			syllabusLesson.setIsDeleted(true);
			syllabusLesson.setModifyDate(date);
			syllabusLessonDao.update(syllabusLesson);
		}
		
	}
	

}
