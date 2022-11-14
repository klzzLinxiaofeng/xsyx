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
					<form class="form-horizontal tan_form" id="canteentakeorder_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>食堂：
								</label>
								<div class="controls">
									<select id="canteenCode" name="canteenCode"  class="span4 chzn-select" >
									</select>
								</div>
							</div>		
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>商品名称：
								</label>
								<div class="controls">
									<select id="goodsCode" name="goodsCode" class="span4 chzn-select" onchange="getCanteenCode()">
									</select>
								</div>
							</div>	
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>库存数量：
								</label>
								<div class="controls">
								<input type="text" id="storeNum" name="storeNum" class="span4" placeholder="" disabled='disabled' >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>领用数量：
								</label>
								<div class="controls">
								<input type="text" id="takeCount" name="takeCount" class="span4" placeholder="" value="${canteenStore.storeNum}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>签收日期
								</label>
								<div class="controls"> 
								<input type="text" id="takeDate" name="takeDate" class="span4" placeholder="请输入时间" onclick="WdatePicker();" >
								</div>
							</div> 
						
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>领用人：
								</label>
								<div class="controls">
								<input type="text" id="signPerson" name="signPerson" class="span4" placeholder="" value="${canteenStore.storeNum}">
								</div>
							</div>	
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
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
		/* $.HqCanteenGoodsSelector({
			   "selector" : "#goodsCode",
			   "afterHandler" : function() {
				}	
		}); 
		
		$.HqCanteenSelector({
			   "selector" : "#canteenCode",
			   "afterHandler" : function() {
				}	
		   }); */
		
		$.HqCanteenList({"selector" : "#canteenCode"});
		checker = initValidator();  
		
	});
	
 	$.HqCanteenList = function(selectorName) {
	 $.get("${ctp}/schoolaffair/canteen/list/json", {},
        function(data, status) {
            if ("success" === status) {
            	var canteenList = JSON.parse(data);
            	$(selectorName.selector).append("<option>请选择</option>");
            	$.each(canteenList, function(index, value){
            		$(selectorName.selector).append("<option value='"+value.code+"'>"+value.name+"</option>");
            	});
            	
            }
         });
	};
	
	$("#canteenCode").change(function(){
		var canteenCode = $("#canteenCode").val();
		$.get("${ctp}/schoolaffair/canteenGoods/getCanteenGoodsByCanteen", {"code":canteenCode},
           function(data, status) {
               if ("success" === status) {
            	   var goods = JSON.parse(data);
            	   $("#goodsCode").empty();
            	   $("#goodsCode").append("<option>请选择</option>");
            	   $.each(goods, function(index, value){
            		  $("#goodsCode").append("<option value='"+value.code+"'>"+value.name+"</option>"); 
            	   });
               }
        });
	}); 
	
	 function getCanteenCode(){
		var canteenCode = $("#canteenCode").val();
		var goodsCode = $("#goodsCode").val();
		if(goodsCode=== "" || canteenCode === ""){
			return;
		}
		var url = "${ctp}/schoolaffair/canteenTakeOrder/getStoreNum";
		$.post(url,{"canteenCode":canteenCode,"goodsCode":goodsCode}, function(data, status) {
			if ("success" === status) {
				data = JSON.parse(data);
				/* data = eval("(" + data + ")"); */
				$("#storeNum").val(data.responseData);
			}
		});
		 
	} 
	
	function initValidator() {
		return $("#canteentakeorder_form").validate({
			errorClass : "myerror",
			rules : {
				"canteenCode":{
					selectNone:true
				},
				"goodsCode":{
					selectNone:true
				},
				"takeCount":{
					required:true,
					digits:true,
					remote:{
						url:"${ctp}/schoolaffair/canteenTakeOrder/check",
						type:"post",
						dataType:"json",
						data:{
							"storeNum":function(){return $("#storeNum").val();},
							"takeCount":function(){return $("#takeCount").val();}
						}
					}
				},
				 "takeDate":{
					required:true
				},
				"signPerson":{
					required:true
				}
			},
			messages : {
				"takeCount":{
					remote:"不能领用"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#canteentakeorder_form");
			var url = "${ctp}/schoolaffair/canteenTakeOrder/creator";
// 			alert(JSON.stringify($requestData));
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