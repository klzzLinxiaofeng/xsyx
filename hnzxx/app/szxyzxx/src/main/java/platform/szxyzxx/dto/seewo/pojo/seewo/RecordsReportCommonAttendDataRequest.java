package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiParam;
import com.seewo.open.sdk.OpenApiRequest;

/**
 * seewo-open API: 考勤数据上报
 * 
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class RecordsReportCommonAttendDataRequest extends OpenApiRequest<RecordsReportCommonAttendDataParam, RecordsReportCommonAttendDataResult> {

    public RecordsReportCommonAttendDataRequest(RecordsReportCommonAttendDataParam param) {
        this();
        setBizModel(param);
    }

    public RecordsReportCommonAttendDataRequest() {
        setServerUrl("https://openapi.seewo.com");
        setPath("/mis-attendance-api/records/report-common-attend-data");
        setHttpMethod("POST");
    }

    public Class<RecordsReportCommonAttendDataResult> getResponseClass() {
        return RecordsReportCommonAttendDataResult.class;
    }

    public Class<RecordsReportCommonAttendDataParam> getDomainClass() {
        return RecordsReportCommonAttendDataParam.class;
    }
}

