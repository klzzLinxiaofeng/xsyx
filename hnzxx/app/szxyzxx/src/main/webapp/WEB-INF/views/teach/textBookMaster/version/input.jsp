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
.form-horizontal .control-group{
	float:left;
	width:50%;
	height:31px;
}
.form-horizontal .control-label {
	width:100px;
}
.form-horizontal .controls {
    margin-left: 120px;
}
input[type="radio"]{
	position:relative;
	margin:0 10px;
	top:-1px;
}
</style>
</head>
<body style="background-color: #fff;">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 50px 0 0;margin-left:50px;">
					<form class="form-horizontal tan_form" id="textbookmanager_form" action="javascript:void(0);">
					<span style="float: left;line-height: 30px;"><span style="float: left;color:red;margin: 4px 0 0 -15px;position: relative;
    font-size: 18px;">*</span>版本名称：</span><input id="name" name="name" type="text" class="span13" placeholder="" style="margin-bottom:20px;"><br>
					<span style="float: left;line-height: 30px;">版本简介：	</span><input id="publisherId" name="publisherId" value="1" type="hidden">
						<input id="description" name="description" type="text" class="span13" placeholder=""><br>
						<button class="btn btn-warning" type="button" onclick="saveOrUpdate();" style="margin: 40px auto;display: block;">确定</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//保存或更新修改
	function saveOrUpdate() {
		var name=$.trim($('#name').val());
		var description =$('#description').val();
		if(name==""){
			$.error("版本名称不能为空")
			return false;
		}
		if(name.length>10){
			$.error("版本名称输入不可超过10字")
			return false;
		}
		if(description.length>250){
			$.error("版本简介输入不可超过250字")
			return false;
		}
			var loader = new loadLayer();
			var $id = $("#id").val();
			
			var $requestData = {};
			$requestData.name=name;
			$requestData.description=description;
			$requestData.publisherId=1;
			var url = "${ctp}/teach/textBookMaster/master/creatorVersion";
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
					}else if("dataRepeat" === data.info){
						$.error("名称重复");
					}else {
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