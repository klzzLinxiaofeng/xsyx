package platform.education.rest.common;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/11/15.
 */
public class ResponseDto<T> implements Serializable {

    private static final long serialVersionUID = 2421048094118048863L;

    /**
     *  操作状态值
     *  success，fail
     */
    private String result;

    /**
     *  版本号
     *  {TimeStamp}&{xxxID}
     */
    private String version;

    /**
     *  对象
     */
    private T data;

    public ResponseDto () {

    }

    public ResponseDto (String result, String version, T data) {
        this.result = result;
        this.version = version;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "result:'" + result +
                ", version:'" + version +
                ", data:" + data.toString() +
                '}';
    }
}
