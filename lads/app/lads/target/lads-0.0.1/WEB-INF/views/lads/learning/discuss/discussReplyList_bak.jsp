<%-- 
    Document   : discussReplyList
    Created on : 2012-10-12, 17:05:08
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<s:iterator value="#request.replyList" id="vo">
    <div class="more">
        <div class="portrait"><img src="<s:property value="image"/>"/></div>
        <div class="name"><s:property value="userName"/>
            <span><s:date name="reply.createTime" format="MM.dd hh:mm"/> 发表</span>
        </div>
        <div class="word"><s:property escape="false" value="reply.content"/></div>
    </div>
</s:iterator>