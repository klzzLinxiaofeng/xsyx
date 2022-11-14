package com.xunyunedu.common.controller;

import com.xunyunedu.common.pojo.StartPagePojo;
import com.xunyunedu.common.service.StartPageService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启动页管理
 *
 * @author: yhc
 * @Date: 2020/12/14 15:04
 * @Description:
 */
@RequestMapping("/start/page")
@RestController
public class StartPageController {


    @Autowired
    private StartPageService startPageService;

    /**
     * 获取图片/视频
     *
     * @return
     */
    @GetMapping("/getStartPageUrl")
    public ApiResult getChildren() {
        StartPagePojo startPagePojo = startPageService.find();
        return new ApiResult(ResultCode.SUCCESS, startPagePojo);
    }
}
