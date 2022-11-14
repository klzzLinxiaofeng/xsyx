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
.table input{
	margin:0;
	height:30px;
	line-height:30px;
	width:70px;
}
.table p{
	margin:0;
	font-size:12px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<!-- <form class="form-horizontal tan_form" id="performanceevaluationscore_form" action="javascript:void(0);">
							
					</form> -->
					<div class="kaohe">
						<div class="kh_left">
							<p>名单</p>
							<ul>
								<li><a href="javascript:void(0)" class="on">赵东娜</a></li>
								<li><a href="javascript:void(0)">赵夏</a></li>
								<li><a href="javascript:void(0)">赵芳</a></li>
								<li><a href="javascript:void(0)">赵芳凡</a></li>
							</ul>
						</div>
						<div class="kh_right">
							<table class="table table-bordered">
								<thead><tr><th>考核项目</th><th>分数</th><th>批注</th></tr></thead>
								<tbody>
									<tr><td><p>团队精神（0-10）</p><p>说明：明白什么是团队精神</p></td><td><input type="text"></td><td><input type="text" style="width:250px;"></td></tr>
									<tr><td><p>团队精神（0-10）</p><p>说明：明白什么是团队精神</p></td><td><input type="text"></td><td><input type="text" style="width:250px;"></td></tr>
								</tbody>
							</table>
						</div>
						<div class="clear"></div>
					</div>
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
		return $("#performanceevaluationscore_form").validate({
			errorClass : "myerror",
			rules : {
				
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
			var $requestData = formData2JSONObj("#performanceevaluationscore_form");
			var url = "${ctp}/personnel/performanceevaluationscore/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/personnel/performanceevaluationscore/" + $id;
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
	
	
	$(function(){
		$(".kaohe .kh_left ul li a").click(function(){
			$(".kaohe .kh_left ul li a").removeClass("on");
			$(this).addClass("on");
			//$("kh_right tbody").remove('1')
			
			$("kh_right tbody").html('<tr><td><p>团队精神（0-10）</p><p>说明：明白什么是团队精神</p></td><td><input type="text"></td><td><input type="text" style="width:250px;"></td></tr>')
		});
	});
</script>
</html>