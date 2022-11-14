<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/statistics/questions.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>Insert title here</title>
</head>
<body>
<div class="title-count">

    <div class="title-content" style="margin-top:15px;    background-color: #fff;">
        <div class="head">题目应答情况统计
         <p style="float: right;position:relative;top:-45px;right:5px;" class="btn_link">
								<a class="a4" href="${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=SHI_JUAN_GUAN_LI&relateId=${param.relateId}&gradeId=${param.gradeId}"><i class="fa fa-reply"></i> 返回 </a>
		</p>
        </div>
        <table border="0" cellspacing="0" cellpadding="0">
            <thead>
                <tr>
                    <th>序号</th>
                    <th>题型</th>
                    <th>作答人数</th>
                    <th>正答人数</th>
                    <th>正答率</th>
                    <th>完成率</th>
                   <!--  <th>操作</th> -->
                </tr>
            </thead>
            <tbody>
            <c:if test="${empty list}">
            <tr>
            <td colspan="7"> 
                                  暂无数据
           </td>
            </tr>
            </c:if>
            <c:forEach items="${list}" var="vo" varStatus="index">
            <tr>
                    <td>${index.count}</td>
                    <td>${vo.questionType}</td>
                    <td>${vo.answerCount}</td>
                    <td>${vo.rightAnswerCount}</td>
                    <td>${vo.rightRate}%</td>
                    <td>${vo.finishRate}%</td>
                    <%-- <td><a href="${pageContext.request.contextPath}/statistic/single?examIdString=${param.examIdString}&relateId=${param.relateId}&taskId=${param.taskId}&questionUuId=${vo.questionUuid}&paperId=${param.paperId}&num=${index.count}">单题统计</a></td> --%>
             </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>