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
<script type="text/javascript">
	$(function() {
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector",
// 			"isOnTopWindow" : true,
			"layerOp" : {
				"shift" : "top"
			}
		});
	});
</script>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<input type="hidden" id="isCK" value="${isCK}"/>
					<form class="form-horizontal tan_form" id="usecard_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>车牌号：
								</label>
								<div class="controls">
								<select id="plateNumber" name="plateNumber"  class="span13 {required:true}">
									<option value="">请选择</option>
									<c:forEach items="${vehicle}" var="list">
										<option value="${list.id}" <c:if test="${list.id == usecard.plateNumber}">selected="selected"</c:if> >${list.plateNumber}</option>
									</c:forEach>
								</select>
								</div>
							</div>
							<div class="control-group">
							<label class="control-label"> <span style="color: red;">*</span>选择用车人员： </label>
							<div class="controls">
								<div class="textarea">
									<input type="button" id="member_selector" value="选择用车的老师">
                                  <span id="teachName">
                                  <c:if test="${!empty  noticeUser}">已选择的老师</c:if>
                                  ${usecard.cardUser}
                                     </span>
								</div>
							</div>
						</div>
							
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>用途：
								</label>
								<div class="controls">
								<textarea id="application" name="application"  class="span13 {required:true}">${usecard.application}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>出车时间：
								</label>
								<div class="controls">
								<input type="text" id="useDate" name="useDate" class="span13 {required:true}" placeholder="" 
									value='<fmt:formatDate pattern="yyyy-MM-dd" value="${usecard.useDate}"></fmt:formatDate>' onclick="WdatePicker({minDate:'%y-%M-{%d}'});">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea id="remark" name="remark" class="span13">${usecard.remark}</textarea>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${usecard.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
						<input type="hidden" id="na" value="${usecard.cardUser}"/>
					</form>
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
	});
	
	function initValidator() {
		return $("#usecard_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	var ids="";
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#usecard_form");
			var url = "${ctp}/oa/usecard/creator?ids="+ids;
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/usecard/" + $id + "?ids="+ids;
			}
			//用车人必填项
			if(ids=="" && ($("#na").val()==null || $("#na").val()=="")){
				$.alert("请选择用车人！");
				return;
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
					}else if("fail" === data.info){
						$.error("该车在使用中");
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
	
	var teachName= "已选择的老师：";
	function selectedHandler(data) {
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				ids=ids + value + ",";
				teachName = teachName + data.names[index] + ",";
			}
		});
		 $("#teachName").html(teachName);
		$.success("老师选择成功"); 
	}
	
</script>
</html>