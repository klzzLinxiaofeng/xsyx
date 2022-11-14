<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="background: #e3e3e3;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link
	href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/dxaxj.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css"
	rel="stylesheet">
<title>查看试卷</title>
<style>
.timu .timu-choose ul li{
	margin-right: 0px;
}

	/*三角形*/
.dxa_ts:before {
    position: absolute;
    top: 35px;
    left: calc(60% - 5px);
    width: 10px;
    height: 10px;
    background: #e7faff;
    box-shadow: 2px 2px 0 -1px #c4c4c4;
    content: "";
    transform: rotate(45deg);
    border-bottom: 1px solid #9ad3ff;
    border-right: 1px solid #9ad3ff;
}
/*自适应居中*/
.dxa_ts {
    position: absolute;
    width:252px;
    top: -56px;
    left: 22%;
    z-index: 3;
    visibility: hidden;
    padding: 10px;
    line-height:20px;
    min-height: 12px;
    border-radius: 4px;
    background: #e7faff;
    box-shadow: 0 2px 8px rgba(0,0,0,.3);
    color: #2ca0f8;
    font-size: 12px;
    opacity: 0;
    transition: visibility 0s linear .2s,opacity .2s linear 0s;
    transform: translateX(-50%);
    backface-visibility: visible!important;
    backface-visibility: hidden;
    border: 1px solid #9ad3ff;
}
/*hover效果*/
.grey_mj:hover .dxa_ts {
    visibility: visible;
    opacity: 1;
    transition: visibility 0s linear 0s,opacity .4s linear;
    animation: fade-top;
    animation-duration: .4s;
    -webkit-animation: fade-top .4s;
}
/*hover动画*/
@keyframes fade-top {
    0% {
        opacity: .1;
        top: 0
    }
 
    100% {
        opacity: 1;
        top: -56px
    }
}
 
@-webkit-keyframes fade-top {
     0% {
        opacity: .1;
        top: 0
    }
 
    100% {
        opacity: 1;
        top: -56px
    }
}

</style>
</head>
<body>
<c:choose>
	<c:when test="${not empty isBasket}">
		<div class="dxaxj" style="    background: none;">
			<p class="btn-caozuo">
				<button class="btn btn-lightGray" onclick="back()">返回</button>
				<c:choose>
					<c:when test="${isFav}">
						<btn class="btn btn-lightGray" onclick="fav(${paper.id},0,this)" fav="${!isFav}">取消收藏</btn>
					</c:when>
					<c:otherwise>
						<btn class="btn btn-blue" onclick="fav(${paper.id},0,this)" fav="${!isFav}" >收藏</btn>
					</c:otherwise>
				</c:choose>
				
					<btn class="btn btn-orange" data-type="paper" data-paperid="${paper.id}" data-stage="${stageCode}" onclick="window.parent.addToBasket(this)">加入试题篮</btn>
				
			</p>
		</div>
	</c:when>
	<c:otherwise>
		<jsp:include page="./paper_head.jsp"></jsp:include>
		<div class="dxaxj">
			<p class="title">查看</p>
			<p class="btn-caozuo">
<!-- 				<button class="btn btn-lightGray" onclick="back()">返回</button> -->
				<c:choose>
					<c:when test="${isFav}">
						<btn class="btn btn-lightGray" onclick="fav(${paper.id},0,this)" fav="${!isFav}">取消收藏</btn>
					</c:when>
					<c:otherwise>
						<btn class="btn btn-blue" onclick="fav(${paper.id},0,this)" fav="${!isFav}" >收藏</btn>
					</c:otherwise>
				</c:choose>
				<button class="btn btn-red" onclick="pub(${paper.id})">布置</button>
			</p>
		</div>
	</c:otherwise>
</c:choose>
	<div class="sj_view">
		<div class="paper_title">
			<div class="p1">${paper.title}</div>
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
				<p style="font-weight: bold;">${index.index+1}、${item.name}</p>
				<ul>
				<c:forEach items="${item.childrens}" var="q" varStatus="j">
					
						<li class="st">
							<div class="timu">
								<span style="line-height: 26px;display: inline-block;margin-bottom: 10px;"> ${q.obj.nodeOrder+1}、${q.obj.content} </span>
								<div class="timu-choose">
								<%-- <c:set value="${ fn:split(q.obj.answer, ',') }" var="str" /> --%>
									<c:choose>
										<c:when
											test="${q.obj.questionType eq 'checkbox' || q.obj.questionType eq 'radio'||q.obj.questionType eq 'multichoise'}">
											<ul style="margin-top: 8px;">
												<c:forEach items="${q.obj.answers}" var="name" varStatus="status">
													<li><span class="xuanxiang" data-a="${status.index}"></span>${name }</li>
												</c:forEach>
											</ul>
										</c:when>
										<c:when test="${q.obj.questionType eq 'trueOrFalse' }">
											<ul style="margin-top: 8px;">
<!-- 												<li><span class="xuanxiang"></span>×</li> -->
<!-- 												<li><span class="xuanxiang"></span>√</li> -->
                                                     <c:forEach items="${q.obj.answers}" var="name" varStatus="status">
													<li><span  data-a="-1" class="xuanxiang"></span>${name }</li>
												     </c:forEach>
											</ul>
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
										<c:when test="${t3.obj.questionType eq 'checkbox' || t3.obj.questionType eq 'radio' ||t3.obj.questionType eq 'multichoise'}">
<%-- 											<c:set value="${ fn:split(t3.obj.answer, ',') }" var="str1" /> --%>
											<div style="margin-bottom: 20px;">
											 <c:if test="${q.obj.questionType eq 'cloze'}">
											<span style="float: left;margin-top: 3px;">(${t3.obj.nodeOrder+1})</span> 
											 </c:if>
											<ul style="margin-left: 24px;">
												<c:forEach items="${t3.obj.answers}" var="name1" varStatus="status11">
												    <c:if test="${q.obj.questionType eq 'cloze'}">
													<li class="cloze"><span class="xuanxiang" data-a="${status11.index}"></span>${name1}</li>
												    </c:if>
													<c:if test="${q.obj.questionType eq 'complex'}">
													<li><span class="xuanxiang" data-a="${status11.index}"></span>${name1}</li>
													</c:if>
												</c:forEach>
											</ul>
											</div>
										</c:when>
										<c:when test="${t3.obj.questionType eq 'trueOrFalse' }">
										<%-- <c:set value="${ fn:split(t3.obj.answer, ',') }" var="str2" /> --%>
											<ul>
												<c:forEach items="${t3.obj.answers}" var="name2" varStatus="status2">
													<li><span  data-a="${status2.index }" class="xuanxiang"></span>${name2}</li>
												</c:forEach>
<!-- 												<li><span class="xuanxiang"></span>×</li> -->
<!-- 												<li><span class="xuanxiang"></span>√</li> -->
											</ul>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
										</c:forEach>
									</c:if>
								</div>
								<div class="clear"></div>
							</div>
							<div class="jiexi" style="padding: 0 10px;">
								<div class="j1">
					                <span class="jx_wz" style="line-height: 30px;">【答案】</span>
					                    
					                <div class="color1d9 jx_nr" style="line-height: 30px;margin-left: 54px;">
					                	
					                	<c:if test="${empty  q.childrens}">
					                	<c:if test="${q.obj.questionType  eq 'blank'}">
					                	<c:forEach items="${q.obj.correctAnswers}" var="ca">
					                	${ca}<br>
					                	</c:forEach>
					                	</c:if>
					                	<c:if test="${q.obj.questionType  ne 'blank'}">
										<span>${q.obj.correctAnswer}</span>
										</c:if>
										</c:if>
										
										<c:if test="${!empty  q.childrens}">
										<c:forEach items="${q.anwerList }" var ="anw" varStatus="anwIndex">
										<div>
                                       <label  style="display:inline-block;float:left;margin:0;line-height:32px">(${anwIndex.index+1}).</label> 
                                       <div style="margin-left: 26px;line-height: 30px;">${anw}</div> <div class="clear"></div>
										</div>
										</c:forEach>
										</c:if>
										 
									</div>
								</div>
								<div class="clear"></div>
							    <div class="j2" style="margin-bottom: 5px;">
							    	<span class="jx_wz" style="line-height: 30px;">【解析】</span>
							    	<div class="jx_nr" style=" padding-top: 3px;    margin-left: 54px;">
							    		<!-- <p style="font-size: 12px;margin: 8px 0 10px;padding-right: 5px;">  -->
							    			<c:if test="${empty  q.childrens}">
											<span style="line-height: 24px;">${q.obj.explanation}<c:if test="${empty q.obj.explanation }">无</c:if></span>
											</c:if>
											<c:if test="${!empty  q.childrens}">
											<c:forEach items="${q.exp }" var ="ex" varStatus="exIndex">
											<span style="display: inline-block;line-height: 30px;">(${exIndex.index+1}).${ex}<c:if test="${empty ex }">无</c:if></span><br>
											</c:forEach>
											</c:if>
										<!-- </p> -->
							    		
							    	</div>
							    </div>
							</div>
							<div class="timu-handle">
								
									<ul style=" float: right;padding-bottom: 0;">
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
									<span class="grey_mj" style="position:relative">
										<btn class="btn btn-orange basket" data-stage="${stageCode}" data-questionId="${q.obj.id}" data-count="${q.count}" onclick="window.parent.addToBasket(this)">加入试题篮</btn>
									</span>
								</div>
								</c:if>
									</li>
								</ul>
								<ul style="margin-right:180px;">
									<li><span><i class="difficulty"></i>难度：<b>${q.obj.difficulityString}</b></span>
									</li>
									<li><span><i class="update-time"></i>更新时间：<b><fmt:formatDate
													value="${q.obj.modifyDate}" type="both"
													pattern="YYYY/MM/dd HH:mm:ss" /></b></span></li>
									<li><span><i class="collect"></i>收藏(<b id="${q.obj.id}" class="blue">${q.obj.favCount}</b>)</span>
									</li>
									<li><span><i class="use"></i>使用(<b class="blue">${q.obj.usedCount}</b>)</span>
									</li>
									</ul>
							<div class="clear"></div>
							</div>
							
						</li>
					

				</c:forEach>
				</ul>

			</c:forEach>
		</div>
	</div>
	<div><a href="javascript:void(0)" class="gotop" onclick="goTop();" style="display: block;">顶部</a></div>
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
	});
	//如果题目包含图片的高度过于大的话
    $('ul li.st .timu span img').each(function(){
        if($(this).width()>500){
            $(this).width('300');
        }
    });
	
// 	<c:if test="${not empty isBasket}">
// 		window.parent.checkBtnState();
// 	</c:if>
	
	
})
	$(function(){
		var zm = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
		$(".xuanxiang").each(function(){
			var index = $(this).data("a");
			if(index!=-1){
			$(this).text(zm[index]);
			}
		});
		
		<c:if test="${not empty isBasket}">
			window.parent.checkBtnState();
		</c:if>
		
	});
function orderByParam(orderParam, obj) {
	var b = $(obj).children("b");
	var ascending = null;
	if(b.hasClass("fa-long-arrow-down")){
		ascending = true;
	} else {
		ascending = false;
	};
	var url="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId=${param.paperId}&property="+orderParam+"&asc="+ascending;
	<c:if test="${not empty isBasket}">
		url+="&isBasket=0";
	</c:if>
	window.location.href=url;
}
//收藏
function fav(id,type,obj) {
	var fav = $(obj).attr("fav");
    $.ajax({
        url: "${pageContext.request.contextPath}/paper/doFav",
        type: "POST",
        data: {"id":id, "fav":fav,"type":type},
        async: false,
        success: function() {
        	if(fav=="true") {
        		$.success("收藏成功！");
				var sc=$("#"+id+"");
				sc.text((parseInt(sc.text())+1));
        		$(obj).text("取消收藏");
        		$(obj).attr("fav",false);
        		$(obj).attr("class","btn btn-lightGray")
        	} else {
        		$.success("取消收藏成功！");
        		var sc=$("#"+id+"");
				sc.text((parseInt(sc.text())-1));
        		$(obj).text("收藏");
        		$(obj).attr("fav",true);
        		$(obj).attr("class","btn btn-blue")
        	}
        }
    });
}
function pub(id){
	window.location.href="${pageContext.request.contextPath}/paperTask/choose/team?dm=BU_ZHI_SHI_JUAN&paperId="+id;
}
function back(){
	 history.go(-1);
}
$(function(){
	$("span").each(function () {
        var style = $(this).attr("style");
        if(style != undefined && style != ""){
            if(style.indexOf("symbol:waveline") != -1){
                 $(this).addClass("waveline");
            }
        }
    })
})
 /**
	 * 回到页面顶部
	 * @param acceleration 加速度
	 * @param time 时间间隔 (毫秒)
	 **/
	function goTop(acceleration, time) {
	  
	  acceleration = acceleration || 0.1;
	  time = time || 16;
	 
	  var x1 = 0;
	  var y1 = 0;
	  var x2 = 0;
	  var y2 = 0;
	  var x3 = 0;
	  var y3 = 0;
	 
	  if (document.documentElement) {
	    x1 = document.documentElement.scrollLeft || 0;
	    y1 = document.documentElement.scrollTop || 0;
	  }
	  if (document.body) {
	    x2 = document.body.scrollLeft || 0;
	    y2 = document.body.scrollTop || 0;
	  }
	  var x3 = window.scrollX || 0;
	  var y3 = window.scrollY || 0; 
	  
	   
	   
	  // 滚动条到页面顶部的水平距离
	  var x = Math.max(x1, Math.max(x2, x3));
	  // 滚动条到页面顶部的垂直距离
	  var y = Math.max(y1, Math.max(y2, y3));
	 
	  // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
	  var speed = 1 + acceleration;
	  window.scrollTo(Math.floor(x / speed), Math.floor(y / speed));
	  
	 
	  // 如果距离不为零, 继续调用迭代本函数
	  if(x!= 0 || y != 0) {
	    var invokeFunction = "goTop(" + acceleration + ", " + time + ")";
	    window.setTimeout(invokeFunction, time);
	    
	  }
	}
</script>
</html>