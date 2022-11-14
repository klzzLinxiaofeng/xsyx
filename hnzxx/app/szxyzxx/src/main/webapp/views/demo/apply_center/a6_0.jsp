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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>学校应用管理</title>
<style>
.input_select .select_div {
    float: none; 
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)"  onload="setup();preselect('省份');promptinfo();">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > 平台应用管理  >   <span>学校应用管理</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>管理-排课管理</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		
		<div class="apply_detail">
			<p class="p1 fl"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></p>
			<div class="cz_btn fr">
				<button class="btn btn-forbidGray" disabled="disabled">上架到全部学校</button>
				<button class="btn btn-green sjdqbxx">上架到全部学校</button>
				<button class="btn btn-forbidGray" disabled="disabled">选择学校上架</button>
				<button class="btn btn-orange xzxxsj">选择学校上架</button>
				<button class="btn btn-oxfordGray">下架应用</button>
			</div>
			<div class="jianjie">
				<p class="p2"><strong>办公OA</strong><span class="banben">版本：1.0</span></p>
				<p class="p3">说明：<span>保修服务只限于一般正常使用下有效。一切人为损坏例如接入不适当电源，使用不适当配件，不依说明书使用；因运输及其它意外而造成之损坏；非经本公司认可的维修和改造，错误使用或疏忽而造成损坏；不适当之安装等，保修服务立即失效。此保修服务并不包括运输费及维修人员上门服务费。保修服务只限于一般正常使用下有效。一切人为损坏例如接入不适当电源，使用不适当配件，不依说明书使用；因运输及其它意外而造成之损坏；非经本公司认可的维修和改造，错误使用或疏忽而造成损坏；不适当之安装等，保修服务立即失效。此保修服务并不包括运输费及维修人员上门服务费。 </span></p>
			</div>
		</div>
		
		<div class="input_select">
			<div class="select_div">
				<span>学校：</span>
				<input type="text">
				<button class="btn btn-blue">查询</button>
			</div>
			<div class="select_div">
				<span>区域：</span>
				<select class="select span2" name="province" id="s1"><option></option></select>
				<select class="select span2" name="city" id="s2"><option></option> </select>
				<select class="select span2" name="town" id="s3"><option></option></select>
				 <input id="address" name="address" type="hidden" value="" />
			</div>
			
			
		</div>
		<div class="input_select" style="border-top: solid 1px #e4e8eb;border-bottom: solid 1px #e4e8eb;line-height: 27px;">
			<span style="color:#666666;">已上架列表：<span style="color:#999999;">(以上架到全部学校)</span></span>
			<button class="btn btn-oxfordGray fr" onclick="batchOffTheShelf()">批量下架</button>
		</div>
		
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>省</th><th>市</th><th>区</th><th>学校</th><th>状态</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td><i class="ck"></i></td><td>广东省</td><td>广州市</td><td>番禺区</td><td>第一小学</td><td class="sj">上架</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj">下架</button></td></tr>
				<tr><td><i class="ck"></i></td><td>广东省</td><td>广州市</td><td>番禺区</td><td>第2小学</td><td class="sj">上架</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj">下架</button></td></tr>
				<tr><td><i class="ck"></i></td><td>广东省</td><td>广州市</td><td>番禺区</td><td>第3小学</td><td class="sj">上架</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj">下架</button></td></tr>
				<tr><td><i class="ck"></i></td><td>广东省</td><td>广州市</td><td>番禺区</td><td>第一小学</td><td class="sj">上架</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj">下架</button></td></tr>
				<tr><td><i class="ck"></i></td><td>广东省</td><td>广州市</td><td>番禺区</td><td>第2小学</td><td class="sj">上架</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj">下架</button></td></tr>
				<tr><td><i class="ck"></i></td><td>广东省</td><td>广州市</td><td>番禺区</td><td>第3小学</td><td class="sj">上架</td><td class="caozuo"><button class="btn btn-oxfordGray apply_xj">下架</button></td></tr>
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
<div class="scts_sjdqbxx" style="display:none;text-align: center;padding-top:20px;">
	<p>您确定将“<span style="color:#2299ee">排课管理</span>”小应用上架到全部学校吗？</p>
	<p>确定上架后全部学校都能使用“<span style="color:#2299ee">排课管理</span>”小应用，</p>
	<p style="color:#ff5252">请谨慎操作。</p>
</div>
<div class="scts_plsc" style="display:none;text-align: center;padding-top:20px;">
	<p>您确定将从选中的学校的应用库中下架“<span style="color:#2299ee">排课管理</span>”小应用？</p>
	<p>一旦确定下架学校将无法使用该小应用。</p>
	<p style="color:#ff5252">请谨慎操作。</p>
	<p style="text-align: left;margin:20px 0px 20px 20px">学校列表：</p>
	<div class="plplpl"  style="padding:10px 20px;">
		<ul class="plsc_list">
			<li>
			</li>
		</ul>
	</div
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
$('body').on('click','.plsc_list i.ck',function(){
	if($(this).hasClass('on')){
        $(this).removeClass('on');
    }else{
        $(this).addClass('on');
    }
})
$('.table thead i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
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
//批量下架
function batchOffTheShelf(){
	if($('tbody i.ck.on').length>0){
		
		$('tbody i.ck.on').each(function(index){
		$("<li><p><i class='ck on'></i><i style='font-style:normal;'>"+(index+1)+"、</i>"+
		$(this).parent().siblings('td:eq(0)').text()+"-"+$(this).parent().siblings('td:eq(1)').text()+"-"+$(this).parent().siblings('td:eq(2)').text()+"-"+$(this).parent().siblings('td:eq(3)').text()+
		"</li></p>").appendTo($('.plsc_list'));
		});
		
		layer.open({
			  type: 1,
			  shade: false,
			  area: ['390px', '350px'],
			  title: '提示', //不显示标题
			  content: $('.scts_plsc'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  btn: ['确定下架','取消'],//按钮
			  btn1: function(index, layero){
				 
			  }
		});
	}else{
		layer.msg('请选择学校');
	}
}
//添加应用
$('.xzxxsj').click(function(){
	 $.initWinOnTopFromLeft_qyjx("选择学校上架", '${pageContext.request.contextPath}/views/demo/apply_center/a6_1_2.jsp', '600', '477');
});
//上架到全部学校
$('.sjdqbxx').click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '200px'],
		  title: '提示', //不显示标题
		  content: $('.scts_sjdqbxx'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['全部上架','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
});


</script>
</body>
</html>