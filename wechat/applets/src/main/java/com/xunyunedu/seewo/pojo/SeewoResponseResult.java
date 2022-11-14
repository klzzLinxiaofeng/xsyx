package com.xunyunedu.seewo.pojo;

import java.util.Map;

/**
 * seewo响应信息
 * @author chenjiaxin
 */
public class SeewoResponseResult {
    private String exception;
    private String code;
    private Integer statusCode;
    private Map<String,Object> data;
    private String message;
    private String originJson;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getOriginJson() {
        return originJson;
    }

    public void setOriginJson(String originJson) {
        this.originJson = originJson;
    }
}
