<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>新增名师工作室</title>
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
							<label class="control-label">教师名称</label>
							<div class="controls">
								<input type="text" class="span4"/>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">教师工作室</label>
							<div class="controls">
								<input type="text" class="span4" />
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">教师学科</label>
							<div class="controls">
								<select class="span4"><option>语文</option><option>数学</option><option>英语</option></select>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">教师年龄</label>
							<div class="controls">
								<input type="text" class="span4" />
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">教师简介</label>
							<div class="controls">
								<textarea class="span8"></textarea>
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