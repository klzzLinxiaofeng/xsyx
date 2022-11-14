package platform.szxyzxx.web.office.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class AESHelper {

    // 使用AES-128-CBC加密模式，key需要为16位，key和iv可以相同！
    private static final String KEY = "bRrJ!EJe#8qKzS9C";

    private static final String IV = "qTYvo3u$IiUa&uNG";

    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @param iv   加密iv
     * @return 加密的结果
     * @throws Exception
     */
    public static String encrypt(String data, String key, String iv) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");// "算法/模式/补码方式"
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv   解密iv
     * @return 解密的结果
     * @throws Exception
     */
    public static String decrypt(String data, String key, String iv) throws Exception {
        try {
            byte[] encrypted = new Base64().decode(data.getBytes(StandardCharsets.UTF_8));

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] original = cipher.doFinal(encrypted);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用默认的key和iv加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        return encrypt(data, KEY, IV);
    }

    /**
     * 使用默认的key和iv解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) throws Exception {
        return decrypt(data, KEY, IV);
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("userId", "114764");
        jsonObject.put("schoolId", 315);
//        jsonObject.put("name", "活动名称");
//        jsonObject.put("timestamp", new Date().getTime());
//        jsonObject.put("timestamp", 1613697672294L);
//        jsonObject.put("pageSize","10");
//        jsonObject.put("currentPage","1");
        System.out.println("\"佩奇\"");
        String data = encrypt("\"佩奇\"");

        System.out.println(data);
        data = URLEncoder.encode(data, "utf-8");
        System.out.println(data);
        data = URLDecoder.decode(data, "utf-8");
        System.out.println(data);
        String s = decrypt(data);
        System.out.println(s);
    }
}