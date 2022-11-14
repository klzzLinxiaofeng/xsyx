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
.chzn-container-single  .chzn-single{
	border: 1px solid #d4d4d4;
}
.layui-layer-iframe .layui-layer-btn, .layui-layer-page .layui-layer-btn {
    padding-top: 0px;
}
</style>
</head>
<body style="background-color: #e3e3e3;">
	<div class="sjcsh_xx_detail">
		<div class="content_main" style="padding: 20px 0 40px;" border-radius: 0;>
		 	<div style="padding: 0 20px;">
		 		<div>
		 			<div class='f_left'>
		 				<div style="display: none;">
							<select id="xn" name="xn" class="span2" style="height: 34px;"></select>
						</div>
						<select id="nj" name="nj" class="span2" style="height: 34px;"></select>
						<select id="bj" name="bj" class="span2"  style="height: 34px;"></select>
						
					</div>
					<div class='f_left' style="margin-left: 5px;">
						<input type="text" id="checkName" placeholder="请输入关键字" style="height: 17px;">
						<button class="btn btn-blue" style="margin-top: -10px;" onclick="search()">检索</button>
					</div>
					
		 		</div>
		 		<div class="f_right">
		 			<button class="btn btn-blue" onclick="createStudent()">新建</button>
					<button class="btn btn-blue" onclick="tuisong()">手动推送到海康</button>
					<button class="btn btn-blue" onclick="tuisong2()">推送班级到海康</button>
		 			<button class="btn btn-lightGray" onclick="batchDelete()">批量删除</button>
		 		</div>
		 		<div class="clear"></div>
		 	</div>
			<div id="student_list">
				<jsp:include page="./student_manage_list.jsp" />
			</div>
			<div class="page_div">
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="student_list" />
					<jsp:param name="url" value="/student/init/list?index=list" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<div class="tis_edit" id="spi_edit" style=" margin-right: 0;margin-left: 18px;margin-top: 20px;display:none;">
		<form id="signupForm" class="form-horizontal" method="get" action="">
			<input type="hidden" id="sid">
			<input type="hidden" id="org_phone">
			<div class="select_div bkbj">
				<span>班内学号：</span>
				<input type="text" disabled="disabled" id="num">
			</div>
			<div class="select_div bkbj">
				<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
				<input type="text" id="name" name="name" disabled="disabled">
			</div>
			<div class="select_div bkbj">
				<span><i style="font-weight: normal;color:red">*</i>年级：</span>
				<input type="text" id="grade" name="grade" disabled="disabled">
			</div>
			<div class="select_div bkbj">
				<span><i style="font-weight: normal;color:red">*</i>班级：</span>
				<input type="text" id="team" name="team" disabled="disabled">
			</div>
			<div class="select_div">
				<span>监护人：</span>
				<input type="text" id="guardian">
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
				<p style="margin-left: 17px;">该家长的登录账号将为新手机号码</p>
			</div>
		</form>
	</div>
</body>
<script>
//手机号码验证  
jQuery.validator.addMethod("isMobile", function(value, element) {  
	var length = value.length;  
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
	return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");  

$(function() {
	$.initCascadeSelector({
		"type" : "team",
		"gradeFirstOptTitle" : "年级",
		"teamFirstOptTitle" : "班级",
	});
})

function search() {
	var url = "/student/init/list?index=list";
    var data = {"gradeId": $("#nj").val(),
    			"teamId": $("#bj").val(),
    			"name": $("#checkName").val(),};
    myPagination("student_list", data, url);
}

function tuisong() {
	$.ajax({
		url: "${pageContext.request.contextPath}/hikvison/hikvisonTeacher",
		type: "POST",
		data: {"type": 0,"kaishi":0,"jieshu":500},
		async: true,
		success: function(result) {
			alert(result);
			$.success("推送成功!");
			//search();
		}
	});
}
function tuisong2() {
	$.ajax({
		url: "${pageContext.request.contextPath}/hikvison/hikTeam",
		type: "GET",
		async: true,
		success: function(result) {
			alert(result);
			$.success("推送班级成功!");
			//search();
		}
	});
}

function createStudent(){
	$.initWinHasCloseCallback("新建学生",  '${pageContext.request.contextPath}/student/init/add', '458', '429', function() {
		if(getCookie("close_tab")=="close") {
			document.cookie="close_tab=open";
			var url = "/student/init/list"; 
			myPagination("student_list", {"index":"list"}, url);
		}
	});
}

function editStudent(obj, id){
	$('label.error1').remove();
	var trs = $(obj).parent(".caozuo").siblings();
	$("#sid").val(id);
	$("#num").val(trs.eq(2).text());
	$("#name").val(trs.eq(4).text());
	$("#grade").val(trs.eq(5).text());
	$("#team").val(trs.eq(6).text());
	$("#guardian").val(trs.eq(7).text());
	$("#old_phone1").val(trs.eq(8).text());
	$("#org_phone").val(trs.eq(7).text());
 	
	 a1 =layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '4px',
		  area: ['418px', '430px'],
		  title: '编辑学生', //不显示标题
		  content: $('#spi_edit'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存'],//按钮
		  yes: function(index, layero){
			  updateStudent();
		  }
	});
}

function updateStudent() {
	$('label.error1').remove();
	var id = $("#sid").val();
    var oldMobile = $("#org_phone").val();
	var guardian = $("#guardian").val();
	var guardianPhone = $("#old_phone1").val();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/student/init/update",
        type: "POST",
        data: {"name":guardian, "newMobile":guardianPhone, "id":id, "oldMobile":oldMobile},
        async: true,
        success: function(result) {
       		if("SUCCESS"==result) {
               	$.closeWindow();
               	layer.close(a1);
               	layer.close(a2);
               	search()
               //	$(window.parent.document).find("#student_parents_manage").click();
       		} else if("FAIL"==result){
       			$('.edit_phone').after("<label class='error1' style='margin-left: 84px;'>"  + "不允许添加新的家长" + '</label>');
       		} else {
       			$('.edit_phone').after("<label class='error1' style='margin-left: 84px;'>"  + "手机号重复" + '</label>');
       			//alert("手机号重复");
       		}
        }
    });
}

function batchDelete() {
	var ids = new Array();
	
	$("#student_list .table .on").each(function() {
		var teacherid = $(this).attr("studentid");
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
        url: "${pageContext.request.contextPath}/student/init/batchDelete",
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

function deleteStudent(id) {
	var loader = new loadDialog();
    loader.show();
    
	var ids = new Array();
	ids[0] = id;
	$.ajax({
        url: "${pageContext.request.contextPath}/student/init/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	loader.close();
        	$.success("删除成功 !");
        	search();
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

$('.edit_phone').click(function(){
	$('label.error1').remove();
	var i =0;
	layer.closeAll(); 
	$('#old_phone2').val($('#old_phone1').val());
	$('#new_phone').val('');
	
	layer.open({
		  type: 1,
		   offset: '4px',
		   shade:  [0.01, '#fff'],
		   shadeClose : true,
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

function openLayer() {
	 a2 = layer.open({
		  type: 1,
		   offset: '4px',
		   shade:  [0.01, '#fff'],
		   shadeClose : true,
		  area: ['418px', '430px'],
		  title: '编辑学生', //不显示标题
		  content: $('#spi_edit'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['保存'],//按钮
		  yes: function(index, layero){
			  updateStudent();
		  }
	});
}
</script>
</html>