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
input[type="radio"], input[type="checkbox"]{ margin:0 4px; margin-left:6px; margin-top: 3px;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="room_form" action="javascript:void(0);">
							<div class="control-group" style="display: none;">
								<label class="control-label">
									id：
								</label>
								<div class="controls">
								<input type="text" id="id" name="id" class="span13" placeholder="" value="${room.id}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									学校id：
								</label>
								<div class="controls">
								<input type="text" id="schoolId" name="schoolId" class="span13" placeholder="" value="${room.schoolId}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${room.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房室类型：
								</label>
								<div class="controls">
								<select id="typeCode" name="typeCode" class="span13" placeholder="" value="${room.typeCode}">
								</select>
								<%-- <input type="text" id="typeCode" name="typeCode" class="span13" placeholder="" value="${room.typeCode}"> --%>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									容纳人数：
								</label>
								<div class="controls">
								<input type="text" id="galleryful" name="galleryful" class="span13" placeholder="0" value="${room.galleryful}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									排序：
								</label>
								<div class="controls">
								<input type="text" id="reorder" name="reorder" class="span13" placeholder="0" value="${room.reorder}">
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									是否可用：
								</label>
								<div class="controls">
								<label>
									<input name="isUsed" type="radio" value="true" />是
									<input name="isUsed" type="radio" value="false" />否
								</label> 
								<%-- <input type="text" id="isUsed" name="isUsed" class="span13" placeholder="" value="${room.isUsed}"> --%>
								</div>
							</div>
							<%-- <div class="control-group" style="display: none;">
								<label class="control-label">
									记录创建时间：
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span13" placeholder="" value="${room.createDate}">
								</div>
							</div>
							<div class="control-group" style="display: none;">
								<label class="control-label">
									记录修改时间：
								</label>
								<div class="controls">
								<input type="text" id="modifyDate" name="modifyDate" class="span13" placeholder="" value="${room.modifyDate}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${room.id}" />
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
	$.RoomType = function(selectId) {
		var selector = $(selectId);
		// selector.empty();
		selector.html("");
		var initTypeCode = "${room.typeCode}";
		selector.append("<option value=''>请选择</option>");
		url = "${pageContext.request.contextPath}" + "/bbx/RoomType/getRoomTypeList";
		$.get(url,function(data){
			$.each(data, function (index, obj) {
				if ( initTypeCode == obj.code ) {
					selector.append("<option value='" + obj.code + "' selected='selected'>" + obj.name +"</option>" );
				} else {
					selector.append("<option value='" + obj.code + "'>" + obj.name +"</option>" );
				}
	        });
		},"json");
		
	}

	$.isUsed = function () {
		var isUsed = "${room.isUsed}";
		if ( isUsed == "true" || isUsed == "" ) {
			$("input:radio[value='true']").attr('checked','true');
		} else if ( isUsed == "false" ) {
			$("input:radio[value='false']").attr('checked','false');
		} 
	}
	
	var checker;
	$(function() {
		checker = initValidator();
		$.RoomType("#typeCode");
		$.isUsed();
	});
	
	function initValidator() {
		var galleryful = $("#galleryful").val();
		if ( galleryful == "" ) {
			$("#galleryful").val("0");
		}
		return $("#room_form").validate({
			errorClass : "myerror",
			rules : {
				"typeCode" : {
					required : true,
				},
				"name" : {
					required : true,
					remote : {
						async : false,
						type : "POST",
						url : "${pageContext.request.contextPath}/bbx/Room/checker",
						data : {
							'dxlx' : 'name',
							'name' : function() {
								return $("#name").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"reorder" : {
					number: true
				}
			},
			messages : {
				"name" : {
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
			var $requestData = formData2JSONObj("#room_form");
			console.log($requestData);
			var url = "${ctp}/bbx/Room/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/Room/" + $id;
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