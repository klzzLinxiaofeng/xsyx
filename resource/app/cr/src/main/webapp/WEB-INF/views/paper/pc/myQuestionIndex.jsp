<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>

<style>
.ku_select .xdkm_div{
	border-radius:0 0 5px 5px;
}
.typeSelect{
height:40px;background-color:#fff;
}
.select_tab{
	float:left;
	margin-right:15px;
	line-height:40px;
}
.select_tab input{
	position:relative;
	top:-1px;
	margin:0 10px 0;
}
</style>

<title>我的试题</title>
</head>
<body>
<input type="hidden" id="libType" name="libType" value="${libType}" />
<div class="content_main">

	<div class="ku_div">
	<div class="zjjl"><a href="${pageContext.request.contextPath}/paper/assembly/my/index">组卷记录</a></div>
		<ul>
			<li id="tab-personal" class="on"><a href="javascript:void(0)"  onclick="changeTab('personal', this)" class="a3">我创建的</a></li>
			<li id="tab-fav"><a href="javascript:void(0)"  onclick="changeTab('fav', this)" class="a4">收藏夹</a></li>
		</ul>
	</div>
	
	<div class="typeSelect" style="">
		<div style="width:95%;border-bottom:1px dashed #999;height:38px;margin:0 auto;">
			<div class="select_tab"><input type="radio" name='objectType' value="question" checked="checked"/>试题</div>
			<div class="select_tab"><input type="radio" name='objectType' value="paper"  />试卷</div>
		</div>
	</div>
	
	<jsp:include page="/views/embedded/plugin/stageWidget.jsp"></jsp:include>
	<div class="nr_right">
		
		<div class="dxa_list" style="margin-bottom:20px;" id="myQuestion_list_content">
			<jsp:include page="./myQuestionList.jsp" />
		</div>
		
		<div style="margin:20px;padding:0" class="page" id="Page">
		<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
			<jsp:param name="id" value="myQuestion_list_content" />
			<jsp:param name="url" value="/paper/listMyQuestion"/>
			<jsp:param name="pageSize" value="${page.pageSize}" />
		</jsp:include>
		</div>
		
		<div class="clear"></div>
		
	</div>
</body>

<script type="text/javascript">
var param = null;
var topTab = $("#libType").val();

var id = "myQuestion_list_content";
var url = "/paper/listMyQuestion";

var objectType="question";


$(function() {
	
	$("input:radio[name='objectType']").click(function () {
		if($(this).val()=='paper'){
			window.location.href="${pageContext.request.contextPath}/paper/myIndex?type=paper&libType="+topTab;
		}
		});
	
	if("fav"==topTab){
		$("#tab-personal").removeClass("on");
		$("#tab-fav").addClass("on");
	}
	
	invokeStageWidget(0);
	
	
})

//调用学段科目组件
function invokeStageWidget(type, afterHandler) {
	$.stageWidget(type, false, function(data) {//0是校本1是公共， true开启知识点
		param= $.extend(param,data);
		getPaperList(param); 
	})
	//是否有回调
	if(afterHandler!=null){
		afterHandler(param);
	}
}


//请求
function getPaperList(data,isStayCurrentPage) {
	data["libType"]=topTab;
 	//alert(JSON.stringify(data));
	myPagination(id, data, url, function() {
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

//切换我的，收藏夹
function changeTab(type, obj) {
	
	$(obj).parents("ul").children("li").removeClass("on");
	$(obj).parent("li").addClass("on");
	topTab=type;
	invokeStageWidget(0);
}


//收藏
function fav(id, obj) {
	var fav = $(obj).attr("fav");
    $.ajax({
        url: "${pageContext.request.contextPath}/paper/doFav",
        type: "POST",
        data: {"id":id, "fav":fav,"type":1},
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
        		var sc=$(obj).parent().parent().children(".sc").children("span");
				sc.text((parseInt(sc.text())+1));
        		$(obj).text("取消收藏");
        		$(obj).attr("fav",false);
        		$(obj).attr("class","btn btn-lightGray")
        	} else {
        		$.success("取消收藏成功！");
        		var sc=$(obj).parent().parent().children(".sc").children("span");
				sc.text((parseInt(sc.text())-1));
        		$(obj).text("收藏");
        		$(obj).attr("fav",true);
        		$(obj).attr("class","btn btn-blue")
        	}
        }
    });
}


//试题详情
function showQuestionDetail(id,uuid){
	window.location.href="${pageContext.request.contextPath}/paper/question/detail?dm=${param.dm}&id="+id+"&uuid="+uuid;
// 	window.open("${pageContext.request.contextPath}/paper/question/detail?dm=${param.dm}&id="+id+"&uuid="+uuid, '_blank');
}
</script>
</html>