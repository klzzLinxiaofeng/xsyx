package platform.szxyzxx.web.schoolbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.service.BetterBusThirtyPartyApiService;
import platform.szxyzxx.web.schoolbus.vo.GateApiParam;

@RequestMapping("/api/gate")
@RestController
public class BetterBusThirtyPartyApiController {

    @Autowired
    private BetterBusThirtyPartyApiService betterBusThirtyPartyApiService;


    @RequestMapping(value = "/getStuPickInfo",method= RequestMethod.POST)
    public GatePickInfo getStuPickInfo(@RequestBody GateApiParam param){
        try {
            return betterBusThirtyPartyApiService.getStuPickInfo(Integer.valueOf(param.getUserId()),param.getDate(),param.getSchoolDirection(),param.getDoorDirection());
        } catch (Exception e) {
            return null;
        }
    }


}
