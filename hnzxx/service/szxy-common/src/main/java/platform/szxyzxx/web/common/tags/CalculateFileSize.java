package platform.szxyzxx.web.common.tags;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 
 * @author 潘维良
 * 计算文件大小 以文本形式输出
 */
public class CalculateFileSize extends RequestContextAwareTag {

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

	private static final long serialVersionUID = 1L;

	public double fileSize;

	public String unit = "";

	public String outPutHtml = "";

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		if(this.unit.equalsIgnoreCase("kb")) {
			SIZE_BT = 1;
		}
		if (this.fileSize >= 0 && this.fileSize < SIZE_BT) {
			DecimalFormat df = new DecimalFormat("###");
			this.outPutHtml = df.format(this.fileSize) + "B";
		} else if (this.fileSize >= SIZE_BT && this.fileSize < SIZE_KB) {
			DecimalFormat df = new DecimalFormat("###.00");
			this.outPutHtml = df.format(this.fileSize / SIZE_BT) + "KB";
		} else if (this.fileSize >= SIZE_KB && this.fileSize < SIZE_MB) {
			DecimalFormat df = new DecimalFormat("###.00");
			this.outPutHtml = df.format(this.fileSize / SIZE_KB) + "MB";
		} else if (this.fileSize >= SIZE_MB && this.fileSize < SIZE_GB) {
			BigDecimal longs = new BigDecimal(Double
					.valueOf(this.fileSize + "").toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_MB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			this.outPutHtml = result + "GB";
		} else {
			BigDecimal longs = new BigDecimal(Double
					.valueOf(this.fileSize + "").toString());
			BigDecimal sizeMB = new BigDecimal(Double.valueOf(SIZE_GB + "")
					.toString());
			String result = longs.divide(sizeMB, SACLE,
					BigDecimal.ROUND_HALF_UP).toString();
			this.outPutHtml = result + "TB";
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			out.print(this.outPutHtml);
			this.outPutHtml = "";
		} catch (IOException e) {
			throw new JspException("标签输出错误!", e);
		}
		return EVAL_PAGE;
	}

	 public static void main(String[] args) {
		 CalculateFileSize calFileSize = new CalculateFileSize();
		 calFileSize.setFileSize(488709859);
		 try {
			 calFileSize.doStartTagInternal();
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	 }

}
