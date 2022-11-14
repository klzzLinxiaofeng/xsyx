package platform.szxyzxx.dto.seewo.service;

import com.seewo.open.sdk.OpenApiRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.szxyzxx.dto.seewo.GeneralSeewoDataOerateService;
import platform.szxyzxx.dto.seewo.SeewoOperateClient;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;

import java.util.List;
import java.util.Map;

/**
 * @author chenjiaxin
 */
public abstract class AbsGeneralSeewoDataOperateService<T>  extends SeewoOperateClient implements GeneralSeewoDataOerateService<T> {

    private static final Logger logger = LoggerFactory.getLogger(AbsGeneralSeewoDataOperateService.class);

    protected abstract OpenApiRequest createQueryAllRequest();

    protected abstract OpenApiRequest createAddAllRequest(List<T> list);

    protected abstract OpenApiRequest createDeleteRequest(List<Map<String, Object>> delList);

    protected abstract OpenApiRequest createUpdateRequest(List<Map<String, Object>> updateList);



    @Override
    public BasicResponseResult addAll(List<T> list) {
        return invoke(createAddAllRequest(list));
    }

    @Override
    public List<Map<String, Object>> queryAll() {
        return invoke(createQueryAllRequest()).getPagingResult();
    }

    @Override
    public BasicResponseResult delete(List<Map<String, Object>> queriedDelList) {
        return invoke(createDeleteRequest(queriedDelList));
    }

    @Override
    public BasicResponseResult update(List<Map<String, Object>> updateList) {
        return invoke(createUpdateRequest(updateList));
    }
}
