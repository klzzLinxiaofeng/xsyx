<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="导出模板数据" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>考核任务名称：</span>
									<select id="taskId">
										<c:forEach items="${taskList}" var="task">
											<option value="${task.id}">${task.name}</option>										
										</c:forEach>
									</select>
								</div>
								<a id="downLoadExcel"  class="a2" href="" onclick="downLoadData();" style="  position: relative;top: 10px;left: 17px;font-size: 14px;"><i class="fa fa-plus"></i>导出模板</a>	
								<div class="clear"></div>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function downLoadData() {
		var $requestData = {};
		$requestData.taskId = $("#taskId").val();
		//必填项
		if($requestData.taskId == "" || $requestData.taskId == null){
			$.alert("请选择考核任务项!");
			return;
		}
		var url = "${ctp}/schoolAffair/aptTask/downLoadData?taskId="+$("#taskId").val();
		$("#downLoadExcel").attr("href", url);
	}
</script>
</html>