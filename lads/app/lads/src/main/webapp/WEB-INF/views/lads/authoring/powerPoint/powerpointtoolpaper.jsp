<%-- 
    Document   : powerpointtoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<div class="title">
    <div class="head">
        <strong style="margin-left: 20px;">标　题：</strong>
        <input onkeyup="changeTitle('<s:property value="#request.id"/>',this.value)" name="powerPointTitle_<s:property value="#request.id"/>" id="powerPointTitle_<s:property value="#request.id"/>" value="<s:property value="#request.title"/>" type="text"/>
        <strong>分    数：</strong>
        <input onkeyup="checkScore('<s:property value="#request.id"/>',this)" name="powerPointScore_<s:property value="#request.id"/>" id="powerPointScore_<s:property value="#request.id"/>" value="<s:property value="#request.powerPointScore"/>" type="text"/>
    </div>
    <div id="powerPointTitleDiv_<s:property value="#request.id"/>" class="title_nav">
        <ul class="title_ul">
            <li class="active"><a href="javascript:void(0)" onclick="loadPowerPointImportContent('upload','<s:property value="#request.id"/>')">文件上传</a></li>
            <li ><a href="javascript:void(0)" onclick="loadPowerPointImportContent('resources','<s:property value="#request.id"/>')">资源分享平台</a></li>
            <li ><a href="javascript:void(0)" onclick="loadPowerPointImportContent('personal','<s:property value="#request.id"/>')">我的资源库</a></li>
        </ul>
    </div>
</div>
<div id="powerPointImportDiv_<s:property value="#request.id"/>" >
</div>
<input type="hidden" id="powerPointFileId_<s:property value="#request.id"/>" value="<s:property value="#request.powerPointFileId"/>"/>


<script type="text/javascript">
    function loadPowerPointContentIndex(fileId,fileTitle,toolId){
        var data = {"pptFileId":fileId,"pptFileTitle":fileTitle,"toolId":toolId};
        $("#powerPointImportDiv_"+toolId).load("/common/lads/ladsPowerPointAction_loadPptContent.action",data, function(){
            $("#powerPointTitleDiv_"+toolId).hide();             
        });
    }
    
    function loadPowerPointImportContent(type,toolId){
        var url;
        var data;
        $("#powerPointTitleDiv_"+toolId).find("li").each(function(){
            $(this).attr("class","");
        })
        $("#powerPointImportDiv_"+toolId).html("<img src=\"${pageContext.request.contextPath}/res/images/loading.gif\" title=\"加载中\" alt=\"加载中\"/>");
        if(type=="personal"){
            $("#powerPointTitleDiv_"+toolId).find("li").eq(2).attr("class","active");
            data = {"toolId":toolId,"sysType":$("#sysType").val()};
            url = "/common/lads/ladsPowerPointAction_loadPersonalPptList.action";
        }else if(type=="upload"){
            $("#powerPointTitleDiv_"+toolId).find("li").eq(0).attr("class","active");
            data = {"toolId":toolId};
            url = "/common/lads/ladsPowerPointAction_uploadPptIndex.action";
        }else if(type=="resources"){
            $("#powerPointTitleDiv_"+toolId).find("li").eq(1).attr("class","active");
            data = {"toolId":toolId,"sysType":$("#sysType").val()};
            url = "/common/lads/ladsPowerPointAction_resourcesPptSearch.action";
        }
        $("#powerPointImportDiv_"+toolId).load(url,data);
    }
    
    function choosePowerPoint(pptFileId,toolId){
        var pptFileTitle = $.trim($("#pptTitleA_"+pptFileId+"_"+toolId).text());
        loadPowerPointContentIndex(pptFileId,pptFileTitle,toolId);
    }
    
    $(function(){
        var pptFileId = "<s:property value="#request.powerPointFileId"/>";
        if(pptFileId!=""&&pptFileId!="null"){
            loadPowerPointContentIndex(pptFileId,"","<s:property value="#request.id"/>");
        }else{
            loadPowerPointImportContent('upload','<s:property value="#request.id"/>')
        }
    })
</script>
