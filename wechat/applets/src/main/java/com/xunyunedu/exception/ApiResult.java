package com.xunyunedu.exception;

/**
 * @author: yhc
 * @Date: 2020/9/18 14:43
 * @Description: 统一的返回结果类
 */
public class ApiResult<T> {
    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;
    /**
     * 1为教师端，2为家长端
     */

    private Integer status;

    /**
     * 数据
     */
    private T data;

    /**
     * 数据2
     */
    private T data2;

    public ApiResult() {

    }

    public ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public ApiResult(ResultCode resultCode, Integer status) {
        this.code = resultCode.getCode();
        this.status = status;
    }

    public ApiResult(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ApiResult(ResultCode resultCode, Integer status, T data, T data2) {
        this.code = resultCode.getCode();
        this.status = status;
        this.msg = resultCode.getMsg();
        this.data = data;
        this.data2 = data2;
    }

    public ApiResult(ResultCode resultCode, T data, T data2) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
        this.data2 = data2;
    }

    public ApiResult(ResultCode resultCode, Integer status, T data) {
        this.code = resultCode.getCode();
        this.status = status;
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 生成一个ApiResult对象, 并返回
     *
     * @param resultCode
     * @return
     */
    public static ApiResult of(ResultCode resultCode) {
        return new ApiResult(resultCode);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData2() {
        return data2;
    }

    public void setData2(T data2) {
        this.data2 = data2;
    }


    public static ApiResult error(String msg){
        ApiResult apiResult=new ApiResult();
        apiResult.setCode(400);
        apiResult.setMsg(msg);
        return apiResult;
    }


    public static ApiResult success(Object data){
        ApiResult apiResult=new ApiResult();
        apiResult.setCode(200);
        apiResult.setData(data);
        return apiResult;
    }


}
