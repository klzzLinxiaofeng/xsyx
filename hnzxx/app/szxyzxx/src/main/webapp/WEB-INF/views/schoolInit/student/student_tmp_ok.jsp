<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.layui-layer-iframe .layui-layer-btn, .layui-layer-page .layui-layer-btn {
    padding-top: 0px;
}
</style>
<table class="table">
	<thead>
		<tr>
			<th><i class="ck" style="top: -11px;"></i></th>
			<th>序号</th>
			<th>班内学号</th>
			<th>学生姓名</th>
			<th>年级</th>
			<th>班级</th>
			<th>监护人</th>
			<th>监护人手机</th>
			<th class="caozuo">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${result }" var="student">
			<tr>
				<td><i class="${student.delete?'ck':'jinzhi' }" studentid="${student.id }"></i></td>
				<td>${student.id }</td>
				<td>${student.num }</td>
				<td>${student.name }</td>
				<td>${student.grade }</td>
				<td>${student.team }</td>
				<td>${student.guardian }</td>
				<td>${student.guardianPhone }</td>
				<td class="caozuo">
					<button class="btn btn-green"  onclick="editStudent(this, ${student.id })">编辑</button>
					<c:if test="${student.delete }">
						<button class="btn btn-red" onclick="deleteTmp(${student.id });">删除</button>
					</c:if>
					<c:if test="${!student.delete }">
						<button class="btn btn-lightGray">删除</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="tis_edit" id="spi_edit" style=" margin-right: 0;margin-left: 18px;margin-top: 20px;display:none;">
	<form id="signupForm" class="form-horizontal" method="get" action="">
		<input type="hidden" id="sid">
		<input type="hidden" id="tmpid">
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
		<div class="select_div bkbj" style="margin-bottom: 0;">
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
<script>
//手机号码验证  
jQuery.validator.addMethod("isMobile", function(value, element) {  
	var length = value.length;  
	var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
	return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");  

function editStudent(obj, id){
	var trs = $(obj).parent(".caozuo").siblings();
	$("#sid").val(id);
	$("#tmpid").val(trs.eq(1).text());
	$("#num").val(trs.eq(2).text());
	$("#name").val(trs.eq(3).text());
	$("#grade").val(trs.eq(4).text());
	$("#team").val(trs.eq(5).text());
	$("#guardian").val(trs.eq(6).text());
	$("#old_phone1").val(trs.eq(7).text());
	
	layer.open({
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
	var id = $("#tmpid").val();
	var studentId = $("#sid").val();
    var oldMobile = $(obj).parent(".caozuo").siblings().eq(7).text();
	var guardian = $("#guardian").val();
	var guardianPhone = $("#old_phone1").val();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/student/updateReal",
        type: "POST",
        data: {"tmpId":id, "name":guardian, "newMobile":guardianPhone,
        	"studentId":studentId, "oldMobile":oldMobile},
        async: true,
        success: function(result) {
        	if("SUCCESS"==result) {
       			$(window.parent.document).find("#spi_daoru_ok").click();
               	$.closeWindow();
       		} else {
       			$('.edit_phone').after("<label class='error1' style='margin-left: 89px;'>"  + "手机号码重复" + '</label>');
       			//alert("手机号重复");
       		}
        }
    });
}

function deleteTmp(id) {
	var ids = new Array();
	ids[0] = id;
	
	var loader = new loadDialog();
    loader.show();
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/student/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	loader.close();
        	$.success("删除成功 !");
        	$(window.parent.document).find("#spi_daoru_ok").click();
        }
    });
}

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
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '4px',
		  area: ['418px', '300px'],
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

function edit() {
	layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '4px',
		  area: ['400px', '430px'],
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