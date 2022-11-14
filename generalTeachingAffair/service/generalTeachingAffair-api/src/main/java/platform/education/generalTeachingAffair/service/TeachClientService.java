package platform.education.generalTeachingAffair.service;

import java.util.List;

import platform.education.generalTeachingAffair.model.DictClient;
import platform.education.generalTeachingAffair.model.Grade;


/***
 * 教学教学，此接口主提供客户端调用
 * @author admin
 *
 */
public interface TeachClientService {

	/**
	 * 通过学校，获取当前学校的所有年级和班级
	 */
	public List<Grade> findGradeAndTeamInfoBySchool(Integer schoolId);
	
	/**
	 * 获取所有常用字典列表
	 */
	public DictClient findAllDictList();
}
