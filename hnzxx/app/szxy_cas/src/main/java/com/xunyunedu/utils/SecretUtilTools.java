package com.xunyunedu.utils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @功能描述: DES
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年4月25日下午3:38:50
 */
public class SecretUtilTools {
    
	//通过配置文件配置KEY
	private final static String KEY = SysContantsAccessor.getStringProp("DESKEY");
	
	/**
	 * 加密
	 * @param souce
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
    public static String encryptForDES(String souce) throws InvalidKeyException,
            NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(KEY.getBytes("UTF-8"));
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key1 = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, key1, sr);
        // 现在，获取数据并加密
        byte encryptedData[] = cipher.doFinal(souce.getBytes("UTF-8"));
        // 通过BASE64位编码成字符创形式
        String base64Str = new BASE64Encoder().encode(encryptedData);

        return base64Str;
    }

    /**
     * 解密
     * @param souce 原符串
     * @param key 秘钥
     * @return
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decryptForDES(String souce) throws InvalidKeyException,
            NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IOException,
            IllegalBlockSizeException, BadPaddingException {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(KEY.getBytes());
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key1 = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, key1, sr);
        // 将加密报文用BASE64算法转化为字节数组
        byte[] encryptedData = new BASE64Decoder().decodeBuffer(souce);
        // 用DES算法解密报文
        byte decryptedData[] = cipher.doFinal(encryptedData);
        return new String(decryptedData,"UTF-8");
    } 

    
    public static void main(String[] args) throws Exception {
		System.out.println(encryptForDES("微软SB"));
		System.out.println(decryptForDES("40pxc++u0/irnzug6msz6A=="));
	}
}