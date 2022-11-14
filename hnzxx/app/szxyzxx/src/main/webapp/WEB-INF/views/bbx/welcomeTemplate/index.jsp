<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
<style>
.ban-active img {
	width: 186px;
	height: 106px;
}

.ban-active .feng {
	width: 190px;
}

.ban-active {
	margin-right: 10px;
}

.uploadify {
	position: absolute;
	top: 0;
	opacity: 0;
}
</style>
</head>
<body style="background-color: #F3F3F3 !important;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal padding15" id="welcomeTemplate"
						style="padding-right: 0;">
						<div class="control-group">
							<div id="welcomeTemplate_list_content">
								<jsp:include page="./list.jsp" />
							</div>
							<div class="ban-active left" style="position: relative">
								<a href="javascript:void(0)" class="feng"><img
									src="${ctp }/res/css/bbx/images/zdy.jpg"></a> <input
									type="hidden" id="uploader" />
								<div class="wenzi">自定义</div>
							</div>
						</div>
					</form>

				</div>
			</div>
		</div>

	</div>

</body>

<script type="text/javascript">
	function search() {
		var val = {};
		var id = "welcomeTemplate_list_content";
		var url = "/bbx/welcomeTemplate/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
</script>

</html>