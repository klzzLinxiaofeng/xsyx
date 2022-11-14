<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
					<form class="form-horizontal tan_form" id="courseConfig_form" action="javascript:void(0);">
						<c:forEach items="${items}" var="item">
							 <div class="control-group">
								<label class="control-label">
									${item.courseNames}：
								</label>
								<div class="controls">
									<input type="text" id="${item.id}" class="span13" value="${item.maxNum}" placeholder="人数" />
								</div>
							</div>	
						</c:forEach>	
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function saveOrUpdate(){
		var list =[];
		
		$("input").each(function(){
			var obj = {};
			obj.id = $(this).attr("id");
			obj.maxNum = $(this).val();
			list.push(obj);
		});
		
		$.ajax({ 
			type:"POST", 
			url:"${ctp}/bbx/courseConfig/setLimitedNum", 
			dataType:"json",      
			contentType:"application/json",               
			data:JSON.stringify(list), 
			success:function(data){ 
				//alert(data.info);
				//var obj = eval("(" + data + ")");
				if("success" == data.info) {
					$.success("操作成功");
					parent.core_iframe.search();
					$.closeWindow();
				} else if("fail" == data.info){
					$.error("操作失败");
				}else {
					$.error("操作失败");
				}                  
			} 
		});
	}

</script>
</html>