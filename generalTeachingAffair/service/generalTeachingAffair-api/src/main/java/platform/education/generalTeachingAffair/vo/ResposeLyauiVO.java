package platform.education.generalTeachingAffair.vo;

/**
 * @author: yhc
 * @Date: 2021/5/8 15:55
 * @Description: layui 上传文件返回值
 */
public class ResposeLyauiVO {

    /**
     * code : 0
     * msg :
     * data : {"src":"http://cdn.layui.com/123.jpg"}
     */

    private int code;
    private String msg;
    private String data;

    public ResposeLyauiVO() {
    }

    public ResposeLyauiVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResposeLyauiVO{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

