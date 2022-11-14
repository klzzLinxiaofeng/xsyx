<%-- 
    Document   : powerpointtoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="lads" uri="/WEB-INF/lads" %>
<!DOCTYPE html>
<h3><s:property value="#request.title"/></h3>
<div id="powerPointContent_<s:property value="#request.id"/>">
</div>
<script type="text/javascript">
    function loadPowerPoint(fileId,toolId){
        if(fileId!=null&&fileId!=""&&fileId!="null"){
            $("#powerPointContent_"+toolId).load("/common/resources/frontResourcesAction_conver.action?fileId="+fileId+"&width=775&height=600", function(){
                        
            });
        }else{
            $("#powerPointContent_"+toolId).text("没有嵌入ppt");
        }
    }
    $(function(){
        loadPowerPoint("<s:property value="#request.powerPointFileId"/>","<s:property value="#request.id"/>");
        
        //预览模式不需要计算学习时间
        if(!previewMode){
            $.ajax({
                url:"/common/lads/ladsPowerPointAction_saveUserStatus.action",
                data:{"toolId":"<s:property value="#request.id"/>","powerPointScore":"<s:property value="#request.powerPointScore"/>","userId":"<lads:getEmbedUser sysType="${requestScope.sysType}"/>"},
                type:'POST', //请求方式
                async: true,
                cache:false//是否缓存
            });
        }
    })
</script>
