<%-- 
    Document   : imageCapture
    Created on : 2011-11-28, 15:26:23
    Author     : 罗志明
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="com.gzxtjy.common.util.FtpUtils"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.gzxtjy.portal.session.SessionManager"%>
<%@page import="com.gzxtjy.common.util.HttpUtils"%>
<%@page import="org.apache.commons.io.IOUtils"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String uploadPath = request.getParameter("uploadPath");
            String code = request.getParameter("code");
            String fileName = request.getParameter("fileName");
            FtpUtils ftp = new FtpUtils();
            String extension;
            if (fileName == null || "".equals(fileName)) {
                fileName = FtpUtils.getRandomNameByTime();
            }
            List allExt = new ArrayList();
            allExt.add(".txt");
            allExt.add(".png");
            ftp.setAllowExt(allExt);
            if (code != null && !"".equals(code)) {
                extension = "txt";
                fileName = fileName + "." + extension;
                if (ftp.uploadFile(uploadPath, fileName, new ByteArrayInputStream(code.getBytes()))) {
                    String httpPath = ftp.getHttpUrl();
                    out.print(httpPath);
                }
            } else {
                extension = "png";
                fileName = fileName + "." + extension;
                if (ftp.uploadFile(uploadPath, fileName, request.getInputStream())) {
                    String httpPath = ftp.getHttpUrl();
                    String absPath = ftp.getAbsolutePath();
                    out.print("{urlPath:'" + httpPath + "',absPath:'" + absPath + "'}");
                }
            }
%>
