package platform.szxyzxx.web.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class CalculateVisualFileSize {
	// bt字节参考量
	public static long SIZE_BT = 1024;
	// KB字节参考量
	public static final long SIZE_KB = SIZE_BT * 1024L;
	// MB字节参考量
	public static final long SIZE_MB = SIZE_KB * 1024L;
	// GB字节参考量
	public static final long SIZE_GB = SIZE_MB * 1024L;
	// TB字节参考量
	public static final long SIZE_TB = SIZE_GB * 1024L;

	public static final int SACLE = 2;
	/**
	 * 传入B为单位的fileSize，自动转换单位可以得到KB，MB，GB，TB
	 * @param fileSize
	 * @return
	 */
	public static String getFileSize(double fileSize){
		String outPutHtml = "";
		if (fileSize >= 0 && fileSize < SIZE_BT) {
			DecimalFormat df = new DecimalFormat("###");
			outPutHtml = df.format(fileSize) + "B";
		} else if (fileSize >= SIZE_BT && fileSize < SIZE_KB) {
			DecimalFormat df = new DecimalFormat("###.00");
			outPutHtml = df.format(fileSize / SIZE_BT) + "KB";
		} else if (fileSize >= SIZE_KB && fileSize < SIZE_MB) {
			DecimalFormat df = new DecimalFormat("###.00");
			outPutHtml = df.format(fileSize / SIZE_KB) + "MB";
		} else if (fileSize >= SIZE_MB && fileSize < SIZE_GB) {
			BigDecimal longs = new BigDecimal(Double
					.valueOf(fileSize + "").toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			outPutHtml = result + "GB";
		} else {
			BigDecimal longs = new BigDecimal(Double
					.valueOf(fileSize + "").toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			outPutHtml = result + "TB";
		}
		return outPutHtml;
	}
}
