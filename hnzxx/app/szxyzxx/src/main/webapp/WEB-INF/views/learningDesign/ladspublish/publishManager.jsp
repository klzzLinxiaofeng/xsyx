<%-- 
    Document   : publishManager
    Created on : 2015-6-9, 15:14:30
    Author     : Administrator
--%>

<%@page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/taglib.jsp"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<!DOCTYPE html>
<table class="responsive table table-striped" id="data-table">
    <thead>
        <tr role="row">
            <th>上课时间</th>
            <th>课程名称</th>
            <th>班级</th>
            <th>科目</th>
            <th>学习人数</th>
            <th>未学习人数</th>
            <th>完成进度</th>
            <th class="caozuo">操作</th>
        </tr>
    </thead>
    <tbody id="module_list_content">
        <c:if test="${mlrList== null || fn:length(mlrList) == 0}">
            <tr>
                <td colspan="8">没有课件,请布置</td>
            </tr>
        </c:if>
        <c:forEach items="${mlrList}" var="micro">
            <tr>
                <td><fmt:formatDate value="${micro.startDate}" pattern="yyyy年MM月dd日 "/>~<fmt:formatDate value="${micro.finishedDate}" pattern="yyyy年MM月dd日 HH"/> 点</td>
                <td>${micro.realMicroArray[0].title}</td>
                <td>${fn:substringBefore(micro.relateName, "[")}</td>
                <td>${fn:substring(micro.relateName, fn:indexOf(micro.relateName,"[")+1,fn:indexOf(micro.relateName,"]"))}</td>
                <td>${micro.finishedCount}</td>
                <td>${micro.unFinishedCount}</td>
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
                <td class="caozuo">
                    <a target="_blank" href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/monitoring/monitor?ldId=${micro.realMicroArray[0].ldId}">上课监控</a>
                    <a target="_blank" href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/learning/info?ldId=${micro.realMicroArray[0].ldId}">学习预览</a>
                    <a onclick="resetDate('${micro.publishMicroLessonId}', '<fmt:formatDate value="${micro.startDate}" pattern="yyyy-MM-dd HH "/>', '<fmt:formatDate value="${micro.finishedDate}" pattern="yyyy-MM-dd HH "/>')" href="javascript:void(0)">修改时间</a>
                    <a onclick="deletePublish('${micro.publishMicroLessonId}')" href="javascript:void(0)">删除</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
