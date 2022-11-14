package platform.education.generalTeachingAffair.utils.hikvision;

import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: cmb
 * @Date: 2020/11/05 11:20
 * @Description:
 */
public class HikvisionClient {
    /**
     * 请求海康数据
     *
     * @param artemisConfig
     * @param url
     * @param body
     * @return
     */
    public static String hikvisionPost(ArtemisConfig artemisConfig, String url, String body) {
        if (body == null || body.equals("")) {
            return "";
        }

        ArtemisConfig.host = artemisConfig.getHost();
        ArtemisConfig.appKey = artemisConfig.getAppKey();
        ArtemisConfig.appSecret = artemisConfig.getAppSecret();

        final String ARTEMIS_PATH = "/artemis";
        final String previewURLsApi = ARTEMIS_PATH + url;
        Map<String, String> path = new HashMap<String, String>(2) {
            {
                //根据现场环境部署确认是http还是https
                put("https://", previewURLsApi);
            }
        };
        String contentType = "application/json";
        return ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType, null);
    }

}
