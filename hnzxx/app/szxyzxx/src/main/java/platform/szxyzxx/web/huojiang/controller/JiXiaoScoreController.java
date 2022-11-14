package platform.szxyzxx.web.huojiang.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.huojiang.service.JiXiaoScoreService;
import platform.szxyzxx.huojiang.vo.JiXiaoScore;

import java.util.List;

@RestController
@RequestMapping("/jixiao")
public class JiXiaoScoreController {

    @Autowired
    private JiXiaoScoreService jiXiaoScoreService;
    @RequestMapping("/jixiaoView")
    public ModelAndView findByView(){
        return new ModelAndView("/jiangxiang/scoresetting/index");
    }

    @RequestMapping("/findByAll")
    public List<JiXiaoScore> findByAll(){
        return  jiXiaoScoreService.findByAll();
    }


    @RequestMapping("/create")
    public String create(JiXiaoScore jiXiaoScore){
        int num=jiXiaoScoreService.create(jiXiaoScore);
        if(num>0){
            return "success";
        }else{
            return  "error";
        }
    }
    @RequestMapping("/update")
    public String update(JiXiaoScore jiXiaoScore){
        int num=jiXiaoScoreService.update(jiXiaoScore);
        if(num>0){
            return "success";
        }else{
            return  "error";
        }

    }
}
