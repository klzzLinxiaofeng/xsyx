package com.xunyunedu.common;

import com.xunyunedu.exception.ApiResult;

/**
 * 基类控制器，不做请求处理，提供控制器常用方法
 * @author chenjiaxin
 */
public class BaseController {

    private int paramErrorCoude=400;

    public ApiResult paramError(String msg){
        ApiResult result=new ApiResult();
        result.setMsg(msg);
        result.setCode(paramErrorCoude);
        return result;
    }

}
