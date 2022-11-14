package platform.education.lads.dao;
import framework.generic.dao.GenericDao;

import platform.education.learningDesign.model.LearningDesignRelate;

public interface CRLearningDesignRelateDao extends GenericDao<LearningDesignRelate, java.lang.Integer> {

        Integer findRelateIdByLdId(String ldId);
	
}
