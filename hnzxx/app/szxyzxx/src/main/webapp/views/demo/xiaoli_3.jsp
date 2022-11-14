<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">

</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal tan_form" id="" action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label">校历标题</label>
							<div class="controls">
								<input type="text" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">开始时间</label>
							<div class="controls">
								<input type="text" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">结束时间</label>
							<div class="controls">
								<input type="text" >
							</div>
						</div>
						
							<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button">确定</button>
								<button class="btn" type="button">取消</button>
							</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
