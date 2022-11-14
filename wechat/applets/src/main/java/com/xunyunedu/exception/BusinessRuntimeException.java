package com.xunyunedu.exception;

/**
 * @author: yhc
 * @Date: 2020/9/18 14:45
 * @Description: 自定义业务异常类try
 */
public class BusinessRuntimeException extends RuntimeException {

    /**
     * 结果码
     */
    private Integer code;

    /**
     * 结果码描述
     */
    private String msg;

    /**
     * 结果码枚举
     */
    private ResultCode resultCode;


    public BusinessRuntimeException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.resultCode = resultCode;
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

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "BusinessRuntimeException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}