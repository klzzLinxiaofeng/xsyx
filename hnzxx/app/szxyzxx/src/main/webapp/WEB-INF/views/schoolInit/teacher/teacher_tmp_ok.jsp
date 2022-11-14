<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.new-phone label.error{
	margin-left: 94px;
    margin-top: 5px;
}
.bkbj input{
	background-color: #E1E1E1;
}
</style>
<table class="table">
	<thead>
		<tr>
			<th><i class="ck" style="top: -11px;"></i></th>
			<th>序号</th>
			<th>名称</th>
			<th>性别</th>
			<th>别名</th>
			<th>手机号码</th>
			<th>部门</th>
			<th>职务</th>
			<th class="caozuo">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${result }" var="student">
			<tr>
				<td><i class="${student.delete?'ck':'jinzhi' }" teacherid="${student.id }"></i></td>
				<td>${student.id }</td>
				<td>${student.name }</td>
				<td>${student.sex }</td>
				<td>${student.alias }</td>
				<td>${student.phone }</td>
				<td>${student.department }</td>
				<td>${student.position }</td>
				<td class="caozuo">
					<button class="btn btn-green"  onclick="editTeacher(this, ${student.teacherId})">编辑</button>
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

<div class="tis_edit" id="tis_edit" style="display:none">
	<form id="signupForm" class="form-horizontal">
		<input type="hidden" id="tid">
		<input type="hidden" id="tmpid">
		<div class="select_div bkbj">
			<span><i style="font-weight: normal;color:red">*</i>姓名：</span>
			<input type="text" id="name" name="name" disabled="disabled">
		</div>
		<div class="select_div">
			<span>别名：</span>
			<input type="text" id="alias" name="alias">
		</div>
		<div class="select_div">
			<span>性别：</span>
			<input type="radio" name="sex" value="男">男
			<input type="radio" name="sex" style="margin-left: 50px;" value="女">女
		</div>
		<div class="select_div bkbj">
			<span><i style="font-weight: normal;color:red">*</i>手机号码：</span>
			<input type="text" id="old_phone1" name="old_phone1" disabled="disabled" value="13729887897"><a class="btn btn-green edit_phone" href="javascript:void(0)">修改</a>
		</div>
	</form>
</div>
<div class="edit_phone1 tis_edit" style="display:none">
	<form id="cell"  id="cell" class="form-horizontal" method="get" action="">
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
//手机号码验证  
jQuery.validator.addMethod("isMobile", function(value, element) {  
var length = value.length;  
var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;  
return this.optional(element) || (length == 11 && mobile.test(value));  
}, "请正确填写手机号码");  

function editTeacher(obj, teacherId){
	$('#tis_edit label.error1').remove();
	var tds = $(obj).parent(".caozuo").siblings();
	$("#tid").val(teacherId);
	$("#tmpid").val(tds.eq(1).text());
	$("#name").val(tds.eq(2).text());
	$("#alias").val(tds.eq(4).text());
	$("#old_phone1").val(tds.eq(5).text());
	var sex = tds.eq(3).text();
	if(sex=="男") {
		$("input[name='sex']:eq(0)").attr("checked","checked");
	} else if(sex=="女") {
		$("input[name='sex']:eq(1)").attr("checked","checked");
	}
	layer.open({
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
	var id = $("#tmpid").val();
	var teacherId = $("#tid").val();
	var teachername = $("#name").val();
	var sex = $("input[name='sex']:checked").val();
	var phone = $("#old_phone1").val();
	var alias = $("#alias").val();
	
	var label1 = " <label class='error1'>";
	var label2 = "</label>"; 
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/teacher/updateReal",
        type: "POST",
        data: {"id":id, "name":teachername, "sex":sex, "phone":phone,
        	"teacherId":teacherId, "alias":alias},
        async: true,
        success: function(result) {
        	if(result=="SUCCESS") {
        		$(window.parent.document).find("#tii_daoru_ok").click();
	        	$.closeWindow();
        	} else if(result="PHONE_REPEAT") {
        		$('.edit_phone').after("<label class='error1' style='margin-left: 84px;'>"  + "电话号码重复" + '</label>');
        		//alert("电话号码重复");
        	} else if(result="NAME_REPEAT") {
        		alert("姓名重复，无别名");
        	} else if(result="ALIAS_REPEAT") {
        		$('input[name="alias"]').after(label1 + "别名已经存在" + label2)
        		//alert("别名已经存在");
        	}
        }
    });
}

function deleteTmp(id) {
	var loader = new loadDialog();
    loader.show();
    
	var ids = new Array();
	ids[0] = id;
	
	$.ajax({
        url: "${pageContext.request.contextPath}/tmp/teacher/batchDelete",
        type: "POST",
        data: {"ids": JSON.stringify(ids)},
        async: true,
        success: function(result) {
        	loader.close();
        	$.success("删除成功 !");
        	$(window.parent.document).find("#tii_daoru_ok").click();
        }
    });
}

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
			  if(i!=0) {
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
	 layer.open({
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
</script>