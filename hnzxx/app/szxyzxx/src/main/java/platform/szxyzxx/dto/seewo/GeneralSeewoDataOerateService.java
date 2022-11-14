package platform.szxyzxx.dto.seewo;

import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;

import java.util.List;
import java.util.Map;

/**
 * seewo数据Api
 * @author chenjiaxin
 */
public interface GeneralSeewoDataOerateService<T> {

    BasicResponseResult addAll(List<T> list);

    List<Map<String,Object>> queryAll();

    BasicResponseResult delete(List<Map<String, Object>> queriedDelList);

    BasicResponseResult update(List<Map<String, Object>> queriedUpdateList);

}
