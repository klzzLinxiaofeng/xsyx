<%-- 
    Document   : powerPointResources
    Created on : 2013-8-24, 13:45:35
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:include value="/module/common/lads/common/xtjyResources/xtjyResourcesIndex.jsp">
    <s:param name="toolId" value="#request.id"></s:param>
    <s:param name="suffix" value='"ppt|pptx"'></s:param>
</s:include>