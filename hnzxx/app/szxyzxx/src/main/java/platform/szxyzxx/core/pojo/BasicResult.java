package platform.szxyzxx.core.pojo;

public class BasicResult {

    private int code;
    private String msg;
    private Object data;

    public BasicResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BasicResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public static BasicResult success(){
        return new BasicResult(200,null,null);
    }

    public static BasicResult success(Object data){
        return new BasicResult(200,null,data);
    }

    public static BasicResult error(String msg){
        return new BasicResult(400,msg,null);
    }

}
