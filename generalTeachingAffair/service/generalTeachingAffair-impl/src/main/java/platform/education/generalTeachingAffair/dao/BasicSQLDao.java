package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.SqlStatement;

import java.util.List;
import java.util.Map;

public interface BasicSQLDao {

    int update(SqlStatement ss);
    int create(String sql);
    List<Map<String,Object>> find(SqlStatement ss);
    List<Map<String,Object>> findByPaging(String sql,Page page );
}
