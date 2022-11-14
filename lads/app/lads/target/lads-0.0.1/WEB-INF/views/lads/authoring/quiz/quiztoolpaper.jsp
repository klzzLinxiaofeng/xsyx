<%-- 
    Document   : quiztoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>

<%@page import="com.gzxtjy.common.config.CommonConfig"%>
<%@page import="com.gzxtjy.resources.util.DocPathUtil"%>
<%@page import="com.gzxtjy.exam.entities.ExamQuiz"%>
<%@page import="com.gzxtjy.common.util.WebContextUtil"%>
<%@page import="com.gzxtjy.exam.service.IQuizService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%
    String id = (String) request.getAttribute("id");
    IQuizService qs = (IQuizService) WebContextUtil.getInstance(this.getServletContext()).getContext("quizServiceImpl");
    String quizXmlPath = request.getParameter("quizXmlPath");
    String dir = "";
    String uploadPath = "";
    if (quizXmlPath == null || "".equals(quizXmlPath)) {
        quizXmlPath = (String) request.getAttribute("quizXmlPath");
    }
    if (quizXmlPath != null && !"".equals(quizXmlPath)) {
        uploadPath = quizXmlPath.substring(quizXmlPath.lastIndexOf("/exam"), quizXmlPath.lastIndexOf("/") + 1);
        dir = quizXmlPath.substring(0, quizXmlPath.lastIndexOf("/") + 1);
    } else {
        quizXmlPath = "";
        String prefixPath = CommonConfig.getProperty("ftp.0.prefix_path").toString().trim();
        String httpPrefix = CommonConfig.getProperty("ftp.0.http_prefix").toString().trim();
        uploadPath = qs.basePathCreater(id);
        dir = httpPrefix + prefixPath + uploadPath;
    }
%>
<style type="text/css">
    .mainDiv{position:relative;height: 700px}
    .popDiv{visibility:hidden;position:absolute;width:100%;height:100%;z-index:1000;}
    .clickDiv{visibility: hidden;position:absolute;width:100%;height:100%;z-index:1002;background:#000;opacity:0.5}
    .apple_overlay .close {
        background-image:url(/image/common/resources/close.png);
        position:absolute; right:5px; top:5px;
        cursor:pointer;
        height:35px;
        width:35px;
    }
    .contentWrap {height:441px;}
</style>
<table class="rt">
    <tr   style="float:left;display:block; margin-right:834px">
        <td width="100" align="right"><strong>标　　题：</strong></td>
        <td><input onkeyup="changeTitle('<%=id%>',this.value)" name="quizTitle_<%=id%>" id="quizTitle_<%=id%>" value="<s:property value="#request.title"/>" type="text"/></td>
    </tr>
    <tr  style="float:left;display:block;margin-right:834px ">
        <td width="100" align="right"><strong>分　　数：</strong></td>
        <td><input onkeyup="checkScore('<%=id%>',this)" name="quizScore_<s:property value="#request.id"/>" id="quizScore_<s:property value="#request.id"/>" value="<s:property value="#request.quizScore"/>" type="text"/></td>
    </tr>
    <tr class="add"  style="width:150px;float:left;display:block; margin-right:672px">
        <td  width="100" align="right"><input class="btn btn-success"onclick="importQuiz('<s:property value="#request.id"/>')" value="从个人库中导入题目" type="button" style="height:24px;"/></td>
    </tr>
</table>
<div class="mainDiv">
    <div  id="builderDiv_<%=id%>"></div>
</div>
<div id="previewDiV_<%=id%>" class="previewDiV">
    <div id="previewSwfDiv_<%=id%>" ></div>
</div>
<input id="workingDir_<%=id%>" type="hidden"/>
<input id="uploadPath_<%=id%>" type="hidden"/>
<input id="absPath_<%=id%>" type="hidden"/>
<input id="isSaveFlag_<%=id%>" type="hidden" value="false"/>
<div id="equationDiV_<%=id%>" class="equationDiV" style="z-index:10001">
</div>
<%--<div id="savePanel_<%=id%>" style="z-index:10001" >
</div>
<div id="tipsPanel_<%=id%>" style="z-index:10001">
</div>--%>
<div id="publishPanel_<%=id%>" style="z-index:10001" >
</div>
<div id="uploadPanel_<%=id%>" style="z-index:10001" >
</div>
<div id="videoPanel_<%=id%>"  style="z-index:10001;">
</div>
<div id="loadQuizPanel_<%=id%>" >
</div>
<script type="text/javascript">
    // quizBuilder.swf 回调函数
    /* 入口函数
     ** quizBuilder.swf加载完成后，会调用此函数，一切从这里开始
     */
   
    $(function(){
        var ladsId = "<%=id%>";
        $("#workingDir_"+ladsId).val("<%=dir%>");
        //设置文件上传路径
        $("#uploadPath_"+ladsId).val("<%=uploadPath%>");
        var Vars = {
        };
        var Params = {
            menu: "false",
            scale: "noScale",
            allowFullscreen: "true",
            allowScriptAccess: "always",
            wmode: "transparent",
            base:"/js/common/quizbuilder/"
        };
        //导入迷你版的quiz swf
        swfobject.embedSWF("/js/common/quizbuilder/QuizBuilder_mini.swf", "builderDiv_"+ladsId, "100%", "100%", "10.0.0", "expressInstall.swf", Vars, Params,null,function(callback){
        });
        //使用定时器来加载xml
        $("body").oneTime("1s",function(){
            var swf;
            var builderId = "<%=id%>";
            var xmlPath = "<%=quizXmlPath%>";
            if(xmlPath!=null&&xmlPath!=""){
                swf = document.getElementById("builderDiv_"+builderId);
            }else{
                swf = getChooseQuizSwf();
            }
            //设置工作目录
            swf.ChangeWorkDirectory("<%=dir%>");
            if(""==xmlPath){
                callNewQuiz();
            }else{
                $("#isSaveFlag_"+builderId).val(builderId);
                callQuizByXml("<%=quizXmlPath%>"+"?random="+new Date().getTime(),builderId);
            }
        });
    });
    
    function importQuiz(toolId){
        openOverlayInLoad("#loadQuizPanel_"+toolId,"/common/lads/ladsQuizAction_loadQuizList.action?sysType="+$("#sysType").val()+"&toolId="+toolId,"closeOverlay",true);
    }
    
</script>
