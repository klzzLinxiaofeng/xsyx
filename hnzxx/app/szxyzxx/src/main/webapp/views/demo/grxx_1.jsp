<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
<style>
	.form-horizontal .control-label{
		width:80px;
	}
.form-horizontal .controls {
    margin-left: 100px;
}
input[type="radio"]{
	position:relative;
	top:-1px;
	margin:0 8px;
}
body{background-color:#fff;}
</style>
</head>
<body>
	
				<form  class="form-horizontal">
					<div class="control-group">
						<label class="control-label">用户名</label>
						<div class="controls">
							<input type="text" placeholder="用户名" value="" id="" name="aidForm">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">昵称</label>
						<div class="controls">
							<input type="text" placeholder="昵称" value="" id="" name="aidForm">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">真实姓名</label>
						<div class="controls">
							<input type="text" placeholder="真实姓名" value="" id="" name="aidForm">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">性别</label>
						<div class="controls" style="height:30px;line-height:30px;">
							<input type="radio" name="sex">男<input type="radio" name="sex">女
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">省市区</label>
						<div class="controls">
							<select style="width:72px;"><option>广东</option><option>广西</option></select>
							<select style="width:72px;"><option>广州</option><option>佛山</option></select>
							<select style="width:72px;"><option>番禺区</option><option>天河区</option></select>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">现居地址</label>
						<div class="controls">
							<input type="text" placeholder="现居地址" value="" id="" name="aidForm" style="width:400px;">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">个人简介</label>
						<div class="controls">
							<textarea rows="3" cols="" style="width:400px;"></textarea>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">手机</label>
						<div class="controls">
							<input type="text" placeholder="手机" value="" id="" name="aidForm">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">邮箱</label>
						<div class="controls">
							<input type="text" placeholder="邮箱" value="" id="" name="aidForm">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label"></label>
						<div class="controls">
							<a href="javascript:void(0)" class="save_btn">保存</a>
						</div>
					</div>
				</form>

</body>
<script type="text/javascript">
</script>
</html>