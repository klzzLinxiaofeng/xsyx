package platform.szxyzxx.schoolbus.pojo;

public class BusResult {

    private Integer code;
    private String msg;

    public BusResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BusResult(Integer code) {
        this.code = code;
        this.msg = "成功";
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
}
