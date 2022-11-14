package platform.education.generalTeachingAffair.service;


import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;

public interface BasicSQLService {
    int update(String sql);
    int create(String sql);
    void updateBatch(String... sqls);
    void updateBatchByStr(String batchSqlStr);
    List<Map<String,Object>> find(String sql);
    List<Map<String,Object>> findByPaging(Page page, String sql);
    long findUniqueLong(String sql);
    Object findUnique(String sql);

    String getNowSchoolYear();

}
