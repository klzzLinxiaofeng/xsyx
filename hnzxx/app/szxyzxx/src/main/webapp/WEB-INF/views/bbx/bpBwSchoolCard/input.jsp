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
					<form class="form-horizontal tan_form" id="bwschoolcard_form" action="javascript:void(0);">
							<%-- <div style="display: none;" class="control-group">
								<label class="control-label">
									主键：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${bwSchoolCard.id}">
								</div>
							</div>
							
							<div style="display: none;" class="control-group">
								<label class="control-label">
									用户类型 1教师 4学生：
								</label>
								<div class="controls">
								<input type="text" id="userType" name="userType" class="span13" placeholder="" value="${bwSchoolCard.userType}">
								</div>
							</div> --%>
							<div class="control-group">
								<label class="control-label">
									用户姓名：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${bwSchoolCard.name}" 
									readonly="readonly" disabled="disabled">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									卡号：
								</label>
								<div class="controls">
								<input type="text" id="phyAccountId" name="phyAccountId" class="span13" placeholder="" value="${bwSchoolCard.phyAccountId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									物理卡号：
								</label>
								<div class="controls">
								<input type="text" id="accountId" name="accountId" class="span13" placeholder="" value="${bwSchoolCard.accountId}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									一卡通状态：
								</label>
								<div class="controls">
								<input type="text" id="status" name="status" class="span13" placeholder="" value="${bwSchoolCard.status}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									开通时间：
								</label>
								<div class="controls">
								<input type="text" id="openTime" name="openTime" class="span13 {date:true}" 
								placeholder="请输入时间" value="<fmt:formatDate pattern="yyyy-MM-dd" value='${bwSchoolCard.openTime}'></fmt:formatDate>"
								onclick="WdatePicker();" ${isCK != null && isCk != '' ? "disabled='disabled'" : ""}>
								</div>
								<input type="text" id="openTime" name="openTime" class="span13" placeholder="" value="${bwSchoolCard.openTime}">
							</div>
							<div class="control-group">
								<label class="control-label">
									是否激活：
								</label>
								<div class="controls">
									<select id="isUsed" name="isUsed" class="span13">
										<option value ="true" <c:if test="${bwSchoolCard.isUsed == true}">selected="selected"</c:if>>激活</option>
										<option value ="false" <c:if test="${bwSchoolCard.isUsed == false}">selected="selected"</c:if>>冻结</option>
									</select>
								<input type="text" id="isUsed" name="isUsed" class="span13" placeholder="" value="${bwSchoolCard.isUsed}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录是否删除：
								</label>
								<div class="controls">
								<select id="isDeleted" name="isDeleted" class="span13">
										<option value ="true" <c:if test="${bwSchoolCard.isDeleted == true}">selected="selected"</c:if>>删除</option>
										<option value ="false" <c:if test="${bwSchoolCard.isDeleted == false}">selected="selected"</c:if>>保留</option>
								</select>
								<input type="text" id="isDeleted" name="isDeleted" class="span13" placeholder="" value="${bwSchoolCard.isDeleted}">
								</div>
							</div> --%>
							<%-- <div class="control-group">
								<label class="control-label">
									记录创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${bwSchoolCard.createDate}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									记录修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${bwSchoolCard.modifyDate}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${bwSchoolCard.id}" />
							<input type="hidden" id="userId" name="userId" value="${bwSchoolCard.userId}">
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
	});
	
	function initValidator() {
		return $("#bwschoolcard_form").validate({
			errorClass : "myerror",
			rules : {
				"phyAccountId" : {
					required : true
				},
				"accountId" : {
					required : true
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
			var $requestData = formData2JSONObj("#bwschoolcard_form");
			var url = "${ctp}/bbx/bpBwschoolcard/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/bpBwschoolcard/" + $id;
			}
			loader.show();
			
			var phyAccountId = $("#phyAccountId").val();
			var accountId  = $("#accountId").val();
			var userId  = $("#userId").val();
			$.post(
				"${ctp}/bbx/bpBwschoolcard/checkCard", 
				{phyAccountId:phyAccountId, accountId:accountId, userId:userId},
				function(data, status) {
					if("success" === status) {
						data = eval("(" + data + ")");
						if("success" === data.info) {
							save(url, $requestData);
						} else {
							$.error("物理卡号已被占用");
						}
					}else{
						$.error("操作失败");
					}
				}
			);
		}
	}
	
	
	function save(url, $requestData){
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
						parent.core_iframe.search();
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
</script>
</html>