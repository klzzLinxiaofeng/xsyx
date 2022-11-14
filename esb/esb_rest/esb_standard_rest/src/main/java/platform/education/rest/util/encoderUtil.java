package platform.education.rest.util;

import org.jboss.resteasy.util.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具
 * Created by Administrator on 2017/11/16.
 */
public class encoderUtil {

    private static String algorithm = "MD5";

    public static String encoder(String key) {

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

}
