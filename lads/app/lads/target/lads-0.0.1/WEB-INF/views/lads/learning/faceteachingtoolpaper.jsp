<%-- 
    Document   : faceteachingtoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<h3><s:property value="#request.title"/></h3>
<p class="small_font"><b>上课时间：</b><s:property value="#request.faceTeachingStartTime"/> - <s:property value="#request.faceTeachingStopTime"/></p>
<p class="small_font"><b>介　　绍：</b>
    <s:property value="#request.faceTeachingDescription"/>
</p>
<p class="small_font"><b>地　　址：</b>
    <s:property value="#request.faceTeachingAddress"/>
</p>
