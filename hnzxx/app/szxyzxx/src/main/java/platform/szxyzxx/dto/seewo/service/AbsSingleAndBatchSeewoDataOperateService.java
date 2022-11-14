package platform.szxyzxx.dto.seewo.service;

import com.seewo.open.sdk.OpenApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.szxyzxx.dto.seewo.SingleAndBatchSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.SeewoOperateClient;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.dto.seewo.pojo.BatchOperateResult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjiaxin
 */
public abstract class AbsSingleAndBatchSeewoDataOperateService<T> extends SeewoOperateClient implements SingleAndBatchSeewoDataOperateService<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbsSingleAndBatchSeewoDataOperateService.class);
    

    protected abstract OpenApiRequest createSingleAddRequest(T data);

    @Override
    public BasicResponseResult add(T data) {
        return invoke(createSingleAddRequest(data));
    }

    @Override
    public BatchOperateResult batchAdd(List<T> dataList) {
        List<T> exceptionDataList=new ArrayList<>(0);
        for (T data : dataList) {
            BasicResponseResult result=add(data);
            if(result.getCode()!=null && !result.getCode().equals("000000")){
                exceptionDataList.add(data);
            }
        }
        return new BatchOperateResult(exceptionDataList);
    }



}
