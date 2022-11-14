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
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
.controls-div{
	width:379px;
	min-height:100px;
}
ul li a{
	float: left;
/*     font-weight: bold; */
    font-size: 14px;
    width: 79px;
    margin-right: 15px;
    margin-top: 10px;
    color: #fff;
/*     border: 1px #666 solid; */
    line-height: 30px;
/*     padding-left: 10px; */
    text-align: center;
    background:#a8a8a8;
    display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;
}
.on{
	background:#0d7bd5;
	color:#fff;
}
ul li a:hover{
	color:#FFF;
	background:#0d7bd5;
}
</style>
<script type="text/javascript">
$(function(){
	$(".controls-div ul li a").on("click",function(){
		$(this).toggleClass("on");
	})
});
</script>
</head>
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="pushsubscription_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>产品：
								</label>
								<div class="controls">
								<select id="appKey" name="appKey" class="span4 chzn-select" >
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>推送内容名称：
								</label>
								<div class="controls">

								<!-- <select id="objectCode" name="objectCode" class="span4 chzn-select" >
								</select> -->
								<div class="controls-div">
									<ul>
										<c:forEach items="${pushList}" var="push">
											<li><a href="javascript:void(0)" data-object-code="${push.code }">${push.content }</a></li>						
										</c:forEach>
									</ul>
								</div>
								<li>
								</div>

							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${pushsubscription.id}" />
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
		$.AllAppEditionsSelector({
			"selector" : "#appKey"
		});
		$.PushObjectSelector({
			"selector" : "#objectCode"
		});
	});
	
	function initValidator() {
		return $("#pushsubscription_form").validate({
			errorClass : "myerror",
			rules : {
				appKey : {
					required : true
				},
				objectCode : {
					required : true,
					remote:{
						async : false,
						url:"${ctp}/im/pushSubscription/check",
						type:"post",
						dataType:"json",
						data:{
							'dxlx' : 'appKey',
							"appKey":function(){return $("#appKey").val();},
							"objectCode" : function() {return $("#objectCode").val();}
						}
					}
				}
			},
			messages : {
				appKey : {
					required : "请选择一款产品"
				},
				objectCode : {
					required : "请选择推送内容名称",
					remote:"该产品已订阅！"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = {};
			
			var jsonArray = [];
			$(".controls-div ul li .on").each(function(){
				jsonArray.push($(this).attr("data-object-code"));
			});
			$requestData.objectCodes = JSON.stringify(jsonArray);
			
			//获取appkey
			var appKey = $("#appKey").val();
			if (appKey != null && appKey != "") {
				$requestData.appKey = appKey;
			}
			var url = "${ctp}/im/pushSubscription/creator";
			loader.show();
 			//alert(JSON.stringify($requestData));
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					parent.core_iframe.search();
					$.success('操作成功');
					$.closeWindow();
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>