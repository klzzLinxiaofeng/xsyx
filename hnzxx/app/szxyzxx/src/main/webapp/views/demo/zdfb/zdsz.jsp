<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>分班设置</title>
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
							<label class="control-label">学段</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="学段, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">年级</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="年级, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">班级个数</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="班级个数, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">每班人数</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="每班人数, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">剩余人数</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="剩余人数" >
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">确定</button>
								<button class="btn btn" type="button" onclick="saveOrUpdate(this);">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>