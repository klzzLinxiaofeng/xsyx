<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/test/zujuan.css" rel="stylesheet">
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
	opacity: 1;
}
</style>
<title>我的试卷</title>
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
			<div class="select_tab"><input type="radio" name='objectType' value="question"/>试题</div>
			<div class="select_tab"><input type="radio" name='objectType' value="paper" checked="checked"/>试卷</div>
		</div>
	</div>
	
	<jsp:include page="/views/embedded/plugin/stageWidget.jsp"></jsp:include>
	<div class="nr_right">
	
		<div class="dxa_list" style="margin-bottom:20px;" id="myPaper_list_content">
			<jsp:include page="./myPaperList.jsp" />
		</div>
		
		<div style="margin:20px;padding:0" class="page" id="Page">
		<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
			<jsp:param name="id" value="myPaper_list_content" />
			<jsp:param name="url" value="/paper/listMyPaper"/>
			<jsp:param name="pageSize" value="${page.pageSize}" />
		</jsp:include>	
		</div>
		
		<div class="clear"></div>
	</div>
	
	<div class="create_div" style="display:none;">
		<div style="margin-left: 53px;margin-top: 33px;">
			<i></i>
			<p>该份试卷已被布置过，无法进行重复编辑操作 如果要继续，只能进行再次创建，是否继续？</p>
        </div>
	</div>
	<div class="delete_div" style="display:none;">
		<p style="width: 236px;margin: 40px auto 0;">该份试卷已被布置过，无法进行删除操作</p>
	</div>
	<div class="clear_item_box" style="display:none;">
		<p style="margin: 40px auto 0;">当前试题篮内已有试题，无法直接编辑这份试卷</p>
		<p style="margin: 0 auto;">你可以<span style="color:red;">清空试题篮</span>直接进行编辑操作：</p>
	</div>
</body>

<script type="text/javascript">
var param = null;
var topTab = $("#libType").val();

var id = "myPaper_list_content";
var url = "/paper/listMyPaper";

var objectType="paper";


$(function() {
		$("input:radio[name='objectType']").click(function () {
		if($(this).val()=='question'){
			window.location.href="${pageContext.request.contextPath}/paper/myIndex?type=question&libType="+topTab;
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
 		//记录科目和学段
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
	window.location.href="${pageContext.request.contextPath}/paperTask/choose/team?dm=${param.dm1}&isPC=true&paperId="+id;
}
function look(id){
	window.location.href="${pageContext.request.contextPath}/paperTask/paper/viewer?isBasket=0&paperId="+id;
}

//type 0编辑试卷  1新建试卷
function editPaper(paperid, hasTask, type) {
	if(hasTask) {
		hasTaskWindow(paperid);
		return;
	}
	var url = "";
	if(type==1) {
		url = "${pageContext.request.contextPath}/paper/assembly/my/edit?paperId="+paperid;
	} else if(type==2){
		url = "${pageContext.request.contextPath}/paper/assembly/my/create?paperId="+paperid;
	}
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/my/record/count",
        type: "POST",
        data: {},
        async: false,
        success: function(data) {
        	if(data==1) {
        		hasCacheWindow(paperid, type);
        	} else {
        		createOrEdit(url, paperid);
        	}
        }
    });
}

function createOrEdit(url, paperId) {
	$.ajax({
        url: url,
        type: "POST",
        data: {"paperid":paperId},
        async: false,
        success: function(data) {
        	if(data=="success") {
        		window.parent.state=1;
        		window.location.href="${pageContext.request.contextPath}/paper/assembly/index";
        	} else if(data=="hasTask"){
        		hasTaskWindow(paperId);
        	} else if(data=="noExit") {
        		$.alert("该试卷不存在, 无法进行该操作");
        	} else {
        		$.alert("一个意外的错误!");
        	}
        }
    });
}

function hasCacheWindow(paperid, type) {
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['391px', '220px'],
		  title: '提示', //不显示标题
		  content: $('.clear_item_box'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['清空试题篮','取消'],//按钮
		  btn1: function(index, layero){
			  clearPaper(paperid, type);
		  },
		  btn2: function(index, layero){
			 layer.close();
		  }
	});
}

function hasTaskWindow(paperid) {
	layer.closeAll();
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['410px', '237px'],
		  title: '提示', //不显示标题
		  content: $('.create_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  editPaper(paperid, false, 2);
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
}

//清空试题篮 type0:清空不跳转 1:清空并编辑 2:清空并新建
function clearPaper(paperid, type) {
	$.ajax({
        url: "${pageContext.request.contextPath}/paper/assembly/remove",
        type: "POST",
        data: {},
        async: false,
        success: function(data) {
        	if(data=="success") {
        		if(type==1) {
        			url = "${pageContext.request.contextPath}/paper/assembly/my/edit?paperId="+paperid;
        			createOrEdit(url, paperid);
        		} else if(type==2) {
        			url = "${pageContext.request.contextPath}/paper/assembly/my/create?paperId="+paperid;
        			createOrEdit(url, paperid);
        		}
        	} else {
        		console.log("清空试卷失败");
        	}
        }
    });
}
</script>
</html>