package platform.szxyzxx.library.service;

import platform.szxyzxx.library.vo.Library;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangyong
 * @Date 2022/10/31 8:32
 * @Version 1.0
 */
public interface LibraryService {
    Boolean addLibrary(String sql,Map<String,Object> map);

    List<Library>  findByLibrary(String sql);
}
