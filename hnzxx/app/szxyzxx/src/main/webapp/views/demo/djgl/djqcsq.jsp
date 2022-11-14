<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>电教管理</title>
<style>
	.row-fluid .span4 {
	width: 227px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="user_form">
						<div class="control-group">
							<label class="control-label">申请人</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="申请人, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">申请时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="申请时间, 不能为空" onclick="WdatePicker();">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">申请电教类型</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="电教类型, 不能为空">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">申请物品名称</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="申请物品名称" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">用途</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="用途, 不能为空" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">用时</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="用时, 不能为空" >
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">确定</button>
								<button class="btn btn-danger" type="button" onclick="saveOrUpdate(this);">删除</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>