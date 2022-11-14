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
	width: 100%;
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
									<select id="canteenCode" name="canteenCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
						</div>	
						
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>供货商名称：
								</label>
								<div class="controls">
									<select id="supplyId" name="supplyId" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
									</select>
								</div>
						</div>	
						
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>签收人：
								</label>
								<div class="controls">
								<input type="text" id="signPerson" name="signPerson" class="span4" placeholder="" value="${canteenSignOrder.signPerson}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
								</div>
						</div>
						
						<%--用日历添加时间 --%>
						<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>签收日期
								</label>
								<div class="controls"> 
								<input type="text" id="signDate" name="signDate" class="span4" placeholder="请输入时间" value="<fmt:formatDate value='${canteenSignOrder.signDate }' />" onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
						</div> 
						
						
						
						<div style="padding:0 20px;">
							<table border="1" class="table table-bordered white responsive table-striped" id="item">
								<thead>
									<tr>
										<th style="width:165px">品名</th>
										<th style="width:165px">单价(元)</th>
										<th style="width:100px">数量</th>
										<th style="width:165px">操作</th>
									</tr>
								</thead>
								<tbody id="mytbody">
									<tr>
										<td >
											<div>
												<select id="goodsCode" name="goodsCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
											</div>	
										</td>
										<td><input type="text" id="price" name="price" class="span12"></td>
										<td><input type="text" id="totalCount" name="totalCount" class="span12" style="width:100px;"></td>
										<td>
											<button class="btn btn-red" type="button" onclick="deleteObj(this, '${canteenSignItem.id}');">删除</button>
										</td>
									</tr>
								</tbody>
							</table>
							<div class="new_add"><button class="btn btn-success" ><i class="fa fa-plus"></i>新增</button></div>
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
			var goodsCode = "goodsCode" + length;
			var price = "price"+length;
			var totalCount = "totalCount"+length;
			$(".table").append("<tr><td><select id='" + goodsCode + "' name='" + goodsCode + "'  class='span4 chzn-select'></select></td><td><input type='text' id='" + price + "' name='" + price + "' class='span12' /></td><td><input type='text' id='" + totalCount + "' name='" + totalCount + "' class='span12' /></td><td><button class='btn btn-red' type='button' onclick='deleteObj(this)'>删除</button></td></tr>")
			$.HqCanteenGoodsSelector({
				   "selector" : "#"+goodsCode+"",
				   "selectedVal" : "${canteenSignItem.goodsCode}",
				   "afterHandler" : function() {
					}	
			});
			step++;
			$("#" + goodsCode).rules("add", {required:true});
			$("#" + price).rules("add", {
 				required:true,
 				isFloatGZero:true,
 				max:100000.0,
 				messages: { isFloatGZero: "价格大于0"} 
 			});
			$("#" + totalCount).rules("add", {
 				required:true,
 				isIntGtZero:true,
 				max:999999999,
 				messages: { isIntGtZero: "数量大于0的整数"} 
 			});
		});
		$.HqCanteenGoodsSelector({
			   "selector" : "#goodsCode",
			   "selectedVal" : "${canteenSignItem.goodsCode}",
			   "afterHandler" : function() {
				
			   }	
		});
		
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
		
		checker = initValidatorOrder();
	});
	function deleteObj(obj){
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
			curJson.price = curTr.find("td:eq(1) input").val();
			curJson.totalCount = curTr.find("td:eq(2) input").val();
			jsonArray.push(curJson);
		});
		var result = {};
		result.items = jsonArray;
		//alert(JSON.stringify(result));
		return JSON.stringify(result);
	} 
	
	function initValidatorOrder() {
// 		alert(JSON.stringify(rules));
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
					isFloatGZero:true,
					max:100000.0
				},
				"totalCount":{
					required:true,
					isIntGtZero:true,
					max:999999999
				}
			},
			messages : {
				"price":{
					isFloatGZero:"价格大于0"
				},
				"totalCount":{
					isIntGtZero: "数量大于0的整数"
				}
			}
		});
		
	}
	
	//保存或更新修改
	function saveOrUpdate() {
// 		var trs = $("#item #mytbody tr");
// 		$.each(trs, function(index, value) {
// 			var goodsCode = $(value).find("td:eq(0) div select").attr("name");
// 			var price = $(value).find("td:eq(1) input").attr("name");
// 			var totalCount = $(value).find("td:eq(2) input").attr("name");
// 			rules[goodsCode] = {selectNone:true};
// 			rules[price] = {
// 	 				required:true,
// 	 				isFloatGteZero:true,
// 	 				max:100000.0
// 	 			};
// 			rules[totalCount] = {
// 	 				required:true,
// 	 				isIntGtZero:true,
// 	 				max:999999999
// 	 		};
// 		});
		if (checker.form()) {
			var tr = $(".table tbody tr").length;
			if(tr=='0'){
				$.alert("未签收商品，不能保存")
				return;
			}
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#canteensignorder_form");
			//alert(JSON.stringify($requestData));
			$requestData.goods = getQS_JSON();
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

// 	// 	删除对话框
// 	function deleteObj(obj, id) {
// 		$.confirm("确定执行此次操作？", function() {
// 			executeDel(obj, id);
// 		});
// 	}

// 	// 	执行删除
// 	function executeDel(obj, id) {
// 		$.post("${ctp}/schoolaffair/canteenSignOrder/" + id, {"_method" : "delete"}, function(data, status) {
// 			if ("success" === status) {
// 				if ("success" === data) {
// 					$.success("删除成功");
// 					$(this).remove();
// 				} else if ("fail" === data) {
// 					$.error("删除失败，系统异常", 1);
// 				}
// 			}
// 		});
// 	}
</script>
</html>