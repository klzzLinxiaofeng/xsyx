<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>学生信息导出</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="student_export">
	<div class="student_export_top">
		<div class="f_left">
			<ul>
				<li class="on">小学</li>
				<li>初中</li>
				<li>高中</li>
			</ul>
		</div>
		<div class="f_right">
			<button class="btn btn-blue export">导出</button>
		</div>
		<div class="clear"></div>
	</div>
	<div class="nj_content">
		<div class="nj_list">
			<p class="f_left"><i class="ck all_choose"></i>一年级</p>
			<div class="bj">
				<span><i class="ck"></i>1班</span>
				<span><i class="ck"></i>2班</span>
				<span><i class="ck"></i>3班</span>
				<span><i class="ck"></i>4班</span>
				<span><i class="ck"></i>5班</span>
				<div class="clear"></div>
			</div>
		</div>
		<div class="nj_list">
			<p class="f_left"><i class="ck all_choose"></i>二年级</p>
			<div class="bj">
				<span><i class="ck"></i>1班</span>
				<span><i class="ck"></i>2班</span>
				<span><i class="ck"></i>3班</span>
				<span><i class="ck"></i>4班</span>
				<span><i class="ck"></i>5班</span>
				<span><i class="ck"></i>6班</span>
				<span><i class="ck"></i>7班</span>
				<span><i class="ck"></i>8班</span>
				<span><i class="ck"></i>9班</span>
				<span><i class="ck"></i>10班</span>
				<div class="clear"></div>
			</div>
		</div>
		<div class="nj_list">
			<p class="f_left"><i class="ck all_choose"></i>三年级</p>
			<div class="bj">
				<span><i class="ck"></i>1班</span>
				<span><i class="ck"></i>2班</span>
				<span><i class="ck"></i>3班</span>
				<span><i class="ck"></i>4班</span>
				<span><i class="ck"></i>5班</span>
				<span><i class="ck"></i>6班</span>
				<span><i class="ck"></i>7班</span>
				<span><i class="ck"></i>8班</span>
				<span><i class="ck"></i>9班</span>
				<span><i class="ck"></i>10班</span>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="nj_content" style="display: none;">
		<div class="nj_list">
			<p class="f_left"><i class="ck all_choose"></i>初一</p>
			<div class="bj">
				<span><i class="ck"></i>1班</span>
				<span><i class="ck"></i>2班</span>
				<span><i class="ck"></i>3班</span>
				<span><i class="ck"></i>4班</span>
				<span><i class="ck"></i>5班</span>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="nj_content" style="display: none;">
		<div class="nj_list">
			<p class="f_left"><i class="ck all_choose"></i>高一</p>
			<div class="bj">
				<span><i class="ck"></i>1班</span>
				<span><i class="ck"></i>2班</span>
				<span><i class="ck"></i>3班</span>
				<span><i class="ck"></i>4班</span>
				<span><i class="ck"></i>5班</span>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>
<div class="export_student_message" style="display:none;padding: 30px;">
		<p>已选择<span style="color: #2da1f8;" id="xsmd"></span>的学生名单</p>
		<p style="margin-bottom: 10px;">请选择导出内容：</p>
		<div class="dcnr">
			<span><i class="ck on"></i>年级</span>
			<span><i class="ck on"></i>学生账号</span>
			<span><i class="ck on"></i>家长登录账号</span>
			<span><i class="ck on"></i>班级</span>
			<span><i class="ck on"></i>班内学号</span>
			<span><i class="ck on"></i>家长手机号码</span>
			<span><i class="ck bnd on" style="cursor: not-allowed;"></i>学生姓名<b style="color:red;">*</b></span>
			<span><i class="ck on"></i>家长姓名</span>
			<div class="clear"></div>
		</div>
		<p style="color:#ff5252;margin-top: 10px;">备注：“学生姓名”必选</p>
	</div>
<script>
$('.student_export_top ul li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	$('.nj_content').hide();
	$('.nj_content').eq($(this).index()).show();
})
$('body').on('click','.nj_list .bj i.ck',function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       if(  $(this).parents('.bj').find('i.ck').length!=$(this).parents('.bj').find('i.ck.on').length){
    	   $(this).parents('.bj').prev('.f_left').children('.all_choose').removeClass('on');
       }
    }else{
        $(this).addClass('on');
        if( $(this).parents('.bj').find('i.ck').length==$(this).parents('.bj').find('i.ck.on').length){
        	 $(this).parents('.bj').prev('.f_left').children('.all_choose').addClass('on');
        }
    }
});
$('body').on('click','.all_choose',function(){
	if($(this).hasClass('on')){
        $(this).removeClass('on');
        $(this).parents().next('.bj').find('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $(this).parents().next('.bj').find('.ck').addClass('on');
    }
})
$('.export_student_message .dcnr .ck').click(function(){
	if(!$(this).hasClass('bnd')){
		if($(this).hasClass('on')){
	        $(this).removeClass('on');
	    }else{
	        $(this).addClass('on');
	    }
	}
})
$('.export').click(function(){
	if($('.nj_content .nj_list .ck').hasClass('on')){
		var s1 ='';
		var qb1='';
		$('.nj_content .nj_list').each(function(){
			
			if($(this).find('.all_choose').hasClass('on')){
				var nj = $(this).find('.f_left').text();
				 s1 = '全部班级';
				 qb1= qb1 + ('"'+ nj +'"'+s1)+" 、"; 
			}else {
				
				if($(this).find('.ck').hasClass('on')){
					
					var nj = $(this).find('.f_left').text();
					$(this).find('.bj .on').each(function(){
						s1 = s1 + $(this).parent('span').text()+'、';
					})
					qb1 = qb1 + ('"'+ nj +'"'+s1);
				}
				
			}
			$('#xsmd').text(qb1);
			s1 = "";
			
		})
	
		layer.open({
			  type: 1,
			  shade:  [0.5, '#000'],
			  shadeClose : true,
			  offset: '20px',
			  area: ['440px', '367px'],
			  title: '导出学生信息', //不显示标题
			  content: $('.export_student_message'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  btn: ['确定导出'],//按钮
			  btn1: function(index, layero){
			  }
		});
	}else{
		layer.msg('请选择班级');
	}
	
});

</script>
</body>
</html>