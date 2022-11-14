<%-- 
    Document   : powerPointResources
    Created on : 2013-8-24, 13:45:35
    Author     : Administrator
--%>
<!DOCTYPE html>
<%
    String toolId = request.getParameter("toolId");
    String suffix = request.getParameter("suffix");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<link rel="stylesheet" href="/css/common/lads/importXtjyResources/style.css" type="text/css" />
<script src="/module/common/lads/common/js/xtjyTree.js" type="text/javascript"></script>
<input type="hidden" id="xtjySearchResourcesSuffix_<%=toolId%>" value="<%=suffix%>"/>
<div class="subSearch">
    <img src="/css/common/lads/importXtjyResources/images/resourcesLogo.jpg" alt="" class="logo"/>
    <input id="button" type="button" class="formBtn" onclick="fileSearch('','','','','','',$('#xtjySearchResourcesKeyWord_<%=toolId%>').val(),$('#xtjySearchResourcesSuffix_<%=toolId%>').val(),'<%=toolId%>')" value="搜索"/>
    <input type="text" id="xtjySearchResourcesKeyWord_<%=toolId%>" value="" class="title" autocomplete="off"/>
    <img src="/css/common/lads/importXtjyResources/images/search.jpg" alt=""/>
</div>
<div class="rs_platformContainer">
    <div class="aside"> 
        <h3 class="subject-header"> 教材目录 </h3>
        <ul id="subjectCatalog<%=toolId%>" style=" height: 273px; margin-bottom: 10px;overflow: auto; " >
        </ul>
        <h3 class="subject-header"> 课本目录 </h3>
        <div class="inner" style="height: 257px;position:relative;">
            <ul id="unitList<%=toolId%>">
                <li> 暂未选择课本，请先选择教材目录。 </li>
            </ul>
        </div>
    </div>
    <div class="main" >
        <ul id="tabUl" class="rs_platformTab">
            <li id="all" class="cur"> <a>全 部 </a> </li>
        </ul>
        <!-- <div id="loading" style="display: none" ><img src="${pageContext.request.contextPath}/res/images/loading.gif" alt="加载中">文件加载中,请耐心等待</div> -->
        <div id="xtjySearchResources_<%=toolId%>" >
        </div>
    </div>
</div>
<!--注释的是只有1页时不显示分页的代码-->
<script type="text/javascript">
    function chooseXtjyResources(fileId,toolId,httpUrl){
        var suffix = $("#xtjySearchResourcesSuffix_"+toolId).val();
        if(suffix.indexOf("ppt")!=-1){
            //ppt处理方式
            choosePowerPoint(fileId,toolId)
        }else if(suffix.indexOf("doc")!=-1){
            //word处理方式
        }else if(suffix.indexOf("jpg")!=-1){
            //图片处理方式
            chooseImage(httpUrl,toolId)
        }else if(suffix.indexOf("swf")!=-1){
            //多媒体处理方式
            chooseMedia(httpUrl,toolId)
        }
    }
    
    $(function(){
        setXtjyTree('<%=toolId%>')
    })
</script>