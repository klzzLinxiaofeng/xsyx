<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}

.chzn-container .chzn-results {
	max-height: 120px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid ">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form id="calendarInfo_form" class="form-horizontal left-align form-well" >
						<div class="control-group">
							<label class="control-label"><span class="red">*</span>请选择：</label>
							<div class="controls">
								<select id="id" name="id" style="margin-bottom: 0">
									<c:forEach items="${calendarList}" var="ca">
										<option value="${ca.id}">${ca.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="submit"
								onclick="downLoadCalendarInfo();">导出EXCLE</button>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
 <script type="text/javascript">
	 var checker;
	$(function() {
		checker = initValidator();
	});

	function initValidator() {
		return $("#calendarInfo_form").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}

	 function downLoadCalendarInfo() {
		var id = $("#id").val();
		if(id == null || id == ""){
			$.error("没有校历，无法导出");
			return;
		}
		window.open("${pageContext.request.contextPath}/teach/calendar/downLoadCalendarInfo?id="+$("#id").val());
		/* if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#calendarInfo_form");
			$requestData._method = "post";
			var url = "${pageContext.request.contextPath}/teach/calendar/downLoadCalendarInfo";
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
// 					$.success('导出成功');
// 					$.closeWindow();
				} else {
					$.error("服务器异常");
				}

			});

			loader.close();
		} */
	}  
	
	
</script> 

</html>