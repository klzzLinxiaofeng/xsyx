package platform.szxyzxx.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import platform.szxyzxx.library.service.LibraryService;
import platform.szxyzxx.library.vo.Library;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyong
 * @Date 2022/10/31 8:33
 * @Version 1.0
 * 图书馆接口
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    @Qualifier("libraryJdbcNameTemplate")
    private NamedParameterJdbcTemplate libraryNameJdbcTemplate;


    /*
    *添加修改接口
    * */
    @Override
    public Boolean addLibrary(String sql,Map<String,Object> map){
       int num= libraryNameJdbcTemplate.update(sql,map);
        return num>0?true:false;
    }

    @Override
    public List<Library> findByLibrary(String sql) {
        RowMapper<Library> rowMapper = new BeanPropertyRowMapper<>(Library.class);
        List<Library> list = null;
        try {
            list = libraryNameJdbcTemplate.query(sql,rowMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
        return list;
    }

}
