<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="微课管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-container">
						<form class="form-horizontal">
							<div class="control-group">
									<label class="control-label">Multiple Select with Groups</label>
									<div class="controls">
										<select data-placeholder="Your Favorite Types of Bear" multiple class="chzn-select span6">
											<option value=""></option>
											<option>American Black Bear</option>
											<option>Asiatic Black Bear</option>
											<option>Brown Bear</option>
											<option>Giant Panda</option>
											<option selected>Sloth Bear</option>
											<option disabled>Sun Bear</option>
											<option selected>Polar Bear</option>
											<option disabled>Spectacled Bear</option>
										</select>
									</div>
								</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function () {
    $(".chzn-select").chosen();
});
</script>
</html>