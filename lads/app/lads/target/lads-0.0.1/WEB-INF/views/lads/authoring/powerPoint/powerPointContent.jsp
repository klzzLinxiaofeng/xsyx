<%-- 
    Document   : powerPointContent
    Created on : 2013-8-24, 14:51:20
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<div class="title_nav">
    <s:property value="#request.pptFileTitle"/>
    <input  type="button" class="btn btn-success" value="删除" onclick="cancelChoosePowerPoint('<s:property value="#request.id"/>')" href="javascript:void(0)">
    
</div>

<div id="powerPointContent_<s:property value="#request.id"/>">

</div>
<script type="text/javascript">
    function loadPowerPoint(fileId,toolId){
        $("#powerPointContent_"+toolId).load("/common/resources/frontResourcesAction_conver.action?fileId="+fileId+"&width=952&height=570", function(){
             $("#powerPointFileId_"+toolId).val(fileId);           
        });
    }
    function cancelChoosePowerPoint(toolId){
        $("#powerPointTitleDiv_"+toolId).show();
        loadPowerPointImportContent('upload',toolId);
        $("#powerPointFileId_"+toolId).removeAttr("value");
    }
    $(function(){
        loadPowerPoint('<s:property value="#request.pptFileId"/>','<s:property value="#request.id"/>')
    })
</script>