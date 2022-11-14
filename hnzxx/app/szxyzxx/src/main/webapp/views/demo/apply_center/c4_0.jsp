<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>栏目管理</title>
<style>
.table thead tr th:first-child{
	padding-left: 33px;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  桌面配置  > 智慧校园应用桌面标准配置  > <span>栏目管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>栏目管理</p></div>
			<div class="f_right"><button class="btn btn-forbidGray"><i class="fa fa-icon-arrow-left"></i>返回</button></div>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>栏目列表：</span>
			</div>
			<div class="btn_link" style="margin:5px 5px 0 0;float:right">
				<button class="btn btn-blue tjlm">添加栏目</button>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr><th>序号</th><th>栏目名称</th><th>状态</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td>1</td><td>教务1</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td>2</td><td>考务2</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td>3</td><td>教务3</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td>4</td><td>教务4</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="bjlm" style="display:none;">
	<div class="control_div"><span>名称</span><div class="zt_div"><input type="text" name=""></div></div>
	<div class="control_div"><span>状态</span><div class="zt_div"><p class="p1"><i class="cg on"></i>启用</p><p class="p2"><i class="cg"></i>禁用</p></div></div>
</div>

<script>
$(".tjlm").click(function(){
	$(".bjlm .control_div input").eq(0).val('')
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '220px'],
		  title: '添加/编辑栏目', //不显示标题
		  content: $('.bjlm'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
})
$("table .btn-blue").click(function(){
	var mc=$(this).parent().parent().children('td:nth-child(2)').text();
	$(".bjlm .control_div input").eq(0).val(mc)
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '220px'],
		  title: '添加/编辑栏目', //不显示标题
		  content: $('.bjlm'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
})


$(function(){
 //上移
 var i=0;
 var $up = $(".go_up")
 $up.click(function() {
  var $tr = $(this).parents("tr");
  if ($tr.index() != 0) {
   i=$tr.children('td:first-child').text();
   $tr.prev().children('td:first-child').text(i);
   $tr.children('td:first-child').text(i-1);
   $tr.fadeOut().fadeIn();
   $tr.prev().before($tr);
  }
 });
 //下移
 var $down = $(".go_down");
 var len = $down.length;
 $down.click(function() {
  var $tr = $(this).parents("tr");
  if ($tr.index() != len - 1) {
	  i= parseInt($tr.children('td:first-child').text());
	   $tr.next().children('td:first-child').text(i);
	   $tr.children('td:first-child').text(i+1);
   $tr.fadeOut().fadeIn();
   $tr.next().after($tr);
  }
 });
 //置顶
 var $top = $(".top");
 $top.click(function(){
  var $tr = $(this).parents("tr");
  $tr.fadeOut().fadeIn();
  $(".table").prepend($tr);
  $tr.css("color","#f60");
 });
//  弹出框的启用
$("i.cg").click(function(){
	$("i.cg").removeClass("on");
	$(this).addClass("on");
})
});

</script>
</body>
</html>