<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span2 {
	width: 125px;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="userregion_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label"> 已经分配的区域： </label>
							<div class="controls">
							<p style="padding-top:5px;margin-bottom:5px;">
								<c:choose>
									<c:when test="${userRegion.regionCode != null && userRegion.regionCode != ''}">
										<jc:cache echoField="name" paramName="code" tableName="jc_region" value="${userRegion.regionCode}"></jc:cache>
									</c:when>
									<c:otherwise>
										暂无分配
									</c:otherwise>
								</c:choose>
								</p>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"> 管理区域： </label>
							<div class="controls">
								<select class="span2" id="province" name="province"></select> 
								<select class="span2" id="city" name="city"></select> 
								<select class="span2" id="district" name="district"></select>
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${userRegion.id}" />
							<button class="btn btn-warning" type="button"
								onclick="saveOrUpdate();">确定</button>
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
		
		$.initRegionSelector({
    		sjSelector : "province",
			shijSelector : "city",
			qxjSelector : "district"
    	});
	});

	function initValidator() {
		return $("#userregion_form").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}

	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			
			var regionCode;
			var province = $("#province").val();
			var city = $("#city").val();
			var district = $("#district").val();
			var level;
			if(province != null && province != "") {
				regionCode = province;
				level = 1;
				if(city != null && city != "") {
					regionCode = city;
					level = 2;
					if(district != null && district != "") {
						regionCode = district;
						level = 3;
					}
				}
			}
			
			var $requestData = {};
			$requestData.regionCode = regionCode;
			$requestData.level = level;
			$requestData.userId = "${param.userId}";
			
			var url = "${ctp}/sys/userregion/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/sys/userregion/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>