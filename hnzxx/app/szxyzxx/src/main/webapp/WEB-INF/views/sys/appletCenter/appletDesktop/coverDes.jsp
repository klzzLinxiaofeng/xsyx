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
<body style="background-color:rgb(248, 248, 249)" onload="setup('2');preselect('省份');promptinfo();">
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
					<select class="select span2" name="city" id="s2" style="width:120px;display:inline-block" onchange="searchArea()"><option></option> </select>
					<!-- <select class="select span2" name="town" id="s3"><option></option></select> -->
					 <input id="address" name="address" type="hidden" value="" />
				</div> 
			</div>
				<div class="clear"></div>
			<div >
				<div class="select_div" >
					<select class="span2" name="attribute" id="attribute" onchange="searchArea()" style="width: inherit;">
						<option value="">请选择</option>
						<c:forEach items="${schoolAttributePlatformList }" var ="attribute">
							<option value="${attribute.id}">${attribute.name }</option>
						</c:forEach>
					</select>
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
			<tbody id ="coverSchool_list_content">
				<jsp:include page="./coverList.jsp" />
				<%-- <c:forEach items="${schDesList}" var="schDes" varStatus="a">
					<tr><td style="width:10%"><i class="ck" data-ownerid="${schDes.ownerId}"></i></td><td style="width:10%">${a.index+1 }</td><td style="width:20%">${schDes.province }</td><td style="width:20%">${schDes.city }</td><td>${schDes.schoolName }</td></tr>
				</c:forEach> --%>
				<!-- <tr><td style="width:10%"><i class="ck"></i></td><td style="width:10%">01</td><td style="width:20%">广东省</td><td style="width:20%">广州市</td><td>第一中学</td></tr>
				<tr><td><i class="ck"></i></td><td>02</td><td>广东省</td><td>广州市</td><td>第二中学</td></tr>
				<tr><td><i class="ck"></i></td><td>03</td><td>广东省</td><td>广州市</td><td>第三中学</td></tr>
				<tr><td><i class="ck"></i></td><td>04</td><td>广东省</td><td>广州市</td><td>第四中学</td></tr>
				<tr><td><i class="ck"></i></td><td>05</td><td>广东省</td><td>广州市</td><td>第五中学</td></tr>
				<tr><td><i class="ck"></i></td><td>06</td><td>广东省</td><td>广州市</td><td>第六中学</td></tr> -->
			</tbody>
		</table>
</div>
	</div>
	<div class="btn_cz">
		<button class="btn btn-blue" onclick="coverDes()">保存</button>
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
	
$('body').on('click','.table tbody i.ck',function(){
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

//搜索enter
$('body').on('keydown','.school_name',function(event){
    if(event.keyCode==13){
    	serach_xx();
    }
})

function serach_xx(){
	var school_name=$('.school_name').val();
	if(school_name==""){
		$(".school_list table tr").show();
	}else{
		$(".school_list table tr").hide();
		$(".school_list table td:nth-child(5)").each(function(){
			var school_html=$(this).text();
			school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
	 		$(this).html(school_html)
			if(school_html.indexOf(school_name)!=-1){
			var reg = new RegExp("("+school_name +")","ig");
			school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
			$(this).html(school_html);
			$(this).parent().show();
			}
		});
	}
}

function coverDes(){
	
	var IList = $("#coverSchool_list_content .ck.on");
	var ownerList ="";
	IList.each(function (index) {
		var ownerId = $(this).data("ownerid");
		if(index == 0){
			ownerList = ownerList + ownerId;
		}else {
			ownerList = ownerList + "," + ownerId;
		}
    });
	
	if(ownerList != ""){
		var val = {};
		val.schoolIdList = ownerList;
		var id = '${appletDesktopId}';
		val.id=id;
		var url = "${ctp}/sys/appletDesktop/cover";
		var loader = new loadLayer();
		loader.show();
		$.post(url, val, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					//parent.window.location.reload();
					$.closeWindow();
					
				} else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
		});
	}else {
		$.alert("请选择学校");
	}
}

//根据地区筛选学校,根据学校归类筛选
function searchArea(){
	var province = $("#s1").val();
	var city = $("#s2").val();
	//alert(province+":"+city);
	var val = {};
	val.province = province;
	val.city = city;
	
	var attributeId = $("#attribute").val();
	if (attributeId != null && attributeId != "") {
		val.attributeId = attributeId;
	}
	
	var id = "coverSchool_list_content";
	var url = "/sys/appletDesktop/coverList/"+'${appletDesktopId}';
	myPagination(id, val, url);
}

</script>
</body>
</html>