<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<title>文印</title>
<style type="text/css">
	.row-fluid .span4{
		width:220px;
	}
	input[type="radio"]{
		margin:0 5px;
		position:relative;
		top:-1px;
	}
</style>
<script type="text/javascript">

	$(function() {
		$(".toggle").click(function() {
			$(this).html($(".content").is(":hidden") ? "收起<i class='fa fa-angle-up'></i>" : "展开<i class='fa fa-angle-down'></i>");
			$(".content").slideToggle();
		});
	});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="文印" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				            <li><a href="javascript:void(0)" class="on">申请文印</a></li>
				        </ul>
					</div>
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">标题：</label>
								<div class="controls">
									<input type="text" class="span8 left_red" placeholder="请输入标题，少于40个中文字符" />
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">发布时间：</label>
								<div class="controls">
									<input type="text" class="span4" placeholder="留空是当前时间"   onclick="WdatePicker();" />
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">摘要：</label>
								<div class="controls">
								<textarea class="span8"></textarea>
								</div>
							</div>
							<div class="control-group content">
								<label class="control-label">正文：</label>
								<div class="controls">
									<textarea style="" class="span8"></textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">附件：</label>
								<div class="controls">
									<button class="btn"><i class="fa fa-upload"></i>上传附件</button>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">提货方式：</label>
								<div class="controls" style="padding-top:5px;">
									<input type="radio" name="daike" />送货
									<input type="radio" name="daike" />自提
								</div>
							</div>
							<div class="tag">
						        <a href="javascript:void(0);" class="toggle"> 收起 <i class="fa fa-angle-up"></i></a>
						    </div>
							<div class="caozuo" style="text-align:center;">
								<button class="btn btn-success">发布</button> <button class="btn">预览</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>