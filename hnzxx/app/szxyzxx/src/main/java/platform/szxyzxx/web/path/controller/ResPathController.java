package platform.szxyzxx.web.path.controller;

import org.springframework.web.bind.annotation.*;
import platform.education.generalTeachingAffair.model.ResPath;
import platform.szxyzxx.services.path.service.ResPathService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 资源路径
 * @author edison
 */
@RestController
@RequestMapping("/res/path")
public class ResPathController {


    @Resource
    ResPathService resPathService;

    /**
     * 根据用户类型获取资源路径
     * @param userTypeId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/user-type")
    public List<ResPath> getByUserType(@RequestParam("userTypeId") Integer userTypeId){
        return resPathService.findByUserTypeId(userTypeId);
    }
    
}
