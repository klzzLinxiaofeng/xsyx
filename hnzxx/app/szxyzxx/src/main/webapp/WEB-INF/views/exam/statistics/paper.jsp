<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/res/css/statistics/questions.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>Insert title here</title>
<style>
	.nierong input[type="text"]{
		border:0 none;
		border-bottom:1px solid #ccc;
		background-color:#E7EAEB;
	}
</style>
</head>
<body>
<script type="text/javascript">
$(function(){
    $(".subject-content").hide();//隐藏wenben
    $(".subject-content:eq(0)").show();//显示第一个wenben
    $(".topic a:eq(0)").addClass("choice");
    $(".topic a").click(function(){
        $(".topic a").removeClass("choice");//移除样式
        $(this).addClass("choice");//添加样式
        var i=$(this).index();//获得下标
        $(".subject-content").hide();//隐藏wenben
        $(".subject-content:eq("+i+")").show();//显示第i个wenben
    });
  //单题统计题目input不能输入
    $(".nierong input[type='text']").attr("disabled","disabled");
    
});
</script>
<body>
<div class="title-count">
<p style="float: right;margin-right:12px;" class="btn_link">
	<c:if test="${canReturn }">
		<a class="a4" href="${pageContext.request.contextPath}/exampublish/publishManagerIndex?dm=SHI_JUAN_GUAN_LI&relateId=${param.relateId}&gradeId=${param.gradeId}"><i class="fa fa-reply"></i> 返回 </a>
	</c:if>
		</p>
    <div class="title-content" style="margin-top:38px;min-height: 500px;">
        <div class="subject-choice">
            <div class="subject" style="border-top: none;">题目</div>
            <div class="topic" >
                <c:forEach items="${qList}" var="item" varStatus="index">
            <a href="javascript:void(0);">${item.pos}</a>
            </c:forEach>
            </div>

        </div>
          <c:forEach items="${qList}" var="q">
        <div class="subject-content">
            <div class="subject" style="border-top: none;">题目内容</div>
            <div class="nierong">
             <c:if test="${!q.isComplex}">
             ${q.content}
             </c:if>
             <c:if test="${q.isComplex}">
             ${q.complexTitle}
             </br>
             ${q.content}
             </c:if>
             <br>
            <c:choose>
				<c:when test="${q.questionType eq 'checkbox' || q.questionType eq 'radio'}">
					<c:forEach items="${q.webQuestionAnswer }" var="name" varStatus="status">
						<span class="xuanxiang" data-a="${status.index}"></span>：${name }</br>
					</c:forEach>
				</c:when>
				<c:when test="${q.questionType eq 'trueOrFalse' }">
					×</br>√
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
			</div>
            <div class="subject">答案</div>
				<div class="nierong">
					<c:choose>
	            	<c:when test="${q.questionType eq 'checkbox' || q.questionType eq 'radio' || q.questionType eq 'trueOrFalse'}">
		            	<c:forEach items="${q.webCorrectAnswer }" var="name" varStatus="status">
		            		${name } <c:if test="${!status.last }">,&nbsp;</c:if>
		            	</c:forEach>
	            	</c:when>
	            	<c:otherwise>
	            		<c:forEach items="${q.webCorrectAnswer }" var="name" varStatus="status">
		            		${name } <br/>
		            	</c:forEach>
	            	</c:otherwise>
	            	</c:choose>
            	</div>
            <div class="subject">解析</div>
            <div class="nierong">
            ${q.explanation}
            </div>
        </div>
        </c:forEach>
    </div>
</div>
</body>
<script type="text/javascript">
$(function(){
	var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
	$(".xuanxiang").each(function(){
		var index = $(this).data("a");
		$(this).text(zm[index]);
	});
});
</script>
</html>