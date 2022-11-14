package platform.szxyzxx.web.yihaostudy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import platform.szxyzxx.kebiao.service.KeBiaoService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

@RestController
@RequestMapping("/kebiao")
public class KeBiaoController {
    @Autowired
    private KeBiaoService keBiaoService;

    @RequestMapping("/tuisongKeBiao")
    public String getByKeBiao(@CurrentUser UserInfo userInfo,
                              @RequestParam Integer teamId,
                              @RequestParam Integer gradeId,
                              @RequestParam String schoolYear,
                              @RequestParam String teamCode){
       String str= keBiaoService.findBySeeoween(userInfo,teamId,gradeId,schoolYear,teamCode);

       if(str=="0"){
           return "参数为空";
       }
        if(str=="1"){
            return "该年级暂未设置作息时间";
        }
        if(str=="2"){
            return "该班级暂未设置课表";
        }
        return str;
    }
}
