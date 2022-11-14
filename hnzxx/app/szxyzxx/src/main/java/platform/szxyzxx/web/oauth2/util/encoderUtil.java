package platform.szxyzxx.web.oauth2.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2018/1/20.
 */
public class encoderUtil {
    private static String algorithm = "MD5";

    private static String salt = "GZXTJY";

    public static String encoder(String key) {

        key = mergeKeyAndSalt(key, salt);
        MessageDigest messageDigest = getMessageDigest();
        try {
            byte[] bytes = messageDigest.digest(key.getBytes("UTF-8"));
            return new String(Hex.encodeHex(bytes));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }

    private static String mergeKeyAndSalt(String key, String salt){
        if (key == null) {
            key = "";
        }
        if (salt == null || "".equals(salt)) {
            return key;
        } else {
            return key + "{" + salt.toString() +  "}";
        }
    }

}
