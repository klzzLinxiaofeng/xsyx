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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>应用桌面管理</title>
<style>
.table{
	margin:0;
}
.content_main {
     box-shadow: none; 
    border: none; 
}
.container-fluid{
	padding:0;
}
.school_list .table td:not(:first-child){
	padding-left:30px;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)" onload="setup();preselect('省份');promptinfo();">
<div class="container-fluid">
	<div class="content_main white">
		<div class="zyy_top">主应用：智慧校园平台</div>
		<div class="input_select">
			<div class="select_div">
				<span style="font-size: 14px;">学校配置列表：</span>
			</div>
				<div class="clear"></div>
			<div >
				<div class="select_div"  style="width:258px;">
					<select class="select span2" name="province" id="s1" style="width:130px;display:inline-block"><option></option></select>
					<select class="select span2" name="city" id="s2" style="width:120px;display:inline-block"><option></option> </select>
					<!-- <select class="select span2" name="town" id="s3"><option></option></select> -->
					 <input id="address" name="address" type="hidden" value="" />
				</div>
				<div style="float:right"><input type="text" class="school_name" style="width: 130px;height: 18px;margin-top: 8px;">
				<button class="btn btn-blue" style="margin-top: 8px;" onclick="serach_xx()">搜索</button></div>
			</div>
		</div>
		<table class="table">
			<thead>
				<tr style="border-top: 1px solid #dddddd;">
					<th style="width:10%"><i class="ck" style="top: -11px;"></i></th>
					<th style="width:10%">序号</th>
					<th style="width:20%">省份</th>
					<th style="width:20%">市区</th>
					<th >学校名称</th>
				</tr>
			</thead>
		</table>
		<div class="school_list"  style="height:155px;overflow:auto;">
		<table class="table">
			<tbody>
				<tr><td style="width:10%"><i class="ck"></i></td><td style="width:10%">01</td><td style="width:20%">广东省</td><td style="width:20%">广州市</td><td>第一中学</td></tr>
				<tr><td><i class="ck"></i></td><td>02</td><td>广东省</td><td>广州市</td><td>第二中学</td></tr>
				<tr><td><i class="ck"></i></td><td>03</td><td>广东省</td><td>广州市</td><td>第三中学</td></tr>
				<tr><td><i class="ck"></i></td><td>04</td><td>广东省</td><td>广州市</td><td>第四中学</td></tr>
				<tr><td><i class="ck"></i></td><td>05</td><td>广东省</td><td>广州市</td><td>第五中学</td></tr>
				<tr><td><i class="ck"></i></td><td>06</td><td>广东省</td><td>广州市</td><td>第六中学</td></tr>
			</tbody>
		</table>
</div>
	</div>
	<div class="btn_cz">
		<button class="btn btn-blue">保存</button>
		<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
	</div>
</div>
<script>
function promptinfo()
{
    var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    /*var s3 = document.getElementById('s3');*/
    address.value = s1.value + s2.value;
}
$(function(){
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
});
function serach_xx(){
	var school_name=$('.school_name').val();
	$(".school_list table td:nth-child(5)").each(function(){
		var school_html=$(this).text();
		school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
 		$(this).html(school_html)
		if(school_html.indexOf(school_name)!=-1){
		var reg = new RegExp("("+school_name +")","ig");
		school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
		$(this).html(school_html)
		}
	})
}
</script>
</body>
</html>