package platform.szxyzxx.dto.seewo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * seewo响应信息
 * @author chenjiaxin
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicResponseResult {
    private String code;
    private String message;
    private Object data;
    private Integer statusCode;
    private String originJson;
    public  String getCode() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOriginJson() {
        return originJson;
    }

    public void setOriginJson(String originJson) {
        this.originJson = originJson;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<Map<String,Object>> getPagingResult(){
        if(data != null ) {
            if (data instanceof Map) {
                Object pagingResult =( (Map<String,Object>)data).get("result");
                if (pagingResult != null) {
                    return (List<Map<String, Object>>) pagingResult;
                }
            }else{
                List<Map<String,Object>> list= (List<Map<String,Object>>)data;
                if(list!=null){
                    return list;
                }
            }
        }
        return new ArrayList<>(0);
    }

    public int getPagingTotalCount(){
        if(data!=null && data instanceof Map){
            return (Integer) ((Map<String,Object>)data).get("totalCount");
        }
        return 0;
    }

}
