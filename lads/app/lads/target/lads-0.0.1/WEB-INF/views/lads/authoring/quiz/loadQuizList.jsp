<%-- 
    Document   : loadQuizList
    Created on : 2013-8-22, 15:21:23
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <s:i18n name="config/struts/locales/resources">
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <meta http-equiv="pragma" content="no-cache" />
            <link rel="stylesheet" href="/css/common/lads/importXtjyResources/style.css" type="text/css" />
        </head>
        <body class="res-body">
            <!--中间部分开始-->
            <div class="res-resbody res-boxline">
                <!--右边主内容开始-->
                <div class="res-main">
                    <!--高级简单开始-->
                    <%--<div class="jiaoan-sel">
                        <div class="mgbotom jiaoan-so">
                            <jsp:include page="/module/teacher/resources/filterSearch.jsp" >
                                <jsp:param name="action" value="/common/lads/ladsQuizAction_loadQuizList.action"></jsp:param>
                                <jsp:param name="hasCatalog" value="false"></jsp:param>
                                <jsp:param name="type" value="ajax"></jsp:param>
                                <jsp:param name="loadDiv" value="loadQuizFormDiv"></jsp:param>
                                <jsp:param name="hasShareType" value="false"></jsp:param>
                                <jsp:param name="hasSuffix" value="false"></jsp:param>
                            </jsp:include>
                        </div>
                    </div>--%>
                    <!--高级简单结束-->
                    <!--表格开始-->
                    <form action="/common/lads/ladsQuizAction_loadQuizList.action" method="post">
                        <div class="totaltable tablediv0 tablediv3">
                            <table>
                                <thead>
                                    <tr>
                                        <th width="20">

                                        </th>
                                        <th width="600" >
                                            <s:text name="标题" />
                                        </th>
                                        <th width="200">
                                            <s:text name="operation" />
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <s:if test="#request.quizList.isEmpty()">
                                        <tr class="nothing">
                                            <td align="center" colspan="4">
                                                <s:text name="myxgxx" />
                                            </td>
                                        </tr>
                                    </s:if>
                                    <s:iterator value="#request.quizList" id="quiz" status="stuts">
                                        <s:if test="#stuts.odd == true">
                                            <tr>
                                            </s:if>
                                            <s:else>
                                                <tr class="trbg">
                                                </s:else>
                                                <td align="center" valign="middle">
                                                </td>
                                                <td align="left" valign="middle">
                                                    <ul class="res-ctboxul">
                                                        <li class="mb">
                                                            <b class="res-iconb icon-file res-exam"></b>
                                                            <a target="_blank" href='/common/resources/quizAction_previewQuiz.action?id=<s:property value="id"/>' class="ress2">
                                                                <s:property value="title" escape="false"/><br/>
                                                            </a>
                                                            <small>
                                                                <s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getSubject(subjectCode)" escape="false"/>
                                                                <s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getPublish(publishCode)" escape="false"/>
                                                                <s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getGrade(gradeCode)" escape="false"/>
                                                                <s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getVolume(volumeCode)" escape="false"/>
                                                                <s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getUnit(unitCode)" escape="false"/>
                                                                <s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getSection(sectionCode)" escape="false"/>
                                                            </small>
                                                        </li>
                                                    </ul>
                                                </td>
                                                <td align="center" valign="middle">
                                                    <a href="javascript:void(0)" onclick='chooseLoadQuiz("<s:property value="#request.toolId"/>","<s:property value="id"/>")' class="ress2 editfall">选  择</a>
                                                    <a target="_blank" href="/common/resources/quizAction_previewQuiz.action?id=<s:property value="id"/>" class="ress2 editfall"><s:text name="preview" /></a>
                                                </td>
                                            </tr>
                                        </s:iterator>
                                </tbody>
                            </table>
                        </div>
                        <!--表格结束-->
                        <!--页码开始-->
                        <div class="totalpage">
                            <jsp:include page="/module/common/lads/common/page.jsp">
                                <jsp:param name="loadDiv" value="div[id^='loadQuizPanel_']"></jsp:param>
                                <jsp:param name="url" value="/common/lads/ladsQuizAction_loadQuizList.action"></jsp:param>
                            </jsp:include>
                        </div>
                    </form>
                    <!--页码结束-->
                </div>
                <!--右边主内容结束-->
                <div class="clear"></div>
            </div>
            <!--中间部分结束-->
            <script type="text/javascript">
                function chooseLoadQuiz(toolId,quizId){
                    $.ajax({
                        url:"/common/lads/ladsQuizAction_chooseQuizXml.action",
                        type: "POST",
                        data:{"sysType":$("#sysType").val(),"quizId":quizId},
                        async: true,
                        success:function(xmlPath){
                            var dir = xmlPath.substring(0, xmlPath.lastIndexOf("/") + 1);
                            var uploadPath = xmlPath.substring(xmlPath.lastIndexOf("/exam"), xmlPath.lastIndexOf("/") + 1);
                            $("#workingDir_"+toolId).val(dir);
                            //设置文件上传路径
                            $("#uploadPath_"+toolId).val(uploadPath);
                            swf = getChooseQuizSwf();
                            //设置工作目录
                            swf.ChangeWorkDirectory(dir);
                            callQuizByXml(xmlPath+"?random="+new Date().getTime(),toolId);
                            closeOverlay();
                        }
                    });
                }
            </script>
            </body>
    </s:i18n>
</html>
