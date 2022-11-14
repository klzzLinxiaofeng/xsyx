<%-- 
    Document   : editortoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<table class="rt">
    <tr>
        <td width="100" align="right"><strong>标　　题：</strong></td>
        <td><input onkeyup="changeTitle('<s:property value="#request.id"/>',this.value)" name="faceTeachingTitle_<s:property value="#request.id"/>" id="faceTeachingTitle_<s:property value="#request.id"/>" value="<s:property value="#request.title"/>" type="text"/></td>
    </tr>
    <tr>
        <td width="100" align="right"><strong>分　　数：</strong></td>
        <td><input onkeyup="checkScore('<s:property value="#request.id"/>',this)" name="faceTeachingScore_<s:property value="#request.id"/>" id="faceTeachingScore_<s:property value="#request.id"/>" value="<s:property value="#request.faceTeachingScore"/>" type="text"/></td>
    </tr>
    <tr>
        <td align="right"><strong>时　　间：</strong></td>
        <td><input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" name="faceTeachingStartTime_<s:property value="#request.id"/>" id="faceTeachingStartTime_<s:property value="#request.id"/>" type="text"
                   value ="<s:property value="#request.faceTeachingStartTime" />" /> -
            <input onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" name="faceTeachingStopTime_<s:property value="#request.id"/>" id="faceTeachingStopTime_<s:property value="#request.id"/>" type="text"
                   value ="<s:property value="#request.faceTeachingStopTime" />"/>
        </td>
    </tr>
    <tr>
        <td valign="top" align="right"><strong>介　　绍：</strong></td>
        <td><textarea style="border:1px solid grey" name="faceTeachingDescription_<s:property value="#request.id"/>" id="faceTeachingDescription_<s:property value="#request.id"/>" cols="90" rows="9"><s:property value="#request.faceTeachingDescription" /></textarea>
        </td>
    </tr>
    <tr>
        <td valign="top" align="right"><strong>地　　址：</strong></td>
        <td><textarea style="border:1px solid grey" name="faceTeachingAddress_<s:property value="#request.id"/>" id="faceTeachingAddress_<s:property value="#request.id"/>" cols="90" rows="9"><s:property value="#request.faceTeachingAddress" /></textarea>
        </td>
    </tr>
</table>
