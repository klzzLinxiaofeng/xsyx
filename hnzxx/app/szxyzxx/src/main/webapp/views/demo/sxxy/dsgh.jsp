<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>书香校园</title>
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
							<label class="control-label">读书人</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="读书人, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">读书标题</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="读书标题, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">书名</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="书名, 不能为空">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">作者</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="作者" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">出版社</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="出版社, 不能为空" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开始时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="开始时间, 不能为空" onclick="WdatePicker();">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结束时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="结束时间, 不能为空" onclick="WdatePicker();">
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>