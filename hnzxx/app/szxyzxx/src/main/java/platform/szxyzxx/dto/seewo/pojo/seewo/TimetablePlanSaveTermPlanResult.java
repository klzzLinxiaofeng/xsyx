package platform.szxyzxx.dto.seewo.pojo.seewo;

import java.util.*;
import com.seewo.open.sdk.OpenApiResult;
import com.seewo.open.sdk.HttpResponse;

/**
 * seewo-open API: 根据名称保存或更新学期排课
 * 保存或更新排课计划
 *
 * @author auto create
 * @since 2.0.1 2020-12-24
 */
public class TimetablePlanSaveTermPlanResult extends OpenApiResult {

    public TimetablePlanSaveTermPlanResult(HttpResponse response) {
        super(response);
    }


    /**
     * 响应体，MimeType为 application/json
     */
    
    private JSONResponseBody responseBody;


    public JSONResponseBody getResponseBody() {
        return this.responseBody;
    }

    public void setResponseBody(JSONResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public static class JSONResponseBody {
        /**
         * 返回值
         */
        private String result;

        public String getResult() {
            return this.result;
        }

        public void setResult(String result) {
            this.result = result;
        }

    }


}

