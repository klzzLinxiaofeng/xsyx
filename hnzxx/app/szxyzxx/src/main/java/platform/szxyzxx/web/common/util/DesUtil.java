package platform.szxyzxx.web.common.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密算法工具类
 * 默认密码:admin
 * 默认key:jlddeskey
 */
public class DesUtil {
    private final static String DES = "DES";
    private final static String default_password = "admin";
    private final static String default_desKey = "jlddeskey";

    static byte[] IV = { (byte)0x12, (byte)0x34, (byte)0x56, (byte)0x78, (byte) 0x90, (byte)0xAB, (byte)0xCD, (byte)0xEF };

    //测试
    public static void main(String[] args) throws Exception {
        System.err.println(encrypt(default_password, default_desKey));
        System.err.println(encryptAndUrlEncode(default_password, default_desKey));
    }

    /**
     * Description 根据键值进行加密,加密结束之后对产生的密码进行UrlEncode
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encryptAndUrlEncode(String data, String key) throws Exception {
        if(data == null || "".equals(data)){
            data = default_password;
        }
        if(key == null || "".equals(key)){
            key = default_desKey;
        }
        byte[] bt = EncodeDES(data, key);
        String strs = new BASE64Encoder().encode(bt);
        return J2C(strs);
    }

    public static String J2C(String data) throws Exception{
       String source = "";
        if(data != null){
            for(int i = 0; i < data.length();i++){
                String ind = data.substring(i,i+1);
                String aft =  java.net.URLEncoder.encode(data.substring(i,i+1),"utf-8");
                if(ind.equals(aft)){
                    source += aft;
                }else{
                    source += aft.toLowerCase();
                }

            }
        }
        return source;
    }

    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        if(data == null || "".equals(data)){
            data = default_password;
        }
        if(key == null || "".equals(key)){
            key = default_desKey;
        }
        byte[] bt = EncodeDES(data, key);
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }

    /**
     * Description 根据键值进行加密
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static byte[] EncodeDES(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(IV);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        return cipher.doFinal(message.getBytes("UTF-8"));
    }

    /**
     * 二行制转字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString();
    }

    private static byte[] hex2byte(byte[] b) {
        if((b.length%2)!=0)
            throw new IllegalArgumentException();
        byte[] b2 = new byte[b.length/2];
        for (int n = 0; n < b.length; n+=2) {
            String item = new String(b,n,2);
            b2[n/2] = (byte)Integer.parseInt(item,16);
        }
        return b2;
    }
}