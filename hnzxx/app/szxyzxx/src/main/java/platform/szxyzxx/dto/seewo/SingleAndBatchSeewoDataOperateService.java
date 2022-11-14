package platform.szxyzxx.dto.seewo;

import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;

/**
 * seewo常见数据操作者
 * @author chenjiaxin
 */
public interface SingleAndBatchSeewoDataOperateService<T> extends BatchSeewoDataOperateService<T> {
    /**
     * 添加数据到seewo
     */
    BasicResponseResult add(T data);

}
