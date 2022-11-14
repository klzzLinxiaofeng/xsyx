 
package platform.szxyzxx.web.oa.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 添加说明 
 * @author sky
 * @version 1.0 2014-3-6
 */
public class JsonWriteUtils {
    
    public static void setJson(JSONObject json,HttpServletResponse response){
        try{
            response.setContentType("text/json;charset=utf-8");
            byte[] jsonBytes = json.toString().getBytes("utf-8");

            response.setContentLength(jsonBytes.length);
            response.getOutputStream().write(jsonBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception ex) {
            // TODO: handle exception
        }finally{
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                 
            }
            
        }
       
    }
    
    public static void setJSONArray(JSONArray json,HttpServletResponse response){
        try{
            response.setContentType("text/json;charset=utf-8");
            byte[] jsonBytes = json.toString().getBytes("utf-8");

            response.setContentLength(jsonBytes.length);
            response.getOutputStream().write(jsonBytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (Exception ex) {
            // TODO: handle exception
        }finally{
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (IOException e) {
                 
            }
            
        }
       
    }

}
