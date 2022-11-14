<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
.caozuo{padding-top:50px;}
.caozuo a{width:100px;height:27px;text-align:center;line-height:27px;display:block;color:#6D6E6F;background-color:#D0D0D0;margin-bottom:12px;}
.widget-head{border-bottom:0 none;}
.form-horizontal .control-label{width:80px;}
.form-horizontal .controls {
    margin-left: 100px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<div class="widget-head">
						<h3>选择查询条件</h3>
					</div>
					<div class="widget-container">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label">选择学生：</label>
								<div class="controls">
									<select><option>一年级</option><option>二年级</option><option>三年级</option><option>四年级</option><option>五年级</option><option>六年级</option></select>
								</div>
							</div>
						</form>
					</div>
					<div class="widget-head">
						<h3>选课课程</h3>
					</div>
					<div class="row-fluid" >
						<div class="span4" style="width:200px;margin-left:20px;">
							<select class="span12 select_left" multiple="multiple" style="height:250px;" >
								<option>计算机与科学</option>
								<option>粤语选修课</option>
								<option>计算机基础</option>
								<option>机电工程</option>
								<option>建筑学</option>
								<option>药理学</option>
								<option>卫生学</option>
								<option>当前国际形势</option>
								<option>国内趣事评论</option>
								<option>国际贸易</option>
								<option>工商管理</option>
								<option>青春生活</option>
								<option>篮球</option>
								<option>羽毛球</option>
								<option>交谊舞</option>
							</select>
						</div>
						<div class="span2 caozuo" style="margin-left:5px;width:100px;">
							<a href="javascript:void(0)" class="add_1">添加  &gt; </a>
							<a href="javascript:void(0)" class="delete_1">&lt; 删除</a>
							<a href="javascript:void(0)" class="all_1">全选  &gt; </a>
							<a href="javascript:void(0)" class="delete_all">&lt; 清空 </a>
						</div>
						<div class="span4" style="width:200px;margin-left:5px;">
							<select class="span12 select_right" multiple="multiple" style="height:250px;" >
							</select>
						</div>
						<div class="span2 caozuo" style="margin-left:5px;width:100px;padding-top:87px;">
							<a href="javascript:void(0)" class="up_1">上移</a>
							<a href="javascript:void(0)" class="down_1">下移</a>
						</div>
					</div>
					<div class="form-actions tan_bottom" >
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
		$(".add_1").click(function(){
				$(".select_left").find("option:selected").appendTo(".select_right");
		});
		$(".delete_1").click(function(){
				$(".select_right").find("option:selected").appendTo(".select_left");
		});
		$(".all_1").click(function(){
			if($(".select_left option").length!=0){
				$(".select_left option").appendTo(".select_right");
			}
		});
		$(".delete_all").click(function(){
			if($(".select_right option").length!=0){
				$(".select_right option").appendTo(".select_left");
			}
		});
		$(".up_1").click(function(){
			if($(".select_right option:selected").length==1){
				$curr =$(".select_right").find("option:selected");
				var text_1=$curr.text();
				var text_2=$curr.prev().text();
				$curr.text(text_2);
				$curr.prev().text(text_1);
				$(".select_right option").attr("selected",false)
				$curr.prev().attr("selected",true);
			}
		});
		$(".down_1").click(function(){
			if($(".select_right option:selected").length==1){
				$curr =$(".select_right").find("option:selected");
				var text_1=$curr.text();
				var text_2=$curr.next().text();
				$curr.text(text_2);
				$curr.next().text(text_1);
				$curr.attr("selected",false)
				$curr.next().attr("selected",true);
			}
		});
	});
   		
	</script>
</body>
</html>
