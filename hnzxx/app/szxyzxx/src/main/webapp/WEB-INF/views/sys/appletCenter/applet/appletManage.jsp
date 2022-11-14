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
			<div class="f_left"><p>管理-${applet.name }</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;" onclick="back();"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		
		<div class="apply_detail">
			<p class="p1 fl"><img src="${applet.iconUrl }"></p>
			<div class="cz_btn fr">
				<c:choose>
					<c:when test="${applet.lineType == 1 }">
						<button class="btn btn-forbidGray" disabled="disabled">上架到全部学校</button>
						<!-- <button class="btn btn-forbidGray" disabled="disabled">选择学校上架</button> -->
						<button class="btn btn-orange xzxxsj">选择学校上架</button>
						<button class="btn btn-oxfordGray xjyy">下架应用</button>
					</c:when>
					<c:when test="${applet.lineType == 2 }">
						<button class="btn btn-green sjdqbxx">上架到全部学校</button>
						<button class="btn btn-orange xzxxsj">选择学校上架</button>
						<button class="btn btn-oxfordGray xjyy">下架应用</button>
					</c:when>
					<c:otherwise>
						<!-- <button class="btn btn-green sjdqbxx">上架到全部学校</button>
						<button class="btn btn-orange xzxxsj">选择学校上架</button> -->
						<button class="btn btn-green sjyy" onclick="onlyUpAppelt()">上架应用</button>
					</c:otherwise>
				</c:choose>
				<!-- <button class="btn btn-oxfordGray xjyy">下架应用</button> -->
			</div>
			<div class="jianjie">
				<p class="p2"><strong>${applet.name }</strong><span class="banben">版本：${applet.version }</span>
					<c:if test="${applet.lineType == 0 }"><span class="qk yxj_red">已下架</span></p></c:if>
				<p class="p3">说明：<span>${applet.description }</span></p>
			</div>
		</div>
		
		<c:if test="${applet.lineType != 0 }">
		<div class="input_select">
			
			<div class="select_div">
				<span>学校：</span>
				<input type="text" id="schoolName" name="schoolName">
				<button class="btn btn-blue" onclick="search();">查询</button>
			</div>
			<div class="select_div">
				<span>区域：</span>
				<select class="select span2" name="province" id="s1"><option></option></select>
				<select class="select span2" name="city" id="s2"><option></option> </select>
				<select class="select span2" name="town" id="s3"><option></option></select>
				 <input id="address" name="address" type="hidden" value="" />
			</div>
			<div class="select_div">
				<span>学校创建者：</span>
				<select class="span2" name="attribute" id="attribute" onchange="search();">
					<option value="">请选择</option>
					<c:forEach items="${schoolAttributePlatformList }" var ="attribute">
						<option value="${attribute.id}">${attribute.name }</option>
					</c:forEach>
				</select>
				
			</div>
			
		</div></c:if>
		<div id="donghua">
			
		</div>
		
		<c:if test="${applet.lineType != 0 }">
		<div class="input_select" style="border-top: solid 1px #e4e8eb;border-bottom: solid 1px #e4e8eb;line-height: 27px;">
			<span style="color:#666666;">已上架列表：
				<c:if test="${applet.lineType == 1 }">
					<span style="color:#999999;">(以上架到全部学校)</span>
				</c:if>
			</span>
			<button class="btn btn-oxfordGray fr" onclick="batchOffTheShelf()">批量下架</button>
		</div>
		
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>省</th><th>市</th><th>区</th><th>学校</th><th>状态</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody id="appletSchool_list_content">
				<jsp:include page="./appletSchoolList.jsp" />
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="appletSchool_list_content" />
				<jsp:param name="url" value="/sys/applet/appletManage/editor?sub=list&dm=${param.dm}&id=${applet.id }" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div></c:if>
		
		
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
$('body').on('click','.table tbody i.ck',function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
    	   $('.table thead i.ck').removeClass('on');
       }
    }else{
        $(this).addClass('on');
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

//搜索enter
$('body').on('keydown','#schoolName',function(event){
    if(event.keyCode==13){
    	search();
    }
})

//下架提示
$('.xjyy').click(function(){
	var appletName = "${applet.name }";
	$('.scts_xj').children("p:first-child").children("span:first-child").text(appletName);
	var appletId = ${applet.id };
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
			 appletXiaJia(appletId);
		  }
	});
});
//执行下架
function appletXiaJia(appletId){
	var loader = new loadLayer();
	loader.show();
	$.post("${ctp}/sys/applet/appletXiaJia/" + appletId, {"_method" : "delete"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("下架成功");
				window.location.reload();
			} else if ("fail" === data) {
				$.error("下架失败，系统异常", 1);
			}
		}
		loader.close();
	});
}


//批量下架
function batchOffTheShelf(){
	if($('tbody i.ck.on').length>0){
		var appletName = "${applet.name }";
		$('.scts_plsc').children("p:first-child").children("span:first-child").text(appletName);
		var appletId = ${applet.id };
		
		$('.plli').remove();
		$('tbody i.ck.on').each(function(index){
		$("<li class='plli' data-id="+$(this).parent().siblings('td:eq(0)').parent().data("id")+"><p><i class='ck on'></i><i style='font-style:normal;'>"+(index+1)+"、</i>"+
		$(this).parent().siblings('td:eq(0)').text()+"-"+$(this).parent().siblings('td:eq(1)').text()+"-"+$(this).parent().siblings('td:eq(2)').text()+"-"+$(this).parent().siblings('td:eq(3)').text()+
		"</p></li>").appendTo($('.plsc_list'));
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
				  appletBatchXiaJia(appletId);
			  }
		});
	}else{
		layer.msg('请选择学校');
	}
}
//执行批量下架
function appletBatchXiaJia(appletId){
	var ownerIdArray = new Array();
	$('.plplpl i.ck.on').each(function(index){
		ownerIdArray[index] = $(this).parent().parent().data("id");
	});
	//console.log(ownerIdArray);
	$.post("${ctp}/sys/applet/appletBatchXiaJia/" + appletId + "/" + ownerIdArray, {"_method" : "delete"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("下架成功");
				window.location.reload();
			} else if ("fail" === data) {
				$.error("下架失败，系统异常", 1);
			}
		}
	});
}


//选择学校上架
$('.xzxxsj').click(function(){
	 $.initWinOnTopFromLeft_qyjx("选择学校上架", '${ctp}/sys/applet/addSchoolApplet/'+'${applet.id}' , '600', '477');
});

//上架到全部学校
$('.sjdqbxx').click(function(){
	var appletName = "${applet.name }";
	$('.scts_sjdqbxx').children("p").children("span:first-child").text(appletName);
	var appletId = ${applet.id };
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
			 appletShangJia(appletId);
		  }
	});
});
//执行上架到全部学校
function appletShangJia(appletId){
	var loader = new loadLayer();
	loader.show();
	$.post("${ctp}/sys/applet/appletShangJia/" + appletId, {"_method" : "put"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("上架成功");
				window.location.reload();
			} else if ("fail" === data) {
				$.error("上架失败，系统异常", 1);
			}
		}
		loader.close();
	});
}


function back(){
	//window.history.back(-1);
	window.location.href = "${ctp}/sys/applet/index";
}

function search() {
	var val = {};
	var ids = ${applet.id };
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
	
	var id = "appletSchool_list_content";
	var url = "/sys/applet/appletManage/editor?sub=list&dm=${param.dm}&id=" +ids;
	myPagination(id, val, url);
}

function onlyUpAppelt(){
	//appletShangJia
	var appletId = ${applet.id };
	$.post("${ctp}/sys/applet/onlyUpAppelt/" + appletId, {"_method" : "put"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("上架成功");
				window.location.reload();
			} else if ("fail" === data) {
				$.error("上架失败，系统异常", 1);
			}
		}
	});
}

</script>
</body>
</html>