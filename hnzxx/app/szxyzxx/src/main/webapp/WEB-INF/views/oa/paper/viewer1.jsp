<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/my.css"
	rel="stylesheet">
<title>公文</title>
<script>
/* 	$(function() {
		 $(".figure").hide();//隐藏wenben
		$(".figure:eq(0)").show();//显示第一个wenben
		$("#dianji a").click(function() {
			$(".on").removeClass("on");//移除样式
			$(this).addClass("on");//添加样式
			var i = $(this).index();//获得下标
			$(".figure").hide();//隐藏wenben
			$(".figure:eq(" + i + ")").show();//显示第i个wenben
		}); 
	})  */
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="查看公文" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="x-head content-top">
							<a href="javascript:void(0);" class="on">查看公文</a><a
								href="javascript:void(0);" class="back right" onclick="back();"><i
								class="fa fa-arrow-circle-left"></i>返回</a>
						</h3>
					</div>
					<div class="substance">
						<div class="title_1">${papervo.title }</div>
						<c:choose>
							<c:when test="${papervo.receiverType==0 }">
								<div class="time_1 all">${papervo.author }&nbsp;&nbsp;&nbsp;&nbsp;
									${papervo.publishDate }
								</div>
							</c:when>
							<c:otherwise>
								<div class="time_1 one">${papervo.author }&nbsp;&nbsp;&nbsp;&nbsp;
									${papervo.publishDate }
								</div>
							</c:otherwise>
						</c:choose>
						<p style="word-wrap:break-word;">${papervo.content }</p>
						<div class="right_1"></div>
						<c:if test="${not empty papervo.attachmentUuid}">
						<div class="wendang">
							<p>
								<a target="_blank" id="a" href='<entity:getHttpUrl uuid="${papervo.attachmentUuid}"/>'>${papervo.realFileName}</a>
							</p>
						</div>
						</c:if>
						  <div class="chayue">
							<i class="fa fa-eye"></i>查阅情况<span>${puList.size()}/${papervo.receiverCount }</span>
						</div> 
						 <div class="change">
							<div id="dianji">
								<a href="javascript:void(0)" 
									onclick="searchByCondition('weiyuedu')" id="weiyuedu" class="on">未查阅（${puLists.size() }）</a>
								<a href="javascript:void(0)"
									onclick="searchByCondition('yiyuedu')" id="yiyuedu">已查阅（${puList.size() }）</a>
							</div>
							<jsp:include page="./yuedu_index.jsp"/>
						</div> 
					</div> 
					<div class="back_btn">
						<button class="btn" onclick="back();">返回</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
function back(){
	window.location.href = document.referrer;
 }


</script>
</html>