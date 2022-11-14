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
.chzn-container-single .chzn-single{
border-radius: 4px;
    border: solid 1px #dcdcdc;
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
			<div class="select_div"  style="float:none">
				<span>第三方系统：</span>
				<select class="span2" id="appletVendor" onchange="search()">
					<c:forEach items="${appletVendorList }" var ="appletVendor">
						<option value="${appletVendor.id}">${appletVendor.name }</option>
					</c:forEach>
				</select>
			</div>
			<div style="height: 3px;"></div>
			<div class="select_div" style="float:none">
				<span>地区：</span>
				<select class="select span2" name="province" id="s1"><option></option></select>
				<select class="select span2" name="city" id="s2"><option></option> </select>
				<select class="select span2" name="town" id="s3" onchange="searchSchool();"><option></option></select>
				<select class="span2" id="school" name="school" onchange="search();"><option data-id="">请选择学校</option></select>
				<input id="address" name="address" type="hidden" value="" />
			</div>
			
			<div class="select_div" style="float:left;">
				<span style="float: left;line-height: 31px;">学校： </span>
				<select id="schoolSelector" onchange='search("mohu");'></select>
			</div>
		</div>
		<div class="input_select school_info">
			<address style="float: left;margin:0">
			  <strong id = "sch" data-ownerid=""></strong><br>
			  <span id = "title_province"></span><span class="fenge"></span><span id = "title_city"></span><span class="fenge"></span><span id = "title_district"></span>
			</address>
			<!-- <button class="btn btn-orange" style="margin-left: 20px;" onclick="toDes()">桌面配置</button> -->
		</div>
		
		<div class="select_condition_div input_select" style="margin:5px 5px 0 0">
			<div class="select_div">
				<span>用户角色：</span>
				<select class="span2" id="roles" name="roles" onchange='search("sel");'><option data-roleid="0">请选择</option>
					<c:forEach items="${roleList }" var="role">
						<option data-roleid="${role.id }">${role.name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="select_div">
				<span>姓名：</span>
				<input type="text" id="name" name="name"  value="" />
			</div>
			<div class="btn_link" style="margin:8px 5px 0 0">
				<button class="btn btn-blue"  onclick='search("sel");'>搜索</button>
			</div>
			<div style="    height: 10px;"></div>
			<div class="select_div">
				<span>学校用户列表：</span>
				<button class="btn btn-orange" onclick="batchBind()">批量绑定</button>
			</div>
		</div>
		<div class="select_condition_div input_select" style="margin:5px 5px 0 0">
			
		</div>
		
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>用户账号</th><th>角色</th><th>用户姓名</th><th>第三方账号</th><th>绑定状态</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody id="vendorUser_list_content">
				<jsp:include page="./list.jsp" />
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="vendorUser_list_content" />
				<jsp:param name="url" value="/sys/applet/binding/index?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="bdzh" style="display:none;">
	<p class="title">正在绑定账号“<span style="color: #0080ff">4</span>”,请输入第三方账号信息</p>
	<p class="title1">已选中<span>4</span>个账号进行第三方账号绑定，请输入第三方账号信息</p>
	<div class="count"><label>账号：</label><input type="text" id = "count"></div>
	<div class="count"><label>密码：</label><input type="password" id = "pass"></div>
</div>
<div class="bdzh_ck" style="display:none;">
	<p class="title">“<span id="us_name">112004</span>”账号已绑定<span id="us_ven">纪光</span>账号</p>
	<div class="count"><label>账号：</label><input type="text" id = "count1" readonly ></div>
	<div class="count password_div" style="position:relative"><label>密码：</label><input type="password" id="pass1" value="" readonly ><i class="fa fa-eye" style= "cursor:pointer;position: absolute;right: 31px;top: 10px;"></i></div>
	<div class="count password_div" style="position:relative;display:none;"><label>密码：</label><input type="text" id="pass_1" value="" readonly ><i class="fa fa-eye" style= "cursor:pointer;position: absolute;right: 31px;top: 10px;"></i></div>
</div>
<div class="bdzh_jcbd" style="display:none;">
	<p class="title">确定解除当前“<span id="us_name1" style="color: #0080ff">112000</span>”的第三方账号绑定吗？解除绑定后将不可使用该账号访问<span id="us_ven1" style="color: #0080ff">纪光</span>的应用</p>
	<p class="p1">请谨慎操作</p>
</div>
<script>
$(function () {
    $.SchoolSelector({
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
    });
    
    $('.password_div i').click(function(){
    	$('.password_div').show();
    	$(this).parent().hide();
    })
    
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

//搜索enter
$('body').on('keydown','#name',function(event){
    if(event.keyCode==13){
    	search("sel");
    }
})

function search(obj){
	var val = {};
	//拿vendorId
	var vendorId = $("#appletVendor").find("option:selected").attr("value");
	if (vendorId != null && vendorId != "") {
		val.vendorId = vendorId;
	}
	
	
	/* var schoolName = $("#schoolName").val();
	if (schoolName != null && schoolName != "") {
		val.schoolName = schoolName;
	} */
	var schoolId = $("#schoolSelector").val();
	if (schoolId != null && schoolId != "") {
		val.schoolId = schoolId;
	}
	
	//加省和市的分割线fenge
	$(".fenge").text("-");
	
	if(obj != "mohu"){
		var school = $("#school").val();
		var ownerId = $("#school option:selected").data("id");
		if (ownerId != null && ownerId != "") {
			val.schoolId = ownerId;
		}
	}
	
	//添加查询条件
	if(obj == "sel"){
		var roleId = $("#roles").find("option:selected").attr("data-roleid");
		console.log(roleId);
		if(roleId != null && roleId != ""){
			val.roleId = roleId;
		}
		var name = $("#name").val();
		if(name != null && name != ""){
			val.name = name;
		}
		//取当前学校信息
		var ownerId = $("#sch").attr("data-ownerid");
		val.schoolId = ownerId;
	}
	
	var id = "vendorUser_list_content";
	var url = "/sys/applet/binding/index?sub=list";
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
	/* var ids = "";
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
	} */
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
//批量绑定小窗口
function batchBind(){
	$('.bdzh .title1').show();
	$('.bdzh .title').hide();
	$('.bdzh .count input').val('');
	var i=$('.table tbody .ck.on').length;
	$('.bdzh .title1 span').text(i);
	if(i == 0){
		$.alert("请选择账号");
	}else {
		layer.open({
			  type: 1,
			  shade: false,
			  area: ['350px', '280px'],
			  title: '绑定账号', //不显示标题
			  content: $('.bdzh'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  btn: ['绑定','取消'],//按钮
			  btn1: function(index, layero){
				  batchBinding();
			  },
			  btn2: function(index, layero){
				  layer.close();
			  }
		});
	}
}
//执行批量绑定
function batchBinding(){
	var val = {};
	
	var count = $("#count").val();
	var pass = $("#pass").val();
	var vendorId = $("#appletVendor").find("option:selected").attr("value");
	val.vendorUserName = count;
	val.vendorPassword = pass;
	val.vendorId = vendorId;
	
	var userIdStr = "";
	var cks = $('.table tbody .ck.on');
	for(var i = 0 ; i < cks.length ; i++){
        var ck = cks[i];
        var userId = $(ck).parent().parent().attr("data-id");
        if(i == 0){
        	userIdStr = userId;
        }else {
        	userIdStr = userIdStr + "," + userId;
		}
    }
	val.userIdStr = userIdStr;
	$.post("${ctp}/sys/applet/binding/batchBind" , val,function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("绑定成功");
				//parent.core_iframe.window.search();
				search();
			} else if ("fail" === data) {
				$.error("绑定失败，系统异常", 1);
			}
		}
	});	
}

</script>
</body>
</html>