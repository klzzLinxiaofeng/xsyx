<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>应用注册</title>
<style type="text/css">
	
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > 平台应用管理  >   <span>应用注册</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>应用注册</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		
		<div class="apply_register">
			<div class="apply_register_left input_select fl">
				<div class="select_div">
					<span>主应用：</span>
					<select class="span2"><option>定邦教育云</option></select>
				</div>
				<div class="select_div">
					<span>应用名称：</span>
					<input type="text">
				</div>
				<div class="select_div">
					<span>Key：</span>
					<input type="text">
				</div>
				<div class="select_div">
					<span>版本：</span>
					<input type="text">
				</div>
				<div class="select_div">
					<span>适用类型：</span>
					<select class="span2"><option>所有平台</option></select>
				</div>
				<div class="select_div">
					<span>来源类型：</span>
					<select class="span2"><option>官方</option></select>
				</div>
				<div class="select_div">
					<span>说明：</span>
					<textarea  style="resize:none" ></textarea>
				</div>
			</div>
			<div class="apply_register_right input_select" style="margin-left:562px">
				<div class="select_div">
					<span style="float: left;margin-top: 10px;margin-right: 10px;">图标：</span>
					<a href="javascript:;" class="a-upload">
						<p><i class="fa fa-plus" ></i></p>
						<p>点击上传</p>
					    <input type="file" name="" id="">
					</a>
				</div>
			</div>
		</div>
		
		<div class="btn_cz" style="padding: 20px;border-top: solid 1px #e3e8ec;">
			<button class="btn btn-blue">保存</button>
			<button class="btn btn-lightGray">取消</button>
		</div>
	</div>
	

<script>


</script>
</body>
</html>