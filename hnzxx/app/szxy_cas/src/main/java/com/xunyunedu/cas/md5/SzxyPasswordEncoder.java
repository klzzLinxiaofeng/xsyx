package com.xunyunedu.cas.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.codec.Hex;
import org.jasig.cas.authentication.handler.PasswordEncoder;

public class SzxyPasswordEncoder implements PasswordEncoder {

	/**
	 * 默认盐salt值
	 */
	private String salt = "GZXTJY";
	/**
	 * 带盐MD5加密是否严密,默认false
	 */
	private boolean strict = false;

	/**
	 * 算法,例如 DSA, RSA, MD5 or SHA-1;已经设置为MD5
	 */
	private String algorithm = "MD5";

	private String characterEncoding = "UTF-8";

	@Override
	public String encode(String password) {
		String saltedPass = mergePasswordAndSalt(password, salt);
		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
			digest = messageDigest.digest(saltedPass
					.getBytes(characterEncoding));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}
		System.out.println(new String(Hex.encode(digest)));
		return new String(Hex.encode(digest));
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
			throw new IllegalArgumentException("No such algorithm ["
					+ algorithm + "]");
		}
	}

	/**
	 * 合并密码password和盐salt,严密时盐salt字符串不能存在<code>"{"</code>或<code>"}"</code>
	 * 
	 * @param password
	 *            密码
	 * @param salt
	 *            盐
	 * @return 返回合并字符串:password{salt}
	 */
	protected String mergePasswordAndSalt(String password, Object salt) {
		if (password == null) {
			password = "";
		}
		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1)
					|| (salt.toString().lastIndexOf("}") != -1)) {
				throw new IllegalArgumentException(
						"Cannot use { or } in salt.toString()");
			}
		}
		if ((salt == null) || "".equals(salt)) {
			return password;
		} else {
			return password + "{" + salt.toString() + "}";
		}
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

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

}
