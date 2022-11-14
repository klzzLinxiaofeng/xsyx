package platform.szxyzxx.dto.seewo.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Seewo批量操作后的结果信息
 * @author chenjiaxin
 */
public class BatchOperateResult<T> {
    /**
     * 发送失败的数据
     */
    private List<T> exceptionDataList=new ArrayList<>(0);

    public BatchOperateResult(){}
    public BatchOperateResult(List<T> exceptionDataList) {
        this.exceptionDataList = exceptionDataList;
    }

    public boolean isAllSuccess() {
        return exceptionDataList==null || exceptionDataList.size()==0;
    }

    public List<T> getExceptionDataList() {
        return exceptionDataList;
    }

    public void setExceptionDataList(List<T> exceptionDataList) {
        this.exceptionDataList = exceptionDataList;
    }
}
