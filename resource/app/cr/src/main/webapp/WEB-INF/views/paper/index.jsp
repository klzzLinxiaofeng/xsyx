<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<style>
.content_main .nr_right{border-radius:5px;}
.content_main .nr_right .px_top{    border-radius: 5px 5px 0 0;}
</style>
<title>试卷</title>
</head>
<body>
<jsp:include page="./paper_head.jsp"></jsp:include>

<!-- <input id="isRefresh" name="isRefresh" -->
<%-- 	value="${isRefresh}" /> --%>

<div class="content_main">
	<div class="ku_div">
		<ul>
<!-- 			<li><a href="javascript:void(0)" onclick="changeTab('public', this)" class="a1">公共库</a></li> -->
			<li class="on"><a href="javascript:void(0)"  onclick="changeTab('school', this)" class="a2">校本库</a></li>
			<li><a href="javascript:void(0)"  onclick="changeTab('personal', this)" class="a3">个人库</a></li>
			<li><a href="javascript:void(0)"  onclick="changeTab('fav', this)" class="a4">收藏夹</a></li>
		</ul>
	</div>
	<jsp:include page="/views/embedded/plugin/stageWidget.jsp"></jsp:include>
	<div class="nr_right">
	<div class="px_top">
			<div class="left">
				<a href="#" onclick="orderByParam('modify_date', this);" class="on">更新时间<b class="fa fa-long-arrow-down"></b></a>
				<a href="#" onclick="orderByParam('used_count', this);">使用次数<b class="fa fa-long-arrow-up"></b></a>
				<a href="#" onclick="orderByParam('fav_count', this);">收藏次数<b class="fa fa-long-arrow-up"></b></a>
			</div>
			<div class="right">总共有:<span></span>份试卷</div>
	</div>
		<div class="dxa_list" style="" id="paPaper_list_content">
			<jsp:include page="./list.jsp" />
		</div>
		
		<div style="margin:20px;padding:0" class="page">
		<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
			<jsp:param name="id" value="paPaper_list_content" />
			<jsp:param name="url" value="/paper/list"/>
			<jsp:param name="pageSize" value="${page.pageSize}" />
		</jsp:include>
		</div>
		<div class="clear"></div>
 	</div>
</div>
</body>

<script type="text/javascript">
var index=0;
var param = null;
var topTab = "school";

var id = "paPaper_list_content";
var url = "/paper/list";

var objectType="paper";


$(function() {
// 	var isRefresh=$("#isRefresh").val();
// 	alert(isRefresh);
// 	if(eval(isRefresh)){
// 		$("#isRefresh").val(false);
// 		alert("is in");
	
// 	}
	
	invokeStageWidget(0);
	
	
	//关闭左侧菜单
	$("body").on("click",".close_left",function(){
		$(".nr_left").fadeOut("100");
		$(".nr_right").css("margin-left","0");
		$(".nicescroll-rails").hide();
		$(this).removeClass("close_left").addClass("open_left");
	})
	//展开左侧菜单
	$("body").on("click",".open_left",function(){
		$(".nr_left").fadeIn("100");
		$(".nr_right").css("margin-left","256px");
		$(".nicescroll-rails").show();
		$(this).removeClass("open_left").addClass("close_left")
		if(index==0) {
			$(".ml_tishi,.zzc").show();
			index++;
		}
	});
})

//调用学段科目组件
function invokeStageWidget(type, afterHandler) {
	$.stageWidget(type, false, function(data) {//0是校本1是公共， true开启知识点
 		//记录科目和学段
//  		data["type"]=topTab;
// 		param = data;
		param= $.extend(param,data);
		delete param.type;
		getPaperList(param); 
		//个人库和收藏夹不需要调用目录组件
		if("personal"!=topTab && "fav"!=topTab) {
			invokeVersionAndVolumn(type, data);
		}
	})
	//是否有回调
	if(afterHandler!=null){
		afterHandler(param);
	}
}

//调用目录组件
function invokeVersionAndVolumn(type, data) {
	$.getVersionAndVolumn(type, data, function(info) {
		//记录科目、学段和目录
		param= $.extend(param,info);
		getPaperList(param);
	})
}

//根据目录
function getPaperList(data,isStayCurrentPage) {
	data["libType"]=topTab;
	
 	//alert(JSON.stringify(data));
	myPagination(id, data, url, function() {
		//刷新总数
		$(".nr_right .px_top .right").children("span").text($("#total").val());
		//调整样式
		if($(".no_resource").is(":hidden")){
			var h1=$(".nr_right .dxa_list ul").height();
			if(h1>500){
				var h=h1-28;
				$(".jspContainer").css("height",h);
			}else{
				$(".jspContainer").css("height","472px");
			}
		}else{
			$(".jspContainer").css("height","472px");
		}
	},isStayCurrentPage);
}

//切换公共库、校本库......
function changeTab(type, obj) {
	//公共库、校本库的标志
	topTab = type;
	
	$(obj).parents("ul").children("li").removeClass("on");
	$(obj).parent("li").addClass("on");
	
	var tab = 0;
	//显示目录组件
	$(".neirong_zs").show();
	if(type=="public") {
		var tab = 1;
	} else if(type=="personal" || "fav"==type) {
		//个人库隐藏目录组件
		$(".neirong_zs").hide();
	}
	 
	//根据不同的库去调用科目组件
	invokeStageWidget(tab);
	
	$(".nr_right").css("margin-left","0");
	$(".nr_left").hide();
	if($(".close_left").length==1){
		$(".close_left").removeClass("close_left").addClass("open_left")
	}
}

//排序
function orderByParam(orderParam, obj) {
	//切换筛选标志
	$(obj).parent(".left").children("a").removeClass("on");
	$(obj).addClass("on");
	
	var b = $(obj).children("b");
	var ascending = null;
	if(b.hasClass("fa-long-arrow-down")){
		ascending = true;
		b.removeClass("fa-long-arrow-down").addClass("fa-long-arrow-up");
	} else {
		ascending = false;
		b.removeClass("fa-long-arrow-up").addClass("fa-long-arrow-down");
	};
	//添加排序的参数
	param["property"] = orderParam;
	param["ascending"] = ascending;
	getPaperList(param);
}

//收藏
function fav(id, obj) {
	var fav = $(obj).attr("fav");
	var isOver=false;
    $.ajax({
        url: "${pageContext.request.contextPath}/paper/doFav",
        type: "POST",
        data: {"id":id, "fav":fav,"type":0},
        async: false,
        success: function() {
        	if("fav"==topTab) {
        		var total=parseInt($("#total").val());
        		var flag=parseInt(($("#currentPage").val()-1)*10+1);
				if(total==flag){
					param["currentPage"]=parseInt($("#currentPage").val()-1);
					$('.pagination').jqPagination('option',{'current_page':$("#currentPage").val()-1});
				}else{
					param["currentPage"]=$("#currentPage").val();
				}
        		getPaperList(param,"isStayCurrentPage");
    			delete param.currentPage;
        		return;
        	}
        	if(fav=="true") {
        		$.success("收藏成功！");
				var sc=$(obj).parent().prev(".detail").children(".sc").children("span");
				sc.text((parseInt(sc.text())+1));
        		$(obj).text("取消收藏");
        		$(obj).attr("fav",false);
        		$(obj).attr("class","btn btn-lightGray");
        	} else {
        		$.success("取消收藏成功！");
        		var sc=$(obj).parent().prev(".detail").children(".sc").children("span");
				sc.text((parseInt(sc.text())-1));
        		$(obj).text("收藏");
        		$(obj).attr("fav",true);
        		$(obj).attr("class","btn btn-blue");
        	}
        }
    });
}

function pub(id){
	var URL="${pageContext.request.contextPath}/paperTask/choose/team?dm=${param.dm1}&paperId="+id;
// 	window.location.href="${pageContext.request.contextPath}/paperTask/choose/team?dm=${param.dm1}&paperId="+id;
	window.open(URL);
}
function look(id){
// 	   window.location.href="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
	   var URL="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
// 	   window.location.href="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
	   window.open(URL);
}
</script>
</html>