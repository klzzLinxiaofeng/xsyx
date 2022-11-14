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
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="improvider_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									IM服务商类型：
								</label>
								<div class="controls">
								<input type="text" id="imType" name="imType" class="span13" placeholder="" value="${imProvider.imType}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${imProvider.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否默认:
								</label>
								<div class="controls" style="line-height:30px;">
								 <c:choose>
								   <c:when test="${not empty imProvider.isDefault }">
								      <input type="radio" id="isDefault" name="isDefault" class=""  value="1"  <c:if test="${imProvider.isDefault==1 }">checked</c:if>/>是&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="isDefault" name="isDefault" class=""  value="0"  <c:if test="${imProvider.isDefault==0 }">checked</c:if>/>否
								   </c:when>
								   <c:otherwise>
								      <input type="radio" id="isDefault" name="isDefault" class=""  value="1"  checked="true"/>是&nbsp;&nbsp;&nbsp;
									  <input type="radio" id="isDefault" name="isDefault" class=""  value="0" />否
								   </c:otherwise>
								 </c:choose>
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${imProvider.id}" />
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
		return $("#improvider_form").validate({
			errorClass : "myerror",
			rules : {
				imType : {
					required : true,
					maxlength:20
				},
				name : {
					required : true,
					maxlength:20
				},
				isDefault : {
					required : true,
					remote:{
						async : false,
	  					url:"${pageContext.request.contextPath}/im/imProvider/check",
	  					type:"post",
	  					data:{
	  						'isDefault':function(){
	  							return $(":radio:checked").val();
	  						}
	  					}
	  				}
				}
			},
			messages : {
				imType : {
					required : "请输入服务商类型"
				},
				name : {
					required : "请输入服务商名称"
				},
				isDefault : {
					required : "是否为系统默认",
					remote:"只能默认一种服务商"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#improvider_form");//这里封装了json对象
			var url = "${ctp}/im/imProvider/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/im/imProvider/" + $id;
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