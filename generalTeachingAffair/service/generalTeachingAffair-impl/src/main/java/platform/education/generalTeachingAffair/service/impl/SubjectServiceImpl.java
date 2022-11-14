package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.SubjectDao;
import platform.education.generalTeachingAffair.dao.SubjectGradeDao;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.model.SubjectGrade;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.service.SchoolSystemService;
import platform.education.generalTeachingAffair.service.SubjectGradeService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.vo.SubjectCondition;
import platform.education.generalTeachingAffair.vo.SubjectGradeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.SubjectVo;

public class SubjectServiceImpl implements SubjectService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SubjectDao subjectDao;
	
	private SubjectGradeDao subjectGradeDao;

	private SchoolService schoolService;

	private SchoolSystemService schoolSystemService;

	private SubjectGradeService subjectGradeService;

	public void setSubjectGradeService(SubjectGradeService subjectGradeService) {
		this.subjectGradeService = subjectGradeService;
	}

	private platform.education.generalcode.service.SubjectService jcSubjectService;

	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	public void setSchoolSystemService(SchoolSystemService schoolSystemService) {
		this.schoolSystemService = schoolSystemService;
	}

	public void setJcSubjectService(platform.education.generalcode.service.SubjectService jcSubjectService) {
		this.jcSubjectService = jcSubjectService;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public void setSubjectGradeDao(SubjectGradeDao subjectGradeDao) {
		this.subjectGradeDao = subjectGradeDao;
	}

	@Override
	public Subject findSubjectById(Integer id) {
		try {
			return subjectDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Subject add(Subject subject) {
		return subjectDao.create(subject);
	}

	@Override
	public Subject modify(Subject subject) {
		try {
			Subject sub = subjectDao.findById(subject.getId());
			SubjectGradeCondition subjectGradeCondition = new SubjectGradeCondition();
			subjectGradeCondition.setDelete(false);
			subjectGradeCondition.setSchoolId(sub.getSchoolId());
			subjectGradeCondition.setSubjectCode(sub.getCode());
			List<SubjectGrade> subjectGradeList = subjectGradeDao.findSubjectGradeByCondition(subjectGradeCondition, null, null);
			if(subjectGradeList != null && subjectGradeList.size() > 0){
				for(SubjectGrade subjectGrade : subjectGradeList){
					if(subjectGrade != null && subjectGrade.getId() != null){
						subjectGrade.setSubjectName(subject.getName());
						subjectGrade.setModifyDate(new Date());
						subjectGradeDao.update(subjectGrade);
					}
				}
			}
			return subjectDao.update(subject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void remove(Subject subject) {
		try{
			subjectDao.delete(subject);
		}catch(Exception e){
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
		 
	}
	@Override
	public List<Subject> findSubjectByCondition(SubjectCondition subjectCondition, Page page, Order order) {
		return subjectDao.findSubjectByCondition(subjectCondition, page, order);
	}

	@Override
	public Subject findUnique(Integer schoolId, String stageCode, String code) {
		return subjectDao.findUnique(schoolId, stageCode, code);
	}

	@Override
	public Subject findUnique(Integer schoolId, String code) {
		return this.subjectDao.findUnique(schoolId, null, code);
	}

	@Override
	public List<Subject> findSubjectByName(String name,Integer schoolId,String stageCode,String subjectClass){
		return this.subjectDao.findSubjectByName(name,schoolId,stageCode,subjectClass);
	}

	/**
	 * 功能描述：获得本学校的授课科目
	 */
	@Override
	public List<Subject> findSubjectsOfSchool(Integer schoolId) {
		return this.subjectDao.findSubjectsOfSchool(schoolId);
	}

	@Override
	public void updateSubjectSort(String[] subjectIds, Integer schoolId) {
		try{
			Subject subject = null;
			if(subjectIds.length >0 && schoolId != null){
				for(int i = 0; i < subjectIds.length; i++){
					subject = new Subject();
					subject.setId(Integer.valueOf(subjectIds[i]));
					subject.setSchoolId(schoolId);
					subject.setListOrder(i+1);
					subjectDao.update(subject);
				}
			}
		}catch(Exception e){
			log.info("排序出错");
		}
	}

	@Override
	public List<Subject> findAllSubjectName() {
		// TODO Auto-generated method stub
		return subjectDao.findAllSubjectName();
	}

	@Override
	public Map<String, String> findAllSubjectNameMap() {
		Map<String,String>map=new HashMap<String, String>();
		List<Subject>slist=findAllSubjectName();
		if(slist!=null&&slist.size()>0){
			for(Subject s:slist){
				map.put(s.getCode(), s.getName());
			}
		}
		return map;
	}

	@Override
	public  Map<String, String> getSubjectMap(Integer schoolId) {
		Map<String, String> subjectMap = new HashMap<String, String>();
		SubjectCondition subjectCondition = new SubjectCondition();
		subjectCondition.setSchoolId(schoolId);
		List<Subject> subjectList = this.findSubjectByCondition(subjectCondition, null, null);
		for (Subject subject : subjectList) {
			subjectMap.put(subject.getCode()+"", subject.getName());
		}
		return subjectMap;
	}

	@Override
	public List<SubjectVo> findIncrementDataByModifyDate(
			Integer schoolId, String subjectClass, Boolean isDeleted, Date modifyDate,Integer subjectId, Boolean isGetNew) {
		return subjectDao.findIncrementDataByModifyDate(schoolId,subjectClass,isDeleted, modifyDate, subjectId, isGetNew);
	}

	@Override
	public Subject addGeneralSubject(Subject subject) {
		if (subject == null) {
			return null;
		}
		Integer schoolId = subject.getSchoolId();
		if (schoolId == null) {
			return null;
		}
		String stageCode = "";
		if (subject.getStageCode() == null || "".equals(subject.getStageCode())) {
			School school = schoolService.findSchoolById(schoolId);
			stageCode = getStageCode(school.getStageScope());
		} else {
			stageCode = subject.getStageCode();
		}

		//创建基础科目，获取code
		platform.education.generalcode.model.Subject jcSubject = new platform.education.generalcode.model.Subject();
		jcSubject.setName(subject.getName());
		jcSubject.setStageCode(stageCode);
		jcSubject.setSubjectClass(subject.getSubjectClass());
		jcSubject.setSubjectType(subject.getSubjectType());
		jcSubject.setSubjectCharacter(subject.getSubjectCharacter());
		jcSubject.setCreateDate(new Date());
		jcSubject.setModifyDate(new Date());
		jcSubject = jcSubjectService.addFromTeach(jcSubject);

		//创建科目
		subject.setStageCode(stageCode);
		subject.setCode(String.valueOf(jcSubject.getCode()));
		subject.setIsDelete(false);
		subject.setCreateDate(new Date());
		subject.setModifyDate(new Date());
		subject = subjectDao.create(subject);

		//创建年级科目
		if (subject != null) {
			List<SchoolSystem> schoolSystemList = this.schoolSystemService.findDefaultGradesOfSchool(subject.getSchoolId());
			SubjectGrade subjectGrade = null;
			for (SchoolSystem schoolSystem : schoolSystemList) {
				subjectGrade = new SubjectGrade();
				subjectGrade.setSchoolId(subject.getSchoolId());
				subjectGrade.setStageCode(schoolSystem.getStageCode());  //学段Code
				subjectGrade.setGradeCode(schoolSystem.getGradeCode());  //年级Code
				subjectGrade.setSubjectCode(subject.getCode());          //科目Code
				subjectGrade.setSubjectName(subject.getName());          //科目名称
				subjectGrade.setDelete(false);
				subjectGrade.setCreateDate(new Date());
				subjectGrade.setModifyDate(new Date());
				this.subjectGradeService.add(subjectGrade);
			}
		}

		return subject;
	}

	private String getStageCode(String stageScope) {
		String stageCode = "";
		if (stageScope.contains("2")) {
            stageCode += ",2";
        } else if (stageScope.contains("3")) {
            stageCode += ",3";
        } else if (stageScope.contains("4")) {
            stageCode += ",4";
        }
		if (stageCode != null && !"".equals(stageCode)) {
            stageCode = stageCode.substring(1);
        }
		return stageCode;
	}
}
