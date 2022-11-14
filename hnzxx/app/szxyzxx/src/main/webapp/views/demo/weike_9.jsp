<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
				<div style="padding:0 20px;">
					<table class="table table-bordered table-striped">
						<thead><tr><th>学籍号</th><th>姓名</th><th>完成情况</th><th>完成时间</th><th>完成用时</th></tr></thead>
						<tbody><tr><td>G445112299006194584</td><td>周津</td><td>已完成</td><td>2015-05-25 19:00</td><td>30分00秒</td></tr></tbody>
					</table>
				</div>	
				<div class="py">
					<p class="p1">请输入评语</p>
					<div class="p2">使用模板 <select><option>请选择</option><option>完成的不错</option><option>继续努力</option></select></div>
					<textarea rows="" cols="" ></textarea>
					<p class="p4">还可以输入100字</p>
					<div class="clear"></div>
					<div class="sz_jl"><span>请选择奖励</span><ul class="star"><li><a href="javascript:void(0)"></a></li><li ><a href="javascript:void(0)"></a></li><li><a href="javascript:void(0)"></a></li></ul></div>
					
				</div>			
				<div class="form-actions tan_bottom">
					<button class="btn btn-warning" onclick="javascript:void(0)" type="button">发送</button>
					<button class="btn" onclick="javascript:void(0)" type="button">取消</button>
				</div>
						</div>
					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
	$(function(){
		/* 评星 */
		 $(".star li a").click(function(){
			var i=$(this).parent().index();
			$(".star li").removeClass("active");
			for(j=0;j<=i;j++){
				$(".star li").eq(j).addClass("active");
			}
		}); 
	});
</script>
</html>