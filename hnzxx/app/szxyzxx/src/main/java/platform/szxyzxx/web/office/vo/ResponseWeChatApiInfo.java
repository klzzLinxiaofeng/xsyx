package platform.szxyzxx.web.office.vo;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

public class ResponseWeChatApiInfo implements Serializable {

    public static final int SUCCEED = 0;
    public static final int FAIL = 1;
    private int result = SUCCEED;
    private String msg = "";
    private Object data = new JSONObject();

    public ResponseWeChatApiInfo() {
    }

    public ResponseWeChatApiInfo(int result) {
        this.result = result;
    }

    public ResponseWeChatApiInfo(String msg) {
        this.msg = msg;
    }

    public ResponseWeChatApiInfo(int result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public ResponseWeChatApiInfo(Object data) {
        this.data = data;
    }

    public ResponseWeChatApiInfo(String msg, Object data) {
        this.msg = msg;
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
