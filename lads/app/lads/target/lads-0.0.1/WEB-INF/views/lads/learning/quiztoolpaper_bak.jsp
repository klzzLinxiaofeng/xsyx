<%-- 
    Document   : quiztoolpaper
    Created on : 2012-8-9, 18:18:26
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%
            String xmlPath = request.getParameter("quizXmlPath");
            String resultUrl = request.getParameter("quizResultUrl");
            String dir = "";
            if (xmlPath == null || "".equals(xmlPath)) {
                xmlPath = (String) request.getAttribute("quizXmlPath");
            }
            if (resultUrl == null || "".equals(resultUrl)) {
                resultUrl = (String) request.getAttribute("quizResultUrl");
            }
            dir = xmlPath.substring(0, xmlPath.lastIndexOf("/") + 1);
%>
<style type="text/css">
    .mainDiv{position:relative;height: 500px}
    /*    .apple_overlay .close {
            background-image:url(/image/common/resources/close.png);
            position:absolute; right:5px; top:5px;
            cursor:pointer;
            height:35px;
            width:35px;
        }
        .contentWrap {height:441px;}*/
</style>
<script type="text/javascript">
    function onQuizPlayerReady(qid){
        var id = qid;
        var previewSwf = document.getElementById("preiviewDiv_"+id);
        var xmlPath = $("#quizXmlPath_"+id).val();
        var xmlContent ;
        $.ajax({
            //这个是万能的,不单单可以读xml
            url:'/common/resources/quizAction_ajaxLoadXml.action?random='+new Date().getTime()+'&filePath='+xmlPath, //xml文档路径
            type:'POST', //请求方式
            async: false,
            dataType:'text',//文档类型
            cache:false,//是否缓存
            error:function(){alert('获取xml失败');},//这个是抛出加载失败的信息,比js的智能吧
            success:function(xml){
                xmlContent = xml;//在这里执行对xml文档内容的操作
            }
        });
        previewSwf.preview(xmlContent);
    }
</script>
<ul class="ctitle">
    <li class="ll"></li>
    <li class="mm"><b>活动状态：</b>未提交</li>
    <li class="rr"></li>
</ul>
<table class="rt">
    <tr>
        <td width="100" align="right"><strong>标　　题：</strong></td>
        <td><s:property value="#request.title"/></td>
    </tr>
</table>
<div class="mainDiv">
    <div id="preiviewDiv_<s:property value="#request.id"/>" ></div>
</div>
<input type="hidden" id="quizXmlPath_<s:property value="#request.id"/>" name="quizXmlPath_<s:property value="#request.id"/>"/>
<script type="text/javascript">
    $(function(){
        $("#quizXmlPath_<s:property value="#request.id"/>").val("<%=xmlPath%>");
        var par = {
            flashvars:"qid=<s:property value="#request.id"/>&resultUrl=<%=resultUrl%>?para=<s:property value="#session.PortalStudent.dcCenterUser.userId"/>|<s:property value="#request.id"/>"
            +"&path="+"<%=dir%>" ,
            base:"/js/common/quizbuilder/",
            wmode:"transparent"
        };
        var id = "previewSwfDiv";
        swfobject.embedSWF("/js/common/quizbuilder/QuizPlayer.swf", "preiviewDiv_<s:property value="#request.id"/>", "100%", "100%", "10.0.0", "expressInstall.swf", null, par);
    });
</script>