<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>多维度教师评价系统</title>
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
						<div class="control-group">评价人</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="评价人, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">被评价人</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="被评价人, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">评价人类型</label>
							<div class="controls">
								<select><option>学生</option><option>教师</option><option>学校</option></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">评分</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="评价教师" >
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