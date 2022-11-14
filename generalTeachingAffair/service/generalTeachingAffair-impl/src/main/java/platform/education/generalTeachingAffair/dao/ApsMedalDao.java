package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsMedal;

public interface ApsMedalDao extends GenericDao<ApsMedal, java.lang.Integer> {

	ApsMedal findById(Integer id);
	
	//List<ApsMedal> findByschoolId(Integer schoolId, String ObjectType);

	ApsMedal findMedals(Integer schoolId,String gradeCode,String name,String objectType,String runPeriod);

	List<ApsMedal> findMedalNoGradeCode(Integer schoolId,String name,String objectType,String runPeriod);

	List<ApsMedal> findByschoolIdAndType(Integer schoolId, String ObjectType);
	
	//找到星级个人的奖项
	ApsMedal findMedalsOfStudent(Integer schoolId, String gradeCode, String name, String objectType);

}
