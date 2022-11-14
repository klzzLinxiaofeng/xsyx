package platform.education.rest.bp.service.util;
/*
 
 Copyright 2015 冼嘉贤
 
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import platform.education.im.dao.PushSubscriptionDao;
import platform.education.im.model.MsgBody;
import platform.education.im.model.PushData;
import platform.education.im.utils.MsgBody2JSON;
import platform.education.im.vo.PushSubscriptionCondition;
import platform.education.rest.util.SysContantsAccessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.List;
import java.util.Properties;


public class Push {
	private static final Logger log = LoggerFactory.getLogger(Push.class);
	
	/*****************************配置文件********************************************************/
	private static Properties cachePops = new Properties();
	private static final String SYSTEM_FILE = "im-config.properties";

	static {
		InputStream inStream = null;
		try {
			inStream = SysContantsAccessor.class.getClassLoader().getResourceAsStream(SYSTEM_FILE);
			cachePops.load(inStream);
		} catch (Exception ex) {
			throw new RuntimeException("读取配置文件出错了..." + ex.getMessage(), ex);
		} finally {
			if(inStream != null) {
				try {
					inStream.close();
					inStream = null;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static Properties getProperty() {
		return cachePops;
	}

	public static String getStringProp(String key) {
		return cachePops.getProperty(key);
	}
	
	public static Integer getIntegerProp(String key) {
		return Integer.parseInt(cachePops.getProperty(key));
	}
	
	public static boolean getBooleanProp(String key) {
		return new Boolean(cachePops.getProperty(key));
	}
	/*************************************************************************************/
	
//	@Autowired
//	@Qualifier("pushSubscriptionDao")
//	private static PushSubscriptionDao pushSubscriptionDao;
	
	
	
	private final static String XJXP_HOST_KEY = "XJXP_HOST";
	public final static String XJXP_HOST = getStringProp(XJXP_HOST_KEY);
	
	private final static String XJXP_PORT_KEY = "XJXP_PORT";
	public final static String XJXP_PORT = getStringProp(XJXP_PORT_KEY);
	
	private static int version = 1; 
	private static int appId = 1;
	private static int timeout = 5000;
	
	private static String host = XJXP_HOST;
	private static int port = Integer.parseInt(XJXP_PORT);
	private static Socket socket;
	private static InputStream in ;
	private static OutputStream out ;
	
	static{
		try {
			initSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void initSocket()throws Exception{
		socket = new Socket(host, port);
		socket.setSoTimeout(timeout);
		in = socket.getInputStream();
		out = socket.getOutputStream();
	}
	
	public static void close() throws Exception{
		if(socket == null){
			return;
		}
		socket.close();
	}
	
	public static void setVersion(int version) throws IllegalArgumentException{
		if(version < 1 || version > 255){
			throw new IllegalArgumentException("version不挣钱");
		}
		Push.version = version;
	}

	public static int getVersion(){
		return version;
	}

	public static void setAppId(int appId) throws IllegalArgumentException{
		if(appId < 1 || appId > 255){
			throw new IllegalArgumentException("appId错误");
		}
		Push.appId = appId;
	}
	
	public static int getAppId(){
		return appId;
	}
	
	private static boolean checkUuidArray(byte[] uuid) throws IllegalArgumentException{
		if(uuid == null || uuid.length != 16){
			throw new IllegalArgumentException("uuid不能为空");
		}
		return true;
	}
	
	public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
	
	public static String md5(String encryptStr) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(encryptStr.getBytes("UTF-8"));
		byte[] digest = md.digest();
		StringBuffer md5 = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			md5.append(Character.forDigit((digest[i] & 0xF0) >> 4, 16));
			md5.append(Character.forDigit((digest[i] & 0xF), 16));
		}

		encryptStr = md5.toString();
		return encryptStr;
	}

	public static void pushMessageByMsgBody(List<String> accountNameList,String appKey,String data_code,
			PushData pushData,PushSubscriptionDao pushSubscriptionDao) throws Exception{
		MsgBody msgBody = null;
		PushSubscriptionCondition condition = new PushSubscriptionCondition();
		condition.setAppKey(appKey);
		condition.setObjectCode(data_code);
		Long count = pushSubscriptionDao.count(condition);
		//List<String> imAccountAppList = pushSubscriptionDao.findImAccountAppByCode(data_code);
//		for(String key:imAccountAppList){
		if(count > 0){
			for(String accountName:accountNameList){
				msgBody = new MsgBody();
				msgBody.setApp_key(appKey);
				msgBody.setAccount(accountName);
				msgBody.setReceiver_id(accountName.toString());
				msgBody.setData_type("txt");
				BeanUtils.copyProperties(pushData, msgBody);
				MsgBody2JSON mj = new MsgBody2JSON(msgBody);
				try{
					pushMessage(mj,accountName);
				}catch(Exception e){
					out.close();
					initSocket();
					pushMessage(mj,accountName);
				}
			}
		}	
	}
	
	
	
	public static boolean pushMessage(MsgBody2JSON mj,String accountName) throws Exception{
		
		byte[] data = mj.getMsg().getBytes("UTF-8");
		byte[] uuid = hexStringToByteArray(md5(accountName));
		checkUuidArray(uuid);
		if(data == null){
			throw new NullPointerException("内容为空，错误");
		}
		if(data.length == 0 || data.length > 50000){
			throw new IllegalArgumentException("数据不能太大");
		}
		byte[] dataLen = new byte[2];
		ByteBuffer.wrap(dataLen).putChar((char)data.length);
		out.write(version);
		out.write(appId);
		out.write(32);
		out.write(uuid);
		out.write(dataLen);
		
		out.write(data);
		out.flush();
		
		byte[] b = new byte[1];
		in.read(b);
		if((int)b[0] == 0){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	public static boolean pushMessage(String msg) throws Exception{
		
		byte[] data = msg.getBytes("UTF-8");
		byte[] uuid = hexStringToByteArray(md5("05ce997ad51c4adaaeaa9724df3f40b7"));
		checkUuidArray(uuid);
		if(data == null){
			throw new NullPointerException("内容为空，错误");
		}
		if(data.length == 0 || data.length > 50000){
			throw new IllegalArgumentException("数据不能太大");
		}
		byte[] dataLen = new byte[2];
		ByteBuffer.wrap(dataLen).putChar((char)data.length);
		out.write(version);
		out.write(appId);
		out.write(32);
		out.write(uuid);
		out.write(dataLen);
		
		out.write(data);
		out.flush();
		
		byte[] b = new byte[1];
		in.read(b);
		if((int)b[0] == 0){
			return true;
		}else{
			return false;
		}
		
		
	}
	
	
	public static void main(String[] args) {
		try {
			System.err.println(pushMessage("111111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
