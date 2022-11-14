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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="device_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>编号：
								</label>
								<div class="controls">
								<input type="text" id="code" name="code" class="span13 {accCheck:true,required : true,maxlength:20}" placeholder="" value="${device.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13 {required : true,maxlength:40}" placeholder="" value="${device.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									英文名称：
								</label>
								<div class="controls">
								<input type="text" id="englishName" name="englishName" class="span13 {maxlength:40}" placeholder="" value="${device.englishName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>产权：
								</label>
								<div class="controls">
								<select id="propertyRight" name="propertyRight" class="span13 {required : true}"></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>使用状况：
								</label>
								<div class="controls">
								<select id="serviceCondition" name="serviceCondition" class="span13 {required : true}"></select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									分类：
								</label>
								<div class="controls">
								<input type="text" id="category" name="category" class="span13 {maxlength:40}" placeholder="" value="${device.category}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									型号：
								</label>
								<div class="controls">
								<input type="text" id="model" name="model" class="span13 {maxlength:40}" placeholder="" value="${device.model}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									出厂时间：
								</label>
								<div class="controls">
								<input type="text" id="exFactoryDate" name="exFactoryDate" class="span13" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${device.exFactoryDate}"></fmt:formatDate>' onclick="WdatePicker({maxDate:'#F{$dp.$D(\'purchaseDate\')}'});">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									购置日期：
								</label>
								<div class="controls">
								<input type="text" id="purchaseDate" name="purchaseDate" class="span13" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${device.purchaseDate}"></fmt:formatDate>' onclick="WdatePicker({minDate:'#F{$dp.$D(\'exFactoryDate\')}',maxDate:'#F{$dp.$D(\'warrantyExpDate\')}'});">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									生产厂家：
								</label>
								<div class="controls">
								<input type="text" id="manufacturer" name="manufacturer" class="span13 {maxlength:40}" placeholder="" value="${device.manufacturer}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>设备来源：
								</label>
								<div class="controls">
								<select id="sourceType" name="sourceType" style="width: 380px;" ></select>
<!-- 								class="{required : true}" -->
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									单据号：
								</label>
								<div class="controls">
								<input type="text" id="documentNumber" name="documentNumber" class="span13 {maxlength:20}" placeholder="" value="${device.documentNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									仪器地点：
								</label>
								<div class="controls">
								<input type="text" id="place" name="place" class="span13 {maxlength:40}" placeholder="" value="${device.place}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>所在建筑物：
								</label>
								<div class="controls">
									<select id="blidingId" name="blidingId" class="span13 {required : true}">
										<option value="">请选择</option>
										<c:forEach items="${floorList}" var="list">
											<option value="${list.id}" <c:if test="${device.blidingId == list.id}">selected="selected"</c:if>>${list.name}</option>
										</c:forEach>
									</select>		
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>所在房间：
								</label>
								<div class="controls">
<%-- 									<c:if test="${vie == null}"> --%>
										<select id="roomId" name="roomId" class="span13 {required : true}">
										</select><input id="roomIdView" type="hidden" value="${device.roomId }"/>
<%-- 									</c:if> --%>
<%-- 									<c:if test="${vie != null}"> --%>
<!-- 										<select name="roomId" id="roomIdvie" class="span13 {required : true}"> -->
<!-- 											<option value="">请选择</option> -->
<%-- 											<c:forEach items="${roomList}" var="list"> --%>
<%-- 												<option value="${list.id}" <c:if test="${device.roomId == list.id}">selected="selected"</c:if>>${list.name}</option> --%>
<%-- 											</c:forEach> --%>
<!-- 										</select> -->
<%-- 									</c:if> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>单价：
								</label>
								<div class="controls">
								<input type="text" id="price" name="price" class="span13 {isFloatGZero:true,max:999999999,isNumber:true,required : true,maxlength:10}" placeholder="请输入单价（元）" value="${device.price}">（元）
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>数量：
								</label>
								<div class="controls">
								<input type="text" id="totalNumber" name="totalNumber" class="span13 {max:999999999,digits:true,required : true}" placeholder="请输入设备数量" value="${device.totalNumber}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									保修期截止日期：
								</label>
								<div class="controls">
								<input type="text" id="warrantyExpDate" name="warrantyExpDate" class="span13" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${device.warrantyExpDate}"></fmt:formatDate>' onclick="WdatePicker({minDate:'#F{$dp.$D(\'purchaseDate\')}'});">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13 {maxlength:200}" placeholder="" value="${device.remark}">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${device.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
					<input type="hidden" id="isCK" value="${isCK}"/>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	var isCK = $("#isCK").val();
	if(isCK!=""){
		$(".controls").disable();
		$(".form-actions").hide();
	}
	$(function() {
		checker = initValidator();
		
		if($("#blidingId").val()!=null && $("#blidingId").val()!=''){
				findRoom();
			}
		$("#blidingId").on("change",function(){ findRoom(); });
				
// 		initRoomChange();
		
		$.jcGcSelector("#sourceType", {"tc" : "XY-JY-SBLY"}, "${device.sourceType}", function(selector) {
			selector.chosen();
		});
		$.jcGcSelector("#serviceCondition", {"tc" : "JY-SYZK"}, "${device.serviceCondition}", function(selector) {
			selector.chosen();
		});
		$.jcGcSelector("#propertyRight", {"tc" : "JY-CQ"}, "${device.propertyRight}", function(selector) {
			selector.chosen();
		});
		$("#blidingId").chosen();
	});
	
	
	function findRoom() {
		var optionHtml = $("#roomId");
		var roomIdView = $("#roomIdView").val();
		var blidingId = $("#blidingId").val();
		$.post("${ctp}/oa/device/selectRoom",{"blidingId" : blidingId},function(returnData,status){
			optionHtml.find("option").remove();
			optionHtml.append("<option value='"+"' selected='selected'>请选择</option>");
			$.each(returnData, function(index, value) {
				if(roomIdView == value.id) {
					optionHtml.append("<option value='" + value.id +"' selected='selected'>" + value.name + "</option>");
				}else {
				optionHtml.append("<option value='" + value.id +"'>" + value.name + "</option>");
				}
			});
			optionHtml.removeClass("chzn-done");
			optionHtml.css("display", "block");
			$("#roomId_chzn").remove();
			optionHtml.chosen();
		},'json');
		
		
	}
	function initRoomChange(){
		var optionHtml = $("#roomId");
		var roomIdView = $("#roomIdView").val();
		optionHtml.html("").append("<option value=''>请选择</option>");
		$("#blidingId").on("change",function(){
			var blidingId = $(this).val(); 
			$.post("${ctp}/oa/device/selectRoom",{"blidingId" : blidingId},function(returnData,status){
				optionHtml.find("option").remove();
				$.each(returnData, function(index, value) {
					alert(value.id)
					if(roomIdView == value.id) {
						optionHtml.append("<option value='" + value.id +"' selected='selected'>" + value.name + "</option>");
					}else {
					optionHtml.append("<option value='" + value.id +"'>" + value.name + "</option>");
					}
				});
// 				optionHtml.val("${device.roomId}");
				optionHtml.removeClass("chzn-done");
				optionHtml.css("display", "block");
				$("#roomId_chzn").remove();
				optionHtml.chosen();
			},'json');
		});
	}
	
	function initValidator() {
		return $("#device_form").validate({
			errorClass : "myerror",
			rules : {
				"code":{
					remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/oa/device/checker",
						data : {
							'code' : function() {
								return $("#code").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"name":{
					remote : {
						async : false,
						type : "POST",
						url : "${pageContext.request.contextPath}/oa/device/nameChecker",
						data : {
							'name' : function() {
								return $("#name").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				}
			},
			messages : {
				"code" : {
					remote : "编号已存在"
				},
				"name":{
					remote:"名称已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#device_form");
			var url = "${ctp}/oa/device/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/device/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						$.success('操作成功');
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					}else if("fail" === data.info){
						/* $.error("您输入的编号已重复，保存失败"); */
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