package platform.education.paper.util;

import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * 解密算法
 * @author quan
 *
 */
public class AESHelper {
	
	/**创建随机密码*/
	public static String createRandomPassword(int passwordLength)
	{
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		String passwrodChar = "@$ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for(byte i = 0; i < passwordLength; i++)
			sb.append(passwrodChar.charAt(rd.nextInt(64)));
		return sb.toString();
	}
	
	/** 
	 * 加密 
	 *  
	 * @param content 需要加密的内容 
	 * @param password 加密密码 1FjPiQzcl@rec5ai0s$7YDOCtjkfQisX
	 * @return 
	 */  
	public static String encrypt(String text) throws Exception {  
	        if(text == null || text == "")
	        	return null;
	        Random rd = new Random();
	        byte[] keys = new byte[16];
	        rd.nextBytes(keys);
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keys, "AES"));
	        byte[] array = cipher.doFinal(text.getBytes("utf-8"));
	        byte[] result = new byte[array.length + keys.length];
	        System.arraycopy(array, 0, result, 0, array.length);
	        System.arraycopy(keys, 0, result, array.length, keys.length);
	        //return Byte.toString(result);
	        //return Base64.getMimeEncoder().encodeToString(result);
	       
	        return  Base64.encode(result);
	}
	
	/**解密 
	 * @param content  待解密内容 
	 * @param password 解密密钥 
	 * @return 
	 */  
	public static String decrypt(String text) throws Exception {  
		if(text == null || text == "")
        	return null; 
		
		//byte[] buffer = Base64.getMimeDecoder().decode(text);
		byte[] buffer = Base64.decode(text);
		byte[] key = new byte[16];
		byte[] data = null;
		if(buffer != null && buffer.length > 0){
			data = new byte[buffer.length - 16];
			System.arraycopy(buffer, 0, data, 0, data.length);
			System.arraycopy(buffer, data.length, key, 0, key.length);
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
			data = cipher.doFinal(data);
			
		}
		String result = new String("");
		if(data != null){
			result = new String(data, "utf-8");
		}
		
	    return result;
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(decrypt("GsoaCcX5aOeEmj9qNzMkALKpMWktmLvG+e+zfNQHWZ6SQu/onh1Tx1tDoIssQCEP"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
