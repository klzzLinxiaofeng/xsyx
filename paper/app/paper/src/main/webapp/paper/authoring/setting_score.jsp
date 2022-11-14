<%-- 
    Document   : setting_score
    Created on : 2014-2-20, 13:46:18
    Author     : Administrator
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    String id = request.getParameter("id");
    String score = request.getParameter("score");
%>
<!DOCTYPE html>
<div name="score">
    分数：<input id="questionScore_<%=id%>" value="<%=score%>" onkeyup="checkScore('<%=id%>',this)" />
</div>
