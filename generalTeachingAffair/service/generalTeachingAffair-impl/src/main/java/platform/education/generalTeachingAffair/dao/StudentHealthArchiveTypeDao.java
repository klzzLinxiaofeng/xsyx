package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.model.StudentHealthArchiveType;

/**
 * @author Eternityhua
 * @create 2020-12-13 11:03
 */
public interface StudentHealthArchiveTypeDao extends GenericDao<StudentHealthArchiveType, Integer> {

    StudentHealthArchiveType create(StudentHealthArchiveType entity);
}
