package platform.szxyzxx.web.knowLedge.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.knowledge.service.KnowLedgeService;
import platform.szxyzxx.knowledge.vo.KnowLedge;
import platform.szxyzxx.knowledge.vo.Menu;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

@RestController
@RequestMapping("/knowLedge")
public class KnowledgeController {
    @Autowired
    private KnowLedgeService knowLedgeService;

    @RequestMapping("/findByAll")
    private ModelAndView findByAll(@RequestParam String sub,
                                   @ModelAttribute("page") Page page,
                                   @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                   @RequestParam(value = "subjectId",required = false) Integer subjectId,
                                   @RequestParam(value = "name",required = false) String name, Model model){
        List<KnowLedge> list=knowLedgeService.findByAll(name,gradeId,subjectId,page);
        model.addAttribute("list",list);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/zhishidian/knowLedge/list";
        }else{
            batUrl="/zhishidian/knowLedge/index";
        }
        return new ModelAndView(batUrl,model.asMap());
    }

    @RequestMapping("/createOrUpdate")
    private ModelAndView createOrUpdate(@RequestParam(value = "id",required = false) Integer id, Model model){
        String batUrl="";
        if(id!=null){
            KnowLedge knowLedge=knowLedgeService.findById(id);
            model.addAttribute("knowLedge",knowLedge);
        }
        return new ModelAndView("/zhishidian/knowLedge/input",model.asMap());
    }

    @RequestMapping("/create")
    private String create(@CurrentUser UserInfo userInfo,KnowLedge knowLedge){
        knowLedge.setSchoolYear(userInfo.getSchoolYear());
        knowLedge.setSchoolTrem(userInfo.getSchoolTermCode());
       Integer num=knowLedgeService.create(knowLedge);
       if(num>0){
           return "success";
       }else {
           return "error";
       }
    }
    @RequestMapping("/update")
    private String update(KnowLedge knowLedge){
        Integer num=knowLedgeService.update(knowLedge);
        if(num>0){
            return "success";
        }else {
            return "error";
        }
    }

    @RequestMapping("/deleteUpdate")
    private String updateDelete(@RequestParam Integer id){
        Integer num=knowLedgeService.updateDelete(id);
        if(num>0){
            return "success";
        }else {
            return "error";
        }
    }


    @RequestMapping("/findMenuView")
    private ModelAndView findMenuView(@RequestParam Integer id, Model model){
            model.addAttribute("id",id);
        return new ModelAndView("/zhishidian/knowLedge/guanliIndex",model.asMap());
    }

    @RequestMapping("/findByAllMenu")
    private List<Menu> updateMenu(@RequestParam(value = "id",required = false) Integer id){
        List<Menu> menuList=knowLedgeService.findByAllMenu(id);
        return menuList;
    }

    @RequestMapping("/createOrUpdateMenu")
    private ModelAndView createOrUpdateMenu(@RequestParam(value = "id",required = false) Integer id,
                                            @RequestParam(value = "knowId",required = false) Integer knowId,
                                            @RequestParam(value = "parentMenu",required = false) Integer parentMenu,
                                            @RequestParam Integer type,  Model model){
        if(id!=null){
            Menu menu=knowLedgeService.findByMenuId(id);
            model.addAttribute("menu",menu);
        }
        model.addAttribute("type",type);
        if(parentMenu!=null){
            model.addAttribute("parentMenu",parentMenu);
        }
        model.addAttribute("knowId",knowId);
        return new ModelAndView("/zhishidian/knowLedge/guanliinput",model.asMap());
    }

    @RequestMapping("/createMenu")
    private String createMenu(Menu menu ){
       Integer num=knowLedgeService.createMenu(menu);
       if(num>0){
           return "success";
       }else{
           return "error";
       }

    }
    @RequestMapping("/updateMenu")
    private String updateMenu(Menu menu ){
        Integer num=knowLedgeService.updateMenu(menu);
        if(num>0){
            return "success";
        }else{
            return "error";
        }

    }
    @RequestMapping("/updateDeleteMenu")
    private String updateDeleteMenu(@RequestParam Integer id){
        Integer num=knowLedgeService.updateDeleteMenu(id);
        if(num>0){
            return "success";
        }else{
            return "error";
        }

    }
}
