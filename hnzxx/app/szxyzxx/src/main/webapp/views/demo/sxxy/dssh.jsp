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
							<label class="control-label">年级</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="年级, 不能为空" value="五年级" disabled="disabled">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">班级</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="班级, 不能为空" value="四班" disabled="disabled">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">人员类别</label>
							<div class="controls">
								<select disabled="disabled"><option>教授</option><option>图书管理员</option><option>老师</option><option>学生</option></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">人员名称</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="人员名称" value="罗志明" disabled="disabled">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">申请时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="申请时间, 不能为空" disabled="disabled" value="2015-06-26">
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate(this);">审核通过</button>
								<button class="btn btn-danger" type="button" onclick="saveOrUpdate(this);">审核不通过</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>