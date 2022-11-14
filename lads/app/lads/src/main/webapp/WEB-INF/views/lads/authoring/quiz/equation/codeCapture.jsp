<%-- 
    Document   : codeCapture
    Created on : 2011-12-28, 13:23:47
    Author     : 罗志明
--%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String filePath = request.getParameter("filePath");
            // 云服务器的特殊路径处理
            if (filePath.contains("files.jiaoxueyun.com")) {
                filePath = filePath.replace("files.jiaoxueyun.com", "127.0.0.1:20205");
            }
            URL url = new URL(filePath);
            URLConnection conn = url.openConnection();
            if (!(conn instanceof HttpURLConnection)) {
                System.err.println("Unable to open connection to: " + filePath);
            }
            HttpURLConnection httpConn = (HttpURLConnection) conn;
            if (httpConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.err.println("HTTP Response Code: " + httpConn.getResponseCode()
                        + ", HTTP Response Message: " + httpConn.getResponseMessage());
            }
            InputStream is = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            is.close();
            out.print(sb.toString());
%>
