<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<style>
ul, ol {
    padding: 0;
    margin: 0 0 0 0;
}
</style>

<div class="ku_select">
	<div class="xdkm_div">
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
</div>
<div class="neirong_zs">
	<a href="javascript:void(0)" class="open_left"></a>
	<div class="nr_left">
		<div class="zszj_select">
			<ul></ul>
		</div>
		<div class="banben" id="versionAndVolumn">
		</div>
		<div class="zj_mulu">
			<div class="jspContainer">
				<ul class="un_ul">
				</ul>
			</div>
		</div>
		<div class="no_mulu" style="display:none"></div>
	</div>
<div class="ml_tishi">
	<div class="i_know"></div>
	<a href="javascript:void(0)">我知道了</a>
</div>
</div>
<div class="zzc" ></div>
<script type="text/javascript">
$(function(){
	/* var h= document.documentElement.clientHeight;
	$(".zzc").css("height",h); */
	$(".ml_tishi a").click(function(){
		$(".ml_tishi,.zzc").hide();
	})
	$(".neirong_zs").hide();
	var stageWidgetHandler = null;
	var versionAndVolumnHandler = null;
	$(".neirong_zs .nr_left").on("click",".zszj_select li input[type='radio']",function(){
		$(".neirong_zs .nr_left .zszj_select li").removeClass("on");
		$(this).parent().addClass("on")
	});
	$(".lesson-link").click(function(){
		$(".lesson-link").removeClass("on font-blue");
		$(".un-link").removeClass("font-blue")
		$(this).addClass("on");
		$(this).parents(".second_ul").prev(".title").children(".lesson-link").addClass("font-blue");
		$(this).parents(".second_ul").prev(".title").children(".un-link").addClass("font-blue");
	})
	
	$("body").on("click",".xdkm_div .xd_km .xueduan .xd a",function(){
		$(this).siblings().removeClass("btn-blue");
		$(this).addClass("btn-blue");
	})
	
	$.stageWidget = function(type, knowledge, afterHandler) {
		stageWidgetHandler = afterHandler;
		$.get("${ctp}/base/widget/getStage", {}, function(data, status) {
			if ("success" === status) {
				var xd = "<a href='#' data-code='"+0+"' class='btn-blue' onclick='$.getSubject("+type+","+knowledge+",0)'>全部</a>";
				var param = JSON.parse(data);
				var index = 0;
				for(var i=0; i<param.length; i++) {
					xd+="<a href='#' data-code='"+param[i].code+"' onclick='$.getSubject("+type+","+knowledge+","+param[i].code+")'>"+param[i].name+"</a>";
				}
				$("#stage").html(xd);
				$("#stage").children("a").eq(0).click();
			}
		});
	}
	
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
		
		var km = "<a href='#' data-code='"+0+"' onclick='$.getSelectData("+id+",0,"+type+","+knowledge+")' class='btn-blue'>全部</a>";
		$.get(url, param, function(data, status) {
			if ("success" === status) {
				var subject = JSON.parse(data);
				var index = 0;
				for(var i=0; i<subject.length; i++) {
					km+="<a href='#' data-code='"+subject[i].code+"' onclick='$.getSelectData("+id+","+subject[i].code+","+type+","+knowledge+")'>"+subject[i].name+"</a>";
				}
				$("#subject").html(km);
				$("#subject").children("a").eq(0).click();
			}
		});
	}
	
	$.getSelectData = function(stage, subject, type, knowledge) {
		$(".jspContainer .un_ul").html("");
		var data = {"stageCode":stage, "subjectCode":subject}
		var li = "";
		
		if(knowledge) {
			//需要知识点情况
			li+="<li><input name='selectType' stype='knowledge' type='radio' onclick='$.getKnowledgeTree("+stage+","+subject+")'> 按知识点</li>";
		}
		if(type==1) {
			//公共
			li += "<li class=\"on\"><input name='selectType' stype='catalog' type='radio' onclick='$.getCatalogTree("+type+")'> 按章节目录</li>";
		} else {
			//校本
			li += "<li class=\"on\"><input name='selectType' stype='catalog' type='radio' onclick='$.getCatalogTree("+type+")'> 按章节目录</li>";
		}
		$(".banben").show();
		$(".zszj_select").children("ul").eq(0).html(li);
		if(type!=2) {
			$.saveTrail(data);
		}
		stageWidgetHandler(data);
	}
	
	$.saveTrail = function(data) {
		$.get("${ctp}/base/widget/saveTrail", data);
	}
});

//滚动条
$(document).ready(function() {  
	$(".jspContainer").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
}); 
</script>