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
    /* float: none;  */
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)" onload="setup();preselect('省份');promptinfo();">
<div class="container-fluid">
	<p class="top_link">应用中心  >  第三方应用管理  > <span>第三方账号绑定</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>第三方账号绑定</p></div>
		</div>
		<div class="input_select">
			<div class="select_div">
				<%-- <span>主应用：</span>
				<select class="span2" id="appSelect" >
					<c:forEach items="${appList }" var ="app">
						<option value="${app.appKey}">${app.name }</option>
					</c:forEach>
				</select> --%>
			</div>
			<div class="select_div" style="float:none">
				<span>学校：</span>
				<select class="select span2" name="province" id="s1"><option></option></select>
				<select class="select span2" name="city" id="s2"><option></option> </select>
				<select class="select span2" name="town" id="s3" onchange="searchSchool();"><option></option></select>
				<select class="span2" id="school" name="school" onchange="search();"><option data-id="">请选择学校</option></select>
				<input id="address" name="address" type="hidden" value="" />
			</div>
			
			<!-- <div class="input_select" style="padding: 5px;">
				<div class="select_div" style="float:left;">
					<span style="float: left;line-height: 31px;">学校： </span>
					<select id="schoolSelector" onchange='search("mohu");'></select>
				</div>
			</div> -->
		</div>
		<div class="input_select school_info">
			<address style="float: left;margin:0">
			  <strong id = "sch" data-ownerid="">广州市第一中学</strong><br>
			  <span id = "title_province"></span><span class="fenge"></span><span id = "title_city"></span><span class="fenge"></span><span id = "title_district"></span>
			</address>
			<!-- <button class="btn btn-orange" style="margin-left: 20px;" onclick="toDes()">桌面配置</button> -->
		</div>
		
		<div class="select_condition_div input_select" style="margin:5px 5px 0 0">
			<div class="select_div">
				<span>用户角色：</span>
				<select class="span2" id="lineType" name="lineType" onchange='search("sel");'><option data-line_type="10">请选择</option><option data-line_type="1">上架</option><option data-line_type="0">下架</option></select>
			</div>
			<div class="select_div">
				<span>姓名：</span>
				<input type="text" id="name" name="name"  value="" />
			</div>
			<div class="btn_link" style="margin:8px 5px 0 0">
				<button class="btn btn-blue"  onclick='search("sel");'>搜索</button>
			</div>
			
		</div>
		
		<div class="input_select">
			<button class="btn btn-oxfordGray" onclick="batchDown()">批量下架</button>
			<button class="btn btn-red" onclick="batchDel()">批量删除</button>
			<button class="btn btn-blue fr addApply">添加应用</button>
		</div>
		
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>图标</th><th>名称</th><th>状态</th><th>注册时间</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody id="vendorUser_list_content">
				<jsp:include page="./list.jsp" />
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="vendorUser_list_content" />
				<jsp:param name="url" value="/sys/appletVendor/index?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<script>
$(function () {
    /* $.SchoolSelector({
        "selector": "#schoolSelector",
        "isShowfirstOptionTitle": false,
        "afterHandler": function () {
            $("#schoolSelector option:first ").prop("selected", 'selected');
            //$("#school option:first-child").attr
            //var id = $("#school option:selected").data("id");
            //$("#school option:selected").attr("data-id","");
            search();
            //$("#school option:selected").attr("data-id",id);
        },
    }); */
})

function promptinfo()
{
    var address = document.getElementById('address');
    var s1 = document.getElementById('s1');
    var s2 = document.getElementById('s2');
    var s3 = document.getElementById('s3');
    address.value = s1.value + s2.value + s3.value;
}
$('body').on('click','.table tbody i.ck',function(){
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
$('.addApply').click(function(){
	 var ownerId = $("#sch").attr("data-ownerid");
	 //var ownerId = ${appletList[0].schoolId }
	 //console.log(ownerId);
	 if(ownerId == ""){
		$.alert("请选择学校");
	 }else {
		 $.initWinOnTopFromLeft_qyjx("添加应用", '${ctp}/sys/applet/school/addApplet/'+ownerId, '600', '500');
	}
});

function search(obj){
	var val = {};
	/* var schoolName = $("#schoolName").val();
	if (schoolName != null && schoolName != "") {
		val.schoolName = schoolName;
	} */
	var schoolId = $("#schoolSelector").val();
	if (schoolId != null && schoolId != "") {
		val.ownerId = schoolId;
	}
	
	//加省和市的分割线fenge
	$(".fenge").text("-");
	
	if(obj != "mohu"){
		var school = $("#school").val();
		var ownerId = $("#school option:selected").data("id");
		if (ownerId != null && ownerId != "") {
			val.school = school;
			val.ownerId = ownerId;
		}
	}
	
	//添加查询条件
	if(obj == "sel"){
		var lineType = $("#lineType").find("option:selected").attr("data-line_type");
		if(lineType != null && lineType != "" && lineType != 10){
			val.lineType = lineType;
		}
		var name = $("#name").val();
		if(name != null && name != ""){
			val.name = name;
		}
		//取当前学校信息
		var ownerId = $("#sch").attr("data-ownerid");
		val.ownerId = ownerId;
	}
	
	var id = "vendorUser_list_content";
	var url = "/sys/applet/school/searchOwnerApplet";
	myPagination(id, val, url);
}

function searchSchool(){
	var val = {};
	var province = $("#s1").val();
	if (province != null && province != "") {
		val.province = province;
	}
	var city = $("#s2").val();
	if (city != null && city != "") {
		val.city = city;
	}
	var district = $("#s3").val();
	if (district != null && district != "") {
		val.district = district;
	}
	var url = "${ctp}/sys/applet/school/searchSchool";
	var dataJson = '{"province":"'+val.province+'","city":"'+val.city+'","district":"'+val.district+'"}';
	var aj = $.ajax({
		url : url,
		data : 'val='+dataJson,
		type : 'post',
		cache : false,
		dataType : 'json',
		success : function(data) {
			loadSchool(data);
		},error : function() {
			//$.alert("异常！");
		}
	});
}

function loadSchool(data){
	var schoolList = data;
	var school = $("#school");
	school.text("");
	
	school.append('<option data-id="">请选择学校</option>');
	
	for (var i = 0; i < schoolList.length; i++) {
		school.append("<option data-id="+schoolList[i].id+">"+ schoolList[i].schoolName +"</option>"    );
	}
}

function batchDown(){
	var ids = "";
	var val ={};
	var ckList = $("#vendorUser_list_content .ck.on");
	ckList.each(function(index){
		var appletOwnerId = $(this).parent().parent().data("id");
		if(index == 0){
			ids = ids + appletOwnerId;
		}else {
			ids = ids + "," + appletOwnerId;
		}
	});
	if(ids == ""){
		$.alert("请选择应用");
	}else {
		val.appletOwnerIds=ids;
		var url = "${ctp}/sys/applet/school/batchDown";
		$.post(url, val, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("操作成功");
					//parent.window.location.reload();
					parent.core_iframe.window.search();
				} else if ("fail" === data) {
					$.error("操作失败，系统异常", 1);
				}
			}
		});
	}
}

function batchDel(){
	var ids = "";
	var val ={};
	var ckList = $("#vendorUser_list_content .ck.on");
	ckList.each(function(index){
		var appletOwnerId = $(this).parent().parent().data("id");
		if(index == 0){
			ids = ids + appletOwnerId;
		}else {
			ids = ids + "," + appletOwnerId;
		}
	});
	if(ids == ""){
		$.alert("请选择应用");
	}else {
		val.appletOwnerIds=ids;
		var url = "${ctp}/sys/applet/school/batchDel";
		$.post(url, val, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("操作成功");
					//parent.window.location.reload();
					parent.core_iframe.window.search();
				} else if ("fail" === data) {
					$.error("操作失败，系统异常", 1);
				}
			}
		});
	}
}

function toDes(){
	window.location.href = "${ctp}/sys/appletDesktop/index";
}

</script>
</body>
</html>