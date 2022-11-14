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
					<form class="form-horizontal tan_form" id="dormitory_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>宿舍楼号：
								</label>
								<div class="controls">
								 <%-- <c:choose>
									<c:when test="${not empty dormitory.floorCode  }">
										<select id="floorCode1" name="floorCode" disabled="disabled">
											<option value="${dormitory.floorCode }" selected>${floor.name }</option>
										</select>
									</c:when> --%>
									<%-- <c:otherwise> --%>
										<select id="floorCode" name="floorCode" class="span4 chzn-select" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></select>
									<%--  </c:otherwise>
								</c:choose> --%>
								
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									<span class="red">*</span>宿舍楼号：
								</label>
								<div class="controls">
								<input type="text" id="floorCode" name="floorCode" class="span4 {accCheck:true,maxlength:6}" placeholder="请输入正确的宿舍楼号" value="${dormitory.floorCode}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} >
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>寝室编号：
								</label>
								<div class="controls">
								<input type="text" id="dormitoryCode" name="dormitoryCode" class="span4 {chrnum:true,maxlength:6}" placeholder="请输入正确的寝室编号" value="${dormitory.dormitoryCode}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>可住人数：
								</label>
								<div class="controls">
								<input type="text" id="capacity" name="capacity" class="span4 {isDigits:true}" placeholder="请输入整数" value="${dormitory.capacity}" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>入住性别：
								</label>
								<div class="controls">
								 <c:choose>
								   <c:when test="${not empty dormitory.sex  }">
								      <input type="radio" id="sex" name="sex" class="span5"  value="1"  ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} <c:if test="${dormitory.sex==1 }">checked</c:if>/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="sex" name="sex" class="span5"  value="2" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""} <c:if test="${dormitory.sex==2 }">checked</c:if>/>女
								   </c:when>
								   <c:otherwise>
								      <input type="radio" id="sex" name="sex" class="span5"  value="1" checked="checked" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}/>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="sex" name="sex" class="span5"  value="2" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}/>女
								   </c:otherwise>
								 </c:choose>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea id="remark" name="remark" rows="5" cols="5" class="span4" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}></textarea>
								</div>
							</div>
							
							</div>
						<div class="form-actions tan_bottom">
						<c:if test="${isCK == null || isCk == ''}">
							<input type="hidden" id="id" name="id" value="${dormitory.id}" />
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
		$.HqFloorSelector({
			   "selector" : "#floorCode",
			   "selectedVal":"${dormitory.floorCode }",
			   "afterHandler" : function() {
				}
		   });
		
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#dormitory_form").validate({
			errorClass : "myerror",
			rules : {
				/* 
			  "floorName":{
				  required:true
			  }, */
			  
			  "floorCode":{
				  required:true,
				 
			  },
			  
			  "dormitoryCode":{
				  required:true,
				  chrnum:true
			  },
			  
			  
			  "capacity":{
				  required:true,
				  isDigits:true,
				  remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/schoolaffair/dormitoryCodeListAjax/checkCapacity",
						data : {
							'dxlx' : 'inCapacity',
							'inCapacity' : function() {
								return $("#capacity").val();
							}
						}
					}
			  },
			  
			  "sex":{
				  required:true
			  }
				
			},
			messages : {
				
				 /* "floorName":{
					  required:"大楼名称必选"
				  },
				   */
				  "floorCode":{
					  required:"宿舍楼号必选"
				  },
				  
				  "dormitoryCode":{
					  required:"寝室编号必填"
				  },
				  
				  
				  "capacity":{
					  required:"可住人数 必填"
				  },
				  
				  "sex":{
					  required:"入住性别必选"
				  }
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var capacity = $("#capacity").val();
			var $requestData = formData2JSONObj("#dormitory_form");
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitory/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/schoolaffair/dormitory/" + $id;
			}
			
			if(capacity==='0'){
				$.error("可住人数不能为0");
			}else{
				
				loader.show();
				 $.post(url, $requestData, function(data, status) {
					if("success" === status) {
						data = eval("(" + data + ")");
						if("success" === data.info) {
							$.success('保存成功');
							if(parent.core_iframe != null) {
	 							parent.core_iframe.window.location.reload();
	 						} else {
	 							parent.window.location.reload();
	 						}
							$.closeWindow();
						} else if("fail"===data.info){
							$.error("该寝室编号已存在，请重新填写！");
						}
					}else{
						$.error("保存失败");
					}
					loader.close();
				}); 
			}
			
		}
	}
	
</script>
</html>