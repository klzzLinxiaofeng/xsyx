package platform.education.commonResource.web.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SzxyHttpClientRequest {
	private static Logger log = LoggerFactory.getLogger(SzxyHttpClientRequest.class);
	
	private static HttpClient httpClient = new HttpClient();
	
	public static JSONObject doPostRequest(String url,Map<String,Object> map){
		PostMethod postMethod = new PostMethod(url);
		HttpMethodParams params = new HttpMethodParams();
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		params.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		params.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		postMethod.setParams(params);
		ObjectMapper mapper = new ObjectMapper();
		String param = null;
		int statusCode = 0;
		JSONObject jsonObject = new JSONObject();
		try {
			param = mapper.writeValueAsString(map);
			RequestEntity requestEntity = new StringRequestEntity(param,"text/xml","UTF-8");
			postMethod.setRequestEntity(requestEntity); 
			statusCode = httpClient.executeMethod(postMethod);
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("executeMethod failed: "
						+ postMethod.getStatusLine());
			   }
			   byte[] responseBody = postMethod.getResponseBody();
			   //处理内容
			   jsonObject = new JSONObject(new String(responseBody));
			   
		} catch (HttpException e) {
			log.error("Do request server error:", e);
			jsonObject = null;
		} catch (IOException e) {
			log.error("Do request server error:", e);
			jsonObject = null;
		}finally {
		   //释放连接
			postMethod.releaseConnection();
		}
		return jsonObject;
	}
	public static JSONObject doGetRequest(String url,Map<String,Object> map){
		GetMethod getMethod = new GetMethod(url);
		HttpMethodParams params = new HttpMethodParams();
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		params.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
		params.setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
		getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		getMethod.setParams(params);
//		ObjectMapper mapper = new ObjectMapper();
//		String param = null;
		int statusCode = 0;
		JSONObject jsonObject = new JSONObject();
		try {
//			param = mapper.writeValueAsString(map);
//			RequestEntity requestEntity = new StringRequestEntity(param,"text/xml","UTF-8");
//			postMethod.setRequestEntity(requestEntity); 
			statusCode = httpClient.executeMethod(getMethod);
			
			if (statusCode != HttpStatus.SC_OK) {
				log.error("executeMethod failed: "
						+ getMethod.getStatusLine());
			}
			String rtnJson = null;
			Header[] header = getMethod.getResponseHeaders("Content-Encoding");
	        if(header!=null&&header.length>0){
	            if("gzip".equals(header[0].getValue())){
	              rtnJson = new String(uncompress(getMethod.getResponseBody()));
	            }else{
	              rtnJson = new String(getMethod.getResponseBody(), "UTF-8");
	            }
	        }else{
	            rtnJson = new String(getMethod.getResponseBody(), "UTF-8");
	        }
//			   byte[] responseBody = postMethod.getResponseBody();
			   //处理内容
	        	if(rtnJson != null && !rtnJson.equals("")){
	        		jsonObject = new JSONObject(rtnJson);
	        	}
			
		} catch (HttpException e) {
			log.error("Do request server error:", e);
			jsonObject = null;
		} catch (IOException e) {
			log.error("Do request server error:", e);
			jsonObject = null;
		}finally {
			//释放连接
			getMethod.releaseConnection();
		}
		return jsonObject;
	}
	
	@SuppressWarnings("resource")
	private static String uncompress(byte[] bytes) throws IOException {
	    if (bytes == null || bytes.length == 0) {
	      return "";
	    }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
	    GZIPInputStream gunzip = new GZIPInputStream(in);
	    byte[] buffer = new byte[256];
	    int n;
	    while ((n = gunzip.read(buffer)) >= 0) {
	      out.write(buffer, 0, n);
	    }
	    return out.toString("utf-8");
	  }
}
