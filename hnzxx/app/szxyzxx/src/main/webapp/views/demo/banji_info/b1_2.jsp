<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<title>学校应用管理</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div  class="lsjgl">
	<span style="color:#2299ee">张大大</span>老师将管理：
	<div style="float:right">
		<button class="btn btn-red" style="margin-top: -4px;" onclick="emptyAll()">清空全部</button>
	</div>
</div>
	
<div class="banji_info_tk1">
	<ul>
		<li>
			<div class="div1">
				<span><i class="ck"></i>一年级</span>
				<p style="float:right" class="cz">
					<span class="cz01">展开<i class="zk"></i></span>
					<span class="cz02" style="display:none">收起<i class="sq"></i></span>
				</p>
			</div>
			<div class="div2">
				<p><i class="ck"></i>1班</p>
				<p><i class="ck"></i>1班</p>
				<p><i class="ck"></i>1班</p>
				<div class="clear"></div>
			</div>
		</li>
		<li>
			<div class="div1">
				<span><i class="ck"></i>2年级</span>
				<p style="float:right" class="cz">
					<span class="cz01">展开<i class="zk"></i></span>
					<span class="cz02" style="display:none">收起<i class="sq"></i></span>
				</p>
			</div>
			<div class="div2">
				<p><i class="ck"></i>1班</p>
				<p><i class="ck"></i>2班</p>
				<p><i class="ck"></i>3班</p>
				<div class="clear"></div>
			</div>
		</li>
		<li>
			<div class="div1">
				<span><i class="ck"></i>2年级</span>
				<p style="float:right" class="cz">
					<span class="cz01">展开<i class="zk"></i></span>
					<span class="cz02" style="display:none">收起<i class="sq"></i></span>
				</p>
			</div>
			<div class="div2">
				<p><i class="ck"></i>1班</p>
				<p><i class="ck"></i>2班</p>
				<p><i class="ck"></i>3班</p>
				<div class="clear"></div>
			</div>
		</li>
		<li>
			<div class="div1">
				<span><i class="ck"></i>2年级</span>
				<p style="float:right" class="cz">
					<span class="cz01">展开<i class="zk"></i></span>
					<span class="cz02" style="display:none">收起<i class="sq"></i></span>
				</p>
			</div>
			<div class="div2">
				<p><i class="ck"></i>1班</p>
				<p><i class="ck"></i>2班</p>
				<p><i class="ck"></i>3班</p>
				<div class="clear"></div>
			</div>
		</li>
	</ul>
</div>
<div class="btn_cz">
	<button class="btn btn-blue">添加</button>
	<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
</div>
<script>
$(".banji_info_tk1").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

$('body').on('click','.cz01',function(){
	$(this).hide();
	$(this).next().show();
	$(this).parents('.div1').next('.div2').slideDown();
	$(this).parents('.div1').css("border-bottom","solid 1px #e4e8eb");
})
$('body').on('click','.cz02',function(){
	$(this).hide();
	$(this).prev().show();
	$(this).parents('.div1').next('.div2').slideUp();
	$(this).parents('.div1').css("border-bottom","none");
})

 function emptyAll(){
	if($('.ck .on').length==0){
		layer.msg('未选择班级')
	}else{
		$('.ck').removeClass('on');
	}
	
} 
//单独选

$('body').on('click','li .div2 i.ck',function(){
	
	if($(this).hasClass('on')){
        $(this).removeClass('on');
       
        if( $(this).parents('.div2').find('.ck').length!=$(this).parents('.div2').find('.on').length){
     	   $(this).parents('.div2').prev().find('.ck').removeClass('on');
        }
    }else{
        $(this).addClass('on');
        if( $(this).parents('.div2').find('.ck').length==$(this).parents('.div2').find('.on').length){
      	   $(this).parents('.div2').prev().find('.ck').addClass('on');
         }
    }
})

//全选
$('body').on('click','li .div1 i.ck',function(){
	if($(this).hasClass('on')){
        $(this).removeClass('on');
        $(this).parents('.div1').next().find('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $(this).parents('.div1').next().find('.ck').addClass('on');
    }
})


</script>
</body>
</html>