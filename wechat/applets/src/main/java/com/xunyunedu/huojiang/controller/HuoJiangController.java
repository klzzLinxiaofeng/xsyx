package com.xunyunedu.huojiang.controller;


import com.xunyunedu.huojiang.service.HuoJiangService;
import com.xunyunedu.huojiang.vo.ClassLunwen;
import com.xunyunedu.huojiang.vo.HuoJiang;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/huojiang")
@Api(value = "HuoJiang", description = "获奖接口")
public class HuoJiangController {
   @Autowired
    private HuoJiangService huoJiangService;
   @RequestMapping("/findByAll")
   @ApiOperation(value = "获奖列表", httpMethod = "GET")
   public List<HuoJiang> findByAll(@RequestParam Integer teacherId,
                                   @RequestParam(value = "type",required = false) String type){
      return huoJiangService.findByAll(teacherId,type);
   }

    @RequestMapping("/findById")
    @ApiOperation(value = "查看详情", httpMethod = "GET")
    public HuoJiang findByAll(@RequestParam Integer teacherId,
                                    @RequestParam Integer id){
        return huoJiangService.findById(teacherId,id);
    }

    @RequestMapping("/findByPaiMing")
    @ApiOperation(value = "教赛个人排名", httpMethod = "GET")
    public  Map<String,Object> findByPaiMing(@RequestParam String  teacherName,
                                            @RequestParam Integer schoolId){
        List<ClassLunwen> list= huoJiangService.findByTongJi(teacherName,schoolId);
        Map map = new HashMap();
        for(int a=0;a<list.size();a++){
            if(list.get(a).getTeacherNames().equals(teacherName)){
                if(list.get(a).getScore()!=0){
                    map.put("paiMing",a+1);
                    map.put("name",list.get(a).getTeacherNames());
                }else{
                    map.put("paiMing","暂无排名");
                    map.put("name",list.get(a).getTeacherNames());
                }
            }
        }
        return map;
    }
}
