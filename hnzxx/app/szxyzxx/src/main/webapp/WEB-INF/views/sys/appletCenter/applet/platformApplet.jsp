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
<title>平台应用管理</title>
<style>
	.table  tbody tr:nth-last-child(3){
		   border-bottom: solid 1px #e4e8eb;
	}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > <span>平台应用管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>平台应用管理</p></div>
			<div class="f_right"><button class="btn btn-blue" onclick="createApplet();"><i class="jia "></i>应用注册</button></div>
		</div>
		<div class="input_select" style="    border-bottom: solid 1px #e4e8eb;">
			<div class="select_div">
				<%-- <span>主应用：</span>
				<select class="span2" id="appSelect" onchange="searchApplet()">
					<c:forEach items="${appList }" var ="app">
						<option value="${app.appKey}">${app.name }</option>
					</c:forEach>
				</select> --%>
			</div>
			<div class="select_div">
				<span>状态：</span>
				<select class="span2" id="lineType" name="lineType" onchange="search();"><option data-line_type="10">请选择</option><option data-line_type="12">上架</option><option data-line_type="0">下架</option></select>
			</div>
			<div class="select_div">
				<span>来源：</span>
				<select class="span2" id="vendor" name="vendor" onchange="search();"><option data-vendorid="-1">请选择</option><option data-vendorid="0">官方</option>
					<c:forEach items="${appletVendorList }" var="appletVendor">
						<option data-vendorid="${appletVendor.id }">${appletVendor.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="select_div">
				<span>应用名称：</span>
				<input type="text" id="name" name="name"  value="" />
			</div>
			<div class="btn_link" style="margin:8px 5px 0 0">
				<button class="btn btn-blue"  onclick="search();">搜索</button>
				
				<button class="btn btn-orange" onclick="toDes()" style="float: right;">桌面配置</button>
			</div>
		</div>
		<div style=" font-size: 18px;color: #666; padding-left: 20px;line-height:50px;margin-bottom: 10px;">小应用列表：</div>
		<table class="table">
			<thead>
				<tr><th>图标</th><th>名称</th><th>KEY</th><th>状态</th><th>注册时间</th><th>来源</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody id="applet_list_content">
				<jsp:include page="./indexList.jsp" />
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="applet_list_content" />
				<jsp:param name="url" value="/sys/applet/index?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="scts" style="display:none;text-align: center;padding-top:20px;">
	<p>您确定删除“<span style="color:#2299ee">排课管理</span>”小应用吗？</p>
	<p>一旦确定删除全平台以及学校的应用列表中无该小应用</p>
	<p>并且无法正常使用</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>

<script>
$(".btn-red").click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '230px'],
		  title: '提示', //不显示标题
		  content: $('.scts'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定删除','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
})

//搜索enter
$('body').on('keydown','#name',function(event){
    if(event.keyCode==13){
    	search();
    }
})

function search() {
		var val = {};
		var lineType = $("#lineType").find("option:selected").attr("data-line_type");
		if(lineType != null && lineType != "" && lineType != 10){
			val.lineType = lineType;
		}
		
		var vendorId = $("#vendor").find("option:selected").attr("data-vendorid");
		if(vendorId != null && vendorId != "" && vendorId != -1){
			if(vendorId != 0){
				val.vendorId = vendorId;
			}else {
				val.sourceType = 1;
			}
		}
		
		var name = $("#name").val();
		if(name != null && name != ""){
			val.name = name;
		}
		
		var id = "applet_list_content";
		var url = "/sys/applet/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

function searchApplet(){
	var val = {};
	var appKey = $("#appSelect").val();
	if(appKey != null && appKey != ""){
		val.appKey = appKey;
	}
	var id = "applet_list_content";
	var url = "/sys/applet/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

function createApplet(){
	window.location.href = "${ctp}/sys/applet/creator";
}

function toDes(){
	window.location.href = "${ctp}/sys/appletDesktop/index";
}

</script>
</body>
</html>