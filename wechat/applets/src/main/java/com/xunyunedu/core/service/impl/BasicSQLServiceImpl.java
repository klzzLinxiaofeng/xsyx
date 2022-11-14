package com.xunyunedu.core.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.core.dao.BasicSQLDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BasicSQLServiceImpl implements BasicSQLService {

    @Autowired
    private BasicSQLDao dao;

    @Override
    public int update(String sql) {
        return dao.update(sql);
    }

    @Override
    @Transactional
    public int updateBatch(String... sqls) {
        int c=0;
        for (String sql : sqls) {
             c+=update(sql);
        }
        return c;
    }

    @Override
    @Transactional
    public int updateBatchByStr(String batchSqlStr) {
        int c=0;
       String[] sqls= batchSqlStr.split(";");
        for (String sql : sqls) {
            c+=update(sql);
        }
        return c;
    }

    @Override
    public  List<Map<String,Object>> find(String sql) {
        return dao.find(sql);
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
    public PageInfo<Map<String,Object>> findByPaging(String sql, int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum,pageSize);
        List list = dao.find(sql);
        PageInfo<Map<String,Object>> pageInfo = page.toPageInfo();
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public Map<String, Object> getStudentParentUserInfo(Integer stuId) {
        String sql="select u.id user_id,u.open_id from pj_parent_student ps inner join pj_student s on s.user_id=ps.student_user_id inner join yh_user u on u.id=ps.parent_user_id where ps.rank=1 and ps.is_delete=0 and s.is_delete=0 and s.id="+stuId;
        List<Map<String,Object>> list=find(sql);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public String getNowSchoolYear() {
        return findUnique("select school_year from pj_school_term_current where school_id=215").toString();
    }

}
