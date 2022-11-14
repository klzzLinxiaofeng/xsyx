package platform.szxyzxx.web.common.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.hikvision.ivms6.cms.ws.IAuthService;
import com.hikvision.ivms6.cms.ws.IAuthServicePortType;

/**
 * @功能描述: 模拟单点登录工具类
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2017年12月7日上午11:18:54
 */
public class SimulationCasUtil {

//	public static void main(String... args) throws Exception {
//		String username = "116001";
//		String password = "123456";
//		String url = "http://127.0.0.1/cas/v1/tickets";
//		String serviceURL = "http://127.0.0.1/sso";
//		validateFromCAS(username, password,url,serviceURL);
//	}

	
	/**
	 * 沿河海康单点登录获取Ticket
	 * @param username 用户名
	 * @param password 密码
	 * @param encryptType 加密方式
	 * @return
	 */
	public static String getCasTicket(String username,String password,String encryptType) {
		IAuthService authService = new IAuthService();
		IAuthServicePortType authServicePortType = authService.getIAuthServiceHttpSoap11Endpoint();
		//海康的加密方式
		if("sha256".equals(encryptType)) {
			password = getSHA256StrJava(password);
		}
		String result = authServicePortType.login(username, password, "", "", "");
		String tgt = readXML(result) ;
		String st = authServicePortType.applyToken(tgt);
		String stS = readXML(st) ;
		System.out.println("单点登录:"+stS);
		return stS;
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
	public static Map<String,String> validateFromCAS(String username, String password,String casServer,String clientService) throws Exception {
		Map<String,String> result = new HashMap<String,String>();
		
		try {
			
			//模拟CAS登陆过程，将用户名，密码通过URLEncoder传送到CAS服务器做验证
			HttpURLConnection hsu = (HttpURLConnection) openConn(casServer);
			String s = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
			s += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
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
				System.out.println(myURL);
				hsu = (HttpURLConnection) openConn(myURL);
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
				while ((line = isr.readLine()) != null) {
					System.out.println(line);
					result.put("lt", line);
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
		return hsu;

	}

	static void closeConn(HttpURLConnection c) {
		c.disconnect();
	}

	
	/**
     *  利用java原生的摘要实现SHA256加密
     * @param str 加密后的报文
     * @return
     */
	private static String getSHA256StrJava(String str){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }    
    
   /**
    * 解析xml字符串，获取各项属性内容
    * @param xmlResult
    * @return
    */
   private static String readXML(String xmlResult) {
	   String obj = "";
       try {
          // String xmlResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><users><user id=\"10001\"><name>张三</name><role>群主</role>&lt;sex>男</sex><content>今天天气真不错！</content><time>2016-04-25 16:43:28</time></user><user id=\"10002\"> <name>李四</name><role>管理员</role><sex>男</sex><content>http://192.168.0.190:9999/beike/data/b3217f668.png</content><time>2016-04-25 16:45:08</time></user></users>";
           // 将xml格式字符串转化为DOM对象
           Document document = DocumentHelper.parseText(xmlResult);
           // 获取根结点对象
           Element rootElement = document.getRootElement();
           // 循环根节点，获取其子节点
           for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
               Element element = (Element) iter.next(); // 获取标签对象
               // 循环第一层节点，获取其子节点
               for (Iterator iterInner = element.elementIterator(); iterInner
                       .hasNext();) {
                   // 获取标签对象
                   Element elementOption = (Element) iterInner.next();
                   
                   List <Attribute>list = elementOption.attributes();
                   for(int i=0;i<list.size();i++) {
                	   Attribute attribute = list.get(i);
                	   if("tgc".equals(attribute.getName())) {
                		   obj = attribute.getValue();
                	   }
                	   if("st".equals(attribute.getName())) {
                		   obj = attribute.getValue();
                	   }
                   }
               }
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
	return obj;
   }
	
	
}

