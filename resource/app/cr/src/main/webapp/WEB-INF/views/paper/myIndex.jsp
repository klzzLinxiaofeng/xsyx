<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷</title>
<%@ include file="/views/embedded/common.jsp"%>
</head>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<style>
ul, ol {
    padding: 0;
    margin: 0 0 0 0;
}
.search_div input{
	height: 28px;
    padding: 0px 10px;
}
.search_div label{
	margin-left:-6px;
}
</style>
<body>
<jsp:include page="./paper_head.jsp"></jsp:include>
<div class="ku_select many_ku ">
	<div class="xdkm_div mgb0">
		<div class="xd_km">
			<div class="xueduan">
				<label>学段：</label>
				<div id="stage" class="xd"></div>
			</div>
			<div class="xueduan">
				<label>科目：</label>
				<div id="subject" class="xd"></div>
			</div>
		</div>
	</div>
	<div  class="search_div">
		
		<label>搜索:</label>
		<div class=ss>
			<input type="text"    id="title" > 
			<a href="javascript:void(0)" class="btn-blue" onclick="sss()">搜索</a>
		</div>
		
	</div>
</div>
	<div class="neirong_zs" style="margin-bottom:36px;margin: 0px 30px;">
		<div class="nr_right" style="margin-left:0;">
			<div class="dxa_list" style="margin-bottom:20px;">
					  <div id="emExamPublish_list_content">
									<jsp:include page="./myList.jsp" />
						</div>
			</div>
			                   				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
													<jsp:param name="id" value="emExamPublish_list_content" />
													<jsp:param name="url" value="/paperTask/team/task?index=list" />
													<jsp:param name="pageSize" value="${page.pageSize}" />
												</jsp:include>
		</div>
		<div class="clear"></div>
	</div>
</body>
<script type="text/javascript">
$(function() {
	var stageWidgetHandler = null;
	$(".lesson-link").click(function(){
		$(".lesson-link").removeClass("on font-blue");
		$(".un-link").removeClass("font-blue")
		$(this).addClass("on");
		$(this).parents(".second_ul").prev(".title").children(".lesson-link").addClass("font-blue");
		$(this).parents(".second_ul").prev(".title").children(".un-link").addClass("font-blue");
	})
// 	$("body").on("click",".xdkm_div .xd_km .xueduan .xd a",function(){
// 		$(this).siblings().removeClass("btn-blue");
// 		$(this).addClass("btn-blue");
// 	})
	$.stageWidget = function(type, knowledge, afterHandler) {
		stageWidgetHandler = afterHandler;
		$.get("${ctp}/base/widget/getStage", {}, function(data, status) {
			if ("success" === status) {
				var xd = "<a href='#' class='btn-blue' onclick='$.getSubject("+type+","+knowledge+",0)'>全部</a>";
				var param = JSON.parse(data);
				var index = 0;
				for(var i=0; i<param.length; i++) {
					if(param[i].selected) {
						index = i+1;
					}
					xd+="<a href='#' onclick='$.getSubject("+type+","+knowledge+","+param[i].code+")'>"+param[i].name+"</a>";
				}
				$("#stage").html(xd);
				$("#stage").children("a").eq(index).click();
			}
		});
	}
var tab = 0;
	
	$.getSubject = function(type, knowledge, id) {
		var param = {"stageCode":id,"subjectCode":null};
		var url = "";
		if(type==1) {
			//公共科目
			url = "${ctp}/base/widget/getSubject";
		}else {
			//学校科目
			url = "${ctp}/base/widget/getResSubject";
		}
		
		var km = "<a href='#'  class='btn-blue'>全部</a>";
		$.get(url, param, function(data, status) {
			if ("success" === status) {
				var subject = JSON.parse(data);
				var index = 0;
				for(var i=0; i<subject.length; i++) {
					if(subject[i].selected) {
						index = i+1;
					}
					km+="<a href='#'  data-id='"+subject[i].code+"'>"+subject[i].name+"</a>";
				}
				$("#subject").html(km);
			}
		});
	}
	})
	
$(function(){
	$.stageWidget(1, true, function(data) {
		
	})
	$("body").on("click",".xdkm_div .xd_km .xueduan .xd a",function(){
		$(this).siblings().removeClass("btn-blue");
		$(this).addClass("btn-blue");
		var val ={};
		val.subjectCode=$(this).attr("data-id");
		var id = "emExamPublish_list_content";
		var url = "/paperTask/my/task?index=list";
		myPagination(id, val, url);
	})
})
function sss() {
		var val = {};
		var name = $("#title").val();
		if (name != null && name != "") {
			val.title = name;
		}
		var id = "emExamPublish_list_content";
		var url = "/paperTask/my/task?index=list";
		myPagination(id, val, url);
	}
	   function del(taskId){
		   $.initWinOnTopFromLeft_qyjx('删除', '${pageContext.request.contextPath}/paperTask/isDelete?taskId='+ taskId, '510', '330');
	   }
	   
function loadEditPage(taskId) {
	$.initWinOnTopFromLeft_qyjx('编辑', '${pageContext.request.contextPath}/paperTask/edit/task?taskId='+ taskId, '600', '472');
}
function look(id){
// 	   window.location.href="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
	   var URL="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
// 	   window.location.href="${pageContext.request.contextPath}/paperTask/paper/viewer?paperId="+id;
	   window.open(URL);
}
</script>
</html>