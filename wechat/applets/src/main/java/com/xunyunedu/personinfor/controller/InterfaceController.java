package com.xunyunedu.personinfor.controller;

import cn.hutool.core.util.StrUtil;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.init.InitHandler;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.personinfor.pojo.LibraryVo;
import com.xunyunedu.personinfor.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图书馆
 *
 * @author: yhc
 * @Date: 2020/11/23 10:02
 * @Description:
 */
@RestController
@RequestMapping("/interface")
public class InterfaceController {

    @Autowired
    private InterfaceService interfaceService;

    /**
     * 图书馆-获取用户借阅信息
     * /api/Lend/Get
     *
     * @param page
     * @param readerInfo
     * @param bookSerial
     * @param bookStatus
     * @param sort
     * @param pageSize
     * @return
     */
    @GetMapping("/api/Lend/Get")
    @Authorization
    public LibraryVo getUserList(@RequestParam String page, @RequestParam String readerInfo, @RequestParam String bookSerial,
                                 @RequestParam String bookStatus, @RequestParam String sort, @RequestParam String pageSize) {
        String libraryIp = InitHandler.urlParam.get("libraryIp");
        String url = InitHandler.urlParam.get("libraryUrl");
        if (StrUtil.isEmpty(libraryIp) || StrUtil.isEmpty(url)) {
            throw new BusinessRuntimeException(ResultCode.INTERNAL_SERVER_ERROR);
        }
        if (StrUtil.isNotEmpty(sort)) {
            // 空格处理
            sort = sort.replace("", "%20");
        }
        return interfaceService.getData(libraryIp + url + "?page=" + page + "&readerInfo=" + readerInfo + "&bookSerial=" + bookSerial + "&bookStatus=" + bookStatus
                + "&sort=" + sort + "&pageSize=" + pageSize);
    }
}
