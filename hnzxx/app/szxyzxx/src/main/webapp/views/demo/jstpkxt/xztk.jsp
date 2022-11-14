<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>教师听评课系统</title>
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
							<label class="control-label">听课教师</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="听课教师, 不能为空" >
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">讲课教师</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="讲课教师, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">学期</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="学期, 不能为空">
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">年级/班级</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="年级/班级" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">科目</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="科目" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">讲课日期</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="日期" onclick="WdatePicker();">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">课程标题</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="课程标题" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">课程内容</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="课程内容" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">点评</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="点评" >
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">经验</label>
							<div class="controls">
								<input type="text" id="" name="" class="span4" placeholder="经验">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">反思与评价</label>
							<div class="controls">
								<input type="file">
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