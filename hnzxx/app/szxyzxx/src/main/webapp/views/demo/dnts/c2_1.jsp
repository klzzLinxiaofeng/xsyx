<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>请假</title>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">所在部门：</label>
								<div class="controls">
									<select class="span8"><option>教务处</option><option>校长室</option></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">问题类别：</label>
								<div class="controls" style="padding-top:5px;">
									<input type="radio" name="wtlb">技术咨询
									<input type="radio" name="wtlb">使用操作
									<input type="radio" name="wtlb">费用咨询
									<input type="radio" name="wtlb">应用更新
									<input type="radio" name="wtlb">其他
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">问题描述：</label>
								<div class="controls">
									<textarea class="span8" style="height:75px;padding:5px;"></textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">上传照片：</label>
								<div class="controls">
									<div class="up_img">
										<a href="javascript:void(0)" class="select">选择图片</a>
										<ul>
											<li><img src="${pageContext.request.contextPath}/res/images/help_text.png"><a href="javascript:void(0)"></a></li>
											<li><img src="${pageContext.request.contextPath}/res/images/help_text.png"><a href="javascript:void(0)"></a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="caozuo" style="text-align:center;">
								<button class="btn btn-primary">确定</button> <button class="btn">取消</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>