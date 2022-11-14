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
<title>班级信息管理</title>
<style>
.table thead tr th:first-child{
	padding-left: 33px;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">智核 >  年级信息管理  > 设置班级管理  > <span>新增班级管理者</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>新增班级管理者</p></div>
		</div>
		<div>
			<p class="banji_info_zt banji_info_bg_lan" id="one"><span><i>1</i></span>选择教师</p>
			<div class="banji_info_tk2_top">
				<span>请先选择教师：</span>
				<input type="text" disabled="disabled" id="teacherKuang">
				<button class="btn btn-blue" onclick="addBjController()">选择教师</button>
			</div>
		</div>
		<div style="border-top: solid 1px #e3e8ec;">
			<p class="banji_info_zt" id="two"><span><i>2</i></span>安排班级管理</p>
			<div class="banji_info_tk2_top">
				<span>请为教师安排管理的班级：</span>
			</div>
			<div class="banji_info_tk1">
			<ul>
				<li>
					<div class="div1">
						<span class="s1"><i class="jinzhi"></i>一年级</span>
						<p style="float:right" class="cz">
						<span class="cz01">展开<i class="zk"></i></span>
						<span class="cz02" style="display:none">收起<i class="sq"></i></span>
					</p>
				</div>
				<div class="div2">
					<p><i class="jinzhi"></i>1班</p>
					<p><i class="jinzhi"></i>1班</p>
					<p><i class="jinzhi"></i>1班</p>
					<div class="clear"></div>
				</div>
			</li>
			<li>
				<div class="div1">
					<span class="s1"><i class="jinzhi"></i>2年级</span>
					<p style="float:right" class="cz">
						<span class="cz01">展开<i class="zk"></i></span>
						<span class="cz02" style="display:none">收起<i class="sq"></i></span>
					</p>
				</div>
				<div class="div2">
					<p><i class="jinzhi"></i>1班</p>
					<p><i class="jinzhi"></i>2班</p>
					<p><i class="jinzhi"></i>3班</p>
					<div class="clear"></div>
				</div>
			</li>
			<li>
				<div class="div1">
					<span class="s1"><i class="jinzhi"></i>2年级</span>
					<p style="float:right" class="cz">
						<span class="cz01">展开<i class="zk"></i></span>
						<span class="cz02" style="display:none">收起<i class="sq"></i></span>
					</p>
				</div>
				<div class="div2">
					<p><i class="jinzhi"></i>1班</p>
					<p><i class="jinzhi"></i>2班</p>
					<p><i class="jinzhi"></i>3班</p>
					<div class="clear"></div>
				</div>
			</li>
			<li>
				<div class="div1">
					<span class="s1"><i class="jinzhi"></i>2年级</span>
					<p style="float:right" class="cz">
						<span class="cz01">展开<i class="zk"></i></span>
						<span class="cz02" style="display:none">收起<i class="sq"></i></span>
							</p>
						</div>
						<div class="div2">
							<p><i class="jinzhi"></i>1班</p>
							<p><i class="jinzhi"></i>2班</p>
							<p><i class="jinzhi"></i>3班</p>
							<div class="clear"></div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="btn_cz" style="margin-top: 30px;">
			<button class="btn-lightGray1" style="cursor: not-allowed;" id="ojbk1">确定</button>
			<button class="btn  btn-blue" style="display:none" id="ojbk2" onclick="final_operation()">确定</button>
		</div>
		
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>
<div class="addbjglz_tis" style="display:none;padding: 30px;">
	<p style="text-align: center;">您是否确定安排<span style="color: #2da1f8;" id="xsmd1"></span>教师管理</p>
	<p style="text-align: center;"><span id="xsmd"></span>总共<span id="banji_num"></span>个班级？</p>
	
</div>
<script>
$(".banji_info_tk1").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
$('body').on('click','.banji_info_top ul li',function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
})
$('body').on('click','.banji_info_list tbody a ',function(){
	$.initWinOnTopFromLeft_qyjx("班级管理", '${pageContext.request.contextPath}/views/demo/banji_info/b1_2.jsp', '600', '500');
})
function addBjController(){
	$.initWinOnTopFromLeft_qyjx("人员选择", '${pageContext.request.contextPath}/views/demo/banji_info/b1_3_2.jsp', '600', '630');
}
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
	if($('li .div2 i.ck.on').length>0){
		$('#ojbk1').hide();
		$('#ojbk2').show();
	}else{
		$('#ojbk1').show();
		$('#ojbk2').hide();
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
	 if( $('li .div2 i.ck.on').length>0){
		$('#ojbk1').hide();
		$('#ojbk2').show();
	}else{
		$('#ojbk1').show();
		$('#ojbk2').hide();
	} 
})
function final_operation(){
	$('#xsmd1').text($('#teacherKuang').val())
		var s1 ='';
		var qb1='';
		$('.banji_info_tk1 ul li').each(function(){
			
			if($(this).find('.div1 .ck').hasClass('on')){
				var nj = $(this).find('.div1 span.s1').text();
				 s1 = '全部班级';
				 qb1= qb1 + ('"'+ nj +'"'+s1)+" 、"; 
			}else {
				
				if($(this).find('.div2 .ck').hasClass('on')){
					
					var nj = $(this).find('.div1 span.s1').text();
					$(this).find('.div2 .on').each(function(){
						s1 = s1 +$(this).parent('p').text()+'、';
					})
					qb1 = qb1 + ('"'+ nj +'"'+ "<i style='color: #2da1f8;font-style: normal;'>"+s1+"</i>");
				}
				
			}
			$('#xsmd').html(qb1);
			var baj_num = $('.banji_info_tk1 ul li .div2 .on').length;
			$('#banji_num').text(baj_num);
			s1 = "";
			
		})
	
		layer.open({
			  type: 1,
			  shade:  [0.5, '#000'],
			  shadeClose : true,
			  //offset: '20px',
			  area: ['390px', '205px'],
			  title: '提示', //不显示标题
			  content: $('.addbjglz_tis'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
			  cancel: function(){
			    layer.close();
			  },
			  btn: ['确定','取消'],//按钮
			  btn1: function(index, layero){
			  }
		});
}
</script>
</body>
</html>