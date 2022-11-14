<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>添加开课</title>
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
								<select><option>一年级</option><option>二年级</option><option>三年级</option><option>四年级</option><option>五年级</option><option>六年级</option></select>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">科目</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="科目, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">类型</label>
							<div class="controls">
								<select><option>艺术类</option><option>文学类</option><option>理工类</option></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">开课时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="开课时间，不能为空" onclick="WdatePicker();" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">选课时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="选课时间，不能为空" onclick="WdatePicker();" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">课程类型</label>
							<div class="controls">
								<select><option>必修课</option><option>选修课</option></select>>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">评价指标</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="评价指标, 不能为空" >
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