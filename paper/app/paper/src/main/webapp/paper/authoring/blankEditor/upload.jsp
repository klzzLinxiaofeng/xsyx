<%-- 
    Document   : upload
    Created on : 2013-9-11, 14:41:46
    Author     : Administrator
--%>


<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper"%>
<%@page import="com.gzxtjy.common.util.FtpUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="org.apache.commons.fileupload.disk.*"%>
<%@ page import="org.apache.commons.fileupload.servlet.*"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page import="net.sf.json.JSONObject"%>
<%
    String uploadType = request.getParameter("dir");
    String[] fileTypes =null;
    long maxSize = 0;
    if (uploadType.equals("image")) {
        //定义允许上传的文件扩展名
        fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
        //允许最大上传文件大小  10M
        maxSize = 1024000 * 10;
    } else if (uploadType.equals("media")) {
        //定义允许上传的文件扩展名
        fileTypes = new String[]{"mp3", "mp4"};
        //允许最大上传文件大小  1024M
        maxSize = 1024000 * 1024;
    }else{
        
    }

    //Struts2 请求 包装过滤器
    MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
    //获得上传的文件名
    String fileName = wrapper.getFileNames("imgFile")[0];
    //获得文件过滤器
    File file = wrapper.getFiles("imgFile")[0];
    //得到上传文件的扩展名
    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1)
            .toLowerCase();
    //检查扩展名
    if (!Arrays.<String>asList(fileTypes).contains(fileExt)) {
        out.println(getError("上传文件扩展名是不允许的扩展名。"));
        return;
    }
    //检查文件大小
    if (file.length() > maxSize) {
        out.println(getError("上传文件大小超过限制。"));
        return;
    }
    String year = new SimpleDateFormat("yyyy").format(new Date());
    FtpUtils ftp = new FtpUtils();
    if (ftp.uploadFile("/paper/editor/"+uploadType+"/"+year+"/", FtpUtils.getRandomNameByTime() + "." + fileExt, file)) {
        System.out.println(ftp.getHttpUrl());
        ftp.getHttpUrl();
        //发送给 KE 
        JSONObject obj = new JSONObject();
        obj.put("error", 0);
        obj.put("url", ftp.getHttpUrl());
        out.println(obj.toString());
    } else {
        out.println(getError("网络或服务器原因导致上传失败"));
    }

%>
<%!private String getError(String message) {
        JSONObject obj = new JSONObject();
        obj.put("error", 1);
        obj.put("message", message);
        return obj.toString();
    }
%>
