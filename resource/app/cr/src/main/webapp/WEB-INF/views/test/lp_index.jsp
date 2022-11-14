<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>A1-0检索</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<div class="content_main">
	<div class="ku_div">
		<ul>
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
			<div class="right">总共有：<span>10</span>份导学案</div>
		</div>
		<div class="dxa_list" style="margin-bottom:20px;" id="lpList">
			<jsp:include page="./lp_list.jsp"></jsp:include>
		</div> 
	</div>
</div>
</body>
<script type="text/javascript">
var param = null;
var topTab = "school";

$(function() {
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
		$(this).removeClass("open_left").addClass("close_left");
		$(".ml_tishi,.zzc").show();
	});
})

//调用学段科目组件
function invokeStageWidget(type, afterHandler) {
	$.stageWidget(type, true, function(data) {
 		//记录科目和学段
 		data["type"]=topTab;
		param = data;
		listBySubject(data); 
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
		data["type"]=topTab;
		param=info;
		getLpList(info);
	})
}

//根据相关目录去获取导学案列表
function getLpList(data) {
	myPagination("lpList", data, "/learningPlan/list", function() {
		$(".nr_right .px_top .right").children("span").text($("#total").val());
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
	});
}

//根据相应科目去获取导学案列表
function listBySubject(data) {
	myPagination("lpList", data, "/learningPlan/listBySubject", function() {
		//刷新导学案总数
		$(".nr_right .px_top .right").children("span").text($("#total").val());
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
	});
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
	invokeStageWidget(tab, function(data){
		data["subjectCode"] = 0;
		data["type"] = topTab;
		//回调刷新列表
		listBySubject(data);
	});
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
	//来源：是根据科目还是根据目录获取的列表
	var source = $("#source").val();
	if("list"==source) {
		getLpList(param);
	} else {
		listBySubject(param);
	}
}

//导学案预览
function preview(id) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&id="+id;
	window.open(url, "预览-${title}");
}

//收藏导学案
function fav(id, obj) {
	var fav = $(obj).attr("fav");
    $.ajax({
        url: "${pageContext.request.contextPath}/learningPlan/fav",
        type: "POST",
        data: {"id":id, "fav":fav},
        async: false,
        success: function() {
        	listBySubject(param);
        }
    });
}

//编辑导学案
function edit(id) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?id="+id;
	window.open(url, "编辑-${title}");
}

//布置导学案
function publish(id) {
	location.href="${pageContext.request.contextPath}/learningPlan/task/prepare?lpId="+id;
}

//删除导学案
function deleteLearningPlan(id, type) {
	var title = $("#title").val();
	var subject = $("#subject").val();
	
	$.confirm("确认删除该导学案吗?", function() {
		var loader = new loadDialog();
        loader.show();
        $.ajax({
            url: "${pageContext.request.contextPath}/learningPlan/delete?id="+id,
            type: "DELETE",
            data: {},
            async: true,
            success: function() {
            	$.success("删除成功！");
            	loader.close();
            	listBySubject(param);
            }
        });
    });
}
</script>
</html>