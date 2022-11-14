<%-- 
    Document   : grouppaper
    Created on : 2012-8-11, 14:00:22
    Author     : Administrator
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<table class="rt">
    <tr>
        <td width="70">标题：</td>
        <td><input onkeyup="changeTitle('${requestScope.id}',this.value)" name="groupTitle_${requestScope.id}" id="groupTitle_${requestScope.id}" value="${requestScope.title}" type="text"/></td>
    </tr>
</table>

