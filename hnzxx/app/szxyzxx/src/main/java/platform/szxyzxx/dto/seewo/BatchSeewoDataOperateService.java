package platform.szxyzxx.dto.seewo;

import platform.szxyzxx.dto.seewo.pojo.BatchOperateResult;

import java.util.List;

/**
 * seewo批量数据操作者
 * @author chenjiaxin
 */
public interface BatchSeewoDataOperateService<T> {
    /**
     * 添加数据到seewo
     */
    BatchOperateResult batchAdd(List<T> dataList);

}
