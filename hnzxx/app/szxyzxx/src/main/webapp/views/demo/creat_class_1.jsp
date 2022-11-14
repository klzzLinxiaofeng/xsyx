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
.table th,.table td{padding-left:10px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<div class="widget-container">
						<form class="form-horizontal">
							<table class="table white table-bordered">
								<thead>
									<tr>
										<th></th>
										<th>初一</th>
										<th>初二</th>
										<th>初三</th>
										<th>高一</th>
										<th>高二</th>
										<th>高三</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>语文</td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
									</tr>
									<tr>
										<td>数学</td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
									</tr>
									<tr>
										<td>英语</td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
										<td><input type="checkbox"></td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="form-actions" style="background-color:#297657;">
            	<div style="text-align:center;">
             		<button  class="btn btn-warning" type="button">保存</button>
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
