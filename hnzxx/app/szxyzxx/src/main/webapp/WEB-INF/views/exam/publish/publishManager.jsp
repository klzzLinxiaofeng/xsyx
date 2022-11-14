<%-- 
    Document   : publishManager
    Created on : 2015-6-9, 15:14:30
    Author     : Administrator
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/taglib.jsp"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<!DOCTYPE html>
<table class="responsive table table-striped" id="data-table" style="margin-top: 20px;">
    <thead>
        <tr role="row">
            <th>上课时间</th>
             <th>标题</th>
            <th>班级</th>
<!--             <th>科目</th> -->
           <!--  <th>学习人数</th>
            <th>未学习人数</th> -->
            <th>完成进度</th>
            <th>发布教师 </th>
            <th class="caozuo">操作</th>
        </tr>
    </thead>
    <tbody id="module_list_content">
        <c:if test="${mlrList== null || fn:length(mlrList) == 0}">
            <tr>
                <td colspan="7">没有试卷,请布置</td>
            </tr>
        </c:if>
        <c:forEach items="${mlrList}" var="micro">
            <tr>
                <td><fmt:formatDate value="${micro.startDate}" pattern="yyyy年MM月dd日 HH:mm "/>~<fmt:formatDate value="${micro.finishedDate}" pattern="yyyy年MM月dd日 HH:mm"/> 点</td>
                 <td>${micro.examTitle}</td>
                <c:if test="${fn:contains(micro.relateName, '[')}" >
                <td>${fn:substringBefore(micro.relateName, "[")}</td>
<%--                 <td>${fn:substring(micro.relateName, fn:indexOf(micro.relateName,"[")+1,fn:indexOf(micro.relateName,"]"))}</td> --%>
                </c:if>
                <c:if test="${!fn:contains(micro.relateName, '[')}" >
                <td>${micro.relateName}</td>
<!--                 <td></td> -->
                </c:if>
               <%--  <td>${micro.finishedCount}</td>
                <td>${micro.unFinishedCount}</td> --%>
                <td>
                    <c:choose>
                        <c:when test="${micro.finishFlag}">
                            <p class="end"></p>
                        </c:when>
                        <c:otherwise>
                            <p class="doing"></p>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>${micro.publisherName}</td>
                <td class="caozuo">
                    <c:if test="${micro.type eq 'common_exam'}">
                    <a href="${pageContext.request.contextPath}/exampublish/publishDetails?dm=${param.dm}&index=index&microPublishedId=${micro.publishMicroLessonId}&relateId=${micro.relateId}&relateType=${relateType}">查看详情</a>
                    <a onclick="resetDate('${micro.publishMicroLessonId}', '<fmt:formatDate value="${micro.startDate}" pattern="yyyy-MM-dd HH:mm "/>', '<fmt:formatDate value="${micro.finishedDate}" pattern="yyyy-MM-dd HH:mm "/>')" href="javascript:void(0)">修改时间</a>
                    </c:if> 
                    <c:if test="${micro.type eq 'xep_exam'}">
<%--                     <a href="${pageContext.request.contextPath}/exampublish/question?dm=${param.dm}&teamId=${micro.relateId}&time=${micro.timeString}&title=${micro.examTitle}&paperId=${micro.paperId}&taskId=${micro.taskId}">查看各题的正确率</a> --%>
<%--                     <a href="${pageContext.request.contextPath}/exampublish/scope?dm=${param.dm}&teamId=${micro.relateId}&time=${micro.timeString}&title=${micro.examTitle}&paperId=${micro.paperId}">查看每人正确率</a> --%>
<%--                     <a href="javascript:void(0)" onclick="mark('${micro.pjExamId}','${micro.paperId}','${micro.taskId}','${micro.finishFlag}')">生成统计</a> --%>
                    <a href="${pageContext.request.contextPath}/statistic/paper?paperId=${micro.paperId}&relateId=${micro.relateId}&gradeId=${gradeId}" >查看试卷</a>
                    <a href="javascript:void(0)" onclick="volume('${micro.pjExamId}','${micro.relateId}','${gradeId}','${micro.finishFlag}','${micro.paperId}','${micro.taskId}')">查看统计</a>
<%--                     <a href="javascript:void(0)" onclick="question('${micro.pjExamId}','${micro.relateId}','${gradeId}','${micro.taskId}','${micro.paperId}','${micro.finishFlag}')">答题统计</a> --%>
                    </c:if>
                    <c:if test="${empty micro.type }">
                    <span>该资源已被删除</span>
                    </c:if>
                    <a onclick="deletePublish('${micro.publishMicroLessonId}',${micro.publisherId},${userId},'${micro.pjExamId}')" href="javascript:void(0)">删除</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
