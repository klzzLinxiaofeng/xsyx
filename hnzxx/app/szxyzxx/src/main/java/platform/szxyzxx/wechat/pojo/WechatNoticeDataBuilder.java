package platform.szxyzxx.wechat.pojo;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * 微信发送订阅通知的data参数构建者
 * 数据格式实例： { "key1": { "value": any }, "key2": { "value": any } }
 */
public class WechatNoticeDataBuilder {

    private Map<String, Map<String,Object>> dataMap=new HashedMap(3,1);

    public WechatNoticeDataBuilder addData(String key,Object val){
        Map<String,Object> valMap=new HashedMap(1);
        valMap.put("value",val);
        this.dataMap.put(key,valMap);
        return this;
    }

    public Object getData(){
        return dataMap;
    }
}
