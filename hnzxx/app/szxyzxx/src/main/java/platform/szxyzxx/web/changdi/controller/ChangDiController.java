package platform.szxyzxx.web.changdi.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.changdi.service.ChangDiService;
import platform.szxyzxx.changdi.vo.ChangDi;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/changdi")
public class ChangDiController {
    @Autowired
    private ChangDiService changDiService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@RequestParam(value = "name",required = false) String name,
                                  @RequestParam(value = "address",required = false) String address,
                                  @ModelAttribute("page") Page page,
                                  @RequestParam(value = "sub",required = false) String sub, Model model){
        List<ChangDi> changDis=changDiService.findByAll(name,address,page);
        String baturl="";
        if(sub!=null){
            if(sub.equals("list")){
                baturl="/changdi/list";
            }else{
                baturl="/changdi/changDiIndex";
            }
        }else{
            baturl="/changdi/changDiIndex";
        }
        model.addAttribute("changDis",changDis);
        return new ModelAndView(baturl,model.asMap());
    }

    @RequestMapping("/addAndUpdate")
    public ModelAndView createorUpdate(@RequestParam(value = "id",required = false) Integer id,
                                       @RequestParam String sub,Model model){
        ChangDi changDi=changDiService.findById(id);
        if(sub.equals("input")) {
            model.addAttribute("changdi",changDi);
        }
        return new ModelAndView("/changdi/input",model.asMap());
    }


    @RequestMapping("/add")
    public String createAll(ChangDi changDi){
        changDi.setCraeteTime(new Date());
        return changDiService.create(changDi);
    }
    @RequestMapping("/update")
    public String updateAll(ChangDi changDi){
        changDi.setModiyTime(new Date());
        return changDiService.update(changDi);
    }
    @RequestMapping("/deleteAll")
    public String deleteAll(@RequestParam Integer id){
        return changDiService.deleteChangDi(id);
    }
}
