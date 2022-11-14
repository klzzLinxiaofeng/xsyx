package platform.education.util;

import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *
 * @author 罗志明
 */
public class DownloadUtil {

    public static final String ENCODING_UTF_8 = "UTF8";

    /**
     * 根据不同浏览器厂商对request header的fileName进行编码,主要防止下载文件名中文乱码 Encode a filename in
     * such a way that the UTF-8 characters won't be munged during the download
     * by a browser. Need the request to work out the user's browser type
     *
     * @return encoded filename
     * @throws UnsupportedEncodingException
     */
    public static String encodeFilenameForDownload(HttpServletRequest request,
            String unEncodedFilename) throws UnsupportedEncodingException {

        // Different browsers handle stream downloads differently LDEV-1243
        String agent = request.getHeader("USER-AGENT");
        String filename = null;
        if (null != agent && -1 != agent.indexOf("MSIE")) {
            // if MSIE then urlencode it
            filename = URLEncoder.encode(unEncodedFilename,
                    DownloadUtil.ENCODING_UTF_8);
        } else if (null != agent && -1 != agent.indexOf("Firefox")) {
            // if Mozilla then base64 url_safe encoding
            //去空格
            unEncodedFilename = unEncodedFilename.trim().replaceAll(" ", "");
            //firefox 不支持书名号《》;
            unEncodedFilename = unEncodedFilename.replaceAll("《", "");
            unEncodedFilename = unEncodedFilename.replaceAll("》", "");
            filename = MimeUtility.encodeText(unEncodedFilename,
                    DownloadUtil.ENCODING_UTF_8, "B");
        } else if (null != agent && -1 != agent.indexOf("Chrome")) {
            // if Chrome then base64 url_safe encoding
            filename = URLEncoder.encode(unEncodedFilename,
                    DownloadUtil.ENCODING_UTF_8);
        } else if (null != agent && -1 != agent.indexOf("Gecko")) {
            // if Gecko then base64 url_safe encoding
            filename = URLEncoder.encode(unEncodedFilename,
                    DownloadUtil.ENCODING_UTF_8);
        } else {
            // any others use same filename.
            filename = unEncodedFilename;
        }
        return filename;
    }
}
