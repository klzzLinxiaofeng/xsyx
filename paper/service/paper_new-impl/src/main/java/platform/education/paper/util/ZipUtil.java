package platform.education.paper.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * 解压工具类（jdk使用8.0的新特性）
 * @author quan
 * 
 */
public class ZipUtil{
	
	/**获取压缩包注释内容以便算出真实密码*/
	public static String getComment(String zipPath){
		if(zipPath!=null){
			try {
				return new ZipFile(zipPath).getComment();
			} catch (ZipException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 解压缩含有密码的压缩包
	 * @param zipPath zip文件路径
	 * @param decompressPath 解压路径
	 * @param password 真实密码
	 * */
	public static boolean explodeZip(String zipPath, String decompressPath,String password){
		//检测字段
		/*if(zipPath==null || decompressPath==null || password == null)
			return false;*/
		//检测文件有效性 && 检测创建解压目录
		File zipFile=new File(zipPath);
		if(!zipFile.exists())
			return false;
		//启动解压缩
		try {
			ZipFile mZipFile=new ZipFile(zipPath);
			/*if(password!=null && !"".equals(password))
				mZipFile.setPassword(password);*/
			mZipFile.extractAll(decompressPath);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 解压方法
	 * @param zipPath zip文件路径
	 * @param decompressPath 解压路径
	 * */
	public static boolean explodeZip(String zipPath, String decompressPath){
		return explodeZip(zipPath, decompressPath, null);
	}

	public static String extractZipComment (String filename) {
		String retStr = null;
		FileInputStream in = null;
		try {
		File file = new File(filename);
		int fileLen = (int)file.length();
		 
		in = new FileInputStream(file);
		
		byte[] buffer = new byte[Math.min(fileLen, 8192)];
		int len;
		 
		in.skip(fileLen - buffer.length);
		 
		if ((len = in.read(buffer)) > 0) {
		retStr = getZipCommentFromBuffer (buffer, len);
		}
		 
		} catch (Exception e) {
		e.printStackTrace();
		}finally {
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return retStr;
}
	
	
	private static String getZipCommentFromBuffer (byte[] buffer, int len) {
		byte[] magicDirEnd = {0x50, 0x4b, 0x05, 0x06};
		int buffLen = Math.min(buffer.length, len);
		for (int i = buffLen-magicDirEnd.length-22; i >= 0; i--) {
			boolean isMagicStart = true;
			for (int k=0; k < magicDirEnd.length; k++) {
				if (buffer[i+k] != magicDirEnd[k]) {
				isMagicStart = false;
				break;
				}
			}
			if (isMagicStart) {
				int commentLen = buffer[i+20] + buffer[i+22]*256;
				int realLen = buffLen - i - 22;
				String comment = new String (buffer, i+22, Math.min(commentLen, realLen));
				return comment;
			}
		}
		System.out.println ("ERROR! ZIP comment NOT found!");
		return null;
	}
	
	
}