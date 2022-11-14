package platform.esb.rest.common.util;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by clouds on 16/11/1.
 */
public class SignEncoder {

    /**
     * 默认盐salt值
     */
    private String salt = "ZHJYY";
    /**
     * 是否启用salt
     */
    private boolean saltEnable = true;
    /**
     * 带盐MD5加密是否严密,默认false
     */
    private boolean strict = false;
    /**
     * 加密算法方式
     */
    private String algorithm = "MD5";

    public SignEncoder() {}

    public SignEncoder(String salt, boolean saltEnable, boolean strict) {
        this.salt = salt;
        this.saltEnable = saltEnable;
        this.strict = strict;
    }


    public String encodeSign(String sign) {
        String saltedSign = mergeSignAndSalt(sign, salt);
        MessageDigest messageDigest = getMessageDigest();
        byte[] digest;
        try {
            digest = messageDigest.digest(saltedSign.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 not supported!");
        }
        return new String(Hex.encodeHex(digest));
    }

    public boolean isSignValid(String encSign, String rawSign) {
        String sign1 = "" + encSign;
        String sign2 = encodeSign(rawSign);
        return sign1.equals(sign2);
    }

    /**
     * 算法,例如 DSA, RSA, MD5 or SHA-1;已经设置为MD5
     *
     * @return
     */
    protected final MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [" + algorithm + "]");
        }
    }

    /**
     * 合并密码sign和盐salt,严密时盐salt字符串不能存在<code>"{"</code>或<code>"}"</code>
     *
     * @param sign
     *            签名原料
     * @param salt
     *            盐
     * @return 返回合并字符串:sign{salt}
     */
    protected String mergeSignAndSalt(String sign, Object salt) {
        if (sign == null) {
            sign = "";
        }
        if (saltEnable) {
            if (strict && salt != null) {
                if (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1) {
                    throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
                }
            }
            if ((salt == null) || "".equals(salt)) {
                return sign;
            } else {
                return sign + "{" + salt.toString() + "}";
            }
        }
        return sign;
    }

    /**
     * 获取带盐MD5加密是否严密,ture为严密加密
     *
     * @return
     */
    public boolean getStrict() {
        return strict;
    }

    /**
     * 设置带盐MD5加密是否严密,ture为严密加密
     *
     * @return
     */
    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    /**
     * 获得盐值salt
     *
     * @return 盐值salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐salt值,一般为用户名
     *
     * @return void
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public boolean isSaltEnable() {
        return saltEnable;
    }

    public void setSaltEnable(boolean saltEnable) {
        this.saltEnable = saltEnable;
    }

}
