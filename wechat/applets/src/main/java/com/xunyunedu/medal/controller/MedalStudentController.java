package com.xunyunedu.medal.controller;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.medal.model.Medal;
import com.xunyunedu.medal.model.MvAwardsMedal;
import com.xunyunedu.medal.service.MedalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 学生端奖章
 * @author: cmb
 * @create: 2020-10-23 14:56
 **/
@RestController
@RequestMapping("/smedal")
public class MedalStudentController {
    @Autowired
    private MedalService medalService;
/**
* @Description: 获取学生奖章数据
* @Param: * @param name
* @return: com.xunyunedu.exception.ApiResult
* @Author: cmb
* @Date: 2020/10/23
*/
    @GetMapping("/medalList")
    public ApiResult MedalList(@RequestParam("studentId") Integer studentId) {
        if (studentId != null ) {
            List<MvAwardsMedal> medalList = medalService.findMvAwardsMedalByStudent(studentId);
            int count=0;
            for (MvAwardsMedal mvAwardsMedal : medalList) {
                count++;
                Medal medal = medalService.findMeDalById(mvAwardsMedal.getMedalId());
                mvAwardsMedal.setMedelName(medal.getName());
                mvAwardsMedal.setDescription(medal.getDescription());
                mvAwardsMedal.setBackground(medal.getBackground());
            }

            return new ApiResult(ResultCode.FIND_SUCCESS,2,medalList,count);
        }
        return new ApiResult(ResultCode.FIND_FAIL);

    }
    /**
    * @Description: 获取奖章信息
    * @Param: * @param medalId
    * @return: com.xunyunedu.exception.ApiResult
    * @Author: cmb
    * @Date: 2020/10/23
    */

    @GetMapping("/description")
    public ApiResult Description(@RequestParam("id") Integer medalId, @RequestParam("stduentId") Integer stduentId){

            Medal medal= medalService.findMeDalById(medalId);
        MvAwardsMedal mvAwardsMedal=null;
            if (medal!=null){
             mvAwardsMedal = medalService.findMvAwardsMedalByStudentAndMeDalById(medalId, stduentId);
                mvAwardsMedal.setMedelName(medal.getName());
                mvAwardsMedal.setDescription(medal.getDescription());
                mvAwardsMedal.setBackground(medal.getBackground());
            }

            return new ApiResult(ResultCode.FIND_SUCCESS,mvAwardsMedal);


    }


}
