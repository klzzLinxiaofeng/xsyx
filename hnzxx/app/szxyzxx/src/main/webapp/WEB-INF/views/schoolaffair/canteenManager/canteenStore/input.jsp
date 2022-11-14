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
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
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
					<form class="form-horizontal tan_form" id="canteenstore_form" action="javascript:void(0);">
						
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>食堂名称：
								</label>
								<div class="controls">
								 <c:choose>
									<c:when test="${not empty canteenStore.canteenCode  }">
										<select id="canteenCode1" name="canteenCode1" disabled="disabled">
											<option value="${canteenStore.canteenCode }" selected>${canteen.name }</option>
										</select>
									</c:when>
									<c:otherwise>
										<select id="canteenCode" name="canteenCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
									 </c:otherwise>
								</c:choose>
								</div>
						</div>	
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>商品名称：
								</label>
								<div class="controls">
								 <c:choose>
									<c:when test="${not empty canteenStore.goodsCode  }">
										<select id="goodsCode1" name="goodsCode1" disabled="disabled">
											<option value="${canteenStore.goodsCode }" selected>${canteenGoods.name }</option>
										</select>
									</c:when>
									<c:otherwise>
										<select id="goodsCode" name="goodsCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
									 </c:otherwise>
								</c:choose>
								</div>
						</div>	
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>库存数量：
								</label>
								<div class="controls">
								<input type="text" id="storeNum" name="storeNum" class="span4" placeholder="" value="${canteenStore.storeNum}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
								</div>
						</div>
							
						<div class="form-actions tan_bottom">
						<c:if test="${isCK == null || isCk == ''}">
							<input type="hidden" id="id" name="id" value="${canteenStore.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
						</c:if>
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
	$.HqCanteenSelector({
		   "selector" : "#canteenCode",
		   "afterHandler" : function() {
			}	
	   });
	$.HqCanteenGoodsSelector({
		   "selector" : "#goodsCode",
		   "afterHandler" : function() {
			}	
	   });
	checker = initValidator();
});
	
	function initValidator() {
		return $("#canteenstore_form").validate({
			errorClass : "myerror",
			rules : {
				"canteenCode" : {
					selectNone : true,
				},
				"goodsCode" : {
					selectNone : true,
				},
				"storeNum" : {
					required : true,
					digits : true
				}
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
			var $requestData = formData2JSONObj("#canteenstore_form");
			var url = "${ctp}/schoolaffair/canteenStore/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/schoolaffair/canteenStore/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>