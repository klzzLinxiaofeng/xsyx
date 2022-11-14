package platform.szxyzxx.web.common.util;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ZipFileUtil {
	public static void unZipFileWithPassword(String zipFile, String destPath){
		try {
			ZipFile zip = new ZipFile(zipFile);
			if(zip.isEncrypted()){
				String aes = zip.getComment();
				zip.setPassword(Security.decrypt(aes)); 
			}
			zip.extractAll(destPath);
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
