package platform.szxyzxx.web.Accommodation.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.accommodation.pojo.HouseAmount;
import platform.szxyzxx.accommodation.service.HouseAmountService;

import java.util.List;

@RestController
@RequestMapping("/HouseAmount")
public class HouseAmountController {
    @Autowired
    private HouseAmountService houseAmountService;
    @RequestMapping("/view")
    public ModelAndView fiwedAll(){
        return new ModelAndView("/accommodation/setting");
    }

    @RequestMapping("/findByAll")
    public List<HouseAmount> findByAll(){
        return houseAmountService.findByAll();
    }
    @RequestMapping("/create")
    public String create(HouseAmount houseAmount){
       int num= houseAmountService.create(houseAmount);
       if(num>0){
         return "success";
       }
        return "error";
    }
    @RequestMapping("/update")
    public String update(HouseAmount houseAmount){
        int num= houseAmountService.update(houseAmount);
        if(num>0){
            return "success";
        }
        return "error";
    }
}
