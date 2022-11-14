<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/dxaxj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>试卷查看</title>
</head>
<body>
	<c:if test="${back != 0 }">
		<a href="javascript:void(0)" onclick="back(this)" style="margin: 15px 15px 0 15px;display:block;">< 返回列表</a>
	</c:if>
	<div class="sj_view" style="width:100%;margin-top:0;">
		<div class="paper_title">
			<div class="p1" style="padding-top:0">${paper.title}</div>
			<div class="p2">
				<ul>
					<li class="zf"><b></b>总分：${paper.score}</li>
					<li class="tm"><b></b>题目数量：${paper.questionCount}</li>
					<li class="bh"><b></b>${subject}</li>
				</ul>
			</div>
			<div class="p3 nr_right">
				<div class="px_top">
					<div class="left">
						<a href="#" onclick="orderByParam('nodeOrder', this);"
							<c:if test="${property eq 'nodeOrder'}">class="on"</c:if>>题号顺序<b
							class="fa <c:if test="${asc}">fa-long-arrow-up</c:if><c:if test="${!asc}">fa-long-arrow-down</c:if>"></b></a> <a href="#"
							onclick="orderByParam('used_count', this);"
							<c:if test="${property eq 'used_count'}">class="on"</c:if>>使用次数<b
							class="fa <c:if test="${asc}">fa-long-arrow-up</c:if><c:if test="${!asc}">fa-long-arrow-down</c:if>"></b></a> <a href="#"
							onclick="orderByParam('fav_count', this);"
							<c:if test="${property eq 'fav_count'}">class="on"</c:if>>收藏次数<b
							class="fa <c:if test="${asc}">fa-long-arrow-up</c:if><c:if test="${!asc}">fa-long-arrow-down</c:if>"></b></a>
					</div>
					<div class="right">

						<!-- 				<span>225</span>份导学案 -->

						<p class="if_show">

							<input type="checkbox">显示答案与解析
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="test-paper-right-content" style="padding: 0 0 20px">
			<c:forEach items="${list}" var="item" varStatus="index">
				<p>${index.index+1}、${item.name}</p>
				<ul>
				<c:forEach items="${item.childrens}" var="q" varStatus="j">
					
						<li class="st">
							<div class="timu">
								<span> ${q.obj.nodeOrder+1}、${q.obj.content} </span>
								<div class="timu-choose">
									<c:choose>
										<c:when
											test="${q.obj.questionType eq 'checkbox' || q.obj.questionType eq 'radio'}">
											<%-- <c:set value="${ fn:split(q.obj.answer, ',') }" var="str" /> --%>
											<ul>
												<c:forEach items="${q.obj.answers}" var="name" varStatus="status">
													<li><span class="xuanxiang" data-a="${status.index}"></span>${name }</li>
												</c:forEach>
											</ul>
										</c:when>
										<c:when test="${q.obj.questionType eq 'trueOrFalse' }">
											<ul>
												<li><span class="xuanxiang"></span>×</li>
												<li><span class="xuanxiang"></span>√</li>
											</ul>
											<!-- ×</br>√ -->
										</c:when>
										<c:otherwise>
											<%-- 					${q.obj.answer} --%>

										</c:otherwise>
									</c:choose>
									<c:if test="${!empty q.childrens}">
										<c:forEach items="${q.childrens}" var="t3" varStatus="z">
								       <c:if 	test="${q.obj.questionType eq 'complex' }">
                                       <span> (${t3.obj.nodeOrder+1})、${t3.obj.content} </span><br>
                                       </c:if>
                                        <c:choose>
										<c:when
											test="${t3.obj.questionType eq 'checkbox' || t3.obj.questionType eq 'radio'}">
											<%-- <c:set value="${ fn:split(t3.obj.answer, ',') }" var="str1" /> --%>
											<div>
											<span style="float: left;margin-top:8px;">(${t3.obj.nodeOrder+1})</span>
											<ul style="margin-left: 24px;">
												<c:forEach items="${t3.obj.answers}" var="name1" varStatus="status1">
													<li style="display:inline-block;"><span class="xuanxiang" data-a="${status1.index}"></span>${name1}</li>
												</c:forEach>
											</ul>
											</div>
										</c:when>
										<c:when test="${t3.obj.questionType eq 'trueOrFalse' }">
											<ul>
												<li><span class="xuanxiang"></span>×</li>
												<li><span class="xuanxiang"></span>√</li>
											</ul>
											<!-- ×</br>√ -->
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
										</c:forEach>
									</c:if>
								</div>
							</div>
							<div class="jiexi">
								<p class="j1">
									【答案】
									<c:if test="${empty  q.childrens}">
									<span>${q.obj.correctAnswer}</span>
									</c:if>
									<c:if test="${!empty  q.childrens}">
									<c:forEach items="${q.anwerList }" var ="anw" varStatus="anwIndex">
									<span>(${anwIndex.index+1}).${anw}</span><br>
									</c:forEach>
									</c:if>
								</p>
								<p class="j2">
									【解析】
									 <c:if test="${empty  q.childrens}">
									<span>${q.obj.explanation}<c:if test="${empty q.obj.explanation }">无</c:if></span>
									</c:if>
									<c:if test="${!empty  q.childrens}">
									<c:forEach items="${q.exp }" var ="ex" varStatus="exIndex">
									<span>(${exIndex.index+1}).${ex}<c:if test="${empty ex }">无</c:if></span><br>
									</c:forEach>
									</c:if>
								</p>
							</div>
							<div class="timu-handle">
								<ul>
									<li><span><i class="difficulty"></i>难度：<b>${q.obj.difficulityString}</b></span>
									</li>
									<li><span><i class="update-time"></i>更新时间：<b><fmt:formatDate
													value="${q.obj.modifyDate}" type="both"
													pattern="YYYY/MM/dd HH:mm:ss" /></b></span></li>
									<li><span><i class="collect"></i>收藏(<b id="${q.obj.id}" class="blue">${q.obj.favCount}</b>)</span>
									</li>
									<li><span><i class="use"></i>使用(<b class="blue">${q.obj.usedCount}</b>)</span>
									</li>
									<li style=" float: right;position: relative;top: -5px;">
										<c:if test="${not empty isBasket}">
								<div class="cz_btn">
									<c:choose>
										<c:when test="${q.obj.isFav}">
											<btn class="btn btn-lightGray" onclick="fav(${q.obj.id},1,this)"
												fav="${!q.obj.isFav}">取消收藏</btn>
										</c:when>
										<c:otherwise>
											<btn class="btn btn-blue" onclick="fav(${q.obj.id},1,this)"
												fav="${!q.obj.isFav}">收藏</btn>
										</c:otherwise>
									</c:choose>
									<btn class="btn btn-orange basket" data-stage="${stageCode}" data-questionId="${q.obj.id}" data-count="${q.count}" onclick="window.parent.addToBasket(this)">加入试题篮</btn>
								</div>
								</c:if>
									</li>
								</ul>
							</div>
						</li>
				</c:forEach>
				</ul>
			</c:forEach>
		</div>
	</div>
</body>
<script>
$(function(){
//  是否显示答案跟解析
	$(".if_show input").click(function(){
		if($(this ).is(':checked' )){
			$(".jiexi").show();
		}else{
			$(".jiexi").hide();
		}
	})
	
	/**处理波浪线*/
	$("span").each(function () {
        var style = $(this).attr("style");
        if(style != undefined && style != ""){
            if(style.indexOf("symbol:waveline") != -1){
                 $(this).addClass("waveline");
            }
        }
    })
})
	$(function(){
		var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
		$(".xuanxiang").each(function(){
			var index = $(this).data("a");
			$(this).text(zm[index]);
		});
	});
	
function orderByParam(orderParam, obj) {
	var b = $(obj).children("b");
	var ascending = null;
	if(b.hasClass("fa-long-arrow-down")){
		ascending = true;
	} else {
		ascending = false;
	};
	var url = "${pageContext.request.contextPath}/learningPlan/paper/viewer?paperId=${param.paperId}&property="+orderParam+"&asc="+ascending;
	$(".test-paper-right-content").load(url);
	
}

//收藏
function fav(id, obj) {
	var fav = $(obj).attr("fav");
    $.ajax({
        url: "${pageContext.request.contextPath}/paper/doFav",
        type: "POST",
        data: {"id":id, "fav":fav,"type":0},
        async: false,
        success: function() {
        	if(fav=="true") {
        		$.success("收藏成功！");
				var sc=$(obj).parent().prev(".detail").children(".sc").children("span");
				sc.text((parseInt(sc.text())+1));
        		$(obj).text("取消收藏");
        		$(obj).attr("fav",false);
        		$(obj).attr("class","btn btn-lightGray")
        	} else {
        		$.success("取消收藏成功！");
        		var sc=$(obj).parent().prev(".detail").children(".sc").children("span");
				sc.text((parseInt(sc.text())-1));
        		$(obj).text("收藏");
        		$(obj).attr("fav",true);
        		$(obj).attr("class","btn btn-blue")
        	}
        }
    });
}


function back(obj) {
	var sign = $("#sign").val();
	var personSign = $("#personSign").val();
	if(sign=="school") {
		$("#schoolMicro").click();
	} else {
		$("#myMicro").click(function() {});
		if(personSign=="myresource") {
			$("#myresource").click();
		} else if(personSign=="favresource") {
			$("#favresource").click();
		} else if(personSign=="myshare") {
			$("#myshare").click();
		}
	}
}
</script>
</html>