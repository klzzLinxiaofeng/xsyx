package com.xunyunedu.util.file;




import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import javax.mail.internet.MimeUtility;
/**
 * @description: 下载工具类
 * @author: cmb
 * @create: 2020-10-29 15:59
 **/
public class DownloadUtil {
    public static final String ENCODING_UTF_8 = "UTF8";

    public DownloadUtil() {
    }

    public static String encodeFilenameForDownload(HttpServletRequest request, String unEncodedFilename) throws UnsupportedEncodingException {
        String agent = request.getHeader("USER-AGENT");
        String filename = null;
        if (null != agent && -1 != agent.indexOf("MSIE")) {
            filename = URLEncoder.encode(unEncodedFilename, "UTF8");
        } else if (null != agent && -1 != agent.indexOf("Firefox")) {
            unEncodedFilename = unEncodedFilename.trim().replaceAll(" ", "");
            unEncodedFilename = unEncodedFilename.replaceAll("《", "");
            unEncodedFilename = unEncodedFilename.replaceAll("》", "");
            filename = MimeUtility.encodeText(unEncodedFilename, "UTF8", "B");
        } else if (null != agent && -1 != agent.indexOf("Chrome")) {
            filename = URLEncoder.encode(unEncodedFilename, "UTF8");
        } else if (null != agent && -1 != agent.indexOf("Gecko")) {
            filename = URLEncoder.encode(unEncodedFilename, "UTF8");
        } else {
            filename = unEncodedFilename;
        }

        return filename;
    }
}
