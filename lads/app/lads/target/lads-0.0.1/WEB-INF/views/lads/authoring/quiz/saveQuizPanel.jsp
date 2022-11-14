<%-- 
    Document   : saveQuizPanel
    Created on : 2012-3-8, 17:34:39
    Author     : Administrator
--%>
<%@page import="com.gzxtjy.exam.entities.ExamQuiz"%>
<%@page import="com.gzxtjy.exam.service.IQuizService"%>
<%@page import="com.gzxtjy.common.util.WebContextUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
            String id = request.getParameter("quizId");
            String callBack = request.getParameter("callBack");
            if (callBack == null || "".equals(callBack)) {
                callBack = "";
            }
            if (id == null || "".equals(id)) {
                id = (String) request.getAttribute("quizId");
            }
            if (id != null && !"".equals(id)) {
                IQuizService qs = (IQuizService) WebContextUtil.getInstance(this.getServletContext()).getContext("quizServiceImpl");
                ExamQuiz eq = qs.findQuiz(id);
                request.setAttribute("quiz", eq);
            }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="addjoon-open1 totaltable tablediv1" style="background-color: white;width: 768px;padding: 10px;border: 5px solid #999;">
    <table id="contentTable">
        <tbody>
            <tr>
                <td width="85" align="right" valign="top" class="tdbold">练习标题</td>
                <td align="left" valign="middle"><input type="text" name="title" id="title" value="${quiz.title}" class="input-all inwd3"/></td>
            </tr>
            <tr>
                <td align="right" valign="top" class="tdbold">索引目录</td>
                <td align="left" valign="middle" id="catalogSearchDiv">
                </td>
            </tr>
            <tr>
                <td align="right" valign="top" class="tdbold">是否共享</td>
                <td align="left" valign="middle" class="tdstyle">
                    <s:if test="#request.quiz!=null&&#request.quiz.shareStatus==3">
                        <input type="checkbox" checked id="shareStatus" name="shareStatus" value="share"/><span>是</span>
                    </s:if>
                    <s:else>
                        <input type="checkbox" id="shareStatus" name="shareStatus" value="share"/><span>是</span>
                    </s:else>
                </td>
            </tr>
        </tbody>
    </table>
    <div class="addjoon-save">
        <input type="hidden" id="subject_id" name="subject"/>
        <input type="hidden" id="publish_id" name="publish"/>
        <input type="hidden" id="grade_id" name="grade"/>
        <input type="hidden" id="volume_id" name="volume"/>
        <input type="hidden" id="unit_id" name="unit"/>
        <input type="hidden" id="section_id" name="section"/>
        <input onclick="quizSubmit('<%=id%>','<%=callBack%>')" type="button" name="tijiao" class="bgsubmit joonbtn"  value="保存"/>
        <input onclick="closeOverlay()" type="button" name="quxiao" class="bgsubmit joonbtn"  value="取消"/>
    </div>
</div>
<script type="text/javascript">
    var cataData= {subjectName:"<s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getSubject(#request.quiz.subjectCode)" escape="false"/>",
        gradeName:"<s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getGrade(#request.quiz.gradeCode)" escape="false"/>",
        publishName:"<s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getPublish(#request.quiz.publishCode)" escape="false"/>",
        volumeName:"<s:property value="@com.gzxtjy.datacenter.dictionary.CatalogDicUtil@getVolume(#request.quiz.volumeCode)" escape="false"/>",
        unitCode:"${quiz.unitCode}",
        sectionCode:"${quiz.sectionCode}"
    };
    $("#catalogSearchDiv").load("/teacher/resources/frontResourcesAction_catalogSearch.action?push=false",cataData);
</script>