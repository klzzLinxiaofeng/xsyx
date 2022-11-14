package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;

import org.apache.ibatis.annotations.Param;
import platform.education.generalTeachingAffair.model.ResPath;

import java.util.List;

public interface ResPathDao extends GenericDao<ResPath, Integer> {


    List<ResPath> readAll();

    List<ResPath> findByUserTypeId(Integer userTypeId);
}
