package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import platform.education.generalTeachingAffair.dao.BasicSQLDao;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.SqlStatement;

import java.util.List;
import java.util.Map;
public class BasicSQLServiceImpl implements BasicSQLService {

    @Autowired
    private BasicSQLDao dao;

    @Override
    public int update(String sql) {
        return dao.update(new SqlStatement(sql));
    }

    @Override
    public int create(String sql) {
        return dao.create(sql);
    }

    @Override
    @Transactional
    public void updateBatch(String... sqls) {
        for (String sql : sqls) {
             update(sql);
        }
    }

    @Override
    @Transactional
    public void updateBatchByStr(String batchSqlStr) {
       String[] sqls= batchSqlStr.split(";");
        for (String sql : sqls) {
            update(sql);
        }
    }

    @Override
    public  List<Map<String,Object>> find(String sql) {
        return dao.find(new SqlStatement(sql));
    }

    @Override
    public long findUniqueLong(String sql) {
        return Long.valueOf(findUnique(sql).toString());
    }

    @Override
    public Object findUnique(String sql) {
        List<Map<String,Object>> mapList=find(sql);
        if(mapList!=null && mapList.size()>0 && mapList.get(0)!=null) {
            return mapList.get(0).entrySet().iterator().next().getValue();
        }
        return null;
    }

    @Override
    public List<Map<String,Object>> findByPaging(Page page, String sql) {
        return dao.findByPaging(sql,page);
    }

    @Override
    public String getNowSchoolYear() {
        return findUnique("select school_year from pj_school_term_current where school_id=215").toString();
    }
}
