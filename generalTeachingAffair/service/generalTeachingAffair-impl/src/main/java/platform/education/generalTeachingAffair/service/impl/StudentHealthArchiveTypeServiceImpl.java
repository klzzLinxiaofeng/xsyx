package platform.education.generalTeachingAffair.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.StudentHealthArchiveTypeDao;
import platform.education.generalTeachingAffair.model.StudentHealthArchiveType;
import platform.education.generalTeachingAffair.service.StudentHealthArchiveTypeService;

/**
 * @author Eternityhua
 * @create 2020-12-13 11:06
 */
public class StudentHealthArchiveTypeServiceImpl implements StudentHealthArchiveTypeService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private StudentHealthArchiveTypeDao studentHealthArchiveTypeDao;

    public void setStudentHealthArchiveTypeDao(StudentHealthArchiveTypeDao studentHealthArchiveTypeDao) {
        this.studentHealthArchiveTypeDao = studentHealthArchiveTypeDao;
    }


    @Override
    public StudentHealthArchiveType createBatch(StudentHealthArchiveType entity) {
        return studentHealthArchiveTypeDao.create(entity);
    }
}
