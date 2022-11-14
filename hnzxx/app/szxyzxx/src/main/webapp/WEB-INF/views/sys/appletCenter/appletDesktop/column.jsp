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
			<div class="f_right"><button class="btn btn-forbidGray" onclick="back()"><i class="fa fa-icon-arrow-left"></i>返回</button></div>
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
			<tbody id="column_list">
				<!-- <tr><td>1</td><td>教务</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td>2</td><td>考务</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td>3</td><td>教务</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td>4</td><td>教务</td><td>启用</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red">删除</button></td></tr> -->
			</tbody>
		</table>
		<%-- <div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div> --%>
	</div>
</div>
<div class="bjlm1" style="display:none;">
	<!-- <div class="control_div"><span>次序</span><div class="zt_div"><input type="text" name=""></div></div> -->
	<div class="control_div"><span>名称</span><div class="zt_div"><input type="text" name=""></div></div>
	<div class="control_div"><span>状态</span><div class="zt_div"><p class="p1"><i class="cg on"></i>启用</p><p class="p2"><i class="cg"></i>禁用</p></div></div>
</div>
<div class="bjlm" style="display:none;">
	<!-- <div class="control_div"><span>次序</span><div class="zt_div"><input type="text" name=""></div></div> -->
	<div class="control_div"><span>次序</span><div class="zt_div">
		<select class="span2" name="order" id="order">
			
		</select>
	</div></div>
	<div class="control_div"><span>名称</span><div class="zt_div"><input type="text" name=""></div></div>
	<div class="control_div"><span>状态</span><div class="zt_div"><p class="p1"><i class="cg on"></i>启用</p><p class="p2"><i class="cg"></i>禁用</p></div></div>
</div>

<script>
$(function(){
	//初始化栏目
	var desktopBody = ${appletDesktop.desktopBody};
	var groups = desktopBody.groups;
	for (var i in groups) {
		var name = groups[i].name;
		console.log(i+':'+name);
		var enabled = groups[i].enabled;
		var list_order = groups[i].list_order;
		var en;
		if(enabled){
			en = "启用";
		}else {
			en = "禁用";
		}
		$("#column_list").append('<tr><td>'+list_order+'</td><td>'+name+'</td><td>'+en+'</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-orange go_up">上移</button><button class="btn btn-orange go_down">下移</button><button class="btn btn-red" onclick="delcolumn(this)">删除</button></td></tr>');
		
		//初始化编辑的次序
		$("#order").append('<option value="'+list_order+'">'+list_order+'</option>');
	}
	
	
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
		  //alert("i:"+i);
		  //alert("j:"+(i-1));
		  //操作json
		  var desktopBody = ${appletDesktop.desktopBody};
		  //改order
		  desktopBody.groups[i-1].list_order = i-1;
		  desktopBody.groups[i-2].list_order = i;
		  //按order顺序排list
		  var num=0;
		  for(var js2 in desktopBody.groups){
		    num++;
		  }
		  var groupsArr = new Array();
		  for (var k = 1; k < num+1; k++) {
			  for(var j in desktopBody.groups){
				 if(desktopBody.groups[j].list_order == k){
					 groupsArr.push(desktopBody.groups[j]);
					 break;
				 }
			  }
		  }
		  desktopBody.groups = groupsArr;
		  
		 //保存编辑
		 var val = {};
		 var id='${appletDesktop.id}';
		 val.id = id;
		 val.showAll = ${appletDesktop.showAll};
		 val.showMore = ${appletDesktop.showMore};
		 val.isDefault = ${appletDesktop.isDefault};
		 var bStr = JSON.stringify(desktopBody);
		 val.desktopBody = bStr;
		 var url = "${ctp}/sys/appletDesktop/up";
			$.post(url, val, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						window.location.reload();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
		 });
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
		  //alert("i:"+i);
		  //alert("j:"+(i+1));
		  //操作json
		  var desktopBody = ${appletDesktop.desktopBody};
		  //改order
		  desktopBody.groups[i-1].list_order = i+1;
		  desktopBody.groups[i].list_order = i;
		  //按order顺序排list
		  var num=0;
		  for(var js2 in desktopBody.groups){
		    num++;
		  }
		  var groupsArr = new Array();
		  for (var k = 1; k < num+1; k++) {
			  for(var j in desktopBody.groups){
				 if(desktopBody.groups[j].list_order == k){
					 groupsArr.push(desktopBody.groups[j]);
					 break;
				 }
			  }
		  }
		  desktopBody.groups = groupsArr;
		  
		 //保存编辑
		 var val = {};
		 var id='${appletDesktop.id}';
		 val.id = id;
		 val.showAll = ${appletDesktop.showAll};
		 val.showMore = ${appletDesktop.showMore};
		 val.isDefault = ${appletDesktop.isDefault};
		 var bStr = JSON.stringify(desktopBody);
		 val.desktopBody = bStr;
		 var url = "${ctp}/sys/appletDesktop/down";
			$.post(url, val, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						window.location.reload();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
		 });
		  
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


function back(){
	var desId = '${appletDesktop.id}';
	if(desId != null){
		window.location.href = "${ctp}/sys/appletDesktop/changeDestop/"+desId;
	}else {
		window.location.href = "${ctp}/sys/appletDesktop/index";
	}
}


//添加栏目
$('body').on('click',".tjlm",function(){
	$(".bjlm1 .control_div input").eq(0).val('')
	//$(".bjlm .control_div input").eq(1).val('')
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '300px'],
		  title: '添加/编辑栏目', //不显示标题
		  content: $('.bjlm1'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存','取消'],//按钮
		  btn1: function(index, layero){
			  var name = $(".bjlm1 .control_div input").eq(0).val();
			  var enabled ;
			  var str = $(".bjlm1 .control_div .zt_div p .on").parent().text();
			  if(str === "启用"){
				 enabled = true;
			  }else {
				 enabled = false;
			  }
			  //操作json
			  var desktopBody = ${appletDesktop.desktopBody};
			  var i=0;
			  for(var js2 in desktopBody.groups){
			    i++;
			  }
			  var group = {};
			  group.name = name;
			  group.enabled = enabled;
			  group.list_order = i+1;
			  var appletsArr = new Array();
			  group.applets = appletsArr;
			  desktopBody.groups.push(group);
			  //保存添加栏目
			  var val = {};
			  var id='${appletDesktop.id}';
			  val.id = id;
			  val.showAll = ${appletDesktop.showAll};
			  val.showMore = ${appletDesktop.showMore};
			  val.isDefault = ${appletDesktop.isDefault};
			  var bStr = JSON.stringify(desktopBody);
			  val.desktopBody = bStr;
		 	  var url = "${ctp}/sys/appletDesktop/addColumn";
			 	$.post(url, val, function(data, status) {
					if("success" === status) {
						$.success('操作成功');
						data = eval("(" + data + ")");
						if("success" === data.info) {
							window.location.reload();
						} else {
							$.error("操作失败");
						}
					}else{
						$.error("操作失败");
					}
					loader.close();
			  });
			  
		  }
	});
})
//编辑栏目
$('body').on('click',"table .btn-blue",function(){
	var xh=$(this).parent().parent().children('td:first-child').text();
	var mc=$(this).parent().parent().children('td:nth-child(2)').text();
	//$("#order option").remove("selected");
	//$("#order option[value='"+xh+"']").attr("selected","true");
	$("#order").val(xh);
	$(".bjlm .control_div input").eq(0).val(mc);
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '300px'],
		  title: '添加/编辑栏目', //不显示标题
		  content: $('.bjlm'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存','取消'],//按钮
		  btn1: function(index, layero){
			 //改的名字
			 var changeName = $(".bjlm .control_div input").eq(0).val();
			 //启用禁用
			 var enabled ;
			 var str = $(".bjlm .control_div .zt_div p .on").parent().text();
			 if(str === "启用"){
				 enabled = true;
			 }else {
				 enabled = false;
			}
			 //操作json
			 var desktopBody = ${appletDesktop.desktopBody};
			 desktopBody.groups[xh-1].name=changeName;
			 desktopBody.groups[xh-1].enabled=enabled;
			 //操作json 判断xh是否等于选择的序号
			 var order = $("#order").val();
			 if(order != xh){
				 var a;//小的数
				 var b;//大的数
				 if((order - xh) < 0 ){
					 a = order;
					 b = xh;
				 }else {
					 b = order;
					 a = xh;
				 }
				 for(var e in desktopBody.groups){
					 if(desktopBody.groups[e].list_order< b && desktopBody.groups[e].list_order >a){ 
						 //groupsArr.push(desktopBody.groups[e]);
						 if(a == order){//序号由大变小
							 desktopBody.groups[e].list_order = desktopBody.groups[e].list_order + 1;
						 }
						 if(b == order){//序号由小变大
							 desktopBody.groups[e].list_order = desktopBody.groups[e].list_order - 1;
						 }
					 }
				 }
				 if(a == order){//序号由大变小
					 desktopBody.groups[xh-1].list_order = order;
					 desktopBody.groups[order-1].list_order = parseInt(order) +1;
				 }
				 if(b == order){//序号由小变大
					 desktopBody.groups[xh-1].list_order = order;
					 desktopBody.groups[order-1].list_order = parseInt(order) -1;
				 }
				//按order顺序排list
				  var num=0;
				  for(var js2 in desktopBody.groups){
				    num++;
				  }
				  var groupsArr = new Array();
				  for (var k = 1; k < num+1; k++) {
					  for(var j in desktopBody.groups){
						 if(desktopBody.groups[j].list_order == k){
							 groupsArr.push(desktopBody.groups[j]);
							 break;
						 }
					  }
				  }
				  desktopBody.groups = groupsArr;
			 }
			 
			 //保存编辑
			 var val = {};
			 var id='${appletDesktop.id}';
			 val.id = id;
			 val.showAll = ${appletDesktop.showAll};
			 val.showMore = ${appletDesktop.showMore};
			 val.isDefault = ${appletDesktop.isDefault};
			 var bStr = JSON.stringify(desktopBody);
			 val.desktopBody = bStr;
			 var url = "${ctp}/sys/appletDesktop/editColumn";
				$.post(url, val, function(data, status) {
					if("success" === status) {
						$.success('操作成功');
						data = eval("(" + data + ")");
						if("success" === data.info) {
							window.location.reload();
						} else {
							$.error("操作失败");
						}
					}else{
						$.error("操作失败");
					}
					loader.close();
			 });
			 
		  }
	});
})

//删除栏目
function delcolumn(obj){
	var list_order = $(obj).parent().parent().children('td:first-child').text();
	//操作json
	 var desktopBody = ${appletDesktop.desktopBody};
	 var groups = desktopBody.groups;
	 for (var i in groups) {
		 if(groups[i].list_order == list_order){
			 //删除
			 desktopBody.groups.splice(i,1);
			 console.log(i);
		 }
	 }
	 //修改groups的排序
	 groups = desktopBody.groups;
	 var j = 0;
	 for (var i in groups) {
		 j++;
		 desktopBody.groups[i].list_order = j;
	 }
	 
	//保存编辑
	 var val = {};
	 var id='${appletDesktop.id}';
	 val.id = id;
	 val.showAll = ${appletDesktop.showAll};
	 val.showMore = ${appletDesktop.showMore};
	 val.isDefault = ${appletDesktop.isDefault};
	 var bStr = JSON.stringify(desktopBody);
	 val.desktopBody = bStr;
	 var url = "${ctp}/sys/appletDesktop/delColumn";
		$.post(url, val, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					window.location.reload();
				} else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
	 });
		
	
}




</script>
</body>
</html>