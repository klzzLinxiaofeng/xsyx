package platform.education.lads.dao;

import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.vo.LadsLastStudyRecordCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsLastStudyRecordDao extends GenericDao<LadsLastStudyRecord, java.lang.Integer> {

	List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Page page, Order order);
	
	LadsLastStudyRecord findById(Integer id);
	
	Long count(LadsLastStudyRecordCondition ladsLastStudyRecordCondition);
	
	void deleteByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition);
        
        LadsLastStudyRecord findByUuid(String uuid);
}
