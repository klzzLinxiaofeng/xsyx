package platform.szxyzxx.web.common.util;

import java.util.Random;

public class Security {
	public static void main(String[] args)
	{
		String pwd = createRandomPassword();
		System.out.println(pwd);
		String pwd1 = encrypt(pwd);
		System.out.println(pwd1);
		String pwd2 = decrypt(pwd1);
		System.out.println(pwd2);
	}
	
	public static String createRandomPassword()
	{
		Random rd = new Random();
		StringBuilder sb = new StringBuilder();
		String passwrodChar = "@$ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		for(byte i = 0; i < 15; i++)
			sb.append(passwrodChar.charAt(rd.nextInt(64)));
		
		return sb.toString();
	}
	
	public static String encrypt(String pwd)
	{
		if(pwd == null || pwd.isEmpty() || pwd.length() != 15)
			return null;
		
		int[] buffer = new int[15];
		int[] ch = new int[20];
		for(int i = 0; i < 15; i++)
			buffer[i] = pwd.charAt(i) << 1;
		for(int i = 0; i < 5; i++)
		{
			int p = i * 3;
			int c = i * 4;
			
			ch[c] = buffer[p] & 0xFC;
			ch[c+1] =(buffer[p] & 0x03) | (buffer[p+1] & 0xF0);
			ch[c+2] = (buffer[p+1] & 0x0F)|(buffer[p+2] & 0xC0);
			ch[c+3] = buffer[p+2] & 0x3F;
		}
		
		return toHex(ch);
	}
	
	public static String decrypt(String pwd)
	{
		if(pwd == null || pwd.isEmpty() || pwd.length() != 40)
			return null;
		
		int buffer[] = toChar(pwd);
		int ch[] = new int[15];
		for(int i = 0; i < 5; i++)
		{
			int p = i * 4;
			int c = i * 3;
			
			ch[c] = (buffer[p] & 0xFC) | (buffer[p+1] & 0x03);
			ch[c+1] = (buffer[p+1] & 0xF0) | (buffer[p+2] & 0x0F);
			ch[c+2] = (buffer[p+2] & 0xC0) | (buffer[p+3] & 0x3F);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < ch.length; i++)
			sb.append((char)(ch[i]>>1));
		
		return sb.toString();
	}
	
	private static String toHex(int[] pwd)
	{
		if(pwd == null || pwd.length <= 0)
			return null;
		
		StringBuilder sb = new StringBuilder();
		String passwrodChar = "0123456789ABCDEF";
		for(byte i = 0; i < pwd.length; i++)
		{
			sb.append(passwrodChar.charAt(pwd[i] / 16));
			sb.append(passwrodChar.charAt(pwd[i] % 16));
		}
		
		return sb.toString();
	}

	private static int[] toChar(String pwd)
	{
		int[] ch = new int[pwd.length() / 2];
		int ch1 = 0;
		int ch2 = 0;
		for(int i = 0; i < ch.length; i++)
		{
			ch1 = pwd.charAt(i * 2) <= '9' ? pwd.charAt(i * 2) - '0' : pwd.charAt(i * 2) - 'A' + 10;
			ch2 = pwd.charAt(i * 2 + 1) <= '9' ? pwd.charAt(i * 2 + 1) - '0' : pwd.charAt(i * 2 + 1) - 'A' + 10;
			ch[i] = ch1 * 16 + ch2;
		}
		
		return ch;
	}
}
