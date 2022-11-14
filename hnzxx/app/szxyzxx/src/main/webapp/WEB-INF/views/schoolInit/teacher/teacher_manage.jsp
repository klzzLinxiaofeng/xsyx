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
	<div class=" content_main" style="padding: 20px 0 0px;" border-radius: 0;>
	 	<div style="padding: 0 20px;">
	 		<div class='f_left'>
	 			<div class="select_div">
					<input type="text" placeholder="请输入关键字" id="name">
					<button class="btn btn-blue" style="margin-top: -6px;" onclick="search();">检索</button>
				</div>
	 		</div>
	 		<div class="f_right">
	 			<button class="btn btn-blue" onclick="newTeacher()">新建</button>
	 			<button class="btn btn-lightGray" onclick="batchDelete();">批量删除</button>
	 		</div>
	 		<div class="clear"></div>
	 	</div>
	 	<div id="teacher_list">
	 		<jsp:include page="./teacher_manage_list.jsp"></jsp:include>
	 	</div>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="teacher_list" />
				<jsp:param name="url" value="/teacher/init/list?index=list" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
	</div>
	<div class="tis_edit" id="tis_edit" style="display:none">
		<form id="signupForm" class="form-horizontal" method="get" action="">
			<input type="hidden" id="tid">
			<div class="select_div bkbj">
				<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
				<input type="text" id="tname" name="name" disabled="disabled">
			</div>
			<div class="select_div">
				<span>别名：</span>
				<input type="text" id="alias" name="alias">
			</div>
			<div class="select_div">
				<span>性别：</span>
				<input type="radio" name="sex" value="1">男
				<input type="radio" name="sex" style="margin-left: 50px;" value="2">女
			</div>
			<div class="select_div bkbj">
				<span><i style="font-weight: normal;color:red">*</i>手机号码：</span>
				<input type="text" id="old_phone1" name="old_phone1" disabled="disabled"><a class="btn btn-green edit_phone" href="javascript:void(0)">修改</a>
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
</body>
<script>
function search() {
	var url = "/teacher/init/list?index=list";
    var data = {"name": $("#name").val()};
    myPagination("teacher_list", data, url);
}

function newTeacher(){
	$.initWinHasCloseCallback("新建教师信息",  '${pageContext.request.contextPath}/teacher/init/add', '458', '453', function() {
		if(getCookie("close_tab")=="close") {
			document.cookie="close_tab=open";
			var url = "/teacher/init/list"; 
			myPagination("teacher_list", {"index":"list"}, url);
		}
	});	
}

function batchDelete() {
	var ids = new Array();
	$(".table .on").each(function() {
		var teacherid = $(this).attr("teacherid");
		if(typeof(teacherid)!="undefined") {
			ids.push(teacherid);
		}
	});
	
	if(ids.length==0) {
		return;
	}
	
	var loader = new loadDialog();
    loader.show();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/teacher/init/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	loader.close();
        	$.success("删除成功!");
        	search();
        }
    });
}

function deleteTeacher(id) {
	var ids = new Array();
	ids[0] = id;
	$.ajax({
        url: "${pageContext.request.contextPath}/teacher/init/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	$.success("删除成功 !");
        	search();
        }
    });
}

function editTeacher(obj, teacherId){
	$('#tis_edit label.error1').remove();
	var tds = $(obj).parent(".caozuo").siblings();
	$("#tid").val(teacherId);
	$("#tname").val(tds.eq(2).text());
	$("#alias").val(tds.eq(4).text());
	$("#old_phone1").val(tds.eq(5).text());
	var sex = tds.eq(3).text();
	if(sex=="男") {
		$("input[name='sex']:eq(0)").attr("checked","checked");
	} else if(sex=="女") {
		$("input[name='sex']:eq(1)").attr("checked","checked");
	}
	
	_index2 = layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '4px',
		  area: ['418px', '339px'],
		  title: '编辑教师信息', //不显示标题
		  content: $('#tis_edit'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存'],//按钮
		  yes: function(index, layero){
			  updateTeacher();
		  }
	});
}

function updateTeacher() {
	$('#tis_edit label.error1').remove();
	var id = $("#tid").val();
	var teachername = $("#tname").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#old_phone1").val();
	var alias = $("#alias").val();
	
	var label1 = " <label class='error1'>";
	var label2 = "</label>"; 
	
	$.ajax({
        url: "${pageContext.request.contextPath}/teacher/init/update",
        type: "POST",
        data: {"id":id, "name":teachername, "sex":sex, "mobile":phone, "alias":alias},
        async: true,
        success: function(result) {
        	if(result=="SUCCESS") {
        		layer.close(_index1);
        		layer.close(_index2);
        		search();
        	} else if(result=="PHONE_REPEAT") {
        		$('.edit_phone').after("<label class='error1' style='margin-left: 84px;'>"  + "电话号码重复" + '</label>');
        		//alert("电话号码重复");
        	} else if(result=="NAME_REPEAT") {
        		alert("姓名重复，无别名");
        	} else if(result=="ALIAS_REPEAT") {
        		$('input[name="alias"]').after(label1 + "别名已经存在" + label2)
        		//alert("别名已经存在");
        	}
        }
    });
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
})
$('body').on('click','.table thead i.ck',function(){
	if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
})

function phoneValidate(){
	return $("#cell").validate({
		    rules: {
		    	new_phone : {  
		    		required : true,
		        	digits:true,
		            rangelength:[11,11]
		        		}, 
		        },
		   messages: {
			   new_phone : {  
				   required : "请输入手机号", 
			       digits:"请输入数字",
			       rangelength:"请输入正确号码"
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
	$('#tis_edit label.error1').remove();
	var i =0;
	layer.closeAll(); 
	$('#old_phone2').val($('#old_phone1').val());
	$('#new_phone').val('');
	
	layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '4px',
		  area: ['400px', '300px'],
		  title: '修改 - 手机号码', //不显示标题
		  content: $('.edit_phone1'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  yes: function(index, layero){
			  i++;
			  if(phoneValidate().form()){
				  layer.closeAll(); 
				  $('#old_phone1').val($('#new_phone').val());
				  openLayer();
			  }
		  },
		  btn2:function(index,layero){
			  if(i!=0){
				  $(".new-phone label.error").remove();//清除验证标签
					$("#cell").validate().resetForm(); //清除验证
			  }
			  i++;
			  openLayer();
		  }
	});
	$('#new_phone').focus();
});
var _index1;
function openLayer() {
	_index1=layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '4px',
		  area: ['418px', '339px'],
		  title: '编辑教师信息', //不显示标题
		  content: $('#tis_edit'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存'],//按钮
		  yes: function(index, layero){
			  updateTeacher();
		  }
	});
}
</script>
</html>