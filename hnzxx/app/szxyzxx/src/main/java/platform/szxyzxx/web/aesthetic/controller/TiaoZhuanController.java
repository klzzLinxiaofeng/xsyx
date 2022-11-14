package platform.szxyzxx.web.aesthetic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pdf")
public class TiaoZhuanController {
    @RequestMapping(value = "/pdfdaochu")
    public String findByAesthetic(){
        return "/pdf/pdfdaochu";
    }
}
