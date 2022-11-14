package platform.szxyzxx.web.yihaostudy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.szxyzxx.kaoqin.service.KaoQinService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Map;

@RestController
@RequestMapping("/kaoqin")
public class KaoQingController {

    @Autowired
    private KaoQinService kaoQinService;

    @RequestMapping("/AllKaoQin")
    public Map<String,Object> findByKaoQinAll(@CurrentUser UserInfo userInfo){
        Map<String,Object> map=kaoQinService.findByKaoQin(userInfo);
        return map;
    }
}
