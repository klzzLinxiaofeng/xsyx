<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/kwgl.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <title>班级学生成绩单</title>
</head>
<body >
<div class="kwgl">
    <div class="return_kw">
        <p>查看详情 ${examWorks.name} - ${teamName} 考试信息</p>
        <a href="javascript:void(0)" class="btn btn-lightGray" onclick="toIndex();">返回</a>
    </div>
    <div class="pd20">
        <div class="kw_select">
            <p class="xnxq fl" style="margin:0">${title}</p>
            <p class="fr" style="color:#86939d">考试时间：<fmt:formatDate value="${examWorks.examDateBegin}" pattern="yyyy/MM/dd HH:mm"/></p>
        </div>
        <div class="kwgl_main">
            <div class="ks_list" style="padding:0">
                <table class="table table-striped table1" style="border:1px solid #e4e8eb;">
                    <thead><tr><th >学号</th><th >姓名</th><th >科目</th><th >分数</th><th>班内排名</th><th class="caozuo">操作</th></tr></thead>
                    <tbody>
                    <c:forEach items="${list}" var="item" varStatus="status">
                        <tr>
                            <td>${item.number}</td>
                            <td>${item.name}</td>
                            <td>${subjectName}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.score eq -1}">
                                        --
                                    </c:when>
                                    <c:otherwise>
                                        ${item.score}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${item.teamRank}</td>
                            <td class="caozuo">
                                <button class="btn btn-green" onclick="toAnalysis('${item.userId}')">个人成绩分析</button>
                                <%--<button class="btn btn-blue">导出分析报告</button>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function toAnalysis(userId){
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/analysis/student?examWorksId=${examWorks.id}&userId=" + userId;
    }

    function toIndex() {
        window.location.href="${pageContext.request.contextPath}/teach/scoreStatistics/exam/index?isJoint=false";
    }
</script>
</html>