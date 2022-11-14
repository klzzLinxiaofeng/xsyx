package com.xunyunedu.exception.advice;

import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2020/9/18 14:54
 * @Description: 全局异常处理
 */
@ControllerAdvice
public class ExceptionResolverAdvice {
    private static final Logger log = LoggerFactory.getLogger(ExceptionResolverAdvice.class);

    /**
     * 处理所有不可知异常
     *
     * @param e 异常
     * @return json结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult handleException(Exception e) {
        // 打印异常堆栈信息
        log.error(e.getMessage(), e);
        return ApiResult.of(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 处理所有业务异常
     *
     * @param e 业务异常
     * @return json结果
     */
    @ExceptionHandler(BusinessRuntimeException.class)
    @ResponseBody
    public ApiResult handleOpdRuntimeException(BusinessRuntimeException e) {
        // 不打印异常堆栈信息
        log.error(e.toString());
        return ApiResult.of(e.getResultCode());

    }


    /**
     * 处理参数校验所产生的异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> errorHandler(MethodArgumentNotValidException exception) {
        System.out.println(exception);
        Map<String, String> resultMap = new HashMap<>(2);
        // 对校验异常返回的格式做出想要的返回结果
        FieldError fieldError = exception.getBindingResult().getFieldError();
        resultMap.put("code", "400");
        resultMap.put("msg", fieldError.getDefaultMessage());
        return resultMap;
    }
}
