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
<title></title>
<style>
.tis_edit span {
    width: 86px;
}
.new-phone label.error{
	margin-left: 94px;
    margin-top: 5px;
}
.bkbj input{
	background-color: #E1E1E1;
}
</style>
</head>
<body style="background-color: #e3e3e3;"">
	<div class="sjcsh_xx_detail">
	<div class=" content_main" style="padding: 20px 0 40px;" border-radius: 0;>
	 	<div style="padding: 0 20px;">
	 		<div class='f_left'>
	 			<div class="select_div">
					<select class="span2" style="height: 34px;"><option>年级</option></select>
					<select class="span2"  style="height: 34px;"><option>班级</option></select>
					<input type="text" placeholder="请输入关键字">
					<button class="btn btn-blue" style="margin-top: -6px;">检索</button>
				</div>
	 		</div>
	 		<div class="f_right">
	 			<button class="btn btn-blue" onclick="newTeacher()">新建</button>
	 			<button class="btn btn-lightGray" >批量删除</button>
	 		</div>
	 		<div class="clear"></div>
	 	</div>
		<table class="table" style="border-top: 1px solid #dddddd;">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>名称</th><th>性别</th><th>别名</th><th>手机号码</th><th>部门</th><th>职务</th><th>编制</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td><i class="ck"></i></td><td>1</td><td>王小明</td><td>男</td><td>小明</td><td>16757837676</td><td></td><td></td><td>在编</td><td class="caozuo"><button class="btn btn-green" onclick="edit()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>2</td><td>李想</td><td>女</td><td>想想</td><td>16757837676</td><td>艺术部</td><td></td><td>在编</td><td class="caozuo"><button class="btn btn-green" onclick="edit()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>3</td><td>刘大旭</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="caozuo"><button class="btn btn-green" onclick="edit()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>4</td><td>张不但</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="caozuo"><button class="btn btn-green" onclick="edit()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>5</td><td>吴晓婷</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="caozuo"><button class="btn btn-green" onclick="edit()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>6</td><td>秋衣</td><td></td><td></td><td>16757837676</td><td></td><td></td><td>在编</td><td class="caozuo"><button class="btn btn-green" onclick="edit()">编辑</button><button class="btn btn-red">删除</button></td></tr>
			</tbody>
		</table>
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
<div class="tis_edit" id="spi_edit" style=" margin-right: 0;margin-left: 18px;display:none;">
<form id="signupForm" class="form-horizontal" method="get" action="">
	<div class="select_div bkbj">
		<span>班内学号：</span>
		<input type="text" disabled="disabled">
	</div>
	<div class="select_div bkbj">
		<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
		<input type="text" id="studentname" name="studentname" disabled="disabled">
	</div>
	<div class="select_div bkbj">
		<span><i style="font-weight: normal;color:red">*</i>年级：</span>
		<input type="text" id="nianji" name="nianji" disabled="disabled">
	</div>
	<div class="select_div bkbj">
		<span><i style="font-weight: normal;color:red">*</i>班级：</span>
		<input type="text" id="banji" name="banji" disabled="disabled">
	</div>
	<div class="select_div">
		<span>监护人：</span>
		<input type="text">
	</div>
	<div class="select_div bkbj">
		<span>监护人手机：</span>
		<input type="text" id="old_phone1" name="old_phone1" disabled="disabled" value="13729887897"><a class="btn btn-green edit_phone" href="javascript:void(0)">修改</a>
	</div>
</form>
</div>
<div class="edit_phone1 tis_edit" style="display:none">
<form  id="cell" class="form-horizontal" method="get" action="">
	<div class="select_div bkbj">
		<span style="width:86px;">原手机号码：</span>
		<input type="text" disabled="disabled" id="old_phone2">
	</div>
	<div class="select_div new-phone" style="margin-bottom: 0;">
		<span  style="width:86px;">新手机号码：</span>
		<input type="text" id="new_phone" name="new_phone" autofocus="autofocus">
	</div>
	<div style="color:red;text-align: center;">
		<p style="margin-right: 54px;">备注：新手机号码将替代原手机号码</p>
		<p style="margin-left: 17px;">该教师的登录账号将为新手机号码</p>
	</div>
	</form>
</div>
<script>
function newTeacher(){
	$.initWinOnTopFromLeft_qyjx("新建学生",  '${pageContext.request.contextPath}/views/demo/new_school/spi_edit.jsp', '458', '429');	
}
function edit(){
	/* $.initWinOnTopFromLeft_qyjx("编辑学生",  '${pageContext.request.contextPath}/views/demo/new_school/spi_edit.jsp', '458', '429'); */
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['400px', '429px'],
		  title: '编辑学生', //不显示标题
		  content: $('#spi_edit'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
}
$('.table tbody i.ck').click(function(){
	
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
function phoneValidate(){
	return $("#cell").validate({
		    rules: {
		    	new_phone : {  
		            required : true,  
		            minlength : 11, 
		            isMobile : true  
		        		}, 
		          },
		   messages: {
			   new_phone : {  
			       required : "请输入手机号",  
			       minlength : "不能小于11个字符",  
			       isMobile : "请正确填写手机号码"  
			  		 	}
	  				 },
			});
}
//手机号码验证  
jQuery.validator.addMethod("isMobile", function(value, element) {  
 var length = value.length;  
 var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
 return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");  


$('.edit_phone').click(function(){
	var i =0;
	layer.closeAll(); 
	$('#old_phone2').val($('#old_phone1').val());
	$('#new_phone').val('');
	
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['400px', '300px'],
		  title: '修改 - 手机号码', //不显示标题
		  content: $('.edit_phone1'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  yes: function(index, layero){
			  i++;
			  $(phoneValidate());  
			  if(phoneValidate().form()){
				  layer.closeAll(); 
				  $('#old_phone1').val($('#new_phone').val());
				 edit();
			  }
		  },
		  btn2:function(index,layero){
			  if(i!=0){
				  $(".new-phone label.error").remove();//清除验证标签
					$("#cell").validate().resetForm(); //清除验证
			  }
			  i++;
			 edit();
		  }
	});
	$('#new_phone').focus();
});


</script>
</body>
</html>