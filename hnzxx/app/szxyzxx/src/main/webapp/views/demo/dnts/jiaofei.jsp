<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>缴费</title>
<style>
.control-group{width:40%;float:left;}
.form-horizontal .control-label{width:60px;}
.control-group .controls{margin-left:80px;}
.content-widgets{padding:10px 40px;}
table input{width:90%;}
.table th, .table td{text-align:center;}
</style>
<script>
$(function(){
	$(".add_money").keyup(function(event){
        var keycode = event.which;
           if (keycode != 37&&keycode != 38&&keycode != 39&&keycode != 40) {
             //匹配负号，数字
             this.value = this.value.replace(/[^\d.]/g, "");
           //匹配第一个输入的字符不是小数点
           this.value = this.value.replace(/^\./g, "");
         //只能输入两个小数   
           this.value = this.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');
           //保证.-只出现一次，而不能出现两次以上
           this.value = this.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
           this.value = this.value.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
           //保证-号只能是第一位
           }
  });

})
</script>
</head>
<body>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);">
							<div>
							<div class="control-group">
								<label class="control-label">年级：</label>
								<div class="controls">
									<select class="span11"><option>一年级</option><option>二年级</option><option>三年级</option></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">班级：</label>
								<div class="controls">
									<select class="span11"><option>一班</option><option>二班</option><option>三班</option></select>
								</div>
							</div>
							<button class="btn btn-primary">搜索</button>
							</div>
							<table class="responsive table table-striped table-bordered">
								<thead>
									<tr><th>姓名</th><th>性别</th><th>学费</th><th>住宿费</th><th>班费</th><th>书本费</th></tr>
								</thead>
								<tbody>
									<tr><td>刘涛</td><td>男</td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td></tr>
									<tr><td>刘汇</td><td>男</td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td></tr>
									<tr><td>张涛</td><td>男</td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td></tr>
									<tr><td>李倩</td><td>女</td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td><td><input type="text" class="add_money"></td></tr>
								</tbody>
							</table>
							<div class="caozuo" style="text-align:center;">
								<button class="btn btn-warning">确定</button> <button class="btn">取消</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
</body>
</html>