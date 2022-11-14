<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
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
					<form class="form-horizontal tan_form" id="result_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label"><font style="color: red">*</font>
									教师：
								</label>
								<div class="controls">
								<input type="text" id="teach" name="teach" class="span4" data-id="${result.teachId }" placeholder="" value="${result.teachName}" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} > 
								<input type="hidden" id="teachId" name="teachId" value="${result.teachId }">
								</div>
							</div>	
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>成果名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.name}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>成果类型：
								</label>
								<div class="controls">
									<select id="type" name="type" class="span4" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<font color="red">*</font>成果级别：
								</label>
								<div class="controls">
									<select id="level" name="level" class="span4" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}></select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>排名次序：
								</label>
								<div class="controls">
								<input type="text" id="rank" name="rank" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.rank}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>刊物分类：
								</label>
								<div class="controls">
								<input type="text" id="publication" name="publication" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.publication}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>日期：
								</label>
								<div class="controls"> 
								<input type="text" id="applyDate" name="applyDate" class="span4" placeholder="" value="<fmt:formatDate value='${result.applyDate }' />" onclick="WdatePicker();" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}>
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>部署人数（人）：
								</label>
								<div class="controls">
								<input type="text" id="personNum" name="personNum" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.personNum}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>申请学分：
								</label>
								<div class="controls">
								<input type="text" id="applyScore" name="applyScore" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.applyScore}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>核定学分：
								</label>
								<div class="controls">
								<input type="text" id="checkScore" name="checkScore" class="span4" placeholder="" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} value="${result.checkScore}" >
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>是否独立完成：
								</label>
								<div class="controls">
								 <c:choose>
								   <c:when test="${not empty result.independent }">
								      <input type="radio" id="independent" name="independent" class="span5"  value="true"  ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} <c:if test="${result.independent==true }">checked</c:if>/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="independent" name="independent" class="span5"  value="false" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""} <c:if test="${result.independent==false }">checked</c:if>/>否
								   </c:when>
								   <c:otherwise>
								      <input type="radio" id="independent" name="independent" class="span5"  value="true" checked="checked" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}/>是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="independent" name="independent" class="span5"  value="false" ${isCK != null && isCK != '' ? "disabled='disabled'" : ""}/>否
								   </c:otherwise>
								 </c:choose>
								</div>
							</div>
							
							<div class="control-group">
							<label class="control-label">附件</label>
							<div class="controls update_img" style="padding-top:5px;">
								<c:choose>
									<c:when test="${not empty result.fileUuid }">
										<c:forEach items="${entityList}" var="entity" varStatus="i">
											<p style='display:inline-block;margin-bottom:0;width:400px;overflow:hidden;'><a target="_blank" id="${i.index+1}" href='<entity:getHttpUrl uuid="${entity.uuid}"/>'>${entity.fileName}</a><a id="b${i.index}" onclick="deleteFile(this);" href="javascript:void(0);" class="remove_fj" ${isCK != null && isCK != '' ? "style='display:none'" : ""}>x</a></p>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<p style='display:inline-block;margin-bottom:0;width:400px;overflow:hidden;'><a taget="_blank" id="a"></a></p>
										<c:choose>
											<c:when test="${isCK != null && isCK != '' }"></c:when>
											<c:otherwise><input type="hidden" id="uploader" name="uploader"/></c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
								<input type="hidden" id="entityId" name="fileUuid" value="${result.fileUuid }"/>
							</div>
							</div>
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
								<input type="hidden" id="id" name="id" value="${result.id}" />
								<button class="btn btn-warning" type="button" onclick="pass();">通过</button>
				                <button class="btn" type="button" onclick="notPass();">不通过</button>
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
		$.jcGcSelector("#type", {"tc" : "XY-JY-CGLX"}, "${result.type}", function(selector) {
			selector.chosen();
		});
		$.jcGcSelector("#level", {"tc" : "XY-JY-CGJB"}, "${result.level}", function(selector) {
			selector.chosen();
		});
		$.createMemberSelector({
			"inputIdSelector" : "#teach",
			"enableBatch" : false,
			"isOnTopWindow" : true
		});
	});
	
	//审核通过
	function pass() {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#result_form");
			$requestData.audit=2;
			//alert(JSON.stringify($requestData));
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/resultCheck/" + $id;
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
	
	//审核不通过
	function notPass() {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#result_form");
			$requestData.audit=1;
			//alert(JSON.stringify($requestData));
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/resultCheck/" + $id;
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
</script>
</html>