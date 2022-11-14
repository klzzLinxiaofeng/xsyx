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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>应用桌面管理</title>
<style>
.layui-layer-btn a{
	height: 40px;
	line-height:40px;
	padding:0 ;
	width:100px;
	font-size: 14px;
	color: #ffffff;
	border-radius: 4px;
}
.layui-layer-btn a.button_blue{
	background-image: linear-gradient(48deg, 
		#1795ef 0%, 
		#2da1f8 100%), 
	linear-gradient(
		#3ac982, 
		#3ac982);
	background-blend-mode: normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(27, 151, 241, 0.45);
}
.layui-layer-btn a.button_yellow{
	background-image: linear-gradient(48deg, 
		#f5a816 0%, 
		#ffbc00 100%), 
	linear-gradient(
		#d4d4d5, 
		#d4d4d5);
	background-blend-mode: normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(245, 168, 22, 0.45);
}
.layui-layer-btn a.button_grey{
	background-image: linear-gradient(48deg, 
		#a8bfca 0%, 
		#c2d4dd 100%), 
	linear-gradient(
		#d4d4d5, 
		#d4d4d5);
	background-blend-mode: normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(139, 170, 185, 0.45);
}
.layui-layer-btn a.button_red{
	background-image: linear-gradient(48deg, 
		#ff6363 0%, 
		#ff7878 100%), 
	linear-gradient(48deg, 
		#f5a816 0%, 
		#ffbc00 100%), 
	linear-gradient(
		#3ac982, 
		#3ac982);
	background-blend-mode: normal, 
		normal, 
		normal;
	box-shadow: 0px 3px 9px 0px 
		rgba(214, 91, 74, 0.45);
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  桌面配置  > <span>智慧校园应用桌面标准配置</span></p>
	<div class="content_main white">
		<div class="content_top">
			<c:choose>
				<c:when test='${user.userName == "superAdmin" && appletDesktop.ownerType == 1 && appletDesktop.isDefault}'>
					<div class="f_left"><p>${appletDesktop.name}<span class="small_gray">(默认配置)</span></p></div>
				</c:when>
				<c:when test='${user.userName == "superAdmin" && appletDesktop.ownerType == 1 && !appletDesktop.isDefault}'>
					<div class="f_left"><p>${appletDesktop.name}<span class="small_gray">(模板配置)</span></p></div>
				</c:when>
				<c:when test='${user.userName == "superAdmin" && appletDesktop.ownerType != 1}'>
					<div class="f_left"><p>${school.name}<span class="small_gray">(桌面配置)</span></p></div>
				</c:when>
				<c:otherwise>
					<div class="f_left"><p>${user.schoolName}<span class="small_gray">(桌面配置)</span></p></div>
				</c:otherwise>
			</c:choose>
			<!-- <div class="f_left"><p>智慧校园应用桌面标准配置<span class="small_gray">(默认配置)</span></p></div> -->
			
			<div class="f_right">
			<c:choose>
				<c:when test='${user.userName == "superAdmin"}'>
				<button class="btn btn-forbidGray" onclick="changeDes()"><i class="fa fa-exchange"></i>切换桌面</button>
				</c:when>
			</c:choose>
			<button class="btn btn-green setUp"><i class="fa fa-cog" ></i>设置</button>
			<c:choose>
				<c:when test='${user.userName == "superAdmin"}'>
				<button class="btn btn-blue saveAs"><i class="fa fa-save" ></i>另存为</button>
				<button class="btn btn-red" onclick="delDes()"><i class="fa fa-trash-o" ></i>删除</button>
				</c:when>
			</c:choose>
			<span style="border-left: solid 1px #e3e8ec;display: inline-block;margin: 10px 16px;padding-left: 20px;">
				<button class="btn btn-blue" style="margin: 0;" onclick="saveDes();"><i class="fa fa-save"></i>保存</button>
			</span>
			</div>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>应用：</span>
				<input type="text" placeholder="请输入搜索关键词" class="apply_name">
				<button class="btn btn-blue" onclick="serach_apply()">搜索</button>
			</div>
			<div style="margin-top: 9px;">
				<c:choose>
					<c:when test='${user.userName == "superAdmin"}'>
					<button class="btn btn-orange fr yyfgd">桌面应用/覆盖到</button>
					</c:when>
				</c:choose>
				<button class="btn btn-forbidGray fr" style="display:none">桌面应用/覆盖到</button>
				<button class="btn btn-peaGreen fr" onclick="columnManage()">栏目管理</button>
				<c:if test='${user.userName != "superAdmin"}'>
					<c:choose>
						<c:when test='${appletDesktop.isDefault == true}'>
						<button class="btn btn-forbidGray fr" disabled="disabled" style="color: #ffffff;cursor: not-allowed;">已使用默认桌面</button>
						</c:when>
						<c:otherwise>
						<button class="btn btn-green fr" onclick="defaultDes()">恢复为默认配置</button>
						</c:otherwise>
					</c:choose>
				</c:if>
				<div class="clear"></div>
			</div>
		</div>
		<!-- 应用拖拽 -->
		<div class="app_drag">
		    <div class="app_dleft">
		        <div class="a_top">全部</div>
		        <div class="app_list all_list">
		        	<p class="ysj">已上架</p>
		            <ul class="ysj_div">
		                <%-- <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA1</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>--%>
			                <c:forEach items="${appletList}" var="applet">
				                <li>
				                    <%-- <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png"> --%>
				                    <img src="${applet.iconUrl}">
				                    <p class="title" data-key="${applet.appletKey }" data-id="${applet.id }">${applet.name }</p>
				                    <div class="yy_detail">
				                        <p class="p1"><span>应用说明：</span>${applet.description}</p>
				                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
				                    </div>
				                </li>
			                </c:forEach>
		                </ul>
		                <div class="clear"></div>
		                <p class="yxj">已下架</p>
		                <!-- 加灰色下架的应用 -->
		                <ul>
			                <c:forEach items="${downAppletList}" var="downApplet">
			                	<li>
				                    <%-- <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png"> --%>
				                    <img src="${downApplet.iconUrl}" class="gray">
				                    <p class="title" data-key="${downApplet.appletKey }" data-id="${downApplet.id }">${downApplet.name }</p>
				                    <div class="yy_detail">
				                        <p class="p1"><span>应用说明：</span>${downApplet.description}</p>
				                        <p class="p2">不可拖拽应用到相应的位置/栏目中</p>
				                    </div>
				                </li>
			                </c:forEach>
		                </ul>
		        </div>
		        <div class="ts_red">*可拖拽应用到相应的位置/栏目中</div>
		    </div>
		    <div class="app_dright">
		        <div class="a_top">
		            <div class="nav_div" id="tabs">
		                <ul>
<!-- 		                    <li><a href="javascript:void(0)" class="on" data-id='0'>管理</a></li> -->
<!-- 		                    <li><a href="javascript:void(0)" data-id='1'>教务</a></li> -->
<!-- 		                    <li><a href="javascript:void(0)" data-id='2'>校务</a></li> -->
		                </ul>
		            </div>
		            <div class="turn_btn">
		                <a href="javascript:void(0)" class="turn_left"></a>
		                <a href="javascript:void(0)" class="turn_right"></a>
		            </div>
		        </div>
		        <div class='a_bottom'>
		            <%-- <div class="app_list" style="display:block"><ul class="connectedSortable ui-helper-reset"><li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA1</p>
		                    <div class="yy_detail">
		                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
		                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
		                    </div>
		                </li>
		                <li>
		                    <img src="${pageContext.request.contextPath}/res/qyjx/css/images/app_icon.png">
		                    <p class="title">办公OA2</p>
			                    <div class="yy_detail">
			                        <p class="p1"><span>应用说明：</span>这是一条应用说明，加一行凑够2行</p>
			                        <p class="p2">可拖拽应用到相应的位置/栏目中</p>
			                    </div>
			                </li></ul></div>
			            <div class="app_list" ><ul class="connectedSortable ui-helper-reset"></ul></div>
			            <div class="app_list" ><ul class="connectedSortable ui-helper-reset"></ul></div> --%>
			        </div>
			        <div class='delete_app'><p><i class="delete_hui"></i>删除</p></div>
			    </div>
			</div>
	</div>
</div>
<div class="scts_setUp" style="display:none;text-align: center;padding:20px;">
	<%-- <p>名称：<input type="text" id="saveasName" value="${appletDesktop.name }"/></p>
	<p id="saveasShowAll"><i class="ck"></i>显示【全部】栏目</p>
	<p id="saveasDefault"><i class="ck"></i>设置为默认桌面</p> --%>
	<c:choose>
		<c:when test='${user.userName == "superAdmin"}'>
		<p>名称：<input type="text" id="saveasName" value="${appletDesktop.name }"/></p>
		<p id="saveasShowAll"><i class="ck"></i>显示【全部】栏目</p>
		<c:if test='${appletDesktop.ownerType == 1}'>
			<p id="saveasDefault"><i class="ck"></i>设置为默认桌面</p></c:if>
		</c:when>
		<c:otherwise>
		<p>名称：<input type="text" id="saveasName" value="${user.schoolName}" readonly  unselectable="on"/></p>
		<p id="saveasShowAll"><i class="ck"></i>显示【全部】栏目</p></c:otherwise>
	</c:choose>
</div>

<div class="scts_setUp_save" style="display:none;text-align: center;padding-top:35px;">
	<p>当前桌面配置为默认桌面，您确定保存当前修改操作吗？</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>

<div class="scts_delete_ts" style="display:none;text-align: center;padding-top:35px;">
	<p>您确定从<span style="color:#2299ee">【管理】</span>栏目中移除已选择的小应用吗？</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>
<script src="${pageContext.request.contextPath}/res/qyjx/js/jquery-ui.min.js"></script>
<script>
//搜索enter
$('body').on('keydown','.apply_name',function(event){
    if(event.keyCode==13){
    	serach_apply();
    }
})

//设置
$(".setUp").click(function(){
	if('${appletDesktop.isDefault }' == 'true'){
		$("#saveasDefault i").attr("class","ck on")
	}else {
		$("#saveasDefault i").attr("class","ck")
	}
	if('${appletDesktop.showAll }' == 'true'){
		$("#saveasShowAll i").attr("class","ck on")
	}else {
		$("#saveasShowAll i").attr("class","ck")
	}
	//判断是否为超管，超管才有另存为模板
	if('${user.userName }' != "superAdmin"){
		layer.open({
			  type: 1,
			  shade: false,
			  area: ['390px', '280px'],
			  title: '设置', //不显示标题
			  content: $('.scts_setUp'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  btn: ['保存','取消'],//按钮
			  btn1: function(index, layero){
				  saveDesktop();
			  }
		});
	}else {
		layer.open({
			  type: 1,
			  shade: false,
			  area: ['390px', '280px'],
			  title: '设置', //不显示标题
			  content: $('.scts_setUp'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  //btn: ['保存','另存为模板','取消'],//按钮
			  btn: ['保存','取消'],//按钮
			  btn1: function(index, layero){
				  saveDesktop();
				  /* layer.open({
					  type: 1,
					  shade: false,
					  area: ['390px', '200px'],
					  title: '提示', //不显示标题
					  content: $('.scts_setUp_save'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
					  cancel: function(){
					    layer.close();
					  },
					  btn: ['确定','取消'],//按钮
					  btn1: function(index, layero){
						  
					  }
				});
				  $(".layui-layer-btn a.layui-layer-btn0").addClass("button_blue");
				  $(".layui-layer-btn a.layui-layer-btn1").addClass("button_grey"); */
			  },
			  /* btn2: function(index, layero){
				  saveasDesktop();
			  } */
		});
	}
	
	$(".layui-layer-btn a.layui-layer-btn0").addClass("button_blue");
	$(".layui-layer-btn a.layui-layer-btn1").addClass("button_yellow");
	$(".layui-layer-btn a.layui-layer-btn2").addClass("button_grey");
})

//另存为的窗口(新加的)
$(".saveAs").click(function(){
	/* if('${appletDesktop.isDefault }' == 'true'){
		$("#saveasDefault i").attr("class","ck on")
	}else { */
		$("#saveasDefault i").attr("class","ck")
	//}
	if('${appletDesktop.showAll }' == 'true'){
		$("#saveasShowAll i").attr("class","ck on")
	}else {
		$("#saveasShowAll i").attr("class","ck")
	}
	//判断是否为超管，超管才有另存为模板
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '280px'],
		  title: '另存为', //不显示标题
		  content: $('.scts_setUp'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['另存为模板','取消'],//按钮
		  btn1: function(index, layero){
			  saveasDesktop();
		  }
	});
	
	$(".layui-layer-btn a.layui-layer-btn0").addClass("button_blue");
	$(".layui-layer-btn a.layui-layer-btn1").addClass("button_yellow");
	$(".layui-layer-btn a.layui-layer-btn2").addClass("button_grey");
})

//执行另存为桌面
function saveasDesktop(){
	var val = {};
	var body = {};
	//另存为的名称
	var saveasName = $("#saveasName").val();
	val.name = saveasName;
	val.showMore = true;
	//var saveasShowAll = $("#saveasShowAll").text();
	//var saveasDefault = $("#saveasDefault").text();
	//显示全部栏目
	if ($("#saveasShowAll i").attr("class") === "ck on") {
		val.showAll = true;
	}else {
		val.showAll = false;
	}
	//设置默认桌面
	if ($("#saveasDefault i").attr("class") === "ck on") {
		val.isDefault = true;
	}else {
		val.isDefault = false;
	}
	
	//桌面排序json
	var groupsArr = new Array();
	var nav_length=$('.a_top ul li').length;
	for(var i=0;i<nav_length;i++){
		var name=$('.a_top ul li').eq(i).text();
		var group = {};
		group.name = name;
		group.enabled = true;
		group.list_order = i+1;
		var appletsArr = new Array();
		console.log(name)
		$('.app_drag .app_dright .app_list').eq(i).children('ul').children('li').each(function(index){
			var applets = {};
			var app_name=$(this).children('.title').text();
			applets.name = app_name;
			applets.list_order = index+1;
			var app_key=$(this).children('.title').data("key");
			var app_id=$(this).children('.title').data("id");
			var app_icon = $(this).children('img').attr("src");
			applets.key = app_key;
			applets.icon = app_icon;
			applets.id = app_id;
			console.log(index+1+':'+app_name+","+app_key+","+app_icon)
			appletsArr.push(applets);
		});
		group.applets = appletsArr;
		groupsArr.push(group);
	}
	body.groups = groupsArr;
	var bStr = JSON.stringify(body);
	val.desktopBody = bStr;
	
	
	var url = "${ctp}/sys/appletDesktop/saveas";
	$.post(url, val, function(data, status) {
		if("success" === status) {
			$.success('操作成功');
			data = eval("(" + data + ")");
			if("success" === data.info) {
				if(parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
				$.closeWindow();
			} else {
				$.error("操作失败");
			}
		}else{
			$.error("操作失败");
		}
		loader.close();
	});
	
}
//设置里面的保存
function saveDesktop(){
	var val = {};
	var id='${appletDesktop.id}';
	val.id = id;
	
	var body = {};
	//另存为的名称
	var saveasName = $("#saveasName").val();
	val.name = saveasName;
	val.showMore = true;
	//var saveasShowAll = $("#saveasShowAll").text();
	//var saveasDefault = $("#saveasDefault").text();
	//显示全部栏目
	if ($("#saveasShowAll i").attr("class") === "ck on") {
		val.showAll = true;
	}else {
		val.showAll = false;
	}
	//设置默认桌面
	if ($("#saveasDefault i").attr("class") === "ck on") {
		val.isDefault = true;
	}else {
		val.isDefault = false;
	}
	
	//桌面排序json
	var groupsArr = new Array();
	var nav_length=$('.a_top ul li').length;
	for(var i=0;i<nav_length;i++){
		var name=$('.a_top ul li').eq(i).text();
		var group = {};
		group.name = name;
		group.enabled = true;
		group.list_order = i+1;
		var appletsArr = new Array();
		console.log(name)
		$('.app_drag .app_dright .app_list').eq(i).children('ul').children('li').each(function(index){
			var applets = {};
			var app_name=$(this).children('.title').text();
			applets.name = app_name;
			applets.list_order = index+1;
			var app_key=$(this).children('.title').data("key");
			var app_id=$(this).children('.title').data("id");
			var app_icon = $(this).children('img').attr("src");
			applets.key = app_key;
			applets.id = app_id;
			applets.icon = app_icon;
			console.log(index+1+':'+app_name+","+app_key+","+app_icon)
			appletsArr.push(applets);
		});
		group.applets = appletsArr;
		groupsArr.push(group);
	}
	body.groups = groupsArr;
	var bStr = JSON.stringify(body);
	val.desktopBody = bStr;
	
	
	var url = "${ctp}/sys/appletDesktop/save2";
	$.post(url, val, function(data, status) {
		if("success" === status) {
			$.success('操作成功');
			data = eval("(" + data + ")");
			if("success" === data.info) {
				/* if(parent.core_iframe != null) {
						parent.core_iframe.window.location.reload();
					} else {
						parent.window.location.reload();
					}
				$.closeWindow(); */
				parent.core_iframe.window.location.reload();
			} else {
				$.error("操作失败");
			}
		}else{
			$.error("操作失败");
		}
		loader.close();
	});
	
}

//主页处保存
function saveDes(){
	var val = {};
	var id='${appletDesktop.id}';
	val.id = id;

	var body = {};
	//另存为的名称
	var saveasName = $("#saveasName").val();
	val.name = saveasName;
	//var saveasShowAll = $("#saveasShowAll").text();
	//var saveasDefault = $("#saveasDefault").text();
	//显示全部栏目
	/* if ($("#saveasShowAll i").attr("class") === "ck on") {
		val.showAll = true;
	}else {
		val.showAll = false;
	} */
	//var desktopBody = ${appletDesktop.showAll};showMore
	val.showAll = ${appletDesktop.showAll};
	val.showMore = ${appletDesktop.showMore};
	val.isDefault = true;
	//设置默认桌面
	/* if ($("#saveasDefault i").attr("class") === "ck on") {
		val.isDefault = true;
	}else {
		val.isDefault = false;
	} */
	
	//桌面排序json
	var groupsArr = new Array();
	var nav_length=$('.a_top ul li').length;
	for(var i=0;i<nav_length;i++){
		var name=$('.a_top ul li').eq(i).text();
		var group = {};
		group.name = name;
		group.enabled = true;
		group.list_order = i+1;
		var appletsArr = new Array();
		//console.log(name)
		$('.app_drag .app_dright .app_list').eq(i).children('ul').children('li').each(function(index){
			var applets = {};
			var app_name=$(this).children('.title').text();
			applets.name = app_name;
			applets.list_order = index+1;
			var app_key=$(this).children('.title').data("key");
			var app_id=$(this).children('.title').data("id");
			var app_icon = $(this).children('img').attr("src");
			applets.key = app_key;
			applets.id = app_id;
			applets.icon = app_icon;
			//console.log(index+1+':'+app_name+","+app_key+","+app_icon)
			appletsArr.push(applets);
		});
		group.applets = appletsArr;
		groupsArr.push(group);
	}
	body.groups = groupsArr;
	var bStr = JSON.stringify(body);
	val.desktopBody = bStr;
	
	var url = "${ctp}/sys/appletDesktop/save";
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

//删除桌面
function delDes(){
	var val = {};
	var id='${appletDesktop.id}';
	val.id = id;
	var url = "${ctp}/sys/appletDesktop/delDes";
	$.post(url, val, function(data, status) {
		if("success" === status) {
			$.success('操作成功');
			data = eval("(" + data + ")");
			if("success" === data.info) {
				window.location.href = "${ctp}/sys/appletDesktop/index";
			} else {
				$.error("默认桌面不能删除");
			}
		}else{
			$.error("操作失败");
		}
		loader.close();
	});
}

//进入栏目管理页面
function columnManage(){
	window.location.href = "${ctp}/sys/appletDesktop/column/${appletDesktop.id}";
}

//进入切换桌面
function changeDes(){
	//window.location.href = "${ctp}/sys/appletDesktop/changeDes";
	$.initWinOnTopFromLeft_qyjx('切换桌面','${ctp}/sys/appletDesktop/changeDes', '600', '482');
}


$(".yyfgd").click(function(){
	$.initWinOnTopFromLeft_qyjx('桌面应用/覆盖到','${ctp}/sys/appletDesktop/coverDes/'+'${appletDesktop.id}', '600', '522');
})
$(function(){
	$(".app_drag .all_list").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
	//初始加载桌面
	var appletList = ${appletListJson };
	console.log(appletList);
    var desktopBody = ${appletDesktop.desktopBody};
    console.log(desktopBody);
    /* var bStr = JSON.stringify(desktopBody);
    alert(bStr); */
    var groups = desktopBody.groups;
    for (var i in groups) {
		 var name = groups[i].name;
		 //console.log(i+':'+name);
		 var enabled = groups[i].enabled;
		 //建立分类
		 if(enabled){
		 	$('<li><a href="javascript:void(0)" data-id='+i+'>'+name+'</a></li>').appendTo($('.app_drag .app_dright .a_top ul'))
		 	$('<div class="app_list" ><ul class="connectedSortable ui-helper-reset"></ul></div>').appendTo('.app_dright .a_bottom')
		 }else{
			 $('<li style="display:none"><a href="javascript:void(0)" data-id='+i+'>'+name+'</a></li>').appendTo($('.app_drag .app_dright .a_top ul'))
			$('<div class="app_list" style="display:none"><ul class="connectedSortable ui-helper-reset"></ul></div>').appendTo('.app_dright .a_bottom')
		 }
		 if(i==0){
			 $('.app_drag .app_dright .a_top ul li').eq(0).children().addClass('on')
			 $('.app_drag .app_dright .app_list').eq(0).show()
		 }
	   	 var applets = groups[i].applets;
	   	 for (var j in applets) {
	   		 var app_name = applets[j].name;
	   		 //console.log(i+':'+app_name);
	   		 var app_list_order = applets[j].list_order;
	   		 var app_key = applets[j].key;
	   		 var app_id = applets[j].id;
	   		 var app_icon = applets[j].icon;
	   		 var dis;
	   		 for (var k in appletList) {
	   			if(app_id == appletList[k].id){
	   				//app_name = appletList[k].name;
	   				app_key = appletList[k].appletKey;
	   				//
	   				app_icon = appletList[k].iconUrl;
	   				dis = appletList[k].description;
		   			//建立applet
		   	   		$('<li><img src='+app_icon+'><p class="title" data-id="'+app_id+'" data-key="'+app_key+'">'+app_name+'</p><div class="yy_detail"><p class="p1"><span>应用说明：</span>'+dis+'</p><p class="p2">可拖拽应用到相应的位置/栏目中</p></div></li>').appendTo($('.a_bottom .app_list').eq(i).children('ul'))
	   			}
	   		 }
	   		 
	   		 //建立applet
	   		 //$('<li><img src='+app_icon+'><p class="title" data-id="'+app_id+'" data-key="'+app_key+'">'+app_name+'</p><div class="yy_detail"><p class="p1"><span>应用说明：</span>'+dis+'</p><p class="p2">可拖拽应用到相应的位置/栏目中</p></div></li>').appendTo($('.a_bottom .app_list').eq(i).children('ul'))
	   	 }
	}
	
	$('.scts_setUp p i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       // $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        //$('.ck').addClass('on');
    }
	});
	var w=$('.app_drag .app_dright').width()-44;
    $(".app_drag .app_dright .a_top .nav_div").width(w);
    var li_length=$('.app_drag .app_dright .a_top ul li').length*120;
    $('.app_drag .app_dright .a_top ul').width(li_length);
    if(w<li_length){
        $(".a_top .turn_btn .turn_right").addClass('on');
    }
    $(window).resize(function(){
        w=$('.app_drag .app_dright').width()-44;
        $(".app_drag .app_dright .a_top .nav_div").width(w);
        var ul_left=parseInt($('.a_top .nav_div ul').css('left'));
        if(w-ul_left>li_length){
            console.log(ul_left)
            $(".a_top .turn_btn .turn_right").removeClass('on');
        }else{
            $(".a_top .turn_btn .turn_right").addClass('on');
        }
    });
    $('body').on('click','.a_top .turn_btn .turn_right.on',function(){
        var ul_left=parseInt($('.a_top .nav_div ul').css('left'))-120;
        $('.a_top .nav_div ul').css('left',ul_left);
        if(w-ul_left>li_length){
            console.log('w:'+w+',ul_left:'+ul_left)
            $(this).removeClass('on');
        }
        if(ul_left==-120){
            $('.a_top .turn_btn .turn_left').addClass('on')
        }
    })
    $('body').on('click','.a_top .turn_btn .turn_left.on',function(){
        var ul_left=parseInt($('.a_top .nav_div ul').css('left'))+120;
        if(w-ul_left<li_length){
            $('.a_top .turn_btn .turn_right').addClass('on');
        }
        if(ul_left==0){
            $(this).removeClass('on')
        }
        $('.a_top .nav_div ul').css('left',ul_left);
    })
        $('.app_drag .app_dright .a_top ul li a').click(function(){
            $('.app_drag .app_dright .a_top ul li a').removeClass('on');
            $(this).addClass('on');
            $('.app_dright .app_list').hide();
            var i=$(this).parent().index();
            $('.app_dright .app_list').eq(i).show();
        });
        /*拖拽*/
        $(".app_list ul").sortable().disableSelection();
        /*左边全部拖拽时复制*/
        $(".all_list .ysj_div li").draggable({
            helper: "clone",
            revert: "invalid", // 当未被放置时，条目会还原回它的初始位置
        });
        /*接收左边拖拽内容*/
        $( ".app_dright .app_list ul").droppable({
          accept: ".all_list .ysj_div li",
          drop: function( event, ui ) {
            var $item = $( this );//放置位置
            var $list=ui.draggable;//拖拽对象
            var app_name=$list.children('.title').text();
            var i=0;
            $item.children().each(function(){
                if($(this).children('.title').text()==app_name){
                    i=1;
                }
            })
            if(i==0){
                $list.clone().appendTo($item);
            }else{
                
            }
          }
        });
        /*拖拽到右边顶部nav*/
        $( "#tabs li" ).droppable({
              accept: ".connectedSortable li",
              hoverClass: "ui-state-hover",
              drop: function( event, ui ) {
                var $item = $( this );
                var i=$item.find( "a" ).data('id');
                var $list = $('.app_dright .app_list').eq(i).find( ".connectedSortable" );
                var $list1 =ui.draggable;//拖拽对象
                var app_name=$list1.children('.title').text();
                var j=0;
                 $('.app_dright .app_list').eq(i).find('li').each(function(){
                    if($(this).children('.title').text()==app_name){
                        j=1;
                    }
                 })
                if(j==0){
                    ui.draggable.hide( "slow", function() {
                      $('#tabs li a').eq(i).click();
                      $( this ).appendTo( $list ).show( "slow" );
                    });
                }else{
                    alert('已包含')
                }
              }
            });
        /*删除app*/
        $(".delete_app").droppable({
            accept: ".connectedSortable li",
            hoverClass: "bianse",
            drop: function(event,ui){
            	layer.open({
				  type: 1,
				  shade: false,
				  area: ['390px', '200px'],
				  title: '提示', //不显示标题
				  content: $('.scts_delete_ts'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
				  cancel: function(){
				    layer.close();
				  },
				  btn: ['确定','取消'],//按钮
				  btn1: function(index, layero){
					 ui.draggable.remove();
				  }
			}); 
			  $(".layui-layer-btn a.layui-layer-btn0").addClass("button_red");
			  $(".layui-layer-btn a.layui-layer-btn1").addClass("button_grey");
            }
        });
})

function serach_apply(){
	var apply_name=$('.apply_name').val();
	$(".app_drag .app_list ul li ").removeClass('pink')
	$('.app_drag .app_dright .a_top ul li a').removeClass('hover1')
	if(apply_name==''){
		$(".app_drag .app_list ul li .title").each(function(){
			var school_html=$(this).text();
			school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
	 		$(this).html(school_html)
		});
	}else{
		$(".app_drag .app_list ul li .title").each(function(){
			var school_html=$(this).text();
			school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
	 		$(this).html(school_html)
			if(school_html.indexOf(apply_name)!=-1){
			var reg = new RegExp("("+apply_name +")","ig");
			school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
			$(this).html(school_html);
			$(this).parent().addClass('pink');
			var aa = $(this).parent('li');
			var yy = $(this).parents('ul').prev().attr('class');
			console.log('yy'+yy)
			if(yy=="ysj"){
				$(this).parents('ul').prev('.ysj').next('ul').find('li:first-child').before(aa);
			}else if(yy=="yxj"){
				$(this).parents('ul').prev('.yxj').next('ul').find('li:first-child').before(aa);
			}
			
			}
		});
	}
	$('.app_dright .app_list ul').each(function(index){
		if($(this).children('.pink').length==1){
			$('.app_drag .app_dright .a_top ul li').eq(index).children('a').addClass('hover1')
		}
	})
}

function defaultDes(){
	//console.log(123);
	var val = {};
	var id='${appletDesktop.id}';
	val.id = id;
	var url = "${ctp}/sys/appletDesktop/defaultDes";
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
		//loader.close();
	});
}

</script>
</body>
</html>