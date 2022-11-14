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
<title>学校应用管理</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="content_main white" style="border-radius: 0;">
	<div class="input_select">
		<div class="select_div">
			<%-- <span>主应用：</span>
			<select class="span2">
				<c:forEach items="${appList }" var ="app">
					<option value="${app.appKey}">${app.name }</option>
				</c:forEach>
			</select> --%>
		</div>
		
		<div class="select_div" style="float:left">
			<span>关键字：</span>
			<input type="text" id="apply_select">
		</div>
		<div class="btn_link" style="margin:5px 5px 0 0;">
			<button class="btn btn-blue" style="margin-top: 8px;" onclick="search_apply()">搜索</button>
		</div>
	</div>
	
</div>
<div class="add_apply" id="add_apply">
	<p class="p2"><i class="ck"></i>全选</p>
	<ul>
		<%-- <li>
			<span class="bg_color_orange"><img src="${pageContext.request.contextPath}/res/qyjx/images/app_icon.png"></span>
			<p class="aa_name">办公OA</p>
			<p class="p1"><i class="ck"></i></p>
		</li> --%>
		<c:forEach items="${appletVoList }" var ="appletVo">
			<li data-id="${appletVo.id }">
			<span class="bg_color_orange"><img src="${appletVo.iconUrl }"></span>
			<p class="aa_name">${appletVo.name }</p>
			<p class="p1"><i class="ck"></i></p>
			</li>
		</c:forEach>
	</ul>
</div>
<div class="btn_cz">
	<button class="btn btn-blue" onclick="downAppletOwner()">下架</button>
	<button class="btn btn-lightGray" onclick="quxiao()">取消</button>
</div>
<script>
$(".add_apply").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

$('body').on('click','.p1 i.ck',function(){
	 if($(this).hasClass('on')){
	        $(this).removeClass('on');
	        if( $('.p1 .ck').length!=$('.p1 .ck.on').length){
	     	   $('.p2 i.ck').removeClass('on');
	        }
	    }else{
	        $(this).addClass('on');
	        if($('.p1 i.ck').length==$('.p1 i.ck.on').length){
	        	$('.p2 i.ck').addClass('on');
	        }

	    }
})
$('body').on('click','.p2 i.ck',function(){ //p2全选
	 if($(this).hasClass('on')){
	        $(this).removeClass('on');
	        $('.ck').removeClass('on');
	    }else{
	        $(this).addClass('on');
	        $('.ck').addClass('on');
	    }
})

//搜索enter
$('body').on('keydown','#apply_select',function(event){
    if(event.keyCode==13){
    	search_apply();
    }
})

function quxiao(){
	$.closeWindow();
}

function downAppletOwner(){
	var ids = "";
	var val ={};
	var ckList = $("#add_apply .p1 .ck.on");
	ckList.each(function(index){
		var appletId = $(this).parent().parent().data("id");
		console.log(index+":"+appletId);
		if(index == 0){
			ids = ids + appletId;
		}else {
			ids = ids + "," + appletId;
		}
	});
	
	if(ids == ""){
		$.alert("请选择应用");
	}else {
		val.ownerId = '${schoolId}';
		val.appletIds=ids;
		var url = "${ctp}/sys/applet/school/downAppletOwner";
		$.post(url, val, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("操作成功");
					//parent.window.location.reload();
					//parent.core_iframe.window.search();
					if(parent.core_iframe != null) {
						parent.core_iframe.window.search();
					}else {
						parent.window.search();
					}
					$.closeWindow();
				} else if ("fail" === data) {
					$.error("操作失败，系统异常", 1);
				}
			}
		});
	}
}
function search_apply(){
	var apply_name=$('#apply_select').val();
	$(".add_apply ul li .aa_name").each(function(){
		var school_html=$(this).text();
		school_html = school_html.replace(/<span[^>]*>([^>]*)<\/span[^>]*>/ig,"$1");
 		$(this).html(school_html)
		if(school_html.indexOf(apply_name)!=-1){
			var reg = new RegExp("("+apply_name +")","ig");
			school_html = school_html.replace(reg,"<span style='color:red'>$1</span>");
			$(this).html(school_html);
			var aa = $(this).parents('li');
			$(this).parents('li').detach();
			$('.add_apply ul li:first-child').before(aa);
		}

	});
	$('.app_dright .app_list ul').each(function(index){
		if($(this).children('.pink').length==1){
			$('.app_drag .app_dright .a_top ul li').eq(index).children('a').addClass('hover1')
		}
	})
}
</script>
</body>
</html>