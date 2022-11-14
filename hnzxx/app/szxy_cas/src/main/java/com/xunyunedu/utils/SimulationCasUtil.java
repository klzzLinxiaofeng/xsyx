package com.xunyunedu.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;




/**
 * @功能描述: 模拟单点登录工具类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年12月7日上午11:18:54
 */
public class SimulationCasUtil {

	public static void main(String... args) throws Exception {
		String username = "116001";
		String password = "123456";
		String url = "http://cas.test.studyo.cn/v1/tickets";
		String serviceURL = "http://test.studyo.cn/sso";
		
	//	post("https://szxycas.studyo.cn/v1/tickets");
		validateFromCAS(username, password,"","",url,serviceURL);
	}

	
	 private static class TrustAnyTrustManager implements X509TrustManager {
		    
	        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        }
	    
	        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	        }
	    
	        public X509Certificate[] getAcceptedIssuers() {
	            return new X509Certificate[]{};
	        }
	    }
	    
	    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
	        public boolean verify(String hostname, SSLSession session) {
	            return true;
	        }
	    }
	
	
	/**
	 * 
	 * @param username 用户名
	 * @param password 密码
	 * @param casServer CAS	服务器端URL
	 * @param clientService 客户端访问地址
	 * @return LT令牌
	 * @throws Exception
	 */
	public static Map<String,String> validateFromCAS(String username, String password,String encryptedPassword,String schoolId,String casServer,String clientService) throws Exception {
		Map<String,String> result = new HashMap<String,String>();
		
		try {
			System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
			//模拟CAS登陆过程，将用户名，密码通过URLEncoder传送到CAS服务器做验证
			 //增加下面两行代码
			HttpURLConnection hsu = (HttpURLConnection) openConn(casServer);
			 if (hsu instanceof HttpsURLConnection)  {
				// SSLContext.getInstance(“TLS”);
		        	SSLContext sc = SSLContext.getInstance("TLS");
		        	sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
		           //new SSLSocketFactory(sc, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		            
		        	((HttpsURLConnection) hsu).setSSLSocketFactory(sc.getSocketFactory());
		        	((HttpsURLConnection) hsu).setHostnameVerifier(new TrustAnyHostnameVerifier());
		        }
			String s = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
			if(schoolId != null && !"".equals(schoolId)) {
				s += "&" + URLEncoder.encode("schoolId", "UTF-8") + "=" + URLEncoder.encode(schoolId, "UTF-8");
			}
			if(password != null && !"".equals(password)){
				s += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
			}
			
			if(encryptedPassword != null && !"".equals(encryptedPassword)){
				s += "&" + URLEncoder.encode("encryptedPassword", "UTF-8") + "=" + URLEncoder.encode(encryptedPassword, "UTF-8");
			}
			System.out.println(hsu.getRequestMethod());
			System.out.println(s);
			OutputStreamWriter out = new OutputStreamWriter(hsu.getOutputStream());
			BufferedWriter bwr = new BufferedWriter(out);
			bwr.write(s);
			bwr.flush();
			bwr.close();
			out.close();
			
			String tgt = hsu.getHeaderField("location");
			System.out.println(hsu.getResponseCode());
			//判断服务器返回状态码是否为201
			if (tgt != null && hsu.getResponseCode() == 201) {
				System.out.println(tgt);
				
				//提取TGT令牌值
				System.out.println("Tgt is : " + tgt.substring(tgt.lastIndexOf("/") + 1));
				tgt = tgt.substring(tgt.lastIndexOf("/") + 1);
				result.put("tgt", tgt);
				bwr.close();
				closeConn(hsu);
				
				//通过URLEncoder加密客户需要访问的地址
				String encodedServiceURL = URLEncoder.encode("service", "utf-8") + "="
						+ URLEncoder.encode(clientService, "utf-8");
				System.out.println("Service url is : " + encodedServiceURL);
				
				//通过URLEncoder加密客户需要访问的地址作为参数传给CAS服务器验证生成LT令牌。
				String myURL = casServer + "/" + tgt;
				result.put("tgt", tgt);
				System.out.println(myURL);
				hsu = (HttpURLConnection) openConn(myURL);
				
				 if (hsu instanceof HttpsURLConnection)  {
			        	SSLContext sc = SSLContext.getInstance("TLS");
			        	sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
			        	((HttpsURLConnection) hsu).setSSLSocketFactory(sc.getSocketFactory());
			        	((HttpsURLConnection) hsu).setHostnameVerifier(new TrustAnyHostnameVerifier());
			        	//new SSLSocketFactory(sc, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			        }
				out = new OutputStreamWriter(hsu.getOutputStream());
				bwr = new BufferedWriter(out);
				bwr.write(encodedServiceURL);
				bwr.flush();
				bwr.close();
				out.close();

				System.out.println("Response code is:  " + hsu.getResponseCode());

				BufferedReader isr = new BufferedReader(new InputStreamReader(hsu.getInputStream()));
				String line;
				System.out.println(hsu.getResponseCode());
				//提取返回的LT令牌
				String ss = null;
				while ((line = isr.readLine()) != null) {
					System.out.println(line);
					result.put("lt", line);
					ss = clientService+"?ticket="+line;
					System.out.println(ss);
				}
				isr.close();
				hsu.disconnect();

			} 

		} catch (MalformedURLException mue) {
			mue.printStackTrace();
			throw mue;

		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		
		return result;

	}

	static URLConnection openConn(String urlk) throws MalformedURLException, IOException {
		URL url = new URL(urlk);
		HttpURLConnection hsu = (HttpURLConnection) url.openConnection();
		hsu.setDoInput(true);
		hsu.setDoOutput(true);
		hsu.setRequestMethod("POST");
		
		//hsu.set
		return hsu;

	}
	
	

	static void closeConn(HttpURLConnection c) {
		c.disconnect();
	}

}

