package com.xunyunedu.util.pwdutil;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: yhc
 * @Date: 2020/9/19 13:56
 * @Description: 密码加密工具--和老系统中相同（二次开发）
 */

public class PwdEncoder {
    private String salt = "GZXTJY";
    private boolean saltEnable = true;
    private boolean strict = false;
    private String algorithm = "MD5";

    public PwdEncoder() {
    }

    public String encodePassword(String rawPass) {
        String saltedPass = this.mergePasswordAndSalt(rawPass, this.salt);
        MessageDigest messageDigest = this.getMessageDigest();

        byte[] digest;
        try {
            digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException var6) {
            throw new IllegalStateException("UTF-8 not supported!");
        }

        return new String(Hex.encodeHex(digest));
    }

    public boolean isPasswordValid(String encPass, String rawPass) {
        String pass1 = "" + encPass;
        String pass2 = this.encodePassword(rawPass);
        return pass1.equals(pass2);
    }

    protected final MessageDigest getMessageDigest() {
        try {
            return MessageDigest.getInstance(this.algorithm);
        } catch (NoSuchAlgorithmException var2) {
            throw new IllegalArgumentException("No such algorithm [" + this.algorithm + "]");
        }
    }

    protected String mergePasswordAndSalt(String password, Object salt) {
        if (password == null) {
            password = "";
        }

        if (!this.saltEnable) {
            return password;
        } else if (this.strict && salt != null && (salt.toString().lastIndexOf("{") != -1 || salt.toString().lastIndexOf("}") != -1)) {
            throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
        } else {
            return salt != null && !"".equals(salt) ? password + "{" + salt.toString() + "}" : password;
        }
    }

    public boolean getStrict() {
        return this.strict;
    }

    public void setStrict(boolean strict) {
        this.strict = strict;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public boolean isSaltEnable() {
        return this.saltEnable;
    }

    public void setSaltEnable(boolean saltEnable) {
        this.saltEnable = saltEnable;
    }
}
