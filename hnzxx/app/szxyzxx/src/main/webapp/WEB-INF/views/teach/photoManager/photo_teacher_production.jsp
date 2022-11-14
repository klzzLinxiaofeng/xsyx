<%@ page language="java"
	import="platform.education.user.contants.UserContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>修改图片</title>
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctp }/res/css/extra/diyUpload.css">
<link rel="stylesheet" type="text/css" href="${ctp }/res/css/extra/webuploader.css">
<script type="text/javascript" src="${ctp }/res/js/extra/diyUpload.js"></script>
<script type="text/javascript" src="${ctp }/res/js/extra/webuploader.html5only.min.js"></script>
<style>
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}
.parentFileBox .fileBoxUl{
	width:400px;
	margin: 0 40px 0 40px;
}
.parentFileBox .fileBoxUl li{
	margin-bottom:10px;
}
.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 16px;
	/* font-weight: bold; */
	color: #888;
	right: 0px;
	top: 0px;
	display: block;
	width: 14px;
	height: 14px;
	line-height: 14px;
	text-align: center;
	cursor: pointer;
	background-color: #eee;
	border: 1px solid #aaa;
}

.form-horizontal .controls {
	margin-left: 100px;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
*{ margin:0; padding:0;}
#box{ margin:50px auto; width:540px; min-height:400px; background:#FF9}
</style>
</head>
<body style="background-color: #F3F3F3 !important;">
		<div class="row-fluid " >
		
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
					<form class="form-horizontal" id="photoImport_form"
						action="javascript:void(0);">
						<div class="control-group">
							<label class="control-label" style="width:80px;"><font style="color: red">*</font>
							部门:
							</label>
							<div class="controls">
								<%-- <input type="text" id="name" name="name" class="span4"
									placeholder="" disabled="disabled" value="${teacher.name}"> --%>
								<select id="dept" name="dept" class="span4 chzn-select" style="width:330px;"></select>
							</div>
						</div>
						
						<div id="box" style="width:100%;background:none;">
							<div id="test" style="margin-left:40px;margin-bottom: 20px;"></div>
						</div>
						
						
						<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
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
		//获取部门
		$.PjDeptSelector({
			   "selector" : "#dept",
			   "afterHandler" : function() {
				}
		   });
		checker = initValidator();
		uploadFile();
	});
	
	<%--
		@function 部门选择
		@date 2016年02月24日
	--%>
	$.PjDeptSelector = function(options) {
	var defOption = {
		"selector" : "#dept",
		"condition" : {},
		"selectedVal" :  "",
		"afterHandler" : function() {},
		"firstOptionTitle" : "请选择",
		"isUseChosen" : true
	};
	options = $.extend({}, defOption, options || {});
	
	var selector = $(options.selector);
	selector.html("");
	selector.append("<option value=''>"+ options.firstOptionTitle +"</option>");
	
	$.getPjDept(options.condition, function(data) {
		$.each(data, function(index, value) {
			selector.append("<option value='" + value.id + "'>" + value.name + "</option>")
		});
		selector.val(options.selectedVal);
		if(options.isUseChosen == null || options.isUseChosen) {
			selector.chosen();
		}
		options.afterHandler(selector);
	});
	}
	
	$.getPjDept = function(conditionJson, afterHandler) {
	$.get("${ctp}/teach/photoManager/list/dept", conditionJson, function(data, status) {
		if("success" === status) {
			data = eval("(" + data + ")");
			afterHandler(data);
		}
	});
	}	

	function closeWin(){
		$.confirm("确定离开此页面？", function() {
			$.closeWindow();
		});
	}
	$('#test').diyUpload({
		url:'server/fileupload.php',
		success:function( data ) {
			console.info( data );
		},
		error:function( err ) {
			console.info( err );
		}
	});
	
	function initValidator() {
		return $("#photoImport_form").validate(
		{
			errorClass : "myerror",
			rules : {
				"dept" : {
					selectNone : true
				}
			},
			messages : {
			}
		});
	}
	
	<%--
		@function 提交数据
	--%>
	function saveOrUpdate(){
		if (checker.form()) {
			var loader = new loadLayer();
			var deptId = $("#dept").val();
			var jsonArray = [];
			$("#box ul li").each(function(){
				var curJson = {};
				var src = $(this).find("div:first-child img").attr("src");
				curJson.base64 = src;
				var name = $(this).find(".diyFileName ").html();
				curJson.fileName = name;
				jsonArray.push(curJson);
			});
			var $request = {};
			$request.deptId = deptId;
			$request.imgs = JSON.stringify(jsonArray);
			//console.log(JSON.stringify($request));
			//return;
			loader.show();
			var url = "${ctp}/teach/photoManager/sendImgs";
			$.post(url, $request, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if(data.info==="success"){
						$.success('操作成功');
						parent.core_iframe.search();
						$.closeWindow();
					}else if(data.info==="out_of_accord"){
						$.error("教师名称和照片名称不一致，请修改!");
					}else{
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