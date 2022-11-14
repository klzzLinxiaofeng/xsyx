<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量创建班级</title>
<style type="text/css">

</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<div class="widget-container">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label">起始班号：</label>
								<div class="controls">
									<input type="text" class="first_num">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">待创建班级：</label>
								<div class="controls add_class">
									<div class="all_class"></div>
									<a href="javascript:void(0)" class="plus"><i class="fa fa-plus"></i></a>
								</div>
							</div>
						</form>
					</div>
					<div class="form-actions" style="background-color:#297657;">
            	<div style="text-align:center;">
             		<button  class="btn btn-danger" type="button">导出</button>
             		<button  class="btn" type="button">取消</button>
            	</div>
            </div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".add_class").on("click",".num",function(){
			$(this).toggleClass("on");
		});
		$(".add_class .plus").click(function(){
			var i=$(".first_num").val();
			var j=$(".num").length;
			var k=parseInt(i)+parseInt(j);
			$(".all_class").append('<a href="javascript:void(0)" class="num on"><span>'+k+'</span>班</a>');
		});
		$(".first_num").change( function() {
			var i=$(".first_num").val();
			var j=$(".num").length;
			var k=parseInt(i)+parseInt(j);
			$(".all_class").empty();
			for(var l=i;l<k;l++){
				$(".all_class").append('<a href="javascript:void(0)" class="num on"><span>'+l+'</span>班</a>');
			}
			});
	});
   		
	</script>
</body>
</html>
