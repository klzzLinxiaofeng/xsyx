<%-- 
    Document   : editortoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page import="platform.education.lads.contants.discussToolCons.AllowUpload"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp" %>
<%
    //允许上传的常量标记
    //request.setAttribute("allowUploadCons", platform.education.lads.contants.discussToolCons.AllowUpload.ALLOW);
%>
<!DOCTYPE html>
<table class="rt">
    <tr>
        <td valign="top" width="100" align="right"><strong>讨论主题：</strong></td>
        <td>
           <input onkeyup="changeTitle('${requestScope.id}',this.value)" name="discussTitle_${requestScope.id}" id="discussTitle_${requestScope.id}" value="${requestScope.title}" type="text"/>
        </td>
    </tr>
    <tr>
        <td valign="top" width="100" align="right"><strong>讨论内容：</strong></td>
        <td>
            <textarea style=" resize: none;border:1px solid grey" name="discussContent_${requestScope.id}" id="discussContent_${requestScope.id}" cols="90" rows="9">${requestScope.discussContent}</textarea>
        </td>
    </tr>
    <tr>
        <td width="100" align="right"><strong>分　　数：</strong></td>
        <td><input onkeyup="checkScore('${requestScope.id}',this)" name="discussScore_${requestScope.id}" id="discussScore_${requestScope.id}" value="${requestScope.discussScore}" type="text"/></td>
    </tr>
<%--    <tr>
        <td align="right"><strong>时　　间：</strong></td>
        <td><input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" name="discussStartTime_${requestScope.id}" id="discussStartTime_${requestScope.id}" type="text"
                   value="${requestScope.discussStartTime}"  /> -
            <input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" name="discussStopTime_${requestScope.id}" id="discussStopTime_${requestScope.id}" type="text"
                   value="${requestScope.discussStopTime}"  />
        </td>
    </tr>--%>

<%--    <tr>
        <td align="right"><strong>上传附件：</strong></td>
        <td>
            <input style=" width: 15px;position:relative;top:1px;" <c:if test="${allowUploadCons eq requestScope.discussAllowUpload}">checked="true"</c:if>
                 type="checkbox" value="<%=AllowUpload.ALLOW%>" id="discussAllowUpload_${requestScope.id}" name="discussAllowUpload_${requestScope.id}" >
            </input>(允许学员上传)
        </td>
    </tr>--%>
</table>
