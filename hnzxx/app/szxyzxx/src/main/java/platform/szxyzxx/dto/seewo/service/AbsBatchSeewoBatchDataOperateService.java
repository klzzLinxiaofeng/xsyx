package platform.szxyzxx.dto.seewo.service;

import com.seewo.open.sdk.OpenApiRequest;
import org.springframework.stereotype.Service;
import platform.szxyzxx.dto.seewo.BatchSeewoDataOperateService;
import platform.szxyzxx.dto.seewo.SeewoOperateClient;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.dto.seewo.pojo.BatchOperateResult;

import java.util.List;


@Service
public abstract class AbsBatchSeewoBatchDataOperateService<T> extends SeewoOperateClient implements BatchSeewoDataOperateService<T> {

    protected abstract OpenApiRequest createSeewoOpenApiRequest(List<T> data);

    @Override
    public BatchOperateResult batchAdd(List<T> dataList) {
        BatchOperateResult batchResult=new BatchOperateResult();
        BasicResponseResult result=invoke(createSeewoOpenApiRequest(dataList));
        if(result.getCode()!=null && !result.getCode().equals("000000")){
            batchResult.setExceptionDataList(dataList);
        }
        return batchResult;
    }


}
