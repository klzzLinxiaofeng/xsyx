package platform.education.generalTeachingAffair.dao;

import java.util.List;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.ApsTaskScoreFile;

public interface ApsTaskScoreFileDao extends GenericDao<ApsTaskScoreFile, java.lang.Integer> {

	ApsTaskScoreFile findById(Integer id);
	
	List<ApsTaskScoreFile> findByTaskScoreId(Integer taskScoreId);
}
