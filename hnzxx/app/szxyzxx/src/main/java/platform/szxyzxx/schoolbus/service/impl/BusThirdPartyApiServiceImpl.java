package platform.szxyzxx.schoolbus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.utils.hikvision.HikvisionClient;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.pojo.HikDoorRequestData;
import platform.szxyzxx.schoolbus.pojo.HikDoorResponseData;
import platform.szxyzxx.schoolbus.service.BusThirdPartyApiService;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: yhc
 * @Date: 2021/4/9 14:14
 * @Description: 海康门禁打卡
 */
@Service("busThirdPartyApiServiceImpl")
public class BusThirdPartyApiServiceImpl implements BusThirdPartyApiService {

    private Logger log = LoggerFactory.getLogger(getClass());


    private static String hikvisionUrl;
    private static String hikvisionHost;
    private static String hikvisionAppKey;
    private static String hikvisionAppSecret;
    private static List<String> list;

    private static ArtemisConfig artemisConfig;

    static {
        String fileName = "System.properties";
        //闸机
        hikvisionHost = PropertiesUtil.getProperty(fileName, "ArtemisConfig.host");
        hikvisionAppKey = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appKey");
        hikvisionAppSecret = PropertiesUtil.getProperty(fileName, "ArtemisConfig.appSecret");
        hikvisionUrl = PropertiesUtil.getProperty(fileName, "hikvision.server.card.event");

        if (StrUtil.isNotEmpty(hikvisionUrl) && StrUtil.isNotEmpty(hikvisionHost) && StrUtil.isNotEmpty(hikvisionAppKey) && StrUtil.isNotEmpty(hikvisionAppSecret)) {
            artemisConfig = new ArtemisConfig();
            artemisConfig.setHost(hikvisionHost);
            artemisConfig.setAppKey(hikvisionAppKey);
            artemisConfig.setAppSecret(hikvisionAppSecret);
        }


            list = new ArrayList<String>(20) {
            {
                add("35132f4b405d4c74b203f87909b57cce");
                add("fe02ad18c6a545209c297b68833bc1b3");
                add("b542e421d5b24a8fbec80436720e2f2e");
                add("4ee66e11d6d4473baa01f6fce628fcb3");
                add("54b78734d79e4e0caffa9753b29a84e4");
                add("8420739dee3b403b9ab29a78bd4c1e5d");
                add("9bf03f9675a54e17b432b4bdc45b03eb");
                add("aa4523b3bb454fb990c9107719ed97c2");
                add("13965d33f10b49679d188dd9e6b66829");
                add("1d493ab4722347f6bcf70caa0b8a1a3e");
                add("acfc603c2de1425a98795bf9016de3fe");
                add("fa173dd8fc3141de97cfcead85a1e572");
                add("d2960c210fb949a89882ba70e2a6e8c9");
                add("7627edb667fb4ca88739f228888ca307");
                add("a6aa56b143f3411f8650f4c586f836ed");
                add("5e663883896c430290f5eeefef91923e");
                add("0cc08f6189554c2baeefc9b69021db3f");
                add("bfdd3dc2e2784acbabffbc1b67fb300d");
                add("6d9f4c8bad434e0cba3a12966816ef3c");
                add("8df40ed5de154e8381af0831a10a4f96");
            }
        };
    }


    @Override
    public List<GatePickInfo> getStudentGatePickInfo(String userId, String startTime, String endTime) {
        List<String> list = new ArrayList<>(1);
        list.add(userId);
        return getListStudentGatePickInfo(list, startTime, endTime);
    }

    @Override
    public List<GatePickInfo> getListStudentGatePickInfo(List<String> userIds, String startTime, String endTime) {
        if(userIds==null || userIds.size()==0){
            return new ArrayList<>(0);
        }

        List<GatePickInfo> list=new ArrayList<>();
        if(userIds.size()>100){
            List<String> hundredList=new ArrayList<>(100);
            for (int i = 1; i <= userIds.size(); i++) {
                hundredList.add(userIds.get(i-1));
                if( i%100==0){
                    List<GatePickInfo> list2=getListStudentClockIn(hundredList, startTime, endTime);
                    if(list2!=null) {
                        list.addAll(list2);
                    }
                    hundredList=new ArrayList<>(100);
                }
            }
            if(userIds.size()>0){
                List<GatePickInfo> list2=getListStudentClockIn(hundredList, startTime, endTime);
                if(list2!=null) {
                    list.addAll(list2);
                }
            }
        }else{
            List<GatePickInfo> list2=getListStudentClockIn(userIds, startTime, endTime);
            if(list2!=null){
                list=list2;
            }
        }

        return filterRootCodes(list);
    }

    /**
     * 获取学生打卡记录
     *
     * @param userIds
     * @param startTime
     * @param endTime
     * @return
     */
    private List<GatePickInfo> getListStudentClockIn(List<String> userIds, String startTime, String endTime) {
        if (artemisConfig!=null){
            HikDoorRequestData hikDoorRequestData = new HikDoorRequestData(startTime, endTime, 1, 1000, userIds);
            log.debug("开始获取门禁打卡信息=============================================");
            HikDoorResponseData response = senRequest(hikDoorRequestData);
            if(response!=null && response.getData()!=null){
                HikDoorResponseData.DataBean responseData=response.getData();
                List<GatePickInfo> list=responseData.getList();
                if(responseData.getTotalPage()<=1){
                    return list;
                }
                if(list==null){
                    list=new ArrayList<>();
                }
                //分页读取
                for(int i=2;i<=responseData.getTotalPage();i++){
                    HikDoorResponseData pagingData=senRequest(new HikDoorRequestData(startTime, endTime, i, 1000, userIds));
                    if(pagingData!=null && pagingData.getData()!=null && pagingData.getData().getList()!=null && pagingData.getData().getList().size()>0){
                        list.addAll(pagingData.getData().getList());
                    }
                }
                return list;
            }
        } else {
            log.error("海康接口配置文件读取失败");
        }

        return new ArrayList<>(0);
    }


    private HikDoorResponseData senRequest(HikDoorRequestData hikDoorRequestData){
        String json = JSONObject.toJSONString(hikDoorRequestData);
        try {
            String data = HikvisionClient.hikvisionPost(artemisConfig, hikvisionUrl, json);
            if (StrUtil.isNotEmpty(data)) {
                HikDoorResponseData hikDoorResponseData = JSON.parseObject(data, new TypeReference<HikDoorResponseData>() {
                });
                if(hikDoorResponseData!=null &&  "success".equals(hikDoorResponseData.getMsg()) ) {
                    return hikDoorResponseData;
                }
                return null;
            }
        } catch (Exception e) {
            log.error("海康接口调用失败");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 过滤指定的机器标识
     *
     * @param gatePickInfoList
     * @return
     */
    private List<GatePickInfo> filterRootCodes(List<GatePickInfo> gatePickInfoList) {
        if (CollUtil.isNotEmpty(gatePickInfoList)) {
            Iterator<GatePickInfo> iterator = gatePickInfoList.iterator();
            while (iterator.hasNext()) {
                GatePickInfo pickInfo = iterator.next();
                String doorIndexCode = pickInfo.getDoorIndexCode();
                if (StrUtil.isNotEmpty(doorIndexCode)) {
                    boolean contains = list.contains(doorIndexCode);
                    if (!contains) {
                        iterator.remove();
                    }
                } else {
                    iterator.remove();
                }
            }
        }
        return gatePickInfoList;
    }
}
