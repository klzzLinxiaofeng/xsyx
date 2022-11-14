<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>教师成长档案</title>
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
								<input type="text" id="" name="" class="span4" placeholder="教师名称, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">上传时间</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="上传时间, 不能为空" onclick="WdatePicker();">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">所教科目</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="所教科目, 不能为空">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">文件类型</label>
							<div class="controls">
								<select><option>教学情况</option><option>教科研成果展示</option><option>教案或者教学设计</option><option>教学反思</option><option>个人获得荣誉奖项</option><option>指导学生获奖情况</option></select>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">文件名称</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="文件名称, 不能为空" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">上传文件</label>
							<div class="controls">
								<button class="btn btn-success" href="javascript:void(0)">上传文件</button>
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