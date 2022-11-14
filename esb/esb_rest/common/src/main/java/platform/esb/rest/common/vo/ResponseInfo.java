package platform.esb.rest.common.vo;

import java.io.Serializable;

/**
 * Created by clouds on 16/11/1.
 */
public class ResponseInfo implements Serializable {

    private String status;

    private String code;

    private String msg;




    public ResponseInfo() {

    }

    public ResponseInfo(String status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseInfo{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
