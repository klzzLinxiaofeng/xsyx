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
<title></title>
<style>
	@media (max-width: 767px){
.input-large, .input-xlarge, .input-xxlarge, input[class*="span"], select[class*="span"], textarea[class*="span"], .uneditable-input {
    display: block;
    width: 25%;
    min-height: 30px;
    box-sizing: border-box;
    float: left;
    margin-right: 10px;
}
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)"  onload="setup();preselect('省份');promptinfo();">
<div class="input_select">
<div class="select_div"  style="width: 100%;">
	<select class="select span2" name="province" id="s1"><option></option></select>
	<select class="select span2" name="city" id="s2"><option></option> </select>
	<select class="select span2" name="town" id="s3"><option></option></select>
	 <input id="address" name="address" type="hidden" value="" />
</div>
</div>
<div class="content_main white" style="border-radius: 0;">
	<div class="input_select">
		<div class="select_div" style="display: inline-flex;">
			<span style="width: 80px;line-height: 30px;">学校列表：</span>
		</div>
		<div style="float:right">
			<input type="text" style="width: 160px;height: 18px;margin-top: 8px;">
			<button class="btn btn-blue" style="margin-top: 8px;">搜索</button>
		</div>
	</div>
</div>
<table class="table" style="margin-bottom:0;">
	<thead>
	<tr><th style="width: 26%;"><i class="ck" style="top: -11px;"></i></th><th style="width: 27%;">学校序号</th><th>学校列表</th></tr>
	</thead>
</table>
<div class="school_list"  style="height:170px">
<table class="table"  style="margin-bottom:0;">
	<tbody>
		<tr><td><i class="ck"></i></td><td>01</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>02</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>03</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>01</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>02</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>03</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>01</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>02</td><td>第一小学</td></tr>
		<tr><td><i class="ck"></i></td><td>03</td><td>第一小学</td></tr>
	</tbody>
</table>
</div>
<div class="btn_cz">
	<button class="btn btn-blue">上架</button>
	<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
</div>
<script>
$(".school_list").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
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

</script>
</body>
</html>