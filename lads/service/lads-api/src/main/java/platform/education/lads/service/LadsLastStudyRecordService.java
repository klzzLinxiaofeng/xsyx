package platform.education.lads.service;

import platform.education.lads.model.LadsLastStudyRecord;
import platform.education.lads.vo.LadsLastStudyRecordCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LadsLastStudyRecordService {

    LadsLastStudyRecord findLadsLastStudyRecordById(Integer id);

    LadsLastStudyRecord add(LadsLastStudyRecord ladsLastStudyRecord);

    LadsLastStudyRecord modify(LadsLastStudyRecord ladsLastStudyRecord);

    void remove(LadsLastStudyRecord ladsLastStudyRecord);

    List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Page page, Order order);

    List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition);

    List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Page page);

    List<LadsLastStudyRecord> findLadsLastStudyRecordByCondition(LadsLastStudyRecordCondition ladsLastStudyRecordCondition, Order order);

    Long count();

    Long count(LadsLastStudyRecordCondition ladsLastStudyRecordCondition);

    void remove(LadsLastStudyRecordCondition ladsLastStudyRecordCondition);

    LadsLastStudyRecord findLadsLastStudyRecordByUuid(String id);
}
