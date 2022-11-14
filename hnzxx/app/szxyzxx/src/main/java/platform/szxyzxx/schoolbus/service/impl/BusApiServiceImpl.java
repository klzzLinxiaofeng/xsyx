package platform.szxyzxx.schoolbus.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.szxyzxx.schoolbus.pojo.BusInfo;
import platform.szxyzxx.schoolbus.service.BusApiService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;

import java.util.List;
@Service
public class BusApiServiceImpl implements BusApiService {

    private volatile List<BusInfo> cachedList=null;
    private volatile String cacheDateStr=null;

    @Override
    public List<BusInfo> getAllBus() {
        String nowDateStr= SchoolBusTimeUtil.getNowDateStr();
        //每天重新获取一次校车列表
        if(cachedList==null || !nowDateStr.equals(cacheDateStr)){
            synchronized (this){
                if(cachedList==null || !nowDateStr.equals(cacheDateStr)) {
                    String responseString = HttpClientUtils.get("http://otsc.chuhangkj.com/api/Student/getSchoolBusList?SID=2309");
                    cachedList= JSON.parseArray(responseString, BusInfo.class);
                    for (BusInfo busInfo : cachedList) {
                        busInfo.setCompanyName("昊吉顺");
                    }
                    String responseString2 = HttpClientUtils.get("https://sync.bzywlw.cn/getSchoolBusList");
                    List<BusInfo> gsList=JSON.parseArray(responseString2, BusInfo.class);
                    for (BusInfo busInfo : gsList) {
                        busInfo.setCompanyName("国盛");
                    }
                    cachedList.addAll(gsList);
                    cacheDateStr=nowDateStr;
                }
            }
        }

        return cachedList;

    }

    @Override
    public BusInfo getByCarNo(String carNo) {
        List<BusInfo> list=getAllBus();
        for (BusInfo busInfo : list) {
            if(carNo.equals(busInfo.getSchoolBusCard())){
                return busInfo;
            }
        }
        return null;
    }
}
