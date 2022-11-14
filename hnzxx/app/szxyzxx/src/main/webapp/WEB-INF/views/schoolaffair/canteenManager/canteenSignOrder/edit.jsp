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
<body style="background-color: #fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0px 0;">
					<form class="form-horizontal tan_form" id="canteensignorder_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>食堂：
								</label>
								<div class="controls">
									<select id="canteenCode" name="canteenCode" class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
						</div>	
						
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>供货商名称：
								</label>
								<div class="controls">
									<select id="supplyId" name="supplyId" class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
						</div>	
						
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>签收人：
								</label>
								<div class="controls">
								<input type="text" id="signPerson" name="signPerson" class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} placeholder="" value="${canteenSignOrder.signPerson}" >
								</div>
						</div>
						
						<%--用日历添加时间 --%>
						<div class="control-group">
							<label class="control-label"> <span class="red">*</span>日期
							</label>
							<div class="controls">
								<input type="text" id="signDate" name="SignDate"
									class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} placeholder="请输入时间"
									value="<fmt:formatDate pattern="yyyy-MM-dd "
															value='${canteenSignOrder.signDate}'></fmt:formatDate>"
									onclick="WdatePicker();">
							</div>
						</div>
						
						<div style="padding:0 20px;">
							<table border="1" class="table table-bordered white responsive table-striped" id="item">
								<thead>
									<tr>
										<th style="width:165px">品名</th>
										<th style="width:165px">单价</th>
										<th style="width:165px">数量</th>
										<th style="width:165px">操作</th>
									</tr>
								</thead>
								<tbody id="mytbody">
									<c:forEach items="${list}" var="item" varStatus="sta">
										<tr>
											<input type="hidden" id="itemId" name="itemId" value="${item.id}" />
											<td >
												<div>
													<select id="goodsCode_${sta.index}" name="goodsCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
														<option value="">请选择</option>
														<c:forEach items="${goodsList}" var="goods">
															<option value="${goods.code}" ${goods.code==item.goodsCode?"selected='selected'":""}>${goods.name}</option>	
														</c:forEach>
													</select>
												</div>	
											</td>
											<td><input type="text" id="price" value="${item.price}" class="span12" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></td>
											<td><input type="text" id="totalCount"  value="${item.totalCount}" class="span12" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></td>
											<c:if test="${empty isCK}">
												<td>
													<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
												</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<c:if test="${empty isCK}">
							<div class="new_add"><button class="btn btn-success" ><i class="fa fa-plus"></i>新增</button></div>
							</c:if>
						</div>
						</form>
						
						<div class="form-actions tan_bottom">
						<c:if test="${isCK == null || isCk == ''}">
							<input type="hidden" id="id" name="id" value="${canteenSignOrder.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
						</c:if>
						</div>
						
					
					
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	var step = 0;
	$(function() {
		$(".new_add button").click(function(){
			var length =  step + 1;
			$(".table").append("<tr><td><select id='goodsCodeAdd_" + length + "' name='goodsCode' class='span4' ></select></td><td><input type='text' id='price' class='span12' /></td><td><input type='text' id='totalCount' class='span12' /></td><td><button class='btn btn-red' type='button' onclick='deleteObj(this)'>删除</button></td></tr>")
			$.HqCanteenGoodsSelector({
				   "selector" : "#goodsCodeAdd_"+length,
				   "selectedVal" : "${canteenSignItem.goodsCode}",
				   "afterHandler" : function() {
					}	
			});
			step++;
		});
// 		var length = "${fn:length(list)}";
// 		for(var i = 0; i<length ;i++){
// 			alert( "${list[0].goodsCode}");
// 			$.HqCanteenGoodsSelector({
// 				   "selector" : "#goodsCode_"+i,
// 				   "selectedVal" : "",
// 				   "afterHandler" : function() {
// 					}	
// 			});
// 		}
// 		$.HqCanteenGoodsSelector({
// 			   "selector" : ".chzn-select",
// 			   "selectedVal" : "",
// 			   "afterHandler" : function() {
// 				}	
// 		});
		$.HqCanteenSelector({
			   "selector" : "#canteenCode",
			   "selectedVal" : "${canteenSignOrder.canteenCode}",
			   "afterHandler" : function() {
				}	
		   });
		
		$.HqCanteenSupplySelector({
			   "selector" : "#supplyId",
			   "selectedVal" : "${canteenSignOrder.supplyId}",
			   "afterHandler" : function() {
				}	
		   });
		$(".chzn-select").chosen();
		checker = initValidator();
	});
	function deleteObj(obj){
		var itemId = $(obj).parent().parent().find("#itemId").val() + "";
		$.confirm("确定执行此次操作？", function() {
			$(obj).parent().parent().remove();
		});
	} 
	function getQS_JSON() {
		var jsonArray = [];
		$.each($("#item #mytbody tr"), function(index, value) {
			var curTr = $(value);
			var curJson = {};
			curJson.goodsCode = curTr.find("select").val();
			curJson.price = curTr.find("#price").val();
			curJson.totalCount = curTr.find("#totalCount").val();
			jsonArray.push(curJson);
		});
		var result = {};
		result.items = jsonArray;
		return JSON.stringify(result);
	} 
	function initValidator() {
		return $("#canteensignorder_form").validate({
			errorClass : "myerror",
			rules : {
				"canteenCode":{
					selectNone:true
				},
				"supplyId":{
					selectNone:true
				},
				"signPerson":{
					required:true
				},
				 "signDate":{
					required:true
				},
				"goodsCode":{
					selectNone:true
				},
				"price":{
					required:true,
					isFloatGteZero:true
				},
				"totalCount":{
					required:true,
					digits:true
				}
			},
			messages : {
				"price":{
					required:true,
					isFloatGteZero:"价格不能小于0"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#canteensignorder_form");
			$requestData.id = $id;
			$requestData.goods = getQS_JSON();
			//alert(JSON.stringify($requestData));
			var url = "${ctp}/schoolaffair/canteenSignOrder/create";
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


 	// 	执行删除
 	function executeDel(obj, id) {
 		$.post("${ctp}/schoolaffair/canteenSignItem/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
 					$.success("删除成功");
 					$(this).remove();
 				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
 				}
			}
 		}); 
	}
</script>
</html>