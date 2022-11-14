<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>代码项编辑</title>
<style>
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="user_form">
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>名称：</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4">
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>代码：</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">适用学段</label>
							<div class="controls" style="padding-top:5px;">
								<input type="checkbox" style="position:relative;top:-2px;margin-right:10px">小学
								<input type="checkbox" style="position:relative;top:-2px;margin-right:10px">初中
								<input type="checkbox" style="position:relative;top:-2px;margin-right:10px">高中
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-success" type="button">保存</button>
								<button class="btn" type="button">返回</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>