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
<title>切换桌面</title>
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
<body style="background-color:rgb(248, 248, 249)" onload="setup('2');preselect('省份');promptinfo();">
<div class="container-fluid">
	<div class="content_main white">
		<div class="zyy_top">
			<button class="btn btn-blue">平台配置</button><button class="btn">学校配置</button>
		</div>
		<div class="pz_select">
			<div class="peizhi_div">
				<div class="input_select">
					<div class="select_div">
						<span style="font-size: 14px;">平台配置列表（选择一个桌面配置）</span>
					</div>
						<div class="clear"></div>
					<div >
						<div style="float:left"><input type="text" class="school_name" style="width: 130px;height: 18px;margin-top: 8px;">
						<button class="btn btn-blue" style="margin-top: 8px;" onclick="serach_xx()">搜索</button></div>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr style="border-top: 1px solid #dddddd;">
							<th style="width:10%"></th>
							<th style="width:30%">序号</th>
							<th >桌面配置-名称</th>

						</tr>
					</thead>
				</table>
				<div class="school_list"  style="height:155px;overflow:auto;">
					<table class="table">
						<tbody>
							<c:forEach items="${defDesList }" var="defDes" varStatus="a">
								<tr><td style="width:10%"><i class="cg" data-id="${defDes.id }"></i></td><td style="width:30%">${a.index+1 }</td><td >${defDes.name }
								<c:if test='${defDes.ownerType == 1 && defDes.ownerId == 0 && defDes.isDefault == "true"}'>（当前默认）</c:if></td></tr>
							</c:forEach>
							<!--<tr><td style="width:10%"><input type="radio" name="zm_name"></td><td style="width:30%">01</td><td >智慧校园应用桌面配置（默认桌面）</td></tr>
							 <tr><td style="width:10%"><input type="radio" name="zm_name"></td><td style="width:30%">02</td><td >智慧校园应用桌面配置（默认桌面）</td></tr>
							<tr><td style="width:10%"><input type="radio" name="zm_name"></td><td style="width:30%">03</td><td >智慧校园应用桌面配置（默认桌面）</td></tr>
							<tr><td style="width:10%"><input type="radio" name="zm_name"></td><td style="width:30%">04</td><td >智慧校园应用桌面配置（默认桌面）</td></tr>
							<tr><td style="width:10%"><input type="radio" name="zm_name"></td><td style="width:30%">05</td><td >智慧校园应用桌面配置（默认桌面）</td></tr> -->
						</tbody>
					</table>
				</div>
			</div>
			<div class="peizhi_div" style="display:none">
				<div class="input_select">
					<div class="select_div">
						<span style="font-size: 14px;">学校配置列表（选择一个学校的桌面配置）</span>
					</div>
						<div class="clear"></div>
					<div >
					<div class="select_div"  style="width:258px;">
						<select class="select span2" name="province" id="s1" style="width:130px;display:inline-block"><option></option></select>
						<select class="select span2" name="city" id="s2" style="width:120px;display:inline-block"  onchange="searchArea()"><option></option> </select>
						 <select class="select span2" name="town" id="s3"  style="display:none"><option></option></select> 
						 <input id="address" name="address" type="hidden" value="" />
					</div>
						<div style="float:right"><input type="text" class="school_name1" style="width: 130px;height: 18px;margin-top: 8px;">
						<button class="btn btn-blue" style="margin-top: 8px;" onclick="serach_xx1()">搜索</button></div>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr style="border-top: 1px solid #dddddd;">
							<th style="width:10%"></th>
							<th style="width:10%">序号</th>
							<th style="width:20%">省份</th>
							<th style="width:20%">市区</th>
							<th >学校名称</th>
						</tr>
					</thead>
				</table>
				<div class="school_list1"  style="height:155px;overflow:auto;">
					<table class="table">
						<tbody id ="appletSchool_list_content">
							<jsp:include page="./schList.jsp" />
							<%-- <c:forEach items="${schDesList }" var="schDes" varStatus="a">
								<tr><td style="width:10%"><i class="cg" data-id="${schDes.id }"></i></td><td style="width:10%">${a.index+1 }</td><td style="width:20%">${schDes.province }</td><td style="width:20%">${schDes.city }</td><td >${schDes.schoolName }</td></tr>
							</c:forEach> --%>
							<!--<tr><td style="width:10%"><input type="radio" name="school_name"></td><td style="width:10%">01</td><td style="width:20%">广东省</td><td style="width:20%">广州市省</td><td >第一中学</td></tr>
							 <tr><td style="width:10%"><input type="radio" name="school_name"></td><td style="width:10%">02</td><td style="width:20%">广东省</td><td style="width:20%">广州市省</td><td >第二中学</td></tr>
							<tr><td style="width:10%"><input type="radio" name="school_name"></td><td style="width:10%">03</td><td style="width:20%">广东省</td><td style="width:20%">广州市省</td><td >第一中学</td></tr>
							<tr><td style="width:10%"><input type="radio" name="school_name"></td><td style="width:10%">04</td><td style="width:20%">广东省</td><td style="width:20%">广州市省</td><td >第一中学</td></tr>
							<tr><td style="width:10%"><input type="radio" name="school_name"></td><td style="width:10%">05</td><td style="width:20%">广东省</td><td style="width:20%">广州市省</td><td >第一中学</td></tr>
							<tr><td style="width:10%"><input type="radio" name="school_name"></td><td style="width:10%">06</td><td style="width:20%">广东省</td><td style="width:20%">广州市省</td><td >第一中学</td></tr> -->
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
	</div>
	<div class="btn_cz">
		<button class="btn btn-blue" onclick="changedes()">确定</button>
		<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
	</div>
</div>
<script>
function promptinfo()
{
    var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    var s3 = document.getElementById('s3');
    address.value = s1.value + s2.value;
}
$(function(){
$(".zyy_top button").click(function(){
	$(".zyy_top button").removeClass('btn-blue');
	$(this).addClass('btn-blue');
	var i=$(this).index();
	$(".peizhi_div").hide();
	$('.peizhi_div').eq(i).show();
});
//弹出框的启用
$("i.cg").click(function(){
	var table_class=$(this).parents('table').parent().attr('class');
	/* if(i==0){
		$('.school_list i.cg').removeClass("on");
	}else{
		$('.school_list1 i.cg').removeClass("on");
	} */
	$('i.cg').removeClass("on");
	$(this).addClass("on");
})
});

//搜索enter
$('body').on('keydown','.school_name',function(event){
    if(event.keyCode==13){
    	serach_xx();
    }
})
$('body').on('keydown','.school_name1',function(event){
    if(event.keyCode==13){
    	serach_xx1();
    }
})

function serach_xx(){
	var school_name=$('.school_name').val();
	console.log(school_name)
	 if(school_name==""){
		$(".school_list table tr").show();
	} else{
			$(".school_list table tr").hide();
		$(".school_list table td:nth-child(3)").each(function(){
			var school_html=$(this).text();
			school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
	 		$(this).html(school_html)
			if(school_html.indexOf(school_name)!=-1){
			var reg = new RegExp("("+school_name +")","ig");
			school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
			$(this).html(school_html);
			$(this).parent().show();
			}
		})
	}
}
function serach_xx1(){
	var school_name=$('.school_name1').val();
	if(school_name==""){
		$(".school_list1 table tr").show();
	}else{
			$(".school_list1 table tr").hide();
		$(".school_list1 table td:nth-child(5)").each(function(){
			var school_html=$(this).text();
			school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
	 		$(this).html(school_html)
			if(school_html.indexOf(school_name)!=-1){
			var reg = new RegExp("("+school_name +")","ig");
			school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
			$(this).html(school_html);
			console.log(school_html)
			$(this).parent().show();
			}
		})
	}
}

//切换桌面
function changedes(){
	var input_val=$("i.cg.on").data('id');
	if(input_val != null){
		var val = {};
		val.id = input_val;
		var url = "${ctp}/sys/appletDesktop/changeDestop";
		parent.core_iframe.window.location.href = url+"/"+input_val;
		//parent.window.location.href = url+"/"+input_val;
		$.closeWindow();
	}else {
		$.alert("请选择切换桌面");
	}
}

//根据地区筛选学校
function searchArea(){
	var province = $("#s1").val();
	var city = $("#s2").val();
	
	var val = {};
	val.province = province;
	val.city = city;
	var id = "appletSchool_list_content";
	var url = "/sys/appletDesktop/schList";
	myPagination(id, val, url);
}

</script>
</body>
</html>