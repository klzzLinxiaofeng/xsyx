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
			<select class="span2" name="attribute" id="attribute" onchange="search();" style="width: inherit;">
				<option value="">请选择</option>
				<c:forEach items="${schoolAttributePlatformList }" var ="attribute">
					<option value="${attribute.id}">${attribute.name }</option>
				</c:forEach>
			</select>
		</div>
		
		<div style="float:right">
			<input type="text" style="width: 160px;height: 18px;margin-top: 8px;" id="schoolName" name="schoolName">
			<button class="btn btn-blue" style="margin-top: 8px;" onclick="search()">搜索</button>
		</div>
		
	</div>
</div>
<table class="table" style="margin-bottom:0;">
	<thead>
	<tr><th style="width: 22%;"><i class="ck" style="top: -11px;"></i></th><th style="width: 22%;">学校序号</th><th>学校列表</th></tr>
	</thead>
</table>
<div class="school_list"  style="height:170px">
<table class="table"  style="margin-bottom:0;">
	<tbody id="addSchool_list_content">
		<jsp:include page="./addSchAppletList.jsp" />
	</tbody>
	<%-- <div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="addSchool_list_content" />
				<jsp:param name="url" value="/sys/applet/addSchoolApplet/${appletId}?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
	</div> --%>
</table>
</div>
<div class="btn_cz">
	<button class="btn btn-blue" onclick="addSchoolApplet()">上架</button>
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
$('body').on('click','.table thead i.ck',function(){
	if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
});

//搜索enter
$('body').on('keydown','#schoolName',function(event){
    if(event.keyCode==13){
    	search();
    }
})

function search() {
	var val = {};
	var ids = ${appletId };
	var schoolName = $("#schoolName").val();
	if (schoolName != null && schoolName != "") {
		val.schoolName = schoolName;
	}
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
	
	var attributeId = $("#attribute").val();
	if (attributeId != null && attributeId != "") {
		val.attributeId = attributeId;
	}
	
	var id = "addSchool_list_content";
	var url = "/sys/applet/addSchoolApplet/"+ ids+"?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

function addSchoolApplet(){
	var ownerIdArray = new Array();
	$('tbody i.ck.on').each(function(index){
		ownerIdArray[index] = $(this).parent().parent().data("id");
	});
	$.post("${ctp}/sys/applet/addSchoolApplet/" + '${appletId}' + "/" + ownerIdArray, {"_method" : "put"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("上架成功");
				//parent.core_iframe.window.search();
				if(parent.core_iframe != null) {
					parent.core_iframe.window.search();
				}else {
					parent.window.search();
				}
				$.closeWindow();
				//parent.window.location.reload();
				//parent.window.location.href = "${ctp}/sys/applet/appletManage/editor?id="+'${appletId}';
			} else if ("fail" === data) {
				$.error("上架失败，系统异常", 1);
			}
		}
	});
}
search();
</script>
</body>
</html>