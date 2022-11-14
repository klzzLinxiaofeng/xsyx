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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>学校应用管理</title>
<style>
.input_select .select_div {
    float: none; 
}
.table thead tr th:nth-child(2){
	padding-left: 33px;
}

</style>
</head>
<body style="background-color:rgb(248, 248, 249)"  onload="setup();preselect('省份');promptinfo();">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > <span>学校应用管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>学校应用管理</p></div>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>主应用：</span>
				<select class="span2"><option>定邦教育云</option></select>
			</div>
			<div class="select_div" style="float:left">
				<span>学校：</span>
				<select class="select span2" name="province" id="s1"><option></option></select>
				<select class="select span2" name="city" id="s2"><option></option> </select>
				<select class="select span2" name="town" id="s3"><option></option></select>
				<select class="span2"><option>广州市第一中学</option></select>
				 <input id="address" name="address" type="hidden" value="" />
			</div>
			
			<div class="select_div" style="float:left">
				<span>关键字：</span>
				<input type="text">
			</div>
			<div class="btn_link" style="margin:5px 5px 0 0;">
				<button class="btn btn-blue" style="margin-top: 8px;">搜索</button>
			</div>
		</div>
		<div class="input_select school_info">
			<address style="float: left;margin:0">
			  <strong>广州市第一中学</strong><br>
			  <span>广东省</span>-<span>广州市</span>-<span>番禺区</span>
			</address>
			<button class="btn btn-orange" style="margin-left: 20px;">桌面配置</button>
		</div>
		<div class="input_select">
			<button class="btn btn-oxfordGray">批量下架</button>
			<button class="btn btn-red">批量删除</button>
			<button class="btn btn-blue fr addApply">添加应用</button>
		</div>
		
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>图标</th><th>名称</th><th>状态</th><th>注册时间</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td><i class="ck"></i></td><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td class="sj">上架</td><td>2018-11-11</td><td class="caozuo"><button class="btn btn-green">权限设置</button><button class="btn btn-oxfordGray apply_xj">下架</button><button class="btn btn-red del">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td class="sj">上架</td><td>2018-11-11</td><td class="caozuo"><button class="btn btn-green">权限设置</button><button class="btn btn-oxfordGray apply_xj">下架</button><button class="btn btn-red del">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td><span class="tbbj"><img alt="" src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span></td><td>排课管理</td><td class="xj">下架</td><td>2018-11-11</td><td class="caozuo"><button class="btn btn-green">权限设置</button><button class="btn btn-orange apply_sj">上架</button><button class="btn btn-red del">删除</button></td></tr>
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
<div class="scts_xj" style="display:none;text-align: center;padding-top:20px;">
	<p>您确定将“<span style="color:#2299ee">排课管理</span>”小应用下架吗？</p>
	<p>一旦确定下架该小应用则不能在当前学校使用。</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>
<div class="scts_del" style="display:none;text-align: center;padding-top:20px;">
	<p>您确定删除“<span style="color:#2299ee">排课管理</span>”小应用吗？</p>
	<p>一旦确定删除则当前学校应用列表中无该小应用。</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>
<script>
function promptinfo()
{
    var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    var s3 = document.getElementById('s3');
    address.value = s1.value + s2.value + s3.value;
}
$('.table tbody i.ck').click(function(){
	
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
    	   $('.table thead i.ck').removeClass('on');
       }
    }else{
        $(this).addClass('on');
        if($('tbody i.ck').length==$('tbody i.ck.on').length){
        	$('.table thead i.ck').addClass('on');
        }
    }
});
$('.table thead i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
});
//添加应用
$('.addApply').click(function(){
	 $.initWinOnTopFromLeft_qyjx("添加应用", '${pageContext.request.contextPath}/views/demo/apply_center/a2_1_add_apply.jsp', '600', '520');
});
//下架提示
$('.apply_xj').click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '200px'],
		  title: '提示', //不显示标题
		  content: $('.scts_xj'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定下架','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
	
});
//删除提示
$('.del').click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '200px'],
		  title: '提示', //不显示标题
		  content: $('.scts_del'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定删除','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
});
</script>
</body>
</html>